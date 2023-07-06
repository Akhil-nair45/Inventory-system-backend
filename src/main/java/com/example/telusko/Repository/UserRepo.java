package com.example.telusko.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.telusko.Model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	@Query(value = "select * from user where email = ?1", nativeQuery = true)
	User findByEmail(String email);

	@Query(value="select * from user where is_deleted='0'", nativeQuery = true)
	List<User> findByNotMarkedAsDeleted();

	Optional<User> findByName(String username);
	
	
	@Query(value="select* from user where id=?1", nativeQuery= true)
	User GetById(int id);
}
