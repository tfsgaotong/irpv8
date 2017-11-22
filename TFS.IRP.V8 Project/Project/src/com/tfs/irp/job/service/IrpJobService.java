package com.tfs.irp.job.service;

import java.util.List;

import com.tfs.irp.job.entity.IrpJob;
import com.tfs.irp.util.PageUtil;

public interface IrpJobService {
	/**
	 * 根据检索条件获得检索总条数
	 * 
	 * @param searchWord
	 * @param searchType
	 * @return
	 */
	int findJobCountBySearch(String searchWord, String searchType);

	/**
	 * 根据检索内容分页获得计划调度集合
	 * 
	 * @param pageUtil
	 * @param searchWord
	 * @param searchType
	 * @param _sOrderBy
	 * @return
	 */
	List<IrpJob> findJobsBySearch(PageUtil pageUtil, String searchWord,
			String searchType, String _sOrderBy);
	
	List<IrpJob> findAllJobsBySearch();

	/**
	 * 根据ID获得任务对象
	 * 
	 * @param _nJobId
	 * @return
	 */
	IrpJob findJobByJobId(Long _nJobId);

	/**
	 * 编辑任务
	 * 
	 * @param irpJob
	 */
	void jobEdit(IrpJob irpJob);

	/**
	 * 删除任务
	 * 
	 * @param _sJobIds
	 * @return
	 */
	int deleteJobByJobIds(String _sJobIds);
}
