package com.tfs.irp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThumbnailPic {

	
	
	/**
	 * 缩略图
	 * 把一个带有img标签的字符串的src属性 的图片后缀加上大小
	 * @param _resname   传入的字符串
	 * @param _widthandheight  大小    例如:  _150X150 _300X300
	 * @return
	 */
	public static String searchThumnailPicSrc(String _resname,String _widthandheight){
		String findimgs =  "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
		Pattern pattern = Pattern.compile(findimgs);
		Matcher matcher = pattern.matcher(_resname);
		while (matcher.find()) {
			String images = matcher.group(1);
		     int disposea =	images.indexOf("=");
			 int disposeb = images.lastIndexOf(".");
			 String disposepicstr =  images.substring(disposea+1,disposeb);
			 String  imagespic = images.replace(disposepicstr,disposepicstr+_widthandheight);
			 _resname =_resname.replace(images,imagespic);
		}
		return _resname; 
	}	
	public static String searchFileName(String _name,String _widthandheight){
		 int disPost= _name.lastIndexOf(".");
		 String name=_name.substring(0,disPost);
		 String _lastName=_name.substring(disPost);
		 String trueName=name+_widthandheight+_lastName;
		 return trueName;
	}
	/**
	 * 把带缩略图的字符串  还原成原来的
	 * @param _resstr  需要传入的字符串
	 * @return
	 */
	public static String disposeThumnail(String _resstr){
		int disstr1 = _resstr.indexOf("_");
		int disstr2 = _resstr.lastIndexOf(".");
		String resstr3 = _resstr.substring(disstr1, disstr2);
		_resstr = _resstr.replace(resstr3,"");
		return _resstr;
	}
	
	
	
	
	
	
}