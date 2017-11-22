package com.tfs.irp.group.service;

import java.util.List;
import java.util.Map;

import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.entity.IrpUsergroupLink;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.PageUtil;

public interface IrpGroupService {
	
	/**
	 * 根据父组织Id获得子组织对象集合
	 * @param _nParentId
	 * @return
	 */
	List<IrpGroup> findGroupsByParentId(Long _nParentId);
	
	/**
	 * 根据父组织Id获得子组织对象集合
	 * @param _nParentId
	 * @return
	 */
	List<IrpGroup> findGroupsByParentId(Long _nParentId, long _nGroupType);
	
	/**
	 * 根据父组织Id获得子组织个数
	 * @param _nParentId
	 * @return
	 */
	int findGroupsCountByParentId(Long _nParentId, String _sSearchWord, String _sSearchType);
	
	/**
	 * 根据父组织Id分页获得子组织对象集合
	 * @param _pageUtil
	 * @param _nParentId
	 * @return
	 */
	List<IrpGroup> findGroupsOfPageByParentId(PageUtil _pageUtil, Long _nParentId, String _sOrderBy, String _sSearchWord, String _sSearchType);
	
	/**
	 * 根据组织Id获得组织对象
	 * @param _nGroupId
	 * @return
	 */
	IrpGroup findGroupByGroupId(Long _nGroupId);
	
	/**
	 * 更新组织
	 * @param _irpGroup
	 */
	void groupEdit(IrpGroup _irpGroup, IrpUser _crUser);
	
	/**
	 * 根据组织Id分页获得组织下的用户对象
	 * @param _pageUtil
	 * @param _nGroupId
	 * @param _sOrderBy
	 * @return
	 */
	List<IrpUser> findUsersOfPageByGroupId(PageUtil _pageUtil, Long _nGroupId, String _sOrderBy, String _sSearchWord, String _sSearchType);
	List<IrpUser> findUsersOfPageByGroupId(PageUtil _pageUtil, Long _nGroupId, String _sOrderBy, String _sSearchWord, String _sSearchType,String sm);
	/**
	 * 根据组织Id获得所属组织用户的总数
	 * @param _nGroupId
	 * @return
	 */
	Integer findUsersCountByGroupId(Long _nGroupId, String _sSearchWord, String _sSearchType);
	
	/**
	 * 根据组织Id获得用户是否是管理员的Map
	 * @param _nGroupId
	 * @return
	 */
	Map<Long, String> findAdminUsersByGroupId(Long _nGroupId);
	
	/**
	 * 根据组织Id获得组织下的用户Id集合
	 * @param nGroupId
	 * @return
	 */
	String findGrpUserIdsByGroupId(Long nGroupId);
	
	/**
	 * 导入选择的用户到组织中
	 * @param _nGroupId
	 * @param _sUserIds
	 * @return
	 */
	int importGroupUserByGroupId(Long _nGroupId, String _sUserIds);
	
	/**
	 * 从指定的组织中移除选择的用户
	 * @param _nGroupId
	 * @param _sUserIds
	 * @return
	 */
	int removeUserGroup(Long _nGroupId, String _sUserIds);
	
	/**
	 * （设置/取消）用户是否是组织的组管理
	 * @param _nGroupId
	 * @param _sUserIds
	 * @param _nStatus
	 * @return
	 */
	int setGroupAdmin(Long _nGroupId, String _sUserIds, int _nStatus);
	
	/**
	 * 删除所选的组织
	 * @param _sGroupIds
	 * @return
	 */
	int deleteGroup(String _sGroupIds, Long _nParentId);
	
	/**
	 * 验证同一级下的组织名称是否重复
	 * @param _sGroupName
	 * @param _nGroupId
	 * @param _nParentId
	 * @return
	 */
	boolean checkGroupName(String _sGroupName, Long _nGroupId, Long _nParentId);
	
	/**
	 * 导入选择的组织到用户中
	 * @param _nUserId
	 * @param _sGroupIds
	 * @param _loginUtil
	 * @return
	 */
	int importGroupUserByUserId(Long _nUserId, String _sGroupIds);
	
	/**
	 * 根据用户ID获得所属组织ID集合
	 * @param _nUserId
	 * @return
	 */
	Map<Long, String> findGroupIdsByUserId(Long _nUserId);
	
	/**
	 * 根据组织ID和用户ID获得组织用户总个数
	 * @param _nGroupId
	 * @param _nUserId
	 * @return
	 */
	int findGrpUserCountByGroupIdAndUserId(Long _nGroupId, Long _nUserId);
	
	/**
	 * 根据用户ID获得组织Id的List集合
	 * @param _nUserId
	 * @param _nGroupType
	 * @return
	 */
	List<Long> findGroupIdListByUserId(Long _nUserId, Long _nGroupType);
	
	/**
	 * 获得排序的组织
	 * @param _nGroupId
	 * @param _nParentId
	 * @return
	 */
	List<IrpGroup> findOrderGroupsByParentId(Long _nGroupId, Long _nParentId);
	
	/**
	 * 获得组织类型获得组织根节点
	 * @param _nGroupType
	 * @return
	 */
	IrpGroup findRootGroupByGroupType(Long _nGroupType);
	
	/**
	 * 根据用户名获得用户个人组织
	 * @param _sUserId
	 * @return
	 */
	List<IrpGroup> findUserGroup(Long _sUserId);
	
	/**
	 * 根据用户名获得用户组织的根节点
	 * @param _sUserId
	 * @return
	 */
	IrpGroup findRootUserGroupByUserId(Long _sUserId);
	
	/**
	 * 编辑个人组织 groupid为null或0时进行添加，则修改个人组织
	 * @param group
	 * @param _sUserId
	 * @return
	 */
	boolean userGroupEdit(IrpGroup group, Long _sUserId);
	
	/**
	 * 根据组织ID获得组织的所有用户
	 * @param _nGroupId
	 * @return
	 */
	List<IrpUser> findUsersByGroupId(Long _nGroupId);
	/**
	 * 查询组织在所给的组织id集合里的对象
	 * @param _groupids
	 * @return
	 */
	List<IrpUsergroupLink> findUserByGroupIdList(List<Long> _groupids);
	
	/**
	 * 获得用户的个人组织个数
	 * @param _sUserId
	 * @return
	 */
	int findUserGroupCount(Long _sUserId); 
	/**
	 * 根据组名，创建人，以及类型获取组id
	 * @param _groupname
	 * @param _cruserid
	 * @param _grouptype
	 * @return
	 */
	Long findGroupIdByGroup(String _groupname,Long _cruserid,Long _grouptype);

	List<IrpGroup> findGroupsParentId(PageUtil _pageUtil, Long _nParentId,
			String _sOrderBy, String _sSearchWord, String _sSearchType);

	int findGroupsCountByGroupId(Long _nGroupId, String _sSearchWord,
			String _sSearchType);
	List<IrpGroup> ceshi(Long _printId);
	
	/**
	 * 根据组名找到相应的id   个人组织
	 * @param _groupname
	 * @param _parentid
	 * @param _grouptype
	 * @return
	 */
	Long findGroupIdByNPT(String _groupname,Long _parentid,Long _grouptype);
	/**
	 * 条件查询正常用户集合id
	 */
	List<Long> selectIdsByGrouplist(List<Long> _groupids);
	
	
	List<IrpGroup> currentGroup(Long groupId, List<IrpGroup> list, long _nRootId);
}
