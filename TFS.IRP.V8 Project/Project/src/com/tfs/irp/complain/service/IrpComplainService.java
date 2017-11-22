package com.tfs.irp.complain.service;

import java.util.List;

import com.tfs.irp.complain.entity.IrpComplain;
import com.tfs.irp.util.PageUtil;

public interface IrpComplainService {

	//添加意见
	/**
	 * 
	 * @param irpc  IrpComplain
	 * @return
	 */
	public int saveComplain(IrpComplain irpc);
	//可根据回复/未回复 状态 查询所有意见
	/**
	 * 
	 * @param pageUtil  分页
	 * @param orderby   排序
	 * @param searchWord 搜索词
	 * @param searchType 搜索返回
	 * @param sids        状态集
	 * @return
	 */
	public List<?> findAllComplainBystatus(PageUtil pageUtil,String orderby,String searchWord,List<Short> sids);
	
	/**
	 * 查询结果条数
	 * @param orderby   排序
	 * @param searchWord 搜索词
	 * @param searchType 搜索返回
	 * @param sids        状态集
	 * @return
	 */
	public int findAllComplainBystatuscount(String searchWord,List<Short> sids);
}
