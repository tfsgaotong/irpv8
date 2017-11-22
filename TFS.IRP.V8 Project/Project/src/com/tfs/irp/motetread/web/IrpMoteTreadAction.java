package com.tfs.irp.motetread.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.motetread.entity.IrpMostTread;
import com.tfs.irp.motetread.service.IrpMoteTreadService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;

public class IrpMoteTreadAction extends ActionSupport {
	private IrpMoteTreadService irpMoteTreadServiceImpl;

	private Long docid;
	private Long questionid;
	public Long getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Long questionid) {
		this.questionid = questionid;
	}

	public Long getDocid() {
		return docid;
	}

	public void setDocid(Long docid) {
		this.docid = docid;
	}

	public IrpMoteTreadService getIrpMoteTreadServiceImpl() {
		return irpMoteTreadServiceImpl;
	}

	public void setIrpMoteTreadServiceImpl(
			IrpMoteTreadService irpMoteTreadServiceImpl) {
		this.irpMoteTreadServiceImpl = irpMoteTreadServiceImpl;
	}
	//顶 知识
	public void dingDocument(){
		int nCount=irpMoteTreadServiceImpl.addMoteThread(docid, IrpMostTread.MOTE_TREAD_STATUS_DING,IrpMostTread.MOTE_DOCUMENT_TYPE);
		ActionUtil.writer(String.valueOf(nCount));
	}
	public void dingDocumentApp(){
		int nCount=irpMoteTreadServiceImpl.addMoteThreadMobile(docid, IrpMostTread.MOTE_TREAD_STATUS_DING,IrpMostTread.MOTE_DOCUMENT_TYPE);
		ActionUtil.writer(String.valueOf(nCount));
	}
	//踩 知识
	public void caiDocument(){
		int nCount=irpMoteTreadServiceImpl.addMoteThread(docid, IrpMostTread.MOTE_TREAD_STATUS_CAI,IrpMostTread.MOTE_DOCUMENT_TYPE);
		ActionUtil.writer(String.valueOf(nCount));
	}
	public void caiDocumentApp(){
		int nCount=irpMoteTreadServiceImpl.addMoteThreadMobile(docid, IrpMostTread.MOTE_TREAD_STATUS_CAI,IrpMostTread.MOTE_DOCUMENT_TYPE);
		ActionUtil.writer(String.valueOf(nCount));
	}
	/**
	 * 赞同    问答
	 * @return
	 */
	public void addMotertreadByQuestion(){
	int status = this.irpMoteTreadServiceImpl.addMoteThread(questionid, IrpMostTread.MOTE_TREAD_STATUS_DING, IrpMostTread.MOTE_QUESTION_TYPE);
	int msg =  this.irpMoteTreadServiceImpl.IrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_CAI, IrpMostTread.MOTE_QUESTION_TYPE,LoginUtil.getLoginUserId());
	if(msg>=1){
		this.irpMoteTreadServiceImpl.deleteIrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_CAI, IrpMostTread.MOTE_QUESTION_TYPE,LoginUtil.getLoginUserId());
	}
	ActionUtil.writer(status+"");	
	}
	public void addMotertreadByQuestionApp(){
		int status = this.irpMoteTreadServiceImpl.addMoteThreadMobile(questionid, IrpMostTread.MOTE_TREAD_STATUS_DING, IrpMostTread.MOTE_QUESTION_TYPE);
		int msg =  this.irpMoteTreadServiceImpl.IrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_CAI, IrpMostTread.MOTE_QUESTION_TYPE,LoginUtil.getLoginUserId());
		if(msg>=1){
			this.irpMoteTreadServiceImpl.deleteIrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_CAI, IrpMostTread.MOTE_QUESTION_TYPE,LoginUtil.getLoginUserId());
		}
		ActionUtil.writer(status+"");	
	}
	/**
	 * 取消	问答
	 * @return
	 */
	public void addMotertreadByQuestionOppose(){
	int status = 0;
	this.irpMoteTreadServiceImpl.addMoteThread(questionid, IrpMostTread.MOTE_TREAD_STATUS_CAI, IrpMostTread.MOTE_QUESTION_TYPE);
	int msg =  this.irpMoteTreadServiceImpl.IrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_DING, IrpMostTread.MOTE_QUESTION_TYPE,LoginUtil.getLoginUserId());
	if(msg>=1){
		this.irpMoteTreadServiceImpl.deleteIrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_DING, IrpMostTread.MOTE_QUESTION_TYPE,LoginUtil.getLoginUserId());
		status = 1;
	}
	ActionUtil.writer(status+"");
	}
	public void addMotertreadByQuestionOpposeApp(){
		int status = 0;
		this.irpMoteTreadServiceImpl.addMoteThreadMobile(questionid, IrpMostTread.MOTE_TREAD_STATUS_CAI, IrpMostTread.MOTE_QUESTION_TYPE);
		int msg =  this.irpMoteTreadServiceImpl.IrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_DING, IrpMostTread.MOTE_QUESTION_TYPE,LoginUtil.getLoginUserId());
		if(msg>=1){
			this.irpMoteTreadServiceImpl.deleteIrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_DING, IrpMostTread.MOTE_QUESTION_TYPE,LoginUtil.getLoginUserId());
			status = 1;
		}
		ActionUtil.writer(status+"");
	}
	/**
	 * 查看当前用户是否已经赞同过了
	 * @return
	 */
	public void selectMoteByUser(){
	int msg =	this.irpMoteTreadServiceImpl.IrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_DING, IrpMostTread.MOTE_QUESTION_TYPE,LoginUtil.getLoginUserId());
	ActionUtil.writer(msg+"");
	}
	public void selectMoteByUserApp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		int msg =	this.irpMoteTreadServiceImpl.IrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_DING, IrpMostTread.MOTE_QUESTION_TYPE,irpuser.getUserid());
		ActionUtil.writer(msg+"");
	}
	/**
	 * 查看当前用户是否已经取消过了
	 * @return
	 */
	public void selectMoteByUserOppose(){
		int msg =	this.irpMoteTreadServiceImpl.IrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_CAI, IrpMostTread.MOTE_QUESTION_TYPE,LoginUtil.getLoginUserId());
		ActionUtil.writer(msg+"");
	}
	public void selectMoteByUserOpposeApp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		int msg =	this.irpMoteTreadServiceImpl.IrpMoteThreadCountByUser(questionid, IrpMostTread.MOTE_TREAD_STATUS_CAI, IrpMostTread.MOTE_QUESTION_TYPE,irpuser.getUserid());
		ActionUtil.writer(msg+"");
	}
	/**
	 * 判断当前 文章顶 踩
	 * @return mobile
	 */
	public void boolDingOrCaiMobile(){
		if (docid!=null) {
	        int dingnum = this.irpMoteTreadServiceImpl.IrpMoteThreadCountByQuestionid(docid,IrpMostTread.MOTE_TREAD_STATUS_DING,IrpMostTread.MOTE_DOCUMENT_TYPE);
			int cainum = this.irpMoteTreadServiceImpl.IrpMoteThreadCountByQuestionid(docid,IrpMostTread.MOTE_TREAD_STATUS_CAI,IrpMostTread.MOTE_DOCUMENT_TYPE);
			int isding = this.irpMoteTreadServiceImpl.IrpMoteThreadCountByUser(docid, IrpMostTread.MOTE_TREAD_STATUS_DING, IrpMostTread.MOTE_DOCUMENT_TYPE,LoginUtil.getLoginUserId());
			int iscai = this.irpMoteTreadServiceImpl.IrpMoteThreadCountByUser(docid, IrpMostTread.MOTE_TREAD_STATUS_CAI, IrpMostTread.MOTE_DOCUMENT_TYPE,LoginUtil.getLoginUserId());
			String str ="[{'dingnum':"+dingnum+",'cainum':"+cainum+",'isding':"+isding+",'iscai':"+iscai+"}]";
			
			ActionUtil.writer(str);	
		}
	}
	
	
}
