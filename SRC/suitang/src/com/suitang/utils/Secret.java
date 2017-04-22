package com.suitang.utils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class Secret implements Serializable{
	/**秘钥*/
	public static final String KEY = "123456781234567812345678";
	
	
	/**设置数据库登录状态的过期时间	7天 */
	public final Timestamp EXPIRATION_TIME = 
			new Timestamp(new Date().getTime() + 7*24*60*60*1000);
	
	/**设置session的存活时间是3小时*/
//	public static final int SESSION_TIME = 60 * 60 * 3;
	public static final int SESSION_TIME = 60 * 3;		//暂时设置3分钟
	
}
