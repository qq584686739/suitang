package com.suitang.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.suitang.dao.SignHistoryDao;
import com.suitang.domain.SignHistory;
import com.suitang.service.SignHistoryService;

@Service(value="signHistoryService")
public class SignHistoryServiceImpl implements SignHistoryService{
	
	@Resource
	private SignHistoryDao signHistoryDao;

	@Override
	public void saveSignHistory(SignHistory signHistory) {
		signHistoryDao.save(signHistory);
	}

	@Override
	public void updateSignHistory(SignHistory signHistory) {
		signHistoryDao.update(signHistory);
	}

	@Override
	public SignHistory[] getSignHistorysBySign_id(Serializable sign_id) {
		return signHistoryDao.getSignHistorysBySign_id(sign_id);
	}

	@Override
	public SignHistory[] getSignHistorysByUid(Integer uid) {
		return signHistoryDao.getSignHistorysByUid(uid);
	}

}
