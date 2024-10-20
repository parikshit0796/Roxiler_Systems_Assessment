package com.datamanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamanage.entities.Product;
import com.datamanage.services.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/getallproducts")
	public ResponseEntity<List<Product>> getAllProduct(){
		 List<Product> products = productService.getAllProducts();
		 
		 if(products.isEmpty()) {
			 productService.fetchAndSaveProductData();
			 products = productService.getAllProducts();
		 }
		return new ResponseEntity<>(products, HttpStatus.OK);
		
	}
	
	@GetMapping("/statistics/{month}")
    public ResponseEntity<Map<String, Object>> getStatistics(@PathVariable int month) {
        Map<String, Object> statistics = productService.getStatistics(month);
        return ResponseEntity.ok(statistics);
    }
	
	@GetMapping("/categorystat/{month}")
    public ResponseEntity<Map<String, Object>> getCatagoryStatistics(@PathVariable int month) {
        Map<String, Object> catagoryStatistics = productService.getCatagoryStatistics(month);
        return ResponseEntity.ok(catagoryStatistics);
    }
	
	@GetMapping("/pricestat/{month}")
	public ResponseEntity<Map<String, Integer>>getPriceStat(@PathVariable int month){
		Map<String, Integer>priceStatistics = productService.getPriceStatistics(month);
		return ResponseEntity.ok(priceStatistics);
	}
	
}
