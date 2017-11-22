package com.tfs.irp.right.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.right.dao.IrpRightDAO;
import com.tfs.irp.right.entity.IrpRight;
import com.tfs.irp.right.entity.IrpRightExample;
import com.tfs.irp.right.service.IrpRightService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpRightServiceImpl implements IrpRightService {
	private IrpRightDAO irpRightDAO;

	public IrpRightDAO getIrpRightDAO() {
		return irpRightDAO;
	}

	public void setIrpRightDAO(IrpRightDAO irpRightDAO) {
		this.irpRightDAO = irpRightDAO;
	}
	
	@Override
	public Map<String, Object> findObjRowRight(String _sType,String _operType, Long operId,String _objType,Long _objId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("type", _sType+"%");
		parameter.put("opertype", _operType);
		parameter.put("operid", operId);
		parameter.put("objtype", _objType);
		parameter.put("objid", _objId);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> list = irpRightDAO.findObjRowRight(parameter);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> row = list.get(i);
				if(row==null){
					continue;
				}
				map.put(row.get("OPERTYPE").toString(), row.get("OPERRIGHT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public void rightEdit(Long _nObjId, String _sObjType, Long _nOperId, String _sOperType, Long _nOperTypeId) {
		IrpRightExample example = new IrpRightExample();
		example.createCriteria()
			.andObjidEqualTo(_nObjId)
			.andObjtypeEqualTo(_sObjType)
			.andOperidEqualTo(_nOperId)
			.andOpertypeEqualTo(_sOperType)
			.andOpertypeidEqualTo(_nOperTypeId);
		try {
			int nCount = irpRightDAO.countByExample(example);
			if(nCount==0){
				IrpUser loginUser = LoginUtil.getLoginUser();
				IrpRight irpRight = new IrpRight();
				irpRight.setRightid(TableIdUtil.getNextId(IrpRight.TABLE_NAME));
				irpRight.setObjid(_nObjId);
				irpRight.setObjtype(_sObjType);
				irpRight.setOperid(_nOperId);
				irpRight.setOpertype(_sOperType);
				irpRight.setOpertypeid(_nOperTypeId);
				irpRight.setCrtime(new Date());
				irpRight.setCruser(loginUser.getName());
				irpRight.setCruserid(loginUser.getId());
				irpRightDAO.insertSelective(irpRight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void rightDelete(Long _nObjId, String _sObjType, Long _nOperId, String _sOperType, Long _nOperTypeId){
		IrpRightExample example = new IrpRightExample();
		example.createCriteria()
			.andObjidEqualTo(_nObjId)
			.andObjtypeEqualTo(_sObjType)
			.andOperidEqualTo(_nOperId)
			.andOpertypeEqualTo(_sOperType)
			.andOpertypeidEqualTo(_nOperTypeId);
		try {
			irpRightDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isExistOfId(Long _nObjId, String _sObjType, Long _nOperId, String _sOperType, Long _nOperTypeId) {	
		int nCount = 0;
		IrpRightExample example = new IrpRightExample();
		example.createCriteria()
			.andObjidEqualTo(_nObjId)
			.andObjtypeEqualTo(_sObjType)
			.andOperidEqualTo(_nOperId)
			.andOpertypeEqualTo(_sOperType)
			.andOpertypeidEqualTo(_nOperTypeId);
		try {
			nCount = irpRightDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount>0;
	}
	
	@Override
	public boolean isExistOfIds(List<Long> _arrObjIds, String _sObjType, Long _nOperId, String _sOperType, Long _nOperTypeId) {	
		int nCount = 0;
		IrpRightExample example = new IrpRightExample();
		example.createCriteria()
			.andObjidIn(_arrObjIds)
			.andObjtypeEqualTo(_sObjType)
			.andOperidEqualTo(_nOperId)
			.andOpertypeEqualTo(_sOperType)
			.andOpertypeidEqualTo(_nOperTypeId);
		try {
			nCount = irpRightDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount>0;
	}
}
