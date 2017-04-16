package com.suitang.utils;

import java.util.Map;

import net.sf.json.JSONObject;

import com.suitang.utils.HttpUtils.HttpResult;



public class ValidateUtil {
	public static JSONObject validate(String requestUrl, Map<String, String> headProperty, Map<String, String> requestProperty){
		JSONObject jsonObject = null;
		try {
			HttpUtils httpUtils = new HttpUtils(requestUrl,headProperty, requestProperty);
			
			/**我这里好像没有设置请求类型	POST/GET*/
			HttpResult httpResult = httpUtils.request();
			jsonObject = JSONObject.fromObject(httpResult.getResponseBodyString("utf-8"));
			
			return jsonObject;
		} catch (Exception e) {
			return jsonObject;
		}
	}
}
