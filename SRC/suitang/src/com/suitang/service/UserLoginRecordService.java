package com.suitang.service;

import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.UserOtherAuths;

public interface UserLoginRecordService {
	/**
	 * saveUserLoginRecord
	 * @Description:传入UserLoginRecord对象保存到UserLoginRecord表
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017-4-10(创建日期)
	 * @Parameters:UserLoginRecord
	 * @Return:void
	 */
	public void saveUserLoginRecord(UserLoginRecord userLoginRecord);
	
	/**
	 * updateUserLoginRecord
	 * @Description:传入UserLoginRecord对象更新UserLoginRecord表
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017-4-10(创建日期)
	 * @Parameters:UserLoginRecord
	 * @Return:void
	 */
	public void updateUserLoginRecord(UserLoginRecord userLoginRecord);
	
	/**
	 * getUserLoginRecordByLast_login_device_id
	 * @Description:根据设备唯一标识id得到UserLoginRecord
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017-4-10(创建日期)
	 * @Parameters:last_login_device_id
	 * @Return:UserLoginRecord
	 */
	public UserLoginRecord getUserLoginRecordByLast_login_device_id(
			String last_login_device_id);
	
	 /**
	 * getUserOtherAuthsByLast_login_device_id
	 * @Description:通过[user_login_record]的last_login_id得到uid，再通过uid得到UserOtherAuths
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017-4-10(创建日期)
	 * @Parameters:无
	 * @Return:UserOtherAuths:
	 */
	public UserOtherAuths getUserOtherAuthsByLast_login_device_id(
			String last_login_device_id);
}
