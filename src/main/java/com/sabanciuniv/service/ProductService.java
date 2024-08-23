package com.sabanciuniv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.sabanciuniv.exception.*;
import com.sabanciuniv.model.Product;
import com.sabanciuniv.repo.ProductRepo;

@Service
public class ProductService {

    private final ProductRepo productRepository;

    public ProductService(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public Product getProduct(Long product_id) {
        return productRepository.findById(product_id)
                .orElseThrow(() -> new ProductNotFoundException(product_id));
    }

    public List<Product> getProductByType(String type) {
        List<Product> products = productRepository.findByType(type);
        if (products.isEmpty()) {
            throw new NoProductsInTypeException(type);
        }
        return products;
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        if (products.isEmpty()) {
            throw new NoProductsInPriceRangeException(minPrice, maxPrice);
        }
        return products;
    }

    public Product updateProduct(@PathVariable Long product_id, @RequestBody Product newProduct) {
        Product existingProduct = productRepository.findById(product_id)
                .orElseThrow(() -> new ProductNotFoundException(product_id));
        
        existingProduct.setName(newProduct.getName());
        existingProduct.setPrice(newProduct.getPrice());
        
        return productRepository.save(existingProduct);
    }

    
    public ResponseEntity<String> deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(productId);
            return ResponseEntity.ok("Product deleted successfully.");
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

}
