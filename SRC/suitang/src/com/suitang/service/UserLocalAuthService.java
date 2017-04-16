package com.suitang.service;

import java.io.Serializable;

import com.suitang.domain.User;
import com.suitang.domain.UserLocalAuths;

public interface UserLocalAuthService {
	public void saveUserLocalAuthS(UserLocalAuths user_Local_Auths);
	public void updateUserLocalAuthS(UserLocalAuths user_Local_Auths);
	public User getUserBySchool_no(Serializable school_no);
}
