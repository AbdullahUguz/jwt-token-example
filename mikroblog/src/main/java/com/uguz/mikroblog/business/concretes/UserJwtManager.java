package com.uguz.mikroblog.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uguz.mikroblog.business.abstracts.UserJwtService;
import com.uguz.mikroblog.dataAccess.abstracts.UserJwtDao;
import com.uguz.mikroblog.entities.concretes.UserJwt;

@Service
public class UserJwtManager implements UserJwtService{

	private UserJwtDao userJwtDao;
	
	@Autowired
	public UserJwtManager(UserJwtDao userJwtDao) {
		this.userJwtDao=userJwtDao;
	}
	
	@Override
	public UserJwt getOneUserByUserName(String userName) {
		
		return this.userJwtDao.findByUserName(userName);
	}

	@Override
	public void add(UserJwt userJwt) {
		this.userJwtDao.save(userJwt);
		
	}

}
