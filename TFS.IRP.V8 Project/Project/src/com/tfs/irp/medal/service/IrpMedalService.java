package com.tfs.irp.medal.service;

import java.util.List;


import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.entity.IrpGoodsExample;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.util.PageUtil;


public interface IrpMedalService {
	/**
	 * 添加勋章记录
	 * @param medal
	 * @return
	 */
	public int addMedal(IrpMedal medal); 
	/**
	 *删除勋章记录
	 * @param medalid
	 * @return
	 */
	public int deleteMedal(Long medalid);
	/**
	 *删除多条勋章记录
	 * @param medalid
	 * @return
	 */
	public int deleteMoreMedal(Long medalids);
	/**
	 * 根据ID查询勋章记录信息
	 * @param medalid
	 * @return
	 */
	public IrpMedal findMedalByMedalid(Long medalid);
	/**
	 * 修改勋章记录
	 * @param medal
	 * @return
	 */
	public int updateMedalByMedalid(IrpMedal medal);
	
	 
	/**
	 * 列表显示所有勋章记录带分页
	 * @param medalid
	 * @return
	 */
	List<IrpMedal> showAllMedal(PageUtil pageUtil,String _orderby,String searchType,String searchWord,String orderField);
	/**
	 * 分类列表显示所有勋章记录带分页
	 * @param medalid
	 * @return
	 */
	List<IrpMedal> showAllMedalByCategory(PageUtil pageUtil,String _orderby,String searchType,String searchWord,String orderField,String categoryId);
	/**
	 * 
	 * showAllMedalByCategory
	 * 分类列表显示所有勋章记录数量
	 * @author Create User At: CY
	 * @date Create Date At: 2017-11-6 下午2:12:38
	 */
	int countshowAllMedalByCategory(String searchType,String searchWord,String categoryId);
	List<IrpMedal> listMedal();
	
	/**
	 * 统计勋章记录
	 * @param medal
	 * @return
	 */
	public int countMedal(String searchType,String searchWord);
	/**查询多个勋章
	 * @param list
	 * @return
	 */
	public List<IrpMedal> findMedalByMedalidList(List<Long> list); 
}
