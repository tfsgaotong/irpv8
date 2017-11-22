package com.tfs.irp.util.wordfilter.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

public class Test {
	/**
	* 去掉字符串中匹配 的字符串
	* @author zhujie
	* @return String regex 要替换的内容 value 字符串 state 替换的内容变成什么
	*/ 
	public static String toRegex(String regex, String value, String state) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
		m.appendReplacement(sb, state);
		}
		m.appendTail(sb);
		return sb.toString();
		} 
    	public static void main(String[] args) {
		  String value="中国，中华人民共和国，";
		  System.out.println(toRegex("中国",value,"<a>中国</a>"));
		} 
}
