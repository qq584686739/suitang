package com.suitang.domain;

import java.io.Serializable;

public class User implements Serializable{
	private int uid; // 用户ID
	private String nickname; // 用户昵称
	private String avatar; // 用户头像
	private Integer sex; // 性别0：男生 1：女生
	private Integer rank; // 职位0：学生 1：老师

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
