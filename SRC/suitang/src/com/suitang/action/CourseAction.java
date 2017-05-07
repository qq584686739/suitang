package com.suitang.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.Course;
import com.suitang.domain.CourseLogin;
import com.suitang.domain.LoginStatus;
import com.suitang.domain.User;
import com.suitang.service.CourseService;
import com.suitang.service.LoginStatusService;
import com.suitang.service.UserOtherAuthsService;
import com.suitang.service.UserService;
import com.suitang.utils.DESUtils;
//import com.suitang.utils.MD5Util;
import com.suitang.utils.HttpUtils;
import com.suitang.utils.Secret;
import com.suitang.utils.HttpUtils.HttpResult;

@SuppressWarnings("serial")
@Controller("courseAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class CourseAction extends BaseAction<CourseLogin>{
	private static String requestUrl = "http://218.197.80.13/jwglxt/xtgl/login_login.html";
	private static String requestUrl2 = "http://218.197.80.13/jwglxt/kbcx/xskbcx_cxXsKb.html";
	
	/**获得模型驱动*/
	private CourseLogin courseLogin = this.getModel();
	
	/**返回的json格式数据*/
	JSONObject jsonObject = new JSONObject();
	
	@Resource
	private CourseService couoCourseService;
	
	@Resource
	private UserOtherAuthsService userOtherAuthsService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private LoginStatusService loginStatusService;
	
	private PrintWriter out = null;
	
	/**判断是否已经登录标识0未登录  1在session  2在数据库  3在数据库过期了  4没有在数据库*/
	private int signin = 0;
	
	public void get(){
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Set<Course> courses = getCourse();
		
		if(courses == null || courses.size()<=0){
			jsonObject.put("status", "success");
			jsonObject.put("message", "");
			jsonObject.put("data", "");
			signin = 5;		//等于5直接输出
		}
		
		if(signin == 1){
			//在session中
			jsonObject.put("status", "success");
			jsonObject.put("message", "");
			
			JSONObject jsonObjectTemp = new JSONObject();
			
			Iterator<Course> iterator = courses.iterator();
			int i = 1;
			while (iterator.hasNext()) {
				Course course = (Course) iterator.next();
//				System.out.println(course.toString());
				jsonObjectTemp.put(i++, course);
			}
			
			jsonObject.put("data", jsonObjectTemp);
//			System.out.println("是否执行成功了");
			
		}else if(signin == 2){
			//在数据库中
			jsonObject.put("status", "success");
			jsonObject.put("message", "");
			jsonObject.put("data", JSONObject.fromObject(courses));
			
			//更新数据库的状态
			LoginStatus loginStatus = loginStatusService.getLoginStatusByLoginId(courseLogin.getToken());
			loginStatus.setExpiration_time(new Secret().EXPIRATION_TIME);	//重新设置过期时间
			
			
			//更新session状态
			HttpSession session = request.getSession();
			session.setAttribute(loginStatus.getLogin_id(), "1");
			session.setMaxInactiveInterval(Secret.SESSION_TIME);	//设置session的默认时间
			
			
		}else if(signin == 3){
			//在数据库中过期了
			jsonObject.put("status", "error");
			jsonObject.put("message", "对不起，您的登录已过期，请重新登陆！");
			jsonObject.put("data", "");
		}else if(signin == 4){
			//没有在数据库
			jsonObject.put("status", "error");
			jsonObject.put("message", "对不起，请先登录再进行操作！");
			jsonObject.put("data", "");
		}else {
			//服务器忙
			jsonObject.put("status", "error");
			jsonObject.put("message", "对不起，服务器忙！");
			jsonObject.put("data", "");
		}
		try {
			out = response.getWriter();
			out.write(jsonObject.toString());
			return ;
		} catch (IOException e) {
//			System.out.println("返回有误！");
			e.printStackTrace();
		}
	}
	
	private Set<Course> getCourse() {
		JSONArray cb = requestCourse();		//请求课表
		/**根据学号获得user*/
		User user = userOtherAuthsService.getUserByIdentifier(DESUtils.encrypt(courseLogin.getId(), Secret.KEY));
		
		Set<Course> courses = user.getCourses();
		int cbSize = cb.size();
		
		for(int i=0;i<cbSize;i++){
			JSONObject jsonObject = (JSONObject)cb.get(i);
			
			String cd_id = (String) jsonObject.get("cd_id");				//获得教室id
			String cd_mc = (String) jsonObject.get("cdmc");					//获得教室名称
			String c_lesson = (String) jsonObject.get("jc");				//获得上课节数
			String cid = (String) jsonObject.get("jxbmc");					//获得课程id
			String c_name = (String) jsonObject.get("kcmc");				//获得课程名字
			String c_teacher = (String) jsonObject.get("xm");				//获得任课老师姓名
			int c_year = Integer.valueOf((String)jsonObject.get("xnm"));					//获得学年
			String c_time = (String) jsonObject.get("xqj");					//获得星期几
			int c_term = Integer.valueOf((String)jsonObject.get("xqm"));					//获得学期
			String c_week = (String) jsonObject.get("zcd");					//获得上课周数
			
			Course course = new Course();
			course.setCd_id(cd_id);
			course.setCd_mc(cd_mc);
			course.setC_lesson(c_lesson);
			course.setCid(cid);
			course.setC_name(c_name);
			course.setC_teacher(c_teacher);
			course.setC_year(c_year);
			course.setC_time(c_time);
			course.setC_term(c_term);
			
			int[] cWeek = format(c_week);		//格式化上课周数
			
			StringBuffer sb = new StringBuffer();
			int j = i;
			while(cWeek[j]!=0){
				sb.append(cWeek[j++] + ",");
			}
			sb.deleteCharAt(sb.length()-1);			//去掉最后面的逗号
			
			course.setC_week(sb.toString());
			
			try {
				courses.add(course);
			} catch (Exception e) {
				System.out.println("保存失败");
			}
			
			//保存课程表
//			couoCourseService.saveCourse(course);
			
			//保存学生课程schedule表
			
			//返回返回课表信息
			
		}
		userService.updateUser(user);
		return user.getCourses();
	}
	
	private JSONArray requestCourse() {
		HttpUtils httpUtils = new HttpUtils(requestUrl);
		
		/**设置请求方式*/
		httpUtils.setRequestMethod("POST");
		
		/**设置请求参数*/
		Map<String, String> requestProperty = new HashMap<String, String>();
		requestProperty.put("yhm", courseLogin.getId());
		requestProperty.put("mm", courseLogin.getPsw());
		requestProperty.put("yzm", "");
		httpUtils.setRequestProperty(requestProperty);
		
		/**第一次请求得到cookie*/
		HttpResult httpResult = httpUtils.request();
		
		/**得到响应头，拿到cookie*/
		Map<String,List<String>> map = httpResult.getResponseHead();
		List<String> list = map.get("Set-Cookie");
		String jsessionid = list.get(0);

		/**拿到cookie*/
		String cookie = jsessionid.substring(0, jsessionid.indexOf(";"));
		
		/**修改第二次请求的url*/
		httpUtils.setRequestUrl(requestUrl2);
		
		/**设置cookie,设置响应头*/
		Map<String, String> headProperty = new HashMap<String, String>();
		headProperty.put("Cookie", cookie);
		httpUtils.setHeadProperty(headProperty);
		
		/**设置请求参数，学年度，学期*/
		Map<String, String> requestProperty2 = new HashMap<String, String>();
//		requestProperty2.put("gnmkdmKey", "N2151");
		requestProperty2.put("sessionUserKey", courseLogin.getId());
		requestProperty2.put("xnm", courseLogin.getXnm());
		requestProperty2.put("xqm", courseLogin.getXqm());
		httpUtils.setRequestProperty(requestProperty2);
		
		/**第二次请求*/
		HttpResult httpResult2 = httpUtils.request();
		
		/**拿到第二次请求的响应正文（课表信息json格式）*/
//		System.out.println(httpResult2.getResponseBodyString("utf-8"));
		
		String jsonString = httpResult2.getResponseBodyString("utf-8");
		
		JSONObject json = JSONObject.fromObject(jsonString);
		
		/**获得所有的课程信息*/
		return (JSONArray)json.get("kbList");
		
	}

	private int[] format(String c_week) {
		int[] result = new int[1024];
		int c = 0;
		
		int parity = 0;		//  0代表无单双。1：单，2双  3：只有一周
		
		String[] s = c_week.split(",");
		for(int i=0;i<s.length;i++){
			String s2 = s[i];
			//用b的长度来判断是否含有单双周  ， 如果含有单双周长度>=7,否则没有单双周之分
			if(s2.length()>=7){
				//说明含有单双周的
				//获得单周还是双周
				String danOrShuang = s2.substring(s2.indexOf("(")+1, s2.indexOf(")"));
				if(danOrShuang.equals("单")){
					//单周
					parity=1;
					
				}else if(danOrShuang.equals("双")){
					//双周
					parity = 2;
				}
				
			}else if(s2.length()<=3){
				//只有一周，直接处理掉就continue
				parity = 3;
				int x = Integer.valueOf(s2.substring(0, s2.indexOf("周")));
				result[c++] = x;
				continue;
			}else{
				//不含有单双周之分
				parity = 0;
				
			}
			int nub1 = Integer.valueOf(s2.substring(0, s2.indexOf("-")));
			int nub2 = Integer.valueOf(s2.substring(s2.indexOf("-")+1, s2.indexOf("周")));
			if(parity==0){
				//无单双之分
				while(nub1<=nub2){
					result[c++] = nub1;
					nub1++;
				}
				continue;
			}else if(parity == 1){
				//单周
				while(nub1<=nub2 && nub1%2!=0){
					result[c++] = nub1;
					nub1+=2;
				}
				continue;
			}else if(parity == 2){
				//双周
				while(nub1<=nub2 && nub1%2==0){
					result[c++] = nub1;
					nub1+=2;
				}
				continue;
			}
		}
		return result;
	}

	@Override
	public void validate() {
		//首先判断是否登录
		HttpSession session = request.getSession();
		String token = courseLogin.getToken();
		int log = Integer.valueOf((String) session.getAttribute(token));
		
		if(log==1){
			//已经登录 在session中
			signin=1;
		}else{
			//去数据库查找数据
			LoginStatus loginStatus = loginStatusService.getLoginStatusByLoginId(courseLogin.getToken());
			if(loginStatus!=null){
				if(loginStatus.getExpiration_time().getTime()>new Date().getTime()){
					//暂未过期
					signin = 2;
				}else if(loginStatus.getExpiration_time().getTime()<new Date().getTime()){
					//过期了
					signin = 3;
				}
			}else{
				//数据库不存在该数据
				signin = 4;
			}
		}
	}
}
