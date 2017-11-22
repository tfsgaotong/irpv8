package com.tfs.irp.question.web;


import java.io.UnsupportedEncodingException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.category_file_link.service.IrpCategoryFileLinkService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.expert.entity.IrpExpertQuestion;
import com.tfs.irp.expert.service.IrpExpertQuestionService;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.motetread.entity.IrpMostTread;
import com.tfs.irp.motetread.service.IrpMoteTreadService;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.entity.IrpQuestionExample;
import com.tfs.irp.question.entity.IrpQuestionExample.Criteria;
import com.tfs.irp.question.entity.IrpQuestionExcel;
import com.tfs.irp.question.service.IrpQuestionService;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.ExcelUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.ThumbnailPic;

@SuppressWarnings("serial")
public class IrpQuestionAction extends ActionSupport {

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
	private Long isPage;
	
	public Long getIsPage() {
		return isPage;
	}

	public void setIsPage(Long isPage) {
		this.isPage = isPage;
	}

	private int id;
	
	private List<IrpQuestion> connqlist;
	private IrpCategoryService irpCategoryService;
	private List<IrpCategory> listCategory;
	private List<IrpCategory> list;
	private List<IrpChnlDocLink> chnlDocLinks51;
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
	private Long categoryId;
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public List<IrpCategory> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<IrpCategory> listCategory) {
		this.listCategory = listCategory;
	}

	public List<IrpChnlDocLink> getChnlDocLinks51() {
		return chnlDocLinks51;
	}

	public void setChnlDocLinks51(List<IrpChnlDocLink> chnlDocLinks51) {
		this.chnlDocLinks51 = chnlDocLinks51;
	}

	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}

	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}

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
		try {			
			IrpUser expertUser = irpUserService.findUserByUserId(expertId);
			if (expertUser!=null) {
				nMsg = this.irpQuestionService.addQuestionAndExpert(questionInfo, expertUser.getShowName(), expertUser.getUserid(),questiontitle,jsonFile,categories);
				IrpUser user=irpUserService.findUserByUserId(expertId);
				user.setAnswercount(user.getAnswercount()+1);
				irpUserService.userEdit(user);
			} else {
				long questionId = this.irpQuestionService.addQuestion(
						questionInfo, jsonFile,questiontitle,categories);
				if (questionId > 0L) {
					nMsg = 1;
				} else {
					nMsg = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionUtil.writer("" + nMsg);
	}
    
	/**
	 * 显示所有问题的列表
	 * 
	 * @return
	 */
	public String listQuestion() {
		//Long nUserid = LoginUtil.getLoginUserId();
		try {
			listQuestion = irpQuestionService.listAllQuestion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 分页显示所有问题列表
	 * 
	 * @return
	 */
	public String listQuestionByPage() {
		questionShowNum = Integer.parseInt(SysConfigUtil
				.getSysConfigValue("QUESTION_SHOWNUM"));
		loginUsername = LoginUtil.getLoginUser().getUsername();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("categoryId", this.id);
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
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 获取全部问题列表
	 * @param mobile
	 * @return 返回问题列表
	 */
	private int mobilequespagenum;
	public int getMobilequespagenum() {
		return mobilequespagenum;
	}
	public void setMobilequespagenum(int mobilequespagenum) {
		this.mobilequespagenum = mobilequespagenum;
	}
	public void questionMobileList(){
		try {
			int mobileallquestionnum = irpQuestionService.findAllQuestions();
			PageUtil pageUtil = new PageUtil(mobilequespagenum,10,mobileallquestionnum);
			List<IrpQuestion> list = irpQuestionService.findQuestionsByPage(pageUtil,"crtime desc");
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
			mobilelist.add("{\"mobilenum\":\""+mobileallquestionnum+"\"}");
			ActionUtil.writer(mobilelist.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * 待解决
	 * 
	 * @return
	 */
	public String questionNoResoule() {
		questionShowNum = Integer.parseInt(SysConfigUtil
				.getSysConfigValue("QUESTION_SHOWNUM"));
		loginUsername = LoginUtil.getLoginUser().getUsername();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("needDeal", "needDeal");
			map.put("categoryId", this.id);
			int nDataCount = irpQuestionService.findAllQuestionsInCategory(map);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
					nDataCount);
			listQuestion = irpQuestionService
					.findQuestionsInCategoryByPage(pageUtil,null,map);
			if(listQuestion!=null && listQuestion.size()>0){
				for (int i = 0; i < listQuestion.size(); i++) {
					irpUser = irpUserService.findUserByUserId(listQuestion.get(i)
							.getCruserid());
				}
			}
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 未解决的问题
	 * @param mobile
	 * @return
	 */
	private int mobilenoquespagenum;
	public int getMobilenoquespagenum() {
		return mobilenoquespagenum;
	}
	public void setMobilenoquespagenum(int mobilenoquespagenum) {
		this.mobilenoquespagenum = mobilenoquespagenum;
	}
	public void mobileQuestionListNo(){
		try {
			int nDataCount = irpQuestionService.findQuestionsCount();
			PageUtil pageUtil = new PageUtil(mobilenoquespagenum,10,nDataCount);
			List<IrpQuestion> list = irpQuestionService.findQuestionsOfNoResoule(pageUtil);
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
			e.printStackTrace();
		}	
	}
	
	

	/**
	 * 已解决问题列表
	 * 
	 * @return
	 */
	public String questionResoule() {
		questionShowNum = Integer.parseInt(SysConfigUtil
				.getSysConfigValue("QUESTION_SHOWNUM"));
		loginUsername = LoginUtil.getLoginUser().getUsername();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("needDeal", "complete");
			map.put("categoryId", this.id);
			int nDataCount = irpQuestionService.findAllQuestionsInCategory(map);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
					nDataCount);
			listQuestion = irpQuestionService.findQuestionsInCategoryByPage(pageUtil,null,map);
			if(listQuestion!=null && listQuestion.size()>0){
				for (int i = 0; i < listQuestion.size(); i++) {
					irpUser = irpUserService.findUserByUserId(listQuestion.get(i)
							.getCruserid());
				}
			}
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查看某个问题的细览
	 * @param mobile
	 * @return
	 */

	public void detailMobileQuestion(){
		
		IrpQuestion irpQuestion = irpQuestionService.findQuestionDetail(questionid);
		List zhuanlist = new ArrayList();
		List disposelist = new ArrayList();
		List omlist = new ArrayList();
		List otherqulist = new ArrayList();
		//增加浏览人次
		hits();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		disposelist.add("{\"userpic\":\""+getIrpUserByUserid(irpQuestion.getCruserid()).getUserpic()+"\"");
		disposelist.add("\"sex\":\""+getIrpUserByUserid(irpQuestion.getCruserid()).getSex()+"\"");
		String titleval = irpQuestion.getTitle();
		if (titleval!=null) {
			disposelist.add("\"title\":\""+titleval.replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");	
		}
		String htmlcontentval = irpQuestion.getHtmlcontent();
		if (htmlcontentval!=null) {
			disposelist.add("\"htmlcontent\":\""+htmlcontentval.replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");	
		}
		disposelist.add("\"showname\":\""+getShowPageViewNameByUserId(irpQuestion.getCruserid())+"\"");
		disposelist.add("\"status\":\""+irpQuestion.getStatus()+"\"");
		disposelist.add("\"crtime\":\""+sdf.format(irpQuestion.getCrtime())+"\"");
		disposelist.add("\"browsernum\":\""+irpQuestion.getBrowsecount()+"\"");
		disposelist.add("\"answernum\":\""+findReplyCountByQuestionId(irpQuestion.getQuestionid())+"\"}");
		//专家答案（有没有）
		try {
			List<IrpQuestion> expertlist = irpQuestionService.findExpertAnswers(questionid);
			if (expertlist.size()>0) {
				
				
				for (int i = 0; i < expertlist.size(); i++) {
					IrpQuestion expertquestion = expertlist.get(i);
					zhuanlist.add("{\"zhtmlcontent\":\""+expertquestion.getHtmlcontent().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
					zhuanlist.add("\"zcrtime\":\""+sdf.format(expertquestion.getCrtime())+"\"");
					zhuanlist.add("\"zshowname\":\""+getShowPageViewNameByUserId(expertquestion.getCruserid())+"\"");
					zhuanlist.add("\"zuserid\":\""+expertquestion.getCruserid()+"\"}");
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//最佳答案
		 try {
			 List<IrpQuestion> optimumlist =	irpQuestionService.findBestAnswer(questionid);
			 for (int i = 0; i < optimumlist.size(); i++) {
					IrpQuestion optimumquestion = optimumlist.get(i);
					omlist.add("{\"ohtmlcontent\":\""+optimumquestion.getHtmlcontent().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
					omlist.add("\"ocrtime\":\""+sdf.format(optimumquestion.getCrtime())+"\"");
					omlist.add("\"oshowname\":\""+getShowPageViewNameByUserId(optimumquestion.getCruserid())+"\"");
					omlist.add("\"ouserid\":\""+optimumquestion.getCruserid()+"\"}");
				
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//其它答案
		try {
			int othernum = irpQuestionService.countAnswers(questionid);
			PageUtil otherpageUtil = new PageUtil(1, 10, othernum);
			List<IrpQuestion> otherlist = irpQuestionService.findAnswersByPage(otherpageUtil,"crtime desc", questionid);
			 for (int i = 0; i < otherlist.size(); i++) {
					IrpQuestion otherquestion = otherlist.get(i);
					otherqulist.add("{\"ohtmlcontent\":\""+otherquestion.getHtmlcontent().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
					otherqulist.add("\"ocrtime\":\""+sdf.format(otherquestion.getCrtime())+"\"");
					otherqulist.add("\"oshowname\":\""+getShowPageViewNameByUserId(otherquestion.getCruserid())+"\"");
					otherqulist.add("\"pgtotalnum\":\""+othernum+"\"");
					otherqulist.add("\"ouserid\":\""+otherquestion.getCruserid()+"\"}");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = "{\"originalques\":"+disposelist+",\"zhuanjiaques\":"+zhuanlist+",\"omques\":"+omlist+",\"otherqulist\":"+otherqulist+"}";
		ActionUtil.writer(str);
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
	/**
	 * 根据id获取某个问题的详细信息
	 * @param mobile
	 * @return
	 */
	public void detailObjQuestion(){
		
		IrpQuestion irpQuestion = this.irpQuestionService.findQuestionDetail(questionid);
		List list = new ArrayList();
		list.add("{\"title\":\""+irpQuestion.getTitle().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
		list.add("\"htmlcontent\":\""+irpQuestion.getHtmlcontent()+"\"}");
		String str = "{\"updatjson\":"+list+"}";
		ActionUtil.writer(str);
	}
	/**
	 * 问题明细
	 * 
	 * @return
	 */
	public String questionDetail() {
		try {
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
			if(listQuestion.size()>0){
				this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
			}
			// 专家答案vf
			listExpertQuestion = irpQuestionService
					.findExpertAnswers(questionid);
			// 获取当前的问题
			irpQuestion = irpQuestionService.findQuestionDetail(questionid);
			irpUser = irpUserService
					.findUserByUserId(irpQuestion.getCruserid());
			// 浏览总数
			browsecount = irpQuestion.getBrowsecount() == null ? 1L
					: irpQuestion.getBrowsecount();
			irpQuestionService.modifyBrowCount(questionid, browsecount);
			// 当前登陆用户
			loginUsername = LoginUtil.getLoginUser().getUsername();
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
	 * 我提问的
	 * 
	 * @return
	 */
	public String findQuestionOfMine() {
		questionShowNum = Integer.parseInt(SysConfigUtil
				.getSysConfigValue("QUESTION_SHOWNUM"));
		loginUsername = LoginUtil.getLoginUser().getUsername();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("myQuestion", "true");
			map.put("categoryId", this.id);
			int nDataCount = irpQuestionService.findQuestionMineCount();
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
					nDataCount);
			listQuestion = irpQuestionService.findQuestionsInCategoryByPage(pageUtil,null,map);
			if(listQuestion!=null && listQuestion.size()>0){
				if (listQuestion.size() > 0) {
					for (int i = 0; i < listQuestion.size(); i++) {
						irpUser = irpUserService.findUserByUserId(listQuestion.get(
								i).getCruserid());
					}
				}
			}
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	private Long userid;
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * 查找专家问答
	 * @return
	 * @author   npz
	 * @date 2017年10月27日
	 */
	public String findQuestionOfExpert() {
		questionShowNum = Integer.parseInt(SysConfigUtil
				.getSysConfigValue("QUESTION_SHOWNUM"));
		loginUsername = LoginUtil.getLoginUser().getUsername();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("myAnswer", "true");
			map.put("categoryId", this.id);
			int nDataCount = irpQuestionService.findQuestioncountById(map, userid);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
					nDataCount);
			listQuestion = irpQuestionService.findQuestionById(userid, null, map, pageUtil);
			if (listQuestion != null) {
				for (int i = 0; i < listQuestion.size(); i++) {
					irpUser = irpUserService.findUserByUserId(listQuestion.get(
							i).getCruserid());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	/**
	 * 我提问的
	 * mobile
	 * @return
	 */
	private int mobilemyqaskpagenum;
	public int getMobilemyqaskpagenum() {
		return mobilemyqaskpagenum;
	}
	public void setMobilemyqaskpagenum(int mobilemyqaskpagenum) {
		this.mobilemyqaskpagenum = mobilemyqaskpagenum;
	}
	public void findMineAskQuOfMobile(){
		try {
			int nDataCount = irpQuestionService.findQuestionMineCount();
			PageUtil pageUtil = new PageUtil(mobilemyqaskpagenum,10,nDataCount);
			List<IrpQuestion> list = irpQuestionService.findQuestionMine(pageUtil);
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
	 * 已解决问题
	 * @param mobile
	 * @return 
	 */
	private int mobileyesquespagenum;
	public int getMobileyesquespagenum() {
		return mobileyesquespagenum;
	}
	public void setMobileyesquespagenum(int mobileyesquespagenum) {
		this.mobileyesquespagenum = mobileyesquespagenum;
	}
	public void mobileQuestionYesList(){
		try {
			int nDataCount = irpQuestionService.findQuestionsResoule();
			PageUtil pageUtil = new PageUtil(mobileyesquespagenum,10,nDataCount);
			List<IrpQuestion> list  = irpQuestionService.findQuestionOfResoule(pageUtil);
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
			e.printStackTrace();
		}
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
		IrpUser loginUser = LoginUtil.getLoginUser();
		loginUsername = loginUser.getUsername();
		loginUserId = loginUser.getUserid();
		int nMsg = 0;
		int expertStatus = 0;
		try {
			nMsg = irpQuestionService.addTextAnswer(jsonFile,questionid, text,contentanswer,
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
		loginUsername = LoginUtil.getLoginUser().getUsername();
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

	/**
	 * 我提问的
	 * 
	 * @return
	 */
	public String meAskQuestion() {
		try {
			int nDataCount = irpQuestionService.countMeAskQuestion(personid);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
					nDataCount);
			// 处理排序
			String sOrderByClause = "";
			if (this.orderField == null || this.orderField.equals("")) {
				sOrderByClause = "crtime desc";
			} else {
				sOrderByClause = this.orderField + " " + this.orderBy;
			}
			irpUser = irpUserService.findUserByUserId(personid);
			listQuestion = irpQuestionService.findMeAskQuestion(pageUtil,
					personid, sOrderByClause);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 我回答的
	 * 
	 * @return
	 */
	public String meAnswerQuestion() {
		int nDataCount;
		try {
			nDataCount = irpQuestionService.countMeAnswerQuestion(personid);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
					nDataCount);
			irpUser = irpUserService.findUserByUserId(personid);
			listQuestion = irpQuestionService.findMeAnswerQuestion(pageUtil,
					personid, orderBy);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 获取输入框字数配置
	 * 
	 * @return
	 */
	public String textAreaNum() {
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
	 * 最新问答
	 * 
	 * @return
	 */
	public String findNewQuestion() {
		listQuestion = this.irpQuestionService.findNewestOrHotQuestion(
				"crtime desc", 5, 2);

		return SUCCESS;
	}/**
	 * 最热问答
	 * 
	 * @return
	 */
	public String findHotQuestion() {
		listQuestion = this.irpQuestionService.findNewestOrHotQuestion(
				"browsecount desc", 5, 2);

		return SUCCESS;
	}

	/**
	 * 常见问题汇总
	 * @return
	 */
	public String findConnQuesColl(){
		questionShowNum = Integer.parseInt(SysConfigUtil.getSysConfigValue("QUESTION_SHOWNUM"));
		loginUsername = LoginUtil.getLoginUser().getUsername();
		int count = this.irpQuestionService.getConnQListCount();
		PageUtil pageutil = new PageUtil(this.pageNum, this.pageSize, count);
		listQuestion = this.irpQuestionService.getConnQList(pageutil);
		if(listQuestion!=null && listQuestion.size()>0){
			for (int i = 0; i < listQuestion.size(); i++) {
				irpUser = irpUserService.findUserByUserId(listQuestion.get(i)
						.getCruserid());
			}
		}
		this.pageHTML = pageutil.getClientPageHtml("page(#PageNum#)");
		
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
	private List<IrpChnlDocLink> chnlDocLinks;
	

	public List<IrpChnlDocLink> getChnlDocLinks() {
		return chnlDocLinks;
	}

	public void setChnlDocLinks(List<IrpChnlDocLink> chnlDocLinks) {
		this.chnlDocLinks = chnlDocLinks;
	}

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
		int msg = 0;
		//判断当前用户是否是问答管理员
		IrpUser loginUser = LoginUtil.getLoginUser();
		if(loginUser.isQuestionManager()){
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
     * 问答列表首页旧版
     * @return
     */
    public String questionIndexold() {

		return SUCCESS;
	}
    /**
     * 问答列表首页
     * @return
     */
    public String questionIndex() {
    	String s="0";
		String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.CATEGORY_QUESTION);
		PageUtil pageUtil = new PageUtil(1, 4, 0);
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("parentid", categoryQuestionId);
		listCategory=irpCategoryService.findCategoryByConditions(map);
		String sOrderByClause = "docpubtime desc";
		List<Long> _channelIds4 = new ArrayList<Long>();
		s=SysConfigUtil.getSysConfigValue("HEAD_QUESTION");
		_channelIds4.add(Long.parseLong(s));//首页元数据50
		chnlDocLinks51=irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM, 
				IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds4, sOrderByClause, pageUtil);
		return SUCCESS;
	}
    
    public String toQueCategory(){
		String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.CATEGORY_QUESTION);
		HashMap<String,Object> map=new HashMap<String, Object>();  
		IrpCategory cate=irpCategoryService.findCategoryByPrimaryKey(categoryId);
		map.put("parentid", cate.getCparentid());
		list=irpCategoryService.findCategoryByConditions(map);
		listCategory=irpCategoryService.currentCategory(categoryId,listCategory,Long.parseLong(categoryQuestionId));
		return SUCCESS;
	}
    public String toQueCategory1(){
  		String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.CATEGORY_QUESTION);
  		HashMap<String,Object> map=new HashMap<String, Object>();  
  		IrpCategory cate=irpCategoryService.findCategoryByPrimaryKey(categoryId);
  		map.put("parentid", cate.getCparentid());
  		list=irpCategoryService.findCategoryByConditions(map);
  		listCategory=irpCategoryService.currentCategory(categoryId,listCategory,Long.parseLong(categoryQuestionId));
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
    		int msg =	this.irpQuestionService.addTextAnswer(null, questionid, null, questionInfo,0l,IrpQuestion.REPLYCOMMENTSTATUS,replyqid);
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
	private String expertName;
	
	
	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

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
	public List<IrpCategory> findChildCategoryByParentId(long _nParentId){
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("parentid", _nParentId);
		List<IrpCategory> childCategory = irpCategoryService.findCategoryByConditions(map);
		return childCategory;
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
	
	
	/**
	 * 链接到问答编辑界面
	 * @return
	 */
	public String linkeditsubmit(){
		
		expertId=expertId;
		if(expertId!=null){
			expertName=LoginUtil.getUserNameString(LoginUtil.findUserById(expertId));
		}
		return SUCCESS;
	}
	
	
	
}
