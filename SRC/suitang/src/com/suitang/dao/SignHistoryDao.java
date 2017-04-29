package com.suitang.dao;

import java.io.Serializable;

import com.suitang.domain.SignHistory;

/**
 * 
 * @author Xjh
 *
 */
public interface SignHistoryDao extends BaseDao<SignHistory>{

	public SignHistory[] getSignHistorysBySign_id(Serializable sign_id);

	public SignHistory[] getSignHistorysByUid(Integer uid);

	//用来写除了增删改查的其他方法
	
}
