package com.suitang.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.suitang.dao.BaseDao;
import com.suitang.domain.User;
import com.suitang.utils.TUtil;


@Transactional(readOnly=false)
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{	
	
	private Class entityClass = TUtil.getActualType(this.getClass());
	
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
	public T getUserById(Serializable uid){
		return (T) this.getHibernateTemplate().get(entityClass,uid);
	}
	
}
