package com.geekbrains.controllers;

import com.geekbrains.entities.Product;
import com.geekbrains.services.ProductsRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/**")
@RestController
public class ProductsRestController {
    private ProductsRestService productsRestService;

    @Autowired
    public void setProductsRestService(ProductsRestService productsRestService) {
        this.productsRestService = productsRestService;
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productsRestService.getProductById(productId);
    }

    @GetMapping("/products")
    public List<Product> getProductById() {
        return productsRestService.getAllProductsList();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product theProduct) {
        theProduct.setId(0L);
        theProduct = productsRestService.saveOrUpdate(theProduct);
        return theProduct;
    }

    @PutMapping(path = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@RequestBody Product theProduct) {
        theProduct = productsRestService.saveOrUpdate(theProduct);
        return theProduct;
    }

    @DeleteMapping("/products/{productId}")
    public int deleteProduct(@PathVariable Long productId) {
        productsRestService.delete(productId);
        return HttpStatus.OK.value();
    }
}
