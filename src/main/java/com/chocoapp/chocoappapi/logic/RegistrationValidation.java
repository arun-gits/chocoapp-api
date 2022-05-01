package com.chocoapp.chocoappapi.logic;

import com.chocoapp.chocoappapi.model.User;

public class RegistrationValidation {

	public static int registerDetailsValidation(User user) throws Exception {
		try {
			validateName(user.getName());
			validateMobile(user.getMobile());
			validateEmail(user.getMail());
			validatePassword(user.getPassword());
			validateAddress(user.getAddress());
			return 1;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public static void validateAddress(String city) throws Exception {
		if (city.isEmpty() || city.isBlank()) {
			throw new Exception("Invalid address");

		}

	}

	public static void validateName(String name) throws Exception {

		if (name.isEmpty() || name.isBlank()) {
			throw new Exception("Invalid name");

		}

	}

	public static void validateEmail(String mail) throws Exception {
		if (!mail.contains("@") || !mail.contains(".")) {
			throw new Exception("Invalid mail");

		}
	}

	public static void validateMobile(String mobile) throws Exception {
		if (mobile.length() != 10) {
			throw new Exception("Invalid number !!!");

		} else if (mobile.length() == 10) {
			for (int i = 0; i < mobile.length(); i++) {
				char ch = mobile.charAt(i);
				if (!Character.isDigit(ch)) {
					throw new Exception("Mobile number should not contain characters!");

				}
			}
		}
	}

	public static void validatePassword(String password) throws Exception {

		if (password.isEmpty() || password.isBlank() || password.length() < 8 || password.length() > 16) {
			throw new Exception("Password should be minimum 8 characters and maximum 16 characters");

		}
	}

}
