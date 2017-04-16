package com.suitang.dao;

import java.io.Serializable;

import com.suitang.domain.User;
import com.suitang.domain.UserLocalAuths;

/**
 * UserLocalAuthDao
 * @author Xjh
 *
 */
public interface UserLocalAuthDao extends BaseDao<UserLocalAuths>{

	//用来写除了增删改查的其他方法
	
	public User getUserBySchool_no(Serializable school_no);
	
}
