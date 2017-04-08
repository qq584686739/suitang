package com.suitang.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.suitang.dao.BaseDao;
import com.suitang.domain.User;

@Transactional(readOnly=false)
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{			
	//只用来实现增删改查
	
	@Resource(name="sessionFactory")
	public void setDi(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public User getUserById(Serializable uid) throws Exception{
		return this.getHibernateTemplate().get(User.class,uid);
	}
}
