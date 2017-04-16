package com.suitang.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.suitang.dao.UserLocalAuthDao;
import com.suitang.domain.User;
import com.suitang.domain.UserLocalAuths;
import com.suitang.service.UserLocalAuthService;

@Service(value="userLocalAuthService")
public class UserLocalAuthServiceImpl implements UserLocalAuthService{
	@Resource
	private UserLocalAuthDao userLocalAuthDao;
	
	@Override
	public void saveUserLocalAuthS(UserLocalAuths user_Local_Auths) {
		userLocalAuthDao.save(user_Local_Auths);
	}


	@Override
	public void updateUserLocalAuthS(UserLocalAuths user_Local_Auths) {
		userLocalAuthDao.update(user_Local_Auths);
	}
	
	public User getUserBySchool_no(Serializable school_no) {
		return userLocalAuthDao.getUserBySchool_no(school_no);
	}
}
