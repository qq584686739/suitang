package com.suitang.dao.impl;

import org.springframework.stereotype.Repository;

import com.suitang.dao.UserLocalAuthDao;
import com.suitang.domain.User_Local_Auths;

@Repository(value="userLocalAuthDao")
public class UserLocalAuthDaoImpl extends BaseDaoImpl<User_Local_Auths> implements UserLocalAuthDao{
	//用来实现除了增删改查的其他方法
}
