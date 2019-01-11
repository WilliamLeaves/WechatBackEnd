package com.WechatBackEnd.ServiceTest;
import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.WechatBackEnd.Model.User;
import com.WechatBackEnd.Service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Test
	public void getUserTest() {
		Map<String,Object> map=userService.getUser("1");
		Assert.assertThat(map.get("nickname"),is("Jack"));
	}
	@Test
	public void updateUserInfoTest() {
		User user=User.getStub();
		user.nickname="Jack";
		boolean result=userService.updateUserInfo(user);
		Assert.assertEquals(result, Boolean.TRUE);
	}
}
