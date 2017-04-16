package com.suitang.domain;

import java.io.Serializable;

public class CourseLogin implements Serializable {
	private String school;		//学校
	private String id;			//学号
	private String psw;			//密码
	private String xnm;			//学年名
	private String xqm;			//学期名
	private String token;		//token

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getXnm() {
		return xnm;
	}

	public void setXnm(String xnm) {
		this.xnm = xnm;
	}

	public String getXqm() {
		return xqm;
	}

	public void setXqm(String xqm) {
		this.xqm = xqm;
	}

}
