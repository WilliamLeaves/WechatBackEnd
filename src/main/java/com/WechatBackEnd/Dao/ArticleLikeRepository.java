package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ArticleLike;
@Transactional
public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Integer> {
	@Modifying
	@Query(value = "select * from article_like where uid=? and aid=? order by record_time desc", nativeQuery = true)
	List findLikeList(String myUid,String aid);
}
