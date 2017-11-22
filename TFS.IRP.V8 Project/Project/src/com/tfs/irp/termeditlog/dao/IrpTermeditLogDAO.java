package com.tfs.irp.termeditlog.dao;

import com.tfs.irp.termeditlog.entity.IrpTermeditLog;
import com.tfs.irp.termeditlog.entity.IrpTermeditLogExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface IrpTermeditLogDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    int countByExample(IrpTermeditLogExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    int deleteByExample(IrpTermeditLogExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    int deleteByPrimaryKey(Long termeditlogid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    void insert(IrpTermeditLog record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    void insertSelective(IrpTermeditLog record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     * @param pageUtil 
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    List<IrpTermeditLog> selectByExample(IrpTermeditLogExample example, PageUtil pageUtil) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    IrpTermeditLog selectByPrimaryKey(Long termeditlogid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    int updateByExampleSelective(IrpTermeditLog record, IrpTermeditLogExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    int updateByExample(IrpTermeditLog record, IrpTermeditLogExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    int updateByPrimaryKeySelective(IrpTermeditLog record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERMEDIT_LOG
     *
     * @ibatorgenerated Tue Oct 10 17:36:42 CST 2017
     */
    int updateByPrimaryKey(IrpTermeditLog record) throws SQLException;
}