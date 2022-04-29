package com.chocoapp.chocoappapi.model;

public class Shopping {
	
	public Shopping() {

	}
	
	private Integer choco_id;
	private String choco_name;
	private String choco_price;
	
	
	public Integer getChoco_id() {
		return choco_id;
	}
	public void setChoco_id(Integer choco_id) {
		this.choco_id = choco_id;
	}
	public String getChoco_name() {
		return choco_name;
	}
	public void setChoco_name(String choco_name) {
		this.choco_name = choco_name;
	}
	public String getChoco_price() {
		return choco_price;
	}
	public void setChoco_price(String choco_price) {
		this.choco_price = choco_price;
	}
	@Override
	public String toString() {
		return "Shopping [choco_id=" + choco_id + ", choco_name=" + choco_name + ", choco_price=" + choco_price + "]";
	}
	
	
}
