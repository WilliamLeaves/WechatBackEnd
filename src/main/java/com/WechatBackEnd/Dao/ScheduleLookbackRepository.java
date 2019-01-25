package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ScheduleLookback;

@Transactional
public interface ScheduleLookbackRepository extends JpaRepository<ScheduleLookback, Integer> {
	@Modifying
	@Query(value = "select u.uid,u.nickname,s.content,s.record_time from user as u,schedule_lookback as s where s.sid=? and s.uid=u.uid order by record_time desc", nativeQuery = true)
	List findLookbackList(String sid);
}
