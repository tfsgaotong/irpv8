package com.tfs.irp.configType.service;

import java.util.List;

import com.tfs.irp.configType.entity.IrpConfigType;
import com.tfs.irp.util.PageUtil;

public interface IrpConfigTypeService {

	/*
	 * 得到所有的配置类型
	 */
	List<IrpConfigType> findAllIrpConfigTypeOfPage(PageUtil pageUtil,String _orderby);
	/*
	 * 得到配置类型的个数
	 */
	Integer IrpConfigTypeCount();
	
	
}
