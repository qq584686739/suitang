package com.suitang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.suitang.dao.UserLocalAuthDao;
import com.suitang.dao.UserOtherAuthsDao;
import com.suitang.domain.User;
import com.suitang.domain.UserOtherAuths;
import com.suitang.domain.User_Local_Auths;
import com.suitang.service.UserOtherAuthsService;

@Service(value="userOtherAuthsService")
public class UserOtherAuthsServiceImpl implements UserOtherAuthsService{
	@Resource
	private UserOtherAuthsDao userOtherAuthsDao;
	
	@Override
	public void saveUserOtherAuths(UserOtherAuths userOtherAuths) {
		userOtherAuthsDao.save(userOtherAuths);
	}


	@Override
	public void updateUserOtherAuths(UserOtherAuths userOtherAuths) {
		userOtherAuthsDao.update(userOtherAuths);
	}

	/**
	 * 根据前台传来的认证类型和认证id去得到uid，再根据uid得到user
	 */
	@Override
	public User getUserByIdentity_typeAndIdentifier(String identity_type,
			String identifier) {
		return userOtherAuthsDao.getUserByIdentity_typeAndIdentifier(
				identity_type, identifier);
	}
}
