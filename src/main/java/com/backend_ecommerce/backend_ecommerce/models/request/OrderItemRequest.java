package com.backend_ecommerce.backend_ecommerce.models.request;

import lombok.Data;

@Data
public class OrderItemRequest {
    private IdRequest order;
    private IdRequest product;
    private Integer quantity;
}
