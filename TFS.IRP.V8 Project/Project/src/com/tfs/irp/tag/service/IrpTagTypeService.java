package com.tfs.irp.tag.service;

import java.util.List;

import com.tfs.irp.tag.entity.IrpTagType;
import com.tfs.irp.util.PageUtil;

public interface IrpTagTypeService {

	/**
	 * 编辑标签类型 ID为0或null添加，则为修改
	 * @param _tagType
	 * @return
	 */
	long editTagType(IrpTagType _tagType);

	/**
	 * 分页获得标签类型集合
	 * @param pageUtil
	 * @return
	 */
	List<IrpTagType> findIrpTagTypes(PageUtil pageUtil, String _sOrderBy);

	/**
	 * 获得所有标签集合数量
	 * @return
	 */
	int findIrpTagTypeCount();

	/**
	 * 检查标签类型名称是否存在
	 * @param _sTypeName
	 * @param _nTypeId
	 * @return
	 */
	boolean checkTypeTypeName(String _sTypeName, long _nTypeId);

	/**
	 * 根据类型ID获得标签类型对象
	 * @param _nTypeId
	 * @return
	 */
	IrpTagType findIrpTagTypeByTypeId(long _nTypeId);

	/**
	 * 根据类型ID删除标签类型对象
	 * @param _nTypeId
	 * @return
	 */
	int deleteIrpTagTypeByTypeId(long _nTypeId);
}
