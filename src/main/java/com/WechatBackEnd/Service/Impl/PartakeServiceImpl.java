package com.WechatBackEnd.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WechatBackEnd.Dao.SchedulePartakeRepository;
import com.WechatBackEnd.Model.SchedulePartake;
import com.WechatBackEnd.Service.PartakeService;
import com.WechatBackEnd.Util.TimeUtil;

@Service
public class PartakeServiceImpl implements PartakeService {
	@Autowired
	private SchedulePartakeRepository schedulePartakeRepository;

	@Override
	public boolean changeScheduleOwner(String uid, String sid, String new_uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean partakeSchedule(String myUid, String sid) {
		// TODO Auto-generated method stub
		SchedulePartake schedulePartake = new SchedulePartake();
		schedulePartake.recordId = 0;
		schedulePartake.recordTime = TimeUtil.getCurrentTime();
		schedulePartake.sid = Integer.parseInt(sid);
		schedulePartake.uid = Integer.parseInt(myUid);
		schedulePartake.status = "proposing";
		this.schedulePartakeRepository.save(schedulePartake);
		return true;
	}

	@Override
	public boolean quitSchedule(String myUid, String sid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receivePartake(String myUid, String sid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectPartake(String myUid, String sid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePartake(String myUid, String sid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getPartakeUserList(String sid) {
		// TODO Auto-generated method stub
		return null;
	}

}
