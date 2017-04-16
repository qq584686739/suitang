package com.suitang.domain;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable{
	private int uid; // 用户ID
	private String nickname; // 用户昵称
	private String avatar; // 用户头像
	private Integer sex; // 性别0：男生 1：女生
	private String email;	//邮箱
	private Integer rank; // 职位0：学生 1：老师
	
	private Set<Course> courses;
	
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
