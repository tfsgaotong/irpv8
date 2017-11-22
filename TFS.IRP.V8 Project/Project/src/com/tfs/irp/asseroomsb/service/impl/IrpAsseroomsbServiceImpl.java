package com.tfs.irp.asseroomsb.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.asseroomsb.dao.IrpAsseroomsbDAO;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsb;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample.Criteria;
import com.tfs.irp.asseroomsb.service.IrpAsseroomsbService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpAsseroomsbServiceImpl implements IrpAsseroomsbService {
	private IrpAsseroomsbDAO asseroomsbDAO;
	
	public IrpAsseroomsbDAO getAsseroomsbDAO() {
		return asseroomsbDAO;
	}

	public void setAsseroomsbDAO(IrpAsseroomsbDAO asseroomsbDAO) {
		this.asseroomsbDAO = asseroomsbDAO;
	}

	@Override
	public List<IrpAsseroomsb> queryAllBugForList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addSb(IrpAsseroomsb asseroomsb) throws Exception {
		asseroomsb.setAsseroomsbid(TableIdUtil.getNextId(IrpAsseroomsb.TABLE_NAME));
		asseroomsb.setCrtime(new Date());
		IrpUser loginUser = LoginUtil.getLoginUser();
		asseroomsb.setCruserid(loginUser.getUserid());
		asseroomsbDAO.insertSelective(asseroomsb);
		return 1L;
	}

	@Override
	public List<IrpAsseroomsb> querySbForPage(IrpAsseroomsbExample Example,
			PageUtil page) throws Exception {
		return asseroomsbDAO.selectByExample(Example,page);
	}

	@Override
	public int getDataCount(IrpAsseroomsbExample example) throws Exception {
		return asseroomsbDAO.countByExample(example);
	}

	@Override
	public IrpAsseroomsb selectByPrimaryKey(Long Sbid) throws SQLException {
		return asseroomsbDAO.selectByPrimaryKey(Sbid);
	}

	@Override
	public List<IrpAsseroomsb> selectByExample(IrpAsseroomsbExample example)
			throws SQLException {
		return asseroomsbDAO.selectByExample(example);
	}

	@Override
	public int updateByExample(IrpAsseroomsb asseroomsb,
			IrpAsseroomsbExample example) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExampleSelective(IrpAsseroomsb asseroomsb,
			IrpAsseroomsbExample example) throws SQLException {
		return asseroomsbDAO.updateByPrimaryKeySelective(asseroomsb);
	}

	@Override
	public Integer countByExample(IrpAsseroomsbExample example)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByExample(IrpAsseroomsbExample example)
			throws SQLException {
		asseroomsbDAO.deleteByExample(example);
		return 1;
	}



}
