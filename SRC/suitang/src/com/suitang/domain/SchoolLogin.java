package com.suitang.domain;

import java.io.Serializable;

public class SchoolLogin implements Serializable {
	private String school_no; // 学号
	private String password; // 密码
	private String xnm; // 学年名
	private String xqm; // 学期名
	private String device_name; // 本次登录设备
	private String device_id; // 本次登录设备id

	public String getSchool_no() {
		return school_no;
	}

	public void setSchool_no(String school_no) {
		this.school_no = school_no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

}
