package com.chocoapp.chocoappapi.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chocoapp.chocoappapi.model.Chocolates;
import com.chocoapp.chocoappapi.repository.ChocoRepository;

@Repository
public class ChocoService {

	@Autowired
	ChocoRepository chocoRepository;

	public List<Chocolates> findAllChocos() {
		List<Chocolates> chocos = chocoRepository.findAll();
		return chocos;
	}

	public Chocolates findByName(String name) {
		Chocolates chocolate = chocoRepository.findByName(name);
		return chocolate;
	}

//	public List<Chocolates> searchChoco(String name) {
//		// without static
//		System.out.println("received in service");
//		List<Chocolates> list = findAllChocos();
//		System.out.println(list);
//		System.out.println("result from repository");
//		List<Chocolates> searchChoco = list.stream().filter(p -> p.getName().contains(name))
//				.collect(Collectors.toList());
//		if (searchChoco == null) {
//			return null;
//		}
//		return searchChoco;
//
//	}

	public List<Chocolates> search(String name) {
		// List<Chocolates> search = chocoRepository.search(name);
		List<Chocolates> chocolates = findAllChocos();
		List<Chocolates> search = chocolates.
				stream().
				filter(c-> c.getName().toLowerCase().contains(name.toLowerCase())).
				collect(Collectors.toList());
		return search;
	}
	
	public List<Chocolates> sortPriceByAsc(){
		List <Chocolates> list = findAllChocos();
		Collections.sort(list);
		return list;
	}
	
	public List<Chocolates> sortPriceByDesc(){
		List <Chocolates> list = findAllChocos();
		Collections.sort(list,Comparator.reverseOrder());
		return list;
	}
	
}