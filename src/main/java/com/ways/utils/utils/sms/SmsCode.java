package com.ways.utils.utils.sms;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class SmsCode implements Serializable {

	private static final long serialVersionUID = 1L;
	private String smsCode;//验证码
	private Date expiredTime;//过期时间
	private Integer validTimes;//校验次数

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	
	public Integer getValidTimes() {
		return validTimes;
	}

	public void setValidTimes(Integer validTimes) {
		this.validTimes = validTimes;
	}

	public boolean isInvalid(){
		if (System.currentTimeMillis() > this.getExpiredTime().getTime()) {
			return true;
		} else {
			return false;
		}
	}

	public SmsCode() {
	}
	/**
	 * 固定短信提醒，不建议使用
	 * add by qiuguanghui 2016-07-22
	 * @param smsCode
	 */
	public SmsCode(String smsCode) {
		this.smsCode = smsCode;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, 30);
		expiredTime = new Date(calendar.getTimeInMillis());
	}
	
	/**
	 * 动态配置失效时间【建议使用】
	 * add by qiuguanghui 216-07-22
	 * @param smsCode
	 * @param minutes
	 */
	public SmsCode(String smsCode, int minutes) {
		this.smsCode = smsCode;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, minutes);
		expiredTime = new Date(calendar.getTimeInMillis());
	}

}
