package com.chocoapp.chocoappapi.dao;

public interface IShoppingDAO {

	int totalPrice(int id) throws Exception;
	// Shows total price after the insertion

	public void transactionInserter(int id, String paymentMode) throws Exception;
}
