package com.tfs.irp.bug_config.dao;

import com.tfs.irp.bug_config.entity.IrpBugConfig;
import com.tfs.irp.bug_config.entity.IrpBugConfigExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpBugConfigDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    int countByExample(IrpBugConfigExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    int deleteByExample(IrpBugConfigExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    int deleteByPrimaryKey(Long bugconfigid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    void insert(IrpBugConfig record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    void insertSelective(IrpBugConfig record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    List<IrpBugConfig> selectByExample(IrpBugConfigExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    IrpBugConfig selectByPrimaryKey(Long bugconfigid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    int updateByExampleSelective(IrpBugConfig record, IrpBugConfigExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    int updateByExample(IrpBugConfig record, IrpBugConfigExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    int updateByPrimaryKeySelective(IrpBugConfig record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_BUG_CONFIG
     *
     * @ibatorgenerated Thu Jan 21 15:14:13 CST 2016
     */
    int updateByPrimaryKey(IrpBugConfig record) throws SQLException;
}