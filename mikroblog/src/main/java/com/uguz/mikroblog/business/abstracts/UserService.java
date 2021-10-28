package com.uguz.mikroblog.business.abstracts;

import java.util.List;

import com.uguz.mikroblog.entities.concretes.User;

public interface UserService {

	List<User> getAll();
	
	void add(User user);
	
	User getById(int userId);
	
	void delete(int userId);
	
	void update(int userId,User userInfo);
	
	
	
}
