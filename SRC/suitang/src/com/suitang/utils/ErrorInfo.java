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
	
	/**数据库无该token*/
	public static final int SQL_NOT_FIND_TOKEN = 2002;
	
	/**token已过期*/
	public static final int TOKEN_EXPIRE = 2003;
	
	/**数据库不存在sign_token，请刷新重试*/
	public static final int SIGN_TOKEN_NOT_FIND = 2004;
	
	/**课程七个字段有问题，请检查后重试*/
	public static final int COURSE_PROBLEM = 2005;
	
	/**传入的课程数据查不到该课程*/
	public static final int NOT_FIND_COURSE = 2006;
	
	/**该sign_token已经过期无法使用，请刷新后重试*/
	public static final int SIGN_TOKEN_EXPIRE = 2007;
}