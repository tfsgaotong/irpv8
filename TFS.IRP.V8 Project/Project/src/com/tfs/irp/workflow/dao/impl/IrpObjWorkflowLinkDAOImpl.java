package com.tfs.irp.workflow.dao.impl;

import com.tfs.irp.workflow.dao.IrpObjWorkflowLinkDAO;
import com.tfs.irp.workflow.entity.IrpObjWorkflowLink;
import com.tfs.irp.workflow.entity.IrpObjWorkflowLinkExample;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpObjWorkflowLinkDAOImpl extends SqlMapClientDaoSupport implements IrpObjWorkflowLinkDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    public int countByExample(IrpObjWorkflowLinkExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    public int deleteByExample(IrpObjWorkflowLinkExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    public int deleteByPrimaryKey(Long objflowid) throws SQLException {
        IrpObjWorkflowLink key = new IrpObjWorkflowLink();
        key.setObjflowid(objflowid);
        int rows = getSqlMapClientTemplate().delete("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    public void insert(IrpObjWorkflowLink record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    public void insertSelective(IrpObjWorkflowLink record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpObjWorkflowLink> selectByExample(IrpObjWorkflowLinkExample example) throws SQLException {
        List<IrpObjWorkflowLink> list = getSqlMapClientTemplate().queryForList("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    public IrpObjWorkflowLink selectByPrimaryKey(Long objflowid) throws SQLException {
        IrpObjWorkflowLink key = new IrpObjWorkflowLink();
        key.setObjflowid(objflowid);
        IrpObjWorkflowLink record = (IrpObjWorkflowLink) getSqlMapClientTemplate().queryForObject("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    public int updateByExampleSelective(IrpObjWorkflowLink record, IrpObjWorkflowLinkExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    public int updateByExample(IrpObjWorkflowLink record, IrpObjWorkflowLinkExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    public int updateByPrimaryKeySelective(IrpObjWorkflowLink record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    public int updateByPrimaryKey(IrpObjWorkflowLink record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_OBJ_WORKFLOW_LINK.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_OBJ_WORKFLOW_LINK
     *
     * @ibatorgenerated Tue May 14 15:40:19 CST 2013
     */
    private static class UpdateByExampleParms extends IrpObjWorkflowLinkExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpObjWorkflowLinkExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}