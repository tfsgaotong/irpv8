package com.tfs.irp.valuesetting.dao;

import com.tfs.irp.util.PageUtil;
import com.tfs.irp.valuesetting.entity.IrpValueSetting;
import com.tfs.irp.valuesetting.entity.IrpValueSettingExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpValueSettingDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	int countByExample(IrpValueSettingExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	int deleteByExample(IrpValueSettingExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	int deleteByPrimaryKey(Long settingid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	void insert(IrpValueSetting record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	void insertSelective(IrpValueSetting record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	List<IrpValueSetting> selectByExample(IrpValueSettingExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	IrpValueSetting selectByPrimaryKey(Long settingid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	int updateByExampleSelective(IrpValueSetting record,
			IrpValueSettingExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	int updateByExample(IrpValueSetting record, IrpValueSettingExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	int updateByPrimaryKeySelective(IrpValueSetting record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VALUE_SETTING
	 * @ibatorgenerated  Thu Jul 11 15:59:28 CST 2013
	 */
	int updateByPrimaryKey(IrpValueSetting record) throws SQLException;
	
	/**
	 * 根据分数获得相应的称号
	 * @param _sumscore
	 * @return
	 */
	String findRankNameBySumScore(long _sumscore)throws SQLException;
	/**
	 * 根据分数获得相应的描述
	 * @param _sumscore
	 * @return
	 */
	String findDescNameBySumScore(long _sumscore)throws SQLException;
	/**
	 * 分页显示数据
	 * @param example
	 * @param pageUtil
	 * @return
	 * @throws SQLException
	 */
	public List<IrpValueSetting> selectByExample(IrpValueSettingExample example,PageUtil pageUtil)
			throws SQLException;
}