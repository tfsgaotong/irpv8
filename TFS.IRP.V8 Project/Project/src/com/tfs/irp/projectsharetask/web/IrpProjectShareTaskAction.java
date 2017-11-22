package com.tfs.irp.projectsharetask.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionSupport;  
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.project.service.IrpProjectService;
import com.tfs.irp.projectsharetask.entity.IrpProjectShareTask;
import com.tfs.irp.projectsharetask.entity.IrpProjectShareTaskExample;
import com.tfs.irp.projectsharetask.service.IrpProjectShareTaskService;
import com.tfs.irp.projecttaskperson.entity.IrpProjectTaskPerson;
import com.tfs.irp.projecttaskperson.service.IrpProjectTaskPersonService; 
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil; 
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.LoginUtil; 
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class IrpProjectShareTaskAction extends ActionSupport {
	private IrpProjectShareTaskService irpProjectShareTaskService;

	private List<IrpProjectShareTask> irpProjectShareTasks;
	
	private IrpProjectShareTask irpProjectShareTask;
	 
	private Long projectId; 
	
	private Integer maxAmount;
	
	private String friendlyshow;
	
	private Long shareTaskId;
	private IrpProject irpProject;
	private List<IrpProject> irpProjects;
	
	public List<IrpProject> getIrpProjects() {
		return irpProjects;
	}

	public void setIrpProjects(List<IrpProject> irpProjects) {
		this.irpProjects = irpProjects;
	}

	public IrpProject getIrpProject() {
		return irpProject;
	}

	public void setIrpProject(IrpProject irpProject) {
		this.irpProject = irpProject;
	}

	public String getTaskPersonNames() {
		return taskPersonNames;
	}

	public void setTaskPersonNames(String taskPersonNames) {
		this.taskPersonNames = taskPersonNames;
	}
	private String projectPersonIdString;
	
	private String taskPersonNames;
	 
	private String projectFileListStr;
	
	private IrpUserService irpUserService;
	
	
	private IrpProjectTaskPersonService irpProjectTaskPersonService;
	 
	
	private Long longuserid;

	private int pageNum=1; 
	
	private int pageSize=10; 
	
	private String pageHTML;
	
	private Long parentid=0l;
	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getFriendlyshow() {
		return friendlyshow;
	}

	public void setFriendlyshow(String friendlyshow) {
		this.friendlyshow = friendlyshow;
	}
	private IrpProjectService irpProjectService;
	
 
	public IrpProjectService getIrpProjectService() {
		return irpProjectService;
	}

	public void setIrpProjectService(IrpProjectService irpProjectService) {
		this.irpProjectService = irpProjectService;
	}
 
	public Long getProjectId() {
		return projectId;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
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
 
	public Long getShareTaskId() {
		return shareTaskId;
	}

	public void setShareTaskId(Long shareTaskId) {
		this.shareTaskId = shareTaskId;
	}

	public String getProjectFileListStr() {
		return projectFileListStr;
	}

	public void setProjectFileListStr(String projectFileListStr) {
		this.projectFileListStr = projectFileListStr;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	 
	public Long getLonguserid() {
		return longuserid;
	}

	public IrpProjectTaskPersonService getIrpProjectTaskPersonService() {
		return irpProjectTaskPersonService;
	}

	public void setIrpProjectTaskPersonService(
			IrpProjectTaskPersonService irpProjectTaskPersonService) {
		this.irpProjectTaskPersonService = irpProjectTaskPersonService;
	}
	public String getProjectPersonIdString() {
		return projectPersonIdString;
	}

	public void setProjectPersonIdString(String projectPersonIdString) {
		this.projectPersonIdString = projectPersonIdString;
	}

	public void setLonguserid(Long longuserid) {
		this.longuserid = longuserid;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public List<IrpProjectShareTask> getIrpProjectShareTasks() {
		return irpProjectShareTasks;
	}

	public void setIrpProjectShareTasks(
			List<IrpProjectShareTask> irpProjectShareTasks) {
		this.irpProjectShareTasks = irpProjectShareTasks;
	}

	public IrpProjectShareTask getIrpProjectShareTask() {
		return irpProjectShareTask;
	}

	public void setIrpProjectShareTask(IrpProjectShareTask irpProjectShareTask) {
		this.irpProjectShareTask = irpProjectShareTask;
	}

	public IrpProjectShareTaskService getIrpProjectShareTaskService() {
		return irpProjectShareTaskService;
	}

	public void setIrpProjectShareTaskService(
			IrpProjectShareTaskService irpProjectShareTaskService) {
		this.irpProjectShareTaskService = irpProjectShareTaskService;
	}
	public String addtheme(){
		return SUCCESS;
	}
	public String updatetheme(){
		irpProjectShareTask=  irpProjectShareTaskService.findShareTaskById(shareTaskId);
		return SUCCESS;
	}
	
	/**
	 * 动态的任务负责人为0，则为分享，若不等于0则为任务
	 * @return
	 */
	public void addTask(){  
		String[] projectFileList=null;
		
		IrpUser irpUser=LoginUtil.getLoginUser();
		IrpProject irpProject=irpProjectService.findProjectById(irpProjectShareTask.getProjectid());
		if(irpProject!=null){ 
			irpProjectShareTask.setProstatus(irpProject.getProstatus());
			irpProjectShareTask.setCruserid(irpUser.getUserid());
			irpProjectShareTask.setThemetype(irpProject.getProjecttype());
			Calendar ca=Calendar.getInstance();
			irpProjectShareTask.setCrtime(ca.getTime());
			irpProjectShareTask.setUpdatetime(ca.getTime());
			if(irpProjectShareTask.getStarttime()==null ||irpProjectShareTask.getStarttime().getTime()<=ca.getTime().getTime()){
				irpProjectShareTask.setStarttime(ca.getTime());
			}
			if(irpProjectShareTask.getEndtime()==null ||irpProjectShareTask.getEndtime().getTime()<=ca.getTime().getTime()){
				ca.add(Calendar.DATE,7);
				irpProjectShareTask.setEndtime(ca.getTime());
			}
			if(irpProjectShareTask.getOffpersonid()==null){//如果负责人没有就默认自己为负责人
				irpProjectShareTask.setOffpersonid(irpUser.getUserid());
			}
			irpProjectShareTask.setIscallmessage(IrpProjectShareTask.NO_CALL);
			irpProjectShareTask.setParentid(0L); 
			if(projectFileListStr!=null &&projectFileListStr.length()>0){
				projectFileList=projectFileListStr.split(",");
			}  
			 irpProjectShareTask.setIsstate(IrpProjectShareTask.IS_TASK_STATE);
			 irpProjectShareTask.setIsclosed(IrpProjectShareTask.NOT_CLOSED);
			 
			 String[]_taskPersons=null;
			 if(projectPersonIdString!=null &&projectPersonIdString.length()>0){
				 _taskPersons =projectPersonIdString.split(","); 
			 }
			 int nCount =irpProjectShareTaskService.addShareTask(irpProjectShareTask, projectFileList, _taskPersons);
			 ActionUtil.writer(String.valueOf(nCount)); 
		}
	}
	//添加回复
	public void addReplay(){
		int nCount=0;
 		IrpUser irpUser=LoginUtil.getLoginUser();
		irpProjectShareTask.setCruserid(irpUser.getUserid());
		Date crtime=new Date();
		irpProjectShareTask.setCrtime(crtime);
		irpProjectShareTask.setStarttime(crtime);
		irpProjectShareTask.setIscallmessage(IrpProjectShareTask.NO_CALL); 
		irpProjectShareTask.setOffpersonid(0L);
		 irpProjectShareTask.setIsstate(IrpProjectShareTask.IS_SHARE_STATE);
		 irpProjectShareTask.setIsclosed(IrpProjectShareTask.NOT_CLOSED);
		 irpProjectShareTask.setUpdatetime(new Date());
		//修改动态或者认为的修改时间  
		IrpProjectShareTask st=irpProjectShareTaskService.findShareTaskById(irpProjectShareTask.getParentid());
		st.setUpdatetime(new Date());
		irpProjectShareTaskService.updateShareTask(st,null);
		nCount=irpProjectShareTaskService.addShareTask(irpProjectShareTask,""); 
		ActionUtil.writer(String.valueOf(nCount)); 
	}

	//添加动态
	public void addShare(){
		IrpUser irpUser=LoginUtil.getLoginUser();
		IrpProject irpProject=irpProjectService.findProjectById(irpProjectShareTask.getProjectid());
		if(irpProject!=null){
			irpProjectShareTask.setCruserid(irpUser.getUserid());
			Date crtime=new Date();
			irpProjectShareTask.setCrtime(crtime);
			if(irpProjectShareTask.getStarttime()==null ){
				irpProjectShareTask.setStarttime(crtime);
			}
			irpProjectShareTask.setIscallmessage(IrpProjectShareTask.NO_CALL);
			irpProjectShareTask.setParentid(0L);
			irpProjectShareTask.setOffpersonid(0L);
			irpProjectShareTask.setIsstate(IrpProjectShareTask.IS_SHARE_STATE);
			irpProjectShareTask.setIsclosed(IrpProjectShareTask.NOT_CLOSED);
			irpProjectShareTask.setUpdatetime(new Date());
			String[] projectFileList=null;
			if(projectFileListStr!=null &&projectFileListStr.length()>0){
				projectFileList=projectFileListStr.split(",");
			} 
			int nCount=irpProjectShareTaskService.addShareTask(irpProjectShareTask,projectFileList);
			ActionUtil.writer(String.valueOf(nCount));
		}
	}
	// 查询的所有动态
	public String linkMeAllShareTask(){ 
		longuserid=LoginUtil.getLoginUserId(); 
		maxAmount=SysConfigUtil.getSysConfigNumValue("SHARE_AMOUNT");
		HashMap<String,Object> map=new HashMap<String, Object>(); 
		IrpProject irpProject=irpProjectService.findProjectById(projectId);
		if (irpProject==null) {
			friendlyshow=IrpProject.PROJECT_IS_DELETE;
			return ERROR;
		}
		if(Long.valueOf(irpProject.getIsclosed())==Long.valueOf(IrpProject.NOT_CLOSED)){
			map.put("orderByClause", " updatetime desc "); 
		}else if(Long.valueOf(irpProject.getIsclosed())==Long.valueOf(IrpProject.IS_CLOSED)){
			map.put("orderByClause", "crtime asc"); 
		}
		if(projectId!=null && projectId!=0){
			map.put("projectid",projectId);
		} 
		map.put("parentid",0L);
		int aDataCount=irpProjectShareTaskService.countShareTaskByMap(map);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		irpProjectShareTasks=irpProjectShareTaskService.findShareTaskByMap(map,pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		if(irpProjectShareTasks!=null && irpProjectShareTasks.size()>0){
			initShareTask(irpProjectShareTasks);
			for (int i = 0; i < irpProjectShareTasks.size(); i++) { 
				List attachedList=irpProjectShareTaskService.findAllFileByProjectIdAndShareId(irpProjectShareTasks.get(i).getProjectid(), irpProjectShareTasks.get(i).getSharetaskid());
				irpProjectShareTasks.get(i).setAttachedList(attachedList);
				List<IrpProjectShareTask> replayList=irpProjectShareTaskService.findReplayShareTask(irpProjectShareTasks.get(i).getSharetaskid());
				replayList=initShareTask(replayList);
				irpProjectShareTasks.get(i).setReplayShareTaskList(replayList);
			} 
		} 
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
	
	// 查询的所有回复
	public String linkMeAllShareTheme(){ 

		longuserid=LoginUtil.getLoginUserId();
		 maxAmount=SysConfigUtil.getSysConfigNumValue("SHARE_AMOUNT");
		if(shareTaskId==null)return ERROR;
		irpProjectShareTask=irpProjectShareTaskService.findShareTaskById(shareTaskId);
		if(irpProjectShareTask==null)return ERROR;
		//判断状态
		IrpUser irpUser=null;
		if(Long.valueOf( irpProjectShareTask.getParentid())!=0L || Long.valueOf(irpProjectShareTask.getIsstate())!=Long.valueOf(IrpProjectShareTask.IS_TASK_STATE)){
			friendlyshow=IrpProjectShareTask.TASK_NOT_SAVE;
			return ERROR;
		} 
		irpUser=irpUserService.findUserByUserId(irpProjectShareTask.getOffpersonid());
		if(irpUser==null)return ERROR;
		//初始化用户图片地址 
		irpProjectShareTask.setUserPicUrl(irpUser.getDefaultUserPic());   
		irpProjectShareTask.setUserName(LoginUtil.getUserNameString(irpUser));

		HashMap<String,Object> map=new HashMap<String, Object>(); 
		IrpProject irpProject=irpProjectService.findProjectById(projectId);
		if (irpProject==null) {
			friendlyshow=IrpProject.PROJECT_IS_DELETE;
			return ERROR;
		}
		if(Long.valueOf(irpProject.getIsclosed())==Long.valueOf(IrpProject.NOT_CLOSED)){
			map.put("orderByClause", " updatetime desc "); 
		}else if(Long.valueOf(irpProject.getIsclosed())==Long.valueOf(IrpProject.IS_CLOSED)){
			map.put("orderByClause", "crtime asc"); 
		}

		map.put("parentid",shareTaskId);
		int aDataCount=irpProjectShareTaskService.countShareTaskByMap(map);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		irpProjectShareTasks=irpProjectShareTaskService.findShareTaskByMap(map,pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		if(irpProjectShareTasks!=null && irpProjectShareTasks.size()>0){
			initShareTask(irpProjectShareTasks);
			for (int i = 0; i < irpProjectShareTasks.size(); i++) { 
				//List attachedList=irpProjectShareTaskService.findAllFileByProjectIdAndShareId(irpProjectShareTasks.get(i).getProjectid(), irpProjectShareTasks.get(i).getSharetaskid());
				//irpProjectShareTasks.get(i).setAttachedList(attachedList);
				List<IrpProjectShareTask> replayList=irpProjectShareTaskService.findReplayShareTask(irpProjectShareTasks.get(i).getSharetaskid());
				replayList=initShareTask(replayList);
				irpProjectShareTasks.get(i).setReplayShareTaskList(replayList);
			} 
		} 
		return SUCCESS;
	} 
	
	/**
	 * 初始化用户图片地址
	 * @param irpProjectShareTasks
	 * @return
	 */ 
	public List<IrpProjectShareTask> initShareTask(List<IrpProjectShareTask> irpProjectShareTasks){
		for (int i = 0; i < irpProjectShareTasks.size(); i++) { 
			//初始化用户图片地址
			IrpUser user = irpUserService.findUserByUserId(irpProjectShareTasks.get(i).getCruserid());
			irpProjectShareTasks.get(i).setUserPicUrl(user.getDefaultUserPic());   
			//判断，昵称  
			String cValue=LoginUtil.getUserNameString(user);
			
			irpProjectShareTasks.get(i).setUserName(cValue); 
		} 
		return irpProjectShareTasks;
	}
	//删除动态
	public void deleteShareTask(){
		  int nCount=irpProjectShareTaskService.deleteShareTask(irpProjectShareTask.getSharetaskid());
		  ActionUtil.writer(String.valueOf(nCount));   
	}
	//所有任务
	public String allTask(){  
		longuserid=LoginUtil.getLoginUserId();
		HashMap<String,Object> map=new HashMap<String, Object>(); 

		if(projectId!=null && projectId!=0){
			map.put("projectid",projectId);
		}
		map.put("isState",IrpProjectShareTask.IS_TASK_STATE);
		map.put("parentid",0L);
		map.put("orderByClause", " updatetime desc ");
		int aDataCount=irpProjectShareTaskService.countShareTaskByMap(map);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		
		irpProjectShareTasks=irpProjectShareTaskService.findShareTaskByMap(map,pageUtil);

		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");  
		return SUCCESS;
	}
	///我给别人的任务
	public String GiveAllTask(){
		longuserid=LoginUtil.getLoginUserId();
		
		HashMap<String,Object> map=new HashMap<String, Object>(); 
		map.put("projectid",projectId);
		map.put("parentid",0L);
		map.put("themetype",0L);
		map.put("orderByClause", " crtime desc ");
		map.put("cruserid",longuserid); 
		map.put("isState",IrpProjectShareTask.IS_TASK_STATE);
		/**
		 * 任务成员不等于创建者(任务给别人了)cruserid,只有此次调用方法给了
		 * (参与任务) 负责人不能是自己
		 * （负责任务）负责人是自己
		 */
		int aDataCount=irpProjectShareTaskService.countShareTaskByMap(map);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		
		irpProjectShareTasks=irpProjectShareTaskService.findShareTaskByMap(map,pageUtil);
		 
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		return SUCCESS;
		
	}
	//我负责的任务
	public String MyAllTask(){
		longuserid=LoginUtil.getLoginUserId(); 
		HashMap<String,Object> map=new HashMap<String, Object>(); 
		map.put("projectid",projectId);
		map.put("parentid",0L);
		map.put("themetype",0L);
		map.put("orderByClause", " updatetime desc ");
		map.put("isState",IrpProjectShareTask.IS_TASK_STATE);
		map.put("offPersonId", LoginUtil.getLoginUserId());
		int aDataCount=irpProjectShareTaskService.countShareTaskByMap(map);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		irpProjectShareTasks=irpProjectShareTaskService.findShareTaskByMap(map,pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		return SUCCESS; 
	}
	//我参与的任务
	public String MeIsPersonAllTask(){
		longuserid=LoginUtil.getLoginUserId(); 
		HashMap<String,Object> map=new HashMap<String, Object>(); 
		map.put("orderByClause", " updatetime desc ");
		map.put("projectid",projectId);
		map.put("parentid",0L);
		map.put("themetype",0L);
		List<Long> shareTaskIds=new ArrayList<Long>();
		shareTaskIds=irpProjectTaskPersonService.myJoinProjectTask();
		map.put("shareTaskIds", shareTaskIds); 
		map.put("isState",IrpProjectShareTask.IS_TASK_STATE);
		map.put("isJoin","isJoin");//参与者
		if(shareTaskIds!=null && shareTaskIds.size()>0){
			int aDataCount=irpProjectShareTaskService.countShareTaskByMap(map);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount); 
			irpProjectShareTasks=irpProjectShareTaskService.findShareTaskByMap(map,pageUtil); 
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		} else{
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, 0);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)"); 
		}
		return SUCCESS; 
	}
	//查询项目动态数量
	public static  Integer countShareTaskCollectByShareTaskId(Long _projectId){ 
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectShareTaskService Service = (IrpProjectShareTaskService) ac.getBean("irpProjectShareTaskService");
		
		IrpProjectShareTaskExample  example=new IrpProjectShareTaskExample();
		example.createCriteria().andProjectidEqualTo(_projectId);
		
		Integer nCount=Service.countShareTaskByExample(example); 
		return nCount;
	}
	//查询动态回复的数量
	public static Integer countReplayShareTask(Long _shareTaskID){ 
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectShareTaskService Service = (IrpProjectShareTaskService) ac.getBean("irpProjectShareTaskService"); 
		
		IrpProjectShareTaskExample  example=new IrpProjectShareTaskExample();
		example.createCriteria().andParentidEqualTo(_shareTaskID);
		
		Integer nCount=Service.countShareTaskByExample(example);  
		return nCount;
	}
	//查询项目任务数量 
	public static  Integer countTaskCollectByShareTaskId(Long _projectId){ 
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectShareTaskService Service = (IrpProjectShareTaskService) ac.getBean("irpProjectShareTaskService"); 
		
		IrpProjectShareTaskExample  example=new IrpProjectShareTaskExample();
		example.createCriteria().andIsstateEqualTo(IrpProjectShareTask.IS_TASK_STATE)
								.andProjectidEqualTo(_projectId)
								.andParentidEqualTo(0L);
		
		Integer nCount=Service.countShareTaskByExample(example);  
		return nCount;
	} 
	/**
	 * 查看某个动态的详细信息
	 */
	public String findShareTaskInfo(){
		longuserid=LoginUtil.getLoginUserId();
		 maxAmount=SysConfigUtil.getSysConfigNumValue("SHARE_AMOUNT");
		if(shareTaskId==null)return ERROR;
		irpProjectShareTask=irpProjectShareTaskService.findShareTaskById(shareTaskId);
		if(irpProjectShareTask==null)return ERROR;
		//判断状态
		IrpUser irpUser=null;
		if(Long.valueOf( irpProjectShareTask.getParentid())!=0L || Long.valueOf(irpProjectShareTask.getIsstate())!=Long.valueOf(IrpProjectShareTask.IS_TASK_STATE)){
			friendlyshow=IrpProjectShareTask.TASK_NOT_SAVE;
			return ERROR;
		} 
		irpUser=irpUserService.findUserByUserId(irpProjectShareTask.getOffpersonid());
		if(irpUser==null)return ERROR;
		//初始化用户图片地址 
		irpProjectShareTask.setUserPicUrl(irpUser.getDefaultUserPic());   
		irpProjectShareTask.setUserName(LoginUtil.getUserNameString(irpUser)); 
		//查询任务的回复
		List<IrpProjectShareTask> replayList=irpProjectShareTaskService.findReplayShareTask(shareTaskId);
		if(replayList!=null && replayList.size()>0){
			for (int i = 0; i < replayList.size(); i++) {
				IrpUser user=irpUserService.findUserByUserId(replayList.get(i).getCruserid());//创建者
				if(user==null)return ERROR;
				//初始化用户图片地址 
				replayList.get(i).setUserPicUrl(user.getDefaultUserPic());   
				String cValueReplay= LoginUtil.getUserNameString(user);
				replayList.get(i).setUserName(cValueReplay);
				
				
			}
		}
		irpProjectShareTask.setReplayShareTaskList(replayList);
		//查询项目的附件
		List attachedList=irpProjectShareTaskService.findAllFileByProjectIdAndShareId(irpProjectShareTask.getProjectid(), irpProjectShareTask.getSharetaskid());
		attachedList=irpProjectService.findFileByTaskid(shareTaskId);
		irpProjectShareTask.setAttachedList(attachedList);
		//查询他的成员//不包括负责人
		List<IrpProjectTaskPerson> personsList=irpProjectTaskPersonService.findTaskPerson(irpProjectShareTask.getSharetaskid(), irpProjectShareTask.getProjectid());
		projectPersonIdString="";
		taskPersonNames="";
		if(personsList!=null && personsList.size()>0){
			  for (IrpProjectTaskPerson person : personsList) {
				 if(Long.valueOf(person.getPersonid())!=Long.valueOf(irpProjectShareTask.getOffpersonid())){
					 IrpUser  personUser=irpUserService.findUserByUserId(person.getPersonid());
					 if(personUser!=null){
						 projectPersonIdString+=person.getPersonid()+",";
						 taskPersonNames+=personUser.getUsername()+",";
					 }
				 }
			}
		}
		//
		irpProject=irpProjectService.findProjectById(irpProjectShareTask.getProjectid());
		irpProjects=new ArrayList<IrpProject>();
		irpProjects.add(irpProject);
		return SUCCESS;
	}

	//查询任务信息
	public String selectTaskById(){ 
		irpProjectShareTask=irpProjectShareTaskService.findShareTaskById(shareTaskId); 
		return SUCCESS; 
	}
	//修改任务信息
	public void updateTask(){ 
		 String[]_taskPersons=null;
		 if(projectPersonIdString!=null &&projectPersonIdString.length()>0){
			 _taskPersons =projectPersonIdString.split(","); 
		 }
		int nCount=irpProjectShareTaskService.updateShareTask(irpProjectShareTask,_taskPersons);
		ActionUtil.writer(String.valueOf(nCount));
	}
	//修改任务状态（开启关闭）
	public  void updateTaskState(){
		IrpProjectShareTask shareTask=irpProjectShareTaskService.findShareTaskById(irpProjectShareTask.getSharetaskid());
		irpProjectShareTask.setIsstate(shareTask.getIsstate());
		irpProjectShareTask.setProjectid(shareTask.getProjectid());
		int nCount=irpProjectShareTaskService.updateShareTask(irpProjectShareTask,null);
		if(nCount>0){
			if(irpProjectShareTask.getIsclosed()==IrpProjectShareTask.IS_CLOSED){
				shareTask.setSharedesc("任务关闭了");}
			else if(irpProjectShareTask.getIsclosed()==IrpProjectShareTask.NOT_CLOSED){
				shareTask.setSharedesc("重新启动了任务");} 
			shareTask.setParentid(shareTask.getSharetaskid());
			shareTask.setCruserid(LoginUtil.getLoginUserId());
			shareTask.setCrtime(new Date());
			shareTask.setIsvisible(IrpProjectShareTask.PUBLIC_TO_SEE);
			shareTask.setIscallmessage(IrpProjectShareTask.NO_CALL);
			shareTask.setIsstate(IrpProjectShareTask.IS_SHARE_STATE);
			shareTask.setIsclosed(IrpProjectShareTask.NOT_CLOSED);
			shareTask.setUpdatetime(new Date());
			irpProjectShareTaskService.addShareTask(shareTask, "");
		}
		ActionUtil.writer(String.valueOf(nCount));
	}
	
	private String usname;
	
	public String getUsname() {
		return usname;
	}

	public void setUsname(String usname) {
		this.usname = usname;
	}

	private long listname;
	
	public long getListname() {
		return listname;
	}

	public void setListname(long listname) {
		this.listname = listname;
	}

	public String alltheme(){
		
		longuserid=LoginUtil.getLoginUserId();
		HashMap<String,Object> map=new HashMap<String, Object>(); 
		HashMap<String,Object> maps=new HashMap<String, Object>(); 
		if(projectId!=null && projectId!=0){
			map.put("projectid",projectId);
		}
		map.put("isState",IrpProjectShareTask.IS_TASK_STATE);
		map.put("parentid",0L);
		map.put("orderByClause", " updatetime desc ");
		int aDataCount=irpProjectShareTaskService.countShareTaskByMap(map);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		
		irpProjectShareTasks=irpProjectShareTaskService.findShareTaskByMap(map,pageUtil);
		List<IrpProjectShareTask> da =null;
		for(int i =0;i<irpProjectShareTasks.size();i++){	
			maps.put("parentid", irpProjectShareTasks.get(i).getSharetaskid());	
			maps.put("sharedesc", irpProjectShareTasks.get(i).getSharedesc());
			da=irpProjectShareTaskService.findShareTaskByMap(maps,pageUtil,false);
			for(int j=0;j<da.size();j++){
				listname=da.get(j).getCruserid();
			}
		}
		
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");  
		return SUCCESS;
	}
	
	//查询主题回复的数量
	public static Integer countReplayShareTheme(Long _shareTaskID){ 
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectShareTaskService Service = (IrpProjectShareTaskService) ac.getBean("irpProjectShareTaskService"); 
		
		IrpProjectShareTaskExample  example=new IrpProjectShareTaskExample();
		example.createCriteria().andParentidEqualTo(_shareTaskID).andSharedescNotEqualTo("启动了任务");
		
		Integer nCount=Service.countShareTaskByExample(example);  
		return nCount;
	}
}
