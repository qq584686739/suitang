package service;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.domain.LoginStatus;
import com.suitang.service.LoginStatusService;

public class LoginStatusServiceTest {

	@Test
	public void save(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		LoginStatusService loginStatusService = (LoginStatusService)applicationContext.getBean("loginStatusService");
		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setUid(85);
		loginStatus.setLogin_id("111111111111111111111111111111111111");
		Timestamp expiration_time = new Timestamp(new Date().getTime());
		loginStatus.setExpiration_time(expiration_time);
		
		loginStatusService.saveLoginStatus(loginStatus);
	}
}
