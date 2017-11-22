package com.tfs.irp.assewarn.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tfs.irp.assewarn.entity.IrpAssewarn;
import com.tfs.irp.assewarn.entity.IrpAssewarnExample;
import java.sql.SQLException;
import java.util.List;

public class IrpAssewarnDAOImpl implements IrpAssewarnDAO {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public IrpAssewarnDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public int countByExample(IrpAssewarnExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject(
				"IRP_ASSEWARN.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public int deleteByExample(IrpAssewarnExample example) throws SQLException {
		int rows = sqlMapClient.delete(
				"IRP_ASSEWARN.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public int deleteByPrimaryKey(Long warnid) throws SQLException {
		IrpAssewarn key = new IrpAssewarn();
		key.setWarnid(warnid);
		int rows = sqlMapClient.delete(
				"IRP_ASSEWARN.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public void insert(IrpAssewarn record) throws SQLException {
		sqlMapClient.insert("IRP_ASSEWARN.ibatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public void insertSelective(IrpAssewarn record) throws SQLException {
		sqlMapClient.insert("IRP_ASSEWARN.ibatorgenerated_insertSelective",
				record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	@SuppressWarnings("unchecked")
	public List<IrpAssewarn> selectByExample(IrpAssewarnExample example)
			throws SQLException {
		List<IrpAssewarn> list = sqlMapClient.queryForList(
				"IRP_ASSEWARN.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public IrpAssewarn selectByPrimaryKey(Long warnid) throws SQLException {
		IrpAssewarn key = new IrpAssewarn();
		key.setWarnid(warnid);
		IrpAssewarn record = (IrpAssewarn) sqlMapClient.queryForObject(
				"IRP_ASSEWARN.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public int updateByExampleSelective(IrpAssewarn record,
			IrpAssewarnExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update(
				"IRP_ASSEWARN.ibatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public int updateByExample(IrpAssewarn record, IrpAssewarnExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update(
				"IRP_ASSEWARN.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public int updateByPrimaryKeySelective(IrpAssewarn record)
			throws SQLException {
		int rows = sqlMapClient.update(
				"IRP_ASSEWARN.ibatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	public int updateByPrimaryKey(IrpAssewarn record) throws SQLException {
		int rows = sqlMapClient.update(
				"IRP_ASSEWARN.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_ASSEWARN
	 * @ibatorgenerated  Thu Aug 25 16:52:31 CST 2016
	 */
	private static class UpdateByExampleParms extends IrpAssewarnExample {
		private Object record;

		public UpdateByExampleParms(Object record, IrpAssewarnExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}