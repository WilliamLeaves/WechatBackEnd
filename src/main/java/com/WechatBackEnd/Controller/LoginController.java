package com.WechatBackEnd.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.WechatBackEnd.Service.LoginService;
import com.WechatBackEnd.Util.Constant;
import com.google.gson.Gson;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		System.out.println("登录运行");
		String sessionKey = "";
		String openId = "";
		String code = req.getParameter("userCode");
		//System.out.println(code);
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constant.APPID + "&secret="
				+ Constant.SECRETKEY + "&js_code=" + code + "&grant_type=authorization_code";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			Map m = new Gson().fromJson(responseEntity.getBody(), Map.class);
			sessionKey = (String) m.get("session_key");
			openId = (String) m.get("openid");
			System.out.println("sessionkey:"+sessionKey);
			System.out.println("openid:"+openId);
			// 服务层自定义登录态
			{
				// 判断是否为新用户
				if (this.loginService.isOldUser(openId)) {
					this.loginService.login(sessionKey, openId);
					res.put("sessionKey", sessionKey);
					res.put("result", "1");
					res.put("message", "登录成功");
				} else {
					res.put("result", "2");
					res.put("message", "首次登录请注册");
					// this.loginService.login(sessionKey, openId);
				}
			}
		} else {
			res.put("result", "-1");
			res.put("message", "登录失败");
		}
		return res;
	}
}
