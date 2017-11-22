package com.tfs.irp.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.directwebremoting.ScriptSession;

import uk.ltd.getahead.dwr.WebContextFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.app.entity.IrpApp;
import com.tfs.irp.app.service.IrpAppService;
import com.tfs.irp.apptype.entity.IrpApptype;
import com.tfs.irp.apptype.service.IrpApptypeService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

@SuppressWarnings("serial")
public class IrpAppAction extends ActionSupport {

	//APPPAGESIZE
	private IrpAppService irpAppService;
	
	/**
	 * 分页排序检索
	 */
	private String pageHTML = "";
	private int pageNum = 1;
	private int pageSize = 10;
	private String orderField = "";
	private String orderBy = "";
	private String searchWord;
	private String searchType;
	private IrpApp irpApp;
	private List<IrpApp> irpApps;
	private Long aid;
	private String appid;
	private Long typeid;
	private String typedesc;
	private List<IrpApptype> irpApptypes;
	private String status;
	private String tab;
	private String appname;
	private  String anzhuangorxiezai;
	private List<IrpUser> irpUsers;
	private String sname;
	private IrpUserService irpUserService;
	private int ismyorall=1;
	
	
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public int getIsmyorall() {
		return ismyorall;
	}

	public void setIsmyorall(int ismyorall) {
		this.ismyorall = ismyorall;
	}
	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}

	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}

	public String getAnzhuangorxiezai() {
		return anzhuangorxiezai;
	}

	public void setAnzhuangorxiezai(String anzhuangorxiezai) {
		this.anzhuangorxiezai = anzhuangorxiezai;
	}
	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	private int resultnum;
	private IrpUser loginuserobj;
	public IrpUser getLoginuserobj() {
		return loginuserobj;
	}

	public void setLoginuserobj(IrpUser loginuserobj) {
		this.loginuserobj = loginuserobj;
	}

	public int getResultnum() {
		return resultnum;
	}

	public void setResultnum(int resultnum) {
		this.resultnum = resultnum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private IrpApptypeService irpApptypeService;

	public IrpApptypeService getIrpApptypeService() {
		return irpApptypeService;
	}

	public void setIrpApptypeService(IrpApptypeService irpApptypeService) {
		this.irpApptypeService = irpApptypeService;
	}

	public List<IrpApptype> getIrpApptypes() {
		return irpApptypes;
	}

	public void setIrpApptypes(List<IrpApptype> irpApptypes) {
		this.irpApptypes = irpApptypes;
	}

	public String getTypedesc() {
		return typedesc;
	}

	public void setTypedesc(String typedesc) {
		this.typedesc = typedesc;
	}

	public Long getTypeid() {
		return typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public IrpApp getIrpApp() {
		return irpApp;
	}

	public void setIrpApp(IrpApp irpApp) {
		this.irpApp = irpApp;
	}

	public List<IrpApp> getIrpApps() {
		return irpApps;
	}

	public void setIrpApps(List<IrpApp> irpApps) {
		this.irpApps = irpApps;
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

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public IrpAppService getIrpAppService() {
		return irpAppService;
	}

	public void setIrpAppService(IrpAppService irpAppService) {
		this.irpAppService = irpAppService;
	}
	/**
	 * 后台  根据应用类型查询应用无论是否发布
	 * @return
	 * @throws Exception
	 */
	public String selectAllAppbytype() throws Exception {
		this.setTypedesc(new String(this.typedesc.getBytes("ISO-8859-1"),
				"UTF-8"));
		try {
			List<Integer> status = new ArrayList<Integer>();
			status.add(IrpApp.ISPUBLISHYES);
			status.add(IrpApp.ISPUBLISHNO);
			irpApps = irpAppService.findAllIrpappbytype(status,this.getTypedesc());
		} catch (RuntimeException e) {
		}
		return SUCCESS;
	}
	
	/**
	 * 前台  根据应用类型查询应用只显示发布
	 * @return
	 * @throws Exception
	 */
	public String selectAllAppbytypeforProscenium() throws Exception {
		this.setTypedesc(new String(this.typedesc.getBytes("ISO-8859-1"),
				"UTF-8"));
		try {
			List<Integer> status = new ArrayList<Integer>();
			status.add(IrpApp.ISPUBLISHYES);
			irpApps = irpAppService.findAllIrpappbytype(status,
					this.getTypedesc());
		} catch (RuntimeException e) {
		}
		return SUCCESS;
	}
 /**
  * 查询所有应用
  * @return
   * @throws Exception
  */
	public String selectAllApp() throws Exception {
		try {
			List<Integer> status = new ArrayList<Integer>();
			status.add(IrpApp.ISPUBLISHYES);
			status.add(IrpApp.ISPUBLISHNO);
			int iresult = irpAppService.findAllIrpappCount(searchWord,
					searchType, status);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
					iresult);
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "applistid desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			irpApps = irpAppService.findAllIrpapp(pageUtil, searchWord,
					searchType, _oOrderby, status);
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		} catch (RuntimeException e) {
		}
		return SUCCESS;
	}

	/**
	 * 添加
	 */
	public void addNewApp() {
		try {
			irpAppService.addNewApp(irpApp);
			ActionUtil.writer(1 + "");
		} catch (RuntimeException e) {
			ActionUtil.writer(0 + "");
		}
	}

	/**
	 * 添加页面
	 */
	public String addfromApp() {
		irpApptypes = irpApptypeService.findAllapptype();
		return SUCCESS;
	}

	/**
	 * 跳到修改页面
	 */
	public String updateformApp() {
		irpApptypes = irpApptypeService.findAllapptype();
		irpApp = irpAppService.findAppByid(this.getAid());
		return SUCCESS;
	}

	/**
	 * 修改
	 */
	public void updateApp() {
		int result = irpAppService.updateApp(irpApp);
		ActionUtil.writer(result + "");
	}

	/**
	 * 修改应用详细信息
	 */
	public String updateAppInfo() {
		resultnum = irpAppService.updateApp(irpApp);
		return SUCCESS;
	}

	/**
	 * 查看应用的配置
	 */
	public String selectAppDetil() {
		irpApp = irpAppService.findAppByid(Long.parseLong(this.getAppid()
				.toString().trim()));
		return SUCCESS;
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	public String jumpTo() {
		if (this.getAppname().equals("意见反馈")) {
			this.setTab("complain");
		}else if(this.getAppname().equals("常用功能")){
			this.setTab("offenmenu");
		}else if(this.getAppname().equals("导出")){
			this.setTab("export");
		}else {
			// 后续可开发其他应用
			this.setTab(null);
		}
		return SUCCESS;
	}

	/**
	 * 修改
	 */
	public void updateAppstatus() {
		IrpApp iapp = new IrpApp();
		iapp.setApplistid(this.getAid());
		iapp.setStatus(Integer.parseInt(this.getStatus()));
		int result = irpAppService.updateAppStatus(iapp);
		ActionUtil.writer(result + "");
	}

	/**
	 * 获取用户可以使用的应用
	 * @return
	 */
	public String selectUserappsbystatus() {
		Long userid=LoginUtil.getLoginUserId();
		irpApps=irpAppService.findUserappbystuts(userid);
		return SUCCESS;
	}
	/**
	 * 跳到应用页面
	 * @return
	 */
	private int chatcontentnums;
	public String jumpPage() {
		//得到当前页面
		chatcontentnums = SysConfigUtil.getSysConfigNumValue("CHATCONTENTGNUMS");
		loginuserobj = this.irpUserService.findUserByUserId(LoginUtil.getLoginUserId());
		
		return SUCCESS;
	}
	public int getChatcontentnums() {
		return chatcontentnums;
	}

	public void setChatcontentnums(int chatcontentnums) {
		this.chatcontentnums = chatcontentnums;
	}

	/**
	 * 获取用户安装应用
	 * @return
	 */
	public String selectUserappsbystatusanddisplay() {
		this.anzhuangorxiezai="卸载";
		Map<String, Long> map=new HashMap<String, Long>();
		map.put("status", 0L);
		map.put("isdisplay", 0L);
		Long userid=LoginUtil.getLoginUserId();
		map.put("userid", userid);
		int result=irpAppService.findappuserdisplaycount(map);
		int ipagesize=Integer.parseInt(SysConfigUtil.getSysConfigValue("APPPAGESIZE"));
		this.setPageSize(ipagesize);
		PageUtil pageUtil = new PageUtil(this.getPageNum(), this.getPageSize(),result);
		irpApps=irpAppService.findappuserdisplay(pageUtil,map);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}

	/**
	 * 用户没有安装的
	 * @return
	 */
	public String selectUsernotapps() {
		this.anzhuangorxiezai="安装";
		Map<String, Long> map=new HashMap<String, Long>();
		map.put("status", 0L);
		map.put("isdisplay", 0L);
		Long userid=LoginUtil.getLoginUserId();
		map.put("userid", userid);
		int result=irpAppService.findappusernotdisplaycount(map);
		//查询配置表
		int ipagesize=Integer.parseInt(SysConfigUtil.getSysConfigValue("APPPAGESIZE"));
		this.setPageSize(ipagesize);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),result);
		irpApps=irpAppService.findappusernotdisplay(pageUtil,map);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	//跳转
	public String selectAllexporttype() {
		return SUCCESS;
	}
	//导出表单
	public String appquestionExportform() {
		return SUCCESS;
	}
	
	  /**
     * 根据id获得用户在页面显示的名字
     * @param _userid
     * @return
     */
    public String getShowPageViewNameByUserId(Long _userid){
    	return this.irpUserService.findShowNameByUserid(_userid);
    }
	
	
}