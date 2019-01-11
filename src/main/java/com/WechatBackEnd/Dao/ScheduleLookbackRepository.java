package com.WechatBackEnd.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ScheduleLookback;
@Transactional
public interface ScheduleLookbackRepository extends JpaRepository<ScheduleLookback,Integer>{

}
