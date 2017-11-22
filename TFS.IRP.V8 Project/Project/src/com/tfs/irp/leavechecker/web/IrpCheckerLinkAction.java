package com.tfs.irp.leavechecker.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.leavechecker.service.IrpCheckerLinkService;

public class IrpCheckerLinkAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IrpCheckerLinkService  irpCheckerLinkService;
	
	public IrpCheckerLinkService getIrpCheckerLinkService() {
		return irpCheckerLinkService;
	}

	public void setIrpCheckerLinkService(IrpCheckerLinkService irpCheckerLinkService) {
		this.irpCheckerLinkService = irpCheckerLinkService;
	}


}
