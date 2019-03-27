package com.WechatBackEnd.Service;

import java.util.List;

import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Model.ScheduleAnnouncement;
import com.WechatBackEnd.Model.ScheduleCollectRecord;
import com.WechatBackEnd.Model.ScheduleComment;
import com.WechatBackEnd.Model.ScheduleLike;
import com.WechatBackEnd.Model.ScheduleLookback;

public interface ScheduleService {
	public Schedule getSchedule(String sid);

	public boolean addSchedule(Schedule schedule);

	public boolean updateSchedule(Schedule schedule);

	public boolean addCollectScheduleRecord(ScheduleCollectRecord record);

	public List getMyCollectingScheduleList(String myUid);

	public List getMyOwningScheduleList(String myUid);

	public List getMyPartakeScheduleList(String myUid);

	public boolean addAnnouncement(ScheduleAnnouncement announcement);

	public List getAnnouncementList(String sid);

	public boolean addScheduleComment(ScheduleComment comment);

	public List getScheduleCommentList(String sid);

	public boolean addScheduleLikeRecord(ScheduleLike scheduleLike);

	public boolean addScheduleLookback(ScheduleLookback scheduleLookback);

	public List getScheduleLookbackList(String sid);

	public List getAllSchedule();

	public boolean isScheduleCollected(String sid, String myUid);
	
	public int getScheduleAnnouncementNum(String sid);

	public String getOwnerNameBySchedule(String sid);
}
