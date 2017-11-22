package com.tfs.irp.apptype.service;

import java.util.List;

import com.tfs.irp.apptype.entity.IrpApptype;

public interface IrpApptypeService {

	/***
	 * 查询所有的应用类型
	 * @return
	 */
	public List<IrpApptype> findAllapptype();
}
