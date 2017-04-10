package com.suitang.dao;

import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.UserOtherAuths;

public interface UserLoginRecordDao extends BaseDao<UserLoginRecord>{

	//用来写除了增删改查的其他方法
	
	public UserLoginRecord getUserLoginRecordByLast_login_device_id(
			String last_login_device_id);
	
	/**
	 * 通过主键last_login_id找到uid，再通过uid找到UserOtherAuths
	 * @param last_login_id 登录设备唯一id
	 * @return
	 */
	public UserOtherAuths getUserOtherAuthsByLast_login_device_id(
			String last_login_device_id);
}
