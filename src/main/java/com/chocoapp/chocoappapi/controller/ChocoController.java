package com.chocoapp.chocoappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chocoapp.chocoappapi.exception.ValidationException;
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
	public ResponseEntity<?> listAll() {
		try {
			return new ResponseEntity<>(chocoService.findAllChocos(), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("search")
	public ResponseEntity<?> search(@RequestParam("name") String name) {
		try {
			return new ResponseEntity<>(chocoService.search(name), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("sort-price-asc")
	public ResponseEntity<?> sortPriceInAsc() {
		try {
			return new ResponseEntity<>(chocoService.sortPriceByAsc(), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("sort-price-desc")
	public ResponseEntity<?> sortPriceInDesc() {
		try {
			return new ResponseEntity<>(chocoService.sortPriceByDesc(), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}