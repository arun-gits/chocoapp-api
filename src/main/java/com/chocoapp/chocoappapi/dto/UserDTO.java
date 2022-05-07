package com.chocoapp.chocoappapi.dto;

import lombok.Data;

@Data
public class UserDTO {

	private int id;
	private String name;
	private String mobile;
	private String mail;
	private String address;
	private String password;
	private String role;
	private String status;
	private String data;
}
