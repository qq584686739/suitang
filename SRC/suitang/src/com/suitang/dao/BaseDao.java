package com.suitang.dao;

import java.io.Serializable;

import com.suitang.domain.User;

public interface BaseDao<T> {		
	//只用来卸增删改查
	void save(T entity);
	void update(T entity);
	User getUserById(Serializable uid)throws Exception;
}
