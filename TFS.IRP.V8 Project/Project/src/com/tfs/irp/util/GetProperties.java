package com.tfs.irp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class GetProperties {

	Properties config = new Properties();

	public GetProperties(String sConfigFile) {

		if (sConfigFile == null || "".equals(sConfigFile.trim()))
			sConfigFile = "infoview.properties";

		InputStream in = GetProperties.class.getClassLoader()
				.getResourceAsStream(sConfigFile);
		try {
			config.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readValue(String key, String sDefault) {
		try {
			String value = config.getProperty(key);
			if (value == null || "".equals(value.trim()))
				return sDefault.trim();
			return value.trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sDefault;
	}

	// ��ȡproperties��ȫ����Ϣ
	public void readAllProperties() {
		try {
			Enumeration en = config.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = config.getProperty(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
