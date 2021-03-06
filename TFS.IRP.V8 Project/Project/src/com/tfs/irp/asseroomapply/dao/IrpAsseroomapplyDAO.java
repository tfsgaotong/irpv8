package com.tfs.irp.asseroomapply.dao;

import com.tfs.irp.asseroomapply.entity.IrpAsseroomapply;
import com.tfs.irp.asseroomapply.entity.IrpAsseroomapplyExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface IrpAsseroomapplyDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	int countByExample(IrpAsseroomapplyExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	int deleteByExample(IrpAsseroomapplyExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	int deleteByPrimaryKey(Long asseroomapplyid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	void insert(IrpAsseroomapply record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	void insertSelective(IrpAsseroomapply record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	List<IrpAsseroomapply> selectByExample(IrpAsseroomapplyExample example)
			throws SQLException;
	List<IrpAsseroomapply> selectByExample(IrpAsseroomapplyExample example,PageUtil page)
			throws SQLException;


	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	IrpAsseroomapply selectByPrimaryKey(Long asseroomapplyid)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	int updateByExampleSelective(IrpAsseroomapply record,
			IrpAsseroomapplyExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	int updateByExample(IrpAsseroomapply record, IrpAsseroomapplyExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	int updateByPrimaryKeySelective(IrpAsseroomapply record)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOMAPPLY
	 * @ibatorgenerated  Tue Sep 06 10:14:48 CST 2016
	 */
	int updateByPrimaryKey(IrpAsseroomapply record) throws SQLException;

}