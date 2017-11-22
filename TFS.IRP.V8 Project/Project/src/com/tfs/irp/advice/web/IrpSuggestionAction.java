package com.tfs.irp.advice.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.advice.entity.IrpSuggestion;
import com.tfs.irp.advice.service.IrpSuggestionService;
import com.tfs.irp.util.ActionUtil;

@SuppressWarnings("serial")
public class IrpSuggestionAction extends ActionSupport {
	
	private IrpSuggestionService irpsuggestionservice;
	private long devicetype;
	private String suggestion;
	
	public long getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(long devicetype) {
		this.devicetype = devicetype;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public IrpSuggestionService getIrpsuggestionservice() {
		return irpsuggestionservice;
	}

	public void setIrpsuggestionservice(IrpSuggestionService irpsuggestionservice) {
		this.irpsuggestionservice = irpsuggestionservice;
	}
	
	public void usersuggesiton(){
		IrpSuggestion irpsuggestion = new IrpSuggestion();
		irpsuggestion.setDevicetype(devicetype);
		irpsuggestion.setSuggestion(suggestion);
		irpsuggestionservice.savesuggestion(irpsuggestion);
		Map<String,String> map = new HashMap<String,String>();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		map.put("resultstatus", "1000");
		list.add(map);
		String jsonString_new = JSON.toJSONString(list);
		ActionUtil.writer(jsonString_new);
	}
	
}