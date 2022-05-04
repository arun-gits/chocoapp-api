package com.chocoapp.chocoappapi.controller;

import java.util.List;

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

import com.chocoapp.chocoappapi.dto.ChocolateDTO;
import com.chocoapp.chocoappapi.model.Chocolates;
import com.chocoapp.chocoappapi.model.User;
import com.chocoapp.chocoappapi.service.AdminService;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("list-users")
	public List<User> listUsers() {
		return adminService.listAllUsers();
	}

	@DeleteMapping("delete-user-id/{id}")
	public String deleteById(@PathVariable("id") int id) {
		return adminService.deleteUserById(id);

	}

	@GetMapping("block-user-id/{id}")
	public String blockById(@PathVariable("id") int id) {
		return adminService.blockUserById(id);

	}

	@GetMapping("activate-user-id/{id}")
	public String activateById(@PathVariable("id") int id) {
		return adminService.activateUserById(id);

	}

	@GetMapping("list-chocolates")
	public List<ChocolateDTO> listChocolates() {
		return adminService.listAllChocos();

	}

	@PostMapping("add-chocolate")
	public ResponseEntity<String> addChocolate(@RequestBody ChocolateDTO chocolate) {
		String addChocolate = adminService.addChocolate(chocolate);
		return new ResponseEntity<>(addChocolate, HttpStatus.OK);
	}

	@DeleteMapping("delete-choco-id/{id}")
	public String deleteChoco(@PathVariable("id") int id) {
		return adminService.deleteChocoById(id);

	}

	@PatchMapping("update-choco-price/{id}/{price}")
	public String updateChocoPrice(@PathVariable("id") int id, @PathVariable("price") int price) {
		return adminService.updateChocoPrice(id, price);
	}

}
