package com.suitang.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.dao.UserOtherAuthsDao;
import com.suitang.domain.User;
import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.UserOtherAuths;
import com.suitang.domain.UserLocalAuths;
import com.suitang.service.UserLocalAuthService;
import com.suitang.service.UserLoginRecordService;
import com.suitang.service.UserOtherAuthsService;
import com.suitang.service.UserService;
import com.suitang.service.impl.UserLocalAuthServiceImpl;

public class ServiceTest {
	@Test
	public void testSave(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLocalAuthService userLocalAuthService = (UserLocalAuthServiceImpl) context.getBean("userLocalAuthService");
	
		UserLocalAuths user_Local_Auths = new UserLocalAuths();
		user_Local_Auths.setPhone_no("18222626942");
		user_Local_Auths.setEmail("584686739@qq.com");
		user_Local_Auths.setPassword("xiaojiahao1997");
		
		userLocalAuthService.saveUserLocalAuthS(user_Local_Auths);
	}
	@Test
	public void testUpdate(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLocalAuthService userLocalAuthService = (UserLocalAuthServiceImpl) context.getBean("userLocalAuthService");
	
		UserLocalAuths user_Local_Auths = new UserLocalAuths();
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
	
	
	@Test
	public void testUserOtherAuths_save(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserOtherAuthsDao userOtherAuthsDao = (UserOtherAuthsDao) context.getBean("userOtherAuthsDao");
		
		UserOtherAuths userOtherAuths = new UserOtherAuths();
//		userOtherAuths.setUid(3);
		userOtherAuths.setIdentity_type("asdsasa");
		userOtherAuths.setIdentifier("aaaaaa");
		userOtherAuths.setToken("tttttttttt");
		userOtherAuths.setInvalid_time((long)123789);
		
		userOtherAuthsDao.save(userOtherAuths);
	
	}
	
	@Test
	public void testUserLoginRecordService_get(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLoginRecordService userLoginRecordService = 
				(UserLoginRecordService) context.getBean("userLoginRecordService");
		
		UserLoginRecord userLoginRecord = 
				userLoginRecordService.getUserLoginRecordByLast_login_device_id("222");
	}
	
	@Test
	public void testUserLoginRecordService_getUserOtherAuths(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLoginRecordService userLoginRecordService = 
				(UserLoginRecordService) context.getBean("userLoginRecordService");
		UserOtherAuths userOtherAuths = 
				userLoginRecordService.getUserOtherAuthsByLast_login_device_id("333");
	}
	@Test
	public void getUserByIdentity_typeAndIdentifier(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserOtherAuthsService userOtherAuthsService = 
				(UserOtherAuthsService) context.getBean("userOtherAuthsService");
		User user = userOtherAuthsService.getUserByIdentity_typeAndIdentifier("qq1234", "141024");
	}
}
