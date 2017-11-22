package com.tfs.irp.projectperson.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
 
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.bug.entity.IrpBug;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.project.service.IrpProjectService; 
import com.tfs.irp.projectperson.entity.IrpProjectPerson;
import com.tfs.irp.projectperson.entity.IrpProjectPersonExample;
import com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria;
import com.tfs.irp.projectperson.service.IrpProjectPersonService;
import com.tfs.irp.projectsharetask.entity.IrpProjectShareTask;
import com.tfs.irp.projectsharetask.service.IrpProjectShareTaskService;
import com.tfs.irp.projecttaskperson.entity.IrpProjectTaskPersonExample;
import com.tfs.irp.projecttaskperson.service.IrpProjectTaskPersonService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpProjectPersonAction extends ActionSupport {
	private IrpProjectPersonService irpProjectPersonService;
	
	private Map<Long, String> teamNameMap = new HashMap<Long, String>();
	private IrpProjectTaskPersonService irpProjectTaskPersonService;
	private Map<Long, String> personNameMap = new HashMap<Long, String>();
	
	/**
	 * 添加成员列表
	 */
	private List<Long> addPersons=new ArrayList<Long>();
	
	public IrpProjectTaskPersonService getIrpProjectTaskPersonService() {
		return irpProjectTaskPersonService;
	}

	public void setIrpProjectTaskPersonService(
			IrpProjectTaskPersonService irpProjectTaskPersonService) {
		this.irpProjectTaskPersonService = irpProjectTaskPersonService;
	}
	/**
	 * 删除成员列表
	 */
	private List<Long> delPersons=new ArrayList<Long>();
	
	private String addPersonsStr;
	
	private String delPersonsStr;
	
	public String getAddPersonsStr() {
		return addPersonsStr;
	}

	public void setAddPersonsStr(String addPersonsStr) {
		this.addPersonsStr = addPersonsStr;
	}

	public String getDelPersonsStr() {
		return delPersonsStr;
	}

	public void setDelPersonsStr(String delPersonsStr) {
		this.delPersonsStr = delPersonsStr;
	}
	/**
	 * 系统所有成员
	 */
	private List<IrpUser> teamPersons;
	
	/**
	 * 当前项目成员
	 */
	private List<IrpProjectPerson> projectPersons;
	
	private IrpUserService userService;
	
	private Long personId;
	
	private Long loginUserId;
	
	private String friendlyshow;
	
	private Boolean isPersonList;
	public Long getLoginUserId() {
		return loginUserId;
	}

	public Boolean getIsPersonList() {
		return isPersonList;
	}

	public void setIsPersonList(Boolean isPersonList) {
		this.isPersonList = isPersonList;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}
	private Long projectId;
	
	private String personIds; 
	
	private List<IrpUser> irpUsers;
	
	private IrpProject irpProject;
	
	private int pageNum=1; 
	
	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}
	private int pageSize=10; 
	
	private String pageHTML;
	
	
	private IrpMicroblogService irpMicroblogService;
	
	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}

	public IrpProject getIrpProject() {
		return irpProject;
	}

	public String getFriendlyshow() {
		return friendlyshow;
	}

	public void setFriendlyshow(String friendlyshow) {
		this.friendlyshow = friendlyshow;
	}

	public void setIrpProject(IrpProject irpProject) {
		this.irpProject = irpProject;
	}

	public IrpMicroblogService getIrpMicroblogService() {
		return irpMicroblogService;
	}

	public void setIrpMicroblogService(IrpMicroblogService irpMicroblogService) {
		this.irpMicroblogService = irpMicroblogService;
	}

	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}
	private IrpProjectShareTaskService irpProjectShareTaskService;
	 
	private IrpProjectService irpProjectService;
	
	private IrpMicroblogFocusService irpMicroblogFocusService;
	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}

	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}

	public String getPersonIds() {
		return personIds;
	}

	public void setPersonIds(String personIds) {
		this.personIds = personIds;
	}
	public IrpProjectService getIrpProjectService() {
		return irpProjectService;
	}

	public IrpProjectShareTaskService getIrpProjectShareTaskService() {
		return irpProjectShareTaskService;
	}

	public void setIrpProjectShareTaskService(
			IrpProjectShareTaskService irpProjectShareTaskService) {
		this.irpProjectShareTaskService = irpProjectShareTaskService;
	}

	public void setIrpProjectService(IrpProjectService irpProjectService) {
		this.irpProjectService = irpProjectService;
	}

 
	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public IrpProjectPersonService getIrpProjectPersonService() {
		return irpProjectPersonService;
	}

	public void setIrpProjectPersonService(
			IrpProjectPersonService irpProjectPersonService) {
		this.irpProjectPersonService = irpProjectPersonService;
	}
	/**
	 * 通过用户的id计算出有多少个用户关注wo
	 * @return
	 */
	public int MicroblogFocusCountFocusUserid(long _userid){
		return this.irpMicroblogFocusService.countMicroblogFocusFocusUserid(_userid);
	}
	/**
	 * 通过用户的id获得用户共关注了多少个用户
	 */
	public int MicroblogFocusCountUserid(long _focususerid){
		return this.irpMicroblogFocusService.countMicroblogFocusUserid(_focususerid);
	}
	/**
	 * 通过用户的id获得用户共有多少条微知
	 */
	public int MicroblogCountByUserid(long _userid){
	 return this.irpMicroblogService.coutnMicroblogOfUserid(_userid, IrpMicroblog.ISDELFALSE);	
	}
	//查看项目成员列表
	public String allPerson(){
		irpUsers=null;
		loginUserId=LoginUtil.getLoginUserId();
		irpProject=irpProjectService.findProjectById(projectId);
		if(irpProject==null){
			friendlyshow=IrpProject.PROJECT_IS_DELETE;
			return ERROR;
		}
		isPersonList=true;
		int aCount=irpProjectPersonService.findProjectPersonCount(projectId, IrpProjectPerson.IS_STATE, IrpProjectPerson.IS_STATUS);
		PageUtil pageUtil=new PageUtil(pageNum, pageSize, aCount);
		irpUsers=irpProjectPersonService.projectAllPerson(projectId, IrpProjectPerson.IS_STATE,IrpProjectPerson.IS_STATUS,pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("pageperson(#PageNum#)");   //chnlDocLinks   
		return SUCCESS;
	}
	//查看项目关注着列表
	public String allPersonSee(){
		irpUsers=null;
		loginUserId=LoginUtil.getLoginUserId();
		irpProject=irpProjectService.findProjectById(projectId);
		if(irpProject==null){
			friendlyshow=IrpProject.PROJECT_IS_DELETE;
			return ERROR;
		}
		isPersonList=false;
		int aCount=irpProjectPersonService.findProjectPersonCount(projectId, IrpProjectPerson.NOT_STATE, IrpProjectPerson.IS_STATUS);
		PageUtil pageUtil=new PageUtil(pageNum, pageSize, aCount);
		irpUsers=irpProjectPersonService.projectAllPerson(projectId, IrpProjectPerson.NOT_STATE,IrpProjectPerson.IS_STATUS,pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("pageperson(#PageNum#)");   //chnlDocLinks   
		return SUCCESS;
	}
	
	//添加项目成员
	public void  addProjectPerson(){
		Long loginUserid=LoginUtil.getLoginUserId();
		int nCount=irpProjectPersonService.addProjectPerson(loginUserid, IrpProjectPerson.IS_STATE, projectId, IrpProjectPerson.IS_STATUS,null,null);
		ActionUtil.writer(String.valueOf(nCount));
	}
	
	//添加成员（数组） 
	public void  addProjectPersons(){
		int nCount=0; 
		if(personIds!=null && personIds.length()>0){
			IrpProject project=irpProjectService.findProjectById(projectId);
			String[] pIds=personIds.split(",");
			for (int i = 0; i <pIds.length; i++) {
				String _personIdStr = pIds[i];
				if(!_personIdStr.trim().equals("")){ 
					if(new Long(_personIdStr).longValue()!=project.getOffpersonid().longValue()){
						nCount+=irpProjectPersonService.addProjectPerson(new Long(_personIdStr),IrpProjectPerson.IS_STATE, projectId, IrpProjectPerson.IS_STATUS,null,project.getPrname());
					}
				}
			}
		}
		ActionUtil.writer(String.valueOf(nCount));
	}  
	 
	//查看登录者是不是成员(开通状态的成员)
	public static Long findIsStateByProjectIds(Long _projectId){  
		Long nState=null;
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectPersonService Service = (IrpProjectPersonService) ac.getBean("irpProjectPersonService"); 
		IrpProjectPersonExample example=new IrpProjectPersonExample();
		example.createCriteria().andPersonidEqualTo(LoginUtil.getLoginUserId())
								.andPridEqualTo(_projectId)
								.andIsstateEqualTo(IrpProjectPerson.IS_STATE)
								.andStatusEqualTo(IrpProjectPerson.IS_STATUS);//开通状态
		
		List<IrpProjectPerson> persons=Service.findPersonByExample(example); 
		///根据这个条件只可以查询出来一个对象
		if(persons!=null &&persons.size()>0){
			IrpProjectPerson person=persons.get(0); 
			nState=person.getIsstate(); 
		}
		return nState;
	}
	//查看登陆者是不是关注着(账号开通状态哈)
	public static  Long findNotStateByProjectIds(Long _projectId){  
		Long nState=null;
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectPersonService Service = (IrpProjectPersonService) ac.getBean("irpProjectPersonService"); 
		IrpProjectPersonExample example=new IrpProjectPersonExample();
		example.createCriteria().andPersonidEqualTo(LoginUtil.getLoginUserId())
								.andPridEqualTo(_projectId)
								.andIsstateEqualTo(IrpProjectPerson.NOT_STATE)
								.andStatusEqualTo(IrpProjectPerson.IS_STATUS);//开通状态
		List<IrpProjectPerson> persons=Service.findPersonByExample(example);
		///根据这个条件只可以查询出来一个对象
		if(persons!=null &&persons.size()>0){
			IrpProjectPerson person=persons.get(0); 
			nState=person.getIsstate(); 
		}
		return nState;
	}
	//添加关注（主动关注）
	public void addAttention(){
		Long loginUserid=LoginUtil.getLoginUserId();
		int nCount=0;
		if(loginUserid!=null){
			nCount=irpProjectPersonService.addProjectPerson(  loginUserid ,IrpProjectPerson.NOT_STATE ,projectId, IrpProjectPerson.IS_STATUS);
		}  
		ActionUtil.writer(String.valueOf(nCount));
	}
	//将自己从某个项目中删除
	public void deleteAttention(){  
		Long loginUserid=LoginUtil.getLoginUserId();
		if(loginUserid!=null){
			//先查询这个对象时成员还是关注者
			IrpProjectPersonExample example=new IrpProjectPersonExample();
			example.createCriteria().andPersonidEqualTo(loginUserid)
									.andPridEqualTo(projectId);
			List<IrpProjectPerson> personsList=irpProjectPersonService.findPersonByExample(example); 
			if(personsList!=null &&personsList.size()>0){ 
				IrpProjectPerson person=personsList.get(0);
				if(person!=null ){  
					 if(person.getIsstate().toString() .equals(IrpProjectPerson.IS_STATE.toString())){
						String str[]=null;
						IrpProjectShareTask shareTask=new IrpProjectShareTask();
						shareTask.setCrtime(new Date());
						shareTask.setCruserid(loginUserid);
						shareTask.setIscallmessage(IrpProjectShareTask.NO_CALL);
						shareTask.setSharedesc("退出了项目");
						shareTask.setOffpersonid(0L);
						shareTask.setParentid(0L);
						shareTask.setProjectid(person.getPrid());
						shareTask.setIsstate(IrpProjectShareTask.IS_SHARE_STATE);
						shareTask.setIsclosed(IrpProjectShareTask.NOT_CLOSED);
						shareTask.setIsvisible(IrpProjectShareTask.PUBLIC_TO_SEE);
						irpProjectShareTaskService.addShareTask(shareTask, str);
					 
					}  
				}
				int nCount=irpProjectPersonService.deletePersonByProjectId(projectId,LoginUtil.getLoginUserId());
				ActionUtil.writer(String.valueOf(nCount));
			} 
		}
	}
	//邀请关注者（发送私信）
	public void addMesgToAddPersonAttention(){
		int nCount=0; 
		if(personIds!=null && personIds.length()>0){ 
			 String[] pIds=personIds.split(",");
			IrpUser irpUser=LoginUtil.getLoginUser();
			IrpProject irpProject=irpProjectService.findProjectById(projectId); //查询项目信息 
			if(irpProject!=null){ 
			for (int i = 0; i <pIds.length; i++) {
					String _personIdStr=pIds[i];
					if(_personIdStr!=null && _personIdStr.length()>0){  
						Long _code=(long)Math.ceil(Math.random()*10000);
						nCount=nCount+irpProjectPersonService.addProjectPerson(new Long(_personIdStr), IrpProjectPerson.NOT_STATE,projectId , IrpProjectPerson.NOT_STATUS, _code,irpProject.getPrname());
					}
			} 
		}
		} 
		ActionUtil.writer(String.valueOf(nCount));  
	}
	//页面调用静态方法，得到用户名
	public static String  findUserNameByUserId(Long _userId){ 
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpUserService Service = (IrpUserService) ac.getBean("irpUserService"); 
		IrpUser irpUser=Service.findUserByUserId(_userId);
		String returnName=LoginUtil.getUserNameString(irpUser);
		return returnName;
	}
	//删除成员
	public void deletePersonFromProject(){ 
		if(projectId!=null && personId!=null){
			int nCount=irpProjectPersonService.deletePersonByProjectId(projectId,personId);
			 IrpProjectTaskPersonExample example=new IrpProjectTaskPersonExample();
			 example.createCriteria().andProjectidEqualTo(projectId).andPersonidEqualTo(personId);
			irpProjectTaskPersonService.deleteTaskPerson(example);
			ActionUtil.writer(String.valueOf(nCount)); 
		} 	
	} 
	
	//查看项目成员的数量(开通状态下)
	public static Integer findProjectPersonCount(Long _projectId){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectPersonService Service = (IrpProjectPersonService) ac.getBean("irpProjectPersonService"); 
		
		Integer nCount=Service.findProjectPersonCount(_projectId,IrpProjectPerson.IS_STATE,IrpProjectPerson.IS_STATUS);
		return nCount;
	} 
	
	//项目关注折数量(开通状态下)
	public static Integer findProjectPersonAttCount(Long _projectId){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpProjectPersonService Service = (IrpProjectPersonService) ac.getBean("irpProjectPersonService"); 
		Integer nCount=Service.findProjectPersonCount(_projectId,IrpProjectPerson.NOT_STATE,IrpProjectPerson.IS_STATUS);
		return nCount;
	}
	
	//修改人员是否开通状态
	public void updatePersonStatus(){
		int nCount=0;
		IrpProjectPersonExample example=new IrpProjectPersonExample();
		
		example.createCriteria().andPersonidEqualTo(LoginUtil.getLoginUserId())
								.andPridEqualTo(projectId);
		
		List<IrpProjectPerson> persons=irpProjectPersonService.findPersonByExample(example);
		if(persons!=null &&persons.size()>0){
			IrpProjectPerson person=persons.get(0);
			person.setStatus(IrpProjectPerson.IS_STATUS);//开通
			nCount=irpProjectPersonService.updateProjectPerson(person);
		} 
		ActionUtil.writer(String.valueOf(nCount));
	}
	private Long cruserid;
	
	public Long getCruserid() {
		return cruserid;
	}

	public void setCruserid(Long cruserid) {
		this.cruserid = cruserid;
	}
	private List<IrpUser> listUsers;
	
	public List<IrpUser> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<IrpUser> listUsers) {
		this.listUsers = listUsers;
	}
	public String findPersons(){
		//手机端用获取项目创始人id
		irpProject=irpProjectService.findProjectById(projectId);
		cruserid=irpProject.getCruserid();
			this.loginUserId=LoginUtil.getLoginUserId();
			findTeamPersons();
			findProjectPersons();
	
		return SUCCESS;
	}
	
	/**
	 * 查询所有已注册系统用户
	 */
	private void findTeamPersons(){
		this.teamPersons=userService.findAllRegUsers();
	}

	/**
	 * 查询该项目下的所有成员
	 */
	private void findProjectPersons(){
		IrpProjectPersonExample example=new IrpProjectPersonExample();
		Criteria criteria=example.createCriteria();
		criteria.andPridEqualTo(projectId);
		example.setOrderByClause(" crtime desc");
		this.projectPersons=this.irpProjectPersonService.findPersonByExample(example);
		List<IrpUser> userList = userService
				.findUserByExample(new IrpUserExample());
		for (IrpProjectPerson ele : projectPersons) {
			for (IrpUser element : userList) {
				if (ele.getPersonid() == Integer.parseInt(element
						.getUserid() + "")) {
					personNameMap.put(ele.getPersonid(),
							element.getTruename());
					break;
				}
			}
		}
	}
	/*private List<IrpUser> findProjectPersons(){
		List<IrpUser> list=new ArrayList<IrpUser>();
		IrpProjectPersonExample example=new IrpProjectPersonExample();
		Criteria criteria=example.createCriteria();
		criteria.andPridEqualTo(projectId);
		example.setOrderByClause(" crtime desc");
		this.projectPersons=this.irpProjectPersonService.findPersonByExample(example);
		List<IrpUser> userList = userService
				.findUserByExample(new IrpUserExample());
		for (IrpProjectPerson ele : projectPersons) {
			for (IrpUser element : userList) {
				if (ele.getPersonid() == Integer.parseInt(element
						.getUserid() + "")) {
					personNameMap.put(ele.getPersonid(),
							element.getTruename());
					break;
				}
			}
			IrpUser irpUser=userService.findUserByUserId(ele.getPersonid()); 
			list.add(irpUser);
		}
		return list;
	}*/
	private String token;
	
	private String tokens;
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	/**
	 * 添加成员到项目
	 * @return
	 */
	public String addPerson(){
		try {
			this.loginUserId=LoginUtil.getLoginUserId();
			String[] addps=addPersonsStr.split(",");
			for(int i=0;i<addps.length;i++){
				addSimplePersonToProject(Long.parseLong(addps[i]));
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		findTeamPersons();
		findProjectPersons();
		return SUCCESS;
	}
	/**
	 * 添加成员到项目
	 * @return
	 */
	public String addPersonApp(){
		try {
			irpProject=irpProjectService.findProjectById(projectId);
			cruserid=irpProject.getCruserid();
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			tokens=token;
			IrpUser irpuser = mobileAction.getlogin(token);
			this.loginUserId=irpuser.getUserid();
			String[] addps=addPersonsStr.split(",");
			for(int i=0;i<addps.length;i++){
				addSimplePersonToProject(Long.parseLong(addps[i]));
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		findTeamPersons();
		findProjectPersons();
		return SUCCESS;
	}
	/**
	 * 添加单一成员到项目
	 */
	private void addSimplePersonToProject(Long pid) throws Exception{
		Long crid=queryProjectCreatePerson();
		IrpProjectPerson ipp=new IrpProjectPerson();
		ipp.setCrtime(new java.util.Date());
		ipp.setCruserid(crid);
		ipp.setPersonid(pid);
		ipp.setPrid(projectId);
		ipp.setProperid(TableIdUtil.getNextId(ipp));
		ipp.setIsstate(IrpProjectPerson.IS_STATE);
		ipp.setStatus(IrpProjectPerson.IS_STATUS);
		this.irpProjectPersonService.insert(ipp);
	}
	
	/**
	 * 查询项目负责人Id
	 * @return
	 */
	private Long queryProjectCreatePerson(){
		IrpProjectPersonExample example=new IrpProjectPersonExample();
		Criteria criteria=example.createCriteria();
		criteria.andPridEqualTo(projectId);
		return this.irpProjectPersonService.findPersonByExample(example).get(0).getCruserid();
	}
	
	/**
	 * 从项目中删除成员
	 * @return
	 */
	public String delPerson(){
		try {
			this.loginUserId=LoginUtil.getLoginUserId();
			//System.out.println(delPersonsStr);
			String[] delps=delPersonsStr.split(",");
			for(int i=0;i<delps.length;i++){
				this.delPersons.add(Long.parseLong(delps[i]));
			}
			IrpProjectPersonExample example=new IrpProjectPersonExample();
			Criteria criteria=example.createCriteria();
			criteria.andPridEqualTo(projectId);
			criteria.andPersonidIn(this.delPersons);
			this.irpProjectPersonService.deleteByExample(example);
			findTeamPersons();
			findProjectPersons();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 从项目中删除成员
	 * @return
	 */
	public String delPersonApp(){
		try {
			irpProject=irpProjectService.findProjectById(projectId);
			cruserid=irpProject.getCruserid();
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			tokens=token;
			IrpUser irpuser = mobileAction.getlogin(token);
			this.loginUserId=irpuser.getUserid();
			//System.out.println(delPersonsStr);
			String[] delps=delPersonsStr.split(",");
			for(int i=0;i<delps.length;i++){
				this.delPersons.add(Long.parseLong(delps[i]));
			}
			IrpProjectPersonExample example=new IrpProjectPersonExample();
			Criteria criteria=example.createCriteria();
			criteria.andPridEqualTo(projectId);
			criteria.andPersonidIn(this.delPersons);
			this.irpProjectPersonService.deleteByExample(example);
			findTeamPersons();
			findProjectPersons();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 退出项目
	 * @return
	 */
	public String outOfProject(){
		try {
			Long perid=LoginUtil.getLoginUserId();
			IrpProjectPersonExample example=new IrpProjectPersonExample();
			Criteria criteria=example.createCriteria();
			criteria.andPersonidEqualTo(perid);
			criteria.andPridEqualTo(projectId);
			this.irpProjectPersonService.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public List<IrpUser> getTeamPersons() {
		return teamPersons;
	}

	public void setTeamPersons(List<IrpUser> teamPersons) {
		this.teamPersons = teamPersons;
	}

	public List<IrpProjectPerson> getProjectPersons() {
		return projectPersons;
	}

	public void setProjectPersons(List<IrpProjectPerson> projectPersons) {
		this.projectPersons = projectPersons;
	}

	public IrpUserService getUserService() {
		return userService;
	}

	public void setUserService(IrpUserService userService) {
		this.userService = userService;
	}

	public Map<Long, String> getTeamNameMap() {
		return teamNameMap;
	}

	public void setTeamNameMap(Map<Long, String> teamNameMap) {
		this.teamNameMap = teamNameMap;
	}

	public Map<Long, String> getPersonNameMap() {
		return personNameMap;
	}

	public void setPersonNameMap(Map<Long, String> personNameMap) {
		this.personNameMap = personNameMap;
	}

	public List<Long> getAddPersons() {
		return addPersons;
	}

	public void setAddPersons(List<Long> addPersons) {
		this.addPersons = addPersons;
	}

	public List<Long> getDelPersons() {
		return delPersons;
	}

	public void setDelPersons(List<Long> delPersons) {
		this.delPersons = delPersons;
	}


	
	
}
 