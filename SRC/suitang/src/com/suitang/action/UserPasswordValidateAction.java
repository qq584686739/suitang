package com.suitang.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.UserPassword;
import com.suitang.utils.DESUtils;
//import com.suitang.utils.MD5Util;
import com.suitang.utils.Secret;
import com.suitang.utils.ValidateUtil;


@SuppressWarnings("serial")
@Controller("userPasswordValidateAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class UserPasswordValidateAction extends BaseAction<UserPassword>{
	
	/**认证地址*/
	private final String requestUrl = "http://218.197.80.13/jwglxt/xtgl/login_cxCheckYh.html";
	
	/**获得模型驱动*/
	private UserPassword userPassword = this.getModel();
	
	/**正确或错误标识*/
	private String validateString = "error";
	
	/**返回的json格式数据*/
	JSONObject jsonObject = new JSONObject();
	
	/**学校验证返回的json格式数据*/
	JSONObject jsonObjectInfo = null;
	
	private PrintWriter out = null;
	
	/**验证方法*/
	public void openAuth(){
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(jsonObjectInfo == null){
			//说明服务器异常,初始化数据
			jsonObjectInfo = new JSONObject();
			jsonObjectInfo.put("status", "error");
			jsonObjectInfo.put("message", "服务器异常,请联系管理员！");
			jsonObjectInfo.put("data", "");
			
			out.write(jsonObjectInfo.toString());
			return ;
		}
		
		if(((String)jsonObjectInfo.get("status")).equals("success")){		//验证通过
			jsonObject.put("status", "success");//覆盖初始化数据
			jsonObject.put("message", "");		//覆盖初始化数据
			
			JSONObject jsonObjectTemp = new JSONObject();
			
//			jsonObjectTemp.put("authId", MD5Util.md5(userPassword.getUser()));	//认证id	md5加密
			jsonObjectTemp.put("authId", DESUtils.encrypt(userPassword.getUser(),Secret.KEY));	//认证DES加密
			jsonObjectTemp.put("nickname", userPassword.getUser());				//昵称，默认学号
			jsonObjectTemp.put("avatar", "");									//头像,暂时为空
			jsonObjectTemp.put("sex", 0);										//性别
			
			jsonObject.put("data", jsonObjectTemp);
		}else{										//验证没通过error
			jsonObject.put("status", "error");
			jsonObject.put("message", (String)jsonObjectInfo.get("message"));		//覆盖初始化数据
			jsonObject.put("data", "");
		}
		out.write(jsonObject.toString());
		
//		return validateString;
	}
	
	/**验证学号密码是否正确*/
	@Override
	public void validate() {
		
		Map<String, String> requestProperty = new HashMap<String, String>();
		requestProperty.put("yhm", userPassword.getUser());
		requestProperty.put("mm", userPassword.getPassword());
		
		/**得到验证信息*/
		jsonObjectInfo = ValidateUtil.validate(requestUrl, null, requestProperty);
		
		super.validate();
	}
}
