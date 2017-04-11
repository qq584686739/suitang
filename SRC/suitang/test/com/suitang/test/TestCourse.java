package com.suitang.test;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suitang.utils.HttpUtils;
import com.suitang.utils.HttpUtils.HttpResult;

public class TestCourse {
	private static String requestUrl = "http://218.197.80.13/jwglxt/xtgl/login_login.html";
	private static String requestUrl2 = "http://218.197.80.13/jwglxt/kbcx/xskbcx_cxXsKb.html";
	public static void main(String[] args) throws Exception {
		HttpUtils httpUtils = new HttpUtils(requestUrl);
		
		httpUtils.setRequestMethod("POST");
		
		Map requestProperty = new HashMap();
		requestProperty.put("yhm", "13150227");
		requestProperty.put("mm", "CHENBING950821");
		requestProperty.put("yzm", "");
		
		httpUtils.setRequestProperty(requestProperty);
		HttpResult httpResult = httpUtils.request();
		
		System.out.println(httpResult.getResponseHead());
		
		Map<String,List<String>> map = httpResult.getResponseHead();
		List<String> list = map.get("Set-Cookie");
		String jsessionid = list.get(0);
		System.out.println(jsessionid);

		String j = jsessionid.substring(0, jsessionid.indexOf(";"));
		
		System.out.println("j = " + j);
		
		httpUtils.setRequestUrl(requestUrl2);
		Map headProperty = new HashMap();
		headProperty.put("Cookie", j);
		httpUtils.setHeadProperty(headProperty);
		
		//设置请求参数
		
		Map<String, String> requestProperty2 = new HashMap<String, String>();
		requestProperty2.put("gnmkdmKey", "N2151");
		requestProperty2.put("sessionUserKey", "13150227");
		requestProperty2.put("xnm", "2016");
		requestProperty2.put("xqm", "3");
		httpUtils.setRequestProperty(requestProperty2);
		
		HttpResult httpResult2 = httpUtils.request();
		System.out.println(httpResult2.getResponseBodyString("utf-8"));
		
	}
}
