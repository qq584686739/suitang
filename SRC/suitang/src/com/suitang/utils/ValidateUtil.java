package com.suitang.utils;

import java.util.Map;

import net.sf.json.JSONObject;

import com.suitang.utils.HttpUtils.HttpResult;



public class ValidateUtil {
	public static String validate(String requestUrl, Map<String, String> headProperty, Map<String, String> requestProperty){
		JSONObject jsonObject = null;
		try {
			HttpUtils httpUtils = new HttpUtils(requestUrl,headProperty, requestProperty);
			
			HttpResult httpResult = httpUtils.request();
			jsonObject = JSONObject.fromObject(httpResult.getResponseBodyString("utf-8"));
			return (String) jsonObject.get("status");
		} catch (Exception e) {
			return "error";
		}
	}
}
