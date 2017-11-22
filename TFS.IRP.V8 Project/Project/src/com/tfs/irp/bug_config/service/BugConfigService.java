package com.tfs.irp.bug_config.service;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.bug_config.entity.IrpBugConfig;
import com.tfs.irp.bug_config.entity.IrpBugConfigExample;

public interface BugConfigService {

	/**
	 * 添加版本/模块
	 * @param record
	 * @throws SQLException
	 */
	void insert(IrpBugConfig record) throws SQLException;
	
	/**
	 * 通过example 查询版本/模块
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	List<IrpBugConfig> selectByExample(IrpBugConfigExample example) throws SQLException;
	
	/**
	 * 编辑版本/模块
	 * @param record
	 * @return
	 * @throws SQLException
	 */
	int updateByPrimaryKeySelective(IrpBugConfig record) throws SQLException;
	
	/**
	 * 通过主键删除版本/模块
	 * @param bugconfigid
	 * @return
	 * @throws SQLException
	 */
	int deleteByPrimaryKey(Long bugconfigid) throws SQLException;
}
