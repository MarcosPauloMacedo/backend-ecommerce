package com.backend_ecommerce.backend_ecommerce.models.request;

import java.time.LocalDateTime;

import com.backend_ecommerce.backend_ecommerce.models.utils.OrderStatus;

import lombok.Data;

@Data
public class OrderRequest {
    private IdRequest user;
    private OrderStatus status;
    private LocalDateTime createAt;
}
