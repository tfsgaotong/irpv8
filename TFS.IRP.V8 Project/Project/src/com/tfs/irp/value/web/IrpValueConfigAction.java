package com.tfs.irp.value.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.config.entity.IrpConfig;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.value.entity.IrpValueConfig;
import com.tfs.irp.value.service.IrpValueConfigService;

public class IrpValueConfigAction extends ActionSupport {
	
	/*service*/
	private IrpValueConfigService irpValueConfigService;

	public IrpValueConfigService getIrpValueConfigService() {
		return irpValueConfigService;
	}

	public void setIrpValueConfigService(IrpValueConfigService irpValueConfigService) {
		this.irpValueConfigService = irpValueConfigService;
	}
	/*分页和排序*/
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
	/*IrpValueConfig集合*/
	private List<IrpValueConfig> irpValueConfiglist;
	public List<IrpValueConfig> getIrpValueConfiglist() {
		return irpValueConfiglist;
	}

	public void setIrpValueConfiglist(List<IrpValueConfig> irpValueConfiglist) {
		this.irpValueConfiglist = irpValueConfiglist;
	}
	/*IrpValueConfig对象*/
	private IrpValueConfig _irpValueConfig;
    public IrpValueConfig get_irpValueConfig() {
		return _irpValueConfig;
	}

	public void set_irpValueConfig(IrpValueConfig _irpValueConfig) {
		this._irpValueConfig = _irpValueConfig;
	}
	/*valueconfigid修改删除id*/
	private String valueconfigid;

    public String getValueconfigid() {
		return valueconfigid;
	}

	public void setValueconfigid(String valueconfigid) {
		this.valueconfigid = valueconfigid;
	}
	/*多选删除获得的id*/
	private String valueconfigids;

    public String getValueconfigids() {
		return valueconfigids;
	}

	public void setValueconfigids(String valueconfigids) {
		this.valueconfigids = valueconfigids;
	}
	/*判断valuekey唯一*/
	private String ck_valuekey;
	private String valuekey;

    public String getCk_valuekey() {
		return ck_valuekey;
	}

	public void setCk_valuekey(String ck_valuekey) {
		this.ck_valuekey = ck_valuekey;
	}

	public String getValuekey() {
		return valuekey;
	}

	public void setValuekey(String valuekey) {
		this.valuekey = valuekey;
	}
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

/**
 * 全部贡献配置
 * @return
 */
	public String setContributeAll(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "valueid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				this.irpValueConfigService.valueConfigListSize(searchWord,searchType));
		this.irpValueConfiglist = this.irpValueConfigService.findAllValueConfigOfPage(pageUtil, _oOrderby,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 增加贡献配置
	 * @return
	 */
	public void setContributeAdd(){
	int _nStatus=this.irpValueConfigService.valueConfigAdd(_irpValueConfig);
	ActionUtil.writer(""+_nStatus);	
	}
	/**
	 * 删除贡献类型
	 * @return
	 */
	public void setContributedelete(){
	int _nStatus = this.irpValueConfigService.valueConfigDelete(Long.parseLong(this.valueconfigid));
	ActionUtil.writer(""+_nStatus);	
	}
	/**
	 * 修改贡献配置
	 * @return
	 */
	public void setContributeUpdate(){
	int _nStatus=this.irpValueConfigService.valueConfigUpdate(Long.parseLong(this.valueconfigid), _irpValueConfig);	
	ActionUtil.writer(""+_nStatus);
	}
	/**
	 * 加载修改贡献类型框
	 * @return
	 */
	public String loadVCEditForm(){
	this._irpValueConfig = this.irpValueConfigService.irpValueConfig(Long.parseLong(this.valueconfigid));
	return SUCCESS;
	}
	/**
	 * 加载增加贡献类型框
	 * @return
	 */
	public String loadVCEditFormAdd(){
	this._irpValueConfig=new IrpValueConfig();
	return SUCCESS;	
	}
	/**
	 * 多选删除
	 * @return
	 */
	public void setContributeAllUpdate(){
	 String valueconfigid[]=this.irpValueConfigService.valueIdAll(valueconfigids);
	 int _nStatus = 0;
		for (int i = 0; i < valueconfigid.length; i++) {
			_nStatus=this.irpValueConfigService.valueConfigDelete(Long.parseLong(valueconfigid[i]));
		}
	 ActionUtil.writer(""+_nStatus);	
	}
	/**
	 * 判断valuekey是否存在
	 * @return
	 */
	public void valueKeyCheck(){
		if (this.valuekey.trim().equals(this.ck_valuekey.trim())) {
			ActionUtil.writer("true");
		} else {
			int _ckeyStatus = this.irpValueConfigService.boolValueKey(this.valuekey.trim());
			if (_ckeyStatus == 1) {
				ActionUtil.writer("false");
			} else if (_ckeyStatus == 2) {
				ActionUtil.writer("true");
			} else {
				ActionUtil.writer("false");
			}
		}
		
	}

	

}
