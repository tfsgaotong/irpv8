package com.tfs.irp.apptype.dao.impl;

import com.tfs.irp.apptype.dao.IrpApptypeDAO;
import com.tfs.irp.apptype.entity.IrpApptype;
import com.tfs.irp.apptype.entity.IrpApptypeExample;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpApptypeDAOImpl extends SqlMapClientDaoSupport implements IrpApptypeDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public int countByExample(IrpApptypeExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_APP_TYPE.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public int deleteByExample(IrpApptypeExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_APP_TYPE.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public int deleteByPrimaryKey(Long appypeid) throws SQLException {
        IrpApptype key = new IrpApptype();
        key.setAppypeid(appypeid);
        int rows = getSqlMapClientTemplate().delete("IRP_APP_TYPE.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public void insert(IrpApptype record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_APP_TYPE.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public void insertSelective(IrpApptype record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_APP_TYPE.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpApptype> selectByExample(IrpApptypeExample example) throws SQLException {
        List<IrpApptype> list = getSqlMapClientTemplate().queryForList("IRP_APP_TYPE.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public IrpApptype selectByPrimaryKey(Long appypeid) throws SQLException {
        IrpApptype key = new IrpApptype();
        key.setAppypeid(appypeid);
        IrpApptype record = (IrpApptype) getSqlMapClientTemplate().queryForObject("IRP_APP_TYPE.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public int updateByExampleSelective(IrpApptype record, IrpApptypeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_APP_TYPE.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public int updateByExample(IrpApptype record, IrpApptypeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_APP_TYPE.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public int updateByPrimaryKeySelective(IrpApptype record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_APP_TYPE.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    public int updateByPrimaryKey(IrpApptype record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_APP_TYPE.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_APP_TYPE
     *
     * @ibatorgenerated Sun Sep 29 15:23:10 CST 2013
     */
    private static class UpdateByExampleParms extends IrpApptypeExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpApptypeExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}