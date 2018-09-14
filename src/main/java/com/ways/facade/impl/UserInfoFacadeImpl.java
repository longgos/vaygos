package com.ways.facade.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ways.constants.ConstantFilal;
import com.ways.dao.UserInfoDao;
import com.ways.entity.UserInfoEntity;
import com.ways.facade.UserInfoFacade;

@Service("userInfoFacade")
public class UserInfoFacadeImpl implements UserInfoFacade{

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public UserInfoEntity getByParam(UserInfoEntity info) {
		return userInfoDao.getByParam(info);
	}

	@Override
	public UserInfoEntity save(UserInfoEntity entity) {
		if (entity.getUserId() == null) {
			entity.setCreateBy(ConstantFilal.SYSTEM);
			entity.setCreateDate(new Date());
			return userInfoDao.insert(entity);
		} else {
			return userInfoDao.update(entity);
		}
	}

	@Override
	public UserInfoEntity findByUsername(String nickName) {
		return userInfoDao.findByUsername(nickName);
	}

}
