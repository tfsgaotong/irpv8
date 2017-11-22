package com.tfs.irp.assewarn.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.assewarn.service.IrpAssewarnService;

public class IrpAssewarnAction extends ActionSupport {
	private IrpAssewarnService assewarnService;

	public IrpAssewarnService getAssewarnService() {
		return assewarnService;
	}

	public void setAssewarnService(IrpAssewarnService assewarnService) {
		this.assewarnService = assewarnService;
	}
	
}
