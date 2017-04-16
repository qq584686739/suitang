package com.suitang.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.suitang.dao.CourseDao;
import com.suitang.dao.UserDao;
import com.suitang.domain.Course;
import com.suitang.domain.User;
import com.suitang.service.CourseService;

@Service(value="courseService")
public class CourseServiceImpl implements CourseService{
	@Resource
	private CourseDao courseDao;

	@Override
	public void saveCourse(Course course) {
		courseDao.save(course);
	}

	@Override
	public void updateCourse(Course course) {
		courseDao.update(course);
	}

	@Override
	public Course getUserById(Serializable uid) {
		return courseDao.getTById(uid);
	}

	@Override
	public Course getCourseByPrimarykeys(String cid, String cd_id, int c_year,
			int c_term) {
		return courseDao.getCourseByPrimarykeys(cid,cd_id,c_year,c_term);
	}
}
