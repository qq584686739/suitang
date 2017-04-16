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
	
	
	/**
	 * 
	 * getCourseByPrimarykeys
	 * @Description: 根据四个主键得到Course
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月16日 下午8:32:40
	 * @param cid
	 * @param cd_id
	 * @param c_year
	 * @param c_term
	 * @return
	 * @Return:Course
	 */
	public Course getCourseByPrimarykeys(String cid, String cd_id, int c_year,int c_term);
}
