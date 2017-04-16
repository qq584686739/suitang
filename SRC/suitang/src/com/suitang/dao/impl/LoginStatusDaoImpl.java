package com.suitang.dao.impl;

import org.springframework.stereotype.Repository;

import com.suitang.dao.LoginStatusDao;
import com.suitang.dao.UserDao;
import com.suitang.domain.LoginStatus;
import com.suitang.domain.User;

@Repository(value="loginStatusDao")
public class LoginStatusDaoImpl extends BaseDaoImpl<LoginStatus> implements LoginStatusDao{
	//用来实现除了增删改查的其他方法
}
