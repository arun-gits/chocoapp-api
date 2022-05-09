package com.chocoapp.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chocoapp.converter.ChocoConverter;
import com.chocoapp.dto.ChocolateDTO;
import com.chocoapp.exception.ValidationException;
import com.chocoapp.model.Chocolate;
import com.chocoapp.model.User;
import com.chocoapp.model.UserCart;
import com.chocoapp.repository.CartRepository;
import com.chocoapp.repository.ChocoRepository;
import com.chocoapp.repository.TransactionRepository;

@Repository
public class ChocoService {

	private static final String NO_CHOCOLATES_FOUND = "No chocolates found";

	@Autowired
	ChocoRepository chocoRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	TransactionRepository transactionRepository; 
	
	public List<ChocolateDTO> findAllChocos() throws ValidationException {
		List<Chocolate> chocolateList = chocoRepository.findAll();
		List<ChocolateDTO> chocolates = ChocoConverter.toDTO(chocolateList);
		if (chocolates.isEmpty()) {
			throw new ValidationException(NO_CHOCOLATES_FOUND);
		}
		return chocolates;
	}

	public List<ChocolateDTO> search(String name) throws ValidationException {
		List<ChocolateDTO> chocolates = findAllChocos();
		List<ChocolateDTO> search = chocolates.stream()
				.filter(c -> c.getName().toLowerCase().contains(name.toLowerCase())).toList();
		if (search.isEmpty()) {
			throw new ValidationException(NO_CHOCOLATES_FOUND);
		}
		return search;
	}

	public List<ChocolateDTO> sortPriceByAsc() throws ValidationException {
		List<ChocolateDTO> list = findAllChocos();
		Collections.sort(list);
		return list;
	}

	public List<ChocolateDTO> sortPriceByDesc() throws ValidationException {
		List<ChocolateDTO> list = findAllChocos();
		Collections.sort(list, Comparator.reverseOrder());
		return list;
	}

	public void addToCart(int userId, String shop) throws ValidationException {
		if (shop.isEmpty()) {
			throw new ValidationException("Invalid request");
		}
		
		User user = new User();
		user.setId(userId);
		
		
		List<String> list = Arrays.asList(shop.split(","));
		Map<Integer, Integer> quants = new TreeMap<Integer, Integer>();
		long n = chocoRepository.count();
		for (int j = 1; j < n; j++) {
			int count = 0;
			for (String i : list) {
				int k = Integer.parseInt(i);
				if (j == k) {
					count++;
				}
			}
			if (count != 0) {
				quants.put(j, count);
			}
		}
		for (Integer chocoId : quants.keySet()) {
			UserCart item = new UserCart();
//			item.setUserId(userId);
			item.setUser(user);			
			int quantity = quants.get(chocoId);
			Chocolate c = new Chocolate();
			c.setId(chocoId);
			item.setChoco(c);
//			item.setChocoId(chocoId);
			item.setChocoQuantity(quantity);
			cartRepository.save(item);
		}
	}

}