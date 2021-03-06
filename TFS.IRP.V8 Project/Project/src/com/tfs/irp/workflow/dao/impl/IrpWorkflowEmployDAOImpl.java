package com.tfs.irp.workflow.dao.impl;

import com.tfs.irp.workflow.dao.IrpWorkflowEmployDAO;
import com.tfs.irp.workflow.entity.IrpWorkflowEmploy;
import com.tfs.irp.workflow.entity.IrpWorkflowEmployExample;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpWorkflowEmployDAOImpl extends SqlMapClientDaoSupport implements IrpWorkflowEmployDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int countByExample(IrpWorkflowEmployExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_WORKFLOW_EMPLOY.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int deleteByExample(IrpWorkflowEmployExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_WORKFLOW_EMPLOY.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int deleteByPrimaryKey(Long employid) throws SQLException {
        IrpWorkflowEmploy key = new IrpWorkflowEmploy();
        key.setEmployid(employid);
        int rows = getSqlMapClientTemplate().delete("IRP_WORKFLOW_EMPLOY.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public void insert(IrpWorkflowEmploy record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_WORKFLOW_EMPLOY.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public void insertSelective(IrpWorkflowEmploy record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_WORKFLOW_EMPLOY.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpWorkflowEmploy> selectByExample(IrpWorkflowEmployExample example) throws SQLException {
        List<IrpWorkflowEmploy> list = getSqlMapClientTemplate().queryForList("IRP_WORKFLOW_EMPLOY.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public IrpWorkflowEmploy selectByPrimaryKey(Long employid) throws SQLException {
        IrpWorkflowEmploy key = new IrpWorkflowEmploy();
        key.setEmployid(employid);
        IrpWorkflowEmploy record = (IrpWorkflowEmploy) getSqlMapClientTemplate().queryForObject("IRP_WORKFLOW_EMPLOY.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int updateByExampleSelective(IrpWorkflowEmploy record, IrpWorkflowEmployExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_WORKFLOW_EMPLOY.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int updateByExample(IrpWorkflowEmploy record, IrpWorkflowEmployExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_WORKFLOW_EMPLOY.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int updateByPrimaryKeySelective(IrpWorkflowEmploy record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_WORKFLOW_EMPLOY.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    public int updateByPrimaryKey(IrpWorkflowEmploy record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_WORKFLOW_EMPLOY.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_WORKFLOW_EMPLOY
     *
     * @ibatorgenerated Sun Apr 28 15:25:36 CST 2013
     */
    private static class UpdateByExampleParms extends IrpWorkflowEmployExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpWorkflowEmployExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectGroupInfo(Long nFlowNodeId) throws SQLException {
    	List<Map<String, Object>> list = getSqlMapClientTemplate().queryForList("IRP_WORKFLOW_EMPLOY.selectGroupInfo", nFlowNodeId);
    	return list;
    }
    
    @Override
    @SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUserInfo(Long nFlowNodeId) throws SQLException {
    	List<Map<String, Object>> list = getSqlMapClientTemplate().queryForList("IRP_WORKFLOW_EMPLOY.selectUserInfo", nFlowNodeId);
    	return list;
    }
    
    @Override
    public int deleteByFlowIds(String _sFlowIds) throws SQLException {
    	int rows = getSqlMapClientTemplate().delete("IRP_WORKFLOW_EMPLOY.deleteByFlowId", _sFlowIds);
        return rows;
	}
}