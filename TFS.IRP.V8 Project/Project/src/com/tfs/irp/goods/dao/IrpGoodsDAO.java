package com.tfs.irp.goods.dao;

import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.entity.IrpGoodsExample;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface IrpGoodsDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    void insert(IrpGoods record) throws SQLException;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    int updateByPrimaryKey(IrpGoods record) throws SQLException;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    int updateByPrimaryKeySelective(IrpGoods record) throws SQLException;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    List<IrpGoods> selectByExample(IrpGoodsExample example) throws SQLException;
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    List selectallByExample(PageUtil pageUtil,IrpGoodsExample example) throws SQLException;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    IrpGoods selectByPrimaryKey(Long goodsid) throws SQLException;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    int deleteByExample(IrpGoodsExample example) throws SQLException;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    int deleteByPrimaryKey(Long goodsid) throws SQLException;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    int countByExample(IrpGoodsExample example) throws SQLException;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    int updateByExampleSelective(IrpGoods record, IrpGoodsExample example) throws SQLException;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table IRP_GOODS
     *
     * @abatorgenerated Wed Apr 12 17:13:06 CST 2017
     */
    int updateByExample(IrpGoods record, IrpGoodsExample example) throws SQLException;
    
    int selectgoodsno(String goodsno);
}