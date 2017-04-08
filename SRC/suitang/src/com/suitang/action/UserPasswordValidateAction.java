package com.suitang.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.User;
import com.suitang.domain.UserPassword;
import com.suitang.service.UserService;
import com.suitang.utils.ValidateUtil;

@SuppressWarnings("serial")
@Controller("userPasswordValidateAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class UserPasswordValidateAction extends BaseAction<UserPassword>{
	
	/**认证地址*/
	private final String requestUrl = "http://218.197.80.13/jwglxt/xtgl/login_cxCheckYh.html";
	
	/**获得模型驱动*/
	private UserPassword userPassword = this.getModel();
	
	@Resource
	private UserService userService;
	
	/**正确或错误标识*/
	private String validateString = "error";
	
	/**返回的json格式数据*/
	JSONObject jsonObject = new JSONObject();
	
	private PrintWriter out = null;
	/**初始化数据*/
	public UserPasswordValidateAction(){
		
		jsonObject.put("status", 200);
		jsonObject.put("message", "错误的用户名或密码");
		jsonObject.put("date", "");
	}
	
	/**验证方法*/
	public void openAuth(){
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("这里");
		
		if(validateString.equals("success")){		//验证通过
			jsonObject.put("status", 302);
			jsonObject.put("message", "正确的用户名或密码");
			
			//获取user信息封装成json数据
			User user = userService.getUserById(userPassword.getUser());
			
			if(user==null){
				user = new User();
				/**********这里需要md5***************/
				user.setUid((int)Integer.parseInt(userPassword.getUser()));	//暂时以为学号为uid
				user.setNickname(userPassword.getUser());					//以学号为默认的昵称
				user.setAvatar("暂时为空");									//头像路径	，暂时设置为null吧
				user.setSex(0);												//性别：默认为男		0：男		1：女
				user.setRank(0);											//职位：默认为学生	0：学生		1：女
			
				/**因为不存在，所以定义一个临时的，然后再把这个临时的存入数据库*/
				userService.saveUser(user);
			}
			JSONObject jsonObjectTemp = JSONObject.fromObject(user);
			
			jsonObject.put("date", jsonObjectTemp);
			
			out.write(jsonObject.toString());
//			out.append(jsonObject.toString());
		}else{										//验证没通过
			
			out.write(jsonObject.toString());
		}
		
//		return validateString;
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
