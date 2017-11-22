package com.tfs.irp.leavechecker.dao.impl;

import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.leavechecker.dao.IrpCheckerLinkDAO;
import com.tfs.irp.leavechecker.entity.IrpCheckerLink;
import com.tfs.irp.leavechecker.entity.IrpCheckerLinkExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpCheckerLinkDAOImpl extends SqlMapClientDaoSupport   implements IrpCheckerLinkDAO {
   

    
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    public int countByExample(IrpCheckerLinkExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_CHECKER_LINK.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    public int deleteByExample(IrpCheckerLinkExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_CHECKER_LINK.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    public int deleteByPrimaryKey(Long checkerlinkid) throws SQLException {
        IrpCheckerLink key = new IrpCheckerLink();
        key.setCheckerlinkid(checkerlinkid);
        int rows = getSqlMapClientTemplate().delete("IRP_CHECKER_LINK.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    public void insert(IrpCheckerLink record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_CHECKER_LINK.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    public void insertSelective(IrpCheckerLink record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_CHECKER_LINK.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    @SuppressWarnings("unchecked")
    public List<IrpCheckerLink> selectByExample(IrpCheckerLinkExample example) throws SQLException {
        List<IrpCheckerLink> list = getSqlMapClientTemplate().queryForList("IRP_CHECKER_LINK.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    public IrpCheckerLink selectByPrimaryKey(Long checkerlinkid) throws SQLException {
        IrpCheckerLink key = new IrpCheckerLink();
        key.setCheckerlinkid(checkerlinkid);
        IrpCheckerLink record = (IrpCheckerLink) getSqlMapClientTemplate().queryForObject("IRP_CHECKER_LINK.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    public int updateByExampleSelective(IrpCheckerLink record, IrpCheckerLinkExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_CHECKER_LINK.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    public int updateByExample(IrpCheckerLink record, IrpCheckerLinkExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_CHECKER_LINK.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    public int updateByPrimaryKeySelective(IrpCheckerLink record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_CHECKER_LINK.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    public int updateByPrimaryKey(IrpCheckerLink record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_CHECKER_LINK.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_CHECKER_LINK
     *
     * @ibatorgenerated Fri Sep 09 10:50:25 CST 2016
     */
    private static class UpdateByExampleParms extends IrpCheckerLinkExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpCheckerLinkExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}