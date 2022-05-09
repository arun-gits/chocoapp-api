package com.chocoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocoapp.dto.ChocolateDTO;
import com.chocoapp.exception.ValidationException;
import com.chocoapp.service.AdminService;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("list-users")
	public ResponseEntity<?> listUsers() {
		try {
			return new ResponseEntity<>(adminService.listAllUsers(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("delete-user-id/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(adminService.deleteUserById(id), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("block-user-id/{id}")
	public ResponseEntity<String> blockById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(adminService.blockUserById(id), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("activate-user-id/{id}")
	public ResponseEntity<String> activateById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(adminService.activateUserById(id), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("list-chocolates")
	public ResponseEntity<?> listChocolates() {
		try {
			return new ResponseEntity<>(adminService.findAllChocos(), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("add-chocolate")
	public ResponseEntity<String> addChocolate(@RequestBody ChocolateDTO chocolate) {
		try {
			return new ResponseEntity<>(adminService.addChocolate(chocolate), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("delete-choco-id/{id}")
	public ResponseEntity<String> deleteChoco(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(adminService.deleteChocoById(id), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PatchMapping("update-choco-price/{id}/{price}")
	public ResponseEntity<String> updateChocoPrice(@PathVariable("id") int id, @PathVariable("price") int price) {
		try {
			return new ResponseEntity<>(adminService.updateChocoPrice(id, price), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
