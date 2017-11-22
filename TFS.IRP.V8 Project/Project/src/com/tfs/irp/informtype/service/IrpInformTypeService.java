package com.tfs.irp.informtype.service;

import java.util.List;

import com.tfs.irp.informtype.entity.IrpInformType;
import com.tfs.irp.util.PageUtil;

public interface IrpInformTypeService {
/**
 * 查询所有的微知举报类型
 * @param pageUtil
 * @param _oOrderby
 * @param _cType
 * @param _sSearchWord
 * @param _sSearchType
 * @return
 */
	List<IrpInformType> findAllIrpInformTypePage(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType);
	/**
	 * 查看共有多少条记录
	 * @param _oOrderby
	 * @param _cType
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	int findAllIrpInformTypeCount(String _sSearchWord, String _sSearchType);
	/**
	 * 增加举报种类配置
	 * @param irpInformType
	 * @return
	 */
	int addInformType(IrpInformType _irpInformType);
	/**
	 * 查看举报类型配置是否已经存在
	 * 1存在    2不存在
	 * @param _informkey
	 * @return
	 */
	int searchInformType(String _informkey);
	/**
	 * 根据id删除某条记录
	 * @param _informtypeid
	 * @return
	 */
	int deleteInformTypeById(Long _informtypeid);
	/**
	 * 根据id找出某条举报种类配置
	 * @param _informtypeid
	 * @return
	 */
	IrpInformType irpInformType(Long _informtypeid);
	/**
	 * 根据id修改某条举报种类配置
	 * @param _informtypeid
	 * @return
	 */
	int updateInformType(Long _informtypeid,IrpInformType _irpInformType);
	/**
	 * 处理多选删除
	 * @param _configid
	 * @return
	 */
	String[] configIdAll(String _configid);
	/**
	 * 查看全部举报分类
	 * @return
	 */
	List<IrpInformType> findAllIrpInformType(Integer _informtype);
	/**
	 * 根据举报key获得举报对象
	 * @param _informtypeid
	 * @return
	 */
	IrpInformType irpInformType(String _informkey);
	/***
	 * 常用菜单
	 * @param pageutil  分页
	 * @param IrpInformType  类型常用为30
	 * @param _oOrderby    排序
	 * @param searchWord   搜索词
	 * @param searchType   搜索范围
	 * @return
	 */
	public List<IrpInformType> findOffenMenuIrpInformType(PageUtil pageutil,Integer IrpInformType,String _oOrderby,String searchWord,String searchType);
	/**
	 * 冲构造一个查询数据方法
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @param infoType类型
	 * @return
	 */
	public int findIrpInformTypeProjectMenuCount(String _sSearchWord, String _sSearchType, Integer infoType);
}
