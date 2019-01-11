package com.WechatBackEnd.Controller;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.WechatBackEnd.Util.Constant;

@RestController
public class LoginController {
	@RequestMapping(value=	"/",method=RequestMethod.POST)
	public String login(@RequestBody Map<String,Object> userCode) {
		System.out.println("登录运行");
		String sessionData = "";
		String code =(String)userCode.get("userCode");
		System.out.println(code);
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+Constant.APPID+
				 "&secret="+Constant.SECRETKEY+"&js_code="+ code +"&grant_type=authorization_code";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			sessionData=responseEntity.getBody();
			System.out.println("请求成功，session： "+sessionData);
			//服务层自定义登录态
			{
				
			}
		}else {
			System.out.println("请求失败");
		}
		return "登录成功";
	}
}
