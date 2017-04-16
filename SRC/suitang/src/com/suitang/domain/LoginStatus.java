package com.suitang.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class LoginStatus implements Serializable {
	private int uid; // 用户ID
	private String login_id;
	private Timestamp expiration_time;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public Timestamp getExpiration_time() {
		return expiration_time;
	}

	public void setExpiration_time(Timestamp expiration_time) {
		this.expiration_time = expiration_time;
	}

}
