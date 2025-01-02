package com.backend_ecommerce.backend_ecommerce.services.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatePricePerQuantityTest {

    private CalculatePricePerQuantity calculatePricePerQuantity;

    @BeforeEach
    void setUp() {
        calculatePricePerQuantity = new CalculatePricePerQuantity();
    }

    @Test
    void testExecute() {
        Integer quantity = 3;
        Double price = 50.0;
        Double result = calculatePricePerQuantity.execute(quantity, price);
        assertEquals(150.0, result);
    }
}
