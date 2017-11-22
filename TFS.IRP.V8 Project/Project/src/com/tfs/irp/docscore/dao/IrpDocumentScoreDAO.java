package com.tfs.irp.docscore.dao;

import com.tfs.irp.docscore.entity.IrpDocumentScore;
import com.tfs.irp.docscore.entity.IrpDocumentScoreExample;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface IrpDocumentScoreDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    int countByExample(IrpDocumentScoreExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    int deleteByExample(IrpDocumentScoreExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    void insert(IrpDocumentScore record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    void insertSelective(IrpDocumentScore record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    List<IrpDocumentScore> selectByExample(IrpDocumentScoreExample example) throws SQLException;
    /**
     * 查询
     * @param docid
     * @return
     * @throws SQLException
     */
    HashMap avgScore(Long docid)throws SQLException;
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    int updateByExampleSelective(IrpDocumentScore record, IrpDocumentScoreExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    int updateByExample(IrpDocumentScore record, IrpDocumentScoreExample example) throws SQLException;
}