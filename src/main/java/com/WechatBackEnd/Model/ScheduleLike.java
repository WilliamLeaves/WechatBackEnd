package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule_like")
public class ScheduleLike {
	@Id
	@Column(name = "recordid", nullable = false)
	public int recordId;
	@Column(name = "sid", columnDefinition = "int not null")
	public int sid;
	@Column(name = "uid", columnDefinition = "int not null")
	public int uid;
	@Column(name = "record_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String recordTime;
	@Column(name = "is_positive", columnDefinition = "enum('Y','N') null default 'Y' ")
	public String isPositive;

	public ScheduleLike() {

	}

	public static ScheduleLike getStub() {
		ScheduleLike record = new ScheduleLike();
		record.recordId = 1;
		record.sid = 1;
		record.uid = 1;
		record.recordTime = "2019-01-29 19:16:03";
		record.isPositive = "Y";
		return record;
	}
}
