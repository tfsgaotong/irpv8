package com.tfs.irp.education.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.education.entity.IrpEducation;
import com.tfs.irp.education.service.IrpEducationService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;

public class EducationAction extends ActionSupport {
	private IrpEducationService irpEducationService;
	
	private List<IrpEducation> educations;
	
	private IrpEducation irpEducation;
	
	private List<String> startYears;
	
	private Map<Short, String> schoolTypeInfos;
	
	private long educationid;

	public long getEducationid() {
		return educationid;
	}

	public void setEducationid(long educationid) {
		this.educationid = educationid;
	}

	public Map<Short, String> getSchoolTypeInfos() {
		return schoolTypeInfos;
	}

	public void setSchoolTypeInfos(Map<Short, String> schoolTypeInfos) {
		this.schoolTypeInfos = schoolTypeInfos;
	}

	public List<String> getStartYears() {
		return startYears;
	}

	public void setStartYears(List<String> startYears) {
		this.startYears = startYears;
	}

	public IrpEducation getIrpEducation() {
		return irpEducation;
	}

	public void setIrpEducation(IrpEducation irpEducation) {
		this.irpEducation = irpEducation;
	}

	public List<IrpEducation> getEducations() {
		return educations;
	}

	public void setEducations(List<IrpEducation> educations) {
		this.educations = educations;
	}

	public IrpEducationService getIrpEducationService() {
		return irpEducationService;
	}

	public void setIrpEducationService(IrpEducationService irpEducationService) {
		this.irpEducationService = irpEducationService;
	}
	
	/**
	 * 用户教育信息
	 * @return
	 */
	public String userEdu() {
		//获得用户教育信息
		educations = irpEducationService.findEducationByUserId(LoginUtil.getLoginUserId());
		//初始化
		initInfo();
		return SUCCESS;
	}
	
	/**
	 * 初始化参数
	 */
	private void initInfo(){
		startYears = new ArrayList<String>();
		for(int i=Calendar.getInstance().get(Calendar.YEAR);i>=1900;i--){
			startYears.add(String.valueOf(i));
		}
		//初始化学校类型
		schoolTypeInfos = new HashMap<Short, String>();
		schoolTypeInfos.put(Short.parseShort("1"), "博士");
		schoolTypeInfos.put(Short.parseShort("2"), "研究生");
		schoolTypeInfos.put(Short.parseShort("3"), "大学");
		schoolTypeInfos.put(Short.parseShort("4"), "高中");
		schoolTypeInfos.put(Short.parseShort("5"), "中专技校");
		schoolTypeInfos.put(Short.parseShort("6"), "初中");
		schoolTypeInfos.put(Short.parseShort("7"), "小学");
	}
	
	/**
	 * 处理编辑用户教育信息
	 */
	public void userEduDowith() {
		long nEduId = irpEducationService.educationEdit(irpEducation);
		ActionUtil.writer(nEduId>0?"1":"0");
	}
	
	/**
	 * 删除教育信息
	 */
	public void deleteEdu() {
		int nCount = irpEducationService.deleteEduById(educationid);
		ActionUtil.writer(nCount>0?"1":"0");
	}
	
	/**
	 * 编辑用户教育信息
	 */
	public String userEduEdit() {
		irpEducation = irpEducationService.findEducationById(educationid);
		initInfo();
		return SUCCESS;
	}
}
