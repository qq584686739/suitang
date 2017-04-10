package com.suitang.dao;

import com.suitang.domain.User;
import com.suitang.domain.UserOtherAuths;

public interface UserOtherAuthsDao extends BaseDao<UserOtherAuths>{
	
	//用来写除了增删改查的其他方法
	/**
	 * 根据前台传来的认证类型和认证id去得到uid，再根据uid得到user
	 * @User xjh
	 * @param identity_type	认证类型
	 * @param identifier	认证id
	 * @return	User
	 */
	public User getUserByIdentity_typeAndIdentifier(String identity_type, String identifier);
	
}
