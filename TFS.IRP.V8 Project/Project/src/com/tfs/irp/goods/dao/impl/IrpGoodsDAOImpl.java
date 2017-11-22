package com.tfs.irp.goods.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;


import com.tfs.irp.goods.dao.IrpGoodsDAO;
import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.entity.IrpGoodsExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpGoodsDAOImpl extends SqlMapClientDaoSupport implements IrpGoodsDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public void insert(IrpGoods record) throws SQLException {
    	getSqlMapClientTemplate().insert("IRP_GOODS.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public int updateByPrimaryKey(IrpGoods record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_GOODS.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public int updateByPrimaryKeySelective(IrpGoods record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_GOODS.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public List<IrpGoods> selectByExample(IrpGoodsExample example) throws SQLException {
        List<IrpGoods> list = getSqlMapClientTemplate().queryForList("IRP_GOODS.abatorgenerated_selectByExample", example);
        return list;
    }
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_BINDING
     *
     * @abatorgenerated Thu Mar 30 14:59:30 CST 2017
     */
    @Override
    public List selectallByExample(PageUtil pageUtil,IrpGoodsExample example) throws SQLException {
        List<IrpGoodsExample> list = getSqlMapClientTemplate().queryForList("IRP_GOODS.abatorgenerated_selectByExample", example,pageUtil.getPageIndex(),pageUtil.getPageSize());
        return list;
    }
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public IrpGoods selectByPrimaryKey(Long goodsid) throws SQLException {
        IrpGoods key = new IrpGoods();
        key.setGoodsid(goodsid);
        IrpGoods record = (IrpGoods) getSqlMapClientTemplate().queryForObject("IRP_GOODS.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public int deleteByExample(IrpGoodsExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_GOODS.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public int deleteByPrimaryKey(Long goodsid) throws SQLException {
        IrpGoods key = new IrpGoods();
        key.setGoodsid(goodsid);
        int rows = getSqlMapClientTemplate().delete("IRP_GOODS.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public int countByExample(IrpGoodsExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_GOODS.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public int updateByExampleSelective(IrpGoods record, IrpGoodsExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_GOODS.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    public int updateByExample(IrpGoods record, IrpGoodsExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_GOODS.abatorgenerated_updateByExample", parms);
        return rows;
    }
    
    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    private static class UpdateByExampleParms extends IrpGoodsExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpGoodsExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    @Override
	public int selectgoodsno(String goodsno) {
		Map map=new HashMap();
		StringBuffer sql = new StringBuffer(""); 
		sql.append("select COUNT(*) from IRP_GOODS ");
		if(goodsno!=""&&goodsno!=null){
			sql.append("  where GOODSNO="+"'"+goodsno+"'");
			
		}
		
		map.put("sql", sql.toString()); 
		 Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_GOODS.isExistGoodsno", map);
	        return count;
	}
}