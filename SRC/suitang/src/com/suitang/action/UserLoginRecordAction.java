package com.suitang.action;

import java.io.PrintWriter;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.UserLoginRecord;
import com.suitang.service.UserLoginRecordService;

@SuppressWarnings("serial")
@Controller("userLoginRecordAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class UserLoginRecordAction extends BaseAction<UserLoginRecord>{
	
	/**返回的json格式数据*/
	private JSONObject jsonObject = new JSONObject();
	
	private UserLoginRecord userLoginRecord = this.getModel();
	
	@Resource(name = "userLoginRecordService")
	private UserLoginRecordService userLoginRecordService;
	
	/**返回的数据*/
	private JSONObject jsObject;
	
	/**初始化返回的json数据*/
	public UserLoginRecordAction(){
		jsObject = new JSONObject();
		jsonObject.put("status", 200);
		jsonObject.put("message", "错误的用户名或密码");
		jsonObject.put("date", "");
		
	}
	
	
	public void getSuccessInfo(){
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			System.out.println("这里收到数据！");
			System.out.println(userLoginRecord.getUid());
			
			/**覆盖一下数据*/
			jsonObject.put("status", 200);
			jsonObject.put("message", "正确的用户名和密码");
			
			JSONObject jsonObjectTemp = new JSONObject();
//			userLoginRecordService.getUserLoginRecordById();
			
			jsonObject.put("data", jsonObjectTemp);
			
//			response.getOutputStream().write(jsonObject.toString().getBytes());
//			out.write(jsonObject.toString());
			out.append(jsonObject.toString());
		}catch (Exception e) {
			e.printStackTrace();
//			return "error";
		}
//		return SUCCESS;
	}
	
	public String getErrorInfo(){
		return ERROR;
	}
}
