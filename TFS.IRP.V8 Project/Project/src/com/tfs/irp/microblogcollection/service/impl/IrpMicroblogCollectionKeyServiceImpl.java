package com.tfs.irp.microblogcollection.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.microblogcollection.dao.IrpMicroblogCollectionDAO;

import com.tfs.irp.microblogcollection.entity.IrpMicroblogCollectionExample;
import com.tfs.irp.microblogcollection.entity.IrpMicroblogCollectionExample.Criteria;
import com.tfs.irp.microblogcollection.entity.IrpMicroblogCollectionKey;
import com.tfs.irp.microblogcollection.service.IrpMicroblogCollectionKeyService;

public class IrpMicroblogCollectionKeyServiceImpl implements
		IrpMicroblogCollectionKeyService {
    private IrpMicroblogCollectionDAO irpMicroblogCollectionDAO;
	public IrpMicroblogCollectionDAO getIrpMicroblogCollectionDAO() {
		return irpMicroblogCollectionDAO;
	}
	public void setIrpMicroblogCollectionDAO(
			IrpMicroblogCollectionDAO irpMicroblogCollectionDAO) {
		this.irpMicroblogCollectionDAO = irpMicroblogCollectionDAO;
	}
	@Override
	public int addTfsMicroblogCollectionKey(long _nMicroblogid,long _nUserid) {
		// TODO Auto-generated method stub
		int nStatus = 0;
		IrpMicroblogCollectionKey irpMicroblogCollectionKey = new IrpMicroblogCollectionKey();
		irpMicroblogCollectionKey.setUserid(_nUserid);
		irpMicroblogCollectionKey.setMicroblogid(_nMicroblogid);
		try {
			this.irpMicroblogCollectionDAO.insertSelective(irpMicroblogCollectionKey);
			nStatus=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nStatus;
	}
	@Override
	public int deleteTfsMicroblogCollectionKey(long _nMicroblogid) {
		// TODO Auto-generated method stub
		int nStatus = 0;
		IrpMicroblogCollectionExample example = new IrpMicroblogCollectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andMicroblogidEqualTo(_nMicroblogid);
		try {
		nStatus=this.irpMicroblogCollectionDAO.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nStatus;
	}
	@Override
	public List selectMicroblogidOfUserid(Long _userid)
			throws SQLException {
		return irpMicroblogCollectionDAO.selectMicroblogidOfUserid(_userid);
	}
}
