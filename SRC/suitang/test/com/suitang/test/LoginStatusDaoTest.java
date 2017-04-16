package com.suitang.test;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.dao.LoginStatusDao;
import com.suitang.domain.LoginStatus;

public class LoginStatusDaoTest {
	@Test
	public void test_save(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		LoginStatusDao loginStatusDao = (LoginStatusDao)applicationContext.getBean("loginStatusDao");
		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setUid(85);
		loginStatus.setLogin_id("123123123123sa1d1d23d1sadsa1");
		Timestamp expiration_time = new Timestamp(new Date().getTime());
		loginStatus.setExpiration_time(expiration_time);
		loginStatusDao.save(loginStatus);
	}
}
