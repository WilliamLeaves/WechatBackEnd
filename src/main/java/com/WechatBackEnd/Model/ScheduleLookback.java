package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule_lookback")
public class ScheduleLookback {
	@Id
	@Column(name = "recordid", nullable = false)
	public int recordId;
	@Column(name = "sid", columnDefinition = "int not null")
	public int sid;
	@Column(name = "uid", columnDefinition = "int not null")
	public int uid;
	@Column(name = "record_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String recordTime;
	@Column(name = "content", columnDefinition = "varchar(255) null default '缺失' ")
	public String content;
	public String[] imgList;

	public ScheduleLookback() {

	}

	public static ScheduleLookback getStub() {
		ScheduleLookback record = new ScheduleLookback();
		record.recordId = 1;
		record.sid = 1;
		record.uid = 1;
		record.recordTime = "2019-02-02 12:13:49";
		record.content = "文艺复兴三杰展真的太棒了！";
		return record;
	}
}
