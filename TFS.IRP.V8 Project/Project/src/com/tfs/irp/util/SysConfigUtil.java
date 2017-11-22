package com.tfs.irp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.tfs.irp.config.dao.IrpConfigDAO;
import com.tfs.irp.config.entity.IrpConfig;
import com.tfs.irp.config.entity.IrpConfigExample;
import com.tfs.irp.util.license.CryptUtil;
import com.tfs.irp.util.license.GetMacAddr;

public class SysConfigUtil implements ApplicationListener<ApplicationEvent> {
	private static Map<String, String> m_SysConfig;
	
	/**
	 * 初始化系统配置项
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("==========加载系统配置项==========");
		ApplicationContext ac = (ApplicationContext) event.getSource();
		//初始化加载MAC地址
		CryptUtil.setArrLocalMacs(GetMacAddr.getMacs());
		//初始化系统配置项
		initSysConfig(ac);
	}
	
	/**
	 * 编辑配置项
	 * @param _sKey
	 * @param _sValue
	 */
	public static void editSysConfigOfCache(String _sKey, String _sValue) {
		if(m_SysConfig==null){
			m_SysConfig = new HashMap<String, String>();
		}
		m_SysConfig.put(_sKey, _sValue);
	}
	
	/**
	 * 删除配置项
	 * @param _sKey
	 */
	public static void deleteSysConfigOfCache(String _sKey) {
		m_SysConfig.remove(_sKey);
	}
	
	private void initSysConfig(ApplicationContext ac){
		if(m_SysConfig==null){
			m_SysConfig = new HashMap<String, String>();
		}else{
			m_SysConfig.clear();
		}
		IrpConfigDAO irpConfigDAO = (IrpConfigDAO) ac.getBean("irpConfigDAO");
		try {
			List<IrpConfig> list = irpConfigDAO.selectByExample(new IrpConfigExample());
			for (IrpConfig irpConfig : list) {
				m_SysConfig.put(irpConfig.getCkey(), irpConfig.getCvalue());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询数据库获得系统配置项
	 * @param _sConfigKey
	 * @return
	 */
	private static String findSysConfigValue(String _sConfigKey, ApplicationContext _ac){
		IrpConfigDAO irpConfigDAO = (IrpConfigDAO) _ac.getBean("irpConfigDAO");
		String sCValue = null;
		try {
			sCValue = irpConfigDAO.selectCValueByCKey(_sConfigKey);
			if(sCValue!=null){
				editSysConfigOfCache(_sConfigKey, sCValue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sCValue;
	}
	
	/**
	 * 获得字符串value的系统配置项
	 * @param _sConfigKey
	 * @return
	 */
	public static String getSysConfigValue(String _sConfigKey) {
		String sCValue = m_SysConfig.get(_sConfigKey);
		if(sCValue==null){
			ApplicationContext ac = ApplicationContextHelper.getContext();
			sCValue = findSysConfigValue(_sConfigKey, ac);
		}
		return sCValue;
	}
	
	/**
	 * 获得字符串value的系统配置项
	 * @param _sConfigKey
	 * @param _ac
	 * @return
	 */
	public static String getSysConfigValue(String _sConfigKey,ApplicationContext _ac) {
		String sCValue = m_SysConfig.get(_sConfigKey);
		if(sCValue==null){
			sCValue = findSysConfigValue(_sConfigKey, _ac);
		}
		return sCValue;
	}
	
	/**
	 * 获得数字类型的系统配置项
	 * @param _sConfigKey
	 * @return
	 */
	public static Integer getSysConfigNumValue(String _sConfigKey) {
		Integer nValue = null;
		try {
			nValue = Integer.valueOf(getSysConfigValue(_sConfigKey));
		} catch (NumberFormatException e) {
			nValue = null;
		}
		return nValue;
	}
  
	
	
}
