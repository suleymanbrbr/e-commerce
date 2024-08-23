package com.sabanciuniv.exception;

public class ProductTypeNotFoundException extends RuntimeException {
    public ProductTypeNotFoundException(String type) {
        super("Product type not found: " + type);
    }
}
