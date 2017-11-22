package com.tfs.irp.apptype.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.apptype.dao.IrpApptypeDAO;
import com.tfs.irp.apptype.entity.IrpApptype;
import com.tfs.irp.apptype.entity.IrpApptypeExample;
import com.tfs.irp.apptype.service.IrpApptypeService;

public class IrpApptypeServiceImpl implements IrpApptypeService{

	private IrpApptypeDAO irpApptypeDAO;
	
	public IrpApptypeDAO getIrpApptypeDAO() {
		return irpApptypeDAO;
	}

	public void setIrpApptypeDAO(IrpApptypeDAO irpApptypeDAO) {
		this.irpApptypeDAO = irpApptypeDAO;
	}

	@Override
	public List<IrpApptype> findAllapptype() {
		List<IrpApptype> list=null;
		IrpApptypeExample example=null;
		try {
			list= irpApptypeDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
