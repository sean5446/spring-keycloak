package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Faker faker;

    public ProductService(ProductRepository productRepository, Faker faker) {
        this.productRepository = productRepository;
        this.faker = faker;
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public void addProducts() {
        List<Product> productList = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> Product.builder()
                        .name(faker.book().title())
                        .sku(faker.code().ean13())
                        .description(faker.lorem().sentence(6))
                        .price(new BigDecimal(faker.commerce().price()))
                        .category(faker.lorem().word())
                        .build()
                ).toList();

        productRepository.saveAll(productList);
    }
}
