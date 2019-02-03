package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Model.SchedulePartake;

@Transactional
public interface SchedulePartakeRepository extends JpaRepository<SchedulePartake, Integer> {
	@Modifying
	@Query(value = "select u.uid,u.nickname,u.college,u.major,s1.record_time from schedule_partake as s1,user as u\r\n"
			+ "where s1.sid=?\r\n" + "and u.uid=s1.uid\r\n" + "and s1.status='partook'\r\n" + "and not exists (\r\n"
			+ "select * from schedule_partake as s2\r\n" + "where s2.uid=s1.uid\r\n" + "and s2.sid=s1.sid\r\n"
			+ "and s2.record_time>s1.record_time\r\n" + "and (s2.status = 'quit'\r\n" + "or s2.status = 'removed')\r\n"
			+ ")\r\n" + "order by record_time;", nativeQuery = true)
	List findPartnerList(String sid);

	@Modifying
	@Query(value = "select s.* from schedule as s,schedule_partake as sp1\r\n" + "where sp1.sid=s.sid\r\n"
			+ "and sp1.uid=1\r\n" + "and sp1.status='partook'\r\n" + "and not exists(\r\n"
			+ "select * from schedule_partake as sp2\r\n" + "where sp2.uid=sp1.uid\r\n" + "and sp2.sid=sp1.sid\r\n"
			+ "and sp2.record_time>sp1.record_time\r\n" + "and (sp2.status = 'quit'\r\n"
			+ "or sp2.status = 'removed')\r\n" + ")\r\n" + "order by record_time;", nativeQuery = true)
	List findMyPartakeList(String uid);
}
