package com.tfs.irp.doctransmite.service;

import java.util.List;

public interface IrpDocumentTransmiteService {
/**
 * 转发文档给选择的关注人
 * @param _focusids
 * @param _summary
 * @param _docid
 * @return
 */
	public int addDocTrans(List<Long> _focusids,String _summary,Long _docid);
 
}
