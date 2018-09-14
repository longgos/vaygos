package com.ways.bo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ways.vo.Param;

/**
 * 登录信息
 * @author jiangsy
 * @version 2016-11-9
 */
public class UserLoginBo implements Serializable {
	
	private static final long serialVersionUID = -2451289258390618916L;
	
	@NotNull
    @Length(min = Param.LoginName.minlength, max = Param.LoginName.maxlength, message = Param.LoginName.message)
	private String loginName;

	@NotEmpty
    @Length(min = Param.LoginPassword.minlength, max = Param.LoginPassword.maxlength, message = Param.LoginPassword.message)
	private String loginPassword;
//	
//	@NotEmpty
//    @Length(min = Param.VerifyCode.length, max = Param.VerifyCode.length, message = Param.VerifyCode.message)
//	private String verifyCode;

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

//	public String getVerifyCode() {
//		return verifyCode;
//	}
//
//	public void setVerifyCode(String verifyCode) {
//		this.verifyCode = verifyCode;
//	}
	
}
