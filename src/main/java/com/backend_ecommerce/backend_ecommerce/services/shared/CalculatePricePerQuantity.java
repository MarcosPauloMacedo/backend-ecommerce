package com.backend_ecommerce.backend_ecommerce.services.shared;

import org.springframework.stereotype.Service;

@Service
public class CalculatePricePerQuantity {

    public Double execute(Integer quantity, Double price) {
        return quantity * price;
    }
}
