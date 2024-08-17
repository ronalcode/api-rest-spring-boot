package com.localhost.main.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localhost.main.Entities.Product;
import com.localhost.main.persintence.IProductDAO;
import com.localhost.main.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	@Autowired
	private IProductDAO productDAO;
	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productDAO.findById(id);
	}

	@Override
	public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
		return productDAO.findByPriceRange(minPrice, maxPrice);
	}

	@Override
	public void save(Product product) {
		productDAO.save(product);
		
	}

	@Override
	public void deleteById(Long id) {
		productDAO.deleteById(id);
	}

}
