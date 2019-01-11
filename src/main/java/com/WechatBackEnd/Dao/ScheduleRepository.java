package com.WechatBackEnd.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.Schedule;

@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule,Integer>{

}
