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
	@Column(nullable = false)
	public String nickname;
	@Column(nullable = true)
	public String college;
	@Column(nullable = true)
	public String major;
	@Column(name="register_time",nullable = true)
	public String registerTime;
	@Column(name="entrance_time",nullable = true)
	public String entranceTime;
	@Column(nullable = true)
	public String describ;

	public User() {

	}

	public static User getStub() {
		User user = new User();
		user.uid = 1;
		user.nickname = "william";
		user.college = "NJU";
		user.major = "software";
		user.registerTime = "2019-01-01";
		user.entranceTime = "2018-09-01";
		user.describ = "";
		return user;
	}
}
