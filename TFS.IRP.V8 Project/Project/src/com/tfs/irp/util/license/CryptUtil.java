package com.tfs.irp.util.license;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.core.io.ClassPathResource;

import com.tfs.irp.util.FileUtil;

/**
 * This program generates a AES key, retrieves its raw bytes, and then
 * reinstantiates a AES key from the key bytes. The reinstantiated key is used
 * to initialize a AES cipher for encryption and decryption.
 */

public class CryptUtil {

	private static final String AES = "AES";

	private static final String CRYPT_KEY = "TfsAdmin(*&^%$#)";

	private static final String LICENSE_PATH = "license/license.lic";
	
	private static Set<String> arrLocalMacs = null;
	
	public static void setArrLocalMacs(Set<String> arrLocalMacs) {
		CryptUtil.arrLocalMacs = arrLocalMacs;
	}

	/**
	 * 解密
	 * 
	 * @param decryptStr
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String key) throws Exception {
		Cipher cipher = Cipher.getInstance(AES);
		SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);// 设置加密Key
		cipher.init(Cipher.DECRYPT_MODE, securekey);// 设置密钥和解密形式
		return cipher.doFinal(src);
	}

	/**
	 * 二行制转十六进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()), CRYPT_KEY));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 获得本地注册码
	 * 
	 * @return
	 */
	public static String findLocalLicense() {
		String sLicense = "";
		ClassPathResource res = new ClassPathResource(LICENSE_PATH);
		InputStream in = null;
		try {
			in = res.getInputStream();
			sLicense = FileUtil.file2String(in, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sLicense;
	}

	public static boolean checkLicense(String _sLicense) {
		/*if(1==1)
		return true;*/
		// 获得本地注册码
		if (_sLicense == null || _sLicense.length() <= 0){
			return false;
		}
		_sLicense = decrypt(_sLicense);
		if (_sLicense == null || _sLicense.length() <= 0){
			return false;
		}
		// 获得机器码信息
		String[] sLicenseInfo = _sLicense.split("-");
		if (sLicenseInfo == null || sLicenseInfo.length != 2) {
			return false;
		}
		// 验证时间是否过期
		try {
			if (Long.parseLong(sLicenseInfo[1]) <= System.currentTimeMillis()) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// 验证Mac是否匹配
		if(arrLocalMacs==null){
			return false;
		}
		for (String sMac : arrLocalMacs) {
			sMac = sMac.replaceAll("-", "");
			if (sLicenseInfo[0].indexOf(sMac) != -1) {
				return true;
			}
		}
		return false;
	}
	 
}
