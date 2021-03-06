package com.tfs.irp.asseroom.dao.impl;

import com.tfs.irp.asseroom.dao.IrpAsseroomDAO;
import com.tfs.irp.asseroom.entity.IrpAsseroom;
import com.tfs.irp.asseroom.entity.IrpAsseroomExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpAsseroomDAOImpl extends SqlMapClientDaoSupport implements IrpAsseroomDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public IrpAsseroomDAOImpl() {
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public int countByExample(IrpAsseroomExample example) throws SQLException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"IRP_ASSEROOM.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public int deleteByExample(IrpAsseroomExample example) throws SQLException {
		int rows = getSqlMapClientTemplate().delete(
				"IRP_ASSEROOM.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public int deleteByPrimaryKey(Long asseroomid) throws SQLException {
		IrpAsseroom key = new IrpAsseroom();
		key.setAsseroomid(asseroomid);
		int rows = getSqlMapClientTemplate().delete(
				"IRP_ASSEROOM.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public void insert(IrpAsseroom record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_ASSEROOM.ibatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public void insertSelective(IrpAsseroom record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_ASSEROOM.ibatorgenerated_insertSelective",
				record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	@SuppressWarnings("unchecked")
	public List<IrpAsseroom> selectByExample(IrpAsseroomExample example,PageUtil page)
			throws SQLException {
		List<IrpAsseroom> list = getSqlMapClientTemplate().queryForList(
				"IRP_ASSEROOM.ibatorgenerated_selectByExample", example,page.getPageIndex(),page.getPageSize());
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<IrpAsseroom> selectByExample(IrpAsseroomExample example)
			throws SQLException {
		List<IrpAsseroom> list = getSqlMapClientTemplate().queryForList(
				"IRP_ASSEROOM.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public IrpAsseroom selectByPrimaryKey(Long asseroomid) throws SQLException {
		IrpAsseroom key = new IrpAsseroom();
		key.setAsseroomid(asseroomid);
		IrpAsseroom record = (IrpAsseroom) getSqlMapClientTemplate().queryForObject(
				"IRP_ASSEROOM.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public int updateByExampleSelective(IrpAsseroom record,
			IrpAsseroomExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_ASSEROOM.ibatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public int updateByExample(IrpAsseroom record, IrpAsseroomExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_ASSEROOM.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public int updateByPrimaryKeySelective(IrpAsseroom record)
			throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_ASSEROOM.ibatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	public int updateByPrimaryKey(IrpAsseroom record) throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_ASSEROOM.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_ASSEROOM
	 * @ibatorgenerated  Tue Aug 23 15:33:11 CST 2016
	 */
	private static class UpdateByExampleParms extends IrpAsseroomExample {
		private Object record;

		public UpdateByExampleParms(Object record, IrpAsseroomExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}