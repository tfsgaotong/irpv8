package com.tfs.irp.solr.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.personalsearch.entity.IrpPersonalSearch;
import com.tfs.irp.solr.entity.DocumentForSolr;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.entity.MicroblogForSolr;
import com.tfs.irp.solr.entity.QuestionForSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class SolrSerivceImpl implements SolrService {

	private HttpSolrServer getSolrServer(String _m_sSolrUrl) {
		HttpSolrServer solrServer = new HttpSolrServer(_m_sSolrUrl);
		solrServer.setSoTimeout(1000);  // socket read timeout
		solrServer.setConnectionTimeout(100);
		solrServer.setDefaultMaxConnectionsPerHost(100);
		solrServer.setMaxTotalConnections(100);
		solrServer.setFollowRedirects(false);  // defaults to false
		// allowCompression defaults to false.
		// Server side must support gzip or deflate for this to have any effect.
		solrServer.setAllowCompression(true);
		solrServer.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
		return solrServer;
	}

	@Override
	public List searchDocument(String _keyWords, String _sSorlUrl,
			PageUtil pageUtil,Integer _sorttype,String rightIds) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		if(_keyWords==null){
			return new ArrayList();
		}
		if (_keyWords.equals("")) {
			return new ArrayList();
		}
		StringBuffer sQuery = new StringBuffer();
		sQuery.append("DOCTITLE:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || DOCCONTENT:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || DOCKEYWORDS:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || CRUSER:")
			.append(ClientUtils.escapeQueryChars(_keyWords));
		query.setQuery(sQuery.toString());
		query.setHighlight(true);
		query.setHighlightSimplePre(SysConfigUtil
				.getSysConfigValue(IrpSolr.STAYLE_START));
		query.setHighlightSimplePost(SysConfigUtil
				.getSysConfigValue(IrpSolr.STAYLE_END));
		query.setParam("hl.fl", "DOCTITLE,DOCCONTENT,DOCKEYWORDS,CRUSER");
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETMERGE));
		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETLENGTH));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE_STARTEND));
		
		query.setStart(pageUtil.getPageIndex());
		query.setRows(pageUtil.getPageSize());
		//在此处判断当前用户所拥有的查看栏目和查看该栏目下文章权限的所有docid
		if(rightIds!=null){
			query.setParam("fq",rightIds);
		}
		if(_sorttype!=null){
			if(_sorttype==1){
				query.setSort(SortClause.desc("CRTIME"));
			}	
		}
		
		
		QueryResponse queryResponse = getSolrServer(_sSorlUrl).query(query);
		
		SolrDocumentList documents = queryResponse.getResults();

		SolrDocumentList list = new SolrDocumentList();

		SolrDocument document = null;

		Map<String, Map<String, List<String>>> map = queryResponse
				.getHighlighting();
		
		//处理高亮显示
		for (int i = 0; i < documents.size(); i++) {
			document = documents.get(i);
			if(map.get(document.getFieldValue("id")).get("DOCTITLE")!=null){
				document.setField("DOCTITLE", map.get(document.getFieldValue("id")).get("DOCTITLE"));
			}
			if(map.get(document.getFieldValue("id")).get("DOCCONTENT")!=null){
				document.setField("DOCCONTENT", map.get(document.getFieldValue("id")).get("DOCCONTENT"));
			}
			if(map.get(document.getFieldValue("id")).get("DOCKEYWORDS")!=null){
				document.setField("DOCKEYWORDS", map.get(document.getFieldValue("id")).get("DOCKEYWORDS"));
			}
			if(map.get(document.getFieldValue("id")).get("CRUSER")!=null){
				document.setField("CRUSER", map.get(document.getFieldValue("id")).get("CRUSER"));
			}
			list.add(document);
		}
		return list;
	}
	
	public List searchDocumentByParam(String _keyWords, String _sSorlUrl,
			PageUtil pageUtil,Integer _sorttype,String rightIds,Map<String,String> paramMap) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		if(_keyWords==null){
			return new ArrayList();
		}
		if (_keyWords.equals("")) {
			return new ArrayList();
		}
		StringBuffer sQuery = new StringBuffer();
		sQuery.append("DOCTITLE:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || DOCCONTENT:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || DOCKEYWORDS:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || CRUSER:")
			.append(ClientUtils.escapeQueryChars(_keyWords));
		query.setQuery(sQuery.toString());
		query.setHighlight(true);
		query.setHighlightSimplePre(SysConfigUtil
				.getSysConfigValue(IrpSolr.STAYLE_START));
		query.setHighlightSimplePost(SysConfigUtil
				.getSysConfigValue(IrpSolr.STAYLE_END));
		query.setParam("hl.fl", "DOCTITLE,DOCCONTENT,DOCKEYWORDS,CRUSER");
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETMERGE));
		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETLENGTH));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE_STARTEND));
		
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-7DAYS TO NOW/DAY]");
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1MONTH TO NOW/DAY]");
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1YEAR TO NOW/DAY]");
				}
			}
		}
		
		query.setStart(pageUtil.getPageIndex());
		query.setRows(pageUtil.getPageSize());
		//在此处判断当前用户所拥有的查看栏目和查看该栏目下文章权限的所有docid
		if(rightIds!=null){
			query.setParam("fq",rightIds);
		}
		if(_sorttype!=null){
			if(_sorttype==1){
				query.setSort(SortClause.desc("CRTIME"));
			}
		}
		
		
		QueryResponse queryResponse = getSolrServer(_sSorlUrl).query(query);
		
		SolrDocumentList documents = queryResponse.getResults();

		SolrDocumentList list = new SolrDocumentList();

		SolrDocument document = null;

		Map<String, Map<String, List<String>>> map = queryResponse
				.getHighlighting();
		
		//处理高亮显示
		for (int i = 0; i < documents.size(); i++) {
			document = documents.get(i);
			if(map.get(document.getFieldValue("id")).get("DOCTITLE")!=null){
				document.setField("DOCTITLE", map.get(document.getFieldValue("id")).get("DOCTITLE"));
			}
			if(map.get(document.getFieldValue("id")).get("DOCCONTENT")!=null){
				document.setField("DOCCONTENT", map.get(document.getFieldValue("id")).get("DOCCONTENT"));
			}
			if(map.get(document.getFieldValue("id")).get("DOCKEYWORDS")!=null){
				document.setField("DOCKEYWORDS", map.get(document.getFieldValue("id")).get("DOCKEYWORDS"));
			}
			if(map.get(document.getFieldValue("id")).get("CRUSER")!=null){
				document.setField("CRUSER", map.get(document.getFieldValue("id")).get("CRUSER"));
			}
			list.add(document);
		}
		return list;
	}

	@Override
	public List searchMicroblog(String _keyWords, String _sSorlUrl,
			PageUtil pageUtil,Integer _sorttype) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery("MICROBLOGCONTENT:" + ClientUtils.escapeQueryChars(_keyWords));

		query.setHighlight(true);
		query.setParam("hl.fl", "MICROBLOGCONTENT");
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.MICR_SNIPPETMERGE));
		query.setHighlightSimplePre(SysConfigUtil
				.getSysConfigValue(IrpSolr.MICR_HIGHT_START));
		query.setHighlightSimplePost(SysConfigUtil
				.getSysConfigValue(IrpSolr.MICR_HIGHT_END));

		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.MICR_SNIPPET_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.MICR_LIGHTNUM));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.MICR_SNIPPET_STARTEND));

		query.setStart(pageUtil.getPageIndex());
		query.setRows(pageUtil.getPageSize());
		if(_sorttype!=null){
			if(_sorttype==1){
				query.setSort(SortClause.desc("CRTIME"));
			}
		}
		QueryResponse queryResponse = getSolrServer(_sSorlUrl).query(query);

		SolrDocumentList documents = queryResponse.getResults();

		SolrDocumentList list = new SolrDocumentList();

		SolrDocument document = null;

		Map<String, Map<String, List<String>>> map = queryResponse
				.getHighlighting();
		for (int i = 0; i < documents.size(); i++) {
			document = documents.get(i);

			document.setField(
					"MICROBLOGCONTENT",
					map.get(document.getFieldValue("id")).get(
							"MICROBLOGCONTENT"));

			list.add(document);
		}

		return list;
	}
	public List searchMicroblogByParam(String _keyWords, String _sSorlUrl,
			PageUtil pageUtil,Integer _sorttype,Map<String,String> paramMap) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery("MICROBLOGCONTENT:" + ClientUtils.escapeQueryChars(_keyWords));

		query.setHighlight(true);
		query.setParam("hl.fl", "MICROBLOGCONTENT");
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.MICR_SNIPPETMERGE));
		query.setHighlightSimplePre(SysConfigUtil
				.getSysConfigValue(IrpSolr.MICR_HIGHT_START));
		query.setHighlightSimplePost(SysConfigUtil
				.getSysConfigValue(IrpSolr.MICR_HIGHT_END));

		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.MICR_SNIPPET_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.MICR_LIGHTNUM));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.MICR_SNIPPET_STARTEND));

		query.setStart(pageUtil.getPageIndex());
		query.setRows(pageUtil.getPageSize());
		if(_sorttype!=null){
			if(_sorttype==1){
				query.setSort(SortClause.desc("CRTIME"));
			}
		}
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-7DAYS TO NOW/DAY]");
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1MONTH TO NOW/DAY]");
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1YEAR TO NOW/DAY]");
				}
			}
		}

		QueryResponse queryResponse = getSolrServer(_sSorlUrl).query(query);

		SolrDocumentList documents = queryResponse.getResults();

		SolrDocumentList list = new SolrDocumentList();

		SolrDocument document = null;

		Map<String, Map<String, List<String>>> map = queryResponse
				.getHighlighting();
		for (int i = 0; i < documents.size(); i++) {
			document = documents.get(i);

			document.setField(
					"MICROBLOGCONTENT",
					map.get(document.getFieldValue("id")).get(
							"MICROBLOGCONTENT"));

			list.add(document);
		}

		return list;
	}
	
	@Override
	public List searchQuestionByParam(String _keyWords, String _sSorlUrl,
			PageUtil pageUtil, Integer _sorttype,
			Map<String, String> paramMap) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		/*query.setQuery("TITLE:" + ClientUtils.escapeQueryChars(_keyWords)+"AND TEXTCONTENT:" +ClientUtils.escapeQueryChars(_keyWords));
		query.setHighlight(true);
		query.setParam("hl.fl", "TITLE TEXTCONTENT");*/
		
		query.setQuery("TITLE:" + ClientUtils.escapeQueryChars(_keyWords));
		query.setHighlight(true);
		query.setParam("hl.fl", "TITLE TEXTCONTENT");
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.QUESTION_SNIPPETMERGE));
		query.setHighlightSimplePre(SysConfigUtil
				.getSysConfigValue(IrpSolr.QUESTION_HIGHT_START));
		query.setHighlightSimplePost(SysConfigUtil
				.getSysConfigValue(IrpSolr.QUESTION_HIGHT_END));

		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.QUESTION_SNIPPET_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.QUESTION_LIGHTNUM));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.QUESTION_SNIPPET_STARTEND));

		query.setStart(pageUtil.getPageIndex());
		query.setRows(pageUtil.getPageSize());
		if(_sorttype!=null){
			if(_sorttype==1){
				query.setSort(SortClause.desc("CRTIME"));
			}	
		}
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-7DAYS TO NOW/DAY]");
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1MONTH TO NOW/DAY]");
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1YEAR TO NOW/DAY]");
				}
			}
		}
		QueryResponse queryResponse = getSolrServer(_sSorlUrl).query(query);

		SolrDocumentList questions = queryResponse.getResults();

		SolrDocumentList list = new SolrDocumentList();

		SolrDocument document = null;
		
		Map<String, Map<String, List<String>>> map = queryResponse
				.getHighlighting();
		for (int i = 0; i < questions.size(); i++) {
			document = questions.get(i);
			
			document.setField(
					"TITLE",
					map.get(document.getFieldValue("id")).get(
							"TITLE"));
			document.setField(
					"TEXTCONTENT",
					map.get(document.getFieldValue("id")).get(
							"TEXTCONTENT"));

			list.add(document);
		}
		return list;
	}
	
	@Override
	public List searchQuestion(String _keyWords, String _sSorlUrl,
			PageUtil pageUtil,Integer _sorttype) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery("TITLE:" + ClientUtils.escapeQueryChars(_keyWords)+"AND TEXTCONTENT:" +ClientUtils.escapeQueryChars(_keyWords));
		query.setHighlight(true);
		query.setParam("hl.fl", "TITLE TEXTCONTENT");
		
		
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.QUESTION_SNIPPETMERGE));
		query.setHighlightSimplePre(SysConfigUtil
				.getSysConfigValue(IrpSolr.QUESTION_HIGHT_START));
		query.setHighlightSimplePost(SysConfigUtil
				.getSysConfigValue(IrpSolr.QUESTION_HIGHT_END));

		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.QUESTION_SNIPPET_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.QUESTION_LIGHTNUM));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.QUESTION_SNIPPET_STARTEND));

		query.setStart(pageUtil.getPageIndex());
		query.setRows(pageUtil.getPageSize());
		if(_sorttype!=null){
			if(_sorttype==1){
				query.setSort(SortClause.desc("CRTIME"));
			}	
		}
		QueryResponse queryResponse = getSolrServer(_sSorlUrl).query(query);

		SolrDocumentList questions = queryResponse.getResults();

		SolrDocumentList list = new SolrDocumentList();

		SolrDocument document = null;
		
		Map<String, Map<String, List<String>>> map = queryResponse
				.getHighlighting();
		for (int i = 0; i < questions.size(); i++) {
			document = questions.get(i);
			
			document.setField(
					"TITLE",
					map.get(document.getFieldValue("id")).get(
							"TITLE"));
			document.setField(
					"TEXTCONTENT",
					map.get(document.getFieldValue("id")).get(
							"TEXTCONTENT"));

			list.add(document);
		}
		return list;
	}

	@Override
	public int searchDocumentNum(String _keyWords, String _sSorlUrl,String rightIds)
			throws SolrServerException {
		int docnum = 0;
		SolrQuery query = new SolrQuery();
		if(_keyWords==null){
			return 0;
		}
		if (_keyWords.equals("")) {
			return 0;
		}
		if(rightIds!=null){
			query.setParam("fq", rightIds);
		}
		StringBuffer sQuery = new StringBuffer();
		sQuery.append("DOCTITLE:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || DOCCONTENT:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || DOCKEYWORDS:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || CRUSER:")
			.append(ClientUtils.escapeQueryChars(_keyWords));
		query.setQuery(sQuery.toString());
		docnum = (int) getSolrServer(_sSorlUrl).query(query).getResults()
				.getNumFound();
		return docnum;
	}
	
	public int searchDocumentnumByParam(String _keyWords,String _sSorlUrl,String rightIds,Map<String,String> paramMap) throws SolrServerException{
		int docnum = 0;
		SolrQuery query = new SolrQuery();
		if(_keyWords==null){
			return 0;
		}
		if (_keyWords.equals("")) {
			return 0;
		}
		if(rightIds!=null){
			query.setParam("fq", rightIds);
		}
		StringBuffer sQuery = new StringBuffer();
		sQuery.append("DOCTITLE:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || DOCCONTENT:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || DOCKEYWORDS:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			.append(" || CRUSER:")
			.append(ClientUtils.escapeQueryChars(_keyWords));
		query.setQuery(sQuery.toString());
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-7DAYS TO NOW/DAY]");
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1MONTH TO NOW/DAY]");
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1YEAR TO NOW/DAY]");
				}
			}
		}

		docnum = (int) getSolrServer(_sSorlUrl).query(query).getResults()
				.getNumFound();
		return docnum;
	}

	@Override
	public int searchMicroblogNum(String _keyWords, String _sSorlUrl)
			throws SolrServerException {
		int micNum = 0;
		SolrQuery query = new SolrQuery();

		query.setQuery("MICROBLOGCONTENT:" + ClientUtils.escapeQueryChars(_keyWords));
		micNum = (int) getSolrServer(_sSorlUrl).query(query).getResults()
				.getNumFound();

		return micNum;
	}
	public int searchMicroblogNumByParam(String _keyWords, String _sSorlUrl,Map<String,String> paramMap)
			throws SolrServerException {
		int micNum = 0;
		SolrQuery query = new SolrQuery();

		query.setQuery("MICROBLOGCONTENT:" + ClientUtils.escapeQueryChars(_keyWords));
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-7DAYS TO NOW/DAY]");
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1MONTH TO NOW/DAY]");
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1YEAR TO NOW/DAY]");
				}
			}
		}

		micNum = (int) getSolrServer(_sSorlUrl).query(query).getResults()
				.getNumFound();

		return micNum;
	}



	@Override
	public int searchQuestionNum(String _keyWords, String _sSorlUrl)
			throws SolrServerException {
		int quesNum = 0;
		SolrQuery query = new SolrQuery();

		query.setQuery("TITLE:" + ClientUtils.escapeQueryChars(_keyWords));
		
		quesNum = (int) getSolrServer(_sSorlUrl).query(query).getResults()
				.getNumFound();
		return quesNum;
	}
	@Override
	public int searchQuestionNumByParam(String searchInfo,
			String _sSorlUrl, Map<String, String> paramMap) throws SolrServerException{
			int quesNum = 0;
			SolrQuery query = new SolrQuery();

			query.setQuery("TITLE:" + ClientUtils.escapeQueryChars(searchInfo));
			if(paramMap!=null){
				if(paramMap.get("CRTIME")!=null){//最近一周
					if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
						query.addFilterQuery("CRTIME:[NOW/DAY-7DAYS TO NOW/DAY]");
					}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
						query.addFilterQuery("CRTIME:[NOW/DAY-1MONTH TO NOW/DAY]");
					}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
						query.addFilterQuery("CRTIME:[NOW/DAY-1YEAR TO NOW/DAY]");
					}
				}
			}
			quesNum = (int) getSolrServer(_sSorlUrl).query(query).getResults()
					.getNumFound();
			return quesNum;
	}

	
	@Override
	public List<Long> searchIdByStr(String _keyWords, Integer _length,String _location) throws SolrServerException {
		List<Long> list = new ArrayList();
		SolrQuery queryids = new SolrQuery();
		if (_keyWords.equals("")) {
			_keyWords = "*";
			queryids.setQuery("DOCCONTENT:" + ClientUtils.escapeQueryChars(_keyWords));
		} else {
			queryids.setQuery("DOCCONTENT:" + ClientUtils.escapeQueryChars(_keyWords));
		}
		queryids.setStart(1);
		queryids.setRows(_length);
		queryids.setParam("hl.fl", "DOCCONTENT");
		QueryResponse queryResponse =   getSolrServer(_location).query(queryids);
		SolrDocumentList documents = queryResponse.getResults();
		for (int i = 0; i < documents.size(); i++) {
			list.add(Long.parseLong(documents.get(i).getFieldValue("id").toString()));
			
		}
		return list;
	}
	
	

	@Override
	public int deleteSolrIndex(Long _id, String _solrurl) {
		int status = 0;
		SolrServer solrServer = getSolrServer(_solrurl);
		try {
			if(_id!=null){
				solrServer.deleteById(String.valueOf(_id));	
				solrServer.commit();
				status = 1;
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return status;
	}

	@Override
	public int deleteSolrIndex(List<String> _ids, String _solrurl) {
		int status = 0;
		SolrServer solrServer = getSolrServer(_solrurl);
		try {
			if(_ids.size()>0 && _ids!=null){
				solrServer.deleteById(_ids);
				solrServer.commit();
				status = 1;
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public int addMicroblogIndex(MicroblogForSolr _microblogForSolr,String _solrurl) {
		int status = 0;
		if(_microblogForSolr!=null){
			SolrServer solrServer = getSolrServer(_solrurl);
			SolrInputDocument sid = new SolrInputDocument();
	    	sid.addField("id",_microblogForSolr.getMICROBLOGID());
	    	sid.addField("MICROBLOGID",_microblogForSolr.getMICROBLOGID());
	    	sid.addField("USERID",_microblogForSolr.getUSERID());
	    	sid.addField("CRTIME",_microblogForSolr.getCRTIME());
	    	sid.addField("FROMDATA",_microblogForSolr.getFROMDATA());
	    	sid.addField("MICROBLOGCONTENT",_microblogForSolr.getMICROBLOGCONTENT());
	    	try {
				solrServer.add(sid);
				solrServer.commit();
				status = 1;
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public int addDocumentIndex(DocumentForSolr _documentForSolr,
			String _solrurl) {
		int status = 0;
		if(_documentForSolr!=null){
			SolrServer solrServer = getSolrServer(_solrurl);
			SolrInputDocument sid = new SolrInputDocument();
			sid.addField("id", _documentForSolr.getDOCID());
			sid.addField("DOCID", _documentForSolr.getDOCID());
			sid.addField("DOCTITLE", _documentForSolr.getDOCTITLE());
			sid.addField("DOCCONTENT", _documentForSolr.getDOCCONTENT());
			sid.addField("DOCKEYWORDS", _documentForSolr.getDOCKEYWORDS());
			sid.addField("CRTIME", _documentForSolr.getCRTIME());
			sid.addField("CRUSER", _documentForSolr.getCRUSER());
			sid.addField("CRUSERID", _documentForSolr.getCRUSERID());
			sid.addField("SITEID",_documentForSolr.getSITEID());
			sid.addField("CHANNELID", _documentForSolr.getCHANNELID());
			try {
				solrServer.add(sid);
				solrServer.commit();
				status = 1;
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public int addQuestionIndex(QuestionForSolr _questionForSolr,
			String _solrurl) {
		int status = 0;
		if(_questionForSolr!=null){
			SolrServer solrServer = getSolrServer(_solrurl);
			SolrInputDocument sid = new SolrInputDocument();
			sid.addField("id", _questionForSolr.getQUESTIONID());
			sid.addField("QUESTIONID", _questionForSolr.getQUESTIONID());
			sid.addField("CRUSERID", _questionForSolr.getCRUSERID());
			sid.addField("TITLE", _questionForSolr.getTITLE());
			sid.addField("TEXTCONTENT", _questionForSolr.getTEXTCONTENT());
			sid.addField("PARENTID", _questionForSolr.getPARENTID());
			sid.addField("CRUSER", _questionForSolr.getCRUSER());
			sid.addField("CRTIME", _questionForSolr.getCRTIME());
			try {
				solrServer.add(sid);
				solrServer.commit();
				status = 1;
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return status;
	}

	@Override
	public int updateMicroblogIndex(MicroblogForSolr _microblogForSolr,
			String _solrurl) {
		int status = 0;
		status =addMicroblogIndex(_microblogForSolr,_solrurl);
		return status;
	}

	@Override
	public int updateDocumentIndex(DocumentForSolr _documentForSolr,
			String _solrurl) {
		int status = 0;
		status = addDocumentIndex(_documentForSolr,_solrurl);
		return status;
	}

	@Override
	public int updateQuestionIndex(QuestionForSolr _questionForSolr,
			String _solrurl) {
		int status = 0;
		status = addQuestionIndex(_questionForSolr,_solrurl);
		return status;
	}
	
	@Override
	public int advancedSearchDocumentNum(Map<String, String> _keyParams, String _sSorlUrl,String rightIds)
			throws SolrServerException {
		int docnum = 0;
		if(_keyParams==null || _keyParams.size()==0){
			return 0;
		}
		SolrQuery query = new SolrQuery();
		StringBuffer sQuery = new StringBuffer();
		
		if(rightIds!=null){
			query.setParam("fq", rightIds);
		}
		List<String> arrSearchFiled = new ArrayList<String>();
		//获得检索字段
		String sSearchFiled = _keyParams.get("searchFiled");
		if(sSearchFiled!=null && sSearchFiled.trim().length()>0){
			if(sSearchFiled.equals("ALL")){
				arrSearchFiled.add("DOCTITLE");
				arrSearchFiled.add("DOCCONTENT");
				arrSearchFiled.add("DOCKEYWORDS");
				arrSearchFiled.add("DOCABSTRACT");
				arrSearchFiled.add("CRUSER");
			}else{
				arrSearchFiled.add(sSearchFiled);
			}
		}
		
		//处理关键词完全匹配
		String sAllTxt = _keyParams.get("allTxt");
		if(sAllTxt!=null && sAllTxt.trim().length()>0){
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" || ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(")
					.append("\"")
					.append(sAllTxt)
					.append("\")");
			}
			sQuery.append(")");
		}
		
		//处理关键词分词匹配
		String sOneTxt = _keyParams.get("oneTxt");
		if(sOneTxt!=null && sOneTxt.trim().length()>0){
			if(sQuery.length()>0){
				sQuery.append(" && ");
			}
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" || ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(")
					.append(sOneTxt)
					.append(")");
			}
			sQuery.append(")");
		}
		
		//处理不等于的逻辑
		String sNoqTxt = _keyParams.get("noqTxt");
		if(sNoqTxt!=null && sNoqTxt.trim().length()>0){
			if(sQuery.length()>0){
				sQuery.append(" && ");
			}
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" && ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(* -\"")
					.append(sNoqTxt)
					.append("\")");
			}
			sQuery.append(")");
		}
		
		String sChannelId = _keyParams.get("chnlid");
		if(sChannelId!=null && sChannelId.trim().length()>0){
			try {
				long nChannelId = Long.parseLong(sChannelId);
				if(nChannelId>0){
					if(sQuery.length()>0){
						sQuery.append(" && ");
					}
					sQuery.append("(CHANNELID:")
						.append(nChannelId)
						.append(")");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		String sCrTime = _keyParams.get("crtime");
		if(sCrTime!=null && sCrTime.trim().length()>0 && !sCrTime.trim().equals("no")){
			Date dStartTime = null;
			Date dEndTime = null;
			if(sCrTime.equals("custom")){
				dStartTime = DateUtils.getDateByStrToFormat("yyyy-MM-dd", _keyParams.get("startTime"));
				dEndTime = DateUtils.getDateByStrToFormat("yyyy-MM-dd", _keyParams.get("endTime"));
			}else{
				Calendar c = Calendar.getInstance();
				if(sCrTime.equals("d")){
					c.add(Calendar.DATE, -1);
				}else if(sCrTime.equals("w")){
					c.add(Calendar.WEDNESDAY, -1);
				}else if(sCrTime.equals("m")){
					c.add(Calendar.MONTH, -1);
				}else if(sCrTime.equals("y")){
					c.add(Calendar.YEAR, -1);
				}
				dStartTime = c.getTime();
				dEndTime = DateUtils.getNOWTime();
			}
			
			if(dStartTime!=null && dEndTime!=null){
				if(sQuery.length()>0){
					sQuery.append(" && ");
				}
				String sFormatStr = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
				sQuery.append("(CRTIME:[")
					.append(DateUtils.getDateByFormat(dStartTime, sFormatStr))
					.append(" TO ")
					.append(DateUtils.getDateByFormat(dEndTime, sFormatStr))
					.append("])");
			}
		}
		query.setQuery(sQuery.toString());
		docnum = (int) getSolrServer(_sSorlUrl).query(query).getResults()
				.getNumFound();
		return docnum;
	}
	
	@Override
	public List advancedSearchDocument(Map<String, String> _keyParams, String _sSorlUrl, PageUtil pageUtil,Integer _sorttype,String rightIds) throws SolrServerException {
		if(_keyParams==null || _keyParams.size()==0){
			return new ArrayList();
		}
		SolrQuery query = new SolrQuery();
		StringBuffer sQuery = new StringBuffer();
		
		List<String> arrSearchFiled = new ArrayList<String>();
		//获得检索字段
		String sSearchFiled = _keyParams.get("searchFiled");
		if(sSearchFiled!=null && sSearchFiled.trim().length()>0){
			if(sSearchFiled.equals("ALL")){
				arrSearchFiled.add("DOCTITLE");
				arrSearchFiled.add("DOCCONTENT");
				arrSearchFiled.add("DOCKEYWORDS");
				arrSearchFiled.add("DOCABSTRACT");
				arrSearchFiled.add("CRUSER");
			}else{
				arrSearchFiled.add(sSearchFiled);
			}
		}
		
		//处理关键词完全匹配
		String sAllTxt = _keyParams.get("allTxt");
		if(sAllTxt!=null && sAllTxt.trim().length()>0){
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" || ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(")
					.append("\"")
					.append(sAllTxt)
					.append("\")");
			}
			sQuery.append(")");
		}
		
		//处理关键词分词匹配
		String sOneTxt = _keyParams.get("oneTxt");
		if(sOneTxt!=null && sOneTxt.trim().length()>0){
			if(sQuery.length()>0){
				sQuery.append(" && ");
			}
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" || ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(")
					.append(sOneTxt)
					.append(")");
			}
			sQuery.append(")");
		}
		
		//处理不等于的逻辑
		String sNoqTxt = _keyParams.get("noqTxt");
		if(sNoqTxt!=null && sNoqTxt.trim().length()>0){
			if(sQuery.length()>0){
				sQuery.append(" && ");
			}
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" && ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(* -\"")
					.append(sNoqTxt)
					.append("\")");
			}
			sQuery.append(")");
		}
		
		String sChannelId = _keyParams.get("chnlid");
		if(sChannelId!=null && sChannelId.trim().length()>0){
			try {
				long nChannelId = Long.parseLong(sChannelId);
				if(nChannelId>0){
					if(sQuery.length()>0){
						sQuery.append(" && ");
					}
					sQuery.append("(CHANNELID:")
						.append(nChannelId)
						.append(")");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		String sCrTime = _keyParams.get("crtime");
		if(sCrTime!=null && sCrTime.trim().length()>0 && !sCrTime.trim().equals("no")){
			Date dStartTime = null;
			Date dEndTime = null;
			if(sCrTime.equals("custom")){
				dStartTime = DateUtils.getDateByStrToFormat("yyyy-MM-dd", _keyParams.get("startTime"));
				dEndTime = DateUtils.getDateByStrToFormat("yyyy-MM-dd", _keyParams.get("endTime"));
			}else{
				Calendar c = Calendar.getInstance();
				if(sCrTime.equals("d")){
					c.add(Calendar.DATE, -1);
				}else if(sCrTime.equals("w")){
					c.add(Calendar.WEDNESDAY, -1);
				}else if(sCrTime.equals("m")){
					c.add(Calendar.MONTH, -1);
				}else if(sCrTime.equals("y")){
					c.add(Calendar.YEAR, -1);
				}
				dStartTime = c.getTime();
				dEndTime = DateUtils.getNOWTime();
			}
			
			if(dStartTime!=null && dEndTime!=null){
				if(sQuery.length()>0){
					sQuery.append(" && ");
				}
				String sFormatStr = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
				sQuery.append("(CRTIME:[")
					.append(DateUtils.getDateByFormat(dStartTime, sFormatStr))
					.append(" TO ")
					.append(DateUtils.getDateByFormat(dEndTime, sFormatStr))
					.append("])");
			}
		}
		
		query.setQuery(sQuery.toString());
		query.setHighlight(true);
		query.setHighlightSimplePre(SysConfigUtil
				.getSysConfigValue(IrpSolr.STAYLE_START));
		query.setHighlightSimplePost(SysConfigUtil
				.getSysConfigValue(IrpSolr.STAYLE_END));
		query.setParam("hl.fl", "DOCTITLE,DOCCONTENT,DOCKEYWORDS,CRUSER");
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETMERGE));
		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETLENGTH));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE_STARTEND));
		query.setStart(pageUtil.getPageIndex());
		query.setRows(pageUtil.getPageSize());
		//在此处判断当前用户所拥有的查看栏目和查看该栏目下文章权限的所有docid
		if(rightIds!=null){
			query.setParam("fq",rightIds);
		}
		if(_sorttype!=null){
			if(_sorttype==1){
				query.setSort(SortClause.desc("CRTIME"));
			}	
		}
		QueryResponse queryResponse = getSolrServer(_sSorlUrl).query(query);
		
		SolrDocumentList documents = queryResponse.getResults();

		SolrDocumentList list = new SolrDocumentList();

		SolrDocument document = null;

		Map<String, Map<String, List<String>>> map = queryResponse
				.getHighlighting();
		
		//处理高亮显示
		for (int i = 0; i < documents.size(); i++) {
			document = documents.get(i);
			if(map.get(document.getFieldValue("id")).get("DOCTITLE")!=null){
				document.setField("DOCTITLE", map.get(document.getFieldValue("id")).get("DOCTITLE"));
			}
			if(map.get(document.getFieldValue("id")).get("DOCCONTENT")!=null){
				document.setField("DOCCONTENT", map.get(document.getFieldValue("id")).get("DOCCONTENT"));
			}
			if(map.get(document.getFieldValue("id")).get("DOCKEYWORDS")!=null){
				document.setField("DOCKEYWORDS", map.get(document.getFieldValue("id")).get("DOCKEYWORDS"));
			}
			if(map.get(document.getFieldValue("id")).get("CRUSER")!=null){
				document.setField("CRUSER", map.get(document.getFieldValue("id")).get("CRUSER"));
			}
			list.add(document);
		}
		return list;
	}
	
	@Override
	public List searchSubject(IrpPersonalSearch _personalSearch, String _sSorlUrl, PageUtil pageUtil, String rightIds) throws SolrServerException {
		if(_personalSearch==null){
			return new ArrayList();
		}
		SolrQuery query = new SolrQuery();
		StringBuffer sQuery = new StringBuffer();
		
		List<String> arrSearchFiled = new ArrayList<String>();
		//获得检索字段
		String sSearchFiled = _personalSearch.getSearchfiled();
		if(sSearchFiled!=null && sSearchFiled.trim().length()>0){
			if(sSearchFiled.equals("ALL")){
				arrSearchFiled.add("DOCTITLE");
				arrSearchFiled.add("DOCCONTENT");
				arrSearchFiled.add("DOCKEYWORDS");
				arrSearchFiled.add("DOCABSTRACT");
				arrSearchFiled.add("CRUSER");
			}else{
				arrSearchFiled.add(sSearchFiled);
			}
		}
		
		//处理关键词完全匹配
		String sAllTxt = _personalSearch.getSearchdalltxt();
		if(sAllTxt!=null && sAllTxt.trim().length()>0){
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" || ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(")
					.append("\"")
					.append(sAllTxt)
					.append("\")");
			}
			sQuery.append(")");
		}
		
		//处理关键词分词匹配
		String sOneTxt = _personalSearch.getSearchdonetxt();
		if(sOneTxt!=null && sOneTxt.trim().length()>0){
			if(sQuery.length()>0){
				sQuery.append(" && ");
			}
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" || ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(")
					.append(sOneTxt)
					.append(")");
			}
			sQuery.append(")");
		}
		
		//处理不等于的逻辑
		String sNoqTxt = _personalSearch.getSearchdnoqtxt();
		if(sNoqTxt!=null && sNoqTxt.trim().length()>0){
			if(sQuery.length()>0){
				sQuery.append(" && ");
			}
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" && ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(* -\"")
					.append(sNoqTxt)
					.append("\")");
			}
			sQuery.append(")");
		}
		
		Long nChannelId = _personalSearch.getSearchdchnlid();
		if(nChannelId!=null && nChannelId>0L){
			if(sQuery.length()>0){
				sQuery.append(" && ");
			}
			sQuery.append("(CHANNELID:")
				.append(nChannelId.longValue())
				.append(")");
		}
		
		String sCrTime = _personalSearch.getSearchdcrtime();
		if(sCrTime!=null && sCrTime.trim().length()>0 && !sCrTime.trim().equals("no")){
			Date dStartTime = null;
			Date dEndTime = null;
			if(sCrTime.equals("custom")){
				dStartTime = DateUtils.getDateByStrToFormat("yyyy-MM-dd", _personalSearch.getSearchdstarttime());
				dEndTime = DateUtils.getDateByStrToFormat("yyyy-MM-dd", _personalSearch.getSearchdendtime());
			}else{
				Calendar c = Calendar.getInstance();
				if(sCrTime.equals("d")){
					c.add(Calendar.DATE, -1);
				}else if(sCrTime.equals("w")){
					c.add(Calendar.WEDNESDAY, -1);
				}else if(sCrTime.equals("m")){
					c.add(Calendar.MONTH, -1);
				}else if(sCrTime.equals("y")){
					c.add(Calendar.YEAR, -1);
				}
				dStartTime = c.getTime();
				dEndTime = DateUtils.getNOWTime();
			}
			
			if(dStartTime!=null && dEndTime!=null){
				if(sQuery.length()>0){
					sQuery.append(" && ");
				}
				String sFormatStr = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
				sQuery.append("(CRTIME:[")
					.append(DateUtils.getDateByFormat(dStartTime, sFormatStr))
					.append(" TO ")
					.append(DateUtils.getDateByFormat(dEndTime, sFormatStr))
					.append("])");
			}
		}
		
		query.setQuery(sQuery.toString());
		query.setParam("hl.fl", "DOCTITLE,DOCCONTENT,DOCKEYWORDS,CRUSER");
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETMERGE));
		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETLENGTH));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE_STARTEND));
		query.setStart(pageUtil.getPageIndex());
		query.setRows(pageUtil.getPageSize());
		//在此处判断当前用户所拥有的查看栏目和查看该栏目下文章权限的所有docid
		if(rightIds!=null){
			query.setParam("fq",rightIds);
		}
		if(_personalSearch.getSearchsort()!=null && _personalSearch.getSearchsort().equals("1")){
			query.setSort(SortClause.desc("CRTIME"));	
		}
		QueryResponse queryResponse = getSolrServer(_sSorlUrl).query(query);
		SolrDocumentList documents = queryResponse.getResults();
		return documents;
	}
	
	@Override
	public long searchSubjectCount(IrpPersonalSearch _personalSearch, String _sSorlUrl, String rightIds) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		StringBuffer sQuery = new StringBuffer();
		
		List<String> arrSearchFiled = new ArrayList<String>();
		//获得检索字段
		String sSearchFiled = _personalSearch.getSearchfiled();
		if(sSearchFiled!=null && sSearchFiled.trim().length()>0){
			if(sSearchFiled.equals("ALL")){
				arrSearchFiled.add("DOCTITLE");
				arrSearchFiled.add("DOCCONTENT");
				arrSearchFiled.add("DOCKEYWORDS");
				arrSearchFiled.add("DOCABSTRACT");
				arrSearchFiled.add("CRUSER");
			}else{
				arrSearchFiled.add(sSearchFiled);
			}
		}
		
		//处理关键词完全匹配
		String sAllTxt = _personalSearch.getSearchdalltxt();
		if(sAllTxt!=null && sAllTxt.trim().length()>0){
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" || ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(")
					.append("\"")
					.append(sAllTxt)
					.append("\")");
			}
			sQuery.append(")");
		}
		
		//处理关键词分词匹配
		String sOneTxt = _personalSearch.getSearchdonetxt();
		if(sOneTxt!=null && sOneTxt.trim().length()>0){
			if(sQuery.length()>0){
				sQuery.append(" && ");
			}
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" || ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(")
					.append(sOneTxt)
					.append(")");
			}
			sQuery.append(")");
		}
		
		//处理不等于的逻辑
		String sNoqTxt = _personalSearch.getSearchdnoqtxt();
		if(sNoqTxt!=null && sNoqTxt.trim().length()>0){
			if(sQuery.length()>0){
				sQuery.append(" && ");
			}
			sQuery.append("(");
			for (int i=0;i<arrSearchFiled.size();i++) {
				if(i>0){
					sQuery.append(" && ");
				}
				sQuery.append(arrSearchFiled.get(i))
					.append(":(* -\"")
					.append(sNoqTxt)
					.append("\")");
			}
			sQuery.append(")");
		}
		
		Long nChannelId = _personalSearch.getSearchdchnlid();
		if(nChannelId!=null && nChannelId>0L){
			if(sQuery.length()>0){
				sQuery.append(" && ");
			}
			sQuery.append("(CHANNELID:")
				.append(nChannelId.longValue())
				.append(")");
		}
		
		String sCrTime = _personalSearch.getSearchdcrtime();
		if(sCrTime!=null && sCrTime.trim().length()>0 && !sCrTime.trim().equals("no")){
			Date dStartTime = null;
			Date dEndTime = null;
			if(sCrTime.equals("custom")){
				dStartTime = DateUtils.getDateByStrToFormat("yyyy-MM-dd", _personalSearch.getSearchdstarttime());
				dEndTime = DateUtils.getDateByStrToFormat("yyyy-MM-dd", _personalSearch.getSearchdendtime());
			}else{
				Calendar c = Calendar.getInstance();
				if(sCrTime.equals("d")){
					c.add(Calendar.DATE, -1);
				}else if(sCrTime.equals("w")){
					c.add(Calendar.WEDNESDAY, -1);
				}else if(sCrTime.equals("m")){
					c.add(Calendar.MONTH, -1);
				}else if(sCrTime.equals("y")){
					c.add(Calendar.YEAR, -1);
				}
				dStartTime = c.getTime();
				dEndTime = DateUtils.getNOWTime();
			}
			
			if(dStartTime!=null && dEndTime!=null){
				if(sQuery.length()>0){
					sQuery.append(" && ");
				}
				String sFormatStr = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
				sQuery.append("(CRTIME:[")
					.append(DateUtils.getDateByFormat(dStartTime, sFormatStr))
					.append(" TO ")
					.append(DateUtils.getDateByFormat(dEndTime, sFormatStr))
					.append("])");
			}
		}
		
		query.setQuery(sQuery.toString());
		query.setParam("hl.fl", "DOCTITLE,DOCCONTENT,DOCKEYWORDS,CRUSER");
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETMERGE));
		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETLENGTH));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE_STARTEND));
		//在此处判断当前用户所拥有的查看栏目和查看该栏目下文章权限的所有docid
		if(rightIds!=null){
			query.setParam("fq",rightIds);
		}
		if(_personalSearch.getSearchsort()!=null && _personalSearch.getSearchsort().equals("1")){
			query.setSort(SortClause.desc("CRTIME"));	
		}
		return getSolrServer(_sSorlUrl).query(query).getResults().getNumFound();
	}
	
	@Override
	public List<String> spellSearch(String _sSearchWord) {
		List<String> suggestedWordList = null;
		SolrQuery query = new SolrQuery();
		query.set("qt", "/suggest");  
		query.set("q", _sSearchWord);  
		query.set("spellcheck.count", "5");// 返回数量
		query.set("spellcheck.build", "true");  
		QueryResponse queryResponse=null;
		try {
			String s=SysConfigUtil.getSysConfigValue("SOLR_APP_URL");
			s=s+"/enterprisesitedoc";
			System.out.println(s);
			queryResponse = getSolrServer(s).query(query);
			SpellCheckResponse spellCheckResponse = queryResponse.getSpellCheckResponse();
			if (spellCheckResponse != null) {  
	            List<Suggestion> suggestionList = spellCheckResponse.getSuggestions();  
	            for (Suggestion suggestion : suggestionList) {
	                suggestedWordList = suggestion.getAlternatives();  
	            }  
	        }
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return suggestedWordList;
	}
	@Override
	public List<String> spellSearchByCore(String _sSearchWord, Integer searchtype) {
		String url="";
		if(searchtype==null||searchtype==0){
			url=SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE);
		}else if (searchtype==5) {
			url=SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE);
		}
		else if (searchtype==2) {
			url=SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG);
		}
		else if (searchtype==6) {
			url=SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION);
		}else{
			url=SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE);
		}
		List<String> suggestedWordList = null;
		SolrQuery query = new SolrQuery();
		query.set("qt", "/suggest");  
		query.set("q", _sSearchWord);  
		query.set("spellcheck.count", "5");// 返回数量
		query.set("spellcheck.build", "true");  
		QueryResponse queryResponse=null;
		try {
			/*String s=SysConfigUtil.getSysConfigValue("SOLR_APP_URL");
			s=s+"/enterprisesitedoc";
			System.out.println(s);*/
			queryResponse = getSolrServer(url).query(query);
			SpellCheckResponse spellCheckResponse = queryResponse.getSpellCheckResponse();
			if (spellCheckResponse != null) {  
	            List<Suggestion> suggestionList = spellCheckResponse.getSuggestions();  
	            for (Suggestion suggestion : suggestionList) {
	                suggestedWordList = suggestion.getAlternatives();  
	            }  
	        }
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return suggestedWordList;
	}
	@Override
	public List<IrpDocument> relativeDocByKeyword(IrpDocument _document, String rightIds) throws SolrServerException {
		if(_document==null || _document.getDockeywords()==null || _document.getDockeywords().isEmpty()){
			return new ArrayList<IrpDocument>();
		}
		SolrQuery query = new SolrQuery();
		StringBuffer sQuery = new StringBuffer();
		sQuery.append("DOCKEYWORDS:")
			.append(ClientUtils.escapeQueryChars(_document.getDockeywords()))
			.append(" && DOCID:(* -")
			.append(_document.getDocid())
			.append(")");
		query.setQuery(sQuery.toString());
		query.setParam("hl.fl", "DOCKEYWORDS");
		query.setParam("hl.mergeContiguous",SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETMERGE));
		query.setParam("hl.fragsize",SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE));
		query.setParam("hl.maxAnalyzedChars",SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETLENGTH));
		query.setParam("hl.regex.slop",SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE_STARTEND));
		query.setStart(0);
		query.setRows(10);
		//在此处判断当前用户所拥有的查看栏目和查看该栏目下文章权限的所有doc!d
		if(rightIds!=null && !rightIds.isEmpty()){
			query.setParam("fq",rightIds);
		}
		QueryResponse queryResponse = getSolrServer(SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE)).query(query);
		SolrDocumentList documents = queryResponse.getResults();
		List<IrpDocument> list = new ArrayList<IrpDocument>();
		
		for (SolrDocument solrDocument : documents) {
			if(solrDocument==null)
				continue;
			IrpDocument document = new IrpDocument();
			document.setDocid(Long.valueOf(solrDocument.getFieldValue("DOCID").toString()));
			document.setSiteid(Long.valueOf(solrDocument.getFieldValue("SITEID").toString()));
			document.setChannelid(Long.valueOf(solrDocument.getFieldValue("CHANNELID").toString()));
			
			document.setDoctitle(stringSolrField(solrDocument.getFieldValue("DOCTITLE")));
			document.setDockeywords(stringSolrField(solrDocument.getFieldValue("DOCKEYWORDS")));
			document.setDocabstract(stringSolrField(solrDocument.getFieldValue("DOCABSTRACT")));
			list.add(document);
		}
		return list;
	}
	
	private String stringSolrField(Object _fieldValue){
		if(_fieldValue==null || _fieldValue.toString().isEmpty() || _fieldValue.toString().indexOf("[")!=0 || _fieldValue.toString().indexOf("]")!=(_fieldValue.toString().length()-1))
			return _fieldValue==null?"":_fieldValue.toString();
		else
			return _fieldValue.toString().substring(1, _fieldValue.toString().length()-1);
	}
	
	@Override
	public List<IrpDocument> similarityDocByDocId(long _nDocId) {
		List<IrpDocument> arrDocuments = new ArrayList<IrpDocument>();
        SolrQuery query = new SolrQuery();
        query.setQuery("DOCID:"+_nDocId)
            .setParam("fl", "DOCID,DOCTITLE")
	        .setParam("mlt", true)
	        .setParam("mlt.fl", "DOCCONTENT,DOCTITLE")
	        .setParam("mlt.qf", "DOCCONTENT^2.0 DOCTITLE^10.0")
            .setParam("mlt.mindf", "1")
            .setParam("mlt.mintf", "1")
            .setParam("mlt.count", "5");
        
        //非空判断
        QueryResponse response = null;
		try {
			response = getSolrServer(SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE)).query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
        if(response==null){
        	return arrDocuments;
        }
        NamedList<Object> list = response.getResponse();
        if(list==null || list.size()==0){
        	return arrDocuments;
        }
        Object moreLikeThis = list.get("moreLikeThis");
        if(moreLikeThis==null){
        	return arrDocuments;
        }
        	
        @SuppressWarnings("unchecked")
        SimpleOrderedMap<SolrDocumentList> mltResults = (SimpleOrderedMap<SolrDocumentList>) moreLikeThis;
        for (int i=0; i<mltResults.size(); i++) {
            SolrDocumentList solrDocumentList = mltResults.getVal(i);
            IrpDocument document = null;
            for (SolrDocument solrDocument : solrDocumentList) {
            	if(solrDocument==null)
            		continue;
            	document = new IrpDocument();
            	try {
                	document.setDocid(Long.valueOf(solrDocument.getFieldValue("DOCID").toString()));
                	document.setDoctitle(stringSolrField(solrDocument.getFieldValue("DOCTITLE")));
                	arrDocuments.add(document);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }
        return arrDocuments;
    }

	@Override
	public int searchDocumentnumByKeyword(String _keyWords,
			String _sSorlUrl, String rightIds, Map<String, String> paramMap)
			throws SolrServerException {
		int docnum = 0;
		SolrQuery query = new SolrQuery();
		if(_keyWords==null){
			return 0;
		}
		if (_keyWords.equals("")) {
			return 0;
		}
		if(rightIds!=null){
			query.setParam("fq", rightIds);
		}
		StringBuffer sQuery = new StringBuffer();
		sQuery.append("DOCKEYWORDS:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			;
		query.setQuery(sQuery.toString());
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-7DAYS TO NOW/DAY]");
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1MONTH TO NOW/DAY]");
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1YEAR TO NOW/DAY]");
				}
			}
		}

		docnum = (int) getSolrServer(_sSorlUrl).query(query).getResults()
				.getNumFound();
		return docnum;
	}

	@Override
	public List searchDocumentByKeyword(String _keyWords,
			String _sSorlUrl, PageUtil pageUtil,
			Integer _sorttype, String rightIds, Map<String, String> paramMap)
			throws SolrServerException {
		SolrQuery query = new SolrQuery();
		if(_keyWords==null){
			return new ArrayList();
		}
		if (_keyWords.equals("")) {
			return new ArrayList();
		}
		StringBuffer sQuery = new StringBuffer();
		sQuery.append("DOCKEYWORDS:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			;
		query.setQuery(sQuery.toString());
		query.setHighlight(true);
		query.setHighlightSimplePre(SysConfigUtil
				.getSysConfigValue(IrpSolr.STAYLE_START));
		query.setHighlightSimplePost(SysConfigUtil
				.getSysConfigValue(IrpSolr.STAYLE_END));
		query.setParam("hl.fl", "DOCKEYWORDS");
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETMERGE));
		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETLENGTH));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE_STARTEND));
		
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-7DAYS TO NOW/DAY]");
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1MONTH TO NOW/DAY]");
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1YEAR TO NOW/DAY]");
				}
			}
		}
		
		query.setStart(pageUtil.getPageIndex());
		query.setRows(pageUtil.getPageSize());
		//在此处判断当前用户所拥有的查看栏目和查看该栏目下文章权限的所有docid
		if(rightIds!=null){
			query.setParam("fq",rightIds);
		}
		if(_sorttype!=null){
			if(_sorttype==1){
				query.setSort(SortClause.desc("CRTIME"));
			}	
		}
		
		
		QueryResponse queryResponse = getSolrServer(_sSorlUrl).query(query);
		
		SolrDocumentList documents = queryResponse.getResults();

		SolrDocumentList list = new SolrDocumentList();

		SolrDocument document = null;

		Map<String, Map<String, List<String>>> map = queryResponse
				.getHighlighting();
		
		//处理高亮显示
		for (int i = 0; i < documents.size(); i++) {
			document = documents.get(i);
			if(map.get(document.getFieldValue("id")).get("DOCTITLE")!=null){
				document.setField("DOCTITLE", map.get(document.getFieldValue("id")).get("DOCTITLE"));
			}
			if(map.get(document.getFieldValue("id")).get("DOCCONTENT")!=null){
				document.setField("DOCCONTENT", map.get(document.getFieldValue("id")).get("DOCCONTENT"));
			}
			if(map.get(document.getFieldValue("id")).get("DOCKEYWORDS")!=null){
				document.setField("DOCKEYWORDS", map.get(document.getFieldValue("id")).get("DOCKEYWORDS"));
			}
			if(map.get(document.getFieldValue("id")).get("CRUSER")!=null){
				document.setField("CRUSER", map.get(document.getFieldValue("id")).get("CRUSER"));
			}
			list.add(document);
		}
		return list;
	}

	@Override
	public int searchDocumentnumByUser(String _keyWords,
			String _sSorlUrl, String rightIds, Map<String, String> paramMap)
			throws SolrServerException {
		int docnum = 0;
		SolrQuery query = new SolrQuery();
		if(_keyWords==null){
			return 0;
		}
		if (_keyWords.equals("")) {
			return 0;
		}
		if(rightIds!=null){
			query.setParam("fq", rightIds);
		}
		StringBuffer sQuery = new StringBuffer();
		sQuery.append("CRUSER:")
			.append(ClientUtils.escapeQueryChars(_keyWords))
			;
		query.setQuery(sQuery.toString());
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-7DAYS TO NOW/DAY]");
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1MONTH TO NOW/DAY]");
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1YEAR TO NOW/DAY]");
				}
			}
		}

		docnum = (int) getSolrServer(_sSorlUrl).query(query).getResults()
				.getNumFound();
		return docnum;
	}

	@Override
	public List searchDocumentByUser(String _keyWords,
			String _sSorlUrl, PageUtil pageUtil,
			Integer _sorttype, String rightIds, Map<String, String> paramMap) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		if(_keyWords==null){
			return new ArrayList();
		}
		if (_keyWords.equals("")) {
			return new ArrayList();
		}
		StringBuffer sQuery = new StringBuffer();
		sQuery.append("CRUSER:")
			.append("'"+ClientUtils.escapeQueryChars(_keyWords)+"'")
			;
		query.setQuery(sQuery.toString());
		query.setHighlight(true);
		query.setHighlightSimplePre(SysConfigUtil
				.getSysConfigValue(IrpSolr.STAYLE_START));
		query.setHighlightSimplePost(SysConfigUtil
				.getSysConfigValue(IrpSolr.STAYLE_END));
		query.setParam("hl.fl", "CRUSER");
		query.setParam("hl.mergeContiguous",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETMERGE));
		query.setParam("hl.fragsize",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE));
		query.setParam("hl.maxAnalyzedChars",
				SysConfigUtil.getSysConfigValue(IrpSolr.SNIPPETLENGTH));
		query.setParam("hl.regex.slop",
				SysConfigUtil.getSysConfigValue(IrpSolr.SIGHVIEW_SIZE_STARTEND));
		
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-7DAYS TO NOW/DAY]");
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1MONTH TO NOW/DAY]");
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					query.addFilterQuery("CRTIME:[NOW/DAY-1YEAR TO NOW/DAY]");
				}
			}
		}
		
		query.setStart(pageUtil.getPageIndex());
		query.setRows(pageUtil.getPageSize());
		//在此处判断当前用户所拥有的查看栏目和查看该栏目下文章权限的所有docid
		if(rightIds!=null){
			query.setParam("fq",rightIds);
		}
		if(_sorttype!=null){
			if(_sorttype==1){
				query.setSort(SortClause.desc("CRTIME"));
			}	
		}
		
		
		QueryResponse queryResponse = getSolrServer(_sSorlUrl).query(query);
		
		SolrDocumentList documents = queryResponse.getResults();

		SolrDocumentList list = new SolrDocumentList();

		SolrDocument document = null;

		Map<String, Map<String, List<String>>> map = queryResponse
				.getHighlighting();
		
		//处理高亮显示
		for (int i = 0; i < documents.size(); i++) {
			document = documents.get(i);
			if(map.get(document.getFieldValue("id")).get("DOCTITLE")!=null){
				document.setField("DOCTITLE", map.get(document.getFieldValue("id")).get("DOCTITLE"));
			}
			if(map.get(document.getFieldValue("id")).get("DOCCONTENT")!=null){
				document.setField("DOCCONTENT", map.get(document.getFieldValue("id")).get("DOCCONTENT"));
			}
			if(map.get(document.getFieldValue("id")).get("DOCKEYWORDS")!=null){
				document.setField("DOCKEYWORDS", map.get(document.getFieldValue("id")).get("DOCKEYWORDS"));
			}
			if(map.get(document.getFieldValue("id")).get("CRUSER")!=null){
				document.setField("CRUSER", map.get(document.getFieldValue("id")).get("CRUSER"));
			}
			list.add(document);
		}
		return list;
	}

	



}
