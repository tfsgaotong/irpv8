package com.tfs.irp.docstatus.dao.impl;

import com.tfs.irp.docstatus.dao.IrpDocstatusDAO;
import com.tfs.irp.docstatus.entity.IrpDocstatus;
import com.tfs.irp.docstatus.entity.IrpDocstatusExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpDocstatusDAOImpl extends SqlMapClientDaoSupport implements IrpDocstatusDAO {
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	public int countByExample(IrpDocstatusExample example) throws SQLException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"IRP_DOCSTATUS.ibatorgenerated_countByExample", example);
		return count;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	public int deleteByExample(IrpDocstatusExample example) throws SQLException {
		int rows = getSqlMapClientTemplate().delete(
				"IRP_DOCSTATUS.ibatorgenerated_deleteByExample", example);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	public int deleteByPrimaryKey(Long statusid) throws SQLException {
		IrpDocstatus key = new IrpDocstatus();
		key.setStatusid(statusid);
		int rows = getSqlMapClientTemplate().delete(
				"IRP_DOCSTATUS.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	public void insert(IrpDocstatus record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_DOCSTATUS.ibatorgenerated_insert", record);
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	public void insertSelective(IrpDocstatus record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_DOCSTATUS.ibatorgenerated_insertSelective",
				record);
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	@SuppressWarnings("unchecked")
	public List<IrpDocstatus> selectByExample(IrpDocstatusExample example)
			throws SQLException {
		List<IrpDocstatus> list = getSqlMapClientTemplate().queryForList(
				"IRP_DOCSTATUS.ibatorgenerated_selectByExample", example);
		return list;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	public IrpDocstatus selectByPrimaryKey(Long statusid) throws SQLException {
		IrpDocstatus key = new IrpDocstatus();
		key.setStatusid(statusid);
		IrpDocstatus record = (IrpDocstatus) getSqlMapClientTemplate().queryForObject(
				"IRP_DOCSTATUS.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	public int updateByExampleSelective(IrpDocstatus record,
			IrpDocstatusExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate()
				.update("IRP_DOCSTATUS.ibatorgenerated_updateByExampleSelective",
						parms);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	public int updateByExample(IrpDocstatus record, IrpDocstatusExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_DOCSTATUS.ibatorgenerated_updateByExample", parms);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	public int updateByPrimaryKeySelective(IrpDocstatus record)
			throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_DOCSTATUS.ibatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	public int updateByPrimaryKey(IrpDocstatus record) throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_DOCSTATUS.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}
	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_DOCSTATUS
	 * @ibatorgenerated  Wed Mar 27 15:51:57 CST 2013
	 */
	private static class UpdateByExampleParms extends IrpDocstatusExample {
		private Object record;

		public UpdateByExampleParms(Object record, IrpDocstatusExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
	public List<IrpDocstatus> selectByExample(PageUtil pageUtil,IrpDocstatusExample example) throws SQLException {
        List<IrpDocstatus> list = getSqlMapClientTemplate().queryForList("IRP_DOCSTATUS.ibatorgenerated_selectByExample", example,pageUtil.getPageIndex(), pageUtil.getPageSize()); 
        return list;
    }
    @Override
    public List<IrpDocstatus> findAllStatus()throws SQLException  { 
    	List<IrpDocstatus> list=getSqlMapClientTemplate().queryForList("IRP_DOCSTATUS.allDocStatus"); 
    	return list;
    }
}