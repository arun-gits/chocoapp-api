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
		return chocoService.findAllChocos();	
	}
	
	@GetMapping("search-by-name")
	public Chocolates findByName(@RequestParam("name") String name) {
		return chocoService.findByName(name);
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
		return chocoService.search(name);
	}
	
	@GetMapping("sort-price-asc")
	public List<Chocolates> ascList(){
		return chocoService.sortPriceByAsc();
		
	}
	
	@GetMapping("sort-price-desc")
	public List<Chocolates> descList(){
		// List<Chocolates> descList = chocoService.sortPriceByDesc();
		return chocoService.sortPriceByDesc();
	}
	
}