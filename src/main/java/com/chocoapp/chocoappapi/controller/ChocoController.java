package com.chocoapp.chocoappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chocoapp.chocoappapi.model.Chocolates;
import com.chocoapp.chocoappapi.repository.ChocoRepository;
import com.chocoapp.chocoappapi.service.ChocoService;

@RestController
@RequestMapping("chocolates")
public class ChocoController {
	
	@Autowired
	ChocoRepository chocoRepository;
	
	@Autowired
	ChocoService chocoService;
	
	@GetMapping("list-all")
	public List<Chocolates> listAll(){
		List<Chocolates> chocolates = chocoService.findAllChocos();
		return chocolates;
	}
	
	@GetMapping("search-by-name")
	public Chocolates findByName(@RequestParam("name") String name) {
		Chocolates chocolate = chocoService.findByName(name);
		return chocolate;
	}
	
//	@GetMapping("search-choco")
//	public List<Chocolates> searchChoco(@RequestParam("name") String name){	
//		System.out.println("requested");
//		List<Chocolates> list = chocoService.searchChoco(name);
//		return list;
//	}
	
	@GetMapping("search")
	public List<Chocolates> search(@RequestParam ("name") String name){
		System.out.println("requested");
		List<Chocolates> chocolate = chocoService.search(name);
		return chocolate;
	}
	
	@GetMapping("sort-price-asc")
	public List<Chocolates> ascList(){
		List<Chocolates> ascList = chocoService.sortPriceByAsc();
		return ascList;
	}
	
	@GetMapping("sort-price-desc")
	public List<Chocolates> descList(){
		List<Chocolates> descList = chocoService.sortPriceByDesc();
		return descList;
	}
	
}