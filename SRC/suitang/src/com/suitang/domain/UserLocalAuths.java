package com.suitang.domain;

import java.io.Serializable;

public class UserLocalAuths implements Serializable{
	private String school_no;	//学号			//主键
	private int uid;
	private String password;
	private int rand;			//用户类型 0学生 1老师
	private String phone_no;	//手机号
	private String email;		//邮箱
	
	public String getSchool_no() {
		return school_no;
	}

	public void setSchool_no(String school_no) {
		this.school_no = school_no;
	}

	public int getRand() {
		return rand;
	}

	public void setRand(int rand) {
		this.rand = rand;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
