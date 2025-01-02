package com.backend_ecommerce.backend_ecommerce.models.response;

import lombok.Data;

@Data
public class OrderItemResponse {
    private Long id;
    private OrderResponse order;
    private ProductResponse product;
    private Integer quantity;
    private String totalPrice;
}
