package com.WechatBackEnd.Service;

public interface LoginService {

	boolean isOldUser(String openId);

	void login(String sessionKey,String openId);

	int findUidBySessionKey(String sessionKey);
}
