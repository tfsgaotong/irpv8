package com.tfs.irp.subscribe.dao;

import com.tfs.irp.subscribe.entity.IrpSubscribe;
import com.tfs.irp.subscribe.entity.IrpSubscribeExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface IrpSubscribeDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    int countByExample(IrpSubscribeExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    int deleteByExample(IrpSubscribeExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    int deleteByPrimaryKey(Long subscribeid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    void insert(IrpSubscribe record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    void insertSelective(IrpSubscribe record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     * @param pageUtil 
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    List<IrpSubscribe> selectByExample(IrpSubscribeExample example, PageUtil pageUtil) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    IrpSubscribe selectByPrimaryKey(Long subscribeid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    int updateByExampleSelective(IrpSubscribe record, IrpSubscribeExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    int updateByExample(IrpSubscribe record, IrpSubscribeExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    int updateByPrimaryKeySelective(IrpSubscribe record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    int updateByPrimaryKey(IrpSubscribe record) throws SQLException;
}