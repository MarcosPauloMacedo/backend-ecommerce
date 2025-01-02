package com.backend_ecommerce.backend_ecommerce.models.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderItemPageResponse extends PageResponse<OrderItemResponse> {
    private long totalItems;
    private String totalPrice;
}
