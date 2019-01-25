package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ScheduleComment;

@Transactional
public interface ScheduleCommentRepository extends JpaRepository<ScheduleComment, Integer> {
	@Modifying
	@Query(value = "select * from schedule_comment where sid=? order by record_time desc;", nativeQuery = true)
	List<ScheduleComment> findCommentList(String sid);
}
