package com.tfs.irp.logs.web;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.logs.service.IrpLogsService;
import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.tableid.entity.IrpTableid;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.PageUtil;

public class IrpLogsAction extends ActionSupport {

	private IrpLogsService irpLogsService;
	private String pageHTML = "";
	private int pageNum = 1;

	private int pageSize = 10;

	private String orderField = "";

	private String orderBy = "";

	private List<IrpLogs> irpLogs;
	//全局设置   操作类型
	private List<IrpOpertype> irpOpertype;
	

	//tableid的集合
	private List<IrpTableid> listIrpTableid;
	public List<IrpTableid> getListIrpTableid() {
		return listIrpTableid;
	}

	public void setListIrpTableid(List<IrpTableid> listIrpTableid) {
		this.listIrpTableid = listIrpTableid;
	}


	public List<IrpOpertype> getIrpOpertype() {
		return irpOpertype;
	}

	public void setIrpOpertype(List<IrpOpertype> irpOpertype) {
		this.irpOpertype = irpOpertype;
	}
	// 日志类型
	private long irplogstype = 1l;

	public long getIrplogstype() {
		return irplogstype;
	}

	public void setIrplogstype(long irplogstype) {
		this.irplogstype = irplogstype;
	}

	public List<IrpLogs> getIrpLogs() {
		return irpLogs;
	}

	public void setIrpLogs(List<IrpLogs> irpLogs) {
		this.irpLogs = irpLogs;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public IrpLogsService getIrpLogsService() {
		return irpLogsService;
	}

	public void setIrpLogsService(IrpLogsService irpLogsService) {
		this.irpLogsService = irpLogsService;
	}

	/*
	 * 获得检索日志时页面传参
	 */
	

	private String c_v_log_type; //日至类型，错误警告信息调试
	private String c_v_log_obj_oper;//操作对象
	private String c_v_log_oper_obj_id;//操作对象id
	private String c_v_log_oper_user;//操作用户
	private String c_v_Irp_type_oper;//操作类型
	private String c_start_end;//操作时间段
	private String c_v_Irp_oper_result;//操作结果
	//时间段 时间选择框
	private String c_date_start_time;
	private String c_date_end_time;
	
	
	





	public String getC_date_start_time() {
		return c_date_start_time;
	}

	public void setC_date_start_time(String c_date_start_time) {
		this.c_date_start_time = c_date_start_time;
	}

	public String getC_date_end_time() {
		return c_date_end_time;
	}

	public void setC_date_end_time(String c_date_end_time) {
		this.c_date_end_time = c_date_end_time;
	}

	public String getC_v_log_type() {
		return c_v_log_type;
	}

	public void setC_v_log_type(String c_v_log_type) {
		this.c_v_log_type = c_v_log_type;
	}



	public String getC_v_log_obj_oper() {
		return c_v_log_obj_oper;
	}

	public void setC_v_log_obj_oper(String c_v_log_obj_oper) {
		this.c_v_log_obj_oper = c_v_log_obj_oper;
	}

	public String getC_v_log_oper_obj_id() {
		return c_v_log_oper_obj_id;
	}

	public void setC_v_log_oper_obj_id(String c_v_log_oper_obj_id) {
		this.c_v_log_oper_obj_id = c_v_log_oper_obj_id;
	}

	public String getC_v_log_oper_user() {
		return c_v_log_oper_user;
	}

	public void setC_v_log_oper_user(String c_v_log_oper_user) {
		this.c_v_log_oper_user = c_v_log_oper_user;
	}


	public String getC_v_Irp_type_oper() {
		return c_v_Irp_type_oper;
	}

	public void setC_v_Irp_type_oper(String c_v_Irp_type_oper) {
		this.c_v_Irp_type_oper = c_v_Irp_type_oper;
	}

	public String getC_start_end() {
		return c_start_end;
	}

	public void setC_start_end(String c_start_end) {
		this.c_start_end = c_start_end;
	}

	public String getC_v_Irp_oper_result() {
		return c_v_Irp_oper_result;
	}

	public void setC_v_Irp_oper_result(String c_v_Irp_oper_result) {
		this.c_v_Irp_oper_result = c_v_Irp_oper_result;
	}

	public String IrpLogsList() {
		// 处理排序
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "logoptime desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		switch ((int)this.getIrplogstype()) {
		case 1:
			//错误
			int logerrorsize= this.irpLogsService.irpLogSizeOfLogtype(this.getIrplogstype());
     		PageUtil pageUtilerror = new PageUtil(this.pageNum, this.getPageSize(),
     				logerrorsize);
			this.irpLogs = this.irpLogsService.findIrpLogsOfPage(
					pageUtilerror, _oOrderby, this.getIrplogstype());
			this.irpOpertype=this.irpLogsService.getIrpOpertype_opertype();
			this.listIrpTableid= this.irpLogsService.findAllTableObjType();
			this.pageHTML = pageUtilerror.getPageHtml("page(#PageNum#)");
			return ERROR;
         case 2:
        		//警告
        	 int logalertsize=this.irpLogsService.irpLogSizeOfLogtype(this.getIrplogstype());
     		PageUtil pageUtilalert = new PageUtil(this.pageNum, this.getPageSize(),
     				logalertsize);
        	 this.irpLogs = this.irpLogsService.findIrpLogsOfPage(
        			 pageUtilalert, _oOrderby, this.getIrplogstype());
        	 this.irpOpertype=this.irpLogsService.getIrpOpertype_opertype();
        	 this.listIrpTableid= this.irpLogsService.findAllTableObjType();
        		this.pageHTML = pageUtilalert.getPageHtml("page(#PageNum#)");
			return LOGIN;
         case 3:
        	 //信息
        	int loginfosize= this.irpLogsService.irpLogSizeOfLogtype(this.getIrplogstype());
        		PageUtil pageUtilinfo = new PageUtil(this.pageNum, this.getPageSize(),
        				loginfosize);
        	 this.irpLogs = this.irpLogsService.findIrpLogsOfPage(
        			 pageUtilinfo, _oOrderby, this.getIrplogstype());
        	 this.irpOpertype=this.irpLogsService.getIrpOpertype_opertype();
        	 this.listIrpTableid= this.irpLogsService.findAllTableObjType();
        	this.pageHTML = pageUtilinfo.getPageHtml("page(#PageNum#)");
 			return SUCCESS;
         case 4:
        	 //调试
        	 int logdebugsize=this.irpLogsService.irpLogSizeOfLogtype(this.getIrplogstype());
     		PageUtil pageUtildebug = new PageUtil(this.pageNum, this.getPageSize(),
     				logdebugsize);
        	 this.irpLogs = this.irpLogsService.findIrpLogsOfPage(
        			 pageUtildebug, _oOrderby, this.getIrplogstype());
        	 this.irpOpertype=this.irpLogsService.getIrpOpertype_opertype();
        	 this.listIrpTableid= this.irpLogsService.findAllTableObjType();
        	this.pageHTML = pageUtildebug.getPageHtml("page(#PageNum#)");
 			return INPUT;
         default:
		    return NONE;
		}
		
		
	}
	//检索
	public String ckLogsInfo(){	
		Date starttimes=null;
		Date endtimes=null;
		int nDateCount = this.irpLogsService.findLogsCountByStatus();
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				nDateCount);
		// 处理排序
		String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "logoptime desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		IrpLogs checkirplog=new IrpLogs();
		//日志类型
		if (c_v_log_type==null) {
			c_v_log_type = "0";
		}
		checkirplog.setLogtype(Long.parseLong(c_v_log_type));
		//操作对象
		checkirplog.setLogobjtype(this.c_v_log_obj_oper);
		//操作对象id
		if(c_v_log_oper_obj_id==null){
			c_v_log_oper_obj_id = "-1";
		}
			if (c_v_log_oper_obj_id.equals("")) 
				c_v_log_oper_obj_id="-1";
		checkirplog.setLogobjid(Long.parseLong(c_v_log_oper_obj_id.trim()));
	
		//操作用户
		if (c_v_log_oper_user!=null) {
			checkirplog.setLoguser(c_v_log_oper_user.trim());
		}
		//操作类型	
		if (c_v_Irp_type_oper!=null) {
			checkirplog.setLogoptype(c_v_Irp_type_oper);
		}
			
		//时间段
		if (this.c_start_end.equals("logs_week")) {
			starttimes=new LogTimeUtil().getWeek();
			endtimes=new LogTimeUtil().getLastWeek();
		}else if(this.c_start_end.equals("logs_month")){
			starttimes=new LogTimeUtil().getMonth();
			endtimes=new LogTimeUtil().getLastMonth();
		}else if(this.c_start_end.equals("logs_quarter")){
			starttimes=new LogTimeUtil().getQuarter();
			endtimes=new LogTimeUtil().getLastQuarter();
		}else if(this.c_start_end.equals("logs_appoint_date")){
			String starttimeL[]=this.c_date_start_time.split("-");
			Date dateS=new Date(new LogTimeUtil().getYearOfYear(starttimeL[0]),Integer.parseInt(starttimeL[1])-1,Integer.parseInt(starttimeL[2]));
			dateS.setHours(0);
			dateS.setMinutes(0);
			dateS.setSeconds(0);
			starttimes=dateS;
			String endtimeL[]=this.c_date_end_time.split("-");
			Date dateE=new Date(new LogTimeUtil().getYearOfYear(endtimeL[0]),Integer.parseInt(endtimeL[1])-1,Integer.parseInt(endtimeL[2])+1);
			dateE.setHours(24);
			dateE.setMinutes(59);
			dateE.setSeconds(59);
			endtimes=dateE;
		}
		//操作结果
		checkirplog.setLogresult(Integer.parseInt(c_v_Irp_oper_result));
		int logchecksize= this.irpLogsService.findIrpLogsOfPageSize(pageUtil, _oOrderby,checkirplog,starttimes,endtimes).size();
		PageUtil pageUtilCheck = new PageUtil(this.pageNum, this.getPageSize(),
				logchecksize);
	    this.irpLogs=this.irpLogsService.findIrpLogsOfPage(pageUtilCheck, _oOrderby,checkirplog,starttimes,endtimes);
	    this.pageHTML = pageUtilCheck.getPageHtml("page(#PageNum#)");
   	 this.irpOpertype=this.irpLogsService.getIrpOpertype_opertype();
   	 this.listIrpTableid= this.irpLogsService.findAllTableObjType();
		return  SUCCESS;
	}

	
	
	

}
