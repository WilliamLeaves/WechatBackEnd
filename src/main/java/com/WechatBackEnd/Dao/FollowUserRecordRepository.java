package com.WechatBackEnd.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.FollowUserRecord;

@Transactional
public interface FollowUserRecordRepository extends JpaRepository<FollowUserRecord, Integer> {

}
