package com.WechatBackEnd.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.WechatBackEnd.Model.FollowUserRecord;
import com.WechatBackEnd.Model.User;
import com.WechatBackEnd.Service.LoginService;
import com.WechatBackEnd.Service.UserService;
import com.WechatBackEnd.Util.TimeUtil;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	LoginService loginService;

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

	@RequestMapping(value = "/user/updateUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserInfo(HttpServletRequest req) {
		User user = new User();
		user.uid = Integer.parseInt(req.getAttribute("uid").toString());
		user.nickname = req.getAttribute("nickname").toString();
		user.college = req.getAttribute("major").toString();
		user.entranceTime = req.getAttribute("entrance_time").toString();
		user.describ = req.getAttribute("describe").toString();
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

		record.followUserId = Integer.parseInt(req.getAttribute("followed_uid").toString());
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
			String myUid = String.valueOf(this.loginService.findUidBySessionKey(sessionKey));
			res.put("result", "1");
			res.put("message", "获取关注列表成功");
			res.put("data", this.getFollowList(myUid));
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}
}
