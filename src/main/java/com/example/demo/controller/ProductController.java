package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String products(Principal principal, Model model) {
        // add customers for demonstration
        productService.addProducts();
        Iterable<Product> products = productService.findAll();
        model.addAttribute("customers", products);
        model.addAttribute("username", principal.getName());
        return "product/products";
    }

}
