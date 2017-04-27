package com.suitang.dao;

import java.io.Serializable;

import com.suitang.domain.Sign;

/**
 * UserDao
 * @author Xjh
 *
 */
public interface SignDao extends BaseDao<Sign>{

	Sign findSignBySign_token(String sign_token);

	Sign findSignBySign_id(Serializable sign_id);

	Sign[] findSignsByUid(Serializable uid);

	//用来写除了增删改查的其他方法
	
}
