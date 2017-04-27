package com.suitang.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.suitang.dao.SignDao;
import com.suitang.domain.Sign;
import com.suitang.service.SignService;

@Service(value="signService")
public class SignServiceImpl implements SignService{
	
	@Resource
	private SignDao signDao;

	@Override
	public void saveSign(Sign sign) {
		signDao.save(sign);
	}

	@Override
	public void updateSign(Sign sign) {
		signDao.update(sign);
	}

	@Override
	public Sign findSignBySign_token(String sign_token) {
		return signDao.findSignBySign_token(sign_token);
	}

	@Override
	public Sign findSignBySign_id(Serializable sign_id) {
		return signDao.findSignBySign_id(sign_id);
	}

	@Override
	public Sign[] findSignsByUid(Serializable uid) {
		return signDao.findSignsByUid(uid);
	}
}
