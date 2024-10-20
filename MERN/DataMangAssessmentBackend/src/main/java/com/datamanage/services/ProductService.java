package com.datamanage.services;

import java.util.List;
import java.util.Map;

import com.datamanage.entities.Product;

public interface ProductService {
	
	String fetchAndSaveProductData();
	
	List<Product> getAllProducts();
	
	Map<String, Object>getStatistics(int month);

	Map<String, Object>getCatagoryStatistics(int month);
	
	Map<String, Integer>getPriceStatistics(int month);
}
