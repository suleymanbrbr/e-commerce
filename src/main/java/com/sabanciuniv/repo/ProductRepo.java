package com.sabanciuniv.repo;


import com.sabanciuniv.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	
	List<Product> findAll();
	List<Product> findByType(String type);
	List<Product> findByPriceBetween(double minPrice, double maxPrice);
	
	
}



