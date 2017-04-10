package com.suitang.service;

import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.UserOtherAuths;

public interface UserLoginRecordService {
	public void saveUserLoginRecord(UserLoginRecord userLoginRecord);
	public void updateUserLoginRecord(UserLoginRecord userLoginRecord);
	public UserLoginRecord getUserLoginRecordByLast_login_device_id(
			String last_login_device_id);
	
	/***
	 * 通过[user_login_record]的last_login_id得到uid，再通过uid得到UserOtherAuths
	 * @param last_login_id
	 * @return	UserOtherAuths
	 */
	public UserOtherAuths getUserOtherAuthsByLast_login_device_id(
			String last_login_device_id);
}
