package com.tfs.irp.workflow.dao.impl;

import com.tfs.irp.util.PageUtil;
import com.tfs.irp.workflow.dao.IrpWorkflowDAO;
import com.tfs.irp.workflow.entity.IrpWorkflow;
import com.tfs.irp.workflow.entity.IrpWorkflowExample;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpWorkflowDAOImpl extends SqlMapClientDaoSupport implements IrpWorkflowDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int countByExample(IrpWorkflowExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_WORKFLOW.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int deleteByExample(IrpWorkflowExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_WORKFLOW.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int deleteByPrimaryKey(Long flowid) throws SQLException {
        IrpWorkflow key = new IrpWorkflow();
        key.setFlowid(flowid);
        int rows = getSqlMapClientTemplate().delete("IRP_WORKFLOW.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public void insert(IrpWorkflow record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_WORKFLOW.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public void insertSelective(IrpWorkflow record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_WORKFLOW.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpWorkflow> selectByExample(IrpWorkflowExample example) throws SQLException {
        List<IrpWorkflow> list = getSqlMapClientTemplate().queryForList("IRP_WORKFLOW.ibatorgenerated_selectByExample", example);
        return list;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<IrpWorkflow> selectByExample(IrpWorkflowExample example, PageUtil pageUtil) throws SQLException {
        List<IrpWorkflow> list = getSqlMapClientTemplate().queryForList("IRP_WORKFLOW.ibatorgenerated_selectByExample", example, pageUtil.getPageIndex(), pageUtil.getPageSize());
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public IrpWorkflow selectByPrimaryKey(Long flowid) throws SQLException {
        IrpWorkflow key = new IrpWorkflow();
        key.setFlowid(flowid);
        IrpWorkflow record = (IrpWorkflow) getSqlMapClientTemplate().queryForObject("IRP_WORKFLOW.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int updateByExampleSelective(IrpWorkflow record, IrpWorkflowExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_WORKFLOW.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int updateByExample(IrpWorkflow record, IrpWorkflowExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_WORKFLOW.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int updateByPrimaryKeySelective(IrpWorkflow record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_WORKFLOW.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int updateByPrimaryKey(IrpWorkflow record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_WORKFLOW.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_WORKFLOW
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    private static class UpdateByExampleParms extends IrpWorkflowExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpWorkflowExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}