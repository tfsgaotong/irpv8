package com.tfs.irp.leaveoper.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.leaveoper.service.IrpLeaveoperService;

public class IrpLeaveoperAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IrpLeaveoperService irpLeaveoperService;
	public IrpLeaveoperService getIrpLeaveoperService() {
		return irpLeaveoperService;
	}
	public void setIrpLeaveoperService(IrpLeaveoperService irpLeaveoperService) {
		this.irpLeaveoperService = irpLeaveoperService;
	}

}
