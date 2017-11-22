package com.tfs.irp.mobile.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

































































import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.bug.entity.IrpBug;
import com.tfs.irp.bug.entity.IrpBugExample;
import com.tfs.irp.bug.entity.IrpBugWithBLOBs;
import com.tfs.irp.bug.service.BugService;
import com.tfs.irp.bug_config.entity.IrpBugConfig;
import com.tfs.irp.bug_config.entity.IrpBugConfigExample;
import com.tfs.irp.bug_config.service.BugConfigService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.docrecommend.entity.IrpDocrecommend;
import com.tfs.irp.docrecommend.service.IrpDocrecommendService;
import com.tfs.irp.docscore.entity.IrpDocumentScore;
import com.tfs.irp.docscore.service.DocScoreService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.documentlogs.service.IrpDocumentLogsService;
import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.service.IrpGoodsService;
import com.tfs.irp.inform.entity.IrpInform;
import com.tfs.irp.inform.service.IrpInformService;
import com.tfs.irp.informtype.entity.IrpInformType;
import com.tfs.irp.informtype.service.IrpInformTypeService;
import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.leaveapply.service.IrpLeaveapplyService;
import com.tfs.irp.leaveoper.service.IrpLeaveoperService;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.medal.service.IrpMedalService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.entity.IrpMicroblogView;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogcollection.service.IrpMicroblogCollectionKeyService;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.project.entity.IrpProjectExample;
import com.tfs.irp.project.service.IrpProjectService;
import com.tfs.irp.projectperson.entity.IrpProjectPerson;
import com.tfs.irp.projectperson.entity.IrpProjectPersonExample;
import com.tfs.irp.projectperson.service.IrpProjectPersonService;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.service.IrpQuestionService;
import com.tfs.irp.selectkey.service.IrpSelectKeyService;
import com.tfs.irp.sign.entity.IrpSignInfo;
import com.tfs.irp.sign.entity.IrpSignInfoExample;
import com.tfs.irp.sign.entity.IrpSignInfoExample.Criteria;
import com.tfs.irp.sign.service.SignService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.subscribe.entity.IrpSubscribe;
import com.tfs.irp.subscribe.service.IrpSubscribeService;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.term.entity.IrpTerm;
import com.tfs.irp.term.service.IrpTermService;
import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.usergoodslink.entity.IrpUserCovertGoods;
import com.tfs.irp.usergoodslink.service.IrpUserCovertGoodsService;
import com.tfs.irp.userlove.entity.IrpUserLove;
import com.tfs.irp.userlove.service.IrpUserLoveService;
import com.tfs.irp.usermedal.entity.IrpUserMedal;
import com.tfs.irp.usermedal.service.IrpUserMedalService;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.service.IrpUserPrivacyService;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.AtmeUtil;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.RightUtil;
import com.tfs.irp.util.SignUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.ThumbnailPic;
import com.tfs.irp.util.wordfilter.TermFilterDoc;
import com.tfs.irp.value.entity.IrpValueConfig;
import com.tfs.irp.value.service.IrpValueConfigService;
import com.tfs.irp.vote.entity.IrpVote;
import com.tfs.irp.vote.service.IrpVoteService;
import com.tfs.irp.voteoptions.entity.IrpVoteOptions;
import com.tfs.irp.voteoptions.service.IrpVoteOptionsService;
import com.tfs.irp.workflow.service.IrpWorkFlowService;

public class mobileAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IrpUserService irpUserService;// 用户service
	private IrpDocumentService irpDocumentService;// 文档service
	private IrpChannelService irpChannelService;// 栏目service
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;// 文档栏目中间表service
	private IrpUserLoveService irpUserLoveService;
	private IrpTagService irpTagService;
	private IrpUserPrivacyService irpUserPrivacyService;
	private IrpSiteService irpSiteService;// 站点service
	private IrpValueConfigService irpValueConfigService;
	private IrpUserValueLinkService irpUserValueLinkService;
	private IrpDocumentLogsService irpDocumentLogsService;
	private IrpAttachedService irpAttachedService;// 附件表service
	private IrpInformService irpInformService;// 举报表service
	private DocScoreService irpDocumentScoreService;
	private IrpSubscribeService irpSubscribeService;
	private SignService signService;// 签到逻辑
	private IrpProjectPersonService projectpersonservice;
	private IrpProjectService projectService;
	private IrpDocrecommendService irpDocrecommendServiceImpl;
	private BugService bugService;
	private IrpUserService userService;
	private BugConfigService bugconfigservice;
	private long sysUserCount;
	private long sysDocumentCount;
	private List<IrpChannel> rootChannels;
	private List<IrpChnlDocLink> newChnlDocs;
	private List<IrpChnlDocLink> hotChnlDocs;
	private List<IrpChnlDocLink> valuableChnlDocs;
	private List<IrpChnlDocLink> collectionChnlDocs;
	private List<Map<String, IrpBaseObj>> commentsData;
	private List<IrpChnlDocLink> essenceDocs;
	private List<IrpChnlDocLink> likeDocs;
	private Map<String, List<IrpTag>> indexTags;
	private String searchWord;
	private int pageSizeclient=20;
	private String sname;
	private int pageNum=1;
	private String pageHTML;
	private Integer loginuserprivacy;
	private String friendlyshow;
	private Long id = new Long(-1);
	private IrpDocumentWithBLOBs irpDocument;// 文档表
	private IrpChannel irpChannel;// 栏目表
	private List<IrpAttached> attacheds;// 附件列表
	private IrpInform docClassicl;
	private String isDelete;
	private String isUpdate;
	private String isPriant;
	private String isFuJian;
	private String isHistoryversion;
	private String isXiangGuan;
	private String isCrUser;
	private Long loginUserId;
	private String maxAmount;
	private String isScore;
	private int subscribe;
	IrpSignInfo irpSignInfo = new IrpSignInfo();
	private Long channelid;
	private String orderField;
	private String orderBy;
	private List<IrpChnlDocLink> chnlDocLinks;// 文档栏目中间表
	private List<IrpChannel> irpChannels;// 前台当前用户的所有栏目包括一级栏目和二级栏目
	private Long projectId;//所属项目ID
	private Long tabPid=0l;
	private List<IrpProject> irpProjects;
	private Long docid;//文档id
	private Long replayUserId;//回复的回复id
	private String recommend ;//回复内容
	private String tokens;
	private List<IrpDocrecommend> irpDocrecommends;
	private int pageSize=10; 
	private Long loginuserid;//获取当前登录用户的id
	private Map<String, String> priorityMap = new HashMap<String, String>();//优先级Map
	private Long projectIdTop;
	private Long projectIdBottom;
	private int pageNumTop = 1;// 当前页
	private int pageSizeTop = SysConfigUtil.getSysConfigNumValue(IrpBug.PAGE_SIZE_TOW);// 每页数量
	private List<IrpBugWithBLOBs> urgentBugs;//紧急待处理的Bug
	private String pageHTMLTop;
	private Map<Long, String> operatorNameMap = new HashMap<Long, String>();//处理人trueName Map
	private int pageNumBottom = 1;// 当前页
	private int pageSizeBottom = SysConfigUtil.getSysConfigNumValue(IrpBug.PAGE_SIZE_TOW);;// 每页数量
	private List<IrpBugWithBLOBs> newestBugs;//最新Bug
	private String pageHTMLBottom;
	private Map<Long, String> founderNameMap = new HashMap<Long, String>();//分配人trueName Map
	private String ordertop="priority desc,createtime desc";//排序条件top
	private List<IrpBugWithBLOBs> tomeBugsWei;//分配给我的未解决Bug
	private String orderbottom="priority desc,createtime desc";//排序条件top
	private List<IrpBugWithBLOBs> tomeBugsWan;//分配给我的已完成Bug
	private Map<Long, String> meFounderNameMap = new HashMap<Long, String>();//我完成的bug发起人trueName Map
	private Map<Long,String> versionNameMap=new HashMap<Long, String>();
	private Map<Long,String> modulNameMap=new HashMap<Long, String>();
	private String state;//状态 :已创建/未处理/待审核/已完成
	private String order="priority desc,createtime desc";//排序条件
	private List<IrpBugWithBLOBs> irpBugs;//Bug集合
	private String headType;// 页面状态 ?所有/我创建/我处理/我待办
	private String nstate;//统计跳转所有Bug页面的状态
	private String stateType = "0";
	private String queryType;
	private List<IrpUser> projectUsers;//项目成员
	private List<IrpBugConfig> bugversions;
	private List<IrpBugConfig> bugmoduls=new ArrayList<IrpBugConfig>();
	private int seltype;
	private String projectPersonsJson;
	private String userid;
	private IrpMicroblogService irpMicroBlogService;
	private IrpMicroblogCollectionKeyService irpMicroblogCollectionKeyService;
	private List<String> irpMicrobloglist;
	private List<String> collectionOfUseridlist;
	private List<IrpInformType> irpInformType;
	private IrpInformTypeService irpInformTypeService;
	private IrpGoodsService irpGoodsService;
	private List<Map<String,Object>> listgoods;
	private Long coverstate;
	private IrpUserCovertGoodsService irpUserCovertGoodsService;
	private IrpMessageContentService irpMessageContentService;
	private IrpUserMedalService irpUserMedalService;
	private IrpQuestionService irpQuestionService;
	private String informKey;
	private String sitetype;
	private List jubaodocuments;
	private String askcontext;
	private String expert;
	private Long epertuserid;
	private String categories;
	private IrpVoteOptionsService irpVoteOptionsService;
	String searchwords;
	private IrpTermService irpTermService;
	private Long qclassifyid;
	private List<IrpQuestion> listQuestion;
	private IrpUser irpUser;
	private int questionShowNum;
	private String loginUsername;
	private List<IrpUserCovertGoods> listcovertrecord;
	private IrpWorkFlowService irpWorkFlowService;
	private List<Map<String, Object>> flowInfos;
	
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
	public List<IrpUserCovertGoods> getListcovertrecord() {
		return listcovertrecord;
	}

	public void setListcovertrecord(List<IrpUserCovertGoods> listcovertrecord) {
		this.listcovertrecord = listcovertrecord;
	}
	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public int getQuestionShowNum() {
		return questionShowNum;
	}

	public void setQuestionShowNum(int questionShowNum) {
		this.questionShowNum = questionShowNum;
	}

	public List<IrpQuestion> getListQuestion() {
		return listQuestion;
	}

	public void setListQuestion(List<IrpQuestion> listQuestion) {
		this.listQuestion = listQuestion;
	}

	public IrpUser getIrpUser() {
		return irpUser;
	}

	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}
	
	public String getSearchwords() {
		return searchwords;
	}

	public Long getQclassifyid() {
		return qclassifyid;
	}

	public void setQclassifyid(Long qclassifyid) {
		this.qclassifyid = qclassifyid;
	}

	public void setSearchwords(String searchwords) {
		this.searchwords = searchwords;
	}
	
	public IrpTermService getIrpTermService() {
		return irpTermService;
	}

	public void setIrpTermService(IrpTermService irpTermService) {
		this.irpTermService = irpTermService;
	}
	
	public IrpVoteOptionsService getIrpVoteOptionsService() {
		return irpVoteOptionsService;
	}

	public void setIrpVoteOptionsService(IrpVoteOptionsService irpVoteOptionsService) {
		this.irpVoteOptionsService = irpVoteOptionsService;
	}
	
	public String getAskcontext() {
		return askcontext;
	}

	public void setAskcontext(String askcontext) {
		this.askcontext = askcontext;
	}

	public String getExpert() {
		return expert;
	}

	public void setExpert(String expert) {
		this.expert = expert;
	}

	
	public Long getEpertuserid() {
		return epertuserid;
	}

	public void setEpertuserid(Long epertuserid) {
		this.epertuserid = epertuserid;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}
	
	public IrpQuestionService getIrpQuestionService() {
		return irpQuestionService;
	}

	public void setIrpQuestionService(IrpQuestionService irpQuestionService) {
		this.irpQuestionService = irpQuestionService;
	}

	public String getInformKey() {
		return informKey;
	}

	public void setInformKey(String informKey) {
		this.informKey = informKey;
	}

	public String getSitetype() {
		return sitetype;
	}

	public void setSitetype(String sitetype) {
		this.sitetype = sitetype;
	}

	public List getJubaodocuments() {
		return jubaodocuments;
	}

	public void setJubaodocuments(List jubaodocuments) {
		this.jubaodocuments = jubaodocuments;
	}

	public IrpUserCovertGoodsService getIrpUserCovertGoodsService() {
		return irpUserCovertGoodsService;
	}
	private List<IrpMedal> listMedal;
	private IrpMedalService irpMedalService;
	private IrpGoods irpGoods;
	private String attachedids;
	private IrpVoteService irpVoteService;
	
	
	public IrpVoteService getIrpVoteService() {
		return irpVoteService;
	}

	public void setIrpVoteService(IrpVoteService irpVoteService) {
		this.irpVoteService = irpVoteService;
	}

	public List<IrpMedal> getListMedal() {
		return listMedal;
	}

	public void setListMedal(List<IrpMedal> listMedal) {
		this.listMedal = listMedal;
	}

	public IrpMedalService getIrpMedalService() {
		return irpMedalService;
	}

	public void setIrpMedalService(IrpMedalService irpMedalService) {
		this.irpMedalService = irpMedalService;
	}

	public IrpGoods getIrpGoods() {
		return irpGoods;
	}

	public void setIrpGoods(IrpGoods irpGoods) {
		this.irpGoods = irpGoods;
	}

	public String getAttachedids() {
		return attachedids;
	}

	public void setAttachedids(String attachedids) {
		this.attachedids = attachedids;
	}

	public void setIrpUserCovertGoodsService(
			IrpUserCovertGoodsService irpUserCovertGoodsService) {
		this.irpUserCovertGoodsService = irpUserCovertGoodsService;
	}

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}

	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}

	public IrpUserMedalService getIrpUserMedalService() {
		return irpUserMedalService;
	}

	public void setIrpUserMedalService(IrpUserMedalService irpUserMedalService) {
		this.irpUserMedalService = irpUserMedalService;
	}
	
	
	public Long getCoverstate() {
		return coverstate;
	}

	public void setCoverstate(Long coverstate) {
		this.coverstate = coverstate;
	}

	public List<Map<String,Object>> getListgoods() {
		return listgoods;
	}

	public void setListgoods(List<Map<String,Object>> listgoods) {
		this.listgoods = listgoods;
	}

	public IrpGoodsService getIrpGoodsService() {
		return irpGoodsService;
	}

	public void setIrpGoodsService(IrpGoodsService irpGoodsService) {
		this.irpGoodsService = irpGoodsService;
	}

	public IrpInformTypeService getIrpInformTypeService() {
		return irpInformTypeService;
	}

	public void setIrpInformTypeService(IrpInformTypeService irpInformTypeService) {
		this.irpInformTypeService = irpInformTypeService;
	}

	public List<IrpInformType> getIrpInformType() {
		return irpInformType;
	}

	public void setIrpInformType(List<IrpInformType> irpInformType) {
		this.irpInformType = irpInformType;
	}

	public List<String> getCollectionOfUseridlist() {
		return collectionOfUseridlist;
	}

	public void setCollectionOfUseridlist(List<String> collectionOfUseridlist) {
		this.collectionOfUseridlist = collectionOfUseridlist;
	}

	public List<String> getIrpMicrobloglist() {
		return irpMicrobloglist;
	}

	public void setIrpMicrobloglist(List<String> irpMicrobloglist) {
		this.irpMicrobloglist = irpMicrobloglist;
	}

	public IrpMicroblogCollectionKeyService getIrpMicroblogCollectionKeyService() {
		return irpMicroblogCollectionKeyService;
	}

	public void setIrpMicroblogCollectionKeyService(
			IrpMicroblogCollectionKeyService irpMicroblogCollectionKeyService) {
		this.irpMicroblogCollectionKeyService = irpMicroblogCollectionKeyService;
	}

	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}

	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getShowPageViewNameByUserId(Long _userid){
    	return this.irpUserService.findShowNameByUserid(_userid);
    }
	public IrpUser getIrpUserByUserid(long _cruserid) {
		IrpUser irpUser = null;
		irpUser = this.irpUserService.findUserByUserId(_cruserid);
		return irpUser;
	}
	/**
	 * 知识库最新
	 * @return
	 */
	public void toDocumentIndex_new() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		//获得系统中的用户和知识数量
		sysUserCount = irpUserService.findAllUserCountByStatus(IrpUser.USER_STATUS_REG);
		sysDocumentCount = irpDocumentService.findAllDocumentCount();
		//获得首页的三级栏目结构
		rootChannels = irpChannelService.allRightChannel();
		//最新知识
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause"," docpubtime desc ");
 		List<Long> _arrChannelIds=new ArrayList<Long>();
 		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		IrpUser irpuser = mobileAction.getlogin(token);
		//用户id为1(admin用户)的默认设置成系统管理员
		if(irpuser.getUserid()==1L){
			irpuser.setAdministrator(true);
		}
		_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS,0L, IrpSite.SITE_TYPE_PUBLISH, irpuser);
 		map.put("channelidsList", _arrChannelIds);
 		newChnlDocs=irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM, 5);
 		for(int i = 0;i<newChnlDocs.size();i++){
 			newChnlDocs.get(i).setDocpuburl(docCoverPath(newChnlDocs.get(i).getDocid(),newChnlDocs.get(i).getDocflag()));
		}
 		String jsonString_new = JSON.toJSONString(newChnlDocs);
		ActionUtil.writer(jsonString_new);
	}
	
	/**
	 * 知识库最热
	 * @return
	 */
	public void toDocumentIndex_hot() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		//获得系统中的用户和知识数量
		sysUserCount = irpUserService.findAllUserCountByStatus(IrpUser.USER_STATUS_REG);
		sysDocumentCount = irpDocumentService.findAllDocumentCount();
		//获得首页的三级栏目结构
		rootChannels = irpChannelService.allRightChannel();
		//最新知识
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause"," docpubtime desc ");
 		List<Long> _arrChannelIds=new ArrayList<Long>();
 		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		IrpUser irpuser = mobileAction.getlogin(token);
		//用户id为1(admin用户)的默认设置成系统管理员
		if(irpuser.getUserid()==1L){
			irpuser.setAdministrator(true);
		}
		_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS,0L, IrpSite.SITE_TYPE_PUBLISH, irpuser);
 		map.put("channelidsList", _arrChannelIds);
 		//最热知识
 		map.remove("sOrderByClause");
		map.put("sOrderByClause"," hitscount desc ");
		hotChnlDocs=irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM, 5);
		for(int i = 0;i<hotChnlDocs.size();i++){
			hotChnlDocs.get(i).setDocpuburl(docCoverPath(hotChnlDocs.get(i).getDocid(),hotChnlDocs.get(i).getDocflag()));
		}
		String jsonString_hot = JSON.toJSONString(hotChnlDocs);
		ActionUtil.writer(jsonString_hot);
	}
	
	
	/**
	 * 知识库最有价值
	 * @return
	 */
	public void toDocumentIndex_valuable() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		//获得系统中的用户和知识数量
		sysUserCount = irpUserService.findAllUserCountByStatus(IrpUser.USER_STATUS_REG);
		sysDocumentCount = irpDocumentService.findAllDocumentCount();
		//获得首页的三级栏目结构
		rootChannels = irpChannelService.allRightChannel();
		//最新知识
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause"," docpubtime desc ");
 		List<Long> _arrChannelIds=new ArrayList<Long>();
 		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		IrpUser irpuser = mobileAction.getlogin(token);
		//用户id为1(admin用户)的默认设置成系统管理员
		if(irpuser.getUserid()==1L){
			irpuser.setAdministrator(true);
		}
		_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS,0L, IrpSite.SITE_TYPE_PUBLISH, irpuser);
 		map.put("channelidsList", _arrChannelIds);
		//最有价值
		map.remove("sOrderByClause");
		map.put("sOrderByClause"," docscore desc ");
		valuableChnlDocs=irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM,5);
		for(int i = 0;i<valuableChnlDocs.size();i++){
			valuableChnlDocs.get(i).setDocpuburl(docCoverPath(valuableChnlDocs.get(i).getDocid(),valuableChnlDocs.get(i).getDocflag()));
		}
		String jsonString_valuable = JSON.toJSONString(valuableChnlDocs);
		ActionUtil.writer(jsonString_valuable);
	}
	
	public void loginDowith_app() {
		IrpUser irpuser = new IrpUser();
		HttpServletRequest request = ServletActionContext.getRequest();
		String uname=request.getParameter("uname");

		String upwd = request.getParameter("upwd");//tfsadmin
		irpuser.setUsername(uname);
		irpuser.setPassword(upwd);
		int nLoginStatus = irpUserService.login(irpuser);
		if(nLoginStatus>0){
			IrpUser irpu = irpUserService.findUserByUsername(uname).get(0);
			JSONObject j = JSON.parseObject("{\"status\":\"" + nLoginStatus +"\",\"token\":\""+irpu.getToken()+"\"}");
			ActionUtil.writer(j.toJSONString());
		}else{
			JSONObject j = JSON.parseObject("{\"status\":\"" + nLoginStatus +"\"}");
			ActionUtil.writer(j.toJSONString());
		}
	}
	
	public static IrpUser getlogin(String token){
		//IrpUserDAO irpUserDAO = (IrpUserDAO) ApplicationContextHelper.getContext().getBean("irpUserDAO");
		IrpUserService irpUserService = (IrpUserService) ApplicationContextHelper.getContext().getBean("irpUserService");
		IrpUser loginUser = null;
		List<IrpUser> listuser = irpUserService.getByToken(token);
		if (listuser!=null && listuser.isEmpty()==false) {
			loginUser = listuser.get(0);
		}else{
			
			loginUser = null;
		} 
		return loginUser;
	}
	
	/**
	 * 用户积分排行
	 */
	public void userRank() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int dateType = Integer.valueOf(request.getParameter("dateType"));
		List<IrpUser> irpUsers = new ArrayList<IrpUser>();
		irpUsers = irpUserService.userRankByDate(dateType);
		String jifen = JSON.toJSONString(irpUsers);
		ActionUtil.writer(jifen);
	}
	/**
	 * 猜你喜欢
	 */
	public void guess(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		List<Long> docIds=irpUserLoveService.docIdsByUserid(mobileAction.getlogin(token).getUserid(),IrpUserLove.CAI_YOU_LIKE);
		if(docIds!=null &&docIds.size()>0){
		likeDocs=irpChnl_Doc_LinkService.chnlDocByDocIds_token(docIds,IrpDocument.DOCUMENT_NOT_INFORM, 5,token);
		String guess = JSON.toJSONString(likeDocs);
		ActionUtil.writer(guess);
		} 
	}
	private IrpMicroblogFocusService  irpMicroblogFocusService;
	
	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}

	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}
	
    /**
     * getAddressBookForMoblie:安卓手机获取通讯录用户接口
     * 
     * @author yijin  
     * @since JDK 1.8
     */
    public void getAddressBookForMoblie() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String token = request.getParameter("token");
        IrpUser irpuser = mobileAction.getlogin(token);
        Long userid = irpuser.getUserid();

        // 查询用户数量
        int result = irpUserService.findUserbySMcount("", null);

        // 创建分页对象
        PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSizeclient, result);

        // 查询所有通讯录用户
        List<IrpUser> irpUsers = irpUserService.findUserbySM("", null, pageUtil);

        // 存放分类数据的集合
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        // 获得当前登录用户所关注的用户列表
        List irpMicroblogFocuslist = this.irpMicroblogFocusService.selectUseridByLoginuserId(userid);

        // 创建一个以大写字母为键的集合
        Map<String, List<Map<String, Object>>> initialMap = new HashMap<String, List<Map<String, Object>>>();
        
        // 创建一个用于分类的字母数组
        String[] words = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };
        
        for (String word : words) {
            for (int i = 0; i < irpUsers.size(); i++) {
                // 如果用户名的首字母等于一个字母，将其填入集合
                if (word.equals(irpUsers.get(i).getUsersm())) {
                    // 存放头像、名称、关注信息和id等相关用户信息
                    Map<String, Object> userMap = new HashMap<String, Object>();

                    userMap.put("addrebookphone", irpUsers.get(i).getMobile());
                    userMap.put("addrebooksex", irpUsers.get(i).getSex());
                    userMap.put("addrebookmail", irpUsers.get(i).getEmail());
                    userMap.put("addrebookarea", irpUsers.get(i).getLocation());

                    if (irpUsers.get(i).getUserpic() != null) {
                        // 头像不为空给头像加token
                        userMap.put("addrebookimg", irpUsers.get(i).getUserpic() + "&token="
                                + irpUsers.get(i).getToken());
                    } else {
                        //给默认头像
                        userMap.put("addrebookimg", irpUsers.get(i).getDefaultUserPic());
                    }

                    // 名称
                    userMap.put("addrebookname", irpUsers.get(i).getTruename());

                    // id
                    userMap.put("addrebookuserid", irpUsers.get(i).getUserid());

                    // 当前登录用户所关注的用户列表中是否存在遍历的用户，如果有设为1（关注）
                    if (irpMicroblogFocuslist.contains(irpUsers.get(i).getUserid().toString())) {
                        userMap.put("addrebookattentionstatus", "1");
                    } else {
                        userMap.put("addrebookattentionstatus", "0");
                    }
                    // 把map放入分类数据的集合中
                    dataList.add(userMap);
                }
            }
            // 放入以字母为键的集合中
            initialMap.put(word, dataList);
            // 置空
            dataList = new ArrayList<Map<String, Object>>();
        }
        
        // 遍历map将空的集合移除
        Iterator<Map.Entry<String, List<Map<String, Object>>>> iterator = initialMap.entrySet().iterator();  
        while(iterator.hasNext()){  
            Map.Entry<String, List<Map<String, Object>>> entry = iterator.next();  
            if(entry.getValue().isEmpty()){
                iterator.remove(); // 使用迭代器的remove()方法删除元素  
            }
        }
        
        ActionUtil.writer(JsonUtil.map2json(initialMap));
    }

	/**
     * 根据用户生母查询用户
     * @return
     */
    public void selectUserbySM(){
    	HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid=irpuser.getUserid();
    	List<IrpUser> irpUsers = new ArrayList<IrpUser>();
    	if(this.getSearchWord()==null||this.getSearchWord().trim().length()==0){
    		this.searchWord=null;
    	}else{
    		try {
    			this.setSearchWord(new String(this.searchWord.getBytes("ISO-8859-1"),"UTF-8"));
    		} catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    		}
    	}
    	int count=0;
    	int result=irpUserService.findUserbySMcount(this.getSname(),searchWord);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSizeclient,result);
		if(result%pageSizeclient==0){
			count=result/pageSizeclient;
		}else{
			count=result/pageSizeclient+1;
		}
		if(this.pageNum<=count){
			irpUsers=irpUserService.findUserbySM(this.getSname(),searchWord,pageUtil);
		}
    	List<Map<Object,Object>> first = new ArrayList<Map<Object,Object>>();
    	List irpMicroblogFocuslist = this.irpMicroblogFocusService.selectUseridByLoginuserId(userid);
    	for(int i = 0 ; i < irpUsers.size();i++){
    		Map<Object,Object> l = new HashMap<Object, Object>();
    		Map<Object,Object> m = new HashMap<Object, Object>();
    		if(irpUsers.get(i).getUserpic() != null){
    			m.put("addrebookimg", irpUsers.get(i).getDefaultUserPic() + "&token=" + irpUsers.get(i).getToken());
    		}else{
    			m.put("addrebookimg", irpUsers.get(i).getDefaultUserPic());
    		}
    		m.put("addrebookname", irpUsers.get(i).getTruename());
    		l.put("addrebookphone",irpUsers.get(i).getMobile());
    		l.put("addrebooksex", irpUsers.get(i).getSex());
    		l.put("addrebookmail", irpUsers.get(i).getEmail());
    		l.put("addrebookarea",irpUsers.get(i).getLocation());
    		m.put("addrebookdetail",l);
    		m.put("addrebookuserid", irpUsers.get(i).getUserid());
    		if(irpMicroblogFocuslist.contains(irpUsers.get(i).getUserid().toString())){
				m.put("addrebookattentionstatus", "1");
			}else{
				m.put("addrebookattentionstatus", "0");
			}
    		first.add(m);
    	}
		ActionUtil.writer(JsonUtil.list2json(first));
    }
    
	// 前台细览页面 （执行这个方法就要对当前文档的点击量操作care_doc）
	private Integer irpUserPrivacy() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUserPrivacy irpUserPrivacy = null;
		irpUserPrivacy = this.irpUserPrivacyService.irpUserPrivacy(
				mobileAction.getlogin(token).getUserid(), IrpUserPrivacy.USERLOGINLOCATION);
		return irpUserPrivacy.getPrivacyvalue();
	}
    
	/**
	 * 判断这个知识的权限
	 * 
	 * @return
	 */
	public Boolean rightToDocument(IrpDocument document) {
		// 判断他的权限
		HttpServletRequest request= ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		//用户id为1(admin用户)的默认设置成系统管理员
		if(irpuser.getUserid()==1L){
			irpuser.setAdministrator(true);
		}
		List<Long> channelidList = new ArrayList<Long>();
		channelidList = irpChannelService.allparentidList(
				irpDocument.getChannelid(), channelidList,
				IrpChannel.IS_DOCSTATIUS, irpuser);
		if (channelidList == null || channelidList.size() == 0)
			return false;
		return true;
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
	
    public String clientShowDocumentInfo() {
		IrpChannel channle = new IrpChannel();
		RightUtil rightUtil = new RightUtil();
		Long nOPIdd = rightUtil.findOperTypeIdByKey("DOCUMENT_PRINTDOC");//打印
		Long nOPIdf = rightUtil.findOperTypeIdByKey("DOCUMENT_ACCORYDOWNLOAD");//附件下载
		Long nOPIdl = rightUtil.findOperTypeIdByKey("DOCUMENT_PRINTDOC");//历史版本
		Long nOPIdx = rightUtil.findOperTypeIdByKey("DOCUMENT_DOCCORRELATIONCOMMENT");//相关评论
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		Long docid = Long.valueOf(request.getParameter("docid"));
		this.loginuserprivacy = irpUserPrivacy();
		IrpUser irpUser = mobileAction.getlogin(token);
		//用户id为1(admin用户)的默认设置成系统管理员
		if(irpUser.getUserid()==1L){
			irpUser.setAdministrator(true);
		}
		if (docid == null || docid == 0L) {
			friendlyshow = IrpDocument.DOCUMENT_IS_ERROR;
			return ERROR;
		}
		if (id == null || id == 0L) {
			friendlyshow = IrpDocument.DOCUMENT_IS_DELETE_TYPE;
			return ERROR;
		}
		irpDocument = irpDocumentService.findDocumentByDocId(docid,token);
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
		
	    IrpUserPrivacy filterwords = this.irpUserPrivacyService.irpUserPrivacy(mobileAction.getlogin(token).getUserid(), IrpUserPrivacy.BOOLDOCWORDHOT);
		if (filterwords!=null && filterwords.getPrivacyvalue()==1) {
			irpDocument.setDochtmlcon(new TermFilterDoc().returnDisposeStr(pageDocument(irpDocument.getDochtmlcon())));
		}else{
			irpDocument.setDochtmlcon(pageDocument(irpDocument.getDochtmlcon()));
		}
		
		if(checkDocHits(docid)){
			//知识点击
			irpDocumentService.addHitScount(docid);
			//用户积分
			List<IrpValueConfig> list = irpValueConfigService.findValueConfigByMethodName("showdocumentinfo");
			long nScoreUserId=0L;
			for (IrpValueConfig irpValueConfig : list) {
				if (irpValueConfig.getBeandao() != null	&& irpValueConfig.getBeanidname() != null) {
					//给知识本身添加积分
					irpDocumentService.updateDocscoreByPrimaryKey(docid, irpValueConfig.getScore());
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
			irpDocumentLogsService.editDocumentLogs(docid);
		}
		
		// 查询所在栏目
		irpChannel = irpChannelService.finChannelByChannelid(id);// 查询新建文件文档所在的栏目名称
		// 同时查看文档的所有附件
		attacheds = irpAttachedService.getAttachedByAttDocId(docid, 0);
		// 查看这个知识的加精对象他是否有加精过
		docClassicl = irpInformService.findInformByExpert(IrpInform.INFORMJIAJINGDOC, docid, irpUser.getUserid());
		
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
		loginUserId = mobileAction.getlogin(token).getUserid();
		maxAmount = SysConfigUtil.getSysConfigValue("MAX_AMOUNT_TO_COMMENTDED");
		IrpDocumentScore documentScore = irpDocumentScoreService.findPersonScore(docid, loginUserId);
		if (documentScore != null) {
			isScore = true + "";
		} else {
			isScore = false + "";
		}
		//查看阅读记录
		List<IrpSubscribe> arrSubscribes = irpSubscribeService.findSubscribeByBaseObj(irpDocument);
		subscribe=arrSubscribes!=null && arrSubscribes.size()>0?1:0;
		// 查询
		return SUCCESS;
	}
    
    
    
    public IrpUser getIrpUser(Long _Userid) {
		IrpUser irpUser = null;
		irpUser = this.irpUserService.findUserByUserId(_Userid);
		return irpUser;
	}
    /**
	 * 签到
	 * 
	 * @param signService
	 */
	public void signIn_app() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String quxiang = request.getParameter("quxiang");
		String beizhu = request.getParameter("beizhu");
		String token = request.getParameter("token");
		irpSignInfo.setSignindirection(quxiang);
		irpSignInfo.setSigncomment(beizhu);
		if (SignUtil.common(irpSignInfo.getSignindirection())) {
			Date signInDate = DateUtils.getNOWTime();
			IrpSignInfo signInfo = new IrpSignInfo();
			signInfo.setSigninfoid(TableIdUtil.getNextId("irp_sign_info"));
			if(SignUtil.common(irpSignInfo.getSigncomment())){
				signInfo.setSigncomment("『签』:"+irpSignInfo.getSigncomment());
			}else{
				signInfo.setSigncomment("");
			}
			signInfo.setSignindirection(irpSignInfo.getSignindirection());
			signInfo.setSignintime(signInDate);
			signInfo.setSignid(mobileAction.getlogin(token).getId());
			signInfo.setSigninip(SignUtil.getAddress());
			if (signInDate.compareTo(SignUtil.getUpTime()) <= 0) {
				signInfo.setSigninstatus(SignUtil.NORMAL);
			} else {
				signInfo.setSigninstatus(SignUtil.LATE);
			}
			ifSign(token);
			if (irpSignInfo == null) {
				signService.insertSelect(signInfo);
				ActionUtil.writer("{\"result\":\"1\"}");
				irpSignInfo = null;
				return;
			}
			ActionUtil.writer("{\"result\":\"0\"}");
		}
	}
	/**
	 * 判断是否签到
	 */
	public void ifSign(String token) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(date) + " 00:00:00";
		String endTime = sdf.format(date) + " 23:59:59";
		IrpSignInfoExample example = new IrpSignInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andSignidEqualTo(mobileAction.getlogin(token).getId());
		criteria.andSignintimeBetween(Timestamp.valueOf(beginTime),
				Timestamp.valueOf(endTime));
		List<IrpSignInfo> list = signService.selectByExample(example);
		if (list != null && list.size() > 0) {
			irpSignInfo = list.get(0);
		} else {
			irpSignInfo = null;
		}
	}
	
	/**
	 * 判断是否签到
	 */
	public void ifSignByinit(String token) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(date) + " 00:00:00";
		String endTime = sdf.format(date) + " 23:59:59";
		IrpSignInfoExample example = new IrpSignInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andSignidEqualTo(mobileAction.getlogin(token).getId());
		criteria.andSignintimeBetween(Timestamp.valueOf(beginTime),
				Timestamp.valueOf(endTime));
		List<IrpSignInfo> list = signService.selectByExample(example);
		if (list != null && list.size() > 0) {
			irpSignInfo = list.get(0);
			ActionUtil.writer("{\"initresult\":\"f\"}");
		} else {
			irpSignInfo = null;
			ActionUtil.writer("{\"initresult\":\"t\"}");
		}
		
	}

	public void getchannels(){
		//获得首页的三级栏目结构
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpUser=mobileAction.getlogin(token);
		rootChannels = irpChannelService.allRightChannel(irpUser);
		String jsonString_channels = JSON.toJSONString(rootChannels);
		ActionUtil.writer(jsonString_channels);
	}

	/**
	 * 根据父栏目ID获得子栏目集合，(知识库首页)
	 * @param _nParentId
	 * @return
	 */
	public void findChildChannelByParentId(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		String parentid = request.getParameter("parentid");
		long _nParentId = Long.valueOf(parentid);
		List<IrpChannel> childChannels = irpChannelService.findChannelsByParentId(_nParentId, IrpChannel.IS_DOCSTATIUS, "chnlorder asc",token);
		String jsonString_childchannels = JSON.toJSONString(childChannels);
		ActionUtil.writer(jsonString_childchannels);
	}

	/**
	 * 知识概览页面
	 * @return
	 */
	public void documentList_app() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String channel = request.getParameter("channelid");
		String token = request.getParameter("token");
		channelid = Long.valueOf(channel);
		RightUtil rightUtil = new RightUtil();
		long nOperTypeId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
		if(channelid==null || channelid.intValue()==0){
			//查询当前栏目同级的栏目
			irpChannels = irpChannelService.findRightChannelsByParentId(0L, IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_PUBLIC, nOperTypeId, "chnlorder asc",token);
			if(irpChannels!=null && irpChannels.size()>0){
				irpChannel = irpChannels.get(0);
				channelid = irpChannel.getChannelid();
			}
		}else{
			irpChannel = irpChannelService.finChannelByChannelid(channelid);
			if(irpChannel==null || irpChannel.getStatus().intValue()==IrpChannel.NO_DOCSTATUS.intValue()){
		    	friendlyshow=IrpChannel.CHANNEL_IS_DELETE;
		    	//return ERROR;
		    }
			IrpSite site=irpSiteService.siteInfo(irpChannel.getSiteid());
	 		if(site==null || site.getStatus().intValue()==IrpSite.SITE_NOT_NORMAL){
	 			friendlyshow=IrpSite.SITE_NOT_SAVE;
	 			//return ERROR;
	 		}
	 		irpChannels = irpChannelService.findRightChannelsByParentId(irpChannel.getParentid(), IrpChannel.IS_DOCSTATIUS,IrpChannel.CHANNEL_TYPE_PUBLIC, nOperTypeId, "chnlorder asc",token);
		}
		if(irpChannel==null){
			//return SUCCESS;
		}
 		
		//验证栏目查看权限
 		IrpUser loginUser = mobileAction.getlogin(token);
 		//用户id为1(admin用户)的默认设置成系统管理员
		if(loginUser.getUserid()==1L){
			loginUser.setAdministrator(true);
		}
 		if(!loginUser.isAdministrator()){
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpChannel.ERROR_INFO_CHANNEL_SELECT;
 				//return ERROR;
 			}
 			//验证栏目文章列表查看权限
 			nOperTypeId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
 			if(!rightUtil.right(irpChannel, nOperTypeId)){
 				friendlyshow=IrpDocument.DOCUMENT_NO_RIGHT;
 				//return ERROR;
 			}
 		}
 		
 		//获得栏目全路径
		rootChannels = irpChannelService.currentChannels(channelid, rootChannels, 0L);
		
		List<Long> _arrChannelIds = new ArrayList<Long>();
		_arrChannelIds.add(channelid);
		
		
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "docpubtime desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
 		
 		int aDataCount=irpChnl_Doc_LinkService.clientSelectChnlDocCount("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
 												IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds, sOrderByClause);
 		int nPageSize = SysConfigUtil.getSysConfigNumValue("APPPAGESIZE");
 		PageUtil pageUtil = new PageUtil(this.pageNum, nPageSize, aDataCount);
 		chnlDocLinks=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
					IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds, sOrderByClause, pageUtil);
 		String jsonString_doclinks = JSON.toJSONString(chnlDocLinks);
		ActionUtil.writer(jsonString_doclinks);
 		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
 		//return SUCCESS;
	}
	
	/**
	 * 初始化签到用户
	 * 
	 * @param signService
	 */
	public void signInit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		ifSignByinit(token);
	}
	
	
	/**
	 * 跳转到Bug管理页面
	 * @return
	 */
	public void toBugManage_app() {
		try {
			if(projectId!=null && !"".equals(projectId)){
				tabPid=projectId;
			}
			findMyProjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化我所在的项目
	 */
	private void findMyProjects() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpProjectPersonExample ppe = new IrpProjectPersonExample();
		com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria = ppe
				.createCriteria();
		criteria.andPersonidEqualTo(mobileAction.getlogin(token).getUserid());
		List<IrpProjectPerson> pplist = projectpersonservice
				.findPersonByExample(ppe);
		if (pplist.size() > 0) {
			List<Long> projectids = new ArrayList<Long>();
			for (IrpProjectPerson ele : pplist) {
				projectids.add(ele.getPrid());
			}
			IrpProjectExample pe = new IrpProjectExample();
			com.tfs.irp.project.entity.IrpProjectExample.Criteria criteria2 = pe
					.createCriteria();
			criteria2.andProjectidIn(projectids);
			criteria2.andProjecttypeEqualTo(9l);
			irpProjects = projectService.selectByExample(pe);
			String buglist = JSON.toJSONString(irpProjects);
			ActionUtil.writer(buglist);
		}
	}
	
	//评论文档
	public void addDocRecommend_app(){  
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		int nCount=irpDocrecommendServiceImpl.addDocRrecommend_app(docid,replayUserId, recommend,token);
		if(nCount==1){
			IrpUser irpUser=mobileAction.getlogin(token);
			String str="<a href='javascript:void(0)' class='linkb14' >"+irpUser.getUsername()+"：</a>"+recommend+"<br>";
			ActionUtil.writer(str);
		} 
		replayUserId=0L;//添加回来之后需要将值设置为0，这样查询评论不会出问题
	} 

	//查看自己某一个文档所有的评论
	public String findMyDocRecommend_app(){ 
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpUser=mobileAction.getlogin(token);
		loginuserid = irpUser.getUserid();
		//此时没有传回复的回复id  
		if (irpUser.isAdministrator()) {
			isXiangGuan = true + "";
		}else{
			RightUtil rightUtil = new RightUtil();
			IrpChannel channle = new IrpChannel();
			channle.setChannelid(channelid);
			Long nOperTypeId = rightUtil.findOperTypeIdByKey("DOCUMENT_DOCCORRELATIONCOMMENT");//相关评论
			isXiangGuan = rightUtil.right(channle, nOperTypeId)+"";
		}
		int aDataCount=irpDocrecommendServiceImpl.countRecommend_app(docid, replayUserId,token);
		PageUtil pageUtil= new PageUtil(this.pageNum, this.pageSize, aDataCount); 
		irpDocrecommends=irpDocrecommendServiceImpl.findDocReommend(docid,replayUserId,IrpDocrecommend.ISDEL_PRO,pageUtil); //查看正常情况下的
		for(int i = 0;i < irpDocrecommends.size();i++){
			if(irpDocrecommends.get(i).getUserPicUrl().indexOf("fileName") > -1 ){
				irpDocrecommends.get(i).setUserPicUrl(irpDocrecommends.get(i).getUserPicUrl() + "&token=" + irpUser.getToken());
			}
		}
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");   
		return SUCCESS;
	}
	
	/**
	 * 跳转到总览页面
	 * 
	 * @return
	 */
	public void toPandectPage_urgent() {
		try {
			findUrgentBugsByProjectId();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转到总览页面
	 * 
	 * @return
	 */
	public void toPandectPage_new() {
		try {
			findNewestBugsByProjectId();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询紧急bugs
	 */
	private void findUrgentBugsByProjectId() throws Exception {
		List<String> prilist = new ArrayList<String>();
		prilist.add("3");
		prilist.add("4");
		prilist.add("5");
		IrpBugExample example = new IrpBugExample();
		example.setOrderByClause("priority desc,createtime desc");
		com.tfs.irp.bug.entity.IrpBugExample.Criteria criteria = example.createCriteria();
		criteria.andPriorityIn(prilist);
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		criteria.andNeweststateNotEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
		if(!"".equals(projectId) && projectId!=null){
			criteria.andProjectidEqualTo(projectId);
			projectIdTop=projectId;
			projectIdBottom=projectId;
		}else if(!"".equals(projectIdTop) && projectIdTop!=null){
			criteria.andProjectidEqualTo(projectIdTop);
			projectId=projectIdTop;
			projectIdBottom=projectIdTop;
		}else if(!"".equals(projectIdBottom) && projectIdBottom!=null){
			criteria.andProjectidEqualTo(projectIdBottom);
			projectIdTop=projectIdBottom;
			projectId=projectIdBottom;
		}
		/**************************************************************/
		int dataCount = bugService.getDataCount(example);
		PageUtil page = new PageUtil(pageNumTop, pageSizeTop, dataCount);
		urgentBugs = bugService.queryBugForPage(example, page);
		List<Map<Object ,Object>> lists = new ArrayList<Map<Object ,Object>>();
		for(int i=0;i<urgentBugs.size();i++){
			Map<Object ,Object> m = new HashMap<Object ,Object>();
			m.put("projectid", urgentBugs.get(i).getProjectid());
			m.put("priority", urgentBugs.get(i).getPriority());
			m.put("title", urgentBugs.get(i).getTitle());
			m.put("bugid", urgentBugs.get(i).getBugid());
			m.put("describe", urgentBugs.get(i).getDescribe());
			m.put("createtime", urgentBugs.get(i).getCreatetime());
			m.put("operatorname", irpUserService.findShowNameByUserid(urgentBugs.get(i).getOperatorid()));
			lists.add(m);
		}
		String json_urgentbugs = JSON.toJSONString(lists);
		ActionUtil.writer(json_urgentbugs);
		if(urgentBugs.size()>0){
			this.pageHTMLTop = page.getClientPageHtml("pageNavigainTop(#PageNum#)");
		}
		
		/**************************************************************/
		List<IrpUser> userList = userService
				.findUserByExample(new IrpUserExample());
		for (IrpBugWithBLOBs ele : urgentBugs) {
			for (IrpUser element : userList) {
				if (ele.getOperatorid() != null
						&& !ele.getOperatorid().equals("")) {
					if (ele.getOperatorid() == Integer.parseInt(element
							.getUserid() + "")) {
						operatorNameMap.put(ele.getOperatorid(),
								element.getTruename());
						break;
					}
				}
			}
		}
		initPriority();
	}
	
	/**
	 * 查询最新bugs
	 */
	private void findNewestBugsByProjectId() throws Exception {
		IrpBugExample example = new IrpBugExample();
		example.setOrderByClause("priority desc,createtime desc");
		com.tfs.irp.bug.entity.IrpBugExample.Criteria criteria = example.createCriteria();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
	//	this.newestBugs = this.bugService.selectByExample(example);
		if(!"".equals(projectId) && projectId!=null){
			criteria.andProjectidEqualTo(projectId);
			projectIdTop=projectId;
			projectIdBottom=projectId;
		}else if(!"".equals(projectIdTop) && projectIdTop!=null){
			criteria.andProjectidEqualTo(projectIdTop);
			projectId=projectIdTop;
			projectIdBottom=projectIdTop;
		}else if(!"".equals(projectIdBottom) && projectIdBottom!=null){
			criteria.andProjectidEqualTo(projectIdBottom);
			projectIdTop=projectIdBottom;
			projectId=projectIdBottom;
		}
		example.setOrderByClause("priority desc,createtime desc");
		/**************************************************************/
		int dataCount = bugService.getDataCount(example);
		PageUtil page = new PageUtil(pageNumBottom, pageSizeBottom, dataCount);
		newestBugs = bugService.queryBugForPage(example, page);
		List<Map<Object ,Object>> lists = new ArrayList<Map<Object ,Object>>();
		for(int i=0;i<newestBugs.size();i++){
			Map<Object ,Object> m = new HashMap<Object ,Object>();
			m.put("projectid", newestBugs.get(i).getProjectid());
			m.put("priority", newestBugs.get(i).getPriority());
			m.put("title", newestBugs.get(i).getTitle());
			m.put("bugid", newestBugs.get(i).getBugid());
			m.put("describe", newestBugs.get(i).getDescribe());
			m.put("createtime", newestBugs.get(i).getCreatetime());
			m.put("operatorname", irpUserService.findShowNameByUserid(newestBugs.get(i).getOperatorid()));
			lists.add(m);
		}
		String json_newestbugs = JSON.toJSONString(lists);
		ActionUtil.writer(json_newestbugs);
		if(newestBugs.size()>0){
			this.pageHTMLBottom = page.getClientPageHtml("pageNavigainBottom(#PageNum#)");
		}
		
		/**************************************************************/
		List<IrpUser> userList = userService
				.findUserByExample(new IrpUserExample());
		for (IrpBugWithBLOBs ele : newestBugs) {
			for (IrpUser element : userList) {
				if (ele.getFounderid() == Integer.parseInt(element.getUserid()
						+ "")) {
					founderNameMap.put(ele.getFounderid(),
							element.getTruename());
					break;
				}
			}
		}
	}
	
	/**
	 * 初始化优先级
	 */
	private void initPriority() {
		this.priorityMap.put("1", "低");
		this.priorityMap.put("2", "中");
		this.priorityMap.put("3", "高");
		this.priorityMap.put("4", "紧急");
		this.priorityMap.put("5", "严重");
	}
		
	/**
	 * 跳转到分配给我的Bug 未完成
	 * 
	 * @return
	 */
	public void findBugForMe_wei() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		try {
			findMyBugsWei(token);
			initPriority();
			this.vmNameMapInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转到分配给我的Bug 已完成
	 * 
	 * @return
	 */
	public void findBugForMe_wan() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		try {
			findMyBugsWan(token);
			initPriority();
			this.vmNameMapInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询分配给我待处理的Bug
	 * 
	 * @throws Exception
	 */
	private void findMyBugsWei(String token) throws Exception {
		List<Long> mebs = this.getSerianums(mobileAction.getlogin(token).getUserid(),token);
		if (mebs.size() > 0) {
			IrpBugExample example = new IrpBugExample();
			com.tfs.irp.bug.entity.IrpBugExample.Criteria criteria = example.createCriteria();
			criteria.andBugidIn(mebs);
			if(!"".equals(projectId) && projectId!=null){
				criteria.andProjectidEqualTo(projectId);
				projectIdTop=projectId;
				projectIdBottom=projectId;
			}else if(!"".equals(projectIdTop) && projectIdTop!=null){
				criteria.andProjectidEqualTo(projectIdTop);
				projectId=projectIdTop;
				projectIdBottom=projectIdTop;
			}else if(!"".equals(projectIdBottom) && projectIdBottom!=null){
				criteria.andProjectidEqualTo(projectIdBottom);
				projectIdTop=projectIdBottom;
				projectId=projectIdBottom;
			}
			/*if(keyword !=null && !"".equals(keyword)){
				criteria.andTitleLike("%"+keyword+"%");
			}
			if(priority !=null && !"".equals(priority)){
				criteria.andPriorityEqualTo(priority);
			}*/
			criteria.andNeweststateNotEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
			example.setOrderByClause(ordertop);
			/**************************************************************/
			int dataCount = bugService.getDataCount(example);
			PageUtil page = new PageUtil(pageNumTop, pageSizeTop, dataCount);
			tomeBugsWei = bugService.queryBugForPage(example, page);
			List<Map<Object,Object>> lists = new ArrayList<Map<Object,Object>>();
			for(int i=0;i<tomeBugsWei.size();i++){
				Map<Object, Object> m = new HashMap<Object, Object>();
				m.put("bugid", tomeBugsWei.get(i).getBugid());
				m.put("createtime", tomeBugsWei.get(i).getCreatetime());
				m.put("title", tomeBugsWei.get(i).getTitle());
				m.put("neweststate", tomeBugsWei.get(i).getNeweststate());
				m.put("projectid", tomeBugsWei.get(i).getProjectid());
				m.put("priority", tomeBugsWei.get(i).getPriority());
				m.put("operatorname", irpUserService.findShowNameByUserid(tomeBugsWei.get(i).getOperatorid()));
				lists.add(m);
			}
			String json_tomebugswei = JSON.toJSONString(lists);
			ActionUtil.writer(json_tomebugswei);
			if(tomeBugsWei.size()>0){
			this.pageHTMLTop = page.getClientPageHtml("pageNavigainTop(#PageNum#)");
			}
			/**************************************************************/
			//this.tomeBugsWei = bugService.selectByExample(example);
			List<IrpUser> userList = userService
					.findUserByExample(new IrpUserExample());
			if (tomeBugsWei.size() != 0) {
				for (IrpBugWithBLOBs ele : tomeBugsWei) {
					if (ele.getFounderid() != null) {
						for (IrpUser element : userList) {
							if (ele.getFounderid() == Integer.parseInt(element
									.getUserid() + "")) {
								founderNameMap.put(ele.getFounderid(),
										element.getTruename());
								break;
							}
						}
					}

				}
			}
		} else {
			tomeBugsWei = null;
		}
	}

	/**
	 * 查询分配给我完成的bug
	 * 
	 * @throws Exception
	 */
	private void findMyBugsWan(String token) throws Exception {
		List<Long> mebs = this.findSerianumsMyWan(token);
		if (mebs.size() > 0) {
			IrpBugExample example = new IrpBugExample();
			com.tfs.irp.bug.entity.IrpBugExample.Criteria criteria = example.createCriteria();
			criteria.andBugidIn(mebs);
			if(!"".equals(projectId) && projectId!=null){
				criteria.andProjectidEqualTo(projectId);
				projectIdTop=projectId;
				projectIdBottom=projectId;
			}else if(!"".equals(projectIdTop) && projectIdTop!=null){
				criteria.andProjectidEqualTo(projectIdTop);
				projectId=projectIdTop;
				projectIdBottom=projectIdTop;
			}else if(!"".equals(projectIdBottom) && projectIdBottom!=null){
				criteria.andProjectidEqualTo(projectIdBottom);
				projectIdTop=projectIdBottom;
				projectId=projectIdBottom;
			}
			/*if(keyword !=null && !"".equals(keyword)){
				criteria.andTitleLike("%"+keyword+"%");
			}
			if(priority !=null && !"".equals(priority)){
				criteria.andPriorityEqualTo(priority);
			}*/
			example.setOrderByClause(orderbottom);
			/**************************************************************/
			int dataCount = bugService.getDataCount(example);
			PageUtil page = new PageUtil(pageNumBottom, pageSizeBottom, dataCount);
			tomeBugsWan = bugService.queryBugForPage(example, page);
			List<Map<Object,Object>> lists = new ArrayList<Map<Object,Object>>();
			for(int i=0;i<tomeBugsWan.size();i++){
				Map<Object, Object> m = new HashMap<Object, Object>();
				m.put("bugid", tomeBugsWan.get(i).getBugid());
				m.put("createtime", tomeBugsWan.get(i).getCreatetime());
				m.put("title", tomeBugsWan.get(i).getTitle());
				m.put("neweststate", tomeBugsWan.get(i).getNeweststate());
				m.put("projectid", tomeBugsWan.get(i).getProjectid());
				m.put("priority", tomeBugsWan.get(i).getPriority());
				m.put("operatorname", irpUserService.findShowNameByUserid(tomeBugsWan.get(i).getOperatorid()));
				lists.add(m);
			}
			String json_tomebugswan = JSON.toJSONString(lists);
			ActionUtil.writer(json_tomebugswan);
			if(tomeBugsWan.size()>0){
				this.pageHTMLBottom = page.getClientPageHtml("pageNavigainBottom(#PageNum#)");
			}
			/**************************************************************/
			List<IrpUser> userList = userService
					.findUserByExample(new IrpUserExample());
			if (tomeBugsWan.size() != 0) {
				for (IrpBugWithBLOBs ele : tomeBugsWan) {
					if (ele.getFounderid() != null) {
						for (IrpUser element : userList) {
							if (ele.getFounderid() == Integer.parseInt(element
									.getUserid() + "")) {
								meFounderNameMap.put(ele.getFounderid(),
										element.getTruename());
								break;
							}
						}
					}
				}
			}
		} else {
			tomeBugsWan = null;
		}
	}
	
	/**
	 * 初始化版本模块名字信息
	 */
	private void vmNameMapInit(){
		try {
			if (projectId != null && !projectId.equals("")) {
				IrpBugConfigExample example = new IrpBugConfigExample();
				com.tfs.irp.bug_config.entity.IrpBugConfigExample.Criteria criteria = example
						.createCriteria();
				criteria.andProidEqualTo(this.projectId);
				List<IrpBugConfig> bclist=this.bugconfigservice.selectByExample(example);
				if(bclist.size()>0){
					for(IrpBugConfig ele :bclist){
						if(ele.getConfigtype().equals(IrpBugConfig.TYPE_VERSION)){
							this.versionNameMap.put(ele.getBugconfigid(), ele.getVersionname());
						}else if(ele.getConfigtype().equals(IrpBugConfig.TYPE_MODUL)){
							this.modulNameMap.put(ele.getBugconfigid(), ele.getModulname());
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得我已经完成bug的序号集合
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<Long> findSerianumsMyWan(String token) throws Exception {
		IrpBugExample example = new IrpBugExample();
		com.tfs.irp.bug.entity.IrpBugExample.Criteria criteria = example.createCriteria();
		criteria.andOperatoridEqualTo(mobileAction.getlogin(token).getUserid());
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
		example.setOrderByClause("priority desc,createtime desc");
		List<IrpBugWithBLOBs> bgs = bugService.selectByExample(example);
		List<Long> serianums = new ArrayList<Long>();
		for (IrpBugWithBLOBs bg : bgs) {
			serianums.add(bg.getSerianum());
		}
		return serianums;
	}
	/**
	 * 获得指定人员的待处理Bug序列号集合
	 * 
	 * @param userId
	 * @return
	 */
	private List<Long> getSerianums(Long userId,String token) throws Exception {
		IrpBugExample example = new IrpBugExample();
		com.tfs.irp.bug.entity.IrpBugExample.Criteria criteria = example.createCriteria();
		criteria.andIsdisposeNotEqualTo(IrpBugWithBLOBs.ISDISPORE_OK);
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
		criteria.andFounderidEqualTo(mobileAction.getlogin(token).getUserid());
		List<IrpBugWithBLOBs> bgs = bugService.selectByExample(example);
		List<Long> serianums = new ArrayList<Long>();
		for (IrpBugWithBLOBs bg : bgs) {
			serianums.add(bg.getSerianum());
		}
		return serianums;
	}
	
	/**
	 * 跳转到我创建的Bug
	 * 
	 * @return
	 */
	public void meToBugPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		try {
			IrpBugExample example = new IrpBugExample();
			com.tfs.irp.bug.entity.IrpBugExample.Criteria criteria = example.createCriteria();
			criteria.andProjectidEqualTo(projectId);
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
			criteria.andFounderidEqualTo(mobileAction.getlogin(token).getUserid());
			example.setOrderByClause("createtime");
			/*if(keyword !=null && !"".equals(keyword)){
				criteria.andTitleLike("%"+keyword+"%");
			}
			if(priority !=null && !"".equals(priority)){
				criteria.andPriorityEqualTo(priority);
			}*/
			if(state !=null && !"".equals(state)){
				if("1".equals(state)){
					criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WEIWAN);
				}else if("2".equals(state)){
					criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_SHENHE);
				}else if("3".equals(state)){
					criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
				}
			}
			if(!"".equals(order)){
				example.setOrderByClause(order);
			}
			/**************************************************************/
			int dataCount = bugService.getDataCount(example);
			PageUtil page = new PageUtil(pageNum, pageSize, dataCount);
			irpBugs = bugService.queryBugForPage(example, page);
			List<Map<Object ,Object>> lists = new ArrayList<Map<Object ,Object>>();
			for(int i=0;i<irpBugs.size();i++){
				Map<Object ,Object> m = new HashMap<Object ,Object>();
				m.put("projectid", irpBugs.get(i).getProjectid());
				m.put("priority", irpBugs.get(i).getPriority());
				m.put("title", irpBugs.get(i).getTitle());
				m.put("bugid", irpBugs.get(i).getBugid());
				m.put("describe", irpBugs.get(i).getDescribe());
				m.put("createtime", irpBugs.get(i).getCreatetime());
				m.put("operatorname", irpUserService.findShowNameByUserid(irpBugs.get(i).getOperatorid()));
				m.put("newstatus", irpBugs.get(i).getNeweststate());
				lists.add(m);
			}
			String json_irpbugs = JSON.toJSONString(lists);
			ActionUtil.writer(json_irpbugs);
			if(irpBugs.size()>0){
				this.pageHTML = page.getClientPageHtml("pageNavigain(#PageNum#)");
			}
			/**************************************************************/
			List<IrpUser> userList = userService
					.findUserByExample(new IrpUserExample());
			if (irpBugs.size() != 0) {
				for (IrpBugWithBLOBs ele : irpBugs) {
					if (ele.getFounderid() != null) {
						for (IrpUser element : userList) {
							if (ele.getFounderid() == Integer.parseInt(element
									.getUserid() + "")) {
								founderNameMap.put(ele.getFounderid(),
										element.getTruename());
								break;
							}
						}
					}

				}
			}
			this.initPriority();
			this.vmNameMapInit();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化Bug信息 所有/我创建/我处理
	 * 
	 * @return
	 */
	public void findBugForProject() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		String result = "success";
		try {
			IrpBugExample bugExample = new IrpBugExample();
			com.tfs.irp.bug.entity.IrpBugExample.Criteria criteria = bugExample.createCriteria();
			if (projectId != null && !projectId.equals("")) {
				criteria.andProjectidEqualTo(projectId);
			}
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
			if ("1".equals(headType)) {
				result = "page";
			} else if ("2".equals(headType)) {
				criteria.andFounderidEqualTo(mobileAction.getlogin(token).getUserid());
				result = "page";
			} else if ("3".equals(headType)) {
				criteria.andOperatoridEqualTo(mobileAction.getlogin(token).getUserid());
				result = "page";
			} else if ("4".equals(headType)) {
				List<Long> ss = getSerianums(mobileAction.getlogin(token).getUserid(),token);
				if (ss.size() == 0) {
					irpBugs = null;
					//return "page";
				}
				criteria.andIsdisposeNotEqualTo(IrpBugWithBLOBs.ISDISPORE_OK);
				criteria.andNeweststateNotEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
				criteria.andSerianumIn(ss);
				result = "page";
			}
			if(nstate !=null  && !"".equals(nstate) ){
				if(nstate.equals("1")){
					criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WEIWAN);
				}
				else if(nstate.equals("2")){
					criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_SHENHE);		
							
				}else if(nstate.equals("3")){
					criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
				}
			}else{
				findByState(criteria);
			}
			
			/*if (founderId != null && !founderId.equals("")) {
				addFounderIdIterm(criteria);
			}
			if (operatorId != null && !operatorId.equals("")) {
				addOperatorIdIterm(criteria);
			}
			if (priority != null && !priority.equals("")) {
				addPriorityIterm(criteria);
			}
			if (keyword != null && !keyword.equals("")) {
				criteria.andTitleLike("%" + keyword + "%");
			}
			if (versionid != null && !versionid.equals("")) {
				criteria.andVersionidEqualTo(versionid);
			}
			if (modulid != null && !modulid.equals("")) {
				criteria.andModuleidEqualTo(modulid);
			}*/
			bugExample.setOrderByClause(order);
			int dataCount = bugService.getDataCount(bugExample);
			PageUtil page = new PageUtil(pageNum, pageSize, dataCount);
			irpBugs = bugService.queryBugForPage(bugExample, page);
			List<Map<Object ,Object>> lists = new ArrayList<Map<Object ,Object>>();
			for(int i=0;i<irpBugs.size();i++){
				Map<Object ,Object> m = new HashMap<Object ,Object>();
				m.put("projectid", irpBugs.get(i).getProjectid());
				m.put("priority", irpBugs.get(i).getPriority());
				m.put("title", irpBugs.get(i).getTitle());
				m.put("bugid", irpBugs.get(i).getBugid());
				m.put("describe", irpBugs.get(i).getDescribe());
				m.put("createtime", irpBugs.get(i).getCreatetime());
				m.put("operatorname", irpUserService.findShowNameByUserid(irpBugs.get(i).getOperatorid()));
				m.put("newstatus", irpBugs.get(i).getNeweststate());
				lists.add(m);
			}
			String json_irpbugs =JSON.toJSONString(lists);
			ActionUtil.writer(json_irpbugs);
			this.pageHTML = page.getClientPageHtml("pageNavigain(#PageNum#)");
			List<IrpUser> userList = userService
					.findUserByExample(new IrpUserExample());
			if ("1".equals(queryType)) {
				result = "page";
			}
			if (irpBugs.size() != 0) {
				for (IrpBugWithBLOBs ele : irpBugs) {
					if (ele.getFounderid() != null) {
						for (IrpUser element : userList) {
							if (ele.getFounderid() == Integer.parseInt(element
									.getUserid() + "")) {
								founderNameMap.put(ele.getFounderid(),
										element.getTruename());
								break;
							}
						}
					}

				}
				for (IrpBugWithBLOBs ele : irpBugs) {
					if (ele.getOperatorid() != null) {
						for (IrpUser element : userList) {
							if (ele.getOperatorid() == Integer.parseInt(element
									.getUserid() + "")) {
								operatorNameMap.put(ele.getOperatorid(),
										element.getTruename());
								break;
							}
						}
					}
				}
				this.vmNameMapInit();
			} else {
				irpBugs = null;
			}
			if (projectId != null && !projectId.equals("")) {
				getProjectUser(projectId, true);
			} else {
				this.projectUsers = userService.findAllRegUsers();
			}
			/*this.findMyProjects();
			this.findVersionByProid();
			this.findModulByProid();*/
			if (seltype == 1) {
				result = SUCCESS;
			}
			initPriority();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return result;
	}
	
	/**
	 * 根据状态改变显示Bug
	 * 
	 * @param criteria
	 */
	private void findByState(com.tfs.irp.bug.entity.IrpBugExample.Criteria criteria) {
		if ("0".equals(stateType)) {
			return;
		} else if ("1".equals(stateType)) {
			criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WEIWAN);
		} else if ("2".equals(stateType)) {
			criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_SHENHE);
		} else if ("3".equals(stateType)) {
			criteria.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
		}
	}
	
	/**
	 * 获取项目成员 true 所有项目成员,false 不包括登录用户
	 * 
	 * @param projectid
	 * @param bl
	 */
	private void getProjectUser(Long projectid, boolean bl) {
		IrpProjectPersonExample example = new IrpProjectPersonExample();
		com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria = example
				.createCriteria();
		if (!bl) {
			Long loginid = LoginUtil.getLoginUserId();
			criteria.andPersonidNotEqualTo(loginid);
		}
		criteria.andPridEqualTo(projectid);
		List<IrpProjectPerson> projectPersons = this.projectpersonservice
				.findPersonByExample(example);
		List<Long> presonIds = new ArrayList<Long>();
		for (IrpProjectPerson ele : projectPersons) {
			presonIds.add(ele.getPersonid());
		}
		IrpUserExample userExample = new IrpUserExample();
		userExample.setOrderByClause("USERID");
		com.tfs.irp.user.entity.IrpUserExample.Criteria userCriteria = userExample
				.createCriteria();
		if (presonIds.size() > 0) {
			userCriteria.andUseridIn(presonIds);
			projectUsers = userService.findUserByExample(userExample);
		} else {
			projectUsers = null;
		}
	}
	
	/**
	 * 查询项目下所有版本
	 */
	private void findVersionByProid() {
		try {
			if (projectId != null && !projectId.equals("")) {
				IrpBugConfigExample example = new IrpBugConfigExample();
				com.tfs.irp.bug_config.entity.IrpBugConfigExample.Criteria criteria = example
						.createCriteria();
				criteria.andProidEqualTo(this.projectId);
				criteria.andConfigtypeEqualTo(0l);
				example.setOrderByClause(" BUGCONFIGID");
				this.bugversions = bugconfigservice.selectByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询项目下所有模块
	 */
	private void findModulByProid() {
		try {
			if (projectId != null && !projectId.equals("")) {
				IrpBugConfigExample example = new IrpBugConfigExample();
				com.tfs.irp.bug_config.entity.IrpBugConfigExample.Criteria criteria = example
						.createCriteria();
				criteria.andProidEqualTo(projectId);
				criteria.andConfigtypeEqualTo(1l);
				example.setOrderByClause(" BUGCONFIGID");
				this.bugmoduls = bugconfigservice.selectByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转到Bug统计页面
	 * @return
	 */
	public String toStatisticsPage(){
		return SUCCESS;
	}
	
	/**
	 * 查询统计数据
	 * @return
	 */
	public String initBugStatistics(){
		try {
			initBugRenyuanStatistics();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 统计人员分布情况
	 */
	private void initBugRenyuanStatistics(){
		this.getProjectUser(projectId,true);
		List<String> projectPersonStr=new ArrayList<String>();
		for(IrpUser ele: projectUsers){
			projectPersonStr.add(ele.getTruename());
		}
		this.projectPersonsJson=JsonUtil.list2json(projectPersonStr);
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
	
	//个人信息
	public void personinfo(){
		IrpUser irpuser = new IrpUser();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		irpuser = mobileAction.getlogin(token);
		if(null != irpuser){
			if(irpuser.getUserpic() != null){
				irpuser.setUserpic(irpuser.getUserpic() + "&token=" + token);
			}
		}
		String personjson = JSON.toJSONString(irpuser);
		ActionUtil.writer(personjson);
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<IrpBugWithBLOBs> getIrpBugs() {
		return irpBugs;
	}

	public void setIrpBugs(List<IrpBugWithBLOBs> irpBugs) {
		this.irpBugs = irpBugs;
	}

	public String getHeadType() {
		return headType;
	}

	public void setHeadType(String headType) {
		this.headType = headType;
	}

	public String getNstate() {
		return nstate;
	}

	public void setNstate(String nstate) {
		this.nstate = nstate;
	}

	public String getStateType() {
		return stateType;
	}

	public void setStateType(String stateType) {
		this.stateType = stateType;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public List<IrpUser> getProjectUsers() {
		return projectUsers;
	}

	public void setProjectUsers(List<IrpUser> projectUsers) {
		this.projectUsers = projectUsers;
	}

	public List<IrpBugConfig> getBugversions() {
		return bugversions;
	}

	public void setBugversions(List<IrpBugConfig> bugversions) {
		this.bugversions = bugversions;
	}

	public List<IrpBugConfig> getBugmoduls() {
		return bugmoduls;
	}

	public void setBugmoduls(List<IrpBugConfig> bugmoduls) {
		this.bugmoduls = bugmoduls;
	}

	public int getSeltype() {
		return seltype;
	}

	public void setSeltype(int seltype) {
		this.seltype = seltype;
	}

	public BugConfigService getBugconfigservice() {
		return bugconfigservice;
	}

	public void setBugconfigservice(BugConfigService bugconfigservice) {
		this.bugconfigservice = bugconfigservice;
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

	public String getOrdertop() {
		return ordertop;
	}

	public void setOrdertop(String ordertop) {
		this.ordertop = ordertop;
	}

	public List<IrpBugWithBLOBs> getTomeBugsWei() {
		return tomeBugsWei;
	}

	public void setTomeBugsWei(List<IrpBugWithBLOBs> tomeBugsWei) {
		this.tomeBugsWei = tomeBugsWei;
	}

	public String getOrderbottom() {
		return orderbottom;
	}

	public void setOrderbottom(String orderbottom) {
		this.orderbottom = orderbottom;
	}

	public List<IrpBugWithBLOBs> getTomeBugsWan() {
		return tomeBugsWan;
	}

	public void setTomeBugsWan(List<IrpBugWithBLOBs> tomeBugsWan) {
		this.tomeBugsWan = tomeBugsWan;
	}

	public Map<Long, String> getMeFounderNameMap() {
		return meFounderNameMap;
	}

	public void setMeFounderNameMap(Map<Long, String> meFounderNameMap) {
		this.meFounderNameMap = meFounderNameMap;
	}

	public Map<Long, String> getVersionNameMap() {
		return versionNameMap;
	}

	public void setVersionNameMap(Map<Long, String> versionNameMap) {
		this.versionNameMap = versionNameMap;
	}

	public Map<Long, String> getModulNameMap() {
		return modulNameMap;
	}

	public void setModulNameMap(Map<Long, String> modulNameMap) {
		this.modulNameMap = modulNameMap;
	}

	public int getPageNumBottom() {
		return pageNumBottom;
	}

	public void setPageNumBottom(int pageNumBottom) {
		this.pageNumBottom = pageNumBottom;
	}

	public int getPageSizeBottom() {
		return pageSizeBottom;
	}

	public void setPageSizeBottom(int pageSizeBottom) {
		this.pageSizeBottom = pageSizeBottom;
	}

	public List<IrpBugWithBLOBs> getNewestBugs() {
		return newestBugs;
	}

	public void setNewestBugs(List<IrpBugWithBLOBs> newestBugs) {
		this.newestBugs = newestBugs;
	}

	public String getPageHTMLBottom() {
		return pageHTMLBottom;
	}

	public void setPageHTMLBottom(String pageHTMLBottom) {
		this.pageHTMLBottom = pageHTMLBottom;
	}

	public Map<Long, String> getFounderNameMap() {
		return founderNameMap;
	}

	public void setFounderNameMap(Map<Long, String> founderNameMap) {
		this.founderNameMap = founderNameMap;
	}

	public BugService getBugService() {
		return bugService;
	}

	public void setBugService(BugService bugService) {
		this.bugService = bugService;
	}

	public IrpUserService getUserService() {
		return userService;
	}

	public void setUserService(IrpUserService userService) {
		this.userService = userService;
	}

	public Map<String, String> getPriorityMap() {
		return priorityMap;
	}

	public void setPriorityMap(Map<String, String> priorityMap) {
		this.priorityMap = priorityMap;
	}

	public Long getProjectIdTop() {
		return projectIdTop;
	}

	public void setProjectIdTop(Long projectIdTop) {
		this.projectIdTop = projectIdTop;
	}

	public Long getProjectIdBottom() {
		return projectIdBottom;
	}

	public void setProjectIdBottom(Long projectIdBottom) {
		this.projectIdBottom = projectIdBottom;
	}

	public int getPageNumTop() {
		return pageNumTop;
	}

	public void setPageNumTop(int pageNumTop) {
		this.pageNumTop = pageNumTop;
	}

	public int getPageSizeTop() {
		return pageSizeTop;
	}

	public void setPageSizeTop(int pageSizeTop) {
		this.pageSizeTop = pageSizeTop;
	}

	public List<IrpBugWithBLOBs> getUrgentBugs() {
		return urgentBugs;
	}

	public void setUrgentBugs(List<IrpBugWithBLOBs> urgentBugs) {
		this.urgentBugs = urgentBugs;
	}

	public String getPageHTMLTop() {
		return pageHTMLTop;
	}

	public void setPageHTMLTop(String pageHTMLTop) {
		this.pageHTMLTop = pageHTMLTop;
	}

	public Map<Long, String> getOperatorNameMap() {
		return operatorNameMap;
	}

	public void setOperatorNameMap(Map<Long, String> operatorNameMap) {
		this.operatorNameMap = operatorNameMap;
	}

	public List<IrpDocrecommend> getIrpDocrecommends() {
		return irpDocrecommends;
	}

	public void setIrpDocrecommends(List<IrpDocrecommend> irpDocrecommends) {
		this.irpDocrecommends = irpDocrecommends;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getLoginuserid() {
		return loginuserid;
	}

	public void setLoginuserid(Long loginuserid) {
		this.loginuserid = loginuserid;
	}

	public IrpDocrecommendService getIrpDocrecommendServiceImpl() {
		return irpDocrecommendServiceImpl;
	}

	public void setIrpDocrecommendServiceImpl(
			IrpDocrecommendService irpDocrecommendServiceImpl) {
		this.irpDocrecommendServiceImpl = irpDocrecommendServiceImpl;
	}

	public Long getDocid() {
		return docid;
	}

	public void setDocid(Long docid) {
		this.docid = docid;
	}

	public Long getReplayUserId() {
		return replayUserId;
	}

	public void setReplayUserId(Long replayUserId) {
		this.replayUserId = replayUserId;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	public IrpProjectPersonService getProjectpersonservice() {
		return projectpersonservice;
	}

	public void setProjectpersonservice(IrpProjectPersonService projectpersonservice) {
		this.projectpersonservice = projectpersonservice;
	}

	public IrpProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IrpProjectService projectService) {
		this.projectService = projectService;
	}

	public List<IrpProject> getIrpProjects() {
		return irpProjects;
	}

	public void setIrpProjects(List<IrpProject> irpProjects) {
		this.irpProjects = irpProjects;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getTabPid() {
		return tabPid;
	}

	public void setTabPid(Long tabPid) {
		this.tabPid = tabPid;
	}

	public IrpSignInfo getIrpSignInfo() {
		return irpSignInfo;
	}

	public void setIrpSignInfo(IrpSignInfo irpSignInfo) {
		this.irpSignInfo = irpSignInfo;
	}

	public List<IrpChnlDocLink> getChnlDocLinks() {
		return chnlDocLinks;
	}

	public void setChnlDocLinks(List<IrpChnlDocLink> chnlDocLinks) {
		this.chnlDocLinks = chnlDocLinks;
	}

	public List<IrpChannel> getIrpChannels() {
		return irpChannels;
	}

	public void setIrpChannels(List<IrpChannel> irpChannels) {
		this.irpChannels = irpChannels;
	}

	public Long getChannelid() {
		return channelid;
	}

	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}

	public SignService getSignService() {
		return signService;
	}

	public void setSignService(SignService signService) {
		this.signService = signService;
	}

	public IrpSubscribeService getIrpSubscribeService() {
		return irpSubscribeService;
	}

	public void setIrpSubscribeService(IrpSubscribeService irpSubscribeService) {
		this.irpSubscribeService = irpSubscribeService;
	}

	public String getIsScore() {
		return isScore;
	}

	public void setIsScore(String isScore) {
		this.isScore = isScore;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public DocScoreService getIrpDocumentScoreService() {
		return irpDocumentScoreService;
	}

	public void setIrpDocumentScoreService(DocScoreService irpDocumentScoreService) {
		this.irpDocumentScoreService = irpDocumentScoreService;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
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

	public String getIsCrUser() {
		return isCrUser;
	}

	public void setIsCrUser(String isCrUser) {
		this.isCrUser = isCrUser;
	}

	public Long getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
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

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public IrpInformService getIrpInformService() {
		return irpInformService;
	}

	public void setIrpInformService(IrpInformService irpInformService) {
		this.irpInformService = irpInformService;
	}

	public IrpChannel getIrpChannel() {
		return irpChannel;
	}

	public void setIrpChannel(IrpChannel irpChannel) {
		this.irpChannel = irpChannel;
	}

	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}

	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	}

	public IrpInform getDocClassicl() {
		return docClassicl;
	}

	public void setDocClassicl(IrpInform docClassicl) {
		this.docClassicl = docClassicl;
	}

	public IrpSiteService getIrpSiteService() {
		return irpSiteService;
	}

	public void setIrpSiteService(IrpSiteService irpSiteService) {
		this.irpSiteService = irpSiteService;
	}

	public IrpValueConfigService getIrpValueConfigService() {
		return irpValueConfigService;
	}

	public void setIrpValueConfigService(IrpValueConfigService irpValueConfigService) {
		this.irpValueConfigService = irpValueConfigService;
	}

	public IrpDocumentWithBLOBs getIrpDocument() {
		return irpDocument;
	}

	public void setIrpDocument(IrpDocumentWithBLOBs irpDocument) {
		this.irpDocument = irpDocument;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFriendlyshow() {
		return friendlyshow;
	}

	public void setFriendlyshow(String friendlyshow) {
		this.friendlyshow = friendlyshow;
	}

	public Integer getLoginuserprivacy() {
		return loginuserprivacy;
	}

	public void setLoginuserprivacy(Integer loginuserprivacy) {
		this.loginuserprivacy = loginuserprivacy;
	}

	public IrpUserPrivacyService getIrpUserPrivacyService() {
		return irpUserPrivacyService;
	}

	public void setIrpUserPrivacyService(IrpUserPrivacyService irpUserPrivacyService) {
		this.irpUserPrivacyService = irpUserPrivacyService;
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

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getPageSizeclient() {
		return pageSizeclient;
	}

	public void setPageSizeclient(int pageSizeclient) {
		this.pageSizeclient = pageSizeclient;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}

	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
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

	public List<IrpChannel> getRootChannels() {
		return rootChannels;
	}

	public void setRootChannels(List<IrpChannel> rootChannels) {
		this.rootChannels = rootChannels;
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

	public List<IrpChnlDocLink> getCollectionChnlDocs() {
		return collectionChnlDocs;
	}

	public void setCollectionChnlDocs(List<IrpChnlDocLink> collectionChnlDocs) {
		this.collectionChnlDocs = collectionChnlDocs;
	}

	public List<Map<String, IrpBaseObj>> getCommentsData() {
		return commentsData;
	}

	public void setCommentsData(List<Map<String, IrpBaseObj>> commentsData) {
		this.commentsData = commentsData;
	}

	public List<IrpChnlDocLink> getEssenceDocs() {
		return essenceDocs;
	}

	public void setEssenceDocs(List<IrpChnlDocLink> essenceDocs) {
		this.essenceDocs = essenceDocs;
	}

	public List<IrpChnlDocLink> getLikeDocs() {
		return likeDocs;
	}

	public void setLikeDocs(List<IrpChnlDocLink> likeDocs) {
		this.likeDocs = likeDocs;
	}

	public Map<String, List<IrpTag>> getIndexTags() {
		return indexTags;
	}

	public void setIndexTags(Map<String, List<IrpTag>> indexTags) {
		this.indexTags = indexTags;
	}
	/*private String searchword;
	
	public String getSearchword() {
		return searchword;
	}

	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}*/
	private IrpSelectKeyService irpSelectKeyService;
	
	public IrpSelectKeyService getIrpSelectKeyService() {
		return irpSelectKeyService;
	}

	public void setIrpSelectKeyService(IrpSelectKeyService irpSelectKeyService) {
		this.irpSelectKeyService = irpSelectKeyService;
	}

	/**
	 * 搜索bug项目     付燕妮
	 */
	public void findMyBugProjects() throws Exception {
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		IrpProject irpProject=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpProjectPersonExample ppe = new IrpProjectPersonExample();
		com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria = ppe
				.createCriteria();
		criteria.andPersonidEqualTo(mobileAction.getlogin(token).getUserid());
		List<IrpProjectPerson> pplist = projectpersonservice
				.findPersonByExample(ppe);
		if (pplist.size() > 0) {
			List<Long> projectids = new ArrayList<Long>();
			for (IrpProjectPerson ele : pplist) {
				projectids.add(ele.getPrid());
			}
			IrpProjectExample pe = new IrpProjectExample();
			com.tfs.irp.project.entity.IrpProjectExample.Criteria criteria2 = pe
					.createCriteria();
			criteria2.andProjectidIn(projectids);
			criteria2.andProjecttypeEqualTo(9l);
			irpProjects = projectService.selectByExample(pe);
			if(irpProjects==null){
				
			}else{
				for(int i=0;i<irpProjects.size();i++){
					IrpBugExample example = new IrpBugExample();
					example.setOrderByClause("priority desc,createtime desc");
					int dataCount =0;
					Map<Object,Object> map = new HashMap<Object, Object>();
					irpProject=projectService.findProjectById(irpProjects.get(i).getProjectid());
					if(!"".equals(irpProject.getProjectid()) && irpProject.getProjectid()!=null){
						example.createCriteria().andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST)
						.andProjectidEqualTo(irpProject.getProjectid()).andTitleLike("%"+searchWord+"%");
						/*example.or(example.createCriteria().andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST)
							.andProjectidEqualTo(irpProject.getProjectid()));*/
						dataCount = bugService.getDataCount(example);
					}
					if(dataCount>0){
						map.put("name", irpProject.getPrname());
						map.put("prostatus", irpProject.getProstatus());
						map.put("projectid", irpProject.getProjectid());
						map.put("crtime", irpProject.getCrtime());
						map.put("resultcount", dataCount);
						list.add(map);
					}
				}
			}
			irpSelectKeyService.saveserachword(searchWord);
			irpTagService.addTag(searchWord);
			String buglist = JSON.toJSONString(list);
			ActionUtil.writer(buglist);
		}
	}
	
	//搜索bug
	public void getNewestBugsByProjectId() throws Exception {
		IrpBugExample example = new IrpBugExample();
		com.tfs.irp.bug.entity.IrpBugExample.Criteria criteria = example.createCriteria();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		if(searchWord!=null){
			searchWord=new String(searchWord.getBytes("ISO-8859-1"),"UTF-8");
			criteria.andTitleLike("%"+searchWord+"%");
		}
	//	this.newestBugs = this.bugService.selectByExample(example);
		if(!"".equals(projectId) && projectId!=null){
			criteria.andProjectidEqualTo(projectId);
			projectIdTop=projectId;
			projectIdBottom=projectId;
		}else if(!"".equals(projectIdTop) && projectIdTop!=null){
			criteria.andProjectidEqualTo(projectIdTop);
			projectId=projectIdTop;
			projectIdBottom=projectIdTop;
		}else if(!"".equals(projectIdBottom) && projectIdBottom!=null){
			criteria.andProjectidEqualTo(projectIdBottom);
			projectIdTop=projectIdBottom;
			projectId=projectIdBottom;
		}
		example.setOrderByClause("priority desc,createtime desc");
		/**************************************************************/
		int dataCount = bugService.getDataCount(example);
		PageUtil page = new PageUtil(pageNumBottom, pageSizeBottom, dataCount);
		newestBugs = bugService.queryBugForPage(example, page);
		List<Map<Object ,Object>> lists = new ArrayList<Map<Object ,Object>>();
		for(int i=0;i<newestBugs.size();i++){
			Map<Object ,Object> m = new HashMap<Object ,Object>();
			//m.put("projectid", newestBugs.get(i).getProjectid());
			m.put("priority", newestBugs.get(i).getPriority());
			m.put("title", newestBugs.get(i).getTitle());
			m.put("bugid", newestBugs.get(i).getBugid());
			m.put("createtime", newestBugs.get(i).getCreatetime());
			m.put("bugstatus", newestBugs.get(i).getNeweststate());
			m.put("operatorname", irpUserService.findShowNameByUserid(newestBugs.get(i).getOperatorid()));
			lists.add(m);
		}
		String json_newestbugs = JSON.toJSONString(lists);
		ActionUtil.writer(json_newestbugs);
		if(newestBugs.size()>0){
			this.pageHTMLBottom = page.getClientPageHtml("pageNavigainBottom(#PageNum#)");
		}
		
		/**************************************************************/
		List<IrpUser> userList = userService
				.findUserByExample(new IrpUserExample());
		for (IrpBugWithBLOBs ele : newestBugs) {
			for (IrpUser element : userList) {
				if (ele.getFounderid() == Integer.parseInt(element.getUserid()
						+ "")) {
					founderNameMap.put(ele.getFounderid(),
							element.getTruename());
					break;
				}
			}
		}
	}
	
	/**
	 * 搜索知识mobile
	 * @return
	 */
	public void searchDocument() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		//最新知识
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause"," docpubtime desc ");
		if(searchWord!=null){
			try {
				searchWord=new String(searchWord.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
 		List<Long> _arrChannelIds=new ArrayList<Long>();
 		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_PUBLIC);
		IrpUser irpuser = mobileAction.getlogin(token);
		//用户id为1(admin用户)的默认设置成系统管理员
		if(irpuser.getUserid()==1L){
			irpuser.setAdministrator(true);
		}
		int count=0;
		_arrChannelIds=irpChannelService.clientfindRightChannelId(_arrChannelIds, chnlTypes, IrpChannel.IS_DOCSTATIUS,0L, IrpSite.SITE_TYPE_PUBLISH, irpuser);
 		map.put("channelidsList", _arrChannelIds);
 		int countnum=irpChnl_Doc_LinkService.clientSelectChnlDocCount(null, null, IrpDocument.DOCUMENT_NOT_INFORM, 
					IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds, " docpubtime desc ");
 		if(countnum%pageSize==0){
			count=countnum/pageSize;
		}else{
			count=countnum/pageSize+1;
		}
		if(this.pageNum<=count){
			newChnlDocs=irpChnl_Doc_LinkService.clientSearchDoc(map,IrpDocument.DOCUMENT_NOT_INFORM,pageNum, pageSize, searchWord);
		}
 		/*for(int i = 0;i<newChnlDocs.size();i++){
 			newChnlDocs.get(i).setDocpuburl(docCoverPath(newChnlDocs.get(i).getDocid(),newChnlDocs.get(i).getDocflag()));
		}*/
		List<Map<Object ,Object>> lists = new ArrayList<Map<Object ,Object>>();
		if(newChnlDocs==null){
			
		}else{
			for(int i=0;i<newChnlDocs.size();i++){
				Map<Object ,Object> m = new HashMap<Object ,Object>();
				m.put("cruser", newChnlDocs.get(i).getCruser());
				m.put("chnldesc", newChnlDocs.get(i).getChnldesc());
				m.put("ducpuburl", docCoverPath(newChnlDocs.get(i).getDocid(),newChnlDocs.get(i).getDocflag()));
				m.put("docpubtime", newChnlDocs.get(i).getDocpubtime());
				m.put("doctitle", newChnlDocs.get(i).getDoctitle());
				m.put("docid", newChnlDocs.get(i).getDocid());
				lists.add(m);
			}
		}
 		String jsonString_new = JSON.toJSONString(lists);
		ActionUtil.writer(jsonString_new);
	}
	/**
	 * 微知管理中心mobile
	 * @return
	 * @author   npz
	 * @date 2017年8月10日
	 */
	public String userWeiboManager(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser irpuser = mobileAction.getlogin(token);
		return SUCCESS;
	}
	/**
	 * 
	 * @return
	 * @author   npz
	 * @date 2017年8月10日
	 */
	public String microblogManage_app() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		return SUCCESS;
	}
	/**
	 * 微知专题mobile
	 * @return
	 * @author   npz
	 * @date 2017年8月14日
	 */
	public String microtopicManage_app(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser irpuser = mobileAction.getlogin(token);
		return SUCCESS;
	}

	/**
	 * 知识标签 、我的会议日程、知识管理、知识举报mobile
	 * @return
	 * @author   npz
	 * @date 2017年8月15日
	 */
	public String Manage_app(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser irpuser = mobileAction.getlogin(token);
		return SUCCESS;
	}
	
	/**
	 * 知识举报mobile
	 * @return
	 * @author   npz
	 * @date 2017年8月29日
	 */
	public String findJuBaoDocument(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser irpuser = mobileAction.getlogin(token);
		
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
		int _nDataCount=irpChnl_Doc_LinkService.findJuBaoDocCount_app(irpuser,sitetype1,sitetype2,expert,IrpInform.INFORMKNOW,IrpInform.INFORM_STATUS);
		PageUtil pageUtil=new PageUtil(this.pageNum,this.pageSize, _nDataCount);
		jubaodocuments=irpChnl_Doc_LinkService.findJuBaoDoc_app(irpuser,sitetype1,sitetype2,expert,IrpInform.INFORMKNOW,IrpInform.INFORM_STATUS, pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("findDocReportConn(#PageNum#)");   //chnlDocLinks  
		return SUCCESS;
	}
	

	/**
	 * 知识审核mobile
	 * @return
	 * @author   npz
	 * @date 2017年9月19日
	 */
	public String findFlowInfo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser irpuser = mobileAction.getlogin(tokens);
		long nUserId = irpuser.getUserid();
		int nDataCount = irpWorkFlowService.findUserFlowInfoCountByUserId(nUserId);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		flowInfos = irpWorkFlowService.findUserFlowInfoByUserId(nUserId, pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("findFlowConn(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 问答记录管理mobile
	 * 
	 * @author   npz
	 * @date 2017年9月18日
	 */
	public String allQuestion(){
		questionShowNum = Integer.parseInt(SysConfigUtil
				.getSysConfigValue("QUESTION_SHOWNUM"));
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser irpuser = mobileAction.getlogin(tokens);
		loginUsername = irpuser.getName();
		
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("categoryId", 0);
			int nDataCount = irpQuestionService.findAllQuestionsInCategory(map);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
					nDataCount);
			// 处理排序
			String sOrderByClause = "";
			if (this.orderField == null || this.orderField.equals("")) {
				sOrderByClause = "crtime desc";
			} else {
				sOrderByClause = this.orderField + " " + this.orderBy;
			}
			listQuestion = irpQuestionService.findQuestionsInCategoryByPage(pageUtil,
					sOrderByClause,map);
			if(listQuestion!=null && listQuestion.size()>0){
				for (int i = 0; i < listQuestion.size(); i++) {
					irpUser = irpUserService.findUserByUserId(listQuestion.get(i)
							.getCruserid());
				}
			}
			this.pageHTML = pageUtil.getClientPageHtml("findFlowConn(#PageNum#)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 兑换记录管理mobile
	 * @return
	 * @author   npz
	 * @date 2017年9月19日
	 */
	public String listCovertRecordByUserid(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser irpuser = mobileAction.getlogin(tokens);
		int aDataCount=irpUserCovertGoodsService.countCovertByUserid(irpuser.getUserid());
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		listcovertrecord = irpUserCovertGoodsService.showAllCovertByUserid(pageUtil,orderField,orderBy,irpuser.getUserid());
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 返回用户角色列表
	 * 
	 * @author   npz
	 * @date 2017年8月10日
	 */
	public void usermanagement(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser currIrpUser = mobileAction.getlogin(token);
		currIrpUser = irpUserService.getRolebyUser(currIrpUser);
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		if(currIrpUser.isMicroblogManager()){
			Map<String,String> weibomap = new HashMap<String,String>();
			weibomap.put("managetitle", "微知信息管理");
			weibomap.put("manageid", "");
			weibomap.put("manageurl", "irp/phone/weibomanage_app.action");
			list.add(weibomap);
		};
		if(currIrpUser.isTopicManager()){
			Map<String,String> weibosubmap = new HashMap<String,String>();
			weibosubmap.put("managetitle", "微知专题管理");
			weibosubmap.put("manageid", "");
			weibosubmap.put("manageurl", "irp/phone/microtopic_app.action");
//			Map<String,String> manager = new HashMap<String,String>();
//			manager.put("managetitle", "总经理审核管理");
//			manager.put("manageid", "");
//			manager.put("manageurl", "");
			list.add(weibosubmap);
//			list.add(manager);
		}
		if(currIrpUser.isDocumentManager()){
			Map<String,String> knowleagemap = new HashMap<String,String>();
			knowleagemap.put("managetitle", "知识专题管理");
			knowleagemap.put("manageid", "");
			knowleagemap.put("manageurl", "/irp/phone/docsubject_app.action");
			Map<String,String> tagmap = new HashMap<String,String>();
			tagmap.put("managetitle", "知识标签管理");
			tagmap.put("manageid", "");
			tagmap.put("manageurl", "/irp/phone/doctag_app.action");
			Map<String,String> mapReport = new HashMap<String,String>();
			mapReport.put("managetitle", "知识举报管理");
			mapReport.put("manageid", "");
			mapReport.put("manageurl", "irp/phone/docreport_app.action");
			Map<String,String> examine = new HashMap<String,String>();
			examine.put("managetitle", "知识审核管理");
			examine.put("manageid", "");
			examine.put("manageurl", "irp/phone/docexamine_app.action");
			Map<String,String> interlocution = new HashMap<String,String>();
			interlocution.put("managetitle", "问答记录管理");
			interlocution.put("manageid", "");
			interlocution.put("manageurl", "irp/phone/questionmanager_app.action");
			list.add(knowleagemap);
			list.add(tagmap);
			list.add(mapReport);
			list.add(examine);
			list.add(interlocution);
		}
		if(currIrpUser.isAsseroomManager()){
			Map<String,String> boardroom = new HashMap<String,String>();
			boardroom.put("managetitle", "会议室预约");
			boardroom.put("manageid", "");
			boardroom.put("manageurl", "");
			list.add(boardroom);
		}
		
		if(currIrpUser.isWorkApplyManager()){
			Map<String,String> overtime = new HashMap<String,String>();
			overtime.put("managetitle", "加班审核管理");
			overtime.put("manageid", "");
			overtime.put("manageurl", "");
			list.add(overtime);
		}
		if(currIrpUser.isLeaveApplyManager()){
			Map<String,String> leave = new HashMap<String,String>();
			leave.put("managetitle", "请假审核管理");
			leave.put("manageid", "");
			leave.put("manageurl", "irp/phone/leavemanager_app.action");
			list.add(leave);
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("managetitle", "我的兑换记录");
		map.put("manageid", "");
		map.put("manageurl", "irp/phone/goodsmanager_app.action");
		Map<String,String> mapone = new HashMap<String,String>();
		mapone.put("managetitle", "我的会议日程");
		mapone.put("manageid", "");
		mapone.put("manageurl", "irp/phone/meeting_app.action");
		list.add(map);
		list.add(mapone);
		ActionUtil.writer(JSON.toJSONString(list));
	}
	
	/**
	 * 获取所有商品
	 * 
	 * @author   npz
	 * @date 2017年8月15日
	 */
	public void exchange(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser currIrpUser = mobileAction.getlogin(token);
		pageNum = Integer.valueOf(request.getParameter("pageNum"));
		pageSize = Integer.valueOf(request.getParameter("pageSize"));
		List<IrpGoods> list;
		if(coverstate!=null&&coverstate==1){
			int aDataCount=irpGoodsService.countGoodsByUserid(currIrpUser.getUserid(),searchWord);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
			String _orderby="reorder desc,crtime desc";
			list = irpGoodsService.showAllGoodsByUserid(pageUtil,currIrpUser.getUserid(),searchWord,_orderby);
		}else{
			int aDataCount=irpGoodsService.countGoods(coverstate,searchWord);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
			String _orderby="reorder desc,crtime desc";
			list = irpGoodsService.showAllGoods(pageUtil,coverstate,searchWord,_orderby);
		}
		listgoods = new ArrayList<Map<String,Object>>();
		for(IrpGoods irpgood:list){
			String filePath="";
			Map<String,Object> goodsmap = new HashMap<String,Object>();
			goodsmap.put("converttitle", irpgood.getName());
			goodsmap.put("convertdetail",irpgood.getGoodsdesc());
			goodsmap.put("convertscore",irpgood.getNeedscore());
			goodsmap.put("converttype", irpgood.getSalestate());
			goodsmap.put("convertstorage", irpgood.getStocknum());
			goodsmap.put("convertid", irpgood.getGoodsid());
			goodsmap.put("goodsdetail", "/phone/goodsDetial_app.action");
			IrpAttached attached=irpAttachedService.getAttachedByPrimary(Long.valueOf(irpgood.getGoodsimage())); 
			if(attached!=null){
				String myFileName=attached.getAttfile(); 
				//获得文件路径 
				filePath= ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="+ThumbnailPic.searchFileName(myFileName, "_300X300");
			}else{
				filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand1.jpg";
			}
			goodsmap.put("convertpic",filePath);
			listgoods.add(goodsmap);
		}
		ActionUtil.writer(JSON.toJSONString(listgoods));
	}
	
	public String goodsdetail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long goodsid = Long.valueOf(request.getParameter("goodsid"));
		String token = request.getParameter("token");
		listMedal=irpMedalService.listMedal();
		irpGoods=irpGoodsService.findGoodsByGoodsid(goodsid);                                 
		attachedids=irpGoods.getGoodsimage();
		return SUCCESS;
	}
	/**
	 * 兑换商品mobile
	 * 
	 * @author   npz
	 * @date 2017年8月16日
	 */
	public void exchangegoods(){
		int msg = 0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		Long convertgoodsid = Long.valueOf(request.getParameter("convertgoodsid"));
		Long convertgoodscount = Long.valueOf(request.getParameter("convertgoodscount"));
		Long coverttype = Long.valueOf(request.getParameter("converttype"));
		IrpMessageContent irpMessageContent=new IrpMessageContent();
		IrpUserCovertGoods covert=new IrpUserCovertGoods();
		IrpUser IrpUser = mobileAction.getlogin(token);
		Date date = new Date();
		covert.setUsergoodsid(TableIdUtil.getNextId(IrpUserCovertGoods.TABLE_NAME));
		covert.setCoverttime(date);
		covert.setGoodsid(Long.valueOf(convertgoodsid));
		covert.setCovertnum(Long.valueOf(convertgoodscount));
		covert.setUserid(LoginUtil.getLoginUserId());
		IrpUser user=irpUserService.findUserByUserId(IrpUser.getUserid());
		covert.setCovertuser(user.getUsername());
		IrpGoods goods=irpGoodsService.findGoodsByGoodsid(Long.valueOf(convertgoodsid));
		covert.setCovertgoods(goods.getGoodsname());
		if(coverttype==20){
			Long needscore=goods.getNeedscore();
			Long goodsScorenum=Long.valueOf(convertgoodscount)*needscore;
			if(user.getSumscore()>=goodsScorenum){
				msg = irpUserCovertGoodsService.addCovert(covert);
				Long sumscore=user.getSumscore()-goodsScorenum;
				// 添加一条扣除积分记录
				IrpUserValueLink irpUserValueLink = new IrpUserValueLink();
				irpUserValueLink.setUserid(LoginUtil.getLoginUserId());
				irpUserValueLink.setValueckey("GOODS_COVERT");
				irpUserValueLink.setScore(-goodsScorenum);
				irpUserValueLink.setExperience(10l);
				irpUserValueLinkService.addIrpUserValueLink(irpUserValueLink);
				irpMessageContent.setFromUid(LoginUtil.getLoginUserId());
				irpMessageContent.setContent("恭喜您,成功兑换"+convertgoodscount+"件"+goods.getGoodsname()+",共花费"+goodsScorenum+"积分,兑换前可用积分为"+user.getSumscore()+",您目前剩余可用积分为"+sumscore+"。");
				irpMessageContent.setCruserid(2l);
				irpMessageContent.setMessagetype(0);
				irpMessageContentService.addMessageContent(irpMessageContent);
				user.setSumscore(sumscore);
				user.setSumexperience(user.getSumexperience()+10);
				irpUserService.updateSumScoreExperience(user);
				Long stocknum=goods.getStocknum()-convertgoodscount;
				goods.setStocknum(stocknum);
				goods.setSalesvolume((int) (goods.getSalesvolume()+convertgoodscount));
				irpGoodsService.updateGoodsByGoodsid(goods);
			}
		}else{
			int aDataCount=irpUserMedalService.countUserMedal();
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
			List<IrpUserMedal> listusermedal = irpUserMedalService.getUserMedalByUserid(pageUtil, LoginUtil.getLoginUserId());
			Long medalid=goods.getMedalid();
			/*for(int i=0;i<listusermedal.size();i++){*/
				if(medalid==0){
					medalid=(long) irpUserMedalService.selectMedalid(convertgoodsid);
				}
				List<IrpUserMedal> listmedal = irpUserMedalService.listUserMedal(LoginUtil.getLoginUserId(),medalid,0l);
				//if(listusermedal.get(i).getMedalid().equals(medalid)){
				if(listmedal.size()>0){
					int count=irpUserMedalService.countUserMedal(LoginUtil.getLoginUserId(),convertgoodsid,0l);
					if(count>=convertgoodscount){
						//List<IrpUserMedal> listmedal = irpUserMedalService.listUserMedal(LoginUtil.getLoginUserId(),medalid,0l);
						for(int j=0;j<convertgoodscount;j++){
							listmedal.get(j).setMedalstate(1l);
							irpUserMedalService.updateUserMedalById(listmedal.get(j));
						}
						msg = irpUserCovertGoodsService.addCovert(covert);
						Long countmedal=count-convertgoodscount;
						irpMessageContent.setFromUid(LoginUtil.getLoginUserId());
						irpMessageContent.setContent("恭喜您,成功兑换"+convertgoodscount+"件"+goods.getGoodsname()+",共花费"+convertgoodscount+"个勋章,兑换前可用"+listusermedal.get(0).getMedalname()+"为"+count+"个,您目前可用"+listusermedal.get(0).getMedalname()+"为"+countmedal+"个。");
						irpMessageContent.setCruserid(2l);
						irpMessageContent.setMessagetype(0);
						irpMessageContentService.addMessageContent(irpMessageContent);
						Long stocknum=goods.getStocknum()-convertgoodscount;
						goods.setStocknum(stocknum);
						goods.setSalesvolume((int) (goods.getSalesvolume()+convertgoodscount));
						irpGoodsService.updateGoodsByGoodsid(goods);
					}
				}
			//}
		}
		Map<String,String> map = new HashMap<String,String>();
		if(msg==1){
			map.put("resultstatus", "1000");
			ActionUtil.writer(JSON.toJSONString(map));
		}else{
			map.put("resultstatus", "2000");
			ActionUtil.writer(JSON.toJSONString(map));
		}
		
	}
	

	/**
	 * 向专家提问
	 * 
	 * @author   npz
	 * @date 2017年8月30日
	 */
	public void askexpert(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpUser = mobileAction.getlogin(token);
		int nMsg = 0;
		try {
			nMsg = this.irpQuestionService.addQuestion(askcontext, expert, epertuserid, irpUser, categories);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,String> map = new HashMap<String,String>();
		if(nMsg==1){
			map.put("resultstatus", "1000");
			ActionUtil.writer(JSON.toJSONString(map));
		}else{
			map.put("resultstatus", "2000");
			ActionUtil.writer(JSON.toJSONString(map));
		}
	}
	

	/**
	 * 搜索词条
	 * 
	 * @author   npz
	 * @date 2017年9月8日
	 */
	public void searchTerm(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int num = this.irpTermService.findAllWTermNum(IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL,searchwords,true,qclassifyid);
		PageUtil pageutil = new PageUtil(pageNum, pageSize, num);
		String _obystr = "crtime desc";
		List<IrpTerm> termlist = this.irpTermService.findAllWTerm(pageutil, _obystr, IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL,searchwords,true,qclassifyid);
		for(IrpTerm term:termlist){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("hotwords", term.getTermname());
			map.put("hotwordsid", term.getTermid());
			list.add(map);
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	/**
	 * 热门词条
	 * 
	 * @author   npz
	 * @date 2017年9月11日
	 */
	public void hotterm(){
		String sword = "";
		int num = this.irpTermService.findAllWTermNum(IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL,sword,true,qclassifyid);
		int pagenum = 1;
		int pagesize = 50;
		pagesize = SysConfigUtil.getSysConfigNumValue(IrpTerm.TERMSIZE);
		PageUtil pageutil = new PageUtil(pagenum, pagesize, num);

		String _obystr = "crtime desc";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<IrpTerm> termcatelist = new ArrayList<IrpTerm>();
		termcatelist = this.irpTermService.findAllWTerm(pageutil, _obystr, IrpTerm.TERMSTATUSWORDS, IrpTerm.TERMISDELNORMAL,sword,true,qclassifyid);
		for(IrpTerm term:termcatelist){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("hotwords",term.getTermname());
			map.put("hotwordsid",term.getTermid());
			list.add(map);
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	
	/**
	 * 投票列表
	 * 
	 * @author   npz
	 * @date 2017年9月8日
	 */
	public void votelist(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int num = irpVoteService.findAllVotecount();
		PageUtil pageUtil = new PageUtil(pageNum, pageSize,num);
		String _oOrderby = "crtime desc";
		List<IrpVote> Voteslist = irpVoteService.findAllVote(pageUtil, _oOrderby);
		for(IrpVote vote:Voteslist){
			Map<String,Object> map = new HashMap<String,Object>();
			IrpUser cruser = irpUserService.findUserByUserId(vote.getCruserid());
			map.put("votetitle", vote.getTitle());
			map.put("creatperson",cruser.getName());
			map.put("creattime", vote.getCrtime());
			map.put("voteid", vote.getVoteid());
			IrpVote irptitlev = irpVoteService.findVotetitleBypid(
					vote.getVoteid()).get(0);
			List<IrpVoteOptions> VoteOptionlist = irpVoteOptionsService.findOptionBypid(irptitlev.getVoteid(),"optionid");
			Map<String,Object> voteoptionmap = new HashMap<String,Object>();
			List<Object> votename  = new ArrayList<Object>();
			List<Object> votecount = new ArrayList<Object>();
			for(IrpVoteOptions voteoption:VoteOptionlist){
				votename.add(voteoption.getName());
				votecount.add(voteoption.getCount());
			}
			voteoptionmap.put("articletitle", votename);
			voteoptionmap.put("votenumber", votecount);
			map.put("articlearray", JSON.toJSON(voteoptionmap));
			list.add(map);
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	
	/**
	 * 安卓投票列表
	 * 
	 * @author   npz
	 * @date 2017年9月21日
	 */
	public void vote_list(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int num = irpVoteService.findAllVotecount();
		PageUtil pageUtil = new PageUtil(pageNum, pageSize,num);
		String _oOrderby = "crtime desc";
		List<IrpVote> Voteslist = irpVoteService.findAllVote(pageUtil, _oOrderby);
		for(IrpVote vote:Voteslist){
			Map<String,Object> map = new HashMap<String,Object>();
			IrpUser cruser = irpUserService.findUserByUserId(vote.getCruserid());
			map.put("votetitle", vote.getTitle());
			map.put("creatperson",cruser.getName());
			map.put("creattime", vote.getCrtime());
			map.put("voteid", vote.getVoteid());
			map.put("allcount", vote.getCount());
			IrpVote irptitlev = irpVoteService.findVotetitleBypid(
					vote.getVoteid()).get(0);
			List<IrpVoteOptions> VoteOptionlist = irpVoteOptionsService.findOptionBypid(irptitlev.getVoteid(),"optionid");
			List<Map<String,Object>> voteoptionlist = new ArrayList<Map<String,Object>>();
			for(IrpVoteOptions voteoption:VoteOptionlist){
				Map<String,Object> voteoptionmap = new HashMap<String,Object>();
				voteoptionmap.put("votename", voteoption.getName());
				if(vote.getCount()!=0){
					NumberFormat numberFormat = NumberFormat.getInstance();
					numberFormat.setMaximumFractionDigits(2);
					voteoptionmap.put("votepercent", numberFormat.format((float)voteoption.getCount()/(float)vote.getCount()*100)+"%");
					voteoptionmap.put("votecount", voteoption.getCount());
				}else{
					voteoptionmap.put("votecount", 0);
				}
				voteoptionlist.add(voteoptionmap);
			}
			map.put("voteoptionlist", JSON.toJSON(voteoptionlist));
			list.add(map);
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	/**
	 * 搜索微知
	 * 
	 * @author   npz
	 * @throws Exception 
	 * @date 2017年9月11日
	 */
	public void searchMicorBlog() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long nUserid=irpuser.getUserid();
		int num = this.irpMicroBlogService.searchMicroBlogCount(searchwords, IrpMicroblog.ISDELFALSE);
		PageUtil pageutil = new PageUtil(pageNum, pageSize, num);
		List<IrpMicroblogView> irpMicrobloglist = new ArrayList<IrpMicroblogView>();
		int count=0;
		if(num%pageSize==0){
			count=num/pageSize;
		}else{
			count=num/pageSize+1;
		}
		if(this.pageNum<=count){
			irpMicrobloglist= this.irpMicroBlogService.searchMicroBlog(searchwords, IrpMicroblog.ISDELFALSE, pageutil,nUserid);
		}
		
		collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(nUserid);
		List<Map<Object,Object>> first = new ArrayList<Map<Object,Object>>();
		String[] qq=new String[]{};
		IrpMicroblog microblog=null;
		IrpUser user=null;
		for(int i = 0 ; i < irpMicrobloglist.size();i++){
			Map<Object,Object> map = new HashMap<Object, Object>();
			String content=irpMicrobloglist.get(i).getMICROBLOGCONTENT();
			content=content.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
			String sFilePath=null;
			if(irpMicrobloglist.get(i).getUSERPIC()==null){
				if(irpMicrobloglist.get(i).getSEX()==null){
					sFilePath="/view/images/male.jpg?1"; 
				}else{
					if(irpMicrobloglist.get(i).getSEX()==2){
						sFilePath="/view/images/female.jpg?1"; 
					}else{
						sFilePath="/view/images/male.jpg?1"; 
					}
				}
			}else{
				sFilePath="/file/readfile.action?fileName="+irpMicrobloglist.get(i).getUSERPIC(); 
			}
			if (irpMicrobloglist.get(i).getTRANSPONDID()==0) {
				map.put("wbuserid", irpMicrobloglist.get(i).getUSERID());
				map.put("wbblogmsid", irpMicrobloglist.get(i).getMICROBLOGID());
				map.put("wbhead", sFilePath);
				map.put("wbusername", irpMicrobloglist.get(i).getSHOWNAME());
				map.put("wbtimes", irpMicrobloglist.get(i).getCRTIME());
				map.put("wbsource", irpMicrobloglist.get(i).getFROMDATA());
				map.put("wbcontext", content);
				String ss=irpMicrobloglist.get(i).getMICROBLOGCONTENTIMG();
				if(ss==null){
					map.put("wbimg", qq);
				}else{
					String jieguo=ss.substring(ss.indexOf("name=")+5, ss.indexOf("height")-4);
					String[] aaa=jieguo.split(",");
					List<Map<Object,Object>> sec = new ArrayList<Map<Object,Object>>();
					for(int j=0;j<aaa.length;j++){
						Map<Object, Object> map1=new HashMap<Object,Object>();
						String path="/file/readfile.action?fileName="+aaa[j];
						map1.put("img0", path);
						sec.add(map1);
					}
					map.put("wbimg", sec);
				}
				if(collectionOfUseridlist.contains(irpMicrobloglist.get(i).getMICROBLOGID().toString())){
					map.put("wbcollec", "1");
				}else{
					map.put("wbcollec", "0");
				}
				map.put("wbReblogStatus", "0");
				map.put("wbReblogName", "");
				map.put("wbReblogContxt", "");
				map.put("wbReblogImg", qq);
			}else{
				microblog=irpMicroBlogService.irpMicroblog(irpMicrobloglist.get(i).getTRANSPONDID());
				user=irpUserService.findUserByUserId(microblog.getUserid());
				map.put("wbuserid", irpMicrobloglist.get(i).getUSERID());
				map.put("wbblogmsid", irpMicrobloglist.get(i).getMICROBLOGID());
				if(microblog.getIsdel()==2){
					map.put("wbReblogContxt", "抱歉，由于此微知内容不符合规定，无法查看。");
					map.put("wbtimes", "");
					map.put("wbimg", qq);
					map.put("wbReblogName", "");
					map.put("wbReblogImg", qq);
				}else if(microblog.getIsdel()==1){
					map.put("wbReblogContxt", "抱歉，此微知已被作者删除。");
					map.put("wbtimes", "");
					map.put("wbimg", qq);
					map.put("wbReblogName", "");
					map.put("wbReblogImg", qq);
				}else{
					String con=microblog.getMicroblogcontent();
					con=con.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
					String showName=null;
					if(user.getNickname()==null){
						showName=user.getTruename();
						if(showName==null){
							showName=user.getUsername();
						}
					}else{
						showName=user.getNickname();
					}
					map.put("wbtimes", irpMicrobloglist.get(i).getCRTIME());
					map.put("wbReblogContxt", con);
					map.put("wbimg", qq);
					map.put("wbReblogName", showName);
					String ss=microblog.getMicroblogcontentimg();
					ss=microblog.getMicroblogcontentimg();
					if(ss==null){
						map.put("wbReblogImg", qq);
					}else{
						String jieguo=ss.substring(ss.indexOf("name=")+5, ss.indexOf("height")-4);
						String[] aaa=jieguo.split(",");
						List<Map<Object,Object>> sec = new ArrayList<Map<Object,Object>>();
						for(int j=0;j<aaa.length;j++){
							Map<Object, Object> map1=new HashMap<Object,Object>();
							String path="/file/readfile.action?fileName="+aaa[j];
							map1.put("img0", path);
							sec.add(map1);
						}
						map.put("wbReblogImg", sec);
					}
				}
				map.put("wbhead", sFilePath);
				String showName=null;
				if(user.getNickname()==null){
					showName=user.getTruename();
					if(showName==null){
						showName=user.getUsername();
					}
				}else{
					showName=user.getNickname();
				}
				map.put("wbusername", irpMicrobloglist.get(i).getSHOWNAME());
				map.put("wbtimes", irpMicrobloglist.get(i).getCRTIME());
				map.put("wbcontext", content);
				if(collectionOfUseridlist.contains(irpMicrobloglist.get(i).getMICROBLOGID().toString())){
					map.put("wbcollec", "1");
				}else{
					map.put("wbcollec", "0");
				}
				map.put("wbReblogStatus", "1");
				map.put("wbsource", irpMicrobloglist.get(i).getFROMDATA());
			}
			first.add(map);
		}
		ActionUtil.writer(JSON.toJSONString(first));
	}
	
	public String termdetail(){
		return SUCCESS;
	}
	
}
