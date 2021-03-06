package com.tfs.irp.topic.dao;

import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.topic.entity.IrpTopicExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface IrpTopicDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	int countByExample(IrpTopicExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	int deleteByExample(IrpTopicExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	int deleteByPrimaryKey(Long topicid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	void insert(IrpTopic record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	void insertSelective(IrpTopic record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	List<IrpTopic> selectByExample(IrpTopicExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	IrpTopic selectByPrimaryKey(Long topicid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	int updateByExampleSelective(IrpTopic record, IrpTopicExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	int updateByExample(IrpTopic record, IrpTopicExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	int updateByPrimaryKeySelective(IrpTopic record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_TOPIC
	 * @ibatorgenerated  Sat Aug 31 10:30:37 CST 2013
	 */
	int updateByPrimaryKey(IrpTopic record) throws SQLException;

	/**
	 * 分页
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	List<IrpTopic> selectByExample(IrpTopicExample example,PageUtil pageUtil) throws SQLException;
}