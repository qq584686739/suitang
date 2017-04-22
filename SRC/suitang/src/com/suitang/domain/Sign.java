package com.suitang.domain;

import java.io.Serializable;

public class Sign implements Serializable {
	public Integer sign_id; // 签到ID，递增
	private Integer uid; // 用户ID
	private String cid; // 课程ID
	private String cd_id; // 教室ID
	private Integer c_year; // 学年
	private Integer c_term; // 学期
	private String c_week; // 周数
	private String c_lesson; // 节数
	private String c_time; // 星期几
	private long sign_time; // 签到时间
	private String sign_token; // 签到请求唯一ID
	private long invalid_time; // 过期时间

	public Integer getSign_id() {
		return sign_id;
	}

	public void setSign_id(Integer sign_id) {
		this.sign_id = sign_id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	public Integer getC_year() {
		return c_year;
	}

	public void setC_year(Integer c_year) {
		this.c_year = c_year;
	}

	public Integer getC_term() {
		return c_term;
	}

	public void setC_term(Integer c_term) {
		this.c_term = c_term;
	}

	public String getC_week() {
		return c_week;
	}

	public void setC_week(String c_week) {
		this.c_week = c_week;
	}

	public String getC_lesson() {
		return c_lesson;
	}

	public void setC_lesson(String c_lesson) {
		this.c_lesson = c_lesson;
	}

	public String getC_time() {
		return c_time;
	}

	public void setC_time(String c_time) {
		this.c_time = c_time;
	}

	public long getSign_time() {
		return sign_time;
	}

	public void setSign_time(long sign_time) {
		this.sign_time = sign_time;
	}

	public String getSign_token() {
		return sign_token;
	}

	public void setSign_token(String sign_token) {
		this.sign_token = sign_token;
	}

	public long getInvalid_time() {
		return invalid_time;
	}

	public void setInvalid_time(long invalid_time) {
		this.invalid_time = invalid_time;
	}

}
