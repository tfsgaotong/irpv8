package com.tfs.irp.navigation.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.navigation.entity.IrpUserNavigation;
import com.tfs.irp.navigation.service.IrpUserNavigationService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;

public class IrpUserNavigationAction extends ActionSupport {

	private IrpUserNavigationService irpUserNavigationService;
	
	private IrpUserNavigation irpUserNavigation;
	
	private String pageHTML;
	
	private int pageNum = 1;
	
	private int pageSize = 10;
	
	private String navigationvalue;
	
	private Long navigationid;
	
	private List<IrpUserNavigation> irpUserNavigationlist;
	
	private String navigationname;
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getNavigationid() {
		return navigationid;
	}

	public void setNavigationid(Long navigationid) {
		this.navigationid = navigationid;
	}

	public String getNavigationname() {
		return navigationname;
	}

	public void setNavigationname(String navigationname) {
		this.navigationname = navigationname;
	}

	public String getNavigationvalue() {
		return navigationvalue;
	}

	public void setNavigationvalue(String navigationvalue) {
		this.navigationvalue = navigationvalue;
	}

	public List<IrpUserNavigation> getIrpUserNavigationlist() {
		return irpUserNavigationlist;
	}

	public void setIrpUserNavigationlist(
			List<IrpUserNavigation> irpUserNavigationlist) {
		this.irpUserNavigationlist = irpUserNavigationlist;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public IrpUserNavigation getIrpUserNavigation() {
		return irpUserNavigation;
	}

	public void setIrpUserNavigation(IrpUserNavigation irpUserNavigation) {
		this.irpUserNavigation = irpUserNavigation;
	}

	public IrpUserNavigationService getIrpUserNavigationService() {
		return irpUserNavigationService;
	}

	public void setIrpUserNavigationService(
			IrpUserNavigationService irpUserNavigationService) {
		this.irpUserNavigationService = irpUserNavigationService;
	}
	/**
	 * 获得我的导航信息
	 * @return
	 */
	public String getMyNavigationInfo(){
		
		
		int count = this.irpUserNavigationService.findUserNavigationOfAllCount(IrpUserNavigation.NAVI_STATUS_NORMAL);
		PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize, count);
		
		irpUserNavigationlist = this.irpUserNavigationService.findUserNavigationOfAll(IrpUserNavigation.NAVI_STATUS_NORMAL, "crtime desc", pageUtil);
		
		return SUCCESS;
	}
	/**
	 * 添加导航弹出框内容
	 * @return
	 */
	public String getUserNavigationInfoAdd(){
		
		irpUserNavigation = new IrpUserNavigation();
		
		return SUCCESS;
	}
	public String getUserNavigationInfoUpdate(){
		
		irpUserNavigation = this.irpUserNavigationService.getIrpUserNavigationById(navigationid);
		
		return SUCCESS;
	}
	/**
	 * 增加导航地址
	 * @return
	 */
	public void userNavigationAdd(){
		int msg = 0;
		
		msg = this.irpUserNavigationService.addUserNavigation(navigationname,navigationvalue);
		 
		ActionUtil.writer(msg+"");
	}
	/**
	 * 删除导航地址
	 * @return
	 */
	public void userNavigationDelete(){
		int msg = 0;
		IrpUserNavigation irpUserNavigations = this.irpUserNavigationService.getIrpUserNavigationById(navigationid);
		if(irpUserNavigations.getUserid()==LoginUtil.getLoginUserId() || LoginUtil.getLoginUser().isMicroblogManager()){
			msg = this.irpUserNavigationService.deleteUserNavigation(navigationid);
		}
		ActionUtil.writer(msg+"");
	}
	/**
	 * 修改导航地址
	 * @return
	 */
	public void userNavigationUpdate(){
		int msg = 0;
		IrpUserNavigation irpUserNavigations = this.irpUserNavigationService.getIrpUserNavigationById(navigationid);
		if(irpUserNavigations.getUserid()==LoginUtil.getLoginUserId() || LoginUtil.getLoginUser().isMicroblogManager()){
		msg = this.irpUserNavigationService.updateUserNavigation(navigationid, navigationname, navigationvalue);
		}
		ActionUtil.writer(msg+"");
	}
	/**
	 * 全部编辑导航地址
	 * @return
	 */
	public String getUserNavigationAllEdit(){
		
		int count = this.irpUserNavigationService.findUserNavigationOfAllCount(IrpUserNavigation.NAVI_STATUS_NORMAL);
		
		PageUtil pageUtilNavigation = new PageUtil(this.pageNum,this.pageSize, count);
		
		irpUserNavigationlist = this.irpUserNavigationService.findUserNavigationOfAll(IrpUserNavigation.NAVI_STATUS_NORMAL, "crtime desc", pageUtilNavigation);

		this.pageHTML = pageUtilNavigation.getClientPageHtml("pageNavigain(#PageNum#)");
		
		return SUCCESS;
	}
	
}
