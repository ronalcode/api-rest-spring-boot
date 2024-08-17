package com.localhost.main.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.localhost.main.Entities.Product;

public interface IProductService {
	List<Product> findAll();
	
	Optional<Product> findById(Long id);
	
	List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
	
	void save(Product product);
	
	void deleteById(Long id);
}
