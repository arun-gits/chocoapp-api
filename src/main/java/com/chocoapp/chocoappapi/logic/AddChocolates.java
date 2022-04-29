package com.chocoapp.chocoappapi.logic;

import java.util.Map;
import java.util.TreeMap;

import com.chocoapp.chocoappapi.dao.CandiesDAO;

public class AddChocolates {

	public static void addCandies() throws Exception {
		Map<String, Integer> chocolates = new TreeMap<String, Integer>();
		chocolates.put("5 Star mini", 5);
		chocolates.put("5 Star Mega", 10);
		chocolates.put("Crush Candy", 20);
		chocolates.put("Dairy Milk Choco", 10);
		chocolates.put("Dairy Milk Bubble", 30);
		chocolates.put("Dairy Milk Silk", 50);
		chocolates.put("Dairy Milk Love", 100);
		chocolates.put("Gems chota", 10);
		chocolates.put("Gems Big", 20);
		chocolates.put("KitKat Bar", 10);
		chocolates.put("KitKat Dessert", 50);
		chocolates.put("MilkyBar", 10);
		chocolates.put("Munch", 10);
		chocolates.put("Perk", 5);
		chocolates.put("Snickers", 10);
		chocolates.put("Snickers Shine On", 10);
		CandiesDAO addcandy = new CandiesDAO();
		for (String c : chocolates.keySet()) {
			Integer i = chocolates.get(c);
			addcandy.addChocolates(c, i);
		}
	}
}
