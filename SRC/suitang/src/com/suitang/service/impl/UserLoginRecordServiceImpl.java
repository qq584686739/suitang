package com.suitang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ModelDriven;
import com.suitang.dao.UserLoginRecordDao;
import com.suitang.domain.UserLoginRecord;
import com.suitang.service.UserLoginRecordService;

@Service(value="userLoginRecordService")
public class UserLoginRecordServiceImpl implements UserLoginRecordService{
	@Resource
	private UserLoginRecordDao userLoginRecordDao;
	
	@Override
	public void saveUserLoginRecord(UserLoginRecord userLoginRecord) {
		userLoginRecordDao.save(userLoginRecord);
	}

	@Override
	public void updateUserLoginRecord(UserLoginRecord userLoginRecord) {
		userLoginRecordDao.update(userLoginRecord);
	}
}
