package com.tfs.irp.configType.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.configType.dao.IrpConfigTypeDAO;
import com.tfs.irp.configType.entity.IrpConfigType;
import com.tfs.irp.configType.entity.IrpConfigTypeExample;
import com.tfs.irp.configType.entity.IrpConfigTypeExample.Criteria;
import com.tfs.irp.configType.service.IrpConfigTypeService;
import com.tfs.irp.util.PageUtil;

public class IrpConfigTypeServiceImpl implements IrpConfigTypeService {

	private IrpConfigTypeDAO irpConfigTypeDAO;
	
	
	
	public IrpConfigTypeDAO getIrpConfigTypeDAO() {
		return irpConfigTypeDAO;
	}

	public void setIrpConfigTypeDAO(IrpConfigTypeDAO irpConfigTypeDAO) {
		this.irpConfigTypeDAO = irpConfigTypeDAO;
	}

	@Override
	public List<IrpConfigType> findAllIrpConfigTypeOfPage(PageUtil pageUtil,
			String _orderby) {
		// TODO Auto-generated method stub
		List<IrpConfigType> irpconfigtypelist=null;
		IrpConfigTypeExample example=new IrpConfigTypeExample();
		example.setOrderByClause(_orderby);
		try {
			
			irpconfigtypelist=this.irpConfigTypeDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpconfigtypelist;
	}

	@Override
	public Integer IrpConfigTypeCount() {
		// TODO Auto-generated method stub
		int configtypecount=0;
		IrpConfigTypeExample example=new IrpConfigTypeExample();
		try {
			configtypecount=this.irpConfigTypeDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return configtypecount;
	}

}
