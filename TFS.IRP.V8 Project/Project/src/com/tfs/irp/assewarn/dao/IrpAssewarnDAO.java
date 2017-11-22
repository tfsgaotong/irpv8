package com.tfs.irp.assewarn.dao;

import com.tfs.irp.assewarn.entity.IrpAssewarn;
import com.tfs.irp.assewarn.entity.IrpAssewarnExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpAssewarnDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	int countByExample(IrpAssewarnExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	int deleteByExample(IrpAssewarnExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	int deleteByPrimaryKey(Long warnid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	void insert(IrpAssewarn record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	void insertSelective(IrpAssewarn record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	List<IrpAssewarn> selectByExample(IrpAssewarnExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	IrpAssewarn selectByPrimaryKey(Long warnid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	int updateByExampleSelective(IrpAssewarn record, IrpAssewarnExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	int updateByExample(IrpAssewarn record, IrpAssewarnExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	int updateByPrimaryKeySelective(IrpAssewarn record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	int updateByPrimaryKey(IrpAssewarn record) throws SQLException;
}