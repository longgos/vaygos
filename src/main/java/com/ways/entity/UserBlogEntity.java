/**
 * Copyright &copy; 2012-2016 <a href="https://www.hzrinfo.com">hzrinfo</a> All rights reserved.
 */
package com.ways.entity;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

/**
 * 用户博客Entity
 * @author ljk
 * @version 2017-07-28
 */
public class UserBlogEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/** 博客id. */
	private Long blogId;
	/** 用户id. */
	private Long userId;
	/** 分类id. */
	private String blogTermId;
	/**文件地址*/
	private String filePath;
	/** 内容. */
	private String contents;
	/** 博客状态：0：开放；1：半开放(尽好友可见);2：未开放，仅自己可见. */
	private String blogType;
	/** 赞次数. */
	private Long zanCount;
	/** 评论次数. */
	private Long commentCount;
	/** 是否置顶. */
	private String isUp;
	/** 新建人. */
	private String createdBy;
	/** 新建时间. */
	private Date createdDate;
	/** 修改人. */
	private String updatedBy;
	/** 修改时间. */
	private Date updatedDate;

	
	public UserBlogEntity() {
	}

	public UserBlogEntity(Long blogId){
			this.blogId = blogId;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getBlogTermId() {
		return blogTermId;
	}

	public void setBlogTermId(String blogTermId) {
		this.blogTermId = blogTermId;
	}
	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getBlogType() {
		return blogType;
	}

	public void setBlogType(String blogType) {
		this.blogType = blogType;
	}
	
	public Long getZanCount() {
		return zanCount;
	}

	public void setZanCount(Long zanCount) {
		this.zanCount = zanCount;
	}
	
	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
	
	public String getIsUp() {
		return isUp;
	}

	public void setIsUp(String isUp) {
		this.isUp = isUp;
	}
	
	@Length(min=0, max=32, message="新建人长度必须介于 0 和 32 之间")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Length(min=0, max=32, message="修改人长度必须介于 0 和 32 之间")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	
}