package com.WechatBackEnd.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WechatBackEnd.Dao.SessionRepository;
import com.WechatBackEnd.Model.Session;
import com.WechatBackEnd.Model.User;
import com.WechatBackEnd.Service.LoginService;
import com.WechatBackEnd.Util.TimeUtil;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public boolean isOldUser(String openId) {
		// TODO Auto-generated method stub
		if (this.sessionRepository.findByOpenId(openId).isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void login(String sessionKey, String openId) {
		// TODO Auto-generated method stub
		Session session = new Session();
		if (this.sessionRepository.findByOpenId(openId).size() != 0) {
			Session pastSession = this.sessionRepository.findByOpenId(openId).get(0);
			session.id = 0;
			session.uid = pastSession.uid;
			session.openId = openId;
			session.sessionId = sessionKey;
			session.activeTime = TimeUtil.getCurrentTime();
			this.sessionRepository.save(session);
		}

	}

	@Override
	public int findUidBySessionKey(String sessionKey) {
		// TODO Auto-generated method stub
		System.out.println("sessionKey in service: " + sessionKey);
		Session session = this.sessionRepository.findUidBySessionKey(sessionKey);
		int uid = session.uid;
		if (!TimeUtil.isLessThanActiveTime(session.activeTime, TimeUtil.getCurrentTime())) {
			return uid;
		} else {
			return -1;
		}
	}

}
