package com.tfs.irp.managementoper.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tfs.irp.managementoper.dao.IrpManagementOperDAO;
import com.tfs.irp.managementoper.entity.IrpManagementOper;
import com.tfs.irp.managementoper.entity.IrpManagementOperExample;
import com.tfs.irp.managementoper.service.IrpManagementOperService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.RightUtil;

public class IrpManagementOperServiceImpl implements IrpManagementOperService {

	private IrpManagementOperDAO irpManagementOperDAO;

	public IrpManagementOperDAO getIrpManagementOperDAO() {
		return irpManagementOperDAO;
	}

	public void setIrpManagementOperDAO(IrpManagementOperDAO irpManagementOperDAO) {
		this.irpManagementOperDAO = irpManagementOperDAO;
	}

	@Override
	public List<IrpManagementOper> findManagementOpersByParentId(long _nParentId,Long roleType) {
		List<IrpManagementOper> irpManagementOpers = new ArrayList<IrpManagementOper>();
		IrpManagementOperExample example = new IrpManagementOperExample();
		example.createCriteria().andParentidEqualTo(_nParentId).andRoletypeEqualTo(roleType).andStatusEqualTo(1l);
		example.setOrderByClause("MANAGEMENTOPERID ASC");
		try {
			irpManagementOpers = irpManagementOperDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpManagementOpers;
	}

	@Override
	public int findManagementOpersCountByParentId(long _nParentId, Long roleType) {
		int nCount = 0;
		IrpManagementOperExample example = new IrpManagementOperExample();
		example.createCriteria().andParentidEqualTo(_nParentId).andRoletypeEqualTo(roleType).andStatusEqualTo(1l);
		try {
			nCount = irpManagementOperDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpManagementOper> findRightManagementOpersByParentId(long _nParentId, long _nOperTypeId, Long roleType) {
		return findRightManagementOpersByParentId(LoginUtil.getLoginUser(), _nParentId, _nOperTypeId,roleType);
	}
	
	@Override
	public List<IrpManagementOper> findRightManagementOpersByParentId(IrpUser loginUser, long _nParentId, long _nOperTypeId, Long roleType) {
		List<IrpManagementOper> irpManagementOpers = new ArrayList<IrpManagementOper>();
		IrpManagementOperExample example = new IrpManagementOperExample();
		RightUtil rightUtil=new RightUtil();
		String sExtSql = rightUtil.getRightExistsSQL(loginUser, new IrpManagementOper(), _nOperTypeId);
		example.createCriteria().andParentidEqualTo(_nParentId).andRoletypeEqualTo(roleType).andStatusEqualTo(1l).extSQL(sExtSql);
		
		example.setOrderByClause("MANAGEMENTOPERID ASC");
		try {
			irpManagementOpers = irpManagementOperDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpManagementOpers;
	}
	
	@Override
	public int findRightManagementOpersCountByParentId(long _nParentId, long _nOperTypeId, Long roleType) {
		return findRightManagementOpersCountByParentId(LoginUtil.getLoginUser(), _nParentId, _nOperTypeId,roleType);
	}

	@Override
	public int findRightManagementOpersCountByParentId(IrpUser loginUser,long _nParentId, long _nOperTypeId, Long roleType) {
		int nCount = 0;
		IrpManagementOperExample example = new IrpManagementOperExample();
		RightUtil rightUtil=new RightUtil();
		String sExtSql = rightUtil.getRightExistsSQL(loginUser, new IrpManagementOper(), _nOperTypeId);
		example.createCriteria().andParentidEqualTo(_nParentId).andRoletypeEqualTo(roleType).andStatusEqualTo(1l).extSQL(sExtSql);
		try {
			nCount = irpManagementOperDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
}
