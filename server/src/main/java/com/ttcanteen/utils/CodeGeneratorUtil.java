package com.ttcanteen.utils;

import java.util.Random;

public class CodeGeneratorUtil {

	/**
	 * 生成一个不重复的6位取餐码
	 * @return
	 */
	public static String generator() {
		String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
		
		return verifyCode;
	}
}
