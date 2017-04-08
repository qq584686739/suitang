package com.suitang.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.User_Local_Auths;
import com.suitang.service.UserLocalAuthService;

@SuppressWarnings("serial")
@Controller("userLocalAuthAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class UserLocalAuthAction extends BaseAction<User_Local_Auths>{
	
	@Resource(name = "userLocalAuthService")
	private UserLocalAuthService userLocalAuthService;
	
	private User_Local_Auths user_Local_Auths = this.getModel();
	
	/**执行保存*/
	public String save(){
//		User_Local_Auths user_Local_Auths = new User_Local_Auths();
//		user_Local_Auths.setUid(16);
//		user_Local_Auths.setPhone_no("16");
//		user_Local_Auths.setEmail("16");
//		user_Local_Auths.setPassword("16");
		
		userLocalAuthService.saveUserLocalAuthS(user_Local_Auths);
		return "save";
	}
	/**执行更新*/
	public String update(){
//		User_Local_Auths user_Local_Auths = new User_Local_Auths();
//		user_Local_Auths.setUid(16);
//		user_Local_Auths.setPhone_no("16");
//		user_Local_Auths.setEmail("16");
//		user_Local_Auths.setPassword("16");
		
		userLocalAuthService.updateUserLocalAuthS(user_Local_Auths);
		return "save";
	}
}
