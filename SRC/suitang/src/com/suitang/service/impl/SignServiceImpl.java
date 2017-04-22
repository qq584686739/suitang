package com.suitang.service.impl;

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
}
