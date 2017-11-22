package com.tfs.irp.group.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.chat.entity.IrpChat;
import com.tfs.irp.chat.service.IrpChatService;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.entity.IrpGroupInfo;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.valuesetting.entity.IrpValueSetting;
import com.tfs.irp.valuesetting.service.IrpValueSettingService;

public class GroupAction extends ActionSupport {
	private static final Logger logger = Logger.getLogger(GroupAction.class);
	
	private IrpGroupService irpGroupService;
	
	private IrpUserService irpUserService;
	
	private IrpUserValueLinkService irpUserValueLinkService;
	
	private IrpValueSettingService irpValueSettingService;
	private List<IrpGroupInfo> listIrpGroupInfos;
	
	public List<IrpGroupInfo> getListIrpGroupInfos() {
		return listIrpGroupInfos;
	}

	public void setListIrpGroupInfos(List<IrpGroupInfo> listIrpGroupInfos) {
		this.listIrpGroupInfos = listIrpGroupInfos;
	}
	private long id;
	
	private long groupId;
	
	private long userId;
	
	private String groupName;
	
	private String groupNames;
	
	private String groupIds;
	
	private long groupType;
	
	private long parentId=1;
	
	private List<IrpGroup> irpGroups;
	
	private List<IrpUser> irpUsers;
	
	private Map<Long, String> isAdminUsers;
	
	public Map<Long, List<IrpUser>> getIrpUsersByGroupid() {
		return irpUsersByGroupid;
	}

	public void setIrpUsersByGroupid(Map<Long, List<IrpUser>> irpUsersByGroupid) {
		this.irpUsersByGroupid = irpUsersByGroupid;
	}
	private Map<Long, List<IrpUser>> irpUsersByGroupid;
	
	private IrpGroup irpGroup;
	
	private int pageNum=1;
	
	private int pageSize=10;
	
	private String orderField="";
	
	private String orderBy="";
	
	private String pageHTML;

	private String searchWord;
	
	private String searchType;
	
	private String userIds;
	
	private String userNames;
	
	private int init=0;
	
	private int maxAddUserNum;
	
	private boolean ismaxamount=true;//是否查询最大分组认识标示
	
	private List<IrpUser> userlist;
	
	private int pageNumSysfriend = 1;
	
	private int sysfriendnum;
	
	private Long loginonlineid;
	
	private IrpChatService irpChatService;
	
	private Long groupid;
	
	private List allUseridByFocusUserId;
	
	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}

	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}
	private IrpMicroblogFocusService irpMicroblogFocusService;


	public List getAllUseridByFocusUserId() {
		return allUseridByFocusUserId;
	}

	public void setAllUseridByFocusUserId(List allUseridByFocusUserId) {
		this.allUseridByFocusUserId = allUseridByFocusUserId;
	}

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public IrpChatService getIrpChatService() {
		return irpChatService;
	}

	public void setIrpChatService(IrpChatService irpChatService) {
		this.irpChatService = irpChatService;
	}

	public int getSysfriendnum() {
		return sysfriendnum;
	}

	public Long getLoginonlineid() {
		return loginonlineid;
	}

	public void setLoginonlineid(Long loginonlineid) {
		this.loginonlineid = loginonlineid;
	}

	public void setSysfriendnum(int sysfriendnum) {
		this.sysfriendnum = sysfriendnum;
	}

	public int getPageNumSysfriend() {
		return pageNumSysfriend;
	}

	public void setPageNumSysfriend(int pageNumSysfriend) {
		this.pageNumSysfriend = pageNumSysfriend;
	}

	public List<IrpUser> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<IrpUser> userlist) {
		this.userlist = userlist;
	}

	public boolean isIsmaxamount() {
		return ismaxamount;
	}

	public void setIsmaxamount(boolean ismaxamount) {
		this.ismaxamount = ismaxamount;
	}

	public IrpUserValueLinkService getIrpUserValueLinkService() {
		return irpUserValueLinkService;
	}

	public void setIrpUserValueLinkService(
			IrpUserValueLinkService irpUserValueLinkService) {
		this.irpUserValueLinkService = irpUserValueLinkService;
	}

	public IrpValueSettingService getIrpValueSettingService() {
		return irpValueSettingService;
	}

	public void setIrpValueSettingService(
			IrpValueSettingService irpValueSettingService) {
		this.irpValueSettingService = irpValueSettingService;
	}

	public int getMaxAddUserNum() {
		return maxAddUserNum;
	}

	public void setMaxAddUserNum(int maxAddUserNum) {
		this.maxAddUserNum = maxAddUserNum;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	
	public String getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(String groupNames) {
		this.groupNames = groupNames;
	}

	public long getGroupType() {
		return groupType;
	}

	public void setGroupType(long groupType) {
		this.groupType = groupType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public int getInit() {
		return init;
	}

	public void setInit(int init) {
		this.init = init;
	}
	
	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord.trim();
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public IrpGroup getIrpGroup() {
		return irpGroup;
	}

	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}

	public Map<Long, String> getIsAdminUsers() {
		return isAdminUsers;
	}

	public void setIsAdminUsers(Map<Long, String> isAdminUsers) {
		this.isAdminUsers = isAdminUsers;
	}

	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}

	public void setIrpGroup(IrpGroup irpGroup) {
		this.irpGroup = irpGroup;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
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

	public List<IrpGroup> getIrpGroups() {
		return irpGroups;
	}

	public void setIrpGroups(List<IrpGroup> irpGroups) {
		this.irpGroups = irpGroups;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IrpGroupService getIrpGroupService() {
		return irpGroupService;
	}

	public void setIrpGroupService(IrpGroupService irpGroupService) {
		this.irpGroupService = irpGroupService;
	}
	
	
	private int ids=0;
	
	public int getIds() {
		return ids;
	}

	public void setIds(int ids) {
		this.ids = ids;
	}

	public void findTreeNode(){
		List<IrpGroup> groups = irpGroupService.findGroupsByParentId(this.id, this.groupType);
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < groups.size(); i++) {
			IrpGroup group = groups.get(i);
			if(group==null){
				continue;
			}
			Map<String, Object> item = new HashMap<String, Object>();
			Long nId = group.getGroupid();
			item.put("id", group.getGroupid());
			item.put("text", group.getGdesc());
			Map<String, Long> param = new HashMap<String, Long>();
			param.put("groupType", group.getGrouptype());
			item.put("attributes", param);
			int nCount = irpGroupService.findGroupsCountByParentId(nId, "", "");
			if(nCount>0){
				item.put("state", "closed");
				ids=0;
			}else{
				item.put("state", "open");
				ids=1;
			}
			if(userId>0){
				nCount = irpGroupService.findGrpUserCountByGroupIdAndUserId(group.getGroupid(), userId);

			}
			treeNode.add(item);
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}
	public void findTreeNode1(){
		List<IrpGroup> groups = irpGroupService.findGroupsByParentId(this.id, this.groupType);
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < groups.size(); i++) {
			
			IrpGroup group = groups.get(i);
			if(group==null){
				continue;
			}
			Map<String, Object> item = new HashMap<String, Object>();
			Long nId = group.getGroupid();
			Long nId1 = group.getParentid();
			if(nId1!=2L){
				if(group.getGroupid()!=2){
			item.put("id", group.getGroupid());
			item.put("text", group.getGdesc());
			Map<String, Long> param = new HashMap<String, Long>();
			param.put("groupType", group.getGrouptype());
			item.put("attributes", param);
			int nCount = irpGroupService.findGroupsCountByParentId(nId, "", "");
			if(nCount>0){
				item.put("state", "closed");
				ids=0;
			}else{
				item.put("state", "open");
				ids=1;
			}
			if(userId>0){
				nCount = irpGroupService.findGrpUserCountByGroupIdAndUserId(group.getGroupid(), userId);
				
			}
			treeNode.add(item);}}
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}
	
	public String groupSystemList() {
		int nDataCount = irpGroupService.findGroupsCountByParentId(this.parentId, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "grouporder asc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpGroups = irpGroupService.findGroupsOfPageByParentId(pageUtil, parentId, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	public String groupList() {
		return SUCCESS;
	}
	
	/**
	 * 编辑组织信息
	 * @return
	 */
	public String groupEdit() {
		if(groupId==0){
			irpGroup = new IrpGroup();
			irpGroup.setGroupid(new Long(0));
			irpGroup.setParentid(parentId);
			irpGroup.setGrouptype(this.groupType);
		}else{
			irpGroup = irpGroupService.findGroupByGroupId(groupId);
			if(irpGroup==null){
				return ERROR;
			}
		}
		//获得同级组织进行前台排序显示
		irpGroups = irpGroupService.findOrderGroupsByParentId(groupId ,parentId);
		return SUCCESS;
	}
	
	public void groupEditDowith() {
		if(irpGroup.getGroupid()==null){
			irpGroup.setGroupid(new Long(0));
		}
		irpGroupService.groupEdit(irpGroup, LoginUtil.getLoginUser());
		ActionUtil.writer("1");
	}
	
	public String groupUserList() {
		int nDataCount = irpGroupService.findUsersCountByGroupId(groupId, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpUsers = irpGroupService.findUsersOfPageByGroupId(pageUtil, groupId, sOrderByClause, searchWord, searchType);
		isAdminUsers = irpGroupService.findAdminUsersByGroupId(groupId);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 导入组织用户列表
	 * @return
	 */
	public String importUserList() {
		//判断是否是初始化，获得当前组织Id下的所有用户Ids
		if(init==1){
			userIds = irpGroupService.findGrpUserIdsByGroupId(new Long(groupId));
		}
		
		int nDataCount = irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_REG , searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpUsers = irpUserService.findUsersOfPageByStatus(pageUtil, IrpUser.USER_STATUS_REG, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("import_page(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 处理导入用户
	 */
	public void importUserDowith() {
		int nRows = irpGroupService.importGroupUserByGroupId(groupId, userIds);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 处理组织中移除用户
	 */
	public void removeUserGroupDowith(){
		int nRows = irpGroupService.removeUserGroup(groupId, userIds);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 设置用户为组织管理员
	 */
	public void setGroupAdminDowith(){
		int nRows = irpGroupService.setGroupAdmin(groupId, userIds, 1);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 取消用户的组织管理员
	 */
	public void cancelGroupAdminDowith(){
		int nRows = irpGroupService.setGroupAdmin(groupId, userIds, 0);
		ActionUtil.writer(String.valueOf(nRows));
	}
	public String importUserOneAudit(){
		userIds=userIds;
		try {
			userNames=new String(userNames.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除组织
	 */
	public void deleteGroupDowith() {
		int nRows = irpGroupService.deleteGroup(groupIds, parentId);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 检查角色名称是否存在
	 */
	public void checkGroupName() {
		boolean bResult = irpGroupService.checkGroupName(groupName, groupId, parentId);
		ActionUtil.writer(bResult+"");
	}
	
	/**
	 * 导入组织
	 * @return
	 */
	public String importGroup() {
		return SUCCESS;
	}
	
	/**
	 * 导入用户
	 * @return
	 */
	public String importUser() {
		return SUCCESS;
	}
	
	/**
	 * 获得用户个人组织
	 * @return
	 */
	public String userGroup() {
		Long nUserId = LoginUtil.getLoginUserId();
		irpGroups = irpGroupService.findUserGroup(nUserId);
		IrpGroup rootGroup = irpGroupService.findRootUserGroupByUserId(nUserId);
		if(rootGroup!=null){
			this.parentId = rootGroup.getGroupid();
		}
		return SUCCESS;
	}
	
	/**
	 * 编辑个人组织
	 */
	public void userGroupEditDowith() {
		String sStatus = "0";
		IrpUser loginUser = LoginUtil.getLoginUser();
		if(irpGroup.getGroupid()==null || irpGroup.getGroupid()<=0L){
			//获得当前用户的组织数
			int nUserScore = irpUserValueLinkService.sumScoreByUserid(loginUser.getUserid());
			//根据分数获得用户级别
			IrpValueSetting valueSet = irpValueSettingService.irpValueSettingOfGroupChannel(nUserScore);
			//获得当前用户创建栏目个数
			int nGroupCount = irpGroupService.findUserGroupCount(loginUser.getUserid());
			if(nGroupCount>=valueSet.getCrgroupnum()){
				sStatus = "-1";
			}else{
				boolean success = irpGroupService.userGroupEdit(irpGroup, loginUser.getUserid());
				sStatus = success?"1":"0";
			}
		}else{
			boolean success = irpGroupService.userGroupEdit(irpGroup, loginUser.getUserid());
			sStatus = success?"1":"0";
		}
		
		ActionUtil.writer(sStatus);
	}
	
	/**
	 * 个人组织用户列表
	 * @return
	 */
	public String userGroupList() {
		irpUsers = irpGroupService.findUsersByGroupId(groupId);
		return SUCCESS;
	}
	
	/**
	 * 前台显示选择的用户
	 * @return
	 */
	public String selectUser() {
		if(init==1){
			this.userIds = irpGroupService.findGrpUserIdsByGroupId(groupId);
		}
		if(ismaxamount){
			this.maxAddUserNum = SysConfigUtil.getSysConfigNumValue("USERGROUP_ADD_USER_NUM");
		}
		int nDataCount = irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_REG, searchWord, searchType);
	    PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpUsers = irpUserService.findUsersOfPageByStatus(pageUtil, IrpUser.USER_STATUS_REG, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 手机端选择用户
	 * @mobile
	 * @return
	 */
	private int pageusernums;
	public int getPageusernums() {
		return pageusernums;
	}
	public void setPageusernums(int pageusernums) {
		this.pageusernums = pageusernums;
	}
	public void selectUserByMobile(){
		String sOrderByClause = "userid desc";
		int nDataCount = irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_REG, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(pageusernums,10, nDataCount);
		List<IrpUser> list = irpUserService.findUsersOfPageByStatus(pageUtil, IrpUser.USER_STATUS_REG, sOrderByClause, searchWord, searchType);
		List mobilelist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			IrpUser irpUsers = list.get(i);
			mobilelist.add("{\"showname\":\""+getShowPageViewNameByUserId(irpUsers.getUserid())+"\"");
			mobilelist.add("\"userid\":\""+irpUsers.getUserid()+"\"");
			mobilelist.add("\"userpic\":\""+irpUsers.getUserpic()+"\"");
			mobilelist.add("\"sex\":\""+irpUsers.getSex()+"\"");
			mobilelist.add("\"username\":\""+irpUsers.getUsername()+"\"");
			mobilelist.add("\"expertinfo\":\""+irpUsers.getExpertintro()+"\"}");
			
		}
		mobilelist.add("{\"mobilenum\":\""+nDataCount+"\"}");
		
		ActionUtil.writer(mobilelist.toString());
	}
    /**
     * 根据id获得用户在页面显示的名字
     * @param _userid
     * @return
     */
    public String getShowPageViewNameByUserId(Long _userid){
    	return this.irpUserService.findShowNameByUserid(_userid);
    }
	
	/**
	 * (个人组织)
	 * 发送微知选择分组时  所有的分组项
	 * @return
	 */
	public String findGroupMicroPersonal(){
		irpGroups =  this.irpGroupService.findUserGroup(LoginUtil.getLoginUserId());
		return SUCCESS;
	}
	/**
	 * select system users
	 * 选择系统用户
	 * @return
	 */
	public String selectUserChat(){
		
		sysfriendnum = this.irpUserService.findUsersCountByStatus(IrpUser.USER_STATUS_REG);
		
		PageUtil pageutil = new PageUtil(pageNumSysfriend, 10, sysfriendnum);
		
		loginonlineid = LoginUtil.getLoginUserId();
		
		this.userlist = this.irpUserService.findUsersOfPageByStatus(pageutil, IrpUser.USER_STATUS_REG, null);

		return 	SUCCESS;
	}
	public boolean boolUserFUser(Long _userid){
		  boolean falg = false;
		  List  listHashMaplist = this.irpMicroblogFocusService.selectUseridByFocuserId(LoginUtil.getLoginUserId());
		  for(int i =0;i<listHashMaplist.size();i++){
			  Map map = (Map) listHashMaplist.get(i);
			  if (_userid==Long.parseLong(map.values().toString().replace("[","").replace("]",""))) {
				  falg = true;
				  break;
			  }
		  } 
		return falg;	
	}
	/**
	 * 获得个人分组
	 * @return
	 */
	private String grouplistjson;
	public String getGrouplistjson() {
		return grouplistjson;
	}

	public void setGrouplistjson(String grouplistjson) {
		this.grouplistjson = grouplistjson;
	}

	public String findMyPeronalGroup(){
		
		Long groupid = this.irpGroupService.findGroupIdByNPT(LoginUtil.getLoginUserId()+"", IrpGroup.GROUP_ROOTID_PRIVATE, IrpGroup.GROUP_TYPE_PRIVATE);
		if (groupid!=null) {
			List<IrpGroup> list = this.irpGroupService.findGroupsByParentId(groupid);
			List<Map> glist = null;
			if (list!=null && list.size()>0) {
				Iterator iterator = list.iterator();
				glist = new ArrayList();
				while (iterator.hasNext()) {
					Map map = new HashMap();
					IrpGroup group = (IrpGroup) iterator.next();
					
					map.put("name",group.getGroupname()+"("+this.irpGroupService.findUsersCountByGroupId(group.getGroupid(), null, null)+"人)");
					map.put("nametitle", group.getGroupname());
					map.put("t", group.getGdesc());
					map.put("idkey", "0");
					map.put("idvalue", group.getGroupid());
					map.put("id", 1);
					findAddGListById(group.getGroupid(),glist);
					map.put("pId", 0l);
					glist.add(map);
				}
			}
			
			grouplistjson = JsonUtil.list2json(glist);
		}
		return SUCCESS;
	}
	
	public void findAddGListById(Long _parentid,List<Map> _glist){
	//	List<IrpGroup> list = this.irpGroupService.findGroupsByParentId(_parentid);
		List<IrpUser> listuser = this.irpGroupService.findUsersByGroupId(_parentid);
		
		if (listuser.size()>0) {
			Iterator nextpent = listuser.iterator();
			while (nextpent.hasNext()) {
				IrpUser irpuser = (IrpUser) nextpent.next();
				Map map = new HashMap();
				String showname =  getShowPageViewNameByUserId(irpuser.getUserid());
				map.put("name",showname);
				map.put("nametitle",showname);
				map.put("t", showname);
				map.put("idkey", "1");
				map.put("idvalue", irpuser.getUserid());
				map.put("id", 2l);
				map.put("pId", 1l);
				_glist.add(map);
			}
		}
	}
	/**
	 * 弹出组内讨论框
	 * @return
	 */
	private List<IrpChat> chatlist;
	public List<IrpChat> getChatlist() {
		return chatlist;
	}

	public void setChatlist(List<IrpChat> chatlist) {
		this.chatlist = chatlist;
	}

	public static Logger getLogger() {
		return logger;
	}
	private Integer chatbyuserpagesgroup = 10;
	
	private Integer chatgrouptotallinks;
	

	public Integer getChatbyuserpagesgroup() {
		return chatbyuserpagesgroup;
	}

	public void setChatbyuserpagesgroup(Integer chatbyuserpagesgroup) {
		this.chatbyuserpagesgroup = chatbyuserpagesgroup;
	}

	public Integer getChatgrouptotallinks() {
		return chatgrouptotallinks;
	}

	public void setChatgrouptotallinks(Integer chatgrouptotallinks) {
		this.chatgrouptotallinks = chatgrouptotallinks;
	}

	public String getGroupMessage(){
		
		chatgrouptotallinks = irpChatService.getAllChatContentByGroupIdNum(IrpChat.CHATOBJGROUP, IrpChat.STATUSNORMAL, groupid);
		
		PageUtil pageutil = new PageUtil(1, chatbyuserpagesgroup, chatgrouptotallinks);
		
		List<IrpChat> chatlists = irpChatService.getAllChatContentByGroupId(IrpChat.CHATOBJGROUP, IrpChat.STATUSNORMAL, groupid,"chatid desc", pageutil);
		if (chatlists.size()>0 &&  chatlists!=null) {
			List<IrpChat>  chatlista = new ArrayList();
			for (int i = chatlists.size()-1; i >=0; i--) {
			   //chatlist.add();
			   chatlista.add(chatlists.get(i));
			}
			this.chatlist = chatlista;
		}
		return SUCCESS;
	}
	
	public String userListByGroup() {
		int nDataCount = irpGroupService.findUsersCountByGroupId(groupId, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpUsers = irpGroupService.findUsersOfPageByGroupId(pageUtil, groupId, sOrderByClause, searchWord, searchType);
		isAdminUsers = irpGroupService.findAdminUsersByGroupId(groupId);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
}