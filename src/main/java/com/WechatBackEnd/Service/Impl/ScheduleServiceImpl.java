package com.WechatBackEnd.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WechatBackEnd.Dao.ScheduleRepository;
import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Model.ScheduleAnnouncement;
import com.WechatBackEnd.Model.ScheduleCollectRecord;
import com.WechatBackEnd.Model.ScheduleComment;
import com.WechatBackEnd.Model.ScheduleLike;
import com.WechatBackEnd.Service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	public ScheduleRepository scheduleRepository;

	@Override
	public Schedule getSchedule(String sid) {
		// TODO Auto-generated method stub
		Schedule schedule = this.scheduleRepository.findById(Integer.parseInt(sid)).get();
		return schedule;
	}

	@Override
	public boolean addSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getPartookScheduleListOfUser(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getLaunchScheduleListOfUser(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getMyCollectingScheduleList(String myUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAnnouncement(ScheduleAnnouncement announcement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getAnnouncementList(String sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addScheduleComment(ScheduleComment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getScheduleCommentList(String sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getMyOwningScheduleList(String myUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getMyPartakeScheduleList(String myUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addScheduleLikeRecord(ScheduleLike scheduleLike) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCollectScheduleRecord(ScheduleCollectRecord record) {
		// TODO Auto-generated method stub
		return false;
	}

}
