package com.tfs.irp.asseroomapply.service;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.asseroomapply.entity.IrpAsseroomapply;
import com.tfs.irp.asseroomapply.entity.IrpAsseroomapplyExample;
import com.tfs.irp.util.PageUtil;

public interface IrpAsseroomapplyService {
	/**
	 * 查询所有会议申请
	 * @return
	 * @throws Exception
	 */
	public List<IrpAsseroomapply> queryAllBugForList()throws Exception;
		
	
	/**
	 * 添加会议申请
	 * @param asseroomapply
	 * @return
	 * @throws Exception
	 */
	public Long addApply(IrpAsseroomapply asseroomapply)throws Exception;
	
	
	/**
	 * 查询会议申请
	 * @param Example
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<IrpAsseroomapply> querySbForPage(IrpAsseroomapplyExample Example)throws Exception;

	/**
	 * 分页查询会议申请
	 * @param Example
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<IrpAsseroomapply> querySbForPage(IrpAsseroomapplyExample Example,PageUtil page)throws Exception;
	
	/**
	 * 根据example获得数据数量
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int getDataCount(IrpAsseroomapplyExample example)throws Exception;
	/**
	 * 根据主键查询
	 * @param Sbid
	 * @return
	 * @throws SQLException
	 */
	public IrpAsseroomapply selectByPrimaryKey(Long Sbid) throws SQLException;
	
	/**
	 * 根据example查询IrpAsseroomapply集合
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public List<IrpAsseroomapply> selectByExample(IrpAsseroomapplyExample example) throws SQLException;
	
	/**
	 * 根据example更新IrpAsseroomapply
	 * @param asseroom
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public int updateByExample(IrpAsseroomapply asseroomapply, IrpAsseroomapplyExample example) throws SQLException;
	
	/**
	 * 根据example更新IrpAsseroomapply
	 * @param asseroom
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public int updateByExampleSelective(IrpAsseroomapply asseroomapply, IrpAsseroomapplyExample example) throws SQLException;
	
	/**
	 * 根据example获得数据数量
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public Integer countByExample(IrpAsseroomapplyExample example) throws SQLException;
	
	/**
	 * 根据example删除IrpAsseroomapply
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	int deleteByExample(IrpAsseroomapplyExample example) throws SQLException;
	
	
	/**
	 * 查询所有未开始的会议列表
	 * @return
	 */
	List<IrpAsseroomapply> getIrpAsseroomapplyListByTime();
	
	
}
