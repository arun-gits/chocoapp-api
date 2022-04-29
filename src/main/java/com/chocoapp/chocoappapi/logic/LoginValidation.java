package com.chocoapp.chocoappapi.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chocoapp.chocoappapi.dao.UserDAO;
import com.chocoapp.chocoappapi.model.User;

public class LoginValidation {

	private static Logger log = LogManager.getLogger(LoginValidation.class);

	public int validateEmail(String mail) throws Exception {
		if (!mail.contains("@") || !mail.contains(".")) {
			throw new Exception("Invalid mail");

		} else {
			return 1;
		}
	}

	public int validateMobile(String mobile) throws Exception {
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

	public int validatePassword(String password) throws Exception {
		if (password.isEmpty() || password.isBlank() || password.length() < 8 || password.length() > 16) {
			throw new Exception("Invalid password");

		}
		return 1;
	}

	public String loginCheck(String data, String password) {

		String message = null;
		int validEmail = 0;
		int validMobile = 0;
		int validPassword = 0;
		int mobileOrEmail = 0;
		int count = 0;

		User user = new User();
		user.setPassword(password);
		if (data.contains("@") || data.contains(".")) {
			user.setEmail(data);
			count = 0;
		} else {
			user.setMobile(data);
			count = 1;
		}

		user.setPassword(password);

		LoginValidation test = new LoginValidation();

		if (count == 0) {
			try {
				validEmail = test.validateEmail(user.getEmail());
				validPassword = test.validatePassword(user.getPassword());
			} catch (Exception e) {
				// log.info(e.getMessage());
				message = e.getMessage();
			}
			mobileOrEmail = 0;
		} else if (count == 1) {
			try {
				validMobile = test.validateMobile(user.getMobile());
				validPassword = test.validatePassword(user.getPassword());
			} catch (Exception e) {
				// log.info(e.getMessage());
				message = e.getMessage();
			}
			mobileOrEmail = 1;
		}

		UserDAO dbTest = new UserDAO();

		if (validEmail + validPassword == 2 && mobileOrEmail == 0) {
			try {
				message = dbTest.loginMailValidation(user.getEmail(), user.getPassword());
				// log.info("Validation using DB passed");
				// message="Validation using DB passed";
				log.info(message);
			} catch (Exception e) {
				log.info(e.getMessage());
				message = e.getMessage();

				// e.printStackTrace();
			}
		}

		else if (validMobile + validPassword == 2 && mobileOrEmail == 1) {
			try {
				message = dbTest.loginNumberValidation(user.getMobile(), user.getPassword());
				// log.info("Validation using DB passed");
//				message="Validation using DB passed";
				log.info(message);
			} catch (Exception e) {
				log.info(e.getMessage());
				message = e.getMessage();
				// e.printStackTrace();
			}
		}
		return message;

	}
}
