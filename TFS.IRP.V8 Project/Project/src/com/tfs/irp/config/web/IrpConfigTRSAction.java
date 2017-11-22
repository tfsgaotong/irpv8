package com.tfs.irp.config.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.config.entity.IrpConfig;
import com.tfs.irp.config.service.IrpConfigService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.PageUtil;

public class IrpConfigTRSAction extends ActionSupport {

	private IrpConfigService irpConfigService;

	public IrpConfigService getIrpConfigService() {
		return irpConfigService;
	}

	public void setIrpConfigService(IrpConfigService irpConfigService) {
		this.irpConfigService = irpConfigService;
	}

	// irpconfig 对象
	private IrpConfig irpconfig;

	public IrpConfig getIrpconfig() {
		return irpconfig;
	}

	public void setIrpconfig(IrpConfig irpconfig) {
		this.irpconfig = irpconfig;
	}

	/*
	 * 分页排序
	 */
	private String pageHTML = "";
	private int pageNum = 1;

	private int pageSize = 10;

	private String orderField = "";

	private String orderBy = "";
	/*
	 * IrpConfig
	 */
	private List<IrpConfig> irpConfig;
	/*
	 * 存放目录名称
	 */
	private String ck_ckey;
	/*
	 * config 存放目录的id
	 */
	private String configid;
	/*
	 * 更新检索验证
	 */
	private String ck_ckeytwo;

	/*
	 * 多选删除
	 */
	private String configidall;

	public String getConfigidall() {
		return configidall;
	}

	public void setConfigidall(String configidall) {
		this.configidall = configidall;
	}

	public String getCk_ckeytwo() {
		return ck_ckeytwo;
	}

	public void setCk_ckeytwo(String ck_ckeytwo) {
		this.ck_ckeytwo = ck_ckeytwo;
	}

	public String getConfigid() {
		return configid;
	}

	public void setConfigid(String configid) {
		this.configid = configid;
	}

	public String getCk_ckey() {
		return ck_ckey;
	}

	public void setCk_ckey(String ck_ckey) {
		this.ck_ckey = ck_ckey;
	}

	public List<IrpConfig> getIrpConfig() {
		return irpConfig;
	}

	public void setIrpConfig(List<IrpConfig> irpConfig) {
		this.irpConfig = irpConfig;
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

	/* 检索 */
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
	 * 系统TRS检索配配置列表
	 * @return
	 */
	public String trsSearchConf(){
		
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "configid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = this.irpConfigService.IrpConfigCount(IrpConfig.SEARCHTRSTYPE,searchWord,searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				configsize);
		this.irpConfig = this.irpConfigService.findAllOfPage(pageUtil,
				_oOrderby,IrpConfig.SEARCHTRSTYPE,searchWord,searchType);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		
		
		return SUCCESS;
	}
	/**
	 * 增加TRS搜索配置
	 */
	public void addSetSearch(){
		int _cataStatus = this.irpConfigService.addConfigCatalogue(irpconfig,IrpConfig.SEARCHTRSTYPE);
		ActionUtil.writer(_cataStatus + "");
	}
	/**
	 * 更新搜索配置
	 */
	public void updateConfigSearchOther(){
		int _updateCataStat = this.irpConfigService.updateConfigCatalogue(
				irpconfig, Long.parseLong(getConfigid()),IrpConfig.SEARCHTRSTYPE);
		ActionUtil.writer(_updateCataStat + "");
		
	}
}
