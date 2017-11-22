package com.tfs.irp.assetype.service.impl;

import java.util.List;

import com.tfs.irp.assetype.dao.IrpAssetypeDAO;
import com.tfs.irp.assetype.entity.IrpAssetype;
import com.tfs.irp.assetype.entity.IrpAssetypeExample;
import com.tfs.irp.assetype.service.IrpAssetypeService;

public class IrpAssetypeServiceImpl implements IrpAssetypeService {
	private IrpAssetypeDAO assetypeDAO;

	public IrpAssetypeDAO getAssetypeDAO() {
		return assetypeDAO;
	}

	public void setAssetypeDAO(IrpAssetypeDAO assetypeDAO) {
		this.assetypeDAO = assetypeDAO;
	}

	@Override
	public List<IrpAssetype> selectByExample(IrpAssetypeExample example)
			throws Exception {
		return assetypeDAO.selectByExample(example);
	}

	@Override
	public IrpAssetype selectByPrimaryKey(Long id) throws Exception {
		return assetypeDAO.selectByPrimaryKey(id);
	}
	
}
