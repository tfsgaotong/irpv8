package com.tfs.irp.goods.service;

import java.util.List;

import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.entity.IrpGoodsExample;
import com.tfs.irp.util.PageUtil;


public interface IrpGoodsService {
	/**
	 * 添加商品
	 * @param goods
	 * @return
	 */
	public int addGoods(IrpGoods goods); 
	/**
	 *删除商品
	 * @param goodsid
	 * @return
	 */
	public int deleteGoods(Long goodsid);
	/**
	 *删除多条商品
	 * @param goodsid
	 * @return
	 */
	public int deleteMoreGoods(String goodsids);
	/**
	 * 根据ID查询商品信息
	 * @param goodsid
	 * @return
	 */
	public IrpGoods findGoodsByGoodsid(Long goodsid);
	/**
	 * 修改商品
	 * @param goods
	 * @return
	 */
	public int updateGoodsByGoodsid(IrpGoods goods);
	
	 
	/**
	 * 列表显示所有商品带分页
	 * @param goodsid
	 * @return
	 */
	List<IrpGoods> listAllGoods(PageUtil pageUtil,String _orderby,String searchType,String searchWord,String orderField);
	List<IrpGoods> showAllGoods(PageUtil pageUtil,Long coverstate,String searchWord,String _orderby);
	List<IrpGoods> showAllGoodsByUserid(PageUtil pageUtil,Long userid,String searchWord,String _orderby);
	
	/**
	 * 统计商品
	 * @param goods
	 * @return
	 */
	public int countGoods(String searchType,String searchWord); 
	public int countGoods(Long coverstate,String searchWord); 
	public int countGoodsByUserid(Long userid,String searchWord); 
	/**
	 * 验证编码唯一性
	 * @param goods
	 * @return
	 */
	boolean isexitcode(String goodsno);
	/**查询大于等于此商品需号的商品
	 * @param reorder
	 * @return
	 */
	public List<IrpGoods> selectByReorder(int reorder);
	/**等于此序列号
	 * @param newreorder
	 * @return
	 */
	public IrpGoods selectByeqReorder(int newreorder);
}
