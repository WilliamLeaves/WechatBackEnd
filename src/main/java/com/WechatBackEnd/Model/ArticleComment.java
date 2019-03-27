package com.WechatBackEnd.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article_comment")
public class ArticleComment {
	@Id
	@Column(name = "recordid", nullable = false)
	public int recordId;
	@Column(name = "aid", columnDefinition = "int not null")
	public int aid;
	@Column(name = "uid", columnDefinition = "int not null")
	public int uid;
	@Column(name = "at_uid", columnDefinition = "int null")
	public int atUid;
	@Column(name = "comment_content", columnDefinition = "varchar(255) null default '缺失'")
	public String commentContent;
	@Column(name = "record_time", columnDefinition = "datetime null default '2000-01-01 00:00:00'")
	public String recordTime;
}
