package com.tfs.irp.document.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.batik.bridge.UserAgent;
import org.apache.http.HttpRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.star.awt.Key;
import com.sun.star.io.IOException;
import com.sun.xml.internal.fastinfoset.util.ValueArray;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.entity.IrpChannelExample;
import com.tfs.irp.channel.entity.IrpChannelExample.Criteria;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.config.entity.IrpConfig;
import com.tfs.irp.docrecommend.entity.IrpDocrecommend;
import com.tfs.irp.docrecommend.service.IrpDocrecommendService;
import com.tfs.irp.docscore.entity.IrpDocumentScore;
import com.tfs.irp.docscore.service.DocScoreService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentExample;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.documentcollection.service.IrpDocumentCollectionService;
import com.tfs.irp.documentlogs.entity.IrpDocumentLogs;
import com.tfs.irp.documentlogs.service.IrpDocumentLogsService;
import com.tfs.irp.documentmap.entity.IrpDocumentMap;
import com.tfs.irp.documentmap.entity.IrpDocumentMapExample;
import com.tfs.irp.documentmap.service.IrpDocumentMapService;
import com.tfs.irp.documentread.entity.IrpDocumentReaded;
import com.tfs.irp.documentread.service.DocumentReadedService;
import com.tfs.irp.docversion.entity.IrpDocversion;
import com.tfs.irp.docversion.entity.IrpDocversionWithBLOBs;
import com.tfs.irp.docversion.service.IrpDocversionService;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.inform.entity.IrpInform;
import com.tfs.irp.inform.service.IrpInformService;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.logs.service.IrpLogsService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.service.IrpQuestionService;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.subscribe.entity.IrpSubscribe;
import com.tfs.irp.subscribe.service.IrpSubscribeService;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userlove.entity.IrpUserLove;
import com.tfs.irp.userlove.service.IrpUserLoveService;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.service.IrpUserPrivacyService;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.RightUtil;
import com.tfs.irp.util.StringUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.ThumbnailPic;
import com.tfs.irp.util.textrank.TextRankKeyword;
import com.tfs.irp.util.textrank.TextRankSummary;
import com.tfs.irp.util.wordfilter.TermFilterDoc;
import com.tfs.irp.value.entity.IrpValueConfig;
import com.tfs.irp.value.service.IrpValueConfigService;
import com.tfs.irp.valuesetting.service.IrpValueSettingService;
import com.tfs.irp.workflow.service.IrpWorkFlowService;

public class DocumentAction extends ActionSupport {
	/**
	 * 错误提示信息
	 */
	private String friendlyshow;
	private IrpDocumentService irpDocumentService;// 文档service
	private IrpSiteService irpSiteService;// 站点service
	private IrpChannelService irpChannelService;// 栏目service
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;// 文档栏目中间表service
	private IrpAttachedService irpAttachedService;// 附件表service
	private IrpAttachedTypeService irpAttachedTypeService;// 附件类型表
	private IrpInformService irpInformService;// 举报表service
	private DocumentReadedService irpDocumentReadedService;
	private IrpUserService irpUserService;// 用户service
	private IrpGroupService irpGroupService;// 组织service
	private IrpDocumentCollectionService irpDocumentCollectionService;
	private IrpLogsService irpLogsService;
	private IrpSubscribeService irpSubscribeService;
	private IrpValueConfigService irpValueConfigService;
	private IrpUserValueLinkService irpUserValueLinkService;
	private IrpDocumentLogsService irpDocumentLogsService;
	private SolrService solrService;
	private IrpDocumentWithBLOBs irpDocument;// 文档表
	private List<IrpDocumentWithBLOBs> irpDocuments;// 文档表集合
	private List<IrpChnlDocLink> chnlDocLinks;// 文档栏目中间表
	private IrpChannel irpChannel;// 栏目表
	private List<IrpChannel> irpChannels;// 前台当前用户的所有栏目包括一级栏目和二级栏目
	private Long siteid;
	private Long channelids;// 栏目id
	private Long id = new Long(-1);
	private Long docid; // 文档主键
	private Long docorder;// 文档的排序
	private Long attAchedId;// 附件表主键
	private IrpAttached irpAttached;// 附件表对象
	private String myFileName = "";// 文件名称
	private String myFileNameSrc = "";// 源文件名称
	private List<IrpAttached> attacheds;// 附件列表
	private String jsonFile;
	private File myFile;// 上传的文件
	private Long isTrue;// 是否发布微知
	private List<IrpDocrecommend> docrecommends;
	private IrpInform docClassicl;
	private String isDelete;
	private String isUpdate;
	private String isScore;
	private String onlydocid;
	private String msg;//验证方法
	private String editordata;//验证内容
	private String sta;//系统设置状态
	private String resulteditor;//验证处理过的内容 
	private String apiresult;//验证处理过的api;1没通过；2通过
	private List<IrpChnlDocLink> chnlDocLinks16;
	private List<IrpChnlDocLink> chnlDocLinks21;
	private List<IrpChnlDocLink> chnlDocLinks22;
	private List<IrpChnlDocLink> chnlDocLinks29;
	private List<IrpChnlDocLink> chnlDocLinks30;
	private List<IrpChnlDocLink> chnlDocLinks52;
	private List<IrpChnlDocLink> chnlDocLinks28;
	private List<String> irpMicrobloglist;
	private IrpMicroblogService irpMicroBlogService;
	private IrpCategoryService irpCategoryService;
	private List<IrpCategory> list;
	private IrpDocrecommendService irpDocrecommendServiceImpl;
	
	
	public IrpDocrecommendService getIrpDocrecommendServiceImpl() {
		return irpDocrecommendServiceImpl;
	}

	public void setIrpDocrecommendServiceImpl(
			IrpDocrecommendService irpDocrecommendServiceImpl) {
		this.irpDocrecommendServiceImpl = irpDocrecommendServiceImpl;
	}

	public List<IrpCategory> getList() {
		return list;
	}

	public void setList(List<IrpCategory> list) {
		this.list = list;
	}

	public IrpCategoryService getIrpCategoryService() {
		return irpCategoryService;
	}

	public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
		this.irpCategoryService = irpCategoryService;
	}

	public List<String> getIrpMicrobloglist() {
		return irpMicrobloglist;
	}

	public void setIrpMicrobloglist(List<String> irpMicrobloglist) {
		this.irpMicrobloglist = irpMicrobloglist;
	}

	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}

	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}
	public List<IrpChnlDocLink> getChnlDocLinks28() {
		return chnlDocLinks28;
	}


	public long getMaptype() {
		return maptype;
	}

	public void setMaptype(long maptype) {
		this.maptype = maptype;
	}

	public String getMaptypeName() {
		return maptypeName;
	}

	public void setMaptypeName(String maptypeName) {
		this.maptypeName = maptypeName;
	}

	public void setChnlDocLinks28(List<IrpChnlDocLink> chnlDocLinks28) {
		this.chnlDocLinks28 = chnlDocLinks28;
	}


	public int getSysExport() {
		return sysExport;
	}

	public void setSysExport(int sysExport) {
		this.sysExport = sysExport;
	}

	public List<IrpChnlDocLink> getChnlDocLinks16() {
		return chnlDocLinks16;
	}
	

	public List<IrpChnlDocLink> getChnlDocLinks52() {
		return chnlDocLinks52;
	}


	public void setChnlDocLinks52(List<IrpChnlDocLink> chnlDocLinks52) {
		this.chnlDocLinks52 = chnlDocLinks52;
	}


	public void setChnlDocLinks16(List<IrpChnlDocLink> chnlDocLinks16) {
		this.chnlDocLinks16 = chnlDocLinks16;
	}

	public List<IrpChnlDocLink> getChnlDocLinks21() {
		return chnlDocLinks21;
	}

	public void setChnlDocLinks21(List<IrpChnlDocLink> chnlDocLinks21) {
		this.chnlDocLinks21 = chnlDocLinks21;
	}

	public List<IrpChnlDocLink> getChnlDocLinks22() {
		return chnlDocLinks22;
	}

	public void setChnlDocLinks22(List<IrpChnlDocLink> chnlDocLinks22) {
		this.chnlDocLinks22 = chnlDocLinks22;
	}

	public String getApiresult() {
		return apiresult;
	}

	public void setApiresult(String apiresult) {
		this.apiresult = apiresult;
	}

	public String getResulteditor() {
		return resulteditor;
	}

	public void setResulteditor(String resulteditor) {
		this.resulteditor = resulteditor;
	}

	public String getSta() {
		return sta;
	}

	public String getChannelidss() {
		return channelidss;
	}

	public void setChannelidss(String channelidss) {
		this.channelidss = channelidss;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getEditordata() {
		return editordata;
	}

	public void setEditordata(String editordata) {
		this.editordata = editordata;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getOnlydocid() {
		return onlydocid;
	}

	public void setOnlydocid(String onlydocid) {
		this.onlydocid = onlydocid;
	}

	public List<IrpDocversionWithBLOBs> getDocversionlist() {
		return docversionlist;
	}

	public void setDocversionlist(List<IrpDocversionWithBLOBs> docversionlist) {
		this.docversionlist = docversionlist;
	}

	private List<IrpDocversionWithBLOBs>  docversionlist;
	private String isPriant;
	private String isFuJian;
	private String isHistoryversion;
	private String isXiangGuan;
	private String isCrUser;
	
	public String getIsCrUser() {
		return isCrUser;
	}

	public void setIsCrUser(String isCrUser) {
		this.isCrUser = isCrUser;
	}

	private int subscribe;

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public IrpSubscribeService getIrpSubscribeService() {
		return irpSubscribeService;
	}

	public void setIrpSubscribeService(IrpSubscribeService irpSubscribeService) {
		this.irpSubscribeService = irpSubscribeService;
	}

	public String getIsPriant() {
		return isPriant;
	}

	public void setIsPriant(String isPriant) {
		this.isPriant = isPriant;
	}

	public String getIsFuJian() {
		return isFuJian;
	}

	public void setIsFuJian(String isFuJian) {
		this.isFuJian = isFuJian;
	}

	public String getIsHistoryversion() {
		return isHistoryversion;
	}

	public void setIsHistoryversion(String isHistoryversion) {
		this.isHistoryversion = isHistoryversion;
	}

	public String getIsXiangGuan() {
		return isXiangGuan;
	}

	public void setIsXiangGuan(String isXiangGuan) {
		this.isXiangGuan = isXiangGuan;
	}

	public IrpValueConfigService getIrpValueConfigService() {
		return irpValueConfigService;
	}

	public HashMap<Long, String> getDenseMap() {
		return denseMap;
	}

	public void setDenseMap(HashMap<Long, String> denseMap) {
		this.denseMap = denseMap;
	}

	public void setIrpValueConfigService(IrpValueConfigService irpValueConfigService) {
		this.irpValueConfigService = irpValueConfigService;
	}

	public IrpUserValueLinkService getIrpUserValueLinkService() {
		return irpUserValueLinkService;
	}

	public void setIrpUserValueLinkService(
			IrpUserValueLinkService irpUserValueLinkService) {
		this.irpUserValueLinkService = irpUserValueLinkService;
	}
	
	public IrpDocumentLogsService getIrpDocumentLogsService() {
		return irpDocumentLogsService;
	}

	public void setIrpDocumentLogsService(
			IrpDocumentLogsService irpDocumentLogsService) {
		this.irpDocumentLogsService = irpDocumentLogsService;
	}

	private Integer avgScore;
	private Integer sumperson;
	private Integer doctype;
	private Long channelid;
	private String allChannelIds;
	private String chnldesc;
	private IrpUserPrivacyService irpUserPrivacyService;
	private Integer loginuserprivacy;
	private IrpDocumentMapService irpDocumentMapService;
	private List<IrpDocument> documentList;
	private String pageHTML = "";
	private int pageNum = 1;
	private int pageSize = 10;
	private String searchWord;
	private String searchType;
	private String orderField;
	private String orderBy;
	private String flag;
	private List<IrpChannel> personsubList;
	private String doorchannelid;
	private Map<Long, String> userGroups;
	private IrpDocversionService irpDocversionService;
	
	public IrpDocversionService getIrpDocversionService() {
		return irpDocversionService;
	}

	public void setIrpDocversionService(IrpDocversionService irpDocversionService) {
		this.irpDocversionService = irpDocversionService;
	}

	public IrpLogsService getIrpLogsService() {
		return irpLogsService;
	}

	public void setIrpLogsService(IrpLogsService irpLogsService) {
		this.irpLogsService = irpLogsService;
	}
	
	public Map<Long, String> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Map<Long, String> userGroups) {
		this.userGroups = userGroups;
	}

	public IrpGroupService getIrpGroupService() {
		return irpGroupService;
	}

	public void setIrpGroupService(IrpGroupService irpGroupService) {
		this.irpGroupService = irpGroupService;
	}

	public IrpDocumentCollectionService getIrpDocumentCollectionService() {
		return irpDocumentCollectionService;
	}

	public void setIrpDocumentCollectionService(
			IrpDocumentCollectionService irpDocumentCollectionService) {
		this.irpDocumentCollectionService = irpDocumentCollectionService;
	}

	public String getDoorchannelid() {
		return doorchannelid;
	}

	public Long getChannelidleft() {
		return channelidleft;
	}

	public void setChannelidleft(Long channelidleft) {
		this.channelidleft = channelidleft;
	}

	public void setDoorchannelid(String doorchannelid) {
		this.doorchannelid = doorchannelid;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public List<IrpChannel> getPersonsubList() {
		return personsubList;
	}

	public void setPersonsubList(List<IrpChannel> personsubList) {
		this.personsubList = personsubList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public List<IrpDocument> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<IrpDocument> documentList) {
		this.documentList = documentList;
	}

	public IrpDocumentMapService getIrpDocumentMapService() {
		return irpDocumentMapService;
	}

	public void setIrpDocumentMapService(IrpDocumentMapService irpDocumentMapService) {
		this.irpDocumentMapService = irpDocumentMapService;
	}

	public Integer getLoginuserprivacy() {
		return loginuserprivacy;
	}

	public void setLoginuserprivacy(Integer loginuserprivacy) {
		this.loginuserprivacy = irpUserPrivacy();
	}

	public IrpUserPrivacyService getIrpUserPrivacyService() {
		return irpUserPrivacyService;
	}

	public void setIrpUserPrivacyService(
			IrpUserPrivacyService irpUserPrivacyService) {
		this.irpUserPrivacyService = irpUserPrivacyService;
	}

	// 1 为question 2为doc
	// 标识
	private int isqusertionordoc;

	public int getIsqusertionordoc() {
		return isqusertionordoc;
	}

	public void setIsqusertionordoc(int isqusertionordoc) {
		this.isqusertionordoc = isqusertionordoc;
	}

	public String getChnldesc() {
		return chnldesc;
	}

	public void setChnldesc(String chnldesc) {
		this.chnldesc = chnldesc;
	}

	public String getAllChannelIds() {
		return allChannelIds;
	}

	public void setAllChannelIds(String allChannelIds) {
		this.allChannelIds = allChannelIds;
	}

	public Long getChannelid() {
		return channelid;
	}

	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}

	private List<IrpInform> docClassicls;

	public Integer getDoctype() {
		return doctype;
	}

	public void setDoctype(Integer doctype) {
		this.doctype = doctype;
	}

	public Integer getAvgScore() {
		return avgScore;
	}

	public Integer getSumperson() {
		return sumperson;
	}

	public void setSumperson(Integer sumperson) {
		this.sumperson = sumperson;
	}

	public void setAvgScore(Integer avgScore) {
		this.avgScore = avgScore;
	}

	public String getIsScore() {
		return isScore;
	}

	public void setIsScore(String isScore) {
		this.isScore = isScore;
	}

	private String toUpdate;
	private Long loginUserId;
	private String maxAmount;
	private Integer saveattachedstatus;
	private String[] chandocids;
	// 问答转换为知识
	private Long questionid;
	private IrpQuestionService irpQuestionService;
	private IrpQuestion irpQuestionofquestionid;
	private IrpQuestion irpQuestionofparentid;
	private String irpAttachedarraylist;

	public String getIrpAttachedarraylist() {
		return irpAttachedarraylist;
	}

	public void setIrpAttachedarraylist(String irpAttachedarraylist) {
		this.irpAttachedarraylist = irpAttachedarraylist;
	}

	public IrpQuestion getIrpQuestionofquestionid() {
		return irpQuestionofquestionid;
	}

	public void setIrpQuestionofquestionid(IrpQuestion irpQuestionofquestionid) {
		this.irpQuestionofquestionid = irpQuestionofquestionid;
	}

	public IrpQuestion getIrpQuestionofparentid() {
		return irpQuestionofparentid;
	}

	public void setIrpQuestionofparentid(IrpQuestion irpQuestionofparentid) {
		this.irpQuestionofparentid = irpQuestionofparentid;
	}

	public IrpQuestionService getIrpQuestionService() {
		return irpQuestionService;
	}

	public void setIrpQuestionService(IrpQuestionService irpQuestionService) {
		this.irpQuestionService = irpQuestionService;
	}

	public Long getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Long questionid) {
		this.questionid = questionid;
	}

	private List<IrpConfig> docclassials;

	public String[] getChandocids() {
		return chandocids;
	}

	public List<IrpConfig> getDocclassials() {
		return docclassials;
	}

	public void setDocclassials(List<IrpConfig> docclassials) {
		this.docclassials = docclassials;
	}

	public void setChandocids(String[] chandocids) {
		this.chandocids = chandocids;
	}

	private Integer refrechstatus;

	public Integer getSaveattachedstatus() {
		return saveattachedstatus;
	}

	public void setSaveattachedstatus(Integer saveattachedstatus) {
		this.saveattachedstatus = saveattachedstatus;
	}

	public String getToUpdate() {
		return toUpdate;
	}

	public Long getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	public void setToUpdate(String toUpdate) {
		this.toUpdate = toUpdate;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Integer getRefrechstatus() {
		return refrechstatus;
	}

	public void setRefrechstatus(Integer refrechstatus) {
		this.refrechstatus = refrechstatus;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	private DocScoreService irpDocumentScoreService;

	public DocScoreService getIrpDocumentScoreService() {
		return irpDocumentScoreService;
	}

	public void setIrpDocumentScoreService(
			DocScoreService irpDocumentScoreService) {
		this.irpDocumentScoreService = irpDocumentScoreService;
	}

	private Long score;

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	//key验证前字符串处理
	public static String String_length(String value) {
		  int valueLength = 0;
		  String chinese = "[\u4e00-\u9fa5]";
		  String gtbiao = "[《，》。？、：；“’”‘｛【｝】|（）！……￥——]"; 
		  StringBuffer sb = new StringBuffer();
		  for (int i = 0; i < value.length(); i++) {
		   String temp = value.substring(i, i + 1);
		   if (temp.matches(chinese)) {
			   sb.append(temp);
		    valueLength += 2;
		   } else { 
			 if(temp.matches(gtbiao)){
				 sb.append(temp);
				 valueLength += 2;
			 }else{ 
				 sb.append(temp+" ");
				 valueLength += 1; 
			 } 
		   }
		  }
		  return sb.toString();
		 }
	
	//key验证
	/*public void topreviewDocument() {
	Object[] obj = new Object[1];
	List keyresult = new ArrayList();
	System.out.println(System.currentTimeMillis());
	editordata = editordata.replaceAll("&quot;",String.valueOf('"'));
	editordata = editordata.replaceAll("&ldquo;",String.valueOf('“'));
	editordata = editordata.replaceAll("&rdquo;",String.valueOf('”'));
	editordata = editordata.replaceAll("&nbsp;",String.valueOf(' '));
	editordata = editordata.replaceAll("&hellip;",String.valueOf('…'));
	editordata = editordata.replaceAll("&mdash;",String.valueOf('—'));
	System.out.println(System.currentTimeMillis());
	obj[0] = editordata;
	if(sta != null ){
		if (sta.equals("true") || sta == "true") {// 开启
			String[] api = msg.split(",");
			String url = "http://127.0.0.1:8081/key/services/key";
			*//**判断有配置方法----开始**//*
			if (api[0] != null || api.length > 0) {
				*//**判断配置方法个数是否有多个----开始**//*
				if(api.length >= 1){
				*//**循环多个api方法----开始**//*
				for (int i = 0; i < api.length; i++) {
					String method = "api" + api[i];
					keyresult.add( "<div style='border: solid;0.5px;'>"+KeyUtil.sendService(obj, url, method)+"<br/>"+"</div>");
					String api2 = null;
					String api3 = null;
					if( method == "api6" || method.equals("api6")){
						KeyUtil.sendService(obj, url, method);
					}
	if(method == "api1" || method.equals("api1") || method == "api4" || method.equals("api4") || method == "api5" || method.equals("api5") ){
		String[] api1splitresult = editordata.split("[，。？！；：,.?!;:]");
		for(int n = 0 ; n < api1splitresult.length ; n++){
			obj[0] = api1splitresult[n];
			if(api1splitresult[n].length() > 375){
				List ap1splitlist = new ArrayList();
				int  api1splitlength = api1splitresult[n].length() / 375;
				for(int h = 0 ; h < api1splitlength ; h++){
					String api1splitresultstring = null;
					if(api1splitresult[n].length() % 375 == 0){
						api1splitresultstring =	api1splitresult[n].substring(h * 375, (h + 1) * 375);
						}else{
							if(h == api1splitlength){
								api1splitresultstring = api1splitresult[n].substring(h * 375,api1splitresult[n].length());
								}else{
									api1splitresultstring =	api1splitresult[n].substring(h * 375, (h + 1) * 375);
								}
						 }
						ap1splitlist.add(api1splitresultstring);
						for(int k = 0; k < ap1splitlist.size() ; k ++){
							obj[0] = ap1splitlist.get(k);
							KeyUtil.sendService(obj, url, method);
						}
				}
			}else{
				obj[0] = api1splitresult[n];
				KeyUtil.sendService(obj, url, method);
				}
		}
		resulteditor = editordata;
	}
	*//**api2方法验证----开始**//*
	if (method == "api2" || method.equals("api2")) {//api2验证
		String[] spliteditordata = editordata.split("[，。？！；：,.?!;:]");
		*//**打散验证----开始**//*
		List alphabetnumberlist = new ArrayList();
		*//**375截取处理----开始**//*
		for(int j = 0 ;j < spliteditordata.length;j++){
			System.out.println(System.currentTimeMillis());
			if(spliteditordata[j].length() > 375){
				String alphabetsplitresult = null;
				int conditions = 0;
				String changeeditordata = null;
				int place = 0;
				int e = 0;
				String alphabetsplit = null;
				if(spliteditordata[j].length()%375 >= 0){
					conditions = spliteditordata[j].length()/375 ;
					for(int h = 0; h <= conditions ; h ++){
						String alphabetnumber = null;
						if(spliteditordata[j].length()%375 > 0){
							if(conditions == h){
								alphabetnumber = spliteditordata[j].substring(h * 375, h * 375 + spliteditordata[j].length()%375);
							  }else{
								alphabetnumber = spliteditordata[j].substring(h * 375, (h+1) * 375);
								}
						}else{
						 alphabetnumber = spliteditordata[j].substring(h * 375, (h+1) * 375);
							}
						alphabetnumberlist.add(alphabetnumber);
					 }
					 for(int p = 0 ; p < alphabetnumberlist.size(); p++){
						 System.out.println(System.currentTimeMillis());
						obj[0] = alphabetnumberlist.get(p);
						String api1 = null;
						api1 = KeyUtil.sendService(obj, url, "api1");
						System.out.println(api1);
						//if
						api2 = "<div style='border: solid;0.5px;'>"+KeyUtil.sendService(obj, url, method)+"<br/>"+"</div>";
						String apifirst = api2.substring(api2.indexOf("置"),api2.indexOf("类"));
						String apisecond = apifirst.substring(1,apifirst.length() - 2);
						String api1result = KeyUtil.sendService(obj, url, "api1");
						String api1resultnumber = api1result.substring(api1result.indexOf("：")+1, api1result.length());
						int second = 0;
						if(Integer.parseInt(api1resultnumber)>0){//判断是否有错字
							alphabetsplit = alphabetnumberlist.get(p).toString().substring(0,Integer.parseInt(apisecond))
											+ "<font color='" + "red" + "'>"
											+ alphabetnumberlist.get(p).toString().substring(Integer.parseInt(apisecond),Integer.parseInt(apisecond)+1)
											+ "</font>"
											+ alphabetnumberlist.get(p).toString().substring(
													Integer.parseInt(apisecond)+1,
													alphabetnumberlist.get(p).toString().length()); 
							if(p == 0){
								alphabetsplitresult = alphabetsplit;
							}else{
								alphabetsplitresult += alphabetsplit;
							}
						}else{
							if( p == 0){
								alphabetsplitresult = alphabetnumberlist.get(p).toString();
							}else{
								alphabetsplitresult += alphabetnumberlist.get(p).toString();
							}
						}
						System.out.println(System.currentTimeMillis());
					}
		}
		String[] api2editordata = editordata.split("[，。？！；：,.?!;:]");
		for(e = 0;e < j;e++){
			place += api2editordata[e].toString().length();
	}
			if(e == api2editordata.length){
				changeeditordata = editordata.substring(0, place + e ) +
						alphabetsplitresult;
			}else{
				if( e  == api2editordata.length){
					changeeditordata = editordata.substring(0, place + e ) +
							alphabetsplitresult ;
				}else{
					changeeditordata = editordata.substring(0, place + e ) +
							alphabetsplitresult  
							+editordata.substring(place + e + api2editordata[e].length(),
									editordata.length());
				}
			 }
	editordata = changeeditordata;
	resulteditor = editordata;
	}
		*//**375截取处理if----结束**//*
		*//**375截取处理else----开始**//*
		else{
			*//**第一个标点判断----开始**//*
			if(spliteditordata.length==1){
				obj[0] = spliteditordata[j];
				String api1 = null;
				api1 = KeyUtil.sendService(obj, url, "api1");
				if( Integer.parseInt(api1.substring(6, api1.length())) > 0 ){
				api2 = "<div style='border: solid;0.5px;'>"+KeyUtil.sendService(obj, url, method)+"<br/>"+"</div>";
				String apifirst = api2.substring(api2.indexOf("置"),api2.indexOf("类"));
				String apisecond = apifirst.substring(1,apifirst.length() - 2);
				*//**第一个错字----开始**//*
				if(Integer.parseInt(apisecond)>0){
					editordata = "<br/>" + editordata.substring(0,Integer.parseInt(apisecond))
								+ "<font color='" + "red" + "'>"
								+ editordata.substring(Integer.parseInt(apisecond),Integer.parseInt(apisecond) + 1)
								+ "</font>"
								+ editordata.substring(Integer.parseInt(apisecond) + 1 , editordata.length());
								resulteditor = editordata;
					}
				
				}
				*//**第一个错字----结束**//*
					}
				*//**第一个标点判断----结束**//*
				else{*//**有多个错字----开始**//*
					spliteditordata = editordata.split("[，。？！；：,.?!;:]");
					*//**获取错字位置循环----开始**//*
					for(j = 0 ;j < spliteditordata.length;j++){
					obj[0] = spliteditordata[j];
					String api1 = null;
					api1 = KeyUtil.sendService(obj, url, "api1");
					if( Integer.parseInt(api1.substring(6, api1.length())) > 0 ){
					System.out.println(api1.substring(6,api1.length()));
					api2 = "<div style='border: solid;0.5px;'>"+KeyUtil.sendService(obj, url, method)+"<br/>"+"</div>";
					String apifirst = api2.substring(api2.indexOf("置"),api2.indexOf("类"));
					StringBuffer apisecondtobuffer = new StringBuffer();
					apisecondtobuffer.append(apifirst.substring(1,apifirst.length() - 2));
					String apisecond = apisecondtobuffer.toString();
					*//**有错字----开始**//*
					if(j == 0){
						if(Integer.parseInt(apisecond)>0){
						editordata = "<br/>" + editordata.substring(0,Integer.parseInt(apisecond))
									+ "<font color='" + "red" + "'>"
									+ editordata.substring(Integer.parseInt(apisecond),Integer.parseInt(apisecond) + 1)
									+ "</font>"
									+ editordata.substring(Integer.parseInt(apisecond) + 1,editordata.length());
						}
					}else{
						String[] first = resulteditor.split("[，。？！；：,.?!;:]");
						int second = 0;
						int l = 0;
						for(l = 0;l < j;l++){
							second += first[l].length();
						}
						String api1result = KeyUtil.sendService(obj, url, "api1");
						String api1resultnumber = api1result.substring(api1result.indexOf("：")+1, api1result.length());
						if(Integer.parseInt(api1resultnumber)>0){//判断是否有错字
						editordata = editordata.substring(0,second+Integer.parseInt(apisecond)+l)
								+ "<font color='" + "red" + "'>"
								+ editordata.substring(second+Integer.parseInt(apisecond)+l,second+Integer.parseInt(apisecond)+l+1)
								+ "</font>"
								+ editordata.substring(second+Integer.parseInt(apisecond)+l+1 , editordata.length());
						}
					 }}
					resulteditor = editordata;
					*//**有错字----结束**//*
					*//**获取错字位置循环----结束**//*
			}
			*//**有多个错字----结束**//*
		}
		*//**375else结束**//*
		}
		*//**打散验证----结束**//*
			String api1result = KeyUtil.sendService(obj, url, "api1");
			String api1resultnumber = api1result.substring(api1result.indexOf("：")+1, api1result.length());
			if(api1resultnumber.equals("0") || api1resultnumber == "0"){
				apiresult = "2";
			}else{
				apiresult = "1";
			}
	}
	*//**api2方法验证if----结束**//*
	else{
			//resulteditor = resulteditor;
			String api1result = KeyUtil.sendService(obj, url, "api1");
			String api1resultnumber = api1result.substring(api1result.indexOf("：")+1, api1result.length());
			if(api1resultnumber.equals("0") || api1resultnumber == "0"){
				apiresult = "2";
			}else{
				apiresult = "1";
			}
		}
	*//**api2方法验证----结束**//*
	// api3方法验证----开始
	if (method == "api3" || method.equals("api3")) {
		resulteditor = editordata;
		String[] result = resulteditor.split("[，。？！；：,.?!;:]");
		String resulteditor_1 = null;
		String[] resulteditor_2 = null;
		if(result.length > 0||result != null){
			resulteditor_2 = resulteditor.split("[，。？！；：,.?!;:]");
		}else{
			resulteditor_2[0] = String_length(resulteditor);
		}
		if(resulteditor_2.length > 0 || resulteditor_2 != null){
			String alphabetsplitresult = null;
			int conditions = 0;
			int place = 0;
			int e = 0;
			String changeeditordata = null;
			List alphabetnumberlist = new ArrayList();
			StringBuffer alphabetsplit = new StringBuffer();
			for(int k = 0;k<resulteditor_2.length;k++){
				*//**api3方法----if开始**//*
				if(resulteditor_2[k].length() >= 375){
					System.out.println(System.currentTimeMillis());
					conditions = resulteditor_2[k].length()/375 ;
					for(int h = 0; h <= conditions ; h ++){
						String alphabetnumber = null;
						if(resulteditor_2[k].length()%375 > 0){
							if(conditions == h){
								alphabetnumber = resulteditor_2[k].substring(h * 375, h * 375 + resulteditor_2[k].length()%375);
							}else{
								alphabetnumber = resulteditor_2[k].substring(h * 375, (h+1) * 375);
							}
						}else{
							alphabetnumber = resulteditor_2[k].substring(h * 375, (h+1) * 375);
						}
						
						alphabetnumberlist.add(alphabetnumber);
					}
			for(int t = 0; t < alphabetnumberlist.size(); t++){
				System.out.println(System.currentTimeMillis());
				obj[0] = String_length(alphabetnumberlist.get(t).toString());
				String api3result = KeyUtil.sendService(obj, url,method);//循环获取api3返回结果
				if (api3result.length() > 78) {//返回值中是否包含错误位置信息判断
					String apifirst = api3result.substring(api3result.indexOf("^"), api3result.length());
					String apithird = apifirst.substring(1, apifirst.length());
					String apisecond = apithird.substring(0, apithird.indexOf("^"));
					String lengthfirst = apithird.substring(apithird.indexOf("^"), apithird.length());
					String lengthsecond = lengthfirst.substring(lengthfirst.indexOf("^") + 1,lengthfirst.length());//获取错误位置
					String lengththird = lengthsecond.substring(0, lengthsecond.indexOf("^"));
					int length = lengththird.length();//错词长度
					if(t == 0){//第一句话
						alphabetsplit.append(alphabetnumberlist.get(t).toString().substring(0,Integer.parseInt(apisecond)/2)
											+ "<font color='" + "#0066FF  " + "'>"
											+ alphabetnumberlist.get(t).toString().substring(Integer.parseInt(apisecond)/2 ,
													Integer.parseInt(apisecond)/2 + length )
											+ "</font>"
											+ alphabetnumberlist.get(t).toString().substring(Integer.parseInt(apisecond)/2 + length,
													alphabetnumberlist.get(t).toString().length()));
					}else{//除去第一句话之后的所有截取语句
						String[] resulteditor_3 = resulteditor.split("[，。？！；：,.?!;:]");
						for(e = 0;e < k - 1 ; e++){
							place += resulteditor_2[e].length();
						}
						alphabetsplit.append(alphabetnumberlist.get(t).toString().substring(0, Integer.parseInt(apisecond)/2)
											+ "<font color='" + "#0066FF  " + "'>"
											+ alphabetnumberlist.get(t).toString().substring(Integer.parseInt(apisecond)/2,
													Integer.parseInt(apisecond)/2 + length )
											+ "</font>"
											+ alphabetnumberlist.get(t).toString().substring(Integer.parseInt(apisecond)/2 + length,
													alphabetnumberlist.get(t).toString().length()));
				  }
					if(t == 0){
						alphabetsplitresult = alphabetsplit.toString();
					}else{
						alphabetsplitresult += alphabetsplit.toString();
					}
				}
				else{
					if(t == 0){
						alphabetsplitresult = alphabetnumberlist.get(t).toString();
					}else{
						alphabetsplitresult += alphabetnumberlist.get(t).toString();
					}
				}
				System.out.println(System.currentTimeMillis());
			}
			if(e == resulteditor_2.length){
				changeeditordata = resulteditor.substring(0, place + e ) + alphabetsplitresult;
			}else{
				String[] resulteditor_3 = resulteditor.split("[，。？！；：,.?!;:]");
				place = 0 ;
				for(e = 0;e < k; e++){
					place += resulteditor_3[e].length();
				}
				if(e + 1 == resulteditor_2.length){
					changeeditordata = resulteditor.substring(0, place + e ) + alphabetsplitresult ;
				}else{
					changeeditordata = resulteditor.substring(0, place + e + 1) + alphabetsplitresult  
									   +resulteditor.substring(place + e  + resulteditor_3[e].length() , resulteditor.length());
				}
			}
			editordata = changeeditordata;
			resulteditor = editordata;
		}
		*//**api3方法----if结束**//*
		*//**api3方法----else开始**//*
		else{
		method = "api3";
		obj[0] = String_length(resulteditor_2[k]);
		String api3result = KeyUtil.sendService(obj, url,method);//循环获取api3返回结果
		if (api3result.length() > 78) {//返回值中是否包含错误位置信息判断
			String apifirst = api3result.substring(api3result.indexOf("^"), api3result.length());
			String apithird = apifirst.substring(1, apifirst.length());
			String apisecond = apithird.substring(0, apithird.indexOf("^"));
			String lengthfirst = apithird.substring(apithird.indexOf("^"), apithird.length());
			String lengthsecond = lengthfirst.substring(lengthfirst.indexOf("^") + 1,lengthfirst.length());//获取错误位置
			String lengththird = lengthsecond.substring(0, lengthsecond.indexOf("^"));
			int length = lengththird.length();//错词长度
			int second = 0;
			if(k == 0){//第一句话
				resulteditor = resulteditor.substring(0, second + Integer.parseInt(apisecond)/2)
								+ "<font color='" + "#0066FF  " + "'>"
								+ resulteditor.substring(second + Integer. parseInt(apisecond)/2 ,second + Integer.parseInt(apisecond)/2 + length )
								+ "</font>"
								+ resulteditor.substring( second + Integer.parseInt(apisecond)/2 + length,resulteditor.length());
			}else{//除去第一句话之后的所有截取语句
				int m = 0;
				String[] resulteditor_3 = resulteditor.split("[，。？！；：,.?!;:]");
				second = 0;
				for(m = 0; m < k ; m++){//累加当前的所有语句字数
					second += resulteditor_3[m].length();
					}
				resulteditor = resulteditor.substring(0,second + Integer.parseInt(apisecond)/2+ m)
								+ "<font color='" + "#0066FF  " + "'>"
								+ resulteditor.substring(second + Integer.parseInt(apisecond)/2 + m,
										second + Integer.parseInt(apisecond)/2 + m + length )
								+ "</font>"
								+ resulteditor.substring(second + Integer.parseInt(apisecond)/2 + m  + length,resulteditor.length());
			}
			if(api3result.length() > 78){
				apiresult = "1";
			}else{
				apiresult = "2";
			}
			}else{
				obj[0] = resulteditor;
				api3result = KeyUtil.sendService(obj, url,method);
				if(api3result.length() > 78){
					apiresult = "1";
				}else{
					apiresult = "2";
				}
				resulteditor = resulteditor;
			}			
		  }
				  *//**api3方法----else结束**//*
							}
						}
					}
					*//**api3方法----结束**//*
				  }
				  *//**循环多个api方法----结束**//*
				}
				*//**判断配置方法个数是否有多个----结束**//*
			}
			*//**判断有配置方法----结束**//*
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("editorcontent", resulteditor);
		session.setAttribute("alertmessage", keyresult);
		ActionUtil.writer(JsonUtil.string2json(apiresult));
		}
	}		*/																												

	public String previewDocument() {
		// **调用分割文章方法，进行对文章进行分页*/
		irpDocument.setDochtmlcon(pageDocument(irpDocument.getDochtmlcon()));
		irpDocument.setDockeywords(keywordEdit(irpDocument.getDockeywords()));
		return SUCCESS;
	}
	
	//添加标签
	private String keywordEdit(String sTags){
		if(sTags==null)
			return "";
		sTags = sTags.trim();
		//替换所有的分隔符为逗号
		sTags = sTags.replaceAll(" ", ",");
		sTags = sTags.replaceAll("；", ",");
		sTags = sTags.replaceAll(";", ",");
		sTags = sTags.replaceAll("，", ","); 
		/**
		 * 将字符串中的重复词语给去掉
		 */
		Set<String> onlyStr=new HashSet<String>();
		if(sTags!=null && sTags.length()>0){
			String[] strs=sTags.split(",");
			for (int i = 0; i < strs.length; i++) {
				onlyStr.add(strs[i].trim());
			}
		}
		/**
		 * 如果标签不为null
		 */
		String sKey="";
		for (String sWord : onlyStr) {
			if(!sKey.isEmpty()){
				sKey += (","+sWord);
			}else{
				sKey = sWord;
			}
		}
		return sKey;
	}

	// 给知识添加分
	public void addScore() {
		int nCount = irpDocumentScoreService.addscore(docid, score);
		ActionUtil.writer(String.valueOf(nCount));
	}

	/**
	 * 知识的分页
	 */
	public String pageDocument(String docHtmlCon) {
		String outStr = "";
		String returnValue = "";
		int nCount = 1;

		if (docHtmlCon != null && !docHtmlCon.trim().equals("")) {
			docHtmlCon = docHtmlCon.trim();
			String pattern = "<div style=\"page-break-after:.*always;?\"><span style=\"display:none.?\">&nbsp;</span></div>";
			String[] strSiplit = docHtmlCon.split(pattern);

			nCount = strSiplit.length;
			if (nCount > 1) {
				outStr = "<div id='page_break'>";
				for (int i = 1; i <= nCount; i++) {
					if (i <= 1) {
						outStr += "<div id='page_" + String.valueOf(i) + "'>"
								+ strSiplit[i - 1] + "</div>";
					} else {
						outStr += "<div id='page_" + String.valueOf(i)
								+ "' class='collapse'>" + strSiplit[i - 1]
								+ "</div>";
					}
				}

				outStr += "<div class='num'>";
				// 上一页
				outStr += "<a href='#documenttop'>上一页</a>";

				for (int j = 1; j <= nCount; j++) {
					outStr += "<li id='" + String.valueOf(j)
							+ "'><a href='#documenttop'>" + String.valueOf(j)
							+ "</a></li>";
				}
				// 下一页
				outStr += "<a href='#documenttop'>下一页</a>&nbsp&nbsp;";
				outStr += "共" + nCount + "页";
				outStr += "&nbsp&nbsp;<a href='#documenttop' onclick='showAllData()' id='show'>查看全文</a>";
				outStr += "</div>";
				outStr += "</div>";
				returnValue = outStr;
			} else {
				returnValue = docHtmlCon;
			}
		}
		// 共几页
		return returnValue;
	}

	// 后台，调整栏目
	public void moveToChannel() {
		int nCount = 0;
		List<Long> arrDocIds = new ArrayList<Long>(), arrChnlDocIds = new ArrayList<Long>();
		if (chandocids != null && chandocids.length > 0) {
			for (String key : chandocids) {
				if(key==null)
					continue;
				String[] _value = key.split("#");
				if(_value.length==2){
					arrDocIds.add(Long.valueOf(_value[0]));
					arrChnlDocIds.add(Long.valueOf(_value[1]));
				}
			}
		}
		nCount = irpDocumentService.adminUpdateDocumentChannelId(this.id, arrDocIds, arrChnlDocIds);
		ActionUtil.writer(String.valueOf(nCount));
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	// 前台列表里面修改文档所属的栏目
	public void updateDocumentChannelId() {
		int nCount = irpDocumentService
				.clientUpdateDocumentChannelId(irpDocument);
		ActionUtil.writer(String.valueOf(nCount));
	}

	// 企业首页跳到添加案例页面
	public String toAddSubject() {
		IrpUser loginUser = LoginUtil.getLoginUser();
		this.loginuserprivacy = irpUserPrivacy();
		documentMap = irpChannelService.allDocumentMap(IrpChannel.IS_DOCSTATIUS);
		// 获得当前栏目的全路径
		if (this.id != null && this.id > 0) {
			// 获得当前文档栏目信息
			this.chnldesc = irpChannelService.findChannelNameByChannelid(this.id);
			List<Long> channelidList = new ArrayList<Long>();
			channelidList = irpChannelService.allparentidList(this.id, channelidList, IrpChannel.IS_DOCSTATIUS, loginUser);
			if (channelidList != null && channelidList.size() > 0) {
				for (Long long1 : channelidList) {
					if (this.allChannelIds == null || this.allChannelIds.length() == 0) {
						this.allChannelIds = long1.toString();
					} else {
						this.allChannelIds += "," + long1.toString();
					}
				}
			} else {
				this.friendlyshow = IrpChannel.CHANNEL_TO_ADD_DOC;
				return ERROR;
			}
		}
		
		//获得用户所属的组织
		userGroups = irpGroupService.findGroupIdsByUserId(loginUser.getUserid());
		
		initAllQiyeSubject();
		return SUCCESS;
	}
	/**
	 * 初始化所有企业专题
	 */
	public void initAllQiyeSubject(){
		IrpChannelExample chnlExample = new IrpChannelExample();
		chnlExample.createCriteria().andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT)
									.andParentidEqualTo(Long.valueOf(SysConfigUtil.getSysConfigValue("DOCUMENT_QIYE_SUBJECT_ID")))
									.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH)
									.andStatusEqualTo(IrpChannel.IS_DOCSTATIUS);
		chnlExample.setOrderByClause(" crtime desc");
		List<IrpChannel> list = irpChannelService.findChannelByExample(chnlExample);
		if(list!=null && list.size()>0){
			irpChannels = list;
			
		}else{
			irpChannels = null;
		}
	}
	
	/**
	 * 所有普通专题
	 * @return
	 */
	public String initAllNormalSubject(){
		String parentid = SysConfigUtil.getSysConfigNumValue("DOCUMENT_QIYE_SUBJECT_ID")+"";
		List<IrpChannel> list = new ArrayList<IrpChannel>();
		list = irpChannelService.findSubIdsByParent(Long.valueOf(parentid),list);
		IrpChannelExample qiyeExample = new IrpChannelExample();
		Criteria criteria = qiyeExample.createCriteria();
		if(list!=null && list.size()>0){
			List<Long> channelidslist  = new ArrayList<Long>();
			for(IrpChannel listele : list){
				channelidslist.add(listele.getChannelid());
			}
			criteria.andChannelidIn(channelidslist);
		}else {
			irpChannels = null;
			return SUCCESS;
		}
		criteria.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
		criteria.andSiteidEqualTo(0L);
		qiyeExample.setOrderByClause(" crtime desc");
		//管理可以修改未发布的
		list = irpChannelService.findChannelByExample(qiyeExample);
		if(list!=null && list.size()>0){
			PageUtil page = new PageUtil(pageNum, pageSize, list.size());
			irpChannels = irpChannelService.selectByExample(page, qiyeExample);
			this.pageHTML = page.getClientPageHtml("page(#PageNum#)"); 
		}else{
			irpChannels = null;
		}
	return SUCCESS;
	}
	/**
	 * 所有检索专题
	 * @return
	 */
	public String initAllSuperSubject(){
		String parentid = SysConfigUtil.getSysConfigNumValue("DOCUMENT_QIYE_SUBJECT_ID")+"";
		List<IrpChannel> list = new ArrayList<IrpChannel>();
		list = irpChannelService.findSubIdsByParent(Long.valueOf(parentid),list);
		IrpChannelExample qiyeExample = new IrpChannelExample();
		Criteria criteria = qiyeExample.createCriteria();
		if(list!=null && list.size()>0){
			List<Long> channelidslist  = new ArrayList<Long>();
			for(IrpChannel listele : list){
				channelidslist.add(listele.getChannelid());
			}
			criteria.andChannelidIn(channelidslist);
		}else {
			irpChannels = null;
			return SUCCESS;
		}
		criteria.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT);
		criteria.andSiteidEqualTo(0L);
		qiyeExample.setOrderByClause(" crtime desc");
		//管理可以修改未发布的
		list = irpChannelService.findChannelByExample(qiyeExample);
		if(list!=null && list.size()>0){
			PageUtil page = new PageUtil(pageNum, pageSize, list.size());
			irpChannels = irpChannelService.selectByExample(page, qiyeExample);
			this.pageHTML = page.getClientPageHtml("page(#PageNum#)"); 
		}else{
			irpChannels = null;
		}
	return SUCCESS;
	}

	// 查看用户有权限的栏目（提交案例用）
	public void userAllChannlRight() {
		// 查看用户当前可以有查看权限的栏目
		irpChannels = irpChannelService.allRightChannel();
		if (irpChannels != null && irpChannels.size() > 0) {
			JSONArray array = JSONArray.fromObject(irpChannels);
			ActionUtil.writer(array.toString());
		} else {
			ActionUtil.writer("");
		}
	}

	// 查看投稿的知识
	public String touGaoDocument() {
		irpDocument = irpDocumentService.findDoucumentByOldId(docid);
		if (irpDocument == null)
			return ERROR;
		this.docid = irpDocument.getDocid();
		return SUCCESS;
		// Boolean isRight= rightToDocument(irpDocument);
		// if(isRight)return SUCCESS;
		// friendlyshow=IrpDocument.DOCUMENT_NO_RIGHT;
		// return ERROR;
	}

	/**
	 * 判断这个知识的权限
	 * 
	 * @return
	 */
	public Boolean rightToDocument(IrpDocument document) {
		// 判断他的权限
		List<Long> channelidList = new ArrayList<Long>();
		channelidList = irpChannelService.allparentidList(
				irpDocument.getChannelid(), channelidList,
				IrpChannel.IS_DOCSTATIUS, LoginUtil.getLoginUser());
		Long channelid=document.getChannelid();
		IrpChannel irpChannel=irpChannelService.finChannelByChannelid(channelid);
		if(irpChannel!=null&&irpChannel.getChnltype()==1000){
			return true;
		}else{
			if (channelidList == null || channelidList.size() == 0)
				return false;
		}
		return true;
	}

	// 前台细览页面 （执行这个方法就要对当前文档的点击量操作care_doc）
	private Integer irpUserPrivacy() {
		IrpUserPrivacy irpUserPrivacy = null;
		irpUserPrivacy = this.irpUserPrivacyService.irpUserPrivacy(
				LoginUtil.getLoginUserId(), IrpUserPrivacy.USERLOGINLOCATION);
		return irpUserPrivacy.getPrivacyvalue();
	}

	public String clientShowDocumentInfo() {
		IrpChannel channle = new IrpChannel();
		RightUtil rightUtil = new RightUtil();
		Long nOPIdd = rightUtil.findOperTypeIdByKey("DOCUMENT_PRINTDOC");//打印
		Long nOPIdf = rightUtil.findOperTypeIdByKey("DOCUMENT_ACCORYDOWNLOAD");//附件下载
		Long nOPIdl = rightUtil.findOperTypeIdByKey("DOCUMENT_PRINTDOC");//历史版本
		Long nOPIdx = rightUtil.findOperTypeIdByKey("DOCUMENT_DOCCORRELATIONCOMMENT");//相关评论
		
		this.loginuserprivacy = irpUserPrivacy();
		IrpUser irpUser = LoginUtil.getLoginUser();
		if (docid == null || docid == 0L) {
			friendlyshow = IrpDocument.DOCUMENT_IS_ERROR;
			return ERROR;
		}
		if (id == null || id == 0L) {
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;
		}
		irpDocument = irpDocumentService.findDocumentByDocId(this.docid);
		Long dense=LoginUtil.getLoginUser().getDense();
		long userDense=0;
		if(dense==null){
			dense=0L;
		}
		userDense=dense;
		String Trans=irpDocument.getTransformname();
		long docDense=0;
		if(Trans==null||Trans.equals("")){
			Trans="0";
		}
		long did=irpDocument.getCruserid();
		long uid=LoginUtil.getLoginUser().getUserid();
		docDense=Long.parseLong(Trans);
		if(did!=uid){
			if(userDense<docDense){
				friendlyshow = "该篇文章未加密文件，您无权查阅！";
				return ERROR;
			}
		}
		if (irpDocument == null) {
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;
		}
		IrpSite site = irpSiteService.siteInfo(irpDocument.getSiteid());
		if (site == null) {
			friendlyshow = IrpSite.SITE_NOT_SAVE;
			return ERROR;
		}
		if (site.getSitetype() == IrpSite.SITE_TYPE_PRIVATE) {// 如果是企业知识就重定向到企业知识去
			return INPUT;
		}
		
		boolean checkCrUser = irpUser.getUserid().longValue() == irpDocument.getCruserid().longValue();
		
		if (!checkCrUser) {
			if (irpDocument.getChannelid() < 0
					|| irpDocument.getDocstatus() < 0) {
				friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
				return ERROR;
			}
			// 查看知识状态是否等于发布状态
			if (!IrpDocument.PUBLISH_STATUS.toString().equals(
					irpDocument.getDocstatus().toString())) {
				friendlyshow = IrpDocument.DOCUMENT_IS_FLOW_TYPE;
				return ERROR;
			}
			boolean isRight = rightToDocument(irpDocument);
			if (!isRight) {
				friendlyshow = IrpDocument.DOCUMENT_NO_RIGHT;
				return ERROR;
			}
		}
		
		//获得当前位置
		rootChannels = irpChannelService.currentChannels(irpDocument.getChannelid(), rootChannels, 0L);
		
		if (irpDocument.getInformtype() > 0
				&& !LoginUtil.getLoginUser().isDocumentManager()) {// 举报状态并且不是知识管理员
			friendlyshow = IrpDocument.DOCUMENT_IS_INFORM_TYPE;
			return ERROR;
		}
		// **调用分割文章方法，进行对文章进行分页*/
		
	    IrpUserPrivacy filterwords = this.irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(), IrpUserPrivacy.BOOLDOCWORDHOT);
		if (filterwords!=null && filterwords.getPrivacyvalue()==1) {
			irpDocument.setDochtmlcon(new TermFilterDoc().returnDisposeStr(pageDocument(irpDocument.getDochtmlcon())));
		}else{
			irpDocument.setDochtmlcon(pageDocument(irpDocument.getDochtmlcon()));
		}
		
		if(checkDocHits(this.docid)){
			//知识点击
			irpDocumentService.addHitScount(this.docid);
			//用户积分
			List<IrpValueConfig> list = irpValueConfigService.findValueConfigByMethodName("showdocumentinfo");
			long nScoreUserId=0L;
			for (IrpValueConfig irpValueConfig : list) {
				if (irpValueConfig.getBeandao() != null	&& irpValueConfig.getBeanidname() != null) {
					//给知识本身添加积分
					irpDocumentService.updateDocscoreByPrimaryKey(this.docid, irpValueConfig.getScore());
					nScoreUserId = irpDocument.getCruserid();
				}else{
					nScoreUserId = irpUser.getUserid();
				}
				irpUserService.updateUserSumScoreEx(
						nScoreUserId, irpValueConfig.getScore(),
						irpValueConfig.getExperience());
				addUserScore(nScoreUserId, irpValueConfig.getName(),
						irpValueConfig.getScore(),
						irpValueConfig.getExperience());
			}
			//知识阅读记录
			irpDocumentLogsService.editDocumentLogs(this.docid);
		}
		
		// 查询所在栏目
		irpChannel = irpChannelService.finChannelByChannelid(id);// 查询新建文件文档所在的栏目名称
		// 同时查看文档的所有附件
		attacheds = irpAttachedService.getAttachedByAttDocId(docid, 0);
		// 查看这个知识的加精对象他是否有加精过
		docClassicl = irpInformService.findInformByExpert(IrpInform.INFORMJIAJINGDOC, this.docid, irpUser.getUserid());
		
		/** 修改，删除权限 */

		if (irpUser.isAdministrator()) {
			isDelete = true + "";
			isUpdate = true + "";
			isPriant = true + "";
			isFuJian = true + "";
			isHistoryversion = true + "";
			isXiangGuan = true + "";
			isCrUser = true + "";
		} else {
			channle.setChannelid(irpDocument.getChannelid());
			if (nOPIdd != null && nOPIdd > 0L) {

				isPriant = rightUtil.right(channle, nOPIdd)+"";
			}
			if (nOPIdf != null && nOPIdf > 0L) {
				isFuJian = rightUtil.right(channle, nOPIdf)+"";
			}
			if (nOPIdl != null && nOPIdl > 0L) {
				isHistoryversion = rightUtil.right(channle, nOPIdl)+"";
			}
			if (nOPIdx != null && nOPIdx > 0L) {
				isXiangGuan = rightUtil.right(channle, nOPIdx)+"";
			}
			try {
				Long nOPIdup = rightUtil.findOperTypeIdByKey("DOCUMENT_UPDATE");
				if (irpUser.getUserid().toString()
						.equals(irpDocument.getCruserid().toString())) {// 创建者
					if (nOPIdup != null && nOPIdup > 0L) {// 有权限
						isUpdate = rightUtil.right(channle, nOPIdup) + "";
					}
				} else {
					isUpdate = false + "";
				}
			} catch (Exception e) {
				isUpdate = false + "";
			}
			try {
				Long nOPId = rightUtil
						.findOperTypeIdByKey("DOCUMENT_DOCSTATUS_UPDATE");
				if (irpUser.getUserid().toString()
						.equals(irpDocument.getCruserid().toString())) {// 创建者
					if (nOPId != null && nOPId > 0L) {
						isDelete = rightUtil.right(channle, nOPId) + "";
					}
				} else {
					isDelete = "false";
				}
			} catch (Exception e) {
				isDelete = "false";
			}
			isCrUser = checkCrUser + "";
		}
		loginUserId = LoginUtil.getLoginUserId();
		maxAmount = SysConfigUtil.getSysConfigValue("MAX_AMOUNT_TO_COMMENTDED");
		IrpDocumentScore documentScore = irpDocumentScoreService.findPersonScore(docid, loginUserId);
		if (documentScore != null) {
			isScore = true + "";
		} else {
			isScore = false + "";
		}
		//查看阅读记录
		//List<IrpSubscribe> arrSubscribes = irpSubscribeService.findSubscribeByBaseObj(irpDocument);
		List<IrpSubscribe> arrSubscribes = irpSubscribeService.findSubscribeByBaseObjLogin(irpDocument);
		subscribe=arrSubscribes!=null && arrSubscribes.size()>0?1:0;
		if(arrSubscribes!=null&&arrSubscribes.size()>0){
			// 修改订阅阅读状态
			IrpSubscribe subscribe=arrSubscribes.get(0);
			subscribe.setSubReadStatus(null);
			irpSubscribeService.updateByPrimary(subscribe);
		}
		return SUCCESS;
	}
	
	/**
	 * 增加具体积分
	 * 
	 * @param _userid
	 * @param _valueckey
	 */
	private void addUserScore(long _userid, String _valueckey, long _score, long _experience) {
		IrpUserValueLink irpUserValueLink = new IrpUserValueLink();
		// 执行新增积分
		irpUserValueLink.setUserid(_userid);
		irpUserValueLink.setValueckey(_valueckey);
		irpUserValueLink.setScore(_score);
		irpUserValueLink.setExperience(_experience);
		irpUserValueLinkService.addIrpUserValueLink(irpUserValueLink);
	}
	
	private boolean checkDocHits(Long _nDocId) {
		boolean isHits = false;
		if(_nDocId==null || _nDocId==0L)
			return isHits;
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session==null)
			return isHits;
		String sessionDocId = "docid_"+_nDocId;
		Object obj = session.getAttribute(sessionDocId);
		if(obj!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(((Calendar) obj).getTimeInMillis());
			calendar.add(Calendar.SECOND, 10);
			if(calendar.compareTo(Calendar.getInstance())==-1){
				isHits = true;
			}
		}else{
			isHits = true;
		}
		//如果可以点击从新计算时间
		if(isHits){
			session.removeAttribute(sessionDocId);
			session.setAttribute(sessionDocId, Calendar.getInstance());
		}
		return isHits;
	}
	
	/**
	 * copy  search history version
	 * @return
	 */
	public String clientShowDocumentInfoVersion(){
		IrpUser irpUser = LoginUtil.getLoginUser();
		if (docid == null || docid == 0L) {
			friendlyshow = IrpDocument.DOCUMENT_IS_ERROR;
			return ERROR;
		}
		
		IrpDocversionWithBLOBs irpdocversion =  this.irpDocversionService.irpDocversion(docid);
		if(irpdocversion==null){
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;
		}
		irpDocument = new IrpDocumentWithBLOBs();
		irpDocument.setDocid(irpdocversion.getDocid());
		irpDocument.setChannelid(irpdocversion.getChannelid());
		irpDocument.setSiteid(irpdocversion.getSiteid());
		irpDocument.setDoctype(irpdocversion.getDoctype());
		irpDocument.setDoctitle(irpdocversion.getDoctitle());
		irpDocument.setDocstatus(irpdocversion.getDocstatus());
		irpDocument.setDoccontent(irpdocversion.getDoccontent().toString());
		irpDocument.setDochtmlcon(irpdocversion.getDochtmlcon().toString());
		irpDocument.setDocabstract(irpdocversion.getDocabstract());
		irpDocument.setDockeywords(irpdocversion.getDockeywords());
		irpDocument.setDocoutupid(irpdocversion.getDocoutupid());
		irpDocument.setDocpubtime(irpdocversion.getDocpubtime());
		irpDocument.setCruser(irpdocversion.getCruser());
		irpDocument.setCruserid(irpdocversion.getCruserid());
		irpDocument.setCrtime(irpdocversion.getCrtime());
		irpDocument.setKnowledgeid(irpdocversion.getKnowledgeid());
		irpDocument.setHitscount(irpdocversion.getHitscount());
		irpDocument.setReplyflag(irpdocversion.getReplyflag());
		irpDocument.setReplycount(irpdocversion.getReplycount());
		irpDocument.setAttachedcontent(irpdocversion.getAttachedcontent().toString());
		irpDocument.setDocscore(irpdocversion.getDocscore());
		irpDocument.setInformtype(irpdocversion.getInformtype());
		irpDocument.setDocversion(irpdocversion.getDocversion());
		
		IrpSite site = irpSiteService.siteInfo(irpDocument.getSiteid());
		if (site == null) {
			friendlyshow = IrpSite.SITE_NOT_SAVE;
			return ERROR;
		}
		if (site.getSitetype() == IrpSite.SITE_TYPE_PRIVATE) {// 如果是企业知识就重定向到企业知识去
			return INPUT;
		}
		if (irpUser.getUserid().longValue() != irpDocument.getCruserid().longValue()) {
			if (irpDocument.getChannelid() < 0
					|| irpDocument.getDocstatus() < 0) {
				friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
				return ERROR;
			}
			// 查看知识状态是否等于发布状态
			if (!IrpDocument.PUBLISH_STATUS.toString().equals(
					irpDocument.getDocstatus().toString())) {
				friendlyshow = IrpDocument.DOCUMENT_IS_FLOW_TYPE;
				return ERROR;
			}
			boolean isRight = rightToDocument(irpDocument);
			if (!isRight) {
				friendlyshow = IrpDocument.DOCUMENT_NO_RIGHT;
				return ERROR;
			}
		}
		
		//获得当前位置
		rootChannels = irpChannelService.currentChannels(irpDocument.getChannelid(), rootChannels, 0L);
		
		// **调用分割文章方法，进行对文章进行分页*/
	    IrpUserPrivacy filterwords = this.irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(), IrpUserPrivacy.BOOLDOCWORDHOT);
		if (filterwords!=null && filterwords.getPrivacyvalue()==1) {
			irpDocument.setDochtmlcon(new TermFilterDoc().returnDisposeStr(pageDocument(irpDocument.getDochtmlcon())));
		}else{
			irpDocument.setDochtmlcon(pageDocument(irpDocument.getDochtmlcon()));
		}
		return SUCCESS;
	}
	
	
	/**
	 * 链接到查看历史版本页面
	 * @return
	 */
	public String linkDocVersionHistory(){
		this.loginuserprivacy = irpUserPrivacy();
		int num =  this.irpDocversionService.getAddDataByDocidNum(docid);
		PageUtil pageutil = new PageUtil(pageNum, pageSize, num);
		docversionlist = this.irpDocversionService.getAddDataByDocid(docid, pageutil);
		this.pageHTML = pageutil.getClientPageHtml("page(#PageNum#)"); 
		return SUCCESS;
	}
	
	private long docidh;
	private int docversionh;
	private IrpDocversion irpDocversionh;
	private long docoutupidh;

	public long getDocidh() {
		return docidh;
	}

	public void setDocidh(long docidh) {
		this.docidh = docidh;
	}

	public int getDocversionh() {
		return docversionh;
	}

	public void setDocversionh(int docversionh) {
		this.docversionh = docversionh;
	}

	public IrpDocversion getIrpDocversionh() {
		return irpDocversionh;
	}

	public void setIrpDocversionh(IrpDocversion irpDocversionh) {
		this.irpDocversionh = irpDocversionh;
	}

	public long getDocoutupidh() {
		return docoutupidh;
	}

	public void setDocoutupidh(long docoutupidh) {
		this.docoutupidh = docoutupidh;
	}
	private Long docvid;

	public Long getDocvid() {
		return docvid;
	}

	public void setDocvid(Long docvid) {
		this.docvid = docvid;
	}

	/**
	 * 链接到恢复历史版本页面
	 * @return
	 */
	public void recoverhistory(){
		IrpDocumentWithBLOBs irpdocument = this.irpDocumentService.findDocumentByDocId(docidh);
		
		int version = this.irpDocversionService.getVersionidByDocid(docidh);
		
	    long  statu = this.irpDocversionService.addDocVersion(irpdocument,version);
		
		IrpDocversionWithBLOBs irpDocversionh = this.irpDocversionService.selectDocversion(docvid);
		
		int msg = irpDocumentService.updateDocument(docidh,irpDocversionh);
		
		ActionUtil.writer(statu+1+"");
	}
	
	
	/**
	 * 判断是否显示删除
	 * mobile
	 * @return
	 */
	private long channelidmobile;
	private long cruseridmobile;
	public long getChannelidmobile() {
		return channelidmobile;
	}
	public void setChannelidmobile(long channelidmobile) {
		this.channelidmobile = channelidmobile;
	}
	public long getCruseridmobile() {
		return cruseridmobile;
	}
	public void setCruseridmobile(long cruseridmobile) {
		this.cruseridmobile = cruseridmobile;
	}

	public void delViewKnowOfMobile(){
		/** 修改，删除权限 */
		IrpUser irpUser = LoginUtil.getLoginUser();
		
		if (irpUser.isAdministrator()) {
			isDelete = true + "";
			isUpdate = true + "";
		} else {
			RightUtil rightUtil = new RightUtil();
			IrpChannel channle = new IrpChannel();
			channle.setChannelid(channelidmobile);

			try {
				Long nOPIdup = rightUtil.findOperTypeIdByKey("DOCUMENT_UPDATE");
				if (irpUser.getUserid().toString()
						.equals(cruseridmobile)) {// 创建者
					if (nOPIdup != null && nOPIdup > 0L) {// 有权限
						isUpdate = rightUtil.right(channle, nOPIdup) + "";
					}
				} else {
					isUpdate = false + "";
				}
			} catch (Exception e) {
				isUpdate = false + "";
			}
			try {
				Long nOPId = rightUtil
						.findOperTypeIdByKey("DOCUMENT_DOCSTATUS_UPDATE");
				if (irpUser.getUserid().toString()
						.equals(cruseridmobile)) {// 创建者
					if (nOPId != null && nOPId > 0L) {
						isDelete = rightUtil.right(channle, nOPId) + "";
					}
				} else {
					isDelete = "false";
				}
			} catch (Exception e) {
				isDelete = "false";
			}
		}
		
		String sendstr = "[{'isDelete':'"+isDelete+"','isUpdate':'"+isUpdate+"'}]";
		ActionUtil.writer(sendstr);
		
		
	}
	/**
	 * 获得文档细览
	 * @param mobile
	 * @return
	 */
	private long mobiledocid;
	public long getMobiledocid() {
		return mobiledocid;
	}
	public void setMobiledocid(long mobiledocid) {
		this.mobiledocid = mobiledocid;
	}
	public void getShowDocumentByMobile(){
		IrpDocument mdocument = irpDocumentService.findDocumentByDocId(mobiledocid);
		String mdjson = JsonUtil.bean2json(mdocument);
		ActionUtil.writer(mdjson);
	}
	/**
	 * 获得文章标题内容
	 * @return
	 */
	public void getShowDocTitleByMobile(){
		IrpDocument mdocument =  irpDocumentService.findDocumentByDocId(mobiledocid);
		ActionUtil.writer(mdocument.getDoctitle());
	}
	// 查询知识的得分
	public String findDocumentScore() {
		if (docid == null || docid == 0L)
			return ERROR;
		/*IrpUser irpUser = LoginUtil.getLoginUser();
		// 查看这个人给的评分对象
		IrpDocumentScore documentScore = irpDocumentScoreService
				.findPersonScore(docid, irpUser.getUserid());
		if (documentScore != null) {
			isScore = true + "";
		} else {
			isScore = false + "";
		}*/
		// 查询知识的平均分
		HashMap<String, Object> map = irpDocumentScoreService
				.findAvgByDocid(docid);
		if (map.get("AVGSCORE") != null && map.get("SUMPERSON") != null) {
			avgScore = (int) (Math.round(Float.parseFloat(map.get("AVGSCORE")
					.toString())));
			sumperson = Integer.parseInt(map.get("SUMPERSON").toString());
		}
		return SUCCESS;
	}
	// 查询用户知识的评分
	public String findUserScore() {
		if (docid == null || docid == 0L)
			return ERROR;
		IrpUser irpUser = LoginUtil.getLoginUser();
		// 查看这个人给的评分对象
		IrpDocumentScore documentScore = irpDocumentScoreService
				.findPersonScore(docid, irpUser.getUserid());
		if (documentScore != null) {
			isScore = true + "";
			avgScore=Integer.parseInt(documentScore.getScore()+"");
		} else {
			isScore = false + "";
		}
		
		return SUCCESS;
	}
	// 得到这个文件的图标显示
	public String findTuBiao(Long attTypeID) {
		String imageName = ServletActionContext.getRequest().getContextPath()
				+ "/client/images/";
		if (IrpAttachedType.JPG_FIELD_NAME.toString().equals(
				attTypeID.toString())) {// 图片
			imageName += "xl_r2_c26.gif";
		}
		if (IrpAttachedType.MENU_FIELD_NAME.toString().equals(
				attTypeID.toString())) {// 视频类型
			imageName += "yinpin.jpg";
		}
		if (IrpAttachedType.MICUS_FIELD_NAME.toString().equals(
				attTypeID.toString())) {// 视频类型
			imageName += "yinpin1.jpg";
		}
		if (IrpAttachedType.ID_FIELD_NAMEOTHER.toString().equals(
				attTypeID.toString())) {// 未知类型
			imageName += "weizhi.jpg";
		}
		if (IrpAttachedType.WORD_FIELD_NAME.toString().equals(
				attTypeID.toString())) {// 文档类型
			imageName += "xl_r8_c5.gif";
		}
		return imageName;
	}

	// 查询某一个栏目的名称显示
	public String findChnlName(Long _channelid) {
		String chnlName = "";
		chnlName = irpChannelService.findChannelNameByChannelid(_channelid);
		return chnlName;
	}
	
	// 前台删除文档
	public void clientDeleteDocument() {
		int nCount = irpDocumentService.clientDeleteDocument(docid);
		if(nCount==1){
			//删除map中对应的关系
			IrpDocumentMapExample example = new IrpDocumentMapExample();
			example.createCriteria().andDocidEqualTo(docid);
			irpDocumentMapService.deleteDocSubByExample(example);
		}
		ActionUtil.writer(String.valueOf(nCount));
	}

	// 前台，修改文档
	public void clientUpdateDocument() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("subjectselect");
		
		Long nDocStatus = irpDocument.getDocstatus();
		if (nDocStatus == IrpDocument.DRAFT_STATUS) {// 说明点击的是草稿按钮
			channelids = null;
			irpDocument.setDocstatus(IrpDocument.DRAFT_STATUS);
		} else {// =="2"//说明点击的是保存按钮
			irpDocument.setDocstatus(null);
		}
		boolean isClient = true;
		int nCount = irpDocumentService.clientUpdateDocument(irpDocument,
				jsonFile, isClient, channelids, documentmaps,ids,flag);
		//更新专题的引用
		
		ActionUtil.writer(String.valueOf(nCount));

	}

	//
	public void addSubject() {
		/**
		 * 提交案例里面的channelid是 投稿的id 他里面没有channelids 将值交换一下位置
		 */
		Long channelid = irpDocument.getChannelid();
		channelids = channelid;
		// 查看 他有没有跟栏目
		irpDocument.setChannelid(null);
		IrpChannel rChannel = irpChannelService.findChannelByPerson(LoginUtil
				.getLoginUserId());
		if (rChannel == null) {// 没有跟栏目就添加
			IrpSite site = null;
			List<IrpSite> sites = irpSiteService
					.findSitesBySiteType(IrpSite.SITE_TYPE_PRIVATE);
			if (sites != null && sites.size() > 0) {
				site = sites.get(0);
				IrpUser irpUser = irpUserService.findUserByUserId(LoginUtil
						.getLoginUserId());
				IrpChannel channel = new IrpChannel();
				channel.setChnlname(irpUser.getUserid().toString());
				channel.setChnldesc(irpUser.getUsername());
				channel.setSiteid(site.getSiteid());
				channel.setStatus(1);
				channel.setParentid(0L);
				channel.setChnltype(IrpChannel.CHANNEL_TYPE_PRIVATE);
				irpChannelService.addChannel(channel, irpUser);
			}
		}
		clientAddDocument();
	}

	// 前台,添加文档
	public void clientAddDocument() {
		int nCount = 0;
		Long nDocStatus = irpDocument.getDocstatus();
		if (nDocStatus == IrpDocument.DRAFT_STATUS) {// 说明点击的是草稿按钮
			// 删除掉他的投稿栏目id
			channelids = null;
			this.isTrue = 0L;// 保存草稿不发微知
			irpDocument.setDocstatus(IrpDocument.DRAFT_STATUS);
		} else {// =="2"//说明点击的是保存按钮
			irpDocument.setDocstatus(null);
		}
		boolean isClient = true;

		if (irpDocument.getDoctype() == null) {
			irpDocument.setDoctype(IrpDocument.DOCTYPE_DOCUMENT);
		}
		// 查看是否有根栏目，若没有就添加一个
		if (irpDocument.getChannelid() == null) {
			// 查看 他有没有跟栏目
			IrpChannel rChannel = irpChannelService
					.findChannelByPerson(LoginUtil.getLoginUserId());
			if (rChannel == null) {// 没有跟栏目就添加
				IrpSite site = null;
				List<IrpSite> sites = irpSiteService
						.findSitesBySiteType(IrpSite.SITE_TYPE_PRIVATE);
				if (sites != null && sites.size() > 0) {
					site = sites.get(0);
					IrpUser irpUser = irpUserService.findUserByUserId(LoginUtil
							.getLoginUserId());
					IrpChannel channel = new IrpChannel();
					channel.setChnlname(irpUser.getUserid().toString());
					channel.setChnldesc(irpUser.getUsername());
					channel.setSiteid(site.getSiteid());
					channel.setStatus(1);
					channel.setParentid(0L);
					channel.setChnltype(IrpChannel.CHANNEL_TYPE_PRIVATE);
					irpChannelService.addChannel(channel, irpUser);
				}
			}
			IrpChannel rootChannel = irpChannelService
					.findChannelByPerson(LoginUtil.getLoginUserId());
			irpDocument.setChannelid(rootChannel.getChannelid());
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("subjectselect");
		nCount = irpDocumentService.clientAddDocument(irpDocument, jsonFile,
				channelids, isTrue, isClient, documentmaps,ids,flag);
		ActionUtil.writer(String.valueOf(nCount));
	}

	// 前台 去添加文档页面

	public String clientToAddDocument() {
		// 查询他自己的所有栏目
		List<IrpChannel> list = new ArrayList<IrpChannel>();
		// 查询公开的知识分类法
		documentMap = irpChannelService.allDocumentMap(IrpChannel.IS_DOCSTATIUS);
		if (questionid != null) {
			irpQuestionofquestionid = irpQuestionService.findQuestionDetail(questionid);
			irpQuestionofparentid = irpQuestionService.findQuestionByParentid(questionid);
			irpAttachedarraylist = JsonUtil.list2json(
					this.irpAttachedService.getPortionAttached(questionid,IrpAttached.QUESTION_DOCIDTYPE)).replace("\"","&amp;");
			doctype = IrpDocument.DOCTYPE_QUESTION;
		} else {
			doctype = IrpDocument.DOCTYPE_DOCUMENT;
		}
		//获得用户所属的组织
		userGroups = irpGroupService.findGroupIdsByUserId(LoginUtil.getLoginUserId());
		return SUCCESS;
	}
	
	public String initAllSelfSubject(){
		//查询他自己的所有专题
		IrpChannelExample example = new IrpChannelExample();
		Criteria criteria = example.createCriteria();
		criteria.andCruseridEqualTo(LoginUtil.getLoginUserId());
		criteria.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
		criteria.andParentidEqualTo(Long.valueOf(SysConfigUtil.getSysConfigValue("DOCUMENT_PERSON_SUBJECT_ID")));
		List<IrpChannel> channellist = irpChannelService.findChannelByExample(example);
		IrpChannel selfChannel = null;
		if(channellist!=null && channellist.size()>0){
			selfChannel = channellist.get(0);
			example =new IrpChannelExample();
			Criteria newcriteria = example.createCriteria();
			newcriteria.andParentidEqualTo(selfChannel.getChannelid());
			newcriteria.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
			example.setOrderByClause("channelid desc");
			irpChannels = irpChannelService.findChannelByExample(example);
			if(docid!=null && docid.intValue()!=0){
				irpChannels = checkifhad(docid,irpChannels);
			}
		}
		return SUCCESS;
	}

	public String toChackChannelToShowDocument() {
		return SUCCESS;
	}

	public void findAdminRightChannel() {
		if (id == -1L)
			id = 0L;
		// 根据所给的id查询
		// 一级栏目按照站点
		List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Integer> chnlTypes=new ArrayList<Integer>();
		if (this.id == null) {
			map.put("parentid", 0L);
		} else {
			map.put("parentid", this.id);
		}
		map.put("status", IrpChannel.IS_DOCSTATIUS);
		map.put("siteid",IrpSite.PUBLIC_SITE_ID);
		List<IrpChannel> chanls = irpChannelService.siteAllChannel(null,map,chnlTypes);
		if (chanls != null) {
			for (int i = 0; i < chanls.size(); i++) {
				IrpChannel chan = (IrpChannel) chanls.get(i);
				if (chan == null) {
					continue;
				}
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("id", chan.getChannelid());// 里面放的应该是siteid的值将它和parentid比较即当做他的父id
				item.put("text", chan.getChnldesc());
				// 根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open
				int nCount = irpChannelService
						.findChild_Channl_CountByParentId(chan.getChannelid());
				if (nCount > 0) {
					item.put("state", "closed");
				} else {
					item.put("state", "open");
				}
				treeNode.add(item);
			}
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}

	// 获取项目管理的id
	public static int findProjectId() {
		int parentId = SysConfigUtil.getSysConfigNumValue("PROJECT_GUAN_LI");
		return parentId;
	}

	/**
	 * 查询项目管理的栏目tree用于项目使用
	 */
	public void findProjectGuanliTree() {
		List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
		if (id == null || id == -1) {
			Map<String, Object> item = new HashMap<String, Object>();
			int parentId = SysConfigUtil
					.getSysConfigNumValue("PROJECT_GUAN_LI");
			if (parentId > 0) {
				IrpChannel rootChannel = irpChannelService
						.finChannelByChannelid(new Long(parentId));
				if (rootChannel != null) {
					String rootName = rootChannel.getChnldesc();
					item.put("id", rootChannel.getChannelid());// 即将作为下一节点的parentid
					item.put("text", rootName);
					int rootCount = irpChannelService
							.findChild_Channl_CountByParentId(new Long(parentId));
					if (rootCount > 0) {
						item.put("state", "closed");
					} else {
						item.put("state", "open");
					}
					treeNode.add(item);
				}
			}
		} else {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("parentid", this.id);
			map.put("status", 1);// 查询没有进入栏目回收站的栏目
			List<Integer> chnlTypes = new ArrayList<Integer>();
			chnlTypes.add(IrpChannel.CHANNEL_TYPE_MAP);
			List<IrpChannel> chanls = irpChannelService.siteAllChannel(null,
					map, chnlTypes);
			if (chanls != null) {
				for (int i = 0; i < chanls.size(); i++) {
					IrpChannel chan = (IrpChannel) chanls.get(i);
					if (chan == null) {
						continue;
					}
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", chan.getChannelid());// 里面放的应该是siteid的值将它和parentid比较即当做他的父id
					item.put("text", chan.getChnldesc());
					// 根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open
					int nCount = irpChannelService
							.findChild_Channl_CountByParentId(chan
									.getChannelid());
					if (nCount > 0) {
						item.put("state", "closed");
					} else {
						item.put("state", "open");
					}
					treeNode.add(item);
				}
			}
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}

	public void findClientRigthChannelMore() {
		if (id == -1L)
			id = 0L;
		// 根据所给的id查询
		// 一级栏目按照站点
		List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (this.id == null) {
			map.put("parentid", new Long(0));
			map.put("status", 1);
		} else {
			map.put("parentid", this.id);
			map.put("status", 1);
		}
		List<IrpChannel> chanls = irpChannelService
				.JsonRightAllChannelMore(map);
		if (chanls != null) {
			for (int i = 0; i < chanls.size(); i++) {
				IrpChannel chan = (IrpChannel) chanls.get(i);
				if (chan == null) {
					continue;
				}
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("id", chan.getChannelid());// 里面放的应该是siteid的值将它和parentid比较即当做他的父id
				item.put("text", chan.getChnldesc());
				// 根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open(该方法尽在这个地方使用)
				int nCount = irpChannelService
						.findChild_Channl_CountByParentId(chan.getChannelid(),
								IrpChannel.CHANNEL_IS_PUBLISH);
				if (nCount > 0) {
					item.put("state", "closed");
				} else {
					item.put("state", "open");
				}
				treeNode.add(item);
			}
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));

	}

	// 前台获取有权限的栏目，动态生成tree显示
	public void findClientRigthChannel() {
		if (id == -1L)
			id = 0L;
		// 根据所给的id查询
		// 一级栏目按照站点
		List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (this.id == null) {
			map.put("parentid", new Long(0));
			map.put("status", 1);
		} else {
			map.put("parentid", this.id);
			map.put("status", 1);
		}
		/**
		 * 给出所限制能够查询的栏目类型为企业栏目类型
		 */
		List<Integer> chnlTypes = new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		List<IrpChannel> chanls = irpChannelService.clientRightChannel(map,
				chnlTypes);
		if (chanls != null) {
			for (int i = 0; i < chanls.size(); i++) {
				IrpChannel chan = (IrpChannel) chanls.get(i);
				if (chan == null) {
					continue;
				}
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("id", chan.getChannelid());// 里面放的应该是siteid的值将它和parentid比较即当做他的父id
				item.put("text", chan.getChnldesc());
				// 根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open(该方法尽在这个地方使用)
				int nCount = irpChannelService
						.findChild_Channl_CountByParentId(chan.getChannelid(),
								IrpChannel.CHANNEL_IS_PUBLISH);
				if (nCount > 0) {
					item.put("state", "closed");
				} else {
					item.put("state", "open");
				}
				treeNode.add(item);
			}
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));

	}

	// 前台 查看文档的详细信息修改用
	public String clientDocumentIfo() {
		loginUserId = LoginUtil.getLoginUserId();
		// 判断他是否有权限
		if (this.docid == null) {
			friendlyshow = IrpDocument.DOCUMENT_IS_ERROR;
			return ERROR;
		}
		irpDocument = irpDocumentService.findDocumentByDocId(this.docid);// 当前需要修改的文档信息
		if (irpDocument == null) {
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;
		}
		if (loginUserId.longValue() != irpDocument.getCruserid().longValue()) {
			if (irpDocument.getChannelid() < 0
					|| irpDocument.getDocstatus() < 0) {
				friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
				return ERROR;
			}
		}
		IrpUser irpUser = LoginUtil.getLoginUser();
		IrpSite site = irpSiteService.siteInfo(irpDocument.getSiteid());
		if (site == null) {
			friendlyshow = IrpSite.SITE_NOT_SAVE;
			return ERROR;
		}
		if (site.getSitetype() == IrpSite.SITE_TYPE_PRIVATE) {// 个人站点
			if (!irpUser.getUserid().toString()
					.equals(irpDocument.getCruserid().toString())) {
				friendlyshow = IrpDocument.DOCUMENT_NO_UPDATE_RIGHT;
				return ERROR;
			}
		} else if (site.getSitetype() == IrpSite.SITE_TYPE_PUBLISH) {// 如果该知识是企业知识库的，就不能走这个修改l
			friendlyshow = IrpDocument.DOCUMENT_IS_ERROR;
			return ERROR;
		}
		documentMapList = irpDocumentService.docDocumentMap(this.docid,IrpDocumentMap.KONWLEDGE_MAP);
		documentMap = irpChannelService.allDocumentMap(IrpChannel.IS_DOCSTATIUS);
		//获得用户所属的组织
		userGroups = irpGroupService.findGroupIdsByUserId(loginUserId);
		return SUCCESS;
	}

	// 判断当前查看的这篇知识是企业的还是个人的，根据所属的站点类型是企业站点还是个人站点
	public String checkDocumentFrom(Long _siteid) {
		String returnStr = "";//
		IrpSite site = irpSiteService.siteInfo(_siteid);
		if (site != null) {
			Integer _siteType = site.getSitetype();
			if (IrpSite.SITE_TYPE_PUBLISH == _siteType) {
				if (!loginUserId.toString().equals(
						irpDocument.getCruserid().toString())) {// 不是登陆者
					if (irpDocument.getDocstatus().longValue() != IrpDocument.PUBLISH_STATUS
							.longValue()) {
						friendlyshow = irpDocument.DOCUMENT_IS_FLOW_TYPE;
						return ERROR;
					}
					Boolean isRight = rightToDocument(irpDocument);
					if (isRight) {
						irpDocument = irpDocumentService
								.updateHitScountJia(irpDocument);// 添加点击量
					} else {
						friendlyshow = IrpDocument.DOCUMENT_NO_RIGHT;
					}
				}
				returnStr = INPUT;// 企业站点返回值
			} else {// 个人站点返回值
				if (loginUserId.longValue() != irpDocument.getCruserid()
						.longValue()) {// 不是登陆者
					if (irpDocument.getDocstatus().longValue() != IrpDocument.PUBLISH_STATUS
							.longValue()) {
						friendlyshow = irpDocument.DOCUMENT_IS_FLOW_TYPE;
						return ERROR;
					}
					irpDocument = irpDocumentService
							.updateHitScountJia(irpDocument);// 添加点击量
				}
				returnStr = SUCCESS;
			}
		}
		return returnStr;
	}

	// 查看文档的详细信息(document_view)
	public String clientDocumentIfoView() {
		loginUserId = LoginUtil.getLoginUserId();
		if (docid == null || docid == 0L) {
			friendlyshow = IrpDocument.DOCUMENT_IS_ERROR;
			return ERROR;
		}
		irpDocument = irpDocumentService.findDocumentByDocId(this.docid);
		if (irpDocument == null) {
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;
		}

		if (loginUserId.longValue() != irpDocument.getCruserid().longValue()) {
			if (irpDocument.getChannelid() < 0
					|| irpDocument.getDocstatus() < 0) {
				friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
				return ERROR;
			}
		}
		if (irpDocument.getDocstatus() <= 0) {
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;// 状态小于0的视为删除状态
		}
		if (irpDocument.getInformtype() > 0
				&& !LoginUtil.getLoginUser().isDocumentManager()) {// 举报状态并且不是知识管理员
			friendlyshow = IrpDocument.DOCUMENT_IS_INFORM_TYPE;
			return ERROR;
		}
		IrpSite site = irpSiteService.siteInfo(irpDocument.getSiteid());
		if (site == null) {
			friendlyshow = IrpSite.SITE_NOT_SAVE;
			return ERROR;
		}
		if (site.getSitetype() == IrpSite.SITE_TYPE_PUBLISH) {// 如果是企业知识就重定向到企业知识去
			return INPUT;
		}
		// **调用分割文章方法，进行对文章进行分页*/
		irpDocument.setDochtmlcon(pageDocument(irpDocument.getDochtmlcon()));
		// 判断当前登录用户是否等于创建者，如果不等于，吧点击量加1
		if (irpDocument != null && refrechstatus != null && refrechstatus == 1) {
			return checkDocumentFrom(irpDocument.getSiteid());
		}
		/** 判断这个人是不是阅读过文章，没有阅读就添加一条记录，阅读过不执行任何操作* */
		List<IrpDocumentReaded> readedList = irpDocumentReadedService.findData(
				irpDocument.getDocid(), loginUserId);
		if (readedList != null && readedList.size() > 0) {
		} else {// /添加一条阅读记录
			irpDocumentReadedService.addData(irpDocument.getDocid(),
					loginUserId);
		}
		attacheds = irpAttachedService.getAttachedByAttDocId(docid, 0);

		irpChannel = irpChannelService.finChannelByChannelid(id);// 查询新建文件文档所在的栏目名称

		// 查询 封装文档的创建者信息
		IrpUser user = irpUserService.findUserByUserId(irpDocument
				.getCruserid());
		if (user != null) {
			irpDocument.setCreateUser(user);
			irpDocument.setUserPicUrl(user.getDefaultUserPic());
		}
		maxAmount = SysConfigUtil.getSysConfigValue("MAX_AMOUNT_TO_COMMENTDED");
		return SUCCESS;
	}

	public String adminPreviewDoc() {
		irpDocument = irpDocumentService.findDocumentByDocId(this.docid);
		return SUCCESS;
	}

	public String toUpdateSubject() {
		if (id == null || id == 0L)
			return ERROR;
		toUpdate = "true";
		irpDocument = irpDocumentService.findDocumentByDocId(this.docid);// 当前需要修改的文档信息
		if (irpDocument.getChannelid() < 0) {
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;
		}
		long nChannelId = irpDocument.getChannelid();

		// 获得当前文档栏目的全路径
		List<Long> channelidList = new ArrayList<Long>();
		channelidList = irpChannelService.allparentidList(nChannelId,
				channelidList, IrpChannel.IS_DOCSTATIUS,
				LoginUtil.getLoginUser());
		if (channelidList != null && channelidList.size() > 0) {
			for (Long long1 : channelidList) {
				if (this.allChannelIds == null
						|| this.allChannelIds.length() == 0) {
					this.allChannelIds = long1.toString();
				} else {
					this.allChannelIds += "," + long1.toString();
				}
			}
		}
		// 获得当前文档栏目信息
		this.chnldesc = irpChannelService
				.findChannelNameByChannelid(nChannelId);

		if (irpDocument == null) {
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;
		}
		IrpSite site = irpSiteService.siteInfo(irpDocument.getSiteid());
		if (site == null) {
			friendlyshow = IrpSite.SITE_NOT_SAVE;
			return ERROR;
		}
		if (site.getSitetype() == IrpSite.SITE_TYPE_PRIVATE) {
			friendlyshow = IrpDocument.DOCUMENT_IS_ERROR;
			return ERROR;
		}
		IrpUser irpUser = LoginUtil.getLoginUser();
		if (!irpUser.isAdministrator()) {
			RightUtil rightUtil = new RightUtil();
			IrpChannel channle = new IrpChannel();
			channle.setChannelid(irpDocument.getChannelid());
			try {
				Long nOPIdup = rightUtil.findOperTypeIdByKey("DOCUMENT_UPDATE");
				if (irpUser.getUserid().toString()
						.equals(irpDocument.getCruserid().toString())) {// 创建者
					if (nOPIdup != null && nOPIdup > 0L) {
					} else {
						friendlyshow = IrpDocument.DOCUMENT_NO_UPDATE_RIGHT;
						return ERROR;
					}
				} else {
					friendlyshow = IrpDocument.DOCUMENT_NO_UPDATE_RIGHT;
					return ERROR;
				}
			} catch (Exception e) {
				friendlyshow = IrpDocument.DOCUMENT_NO_UPDATE_RIGHT;
				e.printStackTrace();
				return ERROR;
			}
		}
		documentMapList = irpDocumentService.docDocumentMap(this.docid,IrpDocumentMap.KONWLEDGE_MAP);
		documentMap = irpChannelService
				.allDocumentMap(IrpChannel.IS_DOCSTATIUS);
		//获取企业专题
		initAllQiyeSubject();
		irpChannels = this.checkifhad(this.docid, irpChannels);
		return SUCCESS;

	}
	
	// 查询知识的加精数量
	public static int countByDocId(Long docid) {
		int nCount = 0;
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpInformService Service = (IrpInformService) ac
				.getBean("irpInformService");
		nCount = Service.findCountByExpert(IrpInform.INFORMJIAJINGDOC, docid);
		return nCount;
	}

	// 后台查看文档的详细信息
	public String selectDocumentInfo() {
		if (id == null || id == 0L)
			return ERROR;
		irpDocument = irpDocumentService.findDocumentByDocId(this.docid);// 当前需要修改的文档信息
		if (irpDocument.getChannelid() < 0) {
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;
		}
		// 查询所在栏目
		irpChannel = irpChannelService.finChannelByChannelid(id);// 查询新建文件文档所在的栏目名称
		return SUCCESS;
	}

	// 查看这个人是不是我关注的对象
	public static Long findFocusByUserid(Long _userID) {
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpMicroblogFocusService Service = (IrpMicroblogFocusService) ac
				.getBean("irpMicroblogFocusServiceImpl");
		List<IrpMicroblogFocus> list = Service.findFocusByUserId(_userID);
		if (list != null && list.size() > 0) {
			return list.get(0).getFocusid();
		}
		return 0L;
	}

	// 查看他的阅读量
	public static int countReadByDocid(Long _docid) {
		ApplicationContext ac = ApplicationContextHelper.getContext();
		DocumentReadedService Service = (DocumentReadedService) ac
				.getBean("irpDocumentReadedService");

		int nCount = Service.coungData(_docid);
		return nCount;
	}

	// 看一个人的所有文章的点击量（自己+投稿出去的）
	public static int countSumHit(Long _cruserid) {
		int intSum = 0;
		try {
			ApplicationContext ac = ApplicationContextHelper.getContext();
			IrpChannelService Service = (IrpChannelService) ac
					.getBean("irpChannelService");
			IrpChannel rootChannel = Service.findChannelByPerson(LoginUtil
					.getLoginUserId());
			if (rootChannel != null) {
				List<Long> arrChannelIds = new ArrayList<Long>();
				arrChannelIds = Service.findChannelIdsByParentId(
						rootChannel.getChannelid(), arrChannelIds,
						IrpSite.SITE_TYPE_PRIVATE, IrpChannel.IS_DOCSTATIUS,
						LoginUtil.getLoginUser());
				if (arrChannelIds != null && arrChannelIds.size() > 0) {
					IrpChnl_Doc_LinkService Service1 = (IrpChnl_Doc_LinkService) ac
							.getBean("irpChnl_Doc_LinkService");
					// intSum= Service1.sumHit(arrChannelIds);
					List<Long> _docids = Service1.docIds(arrChannelIds,
							IrpDocument.DOCUMENT_NOT_INFORM);
					IrpDocumentService Service2 = (IrpDocumentService) ac
							.getBean("irpDocumentService");
					if (_docids != null) {
						int TouSum = Service2.sumTouHit(_docids);
						intSum = intSum + TouSum;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intSum;
	}

	// 页面个关闭根据json数组删除所有文件
	public void deleteJsonFileList() {
		if (jsonFile != null && jsonFile.length() > 0) {
			JSONArray _Array = JSONArray.fromObject(jsonFile);
			for (int i = 0; i < _Array.size(); i++) {
				JSONObject jsonObject = (JSONObject) _Array.getJSONObject(i);
				String _sAttFile = jsonObject.getString("attfile");
				// 判断他是不是临时文件的不是临时文件就不删除
				String sFileType = _sAttFile.substring(0, 2);// 获得文件类型
				if (SysFileUtil.FILE_TYPE_TEMP_FILE.equals(sFileType)) {
					SysFileUtil.deleteFileByFileName(_sAttFile);
				}

			}
		}

	}

	// 根据名称删除文件
	public void deleteJsonFile() {// 页面删除jsonfile中某个文件的时候同时也需要删除服务器临时文件夹的文件
		// 判断他是不是临时文件的不是临时文件就不删除
		if (myFileName != null) {
			String sFileType = myFileName.substring(0, 2);// 获得文件类型
			if (SysFileUtil.FILE_TYPE_TEMP_FILE.equals(sFileType)) {
				SysFileUtil.deleteFileByFileName(myFileName);
			}
		}

	}

	// 查看某个文档的所有附件
	public void allAttachedToDocument() {
		attacheds = irpAttachedService.getAttachedByAttDocId(this.docid, 0);
		List list = new ArrayList();
		if (attacheds != null && attacheds.size() > 0) {
			for (int i = 0; i < attacheds.size(); i++) {
				IrpAttached attached = new IrpAttached();
				IrpAttached att = attacheds.get(i);
				attached.setAttachedid(att.getAttachedid());
				attached.setAttdesc(att.getAttdesc());
				attached.setEditversions(att.getEditversions());
				attached.setAttlinkalt(att.getAttlinkalt());
				list.add(attached);
			}
		}
		ActionUtil.writer(JsonUtil.list2json(attacheds));
	}

	/**
	 * if(attacheds!=null && attacheds.size()>0){ for (int i = 0; i <
	 * attacheds.size(); i++) { IrpAttached attached=new IrpAttached();
	 * IrpAttached att=attacheds.get(i); buffer.append("{");
	 * buffer.append("'attachedid':"+att.getAttachedid());
	 * buffer.append(",'attdesc':'"+att.getAttdesc()+"'");
	 * buffer.append(",'editversions':'"+att.getEditversions()+"'");
	 * buffer.append(",'attlinkalt':'"+att.getAttlinkalt()+"'");
	 * buffer.append("},"); }
	 * ActionUtil.writer("["+buffer.substring(0,buffer.length()-1)+"]");
	 * //JsonUtil.list2json(list) }
	 */
	// 列表显示所有的文件提供下载功能
	public String selectAttachedToDocuemnt() {
		attacheds = irpAttachedService.getAttachedByAttDocId(this.docid, 0);
		return SUCCESS;
	}

	// 跳转到修改附件的页面
	public String toUpdateAttached() {
		return SUCCESS;
	}
	private String token;
	private HashMap<Long, String> denseMap;
	private long userDense;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	// 跳转到天添加附件页面
	public String toSaveAttached() {
		
		return SUCCESS;
	}

	// 跳到修改页面
	public String toUpdateDocument() {
		//密级
		String dense =SysConfigUtil.getSysConfigValue("DENSE") ;
		denseMap=new HashMap<Long, String>();
		if(dense!=null){
			String[] denseArray=dense.split(";");
			if(denseArray!=null&&denseArray.length>0){
				for (String string : denseArray) {
					String[] denseArrayTwo=string.split(":");
					if(denseArrayTwo!=null&&denseArrayTwo.length>0){
						denseMap.put(Long.parseLong(denseArrayTwo[0]), denseArrayTwo[1]);
					}
				}
			}
		}
		
		if (docid == null || docid == 0L)
			return ERROR;
		irpDocument = irpDocumentService.findDocumentByDocId(this.docid);// 当前需要修改的文档信息
		if (irpDocument.getChannelid() < 0) {
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;
		}
		// 查询所在栏目
		irpChannel = irpChannelService.finChannelByChannelid(id);// 查询新建文件文档所在的栏目名称
		documentMap = irpChannelService
				.allDocumentMap(IrpChannel.IS_DOCSTATIUS);
		documentMapList = irpDocumentService.docDocumentMap(this.docid,IrpDocumentMap.KONWLEDGE_MAP);
		documentMapOne = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_ONE);//一维地图
		documentMapTwo = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_TWO);//二维地图
		documentMapThree = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_THREE);//三维地图
		documentMapOther = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_OTHER);//其他
		return SUCCESS;
	}

	// 去添加文档页面
	public String toAddDocument() {
		//密级
		String dense =SysConfigUtil.getSysConfigValue("DENSE") ;
		denseMap=new HashMap<Long, String>();
		if(dense!=null){
			String[] denseArray=dense.split(";");
			if(denseArray!=null&&denseArray.length>0){
				for (String string : denseArray) {
					String[] denseArrayTwo=string.split(":");
					if(denseArrayTwo!=null&&denseArrayTwo.length>0){
						denseMap.put(Long.parseLong(denseArrayTwo[0]), denseArrayTwo[1]);
					}
				}
			}
		}
		// 若id为0 就跳到选择栏目页面
		if (id == null || id == 0L)
			return ERROR;
		if (id == null || id == -1)
			return "toCheckChannel";
		// 若文档id有值则直接到添加文档页面*/
		irpChannel = irpChannelService.finChannelByChannelid(id);// 查询新建文件文档所在的栏目名称
		//documentMap = irpChannelService.allDocumentMap(IrpChannel.IS_DOCSTATIUS);
		documentMapOne = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_ONE);//一维地图
		documentMapTwo = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_TWO);//二维地图
		documentMapThree = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_THREE);//三维地图
		documentMapOther = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_OTHER);//其他
		//获得用户所属的组织
		userGroups = irpGroupService.findGroupIdsByUserId(LoginUtil.getLoginUserId());
		return SUCCESS;
	}

	// 修改文档信息
	public void updateDocument() {
		int nCount = 0;
		Long nDocStatus = irpDocument.getDocstatus();
		if (nDocStatus == IrpDocument.DRAFT_STATUS) {// 说明点击的是草稿按钮
			irpDocument.setDocstatus(IrpDocument.DRAFT_STATUS);
		} else {// =="2"//说明点击的是保存按钮
			irpDocument.setDocstatus(null);
		}
		
			boolean isClient = false;
			Long _touChannelid = null;
			HttpServletRequest request = ServletActionContext.getRequest();
			String[] ids = request.getParameterValues("subjectselect");
			//insert this doc history version
			if (onlydocid.equals("2") && !(onlydocid.equals(""))) {
				addDocHistory();
			}
			
			nCount = irpDocumentService.updateDocumentByDocId(irpDocument, jsonFile, isClient, _touChannelid, true, documentmaps,ids,flag);
			
			ActionUtil.writer(String.valueOf(nCount));
		}
	

	/**
	 * save history version
	 * @return
	 */
	public void addDocHistory(){
		IrpDocumentWithBLOBs irpdocument = this.irpDocumentService.findDocumentByDocId(docid);
		int version = this.irpDocversionService.getVersionidByDocid(docid);
		this.irpDocversionService.addDocVersion(irpdocument,version);
		
	}
	
	

	// 添加文档(后台)
	public void addDocument() {
		int nCount = 0;
		Long nDocStatus = irpDocument.getDocstatus();
		if (nDocStatus == IrpDocument.DRAFT_STATUS) {// 说明点击的是草稿按钮
			irpDocument.setDocstatus(IrpDocument.DRAFT_STATUS);
		} else {// =="2"//说明点击的是保存按钮
			irpDocument.setDocstatus(null);
		}
		boolean isClient = false;
		Long _toChannelid = null;
		// irpDocument.setDoctype(IrpDocument.DOCUMENBT_IS_DOCUMENT);
		Long docid = TableIdUtil.getNextId(IrpDocumentWithBLOBs.TABLE_NAME);// 他的主键值
		irpDocument.setDocid(docid);
		nCount = irpDocumentService.addDocument(irpDocument, jsonFile,
				isClient, _toChannelid, true, documentmaps,
				IrpDocument.DOCTYPE_DOCUMENT);
		ActionUtil.writer(String.valueOf(nCount));
	}

	/**
	 * 新增专题
	 * 
	 * @return
	 */
	public String addsubjectnow() {
		return SUCCESS;
	}
	
	/**
	 * 修改专题
	 * @return
	 */
	public String updatesubjectnow(){
		if(irpChannel!=null && irpChannel.getChannelid()!=null){
			IrpChannelExample example = new IrpChannelExample();
			example.createCriteria().andChannelidEqualTo(irpChannel.getChannelid());
			irpChannel = irpChannelService.findChannelByExample(example).get(0);
		}
		return SUCCESS;
	}

	/**
	 * 新增个人专题
	 */
	public void begincreatesubject() {
		if (irpChannel.getChnlname() == null
				|| "".equals(irpChannel.getChnlname())) {
			ActionUtil.writer("0");
			return;
		}
		// 判断用户专题名是否存在,不存在则新增
		String userName = LoginUtil.getLoginUser().getUsername();
		IrpChannelExample example = new IrpChannelExample();
		Criteria criteria = example.createCriteria();
		criteria.andChnlnameEqualTo(userName);
		criteria.andSiteidEqualTo(0L);
		criteria.andParentidEqualTo(Long.valueOf(SysConfigUtil
				.getSysConfigValue("DOCUMENT_PERSON_SUBJECT_ID")));
		List<IrpChannel> tempList = irpChannelService
				.findChannelByExample(example);
		IrpChannel channel = null;
		if (tempList == null || tempList.size() == 0) {
			// 新增用户专题
			channel = new IrpChannel();
			channel.setChnlname(userName);
			channel.setSiteid(0L);
			channel.setChnldesc(userName);
			channel.setParentid(Long.valueOf(SysConfigUtil
					.getSysConfigValue("DOCUMENT_PERSON_SUBJECT_ID")));
			channel.setStatus(1);
			channel.setChnltype(IrpChannel.CHANNEL_TYPE_SUBJECT);
			channel.setPublishpro(1);
			irpChannelService.addChannel(channel, LoginUtil.getLoginUser());
			tempList = irpChannelService.findChannelByExample(example);
		}
		IrpChannel newChannel = tempList.get(0);
		IrpChannelExample dupChannel = new IrpChannelExample();
		Criteria criteria2 = dupChannel.createCriteria();
		criteria2.andParentidEqualTo(newChannel.getChannelid());
		criteria2.andChnlnameEqualTo(irpChannel.getChnlname());
		List list = irpChannelService.findChannelByExample(dupChannel);
		if (list != null && list.size() > 0) {
			ActionUtil.writer("2");
			return;
		}
		channel = new IrpChannel();
		channel.setChnlname(irpChannel.getChnlname());
		channel.setChnldesc((irpChannel.getChnldesc() == null || ""
				.equals(irpChannel.getChnldesc())) ? irpChannel.getChnlname()
				: irpChannel.getChnldesc());
		channel.setSiteid(0L);
		channel.setParentid(newChannel.getChannelid());
		channel.setStatus(1);
		channel.setChnltype(IrpChannel.CHANNEL_TYPE_SUBJECT);
		channel.setPublishpro(1);
		irpChannelService.addChannel(channel, LoginUtil.getLoginUser());
		ActionUtil.writer("1");
	}
	
	/**
	 * 新增企业专题
	 */
	public void begincreateqiyesubject() {
		if (irpChannel.getChnlname() == null
				|| "".equals(irpChannel.getChnlname())) {
			ActionUtil.writer("0");
			return ;
		}
		
		if(id==null){
			String qiyeid=SysConfigUtil.getSysConfigValue("DOCUMENT_QIYE_SUBJECT_ID");
			id = Long.valueOf(qiyeid);
		}
		IrpChannelExample example = new IrpChannelExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(id);
		criteria.andChnlnameEqualTo(irpChannel.getChnlname());
		criteria.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
		criteria.andSiteidEqualTo(0L);
		List<IrpChannel> deleteList = irpChannelService.findChannelByExample(example);
		if(deleteList!=null && deleteList.size()>0){
			ActionUtil.writer("2");
			return;
		}
		//可以创建
		IrpChannel channel = new IrpChannel();
		channel.setChnlname(irpChannel.getChnlname());
		channel.setChnldesc((irpChannel.getChnldesc() == null || ""
				.equals(irpChannel.getChnldesc())) ? irpChannel.getChnlname()
				: irpChannel.getChnldesc());
		channel.setSiteid(0L);
		channel.setParentid(id);
		channel.setStatus(1);
		channel.setChnltype(IrpChannel.CHANNEL_TYPE_SUBJECT);
		channel.setPublishpro(1);
		irpChannelService.addChannel(channel, LoginUtil.getLoginUser());
		ActionUtil.writer("1");
	}
	
	
	/**
	 * 修改专题
	 */
	public void updatesubject(){
		if (irpChannel.getChnlname() == null
				|| "".equals(irpChannel.getChnlname())) {
			ActionUtil.writer("0");
			return;
		}
		IrpChannelExample dupChanne = new IrpChannelExample();
		IrpChannelExample dupChanne2= new IrpChannelExample();
		dupChanne2.createCriteria().andChannelidEqualTo(irpChannel.getChannelid());
		IrpChannel oldChannel = irpChannelService.findChannelByExample(dupChanne2).get(0);
		Criteria criteria = dupChanne.createCriteria();
		criteria.andChannelidNotEqualTo(irpChannel.getChannelid());
		criteria.andChnlnameEqualTo(irpChannel.getChnlname());
		criteria.andParentidEqualTo(oldChannel.getParentid());
		List list = irpChannelService.findChannelByExample(dupChanne);
		if(list!=null && list.size()>0){
			ActionUtil.writer("2");
			return;
		}
		irpChannelService.updateChannelByChannelid(irpChannel);
		ActionUtil.writer("1");
	}
	
	/**
	 * 删除map关系表中doc和channel关系
	 */
	public void deletedocsub(){
		IrpDocumentMapExample example = new IrpDocumentMapExample();
		example.createCriteria().andDocidEqualTo(irpDocument.getDocid())
								.andChannelidEqualTo(irpChannel.getChannelid());
		irpDocumentMapService.deleteDocSubByExample(example);
		ActionUtil.writer("1");
	}
	
	/**
	 * 删除专题
	 */
	public void deletesubject(){
		int result = irpChnl_Doc_LinkService.deleteDocumentSubjectLink(irpChannel.getChannelid());
		IrpDocumentMapExample example = new IrpDocumentMapExample();
		example.createCriteria().andChannelidEqualTo(irpChannel.getChannelid())
								.andTypeEqualTo(IrpDocumentMap.KNOWLEDGE_SUBJECT);
		irpDocumentMapService.deleteDocSubByExample(example);
		if(result==1){
			ActionUtil.writer("1");
		}else{
			ActionUtil.writer("0");
		}
	}
	
	/**
	 * 选择专题类型
	 */
	public String selectNameType() {
		return SUCCESS;
	}

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public IrpSiteService getIrpSiteService() {
		return irpSiteService;
	}

	public void setIrpSiteService(IrpSiteService irpSiteService) {
		this.irpSiteService = irpSiteService;
	}

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	public IrpDocumentWithBLOBs getIrpDocument() {
		return irpDocument;
	}

	public void setIrpDocument(IrpDocumentWithBLOBs irpDocument) {
		this.irpDocument = irpDocument;
	}

	public List<IrpDocumentWithBLOBs> getIrpDocuments() {
		return irpDocuments;
	}

	public void setIrpDocuments(List<IrpDocumentWithBLOBs> irpDocuments) {
		this.irpDocuments = irpDocuments;
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

	public Long getDocid() {
		return docid;
	}

	public void setDocid(Long docid) {
		this.docid = docid;
	}

	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}

	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}

	public List<IrpChnlDocLink> getChnlDocLinks() {
		return chnlDocLinks;
	}

	public void setChnlDocLinks(List<IrpChnlDocLink> chnlDocLinks) {
		this.chnlDocLinks = chnlDocLinks;
	}

	public Long getDocorder() {
		return docorder;
	}

	public void setDocorder(Long docorder) {
		this.docorder = docorder;
	}

	public String getMyFileName() {
		return myFileName;
	}

	public void setMyFileName(String myFileName) {
		this.myFileName = myFileName;
	}

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}

	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	}

	public String getMyFileNameSrc() {
		return myFileNameSrc;
	}

	public void setMyFileNameSrc(String myFileNameSrc) {
		this.myFileNameSrc = myFileNameSrc;
	}

	public IrpChannel getIrpChannel() {
		return irpChannel;
	}

	public void setIrpChannel(IrpChannel irpChannel) {
		this.irpChannel = irpChannel;
	}

	public Long getAttAchedId() {
		return attAchedId;
	}

	public void setAttAchedId(Long attAchedId) {
		this.attAchedId = attAchedId;
	}

	public IrpAttached getIrpAttached() {
		return irpAttached;
	}

	public void setIrpAttached(IrpAttached irpAttached) {
		this.irpAttached = irpAttached;
	}

	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}

	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public List<IrpChannel> getIrpChannels() {
		return irpChannels;
	}

	public void setIrpChannels(List<IrpChannel> irpChannels) {
		this.irpChannels = irpChannels;
	}

	public Long getChannelids() {
		return channelids;
	}

	public void setChannelids(Long channelids) {
		this.channelids = channelids;
	}

	public List<IrpDocrecommend> getDocrecommends() {
		return docrecommends;
	}

	public void setDocrecommends(List<IrpDocrecommend> docrecommends) {
		this.docrecommends = docrecommends;
	}

	public DocumentReadedService getIrpDocumentReadedService() {
		return irpDocumentReadedService;
	}

	public void setIrpDocumentReadedService(
			DocumentReadedService irpDocumentReadedService) {
		this.irpDocumentReadedService = irpDocumentReadedService;
	}

	public IrpInformService getIrpInformService() {
		return irpInformService;
	}

	public IrpInform getDocClassicl() {
		return docClassicl;
	}

	public List<IrpInform> getDocClassicls() {
		return docClassicls;
	}

	public void setIrpInformService(IrpInformService irpInformService) {
		this.irpInformService = irpInformService;
	}

	public void setDocClassicl(IrpInform docClassicl) {
		this.docClassicl = docClassicl;
	}

	public void setDocClassicls(List<IrpInform> docClassicls) {
		this.docClassicls = docClassicls;
	}

	private IrpValueSettingService irpValueSettingService;

	private IrpMicroblogFocusService irpMicroblogFocusService;

	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}

	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}

	public IrpValueSettingService getIrpValueSettingService() {
		return irpValueSettingService;
	}

	public void setIrpValueSettingService(
			IrpValueSettingService irpValueSettingService) {
		this.irpValueSettingService = irpValueSettingService;
	}

	public IrpUser getIrpUser(Long _Userid) {
		IrpUser irpUser = null;
		irpUser = this.irpUserService.findUserByUserId(_Userid);
		return irpUser;
	}

	public String getRanknameBySumscore(Long _sumscore) {
		String rankname = irpValueSettingService
				.findRanknameBySumScore(_sumscore);
		return rankname;
	}

	public int countMicroblogFocusFocusUserid(Long _userid) {
		int fans = 0;
		fans = irpMicroblogFocusService.countMicroblogFocusFocusUserid(_userid);
		return fans;
	}

	public Long getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Long isTrue) {
		this.isTrue = isTrue;
	}

	/**
	 * 静态方法 根据用户id获取用户图片
	 */

	/**
	 * 用户头像
	 * 
	 * @return
	 */
	public static String userPic(Object userid) {
		// 初始化用户图片地址
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpUserService Service = (IrpUserService) ac.getBean("irpUserService");
		IrpUser user = Service.findUserByUserId(new Long(userid.toString()));
		return user.getDefaultUserPic();
	}
	
	/**
	 * 个人知识专题知识列表
	 * @return
	 */
	public String knowledgeSubject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long channelid = Long.valueOf(request.getParameter("chid"));
		IrpChannelExample chnlExample = new IrpChannelExample();
		chnlExample.createCriteria().andChannelidEqualTo(channelid);
		List<IrpChannel> channels = irpChannelService.findChannelByExample(chnlExample);
		if(channels!=null && channels.size()>0){
			irpChannel = channels.get(0);
		}
		
		IrpDocumentMapExample example = new IrpDocumentMapExample();
		example.createCriteria().andChannelidEqualTo(channelid);
		List<IrpDocumentMap> mapList = irpDocumentMapService.selectMapByExample(example);
		if(mapList!=null && mapList.size()>0){
			List<Long> docids = new ArrayList<Long>();
			for(IrpDocumentMap ele :mapList){
				docids.add(ele.getDocid());
			}
			IrpDocumentExample docExample = new IrpDocumentExample();
			docExample.createCriteria().andDocidIn(docids);
			docExample.setOrderByClause("crtime desc");
			List<IrpDocument> list = irpDocumentService.findAllDatas(docExample);
			if(list!=null && list.size()>0){
				PageUtil page = new PageUtil(pageNum, pageSize, list.size());
				documentList = irpDocumentService.selectByExample(page, docExample);
				this.pageHTML = page.getClientPageHtml("page(#PageNum#)"); 
			}else{
				documentList = null;
			}
		}else{
			documentList = null;
		}
		
		
		return SUCCESS;
	}

	/**
	 * 知识分类法
	 */
	private List<IrpChannel> documentMap;
	private String[] documentmaps;// 当添加知识的时候提供分类法
	private List<IrpDocumentMap> documentMapList;

	/**
	 * 根据id查询名称
	 * @param userid
	 * @return
	 */
	public String getNickName(String userid){
		IrpUser user = irpUserService.findUserByUserId(Long.valueOf(userid));
		return (user.getNickname()==null || "".equals(user.getNickname()))?user.getUsername():user.getNickname();
	}
	/**
	 * 通过id找出相对应的个人信息
	 * @return
	 */
	public IrpUser findUserByUserId(long _userid){
	  IrpUser irpUser = null;
	  irpUser = this.irpUserService.findUserByUserId(_userid);
	  return irpUser;
	}
	
	/**
	 * 查询专题下知识的数量
	 */
	public void checkSubjectDocNum(){
		//通过channelid在关系表中查询出数量
		int nCount = 0;
		List<Long> docNum = irpDocumentMapService.docidsByChannelid(irpChannel.getChannelid());
		if(docNum !=null && docNum.size()>0){
			nCount = docNum.size();
		}
		ActionUtil.writer(nCount+"");
	}
	
	/**
	 * 初始化自己所有专题
	 * @return
	 */
	public String catchknowto(){
		return SUCCESS;
	}
	
	/**
	 * 初始化个人专题
	 */
	public String initpersonsubject(){
		//查询当前用户有没有个人专题
		IrpChannelExample selfExample = new IrpChannelExample();
		selfExample.createCriteria().andCruseridEqualTo(LoginUtil.getLoginUserId())
									.andParentidEqualTo(Long.valueOf(SysConfigUtil.getSysConfigValue("DOCUMENT_PERSON_SUBJECT_ID")))
									.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT)
									.andSiteidEqualTo(0L);
		List<IrpChannel> selfChannel = irpChannelService.findChannelByExample(selfExample);
		if(selfChannel!=null && selfChannel.size()>0){
			//查询当前用户所有专题
			selfExample = new IrpChannelExample();
			Criteria criteria = selfExample.createCriteria();
			criteria.andCruseridEqualTo(LoginUtil.getLoginUserId());
			criteria.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
			criteria.andSiteidEqualTo(0L);
			criteria.andParentidEqualTo(selfChannel.get(0).getChannelid());
			String sOrderByClause = "";
			if(this.orderField==null || this.orderField.equals("")){
				sOrderByClause = "channelid desc";
			}else{
				sOrderByClause = this.orderField+" "+this.orderBy;
			} 
			if("channelname".equals(searchType)){
				criteria.andChnlnameLike("%"+this.searchWord+"%");
			} else if("channeldesc".equals(searchType)){
				criteria.andChnldescLike("%"+this.searchWord+"%");
			} 
			selfExample.setOrderByClause(sOrderByClause);
									
			List numList = irpChannelService.findChannelByExample(selfExample);
			if(numList!=null && numList.size()>0){
				PageUtil page = new PageUtil(pageNum, pageSize, numList.size());
				irpChannels = irpChannelService.selectByExample(page, selfExample);
				irpChannels = checkifhad(irpDocument.getDocid(),irpChannels);
				this.pageHTML = page.getClientPageHtml("page(#PageNum#)");  
			}
		}
		return SUCCESS;
	}
	/**
	 * 初始化企业专题
	 */
	public String initqiyesubject(){
		//初始化一级专题
		String parentid = SysConfigUtil.getSysConfigNumValue("DOCUMENT_QIYE_SUBJECT_ID")+"";
		if(parentid!=null && !"".equals(parentid)){
			List<IrpChannel> list = new ArrayList<IrpChannel>();
			list = irpChannelService.findSubIdsByParent(Long.valueOf(parentid),list);
			IrpChannelExample qiyeExample = new IrpChannelExample();
			Criteria criteria = qiyeExample.createCriteria();
//			Criteria criteriaz = qiyeExample.createCriteria();
			if(list!=null && list.size()>0){
				List<Long> channelidslist  = new ArrayList<Long>();
				for(IrpChannel listele : list){
					channelidslist.add(listele.getChannelid());
				}
				criteria.andChannelidIn(channelidslist);
			}else {
				irpChannels = null;
				return SUCCESS;
			}
//			criteriaz.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT);
//			qiyeExample.or(criteriaz);
			criteria.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
			criteria.andSiteidEqualTo(0L);
			String sOrderByClause = "";
			if(this.orderField==null || this.orderField.equals("")){
				sOrderByClause = "channelid desc";
			}else{
				sOrderByClause = this.orderField+" "+this.orderBy;
			} 
			if("channelname".equals(searchType)){
				criteria.andChnlnameLike("%"+this.searchWord+"%");
			} else if("channeldesc".equals(searchType)){
				criteria.andChnldescLike("%"+this.searchWord+"%");
			} 
			qiyeExample.setOrderByClause(sOrderByClause);
			List<IrpChannel> numList = irpChannelService.findChannelByExample(qiyeExample);
			List<IrpChannel> deleteChannels = new ArrayList<IrpChannel>();
			if(!LoginUtil.getLoginUser().isAdministrator()){
				if(numList!=null && numList.size()>0){
					for(IrpChannel ele : numList){
						Boolean subjectSelect=irpChannelService.findRightSubToChannel(LoginUtil.getLoginUser(), "SUBJECT_QUOTE", IrpChannel.CHANNEL_IS_PUBLISH,IrpChannel.TABLE_NAME,ele.getChannelid().toString());
						if(!subjectSelect){
							deleteChannels.add(ele);
						}
					}
				}
			}
			if(deleteChannels!=null && deleteChannels.size()>0){
				for(IrpChannel dchannel : deleteChannels){
					numList.remove(dchannel);
				}
			}
			
			if(numList!=null && numList.size()>0){
				List<IrpChannel> finalDelete = new ArrayList<IrpChannel>();
				PageUtil page = new PageUtil(pageNum, pageSize, numList.size());
				irpChannels = irpChannelService.selectByExample(page, qiyeExample);
				irpChannels = checkifhad(irpDocument.getDocid(),irpChannels);
				if(irpChannels!=null && irpChannels.size()>0){
					if(deleteChannels!=null && deleteChannels.size()>0){
						for(IrpChannel deleEle : deleteChannels){
							for(IrpChannel ele : irpChannels){
								if(ele.getChannelid().intValue()==deleEle.getChannelid().intValue()){
									finalDelete.add(ele);
								}
							}
						}
					}
				}
				if(finalDelete!=null && finalDelete.size()>0){
					for(int k = 0;k<finalDelete.size();k++){
						irpChannels.remove(finalDelete.get(k));
					}
				}
				this.pageHTML = page.getClientPageHtml("page(#PageNum#)");  
			}
		}
		return SUCCESS;
	}

	/**
	 * 查看专题是否引用过此知识
	 * @return
	 */
	public List<IrpChannel> checkifhad(Long docid,List<IrpChannel> channelList){
		if(docid!=null){
			if(channelList!=null && channelList.size()>0){
				//取出id
				List<Long> channelids = new ArrayList<Long>();
				for(IrpChannel ele : channelList){
					channelids.add(ele.getChannelid());
				}
				IrpDocumentMapExample example = new IrpDocumentMapExample();
				example.createCriteria().andDocidEqualTo(docid)
							.andChannelidIn(channelids)
							.andTypeEqualTo(IrpDocumentMap.KNOWLEDGE_SUBJECT);
				List<IrpDocumentMap> list = irpDocumentMapService.selectMapByExample(example);
				if(list!=null && list.size()>0){
					//改变状态
					long checkedid;
					for(int i = 0 ; i<list.size() ; i++){
						checkedid = list.get(i).getChannelid();
						for(IrpChannel ele : channelList){
							if(checkedid==ele.getChannelid()){
								ele.setSelectstatus("1");
							}
						}
					}
				}
			}
		}
		return channelList;
	}
	
	/**
	 * 企业知识引用到专题
	 */
	public void addqiyesub(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] channelids = request.getParameterValues("selectedsub");
		if(channelids!=null && channelids.length>0){
			IrpDocumentMap newMap = null;
			for(int i = 0;i<channelids.length;i++){
				IrpDocumentMapExample example = new IrpDocumentMapExample();
				example.createCriteria().andDocidEqualTo(irpDocument.getDocid())
										.andChannelidEqualTo(Long.valueOf(channelids[i]))
										.andTypeEqualTo(IrpDocumentMap.KNOWLEDGE_SUBJECT);
				List list = irpDocumentMapService.selectMapByExample(example);
				if(list==null || list.size()==0){
					newMap = new IrpDocumentMap();
					newMap.setMid(TableIdUtil.getNextId(IrpDocumentMap.TABLE_NAME));
					newMap.setDocid(irpDocument.getDocid());
					newMap.setChannelid(Long.valueOf(channelids[i]));
					newMap.setCrtime(new Date());
					newMap.setType(IrpDocumentMap.KNOWLEDGE_SUBJECT);
					irpDocumentMapService.addDocSubByExample(newMap);
				}
			}
			ActionUtil.writer("1");
		}else{
			ActionUtil.writer("0");
		}
	}
	
	
	
	public Long getmapid(String docid,String rootid){
		List<Long> allChannelid = new ArrayList<Long>();
		allChannelid = irpChannelService.mapChildrenIds(Long.valueOf(rootid), null, allChannelid);
		IrpDocumentMapExample example = new IrpDocumentMapExample();
		example.createCriteria().andTypeEqualTo(IrpDocumentMap.KONWLEDGE_MAP)
								.andDocidEqualTo(Long.valueOf(docid))
								.andChannelidIn(allChannelid);
		List<IrpDocumentMap> mapList = irpDocumentMapService.selectMapByExample(example);
		if(mapList!=null && mapList.size()>0){
			return mapList.get(0).getChannelid();
		}else{
			return null;
		}
	}
	
	public String getmapids(String docid,String rootid){
		List<Long> allChannelid = new ArrayList<Long>();
		allChannelid = irpChannelService.mapChildrenIds(Long.valueOf(rootid), null, allChannelid);
		IrpDocumentMapExample example = new IrpDocumentMapExample();
		example.createCriteria().andTypeEqualTo(IrpDocumentMap.KONWLEDGE_MAP)
								.andDocidEqualTo(Long.valueOf(docid))
								.andChannelidIn(allChannelid);
		List<IrpDocumentMap> mapList = irpDocumentMapService.selectMapByExample(example);
		if(mapList!=null && mapList.size()>0){
			StringBuffer stringBuffer=new StringBuffer();
			for (IrpDocumentMap long1 : mapList) {
				stringBuffer.append(long1.getChannelid()+",");
			}
			String lString=stringBuffer.toString();
			documentMapids=lString.substring(0,lString.length()-1);
			return documentMapids;
		}else{
			return null;
		}
	}
	
	public List<IrpDocumentMap> getDocumentMapList() {
		return documentMapList;
	}

	public void setDocumentMapList(List<IrpDocumentMap> documentMapList) {
		this.documentMapList = documentMapList;
	}

	public List<IrpChannel> getDocumentMap() {
		return documentMap;
	}

	public void setDocumentMap(List<IrpChannel> documentMap) {
		this.documentMap = documentMap;
	}

	public String[] getDocumentmaps() {
		return documentmaps;
	}

	public void setDocumentmaps(String[] documentmaps) {
		this.documentmaps = documentmaps;
	}

	public String getFriendlyshow() {
		return friendlyshow;
	}

	public void setFriendlyshow(String friendlyshow) {
		this.friendlyshow = friendlyshow;
	}

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
	
	/**
	 * 根据DOCID查询收藏数量
	 * @param _nDocId
	 * @return
	 */
	public int countCollectionByDocId(long _nDocId) {
		return irpDocumentCollectionService.countByDocId(_nDocId);
	}
	
	/**
	 * 根据DOCID查询精华数量
	 * @param _nDocId
	 * @return
	 */
	public int countEssenceByDocId(long _nDocId) {
		return irpInformService.findCountByExpert(IrpInform.INFORMJIAJINGDOC, _nDocId);
	}

	private List<IrpGroup> irpGroups;
	private String groupname;
	private IrpGroup irpGroup;
	private String groupId; 
	private String docNum;
	private String groupstext;
	private String bttext;
	public String getBttext() {
		return bttext;
	}

	public void setBttext(String bttext) {
		this.bttext = bttext;
	}

	private int itype=1;
	public int getItype() {
		return itype;
	}

	public void setItype(int itype) {
		this.itype = itype;
	}

	public String getGroupstext() {
		return groupstext;
	}

	public void setGroupstext(String groupstext) {
		this.groupstext = groupstext;
	}

	public String getDocNum() {
		return docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public IrpGroup getIrpGroup() {
		return irpGroup;
	}

	public void setIrpGroup(IrpGroup irpGroup) {
		this.irpGroup = irpGroup;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public List<IrpGroup> getIrpGroups() {
		return irpGroups;
	}

	public void setIrpGroups(List<IrpGroup> irpGroups) {
		this.irpGroups = irpGroups;
	}



	/**
	 * 栏目
	 * @return
	 */
	public String checkChannelList() {
		groupstext=groupId;
		String[] sChannel=groupId.split(",");	
		StringBuffer btdoc = new StringBuffer();
		int nDataCount=0;
		Map<String,Object> groupidmap= new HashMap<String,Object>();
		StringBuffer name = new StringBuffer("");
		StringBuffer number = new StringBuffer("");		
		if(itype==1){
			for(int i = 0;i<sChannel.length;i++){
				if(sChannel[i].equals("2")){
					List<IrpChannel> irpcha= this.irpChannelService.findChannelsBySiteIds(Long.parseLong(sChannel[i]));
					for(int s=0;s<irpcha.size();s++){
						if(irpcha.get(s).getChannelid()!=1&irpcha.get(s).getChannelid()!=2&irpcha.get(s).getChannelid()!=3){
							List<String> finalResult = new ArrayList<String>();
							finalResult =this.gets( finalResult,irpcha.get(s).getChannelid().toString(),0);
							groupidmap.put(irpcha.get(s).getChannelid().toString(), finalResult.size());
							List<Long> allgroupid=new ArrayList<Long>();
							for(int k=0;k<finalResult.size();k++){
								allgroupid.add(Long.parseLong(finalResult.get(k)));
							}		
							nDataCount=irpDocumentService.selectCountByChannelDoc(2l, allgroupid);
							name.append("'"+irpChannelService.finChannelByChannelid(Long.parseLong(irpcha.get(s).getChannelid().toString())).getChnlname()+"',");
							number.append(nDataCount+",");	
						}					
					}					
				}else{					
					List<String> finalResult = new ArrayList<String>();
					finalResult =this.gets( finalResult,sChannel[i],0);
					groupidmap.put(sChannel[i], finalResult.size());
					List<Long> allgroupid=new ArrayList<Long>();
					for(int k=0;k<finalResult.size();k++){
						allgroupid.add(Long.parseLong(finalResult.get(k)));
					}		
					nDataCount=irpDocumentService.selectCountByChannelDoc(2l,allgroupid);
					name.append("'"+irpChannelService.finChannelByChannelid(Long.parseLong(sChannel[i])).getChnlname()+"',");
					number.append(nDataCount+",");	
				}				
			}
		}else{
			for(int i = 0;i<sChannel.length;i++){
				if(sChannel[i].equals("2")){
					List<IrpChannel> irpcha= this.irpChannelService.findChannelsBySiteIds(Long.parseLong(sChannel[i]));
					for(int s=0;s<irpcha.size();s++){
						if(irpcha.get(s).getChannelid()!=1&irpcha.get(s).getChannelid()!=2&irpcha.get(s).getChannelid()!=3){
							List<String> finalResult = new ArrayList<String>();
							finalResult =this.gets( finalResult,irpcha.get(s).getChannelid().toString(),0);
							groupidmap.put(irpcha.get(s).getChannelid().toString(), finalResult.size());
							List<Long> allgroupid=new ArrayList<Long>();
							for(int k=0;k<finalResult.size();k++){
								allgroupid.add(Long.parseLong(finalResult.get(k)));
							}		
							nDataCount=irpDocumentService.selectCountByChannelDoc(2l, allgroupid);
							name.append("'"+irpChannelService.finChannelByChannelid(Long.parseLong(irpcha.get(s).getChannelid().toString())).getChnlname()+"',");
							number.append(nDataCount+",");
							btdoc.append(irpChannelService.finChannelByChannelid(Long.parseLong(irpcha.get(s).getChannelid().toString())).getChnlname()+","+nDataCount+"-");						
						}						
					}					
				}else{					
					List<String> finalResult = new ArrayList<String>();
					finalResult =this.gets( finalResult, sChannel[i],0);
					groupidmap.put(sChannel[i], finalResult.size());
					List<Long> allgroupid=new ArrayList<Long>();
					for(int k=0;k<finalResult.size();k++){
						allgroupid.add(Long.parseLong(finalResult.get(k)));
					}		
					
					nDataCount=irpDocumentService.selectCountByChannelDoc(2l, allgroupid);
					name.append("'"+irpChannelService.finChannelByChannelid(Long.parseLong(sChannel[i])).getChnlname()+"',");
					number.append(nDataCount+",");
					btdoc.append(irpChannelService.finChannelByChannelid(Long.parseLong(sChannel[i])).getChnlname()+","+nDataCount+"-");												
				}				
			}	
		}if(itype==1){
			this.groupname = name.toString().substring(0,name.toString().length()-1);
			this.docNum = number.toString().substring(0,number.toString().length()-1);
		}else{
			this.groupname = name.toString().substring(0,name.toString().length()-1);
			this.docNum = number.toString().substring(0,number.toString().length()-1);
			this.bttext=btdoc.toString().substring(0,btdoc.toString().length()-1);
		}if(itype==1){
			return SUCCESS;
		}else{
			return INPUT;  
		}
	}
	
	
	/**
	 * 得到组织
	 * @return
	 */
	public String groupStatisticsList() {
		groupstext=groupId;
		String[] spGroupid=groupId.split(",");	
		StringBuffer btdoc = new StringBuffer();
		int nDataCount=0;
		Map<String,Object> groupidmap= new HashMap<String,Object>();
		StringBuffer name = new StringBuffer("");
		StringBuffer number = new StringBuffer("");	
		if(itype==1){
			for(int i = 0;i<spGroupid.length;i++){
				if(spGroupid[i].equals("1")){
					List<IrpGroup> igroup=null;
					long grupid=Long.parseLong(spGroupid[i]);
					igroup=	this.irpGroupService.ceshi(grupid);
					for(int s=0;s<igroup.size();s++){
						List<String> finalResult = new ArrayList<String>();
						finalResult =this.gets( finalResult,igroup.get(s).getGroupid().toString(),1);
						groupidmap.put(igroup.get(s).getGroupid().toString(), finalResult.size());
						List<Long> allgroupid=new ArrayList<Long>();
						for(int k=0;k<finalResult.size();k++){
							allgroupid.add(Long.parseLong(finalResult.get(k)));
						}		
						nDataCount=irpDocumentService.selectCountByGroupidDoc(2l, allgroupid);
						name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+"',");
						number.append(nDataCount+",");	
					}					
				}else{					
					List<String> finalResult = new ArrayList<String>();
					finalResult =this.gets( finalResult, spGroupid[i],1);
					groupidmap.put(spGroupid[i], finalResult.size());
					List<Long> allgroupid=new ArrayList<Long>();
					for(int k=0;k<finalResult.size();k++){
						allgroupid.add(Long.parseLong(finalResult.get(k)));
					}		
					nDataCount=irpDocumentService.selectCountByGroupidDoc(2l, allgroupid);
					name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+"',");
					number.append(nDataCount+",");	
				}				
			}
		}else{
			for(int i = 0;i<spGroupid.length;i++){
				if(spGroupid[i].equals("1")){
					List<IrpGroup> igroup=null;
					long grupid=Long.parseLong(spGroupid[i]);
					igroup=	this.irpGroupService.ceshi(grupid);
					for(int s=0;s<igroup.size();s++){
						List<String> finalResult = new ArrayList<String>();
						finalResult =this.gets( finalResult,igroup.get(s).getGroupid().toString(),1);
						groupidmap.put(igroup.get(s).getGroupid().toString(), finalResult.size());
						List<Long> allgroupid=new ArrayList<Long>();
						for(int k=0;k<finalResult.size();k++){
							allgroupid.add(Long.parseLong(finalResult.get(k)));
						}		
						nDataCount=irpDocumentService.selectCountByGroupidDoc(2l, allgroupid);
						name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+"',");
						number.append(nDataCount+",");	
						btdoc.append(irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+","+nDataCount+"-");						
					}					
				}else{					
					List<String> finalResult = new ArrayList<String>();
					finalResult =this.gets( finalResult, spGroupid[i],1);
					groupidmap.put(spGroupid[i], finalResult.size());
					List<Long> allgroupid=new ArrayList<Long>();
					for(int k=0;k<finalResult.size();k++){
						allgroupid.add(Long.parseLong(finalResult.get(k)));
					}		
					nDataCount=irpDocumentService.selectCountByGroupidDoc(2l, allgroupid);
					name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+"',");
					number.append(nDataCount+",");	
					btdoc.append(irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+","+nDataCount+"-");						
				}				
			}	
		}if(itype==1){
			this.groupname = name.toString().substring(0,name.toString().length()-1);
			this.docNum = number.toString().substring(0,number.toString().length()-1);
		}else{
			this.groupname = name.toString().substring(0,name.toString().length()-1);
			this.docNum = number.toString().substring(0,number.toString().length()-1);
			this.bttext=btdoc.toString().substring(0,btdoc.toString().length()-1);
		}if(itype==1){
			return SUCCESS;
		}else{
			return INPUT;  
		}
	}

	public List<String> gets( List<String> listls ,String all,int type){
		if(listls==null || listls.size()<=0){
			listls = new ArrayList<String>();
		}
		if(!listls.toString().contains(all)){
			listls.add(all);
		}
		if(type==0){
			List<IrpChannel> irpcha= this.irpChannelService.findAllChannel(Long.parseLong(all));			
			if(irpcha!=null && irpcha.size()>0){
				for(IrpChannel ele : irpcha){
					listls = this.gets(listls, ele.getChannelid().toString(),type);
				}
				return listls;
			}else {
				return listls;
			}
		}else{
			List<IrpGroup> listl = this.irpGroupService.findGroupsByParentId(Long.parseLong(all));
			if(listl!=null && listl.size()>0){
				for(IrpGroup ele : listl){
					listls = this.gets(listls, ele.getGroupid().toString(),type);
				}
				return listls;
			}else {
				return listls;
			}
		}
				
	}
	/**
	 * 查找相同的标题
	 * @return
	 */
	private String simistr;
	public String getSimistr() {
		return simistr;
	}
	public void setSimistr(String simistr) {
		this.simistr = simistr;
	}
	public void bDocSimilarity(){
		int msg = 0;
		String boolknow = SysConfigUtil.getSysConfigValue(IrpDocument.BOOLKNOWSIMILVAL);
		
		if(boolknow.equals("true")){
			List<IrpDocument> list  = this.irpDocumentService.boolSimilByTitle(simistr,Integer.parseInt(IrpDocument.DOCUMENT_NOT_INFORM.toString()),Integer.parseInt(IrpDocument.PUBLISH_STATUS.toString()));
			msg = list.size();
			
		}
		ActionUtil.writer(msg+"");
	}
	private String shotstr;
	private String hotdocword;
	public String getHotdocword() {
		return hotdocword;
	}

	public void setHotdocword(String hotdocword) {
		this.hotdocword = hotdocword;
	}

	public String getShotstr() {
		return shotstr;
	}
	public void setShotstr(String shotstr) {
		this.shotstr = shotstr;
	}
	/**
	 * 热词查找
	 * @return
	 */
	public String quoteHWSearch(){
		return SUCCESS;
	}
	
	
	private String docids;
	private long status;
	
	public String getDocids() {
		return docids;
	}

	public void setDocids(String docids) {
		this.docids = docids;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public void editDocstatus() {
		int rows = irpDocumentService.updateDocStatusByDocIds(docids, status);
		ActionUtil.writer(""+rows);
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 
	 * 
	 * 
	 * 新版方法
	 * 
	 * 
	 * 
	 */
	
	private IrpTagService irpTagService;
	private IrpUserLoveService irpUserLoveService;
	private long sysUserCount;
	private long sysDocumentCount;
	private List<IrpChannel> rootChannels;
	private List<IrpUser> irpUsers;
	private int dateType = 1;
	private List<IrpChnlDocLink> newChnlDocs;
	private List<IrpChnlDocLink> hotChnlDocs;
	private List<IrpChnlDocLink> valuableChnlDocs;
	private List<IrpChnlDocLink> collectionChnlDocs;
	private List<IrpChnlDocLink> essenceDocs;
	private List<IrpChnlDocLink> likeDocs;
	private List<Map<String, IrpBaseObj>> commentsData;
	private Map<String, List<IrpTag>> indexTags;
	private List<IrpDocumentLogs> docLogs;
	private List<IrpDocument> pushDocs;
	private List<IrpChannel> RootAndChilds;
	private int countChannel;
	private String channelidss;
	private String documentMapids;
	private long sysRecommendCount;
	
	

	public long getSysRecommendCount() {
		return sysRecommendCount;
	}

	public void setSysRecommendCount(long sysRecommendCount) {
		this.sysRecommendCount = sysRecommendCount;
	}

	public String getDocumentMapids() {
		return documentMapids;
	}

	public void setDocumentMapids(String documentMapids) {
		this.documentMapids = documentMapids;
	}

	public int getCountChannel() {
		return countChannel;
	}

	public void setCountChannel(int countChannel) {
		this.countChannel = countChannel;
	}

	public List<IrpChannel> getRootAndChilds() {
		return RootAndChilds;
	}

	public void setRootAndChilds(List<IrpChannel> rootAndChilds) {
		RootAndChilds = rootAndChilds;
	}

	public SolrService getSolrService() {
		return solrService;
	}

	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	}

	public List<IrpDocument> getPushDocs() {
		return pushDocs;
	}

	public void setPushDocs(List<IrpDocument> pushDocs) {
		this.pushDocs = pushDocs;
	}

	public List<IrpChnlDocLink> getLikeDocs() {
		return likeDocs;
	}

	public void setLikeDocs(List<IrpChnlDocLink> likeDocs) {
		this.likeDocs = likeDocs;
	}

	public IrpUserLoveService getIrpUserLoveService() {
		return irpUserLoveService;
	}

	public void setIrpUserLoveService(IrpUserLoveService irpUserLoveService) {
		this.irpUserLoveService = irpUserLoveService;
	}

	public List<IrpDocumentLogs> getDocLogs() {
		return docLogs;
	}

	public void setDocLogs(List<IrpDocumentLogs> docLogs) {
		this.docLogs = docLogs;
	}

	public IrpTagService getIrpTagService() {
		return irpTagService;
	}

	public void setIrpTagService(IrpTagService irpTagService) {
		this.irpTagService = irpTagService;
	}

	public Map<String, List<IrpTag>> getIndexTags() {
		return indexTags;
	}

	public void setIndexTags(Map<String, List<IrpTag>> indexTags) {
		this.indexTags = indexTags;
	}

	public List<IrpChnlDocLink> getEssenceDocs() {
		return essenceDocs;
	}

	public void setEssenceDocs(List<IrpChnlDocLink> essenceDocs) {
		this.essenceDocs = essenceDocs;
	}

	public List<Map<String, IrpBaseObj>> getCommentsData() {
		return commentsData;
	}

	public void setCommentsData(List<Map<String, IrpBaseObj>> commentsData) {
		this.commentsData = commentsData;
	}

	public List<IrpChnlDocLink> getCollectionChnlDocs() {
		return collectionChnlDocs;
	}

	public void setCollectionChnlDocs(List<IrpChnlDocLink> collectionChnlDocs) {
		this.collectionChnlDocs = collectionChnlDocs;
	}

	public List<IrpChnlDocLink> getNewChnlDocs() {
		return newChnlDocs;
	}

	public void setNewChnlDocs(List<IrpChnlDocLink> newChnlDocs) {
		this.newChnlDocs = newChnlDocs;
	}

	public List<IrpChnlDocLink> getHotChnlDocs() {
		return hotChnlDocs;
	}

	public void setHotChnlDocs(List<IrpChnlDocLink> hotChnlDocs) {
		this.hotChnlDocs = hotChnlDocs;
	}

	public List<IrpChnlDocLink> getValuableChnlDocs() {
		return valuableChnlDocs;
	}

	public void setValuableChnlDocs(List<IrpChnlDocLink> valuableChnlDocs) {
		this.valuableChnlDocs = valuableChnlDocs;
	}

	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}

	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}

	public int getDateType() {
		return dateType;
	}

	public void setDateType(int dateType) {
		this.dateType = dateType;
	}

	public List<IrpChannel> getRootChannels() {
		return rootChannels;
	}

	public void setRootChannels(List<IrpChannel> rootChannels) {
		this.rootChannels = rootChannels;
	}

	public long getSysUserCount() {
		return sysUserCount;
	}

	public void setSysUserCount(long sysUserCount) {
		this.sysUserCount = sysUserCount;
	}

	public long getSysDocumentCount() {
		return sysDocumentCount;
	}

	public void setSysDocumentCount(long sysDocumentCount) {
		this.sysDocumentCount = sysDocumentCount;
	}
	
	/**
	 * 知识库首页
	 * @return
	 */
	public String toDocumentIndex() {
		//获得系统中的用户和知识数量
		//sysUserCount = irpUserService.findAllUserCountByStatus(IrpUser.USER_STATUS_REG);
		//评论总数+评分总数
		long recommend=irpDocrecommendServiceImpl.getAllCount();
		long score=irpDocumentScoreService.getAllCount();
		sysRecommendCount=recommend+score;
		sysDocumentCount = irpDocumentService.findAllDocumentCount();
		//获得首页的三级栏目结构
		rootChannels = irpChannelService.allRightChannel();
		//最新知识
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause"," docpubtime desc ");
 		List<Long> _arrChannelIds=new ArrayList<Long>();
 		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS,0L, IrpSite.SITE_TYPE_PUBLISH, LoginUtil.getLoginUser());
 		map.put("channelidsList", _arrChannelIds);
 		newChnlDocs=irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM, SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"));
 		//最热知识
 		map.remove("sOrderByClause");
		map.put("sOrderByClause"," hitscount desc ");
		hotChnlDocs=irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM, SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"));
		//最有价值
		map.remove("sOrderByClause");
		map.put("sOrderByClause"," docscore desc ");
		valuableChnlDocs=irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM, SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"));
 		//热门收藏
		map.remove("sOrderByClause");
		map.put("sOrderByClause"," collectcount desc ");
		collectionChnlDocs = irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM, SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"));
		//获得最新评论数据
		commentsData = irpChnl_Doc_LinkService.findDocComments();
		//获得每日推荐知识
		PageUtil pageUtil = new PageUtil(1, 3, 3);
		essenceDocs=irpChnl_Doc_LinkService.findEssenceDocs(null, 1, pageUtil);
		//猜你喜欢		
		List<Long> docIds=irpUserLoveService.docIdsByUserid(LoginUtil.getLoginUserId(),IrpUserLove.CAI_YOU_LIKE);
		if(docIds!=null &&docIds.size()>0){
			likeDocs=irpChnl_Doc_LinkService.chnlDocByDocIds(docIds,IrpDocument.DOCUMENT_NOT_INFORM, 5);
		} 
		//获得首页标签
		indexTags = irpTagService.findDocIndexTagList();
		//个人标签
		List<IrpTag> irpTags = irpTagService.findTagsByUserId(LoginUtil.getLoginUserId());
		if(irpTags!=null&&irpTags.size()>0){
			StringBuffer sbBuffer=new StringBuffer();
			for (IrpTag irpTag : irpTags) {
				sbBuffer.append(irpTag.getTagname()+",");
			}
			pageSize=5;
			pushDocs=findSearchResultByDocumentEprise(pushDocs,sbBuffer.toString());
		}
		return SUCCESS;
	}
	private List<IrpUser> listExpert;
	private Long categoryId;
	private IrpRoleService irpRoleService;
	private int isLeft;
	private Long channelidleft;
	private List<IrpChnlDocLink> chnlDocLinks15;
	private List<IrpChnlDocLink> chnlDocLinks18;
	
	
	public List<IrpChnlDocLink> getChnlDocLinks18() {
		return chnlDocLinks18;
	}

	public void setChnlDocLinks18(List<IrpChnlDocLink> chnlDocLinks18) {
		this.chnlDocLinks18 = chnlDocLinks18;
	}

	public List<IrpChnlDocLink> getChnlDocLinks15() {
		return chnlDocLinks15;
	}

	public void setChnlDocLinks15(List<IrpChnlDocLink> chnlDocLinks15) {
		this.chnlDocLinks15 = chnlDocLinks15;
	}

	public long getUserDense() {
		return userDense;
	}

	public void setUserDense(long userDense) {
		this.userDense = userDense;
	}

	public int getIsLeft() {
		return isLeft;
	}

	public void setIsLeft(int isLeft) {
		this.isLeft = isLeft;
	}

	public List<IrpUser> getListExpert() {
		return listExpert;
	}

	public void setListExpert(List<IrpUser> listExpert) {
		this.listExpert = listExpert;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public IrpRoleService getIrpRoleService() {
		return irpRoleService;
	}

	public void setIrpRoleService(IrpRoleService irpRoleService) {
		this.irpRoleService = irpRoleService;
	}
	private IrpWorkFlowService irpWorkFlowService;
	private List<Map<String, Object>> flowInfos;
	private int count;
	private int countnum;
	private IrpMessageContentService irpMessageContentService;
	private List<IrpMessageContent> irpMessageContentlist;
	private String xName;
	private String yName;
	private String xJson;
	private String yJson;
	private String xyJson;
	private String channelidJson;
	private String valueId;
	private String channelidStr;
	private Long channelidOne;
	private List<IrpChannel> documentMapOne;
	private List<IrpChannel> documentMapTwo;
	private List<IrpChannel> documentMapThree;
	private List<IrpChannel> documentMapOther;
	private long maptype;
	private String maptypeName;
	private int sysExport;
	private int sysQuestion;
	private int sysQueCom;
	private String oneName;
	private String twoName;
	private List<IrpDocument> documentOne;
	private List<IrpDocument> documentTwo;
	private List<IrpDocument> documentThree;

	
	public List<IrpDocument> getDocumentOne() {
		return documentOne;
	}

	public void setDocumentOne(List<IrpDocument> documentOne) {
		this.documentOne = documentOne;
	}


	public List<IrpDocument> getDocumentTwo() {
		return documentTwo;
	}

	public void setDocumentTwo(List<IrpDocument> documentTwo) {
		this.documentTwo = documentTwo;
	}

	public List<IrpDocument> getDocumentThree() {
		return documentThree;
	}

	public void setDocumentThree(List<IrpDocument> documentThree) {
		this.documentThree = documentThree;
	}

	public String getOneName() {
		return oneName;
	}

	public void setOneName(String oneName) {
		this.oneName = oneName;
	}

	public String getTwoName() {
		return twoName;
	}

	public void setTwoName(String twoName) {
		this.twoName = twoName;
	}

	public int getSysQueCom() {
		return sysQueCom;
	}

	public void setSysQueCom(int sysQueCom) {
		this.sysQueCom = sysQueCom;
	}

	public int getSysQuestion() {
		return sysQuestion;
	}

	public void setSysQuestion(int sysQuestion) {
		this.sysQuestion = sysQuestion;
	}

	public List<IrpChannel> getDocumentMapOther() {
		return documentMapOther;
	}

	public void setDocumentMapOther(List<IrpChannel> documentMapOther) {
		this.documentMapOther = documentMapOther;
	}

	public List<IrpChannel> getDocumentMapOne() {
		return documentMapOne;
	}

	public void setDocumentMapOne(List<IrpChannel> documentMapOne) {
		this.documentMapOne = documentMapOne;
	}

	public List<IrpChannel> getDocumentMapTwo() {
		return documentMapTwo;
	}

	public void setDocumentMapTwo(List<IrpChannel> documentMapTwo) {
		this.documentMapTwo = documentMapTwo;
	}

	public List<IrpChannel> getDocumentMapThree() {
		return documentMapThree;
	}

	public void setDocumentMapThree(List<IrpChannel> documentMapThree) {
		this.documentMapThree = documentMapThree;
	}

	public Long getChannelidOne() {
		return channelidOne;
	}

	public void setChannelidOne(Long channelidOne) {
		this.channelidOne = channelidOne;
	}

	public String getChannelidStr() {
		return channelidStr;
	}

	public void setChannelidStr(String channelidStr) {
		this.channelidStr = channelidStr;
	}

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId;
	}

	public String getxName() {
		return xName;
	}

	public void setxName(String xName) {
		this.xName = xName;
	}

	public String getyName() {
		return yName;
	}

	public void setyName(String yName) {
		this.yName = yName;
	}

	public String getxJson() {
		return xJson;
	}

	public void setxJson(String xJson) {
		this.xJson = xJson;
	}

	public String getyJson() {
		return yJson;
	}

	public void setyJson(String yJson) {
		this.yJson = yJson;
	}

	public String getXyJson() {
		return xyJson;
	}

	public void setXyJson(String xyJson) {
		this.xyJson = xyJson;
	}

	public String getChannelidJson() {
		return channelidJson;
	}

	public void setChannelidJson(String channelidJson) {
		this.channelidJson = channelidJson;
	}

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}

	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}

	public List<IrpMessageContent> getIrpMessageContentlist() {
		return irpMessageContentlist;
	}

	public void setIrpMessageContentlist(
			List<IrpMessageContent> irpMessageContentlist) {
		this.irpMessageContentlist = irpMessageContentlist;
	}

	public IrpWorkFlowService getIrpWorkFlowService() {
		return irpWorkFlowService;
	}

	public void setIrpWorkFlowService(IrpWorkFlowService irpWorkFlowService) {
		this.irpWorkFlowService = irpWorkFlowService;
	}

	public List<Map<String, Object>> getFlowInfos() {
		return flowInfos;
	}

	public void setFlowInfos(List<Map<String, Object>> flowInfos) {
		this.flowInfos = flowInfos;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public int getCountnum() {
		return countnum;
	}

	public void setCountnum(int countnum) {
		this.countnum = countnum;
	}

	/**
	 * 新的知识库首页
	 * @return
	 */
	public String documentIndex() {
		//获得系统中的用户和知识数量
		sysUserCount = irpUserService.findAllUserCountByStatus(IrpUser.USER_STATUS_REG);
		sysDocumentCount = irpDocumentService.findAllDocumentCount();
		HashMap<String,Object> map1=new HashMap<String, Object>();  
		String categoryQuestionId = SysConfigUtil.getSysConfigValue("CATEGORY_QUESTION");
		map1.put("parentid", categoryQuestionId);
		list=irpCategoryService.findCategoryByConditions(map1);
		sysExport=0;
		if(list!=null){
			sysExport=list.size();
		}
		Map<String,Object> map3 = new HashMap<String, Object>();
		map3.put("needDeal", "complete");
		map3.put("categoryId", 0);
		try {
			sysQuestion = irpQuestionService.findAllQuestionsInCategory(map3);
			sysQueCom = irpQuestionService.findAllExpertAnswersCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*//获得热门专家
		int nDataCount = irpRoleService.findUsersCountByRoleId((long) 2, null, null);
		PageUtil pageUtil1 = new PageUtil(this.pageNum, 5, nDataCount);
		listExpert=irpUserService.findHotExpertList(categoryId,pageUtil1);*/
		PageUtil pageUtil2 = new PageUtil(pageNum, pageSize, 0);
		String sOrderByClause = "DOCID desc";
		String s="";
		List<Long> _channelIds1 = new ArrayList<Long>();
		s=SysConfigUtil.getSysConfigValue("HEAD_QUESTION");
		_channelIds1.add(Long.parseLong(s));//元数据16
		List<Long> _channelIds2 = new ArrayList<Long>();
		s=SysConfigUtil.getSysConfigValue("HEAD_ZHUANTI");
		_channelIds2.add(Long.parseLong(s));
		List<Long> _channelIds3 = new ArrayList<Long>();
		s=SysConfigUtil.getSysConfigValue("HEAD_BAIKE");
		_channelIds3.add(Long.parseLong(s));
		List<Long> _channelIds4 = new ArrayList<Long>();
		s=SysConfigUtil.getSysConfigValue("HEAD_BIG_IMAGE");
		_channelIds4.add(Long.parseLong(s));//首页元数据15
		List<Long> _channelIds5 = new ArrayList<Long>();
		s=SysConfigUtil.getSysConfigValue("HEAD_ZHUANJIA");
		_channelIds5.add(Long.parseLong(s));
		chnlDocLinks15=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds4, sOrderByClause, pageUtil2);
		//您可能遇到的问题
		chnlDocLinks16=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds1, sOrderByClause, pageUtil2);
		//最新专题
		chnlDocLinks21=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds2, sOrderByClause, pageUtil2);
		//百科
		chnlDocLinks22=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds3, sOrderByClause, pageUtil2);
		chnlDocLinks18=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds5, sOrderByClause, pageUtil2);
		//知识库的知识
		s=SysConfigUtil.getSysConfigValue("HEAD_WEIBO");
		List<IrpChannel> list=irpChannelService.findChanlsByParentid(Long.parseLong(s));
		if(list!=null&&list.size()>0){
			pageUtil2 = new PageUtil(pageNum, SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"), 0);
			_channelIds4.clear();
			 oneName=list.get(0).getChnldesc();
			_channelIds4.add(list.get(0).getChannelid());
			newChnlDocs=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
					IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds4, sOrderByClause, pageUtil2);
			if(list.size()>=1){
				_channelIds4.clear();
				 twoName=list.get(1).getChnldesc();
				_channelIds4.add(list.get(1).getChannelid());
				hotChnlDocs=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
						IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds4, sOrderByClause, pageUtil2);
			}
			
		}
		//您关注的人的动态
		Long nUserid = LoginUtil.getLoginUserId();
		pageUtil2 = new PageUtil(pageNum, 10, 0);
		irpMicrobloglist= irpMicroBlogService.findMicroblogOfFocus(IrpMicroblog.ISDELFALSE, nUserid,pageUtil2,LoginUtil.getLoginUserId());
		count = irpWorkFlowService.findUserFlowInfoCountByUserId(nUserid);
		flowInfos = irpWorkFlowService.findUserFlowInfoByUserId(nUserid, pageUtil2);
		countnum=this.irpMessageContentService.findMessageByUserIdCount(0,nUserid,null,0);
		irpMessageContentlist =this.irpMessageContentService.findMessageByUserId(0,nUserid,pageUtil2,null,0);
		return SUCCESS;
	}
	/**
	 * 更具用户id获得用户的对象
	 * 
	 * @param _cruserid
	 * @return
	 */
	public IrpUser getIrpUserByUserid(long _cruserid) {
		IrpUser irpUser = null;
		irpUser = this.irpUserService.findUserByUserId(_cruserid);
		return irpUser;

	}
	/**
	 * 知识推荐
	 * @return
	 */
	public String docPush() {
		//个人标签
	List<IrpTag> irpTags = irpTagService.findTagsByUserId(LoginUtil.getLoginUserId());
	if(irpTags!=null&&irpTags.size()>0){
		StringBuffer sbBuffer=new StringBuffer();
		for (IrpTag irpTag : irpTags) {
			sbBuffer.append(irpTag.getTagname()+",");
		}
		pushDocs=findSearchResultByDocumentEprise(pushDocs,sbBuffer.toString());
				}
		return SUCCESS;
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
	 * 知识推荐
	 * @return
	 */
	public String docAllPush() {
		//个人标签
		List<IrpTag> irpTags = irpTagService.findTagsByUserId(LoginUtil.getLoginUserId());
		if(irpTags!=null&&irpTags.size()>0){
			StringBuffer sbBuffer=new StringBuffer();
			for (IrpTag irpTag : irpTags) {
				sbBuffer.append(irpTag.getTagname()+",");
			}
			pageSize=5;
			pushDocs=findSearchResultByDocumentEprise(pushDocs,sbBuffer.toString());
		}
		return SUCCESS;
	}
	/**
	 * 搜知识(企业)
	 * @return
	 */
	public List<IrpDocument> findSearchResultByDocumentEprise(List<IrpDocument> documentlist,String searchInfo){
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
				String paramMap="";
				Map<String,String> paramM = new HashMap<String, String>();
				if(StringUtil.isEmpty(paramMap)){
					paramMap = "ALL";
				}
				paramM.put("CRTIME", paramMap);
				int docnum = this.solrService.searchDocumentnumByParam(searchInfo, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),stringBuffer==null?null:stringBuffer.toString(),paramM);
				if(docnum>0){
					PageUtil pageUtildocument = new PageUtil(this.pageNum,this.pageSize,docnum);
					documentlist= this.solrService.searchDocumentByParam(searchInfo,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),pageUtildocument,1,stringBuffer==null?null:stringBuffer.toString(),paramM);
					this.pageHTML = pageUtildocument.getClientPageHtml("allMydocPush(#PageNum#)");
				}
				} catch (SolrServerException e) {
					e.printStackTrace();
				}
		}
		return documentlist;
	}
	
	
	/**
	 * 根据父栏目ID获得子栏目集合，(知识库首页)
	 * @param _nParentId
	 * @return
	 */
	public List<IrpChannel> findChildChannelByParentId(long _nParentId){
		List<IrpChannel> childChannels = irpChannelService.findChannelsByParentId(_nParentId, IrpChannel.IS_DOCSTATIUS, "chnlorder asc");
		return childChannels;
	}
	
	/**
	 * 用户积分排行
	 * @return
	 */
	public String userRank() {
		irpUsers = irpUserService.userRankByDate(dateType);
		return SUCCESS;
	}
	
	/**
	 * 知识编辑
	 * @return
	 */
	public String documentEdit() {
		IrpUser loginUser = LoginUtil.getLoginUser();
		RightUtil rightUtil = new RightUtil();
		IrpChannel rightChannle = new IrpChannel();
		//密级
		String dense =SysConfigUtil.getSysConfigValue("DENSE") ;
		denseMap=new HashMap<Long, String>();
		if(dense!=null){
			String[] denseArray=dense.split(";");
			if(denseArray!=null&&denseArray.length>0){
				for (String string : denseArray) {
					String[] denseArrayTwo=string.split(":");
					if(denseArrayTwo!=null&&denseArrayTwo.length>0){
						denseMap.put(Long.parseLong(denseArrayTwo[0]), denseArrayTwo[1]);
					}
				}
			}
		}
		//根据DOCID判断是修改还是添加
		if(this.docid!=null && this.docid>0){
			toUpdate = "true";
			irpDocument = irpDocumentService.findDocumentByDocId(this.docid);// 当前需要修改的文档信息
			
			if (irpDocument == null) {
				friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
				return ERROR;
			}
			if (irpDocument.getChannelid() < 0) {
				friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
				return ERROR;
			}
			IrpSite site = irpSiteService.siteInfo(irpDocument.getSiteid());
			if (site == null) {
				friendlyshow = IrpSite.SITE_NOT_SAVE;
				return ERROR;
			}
			if (site.getSitetype() == IrpSite.SITE_TYPE_PRIVATE) {
				friendlyshow = IrpDocument.DOCUMENT_IS_ERROR;
				return ERROR;
			}
			long nChannelId = irpDocument.getChannelid();
			//验证知识修改权限
			rightChannle.setChannelid(nChannelId);
			Long nOperTypeId = rightUtil.findOperTypeIdByKey("DOCUMENT_UPDATE");
			if(!loginUser.isAdministrator() && loginUser.getUserid().longValue()!=irpDocument.getCruserid().longValue() && !rightUtil.right(rightChannle, nOperTypeId)){
				friendlyshow = IrpDocument.DOCUMENT_NO_UPDATE_RIGHT;
				return ERROR;
			}
			
			// 获得当前文档栏目信息
			this.chnldesc = irpChannelService.findChannelNameByChannelid(nChannelId);
			// 获得当前文档栏目的全路径
			List<Long> channelidList = new ArrayList<Long>();
			channelidList = irpChannelService.allparentidList(nChannelId,
					channelidList, IrpChannel.IS_DOCSTATIUS, loginUser);
			if (channelidList != null && channelidList.size() > 0) {
				for (Long long1 : channelidList) {
					if (this.allChannelIds == null
							|| this.allChannelIds.length() == 0) {
						this.allChannelIds = long1.toString();
					} else {
						this.allChannelIds += "," + long1.toString();
					}
				}
			}
			documentMapList = irpDocumentService.docDocumentMap(this.docid,IrpDocumentMap.KONWLEDGE_MAP);
			
			irpChannels = this.checkifhad(this.docid, irpChannels);
		}else{
			this.loginuserprivacy = irpUserPrivacy();
			// 获得当前栏目的全路径
			if (this.id != null && this.id > 0) {
				//如果选择的栏目ID验证权限
				rightChannle.setChannelid(id);
				Long nOperTypeId = rightUtil.findOperTypeIdByKey("DOCUMENT_ADD");
				if(!loginUser.isAdministrator() && !rightUtil.right(rightChannle, nOperTypeId)){
					friendlyshow = IrpDocument.DOCUMENT_NO_ADD_RIGHT;
					return ERROR;
				}
				
				// 获得当前文档栏目信息
				this.chnldesc = irpChannelService.findChannelNameByChannelid(this.id);
				List<Long> channelidList = new ArrayList<Long>();
				channelidList = irpChannelService.allparentidList(this.id, channelidList, IrpChannel.IS_DOCSTATIUS, loginUser);
				if (channelidList != null && channelidList.size() > 0) {
					for (Long long1 : channelidList) {
						if (this.allChannelIds == null || this.allChannelIds.length() == 0) {
							this.allChannelIds = long1.toString();
						} else {
							this.allChannelIds += "," + long1.toString();
						}
					}
				} else {
					this.friendlyshow = IrpChannel.CHANNEL_TO_ADD_DOC;
					return ERROR;
				}
			}
			//获得用户所属的组织
			userGroups = irpGroupService.findGroupIdsByUserId(loginUser.getUserid());
		}
		
		/*//获得扩展维度分类
		documentMap = irpChannelService.allDocumentMap(IrpChannel.IS_DOCSTATIUS);*/
		documentMapOne = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_ONE);//一维地图
		documentMapTwo = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_TWO);//二维地图
		documentMapThree = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_THREE);//三维地图
		documentMapOther = irpChannelService.documentMapByMaptype(IrpChannel.CHANNEL_TYPE_MAP_OTHER);//其他
		//获取企业专题
		initAllQiyeSubject();
		return SUCCESS;
	}
	
	/**
	 * 知识专家推荐列表
	 * @return
	 */
	public String findDocInformList() {
		if(this.docid==null || this.docid==0L){
			return ERROR;
		}
		// 查看这个知识呗所有专家加精的集合
		docClassicls = irpInformService.findAllInformByExpert(IrpInform.INFORMJIAJINGDOC, this.docid);
		return SUCCESS;
	}
	
	/**
	 * 知识概览页面
	 * @return
	 */
	public String documentList() {
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
		IrpSite site=null;
		if(channelid==null || channelid.intValue()==0){
			//查询当前栏目同级的栏目
			irpChannels = irpChannelService.findRightChannelsByParentId(0L, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_PUBLIC, nOperTypeId, "chnlorder asc");
			if(irpChannels!=null && irpChannels.size()>0){
				irpChannel = irpChannels.get(0);
				channelid = irpChannel.getChannelid();
			}
		}else{
			irpChannel = irpChannelService.finChannelByChannelid(channelid);
			if(irpChannel==null || irpChannel.getStatus().intValue()==IrpChannel.NO_DOCSTATUS.intValue()){
		    	friendlyshow=IrpChannel.CHANNEL_IS_DELETE;
		    	return ERROR;
		    }
			 site=irpSiteService.siteInfo(irpChannel.getSiteid());
	 		if(site==null || site.getStatus().intValue()==IrpSite.SITE_NOT_NORMAL){
	 			friendlyshow=IrpSite.SITE_NOT_SAVE;
	 			return ERROR;
	 		}
	 		irpChannels = irpChannelService.findRightChannelsByParentId(irpChannel.getParentid(), IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_PUBLIC, nOperTypeId, "chnlorder asc");
		}
		if(irpChannel==null){
			return SUCCESS;
		}
 		
		//验证栏目查看权限
 		IrpUser loginUser = LoginUtil.getLoginUser();
 		if(!loginUser.isAdministrator()){
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpChannel.ERROR_INFO_CHANNEL_SELECT;
 		    	return ERROR;
 			}
 			//验证栏目文章列表查看权限
 			nOperTypeId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpDocument.DOCUMENT_NO_RIGHT;
 		    	return ERROR;
 			}
 		}
 		
 		//获得栏目全路径
		rootChannels = irpChannelService.currentChannels(channelid, rootChannels, 0L);
		
		List<Long> _arrChannelIds = new ArrayList<Long>();
		_arrChannelIds.add(channelid);
		
		//获得当前栏目下的精华知识
		PageUtil essencePageUtil = new PageUtil(1, 2, 2);
		essenceDocs=irpChnl_Doc_LinkService.findEssenceDocs(channelid, 1, essencePageUtil);
		
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "docpubtime desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		String string=SysConfigUtil.getSysConfigValue("CHANNEL_CHILDREN");
		long type=0;
		if(string!=null&&!string.equals("")){
			type=Long.parseLong(string);
		}
		PageUtil pageUtil=null;
		if(type==0){
			
			int aDataCount=irpChnl_Doc_LinkService.clientSelectChnlDocCount("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
					IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds, sOrderByClause);
			int nPageSize = SysConfigUtil.getSysConfigNumValue("APPPAGESIZE");
			 pageUtil = new PageUtil(this.pageNum, nPageSize, aDataCount);
			chnlDocLinks=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
					IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds, sOrderByClause, pageUtil);
		}else{
			 HashMap<String,Object> map=new HashMap<String, Object>(); 
			 map.put("docstatus",IrpDocument.DRAFT_STATUS); 
			 irpChannel = irpChannelService.finChannelByChannelid(channelid);
				if(irpChannel==null || irpChannel.getStatus().intValue()==IrpChannel.NO_DOCSTATUS.intValue()){
			    	friendlyshow=IrpChannel.CHANNEL_IS_DELETE;
			    	return ERROR;
			    }
				 site=irpSiteService.siteInfo(irpChannel.getSiteid());
		 		if(site==null || site.getStatus().intValue()==IrpSite.SITE_NOT_NORMAL){
		 			friendlyshow=IrpSite.SITE_NOT_SAVE;
		 			return ERROR;
		 		}
			_arrChannelIds=irpChannelService.findChannelIdsByParentId(channelid, _arrChannelIds,site.getSitetype(),IrpChannel.IS_DOCSTATIUS,LoginUtil.getLoginUser());
				map.put("channelidsList", _arrChannelIds);
			int aDataCount=irpChnl_Doc_LinkService.selectCountByChannelidAndDocStatus(map,IrpDocument.DOCUMENT_NOT_INFORM,site.getSitetype());   
			 pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
			chnlDocLinks=irpChnl_Doc_LinkService.chanAllDocLink(pageUtil,map,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM,site.getSitetype()); 
			
			
		}

 		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		return SUCCESS;
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
	 * 获得图片
	 * @param _docid
	 * @return
	 */
	public String coverPath(String attachedids){
		String filePath="";
		String[] _attachedid=attachedids.split(",");
		for(int j=0;j<_attachedid.length;j++){
			Long attachedid=Long.parseLong(_attachedid[j]);
			IrpAttached attached=irpAttachedService.getAttachedByPrimary(attachedid); 
			if(attached!=null){
				String myFileName=attached.getAttfile(); 
				//获得文件路径 
				filePath= ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="+ThumbnailPic.searchFileName(myFileName, "_300X300");
			}else{
				filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand1.jpg";
			} 
		}
		return filePath;
	}
	
	/**
	 * 获得图片原图
	 * @param _docid
	 * @return
	 */
	public String coverPathSource(String attachedids){
		String filePath="";
		String[] _attachedid=attachedids.split(",");
		for(int j=0;j<_attachedid.length;j++){
			Long attachedid=Long.parseLong(_attachedid[j]);
			IrpAttached attached=irpAttachedService.getAttachedByPrimary(attachedid); 
			if(attached!=null){
				String myFileName=attached.getAttfile(); 
				//获得文件路径 
				filePath= ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="+ThumbnailPic.searchFileName(myFileName, "");
			}else{
				filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand1.jpg";
			} 
		}
		return filePath;
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
	
	
	/**
	 * 原图
	 * @param _docid
	 * @return
	 */
	public String docCoverPathSource(Long _docid, Integer docflag){
		String filePath="";
		IrpAttached attached=irpAttachedService.getIrpATttachedByDocIDFIle(_docid); 
		if(attached!=null){
			String myFileName=attached.getAttfile(); 
			//获得文件路径 
			filePath= ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="+ThumbnailPic.searchFileName(myFileName, "");
		}else if(docflag!=null && docflag>0){
			filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand"+docflag+".jpg";
		}else{
			filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand1.jpg";
		} 
		return filePath;
	}
	
	public IrpUser findIrpUserByFocusId(long _userid){
		return this.irpUserService.findUserByUserId(_userid);
	}
	/**
	 * 知识地图列表页面
	 * @return
	 */
	public String documentMapListResoure() {
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("MAP_SELECT");
		long nMapRootId = Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID"));
		
		channelidleft=channelid;
		if(channelid==null){
			if(channelidss!=null&&!channelidss.equals("")){
				channelid=Long.parseLong(channelidss.split(",")[0]);
				rootChannels = irpChannelService.currentChannels(channelid, rootChannels, nMapRootId);
				channelidleft=rootChannels.get(0).getChannelid();
				rootChannels.clear();
			}
		}else{
			if(isLeft==1){
				channelidleft=irpChannelService.finChannelByChannelid(channelid).getParentid();
			}
			
		}
		if(channelid==null || channelid.intValue()==0){
			//查询当前栏目同级的栏目
			irpChannels = irpChannelService.findRightChannelsByParentId(nMapRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
			if(irpChannels!=null && irpChannels.size()>0){
				irpChannel = irpChannels.get(0);
				channelid = irpChannel.getChannelid();
			}
		}else{
			irpChannel = irpChannelService.finChannelByChannelid(channelid);
			if(irpChannel==null || irpChannel.getStatus().intValue()==IrpChannel.NO_DOCSTATUS.intValue()){
		    	friendlyshow=IrpChannel.CHANNEL_IS_DELETE;
		    	return ERROR;
		    }
			IrpSite site=irpSiteService.siteInfo(irpChannel.getSiteid());
	 		if(site==null || site.getStatus().intValue()==IrpSite.SITE_NOT_NORMAL){
	 			friendlyshow=IrpSite.SITE_NOT_SAVE;
	 			return ERROR;
	 		}
	 		irpChannels = irpChannelService.findRightChannelsByParentId(irpChannel.getParentid(), IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
		}
		if(irpChannel==null){
			return SUCCESS;
		}
 		
 		IrpUser loginUser = LoginUtil.getLoginUser();
		//验证栏目查看权限
 		if(!loginUser.isAdministrator()){
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpChannel.ERROR_INFO_CHANNEL_SELECT;
 		    	return ERROR;
 			}
 		}
 		//获得栏目全路径
		rootChannels = irpChannelService.currentChannels(channelid, rootChannels, nMapRootId);
		//判断该目录是几维栏目
		if(channelidleft==null){
			channelidleft=channelid;
		}
		countChannel=JiWeiChannel(rootChannels,0,channelidleft);
		 if(countChannel==2){
			getJsonData(rootChannels);
			channelidss=valueId;
		 }
		if(countChannel==1){
			List<IrpChannel> list= findChildMapByParentId(channelid);
			if(list!=null&&list.size()>0){
				channelidOne=list.get(0).getChannelid();
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 知识地图列表页面
	 * @return
	 */
	public String documentMapList() {
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("MAP_SELECT");
		long nMapRootId = Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID"));
		

		if(channelid==null || channelid.intValue()==0){
			//查询当前栏目同级的栏目
			irpChannels = irpChannelService.findRightChannelsByParentId(nMapRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
			if(irpChannels!=null && irpChannels.size()>0){
				irpChannel = irpChannels.get(0);
				channelid = irpChannel.getChannelid();
			}
		}else{
			//irpChannel = irpChannelService.finChannelByChannelid(channelid);
			irpChannel = irpChannelService.finChannelByChannelidAndChalType(channelid,IrpChannel.CHANNEL_TYPE_MAP);
			if(irpChannel==null || irpChannel.getStatus().intValue()==IrpChannel.NO_DOCSTATUS.intValue()){
		    	friendlyshow=IrpChannel.CHANNEL_IS_DELETE;
		    	return ERROR;
		    }
			IrpSite site=irpSiteService.siteInfo(irpChannel.getSiteid());
	 		if(site==null || site.getStatus().intValue()==IrpSite.SITE_NOT_NORMAL){
	 			friendlyshow=IrpSite.SITE_NOT_SAVE;
	 			return ERROR;
	 		}
			irpChannels=irpChannelService.findRightChannelsByMaptype(maptype,nMapRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
	 		//irpChannels = irpChannelService.findRightChannelsByParentId(irpChannel.getParentid(), IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
		}
		if(irpChannel==null){
			return SUCCESS;
		}
 		
 		IrpUser loginUser = LoginUtil.getLoginUser();
		//验证栏目查看权限
 		if(!loginUser.isAdministrator()){
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpChannel.ERROR_INFO_CHANNEL_SELECT;
 		    	return ERROR;
 			}
 		}
 		//获得栏目全路径
		rootChannels = irpChannelService.currentChannels(channelid, rootChannels, nMapRootId);
		//判断该目录是几维栏目
		if(channelidleft==null){
			channelidleft=channelid;
		}
		
		countChannel=JiWeiChannel(rootChannels,0,channelidleft);
		 if(maptype==IrpChannel.CHANNEL_TYPE_MAP_ONE){
				maptypeName="一维知识地图";
				if(countChannel!=1){
					countChannel=0;
				}else{
					countChannel=1;
				}
			}else if(maptype==IrpChannel.CHANNEL_TYPE_MAP_TWO){
				maptypeName="二维知识地图";
				
				if(countChannel!=2){
					countChannel=0;
				}else{
					countChannel=2;
				}
			}else if(maptype==IrpChannel.CHANNEL_TYPE_MAP_THREE){
				maptypeName="三维知识地图";
				
				if(countChannel!=0){
					countChannel=3;
				}else{
					countChannel=0;
				}
			}else if(maptype==IrpChannel.CHANNEL_TYPE_MAP_OTHER){
				maptypeName="其他知识地图";
				countChannel=4;
			}
		 
		 if(countChannel==2){
				getJsonData(rootChannels);
				 
			 }
		if(countChannel==1){
			List<IrpChannel> list= findChildMapByParentId(channelid);
			if(list!=null&&list.size()>0){
				channelidOne=list.get(0).getChannelid();
			}
		}else if(countChannel==2){
				channelidss=valueId;
		}
		return SUCCESS;
	}
	


	/**
	 * 获取地图类型根据父节点获取字节点
	 * @param nMapRootId
	 * @param nOperTypeId
	 * @param idStringBuffer
	 * @return
	 */
	private StringBuffer findRightChannelsByParentId(long nMapRootId,
			long nOperTypeId, StringBuffer idStringBuffer) {
		
		idStringBuffer.append(nMapRootId+",");
		List<IrpChannel> irpList = irpChannelService.findRightChannelsByParentId(nMapRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
		if(irpList!=null&&irpList.size()>0){
			for (IrpChannel irpChannel : irpList) {
				nMapRootId=irpChannel.getChannelid();
				idStringBuffer.append(nMapRootId+",");
				idStringBuffer=findRightChannelsByParentId(nMapRootId, nOperTypeId,idStringBuffer);
			}
		}
		return idStringBuffer;
	}

	private void getJsonData(List<IrpChannel> irpChannel) {
		StringBuffer xJsonBuffer =new StringBuffer();
		StringBuffer yJsonBuffer =new StringBuffer();
		StringBuffer xyJsonBuffer =new StringBuffer();
		StringBuffer channelidJsonBuffer =new StringBuffer();
		long rootId=irpChannel.get(0).getChannelid();
		List<IrpChannel> list_1=findChildMapByParentId(rootId);
		if(list_1!=null&&list_1.size()>=2){
			xName=list_1.get(0).getChnlname();
			yName=list_1.get(1).getChnlname();
			List<IrpChannel> list_x=findChildMapByParentId(list_1.get(0).getChannelid());
			List<IrpChannel> list_y=findChildMapByParentId(list_1.get(1).getChannelid());
			if(list_x!=null&&list_x.size()>0&&list_y!=null&&list_y.size()>0){
				long count=0;
				for ( int i = 0; i < list_x.size(); i++) {
					IrpChannel x =list_x.get(i);
					xJsonBuffer.append("'"+x.getChnlname()+"',");
					for (int j = 0; j < list_y.size(); j++) {
						IrpChannel y =list_y.get(j);
						String idsString=x.getChannelid()+","+y.getChannelid();
						chnlDocLinks=irpChnl_Doc_LinkService.findIrpChnlDocLinksByMapChannelIds(idsString, 1, null, null);
						if(chnlDocLinks!=null&&chnlDocLinks.size()>0){
							count=chnlDocLinks.size();
						}
						xyJsonBuffer.append("["+i+","+j+","+count+"],");
						channelidJsonBuffer.append("["+i+","+j+","+x.getChannelid()+","+y.getChannelid()+"],");
						if(i==0){
							yJsonBuffer.append("'"+y.getChnlname()+"',");
						}
						if(i==0&&j==0){
						 valueId=x.getChannelid()+","+y.getChannelid();
						}
						count=0;
					}
				}
			}
			
			xJson="["+xJsonBuffer.subSequence(0, xJsonBuffer.length()-1)+"]";
			 yJson="["+yJsonBuffer.subSequence(0, yJsonBuffer.length()-1)+"]";
			 xyJson="["+xyJsonBuffer.subSequence(0, xyJsonBuffer.length()-1)+"]";
			 channelidJson="["+channelidJsonBuffer.subSequence(0, channelidJsonBuffer.length()-1)+"]";
		}
	}

	private int JiWeiChannel(List<IrpChannel> rootChannels2,int index,Long channelid2) {
		if(rootChannels2!=null&&rootChannels2.size()>0){
			int i=0;
			int j=0;
			List<IrpChannel> rootChannels1=findChildMapByParentId(channelid2);//第一节点孩子
			if(rootChannels1!=null&&rootChannels1.size()>0){
				for (IrpChannel irpChannel : rootChannels1) {
					j++;
					List<IrpChannel> rootChannels=findChildMapByParentId(irpChannel.getChannelid());
					if(rootChannels!=null&&rootChannels.size()>0){
						i++;
					}
				}
			}else{
				return 0;
			}
			if(j>=0&&i==0){
				return 1;
			}
			if(j==2&&i<=2&&i>=1){//二维地图
				return 2;
			}
			/*if(j>=3&&i<=1){
				return 1;
			}
*/			if(j>=3&&i>0){
				return 3;
			}
		}
		return 0;
	}

	/**
	 * 二维或者多维知识地图显示的列表
	 * 知识地图列表页面
	 * @return
	 */
	public String documentMapListPage() {
	
		if(channelidss!=null&&!channelidss.equals("")){
			//获得当前地图栏目的有权限的DOCIDS
			int aDataCount=0;
			int nPageSize = SysConfigUtil.getSysConfigNumValue("APPPAGESIZE");
			chnlDocLinks=irpChnl_Doc_LinkService.findIrpChnlDocLinksByMapChannelIds(channelidss, 1, null, null);
			if(chnlDocLinks!=null&&chnlDocLinks.size()>0){
				aDataCount=chnlDocLinks.size();
			}
			PageUtil pageUtil = new PageUtil(this.pageNum, nPageSize,aDataCount);
			chnlDocLinks=irpChnl_Doc_LinkService.findIrpChnlDocLinksByMapChannelIds(channelidss, 1, null, pageUtil);
			this.pageHTML = pageUtil.getClientPageHtml("listPage(#PageNum#)");
		}
		return SUCCESS;
	}
	/**
	 * 一维
	 * 知识地图列表页面
	 * @return
	 */
	public String documentMapOneListPage() {
		if(channelidOne!=null){
			//获得当前地图栏目的有权限的DOCIDS
			int aDataCount=irpChnl_Doc_LinkService.findIrpChnlDocLinksCountByMapChannelId(channelidOne, 1);
			int nPageSize = SysConfigUtil.getSysConfigNumValue("APPPAGESIZE");
			PageUtil pageUtil = new PageUtil(this.pageNum, nPageSize, aDataCount);
			chnlDocLinks=irpChnl_Doc_LinkService.findIrpChnlDocLinksByMapChannelId(channelidOne, 1, null, pageUtil);
			this.pageHTML = pageUtil.getClientPageHtml("pageOne(#PageNum#)"); 
		}
		return SUCCESS;
	}
	
	/**
	 * 多维知识地图显示父节点以及子节点文章的列表
	 * 知识地图列表页面
	 * @return
	 */
	public String documentMapAllListPage() {
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("MAP_SELECT");
		long nMapRootId = Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID"));
		if (channelid!=null) {
			//获取该节点下的所有子节点
			if(channelidStr==null||channelidStr.equals("")){
				StringBuffer idStringBuffer=new StringBuffer();
				nMapRootId=channelid;
				idStringBuffer=findRightChannelsByParentId(nMapRootId,nOperTypeId,idStringBuffer);
				channelidStr=idStringBuffer.toString();
				channelidStr=channelidStr.substring(0,channelidStr.length()-1);
			}
			//获取该父节点下的所有文章
			int aDataCount=irpChnl_Doc_LinkService.findIrpChnlDocLinksCountByMapParentIds(channelidStr, 1);
			int nPageSize = SysConfigUtil.getSysConfigNumValue("APPPAGESIZE");
			PageUtil pageUtil = new PageUtil(this.pageNum, nPageSize, aDataCount);
			chnlDocLinks=irpChnl_Doc_LinkService.findIrpChnlDocLinksByMapParentIds(channelidStr, 1, null, pageUtil);
			this.pageHTML = pageUtil.getClientPageHtml("pageAll(#PageNum#)"); 
		}
		return SUCCESS;
	}
	/**
	 * 地图首页，获取不同类型的地图一级栏目
	 * @return
	 */
	public String documentMapIndex() {
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("MAP_SELECT");
		long nMapRootId = Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID"));
		documentMapOne = irpChannelService.findRightChannelsByMaptype(IrpChannel.CHANNEL_TYPE_MAP_ONE,nMapRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
		documentMapTwo = irpChannelService.findRightChannelsByMaptype(IrpChannel.CHANNEL_TYPE_MAP_TWO,nMapRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
		documentMapThree = irpChannelService.findRightChannelsByMaptype(IrpChannel.CHANNEL_TYPE_MAP_THREE,nMapRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
		documentMapOther = irpChannelService.findRightChannelsByMaptype(IrpChannel.CHANNEL_TYPE_MAP_OTHER,nMapRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
		if((documentMapOne==null||documentMapOne.size()>0)&&(documentMapOther==null||documentMapOther.size()>0)&&(documentMapThree==null||documentMapThree.size()>0)&&(documentMapTwo==null||documentMapTwo.size()>0)){
				friendlyshow="您没有地图栏目权限，请联系管理员";
		    	return ERROR;
			}
		return SUCCESS;
	}
	/**
	 * 按照类别显示知识地图
	 * @return
	 */
	public String documentMapWei() {
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("MAP_SELECT");
		long nMapRootId = Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID"));
		if(maptype==IrpChannel.CHANNEL_TYPE_MAP_ONE){
			maptypeName="一维知识地图";
		}else if(maptype==IrpChannel.CHANNEL_TYPE_MAP_TWO){
			maptypeName="二维知识地图";
		}else if(maptype==IrpChannel.CHANNEL_TYPE_MAP_THREE){
			maptypeName="多维知识地图";
		}else if(maptype==IrpChannel.CHANNEL_TYPE_MAP_OTHER){
			maptypeName="聚类知识地图";
		}
		irpChannels=irpChannelService.findRightChannelsByMaptype(maptype,nMapRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
		return SUCCESS;
	}
	
	/**
	 * 知识地图列表页面
	 * @return
	 */
	public String documentMap() {
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("MAP_SELECT");
		long nMapRootId = Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID"));
		if(channelid==null || channelid.intValue()==0){
			//查询当前栏目同级的栏目
			irpChannels = irpChannelService.findRightChannelsByParentId(nMapRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
			if(irpChannels!=null && irpChannels.size()>0){
				irpChannel = irpChannels.get(0);
				channelid = irpChannel.getChannelid();
			}
		}else{
			irpChannel = irpChannelService.finChannelByChannelid(channelid);
			if(irpChannel==null || irpChannel.getStatus().intValue()==IrpChannel.NO_DOCSTATUS.intValue()){
		    	friendlyshow=IrpChannel.CHANNEL_IS_DELETE;
		    	return ERROR;
		    }
	 		irpChannels = irpChannelService.findRightChannelsByParentId(irpChannel.getParentid(), IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
		}
		if(irpChannel==null){
			return SUCCESS;
		}
 		
 		IrpUser loginUser = LoginUtil.getLoginUser();
		//验证栏目查看权限
 		if(!loginUser.isAdministrator()){
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpChannel.ERROR_INFO_CHANNEL_SELECT;
 		    	return ERROR;
 			}
 		}
 		//获得栏目全路径
		rootChannels = irpChannelService.currentChannels(channelid, rootChannels, nMapRootId);
		
		//获得当前地图栏目的有权限的DOCIDS
		int aDataCount=irpChnl_Doc_LinkService.findIrpChnlDocLinksCountByMapChannelId(channelid, 2);
		int nPageSize = SysConfigUtil.getSysConfigNumValue("APPPAGESIZE");
		PageUtil pageUtil = new PageUtil(this.pageNum, nPageSize, aDataCount);
		chnlDocLinks=irpChnl_Doc_LinkService.findIrpChnlDocLinksByMapChannelId(channelid, 2, null, pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		return SUCCESS;
	}
	
	
	public List<IrpChnlDocLink> getChnlDocLinks29() {
		return chnlDocLinks29;
	}

	public void setChnlDocLinks29(List<IrpChnlDocLink> chnlDocLinks29) {
		this.chnlDocLinks29 = chnlDocLinks29;
	}

	public List<IrpChnlDocLink> getChnlDocLinks30() {
		return chnlDocLinks30;
	}

	public void setChnlDocLinks30(List<IrpChnlDocLink> chnlDocLinks30) {
		this.chnlDocLinks30 = chnlDocLinks30;
	}

	//新知识地图首页
	public String docSubjectList() {
		PageUtil pageUtil1 = new PageUtil(1, 10, 10);
		String s="";
		List<Long> _arrChannelIds = new ArrayList<Long>();
		s=SysConfigUtil.getSysConfigValue("SUBJECT_HOT_DOOR");
		_arrChannelIds.add(Long.parseLong(s));//29
		List<Long> _channelIds = new ArrayList<Long>();
		s=SysConfigUtil.getSysConfigValue("SUBJECT_TUIJIAN");
		_channelIds.add(Long.parseLong(s));//30
		String sOrderByClause = "docpubtime desc";
		String orderByClause = "hitscount desc";
		List<Long> _channelIds4 = new ArrayList<Long>();
		s=SysConfigUtil.getSysConfigValue("HEAD_ZHUANTI");
		_channelIds4.add(Long.parseLong(s));//专题大图
		chnlDocLinks52=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds4, sOrderByClause, pageUtil1);
		chnlDocLinks29=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds, orderByClause, pageUtil1);
		chnlDocLinks30=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds, sOrderByClause, pageUtil1);
		
		
		
		
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("SUBJECT_SELECT");
		//long nSubjectRootId=Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_SUBJECT_ID"));
		long nSubjectRootId=Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_QIYE_SUBJECT_ID"));
		if(channelid==null || channelid.intValue()==0){
			//查询当前栏目同级的栏目
			irpChannels = irpChannelService.findRightChannelsByParentId(nSubjectRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_SUBJECT, nOperTypeId, "chnlorder asc");
			if(irpChannels!=null && irpChannels.size()>0){
				irpChannel = irpChannels.get(0);
				channelid = irpChannel.getChannelid();
			}
		}else{
			irpChannel = irpChannelService.finChannelByChannelid(channelid);
			if(irpChannel==null || irpChannel.getStatus().intValue()==IrpChannel.NO_DOCSTATUS.intValue()){
		    	friendlyshow=IrpChannel.CHANNEL_IS_DELETE;
		    	return ERROR;
		    }
	 		irpChannels = irpChannelService.findRightChannelsByParentId(irpChannel.getParentid(), IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_SUBJECT, nOperTypeId, "chnlorder asc");
		}
		if(irpChannel==null){
			return SUCCESS;
		}
 		
 		IrpUser loginUser = LoginUtil.getLoginUser();
		//验证栏目查看权限
 		if(!loginUser.isAdministrator()){
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpChannel.ERROR_INFO_CHANNEL_SELECT;
 		    	return ERROR;
 			}
 		}
 		//获得栏目全路径
		rootChannels = irpChannelService.currentChannels(channelid, rootChannels, nSubjectRootId);
		int aDataCount=irpChnl_Doc_LinkService.findIrpChnlDocLinksCountByMapChannelId(channelid, 2);
		int nPageSize = SysConfigUtil.getSysConfigNumValue("APPPAGESIZE");
		PageUtil pageUtil = new PageUtil(this.pageNum, nPageSize, aDataCount);
		//
		
		chnlDocLinks=irpChnl_Doc_LinkService.findIrpChnlDocLinksByMapChannelId(channelid, 2, null, pageUtil);
		//获得当前地图栏目的有权限的DOCIDS
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		return SUCCESS;
	}
	
	/**
	 * 鐭ヨ瘑鍦板浘鍒楄〃椤甸潰
	 * @return
	 */
	public String documentSubjectList() {
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("SUBJECT_SELECT");
		long nSubjectRootId=Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_QIYE_SUBJECT_ID"));
		if(channelid==null || channelid.intValue()==0){
			//鏌ヨ褰撳墠鏍忕洰鍚岀骇鐨勬爮鐩�
			irpChannels = irpChannelService.findRightChannelsByParentId(nSubjectRootId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_SUBJECT, nOperTypeId, "chnlorder asc");
			if(irpChannels!=null && irpChannels.size()>0){
				irpChannel = irpChannels.get(0);
				channelid = irpChannel.getChannelid();
			}
		}else{
			irpChannel = irpChannelService.finChannelByChannelid(channelid);
			if(irpChannel==null || irpChannel.getStatus().intValue()==IrpChannel.NO_DOCSTATUS.intValue()){
		    	friendlyshow=IrpChannel.CHANNEL_IS_DELETE;
		    	return ERROR;
		    }
	 		irpChannels = irpChannelService.findRightChannelsByParentId(irpChannel.getParentid(), IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_SUBJECT, nOperTypeId, "chnlorder asc");
		}
		if(irpChannel==null){
			return SUCCESS;
		}
 		
 		IrpUser loginUser = LoginUtil.getLoginUser();
		//楠岃瘉鏍忕洰鏌ョ湅鏉冮檺
 		if(!loginUser.isAdministrator()){
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpChannel.ERROR_INFO_CHANNEL_SELECT;
 		    	return ERROR;
 			}
 		}
 		//鑾峰緱鏍忕洰鍏ㄨ矾寰�
		rootChannels = irpChannelService.currentChannels(channelid, rootChannels, nSubjectRootId);
		
		//鑾峰緱褰撳墠鍦板浘鏍忕洰鐨勬湁鏉冮檺鐨凞OCIDS
		int aDataCount=irpChnl_Doc_LinkService.findIrpChnlDocLinksCountByMapChannelId(channelid, 2);
		int nPageSize = SysConfigUtil.getSysConfigNumValue("APPPAGESIZE");
		PageUtil pageUtil = new PageUtil(this.pageNum, nPageSize, aDataCount);
		chnlDocLinks=irpChnl_Doc_LinkService.findIrpChnlDocLinksByMapChannelId(channelid, 2, null, pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		return SUCCESS;
	}
	/**
	 * 根据父地图ID获得子地图集合
	 * @param _nParentId
	 * @return
	 */
	public List<IrpChannel> findChildSubjectByParentId(long _nParentId){
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("MAP_SELECT");
		List<IrpChannel> childChannels = irpChannelService.findRightChannelsByParentId(_nParentId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_SUBJECT, nOperTypeId, "chnlorder asc");
		return childChannels;
	}
	
	/**
	 * 精华知识列表
	 * @return
	 */
	public String documentEssenceList() {
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
		if(channelid==null || channelid.intValue()==0){
			//查询当前栏目同级的栏目
			irpChannels = irpChannelService.findRightChannelsByParentId(0L, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_PUBLIC, nOperTypeId, "chnlorder asc");
			if(irpChannels!=null && irpChannels.size()>0){
				irpChannel = irpChannels.get(0);
				channelid = irpChannel.getChannelid();
			}
		}else{
			irpChannel = irpChannelService.finChannelByChannelid(channelid);
			if(irpChannel==null || irpChannel.getStatus().intValue()==IrpChannel.NO_DOCSTATUS.intValue()){
		    	friendlyshow=IrpChannel.CHANNEL_IS_DELETE;
		    	return ERROR;
		    }
			IrpSite site=irpSiteService.siteInfo(irpChannel.getSiteid());
	 		if(site==null || site.getStatus().intValue()==IrpSite.SITE_NOT_NORMAL){
	 			friendlyshow=IrpSite.SITE_NOT_SAVE;
	 			return ERROR;
	 		}
	 		irpChannels = irpChannelService.findRightChannelsByParentId(irpChannel.getParentid(), IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_PUBLIC, nOperTypeId, "chnlorder asc");
		}
		if(irpChannel==null){
			return SUCCESS;
		}
		//验证栏目查看权限
 		IrpUser loginUser = LoginUtil.getLoginUser();
 		if(!loginUser.isAdministrator()){
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpChannel.ERROR_INFO_CHANNEL_SELECT;
 		    	return ERROR;
 			}
 			//验证栏目文章列表查看权限
 			nOperTypeId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpDocument.DOCUMENT_NO_RIGHT;
 		    	return ERROR;
 			}
 		}
 		//获得栏目全路径
		rootChannels = irpChannelService.currentChannels(channelid, rootChannels, 0L);
		
		//获得当前栏目下的精华知识
		PageUtil essencePageUtil = new PageUtil(1, 2, 2);
		essenceDocs=irpChnl_Doc_LinkService.findEssenceDocs(channelid, 1, essencePageUtil);
 		int aDataCount=irpChnl_Doc_LinkService.countExpertRecommendDocs(channelid);
 		int nPageSize = SysConfigUtil.getSysConfigNumValue("APPPAGESIZE");
 		PageUtil pageUtil = new PageUtil(this.pageNum, nPageSize, aDataCount);
 		chnlDocLinks=irpChnl_Doc_LinkService.findExpertRecommendDocs(channelid, pageUtil);
 		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		return SUCCESS;
	}
	
	/**
	 * 智能抽取
	 */
	public void autoExtract() {
		if(irpDocument.getDoctitle()==null){
			irpDocument.setDoctitle("");
		}
		if(irpDocument.getDoccontent()==null){
			irpDocument.setDoccontent("");
		}
		String sKeyWords = new TextRankKeyword().getKeyword(irpDocument.getDoctitle(), "");
		List<String> arrAbstracts = TextRankSummary.getTopSentenceList(irpDocument.getDoccontent(), 1);
		String sAbstract = "";
		if(arrAbstracts!=null && !arrAbstracts.isEmpty())
			sAbstract = arrAbstracts.get(0);
		Map<String, String> map = new HashMap<String, String>();
		map.put("dockeywords", sKeyWords.trim());
		map.put("docabstract", sAbstract.trim());
		ActionUtil.writer(JsonUtil.map2json(map));
	}
	
	public String documentLogsList() {
		int nPageSize = SysConfigUtil.getSysConfigNumValue("APPPAGESIZE");
		int nDataCount = irpDocumentLogsService.countIrpDocumentLogsByDocId(docid);
		PageUtil pageUtil = new PageUtil(this.pageNum, nPageSize, nDataCount);
		docLogs = irpDocumentLogsService.findIrpDocumentLogsByDocId(docid, pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("docLogsPage(#PageNum#)");
		return SUCCESS;
	}
	
	public String similarityDocument() {
		irpDocument = irpDocumentService.findDocumentByDocId(docid);
		if(irpDocument==null)
			return ERROR;
		List<IrpDocument> documentlist = irpDocumentService.similarityDocByDocId(docid);
		List<Long> idsList=new ArrayList<Long>();
		idsList.add(docid);
		documentList=new ArrayList<IrpDocument>();
		if(documentlist!=null){
			int l=documentlist.size()>5? 5:documentlist.size();
			for (int i = 0; i <l; i++) {
				idsList.add(documentlist.get(i).getDocid());
				documentList.add(documentlist.get(i));
			}
		}
		
		 documentOne=new ArrayList<IrpDocument>();
		 documentTwo=new ArrayList<IrpDocument>();
		 documentThree=new ArrayList<IrpDocument>();
		String valueString=irpDocument.getDockeywords();
		if(valueString!=null&&!valueString.equals("")){
			String[] ValueArray =valueString.split(",");
			//id=ValueArray.length;
			if(ValueArray.length>=1){
				documentOne=searchIrpDocumentBykeyword(ValueArray[0], idsList, 10);
				for (int i = 0; i < documentOne.size(); i++) {
					idsList.add(documentOne.get(i).getDocid());
				}
			}
			if(ValueArray.length>=2){
				documentTwo=searchIrpDocumentBykeyword(ValueArray[1], idsList, 15);
				for (int i = 0; i < documentTwo.size(); i++) {
					idsList.add(documentTwo.get(i).getDocid());
				}
			}
			if(ValueArray.length>=3){
				documentThree=searchIrpDocumentBykeyword(ValueArray[2], idsList, 20);
				for (int i = 0; i < documentThree.size(); i++) {
					idsList.add(documentThree.get(i).getDocid());
				}
			}
		}
		
		return SUCCESS;
	}
	public List<IrpChannel> findChildMapByParentId(long _nParentId){
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("MAP_SELECT");
		List<IrpChannel> childChannels = irpChannelService.findRightChannelsByParentId(_nParentId, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_MAP, nOperTypeId, "chnlorder asc");
		return childChannels;
	}
	
	
	public List<IrpDocument> searchIrpDocumentBykeyword(String searchInfo,List<Long> idsList,int size){
		PageUtil pageUtil = new PageUtil(0, size, size);
		List<IrpDocument> documentOne=new ArrayList<IrpDocument>();
		List<SolrDocument> documentlist=null;
		try {
		      documentlist= this.solrService.searchDocumentByKeyword(searchInfo,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),pageUtil,null,null,null);
		      if(documentlist!=null){
		    	  int len=0;
				 for (SolrDocument solrDocument  : documentlist) {
		            	try {
		            		String DOCID=solrDocument.getFieldValue("DOCID").toString();
		            		int sim=0;
		            		for (Long id : idsList) {
								if(DOCID.equals(id.toString())){
									sim++;
								}
							}
		            		if(sim==0&&len<5){
		            			IrpDocument document = new IrpDocument();
		            			document.setDocid(Long.valueOf(solrDocument.getFieldValue("DOCID").toString()));
		            			document.setDoctitle(stringSolrField(solrDocument.getFieldValue("DOCTITLE")));
		            			documentOne.add(document);
		            			len++;
		            		}
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
		      }
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return documentOne;
	}
	
	
	private String stringSolrField(Object _fieldValue){
		if(_fieldValue==null || _fieldValue.toString().isEmpty() || _fieldValue.toString().indexOf("[")!=0 || _fieldValue.toString().indexOf("]")!=(_fieldValue.toString().length()-1))
			return _fieldValue==null?"":_fieldValue.toString();
		else
			return _fieldValue.toString().substring(1, _fieldValue.toString().length()-1);
	}
}
