package com.tfs.irp.leavechecker.dao;

import com.tfs.irp.leavechecker.entity.IrpCheckerLink;
import com.tfs.irp.leavechecker.entity.IrpCheckerLinkExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface IrpCheckerLinkDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    int countByExample(IrpCheckerLinkExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    int deleteByExample(IrpCheckerLinkExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    int deleteByPrimaryKey(Long checkerlinkid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    void insert(IrpCheckerLink record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    void insertSelective(IrpCheckerLink record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    List<IrpCheckerLink> selectByExample(IrpCheckerLinkExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    IrpCheckerLink selectByPrimaryKey(Long checkerlinkid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    int updateByExampleSelective(IrpCheckerLink record, IrpCheckerLinkExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    int updateByExample(IrpCheckerLink record, IrpCheckerLinkExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    int updateByPrimaryKeySelective(IrpCheckerLink record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    int updateByPrimaryKey(IrpCheckerLink record) throws SQLException;
}