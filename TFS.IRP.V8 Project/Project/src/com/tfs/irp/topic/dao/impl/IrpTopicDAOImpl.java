package com.tfs.irp.topic.dao.impl;

import com.tfs.irp.topic.dao.IrpTopicDAO;
import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.topic.entity.IrpTopicExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpTopicDAOImpl extends SqlMapClientDaoSupport implements IrpTopicDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    public int countByExample(IrpTopicExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_TOPIC.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    public int deleteByExample(IrpTopicExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_TOPIC.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    public int deleteByPrimaryKey(Long topicid) throws SQLException {
        IrpTopic key = new IrpTopic();
        key.setTopicid(topicid);
        int rows = getSqlMapClientTemplate().delete("IRP_TOPIC.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    public void insert(IrpTopic record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_TOPIC.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    public void insertSelective(IrpTopic record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_TOPIC.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpTopic> selectByExample(IrpTopicExample example) throws SQLException {
        List<IrpTopic> list = getSqlMapClientTemplate().queryForList("IRP_TOPIC.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    public IrpTopic selectByPrimaryKey(Long topicid) throws SQLException {
        IrpTopic key = new IrpTopic();
        key.setTopicid(topicid);
        IrpTopic record = (IrpTopic) getSqlMapClientTemplate().queryForObject("IRP_TOPIC.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    public int updateByExampleSelective(IrpTopic record, IrpTopicExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_TOPIC.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    public int updateByExample(IrpTopic record, IrpTopicExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_TOPIC.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    public int updateByPrimaryKeySelective(IrpTopic record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_TOPIC.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    public int updateByPrimaryKey(IrpTopic record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_TOPIC.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_TOPIC
     *
     * @ibatorgenerated Sat Aug 31 10:30:37 CST 2013
     */
    private static class UpdateByExampleParms extends IrpTopicExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpTopicExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	@Override
	public List<IrpTopic> selectByExample(IrpTopicExample example,
			PageUtil pageUtil) throws SQLException {
		 List<IrpTopic> list = getSqlMapClientTemplate().queryForList("IRP_TOPIC.ibatorgenerated_selectByExample", example,pageUtil.getPageIndex(),pageUtil.getPageSize());
	        return list;
	}
}