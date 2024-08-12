package com.example.demo.controller;


import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
@RestController
@RequestMapping("/")
public class JsonContoller {

    // marking the material and product controllers with @RestController causes them to stop working as hypermedia

    private final ProductService productService;

    public JsonContoller(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/json")
    public ResponseEntity<ArrayList<Product>> getJson(@RequestParam(value = "limit", required = false)
                                                      @Min(value = 1, message = "limit must be greater than 0")
                                                      @Max(value = 100, message = "limit must not exceed 100") Integer limit,
                                                      @RequestParam(value = "page", required = false)
                                                      @Min(value = 1, message = "page must be greater than 0") Integer page) {
        var products = productService.getProducts(limit, page);
        var response = new ArrayList<Product>();
        products.forEach(response::add);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
