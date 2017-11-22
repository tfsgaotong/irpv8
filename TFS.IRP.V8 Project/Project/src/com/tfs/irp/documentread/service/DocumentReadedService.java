package com.tfs.irp.documentread.service;

import java.util.List;

import com.tfs.irp.documentread.entity.IrpDocumentReaded;

public interface DocumentReadedService {
	/**
	 * 根据文章id和用户id查询这条记录
	 * @param _docid
	 * @param userid
	 * @return
	 */
	public List<IrpDocumentReaded> findData(Long _docid,Long userid);
	/**
	 * 添加一条阅读记录
	 * @param _docid
	 * @param _userid
	 */
	public void addData(Long _docid,Long _userid);
	
	/**
	 * 查看某个文章阅读的数量
	 * @return
	 */
	public int coungData(Long _docid);

}
