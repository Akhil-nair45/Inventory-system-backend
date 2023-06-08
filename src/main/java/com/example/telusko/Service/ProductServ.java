package com.example.telusko.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.telusko.Model.Product;

public interface ProductServ {

	
	public ResponseEntity<String>  saveproduct(Product product);
	
	public List<Product> getAllProduct();
	
	public Product getproductById(Integer id);
	
	public String deleteproduct(Integer id);
	
	public List<Product> findproductByName(String productName);
	
	
}
