package com.suitang.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.action.UserAction;
import com.suitang.action.UserLocalAuthAction;
import com.suitang.domain.User;
import com.suitang.domain.User_Local_Auths;
import com.suitang.service.UserService;

public class ActionTest {
	@Test
	public void testSave() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLocalAuthAction userLocalAuthAction = (UserLocalAuthAction) context.getBean("userLocalAuthAction");
	
		User_Local_Auths user_Local_Auths = new User_Local_Auths();
		user_Local_Auths.setPhone_no("18222626942");
		user_Local_Auths.setEmail("584686739@qq.com");
		user_Local_Auths.setPassword("xiaojiahao1997");
		
		userLocalAuthAction.save();
	}
	@Test
	public void testUpdate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLocalAuthAction userLocalAuthAction = (UserLocalAuthAction) context.getBean("userLocalAuthAction");
		
		userLocalAuthAction.update();
	}
	@Test
	public void testUser_save(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserAction userAction = (UserAction) context.getBean("userAction");
		
		userAction.save();
	}
	@Test
	public void testUser_get(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserAction userAction = (UserAction) context.getBean("userAction");
		
		User user = userAction.getUserById();
	}
}
