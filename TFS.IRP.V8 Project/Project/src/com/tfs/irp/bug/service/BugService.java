package com.tfs.irp.bug.service;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.bug.entity.IrpBugWithBLOBs;
import com.tfs.irp.bug.entity.IrpBugExample;
import com.tfs.irp.util.PageUtil;

public interface BugService {
	
	/**
	 * 查询所有Bug信息
	 * @return
	 * @throws Exception
	 */
	public List<IrpBugWithBLOBs> queryAllBugForList()throws Exception;
		
	/**
	 * 添加Bug信息
	 * @param bug
	 * @return
	 * @throws Exception
	 */
	public Long addBug(IrpBugWithBLOBs bug)throws Exception;
	
	/**
	 * 分页查询Bug信息
	 * @param bugExample
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<IrpBugWithBLOBs> queryBugForPage(IrpBugExample bugExample,PageUtil page)throws Exception;
	
	/**
	 * 根据example获得数据数量
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int getDataCount(IrpBugExample example)throws Exception;

	/**
	 * 根据主键查询Bug
	 * @param bugid
	 * @return
	 * @throws SQLException
	 */
	public IrpBugWithBLOBs selectByPrimaryKey(Long bugid) throws SQLException;
	
	/**
	 * 根据example查询Bug集合
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public List<IrpBugWithBLOBs> selectByExample(IrpBugExample example) throws SQLException;
	
	/**
	 * 根据example更新Bug
	 * @param record
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public int updateByExample(IrpBugWithBLOBs record, IrpBugExample example) throws SQLException;
	
	/**
	 * 根据example更新Bug
	 * @param record
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public int updateByExampleSelective(IrpBugWithBLOBs record, IrpBugExample example) throws SQLException;
	
	/**
	 * 根据example获得数据数量
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public Integer countByExample(IrpBugExample example) throws SQLException;
	
	/**
	 * 根据example删除Bug
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	int deleteByExample(IrpBugExample example) throws SQLException;
	
	public IrpBugWithBLOBs loadtoeditor(Long bugid)throws SQLException;
}
