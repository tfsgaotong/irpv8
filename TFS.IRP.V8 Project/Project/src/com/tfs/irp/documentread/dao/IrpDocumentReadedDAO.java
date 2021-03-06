package com.tfs.irp.documentread.dao;

import com.tfs.irp.documentread.entity.IrpDocumentReaded;
import com.tfs.irp.documentread.entity.IrpDocumentReadedExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpDocumentReadedDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_READED
     *
     * @ibatorgenerated Thu Jul 25 14:13:30 CST 2013
     */
    int countByExample(IrpDocumentReadedExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_READED
     *
     * @ibatorgenerated Thu Jul 25 14:13:30 CST 2013
     */
    int deleteByExample(IrpDocumentReadedExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_READED
     *
     * @ibatorgenerated Thu Jul 25 14:13:30 CST 2013
     */
    void insert(IrpDocumentReaded record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_READED
     *
     * @ibatorgenerated Thu Jul 25 14:13:30 CST 2013
     */
    void insertSelective(IrpDocumentReaded record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_READED
     *
     * @ibatorgenerated Thu Jul 25 14:13:30 CST 2013
     */
    List<IrpDocumentReaded> selectByExample(IrpDocumentReadedExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_READED
     *
     * @ibatorgenerated Thu Jul 25 14:13:30 CST 2013
     */
    int updateByExampleSelective(IrpDocumentReaded record, IrpDocumentReadedExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_READED
     *
     * @ibatorgenerated Thu Jul 25 14:13:30 CST 2013
     */
    int updateByExample(IrpDocumentReaded record, IrpDocumentReadedExample example) throws SQLException;
}