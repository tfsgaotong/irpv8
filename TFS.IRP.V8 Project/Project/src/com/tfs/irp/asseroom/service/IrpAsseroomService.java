package com.tfs.irp.asseroom.service;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.asseroom.entity.IrpAsseroom;
import com.tfs.irp.asseroom.entity.IrpAsseroomExample;
import com.tfs.irp.util.PageUtil;

public interface IrpAsseroomService {
	/**
	 * 查询所有会议室
	 * @return
	 * @throws Exception
	 */
	public List<IrpAsseroom> queryAllBugForList()throws Exception;
		
	
	/**
	 * 添加会议室
	 * @param asseroom
	 * @return
	 * @throws Exception
	 */
	public Long addSb(IrpAsseroom asseroom, String[] _asseroomsbids)throws Exception;
	
	
	/**
	 * 分页查询会议室
	 * @param Example
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<IrpAsseroom> querySbForPage(IrpAsseroomExample Example,PageUtil page)throws Exception;
	
	/**
	 * 根据example获得数据数量
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int getDataCount(IrpAsseroomExample example)throws Exception;
	/**
	 * 根据主键查询
	 * @param Sbid
	 * @return
	 * @throws SQLException
	 */
	public IrpAsseroom selectByPrimaryKey(Long Sbid) throws SQLException;
	
	/**
	 * 根据example查询IrpAsseroom集合
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public List<IrpAsseroom> selectByExample(IrpAsseroomExample example) throws SQLException;
	
	/**
	 * 根据example更新IrpAsseroom
	 * @param asseroom
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public int updateByExample(IrpAsseroom asseroom, IrpAsseroomExample example) throws SQLException;
	
	/**
	 * 根据example更新IrpAsseroom
	 * @param asseroom
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public int updateByExampleSelective(IrpAsseroom asseroom, IrpAsseroomExample example) throws SQLException;
	
	/**
	 * 根据example获得数据数量
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public Integer countByExample(IrpAsseroomExample example) throws SQLException;
	
	/**
	 * 根据example删除IrpAsseroom
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	int deleteByExample(IrpAsseroomExample example) throws SQLException;
	/**
	 * 查询会议室名称
	 * @param _ck_sbname
	 * @return
	 * @throws SQLException
	 */
	int findname(String _ck_name) throws SQLException;
	 
}
