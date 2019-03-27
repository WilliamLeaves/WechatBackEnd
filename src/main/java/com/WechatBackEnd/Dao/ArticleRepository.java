package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.WechatBackEnd.Model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	@Modifying
	@Query(value = "select user.uid,user.nickname,article.aid,article.title,article.release_time,article.content,article.location,article.pic_link from article,user where user.uid=article.uid order by release_time desc;", nativeQuery = true)
	List getArticleList();
}
