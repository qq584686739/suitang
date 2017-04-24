package com.suitang.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.aspectj.org.eclipse.jdt.internal.compiler.parser.TerminalTokens;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.Course;
import com.suitang.domain.LoginStatus;
import com.suitang.domain.Sign;
import com.suitang.domain.User;
import com.suitang.service.CourseService;
import com.suitang.service.LoginStatusService;
import com.suitang.service.SignService;
import com.suitang.utils.ErrorInfo;

@SuppressWarnings("serial")
@Controller("signAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class SignAction extends BaseAction<Sign>{
	/**错误信息 error==0 则无错，等于其他（错误信息代号），则有错*/
	private int error = 0;
	
	@Resource
	private LoginStatusService loginStatusService;
	
	private Sign sign = this.getModel();
	
	@Resource
	private SignService signService;
	
	@Resource
	private CourseService courseService;
	
	private PrintWriter out = null;
	
	private static  JSONObject jsonObject = new JSONObject();
	
	public void signRequest(){
		//编码设置，后期把他用过滤器处理
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(error!=0){
			//出错了
			jsonObject.put("status", error);
			jsonObject.put("data", "");
			out.write(jsonObject.toString());
			out.close();
			return ;
		}
		
		
		String sign_token = sign.getSign_token();
		
		if(sign_token == null){
			//第一次请求（发起签到请求）
			//创建sign_token
			sign_token = UUID.randomUUID().toString();
			
			sign.setSign_time(new Date().getTime());					// 设置签到时间
			sign.setSign_token(sign_token);		 						// 设置签到请求唯一ID
			sign.setInvalid_time(new Date().getTime() + 5*60*1000);		// 设置过期时间(5分钟)

			signService.saveSign(sign);
			
		}else{
			//后续发起请求（签到请求）
			
			//查看该sign_token是否存在
			Sign signTemp = signService.findSignBySign_token(sign_token);
			if(signTemp == null){
				//该sign_token不存在数据库，无效！
				//该sign_token要么过期，要么传的假sign_token
				jsonObject.put("status", ErrorInfo.SIGN_TOKEN_NOT_FIND);
				jsonObject.put("data", "");
				out.write(jsonObject.toString());
				out.close();
				return ;
			}else{
				//sign_token存在数据库
				//检查时间是否失效
				if(signTemp.getInvalid_time()>new Date().getTime()){
					//时间未过期
					sign_token = UUID.randomUUID().toString();
					signTemp.setSign_token(sign_token);		//重新刷新sign_token
					signService.updateSign(signTemp);		//更新
				}else{
					//时间已过期
					jsonObject.put("status", ErrorInfo.SIGN_TOKEN_EXPIRE);
					jsonObject.put("data", "");
					out.write(jsonObject.toString());
					out.close();
					return ;
				}
			}
			//存在，则更换
			
			//不存在，则返回相应的信息（过期）
		}
		
		jsonObject.put("status", 1);
		jsonObject.put("data", sign_token);
		out.write(jsonObject.toString());
		out.close();
	}

	@Override
	public void validate() {
		
		/**获得课程的七个主键*/
		String cid = sign.getCid();
		String cd_id = sign.getCd_id();
		int c_year = sign.getC_year();
		int c_term = sign.getC_term();
		String c_week = sign.getC_week();
		String c_lesson = sign.getC_lesson();
		String c_time = sign.getC_time();
		
		if((cid != null && !cid.equals(""))
				&&(cd_id != null && !cd_id.equals(""))
				&&(c_year!=0)
				&&(c_term==3 || c_term==12)
				&&(c_week!=null && !c_week.equals(""))
				&&(c_lesson!=null && !c_lesson.equals(""))
				&&(c_time!=null && !c_time.equals(""))){
			
		}else{
			//课程七个字段有问题，请检查后重试
			error = ErrorInfo.COURSE_PROBLEM;
			return ;
		}
		
		Course course = courseService.getCourseByPrimarykeys(
				cid, cd_id, c_year, c_term, c_week, c_lesson, c_time);
		if(course == null){
			//数据库不存在该课程
			error = ErrorInfo.NOT_FIND_COURSE;
			return ;
		}
		
		//token未过期，合法
		sign.setUid(
				loginStatusService.getLoginStatusByLoginId(
						request.getHeader("token")).getUid());
		
		super.validate();
	}
}
