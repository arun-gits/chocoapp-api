package com.chocoapp.chocoappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chocoapp.chocoappapi.model.Chocolates;
import com.chocoapp.chocoappapi.repository.ChocoRepository;

@RestController
@RequestMapping("chocolates")
public class ChocoController {
	
	@Autowired
	ChocoRepository chocoRepository;
	
	
	@GetMapping("list-all")
	public List<Chocolates> listAll(){
		List<Chocolates> chocolates = chocoRepository.findAll();
		return chocolates;
	}
	
	@GetMapping("search-by-name")
	public Chocolates findByName(@RequestParam("name") String name) {
		Chocolates chocolate = chocoRepository.findByName(name);
		return chocolate;
	}
}
