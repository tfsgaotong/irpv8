package com.tfs.irp.assetype.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.assetype.service.IrpAssetypeService;

public class IrpAssetypeAction extends ActionSupport {
	private IrpAssetypeService assetypeService;

	public IrpAssetypeService getAssetypeService() {
		return assetypeService;
	}

	public void setAssetypeService(IrpAssetypeService assetypeService) {
		this.assetypeService = assetypeService;
	}
	
}
