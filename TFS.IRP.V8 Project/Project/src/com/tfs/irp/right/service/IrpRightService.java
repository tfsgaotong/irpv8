package com.tfs.irp.right.service;

import java.util.List;
import java.util.Map;

import com.tfs.irp.user.entity.IrpUser;

public interface IrpRightService {
	
	/**
	 * 根据条件查询权限行
	 * @param _sType
	 * @param _operType
	 * @param operId
	 * @param _objType
	 * @param _objId
	 * @return
	 */
	Map<String, Object> findObjRowRight(String _sType, String _operType, Long operId, String _objType, Long _objId);
	
	/**
	 * 编辑权限
	 * @param _nObjId
	 * @param _sObjType
	 * @param _nOperId
	 * @param _sOperType
	 * @param _nOperTypeId
	 * @param _sCrUserId
	 */
	void rightEdit(Long _nObjId, String _sObjType, Long _nOperId, String _sOperType, Long _nOperTypeId);
	
	/**
	 * 删除权限
	 * @param _nObjId
	 * @param _sObjType
	 * @param _nOperId
	 * @param _sOperType
	 * @param _nOperTypeId
	 */
	void rightDelete(Long _nObjId, String _sObjType, Long _nOperId,
			String _sOperType, Long _nOperTypeId);
	
	/**
	 * 判断Id是否有权限
	 * @param _nObjId
	 * @param _sObjType
	 * @param _nOperId
	 * @param _sOperType
	 * @param _nOperTypeId
	 * @return
	 */
	boolean isExistOfId(Long _nObjId, String _sObjType, Long _nOperId,
			String _sOperType, Long _nOperTypeId);
	
	/**
	 * 判断Ids是否有权限
	 * @param _arrObjIds
	 * @param _sObjType
	 * @param _nOperId
	 * @param _sOperType
	 * @param _nOperTypeId
	 * @return
	 */
	boolean isExistOfIds(List<Long> _arrObjIds, String _sObjType,
			Long _nOperId, String _sOperType, Long _nOperTypeId);
	
}
