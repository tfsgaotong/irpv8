package com.tfs.irp.userprivacy.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.userprivacy.dao.IrpUserPrivacyDAO;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacyExample;
import com.tfs.irp.userprivacy.service.IrpUserPrivacyService;

public class IrpUserPrivacyServiceImpl implements IrpUserPrivacyService {

	private IrpUserPrivacyDAO irpUserPrivacyDAO;

	public IrpUserPrivacyDAO getIrpUserPrivacyDAO() {
		return irpUserPrivacyDAO;
	}

	public void setIrpUserPrivacyDAO(IrpUserPrivacyDAO irpUserPrivacyDAO) {
		this.irpUserPrivacyDAO = irpUserPrivacyDAO;
	}

	@Override
	public int addUserPrivacy(Long _userid, String _privacytype,
			Integer _privacyvalue) {
		int nStatus = 0; 
		
		IrpUserPrivacy record = new IrpUserPrivacy();
		
		record.setUserid(_userid);
		record.setPrivacytype(_privacytype);
		record.setPrivacyvalue(_privacyvalue);
		
		try {
			this.irpUserPrivacyDAO.insert(record);
			nStatus = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nStatus;
	}

	@Override
	public IrpUserPrivacy irpUserPrivacy(Long _userid, String _privacytype) {
		IrpUserPrivacy irpUserPrivacy = null;
		IrpUserPrivacyExample example = new IrpUserPrivacyExample();
		example.createCriteria().andUseridEqualTo(_userid).andPrivacytypeEqualTo(_privacytype);
		try {
			if(this.irpUserPrivacyDAO.selectByExample(example).size()>0){
				irpUserPrivacy = this.irpUserPrivacyDAO.selectByExample(example).get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return irpUserPrivacy;
	}

	@Override
	public int updateIrpUserPrivacy(Long _userid, String _privacytype,Integer _privacyvalue) {
		int nStatus = 0;
		IrpUserPrivacyExample example = new IrpUserPrivacyExample();
		example.createCriteria().andUseridEqualTo(_userid).andPrivacytypeEqualTo(_privacytype);
		IrpUserPrivacy record = new IrpUserPrivacy();
		record.setPrivacyvalue(_privacyvalue);
		try {
			nStatus = this.irpUserPrivacyDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nStatus;
	}

	@Override
	public void judgePrivacyIfEx(Long _userId, String type) {
		IrpUserPrivacyExample example = new IrpUserPrivacyExample();
		example.createCriteria().andUseridEqualTo(_userId).andPrivacytypeEqualTo(type);
		try {
			List list = irpUserPrivacyDAO.selectByExample(example);
			if(list==null || list.size()==0){
				IrpUserPrivacy record = new IrpUserPrivacy();
				record.setPrivacytype(type);
				record.setUserid(_userId);
				record.setPrivacyvalue(IrpUserPrivacy.MICROBLOGVALUE);
				irpUserPrivacyDAO.insertSelective(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
