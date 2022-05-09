package com.chocoapp.converter;

import java.util.ArrayList;
import java.util.List;

import com.chocoapp.dto.UserDTO;
import com.chocoapp.model.User;

public class UserConverter {

	private UserConverter() {
		super();
	}

	public static UserDTO toDTO(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setMail(user.getMail());
		userDto.setMobile(user.getMobile());
		userDto.setAddress(user.getAddress());
		userDto.setPassword(user.getPassword());
		userDto.setRole(user.getRole());
		userDto.setStatus(user.getStatus());
		userDto.setData(user.getData());
		return userDto;
	}

	public static List<UserDTO> toDTO(List<User> users) {
		List<UserDTO> usersDTO = new ArrayList<>();
		for (User u : users) {
			UserDTO userDTO = UserConverter.toDTO(u);
			usersDTO.add(userDTO);
		}
		return usersDTO;
	}

	public static User toModel(UserDTO userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setMail(userDto.getMail());
		user.setMobile(userDto.getMobile());
		user.setAddress(userDto.getAddress());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		user.setStatus(userDto.getStatus());
		user.setData(userDto.getData());
		return user;
	}

	public static List<User> toModel(List<UserDTO> usersDto) {
		List<User> users = new ArrayList<>();
		for (UserDTO u : usersDto) {
			User user = UserConverter.toModel(u);
			users.add(user);
		}
		return users;
	}
}
