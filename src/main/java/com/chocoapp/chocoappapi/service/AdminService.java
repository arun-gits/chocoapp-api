package com.chocoapp.chocoappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chocoapp.chocoappapi.converter.ChocoConverter;
import com.chocoapp.chocoappapi.converter.UserConverter;
import com.chocoapp.chocoappapi.dto.ChocolateDTO;
import com.chocoapp.chocoappapi.dto.UserDTO;
import com.chocoapp.chocoappapi.exception.ValidationException;
import com.chocoapp.chocoappapi.model.Chocolate;
import com.chocoapp.chocoappapi.model.User;
import com.chocoapp.chocoappapi.repository.ChocoRepository;
import com.chocoapp.chocoappapi.repository.UserRepository;

@Repository
public class AdminService {

	private static final String INVALID_USER_ID = "Invalid user id";

	@Autowired
	UserRepository userRepository;

	@Autowired
	ChocoRepository chocoRepository;

	@Autowired
	ChocoService chocoService;

	// view all users
	public List<UserDTO> listAllUsers() {
		List<User> usersList = userRepository.findAll();
		
		return  UserConverter.toDTO(usersList);
	}

	// delete user from the list
	public String deleteUserById(int id) throws ValidationException {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ValidationException(INVALID_USER_ID);
		}
		userRepository.deleteById(id);
		return "id " + id + " deleted successfully " + user;
	}

	// block user from the list
	public String blockUserById(int id) throws ValidationException {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ValidationException(INVALID_USER_ID);
		}
		userRepository.blockUser(id);
		return "Successfully deactivated " + id;
	}

	// activate user
	public String activateUserById(int id) throws ValidationException {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ValidationException(INVALID_USER_ID);
		}
		userRepository.activateUser(id);
		return "Successfully activated " + id;
	}
	
	public List<ChocolateDTO> findAllChocos() throws ValidationException{
		List<Chocolate> chocolateList = chocoRepository.findAll();
		List<ChocolateDTO> chocolates = ChocoConverter.toDTO(chocolateList);
		if(chocolates.isEmpty()) {
			throw new ValidationException ("No chocolates found");
		}
		return chocolates;
	}
	
	// add chocolate
	public String addChocolate(ChocolateDTO chocolate) throws ValidationException {
		List<ChocolateDTO> exists = chocoService.search(chocolate.getName());
		if (!exists.isEmpty()) {
			throw new ValidationException("Chocolate already exists");
		}
		Chocolate c = ChocoConverter.toModel(chocolate);
		chocoRepository.save(c);
		return "Chocolate added successfully " + chocolate;
	}

	// delete chocolate from the list
	public String deleteChocoById(int id) throws ValidationException {
		Chocolate chocolate = chocoRepository.findById(id);
		if (chocolate == null) {
			throw new ValidationException("Invalid id");
		}
		chocoRepository.deleteById(id);
		return "Chocolate deleted successfully " + chocolate;
	}

	// update chocolate price
	public String updateChocoPrice(int id, int price) throws ValidationException {
		Chocolate chocolate = chocoRepository.findById(id);
		if (chocolate == null) {
			throw new ValidationException("Invalid id");
		}
		chocoRepository.updateChocoPrice(id, price);
		return "Successfully updated price " + chocolate;
	}

}