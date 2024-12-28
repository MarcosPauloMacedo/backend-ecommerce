package com.backend_ecommerce.backend_ecommerce.models.response;

import lombok.Data;

@Data
public class CartResponse {
    private Long id;
    private UserResponse user;
    private ProductResponse product;
    private Integer quantity;
}
