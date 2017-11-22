package com.tfs.irp.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUtil {
	//public static  String STARTTIME = SysConfigUtil.getSysConfigValue("STARTTIME");// 上班时间
	//public static  String ENDTIME = SysConfigUtil.getSysConfigValue("ENDTIME");// 下班时间
	public static final String NORMAL = "正常";
	public static final String LATE = "迟到";
	public static final String EARLY = "早退";
	
	/**
	 * 通用字符串非空判断
	 * 
	 * @param msg
	 * @return
	 */
	public static boolean common(String msg) {
		if (msg == null || "".equals(msg) || "".equals(msg.trim())) {
			return false;
		}
		return true;
	}
	
	/**
	 * 将Date类型转换成day字符串
	 * @param date
	 * @return
	 */
	public static String getStringDay(java.util.Date date){
		if(date==null){
			return "";
		}
		return new SimpleDateFormat("dd").format(date);
	}
	
	
	/**
	 * 将日期转换成字符串
	 * @param date
	 * @return
	 */
	public static String getStringDate(java.util.Date date)
	{
		if(date==null){
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 获得上班时间
	 * 
	 * @param time
	 * @return
	 */
	public static Date getUpTime() {
		return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd")
				.format(DateUtils.getNOWTime()) + " " + SysConfigUtil.getSysConfigValue("STARTTIME"));
	}

	/**
	 * 获得下班时间
	 * 
	 * @return
	 */
	public static Date getDownTime() {
		return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd")
				.format(DateUtils.getNOWTime()) + " " + SysConfigUtil.getSysConfigValue("ENDTIME"));
	}

	/**
	 * 获得IP地址
	 * 
	 * @return
	 */
	public static String getAddress() {
		return new LogUtil().getIpAddr();
	}

	/**
	 * 获取当天凌晨0点时间
	 * 
	 * @return
	 */
	public static Date getYesterday() {
		return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd")
				.format(DateUtils.getNOWTime()) + " " + "00:00:00");
	}

	/**
	 * 更换编码
	 * 
	 * @param msg
	 * @return
	 */
	public static String changeUTF(String msg) {
		try {
			return new String(msg.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
