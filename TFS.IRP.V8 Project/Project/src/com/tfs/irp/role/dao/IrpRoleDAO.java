package com.tfs.irp.role.dao;

import com.tfs.irp.role.entity.IrpRole;
import com.tfs.irp.role.entity.IrpRoleExample;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IrpRoleDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	int countByExample(IrpRoleExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	int deleteByExample(IrpRoleExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	int deleteByPrimaryKey(Long roleid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	void insert(IrpRole record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	void insertSelective(IrpRole record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	List<IrpRole> selectByExample(IrpRoleExample example) throws SQLException;
	
	List<IrpRole> selectByExample(IrpRoleExample example, PageUtil pageUtil) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	IrpRole selectByPrimaryKey(Long roleid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	int updateByExampleSelective(IrpRole record, IrpRoleExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	int updateByExample(IrpRole record, IrpRoleExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	int updateByPrimaryKeySelective(IrpRole record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ROLE
	 * @ibatorgenerated  Wed Mar 13 10:43:44 CST 2013
	 */
	int updateByPrimaryKey(IrpRole record) throws SQLException;

	List<IrpUser> findUsersOfPageByRoleId(Map<String, Object> _mParam, PageUtil pageUtil) throws SQLException;

	int findUsersCountByRoleId(Map<String, Object> _mParam) throws SQLException;
	int findExpertCountByRoleId(Map<String, Object> _mParam) throws SQLException;
	List<IrpUser> findUsersOfPageByRoleId(Map<String, Object> _mParam) throws SQLException;
	List<IrpUser> findUsersOfpageByRoleIdAndName(Map<String, Object> _mParam,PageUtil pageUtil) throws Exception;
}