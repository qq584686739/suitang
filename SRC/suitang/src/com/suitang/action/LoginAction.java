package com.suitang.action;

import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.Login;
import com.suitang.domain.User;
import com.suitang.service.UserOtherAuthsService;

@SuppressWarnings("serial")
@Controller("loginAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class LoginAction extends BaseAction<Login>{
	
	/**获得模型驱动*/
	private Login login = this.getModel();
	
	@Resource
	private UserOtherAuthsService userOtherAuthsService;
	
	private PrintWriter out = null;
	
	public String login(){
		String nickName = login.getNickName();							//获得昵称
		int sex = login.getSex();										//获得性别
		String acatar = login.getAvatar();								//获得头像
		String last_login_device = login.getLast_login_device();		//获得本次登录设备
		String last_login_device_id = login.getLast_login_device_id();	//获得本次登录设备id
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		
		String identity_type = login.getIdentity_type();		//得到认证类型
		String identifier = login.getIdentifier();				//得到认证id
		
		User user = userOtherAuthsService.getUserByIdentity_typeAndIdentifier(
				identity_type, identifier);
		if(user==null){	//说明数据库不存在该数据，应该新建存储数据并返回
			
		}else{			//说明数据库存在数据，需要得到数据并返回
			
		}
		
		super.validate();
	}
}
