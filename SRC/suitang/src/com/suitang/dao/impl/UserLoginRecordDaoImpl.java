package com.suitang.dao.impl;

import org.springframework.stereotype.Repository;

import com.suitang.dao.UserLoginRecordDao;
import com.suitang.domain.UserLoginRecord;

@Repository(value="userLocalRecordDao")
public class UserLoginRecordDaoImpl extends BaseDaoImpl<UserLoginRecord> implements UserLoginRecordDao{
	//用来实现除了增删改查的其他方法
}
