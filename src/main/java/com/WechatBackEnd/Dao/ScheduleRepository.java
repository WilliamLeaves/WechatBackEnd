package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.Schedule;

@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	@Modifying
	@Query(value = "select * where uid=? order by execute_time desc;", nativeQuery = true)
	List<Schedule> findOwningList(String myUid);
}
