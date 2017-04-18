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
}