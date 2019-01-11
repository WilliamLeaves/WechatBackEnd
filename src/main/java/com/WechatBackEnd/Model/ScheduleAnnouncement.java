package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule_announcement")
public class ScheduleAnnouncement {
	@Id
	@Column(name = "recordid", nullable = false)
	public int recordId;
	@Column(name = "sid", nullable = false)
	public int sid;
	@Column(name = "announcement_title", nullable = true)
	public int announcement_title;
	@Column(name = "announcement_content", nullable = true)
	public int announcement_content;
	@Column(name = "release_time", nullable = true)
	public String release_time;

	public ScheduleAnnouncement() {

	}

	public static ScheduleAnnouncement getStub() {
		ScheduleAnnouncement record=new ScheduleAnnouncement();
		record.recordId=1;
		record.sid=1;
		record.release_time="";
		return record;
	}
}
