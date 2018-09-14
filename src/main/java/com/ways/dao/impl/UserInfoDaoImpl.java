package com.ways.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.ways.dao.UserInfoDao;
import com.ways.entity.UserInfoEntity;

@Repository
public class UserInfoDaoImpl extends Base2DaoImpl implements UserInfoDao{

	@Override
	public UserInfoEntity getByParam(UserInfoEntity info) {
		return super.selectOne("get", info);
	}

	@Override
	public UserInfoEntity insert(UserInfoEntity entity) {
		super.insert("insert", entity);
		return entity;
	}

	@Override
	public UserInfoEntity update(UserInfoEntity entity) {
		Assert.notNull(entity.getUserId());
		super.update("update", entity);
		return entity;
	}

	@Override
	public UserInfoEntity findByUsername(String nickName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nickName", nickName);
		return super.selectOne("get", paramMap);
	}

}
