package com.tfs.irp.opertype.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.opertype.service.IrpOpertypeService;
import com.tfs.irp.util.PageUtil;

public class OpertypeAction extends ActionSupport {
	
	private IrpOpertypeService irpOpertypeService;
	
	private List<IrpOpertype> irpOpertypes;
	
    private int pageNum=1;
	
	private int pageSize=10;
	
	private String orderField="";
	
	private String orderBy="";
	
	private IrpOpertype irpOpertype;
	
	private Long _opertypeUpdate;
/*检索*/
	private String searchWord;
	private String searchType;
	
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

	public Long get_opertypeUpdate() {
		return _opertypeUpdate;
	}

	public void set_opertypeUpdate(Long _opertypeUpdate) {
		this._opertypeUpdate = _opertypeUpdate;
	}

	public IrpOpertype getIrpOpertype() {
		return irpOpertype;
	}

	public void setIrpOpertype(IrpOpertype irpOpertype) {
		this.irpOpertype = irpOpertype;
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



	private String pageHTML="";
	
	
	
	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}




	public List<IrpOpertype> getIrpOpertypes() {
		return irpOpertypes;
	}

	public void setIrpOpertypes(List<IrpOpertype> irpOpertypes) {
		this.irpOpertypes = irpOpertypes;
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

	public IrpOpertypeService getIrpOpertypeService() {
		return irpOpertypeService;
	}

	public void setIrpOpertypeService(IrpOpertypeService irpOpertypeService) {
		this.irpOpertypeService = irpOpertypeService;
	}

	
	/*
	 * 操作类型列表
	 */
	public String opertypeSetList(){
		int nDateCount=this.irpOpertypeService.findOpertypeCountByStatus(searchWord,searchType);
		PageUtil pageUtil=new PageUtil(this.pageNum,this.getPageSize(),nDateCount);
		//处理排序
		String _oOrderby="";
		if (this.orderField==null || this.orderField.equals("")) {
			_oOrderby="crtime desc";
		}else{
			_oOrderby=this.orderField+" "+this.orderBy;
		}
		
	this.irpOpertypes=this.irpOpertypeService.findOpertypeOfPage(pageUtil, _oOrderby,searchWord,searchType);
	this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
	return SUCCESS;
	}
	/*
	 * 增加操作类型 1:框内表格 
	 */
	public String opertypeSetAdd(){
		this.irpOpertype = new IrpOpertype();
		return SUCCESS;
	}
	/*
	 * 修改操作类型1：框内表格
	 */
     public String opertypeSetUpdate(){
    	 this.irpOpertype=this.irpOpertypeService.irpOpertype(this.get_opertypeUpdate());
    	 return SUCCESS;
     }
}
