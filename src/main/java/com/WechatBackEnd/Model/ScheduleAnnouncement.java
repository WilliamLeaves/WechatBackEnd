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
	@Column(name = "sid", columnDefinition = "int not null")
	public int sid;
	@Column(name = "announcement_title", columnDefinition = "varchar(255) null default '缺失'")
	public String announcement_title;
	@Column(name = "announcement_content", columnDefinition = "varchar(255) null default '缺失'")
	public String announcement_content;
	@Column(name = "release_time", columnDefinition = "datetime null default '2000-01-01 00:00:00' ")
	public String release_time;

	public ScheduleAnnouncement() {

	}

	public static ScheduleAnnouncement getStub() {
		ScheduleAnnouncement record = new ScheduleAnnouncement();
		record.recordId = 1;
		record.sid = 1;
		record.release_time = "2019-01-31 14:00:00";
		return record;
	}
}
