package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule_partake")
public class SchedulePartake {
	@Id
	@Column(name = "recordid", unique = true, nullable = false)
	public int recordId;
	@Column(name = "sid", columnDefinition="int not null")
	public int sid;
	@Column(name = "uid", columnDefinition="int not null")
	public int uid;
	@Column(name = "record_time", columnDefinition="datetime null default '2000-01-01 00:00:00'")
	public String recordTime;
	@Column(name = "status", columnDefinition="enum('proposing','rejected','partook','quit','removed') not null default 'proposing' ")
	public String status;

	public SchedulePartake() {

	}

	public static SchedulePartake getStub() {
		SchedulePartake record = new SchedulePartake();
		record.recordId = 1;
		record.sid = 1;
		record.uid = 1;
		record.recordTime = "2019-01-30 18:09:16";
		record.status = "partook";
		return record;
	}
}
