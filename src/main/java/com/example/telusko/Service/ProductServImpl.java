package com.example.telusko.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.telusko.Model.Product;
import com.example.telusko.Repository.ProductRepo;

@Service
public class ProductServImpl implements ProductServ{

	
	@Autowired
	private ProductRepo repo;
	
//	@Override
//	public Product saveproduct(Product product) {
//		return repo.save(product) ;
//	}
	
	
	@Override
	public ResponseEntity<String> saveproduct(Product product) {
	    String trimmedProductName = product.getProductName().trim(); // Remove leading and trailing whitespaces
	    String[] productWords=trimmedProductName.split(" ");
	    String productNameWithoutSpace=String.join("", productWords);
	    if (trimmedProductName.isEmpty()) {
	        // Handle empty product name (throw exception, return null, etc.)
	        // For example, throwing an exception:
	        throw new IllegalArgumentException("Product name cannot be empty.");
	    }

	    List<Product> existingProduct = repo.findByProductName(productNameWithoutSpace);

	    if (!existingProduct.isEmpty()) {
	        // Duplicate entry found, handle accordingly (throw exception, return null, etc.)
	        // For example, throwing an exception:
	    	
	        return new ResponseEntity<String>("Product already exists",HttpStatus.CONFLICT);
	    }

	    product.setProductName(productNameWithoutSpace); // Update the product name without whitespace
	    repo.save(product);     
	    return new ResponseEntity<String>("Product saved successfully",HttpStatus.OK);
	}


	
	
	

	@Override
	public List<Product> getAllProduct() {
		return repo.findAll();
	}

	@Override
	public Product getproductById(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public String deleteproduct(Integer id) {
		Product product= repo.findById(id).get();
		if( product !=null)
		{
			repo.delete(product);
			return("Deleted product successfully!!");
		}
		return "Some Error Occured!";
	}

	@Override
	public List<Product> findproductByName(String productName) {
		return repo.findByProductName(productName);
	}

	
	
	
}
