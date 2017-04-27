package com.suitang.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.From;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.suitang.dao.SignHistoryDao;
import com.suitang.domain.SignHistory;
import com.suitang.domain.User;

@Repository(value="signHistoryDao")
public class SignHistoryDaoImpl extends BaseDaoImpl<SignHistory> implements SignHistoryDao{
	//用来实现除了增删改查的其他方法

	@Override
	public SignHistory[] getSignHistorysBySign_id(Serializable sign_id) {
		SessionFactory sessionFactory = 
				this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hql = "FROM SignHistory sh WHERE sh.sign_id = '" + sign_id + "'";
		Query query = session.createQuery(hql);
		List<SignHistory> list = query.list();
		int size = list.size();
		
		session.close();
		
		SignHistory[] SignHistorys = new SignHistory[size];
		
		if(list!=null && list.size()>0){
			for(int i=0;i<size;i++){
				SignHistorys[i] = list.get(i);
			}
			return SignHistorys;
		}
		return null;
	}

}
