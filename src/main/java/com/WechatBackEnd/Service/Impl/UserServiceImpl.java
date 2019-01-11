package com.WechatBackEnd.Service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WechatBackEnd.Dao.UserRepository;
import com.WechatBackEnd.Model.User;
import com.WechatBackEnd.Service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public Map<String, Object> getUser(String uid) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(Integer.parseInt(uid)).get();
		System.out.println("");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", user.uid);
		map.put("nickname", user.nickname);
		map.put("college", user.college);
		map.put("major", user.major);
		map.put("register_time",user.register_time);
		map.put("entrance_time", user.entrance_time);
		map.put("describe", user.describ);
		return map;
	}

	@Override
	public boolean updateUserInfo(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
		return true;
	}

	@Override
	public boolean registerUser(User user) {
		userRepository.save(user);
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
