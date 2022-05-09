package com.chocoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chocoapp.exception.ValidationException;
import com.chocoapp.model.Purchase;
import com.chocoapp.repository.ChocoRepository;
import com.chocoapp.service.ChocoService;

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
	
	@GetMapping("add-to-cart/{user_id}")
	public ResponseEntity<?> addToCart(@RequestParam("chocos") String shop, @PathVariable("user_id") int userId) {
		try {
			chocoService.addToCart(userId,shop);
			return new ResponseEntity<>("Success",HttpStatus.OK);
		}catch(ValidationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@PostMapping("purchase/{order_id}")
//	public ResponseEntity<?> purchase(@RequestBody Purchase purchase, @PathVariable("order_id") int orderId){
//		try {
//			chocoService.purchased(orderId, purchase);
//			return new ResponseEntity<>("Success",HttpStatus.OK);
//		}catch(Exception e) {
//			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}	