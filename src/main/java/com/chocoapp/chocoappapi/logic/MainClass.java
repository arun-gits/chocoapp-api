package com.chocoapp.chocoappapi.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chocoapp.chocoappapi.dao.CandiesDAO;
import com.chocoapp.chocoappapi.dao.ConnectionUtil;
import com.chocoapp.chocoappapi.dao.UserDAO;
import com.chocoapp.chocoappapi.model.User;

public class MainClass {
	static Scanner sc = new Scanner(System.in);
	private static Logger log = LogManager.getLogger(MainClass.class);

	public static void listCandies() throws Exception {
		CandiesDAO candy = new CandiesDAO();
		ShowChocolates.list(candy.listAllChocolates());
	}

	public static void Registration() throws Exception {

		log.info("Enter your name:");
		String name = sc.nextLine();
		int ch1 = OverallValidation.validateName(name);
		while (ch1 != 0) {
			name = sc.nextLine();
			ch1 = OverallValidation.validateName(name);
		}

		log.info("Enter mobile number:");
		String number = sc.nextLine();
		int ch2 = OverallValidation.validateNumber(number);
		while (ch2 != 0) {
			number = sc.nextLine();
			ch2 = OverallValidation.validateNumber(number);
		}

		log.info("Enter your mail:");
		String email = sc.nextLine();
		int chk4 = OverallValidation.validateMail(email);
		while (chk4 != 0) {
			email = sc.nextLine();
			chk4 = OverallValidation.validateMail(email);
		}

		log.info("Enter your address:");
		String address = sc.next();

		log.info("Enter password:");
		String password = sc.nextLine();
		int ch3 = OverallValidation.validatePassword(password);
		while (ch3 != 0) {
			password = sc.nextLine();
			ch3 = OverallValidation.validateName(password);
		}
		int a = signUpMailValidation(email);
		int b = signUpNumberValidation(number);
		if (a + b == 0) {
			addUser(name, number, email, address, password);
		} else {
			log.info("Sorry ! Wrong input, refresh the page");
			System.exit(0);
		}
	}

	public static int signUpMailValidation(String mail) throws Exception {

		Connection connect = null;
		PreparedStatement validation = null;
		ResultSet data = null;

		connect = ConnectionUtil.getConnection();
		String query = "select user_mail from candy_users where user_mail=?";
		validation = connect.prepareStatement(query);
		validation.setString(1, mail);
		data = validation.executeQuery();
		String email = null;
		while (data.next()) {
			email = data.getString("user_mail");
		}
		connect.close();
		if (email == null) {
			return 0;
		} else {
			log.info("You're an existing user");
			return 1;
		}
	}

	public static int signUpNumberValidation(String mobile) throws Exception {
		Connection connect = null;
		PreparedStatement validation = null;
		ResultSet data = null;

		connect = ConnectionUtil.getConnection();
		String query = "select user_mobile from candy_users where user_mobile=?";
		validation = connect.prepareStatement(query);
		validation.setString(1, mobile);
		data = validation.executeQuery();
		String number = null;
		while (data.next()) {
			number = data.getString("user_mobile");
		}
		connect.close();
		if (number == null) {
			return 0;
		} else {
			log.info("You're an existing user");
			return 1;
		}
	}

	public static void addUser(String name, String number, String email, String address, String password)
			throws Exception {
		User user = new User();
		user.setName(name);
		user.setMobile(number);
		user.setMail(email);
		user.setAddress(address);
		user.setPassword(password);
		UserDAO add = new UserDAO();
		add.addUser(user);
		sc.close();

	}

	public static void Login() throws Exception {
		int a = 0;
		int b = 0;
		int c = 0;
		User user = new User();
		int count = 1;
		log.info("Enter Email/Number:");
		String data = sc.next();
		log.info("Enter password:");
		String password = sc.next();
		if (data.contains("@") || data.contains(".")) {
			user.setMail(data);
			count = 0;
		} else {
			user.setMobile(data);
			count = 1;
		}
		user.setPassword(password);
		LoginValidation test = new LoginValidation();
		if (count == 0) {
			a = test.validateEmail(user.getMail());
			b = test.validatePassword(user.getPassword());
			c = 0;
		} else if (count == 1) {
			a = test.validateMobile(data);
			b = test.validatePassword(password);
			c = 1;
		}
		UserDAO dbTest = new UserDAO();
		if (a + b == 0 && c == 0) {
			String d = dbTest.loginMailValidation(user.getMail(), user.getPassword());
			if (d.equals("")) {

			} else {
				log.info("Sorry ! Wrong input, refresh the page");
				System.exit(0);
			}
		}

		else if (a + b == 0 && c == 1) {
			String d = dbTest.loginNumberValidation(user.getMobile(), user.getPassword());
			if (d.equals("")) {

			} else {
				log.info("Sorry ! Wrong input, refresh the page");
				System.exit(0);
			}
		}
		sc.close();
	}

	public static void PayMode() throws Exception {
		log.info("Enter the payment mode");
		log.info("1. UPI id");
		log.info("2. Debit/Credit card");
		log.info("3. Cash on Delivery");
		log.info("4. Back");
		log.info("5. Exit");
		int choice = sc.nextInt();
		if (choice == 1) {
			PayOn(choice);
		} else if (choice == 2) {
			PayOn(choice);
		} else if (choice == 3) {
			PayOn(choice);
		} else if (choice == 4) {

		} else if (choice == 5) {
			log.info("Thanks for shopping ! :)");
			System.exit(0);
		}

		else {
			log.info("Enter valid input");
			PayMode();
		}

	}

	public static void PayOn(int a) throws Exception {

		OverallValidation captcha = new OverallValidation();
		switch (a) {
		case 1:
			log.info("Enter your UPI id:");
			String upi = sc.next();
			if (!upi.contains("@") || !upi.contains(".")) {
				log.info("Invalid UPI");
				log.info("Enter valid UPI:");
				PayOn(1);
			} else {

				int capt = captcha.captchaVerification();
				if (capt == 0) {

					log.info("Returning you to main page");

				}
			}
			break;
		case 2:
			log.info("Enter Debit/Credit Card number");
			String deb = sc.next();
			sc.nextLine();
			if (deb.length() == 16) {
				int capt = captcha.captchaVerification();
				if (capt == 0) {

					log.info("Returning you to main page");

				}
			} else {
				log.info("Invalid input");
				PayOn(2);
			}
			break;
		case 3:
			log.info("Your order will be delivered in delivery address");
			int capt = captcha.captchaVerification();
			if (capt == 0) {
				log.info("Returning you to main page");

			}
		}
	}
}