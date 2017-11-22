package com.tfs.irp.category_file_link.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.category_file_link.service.IrpCategoryFileLinkService;

public class IrpCategoryFileLinkAction extends ActionSupport{
	private IrpCategoryFileLinkService irpCategoryFileLinkService;

	public IrpCategoryFileLinkService getIrpCategoryFileLinkService() {
		return irpCategoryFileLinkService;
	}

	public void setIrpCategoryFileLinkService(
			IrpCategoryFileLinkService irpCategoryFileLinkService) {
		this.irpCategoryFileLinkService = irpCategoryFileLinkService;
	}
	
}
