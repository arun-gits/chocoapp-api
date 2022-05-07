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
@Table(name="candies_trans")
public class Shopping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private Integer orderId;
		
	private Integer chocoId;
	
	private String chocoName;
	
	private Integer chocoPrice;
		
	private Integer chocoQuantity;
	
	private String paymentMode;
	
	private String purchasedOn;
	
	private String user_address;
		
}
