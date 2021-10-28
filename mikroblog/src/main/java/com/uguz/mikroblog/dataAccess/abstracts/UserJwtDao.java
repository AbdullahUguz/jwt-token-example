package com.uguz.mikroblog.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uguz.mikroblog.entities.concretes.UserJwt;

public interface UserJwtDao extends JpaRepository<UserJwt, Integer> {

	UserJwt findById(Long id);

	UserJwt findByUserName(String userName);
}
