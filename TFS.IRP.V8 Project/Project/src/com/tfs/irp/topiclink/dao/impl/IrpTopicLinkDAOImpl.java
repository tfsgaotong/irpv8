package com.tfs.irp.topiclink.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.tfs.irp.topiclink.dao.IrpTopicLinkDAO;
import com.tfs.irp.topiclink.entity.IrpTopicLink;
import com.tfs.irp.topiclink.entity.IrpTopicLinkExample;

public class IrpTopicLinkDAOImpl  extends SqlMapClientDaoSupport implements IrpTopicLinkDAO{


	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	public int countByExample(IrpTopicLinkExample example) throws SQLException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"IRP_TOPIC_LINK.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	public int deleteByExample(IrpTopicLinkExample example) throws SQLException {
		int rows = getSqlMapClientTemplate().delete(
				"IRP_TOPIC_LINK.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	public int deleteByPrimaryKey(Long topiclinkid) throws SQLException {
		IrpTopicLink key = new IrpTopicLink();
		key.setTopiclinkid(topiclinkid);
		int rows = getSqlMapClientTemplate().delete(
				"IRP_TOPIC_LINK.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	public void insert(IrpTopicLink record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_TOPIC_LINK.ibatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	public void insertSelective(IrpTopicLink record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_TOPIC_LINK.ibatorgenerated_insertSelective",
				record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	@SuppressWarnings("unchecked")
	public List<IrpTopicLink> selectByExample(IrpTopicLinkExample example)
			throws SQLException {
		List<IrpTopicLink> list = getSqlMapClientTemplate().queryForList(
				"IRP_TOPIC_LINK.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	public IrpTopicLink selectByPrimaryKey(Long topiclinkid)
			throws SQLException {
		IrpTopicLink key = new IrpTopicLink();
		key.setTopiclinkid(topiclinkid);
		IrpTopicLink record = (IrpTopicLink) getSqlMapClientTemplate().queryForObject(
				"IRP_TOPIC_LINK.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	public int updateByExampleSelective(IrpTopicLink record,
			IrpTopicLinkExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_TOPIC_LINK.ibatorgenerated_updateByExampleSelective",
				parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	public int updateByExample(IrpTopicLink record, IrpTopicLinkExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_TOPIC_LINK.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	public int updateByPrimaryKeySelective(IrpTopicLink record)
			throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_TOPIC_LINK.ibatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	public int updateByPrimaryKey(IrpTopicLink record) throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_TOPIC_LINK.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_TOPIC_LINK
	 * @ibatorgenerated  Wed Dec 25 17:22:04 CST 2013
	 */
	private static class UpdateByExampleParms extends IrpTopicLinkExample {
		private Object record;

		public UpdateByExampleParms(Object record, IrpTopicLinkExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
	@Override
	public List selectTopicNumByName(String _topiclinkname) {
		// TODO Auto-generated method stub
		List list = null;
		Map map = new HashMap();
		map.put("topicid", Long.valueOf(_topiclinkname));
		map.put("expandfield", IrpTopicLink.TOPICLINKNORMAL);
		list = getSqlMapClientTemplate().queryForList("IRP_TOPIC_LINK.ibatorgenerated_selectIrpTopicLinkNumByName", map);
		return list;
	}
}