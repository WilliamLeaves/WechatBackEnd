package com.WechatBackEnd.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.SchedulePartake;
@Transactional
public interface SchedulePartakeRepository extends JpaRepository<SchedulePartake, Integer> {

}
