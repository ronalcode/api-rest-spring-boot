package com.localhost.main.persintence.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localhost.main.Entities.Product;
import com.localhost.main.persintence.IProductDAO;
import com.localhost.main.repositories.ProductRepository;

@Component
public class ProductDAOImplementation implements IProductDAO{
	@Autowired
	private ProductRepository productRepository;
	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
		return productRepository.findProductByPriceInRange(minPrice, maxPrice);
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

}
