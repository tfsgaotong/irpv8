package com.tfs.irp.tag.dao.impl;

import com.tfs.irp.tag.dao.IrpUserTagDAO;
import com.tfs.irp.tag.entity.IrpUserTag;
import com.tfs.irp.tag.entity.IrpUserTagExample;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpUserTagDAOImpl extends SqlMapClientDaoSupport implements IrpUserTagDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    public int countByExample(IrpUserTagExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_USER_TAG.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    public int deleteByExample(IrpUserTagExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_USER_TAG.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    public int deleteByPrimaryKey(Long usertagid) throws SQLException {
        IrpUserTag key = new IrpUserTag();
        key.setUsertagid(usertagid);
        int rows = getSqlMapClientTemplate().delete("IRP_USER_TAG.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    public void insert(IrpUserTag record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_USER_TAG.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    public void insertSelective(IrpUserTag record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_USER_TAG.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpUserTag> selectByExample(IrpUserTagExample example) throws SQLException {
        List<IrpUserTag> list = getSqlMapClientTemplate().queryForList("IRP_USER_TAG.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    public IrpUserTag selectByPrimaryKey(Long usertagid) throws SQLException {
        IrpUserTag key = new IrpUserTag();
        key.setUsertagid(usertagid);
        IrpUserTag record = (IrpUserTag) getSqlMapClientTemplate().queryForObject("IRP_USER_TAG.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    public int updateByExampleSelective(IrpUserTag record, IrpUserTagExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_USER_TAG.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    public int updateByExample(IrpUserTag record, IrpUserTagExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_USER_TAG.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    public int updateByPrimaryKeySelective(IrpUserTag record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_USER_TAG.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    public int updateByPrimaryKey(IrpUserTag record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_USER_TAG.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_USER_TAG
     *
     * @ibatorgenerated Thu Apr 25 14:26:24 CST 2013
     */
    private static class UpdateByExampleParms extends IrpUserTagExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpUserTagExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}