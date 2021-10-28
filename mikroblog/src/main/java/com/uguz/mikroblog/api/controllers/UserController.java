package com.uguz.mikroblog.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uguz.mikroblog.business.abstracts.UserService;
import com.uguz.mikroblog.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/get")
	public List<User> get() {
		return this.userService.getAll();
	}

	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody User user) {
		this.userService.add(user);
		return new ResponseEntity<>("User successfully added.", HttpStatus.CREATED);	
	}

	@GetMapping("/get/{id}")
	public User getById(@PathVariable("id") int userId) {

		return this.userService.getById(userId);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int userId) {
		this.userService.delete(userId);
		return new ResponseEntity<>("User successfully deleted.", HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") int userId, @RequestBody User userInfo) {

		this.userService.update(userId,userInfo);
		return new ResponseEntity<>("User successfully updated.", HttpStatus.CREATED);

	}

}
