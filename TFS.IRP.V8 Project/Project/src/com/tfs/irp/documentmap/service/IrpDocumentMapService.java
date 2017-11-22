package com.tfs.irp.documentmap.service;

import java.util.List;

import com.tfs.irp.documentmap.entity.IrpDocumentMap;
import com.tfs.irp.documentmap.entity.IrpDocumentMapExample;

public interface IrpDocumentMapService {
	
	/**
	 * 添加知识分类记录
	 * @param docid
	 * @param channelid
	 */
	public void addDocumentMap(Long docid,Long channelid,String type);
	/**
	 * 查询某一个知识的分类记录 
	 */
	public List<IrpDocumentMap> oneDocDocumentMap(Long docid,String type);
	/**
	 * 删除知识分类
	 */
	public void deltetDocumentMap(Long PrimaryKey);
	
	 /**
	  *  根据所属栏目id查询知识id
	  * @param channelid
	  * @return
	  */
	 public List<Long> docidsByChannelid(Long channelid);
	/**
	 * 批量删除某个分类下的知识
	 */
	public int deleteDocumentByMapId(Long channelid,List<Long> docids);
	/**
	 *查找是否存在这个对象
	 * @param channelid
	 * @param docid
	 * @return
	 */
	public IrpDocumentMap findDocumentMap(Long channelid,Long docid,String type);
	
	
	public List<Long> docidsByChannelidS(List<Long> _channelids,String type);
	 /**
	  *  根据所属栏目id查询知识id
	  * @param channelid
	  * @return
	  */
	 public List<Long> docidsByChannelid(Long channelid,List<Long> docids);
	 /**
	  * 根据例子查找
	  * @param example
	  * @return
	  */
	 public List<IrpDocumentMap> selectMapByExample(IrpDocumentMapExample example);
	 /**
	  * 通过例子删除对象
	  * @param example
	  * @return
	  */
	 public int deleteDocSubByExample(IrpDocumentMapExample example);
	 
	 /**
	  * 通过例子添加对象
	  * @param map
	  */
	 public void addDocSubByExample(IrpDocumentMap map);
}
