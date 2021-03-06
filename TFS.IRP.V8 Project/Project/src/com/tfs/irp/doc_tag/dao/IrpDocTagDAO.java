package com.tfs.irp.doc_tag.dao;

import com.tfs.irp.doc_tag.entity.IrpDocTag;
import com.tfs.irp.doc_tag.entity.IrpDocTagExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpDocTagDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    int countByExample(IrpDocTagExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    int deleteByExample(IrpDocTagExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    int deleteByPrimaryKey(Long doctagid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    void insert(IrpDocTag record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    void insertSelective(IrpDocTag record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    List<IrpDocTag> selectByExample(IrpDocTagExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    IrpDocTag selectByPrimaryKey(Long doctagid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    int updateByExampleSelective(IrpDocTag record, IrpDocTagExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    int updateByExample(IrpDocTag record, IrpDocTagExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    int updateByPrimaryKeySelective(IrpDocTag record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOC_TAG
     *
     * @ibatorgenerated Mon Apr 22 09:11:56 CST 2013
     */
    int updateByPrimaryKey(IrpDocTag record) throws SQLException;
}