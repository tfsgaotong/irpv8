package com.tfs.irp.navigation.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.tfs.irp.navigation.dao.IrpUserNavigationDAO;
import com.tfs.irp.navigation.entity.IrpUserNavigation;
import com.tfs.irp.navigation.entity.IrpUserNavigationExample;
import com.tfs.irp.util.PageUtil;

public class IrpUserNavigationDAOImpl extends SqlMapClientDaoSupport implements IrpUserNavigationDAO {
   

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public int countByExample(IrpUserNavigationExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_USER_NAVIGATION.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public int deleteByExample(IrpUserNavigationExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_USER_NAVIGATION.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public int deleteByPrimaryKey(Long navigationid) throws SQLException {
        IrpUserNavigation key = new IrpUserNavigation();
        key.setNavigationid(navigationid);
        int rows = getSqlMapClientTemplate().delete("IRP_USER_NAVIGATION.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public void insert(IrpUserNavigation record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_USER_NAVIGATION.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public void insertSelective(IrpUserNavigation record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_USER_NAVIGATION.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpUserNavigation> selectByExample(IrpUserNavigationExample example) throws SQLException {
        List<IrpUserNavigation> list = getSqlMapClientTemplate().queryForList("IRP_USER_NAVIGATION.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public IrpUserNavigation selectByPrimaryKey(Long navigationid) throws SQLException {
        IrpUserNavigation key = new IrpUserNavigation();
        key.setNavigationid(navigationid);
        IrpUserNavigation record = (IrpUserNavigation) getSqlMapClientTemplate().queryForObject("IRP_USER_NAVIGATION.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public int updateByExampleSelective(IrpUserNavigation record, IrpUserNavigationExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_USER_NAVIGATION.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public int updateByExample(IrpUserNavigation record, IrpUserNavigationExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_USER_NAVIGATION.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public int updateByPrimaryKeySelective(IrpUserNavigation record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_USER_NAVIGATION.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    public int updateByPrimaryKey(IrpUserNavigation record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_USER_NAVIGATION.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_USER_NAVIGATION
     *
     * @ibatorgenerated Thu Nov 28 17:02:37 CST 2013
     */
    private static class UpdateByExampleParms extends IrpUserNavigationExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpUserNavigationExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

	@Override
	public List<IrpUserNavigation> selectByExample(
			IrpUserNavigationExample example, PageUtil _pageUtil)
			throws SQLException {
		// TODO Auto-generated method stub
		List<IrpUserNavigation> list = null;
		
		list = this.getSqlMapClientTemplate().queryForList("IRP_USER_NAVIGATION.ibatorgenerated_selectByExample", example, _pageUtil.getPageIndex(),_pageUtil.getPageSize());
		
		return list;
	}
}