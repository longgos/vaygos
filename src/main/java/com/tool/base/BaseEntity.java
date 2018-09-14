package com.tool.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity支持类
 * @author ThinkGem
 * @version 2014-05-16
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private String dbName = "mysql";

	public BaseEntity() {
		
	}
	
	/**
	 * 获取数据库名称
	 */
	public String getDbName(){
		return this.dbName;
	}
	
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
//	/**
//	 * 删除标记（0：正常；1：删除；2：审核；）
//	 */
	
}
