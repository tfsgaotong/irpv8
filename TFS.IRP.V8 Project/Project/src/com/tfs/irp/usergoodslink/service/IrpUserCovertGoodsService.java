package com.tfs.irp.usergoodslink.service;

import java.util.Date;
import java.util.List;

import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.usergoodslink.entity.IrpUserCovertGoods;
import com.tfs.irp.util.PageUtil;

public interface IrpUserCovertGoodsService {
	/**
	 * 添加兑换记录
	 * @param goods
	 * @return
	 */
	public int addCovert(IrpUserCovertGoods covert); 
	/**
	 *删除兑换记录
	 * @param goodsid
	 * @return
	 */
	public int deleteCovert(Long covertid);
	/**
	 *删除多条兑换记录
	 * @param goodsid
	 * @return
	 */
	public int deleteMoreCovert(Long covertid);
	/**
	 * 根据ID查询商兑换记录
	 * @param goodsid
	 * @return
	 */
	public IrpUserCovertGoods findCovertByCovertid(Long covertid);
	/**
	 * 修改兑换记录
	 * @param goods
	 * @return
	 */
	public int updateCovertByCovertid(IrpUserCovertGoods covert);
	
	 
	/**
	 * 列表显示所有兑换记录带分页
	 * @param goodsid
	 * @return
	 */
	List<IrpUserCovertGoods> showAllCovert(PageUtil pageUtil,String orderField,String orderBy);
	List<IrpUserCovertGoods> findCovertOfPageSize(PageUtil pageUtil,
			String _oOrderby,IrpUserCovertGoods _irpCovert,Date _starttime,Date _endtime);
	List<IrpUserCovertGoods> findCovertOfPage(PageUtil pageUtil,
			String _oOrderby,IrpUserCovertGoods _irpCovert,Date _starttime,Date _endtime);
	List<IrpUserCovertGoods> showAllCovertByUserid(PageUtil pageUtil,String orderField,String orderBy,Long userid);
	/**
	 * 统计兑换记录
	 * @param goods
	 * @return
	 */
	public int countCovert(); 
	
	/**
	 * 统计当前用户兑换记录
	 * @param goods
	 * @return
	 */
	public int countCovertByUserid(Long userid); 
}
