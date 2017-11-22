package com.tfs.irp.leaveconf.service;

import java.util.List;

import com.tfs.irp.leaveconf.entity.IrpLeaveconfig;
import com.tfs.irp.util.PageUtil;

public interface IrpLeaveconfigService {

	List<IrpLeaveconfig> getAllList();
	/**
	 * 得到所有加班类型配置
	 * @return
	 */
	List<IrpLeaveconfig> getAllOverTimeType();
	/**
	 * 通过标识分页查询list
	 * @param page
	 * @param marking
	 * @returnList List<IrpLeaveconfig>
	 */
	List<IrpLeaveconfig> getAllByMarking(String orderBy,String orderField,String searchWord,String searchType,PageUtil page,Integer marking);
	/**
	 * 通过请假或者加班的标识查询数量
	 * @param marking
	 * @return
	 */
	Integer irpLeaveConfigCountByMarking(Integer marking);
	/**
	 * 增加配置
	 * @param irpLeaveconfig
	 * @return
	 */
	int addLeaveConfig(IrpLeaveconfig irpLeaveconfig);
	/**
	 * 删除配置
	 * @param leaveconfigid
	 * @return
	 */
	int delLeaveConfig(Long leaveconfigid);
	/**
	 * 通过id查询实体
	 * @param leaveconfigid
	 * @return
	 */
	IrpLeaveconfig selLeaveConfigById(Long leaveconfigid);
	/**
	 * 修改配置信息
	 * @param irpLeaveconfig
	 * @param leaveconfigid
	 * @return
	 */
	int upLeaveConfigInfo(IrpLeaveconfig irpLeaveconfig,Long leaveconfigid);
}
