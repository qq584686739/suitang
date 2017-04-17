package com.suitang.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.suitang.dao.UserLocalAuthDao;
import com.suitang.domain.LoginStatus;
import com.suitang.domain.User;
import com.suitang.domain.UserOtherAuths;
import com.suitang.domain.UserLocalAuths;

@Repository(value="userLocalAuthDao")
public class UserLocalAuthDaoImpl extends BaseDaoImpl<UserLocalAuths> implements UserLocalAuthDao{
	//用来实现除了增删改查的其他方法

	public User getUserBySchool_no(Serializable school_no) {
		SessionFactory sessionFactory = 
				this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hql = "FROM User u WHERE u.uid In ( "
				+ "SELECT ula.uid "
				+ "FROM "
				+ "UserLocalAuths ula "
				+ "WHERE 1=1 AND "
				+ "ula.school_no = '" + school_no + "' )";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		
		session.close();
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
	public LoginStatus getLoginStatusBySchool_no(String school_no) {
		SessionFactory sessionFactory = 
				this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hql = "FROM LoginStatus ls WHERE ls.uid In ( "
				+ "SELECT ula.uid "
				+ "FROM "
				+ "UserLocalAuths ula "
				+ "WHERE 1=1 AND "
				+ "ula.school_no = '" + school_no + "' )";
		Query query = session.createQuery(hql);
		List<LoginStatus> list = query.list();
		
		session.close();
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
