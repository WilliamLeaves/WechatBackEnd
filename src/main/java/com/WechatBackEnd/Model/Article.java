package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
	@Id
	@GeneratedValue
	public int aid;
	@Column(name = "uid", columnDefinition = "int not null")
	public int uid;
	@Column(name = "title", columnDefinition = "varchar(255) null default '缺失'")
	public String title;
	@Column(name = "release_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String release_time;
	@Column(name = "content", columnDefinition = "varchar(255) null default '缺失'")
	public String content;
	@Column(name = "pic_link", columnDefinition = "varchar(255) null default '缺失'")
	public String pic_link;
	@Column(name = "location", columnDefinition = "varchar(255) null default '缺失'")
	public String location;

	public Article() {

	}

	public static Article getStub() {
		Article stub = new Article();
		stub.aid = 0;
		stub.uid = 1;
		stub.title = "abc";
		stub.content = "abcde";
		stub.release_time = "2019-03-03 23:52:16";
		stub.location = "南京";
		return stub;
	}
}
