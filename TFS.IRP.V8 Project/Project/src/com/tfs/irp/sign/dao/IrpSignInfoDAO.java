package com.tfs.irp.sign.dao;

import com.tfs.irp.sign.entity.IrpSignInfo;
import com.tfs.irp.sign.entity.IrpSignInfoExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IrpSignInfoDAO {
	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	int countByExample(IrpSignInfoExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	int deleteByExample(IrpSignInfoExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	int deleteByPrimaryKey(Long signinfoid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	void insert(IrpSignInfo record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	void insertSelective(IrpSignInfo record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	List<IrpSignInfo> selectByExample(IrpSignInfoExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	IrpSignInfo selectByPrimaryKey(Long signinfoid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	int updateByExampleSelective(IrpSignInfo record, IrpSignInfoExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	int updateByExample(IrpSignInfo record, IrpSignInfoExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	int updateByPrimaryKeySelective(IrpSignInfo record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method
	 * corresponds to the database table IRP_SIGN_INFO
	 * 
	 * @ibatorgenerated Thu Jan 16 11:19:04 CST 2014
	 */
	int updateByPrimaryKey(IrpSignInfo record) throws SQLException;

	/**
	 * 初始化签到用户
	 * 
	 * @param page
	 * @return
	 */
	List<IrpSignInfo> signInfo(PageUtil page, IrpSignInfoExample example);
	
	/**
	 * 导出当前查询条件的记录
	 * @param example
	 * @return
	 */
	List<IrpSignInfo> signInfo(IrpSignInfoExample example);

	
	String findUserTrueNameById(Map uid);

	List<IrpSignInfo> queryAllSignInfo();
}