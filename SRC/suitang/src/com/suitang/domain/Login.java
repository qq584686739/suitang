package com.suitang.domain;

public class Login {
	private String identity_type;
	private String identifier;
	private String nickName;
	private int sex;
	private String avatar;
	private String last_login_device;
	private String last_login_device_id;

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
