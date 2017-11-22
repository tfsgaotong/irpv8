package com.tfs.irp.role.web;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.role.entity.IrpRole;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;

public class RoleAction extends ActionSupport {
	private static final Logger logger = Logger.getLogger(RoleAction.class);
	
	private String roleName;
	
	private String roleIds;
	
	private long roleId;
	
	private IrpRoleService irpRoleService;
	
	private IrpUserService irpUserService;
	
	private int roleType;
	
	private List<IrpRole> irpRoles;
	
	private List<IrpUser> irpUsers;
	
	private IrpRole irpRole;
	
	private int pageNum=1;
	
	private int pageSize=10;
	
	private String orderField="";
	
	private String orderBy="";
	
	private String pageHTML;
	
	private int init=0;
	
	private String userIds;
	
	private String searchWord;
	
	private String searchType;

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
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

	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}

	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public IrpRoleService getIrpRoleService() {
		return irpRoleService;
	}

	public void setIrpRoleService(IrpRoleService irpRoleService) {
		this.irpRoleService = irpRoleService;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public List<IrpRole> getIrpRoles() {
		return irpRoles;
	}

	public void setIrpRoles(List<IrpRole> irpRoles) {
		this.irpRoles = irpRoles;
	}

	public IrpRole getIrpRole() {
		return irpRole;
	}

	public void setIrpRole(IrpRole irpRole) {
		this.irpRole = irpRole;
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

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}
	
	public String roleTabs() {
		return SUCCESS;
	}
	
	//显示角色列表
	public String roleList() {
		int nDataCount = irpRoleService.findRolesCountByRoleType(roleType, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "roleid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpRoles = irpRoleService.findRolesOfPageByRoleType(pageUtil, roleType, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	//编辑角色信息
	public String roleEdit() {
		if(roleId>0){
			irpRole = irpRoleService.findRoleByRoleId(roleId);
			if(irpRole==null){
				return ERROR;
			}
		}else{
			irpRole = new IrpRole();
			irpRole.setRoleid(new Long(0));
		}
		return SUCCESS;
	}
	
	//处理编辑的角色信息
	public void roleEditDowith() {
		if(irpRole.getRoleid()==null){
			irpRole.setRoleid(new Long(0));
		}
		int nResult = irpRoleService.roleEdit(irpRole);
		ActionUtil.writer(nResult+"");
	}
	
	/**
	 * 检查角色名称是否存在
	 */
	public void checkRoleName() {
		boolean bResult = irpRoleService.checkRoleName(roleName,roleId);
		ActionUtil.writer(bResult+"");
	}
	
	/**
	 * 显示角色中用户列表
	 * @return
	 */
	public String roleUserList() {
		int nDataCount = irpRoleService.findUsersCountByRoleId(roleId, searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "userid desc";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpUsers = irpRoleService.findUsersOfPageByRoleId(pageUtil, roleId, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("roleUserPage(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 导入组织用户列表
	 * @return
	 */
	public String importUserList() {
		//判断是否是初始化，获得当前组织Id下的所有用户Ids
		if(init==1){
			userIds = irpRoleService.findRoleUserIdsByRoleId(roleId);
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
		this.pageHTML = pageUtil.getPageHtml("import_page(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 处理导入用户
	 */
	public void importUserDowith() {
		int nRows = irpRoleService.importRoleUserByRoleId(roleId, userIds);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 处理组织中移除用户
	 */
	public void removeUserRoleDowith(){
		int nRows = irpRoleService.removeUserRole(roleId, userIds);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 处理要删除的角色
	 */
	public void deleteRoleDowith() {
		int nRows = irpRoleService.deleteRole(roleIds);
		ActionUtil.writer(String.valueOf(nRows));
	}
}
