package com.uguz.mikroblog.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uguz.mikroblog.business.abstracts.UserService;
import com.uguz.mikroblog.dataAccess.abstracts.UserDao;
import com.uguz.mikroblog.entities.concretes.User;

@Service
public class UserManager implements UserService{

	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao=userDao;
	}

	@Override
	public List<User> getAll() {
		return this.userDao.findAll();
	}

	@Override
	public void add(User user) {
		
		this.userDao.save(user);
	}

	@Override
	public User getById(int userId) {

		return this.userDao.findById(userId);
	}

	@Override
	public void delete(int userId) {
		User user=this.getById(userId);
		this.userDao.delete(user);
	
	}

	@Override
	public void update(int userId,User userInfo) {
		
		User user=getById(userId);
		
		user.setFirstName(userInfo.getFirstName());
		user.setUserName(userInfo.getUserName());
		user.setLastName(userInfo.getLastName());
		user.setPassword(userInfo.getPassword());
		user.setText(userInfo.getText());
		
		this.userDao.save(user);
		
	}
	
}
