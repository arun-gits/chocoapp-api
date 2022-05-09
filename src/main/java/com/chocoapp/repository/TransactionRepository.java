package com.chocoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocoapp.model.Purchase;

@Repository
public interface TransactionRepository extends JpaRepository<Purchase, Integer>{
	
}
