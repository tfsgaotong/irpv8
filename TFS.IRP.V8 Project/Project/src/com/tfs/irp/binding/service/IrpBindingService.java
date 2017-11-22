package com.tfs.irp.binding.service;

import java.util.HashMap;

import java.util.List; 

import com.tfs.irp.binding.entity.IrpBinding;
import com.tfs.irp.util.PageUtil;

public interface IrpBindingService {
	
	/**
	 * 添加绑定字段组
	 * @param binding
	 * @return
	 */
	public int addBinding(IrpBinding binding); 
	/**
	 *删除绑定字段
	 * @param bindingid
	 * @return
	 */
	public int deleteBinding(Long bindingid);
	/**
	 *删除多条绑定字段
	 * @param bindingid
	 * @return
	 */
	public int deleteBindings(Long bindingids);
	/**
	 * 各家绑定表ID查询绑定字段组信息
	 * @param bindingid
	 * @return
	 */
	public IrpBinding findBindingByBindingid(Long bindingid);
	/**
	 * 修改绑定字段
	 * @param binding
	 * @return
	 */
	public int updateBindingByBindingid(IrpBinding binding);
	
	 
	 
	/**
	 * 列表显示某一栏目下的绑定字段组
	 * @param bindingid
	 * @return
	 */
	List<IrpBinding> listBindings(Long bindingid);
	/**
	 * 列表显示某一栏目下的字段组带分页
	 * @param bindingid
	 * @return
	 */
	List<IrpBinding> listBinding(PageUtil pageUtil,Long bindingid);
	/**
	 * 列表显示所有绑定字段组带分页
	 * @param bindingid
	 * @return
	 */
	List<IrpBinding> showAllBinding(PageUtil pageUtil);
	
	/**
	 * 统计绑定字段组
	 * @param binding
	 * @return
	 */
	public int countBinding(Long channelid); 
	/**
	 * 统计绑定字段组
	 * @param binding
	 * @return
	 */
	public int countBinding(); 
	
	
} 
