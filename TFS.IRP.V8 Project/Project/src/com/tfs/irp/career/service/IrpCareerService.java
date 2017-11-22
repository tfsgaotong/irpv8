package com.tfs.irp.career.service;

import java.util.List;

import com.tfs.irp.career.entity.IrpCareer;

public interface IrpCareerService {
	/**
	 * 根据用户ID获得用户的职业信息
	 * @param _nUserId
	 * @return
	 */
	List<IrpCareer> findCareerByUserId(long _nUserId);
	
	/**
	 * 编辑用户职业信息
	 * @param irpCareer
	 * @return
	 */
	long careerEdit(IrpCareer irpCareer);
	
	/**
	 * 根据ID删除职业信息
	 * @param _nCareerId
	 * @return
	 */
	int deleteCareerById(long _nCareerId);
	
	/**
	 * 根据ID获得职业信息
	 * @param _nCareerId
	 * @return
	 */
	IrpCareer findCareerById(long _nCareerId);
}
