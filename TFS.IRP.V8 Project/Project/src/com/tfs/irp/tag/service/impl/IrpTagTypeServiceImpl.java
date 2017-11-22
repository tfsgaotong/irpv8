package com.tfs.irp.tag.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.tag.dao.IrpTagTypeDAO;
import com.tfs.irp.tag.entity.IrpTagType;
import com.tfs.irp.tag.entity.IrpTagTypeExample;
import com.tfs.irp.tag.service.IrpTagTypeService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpTagTypeServiceImpl implements IrpTagTypeService {
	private IrpTagTypeDAO irpTagTypeDAO;

	public IrpTagTypeDAO getIrpTagTypeDAO() {
		return irpTagTypeDAO;
	}

	public void setIrpTagTypeDAO(IrpTagTypeDAO irpTagTypeDAO) {
		this.irpTagTypeDAO = irpTagTypeDAO;
	}
	
	@Override
	public long editTagType(IrpTagType _tagType){
		long nTagId = 0L;
		if(_tagType==null){
			return nTagId;
		}
		if(_tagType.getTypeid()==null || _tagType.getTypeid()==0L){
			long nTempId = TableIdUtil.getNextId(_tagType);
			_tagType.setTypeid(nTempId);
			_tagType.setCrtime(new Date());
			_tagType.setCruserid(LoginUtil.getLoginUserId());
			try {
				irpTagTypeDAO.insertSelective(_tagType);
				nTagId = nTempId;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				irpTagTypeDAO.updateByPrimaryKeySelective(_tagType);
				nTagId = _tagType.getTypeid();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nTagId;
	}
	
	@Override
	public int findIrpTagTypeCount() {
		int nCount=0;
		try {
			nCount = irpTagTypeDAO.countByExample(new IrpTagTypeExample());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpTagType> findIrpTagTypes(PageUtil pageUtil, String _sOrderBy) {
		List<IrpTagType> list = null;
		IrpTagTypeExample example = new IrpTagTypeExample();
		if(_sOrderBy==null || _sOrderBy.isEmpty()){
			_sOrderBy = "crtime desc";
		}
		example.setOrderByClause(_sOrderBy);
		try {
			list = irpTagTypeDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean checkTypeTypeName(String _sTypeName, long _nTypeId) {
		int nCount=0;
		IrpTagTypeExample example = new IrpTagTypeExample();
		example.createCriteria().andTypenameEqualTo(_sTypeName).andTypeidNotEqualTo(_nTypeId);
		try {
			nCount = irpTagTypeDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (nCount>0);
	}
	
	@Override
	public IrpTagType findIrpTagTypeByTypeId(long _nTypeId) {
		IrpTagType irpTagType = null;
		try {
			irpTagType = irpTagTypeDAO.selectByPrimaryKey(_nTypeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpTagType;
	}
	
	@Override
	public int deleteIrpTagTypeByTypeId(long _nTypeId) {
		int nCount = 0;
		try {
			nCount = irpTagTypeDAO.deleteByPrimaryKey(_nTypeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
}
