package com.suitang.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
	
	/**设置session的存活时间是3小时*/
	private static final int SESSION_TIME = 60 * 60 * 3;

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
	
	/**初始化数据*/
	public LoginAction(){
		jsonObjectLoginInfo.put("status", "error");
		jsonObjectLoginInfo.put("message", "服务器忙");
		jsonObjectLoginInfo.put("data", "");
	}
	
	/**构造方法*/
	public void login(){
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(login_flag){			//允许登录
			HttpSession session = request.getSession();
			
			/**把加密过后的学号当键值， 把0和1当value。0：未登录。1：已登录*/
			session.setAttribute(login.getIdentifier(), "1");	//把秘钥放到session里
			session.setMaxInactiveInterval(SESSION_TIME);
			
			jsonObjectLoginInfo.put("status", "success");
			jsonObjectLoginInfo.put("message", "");
			
			JSONObject jsonObjectLogintemp = JSONObject.fromObject(user);
			
			jsonObjectLoginInfo.put("data", jsonObjectLogintemp);
			
		}else{					//不允许登陆
			jsonObjectLoginInfo.put("status", "error");
			jsonObjectLoginInfo.put("message", "对不起，该设备90分钟之内只允许一个学生登录");
			jsonObjectLoginInfo.put("data", "");
//			return ERROR;
		}
		
		out.write(jsonObjectLoginInfo.toString());
		
//		return SUCCESS;
	}
	
	
	
	
	@Override
	public void validate() {
		
		String identity_type = login.getIdentity_type();		//得到认证类型
		String identifier = login.getIdentifier();				//得到认证id
		
		
		//根据前台传来的认证类型和认证id去得到uid，再根据uid得到user
		user = userOtherAuthsService.getUserByIdentity_typeAndIdentifier(
				identity_type, identifier);
		if(user==null){				//没授权，说明数据库不存在该数据，应该新建数据存储并返回
			user = createNewUser();	//新建一个默认的user
			userService.saveUser(user);
			
			UserOtherAuths userOtherAuths = createUserOtherAuths(user.getUid());
			userOtherAuthsService.saveUserOtherAuths(userOtherAuths);
			
		}else{			//说明数据库存在数据，更新数据，返回user
			user.setAvatar(login.getAvatar());	//更新头像
			user.setNickname(login.getNickname());	//更新昵称
			if(login.getSex()==1){
				user.setSex(1);			//更新性别
			}else{
				user.setSex(0);			//更新性别
			}
			userService.updateUser(user);
		}
		
		//验证是否登陆过
		
		UserLoginRecord userLoginRecord = 
				userLoginRecordService.getUserLoginRecordByLast_login_device_id(
						login.getDevice_id());
		
		if(userLoginRecord == null){		//该设备第一次使用	允许登录
			userLoginRecord = createUserLoginRecord(user.getUid());
			userLoginRecord.setLast_login_time(new Date().getTime());
			userLoginRecordService.saveUserLoginRecord(userLoginRecord);
			
			login_flag = true;	//true：允许登录
			
		}else{			//该设备不是第一次使用
			//再判断这次的用户和上次的用户是否相同
			UserOtherAuths userOtherAuths = 	//获得上次的UserOtherAuths对象
					userLoginRecordService.getUserOtherAuthsByLast_login_device_id(
							login.getDevice_id());
			String last_identifier = userOtherAuths.getIdentifier();			//获得上次的认证id
			String last_identity_type = userOtherAuths.getIdentity_type();		//获得上册认证类型
			if(last_identifier.equals(login.getIdentifier())
					&& last_identity_type.equals(login.getIdentity_type())){	//两个用户一致
				//相同，允许登录
				//说明上次登录的用户和本次登录的用户一致 允许登录，修改信息，返回user
				userLoginRecord.setLast_login_time(new Date().getTime());
				userLoginRecordService.updateUserLoginRecord(userLoginRecord);//更新登录信息
				login_flag = true;	//true：允许登录
			}else{												//两个用户不一致
				//不相同，判断时间满足90分钟
				//说明上次登录的用户和本次登录的用户不一致
				long last_login_time = userLoginRecord.getLast_login_time();		//获得上次登录时间，用来判断是否满足90分钟
				long this_time = new Date().getTime();
				long time_disparity = this_time - last_login_time;//获得时间差
				if(time_disparity>3*60*1000){
					//满足90分钟		允许登录
					userLoginRecord.setLast_login_time(new Date().getTime());
					userLoginRecordService.updateUserLoginRecord(userLoginRecord);
					login_flag = true;	//true：允许登录
				}else {
					//不满足90分钟	不允许登录
					login_flag = false;	//false：不允许登录
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
		if(login.getNickname()==null){
			user.setNickname("默认昵称！");
		}else{
			user.setNickname(login.getNickname());
		}
		
		if(login.getAvatar()==null){
			user.setAvatar("这是默认头像url");
		}else {
			user.setAvatar(login.getAvatar());
		}
//		if(login.getSex()==null){
//			
//		}
		if(login.getSex()==1){
			user.setSex(1);
		}else{
			user.setSex(0);
		}
		
		user.setRank(0);	//没有传入职位，设置默认职位
		
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
		userOtherAuths.setToken("创建一个默认的token，传给你");
		userOtherAuths.setInvalid_time(null);
		
		return userOtherAuths;
	}
	
	
	private UserLoginRecord createUserLoginRecord(int uid) {
		UserLoginRecord userLoginRecord = new UserLoginRecord();
		userLoginRecord.setUid(uid);
		userLoginRecord.setFirst_login_time(new Date().getTime());		//把当前时间当做首次登录时间
		userLoginRecord.setLast_login_time(new Date().getTime());		//因为是第一次登录，把当前时间设置为上次登陆时间
		userLoginRecord.setLast_login_device(login.getDevice_name());		//因为是首次登录，把当前设备设置为上次登录设备
		userLoginRecord.setLast_login_device_id(login.getDevice_id());	//因为是首次登录，把当前设备id当做上次登录设备id
		return userLoginRecord;
	}
}
