package com.tfs.irp.userlove.service;

import java.util.List;

import com.tfs.irp.userlove.entity.IrpUserLove;

public interface IrpUserLoveService {
	/**
	 * 添加记录(保存猜你喜欢)
	 * @param userId
	 * @param docid
	 */
	public void save(Long userId,Long docid ,Integer status);
	/**
	 * 保存相关知识的逻辑记录
	 * @param userId
	 * @param docid
	 * @param xDocid
	 * @param status
	 */
	public void save(Long userId,Long docid,Long xDocid,Integer status);
	/**
	 * 得到知识ID
	 * @param userId
	 * @return
	 */
	public List<Long> docIdsByUserid(Long userId,Integer status);
	
	/***
	 * 得到对象(猜你喜欢)
	 */
	public List<IrpUserLove> allData(Long userId,Integer status);
	/***
	 * 得到对象 
	 */
	public List<IrpUserLove> allData(Long userId,Long docId,Integer status);
/**
 * 删除用户的喜欢
 */
	public void delteDocByUserIds(Long userId,Integer status);
	
	public void deletedoc(List<IrpUserLove> irpUserLoves);
	/**
	 * 查询 相关知识的docid
	 * @param userId
	 * @param status
	 * @param docId
	 * @return
	 */
	public List<Long> xDocids(Long userId,Integer status,Long docId);
	void delteDocByUserIds(Long userId, Long docId, Integer status);
}
