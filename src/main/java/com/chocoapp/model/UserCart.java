package com.chocoapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
 @Entity
 @Table(name = "user_cart")
public class UserCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
		
	
	@OneToOne
	@JoinColumn(name = "choco_id")
	private Chocolate choco;
	
	@Column(name = "choco_quantity")
	private Integer quantity;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="product_status")
	private String productStatus = "added to cart";
	
}
