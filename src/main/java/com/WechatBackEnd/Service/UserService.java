package com.WechatBackEnd.Service;

import java.util.List;
import java.util.Map;

import com.WechatBackEnd.Model.FollowUserRecord;
import com.WechatBackEnd.Model.User;

public interface UserService {
	public Map<String, Object> getUser(String uid);

	public Map<String, Object> getMyInfo(String myUid);

	public boolean updateUserInfo(User user);

	public boolean followUser(FollowUserRecord record);

	public boolean disFollowUser(FollowUserRecord record);

	public List getMyFollowingList(String myUid);
}
