package com.tfs.irp.form.dao;

import com.tfs.irp.form.entity.IrpForm;
import com.tfs.irp.form.entity.IrpFormExample;
import com.tfs.irp.formcolumn.entity.IrpFormColumnExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IrpFormDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    int countByExample(IrpFormExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    int deleteByExample(IrpFormExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    int deleteByPrimaryKey(Long formid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    void insert(IrpForm record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    void insertSelective(IrpForm record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    List<IrpForm> selectByExampleWithBLOBs(IrpFormExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    List<IrpForm> selectByExampleWithoutBLOBs(IrpFormExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    IrpForm selectByPrimaryKey(Long formid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    int updateByExampleSelective(IrpForm record, IrpFormExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    int updateByExampleWithBLOBs(IrpForm record, IrpFormExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    int updateByExampleWithoutBLOBs(IrpForm record, IrpFormExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    int updateByPrimaryKeySelective(IrpForm record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(IrpForm record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    int updateByPrimaryKeyWithoutBLOBs(IrpForm record) throws SQLException;
    /**
     * 不带打字段查询的分页
     */
    List<IrpForm> selectByExampleWithoutBLOBsByPage(PageUtil _pageUtil,IrpFormExample example) throws SQLException;

	int createSql(String _sql);
	int dropTable(String _sql);

	int selectTableName(String formtablename);
	List<Object> selectFromInfo(String _sql)throws SQLException;
	int countBySql(String _sql)throws SQLException;
	/**
	 * 查询对象
	 * @param _sql
	 * @return
	 * @throws SQLException
	 */
	Object findObject(String _sql)throws SQLException;
	
	int countByColumn(IrpFormColumnExample example)throws SQLException;
	int deleteSql(String _sql);
	List<IrpForm> getAllForm(IrpFormExample example) throws SQLException;
}