package com.tfs.irp.mobile.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.bug.entity.IrpBugExample;
import com.tfs.irp.bug.entity.IrpBugWithBLOBs;
import com.tfs.irp.bug.entity.IrpBugExample.Criteria;
import com.tfs.irp.bug.service.BugService;
import com.tfs.irp.bug_config.entity.IrpBugConfig;
import com.tfs.irp.bug_config.entity.IrpBugConfigExample;
import com.tfs.irp.bug_config.service.BugConfigService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.project.entity.IrpProjectExample;
import com.tfs.irp.project.service.IrpProjectService;
import com.tfs.irp.projectperson.entity.IrpProjectPerson;
import com.tfs.irp.projectperson.entity.IrpProjectPersonExample;
import com.tfs.irp.projectperson.service.IrpProjectPersonService;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.upush.IOSPush;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;

public class easyBugAction extends ActionSupport {
	private BugService bugService;
	private IrpUserService userService;
	private IrpProjectService projectService;
	private IrpAttachedService irpAttachedService;
	private IrpProjectPersonService projectpersonservice;
	private BugConfigService bugconfigservice;
	private List<IrpBugWithBLOBs> irpBugs;
	private Long serianum;
	private Long loginuserid;
	/**
	 * 最新状态
	 */
	private String lastBugState;
	/**
	 * 分配人trueName Map
	 */
	private Map<Long, String> founderNameMap = new HashMap<Long, String>();
	/**
	 * 处理人trueName Map
	 */
	private Map<Long, String> operatorNameMap = new HashMap<Long, String>();
	/**
	 * 所属项目
	 */
	private IrpProject project;
	private List<IrpAttached> attacheds;
	/**
	 * 是否是项目的创建者  1代表是    0代表不是
	 */
	private int isprojectcre=0;
	/**
	 * 所属项目ID
	 */
	private Long projectId;
	private Map<String, String> priorityMap = new HashMap<String, String>();
	private Map<Long,String> versionNameMap=new HashMap<Long, String>();
	private Map<Long,String> modulNameMap=new HashMap<Long, String>();
	/**
	 * 项目成员
	 */
	private List<IrpUser> projectUsers;
	/**
	 * 页面处理权限 2状态什么也不显示 1状态什么都显示 3状态不显示未完成按钮
	 */
	private int power = 0;
	
	public BugService getBugService() {
		return bugService;
	}
	public void setBugService(BugService bugService) {
		this.bugService = bugService;
	}
	public IrpUserService getUserService() {
		return userService;
	}
	public void setUserService(IrpUserService userService) {
		this.userService = userService;
	}
	public IrpProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(IrpProjectService projectService) {
		this.projectService = projectService;
	}
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}
	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}
	public IrpProjectPersonService getProjectpersonservice() {
		return projectpersonservice;
	}
	public void setProjectpersonservice(IrpProjectPersonService projectpersonservice) {
		this.projectpersonservice = projectpersonservice;
	}
	public BugConfigService getBugconfigservice() {
		return bugconfigservice;
	}
	public void setBugconfigservice(BugConfigService bugconfigservice) {
		this.bugconfigservice = bugconfigservice;
	}
	public List<IrpBugWithBLOBs> getIrpBugs() {
		return irpBugs;
	}
	public void setIrpBugs(List<IrpBugWithBLOBs> irpBugs) {
		this.irpBugs = irpBugs;
	}
	public Long getSerianum() {
		return serianum;
	}
	public void setSerianum(Long serianum) {
		this.serianum = serianum;
	}
	public Long getLoginuserid() {
		return loginuserid;
	}
	public void setLoginuserid(Long loginuserid) {
		this.loginuserid = loginuserid;
	}
	public String getLastBugState() {
		return lastBugState;
	}
	public void setLastBugState(String lastBugState) {
		this.lastBugState = lastBugState;
	}
	public Map<Long, String> getFounderNameMap() {
		return founderNameMap;
	}
	public void setFounderNameMap(Map<Long, String> founderNameMap) {
		this.founderNameMap = founderNameMap;
	}
	public Map<Long, String> getOperatorNameMap() {
		return operatorNameMap;
	}
	public void setOperatorNameMap(Map<Long, String> operatorNameMap) {
		this.operatorNameMap = operatorNameMap;
	}
	public IrpProject getProject() {
		return project;
	}
	public void setProject(IrpProject project) {
		this.project = project;
	}
	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}
	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	}
	public int getIsprojectcre() {
		return isprojectcre;
	}
	public void setIsprojectcre(int isprojectcre) {
		this.isprojectcre = isprojectcre;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Map<String, String> getPriorityMap() {
		return priorityMap;
	}
	public void setPriorityMap(Map<String, String> priorityMap) {
		this.priorityMap = priorityMap;
	}
	public Map<Long, String> getVersionNameMap() {
		return versionNameMap;
	}
	public void setVersionNameMap(Map<Long, String> versionNameMap) {
		this.versionNameMap = versionNameMap;
	}
	public Map<Long, String> getModulNameMap() {
		return modulNameMap;
	}
	public void setModulNameMap(Map<Long, String> modulNameMap) {
		this.modulNameMap = modulNameMap;
	}
	public List<IrpUser> getProjectUsers() {
		return projectUsers;
	}
	public void setProjectUsers(List<IrpUser> projectUsers) {
		this.projectUsers = projectUsers;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	/**
	 * 判断是否在该项目中
	 * @return
	 */
	private int isInProject(){
		IrpProjectPersonExample example = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		this.loginuserid=irpuser.getUserid();
		try {
			this.projectId=this.bugService.selectByPrimaryKey(serianum).getProjectid();
			example = new IrpProjectPersonExample();
			com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria=example.createCriteria();
			criteria.andPridEqualTo(projectId);
			criteria.andPersonidEqualTo(loginuserid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.projectpersonservice.findPersonByExample(example).size();
	}
	/**
	 * 查询当前登录的用户是否是这个项目的创始人   projectId
	 */
	private void isProjectCre(Long pid){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		this.loginuserid=irpuser.getUserid();
		IrpProjectPersonExample example=new IrpProjectPersonExample();
		com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria=example.createCriteria();
		criteria.andPridEqualTo(pid).andCruseridEqualTo(loginuserid);
		List<IrpProjectPerson> list = projectpersonservice.findPersonByExample(example);
		if(list.size()>0){
			this.isprojectcre=1;
		}
	}
	private String tokens;
	
	public String getTokens() {
		return tokens;
	}
	public void setTokens(String tokens) {
		this.tokens = tokens;
	}
	public String simplebuginfo() {
		String result=null;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			tokens = token;
			IrpUser irpuser = mobileAction.getlogin(token);
			this.loginuserid=irpuser.getUserid();
			IrpBugExample example = new IrpBugExample();
			example.setOrderByClause("BUGID");
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			irpBugs = bugService.selectByExample(example);
			if(irpBugs.size()>0){
				if(isInProject()>0){
				this.lastBugState = irpBugs.get(irpBugs.size() - 1).getState();
				List<IrpUser> userList = userService
						.findUserByExample(new IrpUserExample());
				for (IrpBugWithBLOBs ele : irpBugs) {
					for (IrpUser element : userList) {
						if (ele.getFounderid() == Integer.parseInt(element
								.getUserid() + "")) {
							founderNameMap.put(ele.getFounderid(),
									element.getTruename());
							break;
						}
					}
				}
				for (IrpBugWithBLOBs ele : irpBugs) {
					for (IrpUser element : userList) {
						if (ele.getOperatorid() != null
								&& !ele.getOperatorid().equals("")) {
							if (ele.getOperatorid() == Integer.parseInt(element
									.getUserid() + "")) {
								operatorNameMap.put(ele.getOperatorid(),
										element.getTruename());
								break;
							}
						}
					}
				}
				project = projectService.findProjectById(irpBugs.get(0)
						.getProjectid());
				//System.out.println(1);
				attacheds = this.irpAttachedService.getAttachedByAttDocId(serianum,
						IrpAttached.BUGDOCTYPEID);
				this.projectId=project.getId();
				isProjectCre(project.getId());
				this.vmNameMapInit();
				// 获取项目成员,不包含自己
				getProjectUser(irpBugs.get(0).getProjectid(), false);
				powerInit();
				initPriority();
				result= SUCCESS;
				}else{
					ActionUtil.writer("0");
				}
				// operatorTrueName=userService.findUserByUserId(bug.getOperatorid()).getTruename();
			}else{
				ActionUtil.writer("已删除");
				result= ERROR;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 初始化优先级
	 */
	private void initPriority() {
		this.priorityMap.put("1", "低");
		this.priorityMap.put("2", "中");
		this.priorityMap.put("3", "高");
		this.priorityMap.put("4", "紧急");
		this.priorityMap.put("5", "严重");
	}
	/**
	 * 初始化版本模块名字信息
	 */
	private void vmNameMapInit(){
		try {
			if (projectId != null && !projectId.equals("")) {
				IrpBugConfigExample example = new IrpBugConfigExample();
				com.tfs.irp.bug_config.entity.IrpBugConfigExample.Criteria criteria = example
						.createCriteria();
				criteria.andProidEqualTo(this.projectId);
				List<IrpBugConfig> bclist=this.bugconfigservice.selectByExample(example);
				if(bclist.size()>0){
					for(IrpBugConfig ele :bclist){
						if(ele.getConfigtype().equals(IrpBugConfig.TYPE_VERSION)){
							this.versionNameMap.put(ele.getBugconfigid(), ele.getVersionname());
						}else if(ele.getConfigtype().equals(IrpBugConfig.TYPE_MODUL)){
							this.modulNameMap.put(ele.getBugconfigid(), ele.getModulname());
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取项目成员 true 所有项目成员,false 不包括登录用户
	 * 
	 * @param projectid
	 * @param bl
	 */
	private void getProjectUser(Long projectid, boolean bl) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		this.loginuserid=irpuser.getUserid();
		IrpProjectPersonExample example = new IrpProjectPersonExample();
		com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria = example
				.createCriteria();
		if (!bl) {
			Long loginid = irpuser.getUserid();
			criteria.andPersonidNotEqualTo(loginid);
		}
		criteria.andPridEqualTo(projectid);
		List<IrpProjectPerson> projectPersons = this.projectpersonservice
				.findPersonByExample(example);
		List<Long> presonIds = new ArrayList<Long>();
		for (IrpProjectPerson ele : projectPersons) {
			presonIds.add(ele.getPersonid());
		}
		IrpUserExample userExample = new IrpUserExample();
		userExample.setOrderByClause("USERID");
		com.tfs.irp.user.entity.IrpUserExample.Criteria userCriteria = userExample
				.createCriteria();
		if (presonIds.size() > 0) {
			userCriteria.andUseridIn(presonIds);
			projectUsers = userService.findUserByExample(userExample);
		} else {
			projectUsers = null;
		}
	}
	/**
	 * 初始化页面权限
	 * 
	 * @return
	 */
	private void powerInit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		this.loginuserid=irpuser.getUserid();
		try {
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
			IrpBugWithBLOBs pbug = bugService.selectByExample(example).get(0);
			if (IrpBugWithBLOBs.STATE_SHENHE.equals(pbug.getState())) {
				if (pbug.getFounderid().equals(loginuserid)) {
					power = 1;
				} else if (pbug.getOperatorid().equals(
						loginuserid)) {
					power = 2;
				}
			} else if (IrpBugWithBLOBs.STATE_WEIWAN.equals(pbug.getState())) {
				if (pbug.getFounderid().equals(loginuserid)) {
					power = 3;
				} else if (pbug.getOperatorid().equals(
						loginuserid)) {
					power = 2;
				}
			} else {
				power = 2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到Bug统计页面
	 * @return
	 */
	public String toStatisticsPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser irpuser = mobileAction.getlogin(token);
		return SUCCESS;
	}
	/**
	 * 查询统计数据
	 * @return
	 */
	public String initBugStatistics(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens = token;
		IrpUser irpuser = mobileAction.getlogin(token);
		try {
			initBugRenyuanStatistics();
			countForState();
			countForModul();
			fountForfenbu();
			countForZoushi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	private String projectPersonsJson;
	
	public String getProjectPersonsJson() {
		return projectPersonsJson;
	}
	public void setProjectPersonsJson(String projectPersonsJson) {
		this.projectPersonsJson = projectPersonsJson;
	}
	/**
	 * 统计人员分布情况
	 */
	private void initBugRenyuanStatistics(){
		this.getProjectUser(projectId,true);
		List<String> projectPersonStr=new ArrayList<String>();
		for(IrpUser ele: projectUsers){
			projectPersonStr.add(ele.getTruename());
		}
		this.projectPersonsJson=JsonUtil.list2json(projectPersonStr);
	}
	private String startDate;
	
	private String endDate;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获得指定时间范围内创建的BugID集合
	 * @return
	 * @throws Exception
	 */
	private List<Long> getIdsByDate()throws Exception{
		IrpBugExample example=new IrpBugExample();
		Criteria criteria=example.createCriteria();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		Date begin = DateUtils.getDateByStrToYMD(startDate);
		Date end = DateUtils.getDateByStrToYMD(endDate);
		criteria.andCreatetimeBetween(begin, end);
		//criteria.andCreatetimeBetween(this.startDate, this.endDate);
		criteria.andProjectidEqualTo(projectId);
		List<IrpBugWithBLOBs> bs=bugService.selectByExample(example);
		List<Long> ids =new ArrayList<Long>();
		for(IrpBugWithBLOBs ib:bs){
			ids.add(ib.getBugid());
		}
		return ids;
	}
	//reyuan
		private String weiBugsJson;
		//renyuan
		private String shenBugsJson;
		//renyuan
		private String wanBugsJson;
		
		//modul
		private String mweiBugsJson;
		private String mshenBugsJson;
		private String mwanBugsJson; 
	
	public String getWeiBugsJson() {
			return weiBugsJson;
		}
		public void setWeiBugsJson(String weiBugsJson) {
			this.weiBugsJson = weiBugsJson;
		}
		public String getShenBugsJson() {
			return shenBugsJson;
		}
		public void setShenBugsJson(String shenBugsJson) {
			this.shenBugsJson = shenBugsJson;
		}
		public String getWanBugsJson() {
			return wanBugsJson;
		}
		public void setWanBugsJson(String wanBugsJson) {
			this.wanBugsJson = wanBugsJson;
		}
		public String getMweiBugsJson() {
			return mweiBugsJson;
		}
		public void setMweiBugsJson(String mweiBugsJson) {
			this.mweiBugsJson = mweiBugsJson;
		}
		public String getMshenBugsJson() {
			return mshenBugsJson;
		}
		public void setMshenBugsJson(String mshenBugsJson) {
			this.mshenBugsJson = mshenBugsJson;
		}
		public String getMwanBugsJson() {
			return mwanBugsJson;
		}
		public void setMwanBugsJson(String mwanBugsJson) {
			this.mwanBugsJson = mwanBugsJson;
		}
	/**
	 * 统计项目内每个成员未完/完成/待审核Bug的数量
	 * @throws Exception
	 */
	private void countForState() throws Exception{
		List<Integer> weiBugs=new ArrayList<Integer>();
		List<Integer> wanBugs=new ArrayList<Integer>();
		List<Integer> shenBugs=new ArrayList<Integer>();
		IrpBugExample example=new IrpBugExample();
		List<Long> ss=this.getIdsByDate();
		for(IrpUser ele:projectUsers){
			example.clear();
			int wei=0;
			int wan=0;
			int shen=0;
			Criteria criteria=example.createCriteria();
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
			criteria.andFounderidEqualTo(ele.getId());
			criteria.andProjectidEqualTo(projectId);
			if(ss.size()>0){
				criteria.andSerianumIn(ss);
			}else{
				criteria.andBugidIsNull();
			}
			
			List<IrpBugWithBLOBs> bugs=this.bugService.selectByExample(example);
			for(IrpBugWithBLOBs bug:bugs){
				if(IrpBugWithBLOBs.STATE_WEIWAN.equals(bug.getNeweststate())){
					wei++;
				}else if(IrpBugWithBLOBs.STATE_WANCHENG.equals(bug.getNeweststate())){
					wan++;
				}else if(IrpBugWithBLOBs.STATE_SHENHE.equals(bug.getNeweststate())){
					shen++;
				}
			}
			weiBugs.add(wei);
			wanBugs.add(wan);
			shenBugs.add(shen);
		}
		this.weiBugsJson=JsonUtil.list2json(weiBugs);
		this.wanBugsJson=JsonUtil.list2json(wanBugs);
		this.shenBugsJson=JsonUtil.list2json(shenBugs);
	}
	private List<IrpBugConfig> bugmoduls=new ArrayList<IrpBugConfig>();
	
	public List<IrpBugConfig> getBugmoduls() {
		return bugmoduls;
	}
	public void setBugmoduls(List<IrpBugConfig> bugmoduls) {
		this.bugmoduls = bugmoduls;
	}
	/**
	 * 查询项目下所有模块
	 */
	private void findModulByProid() {
		try {
			if (projectId != null && !projectId.equals("")) {
				IrpBugConfigExample example = new IrpBugConfigExample();
				com.tfs.irp.bug_config.entity.IrpBugConfigExample.Criteria criteria = example
						.createCriteria();
				criteria.andProidEqualTo(projectId);
				criteria.andConfigtypeEqualTo(1l);
				example.setOrderByClause(" BUGCONFIGID");
				this.bugmoduls = bugconfigservice.selectByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private String bugsForModulJson;
	
	public String getBugsForModulJson() {
		return bugsForModulJson;
	}
	public void setBugsForModulJson(String bugsForModulJson) {
		this.bugsForModulJson = bugsForModulJson;
	}
	/**
	 * 统计不同模块bug数量     已解决/待审核/未解决
	 */
	private void countForModul()throws Exception{
		List<Integer> mweiBugs=new ArrayList<Integer>();
		List<Integer> mwanBugs=new ArrayList<Integer>();
		List<Integer> mshenBugs=new ArrayList<Integer>();
		List<String>  bugsForModul=new ArrayList<String>();
		findModulByProid();
		bugsForModul.add("默认模块");
		for(IrpBugConfig ibc:bugmoduls){
			bugsForModul.add(ibc.getModulname());
		}
		this.bugsForModulJson=JsonUtil.list2json(bugsForModul);
		IrpBugExample example=new IrpBugExample();
		Criteria criteria=example.createCriteria();
		criteria.andModuleidIsNull();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		Date begin = DateUtils.getDateByStrToYMD(startDate);
		Date end = DateUtils.getDateByStrToYMD(endDate);
		criteria.andCreatetimeBetween(begin, end);
		//criteria.andCreatetimeLessThanOrEqualTo(DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
		List<IrpBugWithBLOBs> mobugs=bugService.selectByExample(example);
		int mwei=0;
		int mwan=0;
		int mshen=0;
		for(IrpBugWithBLOBs el:mobugs){
			if(IrpBugWithBLOBs.STATE_WEIWAN.equals(el.getNeweststate())){
				mwei++;
			}else if(IrpBugWithBLOBs.STATE_WANCHENG.equals(el.getNeweststate())){
				mwan++;
			}else if(IrpBugWithBLOBs.STATE_SHENHE.equals(el.getNeweststate())){
				mshen++;
			}
		}
		mweiBugs.add(mwei);
		mwanBugs.add(mwan);
		mshenBugs.add(mshen);
		for(IrpBugConfig ele :bugmoduls){
			int wei=0;
			int wan=0;
			int shen=0;
			example.clear();
			criteria=example.createCriteria();
			criteria.andModuleidEqualTo(ele.getBugconfigid());
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
			criteria.andCreatetimeBetween(begin, end);
			//criteria.andCreatetimeBetween(startDate, DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
			//criteria.andCreatetimeLessThanOrEqualTo(DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
			List<IrpBugWithBLOBs> bgs=bugService.selectByExample(example);
			for(IrpBugWithBLOBs el:bgs){
				if(IrpBugWithBLOBs.STATE_WEIWAN.equals(el.getNeweststate())){
					wei++;
				}else if(IrpBugWithBLOBs.STATE_WANCHENG.equals(el.getNeweststate())){
					wan++;
				}else if(IrpBugWithBLOBs.STATE_SHENHE.equals(el.getNeweststate())){
					shen++;
				}
			}
			mweiBugs.add(wei);
			mwanBugs.add(wan);
			mshenBugs.add(shen);
		}
		
		this.mweiBugsJson=JsonUtil.list2json(mweiBugs);
		this.mwanBugsJson=JsonUtil.list2json(mwanBugs);
		this.mshenBugsJson=JsonUtil.list2json(mshenBugs);
		
	}
	private List<Map> fenbu1=new ArrayList<Map>();
	
	private List<Map> fenbu2=new ArrayList<Map>();
	
	public List<Map> getFenbu1() {
		return fenbu1;
	}
	public void setFenbu1(List<Map> fenbu1) {
		this.fenbu1 = fenbu1;
	}
	public List<Map> getFenbu2() {
		return fenbu2;
	}
	public void setFenbu2(List<Map> fenbu2) {
		this.fenbu2 = fenbu2;
	}
	/**
	 * 分布区域统计
	 * @throws Exception
	 */
	private void fountForfenbu() throws Exception{
		IrpBugExample example=new IrpBugExample();
		Criteria criteria=example.createCriteria();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
		criteria.andProjectidEqualTo(projectId);
		Date begin = DateUtils.getDateByStrToYMD(startDate);
		Date end = DateUtils.getDateByStrToYMD(endDate);
		criteria.andCreatetimeBetween(begin, end);
		//criteria.andCreatetimeBetween(startDate, DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
		//criteria.andCreatetimeLessThanOrEqualTo(DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
		List<IrpBugWithBLOBs> fbgs=bugService.selectByExample(example);
		List<String> fenbu1List =new ArrayList<String>();
		Map yan = new HashMap();
		Map jin = new HashMap();
		Map gao = new HashMap();
		Map zhong = new HashMap();
		Map di = new HashMap();
		Map wan = new HashMap();
		Map wei = new HashMap();
		Map shen = new HashMap();
		int nyan = 0;
		int njin = 0;
		int ngao = 0;
		int nzhong = 0;
		int ndi = 0;
		int nwei =0;
		int nwan =0;
		int nshen = 0;
		for(IrpBugWithBLOBs bg:fbgs){
			if(IrpBugWithBLOBs.PRIORITY_YAN.equals(bg.getPriority())){
				nyan++;
			}else if(IrpBugWithBLOBs.PRIORITY_JIN.equals(bg.getPriority())){
				njin++;
			}else if(IrpBugWithBLOBs.PRIORITY_GAO.equals(bg.getPriority())){
				ngao++;
			}else if(IrpBugWithBLOBs.PRIORITY_ZHONG.equals(bg.getPriority())){
				nzhong++;
			}else if(IrpBugWithBLOBs.PRIORITY_DI.equals(bg.getPriority())){
				ndi++;
			}
		}
		for(IrpBugWithBLOBs bg:fbgs){
			if(IrpBugWithBLOBs.STATE_WEIWAN.equals(bg.getNeweststate())){
				nwei++;
			}else if(IrpBugWithBLOBs.STATE_SHENHE.equals(bg.getNeweststate())){
				nshen++;
			}else if(IrpBugWithBLOBs.STATE_WANCHENG.equals(bg.getNeweststate())){
				nwan++;
			}
		}
		yan.put("严重", nyan);
		jin.put("紧急", njin);
		gao.put("高", ngao);
		zhong.put("中", nzhong);
		di.put("低", ndi);
		wei.put("未修复", nwei);
		shen.put("待审核", nshen);
		wan.put("已解决", nwan);
		this.fenbu1.add(yan);
		this.fenbu1.add(jin);
		this.fenbu1.add(gao);
		this.fenbu1.add(zhong);
		this.fenbu1.add(di);
		this.fenbu2.add(wei);
		this.fenbu2.add(shen);
		this.fenbu2.add(wan);
	}
	private String zoushiDate;
	
	private String zoushiCount;
	
	public String getZoushiDate() {
		return zoushiDate;
	}
	public void setZoushiDate(String zoushiDate) {
		this.zoushiDate = zoushiDate;
	}
	public String getZoushiCount() {
		return zoushiCount;
	}
	public void setZoushiCount(String zoushiCount) {
		this.zoushiCount = zoushiCount;
	}
	/**
	 * 查询Bug总量走势
	 * @throws Exception
	 */
	private void countForZoushi()throws Exception{
		Date begin = DateUtils.getDateByStrToYMD(startDate);
		Date end = DateUtils.getDateByStrToYMD(endDate);
		List<java.util.Date> zoushiDateList=DateUtils.getDatesBetweenTwoDate(begin, end);
		List<String>zoushiD=new ArrayList<String>();
		for(Date d:zoushiDateList){
			zoushiD.add(DateUtils.getDateByFormat(d, DateUtils.YMD_FORMAT));
		}
		List<Integer> zoushiCountList=new ArrayList<Integer>();
		for(java.util.Date dat:zoushiDateList){
			IrpBugExample example=new IrpBugExample();
			Criteria criteria=example.createCriteria();
			//criteria.andCreatetimeBetween(startDate, DateUtils.dateTimeAdd(this.endDate, Calendar.DATE, 1));
			criteria.andCreatetimeLessThanOrEqualTo(DateUtils.dateTimeAdd(dat, Calendar.DATE, 1));
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
			criteria.andProjectidEqualTo(projectId);
			//criteria.andCreatetimeBetween(this.startDate, this.endDate);
			zoushiCountList.add(bugService.countByExample(example));
		}
		this.zoushiDate=JsonUtil.list2json(zoushiD);
		this.zoushiCount=JsonUtil.list2json(zoushiCountList);
	}
	private int dowhat;
	
	public int getDowhat() {
		return dowhat;
	}
	public void setDowhat(int dowhat) {
		this.dowhat = dowhat;
	}
	/**
	 * 总处理
	 */
	public String dispose() {
		try {
			if (this.dowhat == 1) {
				resolved();
			} else if (this.dowhat == 2) {
				weiwan();
			} else if (this.dowhat == 3) {
				forward();
			}
			simplebuginfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "page";
	}
	private String bugComment;
	/**
	 * 创始人
	 */
	private Long founderId;
	/**
	 * 处理人
	 */
	private Long operatorId;
	/**
	 * 优先级
	 */
	private String priority;
	/**
	 * bug标题
	 */
	private String bugTitle;
	/**
	 * 要求完成时间
	 */
	private java.util.Date requiredTime;
	/**
	 * 实际完成时间
	 */
	private java.util.Date finishTime;
	/**
	 * 是否处理过
	 */
	private Long isdispose;
	/**
	 * 状态 :已创建/未处理/待审核/已完成
	 */
	private String state;
	/**
	 * 转发的下一个处理人
	 */
	private Long disposeUserId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 私信接口
	 * 
	 * @return
	 */
	private IrpMessageContentService messageService;
	
	public String getBugComment() {
		return bugComment;
	}
	public void setBugComment(String bugComment) {
		this.bugComment = bugComment;
	}
	public Long getFounderId() {
		return founderId;
	}
	public void setFounderId(Long founderId) {
		this.founderId = founderId;
	}
	public Long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getBugTitle() {
		return bugTitle;
	}
	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}
	public java.util.Date getRequiredTime() {
		return requiredTime;
	}
	public void setRequiredTime(java.util.Date requiredTime) {
		this.requiredTime = requiredTime;
	}
	public java.util.Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(java.util.Date finishTime) {
		this.finishTime = finishTime;
	}
	public Long getIsdispose() {
		return isdispose;
	}
	public void setIsdispose(Long isdispose) {
		this.isdispose = isdispose;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getDisposeUserId() {
		return disposeUserId;
	}
	public void setDisposeUserId(Long disposeUserId) {
		this.disposeUserId = disposeUserId;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public IrpMessageContentService getMessageService() {
		return messageService;
	}
	public void setMessageService(IrpMessageContentService messageService) {
		this.messageService = messageService;
	}
	/**
	 * 处理 已解决
	 * 
	 * @return
	 */
	private void resolved() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			tokens = token;
			IrpUser irpuser = mobileAction.getlogin(token);
			Long loginUserId = irpuser.getUserid();
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_FIRST);
			List<IrpBugWithBLOBs> firstBug = bugService.selectByExample(example);
			/** 查询预处理数据begin **/
			example.clear();
			Criteria criteria3 = example.createCriteria();
			criteria3.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
			criteria3.andSerianumEqualTo(serianum);
			IrpBugWithBLOBs bugnew = bugService.selectByExample(example).get(0);
			/** 查询预处理数据end **/
			if (firstBug.get(0).getFounderid().equals(loginUserId)) {
				/** 更新预处理数据 begin **/
				bugnew.setState(IrpBugWithBLOBs.STATE_WANCHENG);
				bugnew.setCreatetime(new java.util.Date());
				bugnew.setFreshness(IrpBugWithBLOBs.ISDISPORE_OK);
				bugnew.setBugcomment(bugComment);
				bugnew.setIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
				example.clear();
				Criteria criteria2 = example.createCriteria();
				criteria2.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
				criteria2.andSerianumEqualTo(serianum);
				bugService.updateByExample(bugnew, example);
				updateNewestState(IrpBugWithBLOBs.STATE_WANCHENG);
				this.updateIsdispose(IrpBugWithBLOBs.ISDISPORE_OK);
				/** 更新预处理数据 end **/
			} else {
				bugnew.setState(IrpBugWithBLOBs.STATE_SHENHE);
				bugnew.setCreatetime(new java.util.Date());
				bugnew.setFreshness(IrpBugWithBLOBs.FRESHNESS_NEW);
				bugnew.setBugcomment(bugComment);
				bugnew.setIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
				example.clear();
				Criteria criteria2 = example.createCriteria();
				criteria2.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
				criteria2.andSerianumEqualTo(serianum);
				bugService.updateByExample(bugnew, example);
				this.founderId = bugnew.getFounderid();
				this.operatorId = bugnew.getOperatorid();
				this.projectId = bugnew.getProjectid();
				this.priority = bugnew.getPriority();
				this.bugTitle = bugnew.getTitle();
				this.requiredTime = bugnew.getRequiredtime();
				this.isdispose = IrpBugWithBLOBs.ISDISPORE_YES;
				state = IrpBugWithBLOBs.STATE_SHENHE;
				this.addWillBug();
				this.updateNewestState(state);
				this.updateIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
			}
			this.bugTitle = bugnew.getTitle();
			this.sendOtherMessage(bugnew.getOperatorid());
			setBugDispose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bug处理 未完成
	 */
	private void weiwan() throws Exception {
		IrpBugWithBLOBs willBug = this.findWillBug();
		willBug.setFreshness(IrpBugWithBLOBs.FRESHNESS_NEW);
		willBug.setBugcomment(bugComment);
		willBug.setCreatetime(new java.util.Date());
		willBug.setIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
		willBug.setState(IrpBugWithBLOBs.STATE_WEIWAN);
		IrpBugExample example = new IrpBugExample();
		Criteria criteria = example.createCriteria();
		criteria.andSerianumEqualTo(serianum);
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
		bugService.updateByExampleSelective(willBug, example);
		this.founderId = willBug.getFounderid();
		this.operatorId = willBug.getOperatorid();
		this.projectId = willBug.getProjectid();
		this.priority = willBug.getPriority();
		this.bugTitle = willBug.getTitle();
		state = IrpBugWithBLOBs.STATE_WEIWAN;
		this.isdispose = IrpBugWithBLOBs.ISDISPORE_YES;
		this.addWillBug();
		this.updateNewestState(state);
		this.sendOtherMessage(operatorId);
	}

	/**
	 * 转发
	 */
	private void forward() throws Exception {
		IrpBugWithBLOBs bg = findWillBug();
		IrpBugExample example = new IrpBugExample();
		Criteria criteria = example.createCriteria();
		criteria.andSerianumEqualTo(serianum);
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
		bg.setState(IrpBugWithBLOBs.STATE_FORWARD);
		bg.setFreshness(IrpBugWithBLOBs.FRESHNESS_NEW);
		bg.setCreatetime(new java.util.Date());
		bg.setOperatorid(disposeUserId);
		bg.setIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
		bg.setBugcomment(bugComment);
		bugService.updateByExampleSelective(bg, example);
		this.bugTitle = bg.getTitle();
		this.operatorId = this.disposeUserId;
		this.founderId = this.findCreateUserId();
		this.projectId = bg.getProjectid();
		this.requiredTime = bg.getRequiredtime();
		this.state = IrpBugWithBLOBs.STATE_WEIWAN;
		this.priority = bg.getPriority();
		this.isdispose = IrpBugWithBLOBs.ISDISPORE_YES;
		this.addWillBug();
		setBugDispose();
		updateNewestState(IrpBugWithBLOBs.STATE_WEIWAN);
		this.sendForwardMessage(disposeUserId);
		this.createsendmessage();
	}
	/**
	 * 获取创始人Id
	 * 
	 * @return
	 * @throws Exception
	 */
	private Long findCreateUserId() throws Exception {
		return bugService.selectByPrimaryKey(serianum).getFounderid();
	}
	/**
	 * 发送私信 转发
	 * 
	 * @return
	 */
	private void sendForwardMessage(Long _cruserid) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		StringBuffer sb = new StringBuffer();
		sb.append(LoginUtil.getLoginUser().getTruename());
		sb.append("将Bug");
		sb.append("<a <a href='javascript:void(0) ' onclick='toBugDoPage("+serianum+")'><strong>" + bugTitle + "</strong></a>");
		sb.append("转发给了您.");
		IrpMessageContent mc = new IrpMessageContent();
		mc.setContent(sb.toString());
		mc.setMessageid(TableIdUtil.getNextId("IRP_MESSAGE_CONTENT"));
		mc.setFromUid(_cruserid);
		mc.setCruserid(LoginUtil.getLoginUserId());
		mc.setCrtime(createTime);
		mc.setMessagetype(1);
		messageService.addMessageContent(mc);
	}
	/**
	 * 更新Bug最新状态
	 * 
	 * @return
	 */
	private void updateNewestState(String neweststate) {
		try {
			IrpBugWithBLOBs newbug = new IrpBugWithBLOBs();
			newbug.setNeweststate(neweststate);
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			bugService.updateByExampleSelective(newbug, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 更新Bug dispose
	 * 
	 * @param
	 */
	private void updateIsdispose(Long disposeState) {
		try {
			IrpBugWithBLOBs newbug = new IrpBugWithBLOBs();
			newbug.setIsdispose(disposeState);
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			bugService.updateByExampleSelective(newbug, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加预处理Bug
	 */
	private void addWillBug() {

		try {
			/** 最新记录begin **/
			IrpBugWithBLOBs bugnew = new IrpBugWithBLOBs();
			bugnew.setTitle(bugTitle);
			bugnew.setBugid(TableIdUtil.getNextId("irp_bug"));
			bugnew.setSerianum(serianum);
			bugnew.setFounderid(operatorId);
			bugnew.setOperatorid(founderId);
			bugnew.setProjectid(projectId);
			bugnew.setRequiredtime(requiredTime);
			bugnew.setState(state);
			bugnew.setPriority(this.priority);
			bugnew.setIsdispose(this.isdispose);
			bugnew.setFreshness(IrpBugWithBLOBs.FRESHNESS_WILL);
			bugService.addBug(bugnew);
			/** 最新记录end **/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 发送私信 其他
	 * 
	 * @return
	 */
	private void sendOtherMessage(Long _cruserid) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		StringBuffer sb = new StringBuffer();
		sb.append("您有新的Bug动态");
		sb.append("<a href='javascript:void(0) ' onclick='toBugDoPage("+serianum+")'><strong>" + bugTitle + "</strong></a>");
		IrpMessageContent mc = new IrpMessageContent();
		mc.setContent(sb.toString());
		mc.setMessageid(TableIdUtil.getNextId("IRP_MESSAGE_CONTENT"));
		mc.setFromUid(_cruserid);
		mc.setCruserid(LoginUtil.getLoginUserId());
		mc.setCrtime(createTime);
		mc.setMessagetype(1);
		messageService.addMessageContent(mc);
	}
	/**
	 * 设置Bug为已处理
	 * 
	 * @return
	 */
	private void setBugDispose() {
		try {
			IrpBugWithBLOBs diposeBug = new IrpBugWithBLOBs();
			diposeBug.setIsdispose(IrpBugWithBLOBs.ISDISPORE_YES);
			IrpBugExample example = new IrpBugExample();
			Criteria criteria = example.createCriteria();
			criteria.andSerianumEqualTo(serianum);
			bugService.updateByExampleSelective(diposeBug, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得预处理Bug
	 * 
	 * @return 预处理IrpBug
	 */
	private IrpBugWithBLOBs findWillBug() throws Exception {
		IrpBugExample example = new IrpBugExample();
		Criteria criteria = example.createCriteria();
		criteria.andFreshnessEqualTo(IrpBugWithBLOBs.FRESHNESS_WILL);
		criteria.andSerianumEqualTo(serianum);
		return bugService.selectByExample(example).get(0);
	}
	/**
	 * 圈子类型
	 */
	private long protype;
	private IrpProject irpProject;
	private IrpProjectService irpProjectService;
	private List<IrpProject> irpProjects;
	public long getProtype() {
		return protype;
	}
	public void setProtype(long protype) {
		this.protype = protype;
	}
	public IrpProject getIrpProject() {
		return irpProject;
	}
	public void setIrpProject(IrpProject irpProject) {
		this.irpProject = irpProject;
	}
	public IrpProjectService getIrpProjectService() {
		return irpProjectService;
	}
	public void setIrpProjectService(IrpProjectService irpProjectService) {
		this.irpProjectService = irpProjectService;
	}
	private String bugtitle;
	private String bugcontent;
	
		public String getBugtitle() {
		return bugtitle;
	}
	public void setBugtitle(String bugtitle) {
		this.bugtitle = bugtitle;
	}
	public String getBugcontent() {
		return bugcontent;
	}
	public void setBugcontent(String bugcontent) {
		this.bugcontent = bugcontent;
	}
	
	public List<IrpProject> getIrpProjects() {
		return irpProjects;
	}
	public void setIrpProjects(List<IrpProject> irpProjects) {
		this.irpProjects = irpProjects;
	}
	private List<IrpBugConfig> bugversions;
	
		public List<IrpBugConfig> getBugversions() {
		return bugversions;
	}
	public void setBugversions(List<IrpBugConfig> bugversions) {
		this.bugversions = bugversions;
	}
		/*//增加项目
		public void addProject(){ 
			if(protype==9l){
				addBugProject();
			}else{
				Calendar ca=Calendar.getInstance();
				irpProject.setCrtime(ca.getTime());

				irpProject.setProjecttype(protype);
				if(irpProject.getStarttime()==null || irpProject.getStarttime().getTime()<=ca.getTime().getTime()){
					irpProject.setStarttime(ca.getTime());
				} 
				if(irpProject.getEndtime()==null || irpProject.getEndtime().getTime()<=ca.getTime().getTime()){
					ca.add(Calendar.DATE,7);
					irpProject.setEndtime(ca.getTime());
				} 
				int nCount=irpProjectService.addProject(irpProject);
				ActionUtil.writer(String.valueOf(nCount));
			}
			
		}*/
		/**
		 * 新建BUG项目
		 */
		public void addBugProject(){
			IrpProject irpProject=new IrpProject();
			int resultstatus=0;
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
			Calendar ca=Calendar.getInstance();
			irpProject.setCrtime(ca.getTime());
			try {
				bugtitle=URLDecoder.decode(bugtitle,"UTF-8");
				bugcontent=URLDecoder.decode(bugcontent,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(irpProject.getStarttime()==null || irpProject.getStarttime().getTime()<=ca.getTime().getTime()){
				irpProject.setStarttime(ca.getTime());
			} 
			if(irpProject.getEndtime()==null || irpProject.getEndtime().getTime()<=ca.getTime().getTime()){
				ca.add(Calendar.DATE,7);
				irpProject.setEndtime(ca.getTime());
			} 
			//定义Projecttype为9，只属于BUG项目所用
			irpProject.setProjecttype(9L);
			irpProject.setPrname(bugtitle);
			irpProject.setPrdesc(bugcontent);
			irpProject.setCruserid(irpuser.getUserid());
			irpProject.setOffpersonid(irpuser.getUserid());//默认负责人为创建者
			int nCount=irpProjectService.addProjectPhone(irpProject);
			if(nCount==1){
	         	resultstatus=1000;
			}else{
				resultstatus=0;
			}
			JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
			ActionUtil.writer(j.toJSONString());
		}
		/**
		 * 跳转到添加bug页面
		 * 
		 * @return
		 */
		public String toAddBugPage() {
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				String token = request.getParameter("token");
				tokens=token;
				IrpUser irpuser = mobileAction.getlogin(token);
				if (projectId != null && !projectId.equals("")) {
					findAddProject();
					getProjectUser(projectId, true);
					this.findVersionByProid();
					this.findModulByProid();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		/**
		 * 查询添加bug的项目
		 * @throws Exception
		 */
		private void findAddProject() throws Exception {
			IrpProjectExample pe = new IrpProjectExample();
			com.tfs.irp.project.entity.IrpProjectExample.Criteria criteria2 = pe
					.createCriteria();
			criteria2.andProjectidEqualTo(projectId);
			criteria2.andProjecttypeEqualTo(9l);
			irpProjects = projectService.selectByExample(pe);
		}
		/**
		 * 查询项目下所有版本
		 */
		private void findVersionByProid() {
			try {
				if (projectId != null && !projectId.equals("")) {
					IrpBugConfigExample example = new IrpBugConfigExample();
					com.tfs.irp.bug_config.entity.IrpBugConfigExample.Criteria criteria = example
							.createCriteria();
					criteria.andProidEqualTo(this.projectId);
					criteria.andConfigtypeEqualTo(0l);
					example.setOrderByClause(" BUGCONFIGID");
					this.bugversions = bugconfigservice.selectByExample(example);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/**
		 * 描述
		 */
		private String describe;
		private Long versionid;
		private Long modulid;
		private String fileName;
		private String fileTrueName;
		private String jsonFile;
		private Long tabPid=0l;
		/**
		 * 是否发送私信
		 */
		private int issendmessage;
		private IrpAttachedTypeService irpAttachedTypeService;
		
		public String getDescribe() {
			return describe;
		}
		public void setDescribe(String describe) {
			this.describe = describe;
		}
		public Long getVersionid() {
			return versionid;
		}
		public void setVersionid(Long versionid) {
			this.versionid = versionid;
		}
		public Long getModulid() {
			return modulid;
		}
		public void setModulid(Long modulid) {
			this.modulid = modulid;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileTrueName() {
			return fileTrueName;
		}
		public void setFileTrueName(String fileTrueName) {
			this.fileTrueName = fileTrueName;
		}
		public String getJsonFile() {
			return jsonFile;
		}
		public void setJsonFile(String jsonFile) {
			this.jsonFile = jsonFile;
		}
		public Long getTabPid() {
			return tabPid;
		}
		public void setTabPid(Long tabPid) {
			this.tabPid = tabPid;
		}
		public int getIssendmessage() {
			return issendmessage;
		}
		public void setIssendmessage(int issendmessage) {
			this.issendmessage = issendmessage;
		}
		public IrpAttachedTypeService getIrpAttachedTypeService() {
			return irpAttachedTypeService;
		}
		public void setIrpAttachedTypeService(
				IrpAttachedTypeService irpAttachedTypeService) {
			this.irpAttachedTypeService = irpAttachedTypeService;
		}
		private Long bugid;
		
		public Long getBugid() {
			return bugid;
		}
		public void setBugid(Long bugid) {
			this.bugid = bugid;
		}
		private File file;
		
		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}
		/**
		 * 添加Bug
		 * 
		 * @return
		 */
		public void addBug() {
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				String token = request.getParameter("token");
				IrpUser irpuser = mobileAction.getlogin(token);
				// System.out.println("负责人Id是"+this.operatorId);
				/** 基础记录begin **/
				IrpBugWithBLOBs bug = new IrpBugWithBLOBs();
				Long key = TableIdUtil.getNextId(bug);
				this.serianum = key;
				bug.setBugid(key);
				bug.setSerianum(key);
				// bug.setBugid(33L);
				//bugTitle=new  String(bugTitle.getBytes("ISO-8859-1"), "utf-8");
				//describe=new  String(describe.getBytes("ISO-8859-1"), "utf-8");
				//bugTitle=bugTitle.trim();
				byte[] b1 = bugTitle.getBytes();
				String title = new String(b1);
				title = title.replaceAll("\\?", "");
				byte[] b2 = describe.getBytes();
				String bugdescribe = new String(b2);
				bugdescribe = bugdescribe.replaceAll("\\?", "");
				//bugTitle= java.net.URLDecoder.decode(bugTitle, "UTF-8"); 
				bug.setTitle(title);
				this.founderId = irpuser.getUserid();
				bug.setFounderid(founderId);
				bug.setOperatorid(operatorId);
				bug.setProjectid(projectId);
				bug.setPriority(this.priority);
				bug.setDescribe(bugdescribe);
				bug.setState(IrpBugWithBLOBs.STATE_CREAT);
				this.createTime = new java.util.Date();
				bug.setCreatetime(createTime);
				bug.setRequiredtime(requiredTime);
				bug.setFreshness(IrpBugWithBLOBs.FRESHNESS_FIRST);
				bug.setIsdispose(IrpBugWithBLOBs.ISDISPORE_NO);
				bug.setVersionid(versionid);
				bug.setModuleid(modulid);
				bug.setFilename(fileName);
				bug.setFiletruename(fileTrueName);
				bugService.addBug(bug);
				bugid=bug.getBugid();
				state = IrpBugWithBLOBs.STATE_WEIWAN;
				/** 基础记录end **/
				this.isdispose = IrpBugWithBLOBs.ISDISPORE_NO;
				addWillBug();
				this.updateNewestState(IrpBugWithBLOBs.STATE_WEIWAN);
				//if(issendmessage ==1){
					this.sendAddMessage(operatorId);
					this.createsendmessage();
				//}
				if (jsonFile != null && jsonFile != "") {
					// 如果存在附件，则添加附件
					JSONArray _Array = JSONArray.fromObject(jsonFile);
					for (int i = 0; i < _Array.size(); i++) {
						net.sf.json.JSONObject jsonObject = _Array
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
								typeid, key, sattdesc, sattlinkalt, seditversions,
								false, null, false);
					}
				}
				/*System.out.println(file);
				if (file != null && !file.equals("")) {
					// 如果存在附件，则添加附件
					JSONArray _Array = JSONArray.fromObject(file);
					for (int i = 0; i < _Array.size(); i++) {
						net.sf.json.JSONObject jsonObject = _Array
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
								typeid, key, sattdesc, sattlinkalt, seditversions,
								false, null, false);
					}
				}*/
				this.tabPid=projectId;
			} catch (Exception e) {
				e.printStackTrace();
			}
			ActionUtil.writer(""+bugid);
			//return SUCCESS;
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
						IrpAttached.BUGDOCTYPEID, newFilePath,
						Integer.parseInt(_sEditversions), TypeId, _attflag);
				_attachedids.add(_attachedid);
			}
			return _attachedids;

		}
		/**
		 * 发送私信 创建
		 * 
		 * @return
		 */
		private void sendAddMessage(Long _cruserid) {
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
			String rootPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath()
					+ "/";
			StringBuffer sb = new StringBuffer();
			sb.append(irpuser.getTruename());
			sb.append("在");
			sb.append(DateUtils.getDateByFormat(createTime, "yyyy-MM-dd HH:mm:ss"));
			sb.append("为您创建了新Bug");
			sb.append("<a href='javascript:void(0) ' onclick='toBugDoPage("+serianum+")'><strong>" + bugTitle + "</strong></a>");
			IrpMessageContent mc = new IrpMessageContent();
			mc.setContent(sb.toString());
			mc.setMessageid(TableIdUtil.getNextId("IRP_MESSAGE_CONTENT"));
			mc.setFromUid(_cruserid);
			mc.setCruserid(irpuser.getUserid());
			mc.setCrtime(createTime);
			mc.setMessagetype(1);
			messageService.addMessageContent(mc);
		}
		/**
		 * 推送通知 创建bug  app
		 * 
		 * @return
		 */
		public void createsendmessage() {
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
			String title = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
			byte[] b = bugTitle.getBytes();
			String bugtitle = new String(b);
			bugtitle = bugtitle.replaceAll("\\?", "");
			String body = irpuser.getTruename()+"在"+title+"为您创建了新Bug"+bugtitle;
			try {
				//测试
				IOSPush.sendIOSBroadcast(body, title, true);
			} catch (Exception e) {
				System.out.println("推送异常");
			}
		}
		/**
		 * 跳转到Bug管理页面
		 * @return
		 */
		public String toBugManage() {
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				String token = request.getParameter("token");
				IrpUser irpuser = mobileAction.getlogin(token);
				if(projectId!=null && !"".equals(projectId)){
					tabPid=projectId;
				}
				findMyProjects();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		/**
		 * 初始化我所在的项目
		 */
		private void findMyProjects() throws Exception {
			IrpProjectPerson pp = new IrpProjectPerson();
			IrpProjectPersonExample ppe = new IrpProjectPersonExample();
			com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria criteria = ppe
					.createCriteria();
			criteria.andPersonidEqualTo(LoginUtil.getLoginUserId());
			List<IrpProjectPerson> pplist = projectpersonservice
					.findPersonByExample(ppe);
			if (pplist.size() > 0) {
				List<Long> projectids = new ArrayList<Long>();
				for (IrpProjectPerson ele : pplist) {
					projectids.add(ele.getPrid());
				}
				IrpProjectExample pe = new IrpProjectExample();
				com.tfs.irp.project.entity.IrpProjectExample.Criteria criteria2 = pe
						.createCriteria();
				criteria2.andProjectidIn(projectids);
				criteria2.andProjecttypeEqualTo(9l);
				irpProjects = projectService.selectByExample(pe);
			}
		}
		/**
		 * 查询项目下所有Bug的数量
		 * 
		 * @return
		 */
		public void bugCountForProject() {
			Integer allbug = 0;//全部
			Integer norepair = 0;//未修复
			Integer check = 0;//待审核
			Integer compelet = 0;//已解决
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
			try {
				ApplicationContext ac = ApplicationContextHelper.getContext();
				BugService service = (BugService) ac.getBean("bugService");
				IrpBugExample example1 = new IrpBugExample();
				Criteria criteria1 = example1.createCriteria();
				criteria1.andProjectidEqualTo(projectId);
				criteria1.andFreshnessEqualTo(-1l);
				allbug = service.countByExample(example1);
				IrpBugExample example2 = new IrpBugExample();
				Criteria criteria2 = example2.createCriteria();
				criteria2.andProjectidEqualTo(projectId);
				criteria2.andFreshnessEqualTo(-1l);
				criteria2.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WEIWAN);
				norepair = service.countByExample(example2);
				IrpBugExample example3 = new IrpBugExample();
				Criteria criteria3 = example3.createCriteria();
				criteria3.andProjectidEqualTo(projectId);
				criteria3.andFreshnessEqualTo(-1l);
				criteria3.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_SHENHE);
				check = service.countByExample(example3);
				IrpBugExample example4 = new IrpBugExample();
				Criteria criteria4 = example4.createCriteria();
				criteria4.andProjectidEqualTo(projectId);
				criteria4.andFreshnessEqualTo(-1l);
				criteria4.andNeweststateEqualTo(IrpBugWithBLOBs.STATE_WANCHENG);
				compelet = service.countByExample(example4);
			} catch (BeansException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JSONObject j = JSON.parseObject("{\"allbug\":\"" + allbug +"\",\"norepair\":\"" + norepair +"\",\"check\":\"" + check +"\",\"compelet\":\"" + compelet +"\"}");
			ActionUtil.writer(j.toJSONString());
		}
		/**
		 * 跳转到编辑项目页面
		 * @return
		 */
		public String toEditorProject(){
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			tokens=token;
			IrpUser irpuser = mobileAction.getlogin(token);
			return SUCCESS;
		}
		private Long cruserid;
		
		public Long getCruserid() {
			return cruserid;
		}
		public void setCruserid(Long cruserid) {
			this.cruserid = cruserid;
		}
		/**
		 * 查找项目创始人id
		 * @return
		 */
		/*public static Integer getprocruserid(Long projectId){
			ApplicationContext ac = ApplicationContextHelper.getContext();
			IrpProjectService projectService=(IrpProjectService) ac.getBean("projectService");
			IrpProject project=projectService.findProjectById(projectId);
			Integer cruserid=project.getCruserid().intValue();
			System.out.println(cruserid);
			return cruserid;
		}*/
		private IrpTagService irpTagService;
		
		public IrpTagService getIrpTagService() {
			return irpTagService;
		}
		public void setIrpTagService(IrpTagService irpTagService) {
			this.irpTagService = irpTagService;
		}
		/**
		 * 获得热门检索词 手机版
		 * mobile
		 * @return
		 */
		public void findHotWordMobile(){
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
			 List<IrpTag> list = this.irpTagService.findHotIndexWord(10);
			 if (list.size()>0 && list!=null) {
				 List wordlist = new ArrayList();
				 for (int i = 0; i < list.size(); i++) {
					 	IrpTag irptag = list.get(i);
					 	wordlist.add("{\"hotwords\":\""+irptag.getTagname()+"\"}");
					 	//wordlist.add("\"tagnamenum\":\""+irptag.getNcount()+"\"}");
				 }	
				 ActionUtil.writer(wordlist.toString());
				 //ActionUtil.writer(JSON.toJSONString(wordlist));
			 }
		}
		
}
