package com.tfs.irp.userapp.service;

import java.util.Map;

import com.tfs.irp.userapp.entity.IrpUserapp;
import com.tfs.irp.userapp.entity.IrpUserappExample;

/**
 * 用户应用
 * @author Administrator
 *
 */
public interface IrpUserappService {

	/**
	 * 根据用户  修改 用户的某个应用的安装或卸载
	 * 安装或卸载应用
	 * @param isdisplay
	 */
	public void updateAppdisplay(Map<String,Long> map);
	/**
	 * 查询用户的应用是否安装
	 * @param map
	 * @return
	 */
	public int findApphas(Map<String,Long> map);
	
	public void addAppuse(IrpUserapp irpUserapp);
}
