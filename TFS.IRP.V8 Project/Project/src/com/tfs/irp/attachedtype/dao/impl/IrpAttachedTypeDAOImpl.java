package com.tfs.irp.attachedtype.dao.impl;

import com.tfs.irp.attachedtype.dao.IrpAttachedTypeDAO;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.entity.IrpAttachedTypeExample;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpAttachedTypeDAOImpl extends SqlMapClientDaoSupport implements IrpAttachedTypeDAO {
	@Override
	public Object  AttachedTypeSuffixImage(Long _typeid)throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("IRP_ATTACHED_TYPE.AttachedTypeSuffix",_typeid); 
	}
	@Override
	public List<IrpAttachedType>  getSuffixExits(String _suffix)throws SQLException {
		/**
		 * 将它转换为大写
		 */ 
		return  getSqlMapClientTemplate().queryForList("IRP_ATTACHED_TYPE.getSuffixExits",_suffix.toUpperCase()); 
	}
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    public int countByExample(IrpAttachedTypeExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_ATTACHED_TYPE.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    public int deleteByExample(IrpAttachedTypeExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_ATTACHED_TYPE.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    public void insert(IrpAttachedType record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_ATTACHED_TYPE.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    public void insertSelective(IrpAttachedType record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_ATTACHED_TYPE.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpAttachedType> selectByExample(IrpAttachedTypeExample example) throws SQLException {
        List<IrpAttachedType> list = getSqlMapClientTemplate().queryForList("IRP_ATTACHED_TYPE.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    public int updateByExampleSelective(IrpAttachedType record, IrpAttachedTypeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_ATTACHED_TYPE.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    public int updateByExample(IrpAttachedType record, IrpAttachedTypeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_ATTACHED_TYPE.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_ATTACHED_TYPE
     *
     * @ibatorgenerated Tue Apr 09 15:38:12 CST 2013
     */
    private static class UpdateByExampleParms extends IrpAttachedTypeExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpAttachedTypeExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}