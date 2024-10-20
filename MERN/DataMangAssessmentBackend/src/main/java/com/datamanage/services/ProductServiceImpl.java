package com.datamanage.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.datamanage.entities.Product;
import com.datamanage.repositories.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String fetchAndSaveProductData() {
		String url = "https://s3.amazonaws.com/roxiler.com/product_transaction.json";
		Product[] productArray = restTemplate.getForObject(url, Product[].class);

		if (productArray != null && productArray.length > 0) {
			List<Product> products = Arrays.asList(productArray);
			productRepository.saveAll(products);
			return ("Products saved: " + products.size() + " products");

		} else {
			return ("Invalid Product");
		}
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		return productList;
	}

	@Override
	public Map<String, Object> getStatistics(int month) {
		List<Product> productList = productRepository.findAll();

		double totalSalesAmount = 0;
		int soldItemsCount = 0;
		int unsoldItemsCount = 0;

		for (Product product : productList) {
			int productMonth = product.getDateOfSale().getMonthValue();
			if (productMonth == month) {
				if (product.isSold()) {
					totalSalesAmount += product.getPrice();
					soldItemsCount++;
				} else {
					unsoldItemsCount++;
				}
			}
		}

		Map<String, Object> statistics = new HashMap<>();
		statistics.put("totalSalesAmount", totalSalesAmount);
		statistics.put("soldItemsCount", soldItemsCount);
		statistics.put("unsoldItemsCount", unsoldItemsCount);

		return statistics;
	}

	@Override
	public Map<String, Object> getCatagoryStatistics(int month) {

		List<Product> productList = productRepository.findAll();
		Map<String, Object> categoryCounts = new HashMap<>();

		for (Product product : productList) {
			int productMonth = product.getDateOfSale().getMonthValue();
			if (productMonth == month) {
				String category = product.getCategory();

				int currentCount;
				if (categoryCounts.containsKey(category)) {
					currentCount = (int) categoryCounts.get(category);
				} else {
					currentCount = 0;
				}

				currentCount++;

				categoryCounts.put(category, currentCount);
			}
		}
		return categoryCounts;
	}

	@Override
	public Map<String, Integer> getPriceStatistics(int month) {

		List<Product> productList = productRepository.findAll();
		Map<String, Integer> priceRangeCount = new LinkedHashMap<>();
		priceRangeCount.put("0-100", 0);
		priceRangeCount.put("101-200", 0);
		priceRangeCount.put("201-300", 0);
		priceRangeCount.put("301-400", 0);
		priceRangeCount.put("401-500", 0);
		priceRangeCount.put("501-600", 0);
		priceRangeCount.put("601-700", 0);
		priceRangeCount.put("701-800", 0);
		priceRangeCount.put("801-900", 0);
		priceRangeCount.put("901-above", 0);

		for (Product product : productList) {
			int productMonth = product.getDateOfSale().getMonthValue();
			if (productMonth == month) { 
				double price = product.getPrice();

				if (price <= 100) {
					priceRangeCount.put("0-100", priceRangeCount.get("0-100") + 1);
				} else if (price <= 200) {
					priceRangeCount.put("101-200", priceRangeCount.get("101-200") + 1);
				} else if (price <= 300) {
					priceRangeCount.put("201-300", priceRangeCount.get("201-300") + 1);
				} else if (price <= 400) {
					priceRangeCount.put("301-400", priceRangeCount.get("301-400") + 1);
				} else if (price <= 500) {
					priceRangeCount.put("401-500", priceRangeCount.get("401-500") + 1);
				} else if (price <= 600) {
					priceRangeCount.put("501-600", priceRangeCount.get("501-600") + 1);
				} else if (price <= 700) {
					priceRangeCount.put("601-700", priceRangeCount.get("601-700") + 1);
				} else if (price <= 800) {
					priceRangeCount.put("701-800", priceRangeCount.get("701-800") + 1);
				} else if (price <= 900) {
					priceRangeCount.put("801-900", priceRangeCount.get("801-900") + 1);
				} else {
					priceRangeCount.put("901-above", priceRangeCount.get("901-above") + 1);
				}
			}
		}
		return priceRangeCount;
	}

}
