package com.suitang.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Course implements Serializable {
	private String cid; // 课程id 使用获取到的jxbmc字段
	private String c_name; // 课程名字
	private String cd_id; // 教室id
	private String cd_mc; // 教室名称
	private String c_teacher; // 任课老师姓名
	private int c_year; // 学年
	private int c_term; // 学期
	private String c_week; // 上课周数 [1,2,4,6,8,10]
	private String c_lesson; // 上课节数[1,2]
	// private String c_address; //上课地点
	private String c_time; // 上课时间

//	private Set<User> users = new HashSet<User>();

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

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	public String getCd_mc() {
		return cd_mc;
	}

	public void setCd_mc(String cd_mc) {
		this.cd_mc = cd_mc;
	}

	public String getC_teacher() {
		return c_teacher;
	}

	public void setC_teacher(String c_teacher) {
		this.c_teacher = c_teacher;
	}

	public int getC_year() {
		return c_year;
	}

	public void setC_year(int c_year) {
		this.c_year = c_year;
	}

	public int getC_term() {
		return c_term;
	}

	public void setC_term(int c_term) {
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


//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}

	

	@Override
	public String toString() {
		return "Course [cid=" + cid + ", c_name=" + c_name + ", cd_id=" + cd_id
				+ ", cd_mc=" + cd_mc + ", c_teacher=" + c_teacher + ", c_year="
				+ c_year + ", c_term=" + c_term + ", c_week=" + c_week
				+ ", c_lesson=" + c_lesson + ", c_time=" + c_time + "]";
	}

	
	//覆写equals和hashcode方法
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;  
		if(this == obj) return true;  
		if(obj instanceof Course){   
			Course course =(Course)obj;  
				if(course.cid.equals(this.cid)
					&& course.cd_id.equals(this.cd_id)
		      		&& course.cd_id.equals(this.cd_id)
		      		&& course.c_year == this.c_year
		      		&& course.c_term == this.c_term
		      		&& course.c_week.equals(this.c_week)
		      		&& course.c_lesson.equals(this.c_lesson)
		      		&& course.c_time.equals(this.c_time)) return true;  
		}  
		return false;  
	}

	@Override
	public int hashCode() {
		 return cid.hashCode() 
				* cd_id.hashCode()
				* c_year
				* c_term
				* c_week.hashCode()
				* c_lesson.hashCode()
		 		* c_time.hashCode();
	}
}
