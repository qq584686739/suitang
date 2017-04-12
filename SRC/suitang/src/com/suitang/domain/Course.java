package com.suitang.domain;

import java.io.Serializable;

public class Course implements Serializable {
	private String cid;
	private String c_name;
	private String c_address;
	private String c_time;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_address() {
		return c_address;
	}

	public void setC_address(String c_address) {
		this.c_address = c_address;
	}

	public String getC_time() {
		return c_time;
	}

	public void setC_time(String c_time) {
		this.c_time = c_time;
	}

}
