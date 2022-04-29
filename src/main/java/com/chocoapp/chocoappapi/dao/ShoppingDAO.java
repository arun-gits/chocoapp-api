package com.chocoapp.chocoappapi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ShoppingDAO implements IShoppingDAO {

	public int totalPrice(int id) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		String query = "select price from candies_list where choco_id=?";
		statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		result = statement.executeQuery();
		int price = 0;
		while (result.next()) {
			price = result.getInt("price");
		}
		return price;
	}

	public void transactionInserter(int id, String paymentMode) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement statement = null;
//		ResultSet result=null;
		Date date = Date.valueOf(LocalDate.now());
		String query = "insert into candy_trans(user_id,choco_id,purchased_on,payment_mode)values(?,?,?,?)";
		statement = connection.prepareStatement(query);
		statement.setInt(1, 2);
		statement.setInt(2, id);
		statement.setDate(3, date);
		statement.setString(4, paymentMode);
		statement.executeUpdate();
	}

}
