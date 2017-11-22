package com.tfs.irp.assetype.dao.impl;

import com.tfs.irp.assetype.dao.IrpAssetypeDAO;
import com.tfs.irp.assetype.entity.IrpAssetype;
import com.tfs.irp.assetype.entity.IrpAssetypeExample;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpAssetypeDAOImpl extends SqlMapClientDaoSupport implements IrpAssetypeDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public IrpAssetypeDAOImpl() {
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public int countByExample(IrpAssetypeExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_ASSETYPE.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public int deleteByExample(IrpAssetypeExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_ASSETYPE.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public int deleteByPrimaryKey(Long asseroomapplytypeid) throws SQLException {
        IrpAssetype key = new IrpAssetype();
        key.setAsseroomapplytypeid(asseroomapplytypeid);
        int rows = getSqlMapClientTemplate().delete("IRP_ASSETYPE.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public void insert(IrpAssetype record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_ASSETYPE.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public void insertSelective(IrpAssetype record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_ASSETYPE.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    @SuppressWarnings("unchecked")
    public List<IrpAssetype> selectByExample(IrpAssetypeExample example) throws SQLException {
        List<IrpAssetype> list = getSqlMapClientTemplate().queryForList("IRP_ASSETYPE.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public IrpAssetype selectByPrimaryKey(Long asseroomapplytypeid) throws SQLException {
        IrpAssetype key = new IrpAssetype();
        key.setAsseroomapplytypeid(asseroomapplytypeid);
        IrpAssetype record = (IrpAssetype) getSqlMapClientTemplate().queryForObject("IRP_ASSETYPE.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public int updateByExampleSelective(IrpAssetype record, IrpAssetypeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_ASSETYPE.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public int updateByExample(IrpAssetype record, IrpAssetypeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_ASSETYPE.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public int updateByPrimaryKeySelective(IrpAssetype record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_ASSETYPE.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    public int updateByPrimaryKey(IrpAssetype record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_ASSETYPE.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_ASSETYPE
     *
     * @ibatorgenerated Thu Aug 25 17:07:22 CST 2016
     */
    private static class UpdateByExampleParms extends IrpAssetypeExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpAssetypeExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}