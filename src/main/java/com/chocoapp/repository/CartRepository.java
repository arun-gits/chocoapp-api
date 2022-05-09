package com.chocoapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chocoapp.model.UserCart;

@Repository
public interface CartRepository extends JpaRepository<UserCart, Integer>{
	
	@Transactional
	@Query(value="SELECT order_id FROM order_items ORDER BY order_id DESC LIMIT 1",nativeQuery=true)
	int orderId();
	
}
