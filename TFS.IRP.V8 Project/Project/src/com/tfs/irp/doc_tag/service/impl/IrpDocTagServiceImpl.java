package com.tfs.irp.doc_tag.service.impl;

import com.tfs.irp.doc_tag.dao.IrpDocTagDAO;
import com.tfs.irp.doc_tag.service.IrpDocTagService;

public class IrpDocTagServiceImpl implements IrpDocTagService {
	private IrpDocTagDAO irpDocTagDAO;

	public IrpDocTagDAO getIrpDocTagDAO() {
		return irpDocTagDAO;
	}

	public void setIrpDocTagDAO(IrpDocTagDAO irpDocTagDAO) {
		this.irpDocTagDAO = irpDocTagDAO;
	}
	
	
	
}
