/**
 * Copyright &copy; 2012-2016 <a href="https://www.hzrinfo.com">hzrinfo</a> All rights reserved.
 */
package com.ways.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.tool.base.BaseMyBatisDao;
import com.ways.dao.UserBlogDao;
import com.ways.entity.UserBlogEntity;
import com.ways.param.UserBlogParam;
import com.ways.utils.utils.model.DataPageModel;

/**
 * 用户博客DAO接口
 * @author ljk
 * @version 2017-07-28
 */
@Repository
public class UserBlogDaoImpl extends BaseMyBatisDao implements UserBlogDao {
	@Override
	public UserBlogEntity getById(Long blogId) {
		Assert.notNull(blogId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("blogId", blogId);
		return super.selectOne("get", paramMap);
	}

	public DataPageModel<UserBlogEntity> findPage(UserBlogParam param) {
		return super.selectPage("findList", param);
	}

	@Override
	public List<UserBlogEntity> findList(UserBlogParam param) {
		return super.selectList("findList", param);
	}
	
	@Override
	public int insert(UserBlogEntity vayBlog) {
		return super.insert("insert", vayBlog);
	}

	@Override
	public int update(UserBlogEntity vayBlog) {
		Assert.notNull(vayBlog.getBlogId());
		return super.update("update", vayBlog);
	}

	@Override
	public int deleteById(Long blogId) {
		Assert.notNull(blogId);
		return super.delete("delete", blogId);
	}
}