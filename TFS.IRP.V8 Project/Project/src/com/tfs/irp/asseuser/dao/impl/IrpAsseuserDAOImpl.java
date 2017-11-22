package com.tfs.irp.asseuser.dao.impl;

import com.tfs.irp.asseuser.dao.IrpAsseuserDAO;
import com.tfs.irp.asseuser.entity.IrpAsseuser;
import com.tfs.irp.asseuser.entity.IrpAsseuserExample;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpAsseuserDAOImpl extends SqlMapClientDaoSupport implements IrpAsseuserDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public IrpAsseuserDAOImpl() {
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public int countByExample(IrpAsseuserExample example) throws SQLException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"IRP_ASSEUSER.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public int deleteByExample(IrpAsseuserExample example) throws SQLException {
		int rows = getSqlMapClientTemplate().delete(
				"IRP_ASSEUSER.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public int deleteByPrimaryKey(Long asseuserid) throws SQLException {
		IrpAsseuser key = new IrpAsseuser();
		key.setAsseuserid(asseuserid);
		int rows = getSqlMapClientTemplate().delete(
				"IRP_ASSEUSER.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public void insert(IrpAsseuser record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_ASSEUSER.ibatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public void insertSelective(IrpAsseuser record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_ASSEUSER.ibatorgenerated_insertSelective",
				record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	@SuppressWarnings("unchecked")
	public List<IrpAsseuser> selectByExample(IrpAsseuserExample example)
			throws SQLException {
		List<IrpAsseuser> list = getSqlMapClientTemplate().queryForList(
				"IRP_ASSEUSER.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public IrpAsseuser selectByPrimaryKey(Long asseuserid) throws SQLException {
		IrpAsseuser key = new IrpAsseuser();
		key.setAsseuserid(asseuserid);
		IrpAsseuser record = (IrpAsseuser) getSqlMapClientTemplate().queryForObject(
				"IRP_ASSEUSER.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public int updateByExampleSelective(IrpAsseuser record,
			IrpAsseuserExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_ASSEUSER.ibatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public int updateByExample(IrpAsseuser record, IrpAsseuserExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_ASSEUSER.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public int updateByPrimaryKeySelective(IrpAsseuser record)
			throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_ASSEUSER.ibatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	public int updateByPrimaryKey(IrpAsseuser record) throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_ASSEUSER.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_ASSEUSER
	 * @ibatorgenerated  Wed Aug 24 15:11:33 CST 2016
	 */
	private static class UpdateByExampleParms extends IrpAsseuserExample {
		private Object record;

		public UpdateByExampleParms(Object record, IrpAsseuserExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}