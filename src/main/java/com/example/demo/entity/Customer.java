package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "Please provide a name")
    private String name;

    @Column
    @NotBlank(message = "Please provide a serviceRendered")
    private String serviceRendered;

    @Column
    @NotBlank(message = "Please provide an address")
    private String address;

}
