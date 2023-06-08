package com.example.telusko.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.telusko.Model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	List<Product> findByProductName(String pname);



}
