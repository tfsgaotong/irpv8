package com.tfs.irp.navigation.service;

import java.util.List;

import com.tfs.irp.navigation.entity.IrpUserNavigation;
import com.tfs.irp.util.PageUtil;

public interface IrpUserNavigationService {
	/**
	 * 添加网址 
	 * 1:succ 2:fail
	 * @param _irpUserNavigation
	 * @return
	 */
	int addUserNavigation(String _navigationname,String _navigationvalue);
	/**
	 * 修改网址
	 * @param _irpUserNavigation
	 * @return
	 */
	int updateUserNavigation(Long _navigationid,String _navigationname,String _navigationvalue);
	/**
	 * 删除网址
	 * @param _navigationid
	 * @return
	 */
	int deleteUserNavigation(Long _navigationid);
	/**
	 * 根据条件查集合
	 * @param _status
	 * @param _orderby
	 * @param _pageUtil
	 * @return
	 */
	List<IrpUserNavigation> findUserNavigationOfAll(Integer _status,String _orderby,PageUtil _pageUtil);
	/**
	 * 根据条件查集合(数量)
	 * @param _status
	 * @return
	 */
	int findUserNavigationOfAllCount(Integer _status);
	/**
	 * 根据id查找指定的对象
	 * @param _usernavigationid
	 * @return
	 */
	IrpUserNavigation getIrpUserNavigationById(Long _usernavigationid);
}
