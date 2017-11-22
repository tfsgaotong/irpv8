package com.tfs.irp.bug_config.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.bug_config.dao.IrpBugConfigDAO;
import com.tfs.irp.bug_config.entity.IrpBugConfig;
import com.tfs.irp.bug_config.entity.IrpBugConfigExample;
import com.tfs.irp.bug_config.service.BugConfigService;

public class BugConfigServiceImpl implements BugConfigService {

	private IrpBugConfigDAO bugconfigdao;
	
	public IrpBugConfigDAO getBugconfigdao() {
		return bugconfigdao;
	}

	public void setBugconfigdao(IrpBugConfigDAO bugconfigdao) {
		this.bugconfigdao = bugconfigdao;
	}

	@Override
	public void insert(IrpBugConfig record) throws SQLException {
		bugconfigdao.insert(record);
	}

	@Override
	public List<IrpBugConfig> selectByExample(IrpBugConfigExample example)
			throws SQLException {
		return bugconfigdao.selectByExample(example);
	}

	@Override
	public int updateByPrimaryKeySelective(IrpBugConfig record)
			throws SQLException {
		return bugconfigdao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Long bugconfigid) throws SQLException {
		return this.bugconfigdao.deleteByPrimaryKey(bugconfigid);
	}

}
