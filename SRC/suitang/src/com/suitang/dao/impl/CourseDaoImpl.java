package com.suitang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.suitang.dao.CourseDao;
import com.suitang.domain.Course;
import com.suitang.domain.User;

@Repository(value="courseDao")
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao{

	@Override
	public Course getCourseByPrimarykeys(String cid, String cd_id, int c_year,
			int c_term) {
		SessionFactory sessionFactory = 
				this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hql = "FROM Course c WHERE 1=1 AND "
				+ "c.cid='" + cid + "' AND "
				+ "c.cd_id='" + cd_id + "' AND "
				+ "c.c_year=" + c_year + " AND "
				+ "c.c_term=" + c_term + ")";
		Query query = session.createQuery(hql);
		List<Course> list = query.list();
		
		session.close();
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	//用来实现除了增删改查的其他方法
}