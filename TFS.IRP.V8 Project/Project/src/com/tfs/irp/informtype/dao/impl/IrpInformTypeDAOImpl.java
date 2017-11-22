package com.tfs.irp.informtype.dao.impl;

import com.tfs.irp.informtype.dao.IrpInformTypeDAO;
import com.tfs.irp.informtype.entity.IrpInformType;
import com.tfs.irp.informtype.entity.IrpInformTypeExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpInformTypeDAOImpl extends SqlMapClientDaoSupport implements IrpInformTypeDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    public int countByExample(IrpInformTypeExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_INFORM_TYPE.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    public int deleteByExample(IrpInformTypeExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_INFORM_TYPE.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    public int deleteByPrimaryKey(Long informtypeid) throws SQLException {
        IrpInformType key = new IrpInformType();
        key.setInformtypeid(informtypeid);
        int rows = getSqlMapClientTemplate().delete("IRP_INFORM_TYPE.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    public void insert(IrpInformType record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_INFORM_TYPE.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    public void insertSelective(IrpInformType record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_INFORM_TYPE.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpInformType> selectByExample(IrpInformTypeExample example) throws SQLException {
        List<IrpInformType> list = getSqlMapClientTemplate().queryForList("IRP_INFORM_TYPE.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    public IrpInformType selectByPrimaryKey(Long informtypeid) throws SQLException {
        IrpInformType key = new IrpInformType();
        key.setInformtypeid(informtypeid);
        IrpInformType record = (IrpInformType) getSqlMapClientTemplate().queryForObject("IRP_INFORM_TYPE.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    public int updateByExampleSelective(IrpInformType record, IrpInformTypeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_INFORM_TYPE.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    public int updateByExample(IrpInformType record, IrpInformTypeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_INFORM_TYPE.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    public int updateByPrimaryKeySelective(IrpInformType record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_INFORM_TYPE.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    public int updateByPrimaryKey(IrpInformType record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_INFORM_TYPE.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_INFORM_TYPE
     *
     * @ibatorgenerated Mon Sep 02 10:13:46 CST 2013
     */
    private static class UpdateByExampleParms extends IrpInformTypeExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpInformTypeExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

	@Override
	public List<IrpInformType> selectByExample(IrpInformTypeExample example, PageUtil pageUtil) {
		List<IrpInformType> list = null;
		if(pageUtil!=null ){
			list = getSqlMapClientTemplate().queryForList("IRP_INFORM_TYPE.ibatorgenerated_selectByExample", example,pageUtil.getPageIndex(),pageUtil.getPageSize());
		}else{
			list = getSqlMapClientTemplate().queryForList("IRP_INFORM_TYPE.ibatorgenerated_selectByExample");
		}
		return list;
	}
}