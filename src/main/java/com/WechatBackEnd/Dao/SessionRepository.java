package com.WechatBackEnd.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.Session;
@Transactional
public interface SessionRepository extends JpaRepository<Session, Integer>{

}
