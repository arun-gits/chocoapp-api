package com.chocoapp.chocoappapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chocoapp.chocoappapi.converter.ChocoConverter;
import com.chocoapp.chocoappapi.dto.ChocolateDTO;
import com.chocoapp.chocoappapi.model.Chocolates;
import com.chocoapp.chocoappapi.model.User;
import com.chocoapp.chocoappapi.repository.ChocoRepository;
import com.chocoapp.chocoappapi.repository.UserRepository;

@Repository
public class AdminService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ChocoRepository chocoRepository;
	
	@Autowired
	ChocoService chocoService;
	
	// view all users
	public List<User> listAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	// delete user from the list
	public String deleteUserById(int id) {
		String message = null;
		User user = userRepository.findById(id);
		if (user == null) {
			message = "Invalid user id";
		} else {
			userRepository.deleteById(id);
			message = "id " + id + " deleted successfully " + user;
		}
		return message;
	}

	// block user from the list
	public String blockUserById(int id) {
		String message = null;
		User user = userRepository.findById(id);
		if (user != null) {
			userRepository.blockUser(id);
			User deactivatedUser = userRepository.findById(id);
			message = "Successfully deactivated " + deactivatedUser;
		} else {
			message = "Invalid user id";
		}
		return message;
	}

	// activate user
	public String activateUserById(int id) {
		String message = null;
		User user = userRepository.findById(id);
		if (user != null) {
			userRepository.activateUser(id);

			User activatedUser = userRepository.findById(id);
			message = "Successfully activated " + activatedUser;
		} else {
			message = "Invalid user id";
		}
		return message;
	}

	// list all chocolates
	public List<ChocolateDTO> listAllChocos() {
		List<Chocolates> chocos = chocoRepository.findAll();
		
		List<ChocolateDTO> list = ChocoConverter.toDTO(chocos);
		return list;
	}

	// add chocolate
	public String addChocolate(ChocolateDTO chocolate) {
		String message = null;
		List<Chocolates> exists = chocoService.search(chocolate.getName());
		if (exists.isEmpty()) {
			Chocolates c = ChocoConverter.toModel(chocolate);
			chocoRepository.save(c);
			Chocolates addedChoco = chocoRepository.findByName(chocolate.getName());
			message = "Chocolate added successfully " + addedChoco;
		} else {
			message = "Chocolate aleady exists";
		}
		return message;
	}

	// delete chocolate from the list
	public String deleteChocoById(int id) {
		String message = null;
		Chocolates chocolate = chocoRepository.findById(id);
		if (chocolate != null) {
			chocoRepository.deleteById(id);
			message = "Successfully removed " + chocolate;
		} else {
			message = "No chocolate found in this id";
		}
		return message;
	}

	// update chocolate price
	public String updateChocoPrice(int id, int price) {
		String message = null;
		Chocolates chocolate = chocoRepository.findById(id);
		if (chocolate != null) {
			chocoRepository.updateChocoPrice(id, price);
			Chocolates choco = chocoRepository.findByName(chocolate.getName());
			message = "Successfully updated price " + choco;
		} else {
			message = "No chocolate found in this id";
		}
		return message;
	}

}