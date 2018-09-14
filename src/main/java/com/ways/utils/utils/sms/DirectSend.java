package com.ways.utils.utils.sms;

import java.io.Serializable;

public class DirectSend implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String userId;// 用户ID
	private String account;// 子账号名
	private String passwrod;// 该账号密码
	private String phones;// 以半角分号分隔的电话号码序列（最后必须以一个分号结束)
	private String content;// 短信内容 [要用UTF8编码]
	private String sendTime;// 定时时间，格式为 yyyy-mm-dd hh:mm:ss（填空则立即发送）
	private int sendType;// 发送类别（填1）
	private String postFixNumber;// 任务扩展号(1到9,或者为空)

	public DirectSend(){
		
	}
	
	/**
	 * 立即发送配置
	 * add by qiuguanghui 2016-07-22
	 * @param phones
	 * @param content
	 */
	public DirectSend(String phones, String content){
//		this.userId = PublicConfig.SMS_USERID;
//		this.account = PublicConfig.SMS_ACCOUNT;
//		this.passwrod = PublicConfig.SMS_PASSWORD;
//		this.sendType = PublicConfig.SMS_SENDTYPE;
		this.phones = phones;
		this.content = content;
		this.sendTime = null;
		this.postFixNumber = null;
	}
	
	/**
	 * 可定时配置发送时间
	 * add by qiuguanghui 2016-07-22
	 * @param phones
	 * @param content
	 * @param sendTime
	 * @param postFixNumber
	 */
	public DirectSend(String phones, String content, String sendTime, String postFixNumber){
//		this.userId = PublicConfig.SMS_USERID;
//		this.account = PublicConfig.SMS_ACCOUNT;
//		this.passwrod = PublicConfig.SMS_PASSWORD;
//		this.sendType = PublicConfig.SMS_SENDTYPE;
		this.phones = phones;
		this.content = content;
		this.sendTime = sendTime;
		this.postFixNumber = postFixNumber;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public int getSendType() {
		return sendType;
	}

	public void setSendType(int sendType) {
		this.sendType = sendType;
	}

	public String getPostFixNumber() {
		return postFixNumber;
	}

	public void setPostFixNumber(String postFixNumber) {
		this.postFixNumber = postFixNumber;
	}

}
