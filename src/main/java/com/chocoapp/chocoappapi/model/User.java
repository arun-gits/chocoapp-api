package com.chocoapp.chocoappapi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "candy_users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	@Column(name = "user_name", nullable = false)
	private String name;
	@Column(name = "user_mobile", nullable = false, unique = true)
	private String mobile;
	@Column(name = "user_email", nullable = false, unique = true)
	private String email;
	@Column(name = "user_address", nullable = false)
	private String address;
	@Column(name = "user_password", nullable = false)
	private String password;

}
