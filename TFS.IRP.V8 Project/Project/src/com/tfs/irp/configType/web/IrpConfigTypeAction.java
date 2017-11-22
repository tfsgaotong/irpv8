package com.tfs.irp.configType.web;

import java.util.List;

import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.configType.entity.IrpConfigType;
import com.tfs.irp.configType.service.IrpConfigTypeService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.PageUtil;

public class IrpConfigTypeAction extends ActionSupport {

	private IrpConfigTypeService irpConfigTypeService;

	public IrpConfigTypeService getIrpConfigTypeService() {
		return irpConfigTypeService;
	}

	public void setIrpConfigTypeService(IrpConfigTypeService irpConfigTypeService) {
		this.irpConfigTypeService = irpConfigTypeService;
	}
	/*
	 * 系统配置类型集合
	 */
	private List<IrpConfigType> irpConfigType;
	public List<IrpConfigType> getIrpConfigType() {
		return irpConfigType;
	}

	public void setIrpConfigType(List<IrpConfigType> irpConfigType) {
		this.irpConfigType = irpConfigType;
	}
	/*
	 * 分页加排序
	 */
	private String pageHTML = "";
	private int pageNum = 1;

	private int pageSize = 10;

	private String orderField = "";

	private String orderBy = "";

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
	/*
	 * 加载系统配置类型
	 */
	public String loadknowledgemenu(){
		// 处理排序
		String _orderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_orderby = "configtypeid asc";
		} else {
			_orderby = this.orderField + " " + this.orderBy;
		}
		int pageconfigtypesize=this.irpConfigTypeService.IrpConfigTypeCount();
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				pageconfigtypesize);
		this.irpConfigType=this.irpConfigTypeService.findAllIrpConfigTypeOfPage(pageUtil, _orderby);
        return SUCCESS;
	}

	
}
