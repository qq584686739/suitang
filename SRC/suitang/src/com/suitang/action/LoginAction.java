package com.suitang.action;

import java.io.PrintWriter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.Login;

@SuppressWarnings("serial")
@Controller("loginAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class LoginAction extends BaseAction<Login>{
	
	/**获得模型驱动*/
	private Login loginInfo = this.getModel();
	
	private PrintWriter out = null;
	
	public String login(){
		return SUCCESS;
	}
	
	/**验证学号密码是否正确*/
	@Override
	public void validate() {
		
		String identifier = loginInfo.getIdentifier();
		
		super.validate();
	}
}
