package com.tfs.irp.config.dao;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.config.entity.IrpConfig;
import com.tfs.irp.config.entity.IrpConfigExample;
import com.tfs.irp.util.PageUtil;

public interface IrpConfigDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    int countByExample(IrpConfigExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    int deleteByExample(IrpConfigExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    int deleteByPrimaryKey(Long configid);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    void insert(IrpConfig record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    void insertSelective(IrpConfig record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    List<IrpConfig> selectByExample(IrpConfigExample example) throws SQLException;
    List<IrpConfig> selectByExample(IrpConfigExample example,PageUtil pageUtil) throws SQLException;
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    IrpConfig selectByPrimaryKey(Long configid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    int updateByExampleSelective(IrpConfig record, IrpConfigExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    int updateByExample(IrpConfig record, IrpConfigExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    int updateByPrimaryKeySelective(IrpConfig record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP.IRP_CONFIG
     *
     * @ibatorgenerated Thu Mar 21 15:25:53 CST 2013
     */
    int updateByPrimaryKey(IrpConfig record) throws SQLException;
    
    String selectCValueByCKey(String _sCKey) throws SQLException;
}