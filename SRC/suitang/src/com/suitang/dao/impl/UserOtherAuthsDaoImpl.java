package com.suitang.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.suitang.dao.UserOtherAuthsDao;
import com.suitang.domain.User;
import com.suitang.domain.UserOtherAuths;

@Repository(value="userOtherAuthsDao")
public class UserOtherAuthsDaoImpl extends BaseDaoImpl<UserOtherAuths> implements UserOtherAuthsDao{
	//用来实现除了增删改查的其他方法

	@Override
	public User getUserByIdentity_typeAndIdentifier(String identity_type,
			String identifier) {
		
		SessionFactory sessionFactory = 
				this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		
//		select * FROM user u WHERE u.uid IN ( 
//				SELECT uos.uid 
//				FROM 
//				user_other_auths uos 
//				WHERE 
//				uos.identity_type = 'qq1234' AND 
//				uos.identifier = '141024' );
		
		
		String hql = "FROM User u WHERE u.uid IN ( "
				+ "SELECT uos.uid "
				+ "FROM "
				+ "UserOtherAuths uos "
				+ "WHERE "
				+ "uos.identity_type = '"+ identity_type +"' AND "
				+ "uos.identifier = '" + identifier + "' )";
		Query query = session.createQuery(hql);
		System.out.println(query);
		List<User> list = query.list();
		
		session.close();
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public User getUserByIdentifier(String identifier) {

		SessionFactory sessionFactory = 
				this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hql = "FROM User u WHERE u.uid IN ( "
				+ "SELECT uos.uid "
				+ "FROM "
				+ "UserOtherAuths uos "
				+ "WHERE "
				+ "uos.identifier = '" + identifier + "' )";
		Query query = session.createQuery(hql);
		System.out.println(query);
		List<User> list = query.list();
		
		session.close();
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
