package com.suitang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ModelDriven;
import com.suitang.dao.UserLoginRecordDao;
import com.suitang.domain.User;
import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.UserOtherAuths;
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

	@Override
	public UserLoginRecord getUserLoginRecordByLast_login_device_id(
			String last_login_device_id) {
		return userLoginRecordDao.getUserLoginRecordByLast_login_device_id(
				last_login_device_id);
	}

	@Override
	public UserOtherAuths getUserOtherAuthsByLast_login_device_id(
			String last_login_device_id) {
		return userLoginRecordDao.getUserOtherAuthsByLast_login_device_id(last_login_device_id);
	}

	@Override
	public User getUserByLast_login_device_id(String last_login_device_id) {
		return userLoginRecordDao.getUserByLast_login_device_id(last_login_device_id);
	}
}
