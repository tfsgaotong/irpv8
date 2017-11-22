package com.tfs.irp.config.service;

import java.util.List;

import com.tfs.irp.config.entity.IrpConfig;
import com.tfs.irp.util.PageUtil;

public interface IrpConfigService {

	/**
	 * 加载config表的内容  ctype= 存放目录
	 * @param pageUtil
	 * @param _oOrderby
	 * @param session
	 * @param _cType
	 * @return
	 */
	List<IrpConfig> findAllOfPage(PageUtil pageUtil,String _oOrderby,Integer _cType, String _sSearchWord, String _sSearchType);
	/**
	 * 获得当前的存放目录总数
	 * @return
	 */
	int IrpConfigCount(Integer _nCType,String _sSearchWord, String _sSearchType);
	/**
	 * 增加配置类型
	 * @param irpConfig
	 * @return
	 */
	int addConfigCatalogue(IrpConfig irpConfig,Integer _nCType);
	/**
	 * 根据用户的输入返回存放目录的ckey 名字
	 * @param _ckey
	 * @return
	 */
	int findConfigCataCkey(String _ckey);
	/**
	 * 根据configid 查出相应的记录
	 * @return
	 */
	IrpConfig irpConfig(Long _configid);
	/**
	 * 修改存放目录
	 * @param irpConfig
	 * @param _configid
	 * @return
	 */
	int updateConfigCatalogue(IrpConfig irpConfig,Long _configid,Integer _nCtype);
	/**
	 * 删除存放目录
	 * @param _configid
	 * @return
	 */
	int deleteConfigCatalogue(Long _configid,Integer _nCtype);
	/**
	 * 获得页面多选删除的参数
	 * @return
	 */
	String []configIdAll(String _configid);
	/**
	 * 通过key取value
	 * @param _ckey
	 * @return
	 */
	String selectCValueByCKey(String _ckey);
	
}
