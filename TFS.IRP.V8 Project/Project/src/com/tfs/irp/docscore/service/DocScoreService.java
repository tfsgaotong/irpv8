package com.tfs.irp.docscore.service;

import java.util.HashMap;
import java.util.List;

import com.tfs.irp.docscore.entity.IrpDocumentScore;

public interface DocScoreService {
	/**
	 * 添加评分
	 * @param score
	 */
	int addscore(Long docid,Long score);
	/**
	 * 获取某个人评分对象
	 * @param docid
	 * @param userid
	 * @return
	 */
	IrpDocumentScore findPersonScore(Long docid,Long userid);
	/**
	 * 查询某个知识的平均评分和他的评分人数
	 * @param docid
	 * @return
	 */
	HashMap  findAvgByDocid(Long docid);
	
	List<IrpDocumentScore> allDatas();
	/**
	 * 查询所有评分条数
	 * @return
	 */
	long getAllCount();

}
