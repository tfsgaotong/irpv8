package com.tfs.irp.leaveapply.web;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.assewarn.entity.IrpAssewarn;
import com.tfs.irp.assewarn.entity.IrpAssewarnExample;
import com.tfs.irp.assewarn.service.IrpAssewarnService;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.entity.IrpAttachedInfo;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.leaveapply.dao.IrpLeaveapplyDAO;
import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.leaveapply.entity.IrpLeaveapplyExample;
import com.tfs.irp.leaveapply.entity.IrpLeaveapplyExample.Criteria;
import com.tfs.irp.leaveapply.entity.IrpLeaveapplystatus;
import com.tfs.irp.leaveapply.service.IrpLeaveapplyService;
import com.tfs.irp.leavechecker.entity.IrpCheckerLink;
import com.tfs.irp.leavechecker.service.IrpCheckerLinkService;
import com.tfs.irp.leaveconf.entity.IrpLeaveconfig;
import com.tfs.irp.leaveconf.service.IrpLeaveconfigService;
import com.tfs.irp.leaveoper.entity.IrpLeaveoper;
import com.tfs.irp.leaveoper.service.IrpLeaveoperService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.role.dao.IrpUserroleLinkDAO;
import com.tfs.irp.role.entity.IrpUserroleLinkExample;
import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userRandom.util.EmailUtils;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.sms.SmsUtil;

public class IrpLeaveapplyAction extends ActionSupport{
	/**
	 * 通知方式
	 */
	private String[] warnMenthod;
	public String[] getWarnMenthod() {
		return warnMenthod;
	}
	public void setWarnMenthod(String[] warnMenthod) {
		this.warnMenthod = warnMenthod;
	}
	/**
	 * 存放会议通知类型的集合
	 */
	private List<IrpAssewarn> assewarnList;
	private IrpAssewarnService assewarnService;
	public List<IrpAssewarn> getAssewarnList() {
		return assewarnList;
	}
	public void setAssewarnList(List<IrpAssewarn> assewarnList) {
		this.assewarnList = assewarnList;
	}
	public IrpAssewarnService getAssewarnService() {
		return assewarnService;
	}
	public void setAssewarnService(IrpAssewarnService assewarnService) {
		this.assewarnService = assewarnService;
	}
	private Integer change;
	public Integer getChange() {
		return change;
	}
	public void setChange(Integer change) {
		this.change = change;
	}
	private IrpUser  irpuserforHolidday;
	public IrpUser getIrpuserforHolidday() {
		return irpuserforHolidday;
	}
	public void setIrpuserforHolidday(IrpUser irpuserforHolidday) {
		this.irpuserforHolidday = irpuserforHolidday;
	}
	private String amountJsonString;	
	public String getAmountJsonString() {
		return amountJsonString;
	}
	public void setAmountJsonString(String amountJsonString) {
		this.amountJsonString = amountJsonString;
	}
	private String xaxisJasonString;
	public String getXaxisJasonString() {
		return xaxisJasonString;
	}
	public void setXaxisJasonString(String xaxisJasonString) {
		this.xaxisJasonString = xaxisJasonString;
	}
    private String hoursarray;	
	private String daytimes;
	public String getDaytimes() {
		return daytimes;
	}
	public void setDaytimes(String daytimes) {
		this.daytimes = daytimes;
	}
	public String getHoursarray() {
		return hoursarray;
	}
	public void setHoursarray(String hoursarray) {
		this.hoursarray = hoursarray;
	}
	private String groups;
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	private String usernamejson;// 名称
	private String latejson;// 名称
	private String earlyjson;// 名称
	public String getUsernamejson() {
		return usernamejson;
	}

	public void setUsernamejson(String usernamejson) {
		this.usernamejson = usernamejson;
	}

	public String getLatejson() {
		return latejson;
	}

	public void setLatejson(String latejson) {
		this.latejson = latejson;
	}

	public String getEarlyjson() {
		return earlyjson;
	}

	public void setEarlyjson(String earlyjson) {
		this.earlyjson = earlyjson;
	}
	private String fileName;//搜索名字
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	private String searchName;//搜索名字
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	private String orderField = "";

	private String orderBy = "";
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
	private String nowtime;
	public String getNowtime() {
		return nowtime;
	}

	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	private IrpUserroleLinkDAO irpUserroleLinkDAO;
	public IrpUserroleLinkDAO getIrpUserroleLinkDAO() {
		return irpUserroleLinkDAO;
	}

	public void setIrpUserroleLinkDAO(IrpUserroleLinkDAO irpUserroleLinkDAO) {
		this.irpUserroleLinkDAO = irpUserroleLinkDAO;
	}
	/**
	 * 
	 */
	private String opinion;
	
	private IrpCheckerLinkService  irpCheckerLinkService;

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public IrpCheckerLinkService getIrpCheckerLinkService() {
		return irpCheckerLinkService;
	}

	public void setIrpCheckerLinkService(IrpCheckerLinkService irpCheckerLinkService) {
		this.irpCheckerLinkService = irpCheckerLinkService;
	}
	private IrpCheckerLink irpCheckerLink;
	public IrpCheckerLink getIrpCheckerLink() {
		return irpCheckerLink;
	}
	public void setIrpCheckerLink(IrpCheckerLink irpCheckerLink) {
		this.irpCheckerLink = irpCheckerLink;
	}
	private static final long serialVersionUID = 1L;
	private Map<Long, String> userGroups;
	public Map<Long, String> getUserGroups() {
		return userGroups;
	}
	public void setUserGroups(Map<Long, String> userGroups) {
		this.userGroups = userGroups;
	}
	private IrpGroupService irpGroupService;// 组织service
	public IrpGroupService getIrpGroupService() {
		return irpGroupService;
	}
	public void setIrpGroupService(IrpGroupService irpGroupService) {
		this.irpGroupService = irpGroupService;
	}
	private IrpLeaveapplyInfo irpLeaveapplyInfo;
	public IrpLeaveapplyInfo getIrpLeaveapplyInfo() {
		return irpLeaveapplyInfo;
	}
	public void setIrpLeaveapplyInfo(IrpLeaveapplyInfo irpLeaveapplyInfo) {
		this.irpLeaveapplyInfo = irpLeaveapplyInfo;
	}
	private List<IrpAttached> attacheds;
	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}
	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	}
	private List<IrpAttachedInfo> attachedinfos;
	
	public List<IrpAttachedInfo> getAttachedinfos() {
		return attachedinfos;
	}
	public void setAttachedinfos(List<IrpAttachedInfo> attachedinfos) {
		this.attachedinfos = attachedinfos;
	}
	private IrpLeaveapply irpLeaveapply;
	public IrpLeaveapply getIrpLeaveapply() {
		return irpLeaveapply;
	}
	public void setIrpLeaveapply(IrpLeaveapply irpLeaveapply) {
		this.irpLeaveapply = irpLeaveapply;
	}
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}
	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}
	private IrpAttachedService irpAttachedService;
	private IrpAttachedTypeService irpAttachedTypeService;
	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}
	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}
	private List<IrpLeaveapplyInfo> irpLeaveapplyInfos;
	public List<IrpLeaveapplyInfo> getIrpLeaveapplyInfos() {
		return irpLeaveapplyInfos;
	}
	public void setIrpLeaveapplyInfos(List<IrpLeaveapplyInfo> irpLeaveapplyInfos) {
		this.irpLeaveapplyInfos = irpLeaveapplyInfos;
	}
	private IrpLeaveapplyDAO irpLeaveapplyDAO;
    private IrpUserDAO irpUserDAO;
	public IrpUserDAO getIrpUserDAO() {
	return irpUserDAO;
}
public void setIrpUserDAO(IrpUserDAO irpUserDAO) {
	this.irpUserDAO = irpUserDAO;
}
	private IrpLeaveapplyService irpLeaveapplyService;
	private List<IrpLeaveapply>  irpLeaveapplyList;
	 private int pageNum=1;	
	private int pageSize=10;
	private String pageHTML;
	private String applyreason;
	private Date starttime;
	private Date endtime;
	private String userids;
	private String  applystatus;//申请状态
	private String applytypeid;
	private String cruserid;
	private String c_start_end;
	private String starttime1;
	private String endtime1;
	private String marking;
	private Integer type;
	private String leaveapplyid;
	public String getLeaveapplyid() {
		return leaveapplyid;
	}
	public void setLeaveapplyid(String leaveapplyid) {
		this.leaveapplyid = leaveapplyid;
	}
	public String getMarking() {
		return marking;
	}
	public void setMarking(String marking) {
		this.marking = marking;
	}
	public String getApplystatus() {
		return applystatus;
	}
	public void setApplystatus(String applystatus) {
		this.applystatus = applystatus;
	}
	public String getApplytypeid() {
		return applytypeid;
	}
	public void setApplytypeid(String applytypeid) {
		this.applytypeid = applytypeid;
	}
	public String getCruserid() {
		return cruserid;
	}
	public void setCruserid(String cruserid) {
		this.cruserid = cruserid;
	}
	public String getC_start_end() {
		return c_start_end;
	}
	public void setC_start_end(String c_start_end) {
		this.c_start_end = c_start_end;
	}
	public String getStarttime1() {
		return starttime1;
	}
	public void setStarttime1(String starttime1) {
		this.starttime1 = starttime1;
	}
	public String getEndtime1() {
		return endtime1;
	}
	public Date getStarttime() {
		return starttime;
	}



	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}



	public Date getEndtime() {
		return endtime;
	}



	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}



	public void setEndtime1(String endtime1) {
		this.endtime1 = endtime1;
	}
	public String getUserids() {
		return userids;
	}
	public void setUserids(String userids) {
		this.userids = userids;
	}
	public IrpLeaveapplyDAO getIrpLeaveapplyDAO() {
		return irpLeaveapplyDAO;
	}
	public void setIrpLeaveapplyDAO(IrpLeaveapplyDAO irpLeaveapplyDAO) {
		this.irpLeaveapplyDAO = irpLeaveapplyDAO;
	}
	public String getApplyreason() {
		return applyreason;
	}
	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}
	public int getEmergency() {
		return emergency;
	}
	public void setEmergency(int emergency) {
		this.emergency = emergency;
	}
	private int emergency;//紧急程度
	private Long leaveconfigid;
	private String jsonFile;
	
	
	public Long getLeaveconfigid() {
		return leaveconfigid;
	}
	public void setLeaveconfigid(Long leaveconfigid) {
		this.leaveconfigid = leaveconfigid;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageHTML() {
		return pageHTML;
	}
	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}
	public IrpLeaveapplyService getIrpLeaveapplyService() {
		return irpLeaveapplyService;
	}
	public void setIrpLeaveapplyService(IrpLeaveapplyService irpLeaveapplyService) {
		this.irpLeaveapplyService = irpLeaveapplyService;
	}
	
	public List<IrpLeaveapply> getIrpLeaveapplyList() {
		return irpLeaveapplyList;
	}
	public void setIrpLeaveapplyList(List<IrpLeaveapply> irpLeaveapplyList) {
		this.irpLeaveapplyList = irpLeaveapplyList;
	}
private IrpLeaveconfigService irpLeaveconfigService;
	
	public IrpLeaveconfigService getIrpLeaveconfigService() {
		return irpLeaveconfigService;
	}

	public void setIrpLeaveconfigService(IrpLeaveconfigService irpLeaveconfigService) {
		this.irpLeaveconfigService = irpLeaveconfigService;
	}

	private List<IrpLeaveconfig>  irpLeaveconfigs;

	public List<IrpLeaveconfig> getIrpLeaveconfigs() {
		return irpLeaveconfigs;
	}

	public void setIrpLeaveconfigs(List<IrpLeaveconfig> irpLeaveconfigs) {
		this.irpLeaveconfigs = irpLeaveconfigs;
	}
	private IrpUserService irpUserService;
	
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}
	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	private List<IrpUser> irpUsers;
	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}
	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}
	private String checker;
	
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	private IrpLeaveoperService irpLeaveoperService;
	
	public IrpLeaveoperService getIrpLeaveoperService() {
		return irpLeaveoperService;
	}
	public void setIrpLeaveoperService(IrpLeaveoperService irpLeaveoperService) {
		this.irpLeaveoperService = irpLeaveoperService;
	}
    private String searchWord;
	
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
	private String searchType;
	private int isMangcenter;//是否属于管理中心  1-是
	private String auditinfo;
	public String getAuditinfo() {
		return auditinfo;
	}
	public void setAuditinfo(String auditinfo) {
		this.auditinfo = auditinfo;
	}
	private List<IrpGroup> irpGroups;
	private Long groupId;
	private Long roleId;
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	private String content;
	private String address;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	private String normaljson;
	private String urgentjson;
	private String importantjson;
	private String worktoataltime;	

	public String getWorktoataltime() {
		return worktoataltime;
	}
	public void setWorktoataltime(String worktoataltime) {
		this.worktoataltime = worktoataltime;
	}
	public String getNormaljson() {
		return normaljson;
	}


	public void setNormaljson(String normaljson) {
		this.normaljson = normaljson;
	}

	public String getUrgentjson() {
		return urgentjson;
	}

	public void setUrgentjson(String urgentjson) {
		this.urgentjson = urgentjson;
	}

	public String getImportantjson() {
		return importantjson;
	}

	public void setImportantjson(String importantjson) {
		this.importantjson = importantjson;
	}
     private String groupIds;	
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	private String groupname;
	private String worktimeNum;
	private String totalTime;
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	private String bttext;
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getWorktimeNum() {
		return worktimeNum;
	}

	public void setWorktimeNum(String worktimeNum) {
		this.worktimeNum = worktimeNum;
	}

	public String getBttext() {
		return bttext;
	}

	public void setBttext(String bttext) {
		this.bttext = bttext;
	}
	private String isqusertionordoc = "2";
	
public String getIsqusertionordoc() {
		return isqusertionordoc;
	}
	public void setIsqusertionordoc(String isqusertionordoc) {
		this.isqusertionordoc = isqusertionordoc;
	}
private IrpChannelService irpChannelService;// 栏目service
	
	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	/**
	 * 获得请假表的所有数据(登录用户)
	 */
	public String leaveList() {
		irpLeaveconfigs=this.irpLeaveconfigService.getAllList();
		String result="success";
		String status = ServletActionContext.getRequest().getParameter(
				"type");
		
		if(status!=""){
		String type = ServletActionContext.getRequest().getParameter(
				"queryType");		
		int nDataCount=this.irpLeaveapplyService.findListNums(IrpLeaveapply.LEAVE,Integer.parseInt(status),LoginUtil.getLoginUserId());
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		irpLeaveapplyList = this.irpLeaveapplyService.getAllList(IrpLeaveapply.LEAVE,pageUtil,Integer.parseInt(status),LoginUtil.getLoginUserId());		
		irpLeaveapplyInfos=this.coverListApply(irpLeaveapplyList);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		if ("1".equals(type)) {
			result = "page";
		}}
		
		return result;
	}
	/**
	 * 选择请假的审核人
	 * @return
	 */
	public String selectchecker(){
		if(groupId==null){
			groupId= IrpGroup.GROUP_ROOTID_PUBLIC;
		}
		userids=userids;
		if(type==10){
			roleId =Long.parseLong(SysConfigUtil.getSysConfigValue("LEAVEAPPLY_ROLE_ID")) ;
		}else{
			roleId =Long.parseLong(SysConfigUtil.getSysConfigValue("WORKAPPLY_ROLE_ID")) ;
		}
		int nCount=irpUserService.countByByGroupIdAndRoleId(cruserid,groupId,roleId,searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nCount);		
		irpUsers=irpUserService.findUsersByGroupIdAndRoleId(cruserid,groupId, roleId, pageUtil, searchWord, searchType);
		this.pageHTML = pageUtil.getClientPageHtml("pagecheck(#PageNum#)");  
		return SUCCESS;
	}
	/**
	 * 查询请假的审核人
	 * @return
	 */
	public String querychecker(){
		if(groupId==null){
			groupId= IrpGroup.GROUP_ROOTID_PUBLIC;
		}
		userids=userids;
		if(type==10){
			roleId =Long.parseLong(SysConfigUtil.getSysConfigValue("LEAVEAPPLY_ROLE_ID")) ;
		}else{
			roleId =Long.parseLong(SysConfigUtil.getSysConfigValue("WORKAPPLY_ROLE_ID")) ;
		}
		int nCount=irpUserService.countByByGroupIdAndRoleId(cruserid,groupId,roleId,searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nCount);		
		irpUsers=irpUserService.findUsersByGroupIdAndRoleId(cruserid,groupId, roleId, pageUtil, searchWord, searchType);
		setIrpGroups(irpGroupService.findGroupsByParentId(1l,IrpGroup.GROUP_ROOTID_PUBLIC));
		this.pageHTML = pageUtil.getClientPageHtml("pagecheck(#PageNum#)");  
		return SUCCESS;
	}
	
	
	/**
	 * 申请请假的跳转页
	 * @return
	 */
	public String addleaveview(){
                 try {
					irpuserforHolidday=this.irpUserDAO.selectByPrimaryKey(LoginUtil.getLoginUserId());
				} catch (SQLException e) {
					e.printStackTrace();
				}
              
		//获得用户所属的组织
	  userGroups = irpGroupService.findGroupIdsByUserId(LoginUtil.getLoginUserId());
		
	  irpLeaveconfigs=this.irpLeaveconfigService.getAllList();
	  findAllAssewarn();//通知类型
		
		return SUCCESS;
	}
	/**
	 * 申请请假的跳转页
	 * @return
	 */
	public String toupdate(){
	
		
		//获得用户所属的组织
		userGroups = irpGroupService.findGroupIdsByUserId(LoginUtil.getLoginUserId());
		 attacheds = this.irpAttachedService.getAttachedByAttDocId(Long.parseLong(leaveapplyid),
					IrpAttached.LEAVETYPE);
		 
		irpLeaveconfigs=this.irpLeaveconfigService.getAllList();
		attachedinfos=new ArrayList<IrpAttachedInfo>();
		
		if(attacheds!=null && attacheds.size()>0){ 
			
			for (int j = 0; j < attacheds.size(); j++) {
				IrpAttachedInfo s=new IrpAttachedInfo();
				s.setAttachedid(attacheds.get(j).getAttachedid());
				s.setAttdocid(attacheds.get(j).getAttdocid());
				s.setAttdesc(attacheds.get(j).getAttdesc());
				s.setAttfile(attacheds.get(j).getAttfile());
				if(isRadio(attacheds.get(j).getAttfile())==1){
					
					s.setFlag("true");
				}else{
					s.setFlag("false");
					
				}
				 
				attachedinfos.add(s);
			}
			 
		} 
		if(leaveapplyid!=null){
			irpLeaveapply=this.irpLeaveapplyService.getById(Long.parseLong(leaveapplyid));
			irpLeaveapplyInfo=this.covertIrpLeaveapply(irpLeaveapply, 0);
			findAllAssewarn();
			String warnString=irpLeaveapply.getWarnid()==null?"":irpLeaveapply.getWarnid().toString();
			List<IrpAssewarn> list=new ArrayList<IrpAssewarn>();
			list.addAll(assewarnList);
			assewarnList.clear();
			for (IrpAssewarn warn : list) {
				if(warnString.indexOf(warn.getWarnid().toString())!=-1){
					warn.setCruserid(0L);
				}
				assewarnList.add(warn);
			}
		}
		if(irpLeaveapply.getCruserid().equals(LoginUtil.getLoginUserId())){
			 return  SUCCESS;
		 }else {
			 return ERROR;
		 }
	}
	/**
	 * 增加请假
	 */
    public void saveleave(){
    	
    	Long msg=0L;
    	IrpLeaveapply irpLeaveapply=new IrpLeaveapply();
    	
    	 Long applyid = TableIdUtil.getNextId(IrpLeaveapply.TABLE_NAME);
    	 irpLeaveapply.setCheckmore(IrpLeaveapply.CHECKMORE1);
    	 irpLeaveapply.setStarttime(DateUtils.getDateByYMDHM(starttime1));
    	 irpLeaveapply.setApplyreason(applyreason);
    	 irpLeaveapply.setLeaveapplyid(applyid);
    	 irpLeaveapply.setEndtime(DateUtils.getDateByYMDHM(endtime1));
    	 irpLeaveapply.setApplytypeid(leaveconfigid);
    	 irpLeaveapply.setCrtime(DateUtils.getDateByYMDHM(nowtime));
    	 irpLeaveapply.setTitle(title);
    	 irpLeaveapply.setEmergency(emergency);
    	 irpLeaveapply.setLeavemarking(IrpLeaveapply.LEAVE);
    	 irpLeaveapply.setApplystatus(IrpLeaveapply.UNPASS);   
    	 irpLeaveapply.setLeavedays(Float.parseFloat(leavedays));
    	 irpLeaveapply.setCruserid(LoginUtil.getLoginUserId());
    		//会议通知类型
  		StringBuffer warnMenthodBuffer=new StringBuffer();
  		if(null!=warnMenthod&&warnMenthod.length>0){
  			for (int i = 0; i < warnMenthod.length; i++) {
  				warnMenthodBuffer.append(warnMenthod[i]);
  			}
  			irpLeaveapply.setWarnid(Integer.parseInt(warnMenthodBuffer.toString()));
  		}
    	 //增加第一个级联审核人
    	 irpCheckerLink=new IrpCheckerLink();
    	 irpCheckerLink.setLeaveapplyid(applyid);
    	 irpCheckerLink.setNextuserid(Long.parseLong(userids));
    	 irpCheckerLink.setLeavemarking(IrpLeaveapply.LEAVE);
    	 int flag=this.irpCheckerLinkService.addIrpCheckerLink(irpCheckerLink);
    	 
    		if (jsonFile != null && jsonFile != "") {    		
				// 如果存在附件，则添加附件
				JSONArray _Array = JSONArray.fromObject(jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String sattfile = jsonObject.getString("attfile");
					String sattdesc = jsonObject.getString("attdesc");
					String sattlinkalt = jsonObject.getString("attlinkalt");
					String seditversions = jsonObject.getString("editversions");
					String attflag = jsonObject.getString("attflag");
					// 获得文件类型
					Long typeid = irpAttachedTypeService
							.findAttachedTypeIdByFileExt(FileUtil
									.findFileExt(sattfile));
					// 入库
					addAttQuestionFile(Integer.parseInt(attflag), sattfile,
							typeid, applyid, sattdesc, sattlinkalt, seditversions,
							false, null, false);
				}
			}
    	 
    	 
    	 try {
    		irpLeaveapplyDAO.insertSelective(irpLeaveapply);
    		//请假单与审核人之间的联系
    		
    		 msg=this.irpLeaveoperService.addOper(applyid,userids);
    		if(msg!=null&&flag>0){
    			//像下一个审核人发送私信
    				 Long userid=Long.parseLong(userids);
    				 if(null!=warnMenthodBuffer){
	    				 String warn_1=warnMenthodBuffer.toString();
	 					if(warn_1.indexOf("1")!=-1){//私信
	 						this.sendAddMessage(userid,applyid,0,IrpLeaveapply.LEAVE);
	 					}
	 					if(warn_1.indexOf("2")!=-1){//短信
	 						this.sendSms(userid,applyid,0,IrpLeaveapply.LEAVE);
	 					}
	 					if(warn_1.indexOf("3")!=-1){//邮件
	 						this.sendEamil(userid,applyid,0,IrpLeaveapply.LEAVE);
	 					}
    				 }
    			}
    		
    		
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		ActionUtil.writer(1 + "");

    	 
    	
    	
    	
    }
    /**
     * 发送短信
     * @param _cruserid
     * @param _applyid
     * @param _isthrough
     * @param _marking
     */
    private void sendSms(Long _cruserid,Long  _applyid,Integer _isthrough,Integer _marking) {
    	Date nowtime = DateUtils.getNOWTime();
    	IrpUser user=irpUserService.findUserByUserId(_cruserid);
		HttpServletRequest request = ServletActionContext.getRequest();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		StringBuffer sb = new StringBuffer();
		//sb.append(LoginUtil.getLoginUser().getTruename());
		sb.append("在");
		sb.append(DateUtils.getDateByFormat(nowtime, "yyyy-MM-dd HH:mm:ss"));
		sb.append("您收到了一份");
		if(_marking==10){//请假
			//1 审核后发送的邮件
			if(_isthrough.equals(1)){
				sb.append("请假审核通知:["+title+"];请您尽快前往处理。");
			}else{
				sb.append("请假申请:["+title+"];请您尽快前往处理。");
			}	
		}else if(_marking==20){//加班
			//1 审核后发送的邮件
			if(_isthrough.equals(1)){
				sb.append("加班审核通知：["+title+"];请您尽快前往处理。");
			}else{
				sb.append("加班申请：["+title+"];请您尽快前往处理。");
			}
		}
		if(user.getMobile()!=null&&!"".equals(user.getMobile())){
			SmsUtil.sendMsg_webchinese(sb.toString(), user.getMobile());
		}
    }
    /**
     * 发送邮件
     * @param _cruserid
     * @param _applyid
     * @param _isthrough
     * @param _marking
     */
    private void sendEamil(Long _cruserid,Long  _applyid,Integer _isthrough,Integer _marking) {
    	Date nowtime = DateUtils.getNOWTime();
    	IrpUser user=irpUserService.findUserByUserId(_cruserid);
		HttpServletRequest request = ServletActionContext.getRequest();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		StringBuffer sb = new StringBuffer();
		sb.append(LoginUtil.getLoginUser().getTruename());
		sb.append("在");
		sb.append(DateUtils.getDateByFormat(nowtime, "yyyy-MM-dd HH:mm:ss"));
		String heard="";
		if(_marking==10){//请假
			heard="请假消息通知";
			//1 审核后发送的邮件
			if(_isthrough.equals(1)){
				sb.append("发送了请假审核通知:"+title+";请您尽快前往处理。");
			}else{
				sb.append("发送了请假申请:"+title+";请您尽快前往处理。");
			}		
		}else if(_marking==20){//加班
			heard="加班消息消息通知";
			//1 审核后发送的邮件
			if(_isthrough.equals(1)){
				sb.append("发送了加班审核通知："+title+";请您尽快前往处理。");
			}else{
				sb.append("发送了加班申请："+title+";请您尽快前往处理。");
			}
		}
		if(user.getEmail()!=null&&!"".equals(user.getEmail())){
			EmailUtils.sendMeetingEmail(user, sb.toString(), heard);
		}
    }
	/**
	 * 请假发送私信 创建
	 * 
	 * @return
	 */
	private void sendAddMessage(Long _cruserid,Long  _applyid,Integer _isthrough,Integer _marking) {
		Date nowtime = DateUtils.getNOWTime();
		HttpServletRequest request = ServletActionContext.getRequest();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		StringBuffer sb = new StringBuffer();
		sb.append(LoginUtil.getLoginUser().getTruename());
		sb.append("在");
		sb.append(DateUtils.getDateByFormat(nowtime, "yyyy-MM-dd HH:mm:ss"));
		//1 审核后发送的私信
		if(_isthrough.equals(1)){
			sb.append("发送了请假审核通知");
		}else{
			sb.append("发送了请假申请");
		}		
		sb.append("<a href='javascript:void(0) ' onclick='toworkDoPage("+_applyid+","+_marking+")' ><strong>" + title + "</strong></a>");
		IrpMessageContent mc = new IrpMessageContent();
		mc.setContent(sb.toString());
		mc.setMessageid(TableIdUtil.getNextId("IRP_MESSAGE_CONTENT"));
		mc.setFromUid(_cruserid);
		mc.setCruserid(LoginUtil.getLoginUserId());
		mc.setCrtime(nowtime);
		mc.setCrisdel(_applyid);
		messageService.addMessageContentForLeave(mc);
	}
	/**
	 * 加班发送私信 创建
	 * 
	 * @return
	 */
	private void sendWorkMessage(Long _cruserid,Long  _applyid,Integer _isthrough,Integer _marking) {
		Date nowtime = DateUtils.getNOWTime();
		HttpServletRequest request = ServletActionContext.getRequest();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		StringBuffer sb = new StringBuffer();
		sb.append(LoginUtil.getLoginUser().getTruename());
		sb.append("在");
		sb.append(DateUtils.getDateByFormat(nowtime, "yyyy-MM-dd HH:mm:ss"));
		//1 审核后发送的私信
		if(_isthrough.equals(1)){
			sb.append("发送了加班审核通知");
		}else{
			sb.append("发送了加班申请");
		}
		sb.append("<a href='javascript:void(0) ' onclick='toworkDoPage("+_applyid+","+_marking+")' ><strong>" + title + "</strong></a>");
		IrpMessageContent mc = new IrpMessageContent();
		mc.setContent(sb.toString());
		mc.setMessageid(TableIdUtil.getNextId("IRP_MESSAGE_CONTENT"));
		mc.setFromUid(_cruserid);
		mc.setCruserid(LoginUtil.getLoginUserId());
		mc.setCrtime(nowtime);
		messageService.addMessageContent(mc);
	}
	private IrpMessageContentService messageService;

	public IrpMessageContentService getMessageService() {
		return messageService;
	}
	public void setMessageService(IrpMessageContentService messageService) {
		this.messageService = messageService;
	}
	private Long applyid;
	
	
	public Long getApplyid() {
		return applyid;
	}
	public void setApplyid(Long applyid) {
		this.applyid = applyid;
	}
	
    /**
	 * 获得加班表的所有数据(根据他们的不同状态)
	 */
	public String overTimeLeaveList() {
		irpLeaveconfigs=this.irpLeaveconfigService.getAllOverTimeType();
		HttpServletRequest request = ServletActionContext.getRequest();
		String nextPage=request.getParameter("nextPage");
		String _status = request.getParameter("status");
			Integer status=0;
			if(_status!=null){
				status=Integer.valueOf(_status);
			}
			int nDataCount=this.irpLeaveapplyService.findListNums(IrpLeaveapply.WORK,status,LoginUtil.getLoginUserId());
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
			irpLeaveapplyList = this.irpLeaveapplyService.getAllList(IrpLeaveapply.WORK,pageUtil,status,LoginUtil.getLoginUserId());
			irpLeaveapplyInfos=this.coverListApply(irpLeaveapplyList);
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");	
			if(nextPage!=null&&nextPage.equals("1")){
				return "page";
			}else{
				return SUCCESS;
			}		
	}
	/**
	 * 跳转加班表单
	 * @return
	 */
	public String addOverTime(){
		//获得用户所属的组织
		userGroups = irpGroupService.findGroupIdsByUserId(LoginUtil.getLoginUserId());
		irpLeaveconfigs=this.irpLeaveconfigService.getAllOverTimeType();
		findAllAssewarn();//通知类型
		return SUCCESS;
	}
	
  
	/**
	 * 添加加班信息
	 */
    public void saveOverTimeInfo(){
    	Long msg=0l;
    	IrpLeaveapply irpLeaveapply=new IrpLeaveapply();
    	Date nowtime = DateUtils.getNOWTime();
    	String[] _userid=userids.split(",");
    	
    	 Long applyid = TableIdUtil.getNextId(IrpLeaveapply.TABLE_NAME);
    	 irpLeaveapply.setStarttime(DateUtils.getDateByYMDHM(starttime1));
    	 irpLeaveapply.setApplyreason(applyreason);
    	 irpLeaveapply.setCheckmore(IrpLeaveapply.CHECKMORE1);
    	 irpLeaveapply.setTitle(title);
    	 irpLeaveapply.setLeaveapplyid(applyid);
    	 irpLeaveapply.setEndtime(DateUtils.getDateByYMDHM(endtime1));
    	 irpLeaveapply.setApplytypeid(leaveconfigid);
    	 irpLeaveapply.setCrtime(nowtime);
    	 irpLeaveapply.setEmergency(emergency);
    	 irpLeaveapply.setLeavemarking(IrpLeaveapply.WORK);
    	 irpLeaveapply.setApplystatus(IrpLeaveapply.UNPASS);
    	 irpLeaveapply.setCruserid(LoginUtil.getLoginUserId());
    	 irpLeaveapply.setLeavedays(Float.parseFloat(leavedays));
    	 irpLeaveapply.setContent(content);
    	 irpLeaveapply.setAddress(address);
    		//会议通知类型
 		StringBuffer warnMenthodBuffer=new StringBuffer();
 		if(null!=warnMenthod&&warnMenthod.length>0){
 			for (int i = 0; i < warnMenthod.length; i++) {
 				warnMenthodBuffer.append(warnMenthod[i]);
 			}
 			irpLeaveapply.setWarnid(Integer.parseInt(warnMenthodBuffer.toString()));
 		}else{
 			irpLeaveapply.setWarnid(0);
 		}
 		if (jsonFile != null && jsonFile != "") {    		
				// 如果存在附件，则添加附件
				JSONArray _Array = JSONArray.fromObject(jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String sattfile = jsonObject.getString("attfile");
					String sattdesc = jsonObject.getString("attdesc");
					String sattlinkalt = jsonObject.getString("attlinkalt");
					String seditversions = jsonObject.getString("editversions");
					String attflag = jsonObject.getString("attflag");
					// 获得文件类型
					Long typeid = irpAttachedTypeService
							.findAttachedTypeIdByFileExt(FileUtil
									.findFileExt(sattfile));
					// 入库
					addAttQuestionFile(Integer.parseInt(attflag), sattfile,
							typeid, applyid, sattdesc, sattlinkalt, seditversions,
							false, null, false);
				}
			}
    	 try {
    		irpLeaveapplyDAO.insertSelective(irpLeaveapply);
    		//请假单与审核人之间的联系
    		 msg=this.irpLeaveoperService.addOper(applyid,userids);
    		if(msg!=null){
    			applyreason = irpLeaveapply.getApplyreason();
    			 for(int i=0;i<_userid.length;i++){
    				 Long userid=Long.parseLong(_userid[i]);
    				 if(null!=warnMenthodBuffer){
	    				 String warn_1=warnMenthodBuffer.toString();
	 					if(warn_1.indexOf("1")!=-1){//私信
	 						this.sendWorkMessage(userid,applyid,0,IrpLeaveapply.LEAVE);
	 					}
	 					if(warn_1.indexOf("2")!=-1){//短信
	 						this.sendSms(userid,applyid,0,IrpLeaveapply.LEAVE);
	 					}
	 					if(warn_1.indexOf("3")!=-1){//邮件
	 						this.sendEamil(userid,applyid,0,IrpLeaveapply.LEAVE);
	 					}
    				 }
    			}
    			
    		}
  		 
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		ActionUtil.writer(1 + ""); 	
    }
    /**
   	 * 管理中心跳转页面
   	 */
   	public String getLeaveList() {
   			return SUCCESS;
   	}
   	
    /**
   	 * 获得管理中心的所有数据
   	 */
   	public String managerLeaveList() {
   		HttpServletRequest request = ServletActionContext.getRequest();
   		String nextPage=request.getParameter("nextPage");
   		String _status = request.getParameter("status");
   		Integer status=0;
   		if(_status!=null){
   				status=Integer.valueOf(_status);
   		}
   		List<Long> list = null;
   		if(status!=0){
			if(status.equals(20)){			
				 list =this.irpLeaveoperService.selLeaveapplyidByUserid(20);
			}else if(status.equals(40)){
				List<Integer> list2=new ArrayList<Integer>();
				list2.add(10);list2.add(30);			
				 list =this.irpLeaveoperService.selLeaveapplyidByUseridList(list2);
			}
		}	
   	
   		
   		if(list==null||list.size()<=0){
   			list.add(0L);
   		}
   		int nDataCount=this.irpLeaveapplyService.findListOverTimeNums(type,status,list);
   		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
   		irpLeaveapplyList = this.irpLeaveapplyService.getAll(pageUtil,type,status,list);
   		//irpCheckerLinkService.selAllByPageAndExample(_pageUtil, example);
   		irpLeaveapplyInfos=this.coverListApply(irpLeaveapplyList);
   		this.pageHTML = pageUtil.getPageHtml("findFlowConn(#PageNum#)");	
   		if(nextPage!=null&&nextPage.equals("1")){
   			return "page";
   		}else{
   			return SUCCESS;
   		}		
   	}
   
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	 /**
     * 查询请假操作
     * @return
     */
   public String leaveListQuery(){
	  
	  
	   Date starttimes=null;
		Date endtimes=null;
		LogTimeUtil ltu = new LogTimeUtil();
	   if (c_start_end.equals("logs_week")) {
		   //本周
		   starttimes= ltu.getWeek();
		   endtimes  = ltu.getLastWeek();
		}else if(c_start_end.equals("logs_month")){
			//本月
			starttimes = ltu.getMonth();
			endtimes = ltu.getLastMonth();
		}else if(c_start_end.equals("logs_quarter")){
			 //本季
			starttimes = ltu.getQuarter();
			endtimes = ltu.getLastQuarter();
		}else if(c_start_end.equals("logs_appoint_date")){
			 //指定
       	 
            starttimes=starttime;
 	        Date date = endtime;
            date.setHours(24);
            endtimes=date;
			
			
			
			
		}
		
		int nDataCount=this.irpLeaveapplyService.findListQueryNums(marking,starttimes,endtimes,applystatus,applytypeid,LoginUtil.getLoginUserId(),emergency);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
				
		
		irpLeaveapplyList=this.irpLeaveapplyService.getQueryList(pageUtil,marking,starttimes,endtimes,applystatus,applytypeid,LoginUtil.getLoginUserId(),emergency);
		irpLeaveapplyInfos=this.coverListApply(irpLeaveapplyList);
		this.pageHTML = pageUtil.getPageHtml("pageWithCondition(#PageNum#)");
		String result="";
		if(marking.equals("10")){
			result="page";
		}else if(marking.equals("20")){
			result="pagework";
		}
	   
	return result;
	   
	   
   }
   /**
    * 对list的某些字段进行转化
    * @param list
    * @return
    */
   public List<IrpLeaveapplyInfo> coverListApply(List<IrpLeaveapply> list){
	   List<IrpLeaveapplyInfo> lists=new ArrayList<IrpLeaveapplyInfo>();
	   for(IrpLeaveapply s:list){
		   IrpLeaveapplyInfo sinfo=new IrpLeaveapplyInfo();
		   sinfo.setLeaveapplyid(s.getLeaveapplyid());
		   sinfo.setApplyreason(s.getApplyreason());
		   sinfo.setLeavedays(s.getLeavedays());
		   sinfo.setAuditinfo(s.getAuditinfo());
		   sinfo.setContent(s.getContent());
		   sinfo.setAddress(s.getAddress());
		   sinfo.setCruseridl(s.getCruserid());
		   //这些用户具有审批权限
		
		   List<IrpLeaveoper> list2 =this.irpLeaveoperService.getOperByapplyId(s.getLeaveapplyid(),LoginUtil.getLoginUserId());
			 
		   for(IrpLeaveoper se:list2){
			  
			   
			if(se.getOperstatus().equals(10)){
				   sinfo.setAdvise("已同意");
			   }else if(se.getOperstatus().equals(20)){
				   sinfo.setAdvise("未审核");
			   }else if(se.getOperstatus().equals(30)){
				   sinfo.setAdvise("已拒绝");
			   }
			   
		   }
		   if(s.getApplystatus().equals(10)){
			   sinfo.setApplystatus("已同意");
		   }else if(s.getApplystatus().equals(20)){
			   sinfo.setApplystatus("未审核");
		   }else if(s.getApplystatus().equals(30)){
			   sinfo.setApplystatus("已拒绝");
		   }
		   IrpUser irpUser=null;  
		   try {
			irpUser = irpUserDAO.selectByPrimaryKey(s.getCruserid());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		   sinfo.setCheckmore(s.getCheckmore());
		   sinfo.setCruserid(LoginUtil.getUserNameString(irpUser));
		   sinfo.setCrtime(s.getCrtime());
		   sinfo.setStarttime(s.getStarttime());
		   sinfo.setEndtime(s.getEndtime());
		   sinfo.setOvertimecontent(s.getOvertimecontent());
		   sinfo.setOvertimeadress(s.getOvertimeadress());
		   sinfo.setTitle(s.getTitle());
		   if(s.getEmergency().equals(10)){
			   
			   sinfo.setEmergency("正常");
		   }else if(s.getEmergency().equals(20)){
			   sinfo.setEmergency("重要");
		   }else if(s.getEmergency().equals(30)){
			   sinfo.setEmergency("紧急");
		   }
		  
		   //请假类型
		   IrpLeaveconfig irpLeaveconfig =this.irpLeaveconfigService.selLeaveConfigById(s.getApplytypeid());
		   sinfo.setApplytypeid(irpLeaveconfig.getLeaveconfigname());
		   //增加审核人
		 List<IrpLeaveoper> opers = this.irpLeaveoperService.getListByapplyId(s.getLeaveapplyid());
		
		 String checker2="";
		 if(opers.size()>0){
		 for(IrpLeaveoper o:opers){
			 IrpUser irpUsero=null; 
			 try {
				irpUsero = irpUserDAO.selectByPrimaryKey(o.getUserid());
				checker2+=","+irpUsero.getName();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			 
		 }
		 sinfo.setChecker(checker2.substring(1));
		 }else{
			 sinfo.setChecker(checker2);
			 
		 }		
		   lists.add(sinfo);
	   }
	return lists;
	   
   }
   
   /***
	 * 添加附件信息到数据库
	 * 
	 * @param _attflag
	 * @param _sAttFile
	 * @param TypeId
	 * @param document
	 * @param _sAttDesc
	 * @param _sAttLinkAlt
	 * @param _sEditversions
	 * @param isClient
	 * @param _touChannelid
	 * @param addUserFile
	 * @return
	 */
	private ArrayList<Long> addAttQuestionFile(Integer _attflag,
			String _sAttFile, Long TypeId, Long _docid, String _sAttDesc,
			String _sAttLinkAlt, String _sEditversions, boolean isClient,
			Long _touChannelid, Boolean addUserFile) {
		ArrayList<Long> _attachedids = new ArrayList<Long>();
		String filePath = SysFileUtil.getFilePathByFileName(_sAttFile);
		File newFile = new File(filePath);
		String newFileName = "";
		if (newFile.exists()) {
			if (IrpAttachedType.JPG_FIELD_NAME.toString().equals(
					TypeId.toString())) { // 这是创建文档之后的附件名字
				newFileName = SysFileUtil.saveFileDoc(newFile,
						SysFileUtil.FILE_TYPE_ATTACHED_PIC, addUserFile);
			} else {
				newFileName = SysFileUtil.saveFileDoc(newFile,
						SysFileUtil.FILE_TYPE_ATTACHED_FILE, addUserFile);
			}
			// 删除临时表中的文件
			String newFilePath = SysFileUtil.getFilePathByFileName(newFileName);

			Long _attachedid = irpAttachedService.addFile(_docid, 0L,
					newFileName, _sAttLinkAlt, _sAttDesc,
					IrpAttached.LEAVETYPE, newFilePath,
					Integer.parseInt(_sEditversions), TypeId, _attflag);
			_attachedids.add(_attachedid);
		}
		return _attachedids;

	}
	/**
	 * 根据ID获得对象
	 * @return
	 */
 public String getleavebyid(){
	 List<Long> checkid = new ArrayList<Long>();
	 Long loginuserid = LoginUtil.getLoginUserId();
	 if(leaveapplyid!=null){	
		 //checkid = irpCheckerLinkService.getAllCheckUserID(Long.parseLong(leaveapplyid));
		 checkid= irpLeaveoperService.getCheckuserids(Long.parseLong(leaveapplyid));
		 irpLeaveapply =this.irpLeaveapplyService.getById(Long.parseLong(leaveapplyid));
		//查询会议通知类型
			String warnString=irpLeaveapply.getWarnid()==null?"":irpLeaveapply.getWarnid().toString();
			StringBuffer warnBuffer=new StringBuffer();
			if(warnString.indexOf("1")!=-1){
				warnBuffer.append("私信；");
			}
			if(warnString.indexOf("2")!=-1){
				warnBuffer.append("短信；");
			}
			if(warnString.indexOf("3")!=-1){
				warnBuffer.append("邮箱；");
			}
			irpLeaveapply.setWarnMenthodString(warnBuffer.toString());
		 checkid.add(irpLeaveapply.getCruserid());
		 attacheds = this.irpAttachedService.getAttachedByAttDocId(Long.parseLong(leaveapplyid),
					IrpAttached.LEAVETYPE);
			attachedinfos=new ArrayList<IrpAttachedInfo>();
			
			if(attacheds!=null && attacheds.size()>0){ 
				
				for (int j = 0; j < attacheds.size(); j++) {
					IrpAttachedInfo s=new IrpAttachedInfo();
					s.setAttachedid(attacheds.get(j).getAttachedid());
					s.setAttdocid(attacheds.get(j).getAttdocid());
					s.setAttdesc(attacheds.get(j).getAttdesc());
					s.setAttfile(attacheds.get(j).getAttfile());
					if(isRadio(attacheds.get(j).getAttfile())==1){
						
						s.setFlag("true");
					}else{
						s.setFlag("false");
						
					}
					 
					attachedinfos.add(s);
				}
				 
			} 
		 
		 irpLeaveapplyInfo=this.covertIrpLeaveapply(irpLeaveapply,0);
		//获得用户所属的组织
		  userGroups = irpGroupService.findGroupIdsByUserId(irpLeaveapply.getCruserid());			  
	 }
	 if(irpLeaveapply.getLeavemarking().equals(10)&&checkid.contains(loginuserid)){
		 return  SUCCESS;
	 }else if(irpLeaveapply.getLeavemarking().equals(20)&&checkid.contains(loginuserid)){
		 return "workbyid";
	 }else{
		 return ERROR;
	 }
	
	
 }
 /**
  * 根据ID获得对象为后台管理员
  * @return
  */
 public String getleavebyidForAdmin(){
	 if(leaveapplyid!=null){
		 irpLeaveapply =this.irpLeaveapplyService.getById(Long.parseLong(leaveapplyid));
		 attacheds = this.irpAttachedService.getAttachedByAttDocId(Long.parseLong(leaveapplyid),
				 IrpAttached.LEAVETYPE);
		 irpLeaveapplyInfo=this.covertIrpLeaveapply(irpLeaveapply,0);
		 //获得用户所属的组织
		 userGroups = irpGroupService.findGroupIdsByUserId(irpLeaveapply.getCruserid());
		 
		 
		 
	 }
	 
			 return SUCCESS;
		
		 
	 
 }
 /**
  * 从审核权限那里传过来的ID
  * @return
  */
 public String getleavebyidFromManager(){
	
	 	 List<Long> checkid = new ArrayList<Long>();
	 	 Long loginuserid = LoginUtil.getLoginUserId();
	 	 if(leaveapplyid!=null){	
	 		 //checkid = irpCheckerLinkService.getAllCheckUserID(Long.parseLong(leaveapplyid));
			 checkid= irpLeaveoperService.getCheckuserids(Long.parseLong(leaveapplyid));
	 		 irpLeaveapply =this.irpLeaveapplyService.getById(Long.parseLong(leaveapplyid));
	 		 if(irpLeaveapply!=null){
	 			 checkid.add(irpLeaveapply.getCruserid());
	 			String warnString=irpLeaveapply.getWarnid()==null?"":irpLeaveapply.getWarnid().toString();
				StringBuffer warnBuffer=new StringBuffer();
				if(warnString.indexOf("1")!=-1){
					warnBuffer.append("私信；");
				}
				if(warnString.indexOf("2")!=-1){
					warnBuffer.append("短信；");
				}
				if(warnString.indexOf("3")!=-1){
					warnBuffer.append("邮箱；");
				}
				irpLeaveapply.setWarnMenthodString(warnBuffer.toString());
	 		 }	 		
	 		 attacheds = this.irpAttachedService.getAttachedByAttDocId(Long.parseLong(leaveapplyid),
	 					IrpAttached.LEAVETYPE);
	 		 attachedinfos=new ArrayList<IrpAttachedInfo>();
			
			if(attacheds!=null && attacheds.size()>0){ 
				
				for (int j = 0; j < attacheds.size(); j++) {
					IrpAttachedInfo s=new IrpAttachedInfo();
					s.setAttachedid(attacheds.get(j).getAttachedid());
					s.setAttdocid(attacheds.get(j).getAttdocid());
					s.setAttdesc(attacheds.get(j).getAttdesc());
					s.setAttfile(attacheds.get(j).getAttfile());
					if(isRadio(attacheds.get(j).getAttfile())==1){
						
						s.setFlag("true");
					}else{
						s.setFlag("false");
						
					}
					 
					attachedinfos.add(s);
				}
				 
			} 
			if(irpLeaveapply!=null){
		 		 irpLeaveapplyInfo=this.covertIrpLeaveapply(irpLeaveapply,isMangcenter);
		 		//获得用户所属的组织
		 		  userGroups = irpGroupService.findGroupIdsByUserId(irpLeaveapply.getCruserid());	
			}		  
	 	 }
	 	if(irpLeaveapply!=null){
	 		 if(irpLeaveapply.getLeavemarking().equals(10)&&checkid.contains(loginuserid)){
		 		 return  SUCCESS;
		 	 }else if(irpLeaveapply.getLeavemarking().equals(20)&&checkid.contains(loginuserid)){
		 		 return "workbyid";
		 	 }else{
		 		 return ERROR;
		 	 }
	 	}else{
	 		return  "workbyid";
	 	}
	 	
	 	
	 	
	
	 
	 
 }


 
 
 public IrpLeaveapplyInfo covertIrpLeaveapply(IrpLeaveapply s,Integer isManager){
	 
	 IrpLeaveapplyInfo sinfo=new IrpLeaveapplyInfo();
	   sinfo.setLeaveapplyid(s.getLeaveapplyid());
	 List<IrpLeaveoper> list2 =this.irpLeaveoperService.getOperByapplyId(s.getLeaveapplyid(),LoginUtil.getLoginUserId());
	 
	   for(IrpLeaveoper se:list2){
		if(se.getOperstatus().equals(10)){
			   sinfo.setAdvise("已同意");
		   }else if(se.getOperstatus().equals(20)){
			   sinfo.setAdvise("未审核");
		   }else if(se.getOperstatus().equals(30)){
			   sinfo.setAdvise("已拒绝");
			 
		   }else{
			   if(s.getApplystatus().equals(10)){
				   sinfo.setAdvise("已同意");
			   }else if(s.getApplystatus().equals(20)){
				   sinfo.setAdvise("未审核");
			   }else if(s.getApplystatus().equals(30)){
				   sinfo.setAdvise("已拒绝");
				 
			   }
			   
		   }
		   
	   }
	
	  IrpCheckerLink irpCheckerLink= this.irpCheckerLinkService.getIrpCheckerLinkByNext(s.getLeaveapplyid(),LoginUtil.getLoginUserId() );
	if(irpCheckerLink.getOpinion()==null||irpCheckerLink.getOpinion()==""){
		sinfo.setOpinion("暂无");
	}else{
		sinfo.setOpinion(irpCheckerLink.getOpinion());
		
	}
	 sinfo.setAuditinfo(s.getAuditinfo());
	  sinfo.setApplyreason(s.getApplyreason());
	   sinfo.setLeavedays(s.getLeavedays());
	   sinfo.setIsManager(isManager);
	   sinfo.setTitle(s.getTitle());
	   sinfo.setContent(s.getContent());
	   sinfo.setAddress(s.getAddress());
	   sinfo.setCruseridl(s.getCruserid());
	   IrpUser irpUser=null;  
	   try {
		irpUser = irpUserDAO.selectByPrimaryKey(s.getCruserid());
	} catch (SQLException e) {
		e.printStackTrace();
	}		
	   sinfo.setCheckmore(s.getCheckmore());
	   //获得当前的用户名
	   LoginUtil.getUserNameString(irpUser);
	   sinfo.setCruserid(LoginUtil.getUserNameString(irpUser));
	  List<IrpLeaveoper> opers1= this.irpLeaveoperService.getListByapplyId(s.getLeaveapplyid());
	   sinfo.setCheckids(opers1.get(0).getUserid());
	   sinfo.setLeavetypeId(s.getApplytypeid());
	   sinfo.setUserleavedays(irpUser.getHoliday());
	   sinfo.setCrtime(s.getCrtime());
	   sinfo.setStarttime(s.getStarttime());
	   sinfo.setEndtime(s.getEndtime());
	   sinfo.setOvertimecontent(s.getOvertimecontent());
	   sinfo.setOvertimeadress(s.getOvertimeadress());
	   if(s.getEmergency().equals(10)){
		   
		   sinfo.setEmergency("正常");
	   }else if(s.getEmergency().equals(20)){
		   sinfo.setEmergency("重要");
	   }else if(s.getEmergency().equals(30)){
		   sinfo.setEmergency("紧急");
	   }
	   
	   if(s.getApplystatus().equals(10)){
		   sinfo.setApplystatus("已同意");
	   }else if(s.getApplystatus().equals(20)){
		   sinfo.setApplystatus("未审核");
	   }else if(s.getApplystatus().equals(30)){
		   sinfo.setApplystatus("已拒绝");
		 
	   }
	   //请假类型
	   IrpLeaveconfig irpLeaveconfig =this.irpLeaveconfigService.selLeaveConfigById(s.getApplytypeid());
	   sinfo.setApplytypeid(irpLeaveconfig.getLeaveconfigname());
	   //增加审核人
	 List<IrpLeaveoper> opers = this.irpLeaveoperService.getListByapplyId(s.getLeaveapplyid());
	
	 String checker2="";
	 if(opers.size()>0){
	 for(IrpLeaveoper o:opers){
		 IrpUser irpUsero=null; 
		 try {
			irpUsero = irpUserDAO.selectByPrimaryKey(o.getUserid());
			checker2+=","+irpUsero.getName();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		 
	 }
	 sinfo.setChecker(checker2.substring(1));
	 }else{
		 sinfo.setChecker(checker2);
		 
	 }		
	return sinfo;
 }
 /**
  * 获取拒绝表单
  */
 public String torefuseFrom(){
	 return SUCCESS;
 }
 private String leavedays;
public String getLeavedays() {
	return leavedays;
}
public void setLeavedays(String leavedays) {
	this.leavedays = leavedays;
}
public int getIsMangcenter() {
	return isMangcenter;
}
public void setIsMangcenter(int isMangcenter) {
	this.isMangcenter = isMangcenter;
}
private Integer checkmore;
public Integer getCheckmore() {
	return checkmore;
}
public void setCheckmore(Integer checkmore) {
	this.checkmore = checkmore;
}
private String advise;
public String getAdvise() {
	return advise;
}

public void setAdvise(String advise) {
	this.advise = advise;
}


/**
 * 处理未审核的
 */
public void upStatus() {
	IrpLeaveapply irpleaveapply = this.irpLeaveapplyService
			.getById(leaveconfigid);
	irpCheckerLink = new IrpCheckerLink();

	if (emergency == 20) {
		if (checkmore != null) {
			if (checkmore == 1) {

				// 该级同意并且需要下一及审核
				// 第一步增加审核人级联关系
				irpCheckerLink.setLeaveapplyid(leaveconfigid); // 申请单id
				irpCheckerLink.setOpinion(opinion); // 该级别的备注
				irpCheckerLink.setNextuserid(Long.parseLong(userids)); // 下一个人审核人

				// 更改该级别的审核状态，设置为通过
				this.irpLeaveoperService.updateOperStatus(leaveconfigid,
						LoginUtil.getLoginUserId(), IrpCheckerLink.PASS);
				title = irpleaveapply.getTitle();
				// 关联下一级别审核人在oper表里面并像他发送私信
				Long msg = this.irpLeaveoperService.addOper(leaveconfigid,
						userids);
				String  warn_1=irpleaveapply.getWarnid()==null?"":irpleaveapply.getWarnid().toString();
				if (msg != null) {
					Long userid = Long.parseLong(userids);
					if(!warn_1.equals("")){
						if(warn_1.indexOf("1")!=-1){//私信
							this.sendAddMessage(userid, leaveconfigid,1,irpleaveapply.getLeavemarking());
						}
						if(warn_1.indexOf("2")!=-1){//短信
							this.sendSms(userid, leaveconfigid,1,irpleaveapply.getLeavemarking());
						}
						if(warn_1.indexOf("3")!=-1){//邮件
							this.sendEamil(userid, leaveconfigid,1,irpleaveapply.getLeavemarking());
						}
					 }
				}

			} else if (checkmore == 0) {
				// 该级别同意无需通知下一级别
                   
				// 级联表单关联结束，存储该级别审核意见
				irpCheckerLink.setLeaveapplyid(leaveconfigid); // 表单id
				irpCheckerLink.setNextuserid(0L);// 整个表单结束的时候下个审核人设置为0代表结束
				// 整个申请表单为通过
				irpleaveapply.setApplystatus(IrpLeaveapply.PASS);
				// 更改该级别的审核状态，设置为通过
				this.irpLeaveoperService.updateOperStatus(leaveconfigid,
						LoginUtil.getLoginUserId(), IrpCheckerLink.PASS);
				//如果请的是年假更改用户的年假状态
				IrpLeaveapply key = new IrpLeaveapply();
				key=this.irpLeaveapplyService.getById(leaveconfigid);
				Long yearId=Long.parseLong(SysConfigUtil.getSysConfigValue("HOLIDAYCONFIG"));
				
				if(key.getApplytypeid().equals(yearId)){
					
				IrpUser user=new IrpUser();
				user=this.irpUserService.findUserByUserId(key.getCruserid());
				Integer leavedays=(int) (user.getHoliday()-key.getLeavedays());
				IrpUser _irpUser=new IrpUser();
				_irpUser.setHoliday(leavedays);
				_irpUser.setUserid(key.getCruserid());
				
				int msg=this.irpUserService.updateLeaveYearDays(_irpUser);
				
				}
				
			}
		}
	} else if (emergency == 10) { // 该级别拒绝的情况			
		if (checkmore == 0 || checkmore == 1) {
			irpCheckerLink.setLeaveapplyid(irpleaveapply.getLeaveapplyid());
			irpCheckerLink.setNextuserid(0L);// 整个表单结束的时候下个审核人设置为0代表结束
			irpleaveapply.setApplystatus(IrpLeaveapply.REFUSE);
			// 更改上个用户的状态
			this.irpLeaveoperService.updateOperStatus(leaveconfigid,
					LoginUtil.getLoginUserId(), IrpCheckerLink.REFUSE);
		}

	}
	irpleaveapply.setAuditinfo(auditinfo);//无论什么状况都更新这个字段
	int msg = this.irpLeaveapplyService.upStatus(irpleaveapply); // 更新申请表单信息
	int msg2 = this.irpCheckerLinkService.addIrpCheckerLink(irpCheckerLink);// 保存关联表

	String  warn_1=irpleaveapply.getWarnid()==null?"":irpleaveapply.getWarnid().toString();
	title = irpleaveapply.getTitle();
	if (irpleaveapply.getLeavemarking().equals(IrpLeaveapply.WORK)) {
		if(!warn_1.equals("")){
			if(warn_1.indexOf("1")!=-1){//私信
				this.sendWorkMessage(irpleaveapply.getCruserid(),
						irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
			}
			if(warn_1.indexOf("2")!=-1){//短信
				this.sendSms(irpleaveapply.getCruserid(),
						irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
			}
			if(warn_1.indexOf("3")!=-1){//邮件
				this.sendEamil(irpleaveapply.getCruserid(),
						irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
			}
		 }
	} else {
		if(!warn_1.equals("")){
			if(warn_1.indexOf("1")!=-1){//私信
				this.sendAddMessage(irpleaveapply.getCruserid(),
						irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
			}
			if(warn_1.indexOf("2")!=-1){//短信
				this.sendSms(irpleaveapply.getCruserid(),
						irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
			}
			if(warn_1.indexOf("3")!=-1){//邮件
				this.sendEamil(irpleaveapply.getCruserid(),
						irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
			}
		 }


	}
	ActionUtil.writer(msg + "");

}

	/**
	 * 处理未审核的请假
	 */
	public void upStatusLeave() {
		IrpLeaveapply irpleaveapply = this.irpLeaveapplyService
				.getById(leaveconfigid);
		
		int result=0;
		boolean flag=true;
		if(irpleaveapply!=null){			
			if(irpleaveapply.getCheckmore().equals(change)){
				flag=true;
			}else{
				flag=false;
				
			}
			
		}else{
			flag=false;
			result=2;
		}
		if(flag){
			//没有被删除
			
		
		irpCheckerLink = new IrpCheckerLink();

		if (emergency == 20) {
			if (checkmore != null) {
				if (checkmore == 1) {

					// 该级同意并且需要下一及审核
					// 第一步增加审核人级联关系
					irpCheckerLink.setLeaveapplyid(leaveconfigid); // 申请单id
					irpCheckerLink.setOpinion(opinion); // 该级别的备注
					irpCheckerLink.setNextuserid(Long.parseLong(userids)); // 下一个人审核人

					// 更改该级别的审核状态，设置为通过
					this.irpLeaveoperService.updateOperStatus(leaveconfigid,
							LoginUtil.getLoginUserId(), IrpCheckerLink.PASS);
					title = irpleaveapply.getTitle();
					// 关联下一级别审核人在oper表里面并像他发送私信
					Long msg = this.irpLeaveoperService.addOper(leaveconfigid,
							userids);
					String  warn_1=irpleaveapply.getWarnid()==null?"":irpleaveapply.getWarnid().toString();
					if (msg != null) {
						Long userid = Long.parseLong(userids);
						if(!warn_1.equals("")){
							if(warn_1.indexOf("1")!=-1){//私信
								this.sendAddMessage(userid, leaveconfigid,1,irpleaveapply.getLeavemarking());
							}
							if(warn_1.indexOf("2")!=-1){//短信
								this.sendSms(userid, leaveconfigid,1,irpleaveapply.getLeavemarking());
							}
							if(warn_1.indexOf("3")!=-1){//邮件
								this.sendEamil(userid, leaveconfigid,1,irpleaveapply.getLeavemarking());
							}
						 }
					}

				} else if (checkmore == 0) {
					// 该级别同意无需通知下一级别
                       
					// 级联表单关联结束，存储该级别审核意见
					irpCheckerLink.setLeaveapplyid(leaveconfigid); // 表单id
					irpCheckerLink.setNextuserid(0L);// 整个表单结束的时候下个审核人设置为0代表结束
					// 整个申请表单为通过
					irpleaveapply.setApplystatus(IrpLeaveapply.PASS);
					// 更改该级别的审核状态，设置为通过
					this.irpLeaveoperService.updateOperStatus(leaveconfigid,
							LoginUtil.getLoginUserId(), IrpCheckerLink.PASS);
					//如果请的是年假更改用户的年假状态
					IrpLeaveapply key = new IrpLeaveapply();
					key=this.irpLeaveapplyService.getById(leaveconfigid);
					Long yearId=Long.parseLong(SysConfigUtil.getSysConfigValue("HOLIDAYCONFIG"));
					
					if(key.getApplytypeid().equals(yearId)){
						
					IrpUser user=new IrpUser();
					user=this.irpUserService.findUserByUserId(key.getCruserid());
					Integer leavedays=(int) (user.getHoliday()-key.getLeavedays());
					IrpUser _irpUser=new IrpUser();
					_irpUser.setHoliday(leavedays);
					_irpUser.setUserid(key.getCruserid());
					
					int msg4=this.irpUserService.updateLeaveYearDays(_irpUser);
					
					}
					
				}
			}
		} else if (emergency == 10) { // 该级别拒绝的情况			
			if (checkmore == 0 || checkmore == 1) {
				irpCheckerLink.setLeaveapplyid(irpleaveapply.getLeaveapplyid());
				irpCheckerLink.setNextuserid(0L);// 整个表单结束的时候下个审核人设置为0代表结束
				irpleaveapply.setApplystatus(IrpLeaveapply.REFUSE);
				// 更改上个用户的状态
				this.irpLeaveoperService.updateOperStatus(leaveconfigid,
						LoginUtil.getLoginUserId(), IrpCheckerLink.REFUSE);
			}

		}
		irpleaveapply.setAuditinfo(auditinfo);//无论什么状况都更新这个字段
		int msg3 = this.irpLeaveapplyService.upStatus(irpleaveapply); // 更新申请表单信息
		int msg2 = this.irpCheckerLinkService.addIrpCheckerLink(irpCheckerLink);// 保存关联表

		String  warn_1=irpleaveapply.getWarnid()==null?"":irpleaveapply.getWarnid().toString();
		title = irpleaveapply.getTitle();
		if (irpleaveapply.getLeavemarking().equals(IrpLeaveapply.WORK)) {
			if(!warn_1.equals("")){
				if(warn_1.indexOf("1")!=-1){//私信
					this.sendWorkMessage(irpleaveapply.getCruserid(),
							irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
				}
				if(warn_1.indexOf("2")!=-1){//短信
					this.sendSms(irpleaveapply.getCruserid(),
							irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
				}
				if(warn_1.indexOf("3")!=-1){//邮件
					this.sendEamil(irpleaveapply.getCruserid(),
							irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
				}
			 }
		} else {
			if(!warn_1.equals("")){
				if(warn_1.indexOf("1")!=-1){//私信
					this.sendAddMessage(irpleaveapply.getCruserid(),
							irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
				}
				if(warn_1.indexOf("2")!=-1){//短信
					this.sendSms(irpleaveapply.getCruserid(),
							irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
				}
				if(warn_1.indexOf("3")!=-1){//邮件
					this.sendEamil(irpleaveapply.getCruserid(),
							irpleaveapply.getLeaveapplyid(),1,irpleaveapply.getLeavemarking());
				}
			 }

		}
		result=1;
}
		ActionUtil.writer(result + "");

	}

public List<IrpGroup> getIrpGroups() {
	return irpGroups;
}
public void setIrpGroups(List<IrpGroup> irpGroups) {
	this.irpGroups = irpGroups;
}

public void leaveByLimit(){
	int msg=0;
	int msg2=0;
	irpLeaveapply=this.irpLeaveapplyService.getById(Long.parseLong(leaveapplyid));
	

	if(type.equals(10)){
		roleId =Long.parseLong(SysConfigUtil.getSysConfigValue("LEAVEAPPLY_ROLE_ID")) ;
	}else{
		roleId =Long.parseLong(SysConfigUtil.getSysConfigValue("WORKAPPLY_ROLE_ID")) ;
	}
	
	IrpUserroleLinkExample example=new IrpUserroleLinkExample();
	example.createCriteria().andRoleidEqualTo(roleId).andUseridEqualTo(LoginUtil.getLoginUserId());
	try {
		 msg2=this.irpUserroleLinkDAO.countByExample(example);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	if(irpLeaveapply.getCruserid().equals(LoginUtil.getLoginUserId())&&msg2==1){


		msg=0;
	}else if(irpLeaveapply.getCruserid().equals(LoginUtil.getLoginUserId())&&msg2!=1){

		
		msg=0;
		

	}else{
		msg=1;
	}
	
	
	ActionUtil.writer(msg + "");
}
    
     /**
      * 查询管理员后台的请假数据
      * @return
      */
     public String getAllLeaveQuery(){
    			String _oOrderby = "";
    			if (this.orderField == null || this.orderField.equals("")) {
    				_oOrderby = "crtime desc";
    			} else {
    				_oOrderby = this.orderField + " " + this.orderBy;
    			}
    			
    			PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
    					irpLeaveapplyService.searchUsername(searchWord, searchType));
    			
    			irpLeaveapplyList =  this.irpLeaveapplyService.findAllQuery(pageUtil,_oOrderby,searchWord,searchType);
    			irpLeaveapplyInfos=this.coverListApply(irpLeaveapplyList);
    			irpLeaveconfigs=this.irpLeaveconfigService.getAllList();
    			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
    			return SUCCESS;
    			
    	 
    	 
     }
     /**
      * 这是获得的个人数据统计
      * @return
      */
  public String statusLeaveApplyByDays(){             
	       String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "crtime desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		 Date starttimes=null;
			Date endtimes=null;
			LogTimeUtil ltu = new LogTimeUtil();
		   if (c_start_end.equals("logs_week")) {
			   //本周
			   starttimes= ltu.getWeek();
			   endtimes  = ltu.getLastWeek();
			}else if(c_start_end.equals("logs_month")){
				//本月
				starttimes = ltu.getMonth();
				endtimes = ltu.getLastMonth();
			}else if(c_start_end.equals("logs_quarter")){
				 //本季
				starttimes = ltu.getQuarter();
				endtimes = ltu.getLastQuarter();
			}else if(c_start_end.equals("logs_appoint_date")){
				 //指定
	       	 
	            starttimes=starttime;
	 	        Date date = endtime;
	            date.setHours(24);
	            endtimes=date;
			
			}
			irpLeaveconfigs=this.irpLeaveconfigService.getAllList();
		
		// 分页查出所有用户
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
						irpUserService.searchUserValueLinkSize(null, null));
	    List<IrpUser> userlist = this.irpUserService.findAllIrpUser(pageUtil,
						_oOrderby, null, null);			
		irpLeaveapplyList =  this.irpLeaveapplyService.getQueryListForAdmin(marking,starttimes,endtimes,applystatus,applytypeid,emergency);
		StringBuffer days = new StringBuffer();
		StringBuffer countday = new StringBuffer();
		StringBuffer sbName = new StringBuffer();
		for (int i = 0; i < userlist.size(); i++) {
			int count = 0;
			float days1=0;
			IrpUser irpUser = userlist.get(i);
			
			sbName.append("'" + irpUser.getTruename() + "',");
			for (int j = 0; j < irpLeaveapplyList.size(); j++) {
				
				if (irpUser.getUserid().equals(irpLeaveapplyList
						.get(j).getCruserid())) {
					count+=1;
					days1+=irpLeaveapplyList
							.get(j).getLeavedays();
					
					
				}
				
			}
			countday.append(count + ",");
			days.append(days1 + ",");
		}
		usernamejson = sbName.toString().substring(0,
				sbName.toString().length() - 1);
		latejson = countday.toString().substring(0,
				countday.toString().length() - 1);
		earlyjson = days.toString().substring(0,
				days.toString().length() - 1);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	  
	  
	  
  }
  /**
	 * 判断
	 * @param str
	 * @return
	 */
	public boolean common(String str){
		if(str!=null && !"".equals(str)){
			return true;
		}
		return false;
	}

  /**
   * 请假导出list
 * @throws SQLException 
   */
  public void exportToZip() {
	 
		ActionContext ac = ActionContext.getContext();     
      ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);     
      String WEB_ROOT_PATH = sc.getRealPath("/"); 
      String app_path=WEB_ROOT_PATH.replace("\\", "/");    
      String path=app_path+"admin";  
    
      Date starttimes=null;
		Date endtimes=null;
		LogTimeUtil ltu = new LogTimeUtil();
	   if (c_start_end.equals("logs_week")) {
		   //本周
		   starttimes= ltu.getWeek();
		   endtimes  = ltu.getLastWeek();
		}else if(c_start_end.equals("logs_month")){
			//本月
			starttimes = ltu.getMonth();
			endtimes = ltu.getLastMonth();
		}else if(c_start_end.equals("logs_quarter")){
			 //本季
			starttimes = ltu.getQuarter();
			endtimes = ltu.getLastQuarter();
		}else if(c_start_end.equals("logs_appoint_date")){
			 //指定
    	 
         starttimes=starttime;
	        Date date = endtime;
	       
         date.setHours(24);
         endtimes=date;
		
		}
	   
			
		
		irpLeaveapplyList=this.irpLeaveapplyService.getQueryListFromExp(null,marking,starttimes,endtimes,applystatus,applytypeid,searchName,emergency);
		irpLeaveapplyInfos=this.coverListApply(irpLeaveapplyList);
		
	
		irpLeaveapplyService.exportAllSignInfoToZip(irpLeaveapplyInfos,path,fileName);
		
	  
  }
  /**
   * 每个部门的请假情况
   * @return
   */
  public String departmentStatic(){
	  String _oOrderby = "";
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "crtime desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		 Date starttimes=null;
			Date endtimes=null;
			LogTimeUtil ltu = new LogTimeUtil();
		   if (c_start_end.equals("logs_week")) {
			   //本周
			   starttimes= ltu.getWeek();
			   endtimes  = ltu.getLastWeek();
			}else if(c_start_end.equals("logs_month")){
				//本月
				starttimes = ltu.getMonth();
				endtimes = ltu.getLastMonth();
			}else if(c_start_end.equals("logs_quarter")){
				 //本季
				starttimes = ltu.getQuarter();
				endtimes = ltu.getLastQuarter();
			}else if(c_start_end.equals("logs_appoint_date")){
				 //指定
	       	 
	            starttimes=starttime;
	          
	 	        Date date = endtime;
	            date.setHours(24);
	            endtimes=date;
			
			}
		   
		   String[] spGroupid=groupIds.split(",");	
			StringBuffer btdoc = new StringBuffer();
			StringBuffer btdoc1 = new StringBuffer();
			int workTimeCount=0;
			int totaltime = 0;
			Map<String,Object> groupidmap= new HashMap<String,Object>();
			StringBuffer name = new StringBuffer("");
			StringBuffer number = new StringBuffer("");	
			StringBuffer time = new StringBuffer("");	
			if(type.equals(1)){
				for(int i = 0;i<spGroupid.length;i++){
					if(spGroupid[i].equals("1")){
						List<IrpGroup> igroup=null;
						long grupid=Long.parseLong(spGroupid[i]);
						igroup=	this.irpGroupService.ceshi(grupid);
						for(int s=0;s<igroup.size();s++){
							List<String> finalResult = new ArrayList<String>();
							finalResult =this.gets( finalResult,igroup.get(s).getGroupid().toString(),1);
							groupidmap.put(igroup.get(s).getGroupid().toString(), finalResult.size());
							List<Long> allgroupid=new ArrayList<Long>();
							for(int k=0;k<finalResult.size();k++){
								allgroupid.add(Long.parseLong(finalResult.get(k)));
							}	
							List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
							workTimeCount = irpLeaveapplyService.selectCountByListForLeave(userids,starttimes,endtimes);
							totaltime = irpLeaveapplyService.selectTotaltimeByListForLeave(userids, starttimes, endtimes);
							name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+"',");
							number.append(workTimeCount+",");	
							time.append(totaltime+",");
						}					
					}else{					
						List<String> finalResult = new ArrayList<String>();
						finalResult =this.gets( finalResult, spGroupid[i],1);
						groupidmap.put(spGroupid[i], finalResult.size());
						List<Long> allgroupid=new ArrayList<Long>();
						for(int k=0;k<finalResult.size();k++){
							allgroupid.add(Long.parseLong(finalResult.get(k)));
						}		
						List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
						workTimeCount = irpLeaveapplyService.selectCountByListForLeave(userids,starttimes,endtimes);
						totaltime = irpLeaveapplyService.selectTotaltimeByListForLeave(userids, starttimes, endtimes);
						name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+"',");
						number.append(workTimeCount+",");	
						time.append(totaltime+",");
					}				
				}
			}else{
				for(int i = 0;i<spGroupid.length;i++){
					if(spGroupid[i].equals("1")){
						List<IrpGroup> igroup=null;
						long grupid=Long.parseLong(spGroupid[i]);
						igroup=	this.irpGroupService.ceshi(grupid);
						for(int s=0;s<igroup.size();s++){
							List<String> finalResult = new ArrayList<String>();
							finalResult =this.gets( finalResult,igroup.get(s).getGroupid().toString(),1);
							groupidmap.put(igroup.get(s).getGroupid().toString(), finalResult.size());
							List<Long> allgroupid=new ArrayList<Long>();
							for(int k=0;k<finalResult.size();k++){
								allgroupid.add(Long.parseLong(finalResult.get(k)));
							}
							List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
							workTimeCount = irpLeaveapplyService.selectCountByListForLeave(userids,starttimes,endtimes);
							totaltime = irpLeaveapplyService.selectTotaltimeByListForLeave(userids, starttimes, endtimes);
							name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+"',");
							number.append(workTimeCount+",");
							time.append(totaltime+",");
							btdoc.append(irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+","+workTimeCount+"-");						
							btdoc1.append(irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+","+totaltime+"-");						
						}					
					}else{					
						List<String> finalResult = new ArrayList<String>();
						finalResult =this.gets( finalResult, spGroupid[i],1);
						groupidmap.put(spGroupid[i], finalResult.size());
						List<Long> allgroupid=new ArrayList<Long>();
						for(int k=0;k<finalResult.size();k++){
							allgroupid.add(Long.parseLong(finalResult.get(k)));
						}		
						List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
						workTimeCount = irpLeaveapplyService.selectCountByListForLeave(userids,starttimes,endtimes);
						totaltime = irpLeaveapplyService.selectTotaltimeByListForLeave(userids, starttimes, endtimes);
						name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+"',");
						number.append(workTimeCount+",");	
						time.append(totaltime+",");
						btdoc.append(irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+","+workTimeCount+"-");						
						btdoc1.append(irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+","+totaltime+"-");						
					}				
				}	
			}if(type.equals(1)){
				if(name.length()>0||number.length()>0){
					this.groupname = name.toString().substring(0,name.toString().length()-1);
					this.worktimeNum = number.toString().substring(0,number.toString().length()-1);
					this.totalTime = time.toString().substring(0,time.toString().length()-1);
				}		
			}else{
				if(name.length()>0||number.length()>0||btdoc.length()>0||btdoc1.length()>0){
					this.groupname = name.toString().substring(0,name.toString().length()-1);
					this.worktimeNum = number.toString().substring(0,number.toString().length()-1);
					this.totalTime = time.toString().substring(0,number.toString().length()-1);
					this.bttext=btdoc.toString().substring(0,btdoc.toString().length()-1);//请假次数
					usernamejson=btdoc1.toString().substring(0,btdoc.toString().length()-1);//请假天数
				}		
			}
		
		

		if(type.equals(1)){
			return SUCCESS;
		}else{
			return INPUT;  
		}
	  
  }
  /**
   * 不同部门之间的比较
   * @return
   */
  public String compareLeaves(){
	  String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "crtime desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			 Date starttimes=null;
				Date endtimes=null;
				LogTimeUtil ltu = new LogTimeUtil();
			   if (c_start_end.equals("logs_week")) {
				   //本周
				   starttimes= ltu.getWeek();
				   endtimes  = ltu.getLastWeek();
				}else if(c_start_end.equals("logs_month")){
					//本月
					starttimes = ltu.getMonth();
					endtimes = ltu.getLastMonth();
				}else if(c_start_end.equals("logs_quarter")){
					 //本季
					starttimes = ltu.getQuarter();
					endtimes = ltu.getLastQuarter();
				}else if(c_start_end.equals("logs_appoint_date")){
					 //指定
		       	 
		            starttimes=starttime;
		 	        Date date = endtime;
		            date.setHours(24);
		            endtimes=date;
				
				}
			   
			   String[] spGroupid=groupIds.split(",");	
				StringBuffer btdoc = new StringBuffer();
				StringBuffer btdoc1 = new StringBuffer();
				int workTimeCount=0;
				int totaltime = 0;
				Map<String,Object> groupidmap= new HashMap<String,Object>();
				StringBuffer name = new StringBuffer("");
				StringBuffer number = new StringBuffer("");	
				StringBuffer time = new StringBuffer("");	
				if(type.equals(1)){
					for(int i = 0;i<spGroupid.length;i++){
						if(spGroupid[i].equals("1")){
							List<IrpGroup> igroup=null;
							long grupid=Long.parseLong(spGroupid[i]);
							igroup=	this.irpGroupService.ceshi(grupid);
							for(int s=0;s<igroup.size();s++){
								List<String> finalResult = new ArrayList<String>();
								finalResult =this.gets( finalResult,igroup.get(s).getGroupid().toString(),1);
								groupidmap.put(igroup.get(s).getGroupid().toString(), finalResult.size());
								List<Long> allgroupid=new ArrayList<Long>();
								for(int k=0;k<finalResult.size();k++){
									allgroupid.add(Long.parseLong(finalResult.get(k)));
								}	
								List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
								workTimeCount = irpLeaveapplyService.selectCountByListForLeave(userids,starttimes,endtimes);
								totaltime = irpLeaveapplyService.selectTotaltimeByListForLeave(userids, starttimes, endtimes);
								name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+"',");
								number.append(workTimeCount+",");	
								time.append(totaltime+",");
							}					
						}else{					
							List<String> finalResult = new ArrayList<String>();
							finalResult =this.gets( finalResult, spGroupid[i],1);
							groupidmap.put(spGroupid[i], finalResult.size());
							List<Long> allgroupid=new ArrayList<Long>();
							for(int k=0;k<finalResult.size();k++){
								allgroupid.add(Long.parseLong(finalResult.get(k)));
							}		
							List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
							workTimeCount = irpLeaveapplyService.selectCountByListForLeave(userids,starttimes,endtimes);
							totaltime = irpLeaveapplyService.selectTotaltimeByListForLeave(userids, starttimes, endtimes);
							name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+"',");
							number.append(workTimeCount+",");	
							time.append(totaltime+",");
						}				
					}
				}else{
					for(int i = 0;i<spGroupid.length;i++){
						if(spGroupid[i].equals("1")){
							List<IrpGroup> igroup=null;
							long grupid=Long.parseLong(spGroupid[i]);
							igroup=	this.irpGroupService.ceshi(grupid);
							for(int s=0;s<igroup.size();s++){
								List<String> finalResult = new ArrayList<String>();
								finalResult =this.gets( finalResult,igroup.get(s).getGroupid().toString(),1);
								groupidmap.put(igroup.get(s).getGroupid().toString(), finalResult.size());
								List<Long> allgroupid=new ArrayList<Long>();
								for(int k=0;k<finalResult.size();k++){
									allgroupid.add(Long.parseLong(finalResult.get(k)));
								}
								List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
								workTimeCount = irpLeaveapplyService.selectCountByListForLeave(userids,starttimes,endtimes);
								totaltime = irpLeaveapplyService.selectTotaltimeByListForLeave(userids, starttimes, endtimes);
								name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+"',");
								number.append(workTimeCount+",");
								time.append(totaltime+",");
								btdoc.append(irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+","+workTimeCount+"-");						
								btdoc1.append(irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+","+totaltime+"-");						
							}					
						}else{					
							List<String> finalResult = new ArrayList<String>();
							finalResult =this.gets( finalResult, spGroupid[i],1);
							groupidmap.put(spGroupid[i], finalResult.size());
							List<Long> allgroupid=new ArrayList<Long>();
							for(int k=0;k<finalResult.size();k++){
								allgroupid.add(Long.parseLong(finalResult.get(k)));
							}		
							List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
							workTimeCount = irpLeaveapplyService.selectCountByListForLeave(userids,starttimes,endtimes);
							totaltime = irpLeaveapplyService.selectTotaltimeByListForLeave(userids, starttimes, endtimes);
							name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+"',");
							number.append(workTimeCount+",");	
							time.append(totaltime+",");
							btdoc.append(irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+","+workTimeCount+"-");						
							btdoc1.append(irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+","+totaltime+"-");						
						}				
					}	
				}if(type.equals(1)){
					if(name.length()>0||number.length()>0){
						this.groupname = name.toString().substring(0,name.toString().length()-1);
						this.worktimeNum = number.toString().substring(0,number.toString().length()-1);
						this.totalTime = time.toString().substring(0,time.toString().length()-1);
					}		
				}else{
					if(name.length()>0||number.length()>0||btdoc.length()>0||btdoc1.length()>0){
						this.groupname = name.toString().substring(0,name.toString().length()-1);
						this.worktimeNum = number.toString().substring(0,number.toString().length()-1);
						this.totalTime = time.toString().substring(0,number.toString().length()-1);
						this.bttext=btdoc.toString().substring(0,btdoc.toString().length()-1);
						usernamejson=btdoc1.toString().substring(0,btdoc.toString().length()-1);
					}		
				}
			
			

			if(type.equals(1)){
				return SUCCESS;
			}else{
				return INPUT;  
			}
	 
  }
  public String departmenttime(){
	
	  String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "crtime desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			 Date starttimes=null;
				Date endtimes=null;
				LogTimeUtil ltu = new LogTimeUtil();
			   if (c_start_end.equals("logs_week")) {
				   //本周
				   starttimes= ltu.getWeek();
				   endtimes  = ltu.getLastWeek();
				}else if(c_start_end.equals("logs_month")){
					//本月
					starttimes = ltu.getMonth();
					endtimes = ltu.getLastMonth();
				}else if(c_start_end.equals("logs_quarter")){
					 //本季
					starttimes = ltu.getQuarter();
					endtimes = ltu.getLastQuarter();
				}else if(c_start_end.equals("logs_appoint_date")){
					 //指定
		       	 
		            starttimes=starttime;
		 	        Date date = endtime;
		            date.setHours(24);
		            endtimes=date;
				
				}
			
			
		    irpLeaveapplyList =  this.irpLeaveapplyService.getQueryListForAdmin(marking,starttimes,endtimes,"0","0",0);
			
			
				
				StringBuffer hoursStr = new StringBuffer();
				
				int hours1 = 0;
				int hours2 = 0;
				int hours3 = 0;
				int hours4 = 0;
				int hours5 = 0;
				int hours6 = 0;
				int hours7 = 0;
				int hours8 = 0;
				for (int j = 0; j < irpLeaveapplyList.size(); j++) {
					IrpLeaveapply s= irpLeaveapplyList.get(j);
					Float hours = s.getLeavedays();
					if(hours>0 && hours<=1){
						hours1++;
					}else if(hours>1 && hours<=2){
						hours2++;			
					}else if(hours>2 && hours<=3){
						hours3++;
					}else if(hours>3 && hours<=4){	
						hours4++;
					}else if(hours>4 && hours<=5){
						hours5++;		
					}else if(hours>5 && hours<10){	
						hours6++;
					}else if(hours>=10 && hours<15){
						hours7++;	
					}else{
						hours8++;
					}
					
					
				}
				
			
				hoursStr.append(hours1+","+hours2+","+hours3+","+hours4+","+hours5+","+hours6+","+hours7+","+hours8);
				
				hoursarray = hoursStr.toString();
				
				
				StringBuffer datytimesstr = new StringBuffer();
				datytimesstr.append((hours3+hours2+hours1)+","+(hours4+hours5)+","+(hours6)+","+(hours8+hours7));
				daytimes = datytimesstr.toString();
		 
	  
	  return SUCCESS;
  }
  
 	//获取请假统计图
 	public String everyDayPublishDocumentAmount(){
 		
			 Date starttimes=null;
				Date endtimes=null;
				LogTimeUtil ltu = new LogTimeUtil();
			   if (c_start_end.equals("logs_week")) {
				   //本周
				   starttimes= ltu.getWeek();
				   endtimes  = ltu.getLastWeek();
				}else if(c_start_end.equals("logs_month")){
					//本月
					starttimes = ltu.getMonth();
					endtimes = ltu.getLastMonth();
				}else if(c_start_end.equals("logs_quarter")){
					 //本季
					starttimes = ltu.getQuarter();
					endtimes = ltu.getLastQuarter();
				}else if(c_start_end.equals("logs_appoint_date")){
					 //指定
		       	 
		            starttimes=starttime;
		 	        Date date = endtime;
		 	       
		            date.setHours(24);
		            endtimes=date;
				
				}
			   irpLeaveapplyList =  this.irpLeaveapplyService.getQueryListForAdmin(marking,starttimes,endtimes,"0","0",0);
 			StringBuffer amountStr=new StringBuffer();
 			StringBuffer privateAmountStr=new StringBuffer();
 			//获取横坐标可以不需要横坐标，横坐标自动每走一步
 			int amount=setTimeBar(this.c_start_end);
 			
 			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
 			Calendar calendar=Calendar.getInstance();
 			calendar.setTime(starttimes);//设置初始时间
 			if(irpLeaveapplyList!=null){  
 				amountStr.append("[");
 			
 				boolean b=false;
 				for (int i = 0; i <=amount; i++) {
 					int pubAmount=0;//企业
 					
 					for (int j = 0; j <irpLeaveapplyList.size(); j++) {
 						Date docPubTime=irpLeaveapplyList.get(j).getCrtime();
 						String doc=dateFormat.format(docPubTime);
 						String time=dateFormat.format(calendar.getTime());
 						if(doc.equals(time)){//如果相同就加1
 							
 								pubAmount++;
 						}
 					}
 					if(b){
 						amountStr.append(","+pubAmount);
 					}else {
 						amountStr.append(pubAmount);
 					}
 					 b=true;
 					 pubAmount=0;
 					 calendar.add(Calendar.DAY_OF_MONTH, 1);
 				}
 				amountStr.append("]");
 				
 			}
 			amountJsonString=amountStr.toString();
 		


 		return SUCCESS;
 	}

/**
* 设置横坐标的值
* @param limit
*/
public int setTimeBar(String limit){
	int amount =0;
	if(limit!=null){
		StringBuffer xAxis=new StringBuffer();
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		xAxis.append("[");
		LogTimeUtil logTimeUtil=new LogTimeUtil();
		if(limit.trim().equals("logs_month")){//本月  结束日期是到明天早上即包含今天
			starttime=logTimeUtil.getMonth();
			endtime=new Date();
			Long limitamount=endtime.getTime()-starttime.getTime();
			amount = (int)(limitamount/1000/60/60/24) ;
			calendar.setTime(starttime);
			xAxis.append("'"+format.format(calendar.getTime())+"'");
			for (int i = 0; i <amount; i++){
				calendar.add(Calendar.DAY_OF_MONTH,1);
				xAxis.append(","+"'"+format.format(calendar.getTime())+"'");
			}
		}else if (limit.trim().equals("logs_week")) {//本周 结束日期是到明天早上即包含今天
			starttime=logTimeUtil.getWeek();
			endtime=new Date();
			Long limitamount=endtime.getTime()-starttime.getTime();
			amount = (int)(limitamount/1000/60/60/24) ;
			calendar.setTime(starttime);
			xAxis.append(calendar.get(Calendar.DAY_OF_WEEK)-1);
			for (int i = 0; i <amount; i++){
				calendar.add(Calendar.DAY_OF_MONTH,1);
				xAxis.append(","+(calendar.get(Calendar.DAY_OF_WEEK)-1));
			}
		}else if(limit.trim().equals("logs_quarter")){//本季度 结束日期是到明天早上即包含今天
			starttime=logTimeUtil.getQuarter();
			endtime=new Date();
			Long limitamount=endtime.getTime()-starttime.getTime();
			amount = (int)(limitamount/1000/60/60/24) ;
			calendar.setTime(starttime);
			xAxis.append("'"+format.format(calendar.getTime())+"'");
			for (int i = 0; i <amount; i++){
				calendar.add(Calendar.DAY_OF_MONTH,1);
				String nextDate=format.format(calendar.getTime());
				xAxis.append(",'"+nextDate+"'"); 
			}
		}else{ 
			Long limitamount=null;
			if(starttime==endtime){
				  Date date = endtime;
		 	       
		            date.setHours(24);
		           Date endtimes=date;
				
				 limitamount=endtimes.getTime()-starttime.getTime();
			}else{
				 limitamount=endtime.getTime()-starttime.getTime();
				
			}
			amount = (int)(limitamount/1000/60/60/24) ;
			calendar.setTime(starttime);
			xAxis.append("'"+format.format(starttime)+"'");
			for (int i = 0; i <amount; i++){
				calendar.add(Calendar.DAY_OF_MONTH,1);
				String nextDate=format.format(calendar.getTime());
				xAxis.append(",'"+nextDate+"'");
			}
		}
		xAxis.append("]");
		this.setXaxisJasonString(xAxis.toString());
	}
	return amount;
}
/**
 * 导出请假查询
 * @return
 */
public String exportQuery(){

	 Date starttimes=null;
		Date endtimes=null;
		LogTimeUtil ltu = new LogTimeUtil();
	   if (c_start_end.equals("logs_week")) {
		   //本周
		   starttimes= ltu.getWeek();
		   endtimes  = ltu.getLastWeek();
		}else if(c_start_end.equals("logs_month")){
			//本月
			starttimes = ltu.getMonth();
			endtimes = ltu.getLastMonth();
		}else if(c_start_end.equals("logs_quarter")){
			 //本季
			starttimes = ltu.getQuarter();
			endtimes = ltu.getLastQuarter();
		}else if(c_start_end.equals("logs_appoint_date")){
			 //指定
      	 
           starttimes=starttime;
	        Date date = endtime;
	       
           date.setHours(24);
           endtimes=date;
		
		}
	   
		int nDataCount=this.irpLeaveapplyService.findListQueryNumsFromExp(marking,starttimes,endtimes,applystatus,applytypeid,searchName,emergency);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
				
		
		irpLeaveapplyList=this.irpLeaveapplyService.getQueryListFromExp(pageUtil,marking,starttimes,endtimes,applystatus,applytypeid,searchName,emergency);
		irpLeaveapplyInfos=this.coverListApply(irpLeaveapplyList);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		irpLeaveconfigs=this.irpLeaveconfigService.getAllList();
	return SUCCESS;
}
/**
 * 个人加班统计报表
 * 
 * @return
 */
public String getWorkTimePersonStatement() {
	   Date starttimes=null;
		Date endtimes=null;
		LogTimeUtil ltu = new LogTimeUtil();
	   if (c_start_end.equals("logs_week")) {
		   //本周
		   starttimes= ltu.getWeek();
		   endtimes  = ltu.getLastWeek();
		}else if(c_start_end.equals("logs_month")){
			//本月
			starttimes = ltu.getMonth();
			endtimes = ltu.getLastMonth();
		}else if(c_start_end.equals("logs_quarter")){
			 //本季
			starttimes = ltu.getQuarter();
			endtimes = ltu.getLastQuarter();
		}else if(c_start_end.equals("logs_appoint_date")){
			 //指定
    	 
         starttimes=starttime;
	        Date date = endtime;
         date.setHours(24);
         endtimes=date;
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         starttime1=sdf.format(starttime);
         endtime1 = sdf.format(endtime);
		}	
	String _oOrderby = "sumscore desc";
	// 分页查出所有用户
	PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
			irpUserService.searchUserValueLinkSize(null, null));
	List<IrpUser> userlist = this.irpUserService.findAllIrpUser(pageUtil,
			_oOrderby, null, null);
	// 正常、紧急、重要
	StringBuffer sbName = new StringBuffer();
	StringBuffer sbnormalnum = new StringBuffer();
	StringBuffer sburgentnum = new StringBuffer();
	StringBuffer sbimportantum = new StringBuffer();
	StringBuffer sbtotaltime = new StringBuffer();
	Long _applytypeid =null;
	if(applytypeid!=null&&!applytypeid.equals("0")){
		_applytypeid =Long.parseLong(applytypeid);
	}
	irpLeaveapplyList =irpLeaveapplyService.selectByExample(starttimes, endtimes,_applytypeid);
	// 遍历用户,计算出对应的结果
	// 外层循环为用户
	List<IrpLeaveapplystatus> orderlist = new ArrayList<IrpLeaveapplystatus>();
	for (int i = 0; i < userlist.size(); i++) {
		int normal = 0;
		int important = 0;
		int urgent = 0;
		int totaltime = 0;
		IrpUser irpUser = userlist.get(i);
		sbName.append("'" + irpUser.getTruename() + "',");
		// 内层签到信息循环
		if (irpLeaveapplyList != null && irpLeaveapplyList.size() != 0) {
			for (int j = 0; j < irpLeaveapplyList.size(); j++) {
				if (Integer.parseInt(irpUser.getUserid() + "") == irpLeaveapplyList
						.get(j).getCruserid()) {
					if (irpLeaveapplyList.get(j).getApplystatus().equals(IrpLeaveapply.NORMAL)) {
						normal += 1;
					}
					if (irpLeaveapplyList.get(j).getApplystatus().equals(IrpLeaveapply.IMPORTANT)) {
						important += 1;
					}
					if (irpLeaveapplyList.get(j).getApplystatus().equals(IrpLeaveapply.URGENT)) {
						urgent += 1;
					}
					totaltime=(int) ((irpLeaveapplyList.get(j).getLeavedays())+totaltime);
				}
			}
		}
		if(orderField!=null&&!orderField.equals("0")&&!orderField.equals("")){
			IrpLeaveapplystatus statusinfo = new IrpLeaveapplystatus();
			statusinfo.setImportant(important);
			statusinfo.setNormal(normal);
			statusinfo.setUrgent(urgent);
			statusinfo.setUsername(irpUser.getTruename());
			statusinfo.setWorktoataltime(totaltime);
			statusinfo.setOrderby(Integer.parseInt(orderField));
			orderlist.add(statusinfo);
		}		
		sbnormalnum.append(normal + ",");
		sburgentnum.append(urgent + ",");
		sbimportantum.append(important + ",");
		sbtotaltime.append(String.valueOf(totaltime)+",");
	}

	if(orderField!=null&&!orderField.equals("0")&&!orderField.equals("")){
		sbName.delete(0, sbName.toString().length());
		sbnormalnum.delete(0, sbnormalnum.toString().length());
		sburgentnum.delete(0, sburgentnum.toString().length());
		sbimportantum.delete(0, sbimportantum.toString().length());
		sbtotaltime.delete(0, sbtotaltime.toString().length());
		Collections.sort(orderlist);
		for(IrpLeaveapplystatus status:orderlist){
			sbName.append("'" + status.getUsername() + "',");
			sbnormalnum.append(status.getNormal() + ",");
			sburgentnum.append(status.getUrgent() + ",");
			sbimportantum.append(status.getImportant() + ",");
			sbtotaltime.append(String.valueOf(status.getWorktoataltime())+",");
		}
	}	
	usernamejson =sbName.toString().substring(0,
			sbName.toString().length() - 1);
	normaljson = sbnormalnum.toString().substring(0,
			sbnormalnum.toString().length() - 1);
	urgentjson = sburgentnum.toString().substring(0,
			sburgentnum.toString().length() - 1);
	importantjson = sbimportantum.toString().substring(0,
			sbimportantum.toString().length() - 1);
	worktoataltime=sbtotaltime.toString().substring(0,
			sbtotaltime.toString().length() - 1);
	this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
	irpLeaveconfigs=this.irpLeaveconfigService.getAllOverTimeType();
	return SUCCESS;
}
/**
 * 部门加班统计
 */
public String departTongJi(){
	 Date starttimes=null;
		Date endtimes=null;
		LogTimeUtil ltu = new LogTimeUtil();
	   if (c_start_end.equals("logs_week")) {
		   //本周
		   starttimes= ltu.getWeek();
		   endtimes  = ltu.getLastWeek();
		}else if(c_start_end.equals("logs_month")){
			//本月
			starttimes = ltu.getMonth();
			endtimes = ltu.getLastMonth();
		}else if(c_start_end.equals("logs_quarter")){
			 //本季
			starttimes = ltu.getQuarter();
			endtimes = ltu.getLastQuarter();
		}else if(c_start_end.equals("logs_appoint_date")){
			 //指定
 	 
      starttimes=starttime;
	        Date date = endtime;
      date.setHours(24);
      endtimes=date;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      starttime1=sdf.format(starttime);
      endtime1 = sdf.format(endtime);
		}
	   Long _applytypeid =null;
		if(applytypeid!=null){
			_applytypeid =Long.parseLong(applytypeid);
		}
	String[] spGroupid=groupIds.split(",");	
	StringBuffer btdoc = new StringBuffer();
	int workTimeCount=0;
	int totaltime = 0;
	Map<String,Object> groupidmap= new HashMap<String,Object>();
	StringBuffer name = new StringBuffer("");
	StringBuffer number = new StringBuffer("");	
	StringBuffer time = new StringBuffer("");	
	if(type.equals(1)){
		for(int i = 0;i<spGroupid.length;i++){
			if(spGroupid[i].equals("1")){
				List<IrpGroup> igroup=null;
				long grupid=Long.parseLong(spGroupid[i]);
				igroup=	this.irpGroupService.ceshi(grupid);
				for(int s=0;s<igroup.size();s++){
					List<String> finalResult = new ArrayList<String>();
					finalResult =this.gets( finalResult,igroup.get(s).getGroupid().toString(),1);
					groupidmap.put(igroup.get(s).getGroupid().toString(), finalResult.size());
					List<Long> allgroupid=new ArrayList<Long>();
					for(int k=0;k<finalResult.size();k++){
						allgroupid.add(Long.parseLong(finalResult.get(k)));
					}	
					List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
					workTimeCount = irpLeaveapplyService.selectCountByList(userids,starttimes,endtimes,_applytypeid);
					totaltime = irpLeaveapplyService.selectTotaltimeByList(userids, starttimes, endtimes, _applytypeid);
					name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+"',");
					number.append(workTimeCount+",");	
					time.append(totaltime+",");
				}					
			}else{					
				List<String> finalResult = new ArrayList<String>();
				finalResult =this.gets( finalResult, spGroupid[i],1);
				groupidmap.put(spGroupid[i], finalResult.size());
				List<Long> allgroupid=new ArrayList<Long>();
				for(int k=0;k<finalResult.size();k++){
					allgroupid.add(Long.parseLong(finalResult.get(k)));
				}		
				List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
				workTimeCount = irpLeaveapplyService.selectCountByList(userids,starttimes,endtimes,_applytypeid);
				totaltime = irpLeaveapplyService.selectTotaltimeByList(userids, starttimes, endtimes, _applytypeid);
				name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+"',");
				number.append(workTimeCount+",");	
				time.append(totaltime+",");
			}				
		}
	}else{
		for(int i = 0;i<spGroupid.length;i++){
			if(spGroupid[i].equals("1")){
				List<IrpGroup> igroup=null;
				long grupid=Long.parseLong(spGroupid[i]);
				igroup=	this.irpGroupService.ceshi(grupid);
				for(int s=0;s<igroup.size();s++){
					List<String> finalResult = new ArrayList<String>();
					finalResult =this.gets( finalResult,igroup.get(s).getGroupid().toString(),1);
					groupidmap.put(igroup.get(s).getGroupid().toString(), finalResult.size());
					List<Long> allgroupid=new ArrayList<Long>();
					for(int k=0;k<finalResult.size();k++){
						allgroupid.add(Long.parseLong(finalResult.get(k)));
					}
					List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
					workTimeCount = irpLeaveapplyService.selectCountByList(userids,starttimes,endtimes,_applytypeid);
					totaltime = irpLeaveapplyService.selectTotaltimeByList(userids, starttimes, endtimes, _applytypeid);
					name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+"',");
					number.append(workTimeCount+",");
					time.append(totaltime+",");
					btdoc.append(irpGroupService.findGroupByGroupId(Long.parseLong(igroup.get(s).getGroupid().toString())).getGroupname()+","+workTimeCount+"-");						
				}					
			}else{					
				List<String> finalResult = new ArrayList<String>();
				finalResult =this.gets( finalResult, spGroupid[i],1);
				groupidmap.put(spGroupid[i], finalResult.size());
				List<Long> allgroupid=new ArrayList<Long>();
				for(int k=0;k<finalResult.size();k++){
					allgroupid.add(Long.parseLong(finalResult.get(k)));
				}		
				List<Long> userids = irpGroupService.selectIdsByGrouplist(allgroupid);
				workTimeCount = irpLeaveapplyService.selectCountByList(userids,starttimes,endtimes,_applytypeid);
				totaltime = irpLeaveapplyService.selectTotaltimeByList(userids, starttimes, endtimes, _applytypeid);
				name.append("'"+irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+"',");
				number.append(workTimeCount+",");	
				time.append(totaltime+",");
				btdoc.append(irpGroupService.findGroupByGroupId(Long.parseLong(spGroupid[i])).getGroupname()+","+workTimeCount+"-");						
			}				
		}	
	}if(type.equals(1)){
		if(name.length()>0||number.length()>0){
			this.groupname = name.toString().substring(0,name.toString().length()-1);
			this.worktimeNum = number.toString().substring(0,number.toString().length()-1);
			this.totalTime = time.toString().substring(0,time.toString().length()-1);
		}		
	}else{
		if(name.length()>0||number.length()>0||bttext.length()>0){
			this.groupname = name.toString().substring(0,name.toString().length()-1);
			this.worktimeNum = number.toString().substring(0,number.toString().length()-1);
			this.totalTime = time.toString().substring(0,number.toString().length()-1);
			this.bttext=btdoc.toString().substring(0,btdoc.toString().length()-1);
		}		
	}
	irpLeaveconfigs=this.irpLeaveconfigService.getAllOverTimeType();
	if(type.equals(1)){
		return SUCCESS;
	}else{
		return INPUT;  
	}
}
public List<String> gets( List<String> listls ,String all,int type){
	if(listls==null || listls.size()<=0){
		listls = new ArrayList<String>();
	}
	if(!listls.toString().contains(all)){
		listls.add(all);
	}
	if(type==0){
		List<IrpChannel> irpcha= this.irpChannelService.findAllChannel(Long.parseLong(all));			
		if(irpcha!=null && irpcha.size()>0){
			for(IrpChannel ele : irpcha){
				listls = this.gets(listls, ele.getChannelid().toString(),type);
			}
			return listls;
		}else {
			return listls;
		}
	}else{
		List<IrpGroup> listl = this.irpGroupService.findGroupsByParentId(Long.parseLong(all));
		if(listl!=null && listl.size()>0){
			for(IrpGroup ele : listl){
				listls = this.gets(listls, ele.getGroupid().toString(),type);
			}
			return listls;
		}else {
			return listls;
		}
	}			
}
public String getAllWorkTimeInfo(){
	 Date starttimes=null;
		Date endtimes=null;
		LogTimeUtil ltu = new LogTimeUtil();
	   if (c_start_end.equals("logs_week")) {
		   //本周
		   starttimes= ltu.getWeek();
		   endtimes  = ltu.getLastWeek();
		}else if(c_start_end.equals("logs_month")){
			//本月
			starttimes = ltu.getMonth();
			endtimes = ltu.getLastMonth();
		}else if(c_start_end.equals("logs_quarter")){
			 //本季
			starttimes = ltu.getQuarter();
			endtimes = ltu.getLastQuarter();
		}else if(c_start_end.equals("logs_appoint_date")){
			 //指定
	 
  starttimes=starttime;
	        Date date = endtime;
  date.setHours(24);
  endtimes=date;
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  starttime1=sdf.format(starttime);
  endtime1 = sdf.format(endtime);
		}
	   Long _applytypeid =null;
		if(applytypeid!=null&&!applytypeid.equals("0")){
			_applytypeid =Long.parseLong(applytypeid);
		}
	int count = irpLeaveapplyService.selectCountBySsearch(searchWord,searchType,starttimes,endtimes,_applytypeid,applystatus);
	PageUtil pageUtil = new PageUtil(pageNum, pageSize, count);
	irpLeaveapplyList = this.irpLeaveapplyService.selectInfoBySearchAndPage(pageUtil, searchWord, searchType, orderField, orderBy,starttimes,endtimes,_applytypeid,applystatus);
	irpLeaveapplyInfos=this.coverListApply(irpLeaveapplyList);
	this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
	irpLeaveconfigs=this.irpLeaveconfigService.getAllOverTimeType();
	return SUCCESS;
}
public void worktimeexportToZip(){	
	 Date starttimes=null;
		Date endtimes=null;
		LogTimeUtil ltu = new LogTimeUtil();
	   if (c_start_end.equals("logs_week")) {
		   //本周
		   starttimes= ltu.getWeek();
		   endtimes  = ltu.getLastWeek();
		}else if(c_start_end.equals("logs_month")){
			//本月
			starttimes = ltu.getMonth();
			endtimes = ltu.getLastMonth();
		}else if(c_start_end.equals("logs_quarter")){
			 //本季
			starttimes = ltu.getQuarter();
			endtimes = ltu.getLastQuarter();
		}else if(c_start_end.equals("logs_appoint_date")){
			 //指定
	 
   starttimes=starttime;
	        Date date = endtime;
   date.setHours(24);
   endtimes=date;
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   starttime1=sdf.format(starttime);
   endtime1 = sdf.format(endtime);
		}
	   Long _applytypeid =null;
		if(applytypeid!=null&&!applytypeid.equals("")){
			_applytypeid =Long.parseLong(applytypeid);
		}
	ActionContext ac = ActionContext.getContext();     
    ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);     
    String WEB_ROOT_PATH = sc.getRealPath("/"); 
    String app_path=WEB_ROOT_PATH.replace("\\", "/");
    String path=app_path+"worktime";
	List<Long> userIds = null;
		try {
			if(searchWord!=null&&!"all".equals(searchType)&&!searchWord.equals("")){
				IrpUserExample userExample = new IrpUserExample();
				com.tfs.irp.user.entity.IrpUserExample.Criteria criteria1 = userExample.createCriteria();
				com.tfs.irp.user.entity.IrpUserExample.Criteria criteria2 = userExample.createCriteria();
				criteria1.andTruenameLike("%"+this.searchWord.trim()+"%");
				criteria2.andNicknameLike("%"+this.searchWord.trim()+"%");
				userExample.or(criteria1);
				userExample.or(criteria2);
				List<IrpUser> userList = irpUserService.findUserByExample(userExample);
				if(userList!=null && userList.size()>0){
					userIds = new ArrayList<Long>();
					for(IrpUser ele : userList){
						userIds.add(ele.getUserid());
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	IrpLeaveapplyExample example = new IrpLeaveapplyExample();
	Criteria criteria = example.createCriteria();
	if(userIds!=null){
		criteria.andCruseridIn(userIds);
	}
	if(starttimes!=null&&endtimes!=null){
		criteria.andCrtimeBetween(starttimes, endtimes);
	}
	if(_applytypeid!=0){
		criteria.andApplytypeidEqualTo(_applytypeid);
	}
	criteria.andLeavemarkingEqualTo(IrpLeaveapply.WORK);
	example.setOrderByClause("cruserid,crtime");
	try {
		irpLeaveapplyList = irpLeaveapplyDAO.selectByExample(example);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	irpLeaveapplyInfos=this.coverListApply(irpLeaveapplyList);
	String fileName = ServletActionContext.getRequest().getParameter(
			"fileName");
	irpLeaveapplyService.exportAllWorkTimeToZip(irpLeaveapplyInfos, path, fileName);
}
public boolean isUpOrDel(){
	boolean isok = false;
	boolean isexit = irpCheckerLinkService.isBeginCheck(Long.parseLong(leaveapplyid));
	IrpLeaveapply irp = this.irpLeaveapplyService.getById(Long.parseLong(leaveapplyid));
	if(isexit&&irp.getApplystatus().equals(IrpLeaveapply.UNPASS)){
		isok =true;
	}
	return isok;
}
//撤销这个表单
 public void deleteleave(){
	 int msg=0;
	 if(leaveapplyid!=null){
		 //该表单是否已经被审核
		 try {
			irpLeaveapply=this.irpLeaveapplyDAO.selectByPrimaryKey(Long.parseLong(leaveapplyid));
		              List<IrpLeaveoper> list=this.irpLeaveoperService.getListByapplyId(Long.parseLong(leaveapplyid));
		 
		              if(irpLeaveapply.getApplystatus().equals(20)&&list.size()==1){
		            	  //删除级联关系
		            	  int one=this.irpLeaveoperService.deleteoper(Long.parseLong(leaveapplyid));
		            	  int two=this.irpCheckerLinkService.deleteLinkByleaveId(Long.parseLong(leaveapplyid));
		            	  //删除私信这个未完成
		            	 int three= messageService.deleteMessageByLeaveapplyId(Long.parseLong(leaveapplyid));
		            			
		            	  //删除附件
		            	  int four=irpAttachedService.deleteFileByExpt(Long.parseLong(leaveapplyid), IrpAttached.LEAVETYPE);
		 
		  
			msg=this.irpLeaveapplyDAO.deleteByPrimaryKey(Long.parseLong(leaveapplyid));
		              }
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	 
	 ActionUtil.writer(msg+ "");
 }
//表单是否可已修改
 public void booleanleave(){
	 int msg=0;
	 if(leaveapplyid!=null){
		 //该表单是否已经被审核
		 try {
			 irpLeaveapply=this.irpLeaveapplyDAO.selectByPrimaryKey(Long.parseLong(leaveapplyid));
		
			 if(irpLeaveapply!=null){
				 
				 List<IrpLeaveoper> list=this.irpLeaveoperService.getListByapplyId(Long.parseLong(leaveapplyid));
				 
				 if(irpLeaveapply.getApplystatus().equals(20)&&list.size()==1){
					 msg=1;
				 }
			 }
		 } catch (NumberFormatException e) {
			 e.printStackTrace();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 ActionUtil.writer(msg+ "");
 }
//表单是否可已修改
 public void booleanisDelete(){
	 int msg=0;
	 if(leaveapplyid!=null){
		 //该表单是否已经被审核
		 try {
			 irpLeaveapply=this.irpLeaveapplyDAO.selectByPrimaryKey(Long.parseLong(leaveapplyid));
			 
			 if(irpLeaveapply!=null){
				 
				
					 msg=1;
				 
			 }
		 } catch (NumberFormatException e) {
			 e.printStackTrace();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 ActionUtil.writer(msg+ "");
 }

 //更新表单
 public void doupdateleave(){
	 int msg=0;
	 try {
		irpLeaveapply=this.irpLeaveapplyDAO.selectByPrimaryKey(Long.parseLong(leaveapplyid));
	} catch (NumberFormatException e1) {
		e1.printStackTrace();
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	 List<IrpLeaveoper> list2=this.irpLeaveoperService.getListByapplyId(Long.parseLong(leaveapplyid));
	 //该表单还是未审核状态
	 if(irpLeaveapply.getApplystatus().equals(20)&&list2.size()==1){
		
 	Long msg1=0L;
 	IrpLeaveapply irpLeaveapply1=new IrpLeaveapply();
 	irpLeaveapply1.setLeaveapplyid(Long.parseLong(leaveapplyid));
 	 irpLeaveapply1.setStarttime(DateUtils.getDateByYMDHM(starttime1));
 	 irpLeaveapply1.setApplyreason(applyreason);
 	 irpLeaveapply1.setEndtime(DateUtils.getDateByYMDHM(endtime1));
 	 irpLeaveapply1.setApplytypeid(leaveconfigid); 	
 	 irpLeaveapply1.setEmergency(emergency);
 	 irpLeaveapply1.setLeavemarking(IrpLeaveapply.LEAVE);
 	 irpLeaveapply1.setApplystatus(IrpLeaveapply.UNPASS);   
 	 irpLeaveapply1.setLeavedays(Float.parseFloat(leavedays));
 	 irpLeaveapply1.setCruserid(LoginUtil.getLoginUserId());
 	//会议通知类型
	StringBuffer warnMenthodBuffer=new StringBuffer();
	if(null!=warnMenthod&&warnMenthod.length>0){
		for (int i = 0; i < warnMenthod.length; i++) {
			warnMenthodBuffer.append(warnMenthod[i]);
		}
		irpLeaveapply1.setWarnid(Integer.parseInt(warnMenthodBuffer.toString()));
	}else{
		irpLeaveapply1.setWarnid(0);
	}
 	int flag1=irpLeaveapply.getCheckmore();
	 irpLeaveapply1.setCheckmore(flag1+1);
		if (jsonFile != null && jsonFile != "") {    		
			// 如果存在附件，则添加附件
			JSONArray _Array = JSONArray.fromObject(jsonFile);
			List<Long> _arrAttachedid = new ArrayList<Long>();
			_arrAttachedid.add(0L);//加入集合
			for (int i = 0; i < _Array.size(); i++) {
				JSONObject jsonObject = (JSONObject) _Array
						.getJSONObject(i);
				String sattfile = jsonObject.getString("attfile");
				String sattdesc = jsonObject.getString("attdesc");
				String sattlinkalt = jsonObject.getString("attlinkalt");
				String seditversions = jsonObject.getString("editversions");
				String attflag = jsonObject.getString("attflag");
				Long attachedid =Long.parseLong(jsonObject.getString("attachedid"));
				if(attachedid!=0L){
				}
				// 获得文件类型
				Long typeid = irpAttachedTypeService
						.findAttachedTypeIdByFileExt(FileUtil
								.findFileExt(sattfile));
				// 入库
				if(attachedid==0L){
					List<Long> id =addAttQuestionFile(Integer.parseInt(attflag), sattfile,
							typeid, Long.parseLong(leaveapplyid), sattdesc, sattlinkalt, seditversions,
							false, null, false);
					_arrAttachedid.add(id.get(0));
				}else{
					 irpAttachedService.udpateAttachedByExprt(Long.parseLong(leaveapplyid), attachedid, Integer.parseInt(seditversions),Integer.parseInt(attflag));
					    _arrAttachedid.add(attachedid);//加入集合
				}			
			}
			//删除数据库文件 不在集合中的文件
			irpAttachedService.deleteFileNotInList(_arrAttachedid, Long.parseLong(leaveapplyid),200);
		}

	
 	try {
 		 List<IrpLeaveoper> list=this.irpLeaveoperService.getListByapplyId(Long.parseLong(leaveapplyid));
 		if(list.size()==1){
 		 //验证审核人是否变化
 		 if(!list.get(0).getUserid().equals(Long.parseLong(userids))){
 			 //删除旧的级联关系，增加新的级联关系
 			 int one=this.irpLeaveoperService.deleteoper(Long.parseLong(leaveapplyid));
       	  int two=this.irpCheckerLinkService.deleteLinkByleaveId(Long.parseLong(leaveapplyid));
       	msg1=this.irpLeaveoperService.addOper(Long.parseLong(leaveapplyid),userids);
        //增加第一个级联审核人
     	 irpCheckerLink=new IrpCheckerLink();
     	 irpCheckerLink.setLeaveapplyid(Long.parseLong(leaveapplyid));
     	 irpCheckerLink.setNextuserid(Long.parseLong(userids));
     	 irpCheckerLink.setLeavemarking(IrpLeaveapply.LEAVE);
     	 int flag=this.irpCheckerLinkService.addIrpCheckerLink(irpCheckerLink);
     	
 			 //删除就得私信，发送新的私信
       	int three= messageService.deleteMessageByLeaveapplyId(Long.parseLong(leaveapplyid));
       	title=irpLeaveapply.getTitle();
       	if(null!=warnMenthodBuffer){
			 String warn_1=warnMenthodBuffer.toString();
			if(warn_1.indexOf("1")!=-1){//私信
				this.sendAddMessage(Long.parseLong(userids),Long.parseLong(leaveapplyid),0,IrpLeaveapply.LEAVE);
			}
			if(warn_1.indexOf("2")!=-1){//短信
				this.sendSms(Long.parseLong(userids),Long.parseLong(leaveapplyid),0,IrpLeaveapply.LEAVE);
			}
			if(warn_1.indexOf("3")!=-1){//邮件
				this.sendEamil(Long.parseLong(userids),Long.parseLong(leaveapplyid),0,IrpLeaveapply.LEAVE);
			}
		 }
 		 }	
 		irpLeaveapplyDAO.updateByPrimaryKeySelective(irpLeaveapply1); 	
 		msg=1;
 		}else{
 			
 			msg=0;
 			
 		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }else{
		 msg=0;
		 
	 }
		ActionUtil.writer(msg+ "");

 	 
 	
 	
 	
 
	 
 }
 
	//是否显示时候为附件的按钮
 public int  isRadio(String fileName)
 {   
 	 
 	/**
 	 * 根据扩展名查询他的id看他是不是图片，如果是图片，返回一个常量，
 	 * 在页面可以利用他来显示因此时候封面的按钮
 	 * 字段editversions
 	 */
 	int isTrue=0;
 	String _sFileExt=FileUtil.findFileExt(fileName);
 	Long atttypeid=irpAttachedTypeService.findAttachedTypeIdByFileExt(_sFileExt); //查询所附件类型id
 	/**
 	 * 如果附件id==0，则没有找到
 	 * 若果附件类型等于图片类型，则标示
 	 */ 
 	if(atttypeid!=null &&atttypeid.toString()!="0"){
 		isTrue=0; 
 	}
 	if(atttypeid==IrpAttachedType.JPG_FIELD_NAME){ 
 		isTrue=1;
 	}
	return isTrue;
 	
 }


public void isDeleteLeaveapply(){
	int res = 0;
	if(this.isUpOrDel()){
		res =irpLeaveapplyService.deleteLeaveapply(Long.parseLong(leaveapplyid));
		irpLeaveoperService.deleteLeaveoper(Long.parseLong(leaveapplyid));
	}
	ActionUtil.writer(res+"");

}
public void isUpdate(){
	int res = 0;
	if(this.isUpOrDel()){
		res = 1;
	}
	ActionUtil.writer(res+"");
}
public String getLeaveapplyInfo(){
	List<Long> list=  irpLeaveoperService.getCheckuserids(Long.parseLong(leaveapplyid));
	userids =list.get(0).toString();
	irpLeaveapply =this.irpLeaveapplyService.getById(Long.parseLong(leaveapplyid));
	findAllAssewarn();
	String warnString=irpLeaveapply.getWarnid()==null?"":irpLeaveapply.getWarnid().toString();
	List<IrpAssewarn> warnlist=new ArrayList<IrpAssewarn>();
	warnlist.addAll(assewarnList);
	assewarnList.clear();
	for (IrpAssewarn warn : warnlist) {
		if(warnString.indexOf(warn.getWarnid().toString())!=-1){
			warn.setCruserid(0L);
		}
		assewarnList.add(warn);
	}
	attacheds = this.irpAttachedService.getAttachedByAttDocId(Long.parseLong(leaveapplyid),
			IrpAttached.LEAVETYPE);
	if(attacheds!=null && attacheds.size()>0){ 
		List<IrpAttachedInfo> _attachedinfos = new ArrayList<IrpAttachedInfo>();
		for (int j = 0; j < attacheds.size(); j++) {
			IrpAttachedInfo s=new IrpAttachedInfo();
			s.setAttachedid(attacheds.get(j).getAttachedid());
			s.setAttdocid(attacheds.get(j).getAttdocid());
			s.setAttdesc(attacheds.get(j).getAttdesc());
			s.setAttfile(attacheds.get(j).getAttfile());
			if(isRadio(attacheds.get(j).getAttfile())==1){
				
				s.setFlag("true");
			}else{
				s.setFlag("false");
				
			}
			_attachedinfos.add(s);
		}
		attachedinfos =  _attachedinfos;
	} 
	irpLeaveconfigs=this.irpLeaveconfigService.getAllOverTimeType();
//获得用户所属的组织
  userGroups = irpGroupService.findGroupIdsByUserId(irpLeaveapply.getCruserid());
	return SUCCESS;
}
public void upLeaveapplyinfo(){
	int res = 0;
    irpLeaveapply = irpLeaveapplyService.getById(Long.parseLong(leaveapplyid));
    irpLeaveapply.setStarttime(DateUtils.getDateByYMDHM(starttime1));
    irpLeaveapply.setApplyreason(applyreason);
    irpLeaveapply.setEndtime(DateUtils.getDateByYMDHM(endtime1));
    irpLeaveapply.setApplytypeid(leaveconfigid);
    irpLeaveapply.setEmergency(emergency);
    irpLeaveapply.setLeavemarking(IrpLeaveapply.WORK);
    irpLeaveapply.setApplystatus(IrpLeaveapply.UNPASS);
    irpLeaveapply.setCruserid(LoginUtil.getLoginUserId());
    irpLeaveapply.setLeavedays(Float.parseFloat(leavedays));
    irpLeaveapply.setContent(content);
    irpLeaveapply.setAddress(address);
  //通知类型
	StringBuffer warnMenthodBuffer=new StringBuffer();
	if(null!=warnMenthod&&warnMenthod.length>0){
		for (int i = 0; i < warnMenthod.length; i++) {
			warnMenthodBuffer.append(warnMenthod[i]);
		}
		irpLeaveapply.setWarnid(Integer.parseInt(warnMenthodBuffer.toString()));
	}else{
		irpLeaveapply.setWarnid(0);
	}
    irpLeaveapply.setCheckmore(IrpLeaveapply.CHECKMORE1+1);
	res = this.irpLeaveapplyService.upLeaveapplyinfo(irpLeaveapply);
	IrpLeaveoper oper = irpLeaveoperService.selIrpLeaveoper(Long.parseLong(leaveapplyid));
	 if(null!=warnMenthodBuffer){
		 String warn_1=warnMenthodBuffer.toString();
			if(warn_1.indexOf("1")!=-1){//私信
				if(oper.getUserid()!=Long.parseLong(userids)){
					this.sendWorkMessage(Long.parseLong(userids),
							Long.parseLong(leaveapplyid),2,IrpLeaveapply.WORK);
				}
			}
			if(warn_1.indexOf("2")!=-1){//短信
				this.sendSms(Long.parseLong(userids),
						Long.parseLong(leaveapplyid),2,IrpLeaveapply.WORK);
			}
			if(warn_1.indexOf("3")!=-1){//邮件
				this.sendEamil(Long.parseLong(userids),
						Long.parseLong(leaveapplyid),2,IrpLeaveapply.WORK);
			}
	 }
	oper.setUserid(Long.parseLong(userids));
	irpLeaveoperService.upcheckuser(oper);
	if (jsonFile != null && jsonFile != "") {    		
		// 如果存在附件，则添加附件
		JSONArray _Array = JSONArray.fromObject(jsonFile);
		List<Long> _arrAttachedid = new ArrayList<Long>();
		_arrAttachedid.add(0L);//加入集合
		for (int i = 0; i < _Array.size(); i++) {
			JSONObject jsonObject = (JSONObject) _Array
					.getJSONObject(i);
			String sattfile = jsonObject.getString("attfile");
			String sattdesc = jsonObject.getString("attdesc");
			String sattlinkalt = jsonObject.getString("attlinkalt");
			String seditversions = jsonObject.getString("editversions");
			String attflag = jsonObject.getString("attflag");
			Long attachedid =Long.parseLong(jsonObject.getString("attachedid"));
			if(attachedid!=0L){
			}
			// 获得文件类型
			Long typeid = irpAttachedTypeService
					.findAttachedTypeIdByFileExt(FileUtil
							.findFileExt(sattfile));
			// 入库
			if(attachedid==0L){
				List<Long> id =addAttQuestionFile(Integer.parseInt(attflag), sattfile,
						typeid, Long.parseLong(leaveapplyid), sattdesc, sattlinkalt, seditversions,
						false, null, false);
				_arrAttachedid.add(id.get(0));
			}else{
				 irpAttachedService.udpateAttachedByExprt(Long.parseLong(leaveapplyid), attachedid, Integer.parseInt(seditversions),Integer.parseInt(attflag));
				    _arrAttachedid.add(attachedid);//加入集合
			}			
		}
		//删除数据库文件 不在集合中的文件
		irpAttachedService.deleteFileNotInList(_arrAttachedid, Long.parseLong(leaveapplyid),200);
	}
	ActionUtil.writer(res+"");
}
//查看某个文档的所有附件
	public void allAttachedToDocument() {
		attacheds = irpAttachedService.getAttachedByAttDocId(Long.parseLong(leaveapplyid), 200);
		List list = new ArrayList();
		if (attacheds != null && attacheds.size() > 0) {
			for (int i = 0; i < attacheds.size(); i++) {
				IrpAttached attached = new IrpAttached();
				IrpAttached att = attacheds.get(i);
				attached.setAttachedid(att.getAttachedid());
				attached.setAttdesc(att.getAttdesc());
				attached.setEditversions(att.getEditversions());
				attached.setAttlinkalt(att.getAttlinkalt());
				list.add(attached);
			}
		}
		ActionUtil.writer(JsonUtil.list2json(attacheds));
	}
	public void deleteleaveADD(){
		int msg=0;
		if(leaveapplyid!=null){
			msg=this.irpAttachedService.delAttachedByPrimaryKey(Long.parseLong(leaveapplyid));
		}
		ActionUtil.writer(msg+"");
	}
public void isRefase(){
	irpLeaveapply = irpLeaveapplyService.getById(Long.parseLong(leaveapplyid));	
	ActionUtil.writer(irpLeaveapply.getCheckmore().toString());
}
/**
 * 查询所有通知类型
 */
public void findAllAssewarn(){
	IrpAssewarnExample example=new IrpAssewarnExample();
	example.setOrderByClause("warnid asc");
	try {
		assewarnList=assewarnService.selectByExample(example);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}

