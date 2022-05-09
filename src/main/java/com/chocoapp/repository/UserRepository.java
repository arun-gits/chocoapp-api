package com.chocoapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chocoapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByMail(String email); // Login by email

	User findByMobile(String mobile);// Login by number

	Optional<User> findByMailAndPassword(String email, String password);

	@Transactional
	@Modifying
	@Query("update User u set u.password = :password where u.id=:id")
	void changePassword(@Param("id") Integer id, @Param("password") String password);

	@Transactional
	@Modifying
	@Query(value = "update candy_users set user_status='inactive' where user_id =:uid", nativeQuery = true)
	void blockUser(@Param("uid") int id);

	@Transactional
	@Modifying
	@Query(value = "update candy_users set user_status='active' where user_id =?1", nativeQuery = true)
	void activateUser(int id);
	
	@Transactional
	@Query("select u from User u where mail= :input or mobile= :input")
	Optional<User> findByMailOrMobile(String input);
	
}
