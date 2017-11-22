package com.tfs.irp.attachedtype.dao;

import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.entity.IrpAttachedTypeExample;
import java.sql.SQLException;
import java.util.List;

public interface IrpAttachedTypeDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    int countByExample(IrpAttachedTypeExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    int deleteByExample(IrpAttachedTypeExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    void insert(IrpAttachedType record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    void insertSelective(IrpAttachedType record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    List<IrpAttachedType> selectByExample(IrpAttachedTypeExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    int updateByExampleSelective(IrpAttachedType record, IrpAttachedTypeExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    int updateByExample(IrpAttachedType record, IrpAttachedTypeExample example) throws SQLException;
    /**
     * 根据主键得到附件类型
     */
    Object AttachedTypeSuffixImage(Long _typeid)throws SQLException ;
    /**
     * 查询所有附件类型
     */
	List<IrpAttachedType> getSuffixExits(String _suffix) throws SQLException;
    
}