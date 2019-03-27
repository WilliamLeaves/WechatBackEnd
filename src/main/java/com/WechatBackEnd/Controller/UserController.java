package com.WechatBackEnd.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.WechatBackEnd.Model.FollowUserRecord;
import com.WechatBackEnd.Model.Session;
import com.WechatBackEnd.Model.User;
import com.WechatBackEnd.Service.LoginService;
import com.WechatBackEnd.Service.UserService;
import com.WechatBackEnd.Util.Constant;
import com.WechatBackEnd.Util.TimeUtil;
import com.google.gson.Gson;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String, Object> register(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		String sessionKey = "";
		String openId = "";
		String code = req.getParameter("userCode");
		System.out.println("Acode" + code);
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constant.APPID + "&secret="
				+ Constant.SECRETKEY + "&js_code=" + code + "&grant_type=authorization_code";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			Map m = new Gson().fromJson(responseEntity.getBody(), Map.class);
			sessionKey = (String) m.get("session_key");
			openId = (String) m.get("openid");
			System.out.println("Asessionkey:" + sessionKey);
			System.out.println("Aopenid:" + openId);
		}
		User user = new User();
		user.uid = 0;
		user.nickname = req.getParameter("nickname");
		user.sex = req.getParameter("sex");
		user.college = req.getParameter("college");
		user.major = req.getParameter("major");
		user.entranceTime = req.getParameter("entrance_time");
		user.describ = req.getParameter("describe");
		user.registerTime = TimeUtil.getCurrentTime();
		Session session = new Session();
		session.id = 0;
		session.uid = 0;
		session.activeTime = TimeUtil.getCurrentTime();
		session.openId = openId;
		session.sessionId = sessionKey;

		if (this.userService.register(user, session)) {
			res.put("result", "1");
			res.put("message", "注册成功");
			res.put("data", "");
		} else {
			res.put("result", "-1");
			res.put("message", "注册失败");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/getUser")
	public Map<String, Object> getUser(@RequestHeader("sessionKey") String sessionKey, String uid) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			User user = userService.getUser(uid);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", user.uid);
			map.put("nickname", user.nickname);
			map.put("sex", user.sex);
			map.put("college", user.college);
			map.put("major", user.major);
			map.put("register_time", user.registerTime);
			map.put("entrance_time", user.entranceTime);
			map.put("describe", user.describ);
			res.put("result", "1");
			res.put("message", "用户信息修改成功");
			res.put("data", map);
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/getMyInfo")
	public Map<String, Object> getMyInfo(@RequestHeader("sessionKey") String sessionKey) {
		Map<String, Object> res = new HashMap<String, Object>();
		// findsession
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			User user = userService.getUser(String.valueOf(myUid));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", user.uid);
			map.put("nickname", user.nickname);
			map.put("college", user.college);
			map.put("sex", user.sex);
			map.put("major", user.major);
			map.put("register_time", user.registerTime);
			map.put("entrance_time", user.entranceTime);
			map.put("describe", user.describ);
			res.put("result", "1");
			res.put("message", "获取个人信息成功");
			res.put("data", map);
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/user/updateUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserInfo(HttpServletRequest req) {
		int myUid = this.loginService.findUidBySessionKey(req.getHeader("sessionKey"));
		User user = this.userService.getUser(String.valueOf(myUid));
		user.nickname = req.getParameter("nickname").toString();
		user.sex = req.getParameter("sex").toString();
		user.college = req.getParameter("college").toString();
		user.major = req.getParameter("major").toString();
		user.entranceTime = req.getParameter("entrance_time").toString();
		user.describ = req.getParameter("describe").toString();
		boolean result = userService.updateUserInfo(user);
		Map<String, Object> res = new HashMap<String, Object>();
		if (result) {
			res.put("result", "1");
			res.put("message", "用户信息修改成功");
			res.put("data", "");
		} else {
			res.put("result", "-1");
			res.put("message", "用户信息修改失败");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/user/followUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> followUser(HttpServletRequest req) {
		FollowUserRecord record = new FollowUserRecord();
		// findsession
		String sessionKey = req.getHeader("sessionKey");
		record.uid = this.loginService.findUidBySessionKey(sessionKey);

		record.followUserId = Integer.parseInt(req.getParameter("followed_uid").toString());
		record.isPositive = "Y";
		record.recordTime = TimeUtil.getCurrentTime();
		Map<String, Object> res = new HashMap<String, Object>();
		if (userService.followUser(record)) {
			res.put("result", "1");
			res.put("message", "关注成功");
			res.put("data", "");
		} else {
			res.put("result", "-1");
			res.put("message", "关注失败");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/user/disFollowUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> disFollowUser(HttpServletRequest req) {
		FollowUserRecord record = new FollowUserRecord();

		// findsession
		String sessionKey = req.getHeader("sessionKey");
		record.uid = this.loginService.findUidBySessionKey(sessionKey);

		record.followUserId = Integer.parseInt(req.getParameter("followed_uid").toString());
		record.isPositive = "N";
		record.recordTime = TimeUtil.getCurrentTime();
		Map<String, Object> res = new HashMap<String, Object>();
		if (userService.followUser(record)) {
			res.put("result", "1");
			res.put("message", "取消关注成功");
			res.put("data", "");
		} else {
			res.put("result", "-1");
			res.put("message", "取消关注失败");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/user/getFollowList", method = RequestMethod.GET)
	public Map<String, Object> getFollowList(@RequestHeader("sessionKey") String sessionKey) {
		// findsession
		int uid = this.loginService.findUidBySessionKey(sessionKey);
		Map<String, Object> res = new HashMap<String, Object>();
		if (uid != -1) {
			res.put("result", "1");
			res.put("message", "获取关注列表成功");
			res.put("data", this.userService.getMyFollowingList(String.valueOf(uid)));
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/user/isFollowed", method = RequestMethod.GET)
	public Map<String, Object> isFollowed(@RequestHeader("SessionKey") String sessionKey, String uid) {
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		Map<String, Object> res = new HashMap<String, Object>();
		if (myUid != -1) {

			res.put("result", "1");
			res.put("message", "获取关注列表成功");
			res.put("data", this.userService.isFollowed(uid, String.valueOf(myUid)));
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}
}
