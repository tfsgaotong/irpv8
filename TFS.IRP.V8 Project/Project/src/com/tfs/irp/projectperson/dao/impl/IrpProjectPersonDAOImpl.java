package com.tfs.irp.projectperson.dao.impl;

import com.tfs.irp.projectperson.dao.IrpProjectPersonDAO;
import com.tfs.irp.projectperson.entity.IrpProjectPerson;
import com.tfs.irp.projectperson.entity.IrpProjectPersonExample;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpProjectPersonDAOImpl extends SqlMapClientDaoSupport  implements IrpProjectPersonDAO {
 
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	public int countByExample(IrpProjectPersonExample example)
			throws SQLException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"IRP_PROJECT_PERSON.ibatorgenerated_countByExample", example);
		return count;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	public int deleteByExample(IrpProjectPersonExample example)
			throws SQLException {
		int rows = getSqlMapClientTemplate().delete(
				"IRP_PROJECT_PERSON.ibatorgenerated_deleteByExample", example);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	public int deleteByPrimaryKey(Long properid) throws SQLException {
		IrpProjectPerson key = new IrpProjectPerson();
		key.setProperid(properid);
		int rows = getSqlMapClientTemplate().delete(
				"IRP_PROJECT_PERSON.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	public void insert(IrpProjectPerson record) throws SQLException {
		getSqlMapClientTemplate()
				.insert("IRP_PROJECT_PERSON.ibatorgenerated_insert", record);
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	public void insertSelective(IrpProjectPerson record) throws SQLException {
		getSqlMapClientTemplate().insert(
				"IRP_PROJECT_PERSON.ibatorgenerated_insertSelective", record);
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	@SuppressWarnings("unchecked")
	public List<IrpProjectPerson> selectByExample(
			IrpProjectPersonExample example) throws SQLException {
		List<IrpProjectPerson> list = getSqlMapClientTemplate().queryForList(
				"IRP_PROJECT_PERSON.ibatorgenerated_selectByExample", example);
		return list;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	public IrpProjectPerson selectByPrimaryKey(Long properid)
			throws SQLException {
		IrpProjectPerson key = new IrpProjectPerson();
		key.setProperid(properid);
		IrpProjectPerson record = (IrpProjectPerson) getSqlMapClientTemplate()
				.queryForObject(
						"IRP_PROJECT_PERSON.ibatorgenerated_selectByPrimaryKey",
						key);
		return record;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	public int updateByExampleSelective(IrpProjectPerson record,
			IrpProjectPersonExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_PROJECT_PERSON.ibatorgenerated_updateByExampleSelective",
				parms);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	public int updateByExample(IrpProjectPerson record,
			IrpProjectPersonExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_PROJECT_PERSON.ibatorgenerated_updateByExample", parms);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	public int updateByPrimaryKeySelective(IrpProjectPerson record)
			throws SQLException {
		int rows = getSqlMapClientTemplate()
				.update("IRP_PROJECT_PERSON.ibatorgenerated_updateByPrimaryKeySelective",
						record);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	public int updateByPrimaryKey(IrpProjectPerson record) throws SQLException {
		int rows = getSqlMapClientTemplate()
				.update("IRP_PROJECT_PERSON.ibatorgenerated_updateByPrimaryKey",
						record);
		return rows;
	}
	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_PROJECT_PERSON
	 * @ibatorgenerated  Wed Jul 10 09:50:25 CST 2013
	 */
	private static class UpdateByExampleParms extends IrpProjectPersonExample {
		private Object record;

		public UpdateByExampleParms(Object record,
				IrpProjectPersonExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
	@Override
	public List<Long> findProjectIdByExample(IrpProjectPersonExample example)throws SQLException {
		// TODO Auto-generated method stub 
		 List<Long> list=getSqlMapClientTemplate().queryForList("IRP_PROJECT_PERSON.selectProjectIdByPersonId",example);
		 return list;
	}
	@Override
	public List<Long> selectPersonIdByProjectId(IrpProjectPersonExample example)
			throws SQLException {
		// TODO Auto-generated method stub 
				 List<Long> list=getSqlMapClientTemplate().queryForList("IRP_PROJECT_PERSON.selectPersonIdByProjectId",example);
				 return list;
	}
	@Override
	public List<Long> excuteSQl(String _sql) {
		List<Long> list = null;
		if(_sql!=""&&_sql!=null){
			Map map=new HashMap();
			map.put("sql", _sql);
			list = getSqlMapClientTemplate().queryForList("IRP_PROJECT_PERSON.selectGroupByPrid", map);
		}
		return list;
	}
}