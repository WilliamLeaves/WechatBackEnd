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
	@Column(name = "sid", columnDefinition = "int not null")
	public int sid;
	@Column(name = "uid", columnDefinition = "int not null")
	public int uid;
	@Column(name = "at_uid", columnDefinition = "int null")
	public int atUid;
	@Column(name = "comment_content", columnDefinition = "varchar(255) null default '缺失'")
	public String commentContent;
	@Column(name = "record_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
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

	public static ScheduleComment parseByObjectArray(Object[] obArray) {
		ScheduleComment res = new ScheduleComment();
		res.recordId = (int) obArray[0];
		res.sid = (int) obArray[1];
		res.uid = (int) obArray[2];
		res.atUid = (int) obArray[3];
		res.commentContent = obArray[4].toString();
		res.recordTime = obArray[5].toString();
		return res;
	}
}
