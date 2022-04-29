package com.chocoapp.chocoappapi.service;

import com.chocoapp.chocoappapi.dao.UserDAO;
import com.chocoapp.chocoappapi.logic.RegistrationValidation;
import com.chocoapp.chocoappapi.model.User;

public class UserService {

	public static String registerUser(User user) {
		String message = null;
		int count = 0;
		try {
		RegistrationValidation.registerDetailsValidation(user);
		}
		catch(Exception e) {
			message = e.getMessage();
			count = 1;
		}
		UserDAO registerUser = new UserDAO();
		if(count!=1) {
			try {
			registerUser.signUpMailValidation(user.getEmail());
			registerUser.signUpNumberValidation(user.getMobile());
			count = 2;
			}
			catch(Exception e) {
				message = e.getMessage();
			}
		}
		if(count == 2) {
			try {
				message = registerUser.addUser(user);	
			}
			catch(Exception e) {
				message = e.getMessage();
			}
		}
		return message;
	}
}