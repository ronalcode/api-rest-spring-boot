package com.localhost.main.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.localhost.main.Entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	// Notación query jpQL
	@Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
	List<Product> findProductByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);
	//Query method
	List<Product> findProductByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
