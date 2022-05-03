package com.chocoapp.chocoappapi.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chocoapp.chocoappapi.model.Chocolates;

import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CandiesDAO implements ICandiesDAO {
	
	private static Logger log = LogManager.getLogger(CandiesDAO.class);

	public List<Chocolates> listAllChocolates() throws Exception {
		List<Chocolates> listAllChocolates = new ArrayList<>();
		Connection connect = null;
		PreparedStatement show = null;
		ResultSet data = null;
		try {
			connect = ConnectionUtil.getConnection();
		} catch (Exception e) {

		}
		String query = "select choco_id,choco_name,price from candies_list";

		show = connect.prepareStatement(query);
		data = show.executeQuery();
		while (data.next()) {
			Integer chocoid = data.getInt("choco_id");
			String chocolate = data.getString("choco_name");
			int price = data.getInt("price");
			Chocolates list = new Chocolates(chocoid, chocolate, price);
			listAllChocolates.add(list);
		}
		show.close();
		connect.close();
		return listAllChocolates;
	}

	public List<Chocolates> findByName(String name) throws Exception {

		List<Chocolates> findByName = new ArrayList<>();
		Connection connect = null;
		PreparedStatement show = null;
		ResultSet data = null;

		connect = ConnectionUtil.getConnection();
		String query = "select choco_id,choco_name,price from candies_list where choco_name=?";
		show = connect.prepareStatement(query);
		show.setString(1, name);
		data = show.executeQuery();

		if (data.next()) {
			Integer chocoid = data.getInt("choco_id");
			String chocolate = data.getString("choco_name");
			int price = data.getInt("price");

			Chocolates menu = new Chocolates();
			menu.setId(chocoid);
			menu.setName(chocolate);
			menu.setPrice(price);
			findByName.add(menu);
		} else {
			log.info("No records found");
			System.exit(1);
		}
		show.close();
		connect.close();
		return findByName;
	}

	public List<Chocolates> findNameAlike(String name) throws Exception {
		List<Chocolates> findNameAlike = new ArrayList<>();
		Connection connect = null;
		// PreparedStatement show=null;
		ResultSet data = null;

		connect = ConnectionUtil.getConnection();
//		String query="SELECT choco_id,choco_name,price FROM candies_list WHERE choco_name LIKE '%?%'"; 
//		show=connect.prepareStatement(query);
//		show.setString(1,name);
//		data=show.executeQuery();
		String query = "select choco_id,choco_name,price from candies_list where choco_name like '%" + name + "%'";
		Statement statement = connect.prepareStatement(query);
		data = statement.executeQuery(query);
		while (data.next()) {
			Integer chocoid = data.getInt("choco_id");
			String chocolate = data.getString("choco_name");
			int price = data.getInt("price");
			Chocolates menu = new Chocolates(chocoid, chocolate, price);
			findNameAlike.add(menu);
		}
		statement.close();
		connect.close();
		return findNameAlike;
	}

	public List<Chocolates> sortPriceInAsc() throws Exception {
		List<Chocolates> chocolatesInAsc = new ArrayList<>();
		Connection connect = null;
		PreparedStatement show = null;
		ResultSet data = null;

		connect = ConnectionUtil.getConnection();
		String query = "select choco_id,choco_name,price from candies_list order by price asc";
		show = connect.prepareStatement(query);
		data = show.executeQuery();
		while (data.next()) {
			Integer chocoid = data.getInt("choco_id");
			String chocolate = data.getString("choco_name");
			int price = data.getInt("price");

			Chocolates menu = new Chocolates();
			menu.setName(chocolate);
			menu.setPrice(price);
			menu.setId(chocoid);

			chocolatesInAsc.add(menu);
		}
		show.close();
		connect.close();
		return chocolatesInAsc;
	}

	public List<Chocolates> sortPriceInDesc() throws Exception {

		List<Chocolates> chocolatesInDesc = new ArrayList<>();
		Connection connect = null;
		PreparedStatement show = null;
		ResultSet data = null;

		connect = ConnectionUtil.getConnection();
		String query = "select choco_id,choco_name,price from candies_list order by price desc";
		show = connect.prepareStatement(query);
		data = show.executeQuery();

		while (data.next()) {
			Integer chocoid = data.getInt("choco_id");
			String chocolate = data.getString("choco_name");
			int price = data.getInt("price");

			Chocolates menu = new Chocolates();
			menu.setName(chocolate);
			menu.setPrice(price);
			menu.setId(chocoid);

			chocolatesInDesc.add(menu);
		}
		show.close();
		connect.close();
		return chocolatesInDesc;
	}

	public int count() throws Exception {

		Connection connect = null;
		Statement show = null;
		ResultSet data = null;
		int rows = 0;

		connect = ConnectionUtil.getConnection();
		String query = "select count(*) from candies_list";
		show = connect.createStatement();
		data = show.executeQuery(query);
		if (data.next()) {
			rows = data.getInt("count(*)");
		}
		show.close();
		connect.close();
		return rows;
	}

	public void addChocolates(String chocolate, Integer price) throws Exception {

		Connection connect = null;
		CallableStatement add = null;
		String message = null;
		connect = ConnectionUtil.getConnection();

		String query = "call add_candy(?,?,?)";

		add = connect.prepareCall(query);
		add.setString(1, chocolate);
		add.setInt(2, price);
		add.registerOutParameter(3, java.sql.Types.VARCHAR);
		add.executeUpdate();
		message = add.getString(3);
		log.info(chocolate + " is " + message + "fully added to the list");
		add.close();
		connect.close();
	}

	public void deleteChocolates(String chocolate) throws Exception {

		Connection connect = null;
		CallableStatement delete = null;
		String message = null;

		connect = ConnectionUtil.getConnection();

		delete = connect.prepareCall("{call delete_candy (?,?)}");
		delete.setString(1, chocolate);
		delete.registerOutParameter(2, java.sql.Types.VARCHAR);
		delete.executeUpdate();
		message = delete.getString(2);

		log.info(chocolate + " is deleted " + message + "fully");
		delete.close();
		connect.close();
	}

	public void updatePrice(Integer price, Integer chocoId) throws Exception {

		Connection connect = null;
		CallableStatement updatePrice = null;
		String message = null;
		connect = ConnectionUtil.getConnection();
		// String query="call updatePrice_candy(?,?,?)";
		// updatePrice=connect.prepareCall(query);
		updatePrice = connect.prepareCall("{call updatePrice_candy (?,?,?)}");
		updatePrice.setInt(1, price);
		updatePrice.setInt(2, chocoId);
		updatePrice.registerOutParameter(3, java.sql.Types.VARCHAR);
		updatePrice.executeUpdate();
		message = updatePrice.getString(3);
		log.info("Price of choco id " + chocoId + " is updated to " + price + " " + message + "fully");
		updatePrice.close();
		connect.close();
	}

}