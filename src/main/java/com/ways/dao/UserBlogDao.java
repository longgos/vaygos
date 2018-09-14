/**
 * Copyright &copy; 2012-2016 <a href="https://www.hzrinfo.com">hzrinfo</a> All rights reserved.
 */
package com.ways.dao;
import java.util.List;

import com.ways.entity.UserBlogEntity;
import com.ways.param.UserBlogParam;
import com.ways.utils.utils.model.DataPageModel;
/**
 * 用户博客DAO接口
 * @author ljk
 * @version 2017-07-28
 */
public interface UserBlogDao {
	/**
	 * 获取单条数据.
	 * @param id ID
	 * @return id对应的实体
	 */
	UserBlogEntity getById(Long blogId);
	
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>()).
	 * @param param 查询参数
	 * @return 列表
	 */
	List<UserBlogEntity> findList(UserBlogParam param);
	
	
	/**
	 * 查询分页.
	 * @param param 查询参数
	 * @return 列表分页对象
	 */
	DataPageModel<UserBlogEntity> findPage(UserBlogParam param);
	
	/**
	 * 插入数据.
	 * @param entity 实体
	 * @return void
	 */
	int insert(UserBlogEntity vayBlog);
	
	/**
	 * 更新数据.
	 * @param entity 实体
	 * @return void
	 */
	int update(UserBlogEntity vayBlog);
	
	/**
	 * 删除数据.
	 * @param id ID
	 * @return void
	 */
	int deleteById(Long blogId);
}