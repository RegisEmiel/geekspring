package com.geekbrains.services;


import com.geekbrains.entities.Product;
import com.geekbrains.repositories.IProductRepository;
import com.geekbrains.utils.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsRestService {
    private IProductRepository productRepository;

    @Autowired
    public void setProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductsRestService() {
    }

    // get
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id = " + id + " not found");
        }
        return product.get();
    }

    // post put
    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    // delete
    public void delete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id = " + id + " not found");
        }
        productRepository.delete(product.get());
    }

    // get
    public List<Product> getAllProductsList() {
        return (List<Product>) productRepository.findAll();
    }
}
