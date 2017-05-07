package com.suitang.record;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.suitang.utils.HttpUtils;
import com.suitang.utils.HttpUtils.HttpResult;

public class RecordTest {
	@Test
	public void testRecord(){
		String requestUrl = "http://127.0.0.1:8080/suitang/recordAction.action";
		
		Map<String, String> headProperty = new HashMap<String, String>();
		headProperty.put("token", "5c55136e-8e9b-4fa4-bfaf-069b82300c31");
		
		Map<String, String> requestProperty = new HashMap<String, String>();
		requestProperty.put("sign_id", "140");
		
		HttpUtils httpUtils = new HttpUtils(requestUrl, headProperty, requestProperty);
		
		httpUtils.setRequestMethod(httpUtils.REQUEST_GET);
		HttpResult HttpResult = httpUtils.request();
		
		System.out.println(HttpResult.getResponseBodyString("utf-8"));
		
	}
}
