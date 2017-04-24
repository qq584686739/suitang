package com.suitang.interceptors;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.suitang.domain.LoginStatus;
import com.suitang.domain.User;
import com.suitang.service.LoginStatusService;
import com.suitang.utils.ErrorInfo;
import com.suitang.utils.Secret;


public class LoginInterceptor extends AbstractInterceptor {
	
	private JSONObject jsonObject = new JSONObject();
	@Resource
	private LoginStatusService loginStatusService;
	
	private PrintWriter out = null;
	
	//返回值：最终目标的返回值。就是一个结果逻辑视图。对应struts.xml中的result
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//先判断登录标识token(身份标识)是否有效(判断时间是否过期)
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String token = request.getHeader("token");
		
		if(token == null){
			//该用户没有携带token，不放行
			
			jsonObject.put("status", ErrorInfo.REQUESTHEAD_NOT_FIND_TOKEN);
			jsonObject.put("data", "");
			
			out.write(jsonObject.toString());
			out.close();
			
			return "schoolLogin";
		}else{
			//用户携带了token，检查token的合法性
			
			//1、在session中找
			HttpSession session = request.getSession();
			Object t = session.getAttribute(token);
			if(t == null){
				//在session中没找到
				//2、在数据库找
				//根据token找到user
				User user = loginStatusService.getUserByLoginId(token);
				if(user == null){
					//说明token在数据库不存在，不放行
					jsonObject.put("status", ErrorInfo.SQL_NOT_FIND_TOKEN);
					jsonObject.put("data", "");
					out = response.getWriter();
					out.write(jsonObject.toString());
					out.close();
					return "schoolLogin";
				}else{
					//说明token存在,去检查token是否过期
					//找到过期时间，然后检查是否过期
					LoginStatus loginStatus = loginStatusService.getLoginStatusByLoginId(token);
					Timestamp timestamp = loginStatus.getExpiration_time();
					if(timestamp.getTime()<new Date().getTime()){
						//过期，不合法，不放行
						jsonObject.put("status", ErrorInfo.TOKEN_EXPIRE);
						jsonObject.put("data", "");
						out = response.getWriter();
						out.write(jsonObject.toString());
						out.close();
						return "schoolLogin";
					}
				}
			}
		}
		
		LoginStatus loginStatus = loginStatusService.getLoginStatusByLoginId(token);
		loginStatus.setExpiration_time(new Secret().EXPIRATION_TIME);		//在放行之前重新更新过期时间
		loginStatusService.updateLoginStatus(loginStatus);
		
		String rtValue = invocation.invoke();//放行
		return rtValue;
	}

}
