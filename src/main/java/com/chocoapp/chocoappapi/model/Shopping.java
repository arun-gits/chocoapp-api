package com.chocoapp.chocoappapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name="candies_trans")
public class Shopping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	//@JoinColumn(name="order_id")
	private Integer order_id;
	
//	@ManyToMany(cascade = CascadeType.PERSIST)
//	@JoinColumn(name="user_id")
//	private User user;
	
	private Integer choco_id;
	
	private String choco_name;
	
	private Integer choco_price;
		
	private Integer choco_quantity;
	
	private String payment_mode;
	
	private String purchased_on;
	
	private String user_address;
		
}
