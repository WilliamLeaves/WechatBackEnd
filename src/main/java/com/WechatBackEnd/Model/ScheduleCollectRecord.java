package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "collect_schedule")
public class ScheduleCollectRecord {
	@Id
	@Column(name = "recordid", nullable = false)
	public int recordId;
	@Column(name = "uid", columnDefinition = "int not null")
	public int uid;
	@Column(name = "sid", columnDefinition = "int not null")
	public int sid;
	@Column(name = "record_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String recordTime;
	@Column(name = "is_positive", columnDefinition = "enum('Y','N') null default 'Y'")
	public String isPositive;

	public ScheduleCollectRecord() {

	}

	public static ScheduleCollectRecord getStub() {
		ScheduleCollectRecord record = new ScheduleCollectRecord();
		record.recordId = 1;
		record.uid = 1;
		record.sid = 1;
		record.recordTime = "2019-01-27 16:10:53";
		record.isPositive = "Y";
		return record;
	}
}
