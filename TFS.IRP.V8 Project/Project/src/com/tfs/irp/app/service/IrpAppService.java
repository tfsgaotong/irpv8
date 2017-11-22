package com.tfs.irp.app.service;

import java.util.List;
import java.util.Map;

import com.tfs.irp.app.entity.IrpApp;
import com.tfs.irp.util.PageUtil;

/***
 * 应用
 * 
 * @author Administrator
 * 
 */
public interface IrpAppService {

	/**
	 * 根据发布状态 分页显示所有应用
	 * 
	 * @param pageUtil
	 * @param searchWord
	 * @param searchType
	 * @return
	 */
	public List<IrpApp> findAllIrpapp(PageUtil pageUtil, String searchWord,
			String searchType, String _oOrderby, List<Integer> status);

	/**
	 * 查询所有应用条数
	 * 
	 * @param searchWord
	 * @param searchType
	 * @return
	 */
	public int findAllIrpappCount(String searchWord, String searchType,
			List<Integer> status);

	/**
	 * 添加
	 * 
	 * @param ctype
	 */
	public void addNewApp(IrpApp ctype);

	/**
	 * 修改状态
	 * 
	 * @param ctype
	 * @return
	 */
	public int updateAppStatus(IrpApp ctype);

	/**
	 * 修改
	 * 
	 * @param ctype
	 * @return
	 */
	public int updateApp(IrpApp ctype);

	/**
	 * 根据id找应用信息
	 * 
	 * @param aid
	 * @return
	 */
	public IrpApp findAppByid(Long aid);

	/**
	 * 根据应用类型查询应用
	 * 
	 * @param status
	 * @param type
	 * @return
	 */

	public List<IrpApp> findAllIrpappbytype(List<Integer> status, String type);

	// 查询用户开启的应用中系统发布的应用
	/**
	 * 根据用户id查询用户启用的系统发布的应用
	 * 
	 * @param userid
	 * @return
	 */
	public List<IrpApp> findUserappbystuts(Long userid);

	/**
	 * 根据系统应用是否安装和用户是否启用查询
	 * 
	 * @param status
	 * @param dispaly
	 * @return
	 */
	public List<IrpApp> findappuserdisplay(PageUtil pageutil,Map<String, Long> map);

	// 系统 用户 提示
	// 发布0 没安装 1 安装
	// 发布0 安装0 卸载
	// 没发布 1 没安装1 无
	// 没发布 1 安装0 卸载
	public List<IrpApp> findappusernotdisplay(PageUtil pageutil,Map<String, Long> map);

	// 查询数量
	public int findappuserdisplaycount(Map<String, Long> map);

	// 查询数量
	public int findappusernotdisplaycount(Map<String, Long> map);
	
}
