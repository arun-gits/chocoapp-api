package com.chocoapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="order_transactions")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name="user_id")
	private Integer userId;

	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "purchased_on")
	private String purchasedOn;
	
	@Column(name = "user_address")
	private String userAddress;

}
