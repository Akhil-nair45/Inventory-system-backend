package com.example.telusko.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.telusko.Model.Product;
import com.example.telusko.Model.Sold;
import com.example.telusko.Service.ProductServ;
import com.example.telusko.Service.SoldService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class ProductController {

	@Autowired
	private ProductServ serv;
	
	@Autowired
	private SoldService serv1;
	
	


	

	    // Mapping to handle the update operation
	    @PostMapping("/updateProductQuantity")
	    public String updateProductQuantity(@RequestBody Sold sold) {
	        List<Product> productList = serv.findproductByName(sold.getpName());
	        Product product=null;
	        if(productList.size() >0)
	        {
	        	System.out.println("product found ");
	        	product=productList.get(0);
	        }
	        
	        else
	        {
	        	return "no product available";
	        }
	        
	        if(product.getQuantity() <sold.getQty() ) {
	        	return " Cannot place order as available quantity is : " + product.getQuantity();
	        }
	        
	        if (product != null && product.getProductName().equals(sold.getpName())) {
	            Integer newQuantity = product.getQuantity() - (sold.getQty());
	            product.setQuantity(newQuantity);
	           serv.saveproduct(product);
	           System.out.println("inserting into sold ");
	           serv1.saveProduct(sold);
	           
	        }

	        return "Quantity Updated"; // Redirect to the product listing page
	    }
	
	
	
	
	
	
	

	@PostMapping("/saveproduct")
	public ResponseEntity<?> saveproduct(@RequestBody Product product) {
		return new ResponseEntity<>(serv.saveproduct(product), HttpStatus.CREATED);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> getAllProducts() {
		return new ResponseEntity<>(serv.getAllProduct(),HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getproductById(@PathVariable Integer id){
		return new ResponseEntity<>(serv.getproductById(id),HttpStatus.OK);
	}
	
	
	@GetMapping("/deleteproduct/{id}")
	public ResponseEntity<?> deleteproduct(@PathVariable Integer id) {
		return new ResponseEntity<>(serv.deleteproduct(id), HttpStatus.OK);
	}
	
	
	@PutMapping("/editproduct")
	public ResponseEntity<?> editproduct(@RequestBody Product product) {
		System.out.println("edit");
		return new ResponseEntity<>(serv.saveproduct(product), HttpStatus.CREATED);
	}
	
	@GetMapping("/findproductByName/{productName}")
	public ResponseEntity<?> findproductByname(@PathVariable String productName) {
		return new ResponseEntity<>(serv.findproductByName(productName), HttpStatus.OK);
	}
	
}

