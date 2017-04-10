package com.suitang.dao;

import java.io.Serializable;

import com.suitang.domain.User;

public interface BaseDao<T> {		
	//只用来卸增删改查
	
	/**
	 * save
	 * @Description:公用的save方法
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017-4-10(创建日期)
	 * @Parameters:T
	 * @Return:void
	 */
	void save(T entity);
	
	/**
	 * update
	 * @Description:公用的update方法
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017-4-10(创建日期)
	 * @Parameters:T
	 * @Return:void
	 */
	void update(T entity);
	
	/**
	 * getTById
	 * @Description:公用的getTById方法
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017-4-10(创建日期)
	 * @Parameters:Serializable
	 * @Return:T
	 */
	T getTById(Serializable uid);
}
