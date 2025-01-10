package com.backend_ecommerce.backend_ecommerce.models.request;

import lombok.Data;

@Data
public class StockRequest {
    private Long productId;
    private Integer quantity;
}
