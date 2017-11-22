package com.tfs.irp.docversion.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.docversion.service.IrpDocversionService;

public class IrpDocversionAction extends ActionSupport {

	
	private IrpDocversionService irpDocversionService;

	public IrpDocversionService getIrpDocversionService() {
		return irpDocversionService;
	}

	public void setIrpDocversionService(IrpDocversionService irpDocversionService) {
		this.irpDocversionService = irpDocversionService;
	}
}
