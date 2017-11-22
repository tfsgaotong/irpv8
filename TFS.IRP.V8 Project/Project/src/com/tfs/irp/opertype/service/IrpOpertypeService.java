package com.tfs.irp.opertype.service;

import java.util.List;

import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.util.PageUtil;

public interface IrpOpertypeService {
	
	/**
	 * 全局设置 全部操作类型配置 传当前登录对象
	 * @param session
	 * @param pageUtil
	 * @param _oOrderby
	 * @return
	 */
	List<IrpOpertype> findOpertypeOfPage(PageUtil pageUtil,String _oOrderby,String _sSearchWord, String _sSearchType);
	/**
	 * 返回操作类型个数
	 * @return
	 */
	int findOpertypeCountByStatus(String _sSearchWord, String _sSearchType);
	/**
	 * 增加操作类型
	 * @param session
	 * @param irpOpertype
	 * @return
	 */
	int InsertOpertype(IrpOpertype irpOpertype);
	/**
	 * 修改操作类型 1:传id查操作类型 2:修改
	 * @param _opertypeId
	 * @return
	 */
	IrpOpertype irpOpertype(Long _opertypeId);
	int UpdateOpertype(Long _opertypeId,IrpOpertype irpOpertype);
	/**
	 * 删除操作类型 1:删除 2:处理页面传参
	 * @param _opertypeId
	 * @return
	 */
	int DeleteOpertype(Long _opertypeId);
	String []opertypeAllId(String _opertypeAllId);
	/**
	 * 检查操作名称是否存在
	 * @param _opername
	 * @return
	 */
	int findOpernameCountByStatus(String _opername);
	/**
	 * 根据操作类型找到操作名称
	 * @param _Opertype
	 * @return
	 */
	String findOpername(String _Opertype);
	
	/**
	 * 根据OperType模糊查询OperType集合
	 * @param string
	 * @return
	 */
	List<IrpOpertype> findOperTypeByLikeOperType(String _sOperType);
	
	/**
	 * 根据OperType查询OperType对象
	 * @param _sOperType
	 * @return
	 */
	IrpOpertype findOperTypeByOperType(String _sOperType);


	

	
}
