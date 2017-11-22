package com.tfs.irp.education.service;

import java.util.List;

import com.tfs.irp.education.entity.IrpEducation;

public interface IrpEducationService {
	
	/**
	 * 根据用户ID获得用户的教育信息
	 * @param _nUserId
	 * @return
	 */
	List<IrpEducation> findEducationByUserId(long _nUserId);
	
	/**
	 * 编辑用户教育信息
	 * @param irpEducation
	 * @return
	 */
	long educationEdit(IrpEducation irpEducation);
	
	/**
	 * 根据ID删除教育信息
	 * @param _nEducationId
	 * @return
	 */
	int deleteEduById(long _nEducationId);
	
	/**
	 * 根据ID获得教育信息
	 * @param _nEducationId
	 * @return
	 */
	IrpEducation findEducationById(long _nEducationId);

}
