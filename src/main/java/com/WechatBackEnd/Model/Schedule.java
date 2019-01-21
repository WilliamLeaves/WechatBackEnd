package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Schedule {
	@Id
	@GeneratedValue
	public int sid;
	@Column(nullable = false)
	public int uid;
	@Column(nullable = true)
	public String start_time;
	@Column(nullable = true)
	public String recruit_start_time;
	@Column(nullable = true)
	public String recruit_end_time;
	@Column(nullable = true)
	public String execute_time;
	@Column(nullable = true)
	public String end_time;
	@Column(nullable = true)
	public String title;
	@Column(nullable = true)
	public String target;
	@Column(name = "describ", nullable = true)
	public String describe;
	@Column(nullable = true)
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
		stub.partookNum=15;
		return stub;
	}
}
