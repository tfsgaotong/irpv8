package com.tfs.irp.documentlogs.service;

import java.util.List;

import com.tfs.irp.documentlogs.entity.IrpDocumentLogs;
import com.tfs.irp.util.PageUtil;

public interface IrpDocumentLogsService {

	/**
	 * 编辑知识阅读日志
	 * @param _nDocId
	 * @return
	 */
	int editDocumentLogs(long _nDocId);

	/**
	 * 根据DOCID获得知识的阅读记录
	 * @param _nDocId
	 * @param pageUtil
	 * @return
	 */
	List<IrpDocumentLogs> findIrpDocumentLogsByDocId(Long _nDocId, PageUtil pageUtil);

	/**
	 * 根据DOCID获得知识的阅读记录数量
	 * @param _nDocId
	 * @return
	 */
	int countIrpDocumentLogsByDocId(Long _nDocId);

}
