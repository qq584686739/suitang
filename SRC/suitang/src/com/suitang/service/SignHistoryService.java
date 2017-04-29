package com.suitang.service;

import java.io.Serializable;

import com.suitang.domain.SignHistory;

public interface SignHistoryService {
	public void saveSignHistory(SignHistory signHistory);
	public void updateSignHistory(SignHistory signHistory);
	public SignHistory[] getSignHistorysBySign_id(Serializable sign_id);
	public SignHistory[] getSignHistorysByUid(Integer uid);
}
