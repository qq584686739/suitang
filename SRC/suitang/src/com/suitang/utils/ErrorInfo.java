package com.suitang.utils;

public class ErrorInfo {
	/**服务器忙，请稍后访问...*/
	public static final int NOT_FIND = 1000;
	
	/**学号不能为空*/
	public static final int LOGIN_NOT_FIND_SCHOOL_NO = 1001;
	
	/**密码不能为空*/
	public static final int LOGIN_NOT_FIND_PASSWORD = 1002;
	
	/**设备ID不能为空*/
	public static final int LOGIN_NOT_FIND_DEVICE_ID = 1003;
	
	/**学号或密码错误*/
	public static final int LOGIN_SCHOOL_NO_OR_PASSWORD_ERROR = 1004;
	
	/**该设备90分钟之内只允许一个学生登录!*/
	public static final int LOGIN_CHANGE = 1005;
	
	/**请求头无token，说明该用户没有携带token，请重新登陆后再访问*/
	public static final int REQUESTHEAD_NOT_FIND_TOKEN = 2001;
	
	/**数据库无该token，请登陆后重新访问*/
	public static final int SQL_NOT_FIND_TOKEN = 2002;
	
	/**token存在，但是token已过期，请重新登录*/
	public static final int TOKEN_EXPIRE = 2003;
	
	/**数据库不存在sign_token，请刷新重试*/
	public static final int SIGN_TOKEN_NOT_FIND = 2004;
	
	/**课程七个字段有问题，请检查后重试*/
	public static final int COURSE_PROBLEM = 2005;
	
	/**传入的课程数据查不到该课程*/
	public static final int NOT_FIND_COURSE = 2006;
	
	/**数据库存在该sign_token，但是已经过期无法使用，请刷新后重试*/
	public static final int SIGN_TOKEN_EXPIRE = 2007;
	
	/**一个人同时发起签到和请求签到*/
	public static final int REQUESTSIGN_AND_SIGN = 2008;
	
	/**学生签到没有携带sign_token*/
	public static final int SIGN_TOKEN_ERROR = 3001;
	
	/**你已经签到成功，请勿连续签到*/
	public static final int STUDENT_SIGN_MANY_TIMES = 3002;
	
	/**查询签到记录的时候出错，sign_id不是数字*/
	public static final int SIGN_ID_NOT_INTEGER = 4001;
	
	/**查看签到详情是GET请求*/
	public static final int GET_RECORD = 4002;
	
}