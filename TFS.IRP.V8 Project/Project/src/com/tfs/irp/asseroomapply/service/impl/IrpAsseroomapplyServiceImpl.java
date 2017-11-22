package com.tfs.irp.asseroomapply.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import oracle.net.aso.d;

import com.tfs.irp.asseroomapply.dao.IrpAsseroomapplyDAO;
import com.tfs.irp.asseroomapply.entity.IrpAsseroomapply;
import com.tfs.irp.asseroomapply.entity.IrpAsseroomapplyExample;
import com.tfs.irp.asseroomapply.service.IrpAsseroomapplyService;
import com.tfs.irp.util.PageUtil;

public class IrpAsseroomapplyServiceImpl implements IrpAsseroomapplyService {
	private IrpAsseroomapplyDAO asseroomapplyDAO;
	
	public IrpAsseroomapplyDAO getAsseroomapplyDAO() {
		return asseroomapplyDAO;
	}

	public void setAsseroomapplyDAO(IrpAsseroomapplyDAO asseroomapplyDAO) {
		this.asseroomapplyDAO = asseroomapplyDAO;
	}

	@Override
	public List<IrpAsseroomapply> queryAllBugForList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addApply(IrpAsseroomapply asseroomapply)
			throws Exception {
		asseroomapplyDAO.insertSelective(asseroomapply);
		return 1L;
	}

	@Override
	public List<IrpAsseroomapply> querySbForPage(
			IrpAsseroomapplyExample Example) throws Exception {
		return asseroomapplyDAO.selectByExample(Example);
	}

	@Override
	public int getDataCount(IrpAsseroomapplyExample example) throws Exception {
		return asseroomapplyDAO.countByExample(example);
	}

	@Override
	public IrpAsseroomapply selectByPrimaryKey(Long Sbid) throws SQLException {
		return asseroomapplyDAO.selectByPrimaryKey(Sbid);
	}

	@Override
	public List<IrpAsseroomapply> selectByExample(
			IrpAsseroomapplyExample example) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByExample(IrpAsseroomapply asseroomapply,
			IrpAsseroomapplyExample example) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExampleSelective(IrpAsseroomapply asseroomapply,
			IrpAsseroomapplyExample example) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer countByExample(IrpAsseroomapplyExample example)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByExample(IrpAsseroomapplyExample example)
			throws SQLException {
		asseroomapplyDAO.deleteByExample(example);
		return 0;
	}

	@Override
	public List<IrpAsseroomapply> getIrpAsseroomapplyListByTime() {
		// TODO Auto-generated method stub
		List<IrpAsseroomapply> list = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1); 
		IrpAsseroomapplyExample example = new IrpAsseroomapplyExample();
		example.createCriteria().andWarntimeIsNotNull()
								.andWarntimeGreaterThan(cal.getTime());
		example.setOrderByClause("asseroomapplyid desc");
		try {
			list = this.asseroomapplyDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<IrpAsseroomapply> querySbForPage(
			IrpAsseroomapplyExample Example, PageUtil page) throws Exception {
		return asseroomapplyDAO.selectByExample(Example, page);
	}
	 
	 
}
