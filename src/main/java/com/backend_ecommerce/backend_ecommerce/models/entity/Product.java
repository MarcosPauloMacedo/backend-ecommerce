package com.backend_ecommerce.backend_ecommerce.models.entity;

import com.backend_ecommerce.backend_ecommerce.models.utils.Categories;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int stock;

    @Enumerated(EnumType.STRING)
    private Categories category;
    private String image;
}
