package com.tfs.irp.attachedtype.service;

import java.util.List;

import com.tfs.irp.attachedtype.entity.IrpAttachedType;

public interface IrpAttachedTypeService {
	/**
	 * 根据主键查询当前需要上传的文件类型
	 * @return
	 */
	Object  AttachedTypeSuffixImage(Long _typeid);
	/**
	 * 得到所有类型对象
	 * @return
	 */
	List<IrpAttachedType> allAttachedTypes();
	
	/**
	 * 根据文件后缀名获得文件类型ID
	 * @param _sFileExt
	 * @return
	 */
	Long findAttachedTypeIdByFileExt(String _sFileExt);
	/**
	 * 获取某个文件类型对象
	 * @param attachedTypeId
	 * @return
	 */
	IrpAttachedType  findDataByPrimaryKey(Long attachedTypeId);
	
	int updateType(IrpAttachedType attachedType);
}
