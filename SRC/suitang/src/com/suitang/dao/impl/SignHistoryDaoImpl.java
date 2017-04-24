package com.suitang.dao.impl;

import org.springframework.stereotype.Repository;

import com.suitang.dao.SignHistoryDao;
import com.suitang.domain.SignHistory;

@Repository(value="signHistoryDao")
public class SignHistoryDaoImpl extends BaseDaoImpl<SignHistory> implements SignHistoryDao{
	//用来实现除了增删改查的其他方法

}
