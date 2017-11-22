package com.tfs.irp.microblogatme.dao;

import com.tfs.irp.microblogatme.entity.IrpMicroblogAtmeExample;
import com.tfs.irp.microblogatme.entity.IrpMicroblogAtmeKey;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IrpMicroblogAtmeDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	int countByExample(IrpMicroblogAtmeExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	int deleteByExample(IrpMicroblogAtmeExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	int deleteByPrimaryKey(IrpMicroblogAtmeKey key) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	void insert(IrpMicroblogAtmeKey record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	void insertSelective(IrpMicroblogAtmeKey record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	List<IrpMicroblogAtmeKey> selectByExample(IrpMicroblogAtmeExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	IrpMicroblogAtmeKey selectByPrimaryKey(IrpMicroblogAtmeKey key)
			throws SQLException;
	IrpMicroblogAtmeKey selectAtmeByPrimaryKey(IrpMicroblogAtmeKey key)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	int updateByExampleSelective(IrpMicroblogAtmeKey record,
			IrpMicroblogAtmeExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	int updateByExample(IrpMicroblogAtmeKey record, IrpMicroblogAtmeExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	int updateByPrimaryKeySelective(IrpMicroblogAtmeKey record)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_MICROBLOG_ATME
	 * @ibatorgenerated  Wed May 15 15:53:49 CST 2013
	 */
	int updateByPrimaryKey(IrpMicroblogAtmeKey record) throws SQLException;
	/**
     * 分页显示数据
     * @param example
     * @param pageUtil
     * @return
     * @throws SQLException
     */
    public List<IrpMicroblogAtmeKey> selectByExample(IrpMicroblogAtmeExample example,PageUtil pageUtil) throws SQLException;
    /**
	   * 获得登录用户还没有查看的@我的消息
	   * @param _fromUid
	   * @param _browsestatus
	   * @return
	   */
	public int selectUnReadAtme(Map<String, Object> _mParam) throws SQLException;
}