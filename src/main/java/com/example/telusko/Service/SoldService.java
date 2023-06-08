package com.example.telusko.Service;

import com.example.telusko.Model.Sold;

public interface SoldService {

	public Sold saveProduct(Sold sold);

	public Object findProductByName(String getpName);
}
