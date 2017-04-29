package com.suitang.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.suitang.domain.Course;
import com.suitang.domain.LoginStatus;
import com.suitang.domain.SchoolLogin;
import com.suitang.domain.User;
import com.suitang.domain.UserLocalAuths;
import com.suitang.domain.UserLoginRecord;
import com.suitang.service.CourseService;
import com.suitang.service.LoginStatusService;
import com.suitang.service.UserLocalAuthService;
import com.suitang.service.UserLoginRecordService;
import com.suitang.service.UserService;
import com.suitang.utils.ErrorInfo;
import com.suitang.utils.HttpUtils;
import com.suitang.utils.ValidateUtil;
import com.suitang.utils.HttpUtils.HttpResult;
import com.suitang.utils.Secret;

@SuppressWarnings("serial")
@Controller("schoolLoginAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class SchoolLoginAction extends BaseAction<SchoolLogin>{
	/**课表请求URL*/
	private static String requestValidateUrl = "http://218.197.80.13/jwglxt/xtgl/login_cxCheckYh.html";
	private static String requestUrl = "http://218.197.80.13/jwglxt/xtgl/login_login.html";
	private static String requestUrl2 = "http://218.197.80.13/jwglxt/kbcx/xskbcx_cxXsKb.html";
	
	
	/**获得模型驱动*/
	private SchoolLogin schoolLogin = this.getModel();
	
	@Resource
	private UserLoginRecordService userLoginRecordService;
	
	private PrintWriter out = null;
	
	@Resource
	private UserService userService;
	
	@Resource
	private LoginStatusService loginStatusService;
	
	@Resource
	private UserLocalAuthService userLocalAuthService;
	
	@Resource
	private CourseService courseService;
	
	//如果是0则没有错，1：用户名为空，2：密码不能为空 ,
	// 6：设备ID不能为空 7:用户名密码错误
	private int errorInfo = 0;		
	
	/**判断是否出错，0：无错，1：出错*/
	private int error = 0;
	
	/**
	 * 登录标识
	 * false:不允许登录
	 * true：允许登录
	 */
	private boolean login_flag = false;
	
	/**需要返回的user*/
	private User user;
	
	private User copyUser;
	
	/**登录返回的json格式数据*/
	private static  JSONObject jsonObject = new JSONObject();
	
	/**如果数据库存在该用户的登录记录，则loginStatusTemp不等于null，去更新数据库的登录状态
	 * 如果数据库不存在该用户的登录记录，则loginStatusTemp等于null，去创建数据库的登录状态
	 * */
	private LoginStatus loginStatusTemp = null;
	
	/**初始化数据*/
	static{
		jsonObject.put("status", ErrorInfo.NOT_FIND);
		jsonObject.put("data", "");
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
		
		
		if(error == 1){
			jsonObject.put("status", errorInfo);
			jsonObject.put("data", "");
			out.write(jsonObject.toString());
			out.close();
			return ;
		}
		
		
		
		if(login_flag){			//允许登录
			
			String uuidString = null;
			if(loginStatusTemp == null){
				//去保存状态
				/**保存数据库的登录状态*/
				loginStatusTemp = new LoginStatus();
				loginStatusTemp.setUid(user.getUid());							//对应的userid
				uuidString = UUID.randomUUID().toString();					//生成uuid
				loginStatusTemp.setLogin_id(uuidString);						//设置uuid
				loginStatusTemp.setExpiration_time(new Secret().EXPIRATION_TIME);		//设置过期时间
				loginStatusService.saveLoginStatus(loginStatusTemp);
			}else{
				//去更新状态
				loginStatusTemp.setExpiration_time(new Secret().EXPIRATION_TIME);		//设置过期时间
				uuidString = loginStatusTemp.getLogin_id();
				loginStatusService.updateLoginStatus(loginStatusTemp);
			}
			
			
			/**获得session*/
			HttpSession session = request.getSession();
			/**把加密过后的学号当键值， 把0和1当value。0：未登录。1：已登录*/
			/**login.getIdentifier()是学号进行加密后的数据*/
			session.setAttribute(uuidString, "1");	//把秘钥放到session里
			session.setMaxInactiveInterval(Secret.SESSION_TIME);	//设置session的默认时间
			
			System.out.println("uuidString = " + uuidString);
			
			//获得课程
//			Set<Course> courses = getCourse();
			getCourse();
			
//			JSONObject jsonObjectTemp = new JSONObject();
//			Iterator<Course> iterator = courses.iterator();
//			int i = 1;
//			while (iterator.hasNext()) {
//				Course course = (Course) iterator.next();
//				System.out.println(course.toString());
//				jsonObjectTemp.put(i++, course);
//			}
//			jsonObjectTemp.put("user", user);		//
			
//			jsonObject.put("data", jsonObjectTemp);
			jsonObject.put("data", copyUser);
			
			jsonObject.put("status", 1);
			jsonObject.put("message", "");
			response.setHeader("token", uuidString);			//把token放到响应头里
//			jsonObject.put("token", uuidString);
			
		}else{					//不允许登陆
			jsonObject.put("status", ErrorInfo.LOGIN_CHANGE);
			jsonObject.put("data", "");
		}
		
		out.write(jsonObject.toString());
		out.close();
		
	}
	
	
	@Override
	public void validate() {
		
		if(schoolLogin.getSchool_no() == null || schoolLogin.getSchool_no().equals("")){
			errorInfo = ErrorInfo.LOGIN_NOT_FIND_SCHOOL_NO;
			error = 1;
			return ;
		}
		if(schoolLogin.getPassword() == null || schoolLogin.getPassword().equals("")){
			errorInfo = ErrorInfo.LOGIN_NOT_FIND_PASSWORD;
			error = 1;
			return ;
		}
		if(schoolLogin.getXnm()==null || schoolLogin.getXnm().equals("")){
			//学年为空，根据当前时间设置学年
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String yearString = sdf.format(date);
			sdf = new SimpleDateFormat("MM");
			String monthString = sdf.format(date);
			int year = Integer.valueOf(yearString);
			int month = Integer.valueOf(monthString);
			
			if( month==0 || (month>=7 && month<=11)){
				//算下半年
			}
			if(month>=1 && month<=6){
				//上半年，年份得-1
				year-=1;
			}
			schoolLogin.setXnm(String.valueOf(year));
		}
		if(schoolLogin.getXqm()==null || schoolLogin.getXqm().equals("")){
			//学期为空，根据当前时间设置学期
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM");
			String monthString = sdf.format(date);
			int month = Integer.valueOf(monthString);
			
			if( month==0 || (month>=7 && month<=11)){
				//算下半年
				schoolLogin.setXqm("3");
			}
			if(month>=1 && month<=6){
				//上半年，年份得-1
				schoolLogin.setXqm("12");
			}
		}
		if(schoolLogin.getDevice_id()==null || schoolLogin.getDevice_id().equals("")){
			errorInfo = ErrorInfo.LOGIN_NOT_FIND_DEVICE_ID;
			error = 1;
			return ;
		}
		
		//验证用户名和密码
		int loginFlag = loginValidate(schoolLogin.getSchool_no(),schoolLogin.getPassword());
		if(loginFlag == 0){
			errorInfo = ErrorInfo.LOGIN_SCHOOL_NO_OR_PASSWORD_ERROR;
			error = 1;
			return ;
		}
		
		//根据前台传来的设备名称和设备ID去得到uid，再根据uid得到user
		
		user = userLocalAuthService.getUserBySchool_no(schoolLogin.getSchool_no());
//		user = userLoginRecordService.getUserByLast_login_device_id(schoolLogin.getDevice_id());
		
		if(user==null){				//没授权，说明数据库不存在该数据，应该新建数据存储并返回
			user = createNewUser();	//新建一个默认的user
			userService.saveUser(user);
			
			UserLocalAuths userLocalAuths = createNewUserLocalAuths(user.getUid(), user.getRank());
			userLocalAuthService.saveUserLocalAuthS(userLocalAuths);
		}else{			
			//数据库原来存在该用户
			//这里应该加一些代码：根据学号找到uid，根据user的uid，再根据user的uid找到loginstatus的过期时间，如果过期，且登录成功，更新原来的过期时间，如果没登录成功，不做任何操作
																									//如果没过期，登陆成功，也要更新数据库和session的时间								
			loginStatusTemp = userLocalAuthService.getLoginStatusBySchool_no(schoolLogin.getSchool_no());
//			if(loginStatusTemp!=null){
////				if(loginStatusTemp.getExpiration_time().getTime()>new Date().getTime()){
////					//说明还没过期
////				}else{
////					//说明过期，如果登陆成功则是去更新数据
////				}
//				//登陆成功之后，不管你是过期还是不过期，我都要去更新数据库的登录状态
//				updateFlag = 1;		//等于1去更新，等于0去保存
//			}
		}
		
		//验证该设备以前是否登陆过
		UserLoginRecord userLoginRecord = 
				userLoginRecordService.getUserLoginRecordByLast_login_device_id(
						schoolLogin.getDevice_id());
		
		if(userLoginRecord == null){		//该设备第一次使用	允许登录
			userLoginRecord = createNewUserLoginRecord(user.getUid());
			userLoginRecordService.saveUserLoginRecord(userLoginRecord);
			
//			userLoginRecord = createUserLoginRecord(user.getUid());
//			userLoginRecord.setLast_login_time(new Date().getTime());
//			userLoginRecordService.saveUserLoginRecord(userLoginRecord);
			
			login_flag = true;	//true：允许登录
			
		}else{			
			//该设备不是第一次使用
			//再判断这次的用户和上次的用户是否相同
			int last_uid = userLoginRecord.getUid();	//获得该设备上次的uid
			int this_uid = user.getUid();				//获得本次的uid
			
			
//			UserOtherAuths userOtherAuths = 	//获得上次的UserOtherAuths对象
//					userLoginRecordService.getUserOtherAuthsByLast_login_device_id(
//							login.getDevice_id());
//			String last_identifier = userOtherAuths.getIdentifier();			//获得上次的认证id
//			String last_identity_type = userOtherAuths.getIdentity_type();		//获得上册认证类型
			
			
			if(last_uid == this_uid){	
				//两个用户一致
				//说明上次登录的用户和本次登录的用户一致 允许登录，修改信息，返回user
				userLoginRecord.setLast_login_time(new Date().getTime());
				userLoginRecordService.updateUserLoginRecord(userLoginRecord);//更新登录信息
				login_flag = true;	//true：允许登录
			}else{												
				//两个用户不一致
				//不相同，判断时间满足90分钟
				//说明上次登录的用户和本次登录的用户不一致
				long last_login_time = userLoginRecord.getLast_login_time();		//获得上次登录时间，用来判断是否满足90分钟
				long this_time = new Date().getTime();
				long time_disparity = (this_time - last_login_time)/1000;//获得时间差
				if(time_disparity>Secret.SESSION_TIME){
					//满足90分钟		允许登录
					userLoginRecord.setUid(user.getUid());
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



	private UserLocalAuths createNewUserLocalAuths(int uid, int rank) {
		UserLocalAuths userLocalAuths = new UserLocalAuths();
		userLocalAuths.setUid(uid);
		userLocalAuths.setSchool_no(schoolLogin.getSchool_no());
		userLocalAuths.setPassword(schoolLogin.getPassword());
		userLocalAuths.setRand(rank);
		//手机和邮箱默认为空
		userLocalAuths.setPhone_no("");
		userLocalAuths.setEmail("");
		return userLocalAuths;
	}


	private UserLoginRecord createNewUserLoginRecord(int uid) {
		UserLoginRecord userLoginRecord = new UserLoginRecord();
		userLoginRecord.setUid(uid);
		userLoginRecord.setFirst_login_time(new Date().getTime());		//把当前时间当做首次登录时间
		userLoginRecord.setLast_login_time(new Date().getTime());		//因为是第一次登录，把当前时间设置为上次登陆时间
		userLoginRecord.setLast_login_device(schoolLogin.getDevice_name());		//因为是首次登录，把当前设备设置为上次登录设备
		userLoginRecord.setLast_login_device_id(schoolLogin.getDevice_id());	//因为是首次登录，把当前设备id当做上次登录设备id
		return userLoginRecord;
	}


	/**
	 * 创建一个默认的User用户
	 */
	private User createNewUser(){
		User user = new User();
//		user.setUid(uid);
		
		user.setNickname(schoolLogin.getSchool_no());		//以学号为默认昵称
		
		user.setAvatar("img/user.png");			//设置一个默认头像url
		
		user.setSex(0);								//设置默认的性别
		
		if(schoolLogin.getSchool_no().length() == 8){
			//说明是学生
			user.setRank(0);
		}else{
			//说明是老师
			user.setRank(1);
		}
		
		
		return user;
	}
	
	
	
	
	
	
	
	
	
	/**下面是获得课表*/
	
	
	
	
	/**
	 * 一个原来的Courses，一个newCourses，把newCourses设置到user里，返回原来的Courses，为什么返回原来的courses，在输出数据之后要把原来的课程放回到schedule表中，这样做是为了保证用户得到的是一个学期的课表
	 * getCourse
	 * @Description:
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月17日 下午7:22:09
	 * @return
	 * @Return:Set<Course>
	 */
	
	private Set<Course> getCourse() {
		JSONArray cb = requestCourse();		//请求课表
		
//		Set<Course> pldCourses = user.getCourses();
		Set<Course> userCourses = user.getCourses();
		
		int isOverride = 0;	
		
		/**这个用来放数据库没有的课程*/
		Set<Course> newCourses = new HashSet<Course>();
		
		
		for(int i=0;i<cb.size();i++){
			JSONObject jsonObject = (JSONObject)cb.get(i);
			
			String cd_id = (String) jsonObject.get("cd_id");				//获得教室id
			String cd_mc = (String) jsonObject.get("cdmc");					//获得教室名称
			String c_lesson = (String) jsonObject.get("jc");				//获得上课节数
			String cid = (String) jsonObject.get("jxbmc");					//获得课程id
			String c_name = (String) jsonObject.get("kcmc");				//获得课程名字
			String c_teacher = (String) jsonObject.get("xm");				//获得任课老师姓名
			int c_year = Integer.valueOf((String)jsonObject.get("xnm"));	//获得学年
			String c_time = (String) jsonObject.get("xqj");					//获得星期几
			int c_term = Integer.valueOf((String)jsonObject.get("xqm"));	//获得学期
			String c_week = (String) jsonObject.get("zcd");					//获得上课周数
			
			//格式化上课节数
			c_lesson = formatLesson(c_lesson);
			
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
			int j = 0;
			while(cWeek[j]!=0){
				sb.append(cWeek[j++] + ",");
			}
			sb.deleteCharAt(sb.length()-1);			//去掉最后面的逗号
			
			course.setC_week(sb.toString());
			
			
			
			try {
				if(!userCourses.contains(course)){
					//在数据库没找到课程
					isOverride = 1;					//标记一下
					userCourses.add(course);		//把课程放到数据库里
				}
				newCourses.add(course);		//不管在数据库找到或者没找到课程都要放到返回的课程信息里
			} catch (Exception e) {
				System.out.println("保存数据异常");
			}
		}
		try {
			copyUser = new User();
			copyUser.setUid(user.getUid());
			copyUser.setSex(user.getSex());
			copyUser.setRank(user.getRank());
			copyUser.setNickname(user.getNickname());
			copyUser.setEmail(user.getEmail());
			copyUser.setAvatar(user.getAvatar());
			copyUser.setCourses(newCourses);
			
			if(isOverride==1){
				userService.updateUser(user);		//更新user课程表
			}
		} catch (Exception e) {
			System.out.println("更新数据异常");
		}
		return copyUser.getCourses();
	}
	
	private String formatLesson(String c_lesson) {
		StringBuffer resultBuffer = new StringBuffer();
		String[] s = c_lesson.split("-");
		String string1 = s[0];
		String string2 = s[1];
		string2 = string2.substring(0, string2.length()-1);
		int i = Integer.valueOf(string1);
		for(;i<=Integer.valueOf(string2);i++){
			resultBuffer.append(i);
			resultBuffer.append(",");
		}
		resultBuffer.deleteCharAt(resultBuffer.length()-1);			//删除最后一个逗号
		return resultBuffer.toString();
	}


	private JSONArray requestCourse() {
		HttpUtils httpUtils = new HttpUtils(requestUrl);
		
		/**设置请求方式*/
		httpUtils.setRequestMethod("POST");
		
		/**设置请求参数*/
		Map requestProperty = new HashMap();
		requestProperty.put("yhm", schoolLogin.getSchool_no());
		requestProperty.put("mm", schoolLogin.getPassword());
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
		Map headProperty = new HashMap();
		headProperty.put("Cookie", cookie);
		httpUtils.setHeadProperty(headProperty);
		
		/**设置请求参数，学年度，学期*/
		Map<String, String> requestProperty2 = new HashMap<String, String>();
//		requestProperty2.put("gnmkdmKey", "N2151");
//		requestProperty2.put("sessionUserKey", courseLogin.getId());
		requestProperty2.put("xnm", schoolLogin.getXnm());		//设置学年名
		requestProperty2.put("xqm", schoolLogin.getXqm());		//设置学期名
		httpUtils.setRequestProperty(requestProperty2);
		
		/**第二次请求*/
		HttpResult httpResult2 = httpUtils.request();
		
		/**拿到第二次请求的响应正文（课表信息json格式）*/
//		System.out.println(httpResult2.getResponseBodyString("utf-8"));
		
		String jsonString = httpResult2.getResponseBodyString("utf-8");
		
		System.out.println("---------------------------------课表信息（开始）---------------------------");
		System.out.println(jsonString);
		System.out.println("---------------------------------课表信息（结束）---------------------------");
		
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
	
	private int loginValidate(String school_no, String password) {
		int resultInt = 0;
		Map<String, String> requestProperty = new HashMap<String, String>();
		requestProperty.put("yhm", school_no);
		requestProperty.put("mm", password);
		JSONObject validateJsonObject = ValidateUtil.validate(requestValidateUrl, null, requestProperty);
		String status = (String)validateJsonObject.get("status");
		if(status!=null && status.equals("success")){
			resultInt = 1;
		}
		return resultInt;
	}
}
