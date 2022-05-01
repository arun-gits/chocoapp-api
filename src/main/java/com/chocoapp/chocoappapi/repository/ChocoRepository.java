package com.chocoapp.chocoappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocoapp.chocoappapi.model.Chocolates;

@Repository
public interface ChocoRepository extends JpaRepository<Chocolates,Integer>{
	
	Chocolates findByName(String name);
}
