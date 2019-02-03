package com.WechatBackEnd.ServiceTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.WechatBackEnd.Dao.ScheduleCollectRecordRepository;
import com.WechatBackEnd.Dao.ScheduleCommentRepository;
import com.WechatBackEnd.Dao.ScheduleRepository;
import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Model.ScheduleAnnouncement;
import com.WechatBackEnd.Model.ScheduleCollectRecord;
import com.WechatBackEnd.Model.ScheduleComment;
import com.WechatBackEnd.Service.ScheduleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceTest {
	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private ScheduleCollectRecordRepository scheduleCollectRecordRepository;
	@Autowired
	private ScheduleCommentRepository scheduleCommentRepository;

	@Test
	public void getScheduleTest() {
		Schedule schedule = Schedule.getStub();
		this.scheduleRepository.save(schedule);
		Schedule schTest = this.scheduleService.getSchedule("1");
		Assert.assertEquals(schTest.describe, schedule.describe);
	}

	// @Test
	// public void addScheduleTest() {
	// Schedule schedule = Schedule.getStub();
	// schedule.sid=2;
	// schedule.describe="aaaaaaa";
	// Assert.assertEquals(this.scheduleService.addSchedule(schedule), true);
	// }

	@Test
	public void updateScheduleTest() {
		Schedule schedule = this.scheduleRepository.findById(1).get();
		schedule.describe = "aaaaa";
		Assert.assertEquals(this.scheduleService.updateSchedule(schedule), true);
		schedule.describe = Schedule.getStub().describe;
		this.scheduleService.updateSchedule(schedule);
	}

	@Test
	public void getMyCollectingScheduleListTest() {
		Assert.assertEquals(this.scheduleService.getMyCollectingScheduleList("1").size() != 0, true);
	}

	// @Test
	// public void addCollectScheduleRecordTest() {
	// ScheduleCollectRecord scheduleCollectRecord1 =
	// ScheduleCollectRecord.getStub();
	// scheduleCollectRecord1.recordId = 0;
	// scheduleCollectRecord1.isPositive = "Y";
	// scheduleCollectRecord1.recordTime = "2019-02-06 00:00:00";
	// Assert.assertEquals(true,
	// this.scheduleService.addCollectScheduleRecord(scheduleCollectRecord1));
	// }

	// @Test
	// public void addAnnouncement() {
	// ScheduleAnnouncement scheduleAnnouncement = ScheduleAnnouncement.getStub();
	// scheduleAnnouncement.recordId = 0;
	// Assert.assertEquals(true,
	// this.scheduleService.addAnnouncement(scheduleAnnouncement));
	//
	// }

	@Test
	public void getAnnouncementListTest() {
		Assert.assertEquals(this.scheduleService.getAnnouncementList("1").size() != 0, true);
	}

	@Test
	public void commentTest() {
		ScheduleComment scheduleComment = ScheduleComment.getStub();
		scheduleComment.recordId = 0;
		scheduleComment.sid = 1;
		scheduleComment.uid = 1;
		scheduleComment.commentContent = "abcd";
		Assert.assertEquals(true, this.scheduleService.addScheduleComment(scheduleComment));
		Assert.assertEquals(true, this.scheduleService.getScheduleCommentList("1").size() == 1);
		this.scheduleCommentRepository.deleteByComment(scheduleComment.commentContent);
	}

	@Test
	public void parseTest() {
		this.scheduleService.getAnnouncementList("1");
		Assert.assertEquals(true, true);
	}

}
