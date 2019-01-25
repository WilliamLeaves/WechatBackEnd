package com.WechatBackEnd.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Model.ScheduleAnnouncement;
import com.WechatBackEnd.Model.ScheduleCollectRecord;
import com.WechatBackEnd.Model.ScheduleComment;
import com.WechatBackEnd.Model.ScheduleLike;
import com.WechatBackEnd.Model.ScheduleLookback;
import com.WechatBackEnd.Service.LoginService;
import com.WechatBackEnd.Service.ScheduleService;
import com.WechatBackEnd.Util.TimeUtil;

@RestController
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/getSchedule", method = RequestMethod.GET)
	public Map<String, Object> getSchedule(@RequestHeader("sessionKey") String sessionKey, String sid) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			Schedule schedule = this.scheduleService.getSchedule(sid);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("sid", schedule.sid);
			data.put("target", schedule.target);
			data.put("meet_place", schedule.meet_place);
			data.put("start_time", schedule.start_time);
			data.put("recruit_start_time", schedule.recruit_start_time);
			data.put("recruit_end_time", schedule.recruit_end_time);
			data.put("execute_time", schedule.execute_time);
			data.put("end_time", schedule.end_time);
			data.put("title", schedule.title);
			data.put("content", schedule.describe);
			data.put("uid", schedule.uid);

			// 需要额外逻辑的值
			data.put("status", schedule.getStatus());
			data.put("nickname", 1);
			data.put("partakeNum", 1);
			data.put("announcementNum", 1);

			res.put("result", "1");
			res.put("message", "获取出游计划详情成功");
			res.put("data", data);
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/createSchedule", method = RequestMethod.POST)
	public Map<String, Object> createSchedule(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getParameter("sessionKey"));
		if (myUid != -1) {
			Schedule schedule = new Schedule();
			schedule.sid = 0;
			schedule.uid = Integer.parseInt(req.getParameter("uid").toString());
			schedule.target = req.getParameter("target").toString();
			schedule.meet_place = req.getParameter("meet_place").toString();
			schedule.recruit_start_time = req.getParameter("recruit_start_time").toString();
			schedule.recruit_end_time = req.getParameter("recruit_end_time").toString();
			schedule.execute_time = req.getParameter("execute_time").toString();
			schedule.end_time = req.getParameter("end_time").toString();
			schedule.title = req.getParameter("title").toString();
			schedule.partookNum = Integer.parseInt(req.getParameter("patookNum").toString());
			schedule.describe = req.getParameter("content".toString());
			schedule.start_time = TimeUtil.getCurrentTime();

			if (this.scheduleService.addSchedule(schedule)) {
				res.put("result", "1");
				res.put("message", "发布出游计划成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "发布出游计划失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}

		return res;
	}

	@RequestMapping(value = "/schedule/getMyCollectingScheduleList", method = RequestMethod.POST)
	public Map<String, Object> getMyCollectingScheduleList(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getParameter("sessionKey"));
		if (myUid != -1) {
			res.put("result", "1");
			res.put("message", "获取我收藏的出游计划成功");
			res.put("data", this.scheduleService.getMyCollectingScheduleList(String.valueOf(myUid)));
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/getMyOwningScheduleList", method = RequestMethod.POST)
	public Map<String, Object> getMyOwningScheduleList(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getParameter("sessionKey"));
		if (myUid != -1) {
			res.put("result", "1");
			res.put("message", "获取我收藏的出游计划成功");
			res.put("data", this.scheduleService.getMyOwningScheduleList(String.valueOf(myUid)));
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/getMyPartakeScheduleList", method = RequestMethod.POST)
	public Map<String, Object> getMyPartakeScheduleList(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getParameter("sessionKey"));
		if (myUid != -1) {
			res.put("result", "1");
			res.put("message", "获取我收藏的出游计划成功");
			res.put("data", this.scheduleService.getMyPartakeScheduleList(String.valueOf(myUid)));
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/updateSchedule", method = RequestMethod.POST)
	public Map<String, Object> updateSchedule(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getParameter("sessionKey"));
		if (myUid != -1) {
			Schedule schedule = this.scheduleService.getSchedule(req.getParameter("sid"));
			// schedule.sid = Integer.parseInt(req.getParameter("sid"));
			schedule.target = req.getParameter("target");
			schedule.meet_place = req.getParameter("meet_place");
			schedule.recruit_start_time = req.getParameter("recruit_start_time");
			schedule.recruit_end_time = req.getParameter("recruit_end_time");
			schedule.execute_time = req.getParameter("execute_time");
			schedule.title = req.getParameter("title");
			schedule.describe = req.getParameter("content");
			schedule.partookNum = Integer.parseInt(req.getParameter("partookNum"));
			this.scheduleService.updateSchedule(schedule);

			res.put("result", "1");
			res.put("message", "获取我收藏的出游计划成功");
			res.put("data", "");
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/releaseAnnouncement", method = RequestMethod.POST)
	public Map<String, Object> releaseAnnouncement(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getParameter("sessionKey"));
		if (myUid != -1) {
			ScheduleAnnouncement scheduleAnnouncement = new ScheduleAnnouncement();
			scheduleAnnouncement.recordId = 0;
			scheduleAnnouncement.sid = Integer.parseInt(req.getParameter("sid"));
			scheduleAnnouncement.announcement_title = req.getParameter("title");
			scheduleAnnouncement.announcement_content = req.getParameter("content");
			scheduleAnnouncement.release_time = TimeUtil.getCurrentTime();
			if (this.scheduleService.addAnnouncement(scheduleAnnouncement)) {
				res.put("result", "1");
				res.put("message", "发布计划公告成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "发布计划公告失敗");
				res.put("data", "");
			}

		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/getAnnouncementList", method = RequestMethod.GET)
	public Map<String, Object> getAnnouncementList(@RequestHeader("sessionKey") String sessionKey, String sid) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			res.put("result", "1");
			res.put("message", "获得计划公告列表成功");
			res.put("data", this.scheduleService.getAnnouncementList(sid));
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/addScheduleComment", method = RequestMethod.POST)
	public Map<String, Object> addScheduleComment(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getParameter("sessionKey"));
		if (myUid != -1) {
			ScheduleComment scheduleComment = new ScheduleComment();
			scheduleComment.recordId = 0;
			scheduleComment.sid = Integer.parseInt(req.getParameter("sid"));
			scheduleComment.uid = Integer.parseInt(req.getParameter("uid"));
			scheduleComment.atUid = Integer.parseInt(req.getParameter("at_uid"));
			scheduleComment.commentContent = req.getParameter("content");
			scheduleComment.recordTime = TimeUtil.getCurrentTime();
			if (this.scheduleService.addScheduleComment(scheduleComment)) {
				res.put("result", "1");
				res.put("message", "评论计划成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "评论计划失败");
				res.put("data", "");
			}

		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/likeSchedule", method = RequestMethod.GET)
	public Map<String, Object> likeSchedule(@RequestHeader("sessionKey") String sessionKey, String sid) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			ScheduleLike scheduleLike = new ScheduleLike();
			scheduleLike.recordId = 0;
			scheduleLike.sid = Integer.parseInt(sid);
			scheduleLike.uid = myUid;
			scheduleLike.recordTime = TimeUtil.getCurrentTime();
			scheduleLike.isPositive = "Y";
			if (this.scheduleService.addScheduleLikeRecord(scheduleLike)) {
				res.put("result", "1");
				res.put("message", "点赞出游计划成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "点赞出游计划失败");
				res.put("data", "");
			}

		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/dislikeSchedule", method = RequestMethod.GET)
	public Map<String, Object> dislikeSchedule(@RequestHeader("sessionKey") String sessionKey, String sid) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			ScheduleLike scheduleLike = new ScheduleLike();
			scheduleLike.recordId = 0;
			scheduleLike.sid = Integer.parseInt(sid);
			scheduleLike.uid = myUid;
			scheduleLike.recordTime = TimeUtil.getCurrentTime();
			scheduleLike.isPositive = "N";
			if (this.scheduleService.addScheduleLikeRecord(scheduleLike)) {
				res.put("result", "1");
				res.put("message", "取消点赞出游计划成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "取消点赞出游计划失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/collectSchedule", method = RequestMethod.GET)
	public Map<String, Object> collectSchedule(@RequestHeader("sessionKey") String sessionKey, String sid) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			ScheduleCollectRecord scheduleCollectRecord = new ScheduleCollectRecord();
			scheduleCollectRecord.sid = Integer.parseInt(sid);
			scheduleCollectRecord.uid = myUid;
			scheduleCollectRecord.recordTime = TimeUtil.getCurrentTime();
			scheduleCollectRecord.recordId = 0;
			scheduleCollectRecord.isPositive = "Y";
			if (this.scheduleService.addCollectScheduleRecord(scheduleCollectRecord)) {
				res.put("result", "1");
				res.put("message", "收藏计划成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "收藏计划失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/discollectSchedule", method = RequestMethod.POST)
	public Map<String, Object> discollectSchedule(@RequestHeader("sessionKey") String sessionKey, String sid) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(sessionKey);
		if (myUid != -1) {
			ScheduleCollectRecord scheduleCollectRecord = new ScheduleCollectRecord();
			scheduleCollectRecord.sid = Integer.parseInt(sid);
			scheduleCollectRecord.uid = myUid;
			scheduleCollectRecord.recordTime = TimeUtil.getCurrentTime();
			scheduleCollectRecord.recordId = 0;
			scheduleCollectRecord.isPositive = "N";
			if (this.scheduleService.addCollectScheduleRecord(scheduleCollectRecord)) {
				res.put("result", "1");
				res.put("message", "取消收藏计划成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "取消收藏计划失败");
				res.put("data", "");
			}
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/releaseLookback", method = RequestMethod.POST)
	public Map<String, Object> releaseLookback(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getParameter("sessionKey"));
		if (myUid != -1) {
			ScheduleLookback scheduleLookback = new ScheduleLookback();
			scheduleLookback.recordId = 0;
			scheduleLookback.sid = Integer.parseInt(req.getParameter("sid"));
			scheduleLookback.uid = myUid;
			scheduleLookback.content = req.getParameter("content");
			scheduleLookback.recordTime = TimeUtil.getCurrentTime();
			if (this.scheduleService.addScheduleLookback(scheduleLookback)) {
				res.put("result", "1");
				res.put("message", "发布出游回顾成功");
				res.put("data", "");
			} else {
				res.put("result", "-1");
				res.put("message", "发布出游回顾失败");
				res.put("data", "");
			}

		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}

	@RequestMapping(value = "/schedule/getLookbackList", method = RequestMethod.POST)
	public Map<String, Object> getLookbackList(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		int myUid = this.loginService.findUidBySessionKey(req.getParameter("sessionKey"));
		if (myUid != -1) {
			res.put("result", "1");
			res.put("message", "获取计划回顾列表成功");
			res.put("data", this.scheduleService.getScheduleLookbackList(req.getParameter("sid")));
		} else {
			res.put("result", "2");
			res.put("message", "请重新登录");
			res.put("data", "");
		}
		return res;
	}
}
