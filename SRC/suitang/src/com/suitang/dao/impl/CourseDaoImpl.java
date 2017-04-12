package com.suitang.dao.impl;

import org.springframework.stereotype.Repository;

import com.suitang.dao.CourseDao;
import com.suitang.domain.Course;

@Repository(value="courseDao")
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao{
	//用来实现除了增删改查的其他方法
}