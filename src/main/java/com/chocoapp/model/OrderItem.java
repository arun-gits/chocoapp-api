package com.chocoapp.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "order_id")
	private Purchase orderId;

	@OneToOne
	@JoinColumn(name = "choco_id")
	private Chocolate chocoId;

	@Column(name = "choco_quantity")
	private Integer quantity;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "order_status")
	private String orderStatus = "pending";

}
