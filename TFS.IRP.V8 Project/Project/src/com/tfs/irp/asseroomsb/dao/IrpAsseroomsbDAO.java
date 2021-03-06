package com.tfs.irp.asseroomsb.dao;

import com.tfs.irp.asseroomsb.entity.IrpAsseroomsb;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface IrpAsseroomsbDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	int countByExample(IrpAsseroomsbExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	int deleteByExample(IrpAsseroomsbExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	int deleteByPrimaryKey(Long asseroomsbid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	void insert(IrpAsseroomsb record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	void insertSelective(IrpAsseroomsb record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	List<IrpAsseroomsb> selectByExample(IrpAsseroomsbExample example,PageUtil page)
			throws SQLException;
	List<IrpAsseroomsb> selectByExample(IrpAsseroomsbExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	IrpAsseroomsb selectByPrimaryKey(Long asseroomsbid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	int updateByExampleSelective(IrpAsseroomsb record,
			IrpAsseroomsbExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	int updateByExample(IrpAsseroomsb record, IrpAsseroomsbExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	int updateByPrimaryKeySelective(IrpAsseroomsb record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMSB
	 * @ibatorgenerated  Tue Aug 23 14:25:05 CST 2016
	 */
	int updateByPrimaryKey(IrpAsseroomsb record) throws SQLException;
}