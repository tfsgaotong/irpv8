package com.tfs.irp.exam.dao;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.exam.entity.IrpExam;
import com.tfs.irp.exam.entity.IrpExamExample;
import com.tfs.irp.util.PageUtil;

public interface IrpExamDAO {
    /**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	int countByExample(IrpExamExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	int deleteByExample(IrpExamExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	int deleteByPrimaryKey(Long examid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	void insert(IrpExam record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	void insertSelective(IrpExam record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	List<IrpExam> selectByExample(IrpExamExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	IrpExam selectByPrimaryKey(Long examid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	int updateByExampleSelective(IrpExam record, IrpExamExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	int updateByExample(IrpExam record, IrpExamExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	int updateByPrimaryKeySelective(IrpExam record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_exam
	 * @ibatorgenerated  Wed Oct 29 14:09:06 CST 2014
	 */
	int updateByPrimaryKey(IrpExam record) throws SQLException;

	/**
     * 分页查询
     * @param example
     * @param _pageutil
     * @return
     * @throws SQLException
     */
    List<IrpExam> selectByExample(IrpExamExample example,PageUtil _pageutil) throws SQLException;
    /**
     * 查找用户所有考试
     * @param condition
     * @param pageutile
     * @return
     * @author   npz
     * @date 2017年10月16日
     */
    List<IrpExam> selectByUser(String condition,PageUtil pageutile);
    int selectByUser(String condition);
}