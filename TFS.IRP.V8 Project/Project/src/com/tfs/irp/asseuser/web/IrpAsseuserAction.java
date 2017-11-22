package com.tfs.irp.asseuser.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.asseuser.service.IrpAsseuserService;

public class IrpAsseuserAction extends ActionSupport {
	private IrpAsseuserService asseuserService;

	public IrpAsseuserService getAsseuserService() {
		return asseuserService;
	}

	public void setAsseuserService(IrpAsseuserService asseuserService) {
		this.asseuserService = asseuserService;
	}
	
}
