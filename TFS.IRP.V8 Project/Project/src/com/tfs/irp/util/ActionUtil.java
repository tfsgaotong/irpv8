package com.tfs.irp.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ActionUtil extends ActionSupport {

	/**
	 * 打印页面输出信息
	 * 
	 * @param _sWriteInfo
	 */
	public static void writer(String _sWriteInfo) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(_sWriteInfo);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印JS页面输出信息
	 * 
	 * @param _sWriteInfo
	 */
	public static void writerJS(String _sWriteInfo) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			response.getWriter().write(_sWriteInfo);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String list2json(List<Map<String, Object>> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list == null || list.size() <= 0) {
			json.append("]");
		} else {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				json.append(map2json(map));
                json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		}
		return json.toString();
	}

	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(key.toString());
				json.append(":");
				json.append(map.get(key));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}
	
	public static String decode(String _str) {
		String sDecode="";
		try {
			sDecode = java.net.URLDecoder.decode(_str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sDecode;
	}
}
