package com.tfs.irp.personalsearch.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.personalsearch.dao.IrpPersonalSearchDAO;
import com.tfs.irp.personalsearch.entity.IrpPersonalSearch;
import com.tfs.irp.personalsearch.entity.IrpPersonalSearchExample;
import com.tfs.irp.personalsearch.service.IrpPersonalSearchService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpPersonalSearchServiceImpl implements IrpPersonalSearchService {
	private IrpPersonalSearchDAO irpPersonalSearchDAO;

	public IrpPersonalSearchDAO getIrpPersonalSearchDAO() {
		return irpPersonalSearchDAO;
	}

	public void setIrpPersonalSearchDAO(IrpPersonalSearchDAO irpPersonalSearchDAO) {
		this.irpPersonalSearchDAO = irpPersonalSearchDAO;
	}
	
	@Override
	public List<IrpPersonalSearch> findPersonalSearchByUserId(long _nUserId) {
		List<IrpPersonalSearch> list = null;
		IrpPersonalSearchExample example = new IrpPersonalSearchExample();
		example.createCriteria().andCruseridEqualTo(_nUserId);
		try {
			list = irpPersonalSearchDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public IrpPersonalSearch findPersonalSearchById(long psId) {
		IrpPersonalSearch personalSearch = null;
		try {
			personalSearch = irpPersonalSearchDAO.selectByPrimaryKey(psId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personalSearch;
	}
	
	@Override
	public long addEditPersonalSearch(IrpPersonalSearch _personalSearch) {
		if(_personalSearch==null)
			return 0L;
		Long personalsearchid = _personalSearch.getPersonalsearchid();
		if(personalsearchid==null || personalsearchid.intValue()==0){
			_personalSearch.setPersonalsearchid(TableIdUtil.getNextId(_personalSearch));
			_personalSearch.setCrtime(new Date());
			_personalSearch.setCruserid(LoginUtil.getLoginUserId());
			try {
				irpPersonalSearchDAO.insertSelective(_personalSearch);
				personalsearchid = _personalSearch.getPersonalsearchid();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				irpPersonalSearchDAO.updateByPrimaryKeySelective(_personalSearch);
			} catch (SQLException e) {
				personalsearchid = 0L;
				e.printStackTrace();
			}
		}
		return personalsearchid;
	}
	
	@Override
	public int deletePersonalSearchById(long _personalsearchid) {
		int nCount=0;
		try {
			nCount = irpPersonalSearchDAO.deleteByPrimaryKey(_personalsearchid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
}
