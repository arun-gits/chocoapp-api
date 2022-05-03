package com.chocoapp.chocoappapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chocoapp.chocoappapi.model.User;

public class UserDAO implements IUserDAO {

	private static Logger log = LogManager.getLogger(CandiesDAO.class);

	public String addUser(User user) throws Exception {
		Connection connect = null;
		connect = ConnectionUtil.getConnection();
		String query = "insert into candy_users (user_name,user_mobile,user_mail,user_address,user_password) values(?,?,?,?,?) ";
		try (PreparedStatement add = connect.prepareStatement(query);) {
			String message = null;
			add.setString(1, user.getName());
			add.setString(2, user.getMobile());
			add.setString(3, user.getMail());
			add.setString(4, user.getAddress());
			add.setString(5, user.getPassword());
			int rows = add.executeUpdate();
			log.info(rows + " user added");
			message = "Welcome " + user.getName() + "! :)";
			log.info("Welcome " + user.getName() + "! :)");
			connect.close();
			return message;
		}

	}

	public User userDetails(String mail) throws Exception {
		Connection connect = null;
		PreparedStatement show = null;
		ResultSet data = null;

		connect = ConnectionUtil.getConnection();
		String query = "select * from candy_users where email=?";
		show = connect.prepareStatement(query);
		show.setString(1, mail);
		data = show.executeQuery();
		User user = new User();
		while (data.next()) {
			String name = data.getString("user_name");
			String mobile = data.getString("user_mobile");
			String email = data.getString("user_mail");
			String address = data.getString("user_address");

			user.setAddress(address);
			user.setName(name);
			user.setMobile(mobile);
			user.setMail(email);

		}
		show.close();
		connect.close();
		return user;
	}

	public int signUpMailValidation(String mail) throws Exception {

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
		validation.close();
		connect.close();
		if (email == null) {
			return 0;
		} else {
			throw new Exception("You're an existing user");
		}
	}

	public int signUpNumberValidation(String mobile) throws Exception {
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
		validation.close();
		connect.close();
		if (number == null) {
			return 0;
		} else {
			throw new Exception("You're an existing user");
		}
	}

	public String loginMailValidation(String mail, String password) throws Exception {
		Connection connect = null;
		PreparedStatement validation = null;
		ResultSet data = null;
		String message = null;
		String email = null;
		String key = null;
		String name = null;

		connect = ConnectionUtil.getConnection();
		String query = "select user_name,user_mail,user_password from candy_users where user_mail=?";
		validation = connect.prepareStatement(query);
		validation.setString(1, mail);
		data = validation.executeQuery();

		while (data.next()) {
			email = data.getString("user_mail");
			key = data.getString("user_password");
			name = data.getString("user_name");
		}
		validation.close();
		connect.close();
		if (email == null) {
			throw new Exception("You're not a registered user");

		} else if (key.equals(password)) {
			// log.info("Hello " + name + "! :)");
			message = "Hello " + name + "! :)";
			return message;
		} else {
			throw new Exception("Invalid Credentials");
		}
	}

	public String loginNumberValidation(String mobile, String password) throws Exception {
		Connection connect = null;
		PreparedStatement validation = null;
		ResultSet data = null;
		String message = null;
		String number = null;
		String key = null;
		String name = null;

		connect = ConnectionUtil.getConnection();
		String query = "select user_name,user_mobile,user_password from candy_users where user_mobile=?";
		validation = connect.prepareStatement(query);
		validation.setString(1, mobile);
		data = validation.executeQuery();

		while (data.next()) {
			number = data.getString("user_mobile");
			key = data.getString("user_password");
			name = data.getString("user_name");
		}
		validation.close();
		connect.close();
		if (number == null) {
			throw new Exception("You're not a registered user");

		} else if (key.equals(password)) {
			// log.info("Hello " + name + "! :)");
			message = "Hello " + name + "! :)";
			return message;
		} else {
			throw new Exception("Invalid Credentials");

		}
	}

}
