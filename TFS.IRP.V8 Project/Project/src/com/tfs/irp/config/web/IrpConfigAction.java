package com.tfs.irp.config.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.config.entity.IrpConfig;
import com.tfs.irp.config.service.IrpConfigService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.PageUtil;

public class IrpConfigAction extends ActionSupport {

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
	 * 知识存放目录 存放目录
	 * 
	 * @return
	 */
	public String setKnowledgecatalogue() {
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "configid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = this.irpConfigService.IrpConfigCount(IrpConfig.CATALOGUE,searchWord,searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				configsize);
		this.irpConfig = this.irpConfigService.findAllOfPage(pageUtil,
				_oOrderby, IrpConfig.CATALOGUE,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 其他设置
	 * @return
	 */
	public String setOtherSet(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "configid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = this.irpConfigService.IrpConfigCount(IrpConfig.OTHERSET,searchWord,searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				configsize);
		this.irpConfig = this.irpConfigService.findAllOfPage(pageUtil,
				_oOrderby, IrpConfig.OTHERSET,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
		
	}

	/**
	 * 修改存放目录的表单内容
	 * 
	 * @return
	 */
	public String setCatalogueform() {
		irpconfig = this.irpConfigService.irpConfig(Long.parseLong(configid));
		return SUCCESS;
	}

	/**
	 * 增加存放目录表单内容
	 * 
	 * @return
	 */
	public String setAddcatalogueform() {
		irpconfig = new IrpConfig();
		return SUCCESS;
	}

	/**
	 * 增加存放目录 
	 * @return
	 */
	public void addConfigCatalogue() {
		int _cataStatus = this.irpConfigService.addConfigCatalogue(irpconfig,IrpConfig.CATALOGUE);
		ActionUtil.writer(_cataStatus + "");
	}
	/**
	 * 增加其它配置
	 */
	public void addSetOther(){
		int _cataStatus = this.irpConfigService.addConfigCatalogue(irpconfig,IrpConfig.OTHERSET);
		ActionUtil.writer(_cataStatus + "");
	}
	/**
	 * 增加系统邮箱配置
	 */
	public void addEmail(){
		int _cataStatus = this.irpConfigService.addConfigCatalogue(irpconfig,IrpConfig.SYS_EMAIL);
		ActionUtil.writer(_cataStatus + "");
	}
	/**
	 * 增加搜索配置
	 */
	public void addSetSearch(){
		int _cataStatus = this.irpConfigService.addConfigCatalogue(irpconfig,IrpConfig.SEARCHTYPE);
		ActionUtil.writer(_cataStatus + "");
	}
	/**
	 * 更新存放目录
	 * 
	 * @return
	 */
	public void updateConfigCatalogue() {
		int _updateCataStat = this.irpConfigService.updateConfigCatalogue(
				irpconfig, Long.parseLong(getConfigid()),IrpConfig.CATALOGUE);
		ActionUtil.writer(_updateCataStat + "");
	}
	/**
	 * 更新系统邮箱配置
	 * @return
	 */
	public void updateConfigEmail() {
		int _updateCataStat = this.irpConfigService.updateConfigCatalogue(
				irpconfig, Long.parseLong(getConfigid()),IrpConfig.SYS_EMAIL);
		ActionUtil.writer(_updateCataStat + "");
	}
	/**
	 * 更新其它配置
	 * @return
	 */
	public void updateConfigOther(){
		int _updateCataStat = this.irpConfigService.updateConfigCatalogue(
				irpconfig, Long.parseLong(getConfigid()),IrpConfig.OTHERSET);
		ActionUtil.writer(_updateCataStat + "");
		
	}
	/**
	 * 更新搜索配置
	 */
	public void updateConfigSearchOther(){
		int _updateCataStat = this.irpConfigService.updateConfigCatalogue(
				irpconfig, Long.parseLong(getConfigid()),IrpConfig.SEARCHTYPE);
		ActionUtil.writer(_updateCataStat + "");
		
	}
	
	/**
	 * 加载其它配置
	 * @return
	 */
	public String updateSetOther(){
		irpconfig = this.irpConfigService.irpConfig(Long.parseLong(configid));
	   return SUCCESS;
	}
    
	/**
	 * 检查存放目录的名称是否重名
	 * 
	 * @return
	 */
	public void checkCataCkey() {
		if (this.ck_ckey.trim().equals(this.ck_ckeytwo.trim())) {
			ActionUtil.writer("true");
		} else {
			int _ckeyStatus = this.irpConfigService
					.findConfigCataCkey(this.ck_ckey.trim());
			if (_ckeyStatus == 1) {
				ActionUtil.writer("false");
			} else if (_ckeyStatus == 2) {
				ActionUtil.writer("true");
			} else {
				ActionUtil.writer("false");
			}
		}
	}
	/**
	 * 检查其它配置类型是否重名
	 */
	public void checkOtherSet(){
		if (this.ck_ckey.trim().equals(this.ck_ckeytwo.trim())) {
			ActionUtil.writer("true");
		} else {
			int _ckeyStatus = this.irpConfigService
					.findConfigCataCkey(this.ck_ckey.trim());
			if (_ckeyStatus == 1) {
				ActionUtil.writer("false");
			} else if (_ckeyStatus == 2) {
				ActionUtil.writer("true");
			} else {
				ActionUtil.writer("false");
			}
		}
	}
		

	/**
	 * 删除存放目录
	 * 
	 * @return
	 */
	public void deleteConfigCatalogue() {
		int _deletecata = this.irpConfigService.deleteConfigCatalogue(Long
				.parseLong(getConfigid()),IrpConfig.CATALOGUE);
		ActionUtil.writer("" + _deletecata);
	}
	/**
	 * 删除其它配置
	 */
	public void deleteSetOther(){
		
		int _deletecata = this.irpConfigService.deleteConfigCatalogue(Long
				.parseLong(getConfigid()),IrpConfig.OTHERSET);
		ActionUtil.writer("" + _deletecata);
	}
	/**
	 * 删除搜索配置
	 */
	public void deleteSearchSet(){
		int _deletecata = this.irpConfigService.deleteConfigCatalogue(Long
				.parseLong(getConfigid()),IrpConfig.SEARCHTYPE);
		ActionUtil.writer("" + _deletecata);
	}
	/**
	 * 多选删除 存放目录
	 * @return
	 */
	public void deleteAllConfigCatalogue(){
	
		String[] configidalls= this.irpConfigService.configIdAll(configidall);
		int date=0;
		for (int i = 0; i < configidalls.length; i++) {
			
			date=this.irpConfigService.deleteConfigCatalogue(Long.parseLong(configidalls[i]),IrpConfig.CATALOGUE);
		}
		
		ActionUtil.writer(""+date);
	}
	/**
	 * 多选删除 搜索
	 */
	public void deleteSearchOther(){
		String[] configidalls= this.irpConfigService.configIdAll(configidall);
		int date=0;
		for (int i = 0; i < configidalls.length; i++) {
			
			date=this.irpConfigService.deleteConfigCatalogue(Long.parseLong(configidalls[i]),IrpConfig.SEARCHTYPE);
		}
		
		ActionUtil.writer(""+date);
	}
	
	/**
	 * 多选删除 其它配置
	 * @return
	 */
	public void deleteAllOther(){
		String[] configidalls= this.irpConfigService.configIdAll(configidall);
		int date=0;
		for (int i = 0; i < configidalls.length; i++) {
			
			date=this.irpConfigService.deleteConfigCatalogue(Long.parseLong(configidalls[i]),IrpConfig.OTHERSET);
		}
		
		ActionUtil.writer(""+date);
		
	}

	/**
	 * 系统检索配配置列表
	 * @return
	 */
	public String setSearchConf(){
		
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "configid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = this.irpConfigService.IrpConfigCount(IrpConfig.SEARCHTYPE,searchWord,searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				configsize);
		this.irpConfig = this.irpConfigService.findAllOfPage(pageUtil,
				_oOrderby,IrpConfig.SEARCHTYPE,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		
		
		return SUCCESS;
	}
	
	public String setEmailList(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "configid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = this.irpConfigService.IrpConfigCount(IrpConfig.SYS_EMAIL,searchWord,searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(), configsize);
		this.irpConfig = this.irpConfigService.findAllOfPage(pageUtil, _oOrderby, IrpConfig.SYS_EMAIL,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 其他登录方式
	 * @return
	 */
	public String oauthLoginSet(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "configid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = this.irpConfigService.IrpConfigCount(IrpConfig.OAUTHLOGIN,searchWord,searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				configsize);
		this.irpConfig = this.irpConfigService.findAllOfPage(pageUtil,
				_oOrderby, IrpConfig.OAUTHLOGIN,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	public void updateOauthLogin() {
		int _updateCataStat = this.irpConfigService.updateConfigCatalogue(irpconfig, Long.parseLong(getConfigid()),IrpConfig.OAUTHLOGIN);
		ActionUtil.writer(_updateCataStat + "");
	}
	
	/**
	 * 数据管理配置列表
	 */
	public String dataSetting() {
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "configid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = this.irpConfigService.IrpConfigCount(IrpConfig.DATASETTING, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(), configsize);
		this.irpConfig = this.irpConfigService.findAllOfPage(pageUtil, _oOrderby, IrpConfig.DATASETTING, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;

	}
	
	/**
	 * 栏目配置列表
	 */
	public String chanelSetting() {
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "configid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = this.irpConfigService.IrpConfigCount(IrpConfig.CHANELSETTING, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(), configsize);
		this.irpConfig = this.irpConfigService.findAllOfPage(pageUtil, _oOrderby, IrpConfig.CHANELSETTING, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;

	}
	
	/**
	 * 删除数据管理配置
	 */
	public void deletedataSetting() {
		int _deletecata = this.irpConfigService.deleteConfigCatalogue(Long.parseLong(getConfigid()), IrpConfig.DATASETTING);
		ActionUtil.writer("" + _deletecata);
	}
	/**
	 * 删除数据管理配置
	 */
	public void deletechannelSetting() {
		int _deletecata = this.irpConfigService.deleteConfigCatalogue(Long.parseLong(getConfigid()), IrpConfig.CHANELSETTING);
		ActionUtil.writer("" + _deletecata);
	}
	
	/**
	 * 增加数据管理配置
	 */
	public void adddataSetting() {
		int _cataStatus = this.irpConfigService.addConfigCatalogue(irpconfig, IrpConfig.DATASETTING);
		ActionUtil.writer(_cataStatus + "");
	}
	
	/**
	 * 增加数据管理配置
	 */
	public void addchannelSetting() {
		int _cataStatus = this.irpConfigService.addConfigCatalogue(irpconfig, IrpConfig.CHANELSETTING);
		ActionUtil.writer(_cataStatus + "");
	}
}
