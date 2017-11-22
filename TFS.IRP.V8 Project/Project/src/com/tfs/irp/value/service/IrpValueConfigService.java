package com.tfs.irp.value.service;

import java.util.List;

import com.tfs.irp.util.PageUtil;
import com.tfs.irp.value.entity.IrpValueConfig;

public interface IrpValueConfigService {

	/**
	 * 获得贡献配置项全部数据
	 * @return
	 */
	List<IrpValueConfig> findAllValueConfigOfPage(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType);
	/**
	 * 获得贡献配置项有多少数据
	 * @return
	 */
	int valueConfigListSize(String _sSearchWord,String _sSearchType);
	/**
	 * 增加贡献类型
	 * @return
	 */
	int valueConfigAdd(IrpValueConfig _irpValueConfig);
	/**
	 * 修改贡献类型
	 * @return
	 */
	int valueConfigUpdate(long _nValueid,IrpValueConfig _irpValueConfig);
	/**
	 * 删除贡献类型
	 * @return
	 */
	int valueConfigDelete(long _nValueid);
	/**
	 * 根据id查对象
	 * @return
	 */
	IrpValueConfig irpValueConfig(long _nValueid);
	/**
	 * 多选删除的id
	 * @return
	 */
	String []valueIdAll(String _sValueid);
	/**
	 * 判断valuekey是否唯一
	 * @return
	 */
	int boolValueKey(String _valueKey);
	
	/**
	 * 根据方法名称获得积分项
	 * @param _sMethodName
	 * @return
	 */
	List<IrpValueConfig> findValueConfigByMethodName(String _sMethodName);
	/**
	 * 根据valuekey获得irpvalueconfig 对象
	 * @return
	 */
	IrpValueConfig irpValueConfigByValueKey(String _valuekey);
	
}
