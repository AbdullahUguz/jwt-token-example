package com.uguz.mikroblog.business.concretes;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uguz.mikroblog.dataAccess.abstracts.UserJwtDao;
import com.uguz.mikroblog.entities.concretes.UserJwt;
import com.uguz.mikroblog.security.JwtUserDetails;

@Service
public class UserDetailManager implements UserDetailsService{

	private UserJwtDao userjwtDao;
	
	public UserDetailManager(UserJwtDao userjwtDao) {
		this.userjwtDao=userjwtDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserJwt userjwt = this.userjwtDao.findByUserName(username);
		
		return JwtUserDetails.create(userjwt);
	}
	
	public UserDetails loadUserById(Long id) {
		UserJwt userjwt=this.userjwtDao.findById(id);
		
		return JwtUserDetails.create(userjwt);

	}

}
