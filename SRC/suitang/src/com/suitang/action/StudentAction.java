package com.suitang.action;


import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.Sign;
import com.suitang.domain.SignHistory;
import com.suitang.service.LoginStatusService;
import com.suitang.service.SignHistoryService;
import com.suitang.service.SignService;
import com.suitang.utils.ErrorInfo;

@SuppressWarnings("serial")
@Controller("studentAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class StudentAction extends BaseAction<SignHistory>{

	private SignHistory signHistory = this.getModel();
	
	@Resource
	private LoginStatusService loginStatusService;
	
	@Resource
	private SignService signService;
	
	@Resource
	private SignHistoryService signHistoryService;
	
	private PrintWriter out = null;
	
	private int error = 0;
	
	private JSONObject jsonObject = new JSONObject();
	
	private Sign signTemp = null;
	
	/**
	 * 学生签到
	 * sign
	 * @Description:
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月24日 上午1:18:24
	 * @Return:void
	 */
	public void sign(){
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(error != 0){
			//说明有错
			jsonObject.put("status", error);
			jsonObject.put("data", "");
			out.write(jsonObject.toString());
			out.close();
			return ;
		}
		
		signHistory.setUid(signTemp.getUid());					//设置uid
		signHistory.setSign_id(signTemp.getSign_id());			//设置sign_token唯一id
		signHistory.setSign_time(System.currentTimeMillis());	//设置签到时间
		if(signHistory.getSign_late() == 1){
			//补签
		}else{
			//不是补签
			signHistory.setLate_reason(null);			//不是补签的话
		}
		try{
			signHistoryService.saveSignHistory(signHistory);
		}catch(Exception e){
			//属于连续扫码签到
			jsonObject.put("status", ErrorInfo.STUDENT_SIGN_MANY_TIMES);
		}
		
		jsonObject.put("status", 1);
		jsonObject.put("data", "");
		out.write(jsonObject.toString());
		out.close();
	}
	
	@Override
	public void validate() {
		//验证sign_token
		signHistory.getSign_id();
		String sign_token = request.getParameter("sign_token");
		if(sign_token != null && sign_token.equals("")){
			signTemp = signService.findSignBySign_token(sign_token);
			if(signTemp == null){
				//数据库不存在该sign_token
				error = ErrorInfo.SIGN_TOKEN_NOT_FIND;
			}else{
				//数据库存在，检查过期时间
				if(signTemp.getInvalid_time()<new Date().getTime()){
					//时间已过期
					error = ErrorInfo.SIGN_TOKEN_EXPIRE;
				}
			}
		}else{
			//用户没有携带sign_token
			error = ErrorInfo.SIGN_TOKEN_ERROR;
		}
		super.validate();
	}
}
