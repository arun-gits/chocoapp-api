package com.chocoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chocoapp.model.Chocolate;

@Repository
public interface ChocoRepository extends JpaRepository<Chocolate,Integer>{
	
	@Transactional(propagation=Propagation.NEVER)
	Chocolate findById(int id);
		
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Modifying
	@Query(value="SELECT * FROM candies_list WHERE choco_name LIKE '%name%' ",nativeQuery=true)
	List<Chocolate> search(@Param("name") String name);
		
	@Transactional
	@Modifying
	@Query("update Chocolate c set c.price = :price where c.id=:id")
	void updateChocoPrice(@Param("id") int id, @Param("price") int price);
	
}
