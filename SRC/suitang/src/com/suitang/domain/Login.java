package com.suitang.domain;

import java.io.Serializable;

public class Login implements Serializable{
	private String identity_type;			//认证类型
	private String identifier;				//认证id
	private String nickName;				//昵称
	private int sex;						//性别
	private String avatar;					//头像
	private String last_login_device;		//本次登录设备
	private String last_login_device_id;	//本次登录设备id

	public String getIdentity_type() {
		return identity_type;
	}

	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
