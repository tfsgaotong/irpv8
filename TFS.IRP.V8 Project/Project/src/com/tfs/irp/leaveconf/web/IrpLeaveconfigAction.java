package com.tfs.irp.leaveconf.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.leaveconf.entity.IrpLeaveconfig;
import com.tfs.irp.leaveconf.service.IrpLeaveconfigService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.PageUtil;

public class IrpLeaveconfigAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IrpLeaveconfigService irpLeaveconfigService;
	private int pageSize=10;
	private Long leaveconfigid;
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private int pageNum;
	private Integer marking;
	private String pageHTML = "";
	public Integer getMarking() {
		return marking;
	}
	private IrpLeaveconfig irpLeaveconfig;
	public void setMarking(Integer marking) {
		this.marking = marking;
	}

	public IrpLeaveconfigService getIrpLeaveconfigService() {
		return irpLeaveconfigService;
	}

	public void setIrpLeaveconfigService(IrpLeaveconfigService irpLeaveconfigService) {
		this.irpLeaveconfigService = irpLeaveconfigService;
	}

	private List<IrpLeaveconfig>  irpLeaveconfigs;

	public List<IrpLeaveconfig> getIrpLeaveconfigs() {
		return irpLeaveconfigs;
	}

	public void setIrpLeaveconfigs(List<IrpLeaveconfig> irpLeaveconfigs) {
		this.irpLeaveconfigs = irpLeaveconfigs;
	}
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}
	public IrpLeaveconfig getIrpLeaveconfig() {
		return irpLeaveconfig;
	}

	public void setIrpLeaveconfig(IrpLeaveconfig irpLeaveconfig) {
		this.irpLeaveconfig = irpLeaveconfig;
	}
	public Long getLeaveconfigid() {
		return leaveconfigid;
	}

	public void setLeaveconfigid(Long leaveconfigid) {
		this.leaveconfigid = leaveconfigid;
	}
	private String leaveconfigids;
	

	public String getLeaveconfigids() {
		return leaveconfigids;
	}

	public void setLeaveconfigids(String leaveconfigids) {
		this.leaveconfigids = leaveconfigids;
	}
	private String searchWord;
	private String searchType;
	private String orderField;
	private String orderBy;
	
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
	 * 获得请假表的所有数据
	 */
	public String leaveConfigList() {
		irpLeaveconfigs=this.irpLeaveconfigService.getAllList();
		return SUCCESS;
	}
	/**
	 * 后台配置列表
	 */
	public String allleaveConfigList() {
		int count = this.irpLeaveconfigService.irpLeaveConfigCountByMarking(marking);
		PageUtil page = new PageUtil(pageNum, pageSize, count);
		irpLeaveconfigs=this.irpLeaveconfigService.getAllByMarking(orderBy,orderField,searchWord,searchType,page,marking);
		this.pageHTML = page.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 跳转添加表单
	 */
	public String toAddFrom(){
		return SUCCESS;
	}
	/**
	 * 添加配置
	 */
	public void addLeaveConfig(){
		irpLeaveconfig.setLeavemarking(marking);
		int msg = this.irpLeaveconfigService.addLeaveConfig(irpLeaveconfig);
		ActionUtil.writer(msg+"");
	}
	/**
	 * 删除
	 */
	public void delLeaveConfig(){
		int msg = this.irpLeaveconfigService.delLeaveConfig(leaveconfigid);
		ActionUtil.writer(msg+"");
	}
	/**
	 * 多选删除
	 */
	public void delSelAllLeaveConfig(){
		int msg = 0;
		String[] leaveconfigidall = leaveconfigids.split(",");
		if(leaveconfigidall.length>0){
			try {
				for(int i=0;i<leaveconfigidall.length;i++){
					
					this.irpLeaveconfigService.delLeaveConfig(Long.valueOf(leaveconfigidall[i]));
				}
				msg = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ActionUtil.writer(msg+"");
	}
	/**
	 * 获取修改的配置信息
	 */
	public String getLeaveConfigInfo(){
		irpLeaveconfig = irpLeaveconfigService.selLeaveConfigById(leaveconfigid);
		return SUCCESS;
	}
	/**
	 * 修改的配置信息
	 */
	public void upLeaveConfigInfo(){
		int msg  = 0;
		msg = irpLeaveconfigService.upLeaveConfigInfo(irpLeaveconfig, leaveconfigid);
		ActionUtil.writer(msg+"");
	}

	
}
