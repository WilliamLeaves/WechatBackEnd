package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.WechatBackEnd.Util.TimeUtil;

@Entity
public class Schedule {
	@Id
	@GeneratedValue
	public int sid;
	@Column(name = "uid", columnDefinition = "int not null")
	public int uid;
	@Column(name = "start_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String start_time;
	@Column(name = "recruit_start_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String recruit_start_time;
	@Column(name = "recruit_end_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String recruit_end_time;
	@Column(name = "execute_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String execute_time;
	@Column(name = "end_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String end_time;
	@Column(name = "title", columnDefinition = "varchar(255) null default '缺失'")
	public String title;
	@Column(name = "target", columnDefinition = "varchar(255) null default '缺失'")
	public String target;
	@Column(name = "describ", columnDefinition = "varchar(255) null default '缺失'")
	public String describe;
	@Column(name = "meet_place", columnDefinition = "varchar(255) null default '缺失'")
	public String meet_place;
	@Column(name = "partookNum", columnDefinition = "int null default 0")
	public int partookNum;

	public Schedule() {
	}

	public static Schedule getStub() {
		Schedule stub = new Schedule();
		stub.sid = 1;
		stub.uid = 1;
		stub.start_time = "2019-01-25 12:53:22";
		stub.end_time = "2019-02-01 16:00:00";
		stub.recruit_start_time = "2019-01-26 00:00:00";
		stub.recruit_end_time = "2019-01-30 23:59:59";
		stub.execute_time = "2019-02-01 13:30:00";
		stub.title = "【南大出游求组】2.1下午南博看展";
		stub.meet_place = "南大鼓楼校区汉口路门";
		stub.target = "南京博物院";
		stub.describe = "二月一日下午一点半出发去南京博物院参观，行程大概两个半小时，欢迎南大的同学参加~";
		stub.partookNum = 15;
		return stub;
	}

	public String timeRationalityJudge() {
		String message = "";
		boolean isRationality = true;
		if (TimeUtil.isEarlierThan(TimeUtil.getCurrentTime(), this.start_time)) {
			message += "startTimeError:计划发起时间在当前时间之后;\r\n";
			isRationality = false;
		}
		if (TimeUtil.isEarlierThan(this.recruit_start_time, this.start_time)) {
			message += "recruitStartTimeError:计划开始时间在计划报名开始时间之前;\r\n";
			isRationality = false;
		}
		if (TimeUtil.isEarlierThan(this.recruit_end_time, this.recruit_start_time)) {
			message += "recruitEndTimeError:计划报名开始时间在计划报名结束时间之前;\r\n";
			isRationality = false;
		}
		if (TimeUtil.isEarlierThan(this.execute_time, this.recruit_end_time)) {
			message += "executeTimeError:计划报名结束时间在计划执行时间之前;\r\n";
			isRationality = false;
		}
		if (TimeUtil.isEarlierThan(this.end_time, this.execute_time)) {
			message += "endTimeError:计划执行时间在计划结束时间之后;\r\n";
			isRationality = false;
		}
		return message;
	}

	public String getStatus() {
		String status = "";
		String now = TimeUtil.getCurrentTime();
		if (TimeUtil.isEarlierThan(this.end_time, now)) {
			status = "已结束";
		} else if (TimeUtil.isEarlierThan(this.execute_time, now)) {
			status = "正在进行";
		} else if (TimeUtil.isEarlierThan(this.recruit_end_time, now)) {
			status = "报名截止";
		} else if (TimeUtil.isEarlierThan(this.recruit_start_time, now)) {
			status = "报名中";
		} else if (TimeUtil.isEarlierThan(this.start_time, now)) {
			status = "尚未开始报名";
		}
		return status;
	}

	public static Schedule parseByObjectArray(Object[] obArray) {
		Schedule res = new Schedule();
		res.sid = (int) obArray[0];
		res.uid = (int) obArray[1];
		res.start_time = obArray[2].toString();
		res.recruit_start_time = obArray[3].toString();
		res.recruit_end_time = obArray[4].toString();
		res.execute_time = obArray[5].toString();
		res.end_time = obArray[6].toString();
		res.title = obArray[7].toString();
		res.target = obArray[8].toString();
		res.describe = obArray[9].toString();
		res.meet_place = obArray[10].toString();
		// res.link=obArray[11].toString();
		res.partookNum = (int) obArray[12];
		return res;
	}
}
