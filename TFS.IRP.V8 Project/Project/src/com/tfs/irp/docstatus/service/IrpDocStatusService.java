package com.tfs.irp.docstatus.service;

import java.util.HashMap;
import java.util.List;

import com.tfs.irp.docstatus.entity.IrpDocstatus;
import com.tfs.irp.util.PageUtil;

public interface IrpDocStatusService {
	/**
	 * 查询所有状态用于下拉选择
	 * @return
	 */
	public List<IrpDocstatus> findAllStatus(); 
	/**
	 * 添加
	 * @param docstatus
	 * @param _session
	 */
	public int insertDocstatus(IrpDocstatus docstatus);
	/**
	 * 删除
	 * @param statusid
	 * @return
	 */
	public int deleteDocustatusByStatusId(Long statusid);
	/**
	 * 修改
	 * @param docstatus
	 * @return
	 */
	public int updateDocustatus(IrpDocstatus docstatus);
	
	/**
	 * 查询单个
	 * @param docstatus
	 * @return
	 */
	public IrpDocstatus finDocstatusByStatusId(Long docstatus);
	/**
	 * 列表显示所有知识状态
	 * @param pageUtil
	 * @param map
	 * @param sOrderByClause
	 * @return
	 */
	public List<IrpDocstatus> findAllStatusinfo(PageUtil pageUtil,HashMap<String,Object> map,String sOrderByClause); 
	/**
	 * 根据名称查询状态
	 * @param docstatusname
	 * @return
	 */
	public boolean findStatusBySname(String docstatusname);
	/**
	 * 根据条件查询数量
	 * @param map
	 * @return
	 */
	public int selectCountByMap(HashMap<String,Object> map);
}
