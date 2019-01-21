package com.WechatBackEnd.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.Session;
@Transactional
public interface SessionRepository extends JpaRepository<Session, Integer>{
	
	List<Session> findByOpenId(String openId);
}
