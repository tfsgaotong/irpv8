package com.tfs.irp.role.service;

import java.util.List;

import com.tfs.irp.role.entity.IrpRole;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.PageUtil;

public interface IrpRoleService {
	
	/**
	 * 根据角色类型，分页获得角色对象集合
	 * @param _pageUtil
	 * @param _nRoleType
	 * @param _sOrderBy
	 * @return
	 */
	List<IrpRole> findRolesOfPageByRoleType(PageUtil _pageUtil, int _nRoleType,	String _sOrderBy, String _sSearchWord, String _sSearchType);
	/**
	 * 根据角色类型，获得角色的总个数
	 * @param _nRoleType
	 * @return
	 */
	int findRolesCountByRoleType(int _nRoleType, String _sSearchWord, String _sSearchType);
	
	/**
	 * 根据角色ID获得角色对象
	 * @param _nRoleId
	 * @return
	 */
	IrpRole findRoleByRoleId(Long _nRoleId);
	
	/**
	 * 编辑角色属性
	 * @param _irpRole
	 */
	int roleEdit(IrpRole _irpRole);
	
	/**
	 * 检查角色名是否存在
	 * @param _sRoleName
	 * @return
	 */
	boolean checkRoleName(String _sRoleName, Long roleId);
	
	/**
	 * 根据角色Id分页获得角色下的用户对象
	 * @param _pageUtil
	 * @param _nRoleId
	 * @param _sOrderBy
	 * @return
	 */
	List<IrpUser> findUsersOfPageByRoleId(PageUtil _pageUtil, Long _nRoleId, String _sOrderBy, String _sSearchWord, String _sSearchType);
	
	/**
	 * 根据角色Id获得所属角色用户的总数
	 * @param _nRoleId
	 * @return
	 */
	Integer findUsersCountByRoleId(Long _nRoleId, String _sSearchWord, String _sSearchType);
	/**
	 * 根据专家分类获取专家的总数
	 * @param _nRoleId
	 * @return
	 */
	Integer findExpertCountByRoleId(Long _nCategoryId,Long _nRoleId, String _sSearchWord, String _sSearchType);
	
	/**
	 * 根据角色Id获得角色下的用户Id集合
	 * @param _nRoleId
	 * @return
	 */
	String findRoleUserIdsByRoleId(Long _nRoleId);
	
	/**
	 * 导入选择的用户到角色中
	 * @param _nRoleId
	 * @param _sUserIds
	 * @param _loginUtil
	 * @return
	 */
	int importRoleUserByRoleId(Long _nRoleId, String _sUserIds);
	
	/**
	 * 从指定的角色中移除选择的用户
	 * @param _nRoleId
	 * @param _sUserIds
	 * @return
	 */
	int removeUserRole(Long _nRoleId, String _sUserIds);
	
	/**
	 * 删除选择的角色
	 * @param _sRoleIds
	 * @return
	 */
	int deleteRole(String _sRoleIds);
	
	/**
	 * 根据角色类型获得角色对象集合
	 * @param _nRoleType
	 * @return
	 */
	List<IrpRole> findRolesByRoleType(int _nRoleType);
	
	/**
	 * 导入选择的角色到用户中
	 * @param _nUserId
	 * @param _sRoleIds
	 * @param _loginUtil
	 * @return
	 */
	int importRoleUserByUserId(Long _nUserId, String _sRoleIds);
	
	/**
	 * 根据用户ID获得角色ID集合
	 * @param _nUserId
	 * @return
	 */
	String findRoleIdsByUserId(Long _nUserId);
	
	/**
	 * 根据用户ID获得角色ID的List集合
	 * @param _nUserId
	 * @return
	 */
	List<Long> findRoleIdListByUserId(Long _nUserId);
	
	/**
	 * 根据角色ID获得用户集合
	 * @param _nRoleId
	 * @return
	 */
	List<IrpUser> findUsersOfPageByRoleId(Long _nRoleId);
	/**
	 * 分页显示专家列表
	 * @return
	 */
	List<IrpUser> findUsersOfPageByRoleId(Long _nRoleId,PageUtil pageUtil,String orderBy);
	/**
	 * 根据专家名字获取专家集合
	 * @return
	 */
	List<IrpUser> findUsersOfPageByRoleIdAndName(Long _nRoleId,PageUtil pageUtil,String orderBy,String expertName);
	
}
