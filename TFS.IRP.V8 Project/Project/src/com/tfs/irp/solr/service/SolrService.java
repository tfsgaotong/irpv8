package com.tfs.irp.solr.service;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.personalsearch.entity.IrpPersonalSearch;
import com.tfs.irp.solr.entity.DocumentForSolr;
import com.tfs.irp.solr.entity.MicroblogForSolr;
import com.tfs.irp.solr.entity.QuestionForSolr;
import com.tfs.irp.util.PageUtil;

public interface SolrService {
	
	/**
	 * 检索文档
	 * @param _search_word
	 * @return
	 */
	public List searchDocument(String _keyWords,String _sSorlUrl,PageUtil pageUtil,Integer _sorttype,String rightIds) throws SolrServerException;
	
	/**
	 * 检索文档(新增检索条件)
	 * @param _search_word
	 * @return
	 */
	public List searchDocumentByParam(String _keyWords,String _sSorlUrl,PageUtil pageUtil,Integer _sorttype,String rightIds,Map<String,String> paramMap) throws SolrServerException;
	
	/**
	 * 共命中多少文档
	 * @param _keyWords
	 * @param _sSorlUrl
	 * @return
	 * @throws SolrServerException
	 */
	public int searchDocumentNum(String _keyWords,String _sSorlUrl,String rightIds) throws SolrServerException;
	/**
	 * 共多少文档(新增检索条件)
	 * @param _keyWords
	 * @param _sSorlUrl
	 * @param rightIds
	 * @return
	 * @throws SolrServerException
	 */
	public int searchDocumentnumByParam(String _keyWords,String _sSorlUrl,String rightIds,Map<String,String> paramMap) throws SolrServerException;
	/**
	 * 检索微知
	 * @param _keyWords
	 * @param _sSorlUrl
	 * @return
	 * @throws SolrServerException
	 */
	public List searchMicroblog(String _keyWords,String _sSorlUrl,PageUtil pageUtil,Integer _sorttype) throws SolrServerException;
	/**
	 * 检索微知(新增检索条件)
	 * @param _keyWords
	 * @param _sSorlUrl
	 * @return
	 * @throws SolrServerException
	 */
	public List searchMicroblogByParam(String _keyWords,String _sSorlUrl,PageUtil pageUtil,Integer _sorttype,Map<String,String> paramMap) throws SolrServerException;
	/**
	 * 共命中多少微知
	 * @param _keyWords
	 * @param _sSorlUrl
	 * @return
	 * @throws SolrServerException
	 */
	public int searchMicroblogNum(String _keyWords,String _sSorlUrl) throws SolrServerException;
	/**
	 * 共命中多少微知(新增检索条件)
	 * @param _keyWords
	 * @param _sSorlUrl
	 * @return
	 * @throws SolrServerException
	 */
	public int searchMicroblogNumByParam(String _keyWords,String _sSorlUrl,Map<String,String> paramMap) throws SolrServerException;
	/**
	 * 检索问答
	 * @param _keyWords
	 * @param _sSorlUrl
	 * @param pageUtil
	 * @return
	 * @throws SolrServerException
	 */
	public List searchQuestion(String _keyWords,String _sSorlUrl,PageUtil pageUtil,Integer _sorttype) throws SolrServerException;
	/**
	 * 共命中多少个问答
	 * @param _keyWords
	 * @param _sSorlUrl
	 * @return
	 * @throws SolrServerException
	 */
	public int searchQuestionNum(String _keyWords,String _sSorlUrl) throws SolrServerException;
	/**
	 * 
	 * @param _keyWords  传入的字符串
	 * @param _length  返回多少
	 * @return
	 */
	public List<Long> searchIdByStr(String _keyWords,Integer _length,String _location) throws SolrServerException;
	/**
	 * 根据id删除solr索引
	 * 0删除失败    1删除成功
	 * @param _id  要删除的id
	 * @param _solrurl	 删除的地址
	 * @return
	 */
	public int deleteSolrIndex(Long _id,String _solrurl);
	/**
	 * 根据id集合删除索引
	 * 0删除失败    1删除成功
	 * @param _id    要删除的id
	 * @param _solrurl  删除的地址
	 * @return
	 */
	public int deleteSolrIndex(List<String> _ids,String _solrurl);
	/**
	 * 微知增加索引
	 * 0: 失败  1：成功
	 * @param _solrurl
	 * @return
	 */
	public int addMicroblogIndex(MicroblogForSolr _microblogForSolr,String _solrurl);
	/**
	 * 微知更新索引
	 * 根据id修改
	 * @param _microblogForSolr
	 * @param _solrurl
	 * @return
	 */
	public int updateMicroblogIndex(MicroblogForSolr _microblogForSolr,String _solrurl);
	/**
	 * 文档增加索引
	 * 0: 失败  1：成功
	 * @param _documentForSolr
	 * @param _solrurl
	 * @return
	 */
	public int addDocumentIndex(DocumentForSolr _documentForSolr,String _solrurl);
	/**
	 * 文档修改索引
	 * 根据id修改
	 * @param _documentForSolr
	 * @param _solrurl
	 * @return
	 */
	public int updateDocumentIndex(DocumentForSolr _documentForSolr,String _solrurl);
	/**
	 * 问答增加索引
	 * 0:是吧  1：成功
	 * @param _questionForSolr
	 * @param _solrurl
	 * @return
	 */
	public int addQuestionIndex(QuestionForSolr _questionForSolr,String _solrurl);
	/**
	 * 问答修改索引 
	 * 根据id修改
	 * @param _questionForSolr
	 * @param _solrurl
	 * @return
	 */
	public int updateQuestionIndex(QuestionForSolr _questionForSolr,String _solrurl);
	
	/**
	 * 高级检索
	 * @param _keyParams
	 * @param _sSorlUrl
	 * @param pageUtil
	 * @param _sorttype
	 * @param rightIds
	 * @return
	 * @throws SolrServerException
	 */
	public List advancedSearchDocument(Map<String, String> _keyParams,
			String _sSorlUrl, PageUtil pageUtil, Integer _sorttype,
			String rightIds) throws SolrServerException;
	
	/**
	 * 高级检索数量
	 * @param _keyParams
	 * @param _sSorlUrl
	 * @param rightIds
	 * @return
	 * @throws SolrServerException
	 */
	public int advancedSearchDocumentNum(Map<String, String> _keyParams,
			String _sSorlUrl, String rightIds) throws SolrServerException;
	
	/**
	 * 检索专题
	 * @param _personalSearch
	 * @param _sSorlUrl
	 * @param pageUtil
	 * @param rightIds
	 * @return
	 * @throws SolrServerException
	 */
	List searchSubject(IrpPersonalSearch _personalSearch, String _sSorlUrl,
			PageUtil pageUtil, String rightIds) throws SolrServerException;
	
	/**
	 * 检索专题数量
	 * @param _personalSearch
	 * @param _sSorlUrl
	 * @param rightIds
	 * @return
	 * @throws SolrServerException
	 */
	long searchSubjectCount(IrpPersonalSearch _personalSearch,
			String _sSorlUrl, String rightIds) throws SolrServerException;
	
	/**
	 * 智能提示检索
	 * @param _sSearchWord
	 * @return
	 */
	List<String> spellSearch(String _sSearchWord);
	
	/**
	 * 根据关键词获得相关知识
	 * @param _sKeyWord
	 * @param rightIds
	 * @return
	 * @throws SolrServerException
	 */
	List<IrpDocument> relativeDocByKeyword(IrpDocument _document, String rightIds) throws SolrServerException;
	
	/**
	 * 查询DOCID的相似知识
	 * @param _nDocId
	 * @return
	 */
	List<IrpDocument> similarityDocByDocId(long _nDocId);

	/**按标签查询知识数量
	 * @param searchInfo
	 * @param sysConfigValue
	 * @param string
	 * @param paramM
	 * @return
	 */
	public int searchDocumentnumByKeyword(String searchInfo,
			String sysConfigValue, String string, Map<String, String> paramM)throws SolrServerException;

	/**按标签查询知识
	 * @param searchInfo
	 * @param sysConfigValue
	 * @param pageUtildocument
	 * @param searchsort
	 * @param string
	 * @param paramM
	 * @return
	 */
	public List searchDocumentByKeyword(String searchInfo,
			String sysConfigValue, PageUtil pageUtildocument,
			Integer searchsort, String string, Map<String, String> paramM)throws SolrServerException;

	/**
	 * 按用户查询知识数量
	 * @param searchInfo
	 * @param sysConfigValue
	 * @param string
	 * @param paramM
	 * @return
	 */
	public int searchDocumentnumByUser(String searchInfo,
			String sysConfigValue, String string, Map<String, String> paramM)throws SolrServerException;

	/**按用户查询知识
	 * @param searchInfo
	 * @param sysConfigValue
	 * @param pageUtildocument
	 * @param searchsort
	 * @param string
	 * @param paramM
	 * @return
	 */
	public List searchDocumentByUser(String searchInfo, String sysConfigValue,
			PageUtil pageUtildocument, Integer searchsort, String string,
			Map<String, String> paramM)throws SolrServerException;

	/**按条件查询问答获取数量
	 * @param searchInfo
	 * @param sysConfigValue
	 * @param paramM
	 * @return
	 */
	public int searchQuestionNumByParam(String searchInfo,
			String sysConfigValue, Map<String, String> paramM)throws SolrServerException;

	/**按条件查询问答获取集合
	 * @param searchInfo
	 * @param sysConfigValue
	 * @param pageUtilquestion
	 * @param searchsort
	 * @param paramM
	 * @return
	 * @throws SolrServerException
	 */
	public List searchQuestionByParam(String searchInfo, String sysConfigValue,
			PageUtil pageUtilquestion, Integer searchsort,
			Map<String, String> paramM)throws SolrServerException;

	/**检索跟随
	 * @param keyword
	 * @param searchtype
	 * @return
	 */
	public List<String> spellSearchByCore(String keyword, Integer searchtype);

}
