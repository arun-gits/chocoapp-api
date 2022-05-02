package com.chocoapp.chocoappapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chocoapp.chocoappapi.model.Chocolates;

@Repository
public interface ChocoRepository extends JpaRepository<Chocolates,Integer>{
	
	@Transactional(propagation=Propagation.NEVER)
	Chocolates findById(int id);
	
	Chocolates findByName(String name);
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Modifying
	@Query(value="SELECT * FROM candies_list WHERE choco_name LIKE '%name%' ",nativeQuery=true)
	List<Chocolates> search(@Param("name") String name);
	
	//List<Chocolates> findByNameLike(String name);
	
	@Transactional
	@Modifying
	@Query("update Chocolates c set c.price = :price where c.id=:id")
	void updateChocoPrice(@Param("id") int id, @Param("price") int price);

}
