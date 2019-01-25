package com.WechatBackEnd.ServiceTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.WechatBackEnd.Dao.ScheduleRepository;
import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Service.ScheduleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceTest {
	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Test
	public void getScheduleTest() {
		Schedule schedule = Schedule.getStub();
		this.scheduleRepository.save(schedule);
		Schedule schTest = this.scheduleService.getSchedule("1");
		Assert.assertEquals(schTest.describe, schedule.describe);
	}
}
