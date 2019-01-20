package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "follow_user")
public class FollowUserRecord {
	@Id
	@Column(name = "recordid", nullable = false)
	public int recordId;
	@Column(name = "uid", nullable = false)
	public int uid;
	@Column(name = "followed_uid", nullable = false)
	public int followUserId;
	@Column(name = "record_time", nullable = false)
	public String recordTime;
	@Column(name = "is_positive",columnDefinition="enum('Y','N') null default 'Y' ")
	public String isPositive;

	public FollowUserRecord() {

	}

	public static FollowUserRecord getStub() {
		FollowUserRecord record = new FollowUserRecord();
		record.recordId = 1;
		record.uid = 1;
		record.followUserId = 2;
		record.recordTime = "2019-01-24 12:00:00";
		record.isPositive = "Y";
		return record;
	}

}
