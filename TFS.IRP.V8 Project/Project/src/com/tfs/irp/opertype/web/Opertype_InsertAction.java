package com.tfs.irp.opertype.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.opertype.service.IrpOpertypeService;
import com.tfs.irp.util.ActionUtil;

public class Opertype_InsertAction extends ActionSupport {

	private IrpOpertype irpOpertype;
	private int pageNum = 1;

	private int pageSize = 10;

	private String orderField = "";

	private String orderBy = "";

	private String pageHTML = "";

	private Long _opertypeUpdate;

	private Long _opertypeDelete;

	private String deleteAllOpertype;

	private String _opername;
	
	//检索   操作类型是否存在
	private String ck_opertypetwo;
	public String getCk_opertypetwo() {
		return ck_opertypetwo;
	}

	public void setCk_opertypetwo(String ck_opertypetwo) {
		this.ck_opertypetwo = ck_opertypetwo;
	}

	private String ck_opertype;
	private String ck_opertypeId;
	
	public String getCk_opertypeId() {
		return ck_opertypeId;
	}

	public void setCk_opertypeId(String ck_opertypeId) {
		this.ck_opertypeId = ck_opertypeId;
	}

	public String getCk_opertype() {
		return ck_opertype;
	}

	public void setCk_opertype(String ck_opertype) {
		this.ck_opertype = ck_opertype;
	}

	public String get_opername() {
		return _opername;
	}

	public void set_opername(String _opername) {
		this._opername = _opername;
	}

	public String getDeleteAllOpertype() {
		return deleteAllOpertype;
	}

	public void setDeleteAllOpertype(String deleteAllOpertype) {
		this.deleteAllOpertype = deleteAllOpertype;
	}

	public Long get_opertypeDelete() {
		return _opertypeDelete;
	}

	public void set_opertypeDelete(Long _opertypeDelete) {
		this._opertypeDelete = _opertypeDelete;
	}

	public Long get_opertypeUpdate() {
		return _opertypeUpdate;
	}

	public void set_opertypeUpdate(Long _opertypeUpdate) {
		this._opertypeUpdate = _opertypeUpdate;
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

	public IrpOpertype getIrpOpertype() {
		return irpOpertype;
	}

	public void setIrpOpertype(IrpOpertype irpOpertype) {
		this.irpOpertype = irpOpertype;
	}

	private IrpOpertypeService irpOpertypeService;

	public IrpOpertypeService getIrpOpertypeService() {
		return irpOpertypeService;
	}

	public void setIrpOpertypeService(IrpOpertypeService irpOpertypeService) {
		this.irpOpertypeService = irpOpertypeService;
	}

	/*
	 * 添加操作类型
	 */
	public String opertypeInsert() {
	   
		int nCount = this.irpOpertypeService.InsertOpertype(this.getIrpOpertype());
		if (nCount == 1) {
			return SUCCESS;
		} else if (nCount == 2) {
			return INPUT;
		} else {
			return ERROR;
		}
	}

	/*
	 * 修改操作类型
	 */
	public void opertypeUpdate() {
		int nCount = this.irpOpertypeService.UpdateOpertype(
				this._opertypeUpdate, irpOpertype);
	ActionUtil.writer(nCount+"");
	}

	/*
	 * 删除操作类型
	 */
	public String opertypeDelete() {
		int nCount = this.irpOpertypeService.DeleteOpertype(this
				.get_opertypeDelete());
		if (nCount == 1) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/*
	 * 全选删除操作类型
	 */
	public String opertypeAllDelete() {

		String[] _opertypeId = this.irpOpertypeService.opertypeAllId(this
				.getDeleteAllOpertype());
		for (int i = 0; i < _opertypeId.length; i++) {
			this.irpOpertypeService.DeleteOpertype(Long
					.parseLong(_opertypeId[i]));
		}
		return SUCCESS;
	}

	/*
	 * 检查用户名是否存在 
	 */
	public void findOpername() {
		boolean date=false;
		if(this.ck_opertype.trim().equals(this.ck_opertypetwo.trim())){
			date=true;
		}else{
		int dates = this.irpOpertypeService.findOpernameCountByStatus(this.ck_opertype);
			if (dates==1) {
				date=true;
			}else if(dates==2){
				date=false;
			}else{
				date=false;
			}
		}
		ActionUtil.writer(""+date);
	}

}
