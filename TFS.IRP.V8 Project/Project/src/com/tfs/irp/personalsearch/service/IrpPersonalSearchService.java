package com.tfs.irp.personalsearch.service;

import java.util.List;

import com.tfs.irp.personalsearch.entity.IrpPersonalSearch;

public interface IrpPersonalSearchService {

	/**
	 * 根据用户ID查询个性化检索集合
	 * @param _nUserId
	 * @return
	 */
	List<IrpPersonalSearch> findPersonalSearchByUserId(long _nUserId);
	
	/**
	 * 添加编辑个性化检索
	 * @param _personalSearch
	 * @return 主键
	 */
	long addEditPersonalSearch(IrpPersonalSearch _personalSearch);
	
	/**
	 * 根据ID删除个性化检索
	 * @param _personalsearchid
	 * @return
	 */
	int deletePersonalSearchById(long _personalsearchid);
	
	/**
	 * 根据Id查询个性化检索
	 * @param psId
	 * @return
	 */
	IrpPersonalSearch findPersonalSearchById(long psId);
}
