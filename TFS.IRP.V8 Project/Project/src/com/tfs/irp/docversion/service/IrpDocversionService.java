package com.tfs.irp.docversion.service;

import java.util.List;

import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.docversion.entity.IrpDocversion;
import com.tfs.irp.docversion.entity.IrpDocversionWithBLOBs;
import com.tfs.irp.util.PageUtil;

public interface IrpDocversionService {

	
	/**
	 * 增加历史版本
	 * @param irpDocversion
	 * @return
	 */
	long addDocVersion(IrpDocumentWithBLOBs _irpDocument,int _version);
	
	/**
	 * 根据id获得正确的版本
	 * @param _docid
	 * @return
	 */
	int getVersionidByDocid(long _docid);
	
	/**
	 * 分页显示不同版本
	 * @param _docid
	 * @param pageutil
	 * @return
	 */
	List<IrpDocversionWithBLOBs> getAddDataByDocid(Long _docid,PageUtil pageutil);
	/**
	 * 分页显示不同版本个数
	 * @param _docid
	 * @return
	 */
	int getAddDataByDocidNum(Long _docid);
	
	/**
	 * 分页显示不同版本
	 * @param _docid
	 * @param pageutil
	 * @return
	 */
	List<IrpDocversionWithBLOBs> getDeleDataByDocid(Long _docid,PageUtil pageutil);
	
	/**
	 * 根据id 获得  IrpDocuemtn object
	 * @param _docid
	 * @return
	 */
	IrpDocversionWithBLOBs irpDocversion(Long _docid);
	
	/**
	 * 根据ID查询docversion表中的历史版本
	 * @param _docid
	 * @return
	 */
	IrpDocversionWithBLOBs selectDocversion(long _docid);
	
	/**
	 * 通过DOCOUTUPID向docversion表中插入最新历史版本
	 * @return
	 */
	int insertDocversion(IrpDocversionWithBLOBs docversion);
}
