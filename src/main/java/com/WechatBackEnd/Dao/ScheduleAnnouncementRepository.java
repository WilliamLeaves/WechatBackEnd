package com.WechatBackEnd.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ScheduleAnnouncement;

@Transactional
public interface ScheduleAnnouncementRepository extends JpaRepository<ScheduleAnnouncement, Integer> {

}
