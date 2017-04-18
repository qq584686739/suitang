package com.suitang.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable{
	private Integer uid; // 用户ID
	private String nickname; // 用户昵称
	private String avatar; // 用户头像
	private Integer sex; // 性别0：男生 1：女生
	private String email;	//邮箱
	private Integer rank; // 职位0：学生 1：老师
	
	private Set<Course> courses = new HashSet<Course>();
	
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
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

//	@Override
//	public boolean equals(Object obj) {
//		if(obj == null) return false;  
//        if(this == obj) return true;  
//        if(obj instanceof User){   
//            User user =(User)obj;  
////	          if(user.id = this.id) return true; // 只比较id  
//            // 比较id和username 一致时才返回true 之后再去比较 hashCode  
//            if(user.uid == this.uid
//            		&& user.nickname.equals(this.nickname)
//            		&& user.avatar.equals(this.avatar)
//            		&& user.sex == this.sex
//            		&& user.email.equals(this.email)
//            		&& user.rank == this.rank) return true;  
//            }  
//        return false;  
//	}
//	
//
//	@Override
//	public int hashCode() {
//		 return uid.hashCode() 
//				 * nickname.hashCode()
//				 * avatar.hashCode()
//				 * sex.hashCode()
//				 * email.hashCode()
//				 * rank.hashCode();
//	}
}
