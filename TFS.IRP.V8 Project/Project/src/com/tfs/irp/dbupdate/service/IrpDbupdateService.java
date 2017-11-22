package com.tfs.irp.dbupdate.service;

import java.util.List;

import com.tfs.irp.dbupdate.entity.IrpDbupdate;
import com.tfs.irp.util.PageUtil;

public interface IrpDbupdateService {

	/**
	 * 查询所有分页信息
	 * @param pageUtil
	 * @param _oOrderby
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	List<IrpDbupdate> findAllIrpDbupdateTypePage(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType);
	/**
	 * 查询所有分页信息个数
	 * @param _oOrderby
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	int findAllIrpDbupdateTypePageCount( String _sSearchWord, String _sSearchType);
}
