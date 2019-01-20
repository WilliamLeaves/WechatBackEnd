package com.WechatBackEnd.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.WechatBackEnd.Model.FollowUserRecord;
import com.WechatBackEnd.Model.User;
import com.WechatBackEnd.Service.UserService;
import com.WechatBackEnd.Util.TimeUtil;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/getUser")
	public Map<String, Object> getUser(String uid) {
		Map<String, Object> res = new HashMap<String, Object>();
		return userService.getUser(uid);
	}

	@RequestMapping(value = "/getMyInfo")
	public Map<String, Object> getMyInfo() {
		Map<String, Object> res = new HashMap<String, Object>();
		// findsession
		String myUid = "";
		return userService.getMyInfo(myUid);
	}

	@RequestMapping(value = "/user/updateUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public String updateUserInfo(HttpServletRequest req) {
		User user = new User();
		user.uid = Integer.parseInt(req.getAttribute("uid").toString());
		user.nickname = req.getAttribute("nickname").toString();
		user.college = req.getAttribute("major").toString();
		user.entranceTime = req.getAttribute("entrance_time").toString();
		user.describ = req.getAttribute("describe").toString();
		boolean result = userService.updateUserInfo(user);
		if (result) {
			return "用户信息修改成功";
		} else {
			return "用户信息修改失败";
		}
	}

	@RequestMapping(value = "/user/followUser", method = RequestMethod.POST)
	@ResponseBody
	public String followUser(HttpServletRequest req) {
		FollowUserRecord record = new FollowUserRecord();
		// findsession
		record.uid = 1;
		record.followUserId = Integer.parseInt(req.getAttribute("followed_uid").toString());
		record.isPositive = "Y";
		record.recordTime = TimeUtil.getCurrentTime();
		if (userService.followUser(record)) {
			return "关注成功";
		} else {
			return "关注失败";
		}
	}

	@RequestMapping(value = "/user/disFollowUser", method = RequestMethod.POST)
	@ResponseBody
	public String disFollowUser(HttpServletRequest req) {
		FollowUserRecord record = new FollowUserRecord();
		// findsession
		record.uid = 1;
		record.followUserId = Integer.parseInt(req.getAttribute("followed_uid").toString());
		record.isPositive = "N";
		record.recordTime = TimeUtil.getCurrentTime();
		if (userService.followUser(record)) {
			return "取消关注成功";
		} else {
			return "取消关注失败";
		}
	}

	@RequestMapping(value = "/user/getFollowList", method = RequestMethod.GET)
	public List getFollowList() {
		// findsession
		String myUid = "";
		return this.userService.getMyFollowingList(myUid);
	}
}
