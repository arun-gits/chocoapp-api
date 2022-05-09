package com.chocoapp.dto;

import lombok.Data;
@Data
public class ChocolateDTO implements Comparable<ChocolateDTO>{
	
	private Integer id;
	
	private String name;
	
	private Integer price;
	
	private Integer quantity;
	
	@Override
	public int compareTo(ChocolateDTO o) {
		if(this.price==o.getPrice()) {
		return 0;
		}
		else if(this.price<o.getPrice()) {
			return -1;
		}
		return 1;
	}

	
}
