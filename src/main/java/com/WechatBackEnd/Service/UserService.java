package com.WechatBackEnd.Service;

import java.util.List;

import com.WechatBackEnd.Model.FollowUserRecord;
import com.WechatBackEnd.Model.User;

public interface UserService {
	public User getUser(String uid);

	public User getMyInfo(String myUid);

	public boolean updateUserInfo(User user);

	public boolean followUser(FollowUserRecord record);

	public boolean disFollowUser(FollowUserRecord record);

	public List getMyFollowingList(String myUid);
	
}
