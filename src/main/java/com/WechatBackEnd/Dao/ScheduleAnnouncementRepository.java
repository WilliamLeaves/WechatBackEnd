package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ScheduleAnnouncement;

@Transactional
public interface ScheduleAnnouncementRepository extends JpaRepository<ScheduleAnnouncement, Integer> {
	@Modifying
	@Query(value = "select * from schedule_announcement where sid=?", nativeQuery = true)
	List findAnnouncementList(String sid);
}
