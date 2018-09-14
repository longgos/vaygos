package com.ways.dao;

import com.ways.entity.UserInfoEntity;

public interface UserInfoDao {

	UserInfoEntity getByParam(UserInfoEntity info);

	UserInfoEntity insert(UserInfoEntity entity);

	UserInfoEntity update(UserInfoEntity entity);

	UserInfoEntity findByUsername(String nickName);

}
