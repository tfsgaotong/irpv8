package com.tfs.irp.question.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tfs.irp.question.dao.IrpQuestionDAO;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.entity.IrpQuestionExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpQuestionDAOImpl extends SqlMapClientDaoSupport implements IrpQuestionDAO {

	
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public int countByExample(IrpQuestionExample example) throws SQLException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"IRP_QUESTION.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public int deleteByExample(IrpQuestionExample example) throws SQLException {
		int rows = getSqlMapClientTemplate().delete(
				"IRP_QUESTION.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public int deleteByPrimaryKey(Long questionid) throws SQLException {
		IrpQuestion key = new IrpQuestion();
		key.setQuestionid(questionid);
		int rows = getSqlMapClientTemplate().delete(
				"IRP_QUESTION.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void insert(IrpQuestion record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_QUESTION.ibatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public void insertSelective(IrpQuestion record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_QUESTION.ibatorgenerated_insertSelective",
				record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	@SuppressWarnings("unchecked")
	public List<IrpQuestion> selectByExample(IrpQuestionExample example)
			throws SQLException {
		List<IrpQuestion> list = getSqlMapClientTemplate().queryForList(
				"IRP_QUESTION.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public IrpQuestion selectByPrimaryKey(Long questionid) throws SQLException {
		IrpQuestion key = new IrpQuestion();
		key.setQuestionid(questionid);
		IrpQuestion record = (IrpQuestion) getSqlMapClientTemplate().queryForObject(
				"IRP_QUESTION.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public int updateByExampleSelective(IrpQuestion record,
			IrpQuestionExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_QUESTION.ibatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public int updateByExample(IrpQuestion record, IrpQuestionExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_QUESTION.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public int updateByPrimaryKeySelective(IrpQuestion record)
			throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_QUESTION.ibatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	public int updateByPrimaryKey(IrpQuestion record) throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_QUESTION.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_QUESTION
	 * @ibatorgenerated  Tue May 06 15:54:06 CST 2014
	 */
	private static class UpdateByExampleParms extends IrpQuestionExample {
		private Object record;

		public UpdateByExampleParms(Object record, IrpQuestionExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
	@Override
	public List<IrpQuestion> selectByExample(IrpQuestionExample example,
			PageUtil pageUtil) throws SQLException {
		List<IrpQuestion> list =null;
		if(pageUtil!=null){
			list = getSqlMapClientTemplate().queryForList(
					"IRP_QUESTION.ibatorgenerated_selectByExample", example,
					pageUtil.getPageIndex(), pageUtil.getPageSize());
		}else{
			list=getSqlMapClientTemplate().queryForList(
					"IRP_QUESTION.ibatorgenerated_selectByExample", example);
		}
		
		
		return list;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IrpQuestion> findQuestionsOfMeAnswer(Map<String, Object> map)
			throws SQLException {
		List<IrpQuestion> questions = getSqlMapClientTemplate().queryForList(
				"IRP_QUESTION.findQuestionOfMeAnswer", map);
		return questions;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<IrpQuestion> findQuestionsOfMeAnswer(Map<String, Object> map,PageUtil pageUtil)
			throws SQLException {
		List<IrpQuestion> questions = getSqlMapClientTemplate().queryForList(
				"IRP_QUESTION.findQuestionOfMeAnswer", map,pageUtil.getPageIndex(), pageUtil.getPageSize());
		return questions;
	}
	@Override
	public List<IrpQuestion> findExpertAnswerThree(Map<String, Object> map)
			throws Exception {
		List<IrpQuestion> expertAnswers = getSqlMapClientTemplate().queryForList("IRP_QUESTION.findExpertAnswerthree", map);
		return expertAnswers;
	}

	@Override
	public int countQuestionsOfMeAnswer(Map<String, Object> _mParam) throws Exception {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("IRP_QUESTION.countQuestionsOfMeAnswer", _mParam);
		return count;
	}

	@Override
	public List findIntelligentUser() throws Exception {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		
		List list_01 = this.getSqlMapClientTemplate().queryForList("IRP_QUESTION.ibatorgenerated_questionintelligentuser");
		
		if(list_01.size()>0){
			for (int i = 0; i < list_01.size(); i++) {
			    if(i<5){
			    	list.add(list_01.get(i));	
			    }
			}	
		}
		return list;
	}

	@Override
	public List<IrpQuestion> findQuestionbyexcel(Map<String, Object> map,PageUtil pageUtil) {
		// ibatorgenerated_selectbyexcel
		List list =null;
		list = this.getSqlMapClientTemplate().queryForList("IRP_QUESTION.ibatorgenerated_selectbyexcel",map);
		return list;
	}

	@Override
	public int findQuestionbyexcelcount(Map<String, Object> map) {
		Integer count = null;
		count = (Integer) getSqlMapClientTemplate().queryForObject("IRP_QUESTION.ibatorgenerated_selectbyexcelcount",map);
		return count;
	}
}