package com.tfs.irp.career.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.career.entity.IrpCareer;
import com.tfs.irp.career.service.IrpCareerService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;

public class CareerAction extends ActionSupport {
	private IrpCareerService irpCareerService;
	
	private List<IrpCareer> irpCareers;
	
	private IrpCareer irpCareer;
	
	private long careerId;
	
	private List<String> years;

	public List<IrpCareer> getIrpCareers() {
		return irpCareers;
	}

	public void setIrpCareers(List<IrpCareer> irpCareers) {
		this.irpCareers = irpCareers;
	}

	public IrpCareer getIrpCareer() {
		return irpCareer;
	}

	public void setIrpCareer(IrpCareer irpCareer) {
		this.irpCareer = irpCareer;
	}

	public long getCareerId() {
		return careerId;
	}

	public void setCareerId(long careerId) {
		this.careerId = careerId;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public IrpCareerService getIrpCareerService() {
		return irpCareerService;
	}

	public void setIrpCareerService(IrpCareerService irpCareerService) {
		this.irpCareerService = irpCareerService;
	}
	
	/**
	 * 用户职业信息
	 * @return
	 */
	public String userCareer() {
		//获得用户职业信息
		irpCareers = irpCareerService.findCareerByUserId(LoginUtil.getLoginUserId());
		//初始化
		initInfo();
		return SUCCESS;
	}
	
	/**
	 * 编辑用户职业信息
	 * @return
	 */
	public String careerEdit() {
		irpCareer = irpCareerService.findCareerById(careerId);
		initInfo();
		return SUCCESS;
	}
	
	/**
	 * 处理编辑用户职业信息
	 */
	public void careerEditDowith() {
		long nCareerId = irpCareerService.careerEdit(irpCareer);
		ActionUtil.writer(nCareerId>0?"1":"0");
	}
	
	/**
	 * 删除职业信息
	 */
	public void deleteCareer() {
		int nCount = irpCareerService.deleteCareerById(careerId);
		ActionUtil.writer(nCount>0?"1":"0");
	}
	
	/**
	 * 初始化参数
	 */
	private void initInfo(){
		years = new ArrayList<String>();
		for(int i=Calendar.getInstance().get(Calendar.YEAR);i>=1900;i--){
			years.add(String.valueOf(i));
		}
	}
}
