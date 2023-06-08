package com.example.telusko.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.telusko.Model.Sold;
import com.example.telusko.Service.SoldServiceImpl;

@RestController
public class SoldController {
	
	@Autowired
	SoldServiceImpl ors;
	
	@PostMapping("/post")
	public ResponseEntity<?> saveorder(@RequestBody Sold order) {
		return new ResponseEntity<>(ors.saveProduct(order), HttpStatus.CREATED);
	}
	

	
	
}
