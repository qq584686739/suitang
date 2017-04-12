package com.suitang.service;

import java.io.Serializable;

import com.suitang.domain.Course;

public interface CourseService {
	public void saveCourse(Course course);
	public void updateCourse(Course course);
	public Course getUserById(Serializable uid);
}
