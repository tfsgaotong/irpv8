package com.tfs.irp.attachedtype.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.attached.entity.IrpAttachedExample;
import com.tfs.irp.attachedtype.dao.IrpAttachedTypeDAO;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.entity.IrpAttachedTypeExample;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;

public class IrpAttachedServiceImpl implements IrpAttachedTypeService {
	private IrpAttachedTypeDAO irpAttachedTypeDAO;
	@Override
	public Object  AttachedTypeSuffixImage(Long _typeid) {
		Object obj=null;
		try {
		obj= irpAttachedTypeDAO. AttachedTypeSuffixImage( _typeid); 
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return obj;
	}
	public IrpAttachedTypeDAO getIrpAttachedTypeDAO() {
		return irpAttachedTypeDAO;
	}
	public void setIrpAttachedTypeDAO(IrpAttachedTypeDAO irpAttachedTypeDAO) {
		this.irpAttachedTypeDAO = irpAttachedTypeDAO;
	}
	@Override
	public List<IrpAttachedType> allAttachedTypes() {
		 List<IrpAttachedType> attachedTypes=null;
		try {
			attachedTypes =irpAttachedTypeDAO.selectByExample(null);
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		return attachedTypes;
	}
	
	@Override
	public Long findAttachedTypeIdByFileExt(String _sFileExt) {
		long nAttachedTypeId = 0L;
		IrpAttachedTypeExample example = new IrpAttachedTypeExample();
		example.or(example.createCriteria().andSuffixLike("%,"+_sFileExt.toUpperCase()+",%"));
		example.or(example.createCriteria().andSuffixLike(_sFileExt.toUpperCase()+",%"));
		example.or(example.createCriteria().andSuffixLike("%,"+_sFileExt.toUpperCase()));
		example.or(example.createCriteria().andSuffixEqualTo(_sFileExt.toUpperCase()));
		try {
			List<IrpAttachedType> list = irpAttachedTypeDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				nAttachedTypeId = list.get(0).getTypeid();
			}else{
				example.clear();
				example.createCriteria().andTypenameEqualTo("其他");
				list.clear();
				list = irpAttachedTypeDAO.selectByExample(example);
				nAttachedTypeId = list.get(0).getTypeid();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nAttachedTypeId;
	}
	@Override
	public IrpAttachedType findDataByPrimaryKey(Long attachedTypeId) {
		// TODO Auto-generated method stub
		IrpAttachedType attachedType=null;
		IrpAttachedTypeExample example=new IrpAttachedTypeExample();
		example.createCriteria().andTypeidEqualTo(attachedTypeId);
		try {
			List<IrpAttachedType> list=irpAttachedTypeDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				attachedType=list.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attachedType;
	}
	@Override
	public int updateType(IrpAttachedType attachedType) {
		// TODO Auto-generated method stub
		int nCount=0; 
		IrpAttachedTypeExample  example =new IrpAttachedTypeExample();
		example.createCriteria().andTypeidEqualTo(attachedType.getTypeid());
		try {
			nCount=irpAttachedTypeDAO.updateByExampleSelective(attachedType, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
}
