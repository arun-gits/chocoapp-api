package com.chocoapp.chocoappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "candy_users")
//@NamedQueries({  
//
//	@NamedQuery(name = "viewAllUsers", query = "FROM User u"), 
//	@NamedQuery(name = "findUserByID", query = "FROM User u WHERE u.id = :id"),
//	@NamedQuery(name = "findUserByName", query = "FROM User s WHERE u.name = :name"),
//	@NamedQuery(name = "updateUserStatus",query = "update User u set u.status='inactive' where id= :id ")
//})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	@Column(name = "user_name", nullable = false)
	private String name;
	@Column(name = "user_mobile", nullable = false, unique = true)
	private String mobile;
	@Column(name = "user_mail", nullable = false, unique = true)
	private String mail;
	@Column(name = "user_address", nullable = false)
	private String address;
	@Column(name = "user_password", nullable = false)
	private String password;
	@Column(name="user_role", nullable=false, insertable=false)
	private String role;
	@Column(name="user_status", nullable=false, insertable=false)
	private String status;
	@Transient
	private String data;
}
