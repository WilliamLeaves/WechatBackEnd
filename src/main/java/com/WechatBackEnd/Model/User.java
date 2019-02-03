package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	public int uid;
	@Column(name = "nickname", columnDefinition = "varchar(255) not null")
	public String nickname;
	@Column(name = "sex", columnDefinition = "enum('男','女') null default '男' ")
	public String sex;
	@Column(name = "college", columnDefinition = "varchar(255) null default '暂无'")
	public String college;
	@Column(name = "major", columnDefinition = "varchar(255) null default '暂无'")
	public String major;
	@Column(name = "register_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String registerTime;
	@Column(name = "entrance_time", columnDefinition = "varchar(255) null default '2000'")
	public String entranceTime;
	@Column(name = "describ", columnDefinition = "varchar(255) null default '暂无'")
	public String describ;

	public User() {

	}

	public static User getStub() {
		User user = new User();
		user.uid = 1;
		user.sex = "男";
		user.nickname = "william";
		user.college = "NJU";
		user.major = "software";
		user.registerTime = "2019-01-01";
		user.entranceTime = "2018";
		user.describ = "";
		return user;
	}
}
