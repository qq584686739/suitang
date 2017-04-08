package com.suitang.dao;

import com.suitang.domain.User_Local_Auths;

public interface BaseDao<T> {		
	//只用来卸增删改查
	void save(T entity);
	void update(T entity);
}
