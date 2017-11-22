package com.tfs.irp.term.dao.impl;

import com.tfs.irp.term.dao.IrpTermDAO;
import com.tfs.irp.term.entity.IrpTerm;
import com.tfs.irp.term.entity.IrpTermExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpTermDAOImpl extends SqlMapClientDaoSupport implements IrpTermDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public int countByExample(IrpTermExample example) throws SQLException {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("IRP_TERM.ibatorgenerated_countByExample",
                example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public int deleteByExample(IrpTermExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_TERM.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public int deleteByPrimaryKey(Long termid) throws SQLException {
        IrpTerm key = new IrpTerm();
        key.setTermid(termid);
        int rows = getSqlMapClientTemplate().delete("IRP_TERM.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public void insert(IrpTerm record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_TERM.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public void insertSelective(IrpTerm record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_TERM.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    @SuppressWarnings("unchecked")
    public List<IrpTerm> selectByExampleWithBLOBs(IrpTermExample example) throws SQLException {
        List<IrpTerm> list = getSqlMapClientTemplate().queryForList(
                "IRP_TERM.ibatorgenerated_selectByExampleWithBLOBs", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    @SuppressWarnings("unchecked")
    public List<IrpTerm> selectByExampleWithoutBLOBs(IrpTermExample example) throws SQLException {
        List<IrpTerm> list = getSqlMapClientTemplate()
                .queryForList("IRP_TERM.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public IrpTerm selectByPrimaryKey(Long termid) throws SQLException {
        IrpTerm key = new IrpTerm();
        key.setTermid(termid);
        IrpTerm record = (IrpTerm) getSqlMapClientTemplate().queryForObject(
                "IRP_TERM.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public int updateByExampleSelective(IrpTerm record, IrpTermExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_TERM.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public int updateByExampleWithBLOBs(IrpTerm record, IrpTermExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_TERM.ibatorgenerated_updateByExampleWithBLOBs", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public int updateByExampleWithoutBLOBs(IrpTerm record, IrpTermExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_TERM.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public int updateByPrimaryKeySelective(IrpTerm record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_TERM.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public int updateByPrimaryKeyWithBLOBs(IrpTerm record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_TERM.ibatorgenerated_updateByPrimaryKeyWithBLOBs", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    public int updateByPrimaryKeyWithoutBLOBs(IrpTerm record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_TERM.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_TERM
     *
     * @ibatorgenerated Mon Jul 21 17:28:15 CST 2014
     */
    private static class UpdateByExampleParms extends IrpTermExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpTermExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

    @Override
    public List<IrpTerm> selectByExampleWithBLOBs(IrpTermExample example, PageUtil _pageutil) throws SQLException {

        List<IrpTerm> list = getSqlMapClientTemplate().queryForList(
                "IRP_TERM.ibatorgenerated_selectByExampleWithBLOBs", example, _pageutil.getPageIndex(),
                _pageutil.getPageSize());

        return list;
    }

    @Override
    public List<IrpTerm> selectByExampleWithoutBLOBs(IrpTermExample example, PageUtil _pageutil) throws SQLException {
        List<IrpTerm> list = getSqlMapClientTemplate().queryForList("IRP_TERM.ibatorgenerated_selectByExample",
                example, _pageutil.getPageIndex(), _pageutil.getPageSize());
        return list;
    }

    @Override
    public int countCreateTermUserNum() throws SQLException {
        return (Integer) getSqlMapClientTemplate().queryForObject("IRP_TERM.countCreateTermUserNum");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> selectCreateTermUserIdList() throws SQLException {
        return getSqlMapClientTemplate().queryForList("IRP_TERM.selectCreateTermUserIdList");
    }

}