package com.tfs.irp.assewarn.service.impl;

import java.util.List;

import com.tfs.irp.assewarn.dao.IrpAssewarnDAO;
import com.tfs.irp.assewarn.entity.IrpAssewarn;
import com.tfs.irp.assewarn.entity.IrpAssewarnExample;
import com.tfs.irp.assewarn.service.IrpAssewarnService;

public class IrpAssewarnServiceImpl implements IrpAssewarnService {
	private IrpAssewarnDAO assewarnDAO;

	public IrpAssewarnDAO getAssewarnDAO() {
		return assewarnDAO;
	}

	public void setAssewarnDAO(IrpAssewarnDAO assewarnDAO) {
		this.assewarnDAO = assewarnDAO;
	}

	@Override
	public List<IrpAssewarn> selectByExample(IrpAssewarnExample example)
			throws Exception {
		return assewarnDAO.selectByExample(example);
	}

	@Override
	public IrpAssewarn SelectByPrimaryKey(Long id) throws Exception {
		return assewarnDAO.selectByPrimaryKey(id);
	}

	
	
}
