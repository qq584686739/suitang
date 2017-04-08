package com.suitang.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.dao.UserLocalAuthDao;
import com.suitang.dao.impl.UserLocalAuthDaoImpl;
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
}
