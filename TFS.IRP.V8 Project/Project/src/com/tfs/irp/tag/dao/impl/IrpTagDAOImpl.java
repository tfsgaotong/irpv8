package com.tfs.irp.tag.dao.impl;

import com.tfs.irp.tag.dao.IrpTagDAO;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.entity.IrpTagExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpTagDAOImpl extends SqlMapClientDaoSupport implements IrpTagDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public int countByExample(IrpTagExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_TAG.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public int deleteByExample(IrpTagExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_TAG.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public int deleteByPrimaryKey(Long tagid) throws SQLException {
        IrpTag key = new IrpTag();
        key.setTagid(tagid);
        int rows = getSqlMapClientTemplate().delete("IRP_TAG.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public void insert(IrpTag record) throws SQLException {
    	getSqlMapClientTemplate().insert("IRP_TAG.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public void insertSelective(IrpTag record) throws SQLException {
    	getSqlMapClientTemplate().insert("IRP_TAG.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    @SuppressWarnings("unchecked")
    public List<IrpTag> selectByExample(IrpTagExample example) throws SQLException {
        List<IrpTag> list = getSqlMapClientTemplate().queryForList("IRP_TAG.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public IrpTag selectByPrimaryKey(Long tagid) throws SQLException {
        IrpTag key = new IrpTag();
        key.setTagid(tagid);
        IrpTag record = (IrpTag) getSqlMapClientTemplate().queryForObject("IRP_TAG.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public int updateByExampleSelective(IrpTag record, IrpTagExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_TAG.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public int updateByExample(IrpTag record, IrpTagExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_TAG.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public int updateByPrimaryKeySelective(IrpTag record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_TAG.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    public int updateByPrimaryKey(IrpTag record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_TAG.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table irp_tag
     *
     * @ibatorgenerated Sat Nov 22 18:18:14 CST 2014
     */
    private static class UpdateByExampleParms extends IrpTagExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpTagExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	
	@Override
    public List<IrpTag> selectByExample(PageUtil pageUtil, IrpTagExample example) throws SQLException {
        List<IrpTag> list = getSqlMapClientTemplate().queryForList("IRP_TAG.ibatorgenerated_selectByExample",example,pageUtil.getPageIndex(),pageUtil.getPageSize());
        return list;
    }
    
    @Override
    public List<IrpTag> selectTagByUserId(long _nUserId) throws SQLException {
        List<IrpTag> list = getSqlMapClientTemplate().queryForList("IRP_TAG.selectByUserId", _nUserId);
        return list;
    }
}