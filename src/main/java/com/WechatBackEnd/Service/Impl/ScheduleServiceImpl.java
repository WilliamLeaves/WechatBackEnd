package com.WechatBackEnd.Service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Model.ScheduleAnnouncement;
import com.WechatBackEnd.Model.ScheduleCollectRecord;
import com.WechatBackEnd.Model.ScheduleComment;
import com.WechatBackEnd.Service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Override
	public Map<String, Object> getSchedule(String sid) {
		// TODO Auto-generated method stub
		return null;
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
	public boolean collectSchedule(ScheduleCollectRecord record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean disCollectSchedule(ScheduleCollectRecord record) {
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

}
