package com.suitang.dao.impl;

import javax.annotation.Resource;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.suitang.dao.BaseDao;

@Transactional(readOnly=false)
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{			
	//只用来实现增删改查
	
	@Resource(name="sessionFactory")
	public void setDi(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
		System.out.println("this.getHibernateTemplate() = " + this.getHibernateTemplate());
	}

	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}
}
