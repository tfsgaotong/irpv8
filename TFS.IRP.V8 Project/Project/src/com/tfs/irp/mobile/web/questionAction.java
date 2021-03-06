package com.tfs.irp.mobile.web;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.category_file_link.service.IrpCategoryFileLinkService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.expert.entity.IrpExpertQuestion;
import com.tfs.irp.expert.service.IrpExpertQuestionService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblogatme.entity.IrpMicroblogAtmeKey;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogComment;
import com.tfs.irp.motetread.entity.IrpMostTread;
import com.tfs.irp.motetread.service.IrpMoteTreadService;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.entity.IrpQuestionExample;
import com.tfs.irp.question.entity.IrpQuestionExample.Criteria;
import com.tfs.irp.question.entity.IrpQuestionExcel;
import com.tfs.irp.question.service.IrpQuestionService;
import com.tfs.irp.role.dao.IrpUserroleLinkDAO;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.ExcelUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

@SuppressWarnings("serial")
public class questionAction extends ActionSupport {

	private IrpQuestionService irpQuestionService;
	private IrpUserService irpUserService;
	private IrpCategoryFileLinkService irpCategoryFileLinkService;
	private String questionInfo;
	// 其他问题列表
	private List<IrpQuestion> listQuestion;
	// 专家答案列表
	private List<IrpQuestion> listExpertQuestion;
	private String loginUsername;
	private int pageNum = 1;
	// 问题列表显示条数
	private int pageSize = 12;
	private int pageSizeshow = 10;
	// 答案列表显示条数
	private int pageSize1 = 5;
	private String pageHTML;
	private Long questionid;
	// 浏览总数
	private Long browsecount;
	// 专家列表
	private List<IrpUser> expertList;
	private IrpRoleService irpRoleService;
	private IrpQuestion irpQuestion;
	// 最佳答案列表
	private List<IrpQuestion> irpQuestion1;
	private String orderField = "";
	private String orderBy = "";
	private String text;
	private String modifyText;
	private int num;
	private String answer;
	private Long answerid;
	// 回复总数
	private int totalReply;

	// 专家id
	private Long expertId;
	// 要显示的标题
	private String title;
	private Long expertRoleId;
	// 截断字数
	private int questionShowNum;
	// 输入框字数的限制
	private int textareaShowNum;
	private Long loginUserId;
	// 用户首页
	private Long personid;
	private IrpUser irpUser;
	// 附件
	private String jsonFile;
	// 附件集合
	private List<IrpAttached> attacheds;

	private Integer questionstatus;

	private Long answercruserid;
	
	private String categories;

	private IrpMoteTreadService irpMoteTreadService;

	private IrpMessageContentService irpMessageContentService;
	private List<IrpQuestion> newstquestionlist;

	private List<IrpQuestion> hotquestionlist;

	private List illquestionlist;
	
	private String contentanswer;
	
	private String questiontitle;
	
	private String pageviewname;
	
	private List<IrpAttached> attachedsofother;
	
	private String textareaModifytitle;
	
	private int id;
	
	private List<IrpQuestion> connqlist;
	
	public List<IrpQuestion> getConnqlist() {
		return connqlist;
	}

	public void setConnqlist(List<IrpQuestion> connqlist) {
		this.connqlist = connqlist;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTextareaModifytitle() {
		return textareaModifytitle;
	}

	public void setTextareaModifytitle(String textareaModifytitle) {
		this.textareaModifytitle = textareaModifytitle;
	}

	public String getTextareaModifycontent() {
		return textareaModifycontent;
	}

	public void setTextareaModifycontent(String textareaModifycontent) {
		this.textareaModifycontent = textareaModifycontent;
	}

	private String textareaModifycontent;
	
	public List<IrpAttached> getAttachedsofother() {
		return attachedsofother;
	}

	public void setAttachedsofother(List<IrpAttached> attachedsofother) {
		this.attachedsofother = attachedsofother;
	}

	public String getPageviewname() {
		return pageviewname;
	}

	public void setPageviewname(String pageviewname) {
		this.pageviewname = pageviewname;
	}

	public String getQuestiontitle() {
		return questiontitle;
	}

	public void setQuestiontitle(String questiontitle) {
		this.questiontitle = questiontitle;
	}

	private IrpAttachedService irpAttachedService;
	
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public String getContentanswer() {
		return contentanswer;
	}

	public int getPageSizeshow() {
		return pageSizeshow;
	}

	public void setPageSizeshow(int pageSizeshow) {
		this.pageSizeshow = pageSizeshow;
	}

	public void setContentanswer(String contentanswer) {
		this.contentanswer = contentanswer;
	}

	private Set<Long> illanswerset;
	public Set<Long> getIllanswerset() {
		return illanswerset;
	}

	public void setIllanswerset(Set<Long> illanswerset) {
		this.illanswerset = illanswerset;
	}

	public List getIllquestionlist() {
		return illquestionlist;
	}

	public void setIllquestionlist(List illquestionlist) {
		this.illquestionlist = illquestionlist;
	}

	public List<IrpQuestion> getHotquestionlist() {
		return hotquestionlist;
	}

	public void setHotquestionlist(List<IrpQuestion> hotquestionlist) {
		this.hotquestionlist = hotquestionlist;
	}

	public List<IrpQuestion> getNewstquestionlist() {
		return newstquestionlist;
	}

	public void setNewstquestionlist(List<IrpQuestion> newstquestionlist) {
		this.newstquestionlist = newstquestionlist;
	}

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}

	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}

	public IrpMoteTreadService getIrpMoteTreadService() {
		return irpMoteTreadService;
	}

	public void setIrpMoteTreadService(IrpMoteTreadService irpMoteTreadService) {
		this.irpMoteTreadService = irpMoteTreadService;
	}

	private IrpExpertQuestionService irpExpertQuestionService;

	public IrpExpertQuestionService getIrpExpertQuestionService() {
		return irpExpertQuestionService;
	}

	public void setIrpExpertQuestionService(
			IrpExpertQuestionService irpExpertQuestionService) {
		this.irpExpertQuestionService = irpExpertQuestionService;
	}

	public Long getAnswercruserid() {
		return answercruserid;
	}

	public void setAnswercruserid(Long answercruserid) {
		this.answercruserid = answercruserid;
	}

	public Integer getQuestionstatus() {
		return questionstatus;
	}

	public void setQuestionstatus(Integer questionstatus) {
		this.questionstatus = questionstatus;
	}

	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}

	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	}

	public IrpCategoryFileLinkService getIrpCategoryFileLinkService() {
		return irpCategoryFileLinkService;
	}

	public void setIrpCategoryFileLinkService(
			IrpCategoryFileLinkService irpCategoryFileLinkService) {
		this.irpCategoryFileLinkService = irpCategoryFileLinkService;
	}

	/**
	 * 提问
	 * 
	 * @return
	 */
	public void addQuestion() {
		// 缩图转到附件图 jsonFile
		int nMsg = 0;
		int resultstatus=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		long questionId = 0;
		try {			
			IrpUser expertUser = irpUserService.findUserByUserId(expertId);
			if (expertUser!=null) {
				questionId = this.irpQuestionService.addQuestionAndExpert(questionInfo, expertUser.getShowName(), expertUser.getUserid(),questiontitle,jsonFile,categories,irpuser);
			} else {
				questionId = this.irpQuestionService.addQuestion(
						questionInfo, jsonFile,questiontitle,categories,irpuser);
				if (questionId > 0L) {
					nMsg = 1;
				} else {
					nMsg = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*if(nMsg==1){
         	resultstatus=1000;
		}else{
			resultstatus=0;
		}
		JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		ActionUtil.writer(j.toJSONString());*/
		ActionUtil.writer(""+questionId);
	}


	/**
	 * 删除
	 * 
	 * @return
	 */
	public void delQuestion() {
		int nMsg = 0;
		// 判断此问题是不是属于我的
		IrpQuestion irpQuestion = this.irpQuestionService
				.findQuestionDetail(questionid);
		if (irpQuestion.getCruserid().equals(LoginUtil.getLoginUserId())) {
			try {
				nMsg = irpQuestionService.delQuestion(questionid);
				//同时删除该问题下所有的答案
				this.irpQuestionService.deleteQuestionAndAnswers(questionid);
				//同时删除附件
				this.irpAttachedService.deleteFileByExpt(questionid,IrpAttached.QUESTION_DOCIDTYPE);
				//同时删除类别关系
				List<Long> fileIds = new ArrayList<Long>();
				fileIds.add(questionid);
				this.irpCategoryFileLinkService.deleteCategoryFileLinkByFid(fileIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ActionUtil.writer("" + nMsg);
	}


	/**
	 * 获取更多其它答案
	 * @param mobile
	 * @return 
	 */
	private int mobileotherpnum;
	public int getMobileotherpnum() {
		return mobileotherpnum;
	}
	public void setMobileotherpnum(int mobileotherpnum) {
		this.mobileotherpnum = mobileotherpnum;
	}
	public void detailMobileOtherList(){
		List otherqulist = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			int othernum = irpQuestionService.countAnswers(questionid);
			PageUtil otherpageUtil = new PageUtil(mobileotherpnum, 10, othernum);
			List<IrpQuestion> otherlist = irpQuestionService.findAnswersByPage(otherpageUtil,"crtime desc", questionid);
			 for (int i = 0; i < otherlist.size(); i++) {
					IrpQuestion otherquestion = otherlist.get(i);
					otherqulist.add("{\"ohtmlcontent\":\""+otherquestion.getHtmlcontent().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
					otherqulist.add("\"ocrtime\":\""+sdf.format(otherquestion.getCrtime())+"\"");
					otherqulist.add("\"oshowname\":\""+getShowPageViewNameByUserId(otherquestion.getCruserid())+"\"");
					otherqulist.add("\"pgtotalnum\":\""+othernum+"\"");
					otherqulist.add("\"ouserid\":\""+otherquestion.getCruserid()+"\"}");
			 }
			String str = "{\"otherqulist\":"+otherqulist+"}";
			ActionUtil.writer(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private IrpUser loginUser;
	
	
	public IrpUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(IrpUser loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * 问题明细
	 * 
	 * @return
	 */
	public String questionDetail() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			tokens=token;
			IrpUser irpuser = mobileAction.getlogin(token);
			
			try {
				long questionRoleId = Long.parseLong(SysConfigUtil.getSysConfigValue("QUESTION_ROLE_ID"));
				List<Long> roleIds = irpUserroleLinkDAO.findRoleIdByUserId(irpuser.getUserid());
				for (long nRoleId : roleIds) {
					if(nRoleId==questionRoleId){
						irpuser.setQuestionManager(true);
					}
				}
			}  catch (SQLException e) {
				e.printStackTrace();
			}
			loginUser=irpuser;
			// 答案总数
			num = irpQuestionService.countAnswers(questionid);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize1, num);
			// 处理排序
			String sOrderByClause = "";
			if (this.orderField == null || this.orderField.equals("")) {
				sOrderByClause = "crtime desc";
			} else {
				sOrderByClause = this.orderField + " " + this.orderBy;
			}
			// 除了专家答案和最佳答案
			listQuestion = irpQuestionService.findAnswersByPage(pageUtil,
					sOrderByClause, questionid);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
			// 专家答案vf
			listExpertQuestion = irpQuestionService
					.findExpertAnswers(questionid);
			// 获取当前的问题
			irpQuestion = irpQuestionService.findQuestionDetail(questionid);
			irpUser = irpUserService
					.findUserByUserId(irpQuestion.getCruserid());
			// 浏览总数
			browsecount = irpQuestion.getBrowsecount() == null ? 1L
					: irpQuestion.getBrowsecount()+1;
			irpQuestionService.modifyBrowCount(questionid, browsecount);
			// 当前登陆用户
			loginUsername = irpuser.getUsername();
			// 最佳答案
			irpQuestion1 = irpQuestionService.findBestAnswer(questionid);
			
			// 获的回答的人总数
			totalReply = irpQuestionService.replyTotal(questionid);
			//图片附件
			attacheds = irpQuestionService.findQuestionAttached(questionid,IrpAttached.TYPEIDPIC);
			//其它类型附件
			attachedsofother = irpQuestionService.findQuestionAttached(questionid,IrpAttached.TYPEIDOTHER);
			
			Iterator itattac = attacheds.iterator();
			pageviewname = "";
			while (itattac.hasNext()) {
				IrpAttached irpAttached = (IrpAttached) itattac.next();
				if(irpAttached.getTypeid()==2){
					pageviewname +=irpAttached.getAttfile()+",";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	

	/**
	 * 我回答过的问题
	 * 
	 * @return
	 */
	public String findQuestionAnswerOfMine() {
		questionShowNum = Integer.parseInt(SysConfigUtil
				.getSysConfigValue("QUESTION_SHOWNUM"));
		loginUsername = LoginUtil.getLoginUser().getUsername();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("myAnswer", "true");
			map.put("categoryId", this.id);
			int nDataCount = irpQuestionService.findAllQuestionsInCategory(map);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
					nDataCount);
			listQuestion = irpQuestionService.findQuestionsInCategoryByPage(pageUtil,null,map);
			if (listQuestion != null) {
				for (int i = 0; i < listQuestion.size(); i++) {
					irpUser = irpUserService.findUserByUserId(listQuestion.get(
							i).getCruserid());
				}
			}
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	/**
	 * 我回答的
	 * mobile
	 * @return
	 */
	private int mobilemyqanswerpagenum;
	public int getMobilemyqanswerpagenum() {
		return mobilemyqanswerpagenum;
	}
	public void setMobilemyqanswerpagenum(int mobilemyqanswerpagenum) {
		this.mobilemyqanswerpagenum = mobilemyqanswerpagenum;
	}
	public void findMineAnswerQuOfMobile(){
		
		try {
			int nDataCount = irpQuestionService.findAnwserQuestionMineCount();
			
			PageUtil pageUtil = new PageUtil(mobilemyqanswerpagenum,10,nDataCount);
			
			List<IrpQuestion> list = irpQuestionService.findAnwserQuestionMine(pageUtil);
			List mobilelist = new ArrayList();
			if(list!=null && list.size()>0){
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
			}
			ActionUtil.writer(mobilelist.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	/**
	 * 根据问答的id获得附件(图片)
	 * @param _questionid
	 * @return
	 */
	public List<IrpAttached> getIrpAttached(Long _questionid){
		return  irpQuestionService.findQuestionAttached(_questionid,IrpAttached.TYPEIDPIC);
	}
	/**
	 * 根据问答的id获得附件(其它)
	 * @param _questionid
	 * @return
	 */
	public List<IrpAttached> getIrpAttachedOther(Long _questionid){
		return  irpQuestionService.findQuestionAttached(_questionid,IrpAttached.TYPEIDOTHER);
	}
	public int getMotetread(Long _id) {
		return irpMoteTreadService.IrpMoteThreadCountByQuestionid(_id,
				IrpMostTread.MOTE_TREAD_STATUS_DING,
				IrpMostTread.MOTE_QUESTION_TYPE);
	}

	public int getOppose(Long _id) {
		return irpMoteTreadService.IrpMoteThreadCountByQuestionid(_id,
				IrpMostTread.MOTE_TREAD_STATUS_CAI,
				IrpMostTread.MOTE_QUESTION_TYPE);
	}

	public String getUsernameByQuestionid(Long _id) {
		String usernameinfo = "";
		StringBuffer usernameSb = new StringBuffer();
		List list = null;
		list = this.irpUserService.findUsernamebyquestionid(
				IrpUser.USER_STATUS_REG, _id,
				IrpMostTread.MOTE_TREAD_STATUS_DING,
				IrpMostTread.MOTE_QUESTION_TYPE);
		if (list.size() > 0) {
			Iterator iterator = list.iterator();
			if (list.size() > 3) {
				int i = 1;
				while (iterator.hasNext()) {
					Map map = (Map) iterator.next();
					if (i <= 3) {
						usernameSb.append(map.get("SHOWNAME") + ",");
					}
					i++;
				}
				usernameinfo = usernameSb.substring(0, usernameSb.length() - 1)
						+ " 等赞同";
				usernameinfo = "<a href=\"javascript:void(0)\" title=\"点击展开全部\" onclick=\"unfoldAllAndorse("
						+ _id + ")\">" + usernameinfo + "</a>";
			} else {
				while (iterator.hasNext()) {
					Map map = (Map) iterator.next();
					usernameSb.append(map.get("SHOWNAME") + ",");
				}
				usernameinfo = usernameSb.substring(0, usernameSb.length() - 1)
						+ " 赞同";
			}
		}
		return usernameinfo;
	}

	public void getMotePeopleConsent() {
		String usernameinfo = "";
		StringBuffer usernameSb = new StringBuffer();
		List list = null;
		list = this.irpUserService.findUsernamebyquestionid(
				IrpUser.USER_STATUS_REG, questionid,
				IrpMostTread.MOTE_TREAD_STATUS_DING,
				IrpMostTread.MOTE_QUESTION_TYPE);
		if (list.size() > 0) {
			Iterator iterator = list.iterator();
			if (list.size() > 3) {
				int i = 1;
				while (iterator.hasNext()) {
					Map map = (Map) iterator.next();
					if (i <= 3) {
						usernameSb.append(map.get("SHOWNAME") + ",");
					}
					i++;
				}
				usernameinfo = usernameSb.substring(0, usernameSb.length() - 1)
						+ " 等赞同";
				usernameinfo = "<a href=\"javascript:void(0)\" title=\"点击展开全部\" onclick=\"unfoldAllAndorse("
						+ questionid + ")\">" + usernameinfo + "</a>";
			} else {
				while (iterator.hasNext()) {
					Map map = (Map) iterator.next();
					usernameSb.append(map.get("SHOWNAME") + ",");
				}
				usernameinfo = usernameSb.substring(0, usernameSb.length() - 1)
						+ " 赞同";
			}
		}
		ActionUtil.writer(usernameinfo);
	}

	/**
	 * 展开全部
	 */
	public void getMotePeopleConsentAll() {
		String usernameinfo = "";
		StringBuffer usernameSb = new StringBuffer();
		List list = null;
		list = this.irpUserService.findUsernamebyquestionid(
				IrpUser.USER_STATUS_REG, questionid,
				IrpMostTread.MOTE_TREAD_STATUS_DING,
				IrpMostTread.MOTE_QUESTION_TYPE);

		if (list.size() > 0) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				Map map = (Map) iterator.next();
				usernameSb.append(map.get("SHOWNAME") + ",");
			}
			usernameinfo = usernameSb.substring(0, usernameSb.length() - 1)
					+ " 赞同";

		}

		ActionUtil.writer(usernameinfo);
	}

	public void hits() {
		// 浏览总数
		try {
			IrpQuestion birpQuestion = irpQuestionService.findQuestionDetail(questionid);
			long browsecount = birpQuestion.getBrowsecount() == null ? 1L
					: birpQuestion.getBrowsecount() + 1;
			irpQuestionService.modifyBrowCount(questionid, browsecount);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回答问题
	 * 
	 * @return
	 */
	public void answerQuestion() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser loginUser = mobileAction.getlogin(token);
		try {
			long expertRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID"));
			List<Long> roleIds = irpUserroleLinkDAO.findRoleIdByUserId(loginUser.getUserid());
			for (long nRoleId : roleIds) {
				if(nRoleId==expertRoleId){
					loginUser.setExpert(true);
				}
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		//IrpUser loginUser = LoginUtil.getLoginUser();
		loginUsername = loginUser.getUsername();
		loginUserId = loginUser.getUserid();
		int nMsg = 0;
		int expertStatus = 0;
		try {
			nMsg = irpQuestionService.addTextAnswerMobile(jsonFile,questionid, text,contentanswer,
					browsecount,null,null);
			if (SysConfigUtil.getSysConfigValue(IrpQuestion.ENABLEEXPERTANSWER)
					.equals("false")) {

				if (loginUser.isExpert()) {
					expertStatus = 1;
				}
			} else {

				IrpExpertQuestion irpExpertQuestion = irpExpertQuestionService
						.irpExpertQuestion(questionid);
				if (irpExpertQuestion == null) {
					expertStatus = 0;

				} else if (irpExpertQuestion.getExpertid().equals(
						LoginUtil.getLoginUserId())) {
					expertStatus = 1;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionUtil.writer("" + nMsg + expertStatus);
	}
	/**
	 * 发布答案
	 * @param mobile
	 * @reutrn
	 */
	public void answerAddByMobile(){
		try {
			int nMsg = irpQuestionService.addTextAnswer(jsonFile,questionid, text,contentanswer,browsecount,null,null);
			ActionUtil.writer(nMsg+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改问答
	 * 
	 * @return
	 */
	public void publishModify() {
		int nMsg = 0;
		try {
			nMsg = irpQuestionService.modifyQuestion(questionid, textareaModifytitle,textareaModifycontent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionUtil.writer("" + nMsg);
	}

	/**
	 * 修改问题状态(设置最佳答案）
	 * 
	 * @return
	 */
	public void modifyStatus() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		loginUsername = irpuser.getUsername();
		int nMsg = 0;
		try {
			//查询要置为最佳答案的知识是否存在
		    IrpQuestion irpQuestion =	this.irpQuestionService.findQuestionDetail(answerid);
		  	if(irpQuestion!=null){
		  		nMsg = irpQuestionService.modifyStatus(answerid, questionid,
						answer, browsecount, answercruserid);
		  	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionUtil.writer("" + nMsg);
	}

	private String tokens;
	
	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	/**
	 * 获取输入框字数配置
	 * 
	 * @return
	 */
	public String textAreaNum() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens=token;
		//IrpUser irpuser = mobileAction.getlogin(token);
		textareaShowNum = Integer.parseInt(SysConfigUtil
				.getSysConfigValue("TEXTAREA_SHOWNUM"));
		return "success";
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getExpertId() {
		return expertId;
	}

	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}

	public int getPageSize1() {
		return pageSize1;
	}

	public void setPageSize1(int pageSize1) {
		this.pageSize1 = pageSize1;
	}

	public int getTotalReply() {
		return totalReply;
	}

	public void setTotalReply(int totalReply) {
		this.totalReply = totalReply;
	}

	public Long getAnswerid() {
		return answerid;
	}

	public void setAnswerid(Long answerid) {
		this.answerid = answerid;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<IrpQuestion> getIrpQuestion1() {
		return irpQuestion1;
	}

	public void setIrpQuestion1(List<IrpQuestion> irpQuestion1) {
		this.irpQuestion1 = irpQuestion1;
	}

	public List<IrpQuestion> getListExpertQuestion() {
		return listExpertQuestion;
	}

	public void setListExpertQuestion(List<IrpQuestion> listExpertQuestion) {
		this.listExpertQuestion = listExpertQuestion;
	}

	public String getModifyText() {
		return modifyText;
	}

	public void setModifyText(String modifyText) {
		this.modifyText = modifyText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public IrpQuestion getIrpQuestion() {
		return irpQuestion;
	}

	public void setIrpQuestion(IrpQuestion irpQuestion) {
		this.irpQuestion = irpQuestion;
	}

	public IrpRoleService getIrpRoleService() {
		return irpRoleService;
	}

	public void setIrpRoleService(IrpRoleService irpRoleService) {
		this.irpRoleService = irpRoleService;
	}

	public List<IrpUser> getExpertList() {
		return expertList;
	}

	public void setExpertList(List<IrpUser> expertList) {
		this.expertList = expertList;
	}

	public List<IrpQuestion> getListQuestion() {
		return listQuestion;
	}

	public void setListQuestion(List<IrpQuestion> listQuestion) {
		this.listQuestion = listQuestion;
	}

	public String getQuestionInfo() {
		return questionInfo;
	}

	public void setQuestionInfo(String questionInfo) {
		this.questionInfo = questionInfo;
	}

	public IrpQuestionService getIrpQuestionService() {
		return irpQuestionService;
	}

	public void setIrpQuestionService(IrpQuestionService irpQuestionService) {
		this.irpQuestionService = irpQuestionService;
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

	public Long getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Long questionid) {
		this.questionid = questionid;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public Long getBrowsecount() {
		return browsecount;
	}

	public void setBrowsecount(Long browsecount) {
		this.browsecount = browsecount;
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

	public Long getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	public Long getPersonid() {
		return personid;
	}

	public void setPersonid(Long personid) {
		this.personid = personid;
	}

	public IrpUser getIrpUser() {
		return irpUser;
	}

	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
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
	
	public List<IrpCategory> getCategoryList(Long questionId){
		//分类状态为开启且id要在category_question下
		List<IrpCategory> categoryList = irpQuestionService.getCategoryByQuestionId(questionId);
		if(categoryList==null || categoryList.size()==0){
			return null;
		}
		return categoryList;
	}

	public List<IrpAttached> findPicList(long _nQuestionId) {
		List<IrpAttached> attachedList = null;
		attachedList = irpQuestionService.findQuestionPic(_nQuestionId);
		for (int i = 0; i < attachedList.size(); i++) {
			String filename = attachedList.get(i).getAttfile();
			int index = filename.lastIndexOf(".");
			StringBuffer zoomfile = new StringBuffer(filename.substring(0,
					index));
			zoomfile.append("_150X150"
					+ filename.substring(index, filename.length()));
			attachedList.get(i).setAttlinkalt(zoomfile.toString());
		}
		return attachedList;
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
	 * 最新问答
	 */

	public String findNewestQuestion() {

		illanswerset  = this.irpQuestionService.findNnewstAnswer(5);
		
		return SUCCESS;
	}

	/**
	 * 最热问答
	 * 
	 * @return
	 */
	public String findHotQuestion() {
		hotquestionlist = this.irpQuestionService.findNewestOrHotQuestion(
				"browsecount desc", 5, 2);

		return SUCCESS;
	}
	/**
	 * 常见问题汇总
	 * @return
	 */
	public String findConnQuesColl(){
		
		connqlist = this.irpQuestionService.getConnQList();
		
		
		return SUCCESS;
	}

	/**
	 * 问答达人
	 * 
	 * @return
	 */
	public String findQuestionIll() {
		illquestionlist = this.irpQuestionService.findIntelligentUser();

		return SUCCESS;
	}

	public IrpUser getIrpUser(Long _userid) {
		IrpUser irpUser = null;
		irpUser = irpUserService.findUserByUserId(_userid);
		return irpUser;
	}

	// 当前选中
	private String picfile;
	// 所有集合
	private String picfilenamelist;
	//
	private String picfileids;
	private String[] picfilenamearray;
	private List<IrpQuestion> irpQuestions;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public List<IrpQuestion> getIrpQuestions() {
		return irpQuestions;
	}

	public void setIrpQuestions(List<IrpQuestion> irpQuestions) {
		this.irpQuestions = irpQuestions;
	}

	private String selectvalue;
	
	public String getSelectvalue() {
		return selectvalue;
	}

	public void setSelectvalue(String selectvalue) {
		this.selectvalue = selectvalue;
	}
	public String[] getPicfilenamearray() {
		return picfilenamearray;
	}

	public void setPicfilenamearray(String[] picfilenamearray) {
		this.picfilenamearray = picfilenamearray;
	}

	public String getPicfile() {
		return picfile;
	}

	public void setPicfile(String picfile) {
		this.picfile = picfile;
	}

	public String getPicfilenamelist() {
		return picfilenamelist;
	}

	public void setPicfilenamelist(String picfilenamelist) {
		this.picfilenamelist = picfilenamelist;
	}

	public String getPicfileids() {
		return picfileids;
	}

	public void setPicfileids(String picfileids) {
		this.picfileids = picfileids;
	}

	/**
	 * 查看图片
	 * 
	 * @return
	 */
	public String lookMaxpic() {
		
		int indexs = picfile.lastIndexOf("_");
		int indexe = picfile.lastIndexOf(".");
		StringBuffer sname = new StringBuffer();
		sname.append(picfile.substring(0, indexs)).append(
				picfile.substring(indexe, picfile.length()));
		this.setPicfile(sname.toString());
		attacheds = findPicList(this.getQuestionid());
		picfilenamearray = new String[attacheds.size()];
		for (int i = 0; i < attacheds.size(); i++) {
			picfilenamearray[i] = attacheds.get(i).getAttlinkalt();
		}
		this.setPicfileids((Integer.parseInt(picfileids)-1)+"");
		return SUCCESS;
	}
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public String findquestion() {
		try {
			irpQuestion.setTextcontent(new String(irpQuestion.getTextcontent()
					.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String sOrderByClause = "";
		if (this.orderField == null || this.orderField.equals("")) {
			sOrderByClause = "crtime desc";
		} else {
			sOrderByClause = this.orderField + " " + this.orderBy;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (irpQuestion.getCruser().length() == 0) {
			irpQuestion.setCruser(null);
		}
		if (irpQuestion.getTextcontent().length() == 0) {
			irpQuestion.setTextcontent(null);
		}else{
			irpQuestion.setTextcontent("%"+irpQuestion.getTextcontent()+"%");
		}
		if(selectvalue==null){
			selectvalue=0+"";
		}
		if(this.getEndtime()==null||this.getEndtime().toString().length()==0){
			this.setEndtime(null);
		}
		map.put("selectvalue", selectvalue);
		map.put("cruser", irpQuestion.getCruser());
		map.put("time", irpQuestion.getCrtime());
		map.put("endtime", this.endtime);
		map.put("keyword", irpQuestion.getTextcontent());
		map.put("sOrderByClause", sOrderByClause);
		int result = irpQuestionService.findALlQuesAnsbywaycount(map);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSizeshow,result);
		irpQuestions = irpQuestionService.findALlQuesAnsbyway(pageUtil, map);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
    //导出
	/**
	 * 根据查询结果导出excel
	 * @return
	 */
	public String excelFindquestion() {
		try {
			irpQuestion.setTextcontent(new String(irpQuestion.getTextcontent()
					.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (irpQuestion.getCruser().length() == 0) {
			irpQuestion.setCruser(null);
		}
		if (irpQuestion.getTextcontent().length() == 0) {
			irpQuestion.setTextcontent(null);
		}else{
			irpQuestion.setTextcontent("%"+irpQuestion.getTextcontent()+"%");
		}
		//查询标识
		map.put("selectvalue", selectvalue);
		map.put("cruser", irpQuestion.getCruser());
		map.put("time", irpQuestion.getCrtime());
		map.put("keyword", irpQuestion.getTextcontent());
		irpQuestions = irpQuestionService.findALlQuesAnsbyway(null, map);
		//生成excel
		String filename=ExcelUtil.createquestionxlsUtil(irpQuestions);
		this.setMessage(filename);
		return SUCCESS;
	}
	
	public IrpQuestion getIrpQuestion(Long _questionid){
      IrpQuestion irpQuestion =  this.irpQuestionService.findQuestionDetail(_questionid);
      return irpQuestion;
	}
	private Date endtime;

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	/**
	 * 删除答案
	 * @return
	 */
	public void deleteAnswerByQuestionid(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		try {
			long questionRoleId = Long.parseLong(SysConfigUtil.getSysConfigValue("QUESTION_ROLE_ID"));
			List<Long> roleIds = irpUserroleLinkDAO.findRoleIdByUserId(irpuser.getUserid());
			for (long nRoleId : roleIds) {
				if(nRoleId==questionRoleId){
					irpuser.setQuestionManager(true);
				}
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		int msg = 0;
		//判断当前用户是否是问答管理员
		//IrpUser loginUser = LoginUtil.getLoginUser();
		if(irpuser.isQuestionManager()){
			if(questionid!=null){
				//判断删除的问题是否有最佳答案
				IrpQuestion irpQuestion = this.irpQuestionService.findQuestionDetail(questionid);
			if(irpQuestion.getStatus()!=null){
				if (irpQuestion.getStatus()==IrpQuestion.STATUS_YES) {
				    msg =  this.irpQuestionService.updateQuestionStatus(irpQuestion.getParentid(), IrpQuestion.STATUS_NO);
				}
			}
					msg = msg + this.irpQuestionService.deleteAnswerByQuestionId(questionid);
					//同时删除附件
					this.irpAttachedService.deleteFileByExpt(questionid,IrpAttached.QUESTION_DOCIDTYPE);
			}
		}
		ActionUtil.writer(msg+"");
	}
	/**
	 * 处理细览问答页面的图片显示
	 * @param _originalpic
	 * @return
	 */
	public String updatePagePic(String _originalpic){
		if(!_originalpic.equals("")){
			_originalpic = _originalpic.replace(".", "_150X150.");
		}
		return _originalpic;
	}
	/**
	 * 通过附件集合获取它查看大图的name
	 * @param _irpAttached
	 * @return
	 */
	public String getAttNameStrForList(List<IrpAttached> _irpAttached){
		String attname = "";
		if(_irpAttached!=null && _irpAttached.size()>0){
			Iterator iterator = _irpAttached.iterator();
			while (iterator.hasNext()) {
				IrpAttached irpattached = (IrpAttached) iterator.next();
				attname +=irpattached.getAttfile()+",";
			}
		}
		return attname;
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
     * 问答列表首页
     * @return
     */
    public String questionIndex() {
		return SUCCESS;
	}
    /**
     * 增加回答评论
     * @return
     */
    private Long replyqid;
    public Long getReplyqid() {
		return replyqid;
	}

	public void setReplyqid(Long replyqid) {
		this.replyqid = replyqid;
	}

	public void addAnswerComment(){
    	
    	try {
    		int msg =	this.irpQuestionService.addTextAnswerMobile(null, questionid, null, questionInfo,0l,IrpQuestion.REPLYCOMMENTSTATUS,replyqid);
			ActionUtil.writer(msg+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    /**
     * 根据id获取某条评论数量
     * @param _questionid
     * @return
     */
    public int findRCNum(long _questionid){
    	int num  = 0;
    	if(_questionid>0){
    		num = this.irpQuestionService.replyCommentNum(_questionid);
    	}
    	return num;
    }
    /**
     * 根据id获取问答评论集合
     */
    private int vnum;
	public int getVnum() {
		return vnum;
	}
	public void setVnum(int vnum) {
		this.vnum = vnum;
	}
	public String  findCRContentById(){
    	int psize = findRCNum(questionid);
    	vnum = psize/IrpQuestion.REPLYCVIEWNUM;
    	if(psize%IrpQuestion.REPLYCVIEWNUM>0){
    		vnum = vnum +1;
    	}
    	PageUtil pageUtil = new PageUtil(pageNum, IrpQuestion.REPLYCVIEWNUM,psize );
    	listQuestion = this.irpQuestionService.getReplyCList(questionid, pageUtil);
    	return SUCCESS;
    }
	public String qandaManager() throws Exception {
		return super.execute();
	}
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void exportToZip(){
		/**
		 * 查询出userid集合
		 */
		List<IrpQuestionExcel> list=new ArrayList<IrpQuestionExcel>();
		List<IrpQuestion> listAnswer=new ArrayList<IrpQuestion>();
		List<IrpQuestion> listExpertQuestion=new ArrayList<IrpQuestion>();
		List<IrpQuestion> irpQuestion1=new ArrayList<IrpQuestion>();
		ActionContext ac = ActionContext.getContext();     
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);     
        String WEB_ROOT_PATH = sc.getRealPath("/"); 
        String app_path=WEB_ROOT_PATH.replace("\\", "/");
        //System.out.println(app_path);
        String path=app_path+"user";
		List<Long> userIds = null;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("categoryId", this.id);
		//int nDataCount = irpQuestionService.findAllQuestionsInCategory(map);
		/*PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
				nDataCount);*/
		// 处理排序
		String sOrderByClause = "";
		if (this.orderField == null || this.orderField.equals("")) {
			sOrderByClause = "crtime desc";
		} else {
			sOrderByClause = this.orderField + " " + this.orderBy;
		}
		
		try {
			listQuestion = irpQuestionService.findQuestionsInCategoryByPage(null,
					sOrderByClause,map);
			for(int i=0;i<listQuestion.size();i++){
				String answerString=null;
				String experanswer=null;
				String bestanswer=null;
				IrpQuestionExcel question=new IrpQuestionExcel();
				// 除了专家答案和最佳答案
				listAnswer = irpQuestionService.findAnswersByPage(null,
								sOrderByClause, listQuestion.get(i).getQuestionid());
				// 专家答案vf
				listExpertQuestion = irpQuestionService.findExpertAnswers(listQuestion.get(i).getQuestionid());
				// 最佳答案
				irpQuestion1 = irpQuestionService.findBestAnswer(listQuestion.get(i).getQuestionid());
				for(int j=0;j<listAnswer.size();j++){
					if(answerString==null){
						answerString=listAnswer.get(j).getTextcontent();
					}else{
						answerString=answerString+"||"+listAnswer.get(j).getTextcontent();
					}
				}
				for(int k=0;k<listExpertQuestion.size();k++){
					if(experanswer==null){
						experanswer=listExpertQuestion.get(k).getHtmlcontent();
					}else{
						experanswer=experanswer+"||"+listExpertQuestion.get(k).getHtmlcontent();
					}
				}
				if(experanswer!=null){
					experanswer=experanswer.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
				}
				for(int s=0;s<irpQuestion1.size();s++){
					if(bestanswer==null){
						bestanswer=irpQuestion1.get(s).getHtmlcontent();
					}else{
						bestanswer=bestanswer+"||"+irpQuestion1.get(s).getHtmlcontent();
					}
				}
				if(bestanswer!=null){
					bestanswer=bestanswer.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
				}
				question.setQuestionid(listQuestion.get(i).getQuestionid());
				String title=listQuestion.get(i).getTitle();
				if(title==null){
					title=listQuestion.get(i).getHtmlcontent();
					title=title.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
				}
				question.setTitle(title);
				question.setTextcontent(answerString);
				question.setCruserid(listQuestion.get(i).getCruserid());
				question.setBestanswer(bestanswer);
				question.setExperanswer(experanswer);
				list.add(question);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 转换id为truename
		irpQuestionService.exportAllQuestionToZip(list,path,fileName);
	}
	public String userpic(IrpUser irpuser){
		String userpic="";
		if(irpUser.getUserpic()==null){
			if(irpUser.getSex()==null){
				userpic="/view/images/male.jpg?1"; 
			}else{
				if(irpUser.getSex()==2){
					userpic="/view/images/female.jpg?1"; 
				}else{
					userpic="/view/images/male.jpg?1"; 
				}
			}
		}else{
		userpic="/file/readfile.action?fileName="+irpUser.getUserpic();
	}
		return userpic;
	}
	public void findQuestionType(){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> list1 = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> list2 = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> list3 = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> list4 = new ArrayList<Map<Object,Object>>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
   		Long userid = irpuser.getUserid();
   		Map<Object,Object> m1 = new HashMap<Object, Object>();
   		Map<Object,Object> m2 = new HashMap<Object, Object>();
   		Map<Object,Object> m3 = new HashMap<Object, Object>();
   		Map<Object,Object> m4 = new HashMap<Object, Object>();
		illanswerset  = this.irpQuestionService.findNnewstAnswer(5);
		String[] aa=new String[illanswerset.size()];
		if(illanswerset!=null&&illanswerset.size()!=0){
			aa=illanswerset.toString().replace("[", "").replace("]", "").split(",");
		}
		if(aa==null||aa.length==0){
		
		}else{
			for(int i=0;i<aa.length;i++){
				Map<Object,Object> map1 = new HashMap<Object, Object>();
				IrpQuestion question = irpQuestionService.findQuestionDetail(Long.parseLong(aa[i].trim()));
				irpUser=irpUserService.findUserByUserId(question.getCruserid());
				 String userpic="";
				 if(irpUser!=null){
					 userpic=this.userpic(irpUser);
				 }else{
					 userpic="/view/images/male.jpg?1"; 
				 }
				if(question.getTitle()!=null){
					map1.put("questitle", question.getTitle());
				}else{
					map1.put("questitle", question.getHtmlcontent());
				}
				map1.put("quesid", question.getQuestionid());
				map1.put("queshead", userpic);
				map1.put("quesname", question.getCruser());
				map1.put("quesscore", "");
				list1.add(map1);
			}
		}
		hotquestionlist = this.irpQuestionService.findNewestOrHotQuestion(
				"browsecount desc", 5, 2);
		if(hotquestionlist==null){
		
		}else{
			for(int i=0;i<hotquestionlist.size();i++){
				Map<Object,Object> map2 = new HashMap<Object, Object>();
				irpUser=irpUserService.findUserByUserId(hotquestionlist.get(i).getCruserid());
				 String userpic="";
				 if(irpUser!=null){
					 userpic=this.userpic(irpUser);
				 }else{
					 userpic="/view/images/male.jpg?1"; 
				 }
				 if(hotquestionlist.get(i).getTitle()!=null){
					 map2.put("questitle", hotquestionlist.get(i).getTitle());
				 }else{
					 map2.put("questitle", hotquestionlist.get(i).getHtmlcontent());
				 }
				map2.put("quesid", hotquestionlist.get(i).getQuestionid());
				map2.put("queshead", userpic);
				map2.put("quesname", hotquestionlist.get(i).getCruser());
				map2.put("quesscore", "");
				list2.add(map2);
			}
		}
		connqlist = this.irpQuestionService.getConnQList();
		if(connqlist==null){
		
		}else{
			for(int i=0;i<connqlist.size();i++){
				Map<Object,Object> map3 = new HashMap<Object, Object>();
				irpUser=irpUserService.findUserByUserId(connqlist.get(i).getCruserid());
				String userpic="";
				if(irpUser!=null){
					 userpic=this.userpic(irpUser);
				 }else{
					 userpic="/view/images/male.jpg?1"; 
				 }
				if(connqlist.get(i).getTitle()!=null){
					map3.put("questitle", connqlist.get(i).getTitle());
				}else{
					map3.put("questitle", connqlist.get(i).getHtmlcontent());
				}
				map3.put("quesid", connqlist.get(i).getQuestionid());
				map3.put("queshead", userpic);
				map3.put("quesname", connqlist.get(i).getCruser());
				map3.put("quesscore", "");
				list3.add(map3);
			}
		}
		illquestionlist = this.irpQuestionService.findIntelligentUser();
		if(illquestionlist==null){
		
		}else{
			for(int i=0;i<illquestionlist.size();i++){
				Map<Object,Object>  map=(Map) illquestionlist.get(i);
				Iterator<Object> iter = map.keySet().iterator();
				Map<Object,Object> map4 = new HashMap<Object, Object>();
				while(iter.hasNext()) {
					 Object key = iter.next();
			         //有了键，就可以通过map集合的get方法获取对应的值
					 if(key.equals("CRUSERID")){
						 Object value1 =map.get(key);
						 irpUser=irpUserService.findUserByUserId(Long.parseLong(value1.toString()));
						 String userpic="";
						 if(irpUser!=null){
							 userpic=this.userpic(irpUser);
							 map4.put("quesname", irpUser.getUsername());
							 map4.put("queshead", userpic);
						 }else{
							 userpic="/view/images/male.jpg?1"; 
							 map4.put("quesname", "");
							 map4.put("queshead", userpic);
						 }
					 }
					 if(key.equals("USERCOUNT")){
						 Object value2 =map.get(key);
						 map4.put("quesscore", value2);
					 }
				}
				map4.put("questitle", "");
				map4.put("quesid", "");
				
				list4.add(map4);
			}
		}
		m1.put("questype", "最新问答");
		m1.put("quesdic", list1);
		m2.put("questype", "最热问答");
		m2.put("quesdic", list2);
		m3.put("questype", "常见问题汇总");
		m3.put("quesdic", list3);
		m4.put("questype", "问答达人");
		m4.put("quesdic", list4);
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		ActionUtil.writer(JSON.toJSONString(list));
	}
	private IrpCategoryService irpCategoryService;
	
	public IrpCategoryService getIrpCategoryService() {
		return irpCategoryService;
	}

	public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
		this.irpCategoryService = irpCategoryService;
	}
	private int filterid;
	
	public int getFilterid() {
		return filterid;
	}

	public void setFilterid(int filterid) {
		this.filterid = filterid;
	}

	//判断是否有子分类
	public List<IrpCategory> childcategory(long categoryid){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("parentid", categoryid);
		map.put("status", IrpCategory.STATUS_START);
		List<IrpCategory> categories = irpCategoryService.findCategoryByConditions(map);
		return categories;
	}
	
	private List<Map<String, Object>> findAllTreeNode(List<Map<String, Object>> treeNode, List<IrpCategory> categories){
		for(IrpCategory irpCategory : categories){
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser user = mobileAction.getlogin(token);
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("categoryid", irpCategory.getCategoryid());
			map.put("title", irpCategory.getCname());
			List<IrpCategory> listcategory = childcategory(irpCategory.getCategoryid());
			if(listcategory!=null&&listcategory.size()>0){
				map.put("mutistatus", "yes");
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				map.put("questiondic", findAllTreeNode(list, listcategory));
			}else{
				Map<String,Object> qmap = new HashMap<String, Object>();
				qmap.put("categoryId", irpCategory.getCategoryid());
				// 处理排序
				String sOrderByClause = "";
				if (this.orderField == null || this.orderField.equals("")) {
					sOrderByClause = "crtime desc";
				} else {
					sOrderByClause = this.orderField + " " + this.orderBy;
				}
				try {
					if(filterid==1){
						qmap.put("needDeal", "needDeal");
					}else if(filterid==2){
						qmap.put("needDeal", "complete");
					}else if(filterid==3){
						qmap.put("myQuestion", "true");
					}else if(filterid==4){
						qmap.put("myAnswer", "true");
					}else{
					}
					listQuestion = irpQuestionService.findQuestionsInCategoryByPage1(null,
							sOrderByClause,qmap);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<Map<String, Object>> question = new ArrayList<Map<String,Object>>();
				if(listQuestion==null){
					
				}else{
					for(int i=0;i<listQuestion.size();i++){
						irpUser=irpUserService.findUserByUserId(listQuestion.get(i).getCruserid());
						String userpic="";
						if(irpUser!=null){
							 userpic=this.userpic(irpUser);
						 }else{
							 userpic="/view/images/male.jpg?1"; 
						 }
						Map<String, Object> map1 = new LinkedHashMap<String, Object>();
						map1.put("queshead", userpic);
						if(listQuestion.get(i).getTitle()!=null){
							map1.put("questitle", listQuestion.get(i).getTitle());
						}else{
							map1.put("questitle", "");
						}
						map1.put("quesstatus", listQuestion.get(i).getStatus());
						map1.put("quesname", listQuestion.get(i).getCruser());
						map1.put("questime", listQuestion.get(i).getCrtime());
						map1.put("quesanwsercount", findReplyCountByQuestionId(listQuestion.get(i).getQuestionid()));
						map1.put("quesscancount", listQuestion.get(i).getBrowsecount());
						map1.put("quesprojectid", listQuestion.get(i).getQuestionid());
						map1.put("questype", "");
						question.add(map1);
					}
				}
				map.put("mutistatus", "no");
				map.put("questiondic", question);
			};
			treeNode.add(map);
		}
		return treeNode;
	}
	private IrpUserroleLinkDAO irpUserroleLinkDAO;

	public IrpUserroleLinkDAO getIrpUserroleLinkDAO() {
		return irpUserroleLinkDAO;
	}

	public void setIrpUserroleLinkDAO(IrpUserroleLinkDAO irpUserroleLinkDAO) {
		this.irpUserroleLinkDAO = irpUserroleLinkDAO;
	}
	/**
	 * 查询分类及分类下的问答
	 * @param _nParentId
	 * @return
	 */
	public void findCategoryAndQuestion(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser user = mobileAction.getlogin(token);
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		Map<String,Object> conditions = new HashMap<String, Object>();
		String categoryQuestionId = SysConfigUtil.getSysConfigValue("CATEGORY_QUESTION");
		if(StringUtils.isNotBlank(categoryQuestionId)){
			conditions.put("parentid", Long.valueOf(categoryQuestionId));
			conditions.put("status", IrpCategory.STATUS_START);
			List<IrpCategory> categories = irpCategoryService.findCategoryByConditions(conditions);
			treeNode = this.findAllTreeNode(treeNode, categories);
		}else{
			return ;
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("categoryid", categoryQuestionId);
		map.put("title", "待解答");
		map.put("mutistatus", "no");
		try {
			listQuestion = irpExpertQuestionService.questionsOfExpertResolute(
					user.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> question = new ArrayList<Map<String,Object>>();
		if(listQuestion==null){
			
		}else{
			for(int i=0;i<listQuestion.size();i++){
				irpUser=irpUserService.findUserByUserId(listQuestion.get(i).getCruserid());
				String userpic="";
				if(irpUser!=null){
					 userpic=this.userpic(irpUser);
				 }else{
					 userpic="/view/images/male.jpg?1"; 
				 }
				Map<String, Object> map1 = new LinkedHashMap<String, Object>();
				map1.put("queshead", userpic);
				if(listQuestion.get(i).getTitle()==null){
					map1.put("questitle", "");
				}else{
					map1.put("questitle", listQuestion.get(i).getTitle());
				}
				if(listQuestion.get(i).getTitle()==null){
					map1.put("quesstatus", listQuestion.get(i).getHtmlcontent());
				}else{
					map1.put("quesstatus", listQuestion.get(i).getStatus());
				}
				//map1.put("quesstatus", listQuestion.get(i).getStatus());
				map1.put("quesname", listQuestion.get(i).getCruser());
				map1.put("questime", listQuestion.get(i).getCrtime());
				map1.put("quesanwsercount", findReplyCountByQuestionId(listQuestion.get(i).getQuestionid()));
				map1.put("quesscancount", listQuestion.get(i).getBrowsecount());
				map1.put("quesprojectid", listQuestion.get(i).getQuestionid());
				map1.put("questype", "");
				question.add(map1);
			}
		}
		map.put("questiondic", question);
		Map<String, Object> wmap = new LinkedHashMap<String, Object>();
		wmap.put("categoryid", 1);
		wmap.put("title", "所有问答");
		wmap.put("mutistatus", "no");
		try {
			Map<String,Object> map2 = new HashMap<String, Object>();
			map2.put("categoryId", 0);
			if(filterid==1){
				map2.put("needDeal", "needDeal");
			}else if(filterid==2){
				map2.put("needDeal", "complete");
			}else if(filterid==3){
				map2.put("myQuestion", "true");
			}else if(filterid==4){
				map2.put("myAnswer", "true");
			}else{
			}
			// 处理排序
			String sOrderByClause = "";
			if (this.orderField == null || this.orderField.equals("")) {
				sOrderByClause = "crtime desc";
			} else {
				sOrderByClause = this.orderField + " " + this.orderBy;
			}
			listQuestion = irpQuestionService.findQuestionsInCategoryByPage(null,
					sOrderByClause,map2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> question1 = new ArrayList<Map<String,Object>>();
		if(listQuestion==null){
			
		}else{
			for(int i=0;i<listQuestion.size();i++){
				irpUser=irpUserService.findUserByUserId(listQuestion.get(i).getCruserid());
				String userpic="";
				if(irpUser!=null){
					 userpic=this.userpic(irpUser);
				 }else{
					 userpic="/view/images/male.jpg?1"; 
				 }
				Map<String, Object> map3 = new LinkedHashMap<String, Object>();
				map3.put("queshead", userpic);
				if(listQuestion.get(i).getTitle()!=null){
					map3.put("questitle", listQuestion.get(i).getTitle());
				}else{
					map3.put("questitle", listQuestion.get(i).getHtmlcontent());
				}
				map3.put("quesstatus", listQuestion.get(i).getStatus());
				map3.put("quesname", listQuestion.get(i).getCruser());
				map3.put("questime", listQuestion.get(i).getCrtime());
				map3.put("quesanwsercount", findReplyCountByQuestionId(listQuestion.get(i).getQuestionid()));
				map3.put("quesscancount", listQuestion.get(i).getBrowsecount());
				map3.put("quesprojectid", listQuestion.get(i).getQuestionid());
				map3.put("questype", "");
				question1.add(map3);
			}
		}
		wmap.put("questiondic", question1);
		try {
			long expertRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID"));
			List<Long> roleIds = irpUserroleLinkDAO.findRoleIdByUserId(user.getUserid());
			for (long nRoleId : roleIds) {
				if(nRoleId==expertRoleId){
					user.setExpert(true);
				}
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		if(user.isExpert()){
			treeNode.add(map);
		}
		treeNode.add(wmap);
		ActionUtil.writer(JSON.toJSONString(treeNode));
	}
	
    /**
     * 
     * addQuestionForApp:手机端问答提问接口
     * @author Administrator  
     * @since JDK 1.8
     */
    public void addQuestionForApp() {

        // 获取token
        HttpServletRequest request = ServletActionContext.getRequest();
        String token = request.getParameter("token");
        IrpUser user = mobileAction.getlogin(token);

        if (user != null) {

            // 初始化两个变量来标识数据库更新数量
            int num = 0;
            Long numLong = 0L;

            try {
                // 根据专家id获取用户
                IrpUser expertUser = irpUserService.findUserByUserId(expertId);

                // 如果用户存在则提问时添加了询问专家
                if (expertUser != null) {
                    num = this.irpQuestionService.addQuestionAndExpertForApp(questionInfo, expertUser.getShowName(),
                            expertUser.getUserid(), questiontitle, jsonFile, categories, user);

                    // 如果问题添加成功则更新用户
                    if (num > 0) {
                        expertUser.setAnswercount(expertUser.getAnswercount() + 1);
                        irpUserService.userEdit(expertUser);
                    }
                } else {
                    numLong = this.irpQuestionService.addQuestionForApp(questionInfo, jsonFile, questiontitle, categories, user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 更新标识大于0则证明插入数据成功
            if (num > 0 || numLong > 0L) {
                ActionUtil.writer("{\"resultstatus\" : \"success\"}");
            } else {
                ActionUtil.writer("{\"resultstatus\" : \"failure\"}");
            }
        } else {
            ActionUtil.writer("{\"resultstatus\" : \"User failure\"}");
        }
    }
}
