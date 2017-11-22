package com.tfs.irp.voteoptions.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.vote.entity.IrpVote;
import com.tfs.irp.voteoptions.dao.IrpVoteOptionsDAO;
import com.tfs.irp.voteoptions.entity.IrpVoteOptions;
import com.tfs.irp.voteoptions.entity.IrpVoteOptionsExample;
import com.tfs.irp.voteoptions.service.IrpVoteOptionsService;

public class IrpVoteOptionsServiceImpl implements IrpVoteOptionsService{

	private IrpVoteOptionsDAO irpVoteOptionsDAO;
	
	public IrpVoteOptionsDAO getIrpVoteOptionsDAO() {
		return irpVoteOptionsDAO;
	}

	public void setIrpVoteOptionsDAO(IrpVoteOptionsDAO irpVoteOptionsDAO) {
		this.irpVoteOptionsDAO = irpVoteOptionsDAO;
	}

	@Override
	public List<IrpVoteOptions> findOptionBypid(Long pid,String orderby) {
		List<IrpVoteOptions> list=null;
		try {
			IrpVoteOptionsExample example=new IrpVoteOptionsExample();
			example.createCriteria().andParentidEqualTo(pid);
			example.setOrderByClause(orderby);
			list=irpVoteOptionsDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
