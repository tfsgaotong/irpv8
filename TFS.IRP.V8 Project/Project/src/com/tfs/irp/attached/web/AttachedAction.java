package com.tfs.irp.attached.web;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.util.SysFileUtil;

public class AttachedAction extends ActionSupport {

	private IrpAttachedService irpAttachedService;

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	private String sname;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String exitZoom() throws Exception {
		//获得文件路径
		String sFilePath = SysFileUtil.getFilePathByFileName(sname);
		File file = new File(sFilePath);
		if(file.isFile() && file.exists()){
			this.setMessage("yes");
		}else{
			this.setMessage("no");
		} 
		return SUCCESS;
	}

}
