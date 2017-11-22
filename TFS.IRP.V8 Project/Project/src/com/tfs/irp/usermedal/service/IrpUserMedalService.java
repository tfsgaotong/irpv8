package com.tfs.irp.usermedal.service;

import java.util.Date;
import java.util.List;


import com.tfs.irp.usergoodslink.entity.IrpUserCovertGoods;
import com.tfs.irp.usermedal.entity.IrpUserMedal;
import com.tfs.irp.util.PageUtil;

public interface IrpUserMedalService {
	/**
	 * 为用户添加勋章
	 * @param usermedal
	 * @return
	 */
	public int addUserMedal(IrpUserMedal userMedal); 
	/**
	 *删除用户勋章
	 * @param usermedalid
	 * @return
	 */
	public int deleteUserMedal(Long usermedalid);
	/**
	 * 根据ID查询商用户勋章
	 * @param usermedalid
	 * @return
	 */
	public IrpUserMedal findUserMedalById(Long usermedalid);
	/**
	 * 修改用户勋章
	 * @param userMedal
	 * @return
	 */
	public int updateUserMedalById(IrpUserMedal userMedal);
	
	 
	/**
	 * 列表显示所有用户勋章
	 * @param userMedal
	 * @return
	 */
	List<IrpUserMedal> showAllUserMedal(PageUtil pageUtil,String orderField,String orderBy);
	List<IrpUserMedal> getUserMedalByUserid(PageUtil pageUtil,Long userid);
	List<IrpUserMedal> listUserMedal(Long userid,Long medalid,Long medalstate);
	
	/**
	 * 统计兑换记录
	 * @param goods
	 * @return
	 */
	public int countUserMedal(); 
	public int countUserMedal(Long userid,Long goodsid,Long medalstate); 
	
	public int selectMedalid(Long goodsid);
	
	public int countUserMedal(Long userid);
	
	List<IrpUserMedal> findUserMedalOfPageSize(PageUtil pageUtil,
			String _oOrderby,IrpUserMedal _userMedal,Date _starttime,Date _endtime);
	List<IrpUserMedal> findUserMedalOfPage(PageUtil pageUtil,
			String _oOrderby,IrpUserMedal _userMedal,Date _starttime,Date _endtime);
}
