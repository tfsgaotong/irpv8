package com.tfs.irp.asseuser.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.asseuser.dao.IrpAsseuserDAO;
import com.tfs.irp.asseuser.entity.IrpAsseuser;
import com.tfs.irp.asseuser.entity.IrpAsseuserExample;
import com.tfs.irp.asseuser.service.IrpAsseuserService;

public class IrpAsseuserServiceImpl implements IrpAsseuserService {
	private IrpAsseuserDAO asseuserDAO;

	public IrpAsseuserDAO getAsseuserDAO() {
		return asseuserDAO;
	}

	public void setAsseuserDAO(IrpAsseuserDAO asseuserDAO) {
		this.asseuserDAO = asseuserDAO;
	}

	@Override
	public void add(IrpAsseuser _asseuser) throws Exception {
		
		asseuserDAO.insertSelective(_asseuser);
	}

	@Override
	public void deleteByApplyid(IrpAsseuserExample example) throws Exception {
		asseuserDAO.deleteByExample(example);
	}

	@Override
	public List<IrpAsseuser> selectByuserid(IrpAsseuserExample example)
			throws Exception {
		return asseuserDAO.selectByExample(example);
	}

	@Override
	public List<IrpAsseuser> selectByApplyid(IrpAsseuserExample example)
			throws Exception {
		return asseuserDAO.selectByExample(example);
	}

	@Override
	public List<IrpAsseuser> getIrpAsseuserByAssId(Long _assid,
			Integer _status) {
		// TODO Auto-generated method stub
		List<IrpAsseuser> list = null;
		IrpAsseuserExample example = new IrpAsseuserExample();
		example.createCriteria().andAsseroomapplyidEqualTo(_assid).andAsseruserstatusEqualTo(_status);
		
		try {
			list = this.asseuserDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}
