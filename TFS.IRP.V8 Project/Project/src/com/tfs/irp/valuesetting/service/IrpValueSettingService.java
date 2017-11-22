package com.tfs.irp.valuesetting.service;

import java.util.List;

import com.tfs.irp.util.PageUtil;
import com.tfs.irp.valuesetting.entity.IrpValueSetting;

public interface IrpValueSettingService {

	/**
	 * 查找所有的级别配置项
	 * @return
	 */
	List<IrpValueSetting> getAllIrpValueSetting(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType);
	/**
	 * 查出来的数据共多少
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	 int searchValueSettingCount(String _sSearchWord, String _sSearchType);
	/**
	 * 添加级别
	 * @param _irpValueSetting
	 * @return
	 */
	int addIrpValueSetting(IrpValueSetting _irpValueSetting);
	/**
	 * 删除级别项
	 * @param _irpValueSetting
	 * @return
	 */
	int deleteIrpValueSetting(long _settingid);
	/**
	 * 更新级别项
	 * @param _irpValueSetting
	 * @return
	 */
	int updateIrpValueSetting(IrpValueSetting _irpValueSetting);
	/**
	 * 根据用户的总积分获得相应的级别
	 * @param _sumscore
	 * @return
	 */
	String findRanknameBySumScore(long _sumscore);
	/**
	 * 根据用户的总积分获得相应的级别描述
	 * @param _sumscore
	 * @return
	 */
	String findRankdescBySumScore(long _sumscore);
	/**
	 * 获得级别配置对象
	 * @return
	 */
	IrpValueSetting irpValueSetting(long _settingid);
	/**
	 * 根据分数获得相应的对象
	 * @param _score
	 * @return
	 */
	IrpValueSetting irpValueSettingOfGroupChannel(long _score);
	
	
	
	
}
