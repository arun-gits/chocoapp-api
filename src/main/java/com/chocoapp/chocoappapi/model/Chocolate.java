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
@Table(name = "candies_list")
public class Chocolate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "choco_id")
	private Integer id;
	@Column(name = "choco_name")
	private String name;
	@Column(name = "price")
	private Integer price;
	@Column(name = "quantity")
	private Integer quantity;

	public Chocolate(Integer id, String name, Integer price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Chocolate() {
		super();
		// TODO Auto-generated constructor stub
	}

}