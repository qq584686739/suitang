package com.suitang.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.domain.User_Local_Auths;
import com.suitang.service.UserLocalAuthService;
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
}
