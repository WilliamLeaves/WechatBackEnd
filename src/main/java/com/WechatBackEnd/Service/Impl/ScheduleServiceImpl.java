package com.WechatBackEnd.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WechatBackEnd.Dao.ScheduleAnnouncementRepository;
import com.WechatBackEnd.Dao.ScheduleCollectRecordRepository;
import com.WechatBackEnd.Dao.ScheduleCommentRepository;
import com.WechatBackEnd.Dao.ScheduleLikeRepository;
import com.WechatBackEnd.Dao.ScheduleLookbackRepository;
import com.WechatBackEnd.Dao.SchedulePartakeRepository;
import com.WechatBackEnd.Dao.ScheduleRepository;
import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Model.ScheduleAnnouncement;
import com.WechatBackEnd.Model.ScheduleCollectRecord;
import com.WechatBackEnd.Model.ScheduleComment;
import com.WechatBackEnd.Model.ScheduleLike;
import com.WechatBackEnd.Model.ScheduleLookback;
import com.WechatBackEnd.Model.SchedulePartake;
import com.WechatBackEnd.Service.ScheduleService;
import com.WechatBackEnd.Util.TimeUtil;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	public ScheduleRepository scheduleRepository;
	@Autowired
	public ScheduleCollectRecordRepository scheduleCollectRecordRepository;
	@Autowired
	public ScheduleAnnouncementRepository scheduleAnnouncementRepository;
	@Autowired
	public ScheduleCommentRepository scheduleCommentRepository;
	@Autowired
	public SchedulePartakeRepository schedulePartakeRepository;
	@Autowired
	public ScheduleLikeRepository scheduleLikeRepository;
	@Autowired
	public ScheduleLookbackRepository scheduleLookbackRepository;

	@Override
	public Schedule getSchedule(String sid) {
		// TODO Auto-generated method stub
		Schedule schedule = this.scheduleRepository.findById(Integer.parseInt(sid)).get();
		return schedule;
	}

	@Override
	public boolean addSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		String message = schedule.timeRationalityJudge();
		if (message.equals("")) {
			if (this.scheduleRepository.save(schedule) != null) {
				SchedulePartake schedulePartake = new SchedulePartake();
				schedulePartake.recordId = 0;
				schedulePartake.sid = schedule.sid;
				schedulePartake.uid = schedule.uid;
				schedulePartake.recordTime = TimeUtil.getCurrentTime();
				schedulePartake.status = "partook";
				this.schedulePartakeRepository.save(schedulePartake);
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println(message);
			return false;
		}

	}

	@Override
	public boolean updateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		if (!TimeUtil.isLessThanMinTime(TimeUtil.getCurrentTime(), schedule.execute_time)) {
			System.out.println("执行前1天无法修改出游计划细节");
			return false;
		} else {
			String message = schedule.timeRationalityJudge();
			if (message.equals("")) {
				if (this.scheduleRepository.save(schedule) != null) {
					return true;
				} else {
					return false;
				}
			} else {
				System.out.println(message);
				return false;
			}
		}
	}

	@Override
	public List getMyCollectingScheduleList(String myUid) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		List<Object[]> list = this.scheduleCollectRecordRepository.findCollectList(myUid);
		for (Object[] ob : list) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("sid", ob[0].toString());
			item.put("title", ob[1].toString());
			item.put("target", ob[2].toString());
			item.put("execute_time", ob[3].toString());
			if (ob[4].toString().equals("Y")) {
				result.add(item);
				Schedule scheduleSample = Schedule.getStub();
				scheduleSample.start_time = ob[5].toString();
				scheduleSample.recruit_start_time = ob[6].toString();
				scheduleSample.recruit_end_time = ob[7].toString();
				scheduleSample.execute_time = ob[3].toString();
				scheduleSample.end_time = ob[8].toString();
				item.put("status", scheduleSample.getStatus());
			} else {
				for (HashMap<String, Object> item2 : result) {
					if (item2.get("sid").toString().equals(item.get("sid").toString())) {
						result.remove(result.indexOf(item2));
						break;
					}
				}
			}
		}
		return result;
	}

	@Override
	public boolean addAnnouncement(ScheduleAnnouncement announcement) {
		// TODO Auto-generated method stub
		if (this.scheduleAnnouncementRepository.save(announcement) != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List getAnnouncementList(String sid) {
		// TODO Auto-generated method stub
		// this.scheduleAnnouncementRepository.findAllById();
		List<ScheduleAnnouncement> result = new ArrayList<ScheduleAnnouncement>();
		List<Object[]> list = this.scheduleAnnouncementRepository.findAnnouncementList(sid);
		for (Object[] obArray : list) {
			result.add(ScheduleAnnouncement.parseByObjectArray(obArray));
		}
		return result;
	}

	@Override
	public boolean addScheduleComment(ScheduleComment comment) {
		// TODO Auto-generated method stub
		if (this.scheduleCommentRepository.save(comment) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List getScheduleCommentList(String sid) {
		// TODO Auto-generated method stub
		return this.scheduleCommentRepository.findCommentList(sid);
	}

	@Override
	public List getMyOwningScheduleList(String myUid) {
		// TODO Auto-generated method stub
		List<Object[]> list = this.scheduleRepository.findOwningList(myUid);
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (Object[] ob : list) {
			Schedule s = Schedule.parseByObjectArray(ob);
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("sid", s.sid);
			item.put("title", s.title);
			item.put("target", s.target);
			item.put("execute_time", s.execute_time);
			item.put("status", s.getStatus());
			result.add(item);
		}
		return result;
	}

	@Override
	public List getMyPartakeScheduleList(String myUid) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		List<Object[]> list = this.schedulePartakeRepository.findMyPartakeList(myUid);
		// List<Schedule> list = (List<Schedule>) ob;
		for (Object[] ob : list) {
			Schedule s = Schedule.parseByObjectArray(ob);
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("sid", s.sid);
			m.put("title", s.title);
			m.put("target", s.target);
			m.put("execute_time", s.execute_time);
			m.put("status", s.getStatus());
			result.add(m);
		}
		return result;
	}

	@Override
	public boolean addScheduleLikeRecord(ScheduleLike scheduleLike) {
		// TODO Auto-generated method stub
		if (this.scheduleLikeRepository.save(scheduleLike) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addCollectScheduleRecord(ScheduleCollectRecord record) {
		// TODO Auto-generated method stub
		if (this.scheduleCollectRecordRepository.save(record) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addScheduleLookback(ScheduleLookback scheduleLookback) {
		// TODO Auto-generated method stub
		if (this.scheduleLookbackRepository.save(scheduleLookback) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List getScheduleLookbackList(String sid) {
		// TODO Auto-generated method stub
		return this.scheduleLookbackRepository.findLookbackList(sid);
	}

	@Override
	public List<HashMap<String, Object>> getAllSchedule() {
		// TODO Auto-generated method stub

		// 这段代码需要重新实现，因为有一个遍历查询，效率低下

		List<HashMap<String, Object>> res = new ArrayList<HashMap<String, Object>>();
		List<Schedule> list = this.scheduleRepository.findAll();
		for (Schedule schedule : list) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("sid", schedule.sid);
			m.put("uid", schedule.uid);
			m.put("nickname", "william");
			m.put("title", schedule.title);
			m.put("target", schedule.target);
			m.put("execute_time", schedule.execute_time);
			m.put("status", schedule.getStatus());
			m.put("describe", schedule.describe);
			m.put("likeNum", "");
			m.put("commentNum", "");
			res.add(m);
		}
		return res;
	}

	@Override
	public boolean isScheduleCollected(String sid, String myUid) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) this
				.getMyCollectingScheduleList(myUid);
		for (HashMap<String, Object> map : list) {
			if (map.get("sid") != null && map.get("sid").equals(sid)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getScheduleAnnouncementNum(String sid) {
		// TODO Auto-generated method stub
		return this.scheduleAnnouncementRepository.findAnnouncementList(sid).size();
	}

	@Override
	public String getOwnerNameBySchedule(String sid) {
		// TODO Auto-generated method stub
		
		return this.scheduleRepository.findOwnerNameBySchedule(sid);
	}

}
