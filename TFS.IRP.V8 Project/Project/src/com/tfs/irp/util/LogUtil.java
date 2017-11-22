package com.tfs.irp.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.logs.service.IrpLogsService;
import com.tfs.irp.tableid.entity.IrpTableid;
import com.tfs.irp.user.entity.IrpUser;

public class LogUtil {

	/**
	 * 定义日志类型
	 * 
	 * @param LOGERROR
	 *            1:错误
	 */
	public static final long LOGERROR = 1;
	/**
	 * 定义日志类型
	 * 
	 * @param LOGWARN
	 *            2：警告
	 */
	public static final long LOGWARN = 2;
	/**
	 * 定义日志类型
	 * 
	 * @param LOGINFO
	 *            3：信息
	 */
	public static final long LOGINFO = 3;
	/**
	 * 定义日志类型
	 * 
	 * @param LOGDEBUG
	 *            4：调试
	 */
	public static final long LOGDEBUG = 4;
	/*
	 * 定义操作结果类型1:成功2：失败
	 */
	private static final int LOGSUCCESS = 1;
	private static final int LOGFIAL = 2;
	/*
	 * 【1】标记开始时间戳【2】操作时间【3】操作类型【4】操作对象类型【5】操作对象id【6】操作对象名称【7】标记结束时间戳【8】登录用户
	 */
	private long STARTTIMES;
	private Date LOGOPTIME;
	private String LOGOPERTYPE;
	private String LOGOPOBJTYPE;
	private long LOGOPOBJID;
	private String LOGOPOBJNAME;
	private long ENDTIMES;
	private String LOGUSER;
	/*
	 * 操作对象
	 */
	private String FORMATOPOBJ;
	
	private IrpLogsService irpLogsdao;
	
	private IrpUser loginUser;
	
	ServletContext servletContext;

	/*
	 * 日志初始
	 */
	public LogUtil() {
		//开始记录时间戳
		this.STARTTIMES = System.currentTimeMillis();
		
		//初始化IrpLogsService
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpLogsdao = (IrpLogsService) ac.getBean("irpLogsService");
		
		//获得登录用户
		loginUser = LoginUtil.getLoginUser();
	}
	
	public LogUtil(String _opertype, IrpBaseObj irpBaseObj, ServletContext application){
		this.LOGOPTIME = this.getDate();
		this.STARTTIMES = System.currentTimeMillis();
		this.LOGOPERTYPE = _opertype;
		if (irpBaseObj.getTableName() == null) {
			this.LOGOPOBJTYPE = "";
		} else {
			this.LOGOPOBJTYPE = irpBaseObj.getTableName();
		}
		if (irpBaseObj.getId() == null) {
			this.LOGOPOBJID = 0;
		} else {
			this.LOGOPOBJID = irpBaseObj.getId();
		}
		if (irpBaseObj.getName() == null) {
			this.LOGOPOBJNAME = "";
		} else {
			this.LOGOPOBJNAME = irpBaseObj.getName();
		}
		
		//初始化IrpLogsService
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpLogsdao = (IrpLogsService) ac.getBean("irpLogsService");
		
		//获得登录用户
		loginUser = (IrpUser)irpBaseObj;	
	}

	/**
	 * 初始化日志，实例化日志对象，接收以下参数。 适用于 站点 栏目 组 用户 工作流配置 文档
	 * 
	 * @param _opertype
	 *            操作类型
	 * @param irpBaseObj
	 *            返回当前对象(包含tablename name id) IrpBaseObj
	 * @return
	 */
	public LogUtil(String _opertype, IrpBaseObj irpBaseObj) {
		this.LOGOPTIME = this.getDate();
		this.STARTTIMES = System.currentTimeMillis();
		this.LOGOPERTYPE = _opertype;
		if (irpBaseObj.getTableName() == null) {
			this.LOGOPOBJTYPE = "";
		} else {
			this.LOGOPOBJTYPE = irpBaseObj.getTableName();
		}
		if (irpBaseObj.getId() == null) {
			this.LOGOPOBJID = 0;
		} else {
			this.LOGOPOBJID = irpBaseObj.getId();
		}
		if (irpBaseObj.getName() == null) {
			this.LOGOPOBJNAME = "";
		} else {
			this.LOGOPOBJNAME = irpBaseObj.getName();
		}
		
		//初始化IrpLogsService
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpLogsdao = (IrpLogsService) ac.getBean("irpLogsService");
		
		//获得登录用户
		loginUser = LoginUtil.getLoginUser();		
	}

	/**
	 * 成功以后调用 适用于 站点 栏目 组 用户 工作流配置 文档 频道文档 重载日志 系统定义详细信息
	 * 
	 * @return
	 */
	public void successLogs() {
		this.successLogs(LogUtil.LOGINFO, logDetailDesc());
	}

	public void successLogs(String _loginfo) {
		this.successLogs(LogUtil.LOGINFO, _loginfo);
	}

	/**
	 * 成功以后调用 适用于 站点 栏目 组 用户 工作流配置 文档 频道文档
	 * 
	 * @param _loginfo
	 *            详细描述 重载日志 自定义详细描述
	 * @return
	 */
	public void successLogs(long _logType, String _loginfo) {
		IrpLogs irpLogs = new IrpLogs();
		irpLogs.setLogid(TableIdUtil.getNextId(IrpLogs.TABLE_NAME));
		irpLogs.setLogtype(_logType);
		getLogUser();
		irpLogs.setLoginfo(_loginfo);
		irpLogs.setLogobjname(FORMATOPOBJ);
		irpLogs.setLogobjtype(getObjtype());
		irpLogs.setLogobjid(this.LOGOPOBJID);
		irpLogs.setLogoptype(this.getOpername(this.LOGOPERTYPE));	
		irpLogs.setLogresult(LogUtil.LOGSUCCESS);
		irpLogs.setLoguser(LOGUSER);
		irpLogs.setLogoptime(this.LOGOPTIME);
		irpLogs.setStimemillis(BigDecimal.valueOf(STARTTIMES));
		this.ENDTIMES = System.currentTimeMillis();
		irpLogs.setEtimemillis(BigDecimal.valueOf(ENDTIMES));
		irpLogs.setExectime(BigDecimal.valueOf(ENDTIMES - STARTTIMES));
		irpLogs.setLoguserip(getIpAddr());
		irpLogsdao.logsUserBehaviour(irpLogs);
	}

	/**
	 * 失败以后调用 适用于登录失败 系统生成详细描述
	 * 
	 * @return
	 */
	public void errorLogs() {
		this.errorLogs(0l, logDetailDesc(), null);
	}

	public void errorLogs(String _loginfo) {
		this.errorLogs(LogUtil.LOGERROR, _loginfo, null);
	}

	public void errorLogs(Throwable _throwable) {
		this.errorLogs(LogUtil.LOGERROR, logDetailDesc(), _throwable);
	}

	public void errorLogs(String _loginfo, Throwable _throwable) {
		this.errorLogs(LogUtil.LOGERROR, _loginfo, _throwable);
	}

	/**
	 * 失败时调用 适用于 站点 栏目 组 用户 工作流配置 文档 频道文档
	 * 
	 * @param _loginfo
	 *            详细描述
	 * @param _throwable
	 *            异常描述 如果没有可以填写 null
	 * @return
	 */
	public void errorLogs(long _logType, String _loginfo, Throwable _throwable) {
		IrpLogs irpLogsError = new IrpLogs();
		this.ENDTIMES = System.currentTimeMillis();
		irpLogsError.setLogid(TableIdUtil.getNextId(IrpLogs.TABLE_NAME));
		irpLogsError.setLogtype(LogUtil.LOGERROR);
		getLogUser();
		if (_throwable != null) {
			_loginfo += "\n" + _throwable.getMessage();
			if (_loginfo.length() > 200) {
				_loginfo = _loginfo.substring(0, 200);
			}
		}
		irpLogsError.setLoginfo(_loginfo);
		irpLogsError.setLogobjname(FORMATOPOBJ);
		irpLogsError.setLogobjtype(getObjtype());
		irpLogsError.setLogobjid(this.LOGOPOBJID);
		irpLogsError.setLogoptype(this.getOpername(this.LOGOPERTYPE));
		irpLogsError.setLogresult(LogUtil.LOGFIAL);
		irpLogsError.setLoguser(LOGUSER);
		irpLogsError.setLogoptime(this.LOGOPTIME);
		irpLogsError.setStimemillis(BigDecimal.valueOf(STARTTIMES));
		this.ENDTIMES = System.currentTimeMillis();
		irpLogsError.setEtimemillis(BigDecimal.valueOf(ENDTIMES));
		irpLogsError.setExectime(BigDecimal.valueOf(ENDTIMES - STARTTIMES));
		irpLogsError.setLoguserip(getIpAddr());
		irpLogsdao.logsUserBehaviour(irpLogsError);
	}

	/*
	 * 获得当前日期
	 */
	private Date getDate() {
		return new Date();
	}

	/**
	 * 操作对象 返回当前操作对象 可用于构建详细信息
	 * 
	 * @return
	 */
	public String getLogUser() {
		if (loginUser == null) {
			FORMATOPOBJ = LOGOPOBJNAME + "["
					+ getTableObjType(this.LOGOPOBJTYPE) + "-" + LOGOPOBJID
					+ "]";
			LOGUSER = LOGOPOBJNAME;
		} else {
			LOGUSER = loginUser.getName();
			FORMATOPOBJ = LOGOPOBJNAME + "["
					+ getTableObjType(this.LOGOPOBJTYPE) + "-" + LOGOPOBJID
					+ "]";
		}
		return FORMATOPOBJ;
	}

	/**
	 * ip 可用户构建详细信息
	 * 
	 * @return
	 */
	public String getIpAddr() {
		HttpServletRequest request = null;
		try {
			request = ServletActionContext.getRequest();
		} catch (NullPointerException e) {
			return "";
		}
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 格式化当前操作时间 　用于构建详细信息
	 * 
	 * @return
	 */
	public String getFormatTimeOfLogTime() {
		return this.getFormatTime(this.LOGOPTIME);
	}

	/*
	 * 获得操作对象类型
	 */
	private String getObjtype() {
		String sObjtype = this.irpLogsdao.irpTableid(LOGOPOBJTYPE).getTableobjtype();
		if (sObjtype == null) {
			return "";
		}
		return sObjtype;
	}

	/*
	 * 操作类型转操作名称
	 */
	private String getOpername(String _opertype) {
		return this.irpLogsdao.getOpername(_opertype);
	}

	/**
	 * 返回操作名称 构建详细信息
	 * 
	 * @return
	 */
	public String getOpername() {
		return this.irpLogsdao.getOpername(this.LOGOPERTYPE);
	}

	/*
	 * 根据表名获得操作对象
	 */
	private String getTableObjType(String _tablename) {
		IrpTableid irpTableid = this.irpLogsdao.irpTableid(_tablename);
		String operobj = irpTableid.getTableobjtype();
		if (operobj == null) {
			operobj = "";
		}
		return operobj;
	}

	/*
	 * 格式化时间
	 */
	private String getFormatTime(Date _getdate) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(_getdate);
	}

	// 构建详细描述
	private String logDetailDesc() {
		return this.getOpername(this.LOGOPERTYPE) + "," + this.getLogUser();

	}
}
