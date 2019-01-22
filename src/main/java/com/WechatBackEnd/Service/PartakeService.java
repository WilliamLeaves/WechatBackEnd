package com.WechatBackEnd.Service;

import java.util.List;

public interface PartakeService {

	boolean changeScheduleOwner(String uid, String sid, String new_uid);
	
	boolean partakeSchedule(String myUid,String sid);
	
	boolean quitSchedule(String myUid,String sid);
	
	boolean receivePartake(String myUid,String sid);
	
	boolean rejectPartake(String myUid,String sid);
	
	boolean removePartake(String myUid,String sid);
	
	List getPartakeUserList(String sid);

}
