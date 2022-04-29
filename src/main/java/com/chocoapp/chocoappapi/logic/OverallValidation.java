package com.chocoapp.chocoappapi.logic;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OverallValidation {
	private static Logger log = LogManager.getLogger(OverallValidation.class);

	static Scanner sc = new Scanner(System.in);

	public static int validateName(String name) {
		int count = 0;
		if (name.isEmpty() || name.isBlank()) {
			log.warn("Name cannot be empty !");
			log.info("Enter valid name:");
			count++;
		}
		return count;
	}

	// Checking number
	public static int validateNumber(String number) {
		int count = 0;
		if (number.length() != 10) {
			log.warn("Invalid number !!!");
			log.info("Enter mobile number:");
			return 1;
		} else {
			for (int i = 0; i < number.length(); i++) {
				char ch = number.charAt(i);
				if (!Character.isDigit(ch)) {
					count++;
					log.warn("Invalid number !!!");
					log.info("Enter mobile number:");
					break;
				}
			}
			return count;
		}
	}
//		if(number.length()==10) {
//			int count=0;
//		for (int i = 0; i < number.length(); i++) {
//			char ch = number.charAt(i);
//			if (!Character.isDigit(ch)) {
//				count++;
//			}
//		if (count!=0) {
//			log.warn("Invalid number !!!");
//			log.info("Enter mobile number:");
//			return 1;
//		}
//		else {
//			return 0;
//	}

	// Checks password
	public static int validatePassword(String password) {
		if (password.isEmpty() || password.isBlank() || password.length() < 8 || password.length() > 16) {
			log.warn("Password should be minimum 8 characters and maximum 16 characters");
			log.warn("Invalid Password");
			log.info("Enter password:");
			return 1;
		} else
			return 0;
	}

	// Checks mail
	public static int validateMail(String mail) {
		if (!mail.contains("@") || !mail.contains(".")) {
			log.warn("Invalid mail");
			log.info("Enter email:");
			return 1;
		} else
			return 0;
	}

	public static String takeCaptcha() {
		int a = (int) (Math.random() * (9000 - 100 + 1) + 100);
		String b = String.valueOf(a);
		return b;
	}

	public int captchaVerification() {
		String captcha = takeCaptcha();
		log.info(captcha);
		log.info("Enter the above captcha:\n");
		String input = sc.next();
		if (input.equals(captcha)) {
			return 0;
		} else {
			captchaVerification();
		}
		return 1;
	}
}
