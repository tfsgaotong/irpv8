package com.tfs.irp.tag.dao;

import com.tfs.irp.tag.entity.IrpUserTag;
import com.tfs.irp.tag.entity.IrpUserTagExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpUserTagDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	int countByExample(IrpUserTagExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	int deleteByExample(IrpUserTagExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	int deleteByPrimaryKey(Long usertagid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	void insert(IrpUserTag record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	void insertSelective(IrpUserTag record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	List<IrpUserTag> selectByExample(IrpUserTagExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	IrpUserTag selectByPrimaryKey(Long usertagid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	int updateByExampleSelective(IrpUserTag record, IrpUserTagExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	int updateByExample(IrpUserTag record, IrpUserTagExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	int updateByPrimaryKeySelective(IrpUserTag record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_TAG
	 * @ibatorgenerated  Thu Apr 25 14:26:24 CST 2013
	 */
	int updateByPrimaryKey(IrpUserTag record) throws SQLException;
}