package com.tfs.irp.chnl_doc_link.web;
 
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample.Criteria;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.docstatus.entity.IrpDocstatus;
import com.tfs.irp.docstatus.service.IrpDocStatusService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentExample;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.documentmap.entity.IrpDocumentMap;
import com.tfs.irp.documentmap.service.IrpDocumentMapService;
import com.tfs.irp.form.entity.IrpForm;
import com.tfs.irp.form.service.IrpFormService;
import com.tfs.irp.formcolumn.entity.IrpFormColumn;
import com.tfs.irp.formcolumn.service.IrpFormColumnService;
import com.tfs.irp.inform.entity.IrpInform;
import com.tfs.irp.inform.service.IrpInformService;
import com.tfs.irp.informtype.entity.IrpInformType;
import com.tfs.irp.informtype.service.IrpInformTypeService;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.selectkey.service.IrpSelectKeyService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.solr.service.TRSSearchService;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.timedtask.service.TimedTaskTermsImport;
import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.topic.service.IrpTopicService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userlove.entity.IrpUserLove;
import com.tfs.irp.userlove.service.IrpUserLoveService;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.service.IrpUserPrivacyService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.ThumbnailPic;

public class Chnl_Doc_LinkAction extends ActionSupport {
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
	private IrpChannelService irpChannelService;//栏目service
	private IrpDocStatusService irpDocStatusService;
	private List<IrpChnlDocLink>  chnlDocLinks;
	private List<IrpChannel> channels;//所有的栏目
	private IrpRoleService irpRoleService;
	private List<IrpUser> irpRoles;
	private IrpTopicService irpTopicService;
	private IrpDocumentService irpDocumentService;
	private IrpDocumentMapService irpDocumentMapService;
	private IrpSiteService irpSiteService;
	private IrpInformService irpInformService;
	private List jubaodocuments;
	private IrpInformTypeService irpInformTypeService;
	private List<IrpInformType> irpInformTypelist;
	private List<IrpDocument> irpDocumentList;
	private String flag = "map";//map为地图 sub为专题
	private IrpChannel superChannel;
	private Integer changeNum = 0;
	private String searchInfo;
	private String searchwhere;
	private SolrService solrService;
	private List documentList;
	private String searchsort;
	private IrpSelectKeyService irpSelectKeyService;
	private TRSSearchService trsSearchService;
	private String cid;
	private String friendlyshow;
	private String informKey;
	private Long[] docids;
	private String sitetype;
	private Long docid; //文档主键
	private Long channelid;
	private IrpUserLoveService irpUserLoveService;
	private IrpChannel irpChannel;
	private IrpTagService irpTagService;
	private List<IrpTag> irpTags;
	private Long siteid;
	private Long id=new Long(-1);///channelid 
	//接收所有文档简约表id
	private String[] chandocids;
	private int isGC=0;//如果这个值为0，则他就是正常，如果为1，代表是文档回收站
	private String channelorDocument;
	private int pageNum=1; 
	private int pageSize=15; 
	private IrpAttachedService irpAttachedService;
    private List<IrpDocstatus> irpDocstatus;
    private String docidlist;
	private List<IrpChnlDocLink> irpChnldoclist;
	private String pageHTML;
	private String orderField=""; 
	private String orderBy="";
	private String searchDocTitle; 
	private String searchModal; 
	private Long personid;
	private Long loginuserid;//获取当前登录用户的id 
	private List<IrpUser> irpUsers;
	private String crtimesort;//按照创建时间排血值为asc desc
	private IrpChnlDocLink chnlDocLink;
    private IrpUserService irpUserService; 
	private String userPicUrl;
	private List< IrpTopic> irpTopIcList;
	private IrpUserPrivacyService irpUserPrivacyService;
	private Integer loginuserprivacy;
	private List<IrpChnlDocLink> chnlDocLinksGongGao;
	private List<IrpChnlDocLink> chnlDocLinkMONTH;
	
	public String getChnldocid() {
		return chnldocid;
	}
	public void setChnldocid(String chnldocid) {
		this.chnldocid = chnldocid;
	}
	public String getChnldocids() {
		return chnldocids;
	}
	public void setChnldocids(String chnldocids) {
		this.chnldocids = chnldocids;
	}

	/**
	 * 统计时指定时间
	 */
	private String timeLimit;
	/**
	 * 开始时间
	 */
	private Date startTime ;
	/**
	 * 结束时间
	 */
	private Date endTime;
	private String fileName;//导出的zip文件名
	private String start;
	private String end;
	private String username;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public IrpDocStatusService getIrpDocStatusService() {
		return irpDocStatusService;
	}
	public void setIrpDocStatusService(IrpDocStatusService irpDocStatusService) {
		this.irpDocStatusService = irpDocStatusService;
	}
	public IrpRoleService getIrpRoleService() {
		return irpRoleService;
	}
	public void setIrpRoleService(IrpRoleService irpRoleService) {
		this.irpRoleService = irpRoleService;
	}
	public List<IrpUser> getIrpRoles() {
		return irpRoles;
	}
	public void setIrpRoles(List<IrpUser> irpRoles) {
		this.irpRoles = irpRoles;
	}
	public IrpTopicService getIrpTopicService() {
		return irpTopicService;
	}
	public void setIrpTopicService(IrpTopicService irpTopicService) {
		this.irpTopicService = irpTopicService;
	}
	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}
	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}
	public IrpDocumentMapService getIrpDocumentMapService() {
		return irpDocumentMapService;
	}
	public void setIrpDocumentMapService(IrpDocumentMapService irpDocumentMapService) {
		this.irpDocumentMapService = irpDocumentMapService;
	}
	public IrpSiteService getIrpSiteService() {
		return irpSiteService;
	}
	public void setIrpSiteService(IrpSiteService irpSiteService) {
		this.irpSiteService = irpSiteService;
	}
	public IrpInformService getIrpInformService() {
		return irpInformService;
	}
	public void setIrpInformService(IrpInformService irpInformService) {
		this.irpInformService = irpInformService;
	}
	public List getJubaodocuments() {
		return jubaodocuments;
	}
	public void setJubaodocuments(List jubaodocuments) {
		this.jubaodocuments = jubaodocuments;
	}
	public IrpInformTypeService getIrpInformTypeService() {
		return irpInformTypeService;
	}
	public void setIrpInformTypeService(IrpInformTypeService irpInformTypeService) {
		this.irpInformTypeService = irpInformTypeService;
	}
	public List<IrpInformType> getIrpInformTypelist() {
		return irpInformTypelist;
	}
	public void setIrpInformTypelist(List<IrpInformType> irpInformTypelist) {
		this.irpInformTypelist = irpInformTypelist;
	}
	public List<IrpDocument> getIrpDocumentList() {
		return irpDocumentList;
	}
	public void setIrpDocumentList(List<IrpDocument> irpDocumentList) {
		this.irpDocumentList = irpDocumentList;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public IrpChannel getSuperChannel() {
		return superChannel;
	}
	public void setSuperChannel(IrpChannel superChannel) {
		this.superChannel = superChannel;
	}
	public Integer getChangeNum() {
		return changeNum;
	}
	public void setChangeNum(Integer changeNum) {
		this.changeNum = changeNum;
	}
	public String getSearchInfo() {
		return searchInfo;
	}
	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}
	public String getSearchwhere() {
		return searchwhere;
	}
	public void setSearchwhere(String searchwhere) {
		this.searchwhere = searchwhere;
	}
	public SolrService getSolrService() {
		return solrService;
	}
	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	}
	public List getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List documentList) {
		this.documentList = documentList;
	}
	public String getSearchsort() {
		return searchsort;
	}
	public void setSearchsort(String searchsort) {
		this.searchsort = searchsort;
	}
	public IrpSelectKeyService getIrpSelectKeyService() {
		return irpSelectKeyService;
	}
	public void setIrpSelectKeyService(IrpSelectKeyService irpSelectKeyService) {
		this.irpSelectKeyService = irpSelectKeyService;
	}
	public TRSSearchService getTrsSearchService() {
		return trsSearchService;
	}
	public void setTrsSearchService(TRSSearchService trsSearchService) {
		this.trsSearchService = trsSearchService;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getFriendlyshow() {
		return friendlyshow;
	}
	public void setFriendlyshow(String friendlyshow) {
		this.friendlyshow = friendlyshow;
	}
	public Long[] getDocids() {
		return docids;
	}
	public void setDocids(Long[] docids) {
		this.docids = docids;
	}
	public String getSitetype() {
		return sitetype;
	}
	public void setSitetype(String sitetype) {
		this.sitetype = sitetype;
	}
	public Long getChannelid() {
		return channelid;
	}
	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}
	public IrpUserLoveService getIrpUserLoveService() {
		return irpUserLoveService;
	}
	public void setIrpUserLoveService(IrpUserLoveService irpUserLoveService) {
		this.irpUserLoveService = irpUserLoveService;
	}
	public IrpTagService getIrpTagService() {
		return irpTagService;
	}
	public void setIrpTagService(IrpTagService irpTagService) {
		this.irpTagService = irpTagService;
	}
	public List<IrpTag> getIrpTags() {
		return irpTags;
	}
	public void setIrpTags(List<IrpTag> irpTags) {
		this.irpTags = irpTags;
	}
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}
	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}
	public IrpChnlDocLink getChnlDocLink() {
		return chnlDocLink;
	}
	public void setChnlDocLink(IrpChnlDocLink chnlDocLink) {
		this.chnlDocLink = chnlDocLink;
	}
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}
	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	public String getUserPicUrl() {
		return userPicUrl;
	}
	public void setUserPicUrl(String userPicUrl) {
		this.userPicUrl = userPicUrl;
	}
	public List<IrpTopic> getIrpTopIcList() {
		return irpTopIcList;
	}
	public void setIrpTopIcList(List<IrpTopic> irpTopIcList) {
		this.irpTopIcList = irpTopIcList;
	}
	public IrpUserPrivacyService getIrpUserPrivacyService() {
		return irpUserPrivacyService;
	}
	public void setIrpUserPrivacyService(IrpUserPrivacyService irpUserPrivacyService) {
		this.irpUserPrivacyService = irpUserPrivacyService;
	}
	public Integer getLoginuserprivacy() {
		return loginuserprivacy;
	}
	public void setLoginuserprivacy(Integer loginuserprivacy) {
		this.loginuserprivacy = loginuserprivacy;
	}
	public List<IrpChnlDocLink> getChnlDocLinksGongGao() {
		return chnlDocLinksGongGao;
	}
	public void setChnlDocLinksGongGao(List<IrpChnlDocLink> chnlDocLinksGongGao) {
		this.chnlDocLinksGongGao = chnlDocLinksGongGao;
	}
	public List<IrpChnlDocLink> getChnlDocLinkMONTH() {
		return chnlDocLinkMONTH;
	}
	public void setChnlDocLinkMONTH(List<IrpChnlDocLink> chnlDocLinkMONTH) {
		this.chnlDocLinkMONTH = chnlDocLinkMONTH;
	}
	//查看我投稿的知识
	public String myallTouGao(){
		loginuserid=LoginUtil.getLoginUser().getUserid();//设置userid权限页面使用  
		//得到当前用户的所有栏目对象 
		 List<IrpChannel> irpChannels=new ArrayList<IrpChannel>(); 
		//按照创建时间排序
	 		String sOrderByClause=null;
	 		if(crtimesort!=null ){
	 			if(crtimesort.equals("asc")){
	 				  sOrderByClause = "crtime asc";  
	 			}
	 			if(crtimesort.equals("desc")){
	 				  sOrderByClause = "crtime desc";  
	 			} 
	 		}   
		 if(personid!=null && personid!=0L){
			 irpChannels= irpChannelService.findChannelIdsByPerson(personid,0L, irpChannels); 
		}else{
			 irpChannels= irpChannelService.findChannelIdsByPerson(loginuserid,0L, irpChannels); 
		}
		 if(irpChannels!=null && irpChannels.size()>0){
			 List<Long> channelidsList=new ArrayList<Long>();
			 for (IrpChannel channel:irpChannels) {
				 if(channel!=null){
					 channelidsList.add(channel.getChannelid());
				 }
			 }
			 if(channelidsList!=null && channelidsList.size()>0){
				//先查询数量
					HashMap<String,Object> map=new HashMap<String, Object>();
					map.put("docstatus",IrpChnlDocLink.DOC_STATUS_CAOGAO);   
			 		//map.put("channelid",this.id); 
					map.put("channelidsList", channelidsList);
			 		//得到自己创建的所有知识的id
			 		List<Long> myallDociIds=irpChnl_Doc_LinkService.allDocids(map,IrpDocument.DOCUMENT_NOT_INFORM);
			 		//到企业知识库查寻oldid在这个集合里面的所有知识 
			 		int aDataCount=irpChnl_Doc_LinkService.alltougaodocumentCount(myallDociIds,IrpDocument.DOCUMENT_NOT_INFORM);
			 		PageUtil pageUtil= new PageUtil(this.pageNum, this.pageSize, aDataCount); 
			 		chnlDocLinks=irpChnl_Doc_LinkService.alltougaodocument(pageUtil,myallDociIds,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM);
				  //初始化用户图片地址
					IrpUser user = irpUserService.findUserByUserId(LoginUtil.getLoginUser().getUserid());
					this.userPicUrl = user.getDefaultUserPic();
			 		this.pageHTML = pageUtil.getClientPageHtml("allsubmission(#PageNum#)");   //chnlDocLinks  
			 }
		 }
	 	return SUCCESS;
	}
	
	
	//查看我收藏的知识
	public String docCollection(){
		loginuserid=LoginUtil.getLoginUser().getUserid();//设置userid权限页面使用  
		//得到当前用户的所有栏目对象 
		 List<IrpChannel> irpChannels=new ArrayList<IrpChannel>(); 
		//按照创建时间排序
	 		String sOrderByClause=null;
	 		if(crtimesort!=null ){
	 			if(crtimesort.equals("asc")){
	 				  sOrderByClause = "crtime asc";  
	 			}
	 			if(crtimesort.equals("desc")){
	 				  sOrderByClause = "crtime desc";  
	 			} 
	 		}   
		 if(personid!=null && personid!=0L){
			 irpChannels= irpChannelService.findChannelIdsByPerson(personid,0L, irpChannels); 
		}else{
			 irpChannels= irpChannelService.findChannelIdsByPerson(loginuserid,0L, irpChannels); 
		}
		 if(irpChannels!=null && irpChannels.size()>0){
			 List<Long> channelidsList=new ArrayList<Long>();
			 for (IrpChannel channel:irpChannels) {
				 if(channel!=null){
					 channelidsList.add(channel.getChannelid());
				 }
			 }
			 if(channelidsList!=null && channelidsList.size()>0){
				//先查询数量
					HashMap<String,Object> map=new HashMap<String, Object>();
					map.put("docstatus",IrpChnlDocLink.DOC_STATUS_CAOGAO);   
			 		//map.put("channelid",this.id); 
					map.put("channelidsList", channelidsList);
			 		//得到自己创建的所有知识的id
			 		List<Long> myallDociIds=irpChnl_Doc_LinkService.allDocids(map,IrpDocument.DOCUMENT_NOT_INFORM);
			 		//到企业知识库查寻oldid在这个集合里面的所有知识 
			 		int aDataCount=irpChnl_Doc_LinkService.alltougaodocumentCount(myallDociIds,IrpDocument.DOCUMENT_NOT_INFORM);
			 		PageUtil pageUtil= new PageUtil(this.pageNum, this.pageSize, aDataCount); 
			 		chnlDocLinks=irpChnl_Doc_LinkService.alltougaodocument(pageUtil,myallDociIds,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM);
				  //初始化用户图片地址
					IrpUser user = irpUserService.findUserByUserId(LoginUtil.getLoginUser().getUserid());
					this.userPicUrl = user.getDefaultUserPic();
			 		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");   //chnlDocLinks  
			 }
		 }
	 	return SUCCESS;
	}
	//查询某一个栏目的名称显示
	public String findChnlName(Long _channelid){
		String chnlName="";
		chnlName=irpChannelService.findChannelNameByChannelid(_channelid);
		return chnlName;
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
	
	//创建者其他知识
	public String personCollectionDocument(){
		String sOrderByClause = "docid desc";
		Integer amount=10;
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause",sOrderByClause);
		map.put("amount",amount); 
		if(this.personid!=null){
			map.put("cruserid",this.personid);
		}else{
			map.put("cruserid", LoginUtil.getLoginUserId());
		}
		chnlDocLinks=irpChnl_Doc_LinkService.selectLikeDocumentToList(docid,map,IrpDocument.DOCUMENT_NOT_INFORM);
		return SUCCESS;
	}
	/**
	 * 其它知识
	 * mobile
	 * @return
	 */
	public void personCollectionDocumentBMobile(){
		String sOrderByClause = "docid desc";
		Integer amount=10;
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause",sOrderByClause);
		map.put("amount",amount); 
		if(this.personid!=null){
			map.put("cruserid",this.personid);
		}else{
			map.put("cruserid", LoginUtil.getLoginUserId());
		}
		chnlDocLinks=irpChnl_Doc_LinkService.selectLikeDocumentToList(docid,map,IrpDocument.DOCUMENT_NOT_INFORM);
		List list = new ArrayList();
		for (int i = 0; i < chnlDocLinks.size(); i++) {
			IrpChnlDocLink icd = chnlDocLinks.get(i);
			list.add("{\"docid\":\""+icd.getDocid()+"\"");
			list.add("\"doctitle\":\""+icd.getDoctitle()+"\"}");
		}
		ActionUtil.writer(list.toString());
		
	}
	
	//猜你喜欢     selectLikeDocumentToList
	public String youLikeDocument(){
		IrpUser irpUser=LoginUtil.getLoginUser(); 
		List<Long> docIds=irpUserLoveService.docIdsByUserid(irpUser.getUserid(),IrpUserLove.CAI_YOU_LIKE);
		if(docIds!=null &&docIds.size()>0){
			chnlDocLinks=irpChnl_Doc_LinkService.chnlDocByDocIds(docIds,IrpDocument.DOCUMENT_NOT_INFORM,5);
		} 
		return SUCCESS;
	}
	
	//分享排行（根据cruserid分组查询转发次数之和查询cruserid 然后查询这些cruserid所在的用户列表显示）
	public String hotShareDocument(){
		int nDataCount = SysConfigUtil.getSysConfigNumValue(IrpUser.SHARERANKINGVIEWNUM);
		PageUtil pageUtil = new PageUtil(1, nDataCount, nDataCount);
		irpUsers = irpUserService.shareRank(pageUtil);
		return SUCCESS;
	}
	
	/**
	 * 企业细览页面的经典知识
	 * @return
	 */
	public String jingdianDocument(){ 
		/**
		 * 已发布，并且他是有封面的
		 */
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("docstatus",IrpDocument.PUBLISH_STATUS); 
		//map.put("channelid",this.id); //栏目id>0代表，栏目没有删除，因此这个文章可以显示  
		map.put("sOrderByClause", " docchannel desc ");//按照加精数量倒叙
		List<Long> _arrChannelIds=new ArrayList<Long>();
		//_arrChannelIds=	irpChannelService.findChannelIdsByParentId(this.id, _arrChannelIds,IrpSite.SITE_TYPE_PUBLISH,IrpChannel.IS_DOCSTATIUS,LoginUtil.getLoginUser());
		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS, this.id, IrpSite.SITE_TYPE_PUBLISH, LoginUtil.getLoginUser());
	
		if(_arrChannelIds!=null && _arrChannelIds.size()>0){
			map.put("channelidsList", _arrChannelIds);
			chnlDocLinks=irpChnl_Doc_LinkService.selectDocumentByMap(map,IrpDocument.DOCUMENT_NOT_INFORM); 
		}
		return SUCCESS;
	}
	//相关专题
		public String collectionSubject(){ 
			if(docid!=null && docid!=0L){
				//查询到id
				IrpUser irpUser=LoginUtil.getLoginUser();
				List<Long> xDocids=irpUserLoveService.xDocids(irpUser.getUserid(), IrpUserLove.XIANG_GUAN_SUBJECT, this.docid);
				if(xDocids!=null && xDocids.size()>0){
					irpTopIcList=irpTopicService.findAllToPic(xDocids);
				}
			}
			return SUCCESS;
		}
	//人气top,（根据cruserid分组查询点击量之和查询cruserid 然后查询这些cruserid所在的用户列表显示）
	public String hotPersonDocument(){
		String sOrderByClause = "hitscount desc";
		Integer amount=10;
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause",sOrderByClause);
		map.put("amount",amount);
		chnlDocLinks=irpChnl_Doc_LinkService.selectLikeDocumentToList(null,map,IrpDocument.DOCUMENT_NOT_INFORM);
		return SUCCESS;
	}
	//相关知识，
	public String collectionDocument(){
 		if(docid!=null && docid.longValue()>0){
 			IrpDocument doc = irpDocumentService.findDocumentByDocId(docid);
 			if(doc!=null){
 				//权限
 				IrpUser user = LoginUtil.getLoginUser();
 				StringBuffer stringBuffer = new StringBuffer();
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
 					}
 				}
 				try {
 					irpDocumentList = solrService.relativeDocByKeyword(doc, stringBuffer.toString());
				} catch (SolrServerException e) {
					e.printStackTrace();
				}
 			}
		}
		return SUCCESS;
	}
	/**
	 * 相关知识
	 * mobile
	 * @return
	 */
	public void collectionDocumentOfMobile(){
		String mstr = "";
 		if(docid!=null && docid!=0){
			IrpUser irpUser=LoginUtil.getLoginUser();
			List<Long> docIds=irpUserLoveService.xDocids(null, IrpUserLove.XIANG_GUAN_DOCUMENT, this.docid);
			if(docIds!=null &&docIds.size()>0){
				List list = new ArrayList();
				chnlDocLinks=irpChnl_Doc_LinkService.chnlDocByDocIds(docIds,IrpDocument.DOCUMENT_NOT_INFORM,10);
				for (int i = 0; i < chnlDocLinks.size(); i++) {
					IrpChnlDocLink icd = chnlDocLinks.get(i);
					list.add("{\"docid\":\""+icd.getDocid()+"\"");
					list.add("\"doctitle\":\""+icd.getDoctitle()+"\"}");
				}
				ActionUtil.writer(list.toString());
			}
		}
		
	}
	
	
	//正在热议    
	public String hotTlak(){
		String sOrderByClause = "commentcount desc";
		Integer amount=5;
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause",sOrderByClause);
		map.put("amount",amount);
		chnlDocLinks=irpChnl_Doc_LinkService.selectLikeDocumentToList(null,map,IrpDocument.DOCUMENT_NOT_INFORM);
		return SUCCESS;
	}  
	
	public Long getDocid() {
		return docid;
	}
	public void setDocid(Long docid) {
		this.docid = docid;
	}
	//细览页面的刷新
	public String clientShowChannelDocJson(){ 
		chnlDocLinks=irpChnl_Doc_LinkService.clientChannelShowDoc(this.id,IrpDocument.DOCUMENT_NOT_INFORM);
		//经典案例
		/**
		 * 已发布，并且他是有封面的
		 */
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("docstatus",IrpDocument.PUBLISH_STATUS); 
		map.put("channelid",this.id);//栏目id>0代表，栏目没有删除，因此这个文章可以显示
		map.put("isFengMian",IrpAttached.IS_FENGMIAN);
		map.put("sOrderByClause", " docchannel desc ");//按照加精数量倒叙
		List<IrpChnlDocLink> cList=irpChnl_Doc_LinkService.selectDocumentByMap(map,IrpDocument.DOCUMENT_NOT_INFORM);
		if(cList!=null && cList.size()>0){
			chnlDocLink=cList.get(0);
		} 
		return SUCCESS;
	}
	//显示自己创建的所有
	public String myAllDocument(){
		loginuserid=LoginUtil.getLoginUser().getUserid();
		chnlDocLinks=irpChnl_Doc_LinkService.myAllDocument(IrpDocument.DOCUMENT_NOT_INFORM); 
		return SUCCESS;
	}
	//得到自己的所有知识的数量
	public void myDocumentCount(){
		int nCount=irpChnl_Doc_LinkService.myDocumentCount(IrpDocument.DOCUMENT_NOT_INFORM);
		ActionUtil.writer(String.valueOf(nCount));
	}
	//企业知识库点击栏目查看他是否有对他查看栏目下的知识列表权限（进入页面执行）
	public void rightDocList(){
		Long _channelid=this.id;
		String str=ERROR;//你看我改页面
		if(_channelid!=null&& _channelid!=0L) {
			IrpUser irpUser=LoginUtil.getLoginUser();
			if(!irpUser.isAdministrator()){
				Boolean b=irpChannelService.findRightToChannel(irpUser, _channelid, "DOCUMENT_LIST", IrpChannel.CHANNEL_IS_PUBLISH);
				if(b)str=SUCCESS;
				else str=ERROR;
		 
			}else{
				str=SUCCESS;
			}
		}
		ActionUtil.writer(str);
	}
	//添加知识的权限和列表权限
	public void rightAddDoc(){
		Long _channelid=this.id;
		String str=ERROR;//你看我改页面
		if(_channelid!=null&& _channelid!=0L) {
			IrpUser irpUser=LoginUtil.getLoginUser();
			if(!irpUser.isAdministrator()){
				Boolean rightlist=irpChannelService.findRightToChannel(irpUser, _channelid, "DOCUMENT_LIST", IrpChannel.CHANNEL_IS_PUBLISH);
				if(rightlist){
					Boolean b=irpChannelService.findRightToChannel(irpUser, _channelid, "DOCUMENT_ADD", IrpChannel.CHANNEL_IS_PUBLISH);
					if(b)str=SUCCESS;
					else str=ERROR;
				}
				else str=ERROR;
			}else{
				str=SUCCESS;
			}
		}
		ActionUtil.writer(str);
	}
	
	//知识专题权限和列表权限
	public void subclientrightadddoc(){
		String str="double";
		IrpUser irpUser=LoginUtil.getLoginUser();
		if(!irpUser.isAdministrator()){
			Boolean createSubject=irpChannelService.findRightSubToChannel(irpUser, "SUBJECT_ADD", IrpChannel.CHANNEL_IS_PUBLISH,IrpChannel.TABLE_NAME,null);
			Boolean quoteToSubject=irpChannelService.findRightSubToChannel(irpUser, "SUBJECT_QUOTE", IrpChannel.CHANNEL_IS_PUBLISH,IrpChannel.TABLE_NAME,null);
			if(!createSubject && !quoteToSubject){
				str = ERROR;
			}else if(createSubject && !quoteToSubject){
				str = "create";
			}else if(!createSubject && quoteToSubject){
				str = "quote";
			}
		}
		ActionUtil.writer(str);
	}
	
	public void subclientrightquote(){
		IrpUser irpUser=LoginUtil.getLoginUser();
		String result = "quote";
		if(this.cid!=null && !"".equals(cid)){
			if(!irpUser.isAdministrator()){
				Boolean quoteToSubject=irpChannelService.findRightSubToChannel(irpUser, "SUBJECT_QUOTE", IrpChannel.CHANNEL_IS_PUBLISH,IrpChannel.TABLE_NAME,cid);
				if(!quoteToSubject){
					result = "error";
				}
			}
			//检索专题不允许引用知识
			Long chanid = Long.valueOf(this.cid);
			IrpChannel chan = irpChannelService.finChannelByChannelid(chanid);
			if(chan!=null){
				if(chan.getChnltype()!=null &&chan.getChnltype()!=0){
					if(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT==chan.getChnltype()){
						result = "error";
					}
				}else{
					result = "error";
				}
			}else{
				result = "error";
			}
		}
		ActionUtil.writer(result);
	}
	
	//前台显示一级栏目的文档 概览页面 
	private  String searchWord; 
	private  String searchType;

	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	//获取前台地图下的知识列表
	public String mapProbableDocShow(){
		if(this.id==null || this.id<=0L){
			return SUCCESS;
//			this.id=Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID"));
		}
	    irpChannel=irpChannelService.finChannelByChannelid(this.id);
	    if(irpChannel==null) return ERROR;
		//处理排序
		String sOrderByClause = "";
		String sOrderByClauseDoc = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "docpubtime desc";
			sOrderByClauseDoc = "docpubtime desc";
		}else{
			if("commentcount".equals(this.orderField)){
				sOrderByClauseDoc = "recommendcounts"+" "+this.orderBy;
			}else{
				sOrderByClauseDoc = this.orderField+" "+this.orderBy;
			}
			sOrderByClause = this.orderField+" "+this.orderBy;
		} 
 		List<Long> _arrChannelIds=new ArrayList<Long>();
 		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		//知识地图id集合
		_arrChannelIds=irpChannelService.mapChildrenIds(irpChannel.getChannelid(), IrpChannel.IS_DOCSTATIUS,_arrChannelIds);
		List<Long> docidsList=irpDocumentMapService.docidsByChannelidS(_arrChannelIds,IrpDocumentMap.KONWLEDGE_MAP);
		if(docidsList!=null && docidsList.size()>0){
			//将没有查看这个doc栏目和文章权限的剔除出去
			HashMap<String, Object> exampleMap = new HashMap<String, Object>();
			exampleMap.put("status", 1);
			List<IrpChannel> chanls = irpChannelService
					.JsonRightAllChannelMore(exampleMap);
			if(chanls!=null && chanls.size()>0){
				IrpDocumentExample docExample = new IrpDocumentExample();
				docExample.createCriteria().andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)
											.andDocidIn(docidsList);
				List<IrpDocument> getrightdocList = irpDocumentService.findAllDatas(docExample);
				if(getrightdocList!=null && getrightdocList.size()>0){
					for(int i =0;i<getrightdocList.size();i++){
						int deletechnlid = getrightdocList.get(i).getChannelid().intValue();
						boolean ifhasright = false;
						for(int j = 0;j<chanls.size();j++){
							int hasrightchnlid = chanls.get(j).getChannelid().intValue();
							if(deletechnlid==hasrightchnlid){
								ifhasright = true;
								break;
							}
						}
						if(!ifhasright){
							docidsList.remove(getrightdocList.get(i).getDocid());
						}
					}
					if(docidsList!=null && docidsList.size()>0){
						HashMap<String, Object> map=new HashMap<String, Object>();
						map.put("docidsList", docidsList);
						map.put("docstatus", IrpDocument.PUBLISH_STATUS);
						int aDataCount=irpChnl_Doc_LinkService.selectCountByChannelidAndDocStatus(map, IrpDocument.DOCUMENT_NOT_INFORM, IrpSite.SITE_TYPE_PUBLISH);
						PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
						IrpDocumentExample example = new IrpDocumentExample();
						example.createCriteria().andDocidIn(docidsList);
						example.setOrderByClause(sOrderByClause);
						chnlDocLinks=irpChnl_Doc_LinkService.chanAllDocLink(pageUtil,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM,IrpSite.SITE_TYPE_PUBLISH); 
						example = new IrpDocumentExample();
						example.createCriteria().andDocidIn(docidsList);
						example.setOrderByClause(sOrderByClauseDoc);
						irpDocumentList = irpDocumentService.selectByExample(pageUtil, example);
						this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
					}
				}
				
			}
		}
		return SUCCESS;
	}
	//获取前台专题下的知识列表
	public String subprobabledocumentshow(){
		return subprobabledocumentshowzi();
	}
	
	public String subprobabledocumentshowzi(){
		if(this.id==null || this.id<=0L){
			return SUCCESS;
			//this.id=Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_QIYE_SUBJECT_ID"));
		}
	    irpChannel=irpChannelService.finChannelByChannelid(this.id);
	    if(irpChannel==null) return ERROR;
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "docpubtime desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}  
 		List<Long> _arrChannelIds=new ArrayList<Long>();
 		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUBJECT);
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT);
		//知识专题id集合
		_arrChannelIds=irpChannelService.mapChildrenIds(irpChannel.getChannelid(), IrpChannel.IS_DOCSTATIUS,_arrChannelIds);
		List<Long> docidsList=irpDocumentMapService.docidsByChannelidS(_arrChannelIds,IrpDocumentMap.KNOWLEDGE_SUBJECT);
		if(docidsList!=null && docidsList.size()>0){
			//将没有查看这个doc栏目和文章权限的剔除出去
			HashMap<String, Object> exampleMap = new HashMap<String, Object>();
			exampleMap.put("status", 1);
			List<IrpChannel> chanls = irpChannelService
					.JsonRightAllChannelMore(exampleMap);
			if(chanls!=null && chanls.size()>0){
				IrpDocumentExample docExample = new IrpDocumentExample();
				docExample.createCriteria().andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)
											.andDocidIn(docidsList);
				List<IrpDocument> getrightdocList = irpDocumentService.findAllDatas(docExample);
				if(getrightdocList!=null && getrightdocList.size()>0){
					for(int i =0;i<getrightdocList.size();i++){
						int deletechnlid = getrightdocList.get(i).getChannelid().intValue();
						boolean ifhasright = false;
						for(int j = 0;j<chanls.size();j++){
							int hasrightchnlid = chanls.get(j).getChannelid().intValue();
							if(deletechnlid==hasrightchnlid){
								ifhasright = true;
								break;
							}
						}
						if(!ifhasright){
							docidsList.remove(getrightdocList.get(i).getDocid());
						}
					}
					if(docidsList!=null && docidsList.size()>0){
						HashMap<String, Object> map=new HashMap<String, Object>();
						map.put("docidsList", docidsList);
						map.put("docstatus", IrpDocument.PUBLISH_STATUS);
						int aDataCount = irpDocumentService.selectCountByChannelidAndDocStatus(map, IrpDocument.DOCUMENT_NOT_INFORM);
						PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
						IrpDocumentExample example = new IrpDocumentExample();
						example.createCriteria().andDocidIn(docidsList);
						example.setOrderByClause(sOrderByClause);
						irpDocumentList = irpDocumentService.selectByExample(pageUtil, example);
						this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
					}
				}
				
			}
		}
		return SUCCESS;
	}
	
	public void docMapZtree(){
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		IrpChannel rootChannel=irpChannelService.finChannelByChannelid(Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID")));
//		Map<String,Object> item=new HashMap<String, Object>();
//		item.put("id",rootChannel.getChannelid());//即将作为下一节点的parentid
//		item.put("name",rootChannel.getChnlname());
//		item.put("open", true);
//		item.put("pId", 0L);
//		treeNode.add(item); 
		this.id=rootChannel.getChannelid();
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("status",1);//查询没有进入栏目回收站的栏目
		List<Integer> chnlTypes=new ArrayList<Integer>(); 
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_MAP); 
		map.put("parentid",this.id);
		treeNode=getMapTreeNode( chnlTypes, map, treeNode);
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}
	
	public List<Map<String, Object>>  getMapTreeNode(List<Integer> chnlTypes ,HashMap<String,Object> map,List<Map<String, Object>>  treeNode){
		List<IrpChannel> irpChannels=irpChannelService.siteAllChannel(null,map,chnlTypes);//他里面的所有一级栏目  
		if(irpChannels!=null && irpChannels.size()>0){
			IrpUser irpUser=LoginUtil.getLoginUser();
			for (int j = 0; j < irpChannels.size(); j++) {
				IrpChannel channel=irpChannels.get(j);
				if(!irpUser.isAdministrator()){
					Boolean mapSelect=irpChannelService.findRightSubToChannel(irpUser, "MAP_SELECT", IrpChannel.CHANNEL_IS_PUBLISH,IrpChannel.TABLE_NAME,irpChannels.get(j).getChannelid().toString());
					if(!mapSelect){
//						int rootChildNum = irpChannelService.findChild_Channl_CountByParentId(channel.getChannelid());
//						if(rootChildNum>0){
//							map=new HashMap<String, Object>();
//							map.put("status",1);//查询没有进入栏目回收站的栏目
//							map.put("parentid",channel.getChannelid());
//							treeNode=getMapTreeNode( chnlTypes, map, treeNode);
//						}
						continue;
					}
				}
				Map<String,Object> selfitem=new HashMap<String, Object>();
				selfitem.put("id",channel.getChannelid());//即将作为下一节点的parentid
				selfitem.put("name",channel.getChnlname());
				int rootCount = irpChannelService.findChild_Channl_CountByParentId(channel.getChannelid());
				selfitem.put("pId",channel.getParentid());
				if(rootCount>0){
					selfitem.put("open", false);
					map=new HashMap<String, Object>();
					map.put("status",1);//查询没有进入栏目回收站的栏目
					map.put("parentid",channel.getChannelid());
					treeNode=getMapTreeNode( chnlTypes, map, treeNode);
				}else{
					selfitem.put("open", true);
				}
				treeNode.add(selfitem); 
			}
		}
		return treeNode;
	}
	
	public void getFirstMapNode(){
		String result = "0";
		IrpChannel rootChannel=irpChannelService.finChannelByChannelid(Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID")));
		this.id=rootChannel.getChannelid();
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("status",1);//查询没有进入栏目回收站的栏目
		List<Integer> chnlTypes=new ArrayList<Integer>(); 
		chnlTypes.add((IrpChannel.CHANNEL_TYPE_MAP));
		map.put("parentid",this.id);
		List<IrpChannel> irpChannels=irpChannelService.siteAllChannel(null,map,chnlTypes);
		if(irpChannels!=null && irpChannels.size()>0){
			IrpUser irpUser=LoginUtil.getLoginUser();
			if(irpUser.isAdministrator()){
				result = irpChannels.get(0).getChannelid().toString();
			}else{
				for (int j = 0; j < irpChannels.size(); j++) {
					IrpChannel channel=irpChannels.get(j);
					Boolean mapSelect=irpChannelService.findRightSubToChannel(irpUser, "MAP_SELECT", IrpChannel.CHANNEL_IS_PUBLISH,IrpChannel.TABLE_NAME,irpChannels.get(j).getChannelid().toString());
					if(!mapSelect){
						continue;
					}else{
						result = channel.getChannelid().toString();
						break;
					}
				}
			}
		}
		ActionUtil.writer(result);
	}
	
	public void getFirstNode(){
		String result = "0";
		IrpChannel rootChannel=irpChannelService.finChannelByChannelid(Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_QIYE_SUBJECT_ID")));
		this.id=rootChannel.getChannelid();
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("status",1);//查询没有进入栏目回收站的栏目
		List<Integer> chnlTypes=new ArrayList<Integer>(); 
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUBJECT); 
		chnlTypes.add((IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT));
		map.put("parentid",this.id);
		List<IrpChannel> irpChannels=irpChannelService.siteAllChannel(null,map,chnlTypes);
		if(irpChannels!=null && irpChannels.size()>0){
			IrpUser irpUser=LoginUtil.getLoginUser();
			if(irpUser.isAdministrator()){
				result = irpChannels.get(0).getChannelid().toString();
			}else{
				for (int j = 0; j < irpChannels.size(); j++) {
					IrpChannel channel=irpChannels.get(j);
					Boolean subjectSelect=irpChannelService.findRightSubToChannel(irpUser, "SUBJECT_SELECT", IrpChannel.CHANNEL_IS_PUBLISH,IrpChannel.TABLE_NAME,irpChannels.get(j).getChannelid().toString());
					if(!subjectSelect){
						continue;
					}else{
						result = channel.getChannelid().toString();
						break;
					}
				}
			}
		}
		ActionUtil.writer(result);
	}
	
	public void docSubMapZtree(){
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		IrpChannel rootChannel=irpChannelService.finChannelByChannelid(Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_QIYE_SUBJECT_ID")));
//		Map<String,Object> item=new HashMap<String, Object>();
//		item.put("id",rootChannel.getChannelid());//即将作为下一节点的parentid
//		item.put("name",rootChannel.getChnlname());
//		item.put("open", true);
//		item.put("pId", 0L);
//		treeNode.add(item); 
		this.id=rootChannel.getChannelid();
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("status",1);//查询没有进入栏目回收站的栏目
		List<Integer> chnlTypes=new ArrayList<Integer>(); 
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUBJECT); 
		chnlTypes.add((IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT));
		map.put("parentid",this.id);
		treeNode=getTreeNode( chnlTypes, map, treeNode);
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}
	
	public List<Map<String, Object>>  getTreeNode(List<Integer> chnlTypes ,HashMap<String,Object> map,List<Map<String, Object>>  treeNode){
		List<IrpChannel> irpChannels=irpChannelService.siteAllChannel(null,map,chnlTypes);//他里面的所有一级栏目  
		if(irpChannels!=null && irpChannels.size()>0){
			IrpUser irpUser=LoginUtil.getLoginUser();
			for (int j = 0; j < irpChannels.size(); j++) {
				IrpChannel channel=irpChannels.get(j);
				if(!irpUser.isAdministrator()){
					Boolean subjectSelect=irpChannelService.findRightSubToChannel(irpUser, "SUBJECT_SELECT", IrpChannel.CHANNEL_IS_PUBLISH,IrpChannel.TABLE_NAME,irpChannels.get(j).getChannelid().toString());
					if(!subjectSelect){
						//判断是否有子专题
//						int rootChildNum = irpChannelService.findChild_Channl_CountByParentId(channel.getChannelid());
//						if(rootChildNum>0){
//							map=new HashMap<String, Object>();
//							map.put("status",1);//查询没有进入栏目回收站的栏目
//							map.put("parentid",channel.getChannelid());
//							treeNode=getTreeNode( chnlTypes, map, treeNode);
//						}
						continue;
					}
				}
				Map<String,Object> selfitem=new HashMap<String, Object>();
				if(channel.getChnltype()!=null && !"".equals(channel.getChnltype()) && IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT==channel.getChnltype()){
					selfitem.put("icon", "../client/images/star.png");
				}
				selfitem.put("id",channel.getChannelid());//即将作为下一节点的parentid
				selfitem.put("name",channel.getChnlname());
				int rootCount = irpChannelService.findChild_Channl_CountByParentId(channel.getChannelid());
				selfitem.put("pId",channel.getParentid());
				if(rootCount>0){
					selfitem.put("open", false);
					map=new HashMap<String, Object>();
					map.put("status",1);//查询没有进入栏目回收站的栏目
					map.put("parentid",channel.getChannelid());
					treeNode=getTreeNode( chnlTypes, map, treeNode);
				}else{
					selfitem.put("open", true);
				}
				treeNode.add(selfitem); 
			}
		}
		return treeNode;
	}
	//点击知识地图跳转到细览页面
	public String clientDocMapShowDoc(){
		this.loginuserprivacy = irpUserPrivacy();
		//使用id接受mapid;
		if(id==null || id==0L){
			//查询所有
		} 
		return SUCCESS;
	}
	//点击知识专题跳转到细览页面
	public String subdocumentmaplistshow(){
		return SUCCESS;
	}
	//跳转到概览页面
	public String clientShowChannelDoc(){
		this.loginuserprivacy = irpUserPrivacy();
		    if(id==null ||id==0L)return ERROR;
		    irpChannel=irpChannelService.finChannelByChannelid(this.id);
		    //栏目删除状态
		    if(irpChannel==null ||irpChannel.getStatus().intValue()==IrpChannel.NO_DOCSTATUS.intValue()){
		    	friendlyshow=IrpChannel.CHANNEL_IS_DELETE;
		    	return ERROR;
		    }
			HashMap<String,Object> map=new HashMap<String, Object>();
			map.put("docstatus",IrpDocument.PUBLISH_STATUS);   
			map.put("isFengMian",IrpAttached.IS_FENGMIAN);
			//处理排序
			String sOrderByClause = "docid desc";
			map.put("sOrderByClause",sOrderByClause);//按照加精数量倒叙    
	 		/**
	 		 * 查询他所有子类的zhis
	 		 */
	 		List<Long> _arrChannelIds=new ArrayList<Long>();
	 		List<Integer> chnlTypes=new ArrayList<Integer>();
			chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
			_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS, this.id, IrpSite.SITE_TYPE_PUBLISH, LoginUtil.getLoginUser());
		
	 		map.put("channelidsList", _arrChannelIds);
			chnlDocLink=irpChnl_Doc_LinkService.oneJiaJingDocumentByMap(map,IrpDocument.DOCUMENT_NOT_INFORM);
			//查询的专家
			if(chnlDocLink!=null){
				List<Long> userIds=irpInformService.findAllUserIdsByExpert(IrpInform.INFORMJIAJINGDOC, chnlDocLink.getDocid());
				if(userIds!=null && userIds.size()>0){
					IrpUserExample example=new IrpUserExample();
					example.createCriteria().andUseridIn(userIds);
					irpRoles=irpUserService.findUserByExample(example);
					if(irpRoles!=null && irpRoles.size()>0){
						for (int i = 0; i < irpRoles.size(); i++) {
							irpRoles.get(i).setUsername(LoginUtil.getUserNameString(irpRoles.get(i)));
						}
					}
				}
			} 
		return SUCCESS;
	}
	/**
	 * 查询某个栏目中的精华知识，不含有子栏目
	 */
	public String channelAllJingHuaDocument(){
		if(id==null ||id==0L)  return ERROR;
		chnlDocLinks=new ArrayList<IrpChnlDocLink>();
		IrpChannel  channel=irpChannelService.finChannelByChannelid(this.id);
		 if(channel!=null){
			 	HashMap<String,Object> map=new HashMap<String, Object>();
				map.put("docstatus",IrpDocument.PUBLISH_STATUS);   
				map.put("isFengMian",IrpAttached.IS_FENGMIAN);
				//处理排序
				String sOrderByClause = "docid desc";
				map.put("sOrderByClause",sOrderByClause);//按照加精数量倒叙    
				map.put("channelid",channel.getChannelid());
				int aDataCount=irpChnl_Doc_LinkService.typicalDocLinkAmount(map,IrpDocument.DOCUMENT_NOT_INFORM,null);
		 		this.pageSize=10;
				PageUtil pageUtil = new PageUtil(1, this.pageSize, aDataCount);
				this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
				List<IrpChnlDocLink> list=irpChnl_Doc_LinkService.typicalDocLink(map,IrpDocument.DOCUMENT_NOT_INFORM,null,pageUtil);
		 		if(list!=null){
		 			for (IrpChnlDocLink irpChnlDocLink : list) {
		 				List<Long> userIds=irpInformService.findAllUserIdsByExpert(IrpInform.INFORMJIAJINGDOC, irpChnlDocLink.getDocid());
						if(userIds!=null && userIds.size()>0){
							IrpUserExample example=new IrpUserExample();
							example.createCriteria().andUseridIn(userIds);
							List<IrpUser> irpRolesList=irpUserService.findUserByExample(example);
							if(irpRolesList!=null && irpRolesList.size()>0){
								for (int i = 0; i < irpRolesList.size(); i++) {
									irpRolesList.get(i).setUsername(LoginUtil.getUserNameString(irpRolesList.get(i)));
								}
							}
							irpChnlDocLink.setIrpRoles(irpRolesList);
							chnlDocLinks.add(irpChnlDocLink);
						}
					}
				} 
		 }
		return SUCCESS;
	}
	
	/**
	 * 精华知识
	 * mobile
	 * @return
	 */
	public void channelAllJHDocumentOfMobile(){
		if(id==null ||id==0L){
			ActionUtil.writer("");
		}
		chnlDocLinks=new ArrayList<IrpChnlDocLink>();
		IrpChannel  channel=irpChannelService.finChannelByChannelid(this.id);
		 if(channel!=null){
			 	HashMap<String,Object> map=new HashMap<String, Object>();
				map.put("docstatus",IrpDocument.PUBLISH_STATUS);   
				map.put("isFengMian",IrpAttached.IS_FENGMIAN);
				//处理排序
				String sOrderByClause = "docid desc";
				map.put("sOrderByClause",sOrderByClause);//按照加精数量倒叙    
				map.put("channelid",channel.getChannelid());
				int aDataCount=irpChnl_Doc_LinkService.typicalDocLinkAmount(map,IrpDocument.DOCUMENT_NOT_INFORM,null);
		 		this.pageSize=10;
				PageUtil pageUtil = new PageUtil(1, this.pageSize, aDataCount);
				this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
				List<IrpChnlDocLink> list=irpChnl_Doc_LinkService.typicalDocLink(map,IrpDocument.DOCUMENT_NOT_INFORM,null,pageUtil);
		 		if(list!=null){
		 			for (IrpChnlDocLink irpChnlDocLink : list) {
		 				List<Long> userIds=irpInformService.findAllUserIdsByExpert(IrpInform.INFORMJIAJINGDOC, irpChnlDocLink.getDocid());
						if(userIds!=null && userIds.size()>0){
							IrpUserExample example=new IrpUserExample();
							example.createCriteria().andUseridIn(userIds);
							List<IrpUser> irpRolesList=irpUserService.findUserByExample(example);
							if(irpRolesList!=null && irpRolesList.size()>0){
								for (int i = 0; i < irpRolesList.size(); i++) {
									irpRolesList.get(i).setUsername(LoginUtil.getUserNameString(irpRolesList.get(i)));
								}
							}
							irpChnlDocLink.setIrpRoles(irpRolesList);
							chnlDocLinks.add(irpChnlDocLink);
						}
					}
				} 
		 		List mobilelist = new ArrayList();
				for (int i = 0; i < chnlDocLinks.size(); i++) {
					IrpChnlDocLink icd = chnlDocLinks.get(i);
					mobilelist.add("{\"docid\":\""+icd.getDocid()+"\"");
					mobilelist.add("\"doctitle\":\""+icd.getDoctitle()+"\"}");
				}
		 		
		 		ActionUtil.writer(mobilelist.toString());
		 		
		 }
		
		
	}
	
	/**
	 * 查询所有精华知识
	 */
	public String allJingHuaDocument(){ 
			chnlDocLinks=new ArrayList<IrpChnlDocLink>();
		   if(id==null ||id==0L)return ERROR;
		    irpChannel=irpChannelService.finChannelByChannelid(this.id);
			HashMap<String,Object> map=new HashMap<String, Object>();
			map.put("docstatus",IrpDocument.PUBLISH_STATUS);   
			map.put("isFengMian",IrpAttached.IS_FENGMIAN);
			//处理排序
			String sOrderByClause = "docid desc";
			map.put("sOrderByClause",sOrderByClause);//按照加精数量倒叙    
	 		/**
	 		 * 查询他所有子类的zhis
	 		 */
	 		List<Long> _arrChannelIds=new ArrayList<Long>();
	 		List<Integer> chnlTypes=new ArrayList<Integer>();
			chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
			_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS, this.id, IrpSite.SITE_TYPE_PUBLISH, LoginUtil.getLoginUser());
		
	 		map.put("channelidsList", _arrChannelIds);
	 		int aDataCount=irpChnl_Doc_LinkService.typicalDocLinkAmount(map,IrpDocument.DOCUMENT_NOT_INFORM,null);
	 		this.pageSize=5;
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
	 		List<IrpChnlDocLink> list=irpChnl_Doc_LinkService.typicalDocLink(map,IrpDocument.DOCUMENT_NOT_INFORM,null,pageUtil);
	 		if(list!=null){
	 			for (IrpChnlDocLink irpChnlDocLink : list) {
	 				List<Long> userIds=irpInformService.findAllUserIdsByExpert(IrpInform.INFORMJIAJINGDOC,irpChnlDocLink.getDocid());
					if(userIds!=null && userIds.size()>0){
						IrpUserExample example=new IrpUserExample();
						example.createCriteria().andUseridIn(userIds);
						List<IrpUser> irpRolesList=irpUserService.findUserByExample(example);
						if(irpRolesList!=null && irpRolesList.size()>0){
							for (int i = 0; i < irpRolesList.size(); i++) {
								irpRolesList.get(i).setUsername(LoginUtil.getUserNameString(irpRolesList.get(i)));
							}
						}
						irpChnlDocLink.setIrpRoles(irpRolesList);
						chnlDocLinks.add(irpChnlDocLink);
					}
				}
			}
		return SUCCESS;
	}
	public String toCheckDocument(){
		return SUCCESS;
	}
	/**
	 * 概览页面的知识列表显示
	 * @return
	 */
	public String probableDocumentShow(){   
		if(this.id!=null ){ 
		    irpChannel=irpChannelService.finChannelByChannelid(this.id);
		    if(irpChannel==null) return ERROR;
			//处理排序
			String sOrderByClause = "";
			if(this.orderField==null || this.orderField.equals("")){
				sOrderByClause = "docpubtime desc";
			}else{
				sOrderByClause = this.orderField+" "+this.orderBy;
			}  
	 		List<Long> _arrChannelIds=new ArrayList<Long>();
	 		List<Integer> chnlTypes=new ArrayList<Integer>();
			chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
			_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS, this.id, IrpSite.SITE_TYPE_PUBLISH, LoginUtil.getLoginUser());
		
	 		IrpSite site=irpSiteService.siteInfo(irpChannel.getSiteid());
	 		if(site==null)return ERROR;
	 		int aDataCount=irpChnl_Doc_LinkService.clientSelectChnlDocCount(this.searchWord, this.searchType, IrpDocument.DOCUMENT_NOT_INFORM, 
	 												IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds, sOrderByClause);
	 		
	 		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
	 		
	 		chnlDocLinks=irpChnl_Doc_LinkService.clientSelectDocChnl(this.searchWord, this.searchType, IrpDocument.DOCUMENT_NOT_INFORM, 
						IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds, sOrderByClause,pageUtil);
 
	 		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
	 		//查询当前这个栏目下面所引用的知识id集合
	 		if(this.channelid!=null){
	 			List docidsList=irpDocumentMapService.docidsByChannelid(this.channelid);
	 			if(docidsList!=null && docidsList.size()>0){
	 				docidlist=docidsList.toString();
	 			}
	 		}
	 		//判断他的子节点数量
			if(_arrChannelIds!=null && _arrChannelIds.size()!=0){
					if(_arrChannelIds.size()==1){
						//代表他是叶子节点
						return INPUT;
					}
			}
		} 
		return SUCCESS;
	}
	//企业门户的文档刷新列表显示(最新)
	public String indexDocumentListNewShow(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause","docpubtime desc ");
 		List<Long> _arrChannelIds=new ArrayList<Long>();
 		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS,0L, IrpSite.SITE_TYPE_PUBLISH, LoginUtil.getLoginUser());
 		map.put("channelidsList", _arrChannelIds);
 		chnlDocLinks=irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM, SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"));
		return SUCCESS;
	}
	/**
	 * 企业知识库首页  mobile 
	 * 时间排序 默认
	 * @param mobile
	 * @return
	 */
	private long mobilechnlid;
	public long getMobilechnlid() {
		return mobilechnlid;
	}
	public void setMobilechnlid(long mobilechnlid) {
		this.mobilechnlid = mobilechnlid;
	}
	private int mobilepages;
	public int getMobilepages() {
		return mobilepages;
	}
	public void setMobilepages(int mobilepages) {
		this.mobilepages = mobilepages;
	}
	public void findEPListByMoible(){
 		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		List<Long> _arrChannelIds=new ArrayList<Long>();
		_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS, mobilechnlid, IrpSite.SITE_TYPE_PUBLISH, LoginUtil.getLoginUser());
		
 		int aDataCount=irpChnl_Doc_LinkService.clientSelectChnlDocCount(null,null, IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds, "docpubtime desc");
 		
		PageUtil pageUtil = new PageUtil(mobilepages,10, aDataCount);
		List<IrpChnlDocLink> irpChnlDocLink =irpChnl_Doc_LinkService.clientSelectDocChnl(null,null, IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds, "docpubtime desc",pageUtil);
		
		List list = new ArrayList();
		List nlist = new ArrayList();
 		for (int i = 0; i < irpChnlDocLink.size(); i++) {
 			IrpChnlDocLink icdl = irpChnlDocLink.get(i);
 			list.add("{\"doctitle\":\""+icdl.getDoctitle()+"\"");
 			list.add("\"doclassify\":\""+icdl.getChnldesc()+"\"");
 			list.add("\"docid\":\""+icdl.getDocid()+"\"");
 			list.add("\"channelid\":\""+icdl.getChannelid()+"\"}");
		}
 		nlist.add("{\"pnum\":\""+aDataCount+"\"}");
 		
 		String pagelist = "{\"pagecontent\":"+list+",\"pages\":"+nlist+"}";
 		
 		ActionUtil.writer(pagelist.toString());
	}
	
	
	
	
	
	//企业门户的文档刷新列表显示（最热）
		public String indexDocumentListHotShow(){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("sOrderByClause"," hitscount desc ");
			List<Long> _arrChannelIds=new ArrayList<Long>();
			List<Integer> chnlTypes=new ArrayList<Integer>();
			chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
			_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS, 0L, IrpSite.SITE_TYPE_PUBLISH, LoginUtil.getLoginUser());
			map.put("channelidsList", _arrChannelIds);
			chnlDocLinks=irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM, SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"));
			return SUCCESS;
		}
		/**
		 */
	//前台的企业门户显示已发表的文档列表显示 经典案例

	public String clientShowAllDoc(){ 
		//经典案例
		/**
		 * 已发布，并且他是有封面的
		 */ 
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("docstatus",IrpDocument.PUBLISH_STATUS);  
		map.put("sOrderByClause", " docchannel desc ");//按照加精数量倒叙 
		chnlDocLinks=irpChnl_Doc_LinkService.selectDocumentByMap(map,IrpDocument.DOCUMENT_NOT_INFORM);
		
		List<IrpChannel> monChannels=irpChannelService.findChannelByChnlType(IrpChannel.CHANNEL_TYPE_GONGGAO);
		if(monChannels!=null && monChannels.size()>0){
			IrpChannel montChannel=monChannels.get(0);
			chnlDocLinksGongGao=irpChnl_Doc_LinkService.selectMonthDocument(montChannel.getChannelid()); 
		}
		int amount=SysConfigUtil.getSysConfigNumValue("HOT_KEY");
		irpTags= irpTagService.findAllTag(amount);
		this.loginuserprivacy = irpUserPrivacy();
		return SUCCESS;
	}
	private Integer irpUserPrivacy(){
		IrpUserPrivacy irpUserPrivacy = null;
		irpUserPrivacy = this.irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.USERLOGINLOCATION);
		return irpUserPrivacy.getPrivacyvalue();
	}
	
	
	
	//概览页面的返回
	public String returnParentChannel(){
		if(this.id==null ||this.id==-1L)this.id=0L;
		irpChannel=irpChannelService.finChannelByChannelid(this.id); 
		return null;
	}
	//企业门户的左边页面查询
	public String allRightChanneltoShow(){
		//经理说前台，分类只显示一级栏目，右边也只显示一级栏目下面的文档
		if(this.id==null || this.id==-1L)this.id=0L;  
			irpChannel=irpChannelService.finChannelByChannelid(this.id); 
			String orderBy=" chnlorder asc";
			channels=irpChannelService.findChannelsByParentId(this.id,IrpChannel.IS_DOCSTATIUS, orderBy);  
			 //如果该栏目没有子栏目，显示他的兄弟栏目
			if(channels==null  || channels.size()==0){
				if(irpChannel==null){
					irpChannel=irpChannelService.finChannelByChannelid(0L); 
				}else{
					channels=irpChannelService.findChannelsByParentId(irpChannel.getParentid(),IrpChannel.IS_DOCSTATIUS,orderBy);  
					irpChannel=irpChannelService.finChannelByChannelid(irpChannel.getParentid()); 
				}
			}
			
		/**
		 * 每月之星
		 * 企业站点下的栏目状态为每月之星状态下的
		 * 最新的一篇文章
		 */ 
			//获取每月之星栏目
			List<IrpChannel> monChannels=irpChannelService.findChannelByChnlType(IrpChannel.CHANNEL_TYPE_MONTH_TOP);
			if(monChannels!=null && monChannels.size()>0){
				IrpChannel montChannel=monChannels.get(0);
				chnlDocLinkMONTH=irpChnl_Doc_LinkService.selectMonthDocument(montChannel.getChannelid()); 
			}
			
		return SUCCESS;
	}
	/**
	 * 获得所有只是分类
	 * @param mobile
	 * @reutrn
	 */
	private long mobilebrachnlid;
	private String chnldocid;
	private String chnldocids;
	public long getMobilebrachnlid() {
		return mobilebrachnlid;
	}
	public void setMobilebrachnlid(long mobilebrachnlid) {
		this.mobilebrachnlid = mobilebrachnlid;
	}
	public void showAllClassifyOfMobile(){
		
		List<IrpChannel> list = irpChannelService.findChannelsByParentId(mobilebrachnlid,IrpChannel.IS_DOCSTATIUS,"chnlorder asc");
		List mobilelist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			IrpChannel irpChannel = list.get(i);
			mobilelist.add("{\"chnldesc\":\""+irpChannel.getChnldesc()+"\"");
			mobilelist.add("\"parentid\":\""+irpChannel.getParentid()+"\"");
			mobilelist.add("\"chnlid\":\""+irpChannel.getChannelid()+"\"}");
		}
		
		ActionUtil.writer(mobilelist.toString());
		
	}
	//递归得到当前栏目下的所有文档 
	public String showPersonDocLink(){  
		loginuserid=LoginUtil.getLoginUser().getUserid();//当前登录者  
		HashMap<String,Object> map=new HashMap<String, Object>(); 
		//按照创建时间排序
 		String sOrderByClause=null;
 		if(crtimesort!=null ){
 			if(crtimesort.equals("asc")){
 				  sOrderByClause = "crtime asc";  
 			}
 			if(crtimesort.equals("desc")){
 				  sOrderByClause = "crtime desc";  
 			} 
 		}  
 		map.put("sOrderByClause", sOrderByClause); 
 		/**
 		 * 判断查看用户文档，用户是不是当前登录着
 		 */
 		if(loginuserid.toString().equals(personid.toString())){
 			map.put("docstatus",IrpChnlDocLink.DOC_STATUS_CAOGAO); 
 		}else{
 			map.put("docstatus",IrpChnlDocLink.DOC_STATUS_PUBLISH); 
 		}  
 		map.put("personid",this.personid); 
 		map.put("channelid",this.id); 
 		//int aDataCount=irpChnl_Doc_LinkService.clientSelectOneChannelCountByChannelidAndDocstatus(map);//查询文档数量
 		int aDataCount=irpChnl_Doc_LinkService.noDiGuiChannelAllDocCount(map,IrpDocument.DOCUMENT_NOT_INFORM);
		PageUtil pageUtil= new PageUtil(this.pageNum, this.pageSize, aDataCount); 
		chnlDocLinks=irpChnl_Doc_LinkService.getPersonDocLink(pageUtil,map,IrpDocument.DOCUMENT_NOT_INFORM);  
		 
		//初始化用户图片地址
		IrpUser user = irpUserService.findUserByUserId(this.personid);
		this.userPicUrl = user.getDefaultUserPic();
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");   //chnlDocLinks 
		return SUCCESS;
	}
	//前台 查询自己的所有文档列表显示
	public String clientAllDocLink(){ 
		loginuserid=LoginUtil.getLoginUser().getUserid();//设置userid权限页面使用  
		//先查询数量
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("docstatus",IrpChnlDocLink.DOC_STATUS_CAOGAO);   
 		map.put("channelid",this.id); 
 		//按照创建时间排序
 		String sOrderByClause=null;
 		if(crtimesort!=null ){
 			if(crtimesort.equals("asc")){
 				  sOrderByClause = "crtime asc";  
 			}
 			if(crtimesort.equals("desc")){
 				  sOrderByClause = "crtime desc";  
 			} 
 		}   
 	 //    aDataCount=irpChnl_Doc_LinkService.clientSelectOneChannelCountByChannelidAndDocstatus(map);  
 	    int aDataCount=irpChnl_Doc_LinkService.noDiGuiChannelAllDocCount(map,IrpDocument.DOCUMENT_NOT_INFORM); 
		PageUtil pageUtil= new PageUtil(this.pageNum, this.pageSize, aDataCount); 
 		chnlDocLinks=irpChnl_Doc_LinkService.clientChanAllDocLink(pageUtil, map, sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM);  
 		//初始化用户图片地址
		IrpUser user = irpUserService.findUserByUserId(LoginUtil.getLoginUser().getUserid());
		this.userPicUrl = user.getDefaultUserPic();
 		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");   //chnlDocLinks  
	 	return SUCCESS;
	}  
	//将文档(其实是讲简约表信息)删除到文档回收站
	public void deleteDocumentLinkToGC(){
		int nCount=0;
		List<Long> _arrChandocids=new ArrayList<Long>();;//拿到字符串后面的，因为，删除chnldocid来的
		List<Long> docidsList=new ArrayList<Long>();;//拿到字符串前面的docid
		if(chandocids!=null &&chandocids.length>0){
			for (String key : chandocids) {
				String[] _value=key.split("#"); 
				docidsList.add(Long.valueOf(_value[0])); 
				_arrChandocids.add(Long.valueOf(_value[1])); 
			} 
			//删除document 
			nCount=irpChnl_Doc_LinkService.deleteDocumentLinkToGC(_arrChandocids,docidsList);
		}
		ActionUtil.writer(String.valueOf(nCount));
	} 
	//（后台）彻底从回收站中删除文档
	public void deleteDocumentFromGC(){
		int nCount=0;
		List<Long> _arrChandocids=new ArrayList<Long>();;//拿到字符串后面的，因为，删除chnldocid来的
		if(chandocids!=null &&chandocids.length>0){
			for (String key : chandocids) {
				String[] _value=key.split("#");
				_arrChandocids.add(new Long(_value[1])); 
			}
	/////////付燕妮
			if(formtablename!=null&&!formtablename.equals("")){	
				for(int i=0;i<_arrChandocids.size();i++){				
					IrpChnlDocLink chnldoc=irpChnl_Doc_LinkService.getChnlDocLinkById(_arrChandocids.get(i));
					int forminfoid=irpFormService.findFormInfoById(chnldoc.getDocid(), formtablename);
					irpFormService.deleteFormInfoById(Long.parseLong(forminfoid+""), formtablename);
				}
			}
			nCount=irpChnl_Doc_LinkService.deleteDocumentLinkFromGC(_arrChandocids);
		}
		ActionUtil.writer(String.valueOf(nCount));
	}
	//从回收站中恢复文档
	public void huifuDocumentFromGC(){
		int nCount=0;
		List<Long> _arrChandocids=new ArrayList<Long>(); //拿到字符串后面的，因为，删除chnldocid来的
		List<Long> docIds=new ArrayList<Long>();
		if(chandocids!=null &&chandocids.length>0){
			for (String key : chandocids) {
				String[] _value=key.split("#");
				docIds.add(new Long(_value[0]));
				_arrChandocids.add(new Long(_value[1])); 
			} 
			nCount=irpChnl_Doc_LinkService.huifuDocumentLinkfromGC(_arrChandocids,docIds);
		}
		ActionUtil.writer(String.valueOf(nCount));
	}
	//显示站点或栏目下的文档回收站
	public String siteOrChannelAllDocumentGC(){ 
		isGC=1;
		IrpForm irpForm=new IrpForm();
		HashMap<String,Object> map=new HashMap<String, Object>();
	 	if(id!=null && id!=-1){//根据栏目查询
	 		map.put("channelid", this.id);
	 	    map.put("docstatus",IrpDocument.DRAFT_STATUS);
	 	   IrpChannel channel=irpChannelService.finChannelByChannelid(this.id);
	 	   if(channel.getFormid()==null||channel.getFormid().equals("")||channel.getFormid()==0){
	 			formtablename=null;
	 		}else{
	 			
	 			irpForm=irpFormService.getFormById(channel.getFormid());
	 			formtablename=irpForm.getFormtablename();
	 		}
	 	   //检索
	 	    if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题
	 	    	searchDocTitle= ActionUtil.decode(searchDocTitle);
	 	    	map.put("searchDocTitle", searchDocTitle);
	 	    }  
	 	    if(searchModal!=null &&!searchModal.equals("-1")){//文档状态
	 	    	map.put("searchModal", searchModal);
	 	    }

	 		//处理排序
			String sOrderByClause = "";
			if(this.orderField==null || this.orderField.equals("")){
				sOrderByClause = "docid desc";
			}else{
				sOrderByClause = this.orderField+" "+this.orderBy;
			} 
	 	    int aDataCount=irpChnl_Doc_LinkService.selectCountByChannelidAndDocStatusGC(map,IrpDocument.DOCUMENT_NOT_INFORM);
	 		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
	 		
	 		 chnlDocLinks=irpChnl_Doc_LinkService.chanAllDocLinkGC(pageUtil ,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM);   
	 		 this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		}else{//根据站点查询  需要注意他查出来的每一文档的栏目id是不是为负数 
			map.put("siteid",this.siteid);
	 	    map.put("docstatus",IrpDocument.DRAFT_STATUS); 
	 	   //检索
	 	    if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题
	 	   	searchDocTitle= ActionUtil.decode(searchDocTitle);
	 	    	map.put("searchDocTitle", searchDocTitle);
	 	    } 
	 	    if(searchModal!=null &&!searchModal.equals("-1")){//文档状态
	 	    	map.put("searchModal", searchModal);
	 	    } 
	 		//处理排序
			String sOrderByClause = "";
			if(this.orderField==null || this.orderField.equals("")){
				sOrderByClause = "docid desc";
			}else{
				sOrderByClause = this.orderField+" "+this.orderBy;
			}
 	      int aDataCount=irpChnl_Doc_LinkService.selectCountBySiteIdAndDocStatusGC(map,IrpDocument.DOCUMENT_NOT_INFORM);  
 	      PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount); 
          chnlDocLinks=irpChnl_Doc_LinkService.siteAllDocLinkGC(  pageUtil ,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM);   
	      this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
	      id=new Long(-1);//用来在站点下面添加文档用到  
		}  
	 	return SUCCESS;
	}
	private IrpFormService irpFormService;
	
	public IrpFormService getIrpFormService() {
		return irpFormService;
	}
	public void setIrpFormService(IrpFormService irpFormService) {
		this.irpFormService = irpFormService;
	}
	private String formtablename;
	
	public String getFormtablename() {
		return formtablename;
	}
	public void setFormtablename(String formtablename) {
		this.formtablename = formtablename;
	}
	private String newpage;
	
	public String getNewpage() {
		return newpage;
	}
	public void setNewpage(String newpage) {
		this.newpage = newpage;
	}
	
	private int columninliststatus;
	
	public int getColumninliststatus() {
		return columninliststatus;
	}
	public void setColumninliststatus(int columninliststatus) {
		this.columninliststatus = columninliststatus;
	}
	private IrpFormColumnService irpFormColumnService;
	
	
	public IrpFormColumnService getIrpFormColumnService() {
		return irpFormColumnService;
	}
	public void setIrpFormColumnService(IrpFormColumnService irpFormColumnService) {
		this.irpFormColumnService = irpFormColumnService;
	}

	private List<IrpFormColumn> irpFormColumnList;
	
	public List<IrpFormColumn> getIrpFormColumnList() {
		return irpFormColumnList;
	}

	public void setIrpFormColumnList(List<IrpFormColumn> irpFormColumnList) {
		this.irpFormColumnList = irpFormColumnList;
	}
	private List<Object> formInfoList;
	
	public List<Object> getFormInfoList() {
		return formInfoList;
	}

	public void setFormInfoList(List<Object> formInfoList) {
		this.formInfoList = formInfoList;
	}
	private List<IrpAttached> attacheds;
	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}
	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	}
	//虚拟列表所有文档(后台有个人站点和企业站点啊 
	/*public String siteOrChanAllDocLink(){
		
		
		 isGC=0;  
		 //查询某个栏目下的所有栏目文档中间表（知识） 
		  HashMap<String,Object> map=new HashMap<String, Object>(); 
		 	if(id!=null && id!=-1){//根据栏目查询
		 		IrpChannel channel=irpChannelService.finChannelByChannelid(this.id);
		 		if(channel==null)return ERROR;
		 		map.put("docstatus",IrpDocument.DRAFT_STATUS); 
		 		//先查询数量 
		 	    //检索
		 	    if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题
		 	     	searchDocTitle= ActionUtil.decode(searchDocTitle);
		 	    	map.put("searchDocTitle", searchDocTitle);
		 	    }  
		 	    if(searchModal!=null &&!searchModal.equals("-1")){//文档状态
		 	    	map.put("searchModal", searchModal);
		 	    }
		 	   *//** * 查询他所有子类的zhis *//*
		 		List<Long> _arrChannelIds=new ArrayList<Long>();
		 		IrpSite site=irpSiteService.siteInfo(channel.getSiteid());
		 		if(site==null)return ERROR;
		 		_arrChannelIds=irpChannelService.findChannelIdsByParentId(this.id, _arrChannelIds,site.getSitetype(),IrpChannel.IS_DOCSTATIUS,LoginUtil.getLoginUser());
		 		map.put("channelidsList", _arrChannelIds);
		 		//处理排序
				String sOrderByClause = "";
				if(this.orderField==null || this.orderField.equals("")){
					sOrderByClause = "crtime desc";
				}else{
					sOrderByClause = this.orderField+" "+this.orderBy;
				} 
				int aDataCount=irpChnl_Doc_LinkService.selectCountByChannelidAndDocStatus(map,IrpDocument.DOCUMENT_NOT_INFORM,site.getSitetype());   
				PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
		 		chnlDocLinks=irpChnl_Doc_LinkService.chanAllDocLink(pageUtil,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM,site.getSitetype()); 
		 		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
			}else{//根据站点查询  需要注意他查出来的每一文档的栏目id是不是为负数 
					map.put("siteid",this.siteid);
			 	    map.put("docstatus",IrpDocument.DRAFT_STATUS); 
			 	    //检索
			 	    if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题 
			 	     	searchDocTitle= ActionUtil.decode(searchDocTitle);
			 	    	map.put("searchDocTitle",searchDocTitle );//转成中文 
			 	    } 
			 	    if(searchModal!=null &&!searchModal.equals("0")){//文档状态   全部
			 	    	map.put("searchModal", searchModal); 
			 	    }
			 		//处理排序
					String sOrderByClause = "";
					if(this.orderField==null || this.orderField.equals("")){
						sOrderByClause = "crtime desc";
					}else{
						sOrderByClause = this.orderField+" "+this.orderBy;
					}
					int aDataCount=irpChnl_Doc_LinkService.selectCountBySiteIdAndDocStatus(map,IrpDocument.DOCUMENT_NOT_INFORM);  
			 	    PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount); 
					chnlDocLinks=irpChnl_Doc_LinkService.siteAllDocLink(pageUtil,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM);
					this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");  
					// id=new Long(-1);//用来在站点下面添加文档用到 
			}  
		 	 ///查询所有的文章状态下拉显示
			 irpDocstatus=irpDocStatusService.findAllStatus(); 
		return SUCCESS;
	}*/
	
	public String siteOrChanAllDocLink(){  
		 isGC=0; 
		 IrpForm irpForm=new IrpForm();
		 //查询某个栏目下的所有栏目文档中间表（知识） 
		  HashMap<String,Object> map=new HashMap<String, Object>(); 
		 	if(id!=null && id!=-1){//根据栏目查询
		 		IrpChannel channel=irpChannelService.finChannelByChannelid(this.id);
		 		if(channel==null){return ERROR;}
		 		else{
		 			if(channel.getFormid()==null||channel.getFormid().equals("")||channel.getFormid()==0){
		 				formtablename=null;
		 				map.put("docstatus",IrpDocument.DRAFT_STATUS); 
		 				//先查询数量 
		 				//检索
		 				if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题
		 					searchDocTitle= ActionUtil.decode(searchDocTitle);
		 					map.put("searchDocTitle", searchDocTitle);
		 				}  
		 				if(searchModal!=null &&!searchModal.equals("-1")){//文档状态
		 					map.put("searchModal", searchModal);
		 				}
		 				/** * 查询他所有子类的zhis */
		 				List<Long> _arrChannelIds=new ArrayList<Long>();
		 				IrpSite site=irpSiteService.siteInfo(channel.getSiteid());
		 				if(site==null)return ERROR;
		 				_arrChannelIds=irpChannelService.findChannelIdsByParentId(this.id, _arrChannelIds,site.getSitetype(),IrpChannel.IS_DOCSTATIUS,LoginUtil.getLoginUser());
		 				map.put("channelidsList", _arrChannelIds);
		 				//处理排序
		 				String sOrderByClause = "";
		 				if(this.orderField==null || this.orderField.equals("")){
		 					sOrderByClause = "crtime desc";
		 				}else{
		 					sOrderByClause = this.orderField+" "+this.orderBy;
		 				} 
		 				int aDataCount=irpChnl_Doc_LinkService.selectCountByChannelidAndDocStatus(map,IrpDocument.DOCUMENT_NOT_INFORM,site.getSitetype());   
		 				PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
		 				chnlDocLinks=irpChnl_Doc_LinkService.chanAllDocLink(pageUtil,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM,site.getSitetype()); 
		 				this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		 				///查询所有的文章状态下拉显示
		 				irpDocstatus=irpDocStatusService.findAllStatus(); 
		 				return SUCCESS;
		 			}else{
		 				irpForm=irpFormService.getFormById(channel.getFormid());
			 			formtablename=irpForm.getFormtablename();
			 			int count = irpFormService.countFormInfo(formtablename, searchType, searchWord,id);
		 		    	PageUtil pageUtil1 = new PageUtil(pageNum, pageSize, count);
		 		    	HttpServletRequest _request =ServletActionContext.getRequest();
		 	 			String realpath=_request.getRealPath("/");
		 	    		String newpath=channel.getLinkurl();
		 		    	if(newpath!=null&&!newpath.equals("")){
		 	 				newpath=newpath.replace("/", "\\");
		 	 				String path=realpath+newpath;
		 		 			File file = new File(path);
		 		 			if(file.exists()){
		 		 				newpage=channel.getLinkurl();
		 		 			}
		 		 			else{
		 		 				//newpage="site/to_adddocument.action?formtablename='+tablename+'";
		 		 				newpage=null;
		 		 			}
		 	 			}
		 	 			else{
		 	 				//newpage="form/toaddforminfopage.action?formtablename='+tablename+'";
		 	 				newpage=null;
		 	 			}
		 		    	columninliststatus=10;
		 		    	irpFormColumnList = irpFormColumnService.getListBytablename(columninliststatus,formtablename);
		 		    	formInfoList = irpFormService.selectFormInfo(pageUtil1,searchType,searchWord,formtablename,orderField,orderBy,id);
		 		    	this.pageHTML = pageUtil1.getPageHtml("page(#PageNum#)");
		 		    	attacheds = this.irpAttachedService.getAttachedByAttDocId(irpForm.getFormid(),
		 						IrpAttached.FORMEXCEL);
		 		    	return INPUT;
		 			}
		 		}
			}else{//根据站点查询  需要注意他查出来的每一文档的栏目id是不是为负数 
					map.put("siteid",this.siteid);
			 	    map.put("docstatus",IrpDocument.DRAFT_STATUS); 
			 	    //检索
			 	    if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题 
			 	     	searchDocTitle= ActionUtil.decode(searchDocTitle);
			 	    	map.put("searchDocTitle",searchDocTitle );//转成中文 
			 	    } 
			 	    if(searchModal!=null &&!searchModal.equals("0")){//文档状态   全部
			 	    	map.put("searchModal", searchModal); 
			 	    }
			 		//处理排序
					String sOrderByClause = "";
					if(this.orderField==null || this.orderField.equals("")){
						sOrderByClause = "crtime desc";
					}else{
						sOrderByClause = this.orderField+" "+this.orderBy;
					}
					int aDataCount=irpChnl_Doc_LinkService.selectCountBySiteIdAndDocStatus(map,IrpDocument.DOCUMENT_NOT_INFORM);  
			 	    PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount); 
					chnlDocLinks=irpChnl_Doc_LinkService.siteAllDocLink(pageUtil,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM);
					this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");  
					// id=new Long(-1);//用来在站点下面添加文档用到 
					 ///查询所有的文章状态下拉显示
					 irpDocstatus=irpDocStatusService.findAllStatus(); 
					 return SUCCESS;
			}  
	}
	
	//虚拟列表所有文档(后台有个人站点和企业站点啊 excel
	public String siteOrChanAllDocLinkExcel(){
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		Criteria criteria = example.createCriteria();
		if(username!=null&&!username.equals("")){
			criteria.andCruserEqualTo(username);
		}
		LogTimeUtil logTimeUtil=new LogTimeUtil();
		if(timeLimit!=null){
			if(this.timeLimit.trim().equals("thismonth")){//本月
				startTime=logTimeUtil.getMonth();
				endTime=logTimeUtil.getLastMonth();
				criteria.andCrtimeBetween(startTime, endTime);
			}else if (this.timeLimit.trim().equals("thisweek")) {//本周
				startTime=logTimeUtil.getWeek();
				endTime=logTimeUtil.getLastWeek();
				criteria.andCrtimeBetween(startTime, endTime);
			}else if(this.timeLimit.trim().equals("thisquarter")){//本季度
				startTime=logTimeUtil.getQuarter();
				endTime=logTimeUtil.getLastQuarter();
				criteria.andCrtimeBetween(startTime, endTime);
			}else if(this.timeLimit.trim().equals("shijian")){//本季度
			}else{
				if(start!=null){
					startTime=DateUtils.getDateByYMD(start);
					endTime=DateUtils.getDateByYMD(end);
					criteria.andCrtimeBetween(startTime, endTime);
				}
			}  //否则开始结束时间都是有值的，即任意时间
		}
		
		
		 isGC=0;  
		 //查询某个栏目下的所有栏目文档中间表（知识） 
		  HashMap<String,Object> map=new HashMap<String, Object>(); 
		 	if(id!=null && id!=-1){//根据栏目查询
		 		IrpChannel channel=irpChannelService.finChannelByChannelid(this.id);
		 		if(channel==null)return ERROR;
		 		//map.put("docstatus",IrpDocument.DRAFT_STATUS); 
		 		criteria.andDocstatusGreaterThanOrEqualTo(IrpDocument.DRAFT_STATUS);
		 		//先查询数量 
		 	    //检索
		 	    if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题
		 	     	searchDocTitle= ActionUtil.decode(searchDocTitle);
		 	    	//map.put("searchDocTitle", searchDocTitle);
		 	     	criteria.andDoctitleLike(searchDocTitle);
		 	    }  
		 	    if(searchModal!=null &&!searchModal.equals("-1")){//文档状态
		 	    	//map.put("searchModal", searchModal);
		 	    	if (searchModal.toString().length() > 0) {
						criteria.andDocstatusEqualTo(Long.parseLong(searchModal
								.toString()));
					}
		 	    }
		 	   /** * 查询他所有子类的zhis */
		 		List<Long> _arrChannelIds=new ArrayList<Long>();
		 		IrpSite site=irpSiteService.siteInfo(channel.getSiteid());
		 		if(site==null)return ERROR;
		 		_arrChannelIds=irpChannelService.findChannelIdsByParentId(this.id, _arrChannelIds,site.getSitetype(),IrpChannel.IS_DOCSTATIUS,LoginUtil.getLoginUser());
		 		//map.put("channelidsList", _arrChannelIds);
		 		if (_arrChannelIds.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < _arrChannelIds.size(); i++) {
						tempChannelids.add(_arrChannelIds.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == _arrChannelIds.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(_arrChannelIds);
				}
		 		
		 		
		 		List<Long> sites = irpSiteService.findSiteIds(site.getSitetype());
				if (sites != null && sites.size() > 0) {
					criteria.andSiteidIn(sites);// 企业站点(不等于私人站点的就是企业站点)
				}
				
				criteria.andInformtypeEqualTo(IrpDocument.DOCUMENT_NOT_INFORM);
		 		//处理排序
				String sOrderByClause = "";
				if(this.orderField==null || this.orderField.equals("")){
					sOrderByClause = "crtime desc";
				}else{
					sOrderByClause = this.orderField+" "+this.orderBy;
				} 
				example.setOrderByClause(sOrderByClause);
				//int aDataCount=irpChnl_Doc_LinkService.selectCountByChannelidAndDocStatus(map,IrpDocument.DOCUMENT_NOT_INFORM,site.getSitetype());   
				int aDataCount=irpChnl_Doc_LinkService.selectCountExample(example);
				PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
		 		chnlDocLinks=irpChnl_Doc_LinkService.selectByExample(pageUtil,example);
		 		//chnlDocLinks=irpChnl_Doc_LinkService.chanAllDocLink(pageUtil,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM,site.getSitetype()); 
		 		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
			}else{//根据站点查询  需要注意他查出来的每一文档的栏目id是不是为负数 
					// example = new IrpChnlDocLinkExample();
					// criteria = example.createCriteria();
					 criteria.andSiteidEqualTo(siteid);
					//map.put("siteid",this.siteid);
			 	  //  map.put("docstatus",IrpDocument.DRAFT_STATUS); 
					criteria.andDocstatusGreaterThanOrEqualTo(IrpDocument.DRAFT_STATUS);
			 	    //检索
			 	    if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题 
			 	     	searchDocTitle= ActionUtil.decode(searchDocTitle);
			 	    	//map.put("searchDocTitle",searchDocTitle );//转成中文 
			 	     	criteria.andDoctitleLike(searchDocTitle);
			 	    } 
			 	    if(searchModal!=null &&!searchModal.equals("0")){//文档状态   全部
			 	    	//map.put("searchModal", searchModal); 
			 	    	if (searchModal.toString().length() > 0) {
							criteria.andDocstatusEqualTo(Long.parseLong(searchModal
									.toString()));
						}
			 	    }
			 		//处理排序
					String sOrderByClause = "";
					if(this.orderField==null || this.orderField.equals("")){
						sOrderByClause = "crtime desc";
					}else{
						sOrderByClause = this.orderField+" "+this.orderBy;
					}
					example.setOrderByClause(sOrderByClause);
				//	int aDataCount=irpChnl_Doc_LinkService.selectCountBySiteIdAndDocStatus(map,IrpDocument.DOCUMENT_NOT_INFORM);  
			 	   // PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount); 
					//chnlDocLinks=irpChnl_Doc_LinkService.siteAllDocLink(pageUtil,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM);
					int aDataCount=irpChnl_Doc_LinkService.selectCountExample(example);
					PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
			 		chnlDocLinks=irpChnl_Doc_LinkService.selectByExample(pageUtil,example);
					this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");  
					// id=new Long(-1);//用来在站点下面添加文档用到 
			}  
		 	 ///查询所有的文章状态下拉显示
			 irpDocstatus=irpDocStatusService.findAllStatus(); 
		return SUCCESS;
	}
	
	/**
	 * 按条件查询导出或按选择的chnldocids导出
	 * 文档导出excel
	 */
	public void exportToZip(){
		
		if(chnldocids.equals("0")){
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			if(username!=null&&!username.equals("")){
				criteria.andCruserEqualTo(username);
			}
			LogTimeUtil logTimeUtil=new LogTimeUtil();
			if(timeLimit!=null){
				if(this.timeLimit.trim().equals("thismonth")){//本月
					startTime=logTimeUtil.getMonth();
					endTime=logTimeUtil.getLastMonth();
					criteria.andCrtimeBetween(startTime, endTime);
				}else if (this.timeLimit.trim().equals("thisweek")) {//本周
					startTime=logTimeUtil.getWeek();
					endTime=logTimeUtil.getLastWeek();
					criteria.andCrtimeBetween(startTime, endTime);
				}else if(this.timeLimit.trim().equals("thisquarter")){//本季度
					startTime=logTimeUtil.getQuarter();
					endTime=logTimeUtil.getLastQuarter();
					criteria.andCrtimeBetween(startTime, endTime);
				}else if(this.timeLimit.trim().equals("shijian")){//本季度
				}else{
					if(start!=null){
						
						startTime=DateUtils.getDateByYMD(start);
						endTime=DateUtils.getDateByYMD(end);
						criteria.andCrtimeBetween(startTime, endTime);
					}
				}  //否则开始结束时间都是有值的，即任意时间
			
			}
			 isGC=0;  
			 //查询某个栏目下的所有栏目文档中间表（知识） 
			  HashMap<String,Object> map=new HashMap<String, Object>(); 
			 	if(id!=null && id!=-1){//根据栏目查询
			 		IrpChannel channel=irpChannelService.finChannelByChannelid(this.id);
			 		if(channel==null)return;
			 		//map.put("docstatus",IrpDocument.DRAFT_STATUS); 
			 		criteria.andDocstatusGreaterThanOrEqualTo(IrpDocument.DRAFT_STATUS);
			 		//先查询数量 
			 	    //检索
			 	    if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题
			 	     	searchDocTitle= ActionUtil.decode(searchDocTitle);
			 	    	//map.put("searchDocTitle", searchDocTitle);
			 	     	criteria.andDoctitleLike(searchDocTitle);
			 	    }  
			 	    if(searchModal!=null &&!searchModal.equals("-1")){//文档状态
			 	    	//map.put("searchModal", searchModal);
			 	    	if (searchModal.toString().length() > 0) {
							criteria.andDocstatusEqualTo(Long.parseLong(searchModal
									.toString()));
						}
			 	    }
			 	   /** * 查询他所有子类的zhis */
			 		List<Long> _arrChannelIds=new ArrayList<Long>();
			 		IrpSite site=irpSiteService.siteInfo(channel.getSiteid());
			 		if(site==null)return ;
			 		_arrChannelIds=irpChannelService.findChannelIdsByParentId(this.id, _arrChannelIds,site.getSitetype(),IrpChannel.IS_DOCSTATIUS,LoginUtil.getLoginUser());
			 		//map.put("channelidsList", _arrChannelIds);
			 		if (_arrChannelIds.size() > 1000) {
						List<Long> tempChannelids = new ArrayList<Long>();
						for (int i = 0; i < _arrChannelIds.size(); i++) {
							tempChannelids.add(_arrChannelIds.get(i));
							if (tempChannelids.size() == 1000
									|| (i + 1) == _arrChannelIds.size()) {
								example.or(example.createCriteria().andChannelidIn(
										tempChannelids));
								tempChannelids = new ArrayList<Long>();
							}
						}
					} else {
						criteria.andChannelidIn(_arrChannelIds);
					}
			 		
			 		
			 		List<Long> sites = irpSiteService.findSiteIds(site.getSitetype());
					if (sites != null && sites.size() > 0) {
						criteria.andSiteidIn(sites);// 企业站点(不等于私人站点的就是企业站点)
					}
					
					criteria.andInformtypeEqualTo(IrpDocument.DOCUMENT_NOT_INFORM);
			 		//处理排序
					String sOrderByClause = "";
					if(this.orderField==null || this.orderField.equals("")){
						sOrderByClause = "crtime desc";
					}else{
						sOrderByClause = this.orderField+" "+this.orderBy;
					} 
					example.setOrderByClause(sOrderByClause);
			 		chnlDocLinks=irpChnl_Doc_LinkService.selectByExample(null,example);
				}else{//根据站点查询  需要注意他查出来的每一文档的栏目id是不是为负数 
						// example = new IrpChnlDocLinkExample();
						// criteria = example.createCriteria();
						 criteria.andSiteidEqualTo(siteid);
						//map.put("siteid",this.siteid);
				 	  //  map.put("docstatus",IrpDocument.DRAFT_STATUS); 
						criteria.andDocstatusGreaterThanOrEqualTo(IrpDocument.DRAFT_STATUS);
				 	    //检索
				 	    if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题 
				 	     	searchDocTitle= ActionUtil.decode(searchDocTitle);
				 	    	//map.put("searchDocTitle",searchDocTitle );//转成中文 
				 	     	criteria.andDoctitleLike(searchDocTitle);
				 	    } 
				 	    if(searchModal!=null &&!searchModal.equals("0")){//文档状态   全部
				 	    	//map.put("searchModal", searchModal); 
				 	    	if (searchModal.toString().length() > 0) {
								criteria.andDocstatusEqualTo(Long.parseLong(searchModal
										.toString()));
							}
				 	    }
				 		//处理排序
						String sOrderByClause = "";
						if(this.orderField==null || this.orderField.equals("")){
							sOrderByClause = "crtime desc";
						}else{
							sOrderByClause = this.orderField+" "+this.orderBy;
						}
						example.setOrderByClause(sOrderByClause);
				 		chnlDocLinks=irpChnl_Doc_LinkService.selectByExample(null,example);
				}  
		}else{
			String[] chnldocidArray=chnldocids.split(",");
			List<Long> list=new ArrayList<Long>();
			for (int i = 0; i < chnldocidArray.length; i++) {
				list.add(Long.parseLong(chnldocidArray[i]));
			}
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			criteria.andChnldocidIn(list);
			example.setOrderByClause("crtime desc");
	 		chnlDocLinks=irpChnl_Doc_LinkService.selectByExample(null,example);
		}
		if(chnlDocLinks==null||chnlDocLinks.size()<=0){
			ActionUtil.writer("0");
		}else{
			ActionContext ac = ActionContext.getContext();     
			ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);     
			String WEB_ROOT_PATH = sc.getRealPath("/"); 
			String app_path=WEB_ROOT_PATH.replace("\\", "/");
			HttpServletRequest _request=ServletActionContext.getRequest();
			String path=app_path+"docment";
			irpChnl_Doc_LinkService.exportAllDocInfoToZip(chnlDocLinks,path,fileName);
			ActionUtil.writer(fileName+"");
		}
	}
	public String getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String findStatusNameByStatusId(long _nStatusId) {
		String sStatusName =  "";
		if(_nStatusId>0L){
			IrpDocstatus status = irpDocStatusService.finDocstatusByStatusId(_nStatusId);
			if(status!=null){
				sStatusName = status.getSname();
			}
		}
		return sStatusName;
	}
 
	
	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}

	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}
	

	public Long getSiteid() {
		return siteid;
	}

	public void setSiteid(Long siteid) {
		this.siteid = siteid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	public List<IrpChnlDocLink> getChnlDocLinks() {
		return chnlDocLinks;
	}
 
	public void setChnlDocLinks(List<IrpChnlDocLink> chnlDocLinks) {
		this.chnlDocLinks = chnlDocLinks;
	}
 
	public int getIsGC() {
		return isGC;
	}
	public void setIsGC(int isGC) {
		this.isGC = isGC;
	}
	public String[] getChandocids() {
		return chandocids;
	}
	public void setChandocids(String[] chandocids) {
		this.chandocids = chandocids;
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
	public String getPageHTML() {
		return pageHTML;
	}
	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getSearchDocTitle() {
		return searchDocTitle;
	}
	public void setSearchDocTitle(String searchDocTitle) {
		this.searchDocTitle = searchDocTitle;
	}
	 
	public String getSearchModal() {
		return searchModal;
	}
	public void setSearchModal(String searchModal) {
		this.searchModal = searchModal;
	}
	public String getChannelorDocument() {
		return channelorDocument;
	}
	public void setChannelorDocument(String channelorDocument) {
		this.channelorDocument = channelorDocument;
	}

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}
	public Long getPersonid() {
		return personid;
	}
	public void setPersonid(Long personid) {
		this.personid = personid;
	}
	public List<IrpChannel> getChannels() {
		return channels;
	}
	public void setChannels(List<IrpChannel> channels) {
		this.channels = channels;
	}
	public Long getLoginuserid() {
		return loginuserid;
	}
	public void setLoginuserid(Long loginuserid) {
		this.loginuserid = loginuserid;
	}
	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}
	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}
	public String getCrtimesort() {
		return crtimesort;
	}
	public String getInformKey() {
		return informKey;
	}
	public void setInformKey(String informKey) {
		this.informKey = informKey;
	}
	public void setCrtimesort(String crtimesort) {
		this.crtimesort = crtimesort;
	}
	public List<IrpDocstatus> getIrpDocstatus() {
		return irpDocstatus;
	}
	public void setIrpDocstatus(List<IrpDocstatus> irpDocstatus) {
		this.irpDocstatus = irpDocstatus;
	}
	public IrpChannel getIrpChannel() {
		return irpChannel;
	}
	public void setIrpChannel(IrpChannel irpChannel) {
		this.irpChannel = irpChannel;
	} 
	
	
	public List<IrpChnlDocLink> getIrpChnldoclist() {
		return irpChnldoclist;
	}
	public void setIrpChnldoclist(List<IrpChnlDocLink> irpChnldoclist) {
		this.irpChnldoclist = irpChnldoclist;
	}
	public String getDocidlist() {
		return docidlist;
	}
	public void setDocidlist(String docidlist) {
		this.docidlist = docidlist;
	}
	/**
	 * 获得选中的知识
	 */
	public void findSelectedDocTitle(){
		StringBuffer docsb = new StringBuffer();
		String docidlistarray[] =  docidlist.split(",");
		Set set = new HashSet();
		for (int i = 0; i < docidlistarray.length; i++) {
			if(!docidlistarray[i].equals("")){
				set.add(docidlistarray[i]);
			}
		}
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			IrpDocument irpDocument =  this.irpDocumentService.irpDocument(Long.parseLong(iterator.next().toString()));
			docsb.append("[<a href=\"javascript:void(0)\" id="+irpDocument.getDocid()+" onclick=\"showdocumentinfo("+irpDocument.getDocid()+")\">"+irpDocument.getDoctitle()+"</a>]<br/>");	
		}
		
		
		ActionUtil.writer(docsb.toString());
	}
	/**
	 * 活得他这个栏目tree
	 */
	public void allparentidList(){
		List<Long> channelidList=new ArrayList<Long>();
		irpChannelService.allparentidList(this.id, channelidList,IrpChannel.IS_DOCSTATIUS,LoginUtil.getLoginUser());
		JSONArray array = JSONArray.fromObject(channelidList);
		ActionUtil.writer(array.toString());
	}
	/**
	 * 查询被举报的知识(不分个人和企业)
	 */
	public String findJuBaoDocument(){
		Integer sitetype1=IrpSite.SITE_TYPE_PRIVATE;
		Integer sitetype2=IrpSite.SITE_TYPE_PUBLISH;
		if(this.sitetype!=null && !this.sitetype.trim().equals("0")){//查询全部
			sitetype1=Integer.parseInt(this.sitetype);
			sitetype2=Integer.parseInt(this.sitetype);
		}	
		String expert="";
		if(informKey!=null && informKey.length()>0){
			int isExit=irpInformTypeService.searchInformType(informKey);//1存在2不存在
			if(isExit==1){//代表前台选择的是全部类型
				expert=" and t.informkey='"+informKey+"'";
			} 
		}
		int _nDataCount=irpChnl_Doc_LinkService.findJuBaoDocCount(sitetype1,sitetype2,expert,IrpInform.INFORMKNOW,IrpInform.INFORM_STATUS);
		PageUtil pageUtil=new PageUtil(this.pageNum,this.pageSize, _nDataCount);
		jubaodocuments=irpChnl_Doc_LinkService.findJuBaoDoc(sitetype1,sitetype2,expert,IrpInform.INFORMKNOW,IrpInform.INFORM_STATUS, pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("findDocReportConn(#PageNum#)");   //chnlDocLinks  
		return SUCCESS;
	}
	public String toFlowMenu(){
		//所有类型---
		irpInformTypelist=this.irpInformTypeService.findAllIrpInformType( IrpInformType.REPORT_TYPE_KNOW);
		return SUCCESS;
	}
	
	/**
	 * 查询非法的知识(不分个人和企业)
	 */
	public String findFeiFaDocument(){
		Integer sitetype1=IrpSite.SITE_TYPE_PRIVATE;
		Integer sitetype2=IrpSite.SITE_TYPE_PUBLISH;
		if(this.sitetype!=null && !this.sitetype.trim().equals("0")){
			sitetype1=Integer.parseInt(this.sitetype);
			sitetype2=Integer.parseInt(this.sitetype);
		} 
		String expert="";
		if(informKey!=null && informKey.length()>0){
			int isExit=irpInformTypeService.searchInformType(informKey);//1存在2不存在
			if(isExit==1){//代表前台选择的是全部类型
				expert=" and t.informkey='"+informKey+"'";
			} 
		} 
		int _nDataCount=irpChnl_Doc_LinkService.findJuBaoDocCount(sitetype1,sitetype2,expert,IrpInform.INFORMKNOW,IrpInform.INFORM_STATUSDELETE_ILLEGALITY);
		PageUtil pageUtil=new PageUtil(this.pageNum,this.pageSize, _nDataCount);
		jubaodocuments=irpChnl_Doc_LinkService.findJuBaoDoc(sitetype1,sitetype2,expert,IrpInform.INFORMKNOW,IrpInform.INFORM_STATUSDELETE_ILLEGALITY, pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("findDocReportConn(#PageNum#)");
		return SUCCESS;
	}
	//通过举报知识
	public void  passInformDocument(){
		 int nCount=0;
		 nCount=irpChnl_Doc_LinkService.passOrNotInformDocument(this.docid, IrpDocument.DOCUMENT_IS_INFORM, IrpInform.INFORM_STATUSDELETE_ILLEGALITY);
		 ActionUtil.writer(String.valueOf(nCount));
	}
	//恢复举报知识
	public void huifuInformDocument(){
		 int nCount=0; 
		 nCount=irpChnl_Doc_LinkService.passOrNotInformDocument(this.docid, IrpDocument.DOCUMENT_NOT_INFORM,IrpInform.INFORM_STATUS);
		 ActionUtil.writer(String.valueOf(nCount));
	}
	//未通过举报知识
	public void  NotInformDocument(){
		 int nCount=0;
		 nCount=irpChnl_Doc_LinkService.deleteInformDocument(this.docid);//暂时用docid接收举报记录id
		 ActionUtil.writer(String.valueOf(nCount));
	}
	/**
	 * 后台批量删除知识分类中的知识
	 */
	public void deleteDocumentFromMap(){
		int nCount=0;
		if(this.docids!=null && this.id!=null && this.id!=0L){
			List<Long> docIdList=Arrays.asList(this.docids);
			nCount=irpDocumentMapService.deleteDocumentByMapId(this.id, docIdList);
		} 
		ActionUtil.writer(String.valueOf(nCount));
	}
	/**
	 * 后台批量 给知识分类
	 */
	public void giveDocumentAddMap(){
		 int nCount=0;
		 if(id!=null && id!=0 && docidlist!=null){
			 String docidlistarray[] =  docidlist.split(",");
			 if(docidlistarray!=null && docidlistarray.length>0){
				for (int i = 0; i < docidlistarray.length; i++) {
					if(!docidlistarray[i].equals("")){
						Long docid=Long.parseLong(docidlistarray[i]);
						IrpDocumentMap map=irpDocumentMapService.findDocumentMap(this.id, docid,"sub".equals(flag)?IrpDocumentMap.KNOWLEDGE_SUBJECT:IrpDocumentMap.KONWLEDGE_MAP);
						if(map==null){//如果不存在就加入，存在就不做操作
							irpDocumentMapService.addDocumentMap(docid,id,"sub".equals(flag)?IrpDocumentMap.KNOWLEDGE_SUBJECT:IrpDocumentMap.KONWLEDGE_MAP);
							nCount++;
						}
					}
				}
			 }
		 }
		ActionUtil.writer(String.valueOf(nCount));
	}
	/**
	 *  查看某个分类下的知识(只取得企业知识库中的知识)
	 */
	public String showMapAllDocument(){
		 isGC=0;  
		 //查询某个栏目下的所有栏目文档中间表（知识） 
		  if(id==null || id==0L ||id==-1) return ERROR;
		  IrpChannel channel=irpChannelService.finChannelByChannelid(this.id);
		  if(channel==null) return ERROR;
		  List<Long> docidsList=irpDocumentMapService.docidsByChannelid(id);
		  if(docidsList!=null && docidsList.size()>0){
			  HashMap<String,Object> map=new HashMap<String, Object>(); 
			  map.put("docidsList", docidsList); 
				map.put("docstatus",IrpDocument.DRAFT_STATUS); 
				if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题
					searchDocTitle= ActionUtil.decode(searchDocTitle);
					map.put("searchDocTitle", searchDocTitle);
				}  
				if(searchModal!=null &&!searchModal.equals("0")){//文档状态
					map.put("searchModal", searchModal);
				} 
				//处理排序
				String sOrderByClause = "";
				if(this.orderField==null || this.orderField.equals("")){
					sOrderByClause = "docid desc";
				}else{
					sOrderByClause = this.orderField+" "+this.orderBy;
				} 
				IrpSite site = null;
				if(channel.getSiteid()!=0){
					site=irpSiteService.siteInfo(channel.getSiteid());
					if(site==null)return ERROR;
				}else{
					site = new IrpSite();
					site.setSitetype(1);
				}
				int aDataCount=irpChnl_Doc_LinkService.selectCountByChannelidAndDocStatus(map,IrpDocument.DOCUMENT_NOT_INFORM,site.getSitetype()); 
				PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
				chnlDocLinks=irpChnl_Doc_LinkService.chanAllDocLink(pageUtil,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM,site.getSitetype()); 
				this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)"); 
		 	 ///查询所有的文章状态下拉显示
		  }
			 irpDocstatus=irpDocStatusService.findAllStatus();
			return SUCCESS;
	}
	
	/**
	 *  查看某个分类下的知识(专题)
	 */
	public String suballdocument(){
	 isGC=0;  
	  if(id==null || id==0L ||id==-1) return ERROR;
	  IrpChannel channel=irpChannelService.finChannelByChannelid(this.id);
	  if(channel==null) return ERROR;
	  List<Long> docidsList=irpDocumentMapService.docidsByChannelid(id);
	  if(docidsList!=null && docidsList.size()>0){
		  HashMap<String,Object> map=new HashMap<String, Object>(); 
		  map.put("docidsList", docidsList); 
			map.put("docstatus",IrpDocument.DRAFT_STATUS); 
			if(searchDocTitle!=null && searchDocTitle.length()>0){//文档标题
				searchDocTitle= ActionUtil.decode(searchDocTitle);
				map.put("searchDocTitle", searchDocTitle);
			}  
			if(searchModal!=null &&!searchModal.equals("0")){//文档状态
				map.put("searchModal", searchModal);
			} 
			//处理排序
			String sOrderByClause = "";
			if(this.orderField==null || this.orderField.equals("")){
				sOrderByClause = "docid desc";
			}else{
				sOrderByClause = this.orderField+" "+this.orderBy;
			}
			//通过docids查出所有document
			int aDataCount=irpDocumentService.selectCountByChannelidAndDocStatus(map,IrpDocument.DOCUMENT_NOT_INFORM); 
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
			irpDocumentList=irpDocumentService.checkAllDocSub(pageUtil,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM); 
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)"); 
	  }
		 irpDocstatus=irpDocStatusService.findAllStatus();
		return SUCCESS;
	}
	
	/**
	 * 新建检索专题
	 * @return
	 */
	public void adminAddSuperChannel(){
		Long primaryKeyId = TableIdUtil.getNextId(IrpChannel.TABLE_NAME);
		irpChannel.setChannelid(primaryKeyId);
		irpChannel.setCrtime(new Date());
		irpChannel.setCruserid(LoginUtil.getLoginUserId());
		irpChannel.setCruser(LoginUtil.getLoginUser().getUsername());
		irpChannel.setSiteid(0L);
		irpChannel.setParentid(this.id);
		irpChannel.setAttribute("0");
		irpChannel.setStatus(IrpChannel.IS_DOCSTATIUS);
		irpChannel.setChnltype(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT);
		irpChannelService.insertChannelByExample(irpChannel);
		
		//将符合条件的关系存入关系表
		List exampleList = new ArrayList();
		searchwhere = SysConfigUtil.getSysConfigValue("searchOptions");
		if("t".equals(searchwhere)){
			irpSelectKeyService.save(irpChannel.getChnlquery());
			irpTagService.addTag(irpChannel.getChnlquery());
			PageUtil pageutil = new PageUtil();
			pageutil.setPageNum(1);
			pageutil.setPageSize(999999999);
			exampleList = trsSearchService.selectDocbypage(pageutil, "IRP_DOC", irpChannel.getChnlquery(),
					"DOCCONTENT", null);
			//添加关系
			if(exampleList!=null && exampleList.size()>0){
				IrpDocumentMap map ;
				IrpDocumentWithBLOBs document;
				for(int i = 0;i<exampleList.size();i++){
					map = new IrpDocumentMap();
					document = (IrpDocumentWithBLOBs) exampleList.get(i);
					map.setMid(TableIdUtil.getNextId(IrpDocumentMap.TABLE_NAME));
					map.setChannelid(primaryKeyId);
					map.setDocid(document.getDocid());
					map.setType(IrpDocumentMap.KNOWLEDGE_SUBJECT);
					map.setCrtime(document.getCrtime());
					irpDocumentMapService.addDocSubByExample(map);
				}
			}
		}else{
			try {
				int docnum = this.solrService.searchDocumentNum(irpChannel.getChnlquery(), SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),null);
				if(docnum>0){
					PageUtil pageUtildocumentExample = new PageUtil(1,docnum,docnum);
					exampleList = this.solrService.searchDocument(irpChannel.getChnlquery(),SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),pageUtildocumentExample,0,null);
					//添加关系
					if(exampleList!=null && exampleList.size()>0){
						IrpDocumentMap map ;
						for(int i = 0;i<exampleList.size();i++){
							String docid = exampleList.get(i).toString().substring(exampleList.get(i).toString().indexOf("DOCID=")+"DOCID=".length()).split(",")[0];
							String crtime = exampleList.get(i).toString().substring(exampleList.get(i).toString().indexOf("CRTIME=")+"CRTIME=".length()).split(",")[0];
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date date;
							try {
								date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US).parse(crtime);
							} catch (ParseException e) {
								e.printStackTrace();
								date = new Date();
							}
							map = new IrpDocumentMap();
							map.setMid(TableIdUtil.getNextId(IrpDocumentMap.TABLE_NAME));
							map.setChannelid(primaryKeyId);
							map.setDocid(Long.valueOf(docid));
							map.setType(IrpDocumentMap.KNOWLEDGE_SUBJECT);
							map.setCrtime(date);
							irpDocumentMapService.addDocSubByExample(map);
						}
					}
				}
			} catch (SolrServerException e) {
				e.printStackTrace();
			}
		}
		ActionUtil.writer("1");
	}
	
	public String indexDocumentListValuableShow() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause"," docscore desc ");
		List<Long> _arrChannelIds=new ArrayList<Long>();
		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS, 0L, IrpSite.SITE_TYPE_PUBLISH, LoginUtil.getLoginUser());
		map.put("channelidsList", _arrChannelIds);
		chnlDocLinks=irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM, SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"));
		return SUCCESS;
	}
	public IrpUser getIrpUserById(Long _userid){
		IrpUser irpUser = null;
		if(_userid!=null){
			irpUser =this.irpUserService.findUserByUserId(_userid);	
		}
		return irpUser;
	}
	
	public IrpDocument getIrpDocumentById(Long _docid){
		IrpDocument irpDocument = null;
		if (_docid!=null) {
			irpDocument = this.irpDocumentService.irpDocument(_docid);
		}
		return irpDocument;
	}
	
	public IrpUser findIrpUserByFocusId(long _userid){
		return this.irpUserService.findUserByUserId(_userid);
	}
}
