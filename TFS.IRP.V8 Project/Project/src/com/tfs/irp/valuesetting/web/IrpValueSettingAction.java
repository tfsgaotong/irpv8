package com.tfs.irp.valuesetting.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.valuesetting.entity.IrpValueSetting;
import com.tfs.irp.valuesetting.service.IrpValueSettingService;

public class IrpValueSettingAction extends ActionSupport {
	
	
	private IrpValueSettingService irpValueSettingService;
	
	private List<IrpValueSetting> irpValueSettinglist;
	
	private IrpValueSetting irpValueSetting;

	   private String searchWord;
	    
	    private String searchType;
	    
	    /*
		 * 分页排序
		 */
		private String pageHTML = "";
		private int pageNum = 1;

		private int pageSize = 10;

		private String orderField = "";

		private String orderBy = "";
		
		private long settingid;
		
		private String settingids;
	    
	public String getSettingids() {
			return settingids;
		}

		public void setSettingids(String settingids) {
			this.settingids = settingids;
		}

	public long getSettingid() {
			return settingid;
		}

		public void setSettingid(long settingid) {
			this.settingid = settingid;
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

	public IrpValueSetting getIrpValueSetting() {
		return irpValueSetting;
	}

	public void setIrpValueSetting(IrpValueSetting irpValueSetting) {
		this.irpValueSetting = irpValueSetting;
	}

	public List<IrpValueSetting> getIrpValueSettinglist() {
		return irpValueSettinglist;
	}

	public void setIrpValueSettinglist(List<IrpValueSetting> irpValueSettinglist) {
		this.irpValueSettinglist = irpValueSettinglist;
	}

	public IrpValueSettingService getIrpValueSettingService() {
		return irpValueSettingService;
	}

	public void setIrpValueSettingService(
			IrpValueSettingService irpValueSettingService) {
		this.irpValueSettingService = irpValueSettingService;
	}
	
	/**
	 * 所有级别配置
	 * @return
	 */
	public String searchUserGrade(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "endscore desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		
		PageUtil pageUtil = new PageUtil(this.pageNum,this.getPageSize(),
				this.irpValueSettingService.searchValueSettingCount(searchWord, searchType));
	irpValueSettinglist =  this.irpValueSettingService.getAllIrpValueSetting(pageUtil,_oOrderby,searchWord,searchType);
	  this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 构建编辑级别控制框
	 * @return
	 */
	public String addValueSetting(){
		
		irpValueSetting = new IrpValueSetting();
		
		return SUCCESS;
	}
	/**
	 * 增加级别控制
	 */
	public void valueSettingConfigAdd(){
		
		int _nStatus=this.irpValueSettingService.addIrpValueSetting(irpValueSetting);
		ActionUtil.writer(""+_nStatus);	
	}

	/**
	 * 加载用户级别配置框
	 */
	public String updateLoadGrade(){
		irpValueSetting =	this.irpValueSettingService.irpValueSetting(settingid);
		return SUCCESS;
	}
	/**
	 * 修改用户级别配置
	 */
	public void updateSettingConfigByid(){
		int _nStatus =	this.irpValueSettingService.updateIrpValueSetting(irpValueSetting);
		ActionUtil.writer(_nStatus+"");
	}
	/**
	 * 删除用户级别配置
	 */
	public void deleteSettingConfigByid(){
		int _nStatus = 0;
		_nStatus =	this.irpValueSettingService.deleteIrpValueSetting(settingid);
		ActionUtil.writer(""+_nStatus);
	}
	/**
	 * 多选删除
	 * @return
	 */
	public void deleteSettingAllByid(){
		int _nStatus = 0;
		String settingidsArray[] = settingids.substring(9).split(",");
		for (int i = 0; i < settingidsArray.length; i++) {
			_nStatus =	this.irpValueSettingService.deleteIrpValueSetting(Long.parseLong(settingidsArray[i]));
			
		}
		ActionUtil.writer(""+_nStatus);
	}
	
}
