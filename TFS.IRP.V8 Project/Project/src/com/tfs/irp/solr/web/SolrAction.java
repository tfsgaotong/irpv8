package com.tfs.irp.solr.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogcollection.service.IrpMicroblogCollectionKeyService;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.personalsearch.service.IrpPersonalSearchService;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.service.IrpQuestionService;
import com.tfs.irp.selectkey.service.IrpSelectKeyService;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.StringUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.ThumbnailPic;

public class SolrAction extends ActionSupport{
	private SolrService solrService;
	
    private Integer searchtype;
    
    private String searchInfo;
    
    private String keyword;
    
    private List documentlist;
    
    private List microbloglist;
    
    private IrpUserService irpUserService;
    
    private List userlist;
    
    private IrpMicroblogFocusService irpMicroblogFocusService;
    
    private IrpDocumentService irpDocumentService;
    
    private IrpChannelService irpChannelService;
    
	private List  allUseridByFocusUserId;
	
	private IrpMicroblogService irpMicroblogService;

	private Long loginUserid;
	
	private Integer searchsort;
	
	private IrpQuestionService irpQuestionService;
	
	private Map<String, String> advancedParam;
	
	private IrpPersonalSearchService irpPersonalSearchService;
	
	private IrpMicroblogCollectionKeyService irpMicroblogCollectionKeyService;
	
	private IrpMicroblogService irpMicroBlogService;
	
	private IrpAttachedService irpAttachedService;
	
	private long loginuserid;
	
	private String paramMap;
	
	private List<String> collectionOfUseridlist;
	
	public String getParamMap() {
		return paramMap;
	}

	public void setParamMap(String paramMap) {
		this.paramMap = paramMap;
	}

	public IrpMicroblogCollectionKeyService getIrpMicroblogCollectionKeyService() {
		return irpMicroblogCollectionKeyService;
	}

	public void setIrpMicroblogCollectionKeyService(
			IrpMicroblogCollectionKeyService irpMicroblogCollectionKeyService) {
		this.irpMicroblogCollectionKeyService = irpMicroblogCollectionKeyService;
	}

	public List<String> getCollectionOfUseridlist() {
		return collectionOfUseridlist;
	}

	public void setCollectionOfUseridlist(List<String> collectionOfUseridlist) {
		this.collectionOfUseridlist = collectionOfUseridlist;
	}

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public long getLoginuserid() {
		return loginuserid;
	}

	public void setLoginuserid(long loginuserid) {
		this.loginuserid = loginuserid;
	}

	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}

	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public IrpPersonalSearchService getIrpPersonalSearchService() {
		return irpPersonalSearchService;
	}

	public void setIrpPersonalSearchService(
			IrpPersonalSearchService irpPersonalSearchService) {
		this.irpPersonalSearchService = irpPersonalSearchService;
	}


	public Map<String, String> getAdvancedParam() {
		return advancedParam;
	}


	public void setAdvancedParam(Map<String, String> advancedParam) {
		this.advancedParam = advancedParam;
	}


	public IrpQuestionService getIrpQuestionService() {
		return irpQuestionService;
	}


	public void setIrpQuestionService(IrpQuestionService irpQuestionService) {
		this.irpQuestionService = irpQuestionService;
	}


	public Integer getSearchsort() {
		return searchsort;
	}


	public void setSearchsort(Integer searchsort) {
		this.searchsort = searchsort;
	}
	/*
	 * 分页排序
	 */
	private String pageHTML = "";
	
	private int pageNum = 0;

	private int pageSize = 10;
	
	private IrpSelectKeyService irpSelectKeyService;
	
	private IrpTagService irpTagService;
	
	private List questionlist;
	
	public List getQuestionlist() {
		return questionlist;
	}


	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}


	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}


	public void setQuestionlist(List questionlist) {
		this.questionlist = questionlist;
	}


	public IrpTagService getIrpTagService() {
		return irpTagService;
	}


	public void setIrpTagService(IrpTagService irpTagService) {
		this.irpTagService = irpTagService;
	}


	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}


	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}


	public IrpSelectKeyService getIrpSelectKeyService() {
		return irpSelectKeyService;
	}


	public void setIrpSelectKeyService(IrpSelectKeyService irpSelectKeyService) {
		this.irpSelectKeyService = irpSelectKeyService;
	}


	public String getPageHTML() {
		return pageHTML;
	}


	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public Long getLoginUserid() {
		return loginUserid;
	}


	public void setLoginUserid(Long loginUserid) {
		this.loginUserid = loginUserid;
	}


	public IrpMicroblogService getIrpMicroblogService() {
		return irpMicroblogService;
	}


	public void setIrpMicroblogService(IrpMicroblogService irpMicroblogService) {
		this.irpMicroblogService = irpMicroblogService;
	}


	public List getAllUseridByFocusUserId() {
		return allUseridByFocusUserId;
	}


	public void setAllUseridByFocusUserId(List allUseridByFocusUserId) {
		this.allUseridByFocusUserId = allUseridByFocusUserId;
	}


	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}


	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}


	public List getUserlist() {
		return userlist;
	}


	public void setUserlist(List userlist) {
		this.userlist = userlist;
	}


	public IrpUserService getIrpUserService() {
		return irpUserService;
	}


	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}


	public List getMicrobloglist() {
		return microbloglist;
	}


	public void setMicrobloglist(List microbloglist) {
		this.microbloglist = microbloglist;
	}


	public Integer getSearchtype() {
		return searchtype;
	}


	public List getDocumentlist() {
		return documentlist;
	}


	public void setDocumentlist(List documentlist) {
		this.documentlist = documentlist;
	}


	public void setSearchtype(Integer searchtype) {
		this.searchtype = searchtype;
	}


	public String getSearchInfo() {
		return searchInfo;
	}


	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}


	public SolrService getSolrService() {
		return solrService;
	}


	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	}


	/**
	 * 查询
	 * @return
	 * @throws ParseException 
	 */
	public String findSearchResult() throws ParseException{
		/**
		 * searchtype
		 * 1：用户
		 * 2：微知
		 * 3：问答
		 * 4：个人知识库
		 * 5：企业知识库
		 */
		/*try {
			this.searchInfo = new String(this.searchInfo.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
		irpSelectKeyService.save(searchInfo);
		irpTagService.addTag(searchInfo);
		return SUCCESS;
	}
	
	/**
	 * 检索结果
	 * mobile
	 */
	private int searchmobileqpnum;
	public int getSearchmobileqpnum() {
		return searchmobileqpnum;
	}
	public void setSearchmobileqpnum(int searchmobileqpnum) {
		this.searchmobileqpnum = searchmobileqpnum;
	}
	public void findQuesForMobile(){
		  int quesnum = 0;
		try {
			List list = new ArrayList();
			
			quesnum = this.solrService.searchQuestionNum(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION));
			
			if(quesnum>0){
				   PageUtil pageUtilquestion = new PageUtil(this.searchmobileqpnum,10,quesnum);
				   questionlist = this.solrService.searchQuestion(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION),pageUtilquestion,null);
				   for (int i = 0; i < questionlist.size(); i++) {
					   
					   SolrDocument sd = (SolrDocument) questionlist.get(i);
					   Long questionid = Long.parseLong(sd.getFieldValue("QUESTIONID").toString());
					   list.add("{\"questionid\":\""+questionid+"\"");
					   String cruserid = sd.getFieldValue("CRUSERID").toString();
					   list.add("\"status\":\""+ getIrpQuestion(questionid).getStatus()+"\"");
					   list.add("\"browsernum\":\""+getIrpQuestion(questionid).getBrowsecount()+"\"");
					   list.add("\"answernum\":\""+findReplyCountByQuestionId(questionid)+"\"");
					   
					   list.add("\"cruserid\":\""+cruserid.substring(1,cruserid.length()-1)+"\"");
					   list.add("\"showname\":\""+getShowPageViewNameByUserId(Long.parseLong(cruserid.substring(1,cruserid.length()-1)))+"\"");
					   list.add("\"userpic\":\""+getIrpUserByUserid(cruserid.substring(1,cruserid.length()-1)).getUserpic()+"\"");
					   list.add("\"sex\":\""+getIrpUserByUserid(cruserid.substring(1,cruserid.length()-1)).getSex()+"\"");
					   Object disposetitle = sd.getFieldValue("TITLE");
					   if(disposetitle!=null){
						   list.add("\"title\":\""+disposetitle.toString().substring(1,disposetitle.toString().length()-1).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");   
					   }
					   
					   

					   Object disposetcontent = sd.getFieldValue("TEXTCONTENT");
					   if(disposetcontent!=null){
						   list.add("\"tcontent\":\""+disposetcontent.toString().substring(1,disposetcontent.toString().length()-1).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");   
					   }
					   String disposecrtime = sd.getFieldValue("CRTIME").toString();
					   list.add("\"dcrtime\":\""+disposecrtime+"\"}");
				   }
				   
				   list.add("{\"mobilenum\":\""+quesnum+"\"}");
				   ActionUtil.writer(list.toString());
			  } 
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 检索个人知识
	 * mobile
	 * @reutrn
	 */
	private int searchmobilepdocnum;
	private int searchkindmobile;
	public int getSearchkindmobile() {
		return searchkindmobile;
	}
	public void setSearchkindmobile(int searchkindmobile) {
		this.searchkindmobile = searchkindmobile;
	}
	public int getSearchmobilepdocnum() {
		return searchmobilepdocnum;
	}
	public void setSearchmobilepdocnum(int searchmobilepdocnum) {
		this.searchmobilepdocnum = searchmobilepdocnum;
	}
	public void sPDocMobile(){
		
		try {	
			int	docnum = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List mobilelist = new ArrayList();
			if(searchkindmobile==1){
				//搜个人知识
				docnum = this.solrService.searchDocumentNum(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL),null);	 
				if(docnum>0){
					PageUtil pageUtildocument = new PageUtil(this.searchmobilepdocnum,10,docnum);
					List list = this.solrService.searchDocument(searchInfo,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL),pageUtildocument,null,null);
					    for (int i = 0; i < list.size(); i++) {
					    	 SolrDocument sd = (SolrDocument) list.get(i); 
					    	 String cruserid = sd.getFieldValue("CRUSERID").toString();
					    	 mobilelist.add("{\"cruserid\":\""+cruserid.substring(1,cruserid.toString().length()-1)+"\"");
					    	 String dockwords = sd.getFieldValue("DOCKEYWORDS").toString();
					    	 mobilelist.add("\"dockwords\":\""+dockwords.substring(1,dockwords.toString().length()-1).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
					    	 mobilelist.add("\"docid\":\""+sd.getFieldValue("DOCID").toString()+"\"");
					    	 mobilelist.add("\"showname\":\""+getShowPageViewNameByUserId(Long.parseLong(cruserid.substring(1,cruserid.length()-1)))+"\"");			    	 
					    	 mobilelist.add("\"crtime\":\""+sdf.format(sd.getFieldValue("CRTIME"))+"\"");
					    	 String dcontent = sd.getFieldValue("DOCCONTENT").toString();
					    	 String disposestr = dcontent.substring(1,dcontent.toString().length()-1).replace("\"", "\\\"").replace("\\\\\"","\\\"");
					    	 String darray[] = disposestr.split("\n");
					    	 StringBuffer sbstr = new StringBuffer();
					    	 for (int j = 0; j < darray.length; j++) {
					    		 sbstr.append(darray[j]);
					    	 }
					    	 mobilelist.add("\"dcontent\":\""+sbstr+"\"");
					    	 String dtitle = sd.getFieldValue("DOCTITLE").toString();
					    	 mobilelist.add("\"dtitle\":\""+dtitle.substring(1,dtitle.toString().length()-1).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"}");
					}
				}
				
			}else{
				//搜企业知识
				//权限
				IrpUser user = LoginUtil.getLoginUser();
				boolean result = false;
				StringBuffer stringBuffer = null;
				if(!user.isAdministrator()){
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("status", 1);
					List<IrpChannel> channelList = irpChannelService.JsonRightAllChannelMore(map);
					if(channelList!=null && channelList.size()>0){
						stringBuffer = new StringBuffer();
						for(IrpChannel irpChannel : channelList){
							if(irpChannel==null)
								continue;
							stringBuffer.append(" CHANNELID:"+irpChannel.getChannelid());
						}
						result = true;
					}
				}else{
					result = true;
				}
				if(result){
					try {
						//权限
						docnum = this.solrService.searchDocumentNum(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),stringBuffer==null?null:stringBuffer.toString());
						if(docnum>0){
							PageUtil pageUtildocument = new PageUtil(searchmobilepdocnum,10,docnum);
							List list= this.solrService.searchDocument(searchInfo,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),pageUtildocument,null,stringBuffer==null?null:stringBuffer.toString());
						    for (int i = 0; i < list.size(); i++) {
						    	 SolrDocument sd = (SolrDocument) list.get(i); 
						    	 String cruserid = sd.getFieldValue("CRUSERID").toString();
						    	 mobilelist.add("{\"cruserid\":\""+cruserid.substring(1,cruserid.toString().length()-1)+"\"");
						    	 String dockwords = sd.getFieldValue("DOCKEYWORDS").toString();
						    	 mobilelist.add("\"dockwords\":\""+dockwords.substring(1,dockwords.toString().length()-1).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
						    	 mobilelist.add("\"docid\":\""+sd.getFieldValue("DOCID").toString()+"\"");
						    	 mobilelist.add("\"showname\":\""+getShowPageViewNameByUserId(Long.parseLong(cruserid.substring(1,cruserid.length()-1)))+"\"");			    	 
						    	 mobilelist.add("\"crtime\":\""+sdf.format(sd.getFieldValue("CRTIME"))+"\"");
						    	 String dcontent = sd.getFieldValue("DOCCONTENT").toString();
						    	 String disposestr = dcontent.substring(1,dcontent.toString().length()-1).replace("\"", "\\\"").replace("\\\\\"","\\\"");
						    	 String darray[] = disposestr.split("\n");
						    	 StringBuffer sbstr = new StringBuffer();
						    	 for (int j = 0; j < darray.length; j++) {
						    		 sbstr.append(darray[j]);
						    	 }
						    	 mobilelist.add("\"dcontent\":\""+sbstr+"\"");
						    	 String dtitle = sd.getFieldValue("DOCTITLE").toString();
						    	 mobilelist.add("\"dtitle\":\""+dtitle.substring(1,dtitle.toString().length()-1).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"}");
						    }
						}
						} catch (SolrServerException e) {
							e.printStackTrace();
						}
				}
			}
			
			mobilelist.add("{\"mobilenum\":\""+docnum+"\"}");

			ActionUtil.writer(mobilelist.toString());
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 搜微知
	 * @return
	 */
	public String findSearchResultByMicroblog(){
		loginuserid = LoginUtil.getLoginUserId();
	   try {
		   Map<String,String> paramM = new HashMap<String, String>();
			if(StringUtil.isEmpty(paramMap)){
				paramMap = "ALL";
			}
			paramM.put("CRTIME", paramMap);
		  collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(loginuserid);
		  int micrnum = this.solrService.searchMicroblogNumByParam(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG),paramM);
		  
		  if(micrnum>0){
			   PageUtil pageUtilmicro = new PageUtil(this.pageNum,Integer.parseInt(SysConfigUtil.getSysConfigValue(IrpSolr.MICR_PAGENUM)),micrnum);
			   microbloglist = this.solrService.searchMicroblogByParam(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG),pageUtilmicro,searchsort,paramM);
			   this.pageHTML = pageUtilmicro.getClientPageHtml("page(#PageNum#)");
		  } 
		   
	} catch (SolrServerException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return SUCCESS;
	}
	
	/**
	 * 获得登录用户的id
	 * @return
	 */
	public long getLoginUserId(){
		return LoginUtil.getLoginUserId();
	}
	/**
	 * 搜问答按条件
	 * @return
	 * @throws SolrServerException 
	 */
	public String searchResultByQuestion(){
		try {
			 Map<String,String> paramM = new HashMap<String, String>();
				if(StringUtil.isEmpty(paramMap)){
					paramMap = "ALL";
				}
				paramM.put("CRTIME", paramMap);
			int quesnum = this.solrService.searchQuestionNumByParam(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION),paramM);
			  if(quesnum>0){
				   PageUtil pageUtilquestion = new PageUtil(this.pageNum,Integer.parseInt(SysConfigUtil.getSysConfigValue(IrpSolr.QUESTION_PAGENUM)),quesnum);
				   questionlist = this.solrService.searchQuestionByParam(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION),pageUtilquestion,searchsort,paramM);
				   this.pageHTML = pageUtilquestion.getClientPageHtml("page(#PageNum#)");
			  } 
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	
		return SUCCESS;
	}	
	/**
	 * 搜问答
	 * @return
	 * @throws SolrServerException 
	 */
	public String findSearchResultByQuestion(){
		try {
			int quesnum = this.solrService.searchQuestionNum(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION));
			  
			  if(quesnum>0){
				   PageUtil pageUtilquestion = new PageUtil(this.pageNum,Integer.parseInt(SysConfigUtil.getSysConfigValue(IrpSolr.QUESTION_PAGENUM)),quesnum);
				   questionlist = this.solrService.searchQuestion(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION),pageUtilquestion,searchsort);
				   this.pageHTML = pageUtilquestion.getClientPageHtml("page(#PageNum#)");
			  } 
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	
		return SUCCESS;
	}
	/**
	 * 搜知识(个人)
	 * @return
	 */
	public String findSearchResultByDocument(){
		try {
		int docnum = this.solrService.searchDocumentNum(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL),null);
		if(docnum>0){
			PageUtil pageUtildocument = new PageUtil(this.pageNum,Integer.parseInt(SysConfigUtil.getSysConfigValue(IrpSolr.SEARCH_PAGENUM_KNOWLEDGE)),docnum);
			documentlist= this.solrService.searchDocument(searchInfo,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL),pageUtildocument,searchsort,null);
			this.pageHTML = pageUtildocument.getClientPageHtml("page(#PageNum#)");
		}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 搜人
	 * @return
	 */
	public String findSearchResultByUser(){
		//搜用户
		Map<String,String> paramM = new HashMap<String, String>();
		if(StringUtil.isEmpty(paramMap)){
			paramMap = "ALL";
		}
		paramM.put("CRTIME", paramMap);
		PageUtil pageUtiluser = new PageUtil(this.pageNum,Integer.parseInt(SysConfigUtil.getSysConfigValue(IrpSolr.MICR_USER_PAGENUM)),irpMicroblogFocusService.findSearchUserNumByParam(searchInfo,LoginUtil.getLoginUserId(),paramM));
		userlist = this.irpMicroblogFocusService.findSearchUserByParam(searchInfo,LoginUtil.getLoginUserId(),pageUtiluser,paramM);
		this.pageHTML=pageUtiluser.getClientPageHtml("page(#PageNum#)");
		List listArraylist = new ArrayList();
		List listHashMaplist = this.irpMicroblogFocusService.selectUseridByFocuserId(LoginUtil.getLoginUserId());
		for(int i =0;i<listHashMaplist.size();i++){
			Map map = (Map) listHashMaplist.get(i);
			listArraylist.add(map.values().toString().replace("[","").replace("]",""));
		} 
		loginUserid = LoginUtil.getLoginUserId();
		allUseridByFocusUserId = listArraylist;
		return SUCCESS;
	}
	/**
	 * 搜知识(企业)
	 * @return
	 */
	public String findSearchResultByDocumentEprise(){
		//权限
		IrpUser user = LoginUtil.getLoginUser();
		boolean result = false;
		StringBuffer stringBuffer = null;
		if(!user.isAdministrator()){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("status", 1);
			List<IrpChannel> channelList = irpChannelService.JsonRightAllChannelMore(map);
			if(channelList!=null && channelList.size()>0){
				stringBuffer = new StringBuffer();
				for(IrpChannel irpChannel : channelList){
					if(irpChannel==null)
						continue;
					stringBuffer.append(" CHANNELID:"+irpChannel.getChannelid());
				}
				result = true;
			}
		}else{
			result = true;
		}
		if(result){
			try {
				searchtype=searchtype;
				//权限
				Map<String,String> paramM = new HashMap<String, String>();
				if(StringUtil.isEmpty(paramMap)){
					paramMap = "ALL";
				}
				paramM.put("CRTIME", paramMap);
				if(searchtype==5){
					int docnum = this.solrService.searchDocumentnumByParam(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),stringBuffer==null?null:stringBuffer.toString(),paramM);
					if(docnum>0){
						PageUtil pageUtildocument = new PageUtil(this.pageNum,Integer.parseInt(SysConfigUtil.getSysConfigValue(IrpSolr.SEARCH_PAGENUM_KNOWLEDGE)),docnum);
						documentlist= this.solrService.searchDocumentByParam(searchInfo,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),pageUtildocument,searchsort,stringBuffer==null?null:stringBuffer.toString(),paramM);
						this.pageHTML = pageUtildocument.getClientPageHtml("page(#PageNum#)");
					}
				}else if(searchtype==51){
					int docnum = this.solrService.searchDocumentnumByKeyword(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),stringBuffer==null?null:stringBuffer.toString(),paramM);
					if(docnum>0){
						PageUtil pageUtildocument = new PageUtil(this.pageNum,Integer.parseInt(SysConfigUtil.getSysConfigValue(IrpSolr.SEARCH_PAGENUM_KNOWLEDGE)),docnum);
						documentlist= this.solrService.searchDocumentByKeyword(searchInfo,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),pageUtildocument,searchsort,stringBuffer==null?null:stringBuffer.toString(),paramM);
						this.pageHTML = pageUtildocument.getClientPageHtml("page(#PageNum#)");
					}
				}
				else if(searchtype==52){
					int docnum = this.solrService.searchDocumentnumByUser(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),stringBuffer==null?null:stringBuffer.toString(),paramM);
					if(docnum>0){
						PageUtil pageUtildocument = new PageUtil(this.pageNum,Integer.parseInt(SysConfigUtil.getSysConfigValue(IrpSolr.SEARCH_PAGENUM_KNOWLEDGE)),docnum);
						documentlist= this.solrService.searchDocumentByUser(searchInfo,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),pageUtildocument,searchsort,stringBuffer==null?null:stringBuffer.toString(),paramM);
						this.pageHTML = pageUtildocument.getClientPageHtml("page(#PageNum#)");
					}
				}
			} catch (SolrServerException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
		
	}
	
	
	/**
	 * 根据用户名寻找用户对象
	 * @return
	 */
	public IrpUser getIrpUserByUserid(String _userid){
		IrpUser irpUser = null;
		Long userid = Long.parseLong(_userid);
		irpUser =this.irpUserService.findUserByUserId(userid);
		
		return irpUser;
	}
	
	public IrpUser getIrpUserById(String _userid){
		IrpUser irpUser = null;
		Long userid = Long.parseLong(_userid);
		if(userid!=null){
			irpUser =this.irpUserService.findUserByUserId(userid);	
		}
		return irpUser;
	}
	
	/**
	 * 查看当前用户所关注的用户的id
	 * @return
	 */
	public List allUseridByUserid(long _userid){
		  //被关注的用户关注了谁
		  List listArraylistUserid =new ArrayList();
		  List listHashMaplistUserid;
			listHashMaplistUserid = this.irpMicroblogFocusService.selectUseridByFocuserId(_userid);
			  for(int i =0;i<listHashMaplistUserid.size();i++){
				  Map map = (Map) listHashMaplistUserid.get(i);
				  listArraylistUserid.add(map.values().toString().replace("[","").replace("]",""));
			  } 
		return listArraylistUserid;	  
	}
	/**
	 * 通过用户的id获得用户共关注了多少个用户
	 */
	public int MicroblogFocusCountUserid(long _focususerid){
		return this.irpMicroblogFocusService.countMicroblogFocusUserid(_focususerid);
	}
	/**
	 * 通过用户的id获得用户共有多少条微知
	 */
	public int MicroblogCountByUserid(long _userid){
	 return this.irpMicroblogService.coutnMicroblogOfUserid(_userid, IrpMicroblog.ISDELFALSE);	
	}
	/**
	 * 通过用户的id计算出有多少个用户关注wo
	 * @return
	 */
	public int MicroblogFocusCountFocusUserid(long _userid){
		return this.irpMicroblogFocusService.countMicroblogFocusFocusUserid(_userid);
	}
	/**
	 * 通过用户ID获取用户最新的一条微知
	 */
	public IrpMicroblog findIrpMicroblogByUserid(long _userid){
		
		
		return this.irpMicroblogService.findFirstMicroblog(_userid);
	}
    /**
     * 过滤带图的字符串  缩略图
     * @param _content
     * @return
     */
    public String getMicroblogContent(String _content){
    	_content = ThumbnailPic.searchThumnailPicSrc(_content, "_150X150");
    	return _content;
    }
    /**
     * 根据id获得用户在页面显示的名字
     * @param _userid
     * @return
     */
    public String getShowPageViewNameByUserId(Long _userid){
    	return this.irpUserService.findShowNameByUserid(_userid);
    }

    /**
     * 根据username获得用户在页面显示的名字
     * @param _userid
     * @return
     */
    public String getShowPageViewNameByUserName(String _username){
    	return this.irpUserService.findShowNameByUsername(_username);
    }
	public IrpQuestion getIrpQuestion(Long _questionid){
	      IrpQuestion irpQuestion =  this.irpQuestionService.findQuestionDetail(_questionid);
	      return irpQuestion;
		}
	
	public int findReplyCountByQuestionId(long _nQuestionId) {
		int nCount = 0;
		try {
			nCount = irpQuestionService.replyTotal(_nQuestionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	public IrpUser getIrpUserByUsername(String _userid){
		if(_userid==null || _userid.length()<3)
			return null;
		String sUserId = _userid.substring(1,_userid.length()-1);
		if(sUserId.length()==0)
			return null;
		long nUserId = 0l;
		try {
			nUserId = Long.parseLong(sUserId);
		} catch (NumberFormatException e) {
			return null;
		}
		IrpUser irpUser = null;
		if (nUserId>0l) {
			irpUser = this.irpUserService.findUserByUserId(nUserId);
		}
		return irpUser;
	}
	
	public String advancedSearch() {
		//权限
		IrpUser user = LoginUtil.getLoginUser();
		boolean result = false;
		StringBuffer stringBuffer = null;
		if(!user.isAdministrator()){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("status", 1);
			List<IrpChannel> channelList = irpChannelService.JsonRightAllChannelMore(map);
			if(channelList!=null && channelList.size()>0){
				stringBuffer = new StringBuffer();
				for(IrpChannel irpChannel : channelList){
					if(irpChannel==null)
						continue;
					stringBuffer.append(" CHANNELID:"+irpChannel.getChannelid());
				}
				result = true;
			}
		}else{
			result = true;
		}
		if(result){
			try {
				if(advancedParam!=null && advancedParam.size()>0){
					int docnum = this.solrService.advancedSearchDocumentNum(advancedParam, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),stringBuffer==null?null:stringBuffer.toString());
					if(docnum>0){
						PageUtil pageUtildocument = new PageUtil(this.pageNum,Integer.parseInt(advancedParam.get("pageSize")),docnum);
						documentlist= this.solrService.advancedSearchDocument(advancedParam,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),pageUtildocument,searchsort,stringBuffer==null?null:stringBuffer.toString());
						this.pageHTML = pageUtildocument.getClientPageHtml("advPage(#PageNum#)");
					}
				}
			} catch (SolrServerException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	/**
	 * 分核心检索跟随
	 */
	public void spellSearchByCore() {
		StringBuffer sArray = new StringBuffer();
		if(keyword==null)
			keyword = "";
		searchtype=searchtype;
		
		List<String> result = solrService.spellSearchByCore(keyword,searchtype);
		sArray.append("[");
		if(result!=null && result.size()>0){
			for (int i = 0; i < result.size(); i++) {
				if(i==0)
					sArray.append("\"").append(result.get(i)).append("\"");
				else
					sArray.append(", \"").append(result.get(i)).append("\"");
			}
			
		}
		sArray.append("]");
		ActionUtil.writerJS(sArray.toString());
	}
	public void spellSearch() {
		StringBuffer sArray = new StringBuffer();
		if(keyword==null)
			keyword = "";
		searchtype=searchtype;
		
		List<String> result = solrService.spellSearch(keyword);
		sArray.append("[");
		if(result!=null && result.size()>0){
			for (int i = 0; i < result.size(); i++) {
				if(i==0)
					sArray.append("\"").append(result.get(i)).append("\"");
				else
					sArray.append(", \"").append(result.get(i)).append("\"");
			}
			
		}
		sArray.append("]");
		ActionUtil.writerJS(sArray.toString());
	}
	/**
	 * 通过传入的id求出转发的微知的对象
	 * @return
	 */
	public IrpMicroblog transpondIrpMicroblog(long _transpond){
		System.out.println(_transpond);
		IrpMicroblog irpMicroblog = null;
		irpMicroblog=this.irpMicroBlogService.irpMicroblog(_transpond);
		return irpMicroblog;
	}
	/**
	 * 通过传入的id求出显示在转发页面的名字
	 * @return
	 */
	public String showPageName(long _transpond){
	    return this.irpUserService.findShowNameByUserid(_transpond);
	    
	}
	/**
	 * 根据ID获得知识
	 * @param _docid
	 * @return
	 */
	public IrpDocument getIrpDocumentById(Long _docid){
		IrpDocument irpDocument = null;
		if (_docid!=null) {
			irpDocument = this.irpDocumentService.irpDocument(_docid);
		}
		return irpDocument;
	}
	/**
	 * 获得知识的封面
	 * @param _docid
	 * @return
	 */
	public String docCoverPath(Long _docid, Integer docflag){
		String filePath="";
		IrpAttached attached=irpAttachedService.getIrpATttachedByDocIDFIle(_docid); 
		if(attached!=null){
			String myFileName=attached.getAttfile(); 
			//获得文件路径 
			filePath= ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="+ThumbnailPic.searchFileName(myFileName, "_150X150");
		}else if(docflag!=null && docflag>0){
			filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand"+docflag+".jpg";
		}else{
			filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand1.jpg";
		} 
		return filePath;
	}
	
	public String findMicById(long _microblogid,int type){
		IrpMicroblog microblog = new IrpMicroblog();
		microblog = irpMicroblogService.irpMicroblog(_microblogid);
		if(microblog!=null){
			if(type==1){
				return microblog.getTranspondid().toString();
			}
			if(type==2){
				return microblog.getIsdel().toString();
			}
			if(type==3){
				return microblog.getTranspondcount().toString();
			}
			if(type==4){
				return microblog.getCommentcount().toString();
			}
			if(type==5){
				return microblog.getUserid().toString();
			}
		}
		return "";
	}
	public String findUserInfoById(long _userId,int type){
		IrpUser user = irpUserService.findUserByUserId(_userId);
		if(user!=null){
			if(type==1){
				return user.getExpertintro();
			}
			if(type==2){
				return user.getLocation();
			}
		}
		return null;
	}
	public IrpQuestion findQuestionById(long questionid){
		IrpQuestion	irpQuestion = irpQuestionService.findQuestionDetail(questionid);
		return irpQuestion;
	}
	
}
