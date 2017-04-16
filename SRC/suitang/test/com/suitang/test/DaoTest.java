package com.suitang.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.dao.UserDao;
import com.suitang.dao.UserLocalAuthDao;
import com.suitang.dao.UserLoginRecordDao;
import com.suitang.dao.UserOtherAuthsDao;
import com.suitang.dao.impl.UserLocalAuthDaoImpl;
import com.suitang.domain.User;
import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.UserOtherAuths;
import com.suitang.domain.UserLocalAuths;
import com.suitang.service.UserLoginRecordService;

public class DaoTest {
	@Test
	public void testSave(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLocalAuthDao userLocalAuthDao = (UserLocalAuthDaoImpl) context.getBean("userLocalAuthDao");
		
		UserLocalAuths user = new UserLocalAuths();
//		user.setUid(10);
		user.setPhone_no("123456789");
		user.setEmail("肖家豪584686739@qq.com");
		user.setPassword("123456");
		
		userLocalAuthDao.save(user);
	}
	@Test
	public void testUpdate(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLocalAuthDao userLocalAuthDao = (UserLocalAuthDaoImpl) context.getBean("userLocalAuthDao");
		
		UserLocalAuths user = new UserLocalAuths();
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
		userLoginRecord.setUid(2);
		userLoginRecord.setFirst_login_time(1111);
		userLoginRecord.setLast_login_time(2222);
		userLoginRecord.setLast_login_device("asdfffffff");
		userLoginRecord.setLast_login_device_id("222");
		
		userLoginRecordDao.save(userLoginRecord);
	}
	
	@Test
	public void testUser_save(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		
		User user = new User();
//		user.setUid(2);
		user.setNickname("这是我的昵称");
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
			User user = userDao.getTById(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUserOtherAuths_save(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserOtherAuthsDao userOtherAuthsDao = (UserOtherAuthsDao) context.getBean("userOtherAuthsDao");
		
		UserOtherAuths userOtherAuths = new UserOtherAuths();
		userOtherAuths.setUid(6);
		userOtherAuths.setIdentity_type("111111");
		userOtherAuths.setIdentifier("222222");
		userOtherAuths.setToken("我是tokensad萨达");
		userOtherAuths.setInvalid_time((long)123789);
		
		userOtherAuthsDao.save(userOtherAuths);
	}
	@Test
	public void testUserOtherAuths_getById(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserOtherAuthsDao userOtherAuthsDao = (UserOtherAuthsDao) context.getBean("userOtherAuthsDao");
		
		User user = userOtherAuthsDao.getUserByIdentity_typeAndIdentifier(
				"222", "111");
		System.out.println(user.getUid());
	}
	
	@Test
	public void testUserLoginRecord_get(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLoginRecordDao userLoginRecordDao = (UserLoginRecordDao) context.getBean("userLoginRecordDao");
		
		UserLoginRecord userLoginRecord = userLoginRecordDao.getUserLoginRecordByLast_login_device_id("222");
	}
	
	@Test
	public void testUserLoginRecord_getUserOtherAuths(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLoginRecordDao userLoginRecordDao = (UserLoginRecordDao) context.getBean("userLoginRecordDao");
		
		UserOtherAuths userOtherAuths = 
				userLoginRecordDao.getUserOtherAuthsByLast_login_device_id("333");
	}
	@Test
	public void testUserLoginRecord_getUserOtherAuthsByLast_login_device_id(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLoginRecordDao userLoginRecordDao = (UserLoginRecordDao) context.getBean("userLoginRecordDao");
		
		userLoginRecordDao.getUserOtherAuthsByLast_login_device_id("");
	}
}
