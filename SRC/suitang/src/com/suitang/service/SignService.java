package com.suitang.service;

import java.io.Serializable;

import com.suitang.domain.Sign;

public interface SignService {
	public void saveSign(Sign sign);
	public void updateSign(Sign sign);
	public Sign findSignBySign_token(String sign_token);
	public Sign findSignBySign_id(Serializable sign_id);
	public Sign[] findSignsByUid(Serializable uid);
}
