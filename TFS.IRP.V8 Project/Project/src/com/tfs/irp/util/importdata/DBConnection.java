package com.tfs.irp.util.importdata;

import java.sql.Connection;
import java.sql.DriverManager;

import com.tfs.irp.util.SysConfigUtil;

public class DBConnection {
	public static Connection getConnection(){
	/*	String sDriverName = "oracle.jdbc.driver.OracleDriver";
		String sUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String sUserName = "TFS_BASE";
		String sPassword = "TFS_BASE";*/
		String sDriverName = SysConfigUtil.getSysConfigValue("DMDRIVERNAME");
		String sUrl = SysConfigUtil.getSysConfigValue("DMURL");
		String sUserName = SysConfigUtil.getSysConfigValue("DMUSERNAME");
		String sPassword = SysConfigUtil.getSysConfigValue("DMPASSWORD");
		Connection conn = null;
		try {
			Class.forName(sDriverName);
			DriverManager.setLoginTimeout(3);
			conn = DriverManager.getConnection(sUrl, sUserName, sPassword);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return conn;
	}
}
