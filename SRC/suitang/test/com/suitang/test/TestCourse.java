package com.suitang.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.suitang.domain.Course;
import com.suitang.utils.HttpUtils;
import com.suitang.utils.HttpUtils.HttpResult;

public class TestCourse {
	private static String requestUrl = "http://218.197.80.13/jwglxt/xtgl/login_login.html";
	private static String requestUrl2 = "http://218.197.80.13/jwglxt/kbcx/xskbcx_cxXsKb.html";
	public static void main(String[] args) throws Exception {
		
		HttpUtils httpUtils = new HttpUtils(requestUrl);
		
		/**设置请求方式*/
		httpUtils.setRequestMethod("POST");
		
		/**设置请求参数*/
		Map requestProperty = new HashMap();
		requestProperty.put("yhm", "13150227");
		requestProperty.put("mm", "CHENBING950821");
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
		requestProperty2.put("gnmkdmKey", "N2151");
		requestProperty2.put("sessionUserKey", "13150227");
		requestProperty2.put("xnm", "2016");
		requestProperty2.put("xqm", "3");
		httpUtils.setRequestProperty(requestProperty2);
		
		/**第二次请求*/
		HttpResult httpResult2 = httpUtils.request();
		
		/**拿到第二次请求的响应正文（课表信息json格式）*/
//		System.out.println(httpResult2.getResponseBodyString("utf-8"));
		
		String jsonString = httpResult2.getResponseBodyString("utf-8");
		
		JSONObject json = JSONObject.fromObject(jsonString);
		
		/**获得所有的课程信息*/
		JSONArray cb = (JSONArray)json.get("kbList");
		
		for(int i=0;i<cb.size();i++){
			JSONObject jsonObject = (JSONObject)cb.get(i);
			String cid = (String) jsonObject.get("cd_id");				//获得课程id
			String c_name = (String) jsonObject.get("kcmc");			//获得课程名
			String c_address = (String) jsonObject.get("cdmc");			//获得上课地点
			String c_time = (String) jsonObject.get("zcd");				//获得上课时间
			
			Course course = new Course();
			course.setCid(cid);
			course.setC_name(c_name);
			course.setC_address(c_address);
			course.setC_time(c_time);
			
			//保存课程表
			
			//保存学生课程schedule表
			
			//返回返回课表信息
			
		}
	}
}
