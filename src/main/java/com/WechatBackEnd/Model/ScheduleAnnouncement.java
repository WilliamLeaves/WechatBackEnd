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

	public static ScheduleAnnouncement parseByObjectArray(Object[] obArray) {
		ScheduleAnnouncement res = new ScheduleAnnouncement();
		// res. =()obArray[];
		res.recordId = (int) obArray[0];
		res.sid = (int) obArray[1];
		res.announcement_title = (String) obArray[2];
		res.announcement_content = (String) obArray[3];
		res.release_time = obArray[4].toString();
		return res;
	}
}
