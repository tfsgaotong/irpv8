package com.tfs.irp.vote.dao;

import com.tfs.irp.util.PageUtil;
import com.tfs.irp.vote.entity.IrpVote;
import com.tfs.irp.vote.entity.IrpVoteExample;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IrpVoteDAO {
    /**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	int countByExample(IrpVoteExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	int deleteByExample(IrpVoteExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	int deleteByPrimaryKey(Long voteid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	void insert(IrpVote record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	void insertSelective(IrpVote record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	List<IrpVote> selectByExample(IrpVoteExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	IrpVote selectByPrimaryKey(Long voteid) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	int updateByExampleSelective(IrpVote record, IrpVoteExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	int updateByExample(IrpVote record, IrpVoteExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	int updateByPrimaryKeySelective(IrpVote record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_VOTE
	 * @ibatorgenerated  Wed Nov 20 16:47:33 CST 2013
	 */
	int updateByPrimaryKey(IrpVote record) throws SQLException;

	public Long selectMaxvoteidByuserid(Long cruserid);
	
	public List<?> findquestionandoption(Map<String, Object> map);
	
	
	public List<IrpVote> selectByExample(IrpVoteExample example, PageUtil pageUtil);
	/**
	 * 我参与的
	 * @param map
	 * @return
	 */
	public int selectByCruseridcount(Map<String,Object> map);
	public List<IrpVote> selectByCruserid(Map<String,Object> map, PageUtil pageUtil);
}