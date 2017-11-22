package com.tfs.irp.education.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.education.dao.IrpEducationDAO;
import com.tfs.irp.education.entity.IrpEducation;
import com.tfs.irp.education.entity.IrpEducationExample;
import com.tfs.irp.education.service.IrpEducationService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpEducationServiceImpl implements IrpEducationService {
	private IrpEducationDAO irpEducationDAO;

	public IrpEducationDAO getIrpEducationDAO() {
		return irpEducationDAO;
	}

	public void setIrpEducationDAO(IrpEducationDAO irpEducationDAO) {
		this.irpEducationDAO = irpEducationDAO;
	}
	
	@Override
	public List<IrpEducation> findEducationByUserId(long _nUserId) {
		List<IrpEducation> list = null;
		IrpEducationExample example = new IrpEducationExample();
		example.createCriteria().andUseridEqualTo(_nUserId);
		example.setOrderByClause("schooltype asc");
		try {
			list = irpEducationDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public long educationEdit(IrpEducation irpEducation) {
		long nEducationId = 0L;
		if(irpEducation.getEducationid()==null || irpEducation.getEducationid()==0){
			long nTempId = TableIdUtil.getNextId(IrpEducation.TABLE_NAME);
			irpEducation.setEducationid(nTempId);
			irpEducation.setCrtime(new Date());
			IrpUser loginUser = LoginUtil.getLoginUser();
			irpEducation.setUserid(loginUser.getUserid());
			irpEducation.setCruser(loginUser.getUsername());
			irpEducation.setCruserid(loginUser.getUserid());
			try {
				irpEducationDAO.insertSelective(irpEducation);
				nEducationId = nTempId;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				irpEducationDAO.updateByPrimaryKeySelective(irpEducation);
				nEducationId = irpEducation.getEducationid();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nEducationId;
	}
	
	@Override
	public int deleteEduById(long _nEducationId) {
		int nCount = 0;
		try {
			nCount = irpEducationDAO.deleteByPrimaryKey(_nEducationId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public IrpEducation findEducationById(long _nEducationId) {
		IrpEducation education = null;
		try {
			education = irpEducationDAO.selectByPrimaryKey(_nEducationId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return education;
	}
}
