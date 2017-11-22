package com.tfs.irp.navigation.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.tfs.irp.navigation.dao.IrpUserNavigationDAO;
import com.tfs.irp.navigation.entity.IrpUserNavigation;
import com.tfs.irp.navigation.entity.IrpUserNavigationExample;
import com.tfs.irp.navigation.service.IrpUserNavigationService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpUserNavigationServiceImpl implements IrpUserNavigationService {

	private IrpUserNavigationDAO irpUserNavigationDAO;

	public IrpUserNavigationDAO getIrpUserNavigationDAO() {
		return irpUserNavigationDAO;
	}

	public void setIrpUserNavigationDAO(IrpUserNavigationDAO irpUserNavigationDAO) {
		this.irpUserNavigationDAO = irpUserNavigationDAO;
	}

	@Override
	public int addUserNavigation(String _navigationname,String _navigationvalue) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpUserNavigation irpUserNavigation = new IrpUserNavigation();
		
		irpUserNavigation.setNavigationid(TableIdUtil.getNextId(IrpUserNavigation.TABLE_NAME));
		irpUserNavigation.setNavigationname(_navigationname);
		irpUserNavigation.setNavigationvalue(_navigationvalue);
		irpUserNavigation.setStatus(IrpUserNavigation.NAVI_STATUS_NORMAL);
		irpUserNavigation.setCrtime(Calendar.getInstance().getTime());
		irpUserNavigation.setUserid(LoginUtil.getLoginUserId());
		irpUserNavigation.setClassifyid(IrpUserNavigation.DEFAULT_CLASS);
		
		try {
			this.irpUserNavigationDAO.insert(irpUserNavigation);
			status = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}

	@Override
	public int updateUserNavigation(Long _navigationid,String _navigationname,String _navigationvalue) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpUserNavigation irpUserNavigation = new IrpUserNavigation();
		irpUserNavigation.setNavigationid(_navigationid);
		irpUserNavigation.setNavigationname(_navigationname);
		irpUserNavigation.setNavigationvalue(_navigationvalue);
		
		try {
			status = this.irpUserNavigationDAO.updateByPrimaryKeySelective(irpUserNavigation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public int deleteUserNavigation(Long _navigationid) {
		// TODO Auto-generated method stub
		int status = 0;
		
		IrpUserNavigation record = new IrpUserNavigation();
		record.setNavigationid(_navigationid);
		record.setStatus(IrpUserNavigation.NAVI_STATUS_DELETE);
		
		try {
			status = this.irpUserNavigationDAO.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<IrpUserNavigation> findUserNavigationOfAll(Integer _status,
			String _orderby, PageUtil _pageUtil) {
		// TODO Auto-generated method stub
		List<IrpUserNavigation> list = null;
		
		IrpUserNavigationExample example = new IrpUserNavigationExample();
		
		example.createCriteria().andStatusEqualTo(_status)
								.andUseridEqualTo(LoginUtil.getLoginUserId());
		example.setOrderByClause(_orderby);
		
								
		
		try {
			list = this.irpUserNavigationDAO.selectByExample(example, _pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int findUserNavigationOfAllCount(Integer _status) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpUserNavigationExample example = new IrpUserNavigationExample();
		
		example.createCriteria().andStatusEqualTo(_status)
								.andUseridEqualTo(LoginUtil.getLoginUserId());
		try {
			num = this.irpUserNavigationDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public IrpUserNavigation getIrpUserNavigationById(Long _usernavigationid) {
		// TODO Auto-generated method stub
		IrpUserNavigation irpUserNavigation = null;
		
		try {
			irpUserNavigation = this.irpUserNavigationDAO.selectByPrimaryKey(_usernavigationid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpUserNavigation;
	}
	
	
}
