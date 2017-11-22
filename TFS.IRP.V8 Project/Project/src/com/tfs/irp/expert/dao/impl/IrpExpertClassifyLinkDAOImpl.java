package com.tfs.irp.expert.dao.impl;

import com.tfs.irp.expert.dao.IrpExpertClassifyLinkDAO;
import com.tfs.irp.expert.entity.IrpExpertClassifyLink;
import com.tfs.irp.expert.entity.IrpExpertClassifyLinkExample;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpExpertClassifyLinkDAOImpl extends SqlMapClientDaoSupport implements IrpExpertClassifyLinkDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    public int countByExample(IrpExpertClassifyLinkExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("irp_expert_classify_link.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    public int deleteByExample(IrpExpertClassifyLinkExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("irp_expert_classify_link.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    public void insert(IrpExpertClassifyLink record) throws SQLException {
        getSqlMapClientTemplate().insert("irp_expert_classify_link.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    public void insertSelective(IrpExpertClassifyLink record) throws SQLException {
        getSqlMapClientTemplate().insert("irp_expert_classify_link.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    @SuppressWarnings("unchecked")
    public List<IrpExpertClassifyLink> selectByExample(IrpExpertClassifyLinkExample example) throws SQLException {
        List<IrpExpertClassifyLink> list = getSqlMapClientTemplate().queryForList("irp_expert_classify_link.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    public int updateByExampleSelective(IrpExpertClassifyLink record, IrpExpertClassifyLinkExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("irp_expert_classify_link.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    public int updateByExample(IrpExpertClassifyLink record, IrpExpertClassifyLinkExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("irp_expert_classify_link.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table irp_expert_classify_link
     *
     * @ibatorgenerated Wed May 07 14:05:15 CST 2014
     */
    private static class UpdateByExampleParms extends IrpExpertClassifyLinkExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpExpertClassifyLinkExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}