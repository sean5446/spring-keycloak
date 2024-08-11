package com.example.demo.service;

import com.example.demo.entity.Material;
import com.example.demo.repository.MaterialRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final Faker faker;

    MaterialService(MaterialRepository materialRepository, Faker faker) {
        this.materialRepository = materialRepository;
        this.faker = faker;
    }

    public Iterable<Material> findAll() {
        return materialRepository.findAll();
    }

    public void save(Material material) {
        materialRepository.save(material);
    }

    public Material createFakeMaterial() {
        return Material.builder()
                .name(faker.lorem().word())
                .type(faker.lorem().word())
                .price(new BigDecimal(faker.commerce().price()))
                .category(faker.lorem().word())
                .build();
    }

}
