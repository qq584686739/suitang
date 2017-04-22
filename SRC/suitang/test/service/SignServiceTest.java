package service;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.domain.Sign;
import com.suitang.service.SignService;

public class SignServiceTest {
	@Test
	public void testSignService_save(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		SignService signService = (SignService)applicationContext.getBean("signService");
		Sign sign = new Sign();
		sign.setUid(222);
		sign.setSign_token(UUID.randomUUID().toString());
		sign.setSign_time(new Date().getTime());
//		sign.setSign_id(sign_id);
		sign.setInvalid_time(new Date().getTime() + 5*60*1000);
		sign.setCid("(2016-2017-1)-TS0513-130005-3");
		sign.setCd_id("J1401");
		sign.setC_year(2016);
		sign.setC_week("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19");
		sign.setC_time("2");
		sign.setC_term(3);
		sign.setC_lesson("1,2");
		
		signService.saveSign(sign);
	}
}
