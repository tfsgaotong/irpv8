package com.tfs.irp.asseroomsblink.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.asseroomsb.service.IrpAsseroomsbService;

public class AsseroomsblinkAction extends ActionSupport {
	private IrpAsseroomsbService asseroomsbService;

	public IrpAsseroomsbService getAsseroomsbService() {
		return asseroomsbService;
	}

	public void setAsseroomsbService(IrpAsseroomsbService asseroomsbService) {
		this.asseroomsbService = asseroomsbService;
	}
	
}
