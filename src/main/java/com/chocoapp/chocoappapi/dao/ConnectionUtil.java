package com.chocoapp.chocoappapi.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConnectionUtil {
	private static Logger log=LogManager.getLogger(ConnectionUtil.class);

	public static Connection getConnection() throws Exception, SQLException {
		Connection connect = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect=DriverManager.getConnection("jdbc:mysql://101.53.133.59:3306/revature_training_db","rev_user","rev_user");		
		log.info("Processing...");
		return connect;
	}
}
