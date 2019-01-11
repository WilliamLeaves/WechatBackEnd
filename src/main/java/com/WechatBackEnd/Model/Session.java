package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "session")
public class Session {
	@Id
	@Column(name = "id", nullable = false)
	public int id;
	@Column(name = "uid", nullable = false)
	public int uid;
	@Column(name = "sessionid", nullable = false)
	public String sessionId;
	@Column(name = "openid", nullable = false)
	public String openId;
	@Column(name = "active_time", nullable = false)
	public String activeTime;

	public Session() {

	}
}
