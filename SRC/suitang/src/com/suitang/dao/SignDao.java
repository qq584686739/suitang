package com.suitang.dao;

import com.suitang.domain.Sign;

/**
 * UserDao
 * @author Xjh
 *
 */
public interface SignDao extends BaseDao<Sign>{

	Sign findSignBySign_token(String sign_token);

	//用来写除了增删改查的其他方法
	
}
