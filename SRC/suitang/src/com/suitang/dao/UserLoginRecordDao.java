package com.suitang.dao;

import com.suitang.domain.User;
import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.UserOtherAuths;

public interface UserLoginRecordDao extends BaseDao<UserLoginRecord>{

	//用来写除了增删改查的其他方法
	
	/**
	 * 通过主键last_login_id找到UserLoginRecord
	 * @param last_login_device_id 登录设备唯一id
	 * @return UserLoginRecord
	 */
	public UserLoginRecord getUserLoginRecordByLast_login_device_id(
			String last_login_device_id);
	
	/**
	 * 通过主键last_login_id找到uid，再通过uid找到UserOtherAuths
	 * @param last_login_id 登录设备唯一id
	 * @return UserOtherAuths
	 */
	public UserOtherAuths getUserOtherAuthsByLast_login_device_id(
			String last_login_device_id);
	
	public User getUserByLast_login_device_id(String last_login_device_id);
}
