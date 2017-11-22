package com.tfs.irp.managementoper.dao;

import com.tfs.irp.managementoper.entity.IrpManagementOper;
import com.tfs.irp.managementoper.entity.IrpManagementOperExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpManagementOperDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    int countByExample(IrpManagementOperExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    int deleteByExample(IrpManagementOperExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    int deleteByPrimaryKey(Long managementoperid) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    void insert(IrpManagementOper record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    void insertSelective(IrpManagementOper record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    List<IrpManagementOper> selectByExample(IrpManagementOperExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    IrpManagementOper selectByPrimaryKey(Long managementoperid) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    int updateByExampleSelective(IrpManagementOper record, IrpManagementOperExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    int updateByExample(IrpManagementOper record, IrpManagementOperExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    int updateByPrimaryKeySelective(IrpManagementOper record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    int updateByPrimaryKey(IrpManagementOper record) throws SQLException;
}