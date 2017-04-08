package com.suitang.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.dao.UserDao;
import com.suitang.dao.UserLocalAuthDao;
import com.suitang.dao.UserLoginRecordDao;
import com.suitang.dao.impl.UserLocalAuthDaoImpl;
import com.suitang.domain.User;
import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.User_Local_Auths;

public class DaoTest {
	@Test
	public void testSave(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLocalAuthDao userLocalAuthDao = (UserLocalAuthDaoImpl) context.getBean("userLocalAuthDao");
		
		User_Local_Auths user = new User_Local_Auths();
//		user.setUid(10);
		user.setPhone_no("123456789");
		user.setEmail("584686739@qq.com");
		user.setPassword("123456");
		
		userLocalAuthDao.save(user);
	}
	@Test
	public void testUpdate(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLocalAuthDao userLocalAuthDao = (UserLocalAuthDaoImpl) context.getBean("userLocalAuthDao");
		
		User_Local_Auths user = new User_Local_Auths();
		user.setUid(16);
		user.setPhone_no("16");
		user.setEmail("16");
		user.setPassword("16");
		
		userLocalAuthDao.update(user);
	}
	
	
	@Test
	public void testUserLocalRecord(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLoginRecordDao userLoginRecordDao = (UserLoginRecordDao) context.getBean("userLocalRecordDao");
		
		UserLoginRecord userLoginRecord = new UserLoginRecord();
		userLoginRecord.setUid(1);
		userLoginRecord.setFirst_login_time(1111);
		userLoginRecord.setLast_login_time(2222);
		userLoginRecord.setLast_login_device("asdfffffff");
		userLoginRecord.setLast_login_device_id("123456789789");
		
		
		userLoginRecordDao.save(userLoginRecord);
		
	}
	
	@Test
	public void testUser_save(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		
		User user = new User();
		user.setUid(11);
		user.setNickname("nicheng");
		user.setAvatar("touxiang1111111111");
		user.setSex(0);
		user.setRank(0);
		userDao.save(user);
	}
	
	@Test
	public void testUser_get(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		
		try {
			User user = userDao.getUserById(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
