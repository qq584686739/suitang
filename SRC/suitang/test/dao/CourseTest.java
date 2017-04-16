package dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.dao.CourseDao;
import com.suitang.domain.Course;

public class CourseTest {
	@Test
	public void test_save(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		CourseDao courseDao = (CourseDao)applicationContext.getBean("courseDao");
		
		Course course = new Course();
		
		course.setCid("a");
		course.setCd_mc("b");
		course.setCd_id("c");
		course.setC_year(2017);
		course.setC_week("d");
		course.setC_time("e");
		course.setC_term(22);
		course.setC_teacher("f");
		course.setC_name("g");
		course.setC_lesson("h");
		/*course.setC_address("i");*/
		
		courseDao.save(course);
	}
}
