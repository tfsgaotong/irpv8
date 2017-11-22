package com.tfs.irp.managementoper.service;

import java.util.List;

import com.tfs.irp.managementoper.entity.IrpManagementOper;
import com.tfs.irp.user.entity.IrpUser;

public interface IrpManagementOperService {
	
	/**
	 * 根据parentid获得管理操作集合
	 * @param _nParentId
	 * @param roleType 
	 * @return
	 */
	List<IrpManagementOper> findManagementOpersByParentId(long _nParentId, Long roleType);
	
	/**
	 * 根据parentid获得管理操作个数
	 * @param _nParentId
	 * @return
	 */
	int findManagementOpersCountByParentId(long _nParentId,Long roleType);
	
	/**
	 * 带权限的根据parentid获得管理操作集合
	 * @param _nParentId
	 * @return
	 */
	List<IrpManagementOper> findRightManagementOpersByParentId(long _nParentId, long _nOperTypeId, Long roleType);
	
	/**
	 * 根据parentid获得管理操作个数
	 * @param _nParentId
	 * @return
	 */
	int findRightManagementOpersCountByParentId(long _nParentId, long _nOperTypeId, Long roleType);
	
	/**
	 * 根据parentid获得管理操作个数
	 * @param loginUser
	 * @param _nParentId
	 * @param _nOperTypeId
	 * @return
	 */
	int findRightManagementOpersCountByParentId(IrpUser loginUser, long _nParentId, long _nOperTypeId, Long roleType);
	
	/**
	 * 带权限的根据parentid获得管理操作集合
	 * @param loginUser
	 * @param _nParentId
	 * @param _nOperTypeId
	 * @param roleType 
	 * @return
	 */
	List<IrpManagementOper> findRightManagementOpersByParentId(IrpUser loginUser, long _nParentId, long _nOperTypeId, Long roleType);
}
