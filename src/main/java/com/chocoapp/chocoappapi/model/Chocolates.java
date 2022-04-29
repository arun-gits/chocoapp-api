package com.chocoapp.chocoappapi.model;

public class Chocolates {

	private Integer id;

	private String name;

	private Integer price;

	public Chocolates(Integer id, String name, Integer price) {
		super();
		this.name = name;
		this.price = price;
		this.id = id;
	}

	public Chocolates() {
		super();
	
	}



	@Override
	public String toString() {
		return "Chocolates [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
