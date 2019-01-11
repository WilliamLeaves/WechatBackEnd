package com.WechatBackEnd.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.WechatBackEnd.Model.User;
import com.WechatBackEnd.Service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/getUser")
	public Map<String,Object> getUser(String uid){
		Map<String,Object> res=new HashMap<String,Object>();
		return userService.getUser(uid);
	}
	
	@RequestMapping(value="/user/updateUserInfo",method=RequestMethod.POST)
	@ResponseBody
	public boolean updateUserInfo(HttpServletRequest req){
		User user=new User();
		user.uid=Integer.parseInt(req.getAttribute("uid").toString());
		user.nickname=req.getAttribute("nickname").toString();
		user.college=req.getAttribute("major").toString();
		user.entrance_time=req.getAttribute("entrance_time").toString();
		user.describ=req.getAttribute("describe").toString();
		boolean result=userService.updateUserInfo(user);
		return result;
	}
}
