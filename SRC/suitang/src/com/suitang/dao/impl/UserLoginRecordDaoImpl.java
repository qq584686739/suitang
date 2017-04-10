package com.suitang.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.suitang.dao.UserLoginRecordDao;
import com.suitang.domain.User;
import com.suitang.domain.UserLoginRecord;
import com.suitang.domain.UserOtherAuths;

@Repository(value="userLoginRecordDao")
public class UserLoginRecordDaoImpl extends BaseDaoImpl<UserLoginRecord> implements UserLoginRecordDao{
	//用来实现除了增删改查的其他方法

	@Override
	public UserLoginRecord getUserLoginRecordByLast_login_device_id(
			String last_login_device_id) {
		
		return this.getHibernateTemplate().get(UserLoginRecord.class, last_login_device_id);
	}

	@Override
	public UserOtherAuths getUserOtherAuthsByLast_login_device_id(
			String last_login_device_id) {
		SessionFactory sessionFactory = 
				this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		
		/*select uoa.identifier from user_other_auths uoa where uoa.uid in
		(select ulr.uid from user_login_record ulr where ulr.last_login_device_id="123");*/
		
		String hql = "FROM UserOtherAuths uoa WHERE uoa.uid In ( "
				+ "SELECT ulr.uid "
				+ "FROM "
				+ "UserLoginRecord ulr "
				+ "WHERE 1=1 AND "
				+ "ulr.last_login_device_id = '" + last_login_device_id + "' )";
		Query query = session.createQuery(hql);
		List<UserOtherAuths> list = query.list();
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
