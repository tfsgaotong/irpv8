package com.tfs.irp.schedule.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.schedule.dao.IrpScheduleDAO;
import com.tfs.irp.schedule.entity.IrpSchedule;
import com.tfs.irp.schedule.entity.IrpScheduleExample;
import com.tfs.irp.schedule.service.IrpScheduleService;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpScheduleServiceImpl implements IrpScheduleService {

	private IrpScheduleDAO irpScheduleDAO;
	
	public IrpScheduleDAO getIrpScheduleDAO() {
		return irpScheduleDAO;
	}

	public void setIrpScheduleDAO(IrpScheduleDAO irpScheduleDAO) {
		this.irpScheduleDAO = irpScheduleDAO;
	}

	@Override
	public List<IrpSchedule> findAllbydate(Integer _year, Integer _month) {
		List<IrpSchedule> list=null;
		IrpScheduleExample example=new IrpScheduleExample();
		String strs=_year+"-"+_month+"-"+"1 00:00";
		String stre=_year+"-"+(_month+1)+"-"+"1 00:00";
		Date datas=DateUtils.getDateByStrToFormat("yyyy-MM-dd HH:mm", strs);
		Date datae=DateUtils.getDateByStrToFormat("yyyy-MM-dd HH:mm", stre);
		example.createCriteria().andStarttimeBetween(datas, datae);
		example.setOrderByClause("starttime desc");
		try {
			list=irpScheduleDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<IrpSchedule> findAllbyday(Integer _year, Integer _month,
			Integer _day) {
		List<IrpSchedule> list=null;
		IrpScheduleExample example=new IrpScheduleExample();
		Date datas=DateUtils.getDateByStrToFormat("yyyy-MM-dd", _year+"-"+_month+"-"+_day);
		example.createCriteria().andStarttimeEqualTo(datas);
		example.setOrderByClause("starttime desc");
		try {
			list=irpScheduleDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void addSchedule(IrpSchedule irpSchedule) {
		irpSchedule.setSchid(TableIdUtil.getNextId(IrpSchedule.TABLE_NAME));
		irpSchedule.setCrtime(new Date());
		irpSchedule.setCruseid(LoginUtil.getLoginUserId());
		if(irpSchedule.getLookusers()==null||irpSchedule.getLookusers().trim().length()==0){
			irpSchedule.setLookusers(LoginUtil.getLoginUser().getUsername());
		}
		try {
			irpScheduleDAO.insert(irpSchedule);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<IrpSchedule> findAllmything() {
		List<IrpSchedule> list=null;
		IrpScheduleExample example=new IrpScheduleExample();
		example.createCriteria().andStarttimeGreaterThanOrEqualTo(new Date());
		example.setOrderByClause("starttime");
		try {
			list=irpScheduleDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
