package com.suitang.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.domain.Course;
import com.suitang.service.CourseService;

public class CourseServiceTest {
	@Test
	public void CourseService_save(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		
		Course course = new Course();
		course.setCid("2");
		course.setC_name("课程名2");
		/*course.setC_address("上课地址2");*/
		course.setC_time("上课时间2");
		
		courseService.saveCourse(course);
	}
	@Test
	public void CourseService_update(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		
		Course course = new Course();
		course.setCid("2");
		course.setC_name("课程名22");
	/*	course.setC_address("上课地址22");*/
		course.setC_time("上课时间22");
		
		courseService.updateCourse(course);
	}
}
