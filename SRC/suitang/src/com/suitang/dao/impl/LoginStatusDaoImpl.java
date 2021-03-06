package com.suitang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.suitang.dao.LoginStatusDao;
import com.suitang.dao.UserDao;
import com.suitang.domain.LoginStatus;
import com.suitang.domain.User;
import com.suitang.domain.UserOtherAuths;

@Repository(value="loginStatusDao")
public class LoginStatusDaoImpl extends BaseDaoImpl<LoginStatus> implements LoginStatusDao{
	//用来实现除了增删改查的其他方法

	@Override
	public User getUserByLoginId(String token) {
		SessionFactory sessionFactory = 
				this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "FROM User u WHERE u.uid In ( "
				+ "SELECT ls.uid "
				+ "FROM "
				+ "LoginStatus ls "
				+ "WHERE 1=1 AND "
				+ "ls.login_id = '" + token + "' )";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		
		session.close();
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
