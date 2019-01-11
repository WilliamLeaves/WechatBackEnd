package com.WechatBackEnd.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.WechatBackEnd.Model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
}
