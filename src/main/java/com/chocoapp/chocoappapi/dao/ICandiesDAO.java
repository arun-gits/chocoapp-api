package com.chocoapp.chocoappapi.dao;

import java.util.List;

import com.chocoapp.chocoappapi.model.Chocolates;

public interface ICandiesDAO {

	List<Chocolates> listAllChocolates() throws Exception;
	// Show all chocolates

	List<Chocolates> findByName(String name) throws Exception;
	// Search chocolates by name

	List<Chocolates> findNameAlike(String name) throws Exception;
	// Show chocolates with similar name

	List<Chocolates> sortPriceInAsc() throws Exception;
	// Show chocolates in price ascending sorted order

	List<Chocolates> sortPriceInDesc() throws Exception;
	// Show chocolates in price descending sorted order

	int count() throws Exception;
	// Show total count of chocolates

	void addChocolates(String chocolate, Integer price) throws Exception;
	// Admin can add chocolates

	void deleteChocolates(String chocolate) throws Exception;
	// Admin can delete chocolates

	void updatePrice(Integer price, Integer chocoId) throws Exception;
	// Admin can update chocolates' price

}
