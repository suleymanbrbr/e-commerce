package com.sabanciuniv.exception;

public class NoProductsInTypeException extends RuntimeException {
    public NoProductsInTypeException(String type) {
        super("No products found in the type: " + type);
    }
}
