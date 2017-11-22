package com.tfs.irp.question.entity;

import java.util.Date;

import com.tfs.irp.base.IrpBaseObj;

public class IrpQuestionExcel extends IrpBaseObj{
    

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.QUESTIONID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long questionid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.TITLE
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private String title;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.HTMLCONTENT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private String htmlcontent;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.TEXTCONTENT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private String textcontent;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.STATUS
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Integer status;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.ROOTID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long rootid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.PARENTID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long parentid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.BESTANSWERID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long bestanswerid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.ISBESTANSWER
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Short isbestanswer;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.EXPERTANSWERID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long expertanswerid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.ISEXPERTANSWER
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Short isexpertanswer;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.REPLYCOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long replycount;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.BROWSECOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long browsecount;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.GOODEVALUATECOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long goodevaluatecount;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.BADEVALUATECOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long badevaluatecount;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.DOCID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long docid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.CRUSERID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long cruserid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.CRUSER
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private String cruser;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.CRTIME
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Date crtime;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column IRP_QUESTION.REPLYQUESTIONID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private Long replyquestionid;
	private String bestanswer;
	private String experanswer;
	
	public String getBestanswer() {
		return bestanswer;
	}

	public void setBestanswer(String bestanswer) {
		this.bestanswer = bestanswer;
	}

	public String getExperanswer() {
		return experanswer;
	}

	public void setExperanswer(String experanswer) {
		this.experanswer = experanswer;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.QUESTIONID
	 * @return  the value of IRP_QUESTION.QUESTIONID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getQuestionid() {
		return questionid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.QUESTIONID
	 * @param questionid  the value for IRP_QUESTION.QUESTIONID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setQuestionid(Long questionid) {
		this.questionid = questionid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.TITLE
	 * @return  the value of IRP_QUESTION.TITLE
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.TITLE
	 * @param title  the value for IRP_QUESTION.TITLE
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.HTMLCONTENT
	 * @return  the value of IRP_QUESTION.HTMLCONTENT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public String getHtmlcontent() {
		return htmlcontent;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.HTMLCONTENT
	 * @param htmlcontent  the value for IRP_QUESTION.HTMLCONTENT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setHtmlcontent(String htmlcontent) {
		this.htmlcontent = htmlcontent;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.TEXTCONTENT
	 * @return  the value of IRP_QUESTION.TEXTCONTENT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public String getTextcontent() {
		return textcontent;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.TEXTCONTENT
	 * @param textcontent  the value for IRP_QUESTION.TEXTCONTENT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setTextcontent(String textcontent) {
		this.textcontent = textcontent;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.STATUS
	 * @return  the value of IRP_QUESTION.STATUS
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.STATUS
	 * @param status  the value for IRP_QUESTION.STATUS
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.ROOTID
	 * @return  the value of IRP_QUESTION.ROOTID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getRootid() {
		return rootid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.ROOTID
	 * @param rootid  the value for IRP_QUESTION.ROOTID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setRootid(Long rootid) {
		this.rootid = rootid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.PARENTID
	 * @return  the value of IRP_QUESTION.PARENTID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getParentid() {
		return parentid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.PARENTID
	 * @param parentid  the value for IRP_QUESTION.PARENTID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.BESTANSWERID
	 * @return  the value of IRP_QUESTION.BESTANSWERID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getBestanswerid() {
		return bestanswerid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.BESTANSWERID
	 * @param bestanswerid  the value for IRP_QUESTION.BESTANSWERID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setBestanswerid(Long bestanswerid) {
		this.bestanswerid = bestanswerid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.ISBESTANSWER
	 * @return  the value of IRP_QUESTION.ISBESTANSWER
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Short getIsbestanswer() {
		return isbestanswer;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.ISBESTANSWER
	 * @param isbestanswer  the value for IRP_QUESTION.ISBESTANSWER
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setIsbestanswer(Short isbestanswer) {
		this.isbestanswer = isbestanswer;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.EXPERTANSWERID
	 * @return  the value of IRP_QUESTION.EXPERTANSWERID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getExpertanswerid() {
		return expertanswerid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.EXPERTANSWERID
	 * @param expertanswerid  the value for IRP_QUESTION.EXPERTANSWERID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setExpertanswerid(Long expertanswerid) {
		this.expertanswerid = expertanswerid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.ISEXPERTANSWER
	 * @return  the value of IRP_QUESTION.ISEXPERTANSWER
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Short getIsexpertanswer() {
		return isexpertanswer;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.ISEXPERTANSWER
	 * @param isexpertanswer  the value for IRP_QUESTION.ISEXPERTANSWER
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setIsexpertanswer(Short isexpertanswer) {
		this.isexpertanswer = isexpertanswer;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.REPLYCOUNT
	 * @return  the value of IRP_QUESTION.REPLYCOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getReplycount() {
		return replycount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.REPLYCOUNT
	 * @param replycount  the value for IRP_QUESTION.REPLYCOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setReplycount(Long replycount) {
		this.replycount = replycount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.BROWSECOUNT
	 * @return  the value of IRP_QUESTION.BROWSECOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getBrowsecount() {
		return browsecount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.BROWSECOUNT
	 * @param browsecount  the value for IRP_QUESTION.BROWSECOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setBrowsecount(Long browsecount) {
		this.browsecount = browsecount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.GOODEVALUATECOUNT
	 * @return  the value of IRP_QUESTION.GOODEVALUATECOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getGoodevaluatecount() {
		return goodevaluatecount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.GOODEVALUATECOUNT
	 * @param goodevaluatecount  the value for IRP_QUESTION.GOODEVALUATECOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setGoodevaluatecount(Long goodevaluatecount) {
		this.goodevaluatecount = goodevaluatecount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.BADEVALUATECOUNT
	 * @return  the value of IRP_QUESTION.BADEVALUATECOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getBadevaluatecount() {
		return badevaluatecount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.BADEVALUATECOUNT
	 * @param badevaluatecount  the value for IRP_QUESTION.BADEVALUATECOUNT
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setBadevaluatecount(Long badevaluatecount) {
		this.badevaluatecount = badevaluatecount;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.DOCID
	 * @return  the value of IRP_QUESTION.DOCID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getDocid() {
		return docid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.DOCID
	 * @param docid  the value for IRP_QUESTION.DOCID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setDocid(Long docid) {
		this.docid = docid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.CRUSERID
	 * @return  the value of IRP_QUESTION.CRUSERID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getCruserid() {
		return cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.CRUSERID
	 * @param cruserid  the value for IRP_QUESTION.CRUSERID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setCruserid(Long cruserid) {
		this.cruserid = cruserid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.CRUSER
	 * @return  the value of IRP_QUESTION.CRUSER
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public String getCruser() {
		return cruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.CRUSER
	 * @param cruser  the value for IRP_QUESTION.CRUSER
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.CRTIME
	 * @return  the value of IRP_QUESTION.CRTIME
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Date getCrtime() {
		return crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.CRTIME
	 * @param crtime  the value for IRP_QUESTION.CRTIME
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column IRP_QUESTION.REPLYQUESTIONID
	 * @return  the value of IRP_QUESTION.REPLYQUESTIONID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public Long getReplyquestionid() {
		return replyquestionid;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column IRP_QUESTION.REPLYQUESTIONID
	 * @param replyquestionid  the value for IRP_QUESTION.REPLYQUESTIONID
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void setReplyquestionid(Long replyquestionid) {
		this.replyquestionid = replyquestionid;
	}

	   
    public String getStatusName(){
		switch (this.status) {
		case STATUS_YES:
			return "已解决";
		case STATUS_NO:
			return "未解决";
		default:
			return "";
		}
	}
    

	/**问题的状态  1表示已解决 0表示未解决**/
    public final static int  STATUS_YES=1;

    public final static int STATUS_NO=0;
    /** 对象表名 **/
    public final static String TABLE_NAME = "IRP_QUESTION";
    
    /** 主键字段名 **/
    public final static String ID_FIELD_NAME = "QUESTIONID";

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.questionid;
	}

	@Override
	public String getName() {
		return this.title;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		return ID_FIELD_NAME;
	}
	
	//增加开关如果启用了，所有的专家角色用户回答的都可以显示在专家答案部分；如果没有启用，则只有指定专家答复的才算专家答案
	public static final String ENABLEEXPERTANSWER = "ENABLEEXPERTANSWER";
	/**
	 * 是问题
	 * @return
	 */
	public static Integer ISQUESTION = 1;
	/**
	 * 是答案
	 * @return
	 */
	public static Integer ISANSWER = 2;
	/**
	 * 以解决的问题
	 * @return
	 */
    public static Integer ISBESTQUESTION = 1;
	/**
	 * 其他问题
	 * @return
	 */
    public static Integer ISOTHERQUESTION = 0;
	/**
	 * 问答统计x轴显示
	 * @return
	 */
    public static Integer QUESTIONTOTALVIEW =10;
    
    /**
     * 回答评论标识
     * @return
     */
    public static final Integer REPLYCOMMENTSTATUS = 3;
    /**
     * 回复回答每页显示条数
     * @return
     */
    public static final Integer REPLYCVIEWNUM = 10;
    
	
	
}