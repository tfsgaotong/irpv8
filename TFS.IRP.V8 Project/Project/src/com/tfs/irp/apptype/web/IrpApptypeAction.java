package com.tfs.irp.apptype.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.apptype.entity.IrpApptype;
import com.tfs.irp.apptype.service.IrpApptypeService;

public class IrpApptypeAction extends ActionSupport{

	
	private IrpApptypeService irpApptypeService;

	private List<IrpApptype> irpApptypes;
	
	public List<IrpApptype> getIrpApptypes() {
		return irpApptypes;
	}

	public void setIrpApptypes(List<IrpApptype> irpApptypes) {
		this.irpApptypes = irpApptypes;
	}

	public IrpApptypeService getIrpApptypeService() {
		return irpApptypeService;
	}

	public void setIrpApptypeService(IrpApptypeService irpApptypeService) {
		this.irpApptypeService = irpApptypeService;
	}
	
	public String findAllapptype() throws Exception {
		irpApptypes=irpApptypeService.findAllapptype();
		return SUCCESS;
	}
}
