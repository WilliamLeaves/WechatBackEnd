package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ScheduleCollectRecord;

@Transactional
public interface ScheduleCollectRecordRepository extends JpaRepository<ScheduleCollectRecord, Integer> {
	@Modifying
	@Query(value = "select s1.sid,s1.title,s1.target,s1.execute_time,c1.is_positive,s1.start_time,\r\n" + 
			"s1.recruit_start_time,s1.recruit_end_time,s1.end_time\r\n" + 
			"from collect_schedule as c1,schedule as s1\r\n" + 
			"where c1.uid=?\r\n" + 
			"and c1.sid=s1.sid\r\n" + 
			"order by c1.record_time;\r\n" + 
			"", nativeQuery = true)
	List findCollectList(String myUid);
}
