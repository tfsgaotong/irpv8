package com.tfs.irp.docversion.dao;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.docversion.entity.IrpDocversion;
import com.tfs.irp.docversion.entity.IrpDocversionExample;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.docversion.entity.IrpDocversionWithBLOBs;

public interface IrpDocversionDAO {
    /**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	int countByExample(IrpDocversionExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	int deleteByExample(IrpDocversionExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	int deleteByPrimaryKey(Long docid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	void insert(IrpDocversionWithBLOBs record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	void insertSelective(IrpDocversionWithBLOBs record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	List<IrpDocversionWithBLOBs> selectByExampleWithBLOBs(
			IrpDocversionExample example) throws SQLException;
	List<IrpDocversionWithBLOBs> selectByExampleWithBLOBs(
			IrpDocversionExample example,PageUtil pageutil) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	List<IrpDocversion> selectByExampleWithoutBLOBs(IrpDocversionExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	IrpDocversionWithBLOBs selectByPrimaryKey(Long docid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	int updateByExampleSelective(IrpDocversionWithBLOBs record,
			IrpDocversionExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	int updateByExample(IrpDocversionWithBLOBs record,
			IrpDocversionExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	int updateByExample(IrpDocversion record, IrpDocversionExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	int updateByPrimaryKeySelective(IrpDocversionWithBLOBs record)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	int updateByPrimaryKey(IrpDocversionWithBLOBs record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_docversion
	 * @ibatorgenerated  Fri Jul 25 10:16:33 CST 2014
	 */
	int updateByPrimaryKey(IrpDocversion record) throws SQLException;
}