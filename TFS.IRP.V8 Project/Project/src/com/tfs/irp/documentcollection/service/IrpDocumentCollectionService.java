package com.tfs.irp.documentcollection.service;

import java.util.HashMap;
import java.util.List;

import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.documentcollection.entity.IrpDocumentCollection;
import com.tfs.irp.util.PageUtil;

public interface IrpDocumentCollectionService {

	/**
	 * 添加收藏文档信息
	 * @param 收藏文档id
	 * @return
	 */
	public int insertFocus(Long documentid);
	/**
	 * 取消收藏
	 * @param documentid
	 * @return
	 */
	public int deleteFocus(Long documentid);
	/**
	 * 得到当前登录者的收藏数量
	 * @return
	 */
	public int myDocCollectionCount();
	/**
	 * 得到当前登录这收藏的所有文档
	 * @return
	 */
	public List<IrpChnlDocLink> myAllDocCollection(HashMap<String,Object> map,PageUtil pageUtil);
	/**
	 * 根据条件查询数量
	 * @param map
	 * @return
	 */
	public int count(HashMap<String,Object> map);
	/**
	 * 判断传入id是否被登录用户收藏
	 * @param _docid
	 * @return
	 */
	public int boolCollectByDocId(long _docid);
	
	/**
	 * 根据DOCID查询收藏数量
	 * @param _nDocId
	 * @return
	 */
	public int countByDocId(long _nDocId);
}
