package com.suitang.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

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
			
			String cd_id = (String) jsonObject.get("cd_id");				//获得教室id
			String cd_mc = (String) jsonObject.get("cdmc");					//获得教室名称
			String c_lesson = (String) jsonObject.get("jc");				//获得上课节数
			String cid = (String) jsonObject.get("jxbmc");					//获得课程id
			String c_name = (String) jsonObject.get("kcmc");				//获得课程名字
			String c_teacher = (String) jsonObject.get("xm");				//获得任课老师姓名
			int c_year = (Integer) jsonObject.get("xnm");					//获得学年
			String c_time = (String) jsonObject.get("xqj");					//获得星期几
			int c_term = (Integer) jsonObject.get("xqm");					//获得学期
			String c_week = (String) jsonObject.get("zcd");					//获得上课周数
			
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
			while(cWeek[i]!=0){
				sb.append(cWeek[i++] + ",");
			}
			sb.deleteCharAt(sb.length()-1);
			
			course.setC_week(sb.toString());
			
			//保存课程表
			
			//保存学生课程schedule表
			
			//返回返回课表信息
			
		}
	}
	private static int[] format(String c_week) {
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
	
	
	@Test
	public void f(){
//		String s = "1-2周,4-18周(双),19周";
//		String s = "3-17周(单)";
//		String s = "1-19周";
		String s = "1-9周";
//		String s = "1-19周";
		int[] cWeek = format(s);
		int i=0;
		
		StringBuffer sb = new StringBuffer();
		while(cWeek[i]!=0){
			sb.append(cWeek[i++] + ",");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
		
		
		
//		while(cWeek[i]!=0){
//			System.out.print(cWeek[i++] + ", ");
//		}
	}
}
