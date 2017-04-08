package com.suitang.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.suitang.dao.UserDao;
import com.suitang.domain.User;
import com.suitang.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public User getUserById(Serializable uid) {
		try {
			return userDao.getUserById(uid);
		} catch (Exception e) {
			return null;
		}
	}
}
