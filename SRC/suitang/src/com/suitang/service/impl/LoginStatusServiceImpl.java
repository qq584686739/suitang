package com.suitang.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.suitang.dao.LoginStatusDao;
import com.suitang.domain.LoginStatus;
import com.suitang.domain.User;
import com.suitang.service.LoginStatusService;

@Service(value="loginStatusService")
public class LoginStatusServiceImpl implements LoginStatusService{
	@Resource
	private LoginStatusDao loginStatusDao;

	@Override
	public void saveLoginStatus(LoginStatus loginStatus) {
		// TODO Auto-generated method stub
		loginStatusDao.save(loginStatus);
		
	}

	@Override
	public void updateLoginStatus(LoginStatus loginStatus) {
		// TODO Auto-generated method stub
		loginStatusDao.update(loginStatus);
		
	}

	@Override
	public LoginStatus getLoginStatusByLoginId(Serializable LoginId) {
		return loginStatusDao.getTById(LoginId);
	}

	@Override
	public User getUserByLoginId(String token) {
		return loginStatusDao.getUserByLoginId(token);
	}

}
