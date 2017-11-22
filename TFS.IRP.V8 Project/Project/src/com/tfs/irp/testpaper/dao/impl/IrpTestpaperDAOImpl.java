package com.tfs.irp.testpaper.dao.impl;

import com.tfs.irp.testpaper.dao.IrpTestpaperDAO;
import com.tfs.irp.testpaper.entity.IrpTestpaper;
import com.tfs.irp.testpaper.entity.IrpTestpaperExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpTestpaperDAOImpl  extends SqlMapClientDaoSupport  implements IrpTestpaperDAO {
	
	
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    public int countByExample(IrpTestpaperExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("irp_testpaper.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    public int deleteByExample(IrpTestpaperExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("irp_testpaper.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    public int deleteByPrimaryKey(Long paperid) throws SQLException {
        IrpTestpaper key = new IrpTestpaper();
        key.setPaperid(paperid);
        int rows = getSqlMapClientTemplate().delete("irp_testpaper.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    public void insert(IrpTestpaper record) throws SQLException {
        getSqlMapClientTemplate().insert("irp_testpaper.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    public void insertSelective(IrpTestpaper record) throws SQLException {
        getSqlMapClientTemplate().insert("irp_testpaper.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    @SuppressWarnings("unchecked")
    public List<IrpTestpaper> selectByExample(IrpTestpaperExample example) throws SQLException {
        List<IrpTestpaper> list = getSqlMapClientTemplate().queryForList("irp_testpaper.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    public IrpTestpaper selectByPrimaryKey(Long paperid) throws SQLException {
        IrpTestpaper key = new IrpTestpaper();
        key.setPaperid(paperid);
        IrpTestpaper record = (IrpTestpaper) getSqlMapClientTemplate().queryForObject("irp_testpaper.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    public int updateByExampleSelective(IrpTestpaper record, IrpTestpaperExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("irp_testpaper.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    public int updateByExample(IrpTestpaper record, IrpTestpaperExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("irp_testpaper.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    public int updateByPrimaryKeySelective(IrpTestpaper record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("irp_testpaper.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    public int updateByPrimaryKey(IrpTestpaper record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("irp_testpaper.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table irp_testpaper
     *
     * @ibatorgenerated Fri Sep 19 16:19:47 CST 2014
     */
    private static class UpdateByExampleParms extends IrpTestpaperExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpTestpaperExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

	@Override
	public List<IrpTestpaper> selectByExample(IrpTestpaperExample example,
			PageUtil _pageutil) throws SQLException {
		// TODO Auto-generated method stub
		
        List<IrpTestpaper> list = getSqlMapClientTemplate().queryForList("irp_testpaper.ibatorgenerated_selectByExample", example,_pageutil.getPageIndex(),_pageutil.getPageSize());
        
        return list;
	}
}