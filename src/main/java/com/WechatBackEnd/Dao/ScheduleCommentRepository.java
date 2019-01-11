package com.WechatBackEnd.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ScheduleComment;

@Transactional
public interface ScheduleCommentRepository extends JpaRepository<ScheduleComment, Integer> {

}
