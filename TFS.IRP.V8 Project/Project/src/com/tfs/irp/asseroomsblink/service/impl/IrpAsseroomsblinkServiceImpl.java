package com.tfs.irp.asseroomsblink.service.impl;

import java.util.Date;
import java.util.List;

import com.tfs.irp.asseroom.entity.IrpAsseroom;
import com.tfs.irp.asseroomsblink.dao.IrpAsseroomsblinkDAO;
import com.tfs.irp.asseroomsblink.entity.IrpAsseroomsblink;
import com.tfs.irp.asseroomsblink.entity.IrpAsseroomsblinkExample;
import com.tfs.irp.asseroomsblink.entity.IrpAsseroomsblinkExample.Criteria;
import com.tfs.irp.asseroomsblink.service.IrpAsseroomsblinkService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpAsseroomsblinkServiceImpl implements IrpAsseroomsblinkService {
	private IrpAsseroomsblinkDAO asseroomsblinkDAO;

	public IrpAsseroomsblinkDAO getAsseroomsblinkDAO() {
		return asseroomsblinkDAO;
	}

	public void setAsseroomsblinkDAO(IrpAsseroomsblinkDAO asseroomsblinkDAO) {
		this.asseroomsblinkDAO = asseroomsblinkDAO;
	}

	@Override
	public Long addSb(Long _asseroomid, String[] _asseroomsbids)
			throws Exception {
		IrpAsseroomsblink asseroomsblink=new IrpAsseroomsblink();
		if(null!=_asseroomsbids){
			for (int i = 0; i < _asseroomsbids.length&& _asseroomsbids.length>0; i++) {
				IrpAsseroomsblinkExample example=new IrpAsseroomsblinkExample();
				Criteria criteria=example.createCriteria();
				criteria.andAsseroomidEqualTo(_asseroomid).andAsseroomsbidEqualTo(Long.parseLong(_asseroomsbids[i]));
				List list=asseroomsblinkDAO.selectByExample(example);
				if(list.isEmpty()){
					asseroomsblink.setAsseroomsblinkid(TableIdUtil.getNextId(IrpAsseroom.TABLE_NAME));
					asseroomsblink.setCrtime(new Date());
					asseroomsblink.setAsseroomid(_asseroomid);
					asseroomsblink.setAsseroomsblinkstatus(1);
					asseroomsblink.setAsseroomsbid(Long.parseLong(_asseroomsbids[i]));
					asseroomsblinkDAO.insertSelective(asseroomsblink);
				}
			}
		}
		return 1L;
	}

	@Override
	public List<IrpAsseroomsblink> findAll() throws Exception {
		IrpAsseroomsblinkExample example=new IrpAsseroomsblinkExample();
		return asseroomsblinkDAO.selectByExample(example);
	}

	@Override
	public List<IrpAsseroomsblink> findbyAsseroomid(Long _asseroomid)
			throws Exception {
		IrpAsseroomsblinkExample example=new IrpAsseroomsblinkExample();
		Criteria criteria=example.createCriteria();
		criteria.andAsseroomidEqualTo(_asseroomid);
		List<IrpAsseroomsblink>	list=asseroomsblinkDAO.selectByExample(example);
		return list.isEmpty()? null:list;
	}

	@Override
	public List<IrpAsseroomsblink> findbyAsseroomsbid(Long _asseroomsbid)
			throws Exception {
		IrpAsseroomsblinkExample example=new IrpAsseroomsblinkExample();
		Criteria criteria=example.createCriteria();
		criteria.andAsseroomidEqualTo(_asseroomsbid);
		List<IrpAsseroomsblink>	list=asseroomsblinkDAO.selectByExample(example);
		return list.isEmpty()? null:list;
	}

	@Override
	public int deletebyAsseroomsbid(Long _asseroomsbid) throws Exception {
		IrpAsseroomsblinkExample example=new IrpAsseroomsblinkExample();
		Criteria criteria=example.createCriteria();
		criteria.andAsseroomidEqualTo(_asseroomsbid);
		return asseroomsblinkDAO.deleteByExample(example);
	}

	@Override
	public int deletebyAsseroomid(Long _asseroomid) throws Exception {
		IrpAsseroomsblinkExample example=new IrpAsseroomsblinkExample();
		Criteria criteria=example.createCriteria();
		criteria.andAsseroomidEqualTo(_asseroomid);
		return asseroomsblinkDAO.deleteByExample(example);
	}

	@Override
	public int deletebyAsseroomsbidList(List<Long> _asseroomsbids)
			throws Exception {
		IrpAsseroomsblinkExample example=new IrpAsseroomsblinkExample();
		Criteria criteria=example.createCriteria();
		criteria.andAsseroomsbidIn(_asseroomsbids);
		return asseroomsblinkDAO.deleteByExample(example);
	}

	@Override
	public int deletebyAsseroomidList(List<Long> _asseroomids) throws Exception {
		IrpAsseroomsblinkExample example=new IrpAsseroomsblinkExample();
		Criteria criteria=example.createCriteria();
		criteria.andAsseroomidIn(_asseroomids);
		return asseroomsblinkDAO.deleteByExample(example);
	}
	
}
