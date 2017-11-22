package com.tfs.irp.util.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


public class SmsUtil {
	public static  void sendMsg_webchinese(String content,String number){
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
		post.addRequestHeader("Content-Type",
		"application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "佩恩人生"), // 注册的用户名
		new NameValuePair("Key", "688d6b9a58beff8535b3"), // 注册成功后,登录网站使用的密钥
		new NameValuePair("smsMob", number), // 手机号码
		new NameValuePair("smsText", content) };
		post.setRequestBody(data);
		  
		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		//System.out.println("statusCode:" + statusCode);
		/*for (Header h : headers) {
		System.out.println(h.toString());
		}*/
		try {
			String result = new String(post.getResponseBodyAsString().getBytes(
			"gbk"));
		//	System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		post.releaseConnection();
		
		}
}
