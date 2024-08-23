package com.sabanciuniv.exception;

public class NoProductsInPriceRangeException extends RuntimeException {
    public NoProductsInPriceRangeException(double minPrice, double maxPrice) {
        super("No products found in the price range: " + minPrice + " - " + maxPrice);
    }
}
