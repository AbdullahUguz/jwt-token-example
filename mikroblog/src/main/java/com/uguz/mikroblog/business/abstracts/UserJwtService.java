package com.uguz.mikroblog.business.abstracts;

import com.uguz.mikroblog.entities.concretes.UserJwt;

public interface UserJwtService {
	
	UserJwt getOneUserByUserName(String userName);
	void add(UserJwt userJwt);
}
