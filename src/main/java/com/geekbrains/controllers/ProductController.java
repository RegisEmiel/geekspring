package com.geekbrains.controllers;

import com.geekbrains.entities.Product;
import com.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //http://localhost:8189/products/list_all
    @RequestMapping("/list_all")
    public String showProductsList(Model model) {
        List<Product> allProducts = productService.getProductsList();
        model.addAttribute("productsList", allProducts);
        return "products";
    }

    @RequestMapping(path="/remove/{id}", method = RequestMethod.GET)
    public String removeById(@PathVariable(value = "id") Long productId) {
        productService.removeById(productId);
        return "redirect:/products/list";
    }

    //http://localhost:8189/products/list
    //http://localhost:8189/products/list?page=1
    @RequestMapping("/list")
    public String indexPage(Model model,
                            @RequestParam(name = "page", required = false) Optional<Integer> page,
                            @RequestParam(name = "size", required = false) Optional<Integer> size) {
        model.addAttribute("productsList", productService.getProducts(page, size));
        return "products-list";
    }
}
