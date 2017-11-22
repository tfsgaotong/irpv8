package com.tfs.irp.util.license;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

public class GetMacAddr {

	public static String getMachineCode() {
		String sCode = "";

		Set<String> arrMacs = getMacs();

		for (String sMac : arrMacs) {
			sCode += sMac.replaceAll("-", "") + " ";
		}
		return sCode.trim();
	}

	public static Set<String> getMacs() {
		Set<String> arrMacs = null;
		String sOs = System.getProperty("os.name", "");
		if (sOs == null || sOs.length() == 0) {
			sOs = System.getProperties().get("os.name").toString();
		}
		sOs = sOs.toUpperCase();
		// 判断系统
		if (sOs.indexOf("WINDOWS") >= 0) {
			arrMacs = getWindowMac();
		} else if (sOs.indexOf("LINUX") >= 0) {
			arrMacs = getLinuxMac();
		}
		return arrMacs;
	}

	private static Set<String> getWindowMac() {
		Set<String> macs = new HashSet<String>();
		try {
			Process process = Runtime.getRuntime().exec("ipconfig /all");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream(), "GBK"));
			String line;
			while ((line = br.readLine()) != null) {
				if ((line.indexOf("Physical Address") != -1)
						|| (line.indexOf("物理地址") != -1)) {
					macs.add(line.split(":")[1].trim());
				}
			}
			br.close();
			process.destroy();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return macs;
	}

	private static Set<String> getLinuxMac() {
		Set<String> macs = new HashSet<String>();
		try {
			Process process = Runtime.getRuntime().exec("/sbin/ifconfig -a");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream(), "GBK"));

			String line;
			while ((line = br.readLine()) != null) {
				line = line.toUpperCase();
				if (line.indexOf("ETHER") > 0) {
		          int index = line.indexOf("ETHER") + 7;
		          macs.add(line.substring(index).trim().replace(':', '-').split(" ")[0].trim());
		        }
			}
			br.close();
			process.destroy();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return macs;
	}
}