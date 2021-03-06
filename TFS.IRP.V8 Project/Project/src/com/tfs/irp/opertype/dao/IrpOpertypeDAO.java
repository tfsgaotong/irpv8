package com.tfs.irp.opertype.dao;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.opertype.entity.IrpOpertypeExample;
import com.tfs.irp.util.PageUtil;

public interface IrpOpertypeDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	int countByExample(IrpOpertypeExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	int deleteByExample(IrpOpertypeExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	int deleteByPrimaryKey(Long opertypeid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	void insert(IrpOpertype record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	void insertSelective(IrpOpertype record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	List<IrpOpertype> selectByExample(IrpOpertypeExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	IrpOpertype selectByPrimaryKey(Long opertypeid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	int updateByExampleSelective(IrpOpertype record, IrpOpertypeExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	int updateByExample(IrpOpertype record, IrpOpertypeExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	int updateByPrimaryKeySelective(IrpOpertype record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_OPERTYPE
	 * @ibatorgenerated  Sat Apr 13 16:02:30 CST 2013
	 */
	int updateByPrimaryKey(IrpOpertype record) throws SQLException;

	List<IrpOpertype> selectByExample(IrpOpertypeExample example,PageUtil pageUtil)
			throws SQLException;
}