package com.tfs.irp.assetype.service;

import java.util.List;

import com.tfs.irp.assetype.entity.IrpAssetype;
import com.tfs.irp.assetype.entity.IrpAssetypeExample;

public interface IrpAssetypeService {

	List<IrpAssetype> selectByExample(IrpAssetypeExample example) throws Exception;
	IrpAssetype selectByPrimaryKey(Long id)throws Exception;
}
