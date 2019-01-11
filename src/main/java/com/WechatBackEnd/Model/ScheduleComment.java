package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule_comment")
public class ScheduleComment {
	@Id
	@Column(name = "recordid", nullable = false)
	public int recordId;
	@Column(name = "sid", nullable = false)
	public int sid;
	@Column(name = "uid", nullable = false)
	public int uid;
	@Column(name = "at_uid", nullable = true)
	public int atUid;
	@Column(name = "commentContent", nullable = true)
	public String commentContent;
	@Column(name = "recordTime", nullable = true)
	public String recordTime;

	public ScheduleComment() {

	}

	public static ScheduleComment getStub() {
		ScheduleComment record = new ScheduleComment();
		record.recordId = 1;
		record.sid = 1;
		record.uid = 1;
		record.atUid = 0;
		record.recordTime = "2019-01-28 10:20:34";
		record.commentContent = "超想看文艺复兴三杰展的！但是那个时候已经放假回家了QAQ";
		return record;
	}
}
