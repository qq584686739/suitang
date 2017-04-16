package com.suitang.service;

import java.io.Serializable;

import com.suitang.domain.Course;

public interface CourseService {
	/**
	 * saveCourse
	 * @Description:传入Course对象进行保存
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017-4-13(创建日期)
	 * @Parameters:Course
	 * @Return:void
	 */
	public void saveCourse(Course course);
	
	/**
	 * updateCourse
	 * @Description:传入Course对象进行更新
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017-4-13(创建日期)
	 * @Parameters:Course
	 * @Return:void
	 */
	public void updateCourse(Course course);
	
	/**
	 * getUserById
	 * @Description:传入uid查找Course
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017-4-13(创建日期)
	 * @Parameters:Serializable
	 * @Return:Course
	 */
	public Course getUserById(Serializable uid);
}
