package com.tfs.irp.asseroomsb.service;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.asseroomsb.entity.IrpAsseroomsb;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample;
import com.tfs.irp.util.PageUtil;

public interface IrpAsseroomsbService {
	/**
	 * 查询所有会议室设备信息
	 * @return
	 * @throws Exception
	 */
	public List<IrpAsseroomsb> queryAllBugForList()throws Exception;
		
	
	/**
	 * 添加会议室设备信息
	 * @param asseroomsb
	 * @return
	 * @throws Exception
	 */
	public Long addSb(IrpAsseroomsb asseroomsb)throws Exception;
	
	
	/**
	 * 分页查询会议室设备信息
	 * @param Example
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<IrpAsseroomsb> querySbForPage(IrpAsseroomsbExample Example,PageUtil page)throws Exception;
	
	/**
	 * 根据example获得数据数量
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int getDataCount(IrpAsseroomsbExample example)throws Exception;
	/**
	 * 根据主键查询设备信息
	 * @param Sbid
	 * @return
	 * @throws SQLException
	 */
	public IrpAsseroomsb selectByPrimaryKey(Long Sbid) throws SQLException;
	
	/**
	 * 根据example查询IrpAsseroomsb集合
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public List<IrpAsseroomsb> selectByExample(IrpAsseroomsbExample example) throws SQLException;
	
	/**
	 * 根据example更新IrpAsseroomsb
	 * @param asseroomsb
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public int updateByExample(IrpAsseroomsb asseroomsb, IrpAsseroomsbExample example) throws SQLException;
	
	/**
	 * 根据example更新IrpAsseroomsb
	 * @param asseroomsb
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public int updateByExampleSelective(IrpAsseroomsb asseroomsb, IrpAsseroomsbExample example) throws SQLException;
	
	/**
	 * 根据example获得数据数量
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public Integer countByExample(IrpAsseroomsbExample example) throws SQLException;
	
	/**
	 * 根据example删除IrpAsseroomsb
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	int deleteByExample(IrpAsseroomsbExample example) throws SQLException;


}
