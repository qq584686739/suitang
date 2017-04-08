package com.suitang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ModelDriven;
import com.suitang.dao.UserLocalAuthDao;
import com.suitang.domain.User_Local_Auths;
import com.suitang.service.UserLocalAuthService;

@Service(value="userLocalAuthService")
public class UserLocalAuthServiceImpl implements UserLocalAuthService,ModelDriven<User_Local_Auths>{
	@Resource
	private UserLocalAuthDao userLocalAuthDao;
	
	private User_Local_Auths user_Local_Auths;
	
	@Override
	public void saveUserLocalAuthS(User_Local_Auths user_Local_Auths) {
		userLocalAuthDao.save(user_Local_Auths);
	}

	@Override
	public User_Local_Auths getModel() {
		return user_Local_Auths;
	}

	@Override
	public void updateUserLocalAuthS(User_Local_Auths user_Local_Auths) {
		userLocalAuthDao.update(user_Local_Auths);
	}
}
