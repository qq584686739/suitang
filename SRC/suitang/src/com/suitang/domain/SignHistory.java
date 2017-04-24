package com.suitang.domain;

import java.io.Serializable;

public class SignHistory implements Serializable {
	private Integer uid;			//用户ID
	private Integer sign_id;		//签到ID
	private Long sign_time;			//签到时间
	private Integer sign_late;		//是否补签
	private String late_reason;		//补签原因
	private Integer late_state;		//审核状态：审核中、已同意、已拒绝

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getSign_id() {
		return sign_id;
	}

	public void setSign_id(Integer sign_id) {
		this.sign_id = sign_id;
	}

	public Long getSign_time() {
		return sign_time;
	}

	public void setSign_time(Long sign_time) {
		this.sign_time = sign_time;
	}

	public Integer getSign_late() {
		return sign_late;
	}

	public void setSign_late(Integer sign_late) {
		this.sign_late = sign_late;
	}

	public String getLate_reason() {
		return late_reason;
	}

	public void setLate_reason(String late_reason) {
		this.late_reason = late_reason;
	}

	public Integer getLate_state() {
		return late_state;
	}

	public void setLate_state(Integer late_state) {
		this.late_state = late_state;
	}

}
