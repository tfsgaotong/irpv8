package com.tfs.irp.attached.dao;

import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.entity.IrpAttachedExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface IrpAttachedDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	int countByExample(IrpAttachedExample example) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	int deleteByExample(IrpAttachedExample example) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	int deleteByPrimaryKey(Long attachedid) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	void insert(IrpAttached record) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	void insertSelective(IrpAttached record) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	List<IrpAttached> selectByExampleWithBLOBs(IrpAttachedExample example)
			throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	List<IrpAttached> selectByExampleWithoutBLOBs(IrpAttachedExample example)
			throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	IrpAttached selectByPrimaryKey(Long attachedid) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	int updateByExampleSelective(IrpAttached record, IrpAttachedExample example)
			throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	int updateByExampleWithBLOBs(IrpAttached record, IrpAttachedExample example)
			throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	int updateByExampleWithoutBLOBs(IrpAttached record,
			IrpAttachedExample example) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	int updateByPrimaryKeySelective(IrpAttached record) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	int updateByPrimaryKeyWithBLOBs(IrpAttached record) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_ATTACHED
	 * @ibatorgenerated  Tue Sep 17 10:40:38 CST 2013
	 */
	int updateByPrimaryKeyWithoutBLOBs(IrpAttached record) throws SQLException;
	/**
	 * 根据文档id获取他所有的附件
	 * @param _attDocid
	 * @return
	 * @throws SQLException
	 */
	public List<IrpAttached> getAttacehedByAttDocId(Long _attDocid,Integer _attDocidType)throws SQLException ;
	/**
	 * 查附件用于问答转换为知识
	 * @param _attDocid
	 * @return
	 * @throws SQLException
	 */
	List<IrpAttached> getPortionAttached(Long _attDocid,Integer _attdocidtype)throws SQLException ;
	/**
	 * 
	 * @param _attDocid
	 * @param _attdocidtype
	 * @return
	 * @throws SQLException
	 */
	List<IrpAttached> getAttachedByExampleSelf(IrpAttachedExample attachedExample, PageUtil pageUtil)throws SQLException ;
	List<IrpAttached> getAttachedByAttprop(Long taskid, Integer projectDocidtype);
	
}