package com.suitang.dao;

import com.suitang.domain.User;
import com.suitang.domain.UserOtherAuths;

public interface UserOtherAuthsDao extends BaseDao<UserOtherAuths>{
	
	//用来写除了增删改查的其他方法
	
	public User getUserByIdentity_typeAndIdentifier(String identity_type, String identifier);
	
}
