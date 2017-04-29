package com.suitang.action;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.suitang.domain.LoginStatus;
import com.suitang.domain.Sign;
import com.suitang.domain.SignHistory;
import com.suitang.domain.User;
import com.suitang.service.LoginStatusService;
import com.suitang.service.SignHistoryService;
import com.suitang.service.SignService;
import com.suitang.service.UserService;
import com.suitang.utils.ErrorInfo;

@SuppressWarnings("serial")
@Controller("recordAction")
@Scope(value="prototype")//如果交给spring管理，scope = "prototype"
public class RecordAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**flag 1:找所有的签到请求，2：找某一条签到请求的所有学生信息,3:查找这个学生的所有签到记录*/
	private int flag = 1;
	
	private int sign_id = -1;
	
	private int error = 0;
	
	private PrintWriter out;
	
	private JSONObject jsonObject = new JSONObject();
	
	@Resource
	private SignHistoryService signHistoryService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private LoginStatusService loginStatusService;
	
	@Resource
	private SignService signService;
	
	private User user;

	public void validate() {
		
		//判断是学生还是老师
		String token = request.getHeader("token");
		user = loginStatusService.getUserByLoginId(token);
		if(user.getRank().intValue() == 0){
			//学生
			//是学生的话就只查签到记录
			flag = 3;
		}else {
			//老师
			
			String sign_id_String = request.getParameter("sign_id");
			
			if(sign_id_String == null){
				//说明是老师查看所有的签到请求记录
				flag = 1;
			}else{
				//说明老师查看某一条签到记录的所有学生信息
				flag = 2;
				//2136895390
				//15692236836
				try {
					sign_id = Integer.valueOf(sign_id_String);
				} catch (Exception e) {
					//查询签到记录的时候出错，sign_id不是数字
					error = ErrorInfo.SIGN_ID_NOT_INTEGER;
				}
			}
		}
		
		
		
		super.validate();
	}
	
	/**
	 * 这个接口既可以查寻所有的签到记录，也可以查寻某一条签到记录的所有已签到的学生信息。
	 * findSign
	 * @Description:
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月28日 上午1:17:21
	 * @Return:void
	 */
	
	public void findSign(){
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(error != 0){
			jsonObject.put("status", error);
			jsonObject.put("data", "");
			out.write(jsonObject.toString());
			out.close();
			return ;
		}
		
		if(flag == 1){
			//说明是老师查看所有的签到请求记录
			findAllSignRequest();
		}else if(flag == 2){
			//说明老师查看某一条签到记录的所有学生信息
			findSignHistorysBySign_id(sign_id);
		}else if(flag == 3){
			//查找这个学生所有的签到记录
			findStudentSignHistorys();
		}
		
		out.write(jsonObject.toString());
		out.close();
	}
	
	/**
	 * 查找这个学生所有的签到记录
	 * findStudentSignHistorys
	 * @Description:
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月29日 下午2:15:01
	 * @Return:void
	 */
	private void findStudentSignHistorys() {
		SignHistory[] signHistorys= signHistoryService.getSignHistorysByUid(user.getUid());
		
		if(signHistorys == null){
			//这个学生没有任何签到记录
			jsonObject.put("status", 1);
			jsonObject.put("message", "请求成功，这个学生没有任何的签到记录，赶快去扫码签到吧");
			jsonObject.put("data", "");
			return ;
		}
		
		Set<Sign> set = new HashSet<Sign>();
		for(int i = 0 ; i < signHistorys.length ; i++){
			SignHistory signHistory = signHistorys[i];
			
			int sign_id = signHistory.getSign_id();
			Sign sign = signService.findSignBySign_id(sign_id);
			set.add(sign);
		}
		jsonObject.put("status", 1);
		jsonObject.put("data", set);
	}

	private void findAllSignRequest() {
		//说明是老师查看所有的签到请求记录
		String token = request.getHeader("token");
		LoginStatus loginStatus = loginStatusService.getLoginStatusByLoginId(token);
		int uid = loginStatus.getUid();
		Sign[] signs = signService.findSignsByUid(uid);
		
		jsonObject.put("status", 1);
		if(signs == null){
			//说明该老师还没有发起任何签到
			jsonObject.put("data", "");
			jsonObject.put("message", "该老师还没有发起任何签到请求，赶紧去课堂上发起签到吧！");
		}else{
			jsonObject.put("data", signs);
		}
	}

	private void findSignHistorysBySign_id(int sign_id2) {
		//说明老师查看某一条签到记录的所有学生信息
		SignHistory[] signHistorys = signHistoryService.getSignHistorysBySign_id(sign_id);
		if(signHistorys == null){
			//该次签到请求，没有一个学生扫码签到
			jsonObject.put("status", 1);
			jsonObject.put("message", "请求成功，老师老师查看本次签到的签到记录，但是没有记录");
			jsonObject.put("data", "");
			return ;
		}
		
		Set<User> set = new HashSet<User>();
		for(int i = 0 ; i < signHistorys.length ; i++){
			SignHistory signHistory = signHistorys[i];
			int uid = signHistory.getUid();
			User user = userService.getUserById(uid);
			user.setCourses(null);
			set.add(user);
		}
		jsonObject.put("status", 1);
		jsonObject.put("data", set);
	}


	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
