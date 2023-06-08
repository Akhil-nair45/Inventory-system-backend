package com.example.telusko.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.telusko.Model.Sold;
import com.example.telusko.Repository.SoldRepo;

@Service
public class SoldServiceImpl implements SoldService {

	@Autowired
	SoldRepo or;
	

	@Override
	public Sold saveProduct(Sold sold) {
		return or.save(sold);
	}


	@Override
	public Object findProductByName(String getpName) {
		return or.findBypName(getpName);
	}

}
