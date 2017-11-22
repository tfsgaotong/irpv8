package com.tfs.irp.schedule.web;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.schedule.entity.IrpSchedule;
import com.tfs.irp.schedule.service.IrpScheduleService;
import com.tfs.irp.util.LoginUtil;

@SuppressWarnings("serial")
public class IrpScheduleAction extends ActionSupport {

	private IrpScheduleService irpScheduleService;
	private IrpSchedule irpSchedule;
	private String message;
	private List<IrpSchedule> irpSchedules;
	private String cyear;
	private String cmonth;
	private Integer cday;
	
	public Integer getCday() {
		return cday;
	}

	public void setCday(Integer cday) {
		this.cday = cday;
	}
	private Map<Integer,String> monthstr;
	
	public Map<Integer, String> getMonthstr() {
		return monthstr;
	}

	public void setMonthstr(Map<Integer, String> monthstr) {
		this.monthstr = monthstr;
	}

	public String getCyear() {
		return cyear;
	}

	public void setCyear(String cyear) {
		this.cyear = cyear;
	}

	public String getCmonth() {
		return cmonth;
	}

	public void setCmonth(String cmonth) {
		this.cmonth = cmonth;
	}

	public List<IrpSchedule> getIrpSchedules() {
		return irpSchedules;
	}

	public void setIrpSchedules(List<IrpSchedule> irpSchedules) {
		this.irpSchedules = irpSchedules;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IrpScheduleService getIrpScheduleService() {
		return irpScheduleService;
	}

	public void setIrpScheduleService(IrpScheduleService irpScheduleService) {
		this.irpScheduleService = irpScheduleService;
	}
	public IrpSchedule getIrpSchedule() {
		return irpSchedule;
	}

	public void setIrpSchedule(IrpSchedule irpSchedule) {
		this.irpSchedule = irpSchedule;
	}


   /*
    * 跳转
    */
	public String jumb(){
		return SUCCESS;
	}
	
	
   //当月
	public String findMonththing() {
		if(cyear!=null&&cyear.length()>0&&cmonth!=null&&cmonth.length()>0){
			List<IrpSchedule> list=irpScheduleService.findAllbydate(Integer.parseInt(cyear),Integer.parseInt(cmonth));
			irpSchedules=new ArrayList<IrpSchedule>();
			for(IrpSchedule irpsc:list){
				if(irpsc.getLookusers().contains(LoginUtil.getLoginUser().getUsername())){
					irpSchedules.add(irpsc);
				}else if(irpsc.getCruseid()==LoginUtil.getLoginUserId()){
					irpSchedules.add(irpsc);
				}
			}
			monthstr=new HashMap<Integer, String>();
			for(int i=0;i<irpSchedules.size();i++){
				Integer day=irpSchedules.get(i).getStarttime().getDate();
				if(monthstr.containsKey(day)){
					monthstr.put(day, monthstr.get(day)+"<br/>"+irpSchedules.get(i).getSchname());
				}else{
					monthstr.put(day, irpSchedules.get(i).getSchname());
				}
			}
		}
		return SUCCESS;
	}
	


	
	//查询所有
	public String findAllththing() {
		List<IrpSchedule> list=irpScheduleService.findAllmything();
		irpSchedules=new ArrayList<IrpSchedule>();
		for(IrpSchedule irpsc:list){
			if(irpsc.getLookusers().contains(LoginUtil.getLoginUser().getUsername())){
				irpSchedules.add(irpsc);
			}else if(irpsc.getCruseid()==LoginUtil.getLoginUserId()){
				irpSchedules.add(irpsc);
			}
		}
		return SUCCESS;
	}
	/**
	 * 添加
	 * @return
	 */
	public String addSchedule(){
		try {
			if(irpSchedule.getSchbak()!=null){
				irpSchedule.setSchbak(new String(irpSchedule.getSchbak().getBytes("ISO-8859-1"), "UTF-8"));
			}
			if(irpSchedule.getPlace()!=null){
				irpSchedule.setPlace(new String(irpSchedule.getPlace().getBytes("ISO-8859-1"), "UTF-8"));
			}
			irpSchedule.setSchname(new String(irpSchedule.getSchname().getBytes("ISO-8859-1"), "UTF-8"));
			irpSchedule.setLookusers(new String(irpSchedule.getLookusers().getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		irpScheduleService.addSchedule(irpSchedule);
		this.setMessage("ok");
		return SUCCESS;
	}
	/*
	 * 当日
	 */
	public String findFloatday(){
		if(cyear!=null&&cyear.length()>0&&cmonth!=null&&cmonth.length()>0&&cday!=null&&cday>0){
			List<IrpSchedule> list=irpScheduleService.findAllbyday(Integer.parseInt(this.getCyear()),Integer.parseInt(this.getCmonth()),this.getCday());
			irpSchedules=new ArrayList<IrpSchedule>();
			for(IrpSchedule irpsc:list){
				if(irpsc.getLookusers().contains(LoginUtil.getLoginUser().getUsername())){
					irpSchedules.add(irpsc);
				}else if(irpsc.getCruseid()==LoginUtil.getLoginUserId()){
					irpSchedules.add(irpsc);
				}
			}
		}
		return SUCCESS;
	}
}
