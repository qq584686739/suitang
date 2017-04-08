package com.suitang.service;

import java.io.Serializable;

import com.suitang.domain.User;

public interface UserService {
	public void saveUser(User user);
	public void updateUser(User user);
	public User getUserById(Serializable uid);
}
