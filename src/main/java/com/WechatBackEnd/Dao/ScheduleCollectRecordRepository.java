package com.WechatBackEnd.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ScheduleCollectRecord;

@Transactional
public interface ScheduleCollectRecordRepository extends JpaRepository<ScheduleCollectRecord, Integer> {

}
