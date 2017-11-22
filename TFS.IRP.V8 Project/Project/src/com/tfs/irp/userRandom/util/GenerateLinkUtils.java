package com.tfs.irp.userRandom.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.SysConfigUtil;


/**
 * 生成帐户激活、重新设置密码的链接
 */
public class GenerateLinkUtils {
	
	private static final String CHECK_CODE = "checkCode";
	private static  String address = SysConfigUtil.getSysConfigValue("domain_name_address");
	
	/**
	 * 生成重设密码的链接
	 */
	public static String generateResetPwdLink(IrpUser user,String randoms) {		
		return address+"/random/findlink.action?userName=" 
				+ Zusername(user.getUsername()) + "&" + CHECK_CODE + "=" + generateCheckcode(user,randoms);
	}
	
	public static String Zusername(String username){
		String name="";
		try {
			name = java.net.URLEncoder.encode(username, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return name;	
	}
	
	/**
	 * 生成验证帐户的MD5校验码
	 * @param user  要激活的帐户
	 * @param rand 
	 * @return 将用户名和密码组合后，通过md5加密后的16进制格式的字符串
	 */
	public static String generateCheckcode(IrpUser user,String randoms) {
		String userName = user.getUsername();
		return md5(userName+randoms);
	}
	
	private static String md5(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
			md.update(string.getBytes());
			byte[] md5Bytes = md.digest();
			return bytes2Hex(md5Bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	private static String bytes2Hex(byte[] byteArray)
	{
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++)
		{
			if(byteArray[i] >= 0 && byteArray[i] < 16)
			{
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}
}