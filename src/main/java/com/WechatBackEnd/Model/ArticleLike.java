package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article_like")
public class ArticleLike {
	@Id
	@Column(name = "recordid", nullable = false)
	public int recordId;
	@Column(name = "aid", columnDefinition = "int not null")
	public int aid;
	@Column(name = "uid", columnDefinition = "int not null")
	public int uid;
	@Column(name = "record_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String recordTime;
	@Column(name = "is_positive", columnDefinition = "enum('Y','N') null default 'Y' ")
	public String isPositive;
}
