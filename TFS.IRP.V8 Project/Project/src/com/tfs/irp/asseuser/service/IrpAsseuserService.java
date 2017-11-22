package com.tfs.irp.asseuser.service;

import java.util.List;

import com.tfs.irp.asseuser.entity.IrpAsseuser;
import com.tfs.irp.asseuser.entity.IrpAsseuserExample;

public interface IrpAsseuserService {
	/**
	 * 添加
	 * @param example
	 * @throws Exception
	 */
	public void add(IrpAsseuser _asseuser) throws Exception;
	/**
	 * 通过申请id删除
	 * @param example
	 * @throws Exception
	 */
	public void deleteByApplyid(IrpAsseuserExample example)throws Exception;
	/**
	 * 通过人员id查询
	 * @param example
	 * @throws Exception
	 */
	public List<IrpAsseuser> selectByuserid(IrpAsseuserExample example)throws Exception;
	/**
	 * 通过申请单号查询
	 * @param example
	 * @throws Exception
	 */
	public List<IrpAsseuser> selectByApplyid(IrpAsseuserExample example)throws Exception;
	
	/**
	 * 根据会议id和状态查询参会人员
	 * @param _assid
	 * @param _status
	 * @return
	 */
	public List<IrpAsseuser> getIrpAsseuserByAssId(Long _assid,Integer _status);


}
