package com.suitang.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.xjh.zxing.QRCodeUtil;

@SuppressWarnings("serial")
@Controller("zxingAction")
@Scope(value="prototype")
public class ZxingAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;

	public void validate() {
		
		super.validate();
	}
	
	public String qrCode(){
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String filePath = request.getSession().getServletContext()
                .getRealPath("/")
                + "qrCode";
		
        String suitang = request.getSession().getServletContext()
                .getRealPath("/")
                + "qrCode/suitang.png";
        
        suitang = suitang.replace("\\", "/");
        filePath = filePath.replace("\\", "/");
        
        File filePathFile = new File(filePath);
        File suitangFile = new File(suitang);
        
        
        if(!filePathFile.exists()){
        	filePathFile.mkdir();
        }
		
		if(!suitangFile.exists()){
			try {
				suitangFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return ERROR;
			}
		}
		
		String fileName = "";
		
		String method = request.getMethod();
		if(method.equals("POST")){
			return ERROR;
		}
		String qr_code_string = request.getParameter("qr_code_string");
		if(qr_code_string == null){
			return ERROR;
		}
		try {
			fileName = QRCodeUtil.encode(qr_code_string, suitang, filePath, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("fileName", fileName);
		return SUCCESS;
		
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
