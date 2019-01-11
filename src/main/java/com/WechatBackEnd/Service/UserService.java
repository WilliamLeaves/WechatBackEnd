package com.WechatBackEnd.Service;

import java.util.Map;

import com.WechatBackEnd.Model.User;

public interface UserService {
	public Map<String,Object> getUser(String uid);
	public boolean updateUserInfo(User user);
	public boolean registerUser(User user);
}
