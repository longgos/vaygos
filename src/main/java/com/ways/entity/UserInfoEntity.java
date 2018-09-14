/**
 * Copyright &copy; 2012-2016 <a href="https://www.hzrinfo.com">hzrinfo</a> All rights reserved.
 */
package com.ways.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

/**
 * 用户信息Entity
 * @author ljk
 * @version 2017-07-28
 */
public class UserInfoEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/** 用户id. */
	private Long userId;
	/** 等级id. */
	private int rankId;
	/** 昵称. */
	private String nickName;
	/** 用户头像. */
	private String userImg;
	/** 姓名. */
	private String userName;
	/** 性别 */
	private String sex;
	/** 年龄. */
	private Long age;
	/** 身份证号码. */
	private String idCard;
	/** 电话. */
	private Long phone;
	/** 邮箱 */
	private String email;
	/** 出生日期. */
	private Date birthDay;
	/** 住址. */
	private String address;
	/** 登录名. */
	private String loginName;
	/** 登录密码. */
	private String loginPassword;
	/** 新建人. */
	private String createBy;
	/** 新建时间. */
	private Date createDate;
	/** 修改人. */
	private String updateBy;
	/** 修改时间. */
	private Date updateDate;
	
	private Set<Role> roleSet = new HashSet<Role>();

	
	public UserInfoEntity() {
	}

	public UserInfoEntity(Long userId){
			this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public int getRankId() {
		return rankId;
	}

	public void setRankId(int rankId) {
		this.rankId = rankId;
	}
	
	@Length(min=0, max=32, message="昵称长度必须介于 0 和 32 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Length(min=0, max=1024, message="用户头像长度必须介于 0 和 1024 之间")
	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	
	@Length(min=0, max=20, message="userName长度必须介于 0 和 20 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}
	
	@Length(min=0, max=18, message="身份证号码长度必须介于 0 和 18 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	@Length(min=0, max=100, message="address长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=20, message="login_name长度必须介于 0 和 20 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=0, max=36, message="login_password长度必须介于 0 和 36 之间")
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}