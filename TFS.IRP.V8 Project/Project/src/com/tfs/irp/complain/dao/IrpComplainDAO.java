package com.tfs.irp.complain.dao;

import com.tfs.irp.complain.entity.IrpComplain;
import com.tfs.irp.complain.entity.IrpComplainExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IrpComplainDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    int countByExample(IrpComplainExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    int deleteByExample(IrpComplainExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    int deleteByPrimaryKey(Long complainid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    void insert(IrpComplain record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    void insertSelective(IrpComplain record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    List<IrpComplain> selectByExample(IrpComplainExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    IrpComplain selectByPrimaryKey(Long complainid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    int updateByExampleSelective(IrpComplain record, IrpComplainExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    int updateByExample(IrpComplain record, IrpComplainExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    int updateByPrimaryKeySelective(IrpComplain record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_COMPLAIN
     *
     * @ibatorgenerated Tue Sep 24 10:03:35 CST 2013
     */
    int updateByPrimaryKey(IrpComplain record) throws SQLException;
    
    public List<IrpComplain> selectByExample(Map<String, Object> map,
			PageUtil pageUtil);
    
}