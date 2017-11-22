package com.tfs.irp.docrecommend.dao;

import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample;
import com.tfs.irp.docrecommend.entity.IrpDocrecommend;
import com.tfs.irp.docrecommend.entity.IrpDocrecommendExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface IrpDocrecommendDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	int countByExample(IrpDocrecommendExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	int deleteByExample(IrpDocrecommendExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	int deleteByPrimaryKey(Long recommendid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	void insert(IrpDocrecommend record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	void insertSelective(IrpDocrecommend record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	List<IrpDocrecommend> selectByExample(IrpDocrecommendExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	IrpDocrecommend selectByPrimaryKey(Long recommendid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	int updateByExampleSelective(IrpDocrecommend record,
			IrpDocrecommendExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	int updateByExample(IrpDocrecommend record, IrpDocrecommendExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	int updateByPrimaryKeySelective(IrpDocrecommend record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCRECOMMEND
	 * @ibatorgenerated  Thu Jun 06 18:05:47 CST 2013
	 */
	int updateByPrimaryKey(IrpDocrecommend record) throws SQLException;
	public List<IrpDocrecommend> selectByExample(PageUtil pageUtil,IrpDocrecommendExample example) throws SQLException; 
}