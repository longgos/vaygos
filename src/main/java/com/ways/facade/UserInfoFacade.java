package com.ways.facade;

import com.ways.entity.UserInfoEntity;

public interface UserInfoFacade {

	UserInfoEntity getByParam(UserInfoEntity info);

	UserInfoEntity save(UserInfoEntity entity);
	
	UserInfoEntity findByUsername(String nickName);

}
