package com.tfs.irp.project.web;
 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.documentmap.service.IrpDocumentMapService;
import com.tfs.irp.informtype.service.IrpInformTypeService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.project.entity.IrpProjectExample;
import com.tfs.irp.project.service.IrpProjectService;
import com.tfs.irp.projectperson.entity.IrpProjectPerson;
import com.tfs.irp.projectperson.entity.IrpProjectPersonExample;
import com.tfs.irp.projectperson.service.IrpProjectPersonService; 
import com.tfs.irp.projectsharetask.entity.IrpProjectShareTask;
import com.tfs.irp.projectsharetask.entity.IrpProjectShareTaskExample;
import com.tfs.irp.projectsharetask.service.IrpProjectShareTaskService;
import com.tfs.irp.projecttaskperson.service.IrpProjectTaskPersonService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.term.entity.IrpTerm;
import com.tfs.irp.term.service.IrpTermService;
import com.tfs.irp.termeditlog.service.IrpTermEditLogService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample; 
import com.tfs.irp.user.entity.IrpUserExample.Criteria;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userlove.entity.IrpUserLove;
import com.tfs.irp.userlove.service.IrpUserLoveService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.ThumbnailPic;

public class ProjectAction extends ActionSupport {
	private IrpProjectService irpProjectService;
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
	private IrpUserLoveService irpUserLoveService;
	private IrpDocumentMapService irpDocumentMapService;
	private IrpProjectShareTaskService irpProjectShareTaskService;
	private IrpProjectTaskPersonService irpProjectTaskPersonService;
	
	private List projectAttacheds;//项目附件集合
	
	private IrpChannelService irpChannelService;
	private String allChannelIds;
	private String chnldesc;
	private String json;
	private List irpProjectShareTasks;//任务集合
	private Long maxAddUserNum=0L;
	private Long projectStatus;
	private boolean isPerson;
	private List irpBugs;//bug集合
	private String prname;
	private List<IrpProjectShareTask> irpProjectAndShareTasks;//任务集合<
	
	private IrpAttachedService irpAttachedService;// 附件表service
    private IrpDocumentService irpDocumentService;// 文档service
    private IrpAttachedTypeService irpAttachedTypeService;
    
    
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}

	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}

	public List getIrpBugs() {
		return irpBugs;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public List<IrpProjectShareTask> getIrpProjectAndShareTasks() {
		return irpProjectAndShareTasks;
	}

	public void setIrpProjectAndShareTasks(
			List<IrpProjectShareTask> irpProjectAndShareTasks) {
		this.irpProjectAndShareTasks = irpProjectAndShareTasks;
	}

	public void setIrpBugs(List irpBugs) {
		this.irpBugs = irpBugs;
	}

	public Long getMaxAddUserNum() {
		return maxAddUserNum;
	}

	public void setMaxAddUserNum(Long maxAddUserNum) {
		this.maxAddUserNum = maxAddUserNum;
	}
	public String userNames;
	public boolean isPerson() {
		return isPerson;
	}

	public List getProjectAttacheds() {
		return projectAttacheds;
	}

	public void setProjectAttacheds(List projectAttacheds) {
		this.projectAttacheds = projectAttacheds;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public void setPerson(boolean isPerson) {
		this.isPerson = isPerson;
	} 
	public Long getProjectStatus() {
		return projectStatus;
	}

	public List getIrpProjectShareTasks() {
		return irpProjectShareTasks;
	}

	public void setIrpProjectShareTasks(List irpProjectShareTasks) {
		this.irpProjectShareTasks = irpProjectShareTasks;
	}

	public IrpProjectTaskPersonService getIrpProjectTaskPersonService() {
		return irpProjectTaskPersonService;
	}

	public void setIrpProjectTaskPersonService(
			IrpProjectTaskPersonService irpProjectTaskPersonService) {
		this.irpProjectTaskPersonService = irpProjectTaskPersonService;
	}

	public IrpProjectShareTaskService getIrpProjectShareTaskService() {
		return irpProjectShareTaskService;
	}

	public void setIrpProjectShareTaskService(
			IrpProjectShareTaskService irpProjectShareTaskService) {
		this.irpProjectShareTaskService = irpProjectShareTaskService;
	}

	public void setProjectStatus(Long projectStatus) {
		this.projectStatus = projectStatus;
	}

	public IrpDocumentMapService getIrpDocumentMapService() {
		return irpDocumentMapService;
	}

	public void setIrpDocumentMapService(
			IrpDocumentMapService irpDocumentMapService) {
		this.irpDocumentMapService = irpDocumentMapService;
	}
	private List< IrpChnlDocLink> chnlDocLinks;
	public IrpUserLoveService getIrpUserLoveService() {
		return irpUserLoveService;
	}

	public List<IrpChnlDocLink> getChnlDocLinks() {
		return chnlDocLinks;
	}

	public void setChnlDocLinks(List<IrpChnlDocLink> chnlDocLinks) {
		this.chnlDocLinks = chnlDocLinks;
	}

	public void setIrpUserLoveService(IrpUserLoveService irpUserLoveService) {
		this.irpUserLoveService = irpUserLoveService;
	}

	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}

	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}
	IrpProjectPersonService irpProjectPersonService;
	
	private String taskorproject=null;
	
	private IrpProject irpProject;
	
	private IrpProjectPerson irpProjectPerson=null;
	
	private Long projectId;
	
	private Long isClosed;
	
	private Long loginUserid;
	
	private String friendlyShow;
	
	private int pageNum=1; 
	
	private int pageSize=10; 
	
	private String pageHTML;
	
	private Integer maxAmount;
	
	public IrpInformTypeService getIrpInformTypeService() {
		return irpInformTypeService;
	}

	public void setIrpInformTypeService(IrpInformTypeService irpInformTypeService) {
		this.irpInformTypeService = irpInformTypeService;
	}
	private IrpInformTypeService irpInformTypeService;
	
	 
	////////////////////////////// 用来查询用户列表
	private int init=0;
	private String userIds;
	private List<IrpUser> irpUsers;
	private String searchWord;
	private String searchType;
	private String orderField;
	private IrpUserService irpUserService;
	private String orderBy;  
	///////////////////////////用来查询用户列表
	
	public String getFriendlyShow() {
		return friendlyShow;
	}

	public IrpProjectPerson getIrpProjectPerson() {
		return irpProjectPerson;
	}

	public void setIrpProjectPerson(IrpProjectPerson irpProjectPerson) {
		this.irpProjectPerson = irpProjectPerson;
	}

	public void setFriendlyShow(String friendlyShow) {
		this.friendlyShow = friendlyShow;
	}
	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	private List<IrpProject> irpProjects; 
	public IrpProjectPersonService getIrpProjectPersonService() {
		return irpProjectPersonService;
	}

	public String getTaskorproject() {
		return taskorproject;
	}

	public void setTaskorproject(String taskorproject) {
		this.taskorproject = taskorproject;
	}

	public Long getIsClosed() {
		return isClosed;
	}
	public int getInit() {
		return init;
	}

	public void setInit(int init) {
		this.init = init;
	}

	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}

	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
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

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
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

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public void setIsClosed(Long isClosed) {
		this.isClosed = isClosed;
	}

	public Long getLoginUserid() {
		return loginUserid;
	}

	public void setLoginUserid(Long loginUserid) {
		this.loginUserid = loginUserid;
	}

	public void setIrpProjectPersonService(
			IrpProjectPersonService irpProjectPersonService) {
		this.irpProjectPersonService = irpProjectPersonService;
	}
	
	
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

	public List<IrpProject> getIrpProjects() {
		return irpProjects;
	}

	public void setIrpProjects(List<IrpProject> irpProjects) {
		this.irpProjects = irpProjects;
	} 
	/**
	 * 圈子类型
	 */
	private Long  protype;
	
	private long ispublish;
		
	public long getIspublish() {
		return ispublish;
	}

	public void setIspublish(long ispublish) {
		this.ispublish = ispublish;
	}

	
	public Long getProtype() {
		return protype;
	}

	public void setProtype(Long protype) {
		this.protype = protype;
	}

	public String getPrname() {
		return prname;
	}

	public void setPrname(String prname) {
		this.prname = prname;
	}

	//增加项目
	public void addProject(){ 
		if(protype!=null&&protype==9l){
			addBugProject();
		}else{
			Calendar ca=Calendar.getInstance();
			irpProject.setCrtime(ca.getTime());
			if(protype==null){
				protype=0L;
			}
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
		
	}
	/**
	 * 新建BUG项目
	 */
	private void addBugProject(){
		Calendar ca=Calendar.getInstance();
		irpProject.setCrtime(ca.getTime());

		if(irpProject.getStarttime()==null || irpProject.getStarttime().getTime()<=ca.getTime().getTime()){
			irpProject.setStarttime(ca.getTime());
		} 
		if(irpProject.getEndtime()==null || irpProject.getEndtime().getTime()<=ca.getTime().getTime()){
			ca.add(Calendar.DATE,7);
			irpProject.setEndtime(ca.getTime());
		} 
		//定义Projecttype为9，只属于BUG项目所用
		irpProject.setProjecttype(9L);
		int nCount=irpProjectService.addProject(irpProject);
		ActionUtil.writer(String.valueOf(nCount));
	}
	//删除项目
	public void deleteProject(){
		int nCount=irpProjectService.deleteProject(projectId);
		ActionUtil.writer(String.valueOf(nCount));
	}
	//修改项目 
	public void updateProject(){ 
		int nCount=irpProjectService.updateProject(irpProject); 
		ActionUtil.writer(String.valueOf(nCount));
	}
	//修改项目 负责人
		public void updateProjectOffPerson(){
			int nCount=irpProjectService.updateProject(irpProject);
			IrpProjectPersonExample example=new IrpProjectPersonExample();
			example.createCriteria().andPersonidEqualTo(irpProject.getOffpersonid());
			List<IrpProjectPerson> persons=irpProjectPersonService.findPersonByExample(example);//查询项目成员肯定集合为0或者1
			if(persons!=null && persons.size()>0){ 
				//判断他是关注这还是成员，如果是关注者，则将他的状态改为成员状态
				if(persons.get(0).getIsstate()==IrpProjectPerson.NOT_STATE){
					persons.get(0).setIsstate(IrpProjectPerson.IS_STATE);
					irpProjectPersonService.updateProjectPerson(persons.get(0));
				}
			}else{//如果没有这个人，则添加他为成员 
				nCount=irpProjectPersonService.addProjectPerson(irpProject.getOffpersonid(), IrpProjectPerson.IS_STATE, projectId, IrpProjectPerson.IS_STATUS,null,null);
			}
			ActionUtil.writer(String.valueOf(nCount));
		}
	//查询自己关注或者已经是成员的项目显示在左边列表中
	public String findProjectLeft(){ 
		HashMap<String,Object> map=new HashMap<String, Object>();
		irpProjects=null;
		IrpUser irpUser=LoginUtil.getLoginUser(); 
		List<Long> projectIdList=irpProjectPersonService.findProjectIdByPersonId(irpUser.getUserid(),null);
		if(projectIdList!=null &&projectIdList.size()>0){ 
			map.put("projectIdList", projectIdList);
			map.put("protype",protype); 
			irpProjects=irpProjectService.findProjectByMap(map);
		} 
		//查询他的任务，查询他负责或者他参与的并且是创建时间最新倒叙的前五条
		HashMap hashMap=new HashMap();
		hashMap.put("orderByClause", " updatetime desc ");
		hashMap.put("parentid",0L);
		List<Long> shareTaskIds=irpProjectTaskPersonService.myJoinProjectTask();
 		if(shareTaskIds!=null && shareTaskIds.size()>0){
			hashMap.put("shareTaskIds", shareTaskIds); 
			hashMap.put("isState",IrpProjectShareTask.IS_TASK_STATE);
			hashMap.put("themetype", 0l);
			//hashMap.put("isJoin","isJoin");//参与者
			hashMap.put("isClosed", IrpProjectShareTask.NOT_CLOSED);
			int pageSizeReplase=SysConfigUtil.getSysConfigNumValue("USER_JOIN_PROHECT_AMOUNT");
			int aDataCount=irpProjectShareTaskService.countShareTaskByMap(map);
			PageUtil pageUtil = new PageUtil(1,pageSizeReplase, aDataCount);
			irpProjectShareTasks=irpProjectShareTaskService.findShareTaskByMap(hashMap,pageUtil);
		}
 		maxAmount=SysConfigUtil.getSysConfigNumValue("SHARE_AMOUNT");
		return SUCCESS;
	} 
	
	


	public Integer countTheme(long project){ 
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectShareTaskService Service = (IrpProjectShareTaskService) ac.getBean("irpProjectShareTaskService"); 
		
		IrpProjectShareTaskExample  example=new IrpProjectShareTaskExample();
		example.createCriteria().andProjectidEqualTo(project).andParentidEqualTo(0L);
		
		Integer nCount=Service.countShareTaskByExample(example);  
		return nCount;
	}
	
	//查询自己已经是成员的项目显示在左边列表中
	public String findthemeLeft(){ 
		projectId=projectId;
		HashMap<String,Object> map=new HashMap<String, Object>();
		irpProjects=null;
		IrpUser irpUser=LoginUtil.getLoginUser(); 
		List<Long> projectIdList=irpProjectPersonService.findProjectIdByPersonId(irpUser.getUserid(),null);
		if(projectIdList!=null &&projectIdList.size()>0){ 
			map.put("projectIdList", projectIdList);
			map.put("protype",protype); 
			irpProjects=irpProjectService.findProjectByMap(map);
		} 
		
 		maxAmount=SysConfigUtil.getSysConfigNumValue("SHARE_AMOUNT");
		return SUCCESS;
	} 
	// 我负责的项目
	public String myOffProject(){
		this.pageHTML = null;
		irpProjects = null;
		HashMap<String,Object> map=new HashMap<String, Object>(); 
		IrpUser irpUser=LoginUtil.getLoginUser();
		loginUserid=irpUser.getUserid();
		map.put("offpersonid", irpUser.getUserid()); 
		map.put("isclosed",isClosed);
		map.put("protype",protype); 
		int aDataCount=irpProjectService.countProjectList(map);   
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
		
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		irpProjects=irpProjectService.findProjectByMap(pageUtil,map); 
		return SUCCESS;
	}

	//我参与的项目
	public String meJoinProject(){
		this.pageHTML = null;
		irpProjects = null;
		HashMap<String,Object> map=new HashMap<String, Object>();
		IrpUser irpUser=LoginUtil.getLoginUser(); 
		loginUserid=irpUser.getUserid();
		
		List<Long> projectIdList=irpProjectPersonService.findProjectIdByPersonId(irpUser.getUserid(),IrpProjectPerson.IS_STATE);
		 
		if(projectIdList!=null &&projectIdList.size()>0){ 
			map.put("projectIdList", projectIdList); 
			map.put("isclosed",isClosed);
			map.put("protype",protype); 
			int aDataCount=irpProjectService.countProjectList(map);   
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
			
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
			irpProjects=irpProjectService.findProjectByMap(pageUtil,map); 
		} 
		return SUCCESS;
	}
	//我关注的项目
	public String myAttactionProject(){
		this.pageHTML = null;
		irpProjects = null;
		HashMap<String,Object> map=new HashMap<String, Object>();
		IrpUser irpUser=LoginUtil.getLoginUser();
		loginUserid=irpUser.getUserid();
		
		List<Long> projectIdList=irpProjectPersonService.findProjectIdByPersonId(irpUser.getUserid(),IrpProjectPerson.NOT_STATE);
		if(projectIdList!=null &&projectIdList.size()>0){ 
			map.put("projectIdList", projectIdList); 
			map.put("isclosed",isClosed);
			map.put("protype",protype); 
			int aDataCount=irpProjectService.countProjectList(map);   
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
			
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
			irpProjects=irpProjectService.findProjectByMap(pageUtil,map);
		}else{
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, 0);  
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		}
		return SUCCESS;
	}
	//所有项目
	public String allProjectList(){
		this.pageHTML = null;
		irpProjects = null;
		HashMap<String,Object> map=new HashMap<String, Object>();
		IrpUser irpUser=LoginUtil.getLoginUser();   
		loginUserid=irpUser.getUserid();
		map.put("isclosed",isClosed); 
		map.put("protype",protype); 
		int aDataCount=irpProjectService.countProjectList(map);   
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		irpProjects=irpProjectService.findProjectByMap(pageUtil,map);
	    if(irpProjects!=null && irpProjects.size()>0){
	    	for (int i = 0; i < irpProjects.size(); i++) {
	    		IrpProject project = irpProjects.get(i);
	    		if(project.getOffpersonid()==loginUserid && project.getIspublish()==IrpProject.NOT_PUBLISH){
	    			irpProjects.remove(project);
	    		}
			}
	    }  
		return SUCCESS; 
	}
	
	/**
	 * 最热圈子
	 * @return
	 */
	public String hotProject(){
		this.pageHTML = null;
		irpProjects = null;
		HashMap<String,Object> map=new HashMap<String, Object>();
		IrpUser irpUser=LoginUtil.getLoginUser();   
		loginUserid=irpUser.getUserid();
		map.put("isclosed",1L); 
		map.put("protype",0L); 
		List<IrpProject> irpProjectList=irpProjectService.findProjectByMapAndTime(map);
	    List<Long> list=irpProjectPersonService.findPersonBySql(irpProjectList);
	    map.put("projectIdList",list);
	    irpProjects=irpProjectService.findProjectByMapAndTime(map);
		return SUCCESS; 
	}
	
	//查看项目修改
	public String projectInfoToUpdate(){
			loginUserid=LoginUtil.getLoginUser().getUserid();
			if(projectId==null)return ERROR;
			irpProject=irpProjectService.findProjectById(projectId);  
		    if(irpProject==null)return ERROR;
		    //判断他是不是负责人
		    if(Long.valueOf(loginUserid)!=Long.valueOf(irpProject.getOffpersonid())){
		    	friendlyShow=IrpProject.PROJECT_IS_UPDATE_RIGHT;
		    	return ERROR;
		    }
		return SUCCESS;
	}
	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public String getAllChannelIds() {
		return allChannelIds;
	}

	public String getChnldesc() {
		return chnldesc;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	public void setAllChannelIds(String allChannelIds) {
		this.allChannelIds = allChannelIds;
	}

	public void setChnldesc(String chnldesc) {
		this.chnldesc = chnldesc;
	}

	//查看一个项目
	public String projectInfo(){
		//System.out.println(this.projectId);
		loginUserid=LoginUtil.getLoginUser().getUserid();
			if(projectId==null)return ERROR;
			irpProject=irpProjectService.findProjectById(projectId);  
		    if(irpProject==null)return ERROR;
		    //登陆者是不是普通用户(从项目成员表中查询如果查询到他，就是成员或者关注者，进入项目详细信息)
		    IrpProjectPersonExample example=new IrpProjectPersonExample();
		    	example.createCriteria().andPersonidEqualTo(loginUserid)
		    							.andPridEqualTo(irpProject.getProjectid());
		    List<IrpProjectPerson> irpPersonsList=irpProjectPersonService.findPersonByExample(example); 
		    if(irpPersonsList==null || irpPersonsList.size()==0){//普通用户  
		    	if(irpProject.getIspublish().toString().equals(IrpProject.NOT_PUBLISH.toString())){
			    	friendlyShow="隐私圈子"; //隐私项目，跳转到提示他是隐私项目页面 
			    	return INPUT;
			    }
		    	return SUCCESS;//如果项目公开
		    }
		 irpProjectPerson=irpPersonsList.get(0);
		//查询项目管理下的所有分类   
    	long nChannelId = irpProject.getProstatus();
    	 if(nChannelId!=0L){
    		 //获得当前文档栏目的全路径
	    	 List<Long> channelidList=new ArrayList<Long>();
	    	 channelidList = irpChannelService.mapParentLine(nChannelId, channelidList,IrpChannel.IS_DOCSTATIUS);
	    	 if(channelidList!=null && channelidList.size()>0){
	    		 for (Long long1 : channelidList) {
	    			if(this.allChannelIds==null || this.allChannelIds.length()==0){
	    				this.allChannelIds = long1.toString();
	    			}else{
	    				this.allChannelIds += ","+long1.toString();
	    			}
				}
	    	 }
	    	 //获得当前文档栏目信息
	    	 this.chnldesc = irpChannelService.findChannelNameByChannelid(nChannelId);
    	 }
		maxAmount=SysConfigUtil.getSysConfigNumValue("SHARE_AMOUNT");
	   return SUCCESS;
	}
	public String toAddProject(){
		loginUserid=LoginUtil.getLoginUserId();
		if(loginUserid==null)return ERROR;
		irpProject=null;
		return SUCCESS;
	}
	/**‘
	 *根据项目id获取项目名称显示在页面
	 */
	public static String findProjectNameByProjectId(Long _projectID){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectService Service = (IrpProjectService) ac.getBean("irpProjectService"); 
	
		IrpProject project=Service.findProjectById(_projectID);
		if(project!=null ){
			return project.getPrname();
		}
		return null;
	}
	/**‘
	 *根据项目id获取项目名称显示在页面
	 */
	public static IrpProject findProjectByProjectId(Long _projectID){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectService Service = (IrpProjectService) ac.getBean("irpProjectService"); 
	
		IrpProject project=Service.findProjectById(_projectID);
		return project;
	}
	/**
	 * 根据项目id获取项目状态
	 * @param _projectID
	 * @return
	 */
	public static String findIsClosedByProjectId(Long _projectID){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectService Service = (IrpProjectService) ac.getBean("irpProjectService"); 
	
		IrpProject project=Service.findProjectById(_projectID);
		if(project!=null ){
			return project.getIsclosed()+"";
		}
		return null;
	}
	/**
	 * 前台显示选择的用户
	 * @return
	 */
	//查询系统中的人员
	public String selectAllUser(){
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		} 
		IrpUserExample example=new IrpUserExample();  
		Criteria criteria = example.createCriteria();
		if("all".equals(searchType)){
			example.or(criteria.andStatusEqualTo(IrpUser.USER_STATUS_REG).andUsernameLike("%"+searchWord+"%"));
			example.or(criteria.andStatusEqualTo(IrpUser.USER_STATUS_REG).andTruenameLike("%"+searchWord+"%"));
		} else if("username".equals(searchType)){
			criteria.andStatusEqualTo(IrpUser.USER_STATUS_REG).andUsernameLike("%"+searchWord+"%");
		} else if("truename".equals(searchType)){
			criteria.andStatusEqualTo(IrpUser.USER_STATUS_REG).andTruenameLike("%"+searchWord+"%");
		} else{
			criteria.andStatusEqualTo(IrpUser.USER_STATUS_REG);
		} 
		example.setOrderByClause(sOrderByClause);
		 List<Long> attUserIds=null;
		if(isPerson!=true){ 
		 attUserIds=irpProjectPersonService.findPersonIdByProjectId(projectId, IrpProjectPerson.IS_STATE);
		} 
		 if(attUserIds!=null && attUserIds.size()>0){
			 criteria.andUseridNotIn(attUserIds);  
		 }
		int nCount=irpUserService.countUserByExample(example);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,nCount);
		irpUsers=irpUserService.findUserByExample(example,pageUtil); 
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		//查询项目的成员或者关注者
		List<Long> personidsList=null;
		if(userIds==null || userIds.length()<1){
			if(isPerson==true){//成员
				personidsList=irpProjectPersonService.findPersonIdByProjectId(projectId, IrpProjectPerson.IS_STATE);
			}else{ 
				personidsList=irpProjectPersonService.findPersonIdByProjectId(projectId, IrpProjectPerson.NOT_STATE,irpProjectPerson.IS_STATUS);
			}
			userIds="";
			if(personidsList!=null && personidsList.size()>0){
				for (Long pid : personidsList) {
					userIds+=pid+",";
				}
			} 
		}
		//personidsList=null;
		return SUCCESS;
	}
	public String selectAllUserApp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens=token;
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		} 
		IrpUserExample example=new IrpUserExample();  
		Criteria criteria = example.createCriteria();
		if("all".equals(searchType)){
			example.or(criteria.andStatusEqualTo(IrpUser.USER_STATUS_REG).andUsernameLike("%"+searchWord+"%"));
			example.or(criteria.andStatusEqualTo(IrpUser.USER_STATUS_REG).andTruenameLike("%"+searchWord+"%"));
		} else if("username".equals(searchType)){
			criteria.andStatusEqualTo(IrpUser.USER_STATUS_REG).andUsernameLike("%"+searchWord+"%");
		} else if("truename".equals(searchType)){
			criteria.andStatusEqualTo(IrpUser.USER_STATUS_REG).andTruenameLike("%"+searchWord+"%");
		} else{
			criteria.andStatusEqualTo(IrpUser.USER_STATUS_REG);
		} 
		example.setOrderByClause(sOrderByClause);
		 List<Long> attUserIds=null;
		if(isPerson!=true){ 
		 attUserIds=irpProjectPersonService.findPersonIdByProjectId(projectId, IrpProjectPerson.IS_STATE);
		} 
		 if(attUserIds!=null && attUserIds.size()>0){
			 criteria.andUseridNotIn(attUserIds);  
		 }
		int nCount=irpUserService.countUserByExample(example);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,nCount);
		irpUsers=irpUserService.findUserByExample(example,pageUtil); 
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		//查询项目的成员或者关注者
		List<Long> personidsList=null;
		if(userIds==null || userIds.length()<1){
			if(isPerson==true){//成员
				personidsList=irpProjectPersonService.findPersonIdByProjectId(projectId, IrpProjectPerson.IS_STATE);
			}else{ 
				personidsList=irpProjectPersonService.findPersonIdByProjectId(projectId, IrpProjectPerson.NOT_STATE,irpProjectPerson.IS_STATUS);
			}
			userIds="";
			if(personidsList!=null && personidsList.size()>0){
				for (Long pid : personidsList) {
					userIds+=pid+",";
				}
			} 
		}
		//personidsList=null;
		return SUCCESS;
	}
	//查询项目中的成员
	public String selectUser() { 
		/**
		 * 查询项目中的成员id集合
		 */  
		loginUserid=LoginUtil.getLoginUserId();
		List<Long> perpsonIds=irpProjectPersonService.findPersonIdByProjectId(projectId, IrpProjectPerson.IS_STATE);
		if(perpsonIds!=null && perpsonIds.size()>0){ 
			//处理排序 
			String sOrderByClause = "";
			if(this.orderField==null || this.orderField.equals("")){
				sOrderByClause = "userid desc";
			}else{
				sOrderByClause = this.orderField+" "+this.orderBy;
			} 
			IrpUserExample example=new IrpUserExample();   
			if("all".equals(searchType)){
				example.or(example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG).andUseridIn(perpsonIds).andUsernameLike("%"+searchWord+"%"));
				example.or(example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG).andUseridIn(perpsonIds).andTruenameLike("%"+searchWord+"%"));
			} else if("username".equals(IrpUser.USER_STATUS_REG)){
				example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG).andUseridIn(perpsonIds).andUsernameLike("%"+searchWord+"%");
			} else if("truename".equals(searchType)){
				example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG).andUseridIn(perpsonIds).andTruenameLike("%"+searchWord+"%");
			} else{
				example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG).andUseridIn(perpsonIds);
			} 
			example.setOrderByClause(sOrderByClause); 
			int nCount=irpUserService.countUserByExample(example);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,nCount);
			irpUsers=irpUserService.findUserByExample(example,pageUtil); 
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");  
		}  
		return SUCCESS;
	} 
	private String tokens;
	private List<Long> taskids;

    private List<IrpChnlDocLink> chnlDocLinks51;
	

	public List<IrpChnlDocLink> getChnlDocLinks51() {
		return chnlDocLinks51;
	}

	public void setChnlDocLinks51(List<IrpChnlDocLink> chnlDocLinks51) {
		this.chnlDocLinks51 = chnlDocLinks51;
	}

	public List<Long> getTaskids() {
		return taskids;
	}

	public void setTaskids(List<Long> taskids) {
		this.taskids = taskids;
	}

	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	public String selectUserPhone() { 
		/**
		 * 查询项目中的成员id集合
		 */ 
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		tokens=token;
		IrpUser irpuser = mobileAction.getlogin(token);
		//System.out.println(token+"sdv");
		loginUserid=irpuser.getUserid();
		List<Long> perpsonIds=irpProjectPersonService.findPersonIdByProjectId(projectId, IrpProjectPerson.IS_STATE);
		if(perpsonIds!=null && perpsonIds.size()>0){ 
			//处理排序 
			String sOrderByClause = "";
			if(this.orderField==null || this.orderField.equals("")){
				sOrderByClause = "userid desc";
			}else{
				sOrderByClause = this.orderField+" "+this.orderBy;
			} 
			IrpUserExample example=new IrpUserExample();   
			if("all".equals(searchType)){
				example.or(example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG).andUseridIn(perpsonIds).andUsernameLike("%"+searchWord+"%"));
				example.or(example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG).andUseridIn(perpsonIds).andTruenameLike("%"+searchWord+"%"));
			} else if("username".equals(IrpUser.USER_STATUS_REG)){
				example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG).andUseridIn(perpsonIds).andUsernameLike("%"+searchWord+"%");
			} else if("truename".equals(searchType)){
				example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG).andUseridIn(perpsonIds).andTruenameLike("%"+searchWord+"%");
			} else{
				example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG).andUseridIn(perpsonIds);
			} 
			example.setOrderByClause(sOrderByClause); 
			int nCount=irpUserService.countUserByExample(example);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,nCount);
			irpUsers=irpUserService.findUserByExample(example,pageUtil); 
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");  
		}  
		return SUCCESS;
	} 
	public void startOrCloseProject(){  
		IrpProject updateProject = irpProjectService.findProjectById(irpProject.getProjectid());
		updateProject.setIsclosed(irpProject.getIsclosed());
		int nCount=irpProjectService.updateProject(updateProject);  
		ActionUtil.writer(String.valueOf(nCount));
	}
	/**
	 * 项目的相关知识
	 * @return
	 */
	public String collectionDocument(){
		chnlDocLinks=null;  
		if(projectId!=null && projectId!=0){ 
			List<Long> docIds=irpUserLoveService.xDocids(null, IrpUserLove.PROJECT_XIANG_GUAN_DOCUMENT, this.projectId);
			if(docIds!=null &&docIds.size()>0){
				chnlDocLinks=irpChnl_Doc_LinkService.chnlDocByDocIds(docIds,IrpDocument.DOCUMENT_NOT_INFORM,5);
			}
		}
		return SUCCESS;
	}
	//获取所处状态（根据channelid获取chnlname）
	public static String findProjectStatus(Long _channelid){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpChannelService Service = (IrpChannelService) ac.getBean("irpChannelService"); 
		String str=Service.findChannelNameByChannelid(_channelid);
		return str;
	}
	//某个项目的附件数量
	public static  Integer  countProjectAttached(Long _projectId){ 
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpAttachedService Service = (IrpAttachedService) ac.getBean("irpAttachedService"); 
		return Service.countFile(_projectId, IrpAttached.PROJECT_DOCIDTYPE);
	}
	//得到某个项目的附件
	public  String  allProjectAttached(){
		int nCount=irpProjectService.countFileByProjectId(projectId);
		PageUtil pageUtil=new PageUtil(pageNum, pageSize, nCount);
		projectAttacheds=irpProjectService.findAllFileByProjectid(projectId,pageUtil);
		//获取任务id我参与或我负责的任务对于一个项目
		 taskids=getTaskidByprojectid();
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");   //chnlDocLinks   
		return SUCCESS;
	}
	private List<Long> getTaskidByprojectid() {
		HashMap<String,Object> map=new HashMap<String, Object>(); 
		map.put("projectid",projectId);
		map.put("parentid",0L);
		map.put("themetype",0L);
		map.put("orderByClause", " updatetime desc ");
		map.put("isState",IrpProjectShareTask.IS_TASK_STATE);
		map.put("offPersonId", LoginUtil.getLoginUserId());
		List<IrpProjectShareTask> Tasks=irpProjectShareTaskService.findShareTaskByMap(map,null);
		map.put("isState",IrpProjectShareTask.IS_SHARE_STATE);
		map.put("offPersonId", 0L);
		map.remove("themetype");
		List<IrpProjectShareTask> Tasks1=irpProjectShareTaskService.findShareTaskByMap(map,null);
		List<Long> shareTaskIds=new ArrayList<Long>();
		shareTaskIds=irpProjectTaskPersonService.myJoinProjectTask();
		if(Tasks1!=null&&Tasks1.size()>0){
			for (IrpProjectShareTask long1 : Tasks1) {
				shareTaskIds.add(long1.getId());
			}
		}
		if(Tasks!=null&&Tasks.size()>0){
			for (IrpProjectShareTask l : Tasks) {
				shareTaskIds.add(l.getId());
			}
		}
		return shareTaskIds;
	}

	public String toSystemPublicProject(){
		return SUCCESS;
	}

	public String toProjectTask() {
		irpProjects = null;
		HashMap<String,Object> map=new HashMap<String, Object>();
		IrpUser irpUser=LoginUtil.getLoginUser();   
		loginUserid=irpUser.getUserid();
		map.put("isclosed",null); 
		map.put("protype",0L); 
		irpProjects=irpProjectService.findProjectByMap(null,map);
	    if(irpProjects!=null && irpProjects.size()>0){
	    	for (int i = 0; i < irpProjects.size(); i++) {
	    		IrpProject project = irpProjects.get(i);
	    		if(project.getIspublish()==IrpProject.NOT_PUBLISH){
	    			irpProjects.remove(project);
	    		}
			}
	    }  
		return SUCCESS;
	}


	public String fourminfo(){
		projectId=projectId;
		return SUCCESS;
	}

	
	public String findBugForProject(){
		return SUCCESS;
	}
	
	/**
	 * 跳转到编辑项目页面
	 * @return
	 */
	public String toEditorProject(){
		
			return SUCCESS;
	}
	
	
	
	/**
	 * 初始化项目基本信息部分
	 * @return
	 */
	public String projectInfoInit(){
		this.irpProject=this.irpProjectService.findProjectById(projectId);
		return SUCCESS;
	}

	/**
	 * 修改项目名
	 * @return
	 */
	public String editorPrname(){
		try {
			IrpProject pro=new IrpProject();
			pro.setProjectid(projectId);
			pro.setPrname(prname);
			this.irpProjectService.updateByPrimaryKeySelective(pro);
			projectInfoInit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	

    /**
     * 原图
     * @param _docid
     * @return
     */
    public String docCoverPathSource(Long _docid, Integer docflag) {
        String filePath = "";
        IrpAttached attached = irpAttachedService.getIrpATttachedByDocIDFIle(_docid);
        if (attached != null) {
            String myFileName = attached.getAttfile();
            //获得文件路径 
            filePath = ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="
                    + ThumbnailPic.searchFileName(myFileName, "");
        } else if (docflag != null && docflag > 0) {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand" + docflag + ".jpg";
        } else {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand1.jpg";
        }
        return filePath;
    }

    /**
     * 获得知识的封面
     * @param _docid
     * @return
     */
    public String docCoverPath(Long _docid, Integer docflag) {
        String filePath = "";
        IrpAttached attached = irpAttachedService.getIrpATttachedByDocIDFIle(_docid);
        if (attached != null) {
            String myFileName = attached.getAttfile();
            //获得文件路径 
            filePath = ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="
                    + ThumbnailPic.searchFileName(myFileName, "_150X150");
        } else if (docflag != null && docflag > 0) {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand" + docflag + ".jpg";
        } else {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand1.jpg";
        }
        return filePath;
    }

    /**
     * 根据ID获得知识
     * @param _docid
     * @return
     */
    public IrpDocument getIrpDocumentById(Long _docid) {
        IrpDocument irpDocument = null;
        if (_docid != null) {
            irpDocument = this.irpDocumentService.irpDocument(_docid);
        }
        return irpDocument;
    }
}
