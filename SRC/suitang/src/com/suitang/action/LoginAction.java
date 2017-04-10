package com.suitang.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.Login;
import com.suitang.domain.User;
import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.UserOtherAuths;
import com.suitang.service.UserLoginRecordService;
import com.suitang.service.UserOtherAuthsService;
import com.suitang.service.UserService;

@SuppressWarnings("serial")
@Controller("loginAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class LoginAction extends BaseAction<Login>{
	
	/**获得模型驱动*/
	private Login login = this.getModel();
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserOtherAuthsService userOtherAuthsService;
	
	@Resource
	private UserLoginRecordService userLoginRecordService;
	
	private PrintWriter out = null;
	
	/**
	 * 登录标识
	 * false:不允许登录
	 * true：允许登录
	 */
	private boolean login_flag = false;
	
	/**需要返回的user*/
	private User user;
	
	/**登录返回的json格式数据*/
	JSONObject jsonObjectLoginInfo = new JSONObject();
	
	public String login(){
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(login_flag){			//允许登录
			jsonObjectLoginInfo.put("status", "success");
			jsonObjectLoginInfo.put("message", "");
			
			JSONObject jsonObjectLogintemp = JSONObject.fromObject(user);
			
			jsonObjectLoginInfo.put("data", jsonObjectLogintemp);
			
		}else{					//不允许登陆
			jsonObjectLoginInfo.put("status", "success");
			jsonObjectLoginInfo.put("message", "不允许在90分钟之内该设备登录两个用户");
			jsonObjectLoginInfo.put("data", "");
		}
		
		out.write(jsonObjectLoginInfo.toString());
		
		return SUCCESS;
	}
	
	
	
	
	@Override
	public void validate() {
		
		String identity_type = login.getIdentity_type();		//得到认证类型
		String identifier = login.getIdentifier();				//得到认证id
		
		user = userOtherAuthsService.getUserByIdentity_typeAndIdentifier(
				identity_type, identifier);
		if(user==null){				//没授权，说明数据库不存在该数据，应该新建数据存储并返回
			user = createNewUser();	//新建一个默认的user
			userService.saveUser(user);
			
			UserOtherAuths userOtherAuths = createUserOtherAuths(user.getUid());
			userOtherAuthsService.saveUserOtherAuths(userOtherAuths);
			
		}else{			//说明数据库存在数据，更新数据，返回user
			userService.updateUser(user);
		}
		
		UserLoginRecord userLoginRecord = 
				userLoginRecordService.getUserLoginRecordByLast_login_device_id(
						login.getLast_login_device_id());
		
		if(userLoginRecord == null){		//该设备第一次使用	允许登录
			userLoginRecord = createUserLoginRecord(user.getUid());
			userLoginRecordService.saveUserLoginRecord(userLoginRecord);
			
			login_flag = true;	//true：允许登录
			
		}else{			//该设备不是第一次使用
			//再判断这次的用户和上次的用户是否相同
			UserOtherAuths userOtherAuths = 	//获得上次的UserOtherAuths对象
					userLoginRecordService.getUserOtherAuthsByLast_login_device_id(
							login.getLast_login_device_id());
			String last_identifier = userOtherAuths.getIdentifier();			//获得上次的认证id
			if(last_identifier.equals(login.getIdentifier())){	//两个用户一致
				//相同，允许登录
				//说明上次登录的用户和本次登录的用户一致 允许登录，修改信息，返回user
				userLoginRecordService.updateUserLoginRecord(userLoginRecord);//更新登录信息
			}else{												//两个用户不一致
				//不相同，判断时间满足90分钟
				//说明上次登录的用户和本次登录的用户不一致
				long last_login_time = userLoginRecord.getLast_login_time();		//获得上次登录时间，用来判断是否满足90分钟
				long this_time = new Date().getTime();
				long time_disparity = this_time - last_login_time;//获得时间差
				if(time_disparity>3*60*1000){
					//满足90分钟		允许登录
					login_flag = true;	//true：允许登录
				}else {
					//不满足90分钟	不允许登录
					login_flag = false;	//false：允许登录
				}
			}
				
		}
		super.validate();
	}

	/**
	 * 创建一个默认的User用户
	 */
	public User createNewUser(){
		User user = new User();
//		user.setUid(uid);
		user.setNickname(login.getNickName());
		user.setAvatar("这是默认头像url");
		user.setSex(0);
		user.setRank(0);
		
		return user;
	}
	/**
	 * 创建一个UserOtherAuths
	 * @param ： user
	 * @return ：UserOtherAuths
	 */
	public UserOtherAuths createUserOtherAuths(int uid){
		
		UserOtherAuths userOtherAuths = new UserOtherAuths();
		userOtherAuths.setUid(uid);
		userOtherAuths.setIdentity_type(login.getIdentity_type());
		userOtherAuths.setIdentifier(login.getIdentifier());
		userOtherAuths.setToken("这是token，我不知道传什么给你！");
		userOtherAuths.setInvalid_time(null);
		
		return userOtherAuths;
	}
	
	
	private UserLoginRecord createUserLoginRecord(int uid) {
		UserLoginRecord userLoginRecord = new UserLoginRecord();
		userLoginRecord.setUid(uid);
		userLoginRecord.setFirst_login_time(new Date().getTime());		//把当前时间当做首次登录时间
		userLoginRecord.setLast_login_time(new Date().getTime());		//因为是第一次登录，把当前时间设置为上次登陆时间
		userLoginRecord.setLast_login_device(login.getLast_login_device());		//因为是首次登录，把当前设备设置为上次登录设备
		userLoginRecord.setLast_login_device_id(login.getLast_login_device_id());	//因为是首次登录，把当前设备id当做上次登录设备id
		return userLoginRecord;
	}
}
