package com.chocoapp.chocoappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocoapp.chocoappapi.model.Chocolates;
import com.chocoapp.chocoappapi.model.User;
import com.chocoapp.chocoappapi.service.AdminService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("list-users")
	public List<User> listUsers(){
		List<User> users = adminService.listAllUsers();
		return users;
	}
	
	@DeleteMapping("delete-user-id/{id}")
	public String deleteById(@PathVariable("id") int id) {
		String message = adminService.deleteUserById(id);
		return message;
	}
	
	@GetMapping("block-user-id/{id}")
	public String blockById(@PathVariable("id") int id) {
		String message = adminService.blockUserById(id);
		return message;
	}
	
	@GetMapping("activate-user-id/{id}")
	public String activateById(@PathVariable("id") int id) {
		String message = adminService.activateUserById(id);
		return message;
	}
	
	@GetMapping("list-chocolates")
	public List<Chocolates> listChocolates(){
		List<Chocolates> chocolates = adminService.listAllChocos();
		return chocolates;
	}
	
	@PostMapping("add-chocolate")
	public String addChocolate(@RequestBody Chocolates chocolate) {
		String message = adminService.addChocolate(chocolate);
		return message;
	}
	
	@DeleteMapping("delete-choco-id/{id}")
	public String deleteChoco(@PathVariable("id") int id) {
		String message = adminService.deleteChocoById(id);
		return message;
	}
	
	@PatchMapping("update-choco-price/{id}/{price}")
	public String updateChocoPrice(@PathVariable("id") int id, @PathVariable("price") int price) {
		String message = adminService.updateChocoPrice(id, price);
		return message;
	}

}
