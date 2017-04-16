package dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suitang.dao.UserDao;
import com.suitang.domain.Course;
import com.suitang.domain.User;

public class UserCourseTest {
	@Test
	public void testUserCourse(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserDao userDao = (UserDao)applicationContext.getBean("userDao");
		User user = new User();
//		user.setUid(uid);
		user.setSex(0);
		user.setRank(0);
		user.setNickname("测试昵称");
		user.setEmail("测试邮箱");
		user.setAvatar("测试头像");
		
		Course course = new Course();
		course.setCid("测试CID");
		course.setCd_mc("测试CDMC");
		course.setCd_id("测试CDID");
		course.setC_year(2017);
		course.setC_week("测试CWEEK");
		course.setC_time("测试CTIME");
		course.setC_term(1);
		course.setC_teacher("测试CTEACHER");
		course.setC_name("测试Cname");
		course.setC_lesson("测试clesson");
		/*course.setC_address("ceshicaddress");*/
		
		Set<Course> courses = new HashSet<Course>();
		courses.add(course);
		user.setCourses(courses);
		
		userDao.save(user);
	}
}
