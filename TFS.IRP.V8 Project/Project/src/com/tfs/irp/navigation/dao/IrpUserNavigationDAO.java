package com.tfs.irp.navigation.dao;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.navigation.entity.IrpUserNavigation;
import com.tfs.irp.navigation.entity.IrpUserNavigationExample;
import com.tfs.irp.util.PageUtil;

public interface IrpUserNavigationDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    int countByExample(IrpUserNavigationExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    int deleteByExample(IrpUserNavigationExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    int deleteByPrimaryKey(Long navigationid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    void insert(IrpUserNavigation record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    void insertSelective(IrpUserNavigation record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    List<IrpUserNavigation> selectByExample(IrpUserNavigationExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    IrpUserNavigation selectByPrimaryKey(Long navigationid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    int updateByExampleSelective(IrpUserNavigation record, IrpUserNavigationExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    int updateByExample(IrpUserNavigation record, IrpUserNavigationExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    int updateByPrimaryKeySelective(IrpUserNavigation record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    int updateByPrimaryKey(IrpUserNavigation record) throws SQLException;
    /**
     * 带分页的查询
     * @param example
     * @param _pageUtil
     * @return
     * @throws SQLException
     */
    List<IrpUserNavigation> selectByExample(IrpUserNavigationExample example,PageUtil _pageUtil) throws SQLException;
}