package com.tfs.irp.docscore.dao.impl;

import com.tfs.irp.docscore.dao.IrpDocumentScoreDAO;
import com.tfs.irp.docscore.entity.IrpDocumentScore;
import com.tfs.irp.docscore.entity.IrpDocumentScoreExample;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpDocumentScoreDAOImpl extends SqlMapClientDaoSupport implements IrpDocumentScoreDAO {
	@Override
	public HashMap  avgScore(Long docid) throws SQLException {
		// TODO Auto-generated method stub
		return (HashMap)getSqlMapClientTemplate().queryForObject("IRP_DOCUMENT_SCORE.avgscore",docid);
	}
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public int countByExample(IrpDocumentScoreExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_DOCUMENT_SCORE.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public int deleteByExample(IrpDocumentScoreExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_DOCUMENT_SCORE.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public void insert(IrpDocumentScore record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_DOCUMENT_SCORE.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public void insertSelective(IrpDocumentScore record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_DOCUMENT_SCORE.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    @SuppressWarnings("unchecked")
    public List<IrpDocumentScore> selectByExample(IrpDocumentScoreExample example) throws SQLException {
        List<IrpDocumentScore> list = getSqlMapClientTemplate().queryForList("IRP_DOCUMENT_SCORE.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public int updateByExampleSelective(IrpDocumentScore record, IrpDocumentScoreExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_DOCUMENT_SCORE.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    public int updateByExample(IrpDocumentScore record, IrpDocumentScoreExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_DOCUMENT_SCORE.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_DOCUMENT_SCORE
     *
     * @ibatorgenerated Mon Sep 09 15:25:11 CST 2013
     */
    private static class UpdateByExampleParms extends IrpDocumentScoreExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpDocumentScoreExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}