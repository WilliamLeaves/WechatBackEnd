package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ArticleComment;

@Transactional
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {
//	@Modifying
//	@Query(value = "", nativeQuery = true)
//	public List getCommentList();
}
