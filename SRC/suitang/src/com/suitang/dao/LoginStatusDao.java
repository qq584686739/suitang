package com.suitang.dao;

import com.suitang.domain.LoginStatus;
import com.suitang.domain.User;

public interface LoginStatusDao extends BaseDao<LoginStatus>{

	/**
	 * 根据token得到user
	 * getUserByLoginId
	 * @Description:
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月20日 下午11:18:27
	 * @param token
	 * @Return:User
	 */
	User getUserByLoginId(String token);
	//用来写除了增删改查的其他方法
	
	
}
