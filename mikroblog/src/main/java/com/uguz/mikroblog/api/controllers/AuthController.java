package com.uguz.mikroblog.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uguz.mikroblog.business.abstracts.UserJwtService;
import com.uguz.mikroblog.entities.concretes.UserJwt;
import com.uguz.mikroblog.request.UserRequest;
import com.uguz.mikroblog.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
private AuthenticationManager authenticationManager;
	
	private JwtTokenProvider jwtTokenProvider;
	
	private UserJwtService userJwtService;
	
	private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserJwtService userJwtService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userJwtService = userJwtService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
	@PostMapping("/login")
	public String login(@RequestBody UserRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		return "Bearer " + jwtToken;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRequest registerRequest) {
		if(userJwtService.getOneUserByUserName(registerRequest.getUserName()) != null)
			return new ResponseEntity<>("Username already in use.", HttpStatus.BAD_REQUEST);
		
		UserJwt userJwt = new UserJwt();
		userJwt.setUserName(registerRequest.getUserName());
		userJwt.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		userJwtService.add(userJwt);
		return new ResponseEntity<>("User successfully registered.", HttpStatus.CREATED);		
	}

}
