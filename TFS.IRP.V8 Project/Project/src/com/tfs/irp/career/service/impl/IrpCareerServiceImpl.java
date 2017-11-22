package com.tfs.irp.career.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.career.dao.IrpCareerDAO;
import com.tfs.irp.career.entity.IrpCareer;
import com.tfs.irp.career.entity.IrpCareerExample;
import com.tfs.irp.career.service.IrpCareerService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpCareerServiceImpl implements IrpCareerService {
	private IrpCareerDAO irpCareerDAO;

	public IrpCareerDAO getIrpCareerDAO() {
		return irpCareerDAO;
	}

	public void setIrpCareerDAO(IrpCareerDAO irpCareerDAO) {
		this.irpCareerDAO = irpCareerDAO;
	}

	@Override
	public List<IrpCareer> findCareerByUserId(long _nUserId) {
		List<IrpCareer> list = null;
		IrpCareerExample example = new IrpCareerExample();
		example.createCriteria().andUseridEqualTo(_nUserId);
		try {
			list = irpCareerDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public long careerEdit(IrpCareer irpCareer) {
		long nCareerId = 0L;
		if(irpCareer.getCareerid()==null || irpCareer.getCareerid()==0){
			long nTempId = TableIdUtil.getNextId(IrpCareer.TABLE_NAME);
			irpCareer.setCareerid(nTempId);
			irpCareer.setCrtime(new Date());
			IrpUser loginUser = LoginUtil.getLoginUser();
			irpCareer.setUserid(loginUser.getUserid());
			irpCareer.setCruser(loginUser.getUsername());
			irpCareer.setCruserid(loginUser.getUserid());
			try {
				irpCareerDAO.insertSelective(irpCareer);
				nCareerId = nTempId;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				irpCareerDAO.updateByPrimaryKeySelective(irpCareer);
				nCareerId = irpCareer.getCareerid();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nCareerId;
	}

	@Override
	public int deleteCareerById(long _nCareerId) {
		int nCount = 0;
		try {
			nCount = irpCareerDAO.deleteByPrimaryKey(_nCareerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public IrpCareer findCareerById(long _nCareerId) {
		IrpCareer career = null;
		try {
			career = irpCareerDAO.selectByPrimaryKey(_nCareerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return career;
	}
}
