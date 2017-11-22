package com.tfs.irp.schedule.service;

import java.util.List;


import com.tfs.irp.schedule.entity.IrpSchedule;

public interface IrpScheduleService {

	
	//查询当前月份的日程
	public List<IrpSchedule> findAllbydate(Integer _year,Integer _month);
	//查询当天日程
	public List<IrpSchedule> findAllbyday(Integer _year,Integer _month,Integer _day);
	
	public void addSchedule(IrpSchedule irpSchedule);
	//查询所有代办
	public List<IrpSchedule> findAllmything();
}
