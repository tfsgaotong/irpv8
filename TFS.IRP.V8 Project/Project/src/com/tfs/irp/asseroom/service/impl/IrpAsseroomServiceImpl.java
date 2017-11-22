package com.tfs.irp.asseroom.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.asseroom.dao.IrpAsseroomDAO;
import com.tfs.irp.asseroom.entity.IrpAsseroom;
import com.tfs.irp.asseroom.entity.IrpAsseroomExample;
import com.tfs.irp.asseroom.entity.IrpAsseroomExample.Criteria;
import com.tfs.irp.asseroom.service.IrpAsseroomService;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsb;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample;
import com.tfs.irp.asseroomsblink.web.AsseroomsblinkAction;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpAsseroomServiceImpl implements IrpAsseroomService {
	private IrpAsseroomDAO asseroomDAO;
	
	public IrpAsseroomDAO getAsseroomDAO() {
		return asseroomDAO;
	}

	public void setAsseroomDAO(IrpAsseroomDAO asseroomDAO) {
		this.asseroomDAO = asseroomDAO;
	}

	@Override
	public List<IrpAsseroom> queryAllBugForList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<IrpAsseroom> querySbForPage(IrpAsseroomExample Example,
			PageUtil page) throws Exception {
		return asseroomDAO.selectByExample(Example, page);
	}

	@Override
	public int getDataCount(IrpAsseroomExample example) throws Exception {
		return asseroomDAO.countByExample(example);
	}

	@Override
	public IrpAsseroom selectByPrimaryKey(Long Sbid) throws SQLException {
		return asseroomDAO.selectByPrimaryKey(Sbid);
	}

	@Override
	public List<IrpAsseroom> selectByExample(IrpAsseroomExample example)
			throws SQLException {
		return asseroomDAO.selectByExample(example);
	}

	@Override
	public int updateByExample(IrpAsseroom asseroom, IrpAsseroomExample example)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExampleSelective(IrpAsseroom asseroom,
			IrpAsseroomExample example) throws SQLException {
		return asseroomDAO.updateByExampleSelective(asseroom, example);
	}

	@Override
	public Integer countByExample(IrpAsseroomExample example)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByExample(IrpAsseroomExample example) throws SQLException {
		return asseroomDAO.deleteByExample(example);
	}

	@Override
	public int findname(String _ck_name) throws SQLException {
		// 1:名称已存在  2：名称不存在
				int _cKeyState=0;
				IrpAsseroomExample example=new IrpAsseroomExample();
				 Criteria criteria=example.createCriteria();
				 criteria.andAsseroomnameEqualTo(_ck_name);
				try {
				List list=this.asseroomDAO.selectByExample(example);
				if (list.size()>=1) {
					_cKeyState=1;
				}else{
					_cKeyState=2;
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return _cKeyState;
	}

	@Override
	public Long addSb(IrpAsseroom asseroom, String[] _asseroomsbids)
			throws Exception {
		asseroom.setCrtime(new Date());
		IrpUser loginUser = LoginUtil.getLoginUser();
		asseroom.setCruserid(loginUser.getUserid());
		asseroomDAO.insertSelective(asseroom);
		return 1L;
	}

}
