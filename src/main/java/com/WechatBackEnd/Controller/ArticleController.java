package com.WechatBackEnd.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.WechatBackEnd.Model.Article;
import com.WechatBackEnd.Model.ArticleComment;
import com.WechatBackEnd.Model.ArticleLike;
import com.WechatBackEnd.Service.ArticleService;
import com.WechatBackEnd.Service.LoginService;
import com.WechatBackEnd.Util.TimeUtil;

@RestController
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/article/createArticle", method = RequestMethod.POST)
	public Map<String, Object> createArticle(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getHeader("sessionKey"));
		if (myUid != -1) {
			Article article = new Article();
			article.aid = 0;
			article.uid = myUid;
			article.title = req.getParameter("title").toString();
			article.content = req.getParameter("content").toString();
			article.location = req.getParameter("location");
			article.pic_link = req.getParameter("pic_link");
			article.release_time = TimeUtil.getCurrentTime();
			if (this.articleService.createArticle(article)) {
				res.put("result", "1");
				res.put("message", "修改成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "修改失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/article/updateArticle", method = RequestMethod.POST)
	public Map<String, Object> updateArticle(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getHeader("sessionKey"));
		if (myUid != -1) {
			Article article = new Article();
			article.aid = 0;
			article.uid = Integer.parseInt(req.getParameter("uid"));
			article.title = req.getParameter("title").toString();
			article.content = req.getParameter("content").toString();
			article.release_time = TimeUtil.getCurrentTime();
			article.location = req.getParameter("location");
			article.pic_link = req.getParameter("pic_link");
			if (this.articleService.createArticle(article)) {
				res.put("result", "1");
				res.put("message", "发布成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "发布失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/article/getArticle", method = RequestMethod.GET)
	public Map<String, Object> getArticle(@RequestHeader("sessionKey") String sessionKey, String aid) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			res.put("result", "1");
			res.put("message", "获取成功");
			res.put("data", this.articleService.getArticle(Integer.parseInt(aid)));
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/article/getArticleList", method = RequestMethod.GET)
	public Map<String, Object> getArticleList(@RequestHeader("sessionKey") String sessionKey) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			res.put("result", "1");
			res.put("message", "获取成功");
			res.put("data", this.articleService.getArticleList());
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/article/likeArticle", method = RequestMethod.GET)
	public Map<String, Object> likeArticle(@RequestHeader("sessionKey") String sessionKey, String aid,
			String is_positive) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			ArticleLike al = new ArticleLike();
			al.aid = Integer.parseInt(aid);
			al.uid = myUid;
			al.isPositive = is_positive;
			al.recordId = 0;
			al.recordTime = TimeUtil.getCurrentTime();
			if (this.articleService.likeArticle(al)) {
				res.put("result", "1");
				res.put("message", is_positive.equals("true") ? "文章点赞成功" : "取消点赞成功");
				res.put("data", "");
			}
			;
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/article/islikedArticle", method = RequestMethod.GET)
	public Map<String, Object> isLikedArticle(@RequestHeader("sessionKey") String sessionKey, String aid) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			res.put("result", "1");
			res.put("message", "");
			res.put("data", this.articleService.isLikedArticle(String.valueOf(myUid), aid));

		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/article/createArticleComment", method = RequestMethod.POST)
	public Map<String, Object> createArticleComment(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getHeader("sessionKey"));
		if (myUid != -1) {
			ArticleComment articleComment = new ArticleComment();
			articleComment.recordId = 0;
			articleComment.recordTime = TimeUtil.getCurrentTime();
			articleComment.commentContent = req.getParameter("content");
			articleComment.aid = Integer.parseInt(req.getParameter("aid"));
			articleComment.uid = myUid;
			if (this.articleService.createArticleComment(articleComment)) {
				res.put("result", "1");
				res.put("message", "文章评论成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "评论失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/article/getArticleCommentList", method = RequestMethod.GET)
	public Map<String, Object> getArticleCommentList(@RequestHeader("sessionKey") String sessionKey, String aid) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			res.put("result", "1");
			res.put("message", "");
			res.put("data", this.articleService.getArticleCommentList(aid));
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}
}
