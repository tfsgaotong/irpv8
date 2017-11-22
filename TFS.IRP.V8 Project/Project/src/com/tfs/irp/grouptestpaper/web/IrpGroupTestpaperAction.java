package com.tfs.irp.grouptestpaper.web;

import java.util.Calendar;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.grouptestpaper.entity.IrpGroupTestpaper;
import com.tfs.irp.grouptestpaper.service.IrpGroupTestpaperService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpGroupTestpaperAction extends ActionSupport {
	
	private IrpGroupTestpaperService irpGroupTestpaperService;
	
	private String groupids;
	
	private Long paperid;

	public Long getPaperid() {
		return paperid;
	}

	public void setPaperid(Long paperid) {
		this.paperid = paperid;
	}

	public String getGroupids() {
		return groupids;
	}

	public void setGroupids(String groupids) {
		this.groupids = groupids;
	}

	public IrpGroupTestpaperService getIrpGroupTestpaperService() {
		return irpGroupTestpaperService;
	}

	public void setIrpGroupTestpaperService(
			IrpGroupTestpaperService irpGroupTestpaperService) {
		this.irpGroupTestpaperService = irpGroupTestpaperService;
	}
	/**
	 * 增加试卷对应的组的权限
	 * @return
	 */
	public void addTestPGroup(){
		int msg = 1;
		if (groupids!="") {
			if (paperid!=null) {
				this.irpGroupTestpaperService.deleteGPByTPId(paperid);
			}
			String []groupidsarray = this.groupids.split(",");
			for (int i = 0; i < groupidsarray.length; i++) {
				IrpGroupTestpaper irpGroupTestpaper = new IrpGroupTestpaper();
				irpGroupTestpaper.setGrouptpaperid(TableIdUtil.getNextId(irpGroupTestpaper));
				irpGroupTestpaper.setGroupid(Long.parseLong(groupidsarray[i]));
				irpGroupTestpaper.setTestpaperid(paperid);
				irpGroupTestpaper.setIsdel(IrpGroupTestpaper.ISDELNORMAL);
				irpGroupTestpaper.setCruserid(LoginUtil.getLoginUserId());
				irpGroupTestpaper.setCrtime(Calendar.getInstance().getTime());
				if (this.irpGroupTestpaperService.selectStatusOfGTId(Long.parseLong(groupidsarray[i]), paperid)==0) {
					msg = this.irpGroupTestpaperService.addIrpGroupTestpaper(irpGroupTestpaper);	
				}
				
			}
		}
		ActionUtil.writer(msg+"");
	}
	

	
	
}

