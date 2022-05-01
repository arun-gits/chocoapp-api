package com.chocoapp.chocoappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chocoapp.chocoappapi.dao.UserDAO;
import com.chocoapp.chocoappapi.logic.RegistrationValidation;
import com.chocoapp.chocoappapi.model.User;
import com.chocoapp.chocoappapi.repository.UserRepository;

@Repository
public class UserService {

	@Autowired
	UserRepository userRepository;

	public static String addUser(User user) {
		String message = null;
		int count = 0;
		try {
			RegistrationValidation.registerDetailsValidation(user);
		} catch (Exception e) {
			message = e.getMessage();
			count = 1;
		}
		UserDAO registerUser = new UserDAO();
		if (count != 1) {
			try {
				registerUser.signUpMailValidation(user.getMail());
				registerUser.signUpNumberValidation(user.getMobile());
				count = 2;
			} catch (Exception e) {
				message = e.getMessage();
			}
		}
		if (count == 2) {
			try {
				message = registerUser.addUser(user);
			} catch (Exception e) {
				message = e.getMessage();
			}
		}
		return message;
	}

	public String loginByMobile(User user) {

		User loginUser = userRepository.findByMobile(user.getMobile());
		if (loginUser != null) {
			if (user.getPassword().equals(loginUser.getPassword())) {
				return "Welcome " + loginUser.getName();
			} else {
				return "Invalid credentials";
			}
		}
		return "No records found for this credentials";
	}

	public String loginByEmail(User user) {

		User loginUser = userRepository.findByMail(user.getMail());
		if (loginUser != null) {
			if (user.getPassword().equals(loginUser.getPassword())) {
				return "Welcome " + loginUser.getName();
			} else {
				return "Invalid credentials";
			}
		}
		return "No records found for this credentials";
	}

	public String login(User user) {
		String message = null;
		int count = 0;
		int temp = 0;
		String data = user.getData();
		String password = user.getPassword();
		if (data.isBlank() || data.isEmpty() || password.isBlank() || password.isEmpty()) {
			message = "Login Credentials cannot be empty";
		} else {
			if (data.contains("@") && data.contains(".") && !data.endsWith(".")) {
				user.setMail(data);
				message = loginByEmail(user);
			} else if (data.contains("@") && !data.contains(".") || !data.contains("@") && data.contains(".")
					|| data.endsWith(".")) {
				message = "Enter valid Email Id";
			} else {
				char[] dataCheck = data.toCharArray();
				for (int i = 0; i < dataCheck.length; i++) {
					if (Character.isDigit(dataCheck[i])) {
						count++;
					}
					if (Character.isAlphabetic(dataCheck[i])) {
						temp++;
					}
				}
				if (count == 10 && temp == 0) {
					user.setMobile(data);
					message = loginByMobile(user);
				} else if (temp != 0) {
					message = "Enter valid Email Id";
				} else if (count != 10 && temp == 0) {
					message = "Ente valid mobile number";
				} else {
					message = "Invalid Email/Mobile Number";
				}
			}
		}
		return message;
	}

	public String registerUser(User user) {
		String message = null;
		int count = 0;
		try {
			count = RegistrationValidation.registerDetailsValidation(user);
		} catch (Exception e) {
			message = e.getMessage();
		}
		if (count == 1) {
			User mailCheck = userRepository.findByMail(user.getMail());
			User mobileCheck = userRepository.findByMobile(user.getMobile());
			if (mailCheck == null && mobileCheck == null) {
				User addedUser = userRepository.save(user);
				message = "Welcome to Choco Shop " + addedUser.getName();
			} else if (mailCheck != null || mobileCheck != null) {
				message = "Already an existing user";
			} else {
				message = "Unknown error occurred\nTry again after a while";
			}
		}
		return message;
	}

	public String deleteUserById(int id) {
		String message = null;
		Optional<User> user = userRepository.findById(id);
		User deleteUser = user.get();
		if (deleteUser == null) {
			message = "User not found in this id";
		} else {
			userRepository.deleteById(id);
			message = "id " + id + " deleted successfully";
		}
		return message;
	}
	
	public List<User> listAllUsers(){
		List<User> users = userRepository.findAll();
		return users;
	}
	
	public String updatePassword(int id, String password) {
		String message = null;
		Optional <User> updateUser = userRepository.findById(id);
		if(updateUser.isPresent()) {
			userRepository.changePassword(id, password);
			message = "Password updated successfully";
		}
		else {
			message = "Error occurred\nTry again after a while";
		}
		return message;
	}
	
}