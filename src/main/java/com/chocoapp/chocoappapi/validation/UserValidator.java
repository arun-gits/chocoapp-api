package com.chocoapp.chocoappapi.validation;

import com.chocoapp.chocoappapi.exception.ValidationException;
import com.chocoapp.chocoappapi.model.User;


public class UserValidator {

	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 */
	public static void registerDetailsValidation(User user) throws ValidationException {
		validateName(user.getName());
		isValidMobile(user.getMobile());
		validateEmail(user.getMail());
		isValidPassword(user.getPassword());
		validateAddress(user.getAddress());
	}

	public static void validateAddress(String city) throws ValidationException {
		if (city.isEmpty() || city.isBlank()) {
			throw new ValidationException("Invalid address");

		}

	}

	public static void validateName(String name) throws ValidationException {

		if (name.isEmpty() || name.isBlank()) {
			throw new ValidationException("Invalid name");

		}

	}

	public static void validateEmail(String mail) throws ValidationException {
		if (!mail.contains("@") || !mail.contains(".")) {
			throw new ValidationException("Invalid mail");

		}
	}

	public static void isValidMobile(String mobile) throws ValidationException {
		if (mobile.length() != 10) {
			throw new ValidationException("Invalid number !!!");

		} else if (mobile.length() == 10) {
			for (int i = 0; i < mobile.length(); i++) {
				char ch = mobile.charAt(i);
				if (!Character.isDigit(ch)) {
					throw new ValidationException("Mobile number should not contain characters!");

				}
			}
		}
	}

	public static void isValidPassword(String password) throws ValidationException {

		if (password.isEmpty() || password.isBlank() || password.length() < 8 || password.length() > 16) {
			throw new ValidationException("Password should be minimum 8 characters and maximum 16 characters");

		}
	}

}
