package com.tfs.irp.uservaluelink.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.uservaluelink.dao.IrpUserValueLinkDAO;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLinkExample;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class IrpUserValueLinkDAOImpl extends SqlMapClientDaoSupport implements IrpUserValueLinkDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_VALUE_LINK
	 * @ibatorgenerated  Mon Jul 15 15:31:37 CST 2013
	 */
	public int countByExample(IrpUserValueLinkExample example)
			throws SQLException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"IRP_USER_VALUE_LINK.ibatorgenerated_countByExample", example);
		return count;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_VALUE_LINK
	 * @ibatorgenerated  Mon Jul 15 15:31:37 CST 2013
	 */
	public int deleteByExample(IrpUserValueLinkExample example)
			throws SQLException {
		int rows = getSqlMapClientTemplate().delete(
				"IRP_USER_VALUE_LINK.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_VALUE_LINK
	 * @ibatorgenerated  Mon Jul 15 15:31:37 CST 2013
	 */
	public void insert(IrpUserValueLink record) throws SQLException {
		getSqlMapClientTemplate().insert("IRP_USER_VALUE_LINK.ibatorgenerated_insert",
				record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_VALUE_LINK
	 * @ibatorgenerated  Mon Jul 15 15:31:37 CST 2013
	 */
	public void insertSelective(IrpUserValueLink record) throws SQLException {
		getSqlMapClientTemplate().insert(
				"IRP_USER_VALUE_LINK.ibatorgenerated_insertSelective", record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_VALUE_LINK
	 * @ibatorgenerated  Mon Jul 15 15:31:37 CST 2013
	 */
	@SuppressWarnings("unchecked")
	public List<IrpUserValueLink> selectByExample(
			IrpUserValueLinkExample example) throws SQLException {
		List<IrpUserValueLink> list = getSqlMapClientTemplate().queryForList(
				"IRP_USER_VALUE_LINK.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_VALUE_LINK
	 * @ibatorgenerated  Mon Jul 15 15:31:37 CST 2013
	 */
	public int updateByExampleSelective(IrpUserValueLink record,
			IrpUserValueLinkExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_USER_VALUE_LINK.ibatorgenerated_updateByExampleSelective",
				parms);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_USER_VALUE_LINK
	 * @ibatorgenerated  Mon Jul 15 15:31:37 CST 2013
	 */
	public int updateByExample(IrpUserValueLink record,
			IrpUserValueLinkExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_USER_VALUE_LINK.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_USER_VALUE_LINK
	 * @ibatorgenerated  Mon Jul 15 15:31:37 CST 2013
	 */
	private static class UpdateByExampleParms extends IrpUserValueLinkExample {
		private Object record;

		public UpdateByExampleParms(Object record,
				IrpUserValueLinkExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	@Override
	public List<IrpUserValueLink> selectByExample(
			IrpUserValueLinkExample example, PageUtil pageUtil)
			throws SQLException {
		List<IrpUserValueLink> list = getSqlMapClientTemplate().queryForList(
				"IRP_USER_VALUE_LINK.ibatorgenerated_selectByExample", example,pageUtil.getPageIndex(),pageUtil.getPageSize());
		return list;
	}

	@Override
	public int sumScoreByUserid(long _userid)throws SQLException {
		int score = 0;
		Map map = new HashMap();
		map.put("userid", _userid);

		score = (Integer) this.getSqlMapClientTemplate().queryForObject("IRP_USER_VALUE_LINK.ibatorgenerated_sumByScoreOfUserid",map);

		return score;
	}
	@Override
	public int getScoreByUserid(long _userid)throws SQLException {
		int score = 0;
		Map map = new HashMap();
		map.put("userid", _userid);

		score = (Integer) this.getSqlMapClientTemplate().queryForObject("IRP_USER_VALUE_LINK.ibatorgenerated_sumByScoreOfUserid1",map);

		return score;
	}

	@Override
	public int sumExperienceByUserid(long _userid)throws SQLException {
		int experience = 0;
		Map map = new HashMap();
		map.put("userid", _userid);
			experience =   (Integer) this.getSqlMapClientTemplate().queryForObject("IRP_USER_VALUE_LINK.ibatorgenerated_sumByExperienceOfUserid", map);
		return experience;
	}
	
	@Override
	public List<Map<String, Object>> userRankByDate(Map<String, Object> _mParam, int _nDataCount)throws SQLException {
		//最大数据个数
		List<Map<String, Object>> list = this.getSqlMapClientTemplate().queryForList("IRP_USER_VALUE_LINK.ibatorgenerated_userRankByDate",_mParam, 0, _nDataCount);
		return list;
	}
}