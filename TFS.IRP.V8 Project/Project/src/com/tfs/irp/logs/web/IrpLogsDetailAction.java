package com.tfs.irp.logs.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.logs.service.IrpLogsService;

public class IrpLogsDetailAction extends ActionSupport {

	
	private IrpLogsService irpLogsService;

	public IrpLogsService getIrpLogsService() {
		return irpLogsService;
	}

	public void setIrpLogsService(IrpLogsService irpLogsService) {
		this.irpLogsService = irpLogsService;
	}
	private long _logid=1;

	public long get_logid() {
		return _logid;
	}

	public void set_logid(long _logid) {
		this._logid = _logid;
	}
	private IrpLogs irpLogs;
	
	public IrpLogs getIrpLogs() {
		return irpLogs;
	}

	public void setIrpLogs(IrpLogs irpLogs) {
		this.irpLogs = irpLogs;
	}

	public String irpLogsDetail(){
		this.irpLogs=this.irpLogsService.findIrpLogsByLogid(this.get_logid());
		if (this.getIrpLogs()!=null) {
			return SUCCESS;	
		}else{
			return ERROR;
		}
		
	}
	
}
