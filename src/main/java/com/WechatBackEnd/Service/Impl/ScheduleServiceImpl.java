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
		return this.scheduleAnnouncementRepository.findAnnouncementList(sid);
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
		List<Schedule> list = this.scheduleRepository.findOwningList(myUid);
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (Schedule ob : list) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("sid", ob.sid);
			item.put("title", ob.title);
			item.put("target", ob.target);
			item.put("execute_time", ob.execute_time);
			item.put("status", ob.getStatus());
			result.add(item);
		}
		return result;
	}

	@Override
	public List getMyPartakeScheduleList(String myUid) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		List<Schedule> list = this.schedulePartakeRepository.findMyPartakeList(myUid);
		for (Schedule s : list) {
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

}
