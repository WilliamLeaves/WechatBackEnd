package com.WechatBackEnd.ServiceTest;

import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.WechatBackEnd.Dao.FollowUserRecordRepository;
import com.WechatBackEnd.Model.FollowUserRecord;
import com.WechatBackEnd.Model.User;
import com.WechatBackEnd.Service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Autowired
	private FollowUserRecordRepository followUserRecordRepository;

	@Test
	public void getUserTest() {
		Map<String, Object> map = userService.getUser("1");
		Assert.assertThat(map.get("nickname"), is("william"));
	}

	@Test
	public void updateUserInfoTest() {
		User user = User.getStub();
		String testStr = String.valueOf(Math.random());
		user.describ = testStr;
		userService.updateUserInfo(user);
		Map<String, Object> userInfo = userService.getUser("1");
		String resultStr = (String) userInfo.get("describe");
		Assert.assertEquals(testStr, resultStr);
	}

	@Test
	public void followUserTest() {
		FollowUserRecord record = FollowUserRecord.getStub();
		record.isPositive = "Y";
		record.recordTime = "2019-01-26 15:15:15";
		userService.followUser(record);
		FollowUserRecord testRecord = followUserRecordRepository.findById(1).get();
		Assert.assertEquals(record.recordTime, testRecord.recordTime);
	}

	@Test
	public void disFollowUserTest() {
		FollowUserRecord record = FollowUserRecord.getStub();
		record.isPositive = "N";
		record.recordId = 2;
		record.recordTime = "2019-01-26 16:17:18";
		userService.followUser(record);
		FollowUserRecord testRecord = followUserRecordRepository.findById(2).get();
		Assert.assertEquals(record.recordTime, testRecord.recordTime);
	}

	@Test
	public void getFollowListTest() {
		int num = userService.getMyFollowingList("1").size();
		Assert.assertEquals(num, 1);
	}
}
