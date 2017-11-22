package com.tfs.irp.userRandom.dao;

import com.tfs.irp.userRandom.entity.IrpUserrandom;
import com.tfs.irp.userRandom.entity.IrpUserrandomExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpUserrandomDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    int countByExample(IrpUserrandomExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    int deleteByExample(IrpUserrandomExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    int deleteByPrimaryKey(Long randomid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    void insert(IrpUserrandom record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    void insertSelective(IrpUserrandom record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    List<IrpUserrandom> selectByExample(IrpUserrandomExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    IrpUserrandom selectByPrimaryKey(Long randomid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    int updateByExampleSelective(IrpUserrandom record, IrpUserrandomExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    int updateByExample(IrpUserrandom record, IrpUserrandomExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    int updateByPrimaryKeySelective(IrpUserrandom record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USERRANDOM
     *
     * @ibatorgenerated Wed Sep 25 15:31:22 CST 2013
     */
    int updateByPrimaryKey(IrpUserrandom record) throws SQLException;
}