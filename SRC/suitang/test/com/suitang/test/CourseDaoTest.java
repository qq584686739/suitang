package com.suitang.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.dao.CourseDao;
import com.suitang.domain.Course;

public class CourseDaoTest {
	@Test
	public void CourseDao_save(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseDao courseDao = (CourseDao) context.getBean("courseDao");
		
		Course course = new Course();
		course.setCid("1");
		course.setC_name("课程名");
		course.setC_address("上课地址");
		course.setC_time("上课时间");
		
		courseDao.save(course);
	}
}
