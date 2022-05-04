package com.chocoapp.chocoappapi.util;

import com.chocoapp.chocoappapi.exception.ValidationException;

public class StringUtil {
	
	public int isValidMail(String mail) throws ValidationException {
		if (!mail.contains("@") || !mail.contains(".")) {
			throw new ValidationException("Invalid mail");

		} else {
			return 1;
		}
	}
	
	public int isValidMobile(String mobile) throws Exception {
		if (mobile.length() != 10) {
			throw new Exception("Invalid number !");

		} else if (mobile.length() == 10) {
			for (int i = 0; i < mobile.length(); i++) {
				char ch = mobile.charAt(i);
				if (!Character.isDigit(ch)) {
					throw new Exception("Mobile number should contain only numbers!");

				}
			}
		}
		return 1;
	}

	public int isValidPassword(String password) throws Exception {
		if (password.isEmpty() || password.isBlank() || password.length() < 8 || password.length() > 16) {
			throw new Exception("Invalid password");

		}
		return 1;
	}

}
