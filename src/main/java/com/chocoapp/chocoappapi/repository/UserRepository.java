package com.chocoapp.chocoappapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chocoapp.chocoappapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//	@SuppressWarnings("unchecked")
//	User save(User user); //Registration
	
	User findByMail(String email); //Login by email
	
	User findByMobile(String mobile);//Login by number

	Optional<User> findByMailAndPassword(String email, String password);
	
	@Transactional
	@Modifying
	@Query("update User u set u.password = :password where u.id=:id")
	void changePassword(@Param("id") Integer id, @Param("password") String password);
}