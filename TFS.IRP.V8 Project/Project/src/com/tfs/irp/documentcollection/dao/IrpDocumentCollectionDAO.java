package com.tfs.irp.documentcollection.dao;

import com.tfs.irp.documentcollection.entity.IrpDocumentCollection;
import com.tfs.irp.documentcollection.entity.IrpDocumentCollectionExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface IrpDocumentCollectionDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT_COLLECTION
	 * @ibatorgenerated  Wed May 15 16:20:12 CST 2013
	 */
	int countByExample(IrpDocumentCollectionExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT_COLLECTION
	 * @ibatorgenerated  Wed May 15 16:20:12 CST 2013
	 */
	int deleteByExample(IrpDocumentCollectionExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT_COLLECTION
	 * @ibatorgenerated  Wed May 15 16:20:12 CST 2013
	 */
	void insert(IrpDocumentCollection record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT_COLLECTION
	 * @ibatorgenerated  Wed May 15 16:20:12 CST 2013
	 */
	void insertSelective(IrpDocumentCollection record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT_COLLECTION
	 * @ibatorgenerated  Wed May 15 16:20:12 CST 2013
	 */
	List<IrpDocumentCollection> selectByExample(
			IrpDocumentCollectionExample example) throws SQLException;

	List<IrpDocumentCollection> selectByExample(
			IrpDocumentCollectionExample example,PageUtil pageUtil) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT_COLLECTION
	 * @ibatorgenerated  Wed May 15 16:20:12 CST 2013
	 */
	int updateByExampleSelective(IrpDocumentCollection record,
			IrpDocumentCollectionExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT_COLLECTION
	 * @ibatorgenerated  Wed May 15 16:20:12 CST 2013
	 */
	int updateByExample(IrpDocumentCollection record,
			IrpDocumentCollectionExample example) throws SQLException;
/**
 * 查询docids
 * @param example
 * @param pageUtil
 * @return
 * @throws SQLException
 */
	List<Long> selectPrimaryKeyByExample(IrpDocumentCollectionExample example,
			PageUtil pageUtil) throws SQLException; 
}