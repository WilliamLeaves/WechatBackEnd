package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "session")
public class Session {
	@Id
	@Column(name = "id")
	public int id;
	@Column(name = "uid", columnDefinition = "int not null")
	public int uid;
	@Column(name = "sessionid", columnDefinition = "varchar(255) not null")
	public String sessionId;
	@Column(name = "openid", columnDefinition = "varchar(255) not null")
	public String openId;
	@Column(name = "active_time", columnDefinition = "datetime not null")
	public String activeTime;

	public Session() {

	}

	public static Session parseByObjectArray(Object[] obArray) {
		Session res = new Session();
		res.id = (int) obArray[0];
		res.uid = (int) obArray[1];
		res.sessionId = obArray[2].toString();
		res.openId = obArray[3].toString();
		res.activeTime = obArray[4].toString();
		return res;
	}
}
