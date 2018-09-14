/**
 * Copyright &copy; 2012-2016 <a href="https://www.hzrinfo.com">hzrinfo</a> All rights reserved.
 */
package com.ways.facade.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ways.dao.UserBlogDao;
import com.ways.entity.UserBlogEntity;
import com.ways.facade.UserBlogFacade;
import com.ways.param.UserBlogParam;
import com.ways.utils.utils.model.DataPageModel;
/**
 * 用户博客Service
 * @author ljk
 * @version 2017-07-28
 */
@Service
@Transactional(readOnly = true)
public class UserBlogFacadeImpl implements UserBlogFacade {
	
	@Autowired
	private UserBlogDao userBlogDao;
	
	public UserBlogEntity getById(Long blogId) {
		return userBlogDao.getById(blogId);
	}
	
	public List<UserBlogEntity> findList(UserBlogParam vayBlogParam) {
		return userBlogDao.findList(vayBlogParam);
	}
	
	public DataPageModel<UserBlogEntity> findPage(UserBlogParam vayBlogParam) {
		return userBlogDao.findPage(vayBlogParam);
	}
	
	@Transactional(readOnly = false)
	public int save(UserBlogEntity vayBlog) {
		if (vayBlog.getBlogId() == null) {
			 return userBlogDao.insert(vayBlog);
		} else {
			return userBlogDao.update(vayBlog);
		}
	}
	
	@Transactional(readOnly = false)
	public int deleteById(Long blogId) {
		return userBlogDao.deleteById(blogId);
	}

}