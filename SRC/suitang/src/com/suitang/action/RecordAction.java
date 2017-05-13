package com.suitang.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.suitang.domain.Course;
import com.suitang.domain.LoginStatus;
import com.suitang.domain.Sign;
import com.suitang.domain.SignHistory;
import com.suitang.domain.User;
import com.suitang.service.CourseService;
import com.suitang.service.LoginStatusService;
import com.suitang.service.SignHistoryService;
import com.suitang.service.SignService;
import com.suitang.service.UserService;
import com.suitang.utils.ErrorInfo;

@SuppressWarnings("serial")
@Controller("recordAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class RecordAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**flag 1:找所有的签到请求，2：找某一条签到请求的所有学生信息,3:查找这个学生的所有签到记录*/
	private int flag = 1;
	
	private int sign_id = -1;
	
	private int error = 0;
	
	private PrintWriter out;
	
	private JSONObject jsonObject = new JSONObject();
	
	@Resource
	private SignHistoryService signHistoryService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private LoginStatusService loginStatusService;
	
	@Resource
	private SignService signService;
	
	@Resource
	private CourseService courseService;
	
	private User user;

	public void validate() {
		
		//判断是学生还是老师
		
		if(request.getMethod().equals("GET")){
			error = ErrorInfo.GET_RECORD;
			return ;
		}
		
		
		String sign_id_String = request.getParameter("sign_id");
		
		if(sign_id_String != null){
			//说明是查看sign_id所有的签到的学生
			flag = 2;
			try {
				sign_id = Integer.valueOf(sign_id_String);
			} catch (Exception e) {
				//查询签到记录的时候出错，sign_id不是数字
				error = ErrorInfo.SIGN_ID_NOT_INTEGER;
			}
			return ;
		}
		
		String token = request.getHeader("token");
		user = loginStatusService.getUserByLoginId(token);
		if(user.getRank().intValue() == 0){
			//学生
			//是学生的话就只查签到记录
			flag = 3;
		}else {
			//老师
			//说明是老师查看所有的签到请求记录
			flag = 1;
		}
		
		super.validate();
	}
	
	/**
	 * 这个接口既可以查寻所有的签到记录，也可以查寻某一条签到记录的所有已签到的学生信息。
	 * findSign
	 * @Description:
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月28日 上午1:17:21
	 * @Return:void
	 */
	
	public void findSign(){
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(error != 0){
			jsonObject.put("status", error);
			jsonObject.put("data", "");
			out.write(jsonObject.toString());
			out.close();
			return ;
		}
		
		if(flag == 1){
			//说明是老师查看所有的签到请求记录
			findAllSignRequest();
		}else if(flag == 2){
			//说明老师或学生查看某一条签到记录的所有学生信息
			findSignHistorysBySign_id(sign_id);
		}else if(flag == 3){
			//查找这个学生所有的签到记录
			findStudentSignHistorys();
		}
		
		out.write(jsonObject.toString());
		out.close();
	}
	
	/**
	 * 查找这个学生所有的签到记录
	 * findStudentSignHistorys
	 * @Description:
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月29日 下午2:15:01
	 * @Return:void
	 */
	private void findStudentSignHistorys() {
		SignHistory[] signHistorys= signHistoryService.getSignHistorysByUid(user.getUid());
		
		if(signHistorys == null){
			//这个学生没有任何签到记录
			jsonObject.put("status", 1);
			jsonObject.put("message", "请求成功，这个学生没有任何的签到记录，赶快去扫码签到吧");
			jsonObject.put("data", "");
			return ;
		}
		
		Set<JSONObject> set = new HashSet<JSONObject>();
		for(int i = 0 ; i < signHistorys.length ; i++){
			JSONObject jsonObjectTemp = new JSONObject();
			
			
			SignHistory signHistory = signHistorys[i];
			
			int sign_id = signHistory.getSign_id();
			Sign sign = signService.findSignBySign_id(sign_id);
			
			Long sign_time = signHistory.getSign_time();		//获取签到时间
			Integer sign_late = signHistory.getSign_late();			//获取是否补签
			String lage_reason = signHistory.getLate_reason();	//获得补签原因
			Integer lage_state = signHistory.getLate_state();		//获取审核状态
				
			String cid = sign.getCid();
			String cd_id = sign.getCd_id();
			int c_year = sign.getC_year();
			int c_term = sign.getC_term();
			String c_week = sign.getC_week();
			String c_lesson = sign.getC_lesson();
			String c_time = sign.getC_time();
			Course course = courseService.getCourseByPrimarykeys(cid, cd_id, c_year, c_term, c_week, c_lesson, c_time);
			
			jsonObjectTemp.put("sign_id", sign_id);
			jsonObjectTemp.put("course", course);
			jsonObjectTemp.put("sign_time", new SimpleDateFormat("yyyy-MM-dd日 HH:mm:ss").format(new Date(sign_time)));
			
			if(sign_late != null){
				jsonObjectTemp.put("sign_late", sign_late);
				jsonObjectTemp.put("lage_reason", lage_reason);
				jsonObjectTemp.put("lage_state", lage_state);
			}
			
			
			set.add(jsonObjectTemp);
		}
		jsonObject.put("status", 1);
		jsonObject.put("data", set);
	}

	private void findAllSignRequest() {
		//说明是老师查看所有的签到请求记录
		String token = request.getHeader("token");
		LoginStatus loginStatus = loginStatusService.getLoginStatusByLoginId(token);
		int uid = loginStatus.getUid();
		Sign[] signs = signService.findSignsByUid(uid);
		
		jsonObject.put("status", 1);
		if(signs == null){
			//说明该老师还没有发起任何签到
			jsonObject.put("data", "");
			jsonObject.put("message", "该老师还没有发起任何签到请求，赶紧去课堂上发起签到吧！");
		}else{
			Set<JSONObject> set = new HashSet<JSONObject>();
			for(Sign sign : signs){
				
				JSONObject jsonObjectTemp = new JSONObject();
				String cid = sign.getCid();
				String cd_id = sign.getCd_id();
				int c_year = sign.getC_year();
				int c_term = sign.getC_term();
				String c_week = sign.getC_week();
				String c_lesson = sign.getC_lesson();
				String c_time = sign.getC_time();
				Course course = courseService.getCourseByPrimarykeys(cid, cd_id, c_year, c_term, c_week, c_lesson, c_time);
				
//				jsonObjectTemp.put("sign_id", sign_id);
				jsonObjectTemp.put("sign_id", sign.getSign_id());
				jsonObjectTemp.put("course", course);
				jsonObjectTemp.put("sign_time", new SimpleDateFormat("yyyy-MM-dd日 HH:mm:ss").format(new Date(sign.getSign_time())));
//				jsonObjectTemp.put("sign_late", sign_late);
//				jsonObjectTemp.put("lage_reason", lage_reason);
//				jsonObjectTemp.put("lage_state", lage_state);
				
				set.add(jsonObjectTemp);
				
				//
//				jsonObjectTemp.put("cid", sign);
//				jsonObjectTemp.put("c_name", sign);
//				jsonObjectTemp.put("cd_id", sign);
//				jsonObjectTemp.put("cd_mc", sign);
//				jsonObjectTemp.put("c_teacher", sign);
//				jsonObjectTemp.put("c_year", sign);
//				jsonObjectTemp.put("c_term", sign);
//				jsonObjectTemp.put("c_week", sign);
//				jsonObjectTemp.put("c_lesson", sign);
//				jsonObjectTemp.put("c_time", sign);
//				
//				jsonObjectTemp.put("sign_id", sign.getSign_id());
//				jsonObjectTemp.put("uid", sign.getUid());
//				jsonObjectTemp.put("sign_time", new SimpleDateFormat("yyyy-MM-dd日 HH:mm:ss").format(new Date(sign.getSign_time())));
//				jsonObjectTemp.put("sign_token", sign.getSign_token());
//				set.add(jsonObjectTemp);
			}
			jsonObject.put("data", set);
		}
	}

	private void findSignHistorysBySign_id(int sign_id) {
		//说明老师查看某一条签到记录的所有学生信息
		SignHistory[] signHistorys = signHistoryService.getSignHistorysBySign_id(sign_id);
		if(signHistorys == null){
			//该次签到请求，没有一个学生扫码签到
			jsonObject.put("status", 1);
//			jsonObject.put("message", "请求成功，老师老师查看本次签到的签到记录，但是没有记录");
			jsonObject.put("message", "请求成功，该课堂暂无学生签到记录，赶紧让同学们去签到吧！");
			jsonObject.put("data", "");
			return ;
		}
		
		Set<User> set = new HashSet<User>();
		for(int i = 0 ; i < signHistorys.length ; i++){
			SignHistory signHistory = signHistorys[i];
			int uid = signHistory.getUid();
			User userTemp = userService.getUserById(uid);
			userTemp.setCourses(null);
			set.add(userTemp);
		}
		jsonObject.put("status", 1);
		jsonObject.put("data", set);
	}


	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
