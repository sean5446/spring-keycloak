package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "Please provide a name")
    private String name;

    @Column
    @NotBlank(message = "Please provide a SKU")
    private String sku;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private String category;

}
