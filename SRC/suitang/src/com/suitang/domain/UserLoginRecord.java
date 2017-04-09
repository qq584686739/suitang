package com.suitang.domain;

import java.io.Serializable;

public class UserLoginRecord implements Serializable{
	private Integer uid; //
	private long first_login_time; // 首次登录时间
	private long last_login_time; // 上次登录时间
	private String last_login_device; // 上次登录设备名称
	private String last_login_device_id; // 上次登录设备id

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public long getFirst_login_time() {
		return first_login_time;
	}

	public void setFirst_login_time(long first_login_time) {
		this.first_login_time = first_login_time;
	}

	public long getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(long last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLast_login_device() {
		return last_login_device;
	}

	public void setLast_login_device(String last_login_device) {
		this.last_login_device = last_login_device;
	}

	public String getLast_login_device_id() {
		return last_login_device_id;
	}

	public void setLast_login_device_id(String last_login_device_id) {
		this.last_login_device_id = last_login_device_id;
	}

}
