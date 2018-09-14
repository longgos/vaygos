package com.ways.bo;

import java.io.Serializable;

/**
 * 用户注册提交数据
 * @author jiangsy
 * @version 2016-11-8
 */
public class UserRegistBo implements Serializable {
	
	private static final long serialVersionUID = -2451289258390618916L;
	
//	@NotEmpty
//    @Length(min = Param.Mobile.length, max = Param.Mobile.length, message = Param.Mobile.message)
	private String loginName;

	private String companyName;
	
	private String ratepayingNo;

//	@NotEmpty
//    @Length(min = Param.LoginPassword.minlength, max = Param.LoginPassword.maxlength, message = Param.LoginPassword.message)
	private String loginPassword;
	
	private String contacts;

	private String contactsMobile;

//	@NotEmpty
//    @Length(min = Param.SmsCode.length, max = Param.SmsCode.length, message = Param.SmsCode.message)
	private String smsCode;
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsMobile() {
		return contactsMobile;
	}

	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRatepayingNo() {
		return ratepayingNo;
	}

	public void setRatepayingNo(String ratepayingNo) {
		this.ratepayingNo = ratepayingNo;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
}
