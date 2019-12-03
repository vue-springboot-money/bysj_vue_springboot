package com.wxt.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**
	 * 可以把一段文字转换为MD
	 * @param text
	 * @return md5
	 */
	public static String encode(String text) {
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			
			byte[] buffer = digest.digest(text.getBytes());
			
			StringBuffer sb = new StringBuffer();
			
			for (byte b : buffer) {
				
				int a = b & 0xff;
				
				String hex = Integer.toHexString(a);

				if (hex.length() == 1) {
					hex = 0 + hex;
				}
				
				sb.append(hex);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
