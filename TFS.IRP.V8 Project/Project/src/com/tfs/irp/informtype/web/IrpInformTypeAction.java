package com.tfs.irp.informtype.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.informtype.entity.IrpInformType;
import com.tfs.irp.informtype.service.IrpInformTypeService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class IrpInformTypeAction extends ActionSupport {

	private List<IrpInformType> irpInformTypelist;
	
	private IrpInformTypeService irpInformTypeService;
	
	private IrpInformType irpInformType;
	
	private String ck_ckey;
	
	private String ck_ckeytwo;
	
	private Long informtypeid;
	
	private String informtypeidall;
	
	private Long docid;//知识id
	
	private Integer informdescnum;
	
	public Integer getInformdescnum() {
		return informdescnum;
	}
	public void setInformdescnum(Integer informdescnum) {
		this.informdescnum = informdescnum;
	}
	public Long getDocid() {
		return docid;
	}
	public void setDocid(Long docid) {
		this.docid = docid;
	}
	//常用面板集合
	private List<IrpInformType> irpInformTypes;
	
	public List<IrpInformType> getIrpInformTypes() {
		return irpInformTypes;
	}
	public void setIrpInformTypes(List<IrpInformType> irpInformTypes) {
		this.irpInformTypes = irpInformTypes;
	}
	public String getInformtypeidall() {
		return informtypeidall;
	}
	public void setInformtypeidall(String informtypeidall) {
		this.informtypeidall = informtypeidall;
	}
	public Long getInformtypeid() {
		return informtypeid;
	}
	public void setInformtypeid(Long informtypeid) {
		this.informtypeid = informtypeid;
	}
	public String getCk_ckey() {
		return ck_ckey;
	}
	public void setCk_ckey(String ck_ckey) {
		this.ck_ckey = ck_ckey;
	}
	public String getCk_ckeytwo() {
		return ck_ckeytwo;
	}
	public void setCk_ckeytwo(String ck_ckeytwo) {
		this.ck_ckeytwo = ck_ckeytwo;
	}
	public IrpInformType getIrpInformType() {
		return irpInformType;
	}
	public void setIrpInformType(IrpInformType irpInformType) {
		this.irpInformType = irpInformType;
	}
	public IrpInformTypeService getIrpInformTypeService() {
		return irpInformTypeService;
	}
	public void setIrpInformTypeService(IrpInformTypeService irpInformTypeService) {
		this.irpInformTypeService = irpInformTypeService;
	}
	public List<IrpInformType> getIrpInformTypelist() {
		return irpInformTypelist;
	}
	public void setIrpInformTypelist(List<IrpInformType> irpInformTypelist) {
		this.irpInformTypelist = irpInformTypelist;
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
	/**
	 * 查看全部
	 * @return
	 */
	public String setReportKind(){
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "informtypeid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = this.irpInformTypeService.findAllIrpInformTypeCount(searchWord,searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				configsize);
		this.irpInformTypelist = this.irpInformTypeService.findAllIrpInformTypePage(pageUtil,
				_oOrderby,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 增加举报种类配置
	 */
	public void setAddInformType(){
		int _cataStatus = this.irpInformTypeService.addInformType(irpInformType);
		ActionUtil.writer(_cataStatus+"");
	}
	/**
	 * 查看key值是否存在
	 */
	public void setCheckInform(){
		
		if (this.ck_ckey.trim().equals(this.ck_ckeytwo.trim())) {
			ActionUtil.writer("true");
		} else {
			int _ckeyStatus = this.irpInformTypeService.searchInformType(this.ck_ckey.trim());
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
	 * 根据id删除某个举报种类配置的记录
	 */
	public void deleteInformByTypeId(){
		
		int _deletecata  = this.irpInformTypeService.deleteInformTypeById(informtypeid);
		ActionUtil.writer("" + _deletecata);
	}
	/**
	 *加载修改某个举报种类配置的记录框
	 * @return
	 */
	public String updateInformByTypeId(){
		irpInformType = this.irpInformTypeService.irpInformType(informtypeid);
		return SUCCESS;
	}
	/**
	 *  根据id修改某个举报种类配置的记录
	 */
	public void  updateSetInformTypeById(){
     int _updateCataStat =this.irpInformTypeService.updateInformType(informtypeid, irpInformType);
     ActionUtil.writer(_updateCataStat+"");
	}
	/**
	 * 根据id多选删除
	 */
	public void deleteInformByTypeAll(){
		
		String[] configidalls= this.irpInformTypeService.configIdAll(informtypeidall);
		int date=0;
		for (int i = 0; i < configidalls.length; i++) {
			
			date=this.irpInformTypeService.deleteInformTypeById(Long.parseLong(configidalls[i]));
		}
		
		ActionUtil.writer(""+date);
		
		
	}
	/**
	 * 查询加精理由配置
	 */
	public String findMyJiaMenuBypage(){
		if(this.pageNum<=0){
			this.pageNum=1;
		}
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "informtypeid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = irpInformTypeService.findIrpInformTypeProjectMenuCount(searchWord, searchType,IrpInformType.JIAJING_PEIZHI);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),configsize);
		irpInformTypelist=irpInformTypeService.findOffenMenuIrpInformType(pageUtil,IrpInformType.JIAJING_PEIZHI,_oOrderby,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		informdescnum=SysConfigUtil.getSysConfigNumValue("JIAJING_MESSAGE_AMOUNT");
		return SUCCESS;
	}
	/**
	 * 查询项目
	 */
	public String findMyProjectMenuBypage(){
		if(this.pageNum<=0){
			this.pageNum=1;
		}
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "informtypeid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = irpInformTypeService.findIrpInformTypeProjectMenuCount(searchWord, searchType,IrpInformType.PROJECT_PEIZHI);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),configsize);
		irpInformTypelist=irpInformTypeService.findOffenMenuIrpInformType(pageUtil,IrpInformType.PROJECT_PEIZHI,_oOrderby,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/***
	 * 查询我的常用面板分页
	 * @return
	 */
	public String findMyoffenMenuBypage(){
		if(this.pageNum<=0){
			this.pageNum=1;
		}
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "informtypeid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = irpInformTypeService.findIrpInformTypeProjectMenuCount(searchWord,searchType,IrpInformType.REPORT_TYPE_offen);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),configsize);
		irpInformTypelist=irpInformTypeService.findOffenMenuIrpInformType(pageUtil,IrpInformType.REPORT_TYPE_offen,_oOrderby,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	//查询我的常用面板
	public String findMyoffenMenu(){
		irpInformTypelist=irpInformTypeService.findAllIrpInformType(IrpInformType.REPORT_TYPE_offen);
		return SUCCESS;
	}
	//前台查询反馈类型
	public String findComplaytype(){
		irpInformTypelist=irpInformTypeService.findAllIrpInformType(IrpInformType.REPORT_TYPE_COMPLAINTYPE);
		return SUCCESS;
	}
	//后台分页显示
	public String findComplaytypeBypage(){
		if(this.pageNum<=0){
			this.pageNum=1;
		}
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "informtypeid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = irpInformTypeService.findIrpInformTypeProjectMenuCount(searchWord,searchType,IrpInformType.REPORT_TYPE_COMPLAINTYPE);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),configsize);
		irpInformTypelist=irpInformTypeService.findOffenMenuIrpInformType(pageUtil,IrpInformType.REPORT_TYPE_COMPLAINTYPE,_oOrderby,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	//查询导出列表
	public String findExport(){
		if(this.pageNum<=0){
			this.pageNum=1;
		}
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "informtypeid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		int configsize = irpInformTypeService.findIrpInformTypeProjectMenuCount(searchWord,searchType,IrpInformType.REPORT_TYPE_EXPORT);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),configsize);
		irpInformTypelist=irpInformTypeService.findOffenMenuIrpInformType(pageUtil,IrpInformType.REPORT_TYPE_EXPORT,_oOrderby,searchWord,searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 查看key值是否存在
	 */
	public void checkInfokey(){
		//判断是添加还是修改  
		if(this.ck_ckey.length()<=0){
			//添加
			int _ckeyStatus = this.irpInformTypeService.searchInformType(this.ck_ckeytwo.trim());
			if(_ckeyStatus==1){
				ActionUtil.writer("false");
			}else{
				ActionUtil.writer("true");
			}
		}else{
			//修改
			if(ck_ckey.equals(ck_ckeytwo)){
				ActionUtil.writer("true");
			}else{
				int _ckeyStatus = this.irpInformTypeService.searchInformType(this.ck_ckeytwo.trim());
				if(_ckeyStatus==1){
					ActionUtil.writer("false");
				}else{
					ActionUtil.writer("true");
				}
			}
			
		}
	}
}
