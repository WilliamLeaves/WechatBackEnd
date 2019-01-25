package com.WechatBackEnd.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.WechatBackEnd.Service.LoginService;
import com.WechatBackEnd.Service.PartakeService;

@RestController
public class PartakeController {
	@Autowired
	private PartakeService partakeService;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/schedule/changeScheduleOwner",method=RequestMethod.POST)
	public Map<String,Object> changeScheduleOwner(HttpServletRequest req){
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getParameter("sessionKey"));
		if (myUid != -1) {
			String uid=req.getParameter("uid");
			String sid=req.getParameter("sid");
			String new_uid=req.getParameter("new _uid");
			if(this.partakeService.changeScheduleOwner(uid,sid,new_uid)) {
				res.put("result", "1");
				res.put("message", "转让出游计划成功");
				res.put("data", "");
			}else {
				res.put("result", "-1");
				res.put("message", "转让出游计划成功");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}
	
	@RequestMapping(value="/schedule/partakeSchedule",method=RequestMethod.GET)
	public Map<String,Object> partakeSchedule(@RequestHeader("sessionKey")String sessionKey,String sid){
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			if(this.partakeService.partakeSchedule(String.valueOf(myUid), sid)) {
				res.put("result", "1");
				res.put("message", "提交申请成功");
				res.put("data", "");
			}else {
				res.put("result", "-1");
				res.put("message", "提交申请失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}
	
	@RequestMapping(value="/schedule/quitSchedule",method=RequestMethod.GET)
	public Map<String,Object> quitSchedule(@RequestHeader("sessionKey")String sessionKey,String sid){
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			if(this.partakeService.quitSchedule(String.valueOf(myUid), sid)) {
				res.put("result", "1");
				res.put("message", "退出出游计划成功");
				res.put("data", "");
			}else {
				res.put("result", "-1");
				res.put("message", "退出出游计划失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}
	
	
	@RequestMapping(value="/schedule/receivePartake",method=RequestMethod.GET)
	public Map<String,Object> receivePartake(@RequestHeader("sessionKey")String sessionKey,String sid){
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			if(this.partakeService.receivePartake(String.valueOf(myUid), sid)) {
				res.put("result", "1");
				res.put("message", "通过申请成功");
				res.put("data", "");
			}else {
				res.put("result", "-1");
				res.put("message", "通过申请失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}
	
	
	@RequestMapping(value="/schedule/rejectPartake",method=RequestMethod.GET)
	public Map<String,Object> rejectPartake(@RequestHeader("sessionKey")String sessionKey,String sid){
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			if(this.partakeService.rejectPartake(String.valueOf(myUid), sid)) {
				res.put("result", "1");
				res.put("message", "拒绝申请成功");
				res.put("data", "");
			}else {
				res.put("result", "-1");
				res.put("message", "拒绝申请失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}
	
	@RequestMapping(value="/schedule/removePartake",method=RequestMethod.GET)
	public Map<String,Object> removePartake(@RequestHeader("sessionKey")String sessionKey,String sid){
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			if(this.partakeService.removePartake(String.valueOf(myUid), sid)) {
				res.put("result", "1");
				res.put("message", "移除参与用户成功");
				res.put("data", "");
			}else {
				res.put("result", "-1");
				res.put("message", "移除参与用户失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}
}
