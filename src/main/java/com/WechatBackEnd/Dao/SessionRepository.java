package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.Session;

@Transactional
public interface SessionRepository extends JpaRepository<Session, Integer> {

	@Query(value = "select * from session where openid=?;", nativeQuery = true)
	List<Session> findByOpenId(String openId);

	@Query(value = "select * from session where sessionid=? order by active_time  desc limit 1;", nativeQuery = true)
	Session findUidBySessionKey(String sessionKey);
}
