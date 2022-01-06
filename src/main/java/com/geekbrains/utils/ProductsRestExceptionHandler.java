package com.geekbrains.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductsRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleAllException(Exception exc) {
        ProductsErrorResponse productsErrorResponse = new ProductsErrorResponse();
        productsErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        productsErrorResponse.setMessage(exc.getMessage());
        productsErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(productsErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
