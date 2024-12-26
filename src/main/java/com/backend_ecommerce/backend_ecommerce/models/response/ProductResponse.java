package com.backend_ecommerce.backend_ecommerce.models.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private String image;
}
