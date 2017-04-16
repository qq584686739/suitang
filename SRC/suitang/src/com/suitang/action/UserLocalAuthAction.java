package com.suitang.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.UserLocalAuths;
import com.suitang.service.UserLocalAuthService;

@SuppressWarnings("serial")
@Controller("userLocalAuthAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class UserLocalAuthAction extends BaseAction<UserLocalAuths>{
	
	@Resource(name = "userLocalAuthService")
	private UserLocalAuthService userLocalAuthService;
	
	private UserLocalAuths userLocalAuths = this.getModel();
	
	/**执行保存*/
	public String save(){
		UserLocalAuths user_Local_Auths = new UserLocalAuths();
		user_Local_Auths.setUid(84);			//跟user表的uid关联
		user_Local_Auths.setPhone_no("84");		
		user_Local_Auths.setEmail("84");
		user_Local_Auths.setPassword("84");
		user_Local_Auths.setSchool_no("1");		//学号
		user_Local_Auths.setRand(0);			//职位 0学生 1老师
		
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
		
		userLocalAuthService.updateUserLocalAuthS(userLocalAuths);
		return "save";
	}
}
