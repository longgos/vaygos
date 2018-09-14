package com.ways.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.ways.dao.Base2Dao;

/**
 * DAO支持类实现
 * @author ThinkGem
 * @version 2014-05-16
 */

public class Base2DaoImpl extends SqlSessionDaoSupport implements Base2Dao {

	protected String namespace;
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
    }
	
	public Base2DaoImpl() {
		super();
		namespace = this.getClass().getName().replace("Impl", "").replace(".impl", "");
	}

	private String appendNamespace(String arg0) {
		return namespace + "." + arg0;
	}
	
/*	@SuppressWarnings("unchecked")
	public <T> DataPageModel<T> selectPage(String statement, Object param, Pager pager) {
		PagerRowBounds rb = new PagerRowBounds(pager.getOffset(), pager.getLimit());
		List<Object> a = getSqlSession().selectList(appendNamespace(statement), param, rb);
		DataPageModel<T> pm = (DataPageModel<T>) a.get(0);
		return pm;
	}
	
	public <T> DataPageModel<T> selectPage(String statement, Object param) {
		Object pager;
		try {
			pager = PropertyUtils.getProperty(param, "pager");
		} catch (Exception e) {
			throw new RuntimeException("未找到分页参数:pager", e);
		}
		Assert.notNull(pager, "pager不能为null");
		return this.selectPage(statement, param, (Pager)pager);
	}*/

	public <T> T selectOne(String statement) {
		return getSqlSession().selectOne(appendNamespace(statement));
	}

	public <T> T selectOne(String statement, Object parameter) {
		return getSqlSession().selectOne(appendNamespace(statement), parameter);
	}

	public <E> List<E> selectList(String statement) {
		return getSqlSession().selectList(appendNamespace(statement));
	}

	/*public <E> List<E> selectList(String statement, Object parameter) {
		Pager pager = null;
		if (PropertyUtils.isReadable(parameter, "pager")) {
			try {
				pager = (Pager) PropertyUtils.getProperty(parameter, "pager");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		if (null != pager) {
			return this.selectList(statement, parameter, pager);
		}
		return getSqlSession().selectList(appendNamespace(statement), parameter);
	}*/

	public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
		return getSqlSession().selectList(appendNamespace(statement), parameter, rowBounds);
	}
	
	/*public <E> List<E> selectList(String statement, Object parameter, Limiter limiter) {
		RowBounds rowBounds = new RowBounds(limiter.getOffset(), limiter.getLimit());
		return getSqlSession().selectList(appendNamespace(statement), parameter, rowBounds );
	}*/

	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return getSqlSession().selectMap(appendNamespace(statement), mapKey);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
		return getSqlSession().selectMap(appendNamespace(statement), parameter, mapKey);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		return getSqlSession().selectMap(appendNamespace(statement), parameter, mapKey, rowBounds);
	}

	public void select(String statement, Object parameter, ResultHandler handler) {
		getSqlSession().select(appendNamespace(statement), parameter, handler);
	}

	public void select(String statement, ResultHandler handler) {
		getSqlSession().select(appendNamespace(statement), handler);
	}

	public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		getSqlSession().select(appendNamespace(statement), parameter, rowBounds, handler);
	}

	public int insert(String statement) {
		return getSqlSession().insert(appendNamespace(statement));
	}

	public int insert(String statement, Object parameter) {
		return getSqlSession().insert(appendNamespace(statement), parameter);
	}

	public int update(String statement) {
		return getSqlSession().update(appendNamespace(statement));
	}

	public int update(String statement, Object parameter) {
		return getSqlSession().update(appendNamespace(statement), parameter);
	}

	public int delete(String statement) {
		return getSqlSession().delete(appendNamespace(statement));
	}

	public int delete(String statement, Object parameter) {
		return getSqlSession().delete(appendNamespace(statement), parameter);
	}

	public void commit() {
		getSqlSession().commit();
	}

	public void commit(boolean force) {
		getSqlSession().commit(force);
	}

	public void rollback() {
		getSqlSession().rollback();
	}

	public void rollback(boolean force) {
		getSqlSession().rollback(force);
	}

	public List<BatchResult> flushStatements() {
		return getSqlSession().flushStatements();
	}

	public void close() {
		getSqlSession().close();
	}

	/*public void clearCache() {
		getSqlSession().clearCache();
	}*/

	public Configuration getConfiguration() {
		return getSqlSession().getConfiguration();
	}

	public <T> T getMapper(Class<T> type) {
		return getSqlSession().getMapper(type);
	}

	public Connection getConnection() {
		return getSqlSession().getConnection();
	}

}