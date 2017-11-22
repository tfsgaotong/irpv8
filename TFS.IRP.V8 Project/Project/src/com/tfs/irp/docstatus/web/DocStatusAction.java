package com.tfs.irp.docstatus.web;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.docstatus.entity.IrpDocstatus;
import com.tfs.irp.docstatus.service.IrpDocStatusService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.PageUtil;

public class DocStatusAction extends ActionSupport {
	private IrpDocStatusService irpDocstatusService;
	private List<IrpDocstatus> irpDocstatuss;
	private IrpDocstatus docstatus;
	
	private String docstatusname; 
	
	private Long docstatusid;
	
	
	private int pageNum=1; 
	private int pageSize=10; 
	private String pageHTML;
	private String orderField=""; 
	private String orderBy=""; 
	
	private String searchWord;
	private String searchType;
	
	//根据id删除文档状态
		public String deleteDocstatusByStatusId(){ 
			int nCount=irpDocstatusService.deleteDocustatusByStatusId(docstatus.getStatusid());
		    ActionUtil.writer(String.valueOf(nCount));
			return allDocustatus();
		}
	//显示所有文档状态
	public String allDocustatus(){
		HashMap<String,Object> map=new HashMap<String, Object>();
		 //检索 
		if(searchWord!=null && searchWord.length()>0){
			searchWord = ActionUtil.decode(searchWord);
			map.put("searchWord", searchWord);
			map.put("searchType", searchType); 
		} 
		//处理排序
		String sOrderByClause ="";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "statusid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		} 
		 int aDataCount=irpDocstatusService.selectCountByMap(map); 
	 	 PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
		irpDocstatuss=irpDocstatusService.findAllStatusinfo(pageUtil,map,sOrderByClause); 
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	} 
	//检查名称的唯一性
	public void checkDocstatusName(){ 
		boolean boo=false;
			if(docstatusid!=null && docstatusid!=0L){
				IrpDocstatus oldDocStatus=irpDocstatusService.finDocstatusByStatusId(docstatusid);
				 if(oldDocStatus.getSname().equals(docstatusname)){
					boo=true;
				 }else{
					 boo=irpDocstatusService.findStatusBySname(docstatusname);
				 }
		}else{
			 boo=irpDocstatusService.findStatusBySname(docstatusname);
		}
			ActionUtil.writer(String.valueOf(boo));
	}
	//跳转到添加页面
	public String toInsertDocstatus(){ 
		return SUCCESS;
	}
	
	//添加文档状态
	public void addDocstatus(){
		int  nCount=irpDocstatusService.insertDocstatus(docstatus); 
		ActionUtil.writer(String.valueOf(nCount));
	}
	
	//修改文档状态
	public void updateDocstatusByExample(){
		int nCount=irpDocstatusService.updateDocustatus(docstatus); 
		ActionUtil.writer(String.valueOf(nCount));
	}
	//跳转到修改文档状态页面
	public String toUpdateDocustatusByStatusId(){
		docstatus=irpDocstatusService.finDocstatusByStatusId(docstatus.getStatusid());
		return SUCCESS;
	}
	public IrpDocStatusService getIrpDocstatusService() {
		return irpDocstatusService;
	}

	public void setIrpDocstatusService(IrpDocStatusService irpDocstatusService) {
		this.irpDocstatusService = irpDocstatusService;
	}

	public List<IrpDocstatus> getIrpDocstatuss() {
		return irpDocstatuss;
	}

	public void setIrpDocstatuss(List<IrpDocstatus> irpDocstatuss) {
		this.irpDocstatuss = irpDocstatuss;
	}

	public IrpDocstatus getDocstatus() {
		return docstatus;
	}

	public void setDocstatus(IrpDocstatus docstatus) {
		this.docstatus = docstatus;
	}
	public String getDocstatusname() {
		return docstatusname;
	}
	public void setDocstatusname(String docstatusname) {
		this.docstatusname = docstatusname;
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
	public String getSearchWord() {
		return searchWord;
	}
	public Long getDocstatusid() {
		return docstatusid;
	}
	public void setDocstatusid(Long docstatusid) {
		this.docstatusid = docstatusid;
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
	 
}
