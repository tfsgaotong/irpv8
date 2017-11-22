package com.tfs.irp.forum.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tfs.irp.forum.dao.IrpForumDAO;
import com.tfs.irp.forum.entity.IrpForum;
import com.tfs.irp.forum.entity.IrpForumExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public class IrpForumDAOImpl implements IrpForumDAO {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    private SqlMapClient sqlMapClient;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public IrpForumDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public int countByExample(IrpForumExample example) throws SQLException {
        Integer count = (Integer) sqlMapClient.queryForObject("IRP_FORUM.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public int deleteByExample(IrpForumExample example) throws SQLException {
        int rows = sqlMapClient.delete("IRP_FORUM.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public int deleteByPrimaryKey(Long forumid) throws SQLException {
        IrpForum key = new IrpForum();
        key.setForumid(forumid);
        int rows = sqlMapClient.delete("IRP_FORUM.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public void insert(IrpForum record) throws SQLException {
        sqlMapClient.insert("IRP_FORUM.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public void insertSelective(IrpForum record) throws SQLException {
        sqlMapClient.insert("IRP_FORUM.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    @SuppressWarnings("unchecked")
    public List<IrpForum> selectByExample(IrpForumExample example, PageUtil pageUtil) throws SQLException {
        List<IrpForum> list = sqlMapClient.queryForList("IRP_FORUM.ibatorgenerated_selectByExample", example,
                pageUtil.getPageIndex(), pageUtil.getPageSize());
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public IrpForum selectByPrimaryKey(Long forumid) throws SQLException {
        IrpForum key = new IrpForum();
        key.setForumid(forumid);
        IrpForum record = (IrpForum) sqlMapClient.queryForObject("IRP_FORUM.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public int updateByExampleSelective(IrpForum record, IrpForumExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("IRP_FORUM.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public int updateByExample(IrpForum record, IrpForumExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("IRP_FORUM.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public int updateByPrimaryKeySelective(IrpForum record) throws SQLException {
        int rows = sqlMapClient.update("IRP_FORUM.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    public int updateByPrimaryKey(IrpForum record) throws SQLException {
        int rows = sqlMapClient.update("IRP_FORUM.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_FORUM
     *
     * @ibatorgenerated Tue Sep 26 15:00:02 CST 2017
     */
    private static class UpdateByExampleParms extends IrpForumExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpForumExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> selectCreateForumUserIdList() throws SQLException {
        return sqlMapClient.queryForList("IRP_FORUM.selectCreateForumUserIdList");
    }

    @Override
    public int countForumListByAllCategoryId(List<Long> categoryIdList) throws SQLException {
        Integer count = (Integer) sqlMapClient
                .queryForObject("IRP_FORUM.countForumListByAllCategoryId", categoryIdList);
        return count;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<IrpForum> selectForumListByAllCategoryId(List<Long> categoryIdList, PageUtil pageUtil)
            throws SQLException {
        return sqlMapClient.queryForList("IRP_FORUM.selectForumListByAllCategoryId", categoryIdList,
                pageUtil.getPageIndex(), pageUtil.getPageSize());
    }
}