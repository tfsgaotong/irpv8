package com.tfs.irp.tag.service;

import com.tfs.irp.tag.entity.IrpUserTag;

public interface IrpUserTagService {
	
	/**
	 * 编辑用户个人标配关系
	 * @param _irpUserTag
	 * @return
	 */
	long userTagEdit(IrpUserTag _irpUserTag);
	
	/**
	 * 根据用户ID和标签ID删除用户个人标签关系
	 * @param _nUserId
	 * @param _nTagId
	 * @return
	 */
	int deleteUserTagByUserIdAndTagId(long _nUserId, long _nTagId);
	
	/**
	 * 根据用户ID和标签ID获得用户个人标签个数
	 * @param _nUserId
	 * @param _nTagId
	 * @return
	 */
	int findUserTagCountByUserIdAndTagId(long _nUserId, long _nTagId);
}
