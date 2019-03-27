package com.WechatBackEnd.Service;

import java.util.HashMap;
import java.util.List;

import com.WechatBackEnd.Model.Article;
import com.WechatBackEnd.Model.ArticleComment;
import com.WechatBackEnd.Model.ArticleLike;

public interface ArticleService {
	public HashMap<String, Object> getArticle(int aid);

	public boolean createArticle(Article article);

	public boolean updateArticle(Article article);

	public List getArticleList();

	public boolean likeArticle(ArticleLike al);

	public boolean isLikedArticle(String aid, String uid);

	public List getArticleCommentList(String aid);

	public boolean createArticleComment(ArticleComment articleComment);
}
