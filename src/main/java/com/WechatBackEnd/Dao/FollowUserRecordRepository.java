package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.FollowUserRecord;

@Transactional
public interface FollowUserRecordRepository extends JpaRepository<FollowUserRecord, Integer> {
	@Modifying
	@Query(value = "select u2.uid,u2.nickname,temp.record_time,temp.is_positive from user as u2,(\r\n"
			+ "	select followed_uid,record_time,is_positive from user as u1,follow_user as f\r\n"
			+ "	where u1.uid=f.uid\r\n" + "	and u1.uid=?1\r\n" + ") as temp \r\n"
			+ "where temp.followed_uid=u2.uid;", nativeQuery = true)
	List findFollowList(String uid);
}
