package com.tfs.irp.userapp.web;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.userapp.entity.IrpUserapp;
import com.tfs.irp.userapp.service.IrpUserappService;
import com.tfs.irp.util.LoginUtil;

@SuppressWarnings("serial")
public class IrpUserappAction extends ActionSupport{

	
	private IrpUserappService irpUserappService;

	public IrpUserappService getIrpUserappService() {
		return irpUserappService;
	}

	public void setIrpUserappService(IrpUserappService irpUserappService) {
		this.irpUserappService = irpUserappService;
	}
	
	private String isdisplay;
	private String applistid;
	
	
	public String getIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(String isdisplay) {
		this.isdisplay = isdisplay;
	}

	public String getApplistid() {
		return applistid;
	}

	public void setApplistid(String applistid) {
		this.applistid = applistid;
	}

	/**
	 * 修改用户应用表
	 * @return
	 * @throws Exception
	 */
	public String updateAppDisplay() throws Exception {
		Map<String, Long> map=new HashMap<String, Long>();
		Long userid=LoginUtil.getLoginUserId();
		map.put("userid", userid);
		map.put("applistid", Long.parseLong(applistid));
		if(isdisplay.equals("0")){
			//查询是否存在   存在则改为启用 不存在则添加
			int count=irpUserappService.findApphas(map);
			if(count>0){
				//修改
				map.put("isdisplay", 0L);
				irpUserappService.updateAppdisplay(map);
			}else{
				//添加用户应用
				IrpUserapp irpUserapp=new IrpUserapp();
				irpUserapp.setApplistid(Long.parseLong(applistid));
				irpUserapp.setIsdisplay(0);
				irpUserapp.setUserid(userid);
				irpUserappService.addAppuse(irpUserapp);
			}
		}else{
			//修改用户应用
			map.put("isdisplay", 1L);
			irpUserappService.updateAppdisplay(map);
		}
		return SUCCESS;
	}
}
