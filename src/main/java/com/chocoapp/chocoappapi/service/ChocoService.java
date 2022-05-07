package com.chocoapp.chocoappapi.service;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chocoapp.chocoappapi.converter.ChocoConverter;
import com.chocoapp.chocoappapi.dto.ChocolateDTO;
import com.chocoapp.chocoappapi.exception.ValidationException;
import com.chocoapp.chocoappapi.model.Chocolate;
import com.chocoapp.chocoappapi.repository.ChocoRepository;

@Repository
public class ChocoService {

	private static final String NO_CHOCOLATES_FOUND = "No chocolates found";
	@Autowired
	ChocoRepository chocoRepository;

	public List<ChocolateDTO> findAllChocos() throws ValidationException{
		List<Chocolate> chocolateList = chocoRepository.findAll();
		List<ChocolateDTO> chocolates = ChocoConverter.toDTO(chocolateList);
		if(chocolates.isEmpty()) {
			throw new ValidationException (NO_CHOCOLATES_FOUND);
		}
		return chocolates;
	}

	public List<ChocolateDTO> search(String name) throws ValidationException{
		List<ChocolateDTO> chocolates = findAllChocos();
		List<ChocolateDTO> search = chocolates.
				stream().
				filter(c-> c.getName().toLowerCase().contains(name.toLowerCase())).
				collect(Collectors.toList());
		if(search.isEmpty()) {
			throw new ValidationException(NO_CHOCOLATES_FOUND);
		}
		return search;
	}
	
	public List<ChocolateDTO> sortPriceByAsc() throws ValidationException{
		List <ChocolateDTO> list = findAllChocos();
		Collections.sort(list);
		return list;
	}
	
	public List<ChocolateDTO> sortPriceByDesc() throws ValidationException{
		List <ChocolateDTO> list = findAllChocos();
		Collections.sort(list,Comparator.reverseOrder());
		return list;
	}
	
}