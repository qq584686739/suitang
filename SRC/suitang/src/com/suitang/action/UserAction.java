package com.suitang.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.User;
import com.suitang.service.UserService;

@SuppressWarnings("serial")
@Controller("userAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class UserAction extends BaseAction<User>{
	
	@Resource(name = "userService")
	private UserService userService;
	
	private User user = this.getModel();
	
	/**执行保存*/
	public String save(){
		
		User user = new User();
		user.setUid(22);
		user.setNickname("这是我的昵称");
		user.setAvatar("这是我的头像地址");
		user.setSex(0);
		user.setRank(0);
		
		userService.saveUser(user);
		return "save";
	}
	/**执行更新*/
	public String update(){
		
		userService.updateUser(user);
		return "save";
	}
	
	public User getUserById(){
		int i = 1;
		User user1 = userService.getUserById(i);
		return user1;
	}
}
