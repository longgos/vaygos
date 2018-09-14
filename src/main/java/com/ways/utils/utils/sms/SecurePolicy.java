package com.ways.utils.utils.sms;

import org.apache.commons.lang3.StringUtils;

public class SecurePolicy {

	/**
	 * 生成通过验证码/授权码 + 手机号的方式验证的字符串
	 * @param code 验证码/授权码
	 * @param mobile 手机号
	 * @return 生成新的校验字符串
	 */
	public static String genVerifyGroup(String code, String mobile) {
		return code + mobile;
	}

	/**
	 * 通过验证码/授权码 + 手机号的方式验证
	 * 防止输入验证码后，再修改提交的手机号
	 * @param crypto 校验字符串
	 * @param code   验证码/授权码
	 * @param mobile 手机号
	 * @return
	 */
	public static boolean verifyGroup(String crypto, String code, String mobile) {
		if (StringUtils.isNotBlank(crypto)) {
			return crypto.equals(genVerifyGroup(code, mobile));
		}
		return false;
	}

}
