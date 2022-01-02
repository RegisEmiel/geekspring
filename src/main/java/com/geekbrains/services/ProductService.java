package com.geekbrains.services;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private IProductRepository productRepository;

    @Autowired
    public void setProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductService() {
    }

    public List<Product> getProductsList() {
        return (List<Product>) productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void removeById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getProducts(Optional<Integer> page, Optional<Integer> size) {
        return productRepository.findAll(PageRequest.of(page.orElse(1) - 1, size.orElse(3)));
    }
}
