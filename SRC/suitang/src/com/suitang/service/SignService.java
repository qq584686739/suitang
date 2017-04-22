package com.suitang.service;

import com.suitang.domain.Sign;

public interface SignService {
	public void saveSign(Sign sign);
	public void updateSign(Sign sign);
	public Sign findSignBySign_token(String sign_token);
}
