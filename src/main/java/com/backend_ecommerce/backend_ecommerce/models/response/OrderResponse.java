package com.backend_ecommerce.backend_ecommerce.models.response;

import java.time.LocalDateTime;

import com.backend_ecommerce.backend_ecommerce.models.utils.OrderStatus;

import lombok.Data;

@Data
public class OrderResponse {
    private Long id;
    private UserResponse user;
    private OrderStatus status;
    private LocalDateTime createAt;
}
