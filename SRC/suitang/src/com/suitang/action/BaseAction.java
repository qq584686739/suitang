package com.suitang.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.suitang.utils.TUtil;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,ServletRequestAware,ServletResponseAware{

	private T entiry;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	//T型实例化
	public BaseAction(){
		/**T型转换*/
		Class entityClass = TUtil.getActualType(this.getClass());
		try {
			entiry = (T) entityClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public T getModel() {
		return entiry;
	}

	@Override
	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

}
