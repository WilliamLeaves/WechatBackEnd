package com.WechatBackEnd.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WechatBackEnd.Dao.FollowUserRecordRepository;
import com.WechatBackEnd.Dao.SessionRepository;
import com.WechatBackEnd.Dao.UserRepository;
import com.WechatBackEnd.Model.FollowUserRecord;
import com.WechatBackEnd.Model.Session;
import com.WechatBackEnd.Model.User;
import com.WechatBackEnd.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;
	@Autowired
	public FollowUserRecordRepository followUserRecordRepository;
	@Autowired
	public SessionRepository sessionRepository;

	@Override
	public User getUser(String uid) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(Integer.parseInt(uid)).get();
		return user;
	}

	@Override
	public User getMyInfo(String myUid) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(Integer.parseInt(myUid)).get();
		return user;
	}

	@Override
	public boolean updateUserInfo(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
		return true;
	}

	@Override
	public boolean followUser(FollowUserRecord record) {
		// TODO Auto-generated method stub
		followUserRecordRepository.save(record);
		return true;
	}

	@Override
	public boolean disFollowUser(FollowUserRecord record) {
		// TODO Auto-generated method stub
		followUserRecordRepository.save(record);
		return true;
	}

	@Override
	public List getMyFollowingList(String myUid) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		List<Object[]> list = followUserRecordRepository.findFollowList(myUid);
		for (Object[] ob : list) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("uid", ob[0].toString());
			item.put("nickname", ob[1].toString());
			item.put("recordTime", ob[2].toString());
			if (ob[3].toString().equals("Y")) {
				result.add(item);
			} else {
				for (HashMap<String, Object> item2 : result) {
					if (item2.get("uid").toString().equals(item.get("uid").toString())) {
						result.remove(result.indexOf(item2));
						break;
					}
				}
			}
		}
		return result;
	}

	@Override
	public boolean register(User user, Session session) {
		// TODO Auto-generated method stub
		int uid = this.userRepository.save(user).uid;
		if (uid != 0) {
			session.uid = uid;
			sessionRepository.save(session);
			return true;
		} else {
			System.out.println("error! uid=0 !");
			return false;
		}
	}

	@Override
	public boolean isFollowed(String uid, String myUid) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) this.getMyFollowingList(myUid);
		for (HashMap<String, Object> map : list) {
			if (map.get("uid") != null && map.get("uid").equals(uid)) {
				return true;
			}
		}
		return false;
	}

}
