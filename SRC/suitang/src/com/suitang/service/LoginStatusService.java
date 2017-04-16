package com.suitang.service;

import java.io.Serializable;

import com.suitang.domain.LoginStatus;
import com.suitang.domain.User;

public interface LoginStatusService {
	public void saveLoginStatus(LoginStatus loginStatus);
	public void updateLoginStatus(LoginStatus loginStatus);
	public LoginStatus getLoginStatusByLoginId(Serializable LoginId);
}
