package com.WechatBackEnd.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WechatBackEnd.Dao.ArticleCommentRepository;
import com.WechatBackEnd.Dao.ArticleLikeRepository;
import com.WechatBackEnd.Dao.ArticleRepository;
import com.WechatBackEnd.Dao.UserRepository;
import com.WechatBackEnd.Model.Article;
import com.WechatBackEnd.Model.ArticleComment;
import com.WechatBackEnd.Model.ArticleLike;
import com.WechatBackEnd.Model.User;
import com.WechatBackEnd.Service.ArticleService;

@Service
public class ArticleSeviceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ArticleLikeRepository articleLikeRepository;
	@Autowired
	private ArticleCommentRepository articleCommentRepository;

	@Override
	public HashMap<String, Object> getArticle(int aid) {
		// TODO Auto-generated method stub
		Article article = this.articleRepository.findById(aid).get();

		User user = this.userRepository.findById(article.uid).get();
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("uid", user.uid);
		res.put("aid", article.aid);
		res.put("title", article.title);
		res.put("content", article.content);
		res.put("release_time", article.release_time.toString());
		res.put("nickname", user.nickname);
		res.put("location", article.location);
		res.put("pic_link", article.pic_link);
		return res;
	}

	@Override
	public boolean createArticle(Article article) {
		// TODO Auto-generated method stub
		return this.articleRepository.save(article) != null ? true : false;
	}

	@Override
	public boolean updateArticle(Article article) {
		// TODO Auto-generated method stub
		return this.articleRepository.save(article) != null ? true : false;
	}

	@Override
	public List getArticleList() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> res = new ArrayList<HashMap<String, Object>>();
		List<Object[]> list = this.articleRepository.getArticleList();
		for (Object[] ob : list) {
			HashMap<String, Object> col = new HashMap<String, Object>();
			col.put("uid", ob[0]);
			col.put("nickname", ob[1]);
			col.put("aid", ob[2]);
			col.put("title", ob[3]);
			col.put("release_time", ob[4]);
			col.put("content", ob[5]);
			col.put("location", ob[6]);
			col.put("pic_link", ob[7]);
			res.add(col);
		}
		return res;
	}

	@Override
	public boolean likeArticle(ArticleLike al) {
		// TODO Auto-generated method stub
		return this.articleLikeRepository.save(al).recordId != 0 ? true : false;
	}

	@Override
	public boolean isLikedArticle(String aid, String uid) {
		// TODO Auto-generated method stub
		List<Object[]> list = this.articleLikeRepository.findLikeList(uid, aid);
		if (list.size() == 0) {
			return false;
		} else {
			return list.get(0)[1].equals("true") ? true : false;
		}
	}

	@Override
	public List getArticleCommentList(String aid) {
		// TODO Auto-generated method stub
		return this.articleCommentRepository.findAll();
	}

	@Override
	public boolean createArticleComment(ArticleComment articleComment) {
		// TODO Auto-generated method stub
		return this.articleCommentRepository.save(articleComment) != null ? true : false;
	}

}
