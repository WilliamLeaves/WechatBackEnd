package com.WechatBackEnd.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.ScheduleLike;

@Transactional
public interface ScheduleLikeRepository extends JpaRepository<ScheduleLike, Integer> {

}
