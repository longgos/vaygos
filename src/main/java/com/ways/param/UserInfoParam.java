/**
 * Copyright &copy; 2012-2016 <a href="https://www.hzrinfo.com">hzrinfo</a> All rights reserved.
 */
package com.ways.param;
import com.tool.base.Pager;
import com.ways.entity.UserInfoEntity;
/**
 * 用户信息param
 * @author ljk
 * @version 2017-07-28
 */
public class UserInfoParam extends UserInfoEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Pager pager = new Pager();


	
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	public Pager getPager() {
		return this.pager;
	}
}