package com.tfs.irp.leaveconf.service.impl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.leaveapply.entity.IrpLeaveapplyExample;
import com.tfs.irp.leaveconf.dao.IrpLeaveconfigDAO;
import com.tfs.irp.leaveconf.entity.IrpLeaveconfig;
import com.tfs.irp.leaveconf.entity.IrpLeaveconfigExample;
import com.tfs.irp.leaveconf.entity.IrpLeaveconfigExample.Criteria;
import com.tfs.irp.leaveconf.service.IrpLeaveconfigService;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpLeaveconfigServiceImpl implements IrpLeaveconfigService {
	
	private IrpLeaveconfigDAO irpLeaveconfigDAO;

	

	public IrpLeaveconfigDAO getIrpLeaveconfigDAO() {
		return irpLeaveconfigDAO;
	}



	public void setIrpLeaveconfigDAO(IrpLeaveconfigDAO irpLeaveconfigDAO) {
		this.irpLeaveconfigDAO = irpLeaveconfigDAO;
	}



	@Override
	public List<IrpLeaveconfig> getAllList() {
		IrpLeaveconfigExample example = new IrpLeaveconfigExample();
		List<IrpLeaveconfig>  list=new ArrayList<IrpLeaveconfig>();
		
		example.createCriteria().andLeavemarkingEqualTo(IrpLeaveapply.LEAVE).
		andApplystatusEqualTo(IrpLeaveconfig.NORMAL);
		
		example.setOrderByClause("leaveconfigid desc");
		
		try {
			list=irpLeaveconfigDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	/**
	 * 获取所有加班类型
	 */
	public List<IrpLeaveconfig> getAllOverTimeType() {
		IrpLeaveconfigExample example = new IrpLeaveconfigExample();
		List<IrpLeaveconfig>  list=new ArrayList<IrpLeaveconfig>();
		
		example.createCriteria().andLeavemarkingEqualTo(IrpLeaveapply.WORK).
		andApplystatusEqualTo(IrpLeaveconfig.NORMAL);		
		try {
			list=irpLeaveconfigDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<IrpLeaveconfig> getAllByMarking(String _orderBy,String _orderField,String _searchWord,String _searchType,PageUtil page,Integer marking) {
		IrpLeaveconfigExample example = new IrpLeaveconfigExample();
		Criteria criteria = example.createCriteria();
		List<IrpLeaveconfig>  list=new ArrayList<IrpLeaveconfig>();
		criteria.andLeavemarkingEqualTo(marking);
		if(_searchType!=null&&_searchType.equals("all")){
			example.or(criteria.andLeaveconfigdescLike("%"+_searchWord+"%"));
			example.or(criteria.andLeaveconfignameLike("%"+_searchWord+"%"));
		}else if(_searchType!=null&&_searchType.equals("leaveconfigname")){
			criteria.andLeaveconfignameLike("%"+_searchWord+"%");
		}
		if(_orderBy!=null&&!_orderBy.equals("")){
			example.setOrderByClause(_orderField+" "+_orderBy);
		}else{
			example.setOrderByClause("leaveconfigid desc");
		}
		try {
			list=irpLeaveconfigDAO.selectByExample(example, page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public Integer irpLeaveConfigCountByMarking(Integer marking) {
		IrpLeaveconfigExample example = new IrpLeaveconfigExample();	
		example.createCriteria().andLeavemarkingEqualTo(marking);
		int count=0;
		try {
		count = irpLeaveconfigDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public int addLeaveConfig(IrpLeaveconfig irpLeaveconfig) {
		int msg = 0;
		Date nowtime = DateUtils.getNOWTime();
		Long confiid = TableIdUtil.getNextId(IrpLeaveconfig.TABLENAME);
		irpLeaveconfig.setCrtime(nowtime);
		irpLeaveconfig.setCruserid(LoginUtil.getLoginUserId());
		irpLeaveconfig.setLeaveconfigid(confiid);
		  LogUtil logUtil=new LogUtil("LEAVECONFIG_ADD",irpLeaveconfig);
		try {
			irpLeaveconfigDAO.insert(irpLeaveconfig);
			msg=1;
			logUtil.successLogs( "添加加班请假配置["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "添加加班请假配置["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}



	@Override
	public int delLeaveConfig(Long leaveconfigid) {
		int msg=0;
		 LogUtil logUtil=null;
		try {
			logUtil=new LogUtil("LEAVECONFIG_DEL",irpLeaveconfigDAO.selectByPrimaryKey(leaveconfigid));
			msg = irpLeaveconfigDAO.deleteByPrimaryKey(leaveconfigid);
			logUtil.successLogs( "删除加班请假配置["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "删除加班请假配置["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}



	@Override
	public IrpLeaveconfig selLeaveConfigById(Long leaveconfigid) {
		// TODO Auto-generated method stub
		IrpLeaveconfig irpLeaveconfig= null;
		try {
			 irpLeaveconfig = irpLeaveconfigDAO.selectByPrimaryKey(leaveconfigid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpLeaveconfig;
	}



	@Override
	public int upLeaveConfigInfo(IrpLeaveconfig irpLeaveconfig,
			Long leaveconfigid) {
		// TODO Auto-generated method stub
		int msg = 0;
		  LogUtil logUtil=new LogUtil("LEAVECONFIG_UPDATE",irpLeaveconfig);
		irpLeaveconfig.setLeaveconfigid(leaveconfigid);
		try {
			msg = irpLeaveconfigDAO.updateByPrimaryKeySelective(irpLeaveconfig);
			logUtil.successLogs( "修改加班请假配置["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "修改加班请假配置["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}

}
