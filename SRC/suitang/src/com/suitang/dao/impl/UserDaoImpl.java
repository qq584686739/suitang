package com.suitang.dao.impl;

import org.springframework.stereotype.Repository;

import com.suitang.dao.UserDao;
import com.suitang.domain.User;

@Repository(value="userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	//用来实现除了增删改查的其他方法
}
