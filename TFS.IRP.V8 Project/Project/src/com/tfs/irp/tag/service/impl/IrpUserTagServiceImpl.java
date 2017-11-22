package com.tfs.irp.tag.service.impl;

import java.sql.SQLException;

import com.tfs.irp.tag.dao.IrpUserTagDAO;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.entity.IrpUserTag;
import com.tfs.irp.tag.entity.IrpUserTagExample;
import com.tfs.irp.tag.service.IrpUserTagService;
import com.tfs.irp.util.TableIdUtil;

public class IrpUserTagServiceImpl implements IrpUserTagService {
	private IrpUserTagDAO irpUserTagDAO;
	
	public IrpUserTagDAO getIrpUserTagDAO() {
		return irpUserTagDAO;
	}

	public void setIrpUserTagDAO(IrpUserTagDAO irpUserTagDAO) {
		this.irpUserTagDAO = irpUserTagDAO;
	}
	
	@Override
	public long userTagEdit(IrpUserTag _irpUserTag) {
		long nUserTagId = 0L;
		if(_irpUserTag.getUsertagid()==null || _irpUserTag.getUsertagid()==0L){
			long nTempId = TableIdUtil.getNextId(IrpUserTag.TABLE_NAME);
			_irpUserTag.setUsertagid(nTempId);
			try {
				irpUserTagDAO.insertSelective(_irpUserTag);
				nUserTagId = nTempId;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				irpUserTagDAO.updateByPrimaryKey(_irpUserTag);
				nUserTagId = _irpUserTag.getUsertagid();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nUserTagId;
	}
	
	@Override
	public int deleteUserTagByUserIdAndTagId(long _nUserId, long _nTagId) {
		int nCount = 0;
		try {
			IrpUserTagExample example = new IrpUserTagExample();
			example.createCriteria().andUseridEqualTo(_nUserId).andTagidEqualTo(_nTagId);
			nCount = irpUserTagDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public int findUserTagCountByUserIdAndTagId(long _nUserId, long _nTagId) {
		int nCount = 0;
		try {
			IrpUserTagExample example = new IrpUserTagExample();
			example.createCriteria().andUseridEqualTo(_nUserId).andTagidEqualTo(_nTagId);
			nCount = irpUserTagDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
}
