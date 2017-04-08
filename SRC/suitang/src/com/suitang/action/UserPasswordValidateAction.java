package com.suitang.action;

import java.util.HashMap;
import java.util.Map;



import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.UserPassword;
import com.suitang.utils.ValidateUtil;

@SuppressWarnings("serial")
@Controller("userPasswordValidateAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class UserPasswordValidateAction extends BaseAction<UserPassword>{
	/**
	 * 认证地址
	 */
	private final String requestUrl = "http://218.197.80.13/jwglxt/xtgl/login_cxCheckYh.html";
	/**获得模型驱动*/
	private UserPassword userPassword = this.getModel();
	
	/**返回字符串*/
	private String validateString = "error";
	
	/**验证方法*/
	public String openAuth(){
		
		System.out.println("result = " + validateString);
		return validateString;
	}
	
	
	/**验证学号密码是否正确*/
	@Override
	public void validate() {
		
		Map<String, String> requestProperty = new HashMap<String, String>();
		requestProperty.put("yhm", userPassword.getUser());
		requestProperty.put("mm", userPassword.getPassword());
		
		String result = ValidateUtil.validate(requestUrl, null, requestProperty);
		
		if(result.equals("success")){
			validateString = "success";
		}else {
			validateString = "error";
		}
		
		super.validate();
	}
}
