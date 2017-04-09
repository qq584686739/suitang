package com.suitang.domain;

public class UserOtherAuths {
	private int uid;
	private String identity_type;
	private String identifier;
	private String token;
	private Long invalid_time;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getInvalid_time() {
		return invalid_time;
	}

	public void setInvalid_time(Long invalid_time) {
		this.invalid_time = invalid_time;
	}

}
