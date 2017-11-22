package com.tfs.irp.expert.service;

import java.util.List;

import com.tfs.irp.expert.entity.IrpExpertClassifyLink;

public interface IrpExpertClassifyLinkService {
	/**
	 * 添加专家和分类的关系
	 * @param _nUserId
	 * @param _nClassifyId
	 * @return 执行结果
	 */
	public int importExpertClassifyByUserId(Long _nUserId, String _sClassifyIds);
	
	/**
	 * 根据用户ID和分类查询是否存在
	 * @param _nUserId
	 * @param _nClassifyId
	 * @return
	 */
	public int countExpertClassifyByUIdAndCId(Long _nUserId, Long _nClassifyId);
	
	/**
	 * 根据用户ID获得用户分类关系
	 * @param _nUserId
	 * @return
	 */
	List<IrpExpertClassifyLink> findCategorysByUserId(Long _nUserId);
}
