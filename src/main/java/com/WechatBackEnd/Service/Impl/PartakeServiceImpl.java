package com.WechatBackEnd.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WechatBackEnd.Dao.SchedulePartakeRepository;
import com.WechatBackEnd.Dao.ScheduleRepository;
import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Model.SchedulePartake;
import com.WechatBackEnd.Service.PartakeService;
import com.WechatBackEnd.Util.TimeUtil;

@Service
public class PartakeServiceImpl implements PartakeService {
	@Autowired
	private SchedulePartakeRepository schedulePartakeRepository;
	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public boolean changeScheduleOwner(String uid, String sid, String new_uid) {
		// TODO Auto-generated method stub
		//合法性校验，需要判断该计划可以加入，原uid为持有者，new_uid已经在参与者名单中
		{
			
		}
		Schedule schedule = this.scheduleRepository.findById(Integer.parseInt(sid)).get();
		schedule.uid=Integer.parseInt(new_uid);
		return false;
	}

	@Override
	public boolean partakeSchedule(String myUid, String sid) {
		// TODO Auto-generated method stub
		// 合法性校验,包括该计划可以被加入，以及发起请求的用户未在参与者名单中
		{

		}
		SchedulePartake schedulePartake = new SchedulePartake();
		schedulePartake.recordId = 0;
		schedulePartake.recordTime = TimeUtil.getCurrentTime();
		schedulePartake.sid = Integer.parseInt(sid);
		schedulePartake.uid = Integer.parseInt(myUid);
		schedulePartake.status = "proposing";
		if (this.schedulePartakeRepository.save(schedulePartake) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean quitSchedule(String myUid, String sid) {
		// TODO Auto-generated method stub
		// 合法性校验,包括该计划可以被加入，以及发起请求的用户已经在参与者名单中
		{

		}
		SchedulePartake schedulePartake = new SchedulePartake();
		schedulePartake.recordId = 0;
		schedulePartake.recordTime = TimeUtil.getCurrentTime();
		schedulePartake.sid = Integer.parseInt(sid);
		schedulePartake.uid = Integer.parseInt(myUid);
		schedulePartake.status = "quit";
		if (this.schedulePartakeRepository.save(schedulePartake) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean receivePartake(String myUid, String sid) {
		// TODO Auto-generated method stub
		// 合法性校验,包括该计划可以被加入，以及发起请求的用户已经在申请者名单中
		{

		}
		SchedulePartake schedulePartake = new SchedulePartake();
		schedulePartake.recordId = 0;
		schedulePartake.recordTime = TimeUtil.getCurrentTime();
		schedulePartake.sid = Integer.parseInt(sid);
		schedulePartake.uid = Integer.parseInt(myUid);
		schedulePartake.status = "partook";
		if (this.schedulePartakeRepository.save(schedulePartake) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean rejectPartake(String myUid, String sid) {
		// TODO Auto-generated method stub
		// 合法性校验,包括该计划可以被加入，以及发起请求的用户已经在申请者名单中
		{

		}
		SchedulePartake schedulePartake = new SchedulePartake();
		schedulePartake.recordId = 0;
		schedulePartake.recordTime = TimeUtil.getCurrentTime();
		schedulePartake.sid = Integer.parseInt(sid);
		schedulePartake.uid = Integer.parseInt(myUid);
		schedulePartake.status = "rejected";
		if (this.schedulePartakeRepository.save(schedulePartake) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removePartake(String myUid, String sid) {
		// TODO Auto-generated method stub
		// 合法性校验,包括该计划可以被加入，以及发起请求的用户已经在参与者名单中
		{

		}
		SchedulePartake schedulePartake = new SchedulePartake();
		schedulePartake.recordId = 0;
		schedulePartake.recordTime = TimeUtil.getCurrentTime();
		schedulePartake.sid = Integer.parseInt(sid);
		schedulePartake.uid = Integer.parseInt(myUid);
		schedulePartake.status = "removed";
		if (this.schedulePartakeRepository.save(schedulePartake) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List getPartakeUserList(String sid) {
		// TODO Auto-generated method stub
		return this.schedulePartakeRepository.findPartnerList(sid);
	}

}
