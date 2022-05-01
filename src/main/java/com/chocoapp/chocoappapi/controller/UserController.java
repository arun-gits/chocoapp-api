package com.chocoapp.chocoappapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocoapp.chocoappapi.model.User;
import com.chocoapp.chocoappapi.repository.UserRepository;
import com.chocoapp.chocoappapi.service.UserService;

@RequestMapping("user")
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@PostMapping("register")
	public String register(@RequestBody User user) {
		String message = userService.registerUser(user);
		return message;
	}
	
	@PostMapping("login")
	public String login(@RequestBody User user) {
		String message = userService.login(user);
		return message;	
	}
	
	
	@PatchMapping("update-password/{id}/{password}")
	public String updatePassword(@PathVariable("id") int id, @PathVariable("password") String password) {
		String message = userService.updatePassword(id,password);
		return message;
	}
	
}
