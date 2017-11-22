package com.tfs.irp.bug.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.bug.dao.IrpBugDAO;
import com.tfs.irp.bug.entity.IrpBugWithBLOBs;
import com.tfs.irp.bug.entity.IrpBugExample;
import com.tfs.irp.bug.service.BugService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class BugServiceImpl implements BugService {
	
	private IrpBugDAO bugDao;
	
	

	public void setBugDao(IrpBugDAO bugDao) {
		this.bugDao = bugDao;
	}

	@Override
	public List<IrpBugWithBLOBs> queryAllBugForList() throws Exception{
		return null;
	}

	@Override
	public Long addBug(IrpBugWithBLOBs bug) throws Exception{
		
		 bugDao.insert(bug);
		 return null;
	}

	@Override
	public List<IrpBugWithBLOBs> queryBugForPage(IrpBugExample bugExample,PageUtil page) throws Exception {
		return bugDao.selectByExample(bugExample,page);
	}

	@Override
	public int getDataCount(IrpBugExample example) throws Exception {
		return bugDao.countByExample(example);
	}

	@Override
	public IrpBugWithBLOBs selectByPrimaryKey(Long bugid) throws SQLException {
		return bugDao.selectByPrimaryKey(bugid);
	}
	
	@Override
	public List<IrpBugWithBLOBs> selectByExample(IrpBugExample example)  throws SQLException{
		return bugDao.selectByExample(example);
	}

	@Override
	public int updateByExample(IrpBugWithBLOBs record, IrpBugExample example)
			throws SQLException {
		return bugDao.updateByExample(record, example);
	}

	@Override
	public int updateByExampleSelective(IrpBugWithBLOBs record, IrpBugExample example)
			throws SQLException {
		return bugDao.updateByExampleSelective(record, example);
	}

	@Override
	public Integer countByExample(IrpBugExample example) throws SQLException {
		return bugDao.countByExample(example);
	}

	@Override
	public IrpBugWithBLOBs loadtoeditor(Long bugid) throws SQLException {
		// TODO Auto-generated method stub
		return bugDao.selectByPrimaryKey(bugid);
	}

	@Override
	public int deleteByExample(IrpBugExample example) throws SQLException {
		return bugDao.deleteByExample(example);
	}

}
