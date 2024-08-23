package com.sabanciuniv.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sabanciuniv.model.Product;
import com.sabanciuniv.repo.ProductRepo;
import com.sabanciuniv.service.ProductService;

import jakarta.annotation.PostConstruct;


@RestController
@RequestMapping("/products")
public class ProductController {

	
    @Autowired
    private ProductRepo productRepository;
    
    @Autowired
    private ProductService productservice;
    
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	private static final Product p = null;
	
	
	@PostConstruct
	public void init() {
	Product first = new Product(32, "clock", "decoration",  300);
	productservice.saveProduct(first);
	Product second = new Product(23, "glass", "decoration",  100);
	productservice.saveProduct(second);
	Product third = new Product(51, "door", "decoration",  2500);
	productservice.saveProduct(third);
	Product fourth = new Product(48, "tshirt", "clothes",  430);
	productservice.saveProduct(fourth);
	Product fifth = new Product(12, "panth", "clothes",  74);
	productservice.saveProduct(fifth);
    
	
	}
    // GET all products
    @GetMapping
    public List<Product> getAllProducts() {
    	logger.info("\"All Products are visible");
    	
        return productservice.getProduct();
    }

    // GET product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
    	
    	return productservice.getProduct(id);    
    }
    
    // GET product by Type
    @GetMapping("/type/{type}")
    public List<Product> getProductByType(@PathVariable String type) {
    	
    	return productservice.getProductByType(type);	
    }
    
    @GetMapping("/price-range")
    public List<Product> getProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        
        return productservice.getProductsByPriceRange(minPrice, maxPrice);
    }
    

    // POST create new product
    @PostMapping("/save")
    public Product createProduct(@RequestBody Product product) {

        return productservice.saveProduct(product);
    }

    // PUT update product
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        
    	return productservice.updateProduct(id, productDetails);
    }

    
  // DELETE product by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return productservice.deleteProduct(id);
    }

}
