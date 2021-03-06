package com.tfs.irp.subscribe.dao.impl;

import com.tfs.irp.subscribe.dao.IrpSubscribeDAO;
import com.tfs.irp.subscribe.entity.IrpSubscribe;
import com.tfs.irp.subscribe.entity.IrpSubscribeExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpSubscribeDAOImpl extends SqlMapClientDaoSupport implements IrpSubscribeDAO {
   
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    public int countByExample(IrpSubscribeExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("irp_subscribe.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    public int deleteByExample(IrpSubscribeExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("irp_subscribe.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    public int deleteByPrimaryKey(Long subscribeid) throws SQLException {
        IrpSubscribe key = new IrpSubscribe();
        key.setSubscribeid(subscribeid);
        int rows = getSqlMapClientTemplate().delete("irp_subscribe.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    public void insert(IrpSubscribe record) throws SQLException {
        getSqlMapClientTemplate().insert("irp_subscribe.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    public void insertSelective(IrpSubscribe record) throws SQLException {
        getSqlMapClientTemplate().insert("irp_subscribe.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    @SuppressWarnings("unchecked")
    public List<IrpSubscribe> selectByExample(IrpSubscribeExample example, PageUtil pageUtil) throws SQLException {
    	 List<IrpSubscribe> list = null;
    	if(pageUtil==null){
    		list =getSqlMapClientTemplate().queryForList("irp_subscribe.ibatorgenerated_selectByExample", example);
    	}else{
    		list =getSqlMapClientTemplate().queryForList("irp_subscribe.ibatorgenerated_selectByExample", example,
    				pageUtil.getPageIndex(),pageUtil.getPageSize());
    	}
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    public IrpSubscribe selectByPrimaryKey(Long subscribeid) throws SQLException {
        IrpSubscribe key = new IrpSubscribe();
        key.setSubscribeid(subscribeid);
        IrpSubscribe record = (IrpSubscribe) getSqlMapClientTemplate().queryForObject("irp_subscribe.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    public int updateByExampleSelective(IrpSubscribe record, IrpSubscribeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("irp_subscribe.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    public int updateByExample(IrpSubscribe record, IrpSubscribeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("irp_subscribe.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    public int updateByPrimaryKeySelective(IrpSubscribe record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("irp_subscribe.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    public int updateByPrimaryKey(IrpSubscribe record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("irp_subscribe.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table irp_subscribe
     *
     * @ibatorgenerated Thu Jul 24 15:20:08 CST 2014
     */
    private static class UpdateByExampleParms extends IrpSubscribeExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpSubscribeExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}