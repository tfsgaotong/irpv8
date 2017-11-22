package com.tfs.irp.binding.dao.impl;


import com.tfs.irp.binding.dao.IrpBindingDAO;
import com.tfs.irp.binding.entity.IrpBinding;
import com.tfs.irp.binding.entity.IrpBindingExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpBindingDAOImpl extends SqlMapClientDaoSupport implements IrpBindingDAO {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    /*private SqlMapClient sqlMapClient;*/

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
   /* public IrpBindingDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }*/

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public void insert(IrpBinding record) throws SQLException {
    	getSqlMapClientTemplate().insert("IRP_BINDING.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public int updateByPrimaryKey(IrpBinding record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_BINDING.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public int updateByPrimaryKeySelective(IrpBinding record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_BINDING.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public List<IrpBindingExample> selectallByExample(PageUtil pageUtil,IrpBindingExample example) throws SQLException {
        List<IrpBindingExample> list = getSqlMapClientTemplate().queryForList("IRP_BINDING.abatorgenerated_selectByExample", example,pageUtil.getPageIndex(),pageUtil.getPageSize());
        return list;
    }
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public List selectByExample(IrpBindingExample example) throws SQLException {
    	List list = getSqlMapClientTemplate().queryForList("IRP_BINDING.abatorgenerated_selectByExample", example);
    	return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public IrpBinding selectByPrimaryKey(Long bindingid) throws SQLException {
        IrpBinding key = new IrpBinding();
        key.setBindingid(bindingid);
        IrpBinding record = (IrpBinding) getSqlMapClientTemplate().queryForObject("IRP_BINDING.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public int deleteByExample(IrpBindingExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_BINDING.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public int deleteByPrimaryKey(Long bindingid) throws SQLException {
        IrpBinding key = new IrpBinding();
        key.setBindingid(bindingid);
        int rows = getSqlMapClientTemplate().delete("IRP_BINDING.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public int countByExample(IrpBindingExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_BINDING.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public int updateByExampleSelective(IrpBinding record, IrpBindingExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_BINDING.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    public int updateByExample(IrpBinding record, IrpBindingExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_BINDING.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    private static class UpdateByExampleParms extends IrpBindingExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpBindingExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}