package com.example.telusko.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.telusko.Model.Sold;

public interface SoldRepo extends JpaRepository<Sold, String> {

	@Query(value ="select * from sold where p_name = ?1", nativeQuery=true)
	Sold findBypName(String pName);
}
