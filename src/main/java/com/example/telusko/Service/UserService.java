package com.example.telusko.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.telusko.Model.User;

public interface UserService {

	
	public User saveuser(User user);
	public List<User> findAllusers();
	public User getByid(int id);
	public ResponseEntity<String> deleteuser(Integer id);
	public boolean validate(User user);
	List<User> findByNotMarkedAsDeleted();
}
