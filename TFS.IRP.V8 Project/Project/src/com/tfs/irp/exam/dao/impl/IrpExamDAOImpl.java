package com.tfs.irp.exam.dao.impl;

import com.tfs.irp.exam.dao.IrpExamDAO;
import com.tfs.irp.exam.entity.IrpExam;
import com.tfs.irp.exam.entity.IrpExamExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpExamDAOImpl extends SqlMapClientDaoSupport implements IrpExamDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	public int countByExample(IrpExamExample example) throws SQLException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"irp_exam.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	public int deleteByExample(IrpExamExample example) throws SQLException {
		int rows = getSqlMapClientTemplate().delete(
				"irp_exam.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	public int deleteByPrimaryKey(Long examid) throws SQLException {
		IrpExam key = new IrpExam();
		key.setExamid(examid);
		int rows = getSqlMapClientTemplate().delete(
				"irp_exam.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	public void insert(IrpExam record) throws SQLException {
		getSqlMapClientTemplate().insert("irp_exam.ibatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	public void insertSelective(IrpExam record) throws SQLException {
		getSqlMapClientTemplate().insert("irp_exam.ibatorgenerated_insertSelective", record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	@SuppressWarnings("unchecked")
	public List<IrpExam> selectByExample(IrpExamExample example)
			throws SQLException {
		List<IrpExam> list = getSqlMapClientTemplate().queryForList(
				"irp_exam.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	public IrpExam selectByPrimaryKey(Long examid) throws SQLException {
		IrpExam key = new IrpExam();
		key.setExamid(examid);
		IrpExam record = (IrpExam) getSqlMapClientTemplate().queryForObject(
				"irp_exam.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	public int updateByExampleSelective(IrpExam record, IrpExamExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"irp_exam.ibatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	public int updateByExample(IrpExam record, IrpExamExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"irp_exam.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	public int updateByPrimaryKeySelective(IrpExam record) throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"irp_exam.ibatorgenerated_updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	public int updateByPrimaryKey(IrpExam record) throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"irp_exam.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table irp_exam
	 * @ibatorgenerated  Mon Oct 20 17:01:06 CST 2014
	 */
	private static class UpdateByExampleParms extends IrpExamExample {
		private Object record;

		public UpdateByExampleParms(Object record, IrpExamExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
	@Override
	public List<IrpExam> selectByExample(IrpExamExample example,
			PageUtil _pageutil) throws SQLException {
		// TODO Auto-generated method stub
        List<IrpExam> list = getSqlMapClientTemplate().queryForList("irp_exam.ibatorgenerated_selectByExample", example,_pageutil.getPageIndex(),_pageutil.getPageSize());
        return list;
	}

	@Override
	public List<IrpExam> selectByUser(String condition,PageUtil pageutile) {
		List<IrpExam> list = getSqlMapClientTemplate().queryForList("irp_exam.ibatorgenerated_findbyuser", condition,pageutile.getPageIndex(),pageutile.getPageSize());
		return list;
	}

	@Override
	public int selectByUser(String condition) {
		List<IrpExam> list = getSqlMapClientTemplate().queryForList("irp_exam.ibatorgenerated_findbyuser", condition);
		return list.size();
	}

}