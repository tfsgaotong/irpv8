package com.tfs.irp.doc_tag.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.doc_tag.service.IrpDocTagService;

public class DocTagAction extends ActionSupport {
	private IrpDocTagService irpDocTagService;

	public IrpDocTagService getIrpDocTagService() {
		return irpDocTagService;
	}

	public void setIrpDocTagService(IrpDocTagService irpDocTagService) {
		this.irpDocTagService = irpDocTagService;
	}
	
	

}
