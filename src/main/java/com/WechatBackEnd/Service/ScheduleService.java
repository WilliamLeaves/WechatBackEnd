package com.WechatBackEnd.Service;

import java.util.List;
import java.util.Map;

import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Model.ScheduleAnnouncement;
import com.WechatBackEnd.Model.ScheduleCollectRecord;
import com.WechatBackEnd.Model.ScheduleComment;

public interface ScheduleService {
	public Schedule getSchedule(String sid);

	public boolean addSchedule(Schedule schedule);

	public List getPartookScheduleListOfUser(String uid);

	public List getLaunchScheduleListOfUser(String uid);

	public boolean updateSchedule(Schedule schedule);

	public boolean collectSchedule(ScheduleCollectRecord record);

	public boolean disCollectSchedule(ScheduleCollectRecord record);

	public List getMyCollectingScheduleList(String myUid);
	
	public List getMyOwningScheduleList(String myUid);
	
	public List getMyPartakeScheduleList(String myUid);

	public boolean addAnnouncement(ScheduleAnnouncement announcement);

	public List getAnnouncementList(String sid);

	public boolean addScheduleComment(ScheduleComment comment);

	public List getScheduleCommentList(String sid);
}
