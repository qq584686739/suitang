package com.suitang.service;

import java.io.Serializable;

import com.suitang.domain.LoginStatus;
import com.suitang.domain.User;

public interface LoginStatusService {
	public void saveLoginStatus(LoginStatus loginStatus);
	public void updateLoginStatus(LoginStatus loginStatus);
	public LoginStatus getLoginStatusByLoginId(Serializable LoginId);
	
	/**
	 * 根据token获得user
	 * getUserByLoginId
	 * @Description:
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月20日 下午11:17:29
	 * @param token
	 * @Return:User
	 */
	public User getUserByLoginId(String token);
}
