package com.tfs.irp.schedule.dao;

import com.tfs.irp.schedule.entity.IrpSchedule;
import com.tfs.irp.schedule.entity.IrpScheduleExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpScheduleDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    int countByExample(IrpScheduleExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    int deleteByExample(IrpScheduleExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    int deleteByPrimaryKey(Long schid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    void insert(IrpSchedule record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    void insertSelective(IrpSchedule record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    List<IrpSchedule> selectByExample(IrpScheduleExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    IrpSchedule selectByPrimaryKey(Long schid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    int updateByExampleSelective(IrpSchedule record, IrpScheduleExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    int updateByExample(IrpSchedule record, IrpScheduleExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    int updateByPrimaryKeySelective(IrpSchedule record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_SCHEDULE
     *
     * @ibatorgenerated Thu Mar 13 17:51:04 CST 2014
     */
    int updateByPrimaryKey(IrpSchedule record) throws SQLException;
}