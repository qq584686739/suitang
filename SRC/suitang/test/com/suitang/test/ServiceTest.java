package com.suitang.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.domain.User;
import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.User_Local_Auths;
import com.suitang.service.UserLocalAuthService;
import com.suitang.service.UserLoginRecordService;
import com.suitang.service.UserService;
import com.suitang.service.impl.UserLocalAuthServiceImpl;

public class ServiceTest {
	@Test
	public void testSave(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLocalAuthService userLocalAuthService = (UserLocalAuthServiceImpl) context.getBean("userLocalAuthService");
	
		User_Local_Auths user_Local_Auths = new User_Local_Auths();
		user_Local_Auths.setPhone_no("18222626942");
		user_Local_Auths.setEmail("584686739@qq.com");
		user_Local_Auths.setPassword("xiaojiahao1997");
		
		userLocalAuthService.saveUserLocalAuthS(user_Local_Auths);
	}
	@Test
	public void testUpdate(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLocalAuthService userLocalAuthService = (UserLocalAuthServiceImpl) context.getBean("userLocalAuthService");
	
		User_Local_Auths user_Local_Auths = new User_Local_Auths();
		user_Local_Auths.setUid(16);
		user_Local_Auths.setPhone_no("18222626942");
		user_Local_Auths.setEmail("584686739@qq.com");
		user_Local_Auths.setPassword("xiaojiahao1997");
		
		userLocalAuthService.updateUserLocalAuthS(user_Local_Auths);
	}
	
	@Test
	public void testUserLoginRecordService_save(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLoginRecordService userLoginRecordService = (UserLoginRecordService) context.getBean("userLoginRecordService");
	
		UserLoginRecord userLoginRecord = new UserLoginRecord();
		userLoginRecord.setUid(4);
		userLoginRecord.setFirst_login_time(4444444);
		userLoginRecord.setLast_login_time(4444444);
		
		userLoginRecord.setLast_login_device("444444444444");
		userLoginRecord.setLast_login_device_id("44444444444444");
		
		userLoginRecordService.saveUserLoginRecord(userLoginRecord);
		
	}
	
	@Test
	public void testUser_save(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		
		User user = new User();
		user.setUid(15);
		user.setNickname("nicheng");
		user.setAvatar("touxiang1111111111");
		user.setSex(0);
		user.setRank(0);
		
		userService.saveUser(user);
	
	}
	
	@Test
	public void testUser_get(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		
		User user = userService.getUserById(1);
	
	}
}
