package com.suitang.dao;

import java.io.Serializable;

import com.suitang.domain.LoginStatus;
import com.suitang.domain.User;
import com.suitang.domain.UserLocalAuths;

/**
 * UserLocalAuthDao
 * @author Xjh
 *
 */
public interface UserLocalAuthDao extends BaseDao<UserLocalAuths>{

	//用来写除了增删改查的其他方法
	
	public User getUserBySchool_no(Serializable school_no);

	/**
	 * 
	 * getLoginStatusBySchool_no
	 * @Description:根据学号找到uid，再根据uid找到loginstatus
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月17日 上午11:34:44
	 * @param school_no
	 * @Return:LoginStatus
	 */
	public LoginStatus getLoginStatusBySchool_no(String school_no);
	
}
