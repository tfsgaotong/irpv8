package com.tfs.irp.expert.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.expert.entity.IrpExpertQuestion;
import com.tfs.irp.expert.service.IrpExpertQuestionService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.service.IrpQuestionService;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class ExpertAction extends ActionSupport {
	private int pageNum;
	private int pageSize = 6;
	private String orderField = "";
	private String orderBy = "";
	private String pageHTML;
	private List<IrpUser> expertList;
	private IrpRoleService irpRoleService;
	private IrpExpertQuestionService irpExpertQuestionService;
	private String searchWord = "";
	private String searchType = "";
	private String loginUsername;
	private Long loginUserId;
	private List<IrpExpertQuestion> irpExpertQuestion;
	private List<IrpQuestion> listQuestion;
	private IrpQuestionService irpQuestionService;
	private IrpMicroblogService irpMicroBlogService;
	private IrpMessageContentService irpMessageContentService;
	private Long questionId;
	private String questionInfo;
	private String expertName;
	private Long expertId;
	private IrpUserService irpUserService;
	private IrpUser irpUser;
	private Long expertRoleId;
	// 截断字数
	private int questionShowNum;
	private int textareaShowNum;
	private int expertIntro;
	private IrpMicroblog irpMicroblog;
	private int expertMicroNum;
	private List<IrpMicroblog> irpMicroblogs;
	private IrpMicroblogFocusService irpMicroblogFocusServiceImpl;
	private Long userid;
	private String jsonFile;
	private String questiontitle;
	private String categories;
	private Long categoryId;
	
	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}

	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getQuestiontitle() {
		return questiontitle;
	}

	public void setQuestiontitle(String questiontitle) {
		this.questiontitle = questiontitle;
	}

	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	/**
	 * 专家列表
	 * 
	 * @return
	 */
	public String expertList() {
		textareaShowNum=Integer.parseInt(SysConfigUtil.getSysConfigValue("TEXTAREA_SHOWNUM"));
		return SUCCESS;
	}
	private String tokens;
	
	public String getTokens() {
		return tokens;
	}
	public void setTokens(String tokens) {
		this.tokens = tokens;
	}
	public String expertListApp() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens=token;
		textareaShowNum=Integer.parseInt(SysConfigUtil.getSysConfigValue("TEXTAREA_SHOWNUM"));
		return SUCCESS;
	}
	/**
	 * 获得专家列表
	 * mobile
	 * @return
	 */
	public void expertListMobile(){
		//专家id
		Long expertRoleId = Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID"));
		int nDataCount = irpRoleService.findUsersCountByRoleId(expertRoleId,"","");
		PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,nDataCount);
		List<IrpUser> list = irpRoleService.findUsersOfPageByRoleId(expertRoleId, pageUtil, "crtime desc");
		List mobilelist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			IrpUser irpUser = list.get(i);
			mobilelist.add("{\"expertname\":\""+getShowPageViewNameByUserId(irpUser.getUserid())+"\"");
			mobilelist.add("\"expertsex\":\""+irpUser.getSex()+"\"");
			mobilelist.add("\"expertuserid\":\""+irpUser.getUserid()+"\"");
			mobilelist.add("\"expertarea\":\""+irpUser.getLocation()+"\"");
			mobilelist.add("\"expertdescrip\":\""+irpUser.getExpertintro()+"\"");	
			mobilelist.add("\"expertpic\":\""+irpUser.getUserpic()+"\"");
			mobilelist.add("\"expertattension\":\""+findFocusByUserid(irpUser.getUserid())+"\"");
			mobilelist.add("\"expertfuns\":\""+findFocusMeByUserid(irpUser.getUserid())+"\"");
			mobilelist.add("\"expertweiblog\":\""+findMicroblogById(irpUser.getUserid())+"\"}");
		}
		ActionUtil.writer(mobilelist.toString());
	}
	

	/**
	 * 待解答(专家特有)
	 * 
	 * @return
	 */
	public String questionToExpert() {
		questionShowNum = Integer.parseInt(SysConfigUtil
				.getSysConfigValue("QUESTION_SHOWNUM"));
		expertList = irpRoleService.findUsersOfPageByRoleId(Long
				.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID")));
		loginUsername = LoginUtil.getLoginUser().getUsername();
		loginUserId = LoginUtil.getLoginUserId();
		try {
			int nDataCount = irpExpertQuestionService
					.totalQuestion(loginUserId);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
					nDataCount);
			irpUser = irpUserService.findUserByUserId(loginUserId);
			listQuestion = irpExpertQuestionService.questionsOfExpertResolute(
					loginUserId, pageUtil);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 待解答 手机
	 * mobile
	 * @return
	 */
	private int mobilemyneedanswerpagenum;
	public int getMobilemyneedanswerpagenum() {
		return mobilemyneedanswerpagenum;
	}
	public void setMobilemyneedanswerpagenum(int mobilemyneedanswerpagenum) {
		this.mobilemyneedanswerpagenum = mobilemyneedanswerpagenum;
	}
	public void myNeedAnswerQMobile(){
		try {
			int nDataCount = irpExpertQuestionService.totalQuestion(LoginUtil.getLoginUserId());
			PageUtil pageUtil = new PageUtil(mobilemyneedanswerpagenum,10,nDataCount);
			List<IrpQuestion> list = irpExpertQuestionService.questionsOfExpertResolute(LoginUtil.getLoginUserId(), pageUtil);
			List mobilelist = new ArrayList();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (int i = 0; i < list.size(); i++) {
				IrpQuestion irpQuestion = list.get(i);
				mobilelist.add("{\"showname\":\""+getShowPageViewNameByUserId(irpQuestion.getCruserid())+"\"");
				mobilelist.add("\"questionid\":\""+irpQuestion.getQuestionid()+"\"");
				mobilelist.add("\"userpic\":\""+getIrpUserByUserid(irpQuestion.getCruserid()).getUserpic()+"\"");
				mobilelist.add("\"sex\":\""+getIrpUserByUserid(irpQuestion.getCruserid()).getSex()+"\"");
				mobilelist.add("\"cruserid\":\""+irpQuestion.getCruserid()+"\"");
				mobilelist.add("\"crtime\":\""+sdf.format(irpQuestion.getCrtime())+"\"");
				mobilelist.add("\"browsernum\":\""+irpQuestion.getBrowsecount()+"\"");
				mobilelist.add("\"answernum\":\""+findReplyCountByQuestionId(irpQuestion.getQuestionid())+"\"");
				String titleval = irpQuestion.getTitle();
				if (titleval!=null) {
					mobilelist.add("\"title\":\""+titleval.replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");	
				}
				String htmlcontentval = irpQuestion.getHtmlcontent();
				if (htmlcontentval!=null) {
					mobilelist.add("\"htmlcontent\":\""+htmlcontentval.replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");	
				}
				mobilelist.add("\"status\":\""+irpQuestion.getStatus()+"\"}");
			}
			mobilelist.add("{\"mobilenum\":\""+nDataCount+"\"}");
			ActionUtil.writer(mobilelist.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 浏览量
	 * @param _nQuestionId
	 * @return
	 */
	public int findReplyCountByQuestionId(long _nQuestionId) {
		int nCount = 0;
		try {
			nCount = irpQuestionService.replyTotal(_nQuestionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nCount;
	}
	/**
	 * 返回向专家提问页面表
	 * 
	 * @return
	 */
	public String askExpert() {
		textareaShowNum = Integer.parseInt(SysConfigUtil
				.getSysConfigValue("TEXTAREA_SHOWNUM"));
		return "success";
	}

	/**
	 * 向专家提问
	 * 
	 * @return
	 */
	public void ask() {
		int nMsg = 0;
		try {
			nMsg = this.irpQuestionService.addQuestionAndExpert(questionInfo,
					expertName, expertId,questiontitle,jsonFile,categories);
			IrpUser user=irpUserService.findUserByUserId(expertId);
			user.setAnswercount(user.getAnswercount()+1);
			irpUserService.userEdit(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionUtil.writer("" + nMsg);
	}

	/**
	 * 按照专家名字搜索专家
	 * 
	 * @return
	 */
	public String searchExpertByName() {
		expertRoleId = Long.parseLong(SysConfigUtil
				.getSysConfigValue("EXPERT_ROLE_ID"));
		searchWord = expertName;
		int nDataCount = irpRoleService.findUsersCountByRoleId(expertRoleId,
				searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
				nDataCount);
		if ("".equals(expertName)) {
			expertList = irpRoleService.findUsersOfPageByRoleId(expertRoleId,
					pageUtil, orderBy);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		} else {
			if (expertRoleId != null) {
				expertList = irpRoleService.findUsersOfPageByRoleIdAndName(
						expertRoleId, pageUtil, orderBy, expertName);
			}
		}
		return "success";
	}
	/**
	 * 根据专家id取出关注的总人数
	 * @return
	 */
	public Integer findFocusByUserid(Long _userid){
		return irpMicroblogFocusServiceImpl.countMicroblogFocusUserid(_userid);
	}
	/**
	 * 根据userid查出粉丝
	 * @return
	 */
	public Integer findFocusMeByUserid(Long _userid){
		return irpMicroblogFocusServiceImpl.findMicroblogUserIdCount(_userid);
	}
	/**
	 * 根据id查出微知总数
	 * @return
	 */
	public Integer findMicroblogById(Long _userid){
		return irpMicroBlogService.coutnMicroblogOfUserid(_userid,IrpMicroblog.ISDELFALSE);
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

	public List<IrpUser> getExpertList() {
		return expertList;
	}

	public void setExpertList(List<IrpUser> expertList) {
		this.expertList = expertList;
	}

	public IrpRoleService getIrpRoleService() {
		return irpRoleService;
	}

	public void setIrpRoleService(IrpRoleService irpRoleService) {
		this.irpRoleService = irpRoleService;
	}

	public IrpExpertQuestionService getIrpExpertQuestionService() {
		return irpExpertQuestionService;
	}

	public void setIrpExpertQuestionService(
			IrpExpertQuestionService irpExpertQuestionService) {
		this.irpExpertQuestionService = irpExpertQuestionService;
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

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public List<IrpExpertQuestion> getIrpExpertQuestion() {
		return irpExpertQuestion;
	}

	public void setIrpExpertQuestion(List<IrpExpertQuestion> irpExpertQuestion) {
		this.irpExpertQuestion = irpExpertQuestion;
	}

	public Long getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	public List<IrpQuestion> getListQuestion() {
		return listQuestion;
	}

	public void setListQuestion(List<IrpQuestion> listQuestion) {
		this.listQuestion = listQuestion;
	}

	public IrpQuestionService getIrpQuestionService() {
		return irpQuestionService;
	}

	public void setIrpQuestionService(IrpQuestionService irpQuestionService) {
		this.irpQuestionService = irpQuestionService;
	}

	public String getQuestionInfo() {
		return questionInfo;
	}

	public void setQuestionInfo(String questionInfo) {
		this.questionInfo = questionInfo;
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public Long getExpertId() {
		return expertId;
	}

	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpUser getIrpUser() {
		return irpUser;
	}

	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}

	public Long getExpertRoleId() {
		return expertRoleId;
	}

	public void setExpertRoleId(Long expertRoleId) {
		this.expertRoleId = expertRoleId;
	}

	public int getQuestionShowNum() {
		return questionShowNum;
	}

	public void setQuestionShowNum(int questionShowNum) {
		this.questionShowNum = questionShowNum;
	}

	public int getTextareaShowNum() {
		return textareaShowNum;
	}

	public void setTextareaShowNum(int textareaShowNum) {
		this.textareaShowNum = textareaShowNum;
	}

	public int getExpertIntro() {
		return expertIntro;
	}

	public void setExpertIntro(int expertIntro) {
		this.expertIntro = expertIntro;
	}

	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}

	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}

	public IrpMicroblog getIrpMicroblog() {
		return irpMicroblog;
	}

	public void setIrpMicroblog(IrpMicroblog irpMicroblog) {
		this.irpMicroblog = irpMicroblog;
	}

	public int getExpertMicroNum() {
		return expertMicroNum;
	}

	public void setExpertMicroNum(int expertMicroNum) {
		this.expertMicroNum = expertMicroNum;
	}

	public List<IrpMicroblog> getIrpMicroblogs() {
		return irpMicroblogs;
	}

	public void setIrpMicroblogs(List<IrpMicroblog> irpMicroblogs) {
		this.irpMicroblogs = irpMicroblogs;
	}

	public IrpMicroblogFocusService getIrpMicroblogFocusServiceImpl() {
		return irpMicroblogFocusServiceImpl;
	}

	public void setIrpMicroblogFocusServiceImpl(
			IrpMicroblogFocusService irpMicroblogFocusServiceImpl) {
		this.irpMicroblogFocusServiceImpl = irpMicroblogFocusServiceImpl;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	/**
	 * 更具用户id获得用户的对象
	 * @param _cruserid
	 * @return
	 */
	public IrpUser getIrpUserByUserid(long _cruserid){
		IrpUser irpUser = null;
		irpUser = this.irpUserService.findUserByUserId(_cruserid);
		return irpUser;
		
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
    
    /**
     * 获得专家列表
     * @return
     */
    public String findExpertList() {
    	expertRoleId = Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID"));
    	expertIntro = Integer.parseInt(SysConfigUtil.getSysConfigValue("EXPERT_INTRO"));
		int nDataCount = irpUserService.findExpertCount(categoryId, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		expertList = irpUserService.findExpertList(categoryId, pageUtil, searchWord, searchType);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
    
    /**
     * 获得选择专家列表
     * @return
     */
    public String selectExpertList() {
		int nDataCount = irpUserService.findExpertCount(categoryId, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		expertList = irpUserService.findExpertList(categoryId, pageUtil, searchWord, searchType);
		this.pageHTML = pageUtil.getClientPageHtml("sepage(#PageNum#)");
		return SUCCESS;
	}
    
    /**
     * 
     * selectExpertListApp:手机端获取所有专家的接口
     * @author Administrator 
     * @since JDK 1.8
     */
    public void selectExpertListApp() {
        // 初始化一个存放json列表的list
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		
		// 获得所有专家列表
		List<IrpUser> expertList = irpUserService.findExpertList(categoryId, null, null, null);
		
		for (IrpUser expert : expertList) {
		    
		    // 初始化一个存放json对象的map
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("expertNickName", expert.getNickname());
            jsonMap.put("expertId", expert.getUserid());
            
            // 添加到json列表中
            jsonList.add(jsonMap);
        }
		ActionUtil.writer(JsonUtil.list2json(jsonList));
	}
    
    public void recommendExpert() {
    	IrpMessageContent message = new IrpMessageContent();
    	message.setFromUid(expertId);
    	String conntent = SysConfigUtil.getSysConfigValue("RECOMMEND_EXPERT_CONTENT");
    	String sUserInfo = LoginUtil.getLoginUser().getShowName();
    	IrpQuestion question = irpQuestionService.findQuestionDetail(questionId);
    	String sQuestionName = "";
    	if(question.getTitle()!=null && question.getTitle().length()>0){
    		sQuestionName = question.getTitle();
    	}else{
    		sQuestionName = question.getTextcontent();
    	}
    	String sAllName = sQuestionName;
    	if(sQuestionName.length()>10){
    		sQuestionName=sQuestionName.substring(0,10)+"...";
    	}
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    	String sQuestionInfo = "<a class='linkbh14'  title='"+sAllName+"' href=\""+redirectURI+"/question/questionDetail.action?questionid="+question.getQuestionid()+"\" target=\"_blank\">"+sQuestionName+"</a>";
		//String sQuestionInfo = "<a class='linkbh14' href='javascript:void(0)' title='"+sAllName+"' onclick='connectQuestion("+question.getQuestionid()+")'>"+sQuestionName+"</a>";
    	conntent = conntent.replace("#username#", sUserInfo);
    	conntent = conntent.replace("#question#", sQuestionInfo);
    	message.setContent(conntent);
    	int nMsg = irpMessageContentService.addMessageContent(message);
    	ActionUtil.writer("" + nMsg);
	}
    public void recommendExpertApp() {
    	HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens=token;
		IrpUser irpuser = mobileAction.getlogin(token);
    	IrpMessageContent message = new IrpMessageContent();
    	message.setFromUid(expertId);
    	String conntent = SysConfigUtil.getSysConfigValue("RECOMMEND_EXPERT_CONTENT");
    	String sUserInfo = irpuser.getUsername();
    	IrpQuestion question = irpQuestionService.findQuestionDetail(questionId);
    	String sQuestionName = "";
    	if(question.getTitle()!=null && question.getTitle().length()>0){
    		sQuestionName = question.getTitle();
    	}else{
    		sQuestionName = question.getTextcontent();
    	}
    	String sAllName = sQuestionName;
    	if(sQuestionName.length()>10){
    		sQuestionName=sQuestionName.substring(0,10)+"...";
    	}
    	//HttpServletRequest request = ServletActionContext.getRequest();
    	String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    	String sQuestionInfo = "<a class='linkbh14'  title='"+sAllName+"' href=\""+redirectURI+"/question/questionDetail.action?questionid="+question.getQuestionid()+"\" target=\"_blank\">"+sQuestionName+"</a>";
		//String sQuestionInfo = "<a class='linkbh14' href='javascript:void(0)' title='"+sAllName+"' onclick='connectQuestion("+question.getQuestionid()+")'>"+sQuestionName+"</a>";
    	conntent = conntent.replace("#username#", sUserInfo);
    	conntent = conntent.replace("#question#", sQuestionInfo);
    	message.setContent(conntent);
    	int nMsg = irpMessageContentService.addMessageContent(message);
    	ActionUtil.writer("" + nMsg);
	}
}
