package com.example.telusko.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.telusko.Dto.AuthRequest;
import com.example.telusko.Model.User;
import com.example.telusko.Service.JwtService;
import com.example.telusko.Service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/User")
public class UserController {
	
	@Autowired
	private UserServiceImpl serv1;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@PostMapping("/addUser")
	public ResponseEntity<?> saveuser( @RequestBody @Valid User user){
		return new ResponseEntity<>(serv1.saveuser(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/get/All")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> findAllusers(){
		return new ResponseEntity<>(serv1.findByNotMarkedAsDeleted(),HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<User> getByid(@PathVariable int id){
		return new ResponseEntity<User>(serv1.getByid(id),HttpStatus.FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> deleteuser(@PathVariable Integer id){
		return new ResponseEntity<>(serv1.deleteuser(id),HttpStatus.OK);
	}
	
	@PutMapping("/put/edit")
	public ResponseEntity<?> edituser(@RequestBody User user){
		return new ResponseEntity<>(serv1.saveuser(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/validateUser")
	public ResponseEntity<?> validate(@RequestBody User user){
		boolean isValid=serv1.validate(user);
		if(isValid) return new ResponseEntity<>("Successfull", HttpStatus.OK);
		return new ResponseEntity<>("Authentication Failed", HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/authentication")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			if(authentication.isAuthenticated())
			{
				return jwtService.generateToken(authRequest.getUsername());
			}else {
				throw new UsernameNotFoundException("Invalid User Request!");
			}
		
	}
}
