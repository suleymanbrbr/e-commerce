package com.sabanciuniv.exception;

// Public class (only one can be public)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Product not found with ID: " + id);
    }
}






