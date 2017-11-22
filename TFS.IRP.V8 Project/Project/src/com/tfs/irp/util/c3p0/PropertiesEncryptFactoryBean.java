package com.tfs.irp.util.c3p0;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.FactoryBean;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
  
/****
 * C3P0连接用户名密码解密类。
 * @author peng.pengfei
 *
 */
public class PropertiesEncryptFactoryBean implements FactoryBean {  
  
    private Properties properties;  
      
    public Object getObject() throws Exception {  
        return getProperties();  
    }  
  
    public Class getObjectType() {  
        return java.util.Properties.class;  
    }  
  
    public boolean isSingleton() {  
        return true;  
    }  
  
    public Properties getProperties() {  
        return properties;  
    }  
  
    /****
     * 放置property属性
     * @author peng.pengfei
     * @create 2017-10-20下午4:43:48
     * @Modified peng.pengfei
     * @update 2017-10-20下午4:43:48
     * @detail 
     * @param inProperties
     */
    public void setProperties(Properties inProperties) {  
        this.properties = inProperties;  
        String originalUsername = properties.getProperty("user");  
        String originalPassword = properties.getProperty("password");  
        if (originalUsername != null){  
            String newUsername = base64Decrypt(originalUsername);  
            properties.put("user", newUsername);  
        }  
        if (originalPassword != null){  
            String newPassword = base64Decrypt(originalPassword);  
            properties.put("password", newPassword);  
        }  
    }  
  
    
    /**
	 * base64加密
	 * 
	 * @param sValue
	 * @return
	 */
	private   String base64Encryption(String sValue) {
		String sBase64 = "";
		try {
			sBase64 = new BASE64Encoder().encode(sValue.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return sBase64;
	}

	/**
	 * base64解密
	 * 
	 * @param sValue
	 * @return
	 */
	private  String base64Decrypt(String sValue) {
		String sStr = "";
		try {
			byte[] bytes = new BASE64Decoder().decodeBuffer(sValue);
			sStr = new String(bytes, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return sStr;
	}
	
	public static void main(String[] args) {
		System.out.println(new PropertiesEncryptFactoryBean().base64Encryption("irpv8"));
		System.out.println(new PropertiesEncryptFactoryBean().base64Encryption("123456"));
	}
    
}  