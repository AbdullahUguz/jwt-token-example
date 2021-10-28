package com.uguz.mikroblog.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uguz.mikroblog.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	User findById(int id);
	
}
