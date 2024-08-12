package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

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
        Iterable<Product> products = productService.getProducts(10, 1);
        model.addAttribute("products", products);
        model.addAttribute("username", principal.getName());
        model.addAttribute("count", productService.getCount());
        return "product/products";
    }

    @GetMapping("/count")
    public Long getProductCount() {
        return productService.getCount();
    }

    @GetMapping("/json")
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

    @GetMapping("/html")
    public String getHtml(Model model,
                          @RequestParam(value = "limit", required = false)
                          @Min(value = 1, message = "limit must be greater than 0")
                          @Max(value = 100, message = "limit must not exceed 100") Integer limit,
                          @RequestParam(value = "page", required = false)
                          @Min(value = 1, message = "page must be greater than 0") Integer page) {

        var products = productService.getProducts(limit, page);
        model.addAttribute("products", products);
        model.addAttribute("limit", limit);
        model.addAttribute("page", page + 1);

        return "product/rows";
    }

    @Data
    public static class Search {
        private String term;
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam(name = "search", defaultValue = "") String searchTerm) {
        var products = productService.search(searchTerm);
        model.addAttribute("products", products);
        return "product/search";
    }

}
