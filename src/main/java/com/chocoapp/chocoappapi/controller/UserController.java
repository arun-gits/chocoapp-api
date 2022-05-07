package com.chocoapp.chocoappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocoapp.chocoappapi.dto.UserDTO;
import com.chocoapp.chocoappapi.exception.ServiceException;
import com.chocoapp.chocoappapi.exception.ValidationException;
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
	public ResponseEntity<String> register(@RequestBody  UserDTO user) {
		try {
			userService.userRegistration(user);
			return new ResponseEntity<>("Success",HttpStatus.OK);
		}catch(ValidationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch(ServiceException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody UserDTO user) {
		try {
			userService.userLogin(user);
			return new ResponseEntity<>("Success",HttpStatus.OK);
		}catch(ValidationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}catch(ServiceException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
