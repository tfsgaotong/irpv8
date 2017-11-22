package com.tfs.irp.dbupdate.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.dbupdate.entity.IrpDbupdate;
import com.tfs.irp.dbupdate.service.IrpDbupdateService;
import com.tfs.irp.util.PageUtil;

public class IrpDbupdateAction extends ActionSupport {

	private IrpDbupdateService irpDbupdateService;

	public IrpDbupdateService getIrpDbupdateService() {
		return irpDbupdateService;
	}

	public void setIrpDbupdateService(IrpDbupdateService irpDbupdateService) {
		this.irpDbupdateService = irpDbupdateService;
	}
	
	
	/**
	 * 分页排序检索
	 */
	private String pageHTML = "";
	private int pageNum = 1;
	private int pageSize = 10;
	private String orderField = "";
	private String orderBy = "";
	private String searchWord;
	private String searchType;
	private List<IrpDbupdate> irpDbupdatelist; 
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

	public List<IrpDbupdate> getIrpDbupdatelist() {
		return irpDbupdatelist;
	}

	public void setIrpDbupdatelist(List<IrpDbupdate> irpDbupdatelist) {
		this.irpDbupdatelist = irpDbupdatelist;
	}

	/**
	 * 查看所有信息
	 * @return
	 */
	public String getAllDbUpdate(){
		
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "dbupid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = this.irpDbupdateService.findAllIrpDbupdateTypePageCount(searchWord,searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				configsize);
		this.irpDbupdatelist = this.irpDbupdateService.findAllIrpDbupdateTypePage(pageUtil,
				_oOrderby,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		
		return SUCCESS;
	}
}
