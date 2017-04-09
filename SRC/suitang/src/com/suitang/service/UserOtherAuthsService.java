package com.suitang.service;

import com.suitang.domain.User;
import com.suitang.domain.UserOtherAuths;

public interface UserOtherAuthsService {
	public void saveUserOtherAuths(UserOtherAuths userOtherAuths);
	public void updateUserOtherAuths(UserOtherAuths userOtherAuths);
	public User getUserByIdentity_typeAndIdentifier(String identity_type,
			String identifier);
}
