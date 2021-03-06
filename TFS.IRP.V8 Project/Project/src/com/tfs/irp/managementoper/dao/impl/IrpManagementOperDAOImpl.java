package com.tfs.irp.managementoper.dao.impl;

import com.tfs.irp.managementoper.dao.IrpManagementOperDAO;
import com.tfs.irp.managementoper.entity.IrpManagementOper;
import com.tfs.irp.managementoper.entity.IrpManagementOperExample;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpManagementOperDAOImpl extends SqlMapClientDaoSupport implements IrpManagementOperDAO {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public int countByExample(IrpManagementOperExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_MANAGEMENT_OPER.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public int deleteByExample(IrpManagementOperExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_MANAGEMENT_OPER.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public int deleteByPrimaryKey(Long managementoperid) throws SQLException {
        IrpManagementOper _key = new IrpManagementOper();
        _key.setManagementoperid(managementoperid);
        int rows = getSqlMapClientTemplate().delete("IRP_MANAGEMENT_OPER.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public void insert(IrpManagementOper record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_MANAGEMENT_OPER.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public void insertSelective(IrpManagementOper record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_MANAGEMENT_OPER.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpManagementOper> selectByExample(IrpManagementOperExample example) throws SQLException {
        List<IrpManagementOper> list = getSqlMapClientTemplate().queryForList("IRP_MANAGEMENT_OPER.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public IrpManagementOper selectByPrimaryKey(Long managementoperid) throws SQLException {
        IrpManagementOper _key = new IrpManagementOper();
        _key.setManagementoperid(managementoperid);
        IrpManagementOper record = (IrpManagementOper) getSqlMapClientTemplate().queryForObject("IRP_MANAGEMENT_OPER.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public int updateByExampleSelective(IrpManagementOper record, IrpManagementOperExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_MANAGEMENT_OPER.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public int updateByExample(IrpManagementOper record, IrpManagementOperExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_MANAGEMENT_OPER.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public int updateByPrimaryKeySelective(IrpManagementOper record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_MANAGEMENT_OPER.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    public int updateByPrimaryKey(IrpManagementOper record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_MANAGEMENT_OPER.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table IRP_MANAGEMENT_OPER
     *
     * @mbggenerated Thu Nov 07 13:57:02 CST 2013
     */
    protected static class UpdateByExampleParms extends IrpManagementOperExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpManagementOperExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}