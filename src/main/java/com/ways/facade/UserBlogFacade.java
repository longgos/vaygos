/**
 * Copyright &copy; 2012-2016 <a href="https://www.hzrinfo.com">hzrinfo</a> All rights reserved.
*/
package com.ways.facade;
import java.util.List;

import com.ways.entity.UserBlogEntity;
import com.ways.param.UserBlogParam;
import com.ways.utils.utils.model.DataPageModel;
/**
 * 用户博客Service
 * @author ljk
 * @version 2017-07-28
 */
public interface UserBlogFacade {
	/**
	 * 获取单条数据.
	 * @param id ID
	 * @return 实体
	 */
	UserBlogEntity getById(Long blogId);
	
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>()).
	 * @param param 参数对象
	 * @return 列表
	 */
	List<UserBlogEntity> findList(UserBlogParam param);
	
	
	/**
	 * 查询分页.
	 * @param param 参数对象
	 * @return 分页对象
	 */
	DataPageModel<UserBlogEntity> findPage(UserBlogParam param);
	
	/**
	 * 更新数据.
	 * @param entity 实体
	 * @return void
	 */
	int save(UserBlogEntity entity);
	
	/**
	 * 删除数据.
	 * @param id ID
	 * @return void
	 */
	int deleteById(Long blogId);
}