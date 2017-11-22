package com.tfs.irp.form.dao.impl;

import com.tfs.irp.form.dao.IrpFormDAO;
import com.tfs.irp.form.entity.IrpForm;
import com.tfs.irp.form.entity.IrpFormExample;
import com.tfs.irp.formcolumn.entity.IrpFormColumnExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpFormDAOImpl extends SqlMapClientDaoSupport implements IrpFormDAO {
   

    

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public int countByExample(IrpFormExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_FORM.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public int deleteByExample(IrpFormExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_FORM.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public int deleteByPrimaryKey(Long formid) throws SQLException {
        IrpForm key = new IrpForm();
        key.setFormid(formid);
        int rows = getSqlMapClientTemplate().delete("IRP_FORM.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public void insert(IrpForm record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_FORM.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public void insertSelective(IrpForm record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_FORM.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    @SuppressWarnings("unchecked")
    public List<IrpForm> selectByExampleWithBLOBs(IrpFormExample example) throws SQLException {
        List<IrpForm> list = getSqlMapClientTemplate().queryForList("IRP_FORM.ibatorgenerated_selectByExampleWithBLOBs", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    @SuppressWarnings("unchecked")
    public List<IrpForm> selectByExampleWithoutBLOBs(IrpFormExample example) throws SQLException {
        List<IrpForm> list = getSqlMapClientTemplate().queryForList("IRP_FORM.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public IrpForm selectByPrimaryKey(Long formid) throws SQLException {
        IrpForm key = new IrpForm();
        key.setFormid(formid);
        IrpForm record = (IrpForm) getSqlMapClientTemplate().queryForObject("IRP_FORM.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public int updateByExampleSelective(IrpForm record, IrpFormExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_FORM.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public int updateByExampleWithBLOBs(IrpForm record, IrpFormExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_FORM.ibatorgenerated_updateByExampleWithBLOBs", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public int updateByExampleWithoutBLOBs(IrpForm record, IrpFormExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_FORM.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public int updateByPrimaryKeySelective(IrpForm record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_FORM.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public int updateByPrimaryKeyWithBLOBs(IrpForm record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_FORM.ibatorgenerated_updateByPrimaryKeyWithBLOBs", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    public int updateByPrimaryKeyWithoutBLOBs(IrpForm record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_FORM.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_FORM
     *
     * @ibatorgenerated Wed Sep 28 15:42:44 CST 2016
     */
    private static class UpdateByExampleParms extends IrpFormExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpFormExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

	@Override
	public List<IrpForm> selectByExampleWithoutBLOBsByPage(PageUtil _pageUtil,
			IrpFormExample example) throws SQLException {
		 @SuppressWarnings("unchecked")
		List<IrpForm> list = getSqlMapClientTemplate().queryForList("IRP_FORM.ibatorgenerated_selectByExample", example,_pageUtil.getPageIndex(),_pageUtil.getPageSize());
	        return list;
	}

	@Override
	public int createSql(String _sql) {
		int msg=0;
		if(_sql!=""&&_sql!=null){
			
			Map map=new HashMap();
			map.put("sql", _sql); 
			 getSqlMapClientTemplate().insert("IRP_FORM.createSql", map);
			 msg=1;
		}
	 return msg;
		
	}

	@Override
	public int selectTableName(String formtablename) {
		Map map=new HashMap();
		StringBuffer sql = new StringBuffer(""); 
		sql.append("select COUNT(*) from user_tables s ");
		if(formtablename!=""&&formtablename!=null){
			sql.append("  where s.table_name=");
			sql.append("'"+formtablename.toUpperCase()+"'");//要转化为大写
			//System.out.println(formtablename.toUpperCase()+"这是转化为的大小写");
			
			
		}
		
		map.put("sql", sql.toString()); 
		 Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_FORM.isExistTablename", map);
	        return count;
	}

	@Override
	public int dropTable(String _sql) {
		int msg=0;
		if(_sql!=""&&_sql!=null){
		Map map=new HashMap();
		map.put("sql", _sql);
		   getSqlMapClientTemplate().insert("IRP_FORM.createSql", map);
		   msg=1;}
	        return msg ;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectFromInfo(String _sql) {
		List<Object> list = null;
		if(_sql!=""&&_sql!=null){
		Map map=new HashMap();
		map.put("sql", _sql);
		list = getSqlMapClientTemplate().queryForList("IRP_FORM.selectFromInfo", map);
		}
		return list;
	}

	@Override
	public int countBySql(String _sql) {
		int count  = 0;
		if(_sql!=""&&_sql!=null){
			Map map=new HashMap();
			map.put("sql", _sql);
			count =  (Integer) getSqlMapClientTemplate().queryForObject("IRP_FORM.isExistTablename", map);
			}
		return count;
	}

	@Override
	public Object findObject(String _sql) throws SQLException {
		Object obj = new Object();
		Map map = new HashMap();		
		if(_sql!=""&&_sql!=null){
			map.put("sql", _sql);
			obj = getSqlMapClientTemplate().queryForObject("IRP_FORM.selectFromInfo", map);
		}
		return obj;
	}

	@Override
	public int countByColumn(IrpFormColumnExample example) {
		int count  = 0;
		count =  (Integer) getSqlMapClientTemplate().queryForObject("IRP_FORM.ibatorgenerated_countByColumn", example);
		return count;
	}
	@Override
	public int deleteSql(String _sql) {
		int docid=0;
		if(_sql!=""&&_sql!=null){
			Map map=new HashMap();
			map.put("sql", _sql);
			Integer doc=(Integer) getSqlMapClientTemplate().queryForObject("IRP_FORM.deleteSql", map);
			if(doc==null){
				docid=0;
			}else{
				docid=doc;
			}
			}
		return docid ;
		
	}
	@Override
	public List<IrpForm> getAllForm(IrpFormExample example) throws SQLException {
		List<IrpForm> list = getSqlMapClientTemplate().queryForList("IRP_FORM.ibatorgenerated_selectByExample",example);
		return list;
	}
}