package com.suitang.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.suitang.dao.SignDao;
import com.suitang.domain.Sign;
import com.suitang.domain.User;

@Repository(value="signDao")
public class SignDaoImpl extends BaseDaoImpl<Sign> implements SignDao{
	//用来实现除了增删改查的其他方法

	@Override
	public Sign findSignBySign_token(String sign_token) {
		SessionFactory sessionFactory = 
				this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hql = "FROM Sign s WHERE s.sign_token = '" + sign_token + "'";
		Query query = session.createQuery(hql);
		List<Sign> list = query.list();
		
		session.close();
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Sign findSignBySign_id(Serializable sign_id) {
		this.getHibernateTemplate().get(Sign.class, sign_id);
		return null;
	}

	@Override
	public Sign[] findSignsByUid(Serializable uid) {
		SessionFactory sessionFactory = 
				this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "FROM Sign s WHERE s.uid = '" + uid + "'";
		Query query = session.createQuery(hql);
		List<Sign> list = query.list();
		int size = list.size();
		
		Sign[] signs = new Sign[size];
		
		session.close();
		
		if(list!=null && list.size()>0){
			for(int i = 0 ; i < size ; i++){
				signs[i] = list.get(i);
			}
			return signs;
		}
		return null;
	}
}
