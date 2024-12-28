package com.backend_ecommerce.backend_ecommerce.models.request;

import lombok.Data;

@Data
public class CartRequest {
    private IdRequest user;
    private IdRequest product;
    private Integer quantity;
}
