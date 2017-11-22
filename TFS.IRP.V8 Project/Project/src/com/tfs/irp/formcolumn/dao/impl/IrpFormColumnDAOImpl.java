package com.tfs.irp.formcolumn.dao.impl;

import com.tfs.irp.formcolumn.dao.IrpFormColumnDAO;
import com.tfs.irp.formcolumn.entity.IrpFormColumn;
import com.tfs.irp.formcolumn.entity.IrpFormColumnExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpFormColumnDAOImpl extends SqlMapClientDaoSupport  implements IrpFormColumnDAO {
  
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    public int countByExample(IrpFormColumnExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_FORM_COLUMN.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    public int deleteByExample(IrpFormColumnExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_FORM_COLUMN.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    public int deleteByPrimaryKey(Long columnid) throws SQLException {
        IrpFormColumn key = new IrpFormColumn();
        key.setColumnid(columnid);
        int rows = getSqlMapClientTemplate().delete("IRP_FORM_COLUMN.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    public void insert(IrpFormColumn record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_FORM_COLUMN.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    public void insertSelective(IrpFormColumn record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_FORM_COLUMN.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    @SuppressWarnings("unchecked")
    public List<IrpFormColumn> selectByExample(IrpFormColumnExample example) throws SQLException {
        List<IrpFormColumn> list = getSqlMapClientTemplate().queryForList("IRP_FORM_COLUMN.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    public IrpFormColumn selectByPrimaryKey(Long columnid) throws SQLException {
        IrpFormColumn key = new IrpFormColumn();
        key.setColumnid(columnid);
        IrpFormColumn record = (IrpFormColumn) getSqlMapClientTemplate().queryForObject("IRP_FORM_COLUMN.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    public int updateByExampleSelective(IrpFormColumn record, IrpFormColumnExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_FORM_COLUMN.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    public int updateByExample(IrpFormColumn record, IrpFormColumnExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_FORM_COLUMN.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    public int updateByPrimaryKeySelective(IrpFormColumn record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_FORM_COLUMN.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    public int updateByPrimaryKey(IrpFormColumn record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_FORM_COLUMN.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_FORM_COLUMN
     *
     * @ibatorgenerated Wed Sep 28 15:47:17 CST 2016
     */
    private static class UpdateByExampleParms extends IrpFormColumnExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpFormColumnExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<IrpFormColumn> selectByExample(PageUtil pageUtil,IrpFormColumnExample example) throws SQLException {
    	List<IrpFormColumn> list = getSqlMapClientTemplate().queryForList("IRP_FORM_COLUMN.ibatorgenerated_selectByExample", example,pageUtil.getPageIndex(),pageUtil.getPageSize());
    	return list;
    }
}